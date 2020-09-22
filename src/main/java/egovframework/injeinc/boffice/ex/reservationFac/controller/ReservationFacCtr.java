package egovframework.injeinc.boffice.ex.reservationFac.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.util.WebUtils;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.EgovWebUtil;
import egovframework.cmm.Message;
import egovframework.cmm.service.EgovProperties;
import egovframework.injeinc.boffice.ex.reservation.service.ReservationEventSvc;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationAddItemVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationIndexVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationUserAnswerVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationUserInfoVo;
import egovframework.injeinc.boffice.ex.reservationEtc.service.ReservationEtcSvc;
import egovframework.injeinc.boffice.ex.reservationFac.service.ReservationFacSvc;
import egovframework.injeinc.boffice.ex.reservationFac.vo.ReservationFacTeamVo;
import egovframework.injeinc.boffice.ex.reservationFac.vo.ReservationFacTimeVo;
import egovframework.injeinc.boffice.ex.reservationFac.vo.ReservationFacVo;
import egovframework.injeinc.boffice.ex.reservationHealth.service.ReservationHealthSvc;
import egovframework.injeinc.boffice.ex.reservationHealth.vo.ReservationAddNodateVo;
import egovframework.injeinc.boffice.ex.reservationHealth.vo.ReservationIndexHealthVo;
import egovframework.injeinc.boffice.excel.controller.ExcelCtr;
import egovframework.injeinc.boffice.excel.service.BizExcelSvc;
import egovframework.injeinc.boffice.login.vo.LoginVo;
import egovframework.injeinc.common.code.service.CodeSvc;
import egovframework.injeinc.common.code.vo.CodeVo;
import egovframework.injeinc.common.util.DateUtil;
import egovframework.injeinc.common.util.EgovStringUtil;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import lgdacom.XPayClient.XPayClient;

@Controller
public class ReservationFacCtr extends CmmLogCtr{
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationFacCtr.class);
	 @Resource(name = "CodeSvc")
	 private CodeSvc codeSvc;

	 @Resource(name = "ReservationFacSvc")
	 private ReservationFacSvc reservationFacSvc;
	 
	 @Resource(name = "ReservationHealthSvc")
	 private ReservationHealthSvc reservationHealthSvc;
	 
	 @Resource(name = "ReservationEventSvc")
	 private ReservationEventSvc reservationEventSvc;
	 
	 @Resource(name = "ReservationEtcSvc")
	 private ReservationEtcSvc reservationEtcSvc;

	 @Resource(name = "reservationIdGnrService")
	 private EgovIdGnrService reservationIdgenService;

	 @Resource(name = "reservationUserIdGnrService")
	 private EgovIdGnrService reservationUserIdGnrService;
	 
	 
	@Resource(name = "BizExcelService")
	private BizExcelSvc bizExcelService;

	@Autowired(required=true)
	private MappingJackson2JsonView jsonView;

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/boffice/ex/reservationFac/reservationFacList.do")
	public String reservationFacPag(
			HttpServletRequest request
			, @ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo
			, ModelMap model
			) throws Exception {

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(reservationIndexVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(reservationIndexVo.getPageUnit());
		paginationInfo.setPageSize(reservationIndexVo.getPageSize());

		reservationIndexVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		reservationIndexVo.setLastIndex(paginationInfo.getLastRecordIndex());
		reservationIndexVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		reservationIndexVo.setRiType("F");
		
		
		//게시물 권한 체크 - 최고관리자가 아니면 각부서에 해당하는 게시물만 보인다.
		HttpSession ses = request.getSession();
		LoginVo cmsUser = (LoginVo)ses.getAttribute("CMSUSER");
		if(!cmsUser.getPermCd().equals("05010000")){
			reservationIndexVo.setRiManageDept(cmsUser.getpDeptCd());
		}
		
		if(!cmsUser.getIsAdmin()){
			//lectureVo.setDeptCd(cmsUser.getpDeptCd());
			reservationIndexVo.setMgrSiteCdList(cmsUser.getMgrSiteCdList());
		}

		Map<String, Object> map = reservationFacSvc.retrievePagReservationFac(reservationIndexVo);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));

		paginationInfo.setTotalRecordCount(totCnt);
		if(model != null){
			model.addAttribute("resultList", map.get("resultList"));
			model.addAttribute("resultCnt", map.get("resultCnt"));
			model.addAttribute("paginationInfo", paginationInfo);
		}

		return "injeinc/boffice/ex/reservationFac/reservation_fac_list";
	}

	@RequestMapping("/boffice/ex/reservationFac/reservationFacForm.do")
	public String reservationFacForm(
			HttpServletRequest request
			, @ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo
			, ModelMap model
			) throws Exception {
		
		HttpSession ses = request.getSession();
		LoginVo cmsUser = (LoginVo)ses.getAttribute("CMSUSER");
		
		HashMap<String, String> param = new HashMap<String, String>();
		ReservationIndexHealthVo reservationIndexHealthVo = new ReservationIndexHealthVo();
		ReservationIndexVo result = reservationFacSvc.retrieveReservationFac(reservationIndexVo);

		if(result != null) {
			result.setActionkey("modify");
			result.setPageIndex(reservationIndexVo.getPageIndex());
			result.setSearchCondition(reservationIndexVo.getSearchCondition());
			result.setSearchKeyword(reservationIndexVo.getSearchKeyword());
			if(model != null){
				model.addAttribute("ReservationAddItemList", reservationFacSvc.retrieveListReservationFacAddItem(reservationIndexVo));
				reservationIndexHealthVo.setRiIdx(reservationIndexVo.getRiIdx());
				model.addAttribute("ReservationAddNodateList", reservationHealthSvc.retrieveListReservationHealtNodate(reservationIndexHealthVo));
				model.addAttribute("ReservationIndexVo", result);
			}
		}else{
			reservationIndexVo.setActionkey("regist");
		}

		HashMap<String, Object> serviceMap = codeSvc.retrieveDeptCodeAll(param);
		List<CodeVo> rowDataList = (List<CodeVo>) serviceMap.get("rowDataList");
		List resultList = reservationEtcSvc.retrieveListSuervisionOrg(cmsUser.getIsAdmin(), cmsUser.getMgrSiteCdList());
		
		List<ReservationFacTimeVo> resultTimeVo = reservationFacSvc.retrieveReservationFacTimeInfo(reservationIndexVo);
		model.addAttribute("resultListTimeVo", resultTimeVo);
		//관리부서목록
		List deptCode = reservationFacSvc.retrieveDeptCode();
		
		if(model != null){
			model.addAttribute("deptList", rowDataList);
			model.addAttribute("resultList", resultList);
			model.addAttribute("dept", deptCode);
		}

		return "injeinc/boffice/ex/reservationFac/reservation_fac_form";
	}

	@RequestMapping("/boffice/ex/reservationFac/reservationFacReg.do")
	public String reservationFacReg(
			final MultipartHttpServletRequest multiRequest
			, @ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo
			, ModelMap model
			) throws Exception {

		ReservationAddItemVo reservationAddItemVo;
		ReservationAddNodateVo reservationAddNodateVo;
		ReservationFacTimeVo reservationFacTimeVo;
		String riIdx = reservationIdgenService.getNextStringId();
		reservationIndexVo.setRiIdx(riIdx);
		reservationIndexVo.setRiType("F");

		String userid = (String) WebUtils.getSessionAttribute(multiRequest, "SesUserId");
		reservationIndexVo.setRegId(userid);
		String raContent[] = null;
		if(multiRequest != null){
			raContent = multiRequest.getParameterValues("raContent");
		}
		
		if(raContent != null){
			
			for(int i = 0; i < raContent.length; i++){
				reservationAddItemVo = new ReservationAddItemVo();
				
				reservationAddItemVo.setRiIdx(riIdx);
				reservationAddItemVo.setRaIdx(i+1);
				reservationAddItemVo.setRaContent(raContent[i]);
				reservationAddItemVo.setRegId(userid);
				
				reservationFacSvc.registReservationFacAddItem(reservationAddItemVo);
			}
		}
		
		//운영불가(휴무)일
		String noDateContent[] = multiRequest.getParameterValues("noDateContent");
		if(noDateContent != null){
			for(int i = 0; i < noDateContent.length; i++){
				reservationAddNodateVo = new ReservationAddNodateVo();
				
				reservationAddNodateVo.setRiIdx(riIdx);
				reservationAddNodateVo.setRaIdx(i+1);
				reservationAddNodateVo.setRaNodate(noDateContent[i]);
				reservationAddNodateVo.setRegId(userid);
				
				reservationHealthSvc.registReservationAddNodate(reservationAddNodateVo);
			}
		}
		
		/**회차 추가**/
		String[] rtWeek = reservationIndexVo.getRiDayWeek().split(",");
		String tempsttime = "";
		String tempedtime = "";
		System.out.println("rtWeek1 : "+reservationIndexVo.getRiDayWeek());
		System.out.println("rtWeek2 : "+rtWeek.length);
		if(rtWeek != null && rtWeek.length > 0 && !reservationIndexVo.getRiDayWeek().equals("")){
			for(int i = 0; i < rtWeek.length; i++){		
				System.out.println("rtWeek : "+rtWeek[i]);
				String[] stTime = multiRequest.getParameterValues("stTime"+rtWeek[i]);
				String[] etTime = multiRequest.getParameterValues("etTime"+rtWeek[i]);
				if(multiRequest.getParameter("rtmType"+rtWeek[i]).equals("D")){
					
					System.out.println(" stTime[0] : "+stTime[0]);
					System.out.println(" etTime[0] : "+etTime[0]);
					
					if(stTime[0].length() > 1 && stTime[0].substring(0,1).equals("0")){
						tempsttime = stTime[0].replace("0", "");
					}else{
						tempsttime = stTime[0];
					}
					if(etTime[0].length() > 1 && etTime[0].substring(0,1).equals("0")){
						tempedtime = etTime[0].replace("0", "");
					}else{
						tempedtime = etTime[0];
					}
					System.out.println(" tempsttime : "+tempsttime);
					System.out.println(" tempedtime : "+tempedtime);
					int cnt = 0;
					for(int k = Integer.parseInt(tempsttime); k < Integer.parseInt(tempedtime);k=k+(Integer.parseInt(multiRequest.getParameter("rtmTerm"+rtWeek[i])))){
						System.out.println(" k : "+k);
						reservationFacTimeVo = new ReservationFacTimeVo();
						if(k < 10){
							reservationFacTimeVo.setStTime("0"+k);
						}else{
							reservationFacTimeVo.setStTime(""+k);
						}
						
						if(k+ (Integer.parseInt(multiRequest.getParameter("rtmTerm"+rtWeek[i]))) < 10){
							reservationFacTimeVo.setEtTime("0"+(k+ (Integer.parseInt(multiRequest.getParameter("rtmTerm"+rtWeek[i])))));
						}else{
							reservationFacTimeVo.setEtTime(""+(k+ (Integer.parseInt(multiRequest.getParameter("rtmTerm"+rtWeek[i])))));
						}												
						reservationFacTimeVo.setRiIdx(riIdx);
						reservationFacTimeVo.setRtmIdx(cnt+1);
						reservationFacTimeVo.setRtmWeek(Integer.parseInt(rtWeek[i]));
					    reservationFacTimeVo.setRtmType(multiRequest.getParameter("rtmType"+rtWeek[i]));
					    reservationFacTimeVo.setRtmTerm(multiRequest.getParameter("rtmTerm"+rtWeek[i]));
					    
					    reservationFacSvc.registReservationFacTime(reservationFacTimeVo);
					    cnt++;
					}
				}else{
					if(stTime != null && stTime.length > 0){					
						for(int j = 0 ; j < stTime.length;j++){
							System.out.println("stTime : "+stTime[j]);
							System.out.println("etTime : "+etTime[j]);
							reservationFacTimeVo = new ReservationFacTimeVo();
							reservationFacTimeVo.setStTime(stTime[j]);
							reservationFacTimeVo.setEtTime(etTime[j]);
							reservationFacTimeVo.setRiIdx(riIdx);
							reservationFacTimeVo.setRtmIdx(j+1);
							reservationFacTimeVo.setRtmWeek(Integer.parseInt(rtWeek[i]));
						    reservationFacTimeVo.setRtmType(multiRequest.getParameter("rtmType"+rtWeek[i]));
						    reservationFacTimeVo.setRtmTerm(multiRequest.getParameter("rtmTerm"+rtWeek[i]));
						    
						    reservationFacSvc.registReservationFacTime(reservationFacTimeVo);
						}
					}
				}							
			}
		}
		
		reservationIndexVo.setRiDayWeek(reservationIndexVo.getRiDayWeek().replaceAll(",", "||"));

		// 파일업로드
		final Map<String, MultipartFile> files = multiRequest == null ? null : multiRequest.getFileMap();
		ReservationIndexVo uploadVO = new ReservationIndexVo();
    	uploadVO = reservationFacSvc.uploadFile(files, reservationIndexVo);

    	reservationIndexVo.setRiImageFileId(uploadVO.getRiImageFileId());

    	reservationIndexVo.setRiOffLotUse(reservationIndexVo.getRiOffLotUse().replaceAll(",", "||"));
		reservationIndexVo.setRiConfirmYn("Y");
		reservationIndexVo.setRiUserloginType("A");

		reservationFacSvc.registReservationFac(reservationIndexVo);

		String SVC_MSGE = Message.getMessage("100.message");
		return alert("/boffice/ex/reservationFac/reservationFacList.do", SVC_MSGE, model);
	}

	@RequestMapping("/boffice/ex/reservationFac/reservationFacMod.do")
	public String reservationFacMod(
			final MultipartHttpServletRequest multiRequest
			, @ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo
			, ModelMap model
			) throws Exception {

		ReservationAddItemVo reservationAddItemVo;
		ReservationAddNodateVo reservationAddNodateVo;		
		ReservationFacTimeVo reservationFacTimeVo;
		ReservationIndexHealthVo reservationIndexHealthVo = new ReservationIndexHealthVo();

		String userid = (String) WebUtils.getSessionAttribute(multiRequest, "SesUserId");
		reservationIndexVo.setModId(userid);
		String raContent[] = null;
		if(multiRequest != null){
			raContent = multiRequest.getParameterValues("raContent");
		}
		reservationFacSvc.removeReservationFacAddItem(reservationIndexVo);
		if(raContent != null){
			
			for(int i = 0; i < raContent.length; i++){
				reservationAddItemVo = new ReservationAddItemVo();
				
				reservationAddItemVo.setRiIdx(reservationIndexVo.getRiIdx());
				reservationAddItemVo.setRaIdx(i+1);
				reservationAddItemVo.setRaContent(raContent[i]);
				reservationAddItemVo.setRegId(userid);
				
				reservationFacSvc.registReservationFacAddItem(reservationAddItemVo);
			}
		}
		
		//운영불가(휴무)일 전체 삭제
		reservationIndexHealthVo.setRiIdx(reservationIndexVo.getRiIdx());
		reservationHealthSvc.removeReservationHealthNodate(reservationIndexHealthVo);
		//운영불가(휴무)일
		String noDateContent[] = multiRequest.getParameterValues("noDateContent");
		if(noDateContent != null){
			for(int i = 0; i < noDateContent.length; i++){
				reservationAddNodateVo = new ReservationAddNodateVo();
				
				reservationAddNodateVo.setRiIdx(reservationIndexVo.getRiIdx());
				reservationAddNodateVo.setRaIdx(i+1);
				reservationAddNodateVo.setRaNodate(noDateContent[i]);
				reservationAddNodateVo.setRegId(userid);
				
				reservationHealthSvc.registReservationAddNodate(reservationAddNodateVo);
			}
		}
				
		reservationFacSvc.removeReservationFacTime(reservationIndexVo);
		/**회차 추가**/
		String[] rtWeek = reservationIndexVo.getRiDayWeek().split(",");
		String tempsttime = "";
		String tempedtime = "";
		
		if(rtWeek != null && rtWeek.length > 0 && reservationIndexVo.getRiOffLotUse().indexOf("online") != -1){
			for(int i = 0; i < rtWeek.length; i++){		
				System.out.println("rtWeek : "+rtWeek[i]);
				String[] stTime = multiRequest.getParameterValues("stTime"+rtWeek[i]);
				String[] etTime = multiRequest.getParameterValues("etTime"+rtWeek[i]);
				if(multiRequest.getParameter("rtmType"+rtWeek[i]).equals("D")){
					
					System.out.println(" stTime[0] : "+stTime[0]);
					System.out.println(" etTime[0] : "+etTime[0]);
					
					if(stTime[0].length() > 1 && stTime[0].substring(0,1).equals("0")){
						tempsttime = stTime[0].replace("0", "");
					}else{
						tempsttime = stTime[0];
					}
					if(etTime[0].length() > 1 && etTime[0].substring(0,1).equals("0")){
						tempedtime = etTime[0].replace("0", "");
					}else{
						tempedtime = etTime[0];
					}
					System.out.println(" tempsttime : "+tempsttime);
					System.out.println(" tempedtime : "+tempedtime);
					int cnt = 0;
					for(int k = Integer.parseInt(tempsttime); k < Integer.parseInt(tempedtime);k=k+(Integer.parseInt(multiRequest.getParameter("rtmTerm"+rtWeek[i])))){
						System.out.println(" k : "+k);
						reservationFacTimeVo = new ReservationFacTimeVo();
						if(k < 10){
							reservationFacTimeVo.setStTime("0"+k);
						}else{
							reservationFacTimeVo.setStTime(""+k);
						}
						
						if(k+ (Integer.parseInt(multiRequest.getParameter("rtmTerm"+rtWeek[i]))) < 10){
							reservationFacTimeVo.setEtTime("0"+(k+ (Integer.parseInt(multiRequest.getParameter("rtmTerm"+rtWeek[i])))));
						}else{
							reservationFacTimeVo.setEtTime(""+(k+ (Integer.parseInt(multiRequest.getParameter("rtmTerm"+rtWeek[i])))));
						}												
						reservationFacTimeVo.setRiIdx(reservationIndexVo.getRiIdx());
						reservationFacTimeVo.setRtmIdx(cnt+1);
						reservationFacTimeVo.setRtmWeek(Integer.parseInt(rtWeek[i]));
					    reservationFacTimeVo.setRtmType(multiRequest.getParameter("rtmType"+rtWeek[i]));
					    reservationFacTimeVo.setRtmTerm(multiRequest.getParameter("rtmTerm"+rtWeek[i]));
					    
					    reservationFacSvc.registReservationFacTime(reservationFacTimeVo);
					    cnt++;
					}
				}else{
					if(stTime != null && stTime.length > 0){					
						for(int j = 0 ; j < stTime.length;j++){
							System.out.println("stTime : "+stTime[j]);
							System.out.println("etTime : "+etTime[j]);
							reservationFacTimeVo = new ReservationFacTimeVo();
							reservationFacTimeVo.setStTime(stTime[j]);
							reservationFacTimeVo.setEtTime(etTime[j]);
							reservationFacTimeVo.setRiIdx(reservationIndexVo.getRiIdx());
							reservationFacTimeVo.setRtmIdx(j+1);
							reservationFacTimeVo.setRtmWeek(Integer.parseInt(rtWeek[i]));
						    reservationFacTimeVo.setRtmType(multiRequest.getParameter("rtmType"+rtWeek[i]));
						    reservationFacTimeVo.setRtmTerm(multiRequest.getParameter("rtmTerm"+rtWeek[i]));
						    
						    reservationFacSvc.registReservationFacTime(reservationFacTimeVo);
						}
					}
				}							
			}
		}

		reservationIndexVo.setRiDayWeek(reservationIndexVo.getRiDayWeek().replaceAll(",", "||"));

		// 파일업로드
		final Map<String, MultipartFile> files = multiRequest == null ? null : multiRequest.getFileMap();
		ReservationIndexVo uploadVO = new ReservationIndexVo();
    	uploadVO = reservationFacSvc.uploadFile(files, reservationIndexVo);

    	reservationIndexVo.setRiImageFileId(uploadVO.getRiImageFileId());

		reservationFacSvc.modifyReservationFac(reservationIndexVo);

		StringBuffer addParam = new StringBuffer();
		addParam.append("?pageIndex=").append(reservationIndexVo.getPageIndex());
		addParam.append("&searchCondition=").append(reservationIndexVo.getSearchCondition());
		addParam.append("&searchKeyword=").append(reservationIndexVo.getSearchKeyword());

		String SVC_MSGE = Message.getMessage("401.message");
		return alert("/boffice/ex/reservationFac/reservationFacList.do"+addParam.toString(), SVC_MSGE, model);
	}

	@RequestMapping("/boffice/ex/reservationFac/reservationFacRmv.do")
	public String reservationFacRmv(
			HttpServletRequest request
			, @ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo
			, ModelMap model
			) throws Exception {

		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		reservationIndexVo.setModId(userid);

		reservationFacSvc.removeReservationFac(reservationIndexVo);

		StringBuffer addParam = new StringBuffer();
		addParam.append("?pageIndex=").append(reservationIndexVo.getPageIndex());
		addParam.append("&searchCondition=").append(reservationIndexVo.getSearchCondition());
		addParam.append("&searchKeyword=").append(reservationIndexVo.getSearchKeyword());

		String SVC_MSGE = Message.getMessage("501.message");
		return alert("/boffice/ex/reservationFac/reservationFacList.do"+addParam.toString(), SVC_MSGE, model);
	}

	@RequestMapping("/boffice/ex/reservationFac/reservationFacView.do")
	public String reservationFacView(
			HttpServletRequest request
			, @ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo
			, @ModelAttribute("ReservationFacVo") ReservationFacVo reservationFacVo
			, ModelMap model
			) throws Exception {


		ReservationIndexVo result = reservationFacSvc.retrieveReservationFac(reservationIndexVo);		
		
		Calendar cal = Calendar.getInstance();

		if(reservationFacVo.getYear()==null || reservationFacVo.getYear().equals("")){
			reservationFacVo.setYear(Integer.toString(cal.get(Calendar.YEAR)));
		}
		if(reservationFacVo.getMonth()==null || reservationFacVo.getMonth().equals("")){
			reservationFacVo.setMonth(Integer.toString(cal.get(Calendar.MONTH)+1));
		}
		int iYear  = Integer.parseInt(reservationFacVo.getYear());
		int iMonth = Integer.parseInt(reservationFacVo.getMonth());

		if (iMonth<1){
			iYear--;
			iMonth = 12;
		}
		if (iMonth>12){
			iYear++;
			iMonth = 1;
		}
		if (iYear<1){
			iYear = 1;
			iMonth = 1;
		}
		if (iYear>9999){
			iYear = 9999;
			iMonth = 12;
		}
		reservationFacVo.setYear(Integer.toString(iYear));
		reservationFacVo.setMonth(Integer.toString(iMonth));

		cal.set(iYear,iMonth-1,1);

		reservationFacVo.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		reservationFacVo.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));

		List CalInfoList = reservationFacSvc.retrieveListReservationFacCal(reservationFacVo);		
		String addcnt = reservationFacSvc.retrieveReservationFacAddCnt(reservationIndexVo);

		result.setPageIndex(reservationIndexVo.getPageIndex());
		result.setSearchCondition(reservationIndexVo.getSearchCondition());
		result.setSearchKeyword(reservationIndexVo.getSearchKeyword());
		
		model.addAttribute("ReservationIndexVo", result);
		model.addAttribute("CalInfoList", CalInfoList);
		model.addAttribute("ReservationAddCnt",addcnt);

		return "injeinc/boffice/ex/reservationFac/reservation_fac_view";
	}

	@RequestMapping("/boffice/ex/reservationFac/reservationFacUserFormView.do")
	public String reservationFacUserFormView(
			HttpServletRequest request
			, @ModelAttribute("ReservationFacVo") ReservationFacVo reservationFacVo
			, ModelMap model
			) throws Exception {

		ReservationIndexVo reservationIndexVo = new ReservationIndexVo();
		ReservationFacTimeVo reservationFacTimeVo = new ReservationFacTimeVo();

		reservationIndexVo.setRiIdx(reservationFacVo.getRiIdx());
		
		String ruReservationDay = reservationFacVo.getYear();
		if(reservationFacVo.getMonth().length() >1){
			ruReservationDay += "-"+reservationFacVo.getMonth();
		}else{
			ruReservationDay += "-0"+reservationFacVo.getMonth();
		}
		if(reservationFacVo.getDay().length() >1){
			ruReservationDay += "-"+reservationFacVo.getDay();
		}else{
			ruReservationDay += "-0"+reservationFacVo.getDay();
		}

		reservationFacVo.setRuReservationDay(ruReservationDay);

		if(reservationFacVo.getRaIdx() == null || reservationFacVo.getRaIdx().equals("")){
			reservationFacVo.setRaIdx("1");
		}
		reservationIndexVo = reservationFacSvc.retrieveReservationFac(reservationIndexVo);
		List<ReservationUserInfoVo> userList = reservationFacSvc.retrieveListReservationFacUserInfo(reservationFacVo);
		List<ReservationUserInfoVo> returnUserList = reservationFacSvc.retrieveListReservationFacReturnFee(reservationFacVo);
		List<ReservationFacTimeVo>  timeList = reservationFacSvc.retrieveReservationFacUserTimeInfo(reservationFacVo);
		if(model != null){
			model.addAttribute("ReservationAddItemList", reservationFacSvc.retrieveListReservationFacAddItem(reservationIndexVo));
			model.addAttribute("ReservationIndexVo", reservationIndexVo);
			model.addAttribute("ReservationFacTimeVo", reservationFacTimeVo);
			model.addAttribute("userList", userList);
			model.addAttribute("returnUserList", returnUserList);
			model.addAttribute("timeList", timeList);
		}

		return "injeinc/boffice/ex/reservationFac/reservation_fac_user_form_view";
	}

	@RequestMapping("/boffice/ex/reservationFac/reservationFacUserForm.do")
	public String reservationFacUserForm(
			HttpServletRequest request
			, @ModelAttribute("ReservationFacVo") ReservationFacVo reservationFacVo
			, @ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo
			, ModelMap model
			) throws Exception {

		ReservationIndexVo reservationIndexVo = new ReservationIndexVo();
		ReservationFacTimeVo reservationFacTimeVo = new ReservationFacTimeVo();
				
		reservationIndexVo.setRiIdx(reservationUserInfoVo.getRiIdx());
		String ruReservationDay = "";
		if(reservationFacVo != null){
			ruReservationDay = reservationFacVo.getYear();
		}
		if(reservationFacVo != null){
			if(reservationFacVo.getMonth().length() >1){
				ruReservationDay += "-"+reservationFacVo.getMonth();
			}else{
				ruReservationDay += "-0"+reservationFacVo.getMonth();
			}
			if(reservationFacVo.getDay().length() >1){
				ruReservationDay += "-"+reservationFacVo.getDay();
			}else{
				ruReservationDay += "-0"+reservationFacVo.getDay();
			}
		}
		reservationFacVo.setRuReservationDay(ruReservationDay);

		reservationIndexVo = reservationFacSvc.retrieveReservationFac(reservationIndexVo);
		ReservationUserInfoVo result = reservationFacSvc.retrieveReservationFacUserInfo(reservationUserInfoVo);				

		if(result != null) {
			reservationIndexVo.setActionkey("modify");
			if(model != null){
				List<ReservationFacTimeVo>  timeList = reservationFacSvc.retrieveReservationFacUserTimeInfo4(reservationUserInfoVo);
				model.addAttribute("timeList", timeList);
				model.addAttribute("ReservationUserInfoVo", result);
			}
		}else{
			List<ReservationFacTimeVo>  timeList = reservationFacSvc.retrieveReservationFacUserTimeInfo2(reservationFacVo);
			model.addAttribute("timeList", timeList);
			reservationIndexVo.setActionkey("regist");
		}

		reservationIndexVo.setRuIdx(reservationUserInfoVo.getRuIdx());
		reservationUserInfoVo.setRaIdx(reservationFacVo.getRaIdx());
		reservationUserInfoVo.setRuReservationDay(ruReservationDay);
		if(model != null){
			model.addAttribute("ReservationIndexVo", reservationIndexVo);			
			model.addAttribute("ReservationFacTimeVo", reservationIndexVo);
			model.addAttribute("ReservationAddItemList", reservationFacSvc.retrieveListReservationFacAddItem(reservationIndexVo));
		}

		return "injeinc/boffice/ex/reservationFac/reservation_fac_user_form";
	}
	
	@RequestMapping("/boffice/ex/reservationFac/reservationFacUserReturnForm.do")
	public String reservationFacUserReturnForm(
			HttpServletRequest request
			, @ModelAttribute("ReservationFacVo") ReservationFacVo reservationFacVo
			, @ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo
			, ModelMap model
			) throws Exception {

		ReservationIndexVo reservationIndexVo = new ReservationIndexVo();
		ReservationFacTimeVo reservationFacTimeVo = new ReservationFacTimeVo();
				
		reservationIndexVo.setRiIdx(reservationUserInfoVo.getRiIdx());
		String ruReservationDay = "";
		if(reservationFacVo != null){
			ruReservationDay = reservationFacVo.getYear();
		}
		if(reservationFacVo != null){
			if(reservationFacVo.getMonth().length() >1){
				ruReservationDay += "-"+reservationFacVo.getMonth();
			}else{
				ruReservationDay += "-0"+reservationFacVo.getMonth();
			}
			if(reservationFacVo.getDay().length() >1){
				ruReservationDay += "-"+reservationFacVo.getDay();
			}else{
				ruReservationDay += "-0"+reservationFacVo.getDay();
			}
		}
		reservationFacVo.setRuReservationDay(ruReservationDay);

		reservationIndexVo = reservationFacSvc.retrieveReservationFac(reservationIndexVo);
		ReservationUserInfoVo result = reservationFacSvc.retrieveReservationFacUserInfoReturnFee(reservationUserInfoVo);				

		List<ReservationFacTimeVo>  timeList = reservationFacSvc.retrieveReservationFacUserTimeInfo4(reservationUserInfoVo);
		model.addAttribute("timeList", timeList);
		model.addAttribute("ReservationUserInfoVo", result);

		reservationIndexVo.setRuIdx(reservationUserInfoVo.getRuIdx());
		reservationUserInfoVo.setRaIdx(reservationFacVo.getRaIdx());
		reservationUserInfoVo.setRuReservationDay(ruReservationDay);
		if(model != null){
			model.addAttribute("ReservationIndexVo", reservationIndexVo);			
			model.addAttribute("ReservationFacTimeVo", reservationIndexVo);
			model.addAttribute("ReservationAddItemList", reservationFacSvc.retrieveListReservationFacAddItem(reservationIndexVo));
		}

		return "injeinc/boffice/ex/reservationFac/reservation_fac_user_return_form";
	}

	@RequestMapping("/boffice/ex/reservationFac/reservationFacUserInfoReg.do")
	public String reservationUserInfoReg(
			HttpServletRequest request
			, @ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo
			, @ModelAttribute("ReservationUserAnswerVo") ReservationUserAnswerVo reservationUserAnswerVo
			, ModelMap model
			) throws Exception {

		ReservationIndexVo reservationIndexVo = new ReservationIndexVo();
		String riIdx = "";

		if(reservationFacSvc.retrieveReservationFacUserInfoDupCnt(reservationUserInfoVo) > 0){
			return alert("/boffice/ex/reservationFac/reservationFacView.do?riIdx="+reservationUserInfoVo.getRiIdx(), "이미 예약을 하셨습니다.", model);
		}else{
			riIdx = reservationUserInfoVo.getRiIdx();
			reservationIndexVo.setRiIdx(riIdx);
			



			String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
			reservationUserInfoVo.setRegId(userid);

			ReservationIndexVo result = reservationFacSvc.retrieveReservationFac(reservationIndexVo);

			if(result.getRiLotUse().equals("OO")){
				reservationUserInfoVo.setRuLotResult("S");
			}else{
				reservationUserInfoVo.setRuLotResult("R");
			}
			String ruIdx = "";
			reservationUserInfoVo.setRuIdx(ruIdx);
			if(reservationUserInfoVo.getRtmType().equals("D")){				
				int start = Integer.parseInt(reservationUserInfoVo.getStfacTime());
				int end = Integer.parseInt(reservationUserInfoVo.getEdfacTime());
				String strrtmidx = "";
				int s = 0;
				for(int i = start; i <= end; i++){	
					if(s == 0){
						strrtmidx = strrtmidx + i;
					}else{
						strrtmidx = strrtmidx +","+ i;
					}						
					s++;
				}
				ruIdx = reservationUserIdGnrService.getNextStringId();
				reservationUserInfoVo.setRtmIdx(strrtmidx);		
				reservationUserInfoVo.setRuIdx(ruIdx);
				reservationFacSvc.registReservationFacUserInfo(reservationUserInfoVo);
			}else{
				ruIdx = reservationUserIdGnrService.getNextStringId();					
				reservationUserInfoVo.setRuIdx(ruIdx);
				reservationUserInfoVo.setRtmIdx(reservationUserInfoVo.getStfacTime());
				reservationFacSvc.registReservationFacUserInfo(reservationUserInfoVo);
			}
			
			
		}

		String SVC_MSGE = Message.getMessage("100.message");
		return alert("/boffice/ex/reservationFac/reservationFacView.do?riIdx="+riIdx, SVC_MSGE, model);
	}

	@RequestMapping("/boffice/ex/reservationFac/reservationFacUserInfoMod.do")
	public String reservationUserInfoMod(
			HttpServletRequest request
			, @ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo
			, ModelMap model
			) throws Exception {

		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		reservationUserInfoVo.setModId(userid);

		reservationFacSvc.modifyReservationFacUserInfo(reservationUserInfoVo);

		String SVC_MSGE = Message.getMessage("100.message");
		return alert("/boffice/ex/reservationFac/reservationFacView.do?riIdx="+reservationUserInfoVo.getRiIdx(), SVC_MSGE, model);
	}

	/* 신청자 추첨 */
	@RequestMapping("/boffice/ex/reservationFac/reservationFacLot.do")
	public String reservationEventLot(
			HttpServletRequest request
			, @ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo
			, @ModelAttribute("ReservationFacVo") ReservationFacVo reservationFacVo
			, ModelMap model
			, String[] ruIdx
			) throws Exception {

		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		reservationIndexVo.setModId(userid);
		reservationFacVo.setRaIdx(EgovStringUtil.nullConvert(request.getParameter("facType")));

		int r = (int) (Math.random() * ruIdx.length);

		reservationIndexVo.setRuIdx(ruIdx[r]);

		reservationFacSvc.updateReservationFacLot(reservationIndexVo);

		return alert("/boffice/ex/reservationFac/reservationFacUserFormView.do?riIdx="+reservationIndexVo.getRiIdx()+"&year="+reservationFacVo.getYear()+"&month="+reservationFacVo.getMonth()+"&day="+reservationFacVo.getDay()+"&raIdx="+reservationFacVo.getRaIdx(), "추첨완료", model);
	}

	/* 신청자 삭제 */
	@RequestMapping("/boffice/ex/reservationFac/reservationFacUserInfoRmv.do")
	public String reservationEventUserInfoRmv(
			HttpServletRequest request
			, @ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo
			, ModelMap model
			) throws Exception {

		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		reservationUserInfoVo.setModId(userid);

		reservationFacSvc.removeReservationFacUserInfo(reservationUserInfoVo);

		return alert("/boffice/ex/reservationFac/reservationFacView.do?riIdx="+reservationUserInfoVo.getRiIdx(), "접수자 삭제완료", model);
	}
	
	@RequestMapping("/boffice/ex/reservationFac/reservationFacUserInfoConfirm.do")
	public void reservationFacUserInfoConfirm(
			HttpServletRequest request
			,HttpServletResponse response
			, @ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo			
			) throws Exception {

		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		reservationUserInfoVo.setModId(userid);		
		reservationFacSvc.modReservationFacUserConfirm(reservationUserInfoVo);

		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", true);
		jsonMap.put("message", "승인되었습니다");
		jsonView.render(jsonMap, request, response);
	}		

	@RequestMapping("/boffice/ex/reservationFac/reservationFacExcel_bak.do")
	public void webzineExcel(
			@ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo
			, @ModelAttribute("ReservationFacVo") ReservationFacVo reservationFacVo
			, ModelMap model
			,HttpServletRequest request
			,HttpServletResponse response
			) throws Exception {
		String ruReservationDay = reservationFacVo.getYear();
		if(reservationFacVo.getMonth().length() >1){
			ruReservationDay += "-"+reservationFacVo.getMonth();
		}else{
			ruReservationDay += "-0"+reservationFacVo.getMonth();
		}
		if(reservationFacVo.getDay().length() >1){
			ruReservationDay += "-"+reservationFacVo.getDay();
		}else{
			ruReservationDay += "-0"+reservationFacVo.getDay();
		}

		reservationFacVo.setRuReservationDay(ruReservationDay);

		List<ReservationUserInfoVo> result = bizExcelService.selectList("ReservationFacDao.selectListReservationFacUserInfo", reservationFacVo);


		String createString = "reservationFac_" + EgovStringUtil.getTimeStamp() +".xls";

		String storePathString = Message.getMessage("reservationFac.file.upload.path");
		File saveFolder = new File(EgovWebUtil.filePathBlackList(storePathString));

		if (!saveFolder.exists() || saveFolder.isFile()) {
		    saveFolder.mkdirs();
		}

		String filePath = storePathString + createString;
		//System.out.println("filePath : " + filePath);
		File path = new File(EgovWebUtil.filePathBlackList(filePath)); //출력할 엑셀의 파일명.

		String sheetName="";
		String fileName ="";
		String[][] data = new String[result.size()+1][8];


		data[0][0] = "번호";
		data[0][1] = "이름";
		data[0][2] = "주소";
		data[0][3] = "전화번호";
		data[0][4] = "핸드폰번호";
		data[0][5] = "이메일";
		data[0][6] = "신청자(단체)명";
		data[0][7] = "추첨";
		for(int i=0;i<result.size();i++){
			ReservationUserInfoVo resultVO = (ReservationUserInfoVo)result.get(i);
			data[i+1][0] = String.valueOf(result.size()-i);
			data[i+1][1] = resultVO.getRuName();
			data[i+1][2] = resultVO.getRuAddress();
			data[i+1][3] = resultVO.getRuTel();
			data[i+1][4] = resultVO.getRuHp();
			data[i+1][5] = resultVO.getRuEmail();
			data[i+1][6] = resultVO.getRuReservationName();
			if(resultVO.getRuLotResult().equals("C")){
				data[i+1][7] = "추첨대기";
			}else if(resultVO.getRuLotResult().equals("S")){
				data[i+1][7] = "추첨확정";
			}else{
				data[i+1][7] = "신청";
			}
		}
		sheetName = "시설예약신청자목록";
		/** 다운로드 파일명*/
		fileName = "시설예약신청자목록_" + EgovStringUtil.getTimeStamp();

		short[] halign = new short[] {HSSFCellStyle.ALIGN_CENTER, HSSFCellStyle.ALIGN_LEFT, HSSFCellStyle.ALIGN_LEFT, HSSFCellStyle.ALIGN_LEFT, HSSFCellStyle.ALIGN_LEFT}; 			//정렬
		short[] valign = new short[] {HSSFCellStyle.VERTICAL_CENTER, HSSFCellStyle.VERTICAL_CENTER, HSSFCellStyle.VERTICAL_CENTER, HSSFCellStyle.VERTICAL_CENTER, HSSFCellStyle.VERTICAL_CENTER}; //정렬

		try {
			ExcelCtr.writeToExcel(path , data , sheetName , fileName , halign , valign, request, response);		
		} catch (IOException e) {
			path.delete();
			LOGGER.debug("IGNORED: "+e.getMessage());
		} finally{
			path.delete();
		}
	}
	
	@RequestMapping("/boffice/ex/reservationFac/reservationFacExcel.do")
	public String reservationFacExcel(
			@ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo
			, @ModelAttribute("ReservationFacVo") ReservationFacVo reservationFacVo
			, ModelMap model
			,HttpServletRequest request
			,HttpServletResponse response
			) throws Exception {
		String ruReservationDay = reservationFacVo.getYear();
		if(reservationFacVo.getMonth().length() >1){
			ruReservationDay += "-"+reservationFacVo.getMonth();
		}else{
			ruReservationDay += "-0"+reservationFacVo.getMonth();
		}
		if(reservationFacVo.getDay().length() >1){
			ruReservationDay += "-"+reservationFacVo.getDay();
		}else{
			ruReservationDay += "-0"+reservationFacVo.getDay();
		}

		reservationFacVo.setRuReservationDay(ruReservationDay);

		List<ReservationUserInfoVo> resultList = bizExcelService.selectList("ReservationFacDao.selectListReservationFacUserInfo", reservationFacVo);
		if(model != null){
			model.addAttribute("resultList", resultList);
		}
		
		String nowDate = DateUtil.getCurrentDate("yyyyMMdd");
		//다운로드 파일명
		String clName = "시설예약신청자목록";
		String fileName = clName+"_"+nowDate+".xls";
		
		String encodedFilename = URLEncoder.encode(fileName, "UTF-8");
		
		request.setCharacterEncoding("utf-8");
		response.setHeader("Content-Disposition", "attachment; filename="+encodedFilename);
		response.setHeader("Content-Description", "JSP Generated Data");
		response.setContentType("application/vnd.ms-excel");	

		return "injeinc/boffice/ex/reservationFac/reservation_fac_excel";
	}
	
	/** 환불처리 프로세스 */
	/** 수강취소_환불포함 */
		
	@RequestMapping(value="/boffice/ex/reservationFac/reservationFacUserReturnFee.do", produces="application/json;charset=UTF-8")
	@ResponseBody
	public String reservationFacUserReturnFee(
			HttpServletRequest request
			, @ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo
			, ModelMap model
			) throws Exception {

		ReservationIndexVo reservationIndexVo = new ReservationIndexVo();

		reservationIndexVo.setRiIdx(reservationUserInfoVo.getRiIdx());

		reservationIndexVo = reservationEventSvc.retrieveReservationEvent(reservationIndexVo);
		ReservationUserInfoVo result = reservationEventSvc.retrieveReservationEventUserInfoReturnFee(reservationUserInfoVo);
		String SVC_MSGE = "";
		Calendar rightNow = Calendar.getInstance();
    	String ryear  = Integer.toString(rightNow.get(Calendar.YEAR));
    	String rmonth = Integer.toString(rightNow.get(Calendar.MONTH)+1);
    	String rday   = Integer.toString(rightNow.get(Calendar.DAY_OF_MONTH));
    	String rhour  = Integer.toString(rightNow.get(Calendar.HOUR_OF_DAY));
    	String rmin	  = Integer.toString(rightNow.get(Calendar.MINUTE));
    	String rnow   = ryear+ "-" + rmonth + "-" + rday + " " + rhour +" : "+ rmin;  
    	EgovMap map = reservationEventSvc.selectPgInfo(reservationUserInfoVo);
		if(result.getPayTid() != null && !"".equals(result.getPayTid()) && (!"0".equals(result.getPayPrice()) && !"".equals(result.getPayPrice()))){
			
			
	    	
			//결제취소
			boolean resultBl = false;
			String message = "";
			
			
			if(result.getMethod().equals("SC0010") || result.getMethod().equals("SC0030")) {
				
				/*
				 * [결제취소 요청 페이지]
				 *
				 * LG유플러스으로 부터 내려받은 거래번호(LGD_TID)를 가지고 취소 요청을 합니다.
				 * (파라미터 전달시 POST를 사용하세요)
				 * (승인시 LG유플러스으로 부터 내려받은 PAYKEY와 혼동하지 마세요.)
				 */
				String payType = "";
				//TODO jboa86 lgdacom 임시로 test 설정
				//LG유플러스 결제서비스 선택(test:테스트, service:서비스)
				String CST_PLATFORM		= "test";
				//LG유플러스으로 부터 발급받으신 상점아이디를 입력하세요.
				String CST_MID			= "t"+map.get("cstMid");										
				//테스트 아이디는 't'를 제외하고 입력하세요.
				String LGD_MID			= "t"+map.get("cstMid");
				//LG유플러스으로 부터 내려받은 거래번호(LGD_TID)
				String LGD_TID			= result.getPayTid();
				
				/*String configPath = "C:/eGovFrameDev-2.7.1-64bit/workspace/SCGHOME/src/main/webapp/WEB-INF/jsp/injeinc/"+pg.getLgdacomConfPath()+"/lgdacom";*/					
				//String configPath 		= "/was/schome/WEB-INF/jsp/injeinc/"+pg.getLgdacomConfPath()+"/lgdacom"; //LG유플러스에서 제공한 환경파일("/conf/lgdacom.conf,/conf/mall.conf") 위치 지정.
				/* ※ 중요
				 * 환경설정 파일의 경우 반드시 외부에서 접근이 가능한 경로에 두시면 안됩니다.
				 * 해당 환경파일이 외부에 노출이 되는 경우 해킹의 위험이 존재하므로 반드시 외부에서 접근이 불가능한 경로에 두시기 바랍니다. 
				 * 예) [Window 계열] C:\inetpub\wwwroot\lgdacom ==> 절대불가(웹 디렉토리)
				 */
				
				String configPath 		= EgovProperties.getProperty("lgdacom.configPath"); //LG유플러스에서 제공한 환경파일("/conf/lgdacom.conf,/conf/mall.conf") 위치 지정.
				
				LGD_TID					= EgovStringUtil.isNullToString(LGD_TID); 
				
				XPayClient xpay = new XPayClient();
				xpay.Init(configPath, CST_PLATFORM);
				xpay.Init_TX(LGD_MID);
				xpay.Set("LGD_TID", LGD_TID);
				
				//취소
				
				xpay.Set("LGD_TXNAME", "Cancel");
				
				
				/*
				 * 1. 결제취소 요청 결과처리
				 *
				 * 취소결과 리턴 파라미터는 연동메뉴얼을 참고하시기 바랍니다.
				 *
				 * [[[중요]]] 고객사에서 정상취소 처리해야할 응답코드
				 * 1. 신용카드 : 0000, AV11  
				 * 2. 계좌이체 : 0000, RF00, RF10, RF09, RF15, RF19, RF23, RF25 (환불진행중 응답건-> 환불결과코드.xls 참고)
				 * 3. 나머지 결제수단의 경우 0000(성공) 만 취소성공 처리
				 *
				 */
				
				if (xpay.TX()) {
					
					System.out.println("결제 취소요청이 완료되었습니다.");
					System.out.println( "TX Response_code = " + xpay.m_szResCode);
					System.out.println( "TX Response_msg = " + xpay.m_szResMsg);
					
					// 신용카드 
					if(result.getMethod().equals("SC0010")
							&& (xpay.m_szResCode.equals("0000") || xpay.m_szResCode.equals("AV11"))) {
						
						resultBl = true;
						payType = "신용카드";
					// 신용카드
					}else if(result.getMethod().equals("SC0030")
							&& (xpay.m_szResCode.equals("0000")
									|| xpay.m_szResCode.equals("RF00")
									|| xpay.m_szResCode.equals("RF10")
									|| xpay.m_szResCode.equals("RF09")
									|| xpay.m_szResCode.equals("RF15")
									|| xpay.m_szResCode.equals("RF19")
									|| xpay.m_szResCode.equals("RF23")
									|| xpay.m_szResCode.equals("RF25"))) {
						
						resultBl = true;
						payType = "계좌이체";
					// 그외	
					}else if(xpay.m_szResCode.equals("0000")){
						
						resultBl = true;
						
					}
					
					if(resultBl) {
						//1)결제취소결과 화면처리(성공,실패 결과 처리를 하시기 바랍니다.)
						
						String canReason = "관리자가 취소버튼클릭 100%환불";
						StringBuffer pay_memo = new StringBuffer();
						pay_memo.append("결제자동확인 : 결제확인시간(");
						pay_memo.append(rnow);
						pay_memo.append(")<br>");
						pay_memo.append("거래종류 : ");		    	
						pay_memo.append(payType);
						pay_memo.append("<br>");
						pay_memo.append("취소코드 : ");
						pay_memo.append(xpay.Response("LGD_RESPCODE",0));
						pay_memo.append("<br>");
						pay_memo.append("취소메세지 : ");
						pay_memo.append(xpay.Response("LGD_RESPMSG",0));
						
						reservationUserInfoVo.setPayTid(result.getPayTid());
						reservationUserInfoVo.setPayAuth("");
						reservationUserInfoVo.setMethod(result.getMethod());
						reservationUserInfoVo.setPayMemo(pay_memo.toString());
						reservationUserInfoVo.setPayStatus("5");	
						reservationUserInfoVo.setCanReason(canReason);
						
						/*최종결제요청 결과 실패 DB처리하시기 바랍니다.*/
					    
					    /*최종결제 완료후 현재 페이지 새로 고침하거나 백스페이스 누르면 자동 아래구문 자동 업데이트 방지
					     * 취소단계는 PG사 관리자 페이지 관리업체가 수동 취소한다고 함. 
					     */				
						
						reservationEventSvc.modReservationEventReturn(reservationUserInfoVo);
						
						
						message = "취소완료 되었습니다.";
						
						
					}else{
						
						if(xpay.m_szResCode.equals("RF02")
									|| xpay.m_szResCode.equals("RF08")
									|| xpay.m_szResCode.equals("RF13")
									|| xpay.m_szResCode.equals("RF22")
									|| xpay.m_szResCode.equals("RF28")
									|| xpay.m_szResCode.equals("RF26")
									|| xpay.m_szResCode.equals("RF27")
									|| xpay.m_szResCode.equals("RF30")
									|| xpay.m_szResCode.equals("RF31")){
							
							SVC_MSGE = "전자결제를 통한 환불처리가 실패되었습니다. \n";
							SVC_MSGE += "아래의 응답메시지에 따라 조치 후 재요청하시기 바랍니다. \n";
							SVC_MSGE += "(현금, 계좌이체를 통해 담당자가 직접 환불처리 하실 수 있습니다.)\n\n";
							SVC_MSGE += "응답메시지["+xpay.m_szResMsg+"] 응답코드["+xpay.m_szResCode+"]";
						}else{
							SVC_MSGE = "전자결제를 통한 환불처리가 실패되었습니다. \n";
							SVC_MSGE += "현금, 계좌이체를 통해 담당자가 직접 환불처리 해주시기 바랍니다. \n\n";
							SVC_MSGE += "응답메시지["+xpay.m_szResMsg+"] 응답코드["+xpay.m_szResCode+"]";
						}
						
						return returnJsonType(false, SVC_MSGE, null);
					}
					
				}else {
					//2)API 요청 실패 화면처리
					System.out.println("결제 취소요청이 실패하였습니다.");
					System.out.println( "TX Response_code = " + xpay.m_szResCode);
					System.out.println( "TX Response_msg = " + xpay.m_szResMsg);
					
					message = "결제 취소요청이 실패하였습니다";
				}
				
			}
				
			SVC_MSGE = message;
		}else{
			SVC_MSGE = "취소완료 되었습니다.";
			String canReason = "관리자 오프라인결제 취소처리";
			StringBuffer pay_memo = new StringBuffer();
			String payType = "";
			if(result.getMethod().equals("SC0010")){
				payType = "신용카드";
			}else if(result.getMethod().equals("SC0030")){
				payType = "계좌이체";
			}else if(result.getMethod().equals("OFFLINE1")){
				payType = "무통장입금";
			}else if(result.getMethod().equals("OFFLINE2")){
				payType = "현장결제";
			}
			pay_memo.append("취소일자 : "+rnow);			
			pay_memo.append("<br>");
			pay_memo.append("거래종류 : ");		    	
			pay_memo.append(payType);
			pay_memo.append("<br>");
			pay_memo.append("취소코드 : 완료");			
			
			reservationUserInfoVo.setPayTid("");
			reservationUserInfoVo.setPayAuth("");
			reservationUserInfoVo.setMethod(result.getMethod());
			reservationUserInfoVo.setPayMemo(pay_memo.toString());
			reservationUserInfoVo.setPayStatus("5");	
			reservationUserInfoVo.setCanReason(canReason);
			
			/*최종결제요청 결과 실패 DB처리하시기 바랍니다.*/
		    
		    /*최종결제 완료후 현재 페이지 새로 고침하거나 백스페이스 누르면 자동 아래구문 자동 업데이트 방지
		     * 취소단계는 PG사 관리자 페이지 관리업체가 수동 취소한다고 함. 
		     */				
			
			reservationEventSvc.modReservationEventReturn(reservationUserInfoVo);
		}
		
		return returnJsonType(false, SVC_MSGE, null);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/boffice/ex/reservationFac/reservationFacTeamList.do")
	public String reservationFacTeamList(
			HttpServletRequest request			
			, @ModelAttribute("ReservationFacTeamVo") ReservationFacTeamVo reservationFacTeamVo
			, ModelMap model
			) throws Exception {

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(reservationFacTeamVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(reservationFacTeamVo.getPageUnit());
		paginationInfo.setPageSize(reservationFacTeamVo.getPageSize());

		reservationFacTeamVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		reservationFacTeamVo.setLastIndex(paginationInfo.getLastRecordIndex());
		reservationFacTeamVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		
		Map<String, Object> map = reservationFacSvc.retrievePagFacTeam(reservationFacTeamVo);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));

		paginationInfo.setTotalRecordCount(totCnt);
		if(model != null){
			model.addAttribute("ReservationFacTeamVo", reservationFacTeamVo);
			model.addAttribute("resultList", map.get("resultList"));
			model.addAttribute("resultCnt", map.get("resultCnt"));
			model.addAttribute("paginationInfo", paginationInfo);
		}

		return "injeinc/boffice/ex/reservationFac/fac_team_list";
	}
	
	@RequestMapping("/boffice/ex/reservationFac/reservationFacTeamForm.do")
	public String reservationFacTeamForm(
			HttpServletRequest request
			, @ModelAttribute("ReservationFacTeamVo") ReservationFacTeamVo reservationFacTeamVo
			, ModelMap model
			) throws Exception {
		
		HttpSession ses = request.getSession();
		LoginVo cmsUser = (LoginVo)ses.getAttribute("CMSUSER");
		
		EgovMap result = reservationFacSvc.retrieveFacTeam(reservationFacTeamVo);

		if(result != null) {
			reservationFacTeamVo.setActionkey("modify");			
			model.addAttribute("TeamInfo", result);
		}else{
			reservationFacTeamVo.setActionkey("regist");
			
		}		
		
		model.addAttribute("ReservationFacTeamVo", reservationFacTeamVo);
		return "injeinc/boffice/ex/reservationFac/fac_team_form";
	}
	
	@RequestMapping("/ex/reservationFac/reservationFacTeamCheck.do")
	public void reservationFacTeamCheck(
			HttpServletRequest request
			,HttpServletResponse response
			, @ModelAttribute("ReservationUserInfoVo") ReservationFacTeamVo reservationFacTeamVo			
			) throws Exception {

		int cnt1 = reservationFacSvc.retrieveUserckCnt(reservationFacTeamVo);
				
		int cnt2 = reservationFacSvc.retrieveTeamCkCnt(reservationFacTeamVo);
		
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("cnt1", cnt1);
		jsonMap.put("cnt2", cnt2);
		jsonView.render(jsonMap, request, response);
	}
	
	@RequestMapping("/boffice/ex/reservationFac/reservationFacTeamReg.do")
	public String reservationFacTeamReg(
			HttpServletRequest request
			, @ModelAttribute("ReservationFacTeamVo") ReservationFacTeamVo reservationFacTeamVo
			, ModelMap model
			) throws Exception {
		
		reservationFacSvc.createReservationFacTeam(reservationFacTeamVo);
				
		return alert("/boffice/ex/reservationFac/reservationFacTeamList.do","등록되었습니다.",model);
	}
	
	@RequestMapping("/boffice/ex/reservationFac/reservationFacTeamMod.do")
	public String reservationFacTeamMod(
			HttpServletRequest request
			, @ModelAttribute("ReservationFacTeamVo") ReservationFacTeamVo reservationFacTeamVo
			, ModelMap model
			) throws Exception {
		
		reservationFacSvc.modReservationFacTeam(reservationFacTeamVo);
		
		return alert("/boffice/ex/reservationFac/reservationFacTeamList.do","수정되었습니다.",model);
	}
}
