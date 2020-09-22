package egovframework.injeinc.boffice.ex.reservation.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.WebUtils;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.EgovWebUtil;
import egovframework.cmm.Message;
import egovframework.cmm.service.EgovProperties;
import egovframework.injeinc.boffice.ex.reservation.service.ReservationEventSvc;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationAddItemVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationIndexVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationItemVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationUserAnswerVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationUserInfoVo;
import egovframework.injeinc.boffice.ex.reservationEtc.service.ReservationEtcSvc;
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
public class ReservationEventCtr extends CmmLogCtr{
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationEventCtr.class);
	
	 @Resource(name = "CodeSvc")
	 private CodeSvc codeSvc;

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

	@RequestMapping(value = "/boffice/ex/reservation/reservationEventList.do")
	public String reservationEventPag(HttpServletRequest request , @ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo , ModelMap model) throws Exception {

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(reservationIndexVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(reservationIndexVo.getPageUnit());
		paginationInfo.setPageSize(reservationIndexVo.getPageSize());

		reservationIndexVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		reservationIndexVo.setLastIndex(paginationInfo.getLastRecordIndex());
		reservationIndexVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		reservationIndexVo.setRiType("E");
		
		//게시물 권한 체크 - 최고관리자가 아니면 각부서에 해당하는 게시물만 보인다.
		HttpSession ses = request.getSession();
		LoginVo cmsUser = (LoginVo)ses.getAttribute("CMSUSER");
		if(!cmsUser.getPermCd().equals("05010000")){
			reservationIndexVo.setRiManageDept(cmsUser.getpDeptCd());
		}
		
		if(!cmsUser.getIsAdmin()){
			if(cmsUser.getMgrSiteCd() == null || "".equals(cmsUser.getMgrSiteCd())){
				throw new RuntimeException("접근권한이 없습니다. \n담당 교육기관 지정 후 접근이 가능합니다. \n관리자에게 문의해 주세요.");
			}
		}
		if(!cmsUser.getIsAdmin()){
			//lectureVo.setDeptCd(cmsUser.getpDeptCd());
			reservationIndexVo.setMgrSiteCdList(cmsUser.getMgrSiteCdList());
		}
		
		Map<String, Object> map = reservationEventSvc.retrievePagReservationEvent(reservationIndexVo);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));

		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);

		return "injeinc/boffice/ex/reservation/reservation_event_list";
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/boffice/ex/reservation/reservationEventForm.do")
	public String reservationEventForm(
			HttpServletRequest request
			, @ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo
			, ModelMap model
			) throws Exception {
		
		HttpSession ses = request.getSession();
		LoginVo cmsUser = (LoginVo)ses.getAttribute("CMSUSER");
		
		HashMap<String, String> param = new HashMap<String, String>();
		ReservationIndexVo result = reservationEventSvc.retrieveReservationEvent(reservationIndexVo);
		
		if(result != null) {
			result.setActionkey("modify");
			result.setPageIndex(reservationIndexVo.getPageIndex());
			result.setSearchCondition(reservationIndexVo.getSearchCondition());
			result.setSearchKeyword(reservationIndexVo.getSearchKeyword());

			model.addAttribute("ReservationAddItemList", reservationEventSvc.retrieveListReservationEventAddItem(reservationIndexVo));
			model.addAttribute("ReservationAddItemList2", reservationEventSvc.retrieveListReservationEventAddItem2(reservationIndexVo));
			model.addAttribute("ReservationItemList", reservationEventSvc.retrieveListReservationEventItem(reservationIndexVo));
			model.addAttribute("ReservationIndexVo", result);
			
		}else{
			reservationIndexVo.setActionkey("regist");
		}

		HashMap<String, Object> serviceMap = codeSvc.retrieveDeptCodeAll(param);
		List resultList = reservationEtcSvc.retrieveListSuervisionOrg(cmsUser.getIsAdmin(), cmsUser.getMgrSiteCdList());
		List<CodeVo> rowDataList = (List<CodeVo>) serviceMap.get("rowDataList");

		model.addAttribute("deptList", rowDataList);
		model.addAttribute("resultList", resultList);
		
		//관리부서목록
		List deptCode = reservationEventSvc.retrieveDeptCode();
		model.addAttribute("dept", deptCode);
		return "injeinc/boffice/ex/reservation/reservation_event_form";
	}

	@RequestMapping("/boffice/ex/reservation/reservationEventReg.do")
	public String reservationEventReg(
			final MultipartHttpServletRequest multiRequest
			, @ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo
			, ModelMap model
			) throws Exception {

		ReservationAddItemVo reservationAddItemVo;
		ReservationAddItemVo reservationAddItemVo2;
		ReservationItemVo reservationItemVo;

		String riIdx = reservationIdgenService.getNextStringId();
		reservationIndexVo.setRiIdx(riIdx);

		String userid = (String) WebUtils.getSessionAttribute(multiRequest, "SesUserId");

		reservationIndexVo.setRegId(userid);
		reservationIndexVo.setRiType("E");

		String raTitle[] = multiRequest.getParameterValues("raTitle");
		String raContent[] = multiRequest.getParameterValues("raContent");
		
		String raTitleT[] = multiRequest.getParameterValues("raTitleT");
		String raContentT[] = multiRequest.getParameterValues("raContentT");

		String tableNum[] = multiRequest.getParameterValues("tableNum");
		String rtItemTitle[] = multiRequest.getParameterValues("rtItemTitle");
		String rtItemType[] = multiRequest.getParameterValues("rtItemType");
		String rtItemNotice[] = multiRequest.getParameterValues("rtItemNotice");
		String rtItemExampleText = "";
		if(raTitle != null){
			for(int i = 0; i < raTitle.length; i++){
				if(raTitle[i] != null && !raTitle.equals("")){
					reservationAddItemVo2 = new ReservationAddItemVo();
					
					reservationAddItemVo2.setRiIdx(riIdx);
					reservationAddItemVo2.setRaIdx(i+1);
					reservationAddItemVo2.setRaTitle(raTitle[i]);
					reservationAddItemVo2.setRaContent(raContent[i]);
					reservationAddItemVo2.setRegId(userid);
					
					reservationEventSvc.registReservationEventAddItem2(reservationAddItemVo2);
				}				
			}
		}
		
		if(raTitleT != null){
			for(int i = 0; i < raTitleT.length; i++){
				if(raTitleT[i] != null && !raTitleT.equals("")){
					reservationAddItemVo = new ReservationAddItemVo();
					
					reservationAddItemVo.setRiIdx(riIdx);
					reservationAddItemVo.setRaIdx(i+1);
					reservationAddItemVo.setRaTitle(raTitleT[i]);
					reservationAddItemVo.setRaContent(raContentT[i]);
					reservationAddItemVo.setRegId(userid);
					
					reservationEventSvc.registReservationEventAddItem(reservationAddItemVo);
				}				
			}
		}
		
		if(tableNum != null){
			
			for(int i=0; i < tableNum.length; i++){
				if(rtItemTitle[i] != null && !rtItemTitle.equals("")){
					reservationItemVo = new ReservationItemVo();
					rtItemExampleText  = ""; //초기화
					
					reservationItemVo.setRiIdx(riIdx);
					reservationItemVo.setRtIdx(i+1);
					reservationItemVo.setRtItemTitle(rtItemTitle[i]);
					reservationItemVo.setRtItemType(rtItemType[i]);
					reservationItemVo.setRtItemNotice(rtItemNotice[i]);
					reservationItemVo.setRtItemUseCheck(multiRequest.getParameter("rtItemUseCheck_"+tableNum[i]));
					reservationItemVo.setRtItemEtcUseCheck(multiRequest.getParameter("rtItemEtcUseCheck_"+tableNum[i]));
					reservationItemVo.setRtItemExampleLen(multiRequest.getParameterValues("rtItemExample"+tableNum[i]).length);
					
					reservationItemVo.setRegId(userid);
					
					String rtItemExample[] = multiRequest.getParameterValues("rtItemExample"+tableNum[i]);
					if(rtItemExample != null){
						for(int j=0; j < rtItemExample.length; j++){
							rtItemExampleText += rtItemExample[j]+"||";
						}
						
					}
					
					reservationItemVo.setRtItemExample(rtItemExampleText);
					
					reservationEventSvc.registReservationEventItem(reservationItemVo);
				}				
			}
		}

		reservationIndexVo.setRiOffLotUse(reservationIndexVo.getRiOffLotUse().replaceAll(",", "||"));
		reservationIndexVo.setRiConfirmYn("Y");
		reservationIndexVo.setRiUserloginType("A");

		if(reservationIndexVo.getRiTermType().equals("O")){
			reservationIndexVo.setRiSdate(multiRequest.getParameter("riSdate1"));
		}

		// 파일업로드
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		ReservationIndexVo uploadVO = new ReservationIndexVo();
    	uploadVO = reservationEventSvc.uploadFile(files, reservationIndexVo);

    	reservationIndexVo.setRiImageFileId(uploadVO.getRiImageFileId());

		reservationEventSvc.registReservationEvent(reservationIndexVo);

		String SVC_MSGE = Message.getMessage("100.message");
		return alert("/boffice/ex/reservation/reservationEventList.do", SVC_MSGE, model);
	}

	@RequestMapping("/boffice/ex/reservation/reservationEventMod.do")
	public String reservationEventMod(
			final MultipartHttpServletRequest multiRequest
			, @ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo
			, ModelMap model
			) throws Exception {

		ReservationAddItemVo reservationAddItemVo;
		ReservationAddItemVo reservationAddItemVo2;
		ReservationItemVo reservationItemVo;

		String userid = (String) WebUtils.getSessionAttribute(multiRequest, "SesUserId");
		reservationIndexVo.setModId(userid);

		String raTitle[] = multiRequest.getParameterValues("raTitle");
		String raContent[] = multiRequest.getParameterValues("raContent");
		
		String raTitleT[] = multiRequest.getParameterValues("raTitleT");
		String raContentT[] = multiRequest.getParameterValues("raContentT");

		String tableNum[] = multiRequest.getParameterValues("tableNum");
		String rtItemTitle[] = multiRequest.getParameterValues("rtItemTitle");
		String rtItemType[] = multiRequest.getParameterValues("rtItemType");
		String rtItemNotice[] = multiRequest.getParameterValues("rtItemNotice");
		String rtItemExampleText = "";

		reservationEventSvc.removeReservationEventAddItem(reservationIndexVo);
		
		if(raTitle != null){
			for(int i = 0; i < raTitle.length; i++){
				reservationAddItemVo = new ReservationAddItemVo();
				
				reservationAddItemVo.setRiIdx(reservationIndexVo.getRiIdx());
				reservationAddItemVo.setRaIdx(i+1);
				reservationAddItemVo.setRaTitle(raTitle[i]);
				reservationAddItemVo.setRaContent(raContent[i]);
				reservationAddItemVo.setRegId(userid);
				
				reservationEventSvc.registReservationEventAddItem(reservationAddItemVo);
			}
		}
		
		reservationEventSvc.removeReservationEventAddItem2(reservationIndexVo);
		
		if(raTitleT != null){
			for(int i = 0; i < raTitleT.length; i++){
				reservationAddItemVo2 = new ReservationAddItemVo();
				
				reservationAddItemVo2.setRiIdx(reservationIndexVo.getRiIdx());
				reservationAddItemVo2.setRaIdx(i+1);
				reservationAddItemVo2.setRaTitle(raTitleT[i]);
				reservationAddItemVo2.setRaContent(raContentT[i]);
				reservationAddItemVo2.setRegId(userid);
				
				reservationEventSvc.registReservationEventAddItem2(reservationAddItemVo2);
			}
		}

		reservationEventSvc.removeReservationEventItem(reservationIndexVo);
		
		if(tableNum != null){
			
			for(int i=0; i < tableNum.length; i++){
				reservationItemVo = new ReservationItemVo();
				rtItemExampleText  = ""; //초기화
				
				reservationItemVo.setRiIdx(reservationIndexVo.getRiIdx());
				reservationItemVo.setRtIdx(i+1);
				reservationItemVo.setRtItemTitle(rtItemTitle[i]);
				reservationItemVo.setRtItemType(rtItemType[i]);
				reservationItemVo.setRtItemNotice(rtItemNotice[i]);
				reservationItemVo.setRtItemUseCheck(multiRequest.getParameter("rtItemUseCheck_"+tableNum[i]));
				reservationItemVo.setRtItemEtcUseCheck(multiRequest.getParameter("rtItemEtcUseCheck_"+tableNum[i]));
				reservationItemVo.setRtItemExampleLen(multiRequest.getParameterValues("rtItemExample"+tableNum[i]).length);
				
				reservationItemVo.setRegId(userid);
				
				String rtItemExample[] = multiRequest.getParameterValues("rtItemExample"+tableNum[i]);
				if(rtItemExample != null){
					for(int j=0; j < rtItemExample.length; j++){
						rtItemExampleText += rtItemExample[j]+"||";
					}
				}
				
				reservationItemVo.setRtItemExample(rtItemExampleText);
				
				reservationEventSvc.registReservationEventItem(reservationItemVo);
			}
		}

		reservationIndexVo.setRiOffLotUse(reservationIndexVo.getRiOffLotUse().replaceAll(",", "||"));

		if(reservationIndexVo.getRiTermType().equals("O")){
			reservationIndexVo.setRiSdate(multiRequest.getParameter("riSdate1"));
		}

		// 파일업로드
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		ReservationIndexVo uploadVO = new ReservationIndexVo();
    	uploadVO = reservationEventSvc.uploadFile(files, reservationIndexVo);

    	reservationIndexVo.setRiImageFileId(uploadVO.getRiImageFileId());

		reservationEventSvc.modifyReservationEvent(reservationIndexVo);

		StringBuffer addParam = new StringBuffer();
		addParam.append("?pageIndex=").append(reservationIndexVo.getPageIndex());
		addParam.append("&searchCondition=").append(reservationIndexVo.getSearchCondition());
		addParam.append("&searchKeyword=").append(reservationIndexVo.getSearchKeyword());

		String SVC_MSGE = Message.getMessage("401.message");
		return alert("/boffice/ex/reservation/reservationEventList.do"+addParam.toString(), SVC_MSGE, model);
	}

	@RequestMapping("/boffice/ex/reservation/reservationEventRmv.do")
	public String reservationEventRmv(
			HttpServletRequest request
			, @ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo
			, ModelMap model
			) throws Exception {

		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		reservationIndexVo.setModId(userid);

		reservationEventSvc.removeReservationEvent(reservationIndexVo);

		StringBuffer addParam = new StringBuffer();
		addParam.append("?pageIndex=").append(reservationIndexVo.getPageIndex());
		addParam.append("&searchCondition=").append(reservationIndexVo.getSearchCondition());
		addParam.append("&searchKeyword=").append(reservationIndexVo.getSearchKeyword());

		String SVC_MSGE = Message.getMessage("501.message");
		return alert("/boffice/ex/reservation/reservationEventList.do"+addParam.toString(), SVC_MSGE, model);
	}

	@RequestMapping("/boffice/ex/reservation/reservationEventView.do")
	public String reservationEventView(
			HttpServletRequest request
			, @ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo
			, ModelMap model
			) throws Exception {

		ReservationIndexVo result = reservationEventSvc.retrieveReservationEvent(reservationIndexVo);

		List<ReservationUserInfoVo> userList = reservationEventSvc.retrieveListReservationEventUserInfo(reservationIndexVo);
		List<ReservationUserInfoVo> userReturnList = reservationEventSvc.retrieveListReservationEventUserInfoReturnFee(reservationIndexVo);

		result.setPageIndex(reservationIndexVo.getPageIndex());
		result.setSearchCondition(reservationIndexVo.getSearchCondition());
		result.setSearchKeyword(reservationIndexVo.getSearchKeyword());

		model.addAttribute("ReservationIndexVo", result);
		model.addAttribute("ReservationUserList", userList);
		model.addAttribute("userReturnList", userReturnList);
		
		ReservationUserInfoVo reservationUserInfoVo = new ReservationUserInfoVo();
		reservationUserInfoVo.setRiIdx(result.getRiIdx());
		reservationUserInfoVo.setRuLotResult("T"); //예비
		int countT = reservationEventSvc.retrieveReservationEventCntForLot(reservationUserInfoVo);
		reservationUserInfoVo.setRuLotResult("C"); // 확정대기
		int countC = reservationEventSvc.retrieveReservationEventCntForLot(reservationUserInfoVo);
		reservationUserInfoVo.setRuLotResult("S"); //추첨확정
		int countS = reservationEventSvc.retrieveReservationEventCntForLot(reservationUserInfoVo);
		
		model.addAttribute("countT", countT);
		model.addAttribute("countC", countC);
		model.addAttribute("countS", countS);

		return "injeinc/boffice/ex/reservation/reservation_event_view";
	}

	@RequestMapping("/boffice/ex/reservation/reservationEventUserForm.do")
	public String reservationEventUserForm(
			HttpServletRequest request
			, @ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo
			, ModelMap model
			) throws Exception {

		ReservationIndexVo reservationIndexVo = new ReservationIndexVo();

		reservationIndexVo.setRiIdx(reservationUserInfoVo.getRiIdx());

		reservationIndexVo = reservationEventSvc.retrieveReservationEvent(reservationIndexVo);
		ReservationUserInfoVo result = reservationEventSvc.retrieveReservationEventUserInfo(reservationUserInfoVo);

		if(result != null) {
			reservationIndexVo.setActionkey("modify");

			model.addAttribute("ReservationUserInfoVo", result);
		}else{
			if(reservationIndexVo != null){
				if(reservationIndexVo.getRiRecruitYn().equals("N") && reservationIndexVo.getRiLotUse().equals("OO")){
					if(Integer.parseInt(reservationIndexVo.getRiRecruitCnt())  <= reservationIndexVo.getRiAccCnt() ){
						return alert(request.getContextPath()+"/boffice/ex/reservation/reservationEventView.do?riIdx="+reservationIndexVo.getRiIdx(), "모집인원이 초과되었습니다.", model);
					}
				}
			}

			reservationIndexVo.setActionkey("regist");
		}

		reservationIndexVo.setRuIdx(reservationUserInfoVo.getRuIdx());

		model.addAttribute("ReservationItemList", reservationEventSvc.retrieveListReservationEventItem(reservationIndexVo));
		model.addAttribute("ReservationIndexVo", reservationIndexVo);

		return "injeinc/boffice/ex/reservation/reservation_event_user_form";
	}
	
	@RequestMapping("/boffice/ex/reservation/reservationEventUserReturnForm.do")
	public String reservationEventUserReturnForm(
			HttpServletRequest request
			, @ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo
			, ModelMap model
			) throws Exception {

		ReservationIndexVo reservationIndexVo = new ReservationIndexVo();

		reservationIndexVo.setRiIdx(reservationUserInfoVo.getRiIdx());

		reservationIndexVo = reservationEventSvc.retrieveReservationEvent(reservationIndexVo);
		ReservationUserInfoVo result = reservationEventSvc.retrieveReservationEventUserInfoReturnFee(reservationUserInfoVo);

		
		model.addAttribute("ReservationUserInfoVo", result);
		
		reservationIndexVo.setRuIdx(reservationUserInfoVo.getRuIdx());

		model.addAttribute("ReservationItemList", reservationEventSvc.retrieveListReservationEventItem(reservationIndexVo));
		model.addAttribute("ReservationIndexVo", reservationIndexVo);

		return "injeinc/boffice/ex/reservation/reservation_event_user_return_form";
	}
	
	/** 환불처리 프로세스 */
	/** 수강취소_환불포함 */
		
	@RequestMapping(value="/boffice/ex/reservation/reservationEventUserReturnFee.do", produces="application/json;charset=UTF-8")
	@ResponseBody
	public String reservationEventUserReturnFee(
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/boffice/ex/reservation/reservationUserInfoReg.do")
	public String reservationUserInfoReg(
			HttpServletRequest request
			, @ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo
			, @ModelAttribute("ReservationUserAnswerVo") ReservationUserAnswerVo reservationUserAnswerVo
			, ModelMap model
			) throws Exception {

		ReservationIndexVo reservationIndexVo = new ReservationIndexVo();

		String riIdx = reservationUserInfoVo.getRiIdx();
		reservationIndexVo.setRiIdx(riIdx);
		String ruIdx = reservationUserIdGnrService.getNextStringId();
		reservationUserInfoVo.setRuIdx(ruIdx);

		List<ReservationItemVo> itemList = reservationEventSvc.retrieveListReservationEventItem(reservationIndexVo);

		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		reservationUserInfoVo.setRegId(userid);

		ReservationIndexVo result = reservationEventSvc.retrieveReservationEvent(reservationIndexVo);

		ArrayList riIdxList = new ArrayList();
		String redundancyList[] = reservationIndexVo.getRiRedundancy().split(";");

		for(int i=0; i < redundancyList.length; i++){
			riIdxList.add(redundancyList[i]);
		}

		reservationUserInfoVo.setRiIdxList(riIdxList);
		if(!reservationUserInfoVo.getRuDupkey().equals("")){
			if(reservationEventSvc.retrieveReservationEventUserInfoDupCnt(reservationUserInfoVo) > 0){
				return alert("/boffice/ex/reservation/reservationEventUserForm.do?riIdx="+reservationIndexVo.getRiIdx(), "이미 예약을 하셨습니다.", model);
			}else if(reservationEventSvc.retrieveReservationEventRedundancyDupCnt(reservationUserInfoVo) > 0){
				return alert("/boffice/ex/reservation/reservationEventUserForm.do?riIdx="+reservationIndexVo.getRiIdx(), "일부 행사와 중복 예약을 하실 수 없습니다.", model);
			}
		}

		if(result.getRiLotUse().equals("OO")){
			reservationUserInfoVo.setRuLotResult("S");
		}else{
			reservationUserInfoVo.setRuLotResult("R");
		}

		reservationEventSvc.registReservationEventUserInfo(reservationUserInfoVo);

		String[] raEtcAnswer = request.getParameterValues("raEtcAnswer");
		
		for(int i = 0; i < itemList.size(); i++) {
			reservationUserAnswerVo = new ReservationUserAnswerVo();

			reservationUserAnswerVo.setRuIdx(ruIdx);
			reservationUserAnswerVo.setRiIdx(riIdx);
			reservationUserAnswerVo.setRtIdx(i+1);
			String raAnswerVal = "";
			String[] raAnswer =  request.getParameterValues("raAnswer"+(i+1));
			for(int j = 0; j < raAnswer.length; j++){
				raAnswerVal = raAnswerVal + raAnswer[j] + "||";
			}
			reservationUserAnswerVo.setRaAnswer(raAnswerVal);

			if(raEtcAnswer != null && raEtcAnswer.length > i){
				reservationUserAnswerVo.setRaEtcAnswer(raEtcAnswer[i]);
			}
			reservationEventSvc.registReservationEventUserAnswer(reservationUserAnswerVo);
		}

		String SVC_MSGE = Message.getMessage("100.message");
		return alert("/boffice/ex/reservation/reservationEventView.do?riIdx="+riIdx, SVC_MSGE, model);
	}

	@RequestMapping("/boffice/ex/reservation/reservationUserInfoMod.do")
	public String reservationUserInfoMod(HttpServletRequest request, @ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo, @ModelAttribute("ReservationUserAnswerVo") ReservationUserAnswerVo reservationUserAnswerVo, ModelMap model) throws Exception {

		ReservationIndexVo reservationIndexVo = new ReservationIndexVo();

		String riIdx = reservationUserInfoVo.getRiIdx();
		reservationIndexVo.setRiIdx(riIdx);

		List<ReservationItemVo> itemList = reservationEventSvc.retrieveListReservationEventItem(reservationIndexVo);

		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		reservationUserInfoVo.setModId(userid);

		ReservationIndexVo result = reservationEventSvc.retrieveReservationEvent(reservationIndexVo);

		if(result.getRiLotUse().equals("OO")){
			reservationUserInfoVo.setRuLotResult("S");
		}else{
			reservationUserInfoVo.setRuLotResult("R");
		}

		reservationEventSvc.modifyReservationEventUserInfo(reservationUserInfoVo);

		reservationEventSvc.removeReservationEventUserAnswer(reservationUserInfoVo);

		String[] raEtcAnswer = request.getParameterValues("raEtcAnswer");

		for(int i = 0; i < itemList.size(); i++){
			reservationUserAnswerVo = new ReservationUserAnswerVo();
			
			reservationUserAnswerVo.setRuIdx(reservationUserInfoVo.getRuIdx());
			reservationUserAnswerVo.setRiIdx(riIdx);
			reservationUserAnswerVo.setRtIdx(i+1);
			String raAnswerVal = "";
			String[] raAnswer =  request.getParameterValues("raAnswer"+(i+1));
			for(int j = 0; j < raAnswer.length; j++){
				raAnswerVal = raAnswerVal + raAnswer[j] + "||";
			}
			reservationUserAnswerVo.setRaAnswer(raAnswerVal);
			
			if(raEtcAnswer != null && raEtcAnswer.length > i){
				reservationUserAnswerVo.setRaEtcAnswer(raEtcAnswer[i]);
			}
			reservationEventSvc.registReservationEventUserAnswer(reservationUserAnswerVo);
		}

		String SVC_MSGE = Message.getMessage("100.message");
		return alert("/boffice/ex/reservation/reservationEventView.do?riIdx="+riIdx, SVC_MSGE, model);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/boffice/ex/popup/reservationEventListPopup.do")
	public String reservationEventPagPopup(HttpServletRequest request, @ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo, ModelMap model) throws Exception {

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(reservationIndexVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(reservationIndexVo.getPageUnit());
		paginationInfo.setPageSize(reservationIndexVo.getPageSize());

		reservationIndexVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		reservationIndexVo.setLastIndex(paginationInfo.getLastRecordIndex());
		reservationIndexVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		reservationIndexVo.setRiType("E");

		Map<String, Object> map = reservationEventSvc.retrievePagReservationEvent(reservationIndexVo);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));

		paginationInfo.setTotalRecordCount(totCnt);

		HashMap<String, String> param = new HashMap<String, String>();
		HashMap<String, Object> serviceMap = codeSvc.retrieveDeptCodeAll(param);

		List<CodeVo> rowDataList = (List<CodeVo>) serviceMap.get("rowDataList");

		model.addAttribute("deptList", rowDataList);

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);

		return "injeinc/boffice/ex/reservation/reservation_event_list_popup";
	}

	/* 신청자 추첨 */
	@RequestMapping("/boffice/ex/reservation/reservationEventLot.do")
	public String reservationEventLot(HttpServletRequest request, @ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo, ModelMap model, String[] ruIdx, int procRecruitCnt) throws Exception {

		Random random = new Random();

		List<String> ruIdxList = new ArrayList<String>();
		if(ruIdx.length > procRecruitCnt){
			//System.out.println("procRecruitCnt=========="+procRecruitCnt);
			for(int i=procRecruitCnt ; i>0 ; i--) {
				int r = random.nextInt(i);
				//System.out.println("rrrrrrrrrrrrrrrrrrrrrrrrrrrrr=========="+r);
				ruIdxList.add(ruIdx[r]);
				
				for(int j=r ; j<i-1 ; j++) {
					ruIdx[j] = ruIdx[j+1];
				}
			}
		}else{
			//System.out.println("else=========="+procRecruitCnt);
			for (int i = 0; i < ruIdx.length; i++) {
				ruIdxList.add(ruIdx[i]);
			}
		}
		//System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		reservationIndexVo.setStrList(ruIdxList);
		//System.out.println(reservationIndexVo.getStrList());
		reservationEventSvc.updateReservationEventLot(reservationIndexVo);

		return alert(request.getContextPath()+"/boffice/ex/reservation/reservationEventView.do?riIdx="+reservationIndexVo.getRiIdx(), "추첨완료", model);
	}

	/* 신청자 추첨 */
	@RequestMapping("/boffice/ex/reservation/reservationEventLot2015.do")
	public String reservationEventLot2015(HttpServletRequest request, @ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo, ModelMap model) throws Exception {

		ReservationIndexVo result = reservationEventSvc.retrieveReservationEvent(reservationIndexVo);
		
		if(result == null) {
			return alert("", "잘못된 접근입니다.", model);
		}
		
		List<String> ruIdxList = new ArrayList<String>();
		List<String> ruIdxListTemp = new ArrayList<String>();
		
		int riRecruitCnt = EgovStringUtil.zeroConvert(result.getRiRecruitCnt());
		int riTempCnt = result.getRiTempCnt();
		String riIdx = result.getRiIdx();
		
		ReservationUserInfoVo reservationUserInfoVo = new ReservationUserInfoVo();
		reservationUserInfoVo.setRiIdx(riIdx);
		reservationUserInfoVo.setRuLotResult("R"); //접수
		int countR = reservationEventSvc.retrieveReservationEventCntForLot(reservationUserInfoVo);
		reservationUserInfoVo.setRuLotResult("T"); //예비
		int countT = reservationEventSvc.retrieveReservationEventCntForLot(reservationUserInfoVo);
		reservationUserInfoVo.setRuLotResult("C"); // 확정대기
		int countC = reservationEventSvc.retrieveReservationEventCntForLot(reservationUserInfoVo);
		reservationUserInfoVo.setRuLotResult("S"); //추첨확정
		int countS = reservationEventSvc.retrieveReservationEventCntForLot(reservationUserInfoVo);
		
		int remainCnt = riRecruitCnt - (countS+countC);
		
		if(remainCnt > 0) {
			
			if(countT > 0) {
				List<String> tempList = reservationEventSvc.retrieveListReservationEventRandomTemp(reservationIndexVo);
				
				for(String ruIdx : tempList) {
					if(remainCnt <= 0) {
						break;
					}
					ruIdxList.add(ruIdx);
					remainCnt--;
					countT--;
				}
				
				reservationIndexVo.setStrList(ruIdxList);
				reservationEventSvc.updateReservationEventLot(reservationIndexVo);
			}
			
			if(remainCnt > 0) {
				
				List<String> readyList = reservationEventSvc.retrieveListReservationEventRandom(reservationIndexVo);
				
				for(String ruIdx : readyList) {
					if(remainCnt <= 0) {
						break;
					}
					ruIdxList.add(ruIdx);
					remainCnt--;
					countR--;
				}
				
				reservationIndexVo.setStrList(ruIdxList);
				reservationEventSvc.updateReservationEventLot(reservationIndexVo);
			}
			
			int remainTempCnt = riTempCnt - countT;
			
			if(remainTempCnt > 0 && countR > 0) {
				
				List<String> readyList = reservationEventSvc.retrieveListReservationEventRandom(reservationIndexVo);
				
				for(String ruIdx : readyList) {
					if(remainTempCnt <= 0) {
						break;
					}
					ruIdxListTemp.add(ruIdx);
					remainTempCnt--;
				}
				
				reservationIndexVo.setStrList(ruIdxListTemp);
				reservationEventSvc.updateReservationEventLotTemp(reservationIndexVo);
				
			}
		}

		return alert(request.getContextPath()+"/boffice/ex/reservation/reservationEventView.do?riIdx="+reservationIndexVo.getRiIdx(), "추첨완료", model);
	}

	/* 신청자 삭제 */
	@RequestMapping("/boffice/ex/reservation/reservationEventUserInfoRmv.do")
	public String reservationEventUserInfoRmv(HttpServletRequest request, @ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo, ModelMap model) throws Exception {

		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		reservationUserInfoVo.setModId(userid);

		reservationEventSvc.removeReservationEventUserInfo(reservationUserInfoVo);

		return alert("/boffice/ex/reservation/reservationEventView.do?riIdx="+reservationUserInfoVo.getRiIdx(), "접수자 삭제완료", model);
	}

	/* 신청자 추첨확정 */
	@RequestMapping("/boffice/ex/reservation/reservationEventUserInfoConfrim.do")
	public String reservationEventUserInfoConfirm(HttpServletRequest request, @ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo, ModelMap model) throws Exception {

		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		reservationUserInfoVo.setModId(userid);

		reservationEventSvc.updateReservationEventUserInfoConfirm(reservationUserInfoVo);

		return alert("/boffice/ex/reservation/reservationEventView.do?riIdx="+reservationUserInfoVo.getRiIdx(), "추첨확정되었습니다.", model);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/boffice/ex/reservation/reservationEventExcel_bak.do")
	public void webzineExcel(@ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ReservationIndexVo reservationIndexVo = new ReservationIndexVo();

		reservationIndexVo.setRiIdx(reservationUserInfoVo.getRiIdx());

		List<ReservationUserInfoVo> result = bizExcelService.selectList("ReservationEventDao.selectListReservationEventUserInfo", reservationIndexVo);


		String createString = "reservationEvent_" + EgovStringUtil.getTimeStamp() +".xls";

		String storePathString = Message.getMessage("board.file.upload.path");
		File saveFolder = new File(EgovWebUtil.filePathBlackList(storePathString));

		if (!saveFolder.exists() || saveFolder.isFile()) {
		    saveFolder.mkdirs();
		}

		String filePath = storePathString + createString;
		//System.out.println("filePath : " + filePath);
		File path = new File(EgovWebUtil.filePathBlackList(filePath)); //출력할 엑셀의 파일명.

		String sheetName="";
		String fileName ="";
		String[][] data = new String[result.size()+1][7];


		data[0][0] = "번호";
		data[0][1] = "이름";
		data[0][2] = "주소";
		data[0][3] = "전화번호";
		data[0][4] = "핸드폰번호";
		data[0][5] = "이메일";
		data[0][6] = "추첨";
		for(int i=0;i<result.size();i++){
			ReservationUserInfoVo resultVO = (ReservationUserInfoVo)result.get(i);
			data[i+1][0] = String.valueOf(result.size()-i);
			data[i+1][1] = resultVO.getRuName();
			data[i+1][2] = resultVO.getRuAddress();
			data[i+1][3] = resultVO.getRuTel();
			data[i+1][4] = resultVO.getRuHp();
			data[i+1][5] = resultVO.getRuEmail();
			if(resultVO.getRuLotResult().equals("C")){
				data[i+1][6] = "추첨대기";
			}else if(resultVO.getRuLotResult().equals("S")){
				data[i+1][6] = "추첨확정";
			}else{
				data[i+1][6] = "신청";
			}
		}
		sheetName = "행사예약신청자목록";
		/** 다운로드 파일명*/
		fileName = "행사예약신청자목록_" + EgovStringUtil.getTimeStamp();

		short[] halign = new short[] {HSSFCellStyle.ALIGN_CENTER, HSSFCellStyle.ALIGN_LEFT, HSSFCellStyle.ALIGN_LEFT, HSSFCellStyle.ALIGN_LEFT, HSSFCellStyle.ALIGN_LEFT}; 			//정렬
		short[] valign = new short[] {HSSFCellStyle.VERTICAL_CENTER, HSSFCellStyle.VERTICAL_CENTER, HSSFCellStyle.VERTICAL_CENTER, HSSFCellStyle.VERTICAL_CENTER, HSSFCellStyle.VERTICAL_CENTER}; //정렬

		try {
			ExcelCtr.writeToExcel(path , data , sheetName , fileName , halign , valign, request, response);
		} catch (FileNotFoundException e) {
			LOGGER.debug("IGNORED: " + e.getMessage());
			path.delete();
		} catch (IOException e) {
			LOGGER.debug("IGNORED: " + e.getMessage());
			path.delete();
		} finally{
			path.delete();
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/boffice/ex/reservation/reservationEventExcel.do")
	public String reservationEventExcel( @ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ReservationIndexVo reservationIndexVo = new ReservationIndexVo();

		reservationIndexVo.setRiIdx(reservationUserInfoVo.getRiIdx());

		//행사신청자 목록
		List<ReservationUserInfoVo> resultList = bizExcelService.selectList("ReservationEventDao.selectListReservationEventUserInfo", reservationIndexVo);
		
		model.addAttribute("resultList", resultList);
		
		String nowDate = DateUtil.getCurrentDate("yyyyMMdd");
		//다운로드 파일명
		String clName = "행사예약신청자목록";
		String fileName = clName+"_"+nowDate+".xls";
		
		String encodedFilename = URLEncoder.encode(fileName, "UTF-8");
		
		request.setCharacterEncoding("utf-8");
		response.setHeader("Content-Disposition", "attachment; filename="+encodedFilename);
		response.setHeader("Content-Description", "JSP Generated Data");
		response.setContentType("application/vnd.ms-excel");	

		return "injeinc/boffice/ex/reservation/reservation_event_excel";
		
	}
	
}
