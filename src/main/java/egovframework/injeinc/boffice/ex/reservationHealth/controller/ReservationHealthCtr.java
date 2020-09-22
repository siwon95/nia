package egovframework.injeinc.boffice.ex.reservationHealth.controller;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.util.WebUtils;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.EgovWebUtil;
import egovframework.cmm.Message;
import egovframework.injeinc.boffice.ex.reservationHealth.service.ReservationHealthSvc;
import egovframework.injeinc.boffice.ex.reservationHealth.vo.ReservationAddHealthVo;
import egovframework.injeinc.boffice.ex.reservationHealth.vo.ReservationAddNodateVo;
import egovframework.injeinc.boffice.ex.reservationHealth.vo.ReservationHealthDayVo;
import egovframework.injeinc.boffice.ex.reservationHealth.vo.ReservationHealthUserAnswerVo;
import egovframework.injeinc.boffice.ex.reservationHealth.vo.ReservationIndexHealthVo;
import egovframework.injeinc.boffice.ex.reservationHealth.vo.ReservationItemHealthVo;
import egovframework.injeinc.boffice.ex.reservationHealth.vo.ReservationUserInfoHealthVo;
import egovframework.injeinc.boffice.excel.controller.ExcelCtr;
import egovframework.injeinc.boffice.excel.service.BizExcelSvc;
import egovframework.injeinc.common.code.service.CodeSvc;
import egovframework.injeinc.common.code.vo.CodeVo;
import egovframework.injeinc.common.util.DateUtil;
import egovframework.injeinc.common.util.EgovStringUtil;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class ReservationHealthCtr extends CmmLogCtr{
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationHealthCtr.class);
	
	 @Resource(name = "CodeSvc")
	 private CodeSvc codeSvc;
	 
	 @Autowired(required=true)
	 private MappingJackson2JsonView jsonView;

	 @Resource(name = "ReservationHealthSvc")
	 private ReservationHealthSvc reservationHealthSvc;

	 @Resource(name = "reservationIdGnrService")
	 private EgovIdGnrService reservationIdgenService;

	 @Resource(name = "reservationUserIdGnrService")
	 private EgovIdGnrService reservationUserIdGnrService;

	@Resource(name = "BizExcelService")
	private BizExcelSvc bizExcelService;

	@RequestMapping(value = "/boffice/ex/reservationHealth/reservationHealthList.do")
	public String reservationHealthPag(HttpServletRequest request , @ModelAttribute("ReservationIndexHealthVo") ReservationIndexHealthVo reservationIndexHealthVo , ModelMap model) throws Exception {

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(reservationIndexHealthVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(reservationIndexHealthVo.getPageUnit());
		paginationInfo.setPageSize(reservationIndexHealthVo.getPageSize());

		reservationIndexHealthVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		reservationIndexHealthVo.setLastIndex(paginationInfo.getLastRecordIndex());
		reservationIndexHealthVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		reservationIndexHealthVo.setRiType("H");
		
		//게시물 권한 체크 - 최고관리자가 아니면 각부서에 해당하는 게시물만 보인다.
		HttpSession ses = request.getSession();
		String PermCd = (String)ses.getAttribute("SesUserPermCd");
		if(!PermCd.equals("05010000")){
			String riManageDept = (String)ses.getAttribute("SesUserDeptCd");
			reservationIndexHealthVo.setRiManageDept(riManageDept);
		}

		Map<String, Object> map = reservationHealthSvc.retrievePagReservationHealth(reservationIndexHealthVo);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));

		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);

		return "injeinc/boffice/ex/reservationHealth/reservation_health_list";
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/boffice/ex/reservationHealth/reservationHealthForm.do")
	public String reservationHealthForm(
			@ModelAttribute("ReservationIndexHealthVo") ReservationIndexHealthVo reservationIndexHealthVo
			, ModelMap model
			) throws Exception {

		HashMap<String, String> param = new HashMap<String, String>();

		ReservationIndexHealthVo result = reservationHealthSvc.retrieveReservationHealth(reservationIndexHealthVo);

		if(result != null) {
			result.setActionkey("modify");
			result.setPageIndex(reservationIndexHealthVo.getPageIndex());
			result.setSearchCondition(reservationIndexHealthVo.getSearchCondition());
			result.setSearchKeyword(reservationIndexHealthVo.getSearchKeyword());
			
			//예약등록(추가항목)2 리스트
			model.addAttribute("ReservationItemList", reservationHealthSvc.retrieveListReservationHealthItem(reservationIndexHealthVo));
			
			//요일별회차 리스트
			reservationIndexHealthVo.setRiDayWeek("1");
			model.addAttribute("ReservationAddHealtWeekList1", reservationHealthSvc.retrieveListReservationHealthWeek(reservationIndexHealthVo));
			reservationIndexHealthVo.setRiDayWeek("2");
			model.addAttribute("ReservationAddHealtWeekList2", reservationHealthSvc.retrieveListReservationHealthWeek(reservationIndexHealthVo));
			reservationIndexHealthVo.setRiDayWeek("3");
			model.addAttribute("ReservationAddHealtWeekList3", reservationHealthSvc.retrieveListReservationHealthWeek(reservationIndexHealthVo));
			reservationIndexHealthVo.setRiDayWeek("4");
			model.addAttribute("ReservationAddHealtWeekList4", reservationHealthSvc.retrieveListReservationHealthWeek(reservationIndexHealthVo));
			reservationIndexHealthVo.setRiDayWeek("5");
			model.addAttribute("ReservationAddHealtWeekList5", reservationHealthSvc.retrieveListReservationHealthWeek(reservationIndexHealthVo));
			reservationIndexHealthVo.setRiDayWeek("6");
			model.addAttribute("ReservationAddHealtWeekList6", reservationHealthSvc.retrieveListReservationHealthWeek(reservationIndexHealthVo));
			reservationIndexHealthVo.setRiDayWeek("7");
			model.addAttribute("ReservationAddHealtWeekList7", reservationHealthSvc.retrieveListReservationHealthWeek(reservationIndexHealthVo));
			/*model.addAttribute("ReservationAddItemList", reservationHealthSvc.retrieveListReservationHealthAddItem(reservationIndexHealthVo));*/
			//운영불가(휴무)일 리스트
			model.addAttribute("ReservationAddHealtNodateList", reservationHealthSvc.retrieveListReservationHealtNodate(reservationIndexHealthVo));
			
			
			
			model.addAttribute("ReservationIndexHealthVo", result);
		}else{
			reservationIndexHealthVo.setActionkey("regist");
		}

		HashMap<String, Object> serviceMap = codeSvc.retrieveDeptCodeAll(param);
		List<CodeVo> rowDataList = (List<CodeVo>) serviceMap.get("rowDataList");

		model.addAttribute("deptList", rowDataList);
		
		//관리부서목록
		List deptCode = reservationHealthSvc.retrieveDeptCode();
		model.addAttribute("dept", deptCode);
		return "injeinc/boffice/ex/reservationHealth/reservation_health_form";
	}

	@RequestMapping("/boffice/ex/reservationHealth/reservationHealthReg.do")
	public String reservationHealthReg(
			final MultipartHttpServletRequest multiRequest
			, @ModelAttribute("ReservationIndexHealthVo") ReservationIndexHealthVo reservationIndexHealthVo
			, ModelMap model
			) throws Exception {

		ReservationAddHealthVo reservationAddHealthVo;
		ReservationItemHealthVo reservationItemHealthVo;
		ReservationAddNodateVo reservationAddNodateVo;
		
		//예약번호
		String riIdx = reservationIdgenService.getNextStringId();
		reservationIndexHealthVo.setRiIdx(riIdx);

		String userid = (String) WebUtils.getSessionAttribute(multiRequest, "SesUserId");

		reservationIndexHealthVo.setRegId(userid);
		
		//예약구분(H:진료예약, R:장비대여, F:통합시설예약, E:통합행사예약)
		reservationIndexHealthVo.setRiType("H");

		
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
		
		//일요일 회차별 등록
		if(reservationIndexHealthVo.getRiDayWeek().indexOf("1") > -1){
			String riConfirmSdateHh1[] = multiRequest.getParameterValues("riConfirmSdateHh1");
			String riConfirmSdateMm1[] = multiRequest.getParameterValues("riConfirmSdateMm1");
			if(riConfirmSdateHh1 != null){
				for(int i = 0; i < riConfirmSdateHh1.length; i++){
					reservationAddHealthVo = new ReservationAddHealthVo();
					
					reservationAddHealthVo.setRiIdx(riIdx);
					reservationAddHealthVo.setRhIdx(i+1);
					reservationAddHealthVo.setRiWeek("1");
					reservationAddHealthVo.setRiConfirmSdateHh(riConfirmSdateHh1[i]);
					reservationAddHealthVo.setRiConfirmSdateMm(riConfirmSdateMm1[i]);
					reservationAddHealthVo.setRegId(userid);
					
					reservationHealthSvc.registReservationAddWeek(reservationAddHealthVo);
					}
				}
			}
		//월요일 회차별 등록
		if(reservationIndexHealthVo.getRiDayWeek().indexOf("2") > -1){
			String riConfirmSdateHh2[] = multiRequest.getParameterValues("riConfirmSdateHh2");
			String riConfirmSdateMm2[] = multiRequest.getParameterValues("riConfirmSdateMm2");
			if(riConfirmSdateHh2 != null){
				for(int i = 0; i < riConfirmSdateHh2.length; i++){
					reservationAddHealthVo = new ReservationAddHealthVo();
					
					reservationAddHealthVo.setRiIdx(riIdx);
					reservationAddHealthVo.setRhIdx(i+1);
					reservationAddHealthVo.setRiWeek("2");
					reservationAddHealthVo.setRiConfirmSdateHh(riConfirmSdateHh2[i]);
					reservationAddHealthVo.setRiConfirmSdateMm(riConfirmSdateMm2[i]);
					reservationAddHealthVo.setRegId(userid);
					
					reservationHealthSvc.registReservationAddWeek(reservationAddHealthVo);
				}
			}
		}
		//화요일 회차별 등록
		if(reservationIndexHealthVo.getRiDayWeek().indexOf("3") > -1){
			String riConfirmSdateHh3[] = multiRequest.getParameterValues("riConfirmSdateHh3");
			String riConfirmSdateMm3[] = multiRequest.getParameterValues("riConfirmSdateMm3");
			if(riConfirmSdateHh3 != null){
				for(int i = 0; i < riConfirmSdateHh3.length; i++){
					reservationAddHealthVo = new ReservationAddHealthVo();
					
					reservationAddHealthVo.setRiIdx(riIdx);
					reservationAddHealthVo.setRhIdx(i+1);
					reservationAddHealthVo.setRiWeek("3");
					reservationAddHealthVo.setRiConfirmSdateHh(riConfirmSdateHh3[i]);
					reservationAddHealthVo.setRiConfirmSdateMm(riConfirmSdateMm3[i]);
					reservationAddHealthVo.setRegId(userid);

					reservationHealthSvc.registReservationAddWeek(reservationAddHealthVo);
				}
			}
		}
		//수요일 회차별 등록
		if(reservationIndexHealthVo.getRiDayWeek().indexOf("4") > -1){
			String riConfirmSdateHh4[] = multiRequest.getParameterValues("riConfirmSdateHh4");
			String riConfirmSdateMm4[] = multiRequest.getParameterValues("riConfirmSdateMm4");
			if(riConfirmSdateHh4 != null){
				for(int i = 0; i < riConfirmSdateHh4.length; i++){
					reservationAddHealthVo = new ReservationAddHealthVo();
					
					reservationAddHealthVo.setRiIdx(riIdx);
					reservationAddHealthVo.setRhIdx(i+1);
					reservationAddHealthVo.setRiWeek("4");
					reservationAddHealthVo.setRiConfirmSdateHh(riConfirmSdateHh4[i]);
					reservationAddHealthVo.setRiConfirmSdateMm(riConfirmSdateMm4[i]);
					reservationAddHealthVo.setRegId(userid);

					reservationHealthSvc.registReservationAddWeek(reservationAddHealthVo);
				}
			}
		}
		//목요일 회차별 등록
		if(reservationIndexHealthVo.getRiDayWeek().indexOf("5") > -1){
			String riConfirmSdateHh5[] = multiRequest.getParameterValues("riConfirmSdateHh5");
			String riConfirmSdateMm5[] = multiRequest.getParameterValues("riConfirmSdateMm5");
			if(riConfirmSdateHh5 != null){
				for(int i = 0; i < riConfirmSdateHh5.length; i++){
					reservationAddHealthVo = new ReservationAddHealthVo();
					
					reservationAddHealthVo.setRiIdx(riIdx);
					reservationAddHealthVo.setRhIdx(i+1);
					reservationAddHealthVo.setRiWeek("5");
					reservationAddHealthVo.setRiConfirmSdateHh(riConfirmSdateHh5[i]);
					reservationAddHealthVo.setRiConfirmSdateMm(riConfirmSdateMm5[i]);
					reservationAddHealthVo.setRegId(userid);

					reservationHealthSvc.registReservationAddWeek(reservationAddHealthVo);
				}
			}
		}
		//금요일 회차별 등록
		if(reservationIndexHealthVo.getRiDayWeek().indexOf("6") > -1){
			String riConfirmSdateHh6[] = multiRequest.getParameterValues("riConfirmSdateHh6");
			String riConfirmSdateMm6[] = multiRequest.getParameterValues("riConfirmSdateMm6");
			if(riConfirmSdateHh6 != null){
				for(int i = 0; i < riConfirmSdateHh6.length; i++){
					reservationAddHealthVo = new ReservationAddHealthVo();
					
					reservationAddHealthVo.setRiIdx(riIdx);
					reservationAddHealthVo.setRhIdx(i+1);
					reservationAddHealthVo.setRiWeek("6");
					reservationAddHealthVo.setRiConfirmSdateHh(riConfirmSdateHh6[i]);
					reservationAddHealthVo.setRiConfirmSdateMm(riConfirmSdateMm6[i]);
					reservationAddHealthVo.setRegId(userid);

					reservationHealthSvc.registReservationAddWeek(reservationAddHealthVo);
				}
			}
		}
		//토요일 회차별 등록
		if(reservationIndexHealthVo.getRiDayWeek().indexOf("7") > -1){
			String riConfirmSdateHh7[] = multiRequest.getParameterValues("riConfirmSdateHh7");
			String riConfirmSdateMm7[] = multiRequest.getParameterValues("riConfirmSdateMm7");
			if(riConfirmSdateHh7 != null){
				for(int i = 0; i < riConfirmSdateHh7.length; i++){
					reservationAddHealthVo = new ReservationAddHealthVo();
					
					reservationAddHealthVo.setRiIdx(riIdx);
					reservationAddHealthVo.setRhIdx(i+1);
					reservationAddHealthVo.setRiWeek("7");
					reservationAddHealthVo.setRiConfirmSdateHh(riConfirmSdateHh7[i]);
					reservationAddHealthVo.setRiConfirmSdateMm(riConfirmSdateMm7[i]);
					reservationAddHealthVo.setRegId(userid);

					reservationHealthSvc.registReservationAddWeek(reservationAddHealthVo);
				}
			}
		}
		
		
		//예약등록(추가항목2) 입력
		String tableNum[] = multiRequest.getParameterValues("tableNum");
		String rtItemTitle[] = multiRequest.getParameterValues("rtItemTitle");
		String rtItemType[] = multiRequest.getParameterValues("rtItemType");
		String rtItemNotice[] = multiRequest.getParameterValues("rtItemNotice");
		String rtItemExampleText = "";
		
		if(tableNum != null){
			
			for(int i=0; i < tableNum.length; i++){
				reservationItemHealthVo = new ReservationItemHealthVo();
				rtItemExampleText  = ""; //초기화
				reservationItemHealthVo.setRiIdx(riIdx);
				reservationItemHealthVo.setRtIdx(i+1);
				reservationItemHealthVo.setRtItemTitle(rtItemTitle[i]);
				reservationItemHealthVo.setRtItemType(rtItemType[i]);
				reservationItemHealthVo.setRtItemNotice(rtItemNotice[i]);
				reservationItemHealthVo.setRtItemUseCheck(multiRequest.getParameter("rtItemUseCheck_"+tableNum[i]));
				reservationItemHealthVo.setRtItemEtcUseCheck(multiRequest.getParameter("rtItemEtcUseCheck_"+tableNum[i]));
				reservationItemHealthVo.setRtItemExampleLen(multiRequest.getParameterValues("rtItemExample"+tableNum[i]).length);
				
				reservationItemHealthVo.setRegId(userid);
				
				String rtItemExample[] = multiRequest.getParameterValues("rtItemExample"+tableNum[i]);
				if(rtItemExample != null){
					for(int j=0; j < rtItemExample.length; j++){
						rtItemExampleText += rtItemExample[j]+"||";
					}
					
				}
				
				reservationItemHealthVo.setRtItemExample(rtItemExampleText);
				
				reservationHealthSvc.registReservationHealthItem(reservationItemHealthVo);
			}
		}
		
		reservationIndexHealthVo.setRiDayWeek(reservationIndexHealthVo.getRiDayWeek().replaceAll(",", "||"));

		reservationIndexHealthVo.setRiOffLotUse(reservationIndexHealthVo.getRiOffLotUse().replaceAll(",", "||"));
		reservationIndexHealthVo.setRiConfirmYn("Y");
		reservationIndexHealthVo.setRiUserloginType("A");

		/*if(reservationIndexHealthVo.getRiAfterRegsterYn().equals("Y")){
			reservationIndexHealthVo.setRiSdate(multiRequest.getParameter("riSdate1"));
		}*/
		// 파일업로드
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		ReservationIndexHealthVo uploadVO = new ReservationIndexHealthVo();
    	uploadVO = reservationHealthSvc.uploadFile(files, reservationIndexHealthVo);

    	reservationIndexHealthVo.setRiImageFileId(uploadVO.getRiImageFileId());

		reservationHealthSvc.registReservationHealth(reservationIndexHealthVo);

		String SVC_MSGE = Message.getMessage("100.message");
		return alert("/boffice/ex/reservationHealth/reservationHealthList.do", SVC_MSGE, model);
	}
	
	

	@RequestMapping("/boffice/ex/reservationHealth/reservationHealthMod.do")
	public String reservationHealthMod(
			final MultipartHttpServletRequest multiRequest
			, @ModelAttribute("ReservationIndexHealthVo") ReservationIndexHealthVo reservationIndexHealthVo
			, ModelMap model
			) throws Exception {

		ReservationAddHealthVo reservationAddHealthVo;
		ReservationItemHealthVo reservationItemHealthVo;
		ReservationAddNodateVo	reservationAddNodateVo;

		String userid = (String) WebUtils.getSessionAttribute(multiRequest, "SesUserId");
		reservationIndexHealthVo.setModId(userid);


		
		
		//운영불가(휴무)일 전체 삭제
		reservationHealthSvc.removeReservationHealthNodate(reservationIndexHealthVo);
		//운영불가(휴무)일
		String noDateContent[] = multiRequest.getParameterValues("noDateContent");
		if(noDateContent != null){
			for(int i = 0; i < noDateContent.length; i++){
				reservationAddNodateVo = new ReservationAddNodateVo();
				
				reservationAddNodateVo.setRiIdx(reservationIndexHealthVo.getRiIdx());
				reservationAddNodateVo.setRaIdx(i+1);
				reservationAddNodateVo.setRaNodate(noDateContent[i]);
				reservationAddNodateVo.setRegId(userid);
				
				reservationHealthSvc.registReservationAddNodate(reservationAddNodateVo);
			}
		}
		
		//요일별회차 전체삭제
		reservationHealthSvc.removeReservationHealthAddWeek(reservationIndexHealthVo);
		//일요일 회차별 등록
		if(reservationIndexHealthVo.getRiDayWeek().indexOf("1") > -1){
			String riConfirmSdateHh1[] = multiRequest.getParameterValues("riConfirmSdateHh1");
			String riConfirmSdateMm1[] = multiRequest.getParameterValues("riConfirmSdateMm1");
			if(riConfirmSdateHh1 != null){
				for(int i = 0; i < riConfirmSdateHh1.length; i++){
					reservationAddHealthVo = new ReservationAddHealthVo();
					
					reservationAddHealthVo.setRiIdx(reservationIndexHealthVo.getRiIdx());
					reservationAddHealthVo.setRhIdx(i+1);
					reservationAddHealthVo.setRiWeek("1");
					reservationAddHealthVo.setRiConfirmSdateHh(riConfirmSdateHh1[i]);
					reservationAddHealthVo.setRiConfirmSdateMm(riConfirmSdateMm1[i]);
					reservationAddHealthVo.setRegId(userid);
					
					reservationHealthSvc.registReservationAddWeek(reservationAddHealthVo);
					}
				}
			}
		//월요일 회차별 등록
		if(reservationIndexHealthVo.getRiDayWeek().indexOf("2") > -1){
			String riConfirmSdateHh2[] = multiRequest.getParameterValues("riConfirmSdateHh2");
			String riConfirmSdateMm2[] = multiRequest.getParameterValues("riConfirmSdateMm2");
			if(riConfirmSdateHh2 != null){
				for(int i = 0; i < riConfirmSdateHh2.length; i++){
					reservationAddHealthVo = new ReservationAddHealthVo();
					
					reservationAddHealthVo.setRiIdx(reservationIndexHealthVo.getRiIdx());
					reservationAddHealthVo.setRhIdx(i+1);
					reservationAddHealthVo.setRiWeek("2");
					reservationAddHealthVo.setRiConfirmSdateHh(riConfirmSdateHh2[i]);
					reservationAddHealthVo.setRiConfirmSdateMm(riConfirmSdateMm2[i]);
					reservationAddHealthVo.setRegId(userid);
					
					reservationHealthSvc.registReservationAddWeek(reservationAddHealthVo);
				}
			}
		}
		//화요일 회차별 등록
		if(reservationIndexHealthVo.getRiDayWeek().indexOf("3") > -1){
			String riConfirmSdateHh3[] = multiRequest.getParameterValues("riConfirmSdateHh3");
			String riConfirmSdateMm3[] = multiRequest.getParameterValues("riConfirmSdateMm3");
			if(riConfirmSdateHh3 != null){
				for(int i = 0; i < riConfirmSdateHh3.length; i++){
					reservationAddHealthVo = new ReservationAddHealthVo();
					
					reservationAddHealthVo.setRiIdx(reservationIndexHealthVo.getRiIdx());
					reservationAddHealthVo.setRhIdx(i+1);
					reservationAddHealthVo.setRiWeek("3");
					reservationAddHealthVo.setRiConfirmSdateHh(riConfirmSdateHh3[i]);
					reservationAddHealthVo.setRiConfirmSdateMm(riConfirmSdateMm3[i]);
					reservationAddHealthVo.setRegId(userid);

					reservationHealthSvc.registReservationAddWeek(reservationAddHealthVo);
				}
			}
		}
		//수요일 회차별 등록
		if(reservationIndexHealthVo.getRiDayWeek().indexOf("4") > -1){
			String riConfirmSdateHh4[] = multiRequest.getParameterValues("riConfirmSdateHh4");
			String riConfirmSdateMm4[] = multiRequest.getParameterValues("riConfirmSdateMm4");
			if(riConfirmSdateHh4 != null){
				for(int i = 0; i < riConfirmSdateHh4.length; i++){
					reservationAddHealthVo = new ReservationAddHealthVo();
					
					reservationAddHealthVo.setRiIdx(reservationIndexHealthVo.getRiIdx());
					reservationAddHealthVo.setRhIdx(i+1);
					reservationAddHealthVo.setRiWeek("4");
					reservationAddHealthVo.setRiConfirmSdateHh(riConfirmSdateHh4[i]);
					reservationAddHealthVo.setRiConfirmSdateMm(riConfirmSdateMm4[i]);
					reservationAddHealthVo.setRegId(userid);

					reservationHealthSvc.registReservationAddWeek(reservationAddHealthVo);
				}
			}
		}
		//목요일 회차별 등록
		if(reservationIndexHealthVo.getRiDayWeek().indexOf("5") > -1){
			String riConfirmSdateHh5[] = multiRequest.getParameterValues("riConfirmSdateHh5");
			String riConfirmSdateMm5[] = multiRequest.getParameterValues("riConfirmSdateMm5");
			if(riConfirmSdateHh5 != null){
				for(int i = 0; i < riConfirmSdateHh5.length; i++){
					reservationAddHealthVo = new ReservationAddHealthVo();
					
					reservationAddHealthVo.setRiIdx(reservationIndexHealthVo.getRiIdx());
					reservationAddHealthVo.setRhIdx(i+1);
					reservationAddHealthVo.setRiWeek("5");
					reservationAddHealthVo.setRiConfirmSdateHh(riConfirmSdateHh5[i]);
					reservationAddHealthVo.setRiConfirmSdateMm(riConfirmSdateMm5[i]);
					reservationAddHealthVo.setRegId(userid);

					reservationHealthSvc.registReservationAddWeek(reservationAddHealthVo);
				}
			}
		}
		//금요일 회차별 등록
		if(reservationIndexHealthVo.getRiDayWeek().indexOf("6") > -1){
			String riConfirmSdateHh6[] = multiRequest.getParameterValues("riConfirmSdateHh6");
			String riConfirmSdateMm6[] = multiRequest.getParameterValues("riConfirmSdateMm6");
			if(riConfirmSdateHh6 != null){
				for(int i = 0; i < riConfirmSdateHh6.length; i++){
					reservationAddHealthVo = new ReservationAddHealthVo();
					
					reservationAddHealthVo.setRiIdx(reservationIndexHealthVo.getRiIdx());
					reservationAddHealthVo.setRhIdx(i+1);
					reservationAddHealthVo.setRiWeek("6");
					reservationAddHealthVo.setRiConfirmSdateHh(riConfirmSdateHh6[i]);
					reservationAddHealthVo.setRiConfirmSdateMm(riConfirmSdateMm6[i]);
					reservationAddHealthVo.setRegId(userid);

					reservationHealthSvc.registReservationAddWeek(reservationAddHealthVo);
				}
			}
		}
		//토요일 회차별 등록
		if(reservationIndexHealthVo.getRiDayWeek().indexOf("7") > -1){
			String riConfirmSdateHh7[] = multiRequest.getParameterValues("riConfirmSdateHh7");
			String riConfirmSdateMm7[] = multiRequest.getParameterValues("riConfirmSdateMm7");
			if(riConfirmSdateHh7 != null){
				for(int i = 0; i < riConfirmSdateHh7.length; i++){
					reservationAddHealthVo = new ReservationAddHealthVo();
					
					reservationAddHealthVo.setRiIdx(reservationIndexHealthVo.getRiIdx());
					reservationAddHealthVo.setRhIdx(i+1);
					reservationAddHealthVo.setRiWeek("7");
					reservationAddHealthVo.setRiConfirmSdateHh(riConfirmSdateHh7[i]);
					reservationAddHealthVo.setRiConfirmSdateMm(riConfirmSdateMm7[i]);
					reservationAddHealthVo.setRegId(userid);

					reservationHealthSvc.registReservationAddWeek(reservationAddHealthVo);
				}
			}
		}
		
		
		//예약등록(추가항목2) 전체삭제
		reservationHealthSvc.removeReservationHealthItem(reservationIndexHealthVo);
		//예약등록(추가항목2)입력
		String tableNum[] = multiRequest.getParameterValues("tableNum");
		String rtItemTitle[] = multiRequest.getParameterValues("rtItemTitle");
		String rtItemType[] = multiRequest.getParameterValues("rtItemType");
		String rtItemNotice[] = multiRequest.getParameterValues("rtItemNotice");
		String rtItemExampleText = "";
		
		
		if(tableNum != null){
			
			for(int i=0; i < tableNum.length; i++){
				reservationItemHealthVo = new ReservationItemHealthVo();
				rtItemExampleText  = ""; //초기화
				
				reservationItemHealthVo.setRiIdx(reservationIndexHealthVo.getRiIdx());
				reservationItemHealthVo.setRtIdx(i+1);
				reservationItemHealthVo.setRtItemTitle(rtItemTitle[i]);
				reservationItemHealthVo.setRtItemType(rtItemType[i]);
				reservationItemHealthVo.setRtItemNotice(rtItemNotice[i]);
				reservationItemHealthVo.setRtItemUseCheck(multiRequest.getParameter("rtItemUseCheck_"+tableNum[i]));
				reservationItemHealthVo.setRtItemEtcUseCheck(multiRequest.getParameter("rtItemEtcUseCheck_"+tableNum[i]));
				reservationItemHealthVo.setRtItemExampleLen(multiRequest.getParameterValues("rtItemExample"+tableNum[i]).length);
				
				reservationItemHealthVo.setRegId(userid);
				
				String rtItemExample[] = multiRequest.getParameterValues("rtItemExample"+tableNum[i]);
				if(rtItemExample != null){
					for(int j=0; j < rtItemExample.length; j++){
						rtItemExampleText += rtItemExample[j]+"||";
					}
				}
				
				reservationItemHealthVo.setRtItemExample(rtItemExampleText);
				
				reservationHealthSvc.registReservationHealthItem(reservationItemHealthVo);
			}
		}
		
		reservationIndexHealthVo.setRiDayWeek(reservationIndexHealthVo.getRiDayWeek().replaceAll(",", "||"));

		reservationIndexHealthVo.setRiOffLotUse(reservationIndexHealthVo.getRiOffLotUse().replaceAll(",", "||"));

		/*if(reservationIndexHealthVo.getRiTermType().equals("O")){
			reservationIndexHealthVo.setRiSdate(multiRequest.getParameter("riSdate1"));
		}*/

		// 파일업로드
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		ReservationIndexHealthVo uploadVO = new ReservationIndexHealthVo();
    	uploadVO = reservationHealthSvc.uploadFile(files, reservationIndexHealthVo);

    	reservationIndexHealthVo.setRiImageFileId(uploadVO.getRiImageFileId());

		reservationHealthSvc.modifyReservationHealth(reservationIndexHealthVo);

		StringBuffer addParam = new StringBuffer();
		addParam.append("?pageIndex=").append(reservationIndexHealthVo.getPageIndex());
		addParam.append("&searchCondition=").append(reservationIndexHealthVo.getSearchCondition());
		addParam.append("&searchKeyword=").append(reservationIndexHealthVo.getSearchKeyword());

		String SVC_MSGE = Message.getMessage("401.message");
		return alert("/boffice/ex/reservationHealth/reservationHealthList.do"+addParam.toString(), SVC_MSGE, model);
	}

	@RequestMapping("/boffice/ex/reservationHealth/reservationHealthRmv.do")
	public String reservationHealthRmv(
			HttpServletRequest request
			, @ModelAttribute("ReservationIndexHealthVo") ReservationIndexHealthVo reservationIndexHealthVo
			, ModelMap model
			) throws Exception {

		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		reservationIndexHealthVo.setModId(userid);

		reservationHealthSvc.removeReservationHealth(reservationIndexHealthVo);

		StringBuffer addParam = new StringBuffer();
		addParam.append("?pageIndex=").append(reservationIndexHealthVo.getPageIndex());
		addParam.append("&searchCondition=").append(reservationIndexHealthVo.getSearchCondition());
		addParam.append("&searchKeyword=").append(reservationIndexHealthVo.getSearchKeyword());

		String SVC_MSGE = Message.getMessage("501.message");
		return alert("/boffice/ex/reservationHealth/reservationHealthList.do"+addParam.toString(), SVC_MSGE, model);
	}
	
	
	
	
	
	@RequestMapping("/boffice/ex/reservationHealth/reservationHealthView.do")
	public String reservationFacView(
			HttpServletRequest request
			, @ModelAttribute("ReservationIndexHealthVo") ReservationIndexHealthVo reservationIndexHealthVo
			, @ModelAttribute("ReservationHealthDayVo") ReservationHealthDayVo reservationHealthDayVo
			, @ModelAttribute("ReservationUserInfoHealthVo") ReservationUserInfoHealthVo reservationUserInfoHealthVo
			, ModelMap model
			) throws Exception {

		//예약상세
		ReservationIndexHealthVo result = reservationHealthSvc.retrieveReservationHealth(reservationIndexHealthVo);
		
		
		/*String riIdx = request.getParameter("riIdx");
		String hYear = request.getParameter("year");
		String hMonth = request.getParameter("month");
		String hDay = request.getParameter("day");
		String hWeek = request.getParameter("week");
		String rhIdx = request.getParameter("rhIdx");*/
		
		
		if(reservationUserInfoHealthVo.gethYear() != null){
		
		
		/*reservationUserInfoHealthVo.setRiIdx(riIdx);
		reservationUserInfoHealthVo.sethYear(hYear);
		reservationUserInfoHealthVo.sethMonth(hMonth);
		reservationUserInfoHealthVo.sethDay(hDay);
		reservationUserInfoHealthVo.sethWeek(hWeek);
		reservationUserInfoHealthVo.setRhIdx(rhIdx);*/
			
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(reservationUserInfoHealthVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(reservationUserInfoHealthVo.getPageUnit());
		paginationInfo.setPageSize(reservationUserInfoHealthVo.getPageSize());

		reservationUserInfoHealthVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		reservationUserInfoHealthVo.setLastIndex(paginationInfo.getLastRecordIndex());
		reservationUserInfoHealthVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		

		Map<String, Object> map = reservationHealthSvc.retrieveUserHealthList(reservationUserInfoHealthVo);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));

		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);
		
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("riIdx", reservationUserInfoHealthVo.getRiIdx());
		param.put("year", reservationUserInfoHealthVo.gethYear());
		param.put("month", reservationUserInfoHealthVo.gethMonth());
		param.put("day", reservationUserInfoHealthVo.gethDay());
		param.put("week", reservationUserInfoHealthVo.gethWeek());

		
		//해당일자 진료회차 목록
		HashMap<String, Object> weekHealthDataMap = (HashMap<String, Object>) reservationHealthSvc.retrieveWeekHealthList(param);
		List<ReservationAddHealthVo> weekHealthDataList = (List<ReservationAddHealthVo>) weekHealthDataMap.get("weekHealthDataList");
		model.addAttribute("weekHealthDataList", weekHealthDataList);
		}
		

		Calendar cal = Calendar.getInstance();

		if(reservationHealthDayVo.getYear()==null || reservationHealthDayVo.getYear().equals("")){
			reservationHealthDayVo.setYear(Integer.toString(cal.get(Calendar.YEAR)));
		}
		if(reservationHealthDayVo.getMonth()==null || reservationHealthDayVo.getMonth().equals("")){
			reservationHealthDayVo.setMonth(Integer.toString(cal.get(Calendar.MONTH)+1));
		}
		int iYear  = Integer.parseInt(reservationHealthDayVo.getYear());
		int iMonth = Integer.parseInt(reservationHealthDayVo.getMonth());

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
		reservationHealthDayVo.setYear(Integer.toString(iYear));
		reservationHealthDayVo.setMonth(Integer.toString(iMonth));

		cal.set(iYear,iMonth-1,1);

		reservationHealthDayVo.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		reservationHealthDayVo.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));

		List CalInfoList = reservationHealthSvc.retrieveListReservationHealthCal(reservationHealthDayVo);

		result.setPageIndex(reservationIndexHealthVo.getPageIndex());
		result.setSearchCondition(reservationIndexHealthVo.getSearchCondition());
		result.setSearchKeyword(reservationIndexHealthVo.getSearchKeyword());

		model.addAttribute("ReservationIndexHealthVo", result);
		model.addAttribute("CalInfoList", CalInfoList);

		//return "injeinc/boffice/ex/reservationFac/reservation_fac_view";
		return "injeinc/boffice/ex/reservationHealth/reservation_health_view";
	}
	
	/* 신청자 삭제 */
	@RequestMapping("/boffice/ex/reservationHealth/reservationHealthUserInfoRmv.do")
	public String reservationHealthUserInfoRmv(HttpServletRequest request, @ModelAttribute("ReservationUserInfoHealthVo") ReservationUserInfoHealthVo reservationUserInfoHealthVo, ModelMap model) throws Exception {

		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		reservationUserInfoHealthVo.setModId(userid);
		reservationHealthSvc.removeReservationHealthUserInfo(reservationUserInfoHealthVo);
		String MSG ="";
		if("Y".equals(reservationUserInfoHealthVo.getRuDelYn())){
			MSG = "삭제";
		}
		if("C".equals(reservationUserInfoHealthVo.getRuDelYn())){
			MSG = "취소";
		}
		

		return alert("/boffice/ex/reservationHealth/reservationHealthView.do?riIdx="+reservationUserInfoHealthVo.getRiIdx(), "접수자 "+MSG+"완료", model);
	}
	
	
	//신청자 등록
	@RequestMapping("/boffice/ex/reservationHealth/reservationHealthUserForm.do")
	public String reservationHealthUserForm(
			HttpServletRequest request
			, @ModelAttribute("ReservationIndexHealthVo") ReservationIndexHealthVo reservationIndexHealthVo
			, @ModelAttribute("ReservationUserInfoHealthVo") ReservationUserInfoHealthVo reservationUserInfoHealthVo
			, @ModelAttribute("ReservationHealthDayVo") ReservationHealthDayVo reservationHealthDayVo
			, ModelMap model
			) throws Exception {
		
		

		reservationIndexHealthVo = reservationHealthSvc.retrieveReservationHealth(reservationIndexHealthVo);
		
		reservationIndexHealthVo.setRiIdx(reservationIndexHealthVo.getRiIdx());
		reservationHealthDayVo.setRiIdx(reservationIndexHealthVo.getRiIdx());
		
		reservationHealthDayVo.setRiAfterRegsterYn(reservationIndexHealthVo.getRiAfterRegsterYn());
		reservationHealthDayVo.setRiConfirmSdate(reservationIndexHealthVo.getRiConfirmSdate());
		reservationHealthDayVo.setRiConfirmSdateHh(reservationIndexHealthVo.getRiConfirmSdateHh());
		reservationHealthDayVo.setRiConfirmSdateMm(reservationIndexHealthVo.getRiConfirmSdateMm());
		reservationHealthDayVo.setRiConfirmEdate(reservationIndexHealthVo.getRiConfirmEdate());
		reservationHealthDayVo.setRiConfirmEdateHh(reservationIndexHealthVo.getRiConfirmEdateHh());
		reservationHealthDayVo.setRiConfirmEdateMm(reservationIndexHealthVo.getRiConfirmEdateMm());
		
		
		
		Calendar cal = Calendar.getInstance();

		if(reservationHealthDayVo.getYear()==null || reservationHealthDayVo.getYear().equals("")){
			reservationHealthDayVo.setYear(Integer.toString(cal.get(Calendar.YEAR)));
		}
		if(reservationHealthDayVo.getMonth()==null || reservationHealthDayVo.getMonth().equals("")){
			reservationHealthDayVo.setMonth(Integer.toString(cal.get(Calendar.MONTH)+1));
		}
		int iYear  = Integer.parseInt(reservationHealthDayVo.getYear());
		int iMonth = Integer.parseInt(reservationHealthDayVo.getMonth());

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
		reservationHealthDayVo.setYear(Integer.toString(iYear));
		reservationHealthDayVo.setMonth(Integer.toString(iMonth));

		cal.set(iYear,iMonth-1,1);

		reservationHealthDayVo.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		reservationHealthDayVo.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));

		List CalInfoList = reservationHealthSvc.retrieveListReservationHealthDate(reservationHealthDayVo);
		
		
		/*ReservationUserInfoHealthVo result = reservationHealthSvc.retrieveReservationHealthUserInfo(reservationUserInfoHealthVo);

		if(result != null) {
			reservationIndexHealthVo.setActionkey("modify");

			model.addAttribute("ReservationUserInfoHealthVo", result);
		}else{
			if(reservationIndexHealthVo != null){
				if(reservationIndexHealthVo.getRiRecruitYn().equals("N") && reservationIndexHealthVo.getRiLotUse().equals("OO")){
					if(Integer.parseInt(reservationIndexHealthVo.getRiRecruitCnt())  <= reservationIndexHealthVo.getRiAccCnt() ){
						return alert(request.getContextPath()+"/boffice/ex/reservationHealth/reservationHealthView.do?riIdx="+reservationIndexHealthVo.getRiIdx(), "모집인원이 초과되었습니다.", model);
					}
				}
			}

			reservationIndexHealthVo.setActionkey("regist");
		}*/

		reservationIndexHealthVo.setRuIdx(reservationUserInfoHealthVo.getRuIdx());

		model.addAttribute("ReservationItemList", reservationHealthSvc.retrieveListReservationHealthItem(reservationIndexHealthVo));
		model.addAttribute("ReservationIndexHealthVo", reservationIndexHealthVo);
		model.addAttribute("CalInfoList", CalInfoList);

		return "injeinc/boffice/ex/reservationHealth/reservation_health_user_form";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/boffice/ex/reservationHealth/reservationUserHealthAx.do")
	public void fofficeLoginListAx(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String riIdx = request.getParameter("riIdx");
		String hYear = request.getParameter("year");
		String hMonth = request.getParameter("month");
		String hDay = request.getParameter("day");
		String hWeek = request.getParameter("week");
		
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("riIdx", riIdx);
		param.put("hYear", hYear);
		param.put("hMonth", hMonth);
		param.put("hDay", hDay);
		param.put("hWeek", hWeek);

		//해당일자 진료예약 목록
		HashMap<String, Object> userHealthDataMap = (HashMap<String, Object>) reservationHealthSvc.retrieveUserHealthCnt(param);
		List<ReservationUserInfoHealthVo> userHealthDataList = (List<ReservationUserInfoHealthVo>) userHealthDataMap.get("userHealthDataList");

		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("USERHEALT_CODE", userHealthDataMap.get("SVC_CODE"));
		jsonMap.put("USERHEALT_MSGE", userHealthDataMap.get("SVC_MSGE"));
		jsonMap.put("userHealthDataList", userHealthDataList);
		jsonView.render(jsonMap, request, response);
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/boffice/ex/reservationHealth/reservationUserInfoHealthReg.do")
	public String reservationUserInfoReg(
			HttpServletRequest request
			, @ModelAttribute("ReservationUserInfoHealthVo") ReservationUserInfoHealthVo reservationUserInfoHealthVo
			, @ModelAttribute("ReservationHealthUserAnswerVo") ReservationHealthUserAnswerVo reservationHealthUserAnswerVo
			, ModelMap model
			) throws Exception {

		ReservationIndexHealthVo reservationIndexHealthVo = new ReservationIndexHealthVo();

		String riIdx = reservationUserInfoHealthVo.getRiIdx();
		reservationIndexHealthVo.setRiIdx(riIdx);
		String ruIdx = reservationUserIdGnrService.getNextStringId();
		reservationUserInfoHealthVo.setRuIdx(ruIdx);

		List<ReservationItemHealthVo> itemList = reservationHealthSvc.retrieveListReservationHealthItem(reservationIndexHealthVo);

		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		reservationUserInfoHealthVo.setRegId(userid);

		ReservationIndexHealthVo result = reservationHealthSvc.retrieveReservationHealth(reservationIndexHealthVo);

		ArrayList riIdxList = new ArrayList();
		String redundancyList[] = reservationIndexHealthVo.getRiRedundancy().split(";");

		for(int i=0; i < redundancyList.length; i++){
			riIdxList.add(redundancyList[i]);
		}

		reservationUserInfoHealthVo.setRiIdxList(riIdxList);
		/*if(!reservationUserInfoHealthVo.getRuDupkey().equals("")){
			if(reservationHealthSvc.retrieveReservationHealthUserInfoDupCnt(reservationUserInfoHealthVo) > 0){
				return alert("/boffice/ex/reservationHealth/reservationHealthUserForm.do?riIdx="+reservationIndexHealthVo.getRiIdx(), "이미 예약을 하셨습니다.", model);
			}else if(reservationHealthSvc.retrieveReservationHealthRedundancyDupCnt(reservationUserInfoHealthVo) > 0){
				return alert("/boffice/ex/reservationHealth/reservationHealthUserForm.do?riIdx="+reservationIndexHealthVo.getRiIdx(), "일부 행사와 중복 예약을 하실 수 없습니다.", model);
			}
		}

		if(result.getRiLotUse().equals("OO")){
			reservationUserInfoHealthVo.setRuLotResult("S");
		}else{
			reservationUserInfoHealthVo.setRuLotResult("R");
		}*/
		
		//신청인원
		int rhRecruitCnt = Integer.valueOf(reservationUserInfoHealthVo.getRhRecruitCnt());
		
		//정원체크
		int RiRecruitCnt =  Integer.valueOf(result.getRiRecruitCnt());
		
		//신청인원체크
		int HealUserCnt = reservationHealthSvc.retrieveReservationHealthUserCnt(reservationUserInfoHealthVo);
		
		
		String SVC_MSGE = "";
		
		if((HealUserCnt+rhRecruitCnt) <= RiRecruitCnt){
			reservationHealthSvc.registReservationHealthUserInfo(reservationUserInfoHealthVo);

			String[] raEtcAnswer = request.getParameterValues("raEtcAnswer");
			
				for(int i = 0; i < itemList.size(); i++) {
					reservationHealthUserAnswerVo = new ReservationHealthUserAnswerVo();
		 
					reservationHealthUserAnswerVo.setRuIdx(ruIdx);
					reservationHealthUserAnswerVo.setRiIdx(riIdx);
					reservationHealthUserAnswerVo.setRtIdx(i+1);
					String raAnswerVal = "";
					String[] raAnswer =  request.getParameterValues("raAnswer"+(i+1));
					
					if(raAnswer != null){
					for(int j = 0; j < raAnswer.length; j++){
						raAnswerVal = raAnswerVal + raAnswer[j] + "||";
					}
					reservationHealthUserAnswerVo.setRaAnswer(raAnswerVal);
	
					if(raEtcAnswer != null && raEtcAnswer.length > i){
						reservationHealthUserAnswerVo.setRaEtcAnswer(raEtcAnswer[i]);
					}
					
					reservationHealthSvc.registReservationHealthUserAnswer(reservationHealthUserAnswerVo);
					}
				}
					
			
			SVC_MSGE = Message.getMessage("100.message");
			
		}else{
			
			SVC_MSGE = "정원이 초과되었습니다.";
			
		}
		

		
	return alert("/boffice/ex/reservationHealth/reservationHealthView.do?riIdx="+riIdx, SVC_MSGE, model);
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/boffice/ex/reservationHealth/reservationUserCloseHealthReg.do")
	public String reservationUserCloseHealthReg(
			HttpServletRequest request
			, @ModelAttribute("ReservationUserInfoHealthVo") ReservationUserInfoHealthVo reservationUserInfoHealthVo
			, @ModelAttribute("ReservationHealthUserAnswerVo") ReservationHealthUserAnswerVo reservationHealthUserAnswerVo
			, ModelMap model
			) throws Exception {

		ReservationIndexHealthVo reservationIndexHealthVo = new ReservationIndexHealthVo();

		String riIdx = reservationUserInfoHealthVo.getRiIdx();
		reservationIndexHealthVo.setRiIdx(riIdx);
		String ruIdx = reservationUserIdGnrService.getNextStringId();
		reservationUserInfoHealthVo.setRuIdx(ruIdx);

		List<ReservationItemHealthVo> itemList = reservationHealthSvc.retrieveListReservationHealthItem(reservationIndexHealthVo);

		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		reservationUserInfoHealthVo.setRegId(userid);

		ReservationIndexHealthVo result = reservationHealthSvc.retrieveReservationHealth(reservationIndexHealthVo);

		ArrayList riIdxList = new ArrayList();
		String redundancyList[] = reservationIndexHealthVo.getRiRedundancy().split(";");

		for(int i=0; i < redundancyList.length; i++){
			riIdxList.add(redundancyList[i]);
		}

		reservationUserInfoHealthVo.setRiIdxList(riIdxList);
		
		
		//정원체크
		int RiRecruitCnt =  Integer.valueOf(result.getRiRecruitCnt());
		
		//신청인원
		int rhRecruitCnt = RiRecruitCnt * 2;
		
		//신청인원체크
		int HealUserCnt = reservationHealthSvc.retrieveReservationHealthUserCnt(reservationUserInfoHealthVo);
		
		
		//신청마감을 위한 더미사용자 저장 신청정원 * 2 배 인원
		reservationUserInfoHealthVo.setRuName("Health_Dummy");
		reservationUserInfoHealthVo.setRhRecruitCnt(String.valueOf(rhRecruitCnt));
		
		String SVC_MSGE = "";
		
		reservationHealthSvc.registReservationHealthUserInfo(reservationUserInfoHealthVo);

		
		SVC_MSGE = Message.getMessage("100.message");
			
			
		

		
	return alert("/boffice/ex/reservationHealth/reservationHealthView.do?riIdx="+riIdx, SVC_MSGE, model);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	

	

	@RequestMapping("/boffice/ex/reservationHealth/reservationUserInfoHealthMod.do")
	public String reservationUserInfoMod(HttpServletRequest request, @ModelAttribute("ReservationUserInfoHealthVo") ReservationUserInfoHealthVo reservationUserInfoHealthVo
			/*,@ModelAttribute("ReservationHealthUserAnswerVo") ReservationHealthUserAnswerVo reservationHealthUserAnswerVo*/
			, ModelMap model) throws Exception {

		ReservationIndexHealthVo reservationIndexHealthVo = new ReservationIndexHealthVo();

		String riIdx = reservationUserInfoHealthVo.getRiIdx();
		reservationIndexHealthVo.setRiIdx(riIdx);

		List<ReservationItemHealthVo> itemList = reservationHealthSvc.retrieveListReservationHealthItem(reservationIndexHealthVo);

		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		reservationUserInfoHealthVo.setModId(userid);

		ReservationIndexHealthVo result = reservationHealthSvc.retrieveReservationHealth(reservationIndexHealthVo);

		if(result.getRiLotUse().equals("OO")){
			reservationUserInfoHealthVo.setRuLotResult("S");
		}else{
			reservationUserInfoHealthVo.setRuLotResult("R");
		}

		reservationHealthSvc.modifyReservationHealthUserInfo(reservationUserInfoHealthVo);

		reservationHealthSvc.removeReservationHealthUserAnswer(reservationUserInfoHealthVo);

		/*String[] raEtcAnswer = request.getParameterValues("raEtcAnswer");

		for(int i = 0; i < itemList.size(); i++){
			reservationHealthUserAnswerVo = new ReservationHealthUserAnswerVo();
			
			reservationHealthUserAnswerVo.setRuIdx(reservationUserInfoVo.getRuIdx());
			reservationHealthUserAnswerVo.setRiIdx(riIdx);
			reservationHealthUserAnswerVo.setRtIdx(i+1);
			String raAnswerVal = "";
			String[] raAnswer =  request.getParameterValues("raAnswer"+(i+1));
			for(int j = 0; j < raAnswer.length; j++){
				raAnswerVal = raAnswerVal + raAnswer[j] + "||";
			}
			reservationHealthUserAnswerVo.setRaAnswer(raAnswerVal);
			
			if(raEtcAnswer != null && raEtcAnswer.length > i){
				reservationHealthUserAnswerVo.setRaEtcAnswer(raEtcAnswer[i]);
			}
			reservationHealthSvc.registReservationHealthUserAnswer(reservationHealthUserAnswerVo);
		}*/

		String SVC_MSGE = Message.getMessage("100.message");
		return alert("/boffice/ex/reservationHealth/reservationHealthView.do?riIdx="+riIdx, SVC_MSGE, model);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/boffice/ex/popup/reservationHealthListPopup.do")
	public String reservationHealthPagPopup(HttpServletRequest request, @ModelAttribute("ReservationIndexHealthVo") ReservationIndexHealthVo reservationIndexHealthVo, ModelMap model) throws Exception {

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(reservationIndexHealthVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(reservationIndexHealthVo.getPageUnit());
		paginationInfo.setPageSize(reservationIndexHealthVo.getPageSize());

		reservationIndexHealthVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		reservationIndexHealthVo.setLastIndex(paginationInfo.getLastRecordIndex());
		reservationIndexHealthVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		reservationIndexHealthVo.setRiType("E");

		Map<String, Object> map = reservationHealthSvc.retrievePagReservationHealth(reservationIndexHealthVo);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));

		paginationInfo.setTotalRecordCount(totCnt);

		HashMap<String, String> param = new HashMap<String, String>();
		HashMap<String, Object> serviceMap = codeSvc.retrieveDeptCodeAll(param);

		List<CodeVo> rowDataList = (List<CodeVo>) serviceMap.get("rowDataList");

		model.addAttribute("deptList", rowDataList);

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);

		return "injeinc/boffice/ex/reservationHealth/reservation_Health_list_popup";
	}

	/* 신청자 추첨 */
	@RequestMapping("/boffice/ex/reservationHealth/reservationHealthLot.do")
	public String reservationHealthLot(HttpServletRequest request, @ModelAttribute("ReservationIndexHealthVo") ReservationIndexHealthVo reservationIndexHealthVo, ModelMap model, String[] ruIdx, int procRecruitCnt) throws Exception {

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
		reservationIndexHealthVo.setStrList(ruIdxList);
		//System.out.println(reservationIndexHealthVo.getStrList());
		reservationHealthSvc.updateReservationHealthLot(reservationIndexHealthVo);

		return alert(request.getContextPath()+"/boffice/ex/reservationHealth/reservationHealthView.do?riIdx="+reservationIndexHealthVo.getRiIdx(), "추첨완료", model);
	}

	/* 신청자 추첨 */
	@RequestMapping("/boffice/ex/reservationHealth/reservationHealthLot2015.do")
	public String reservationHealthLot2015(HttpServletRequest request, @ModelAttribute("ReservationIndexHealthVo") ReservationIndexHealthVo reservationIndexHealthVo, ModelMap model) throws Exception {

		ReservationIndexHealthVo result = reservationHealthSvc.retrieveReservationHealth(reservationIndexHealthVo);
		
		if(result == null) {
			return alert("", "잘못된 접근입니다.", model);
		}
		
		List<String> ruIdxList = new ArrayList<String>();
		List<String> ruIdxListTemp = new ArrayList<String>();
		
		int riRecruitCnt = EgovStringUtil.zeroConvert(result.getRiRecruitCnt());
		int riTempCnt = result.getRiTempCnt();
		String riIdx = result.getRiIdx();
		
		ReservationUserInfoHealthVo reservationUserInfoHealthVo = new ReservationUserInfoHealthVo();
		reservationUserInfoHealthVo.setRiIdx(riIdx);
		reservationUserInfoHealthVo.setRuLotResult("R"); //접수
		int countR = reservationHealthSvc.retrieveReservationHealthCntForLot(reservationUserInfoHealthVo);
		reservationUserInfoHealthVo.setRuLotResult("T"); //예비
		int countT = reservationHealthSvc.retrieveReservationHealthCntForLot(reservationUserInfoHealthVo);
		reservationUserInfoHealthVo.setRuLotResult("C"); // 확정대기
		int countC = reservationHealthSvc.retrieveReservationHealthCntForLot(reservationUserInfoHealthVo);
		reservationUserInfoHealthVo.setRuLotResult("S"); //추첨확정
		int countS = reservationHealthSvc.retrieveReservationHealthCntForLot(reservationUserInfoHealthVo);
		
		int remainCnt = riRecruitCnt - (countS+countC);
		
		if(remainCnt > 0) {
			
			if(countT > 0) {
				List<String> tempList = reservationHealthSvc.retrieveListReservationHealthRandomTemp(reservationIndexHealthVo);
				
				for(String ruIdx : tempList) {
					if(remainCnt <= 0) {
						break;
					}
					ruIdxList.add(ruIdx);
					remainCnt--;
					countT--;
				}
				
				reservationIndexHealthVo.setStrList(ruIdxList);
				reservationHealthSvc.updateReservationHealthLot(reservationIndexHealthVo);
			}
			
			if(remainCnt > 0) {
				
				List<String> readyList = reservationHealthSvc.retrieveListReservationHealthRandom(reservationIndexHealthVo);
				
				for(String ruIdx : readyList) {
					if(remainCnt <= 0) {
						break;
					}
					ruIdxList.add(ruIdx);
					remainCnt--;
					countR--;
				}
				
				reservationIndexHealthVo.setStrList(ruIdxList);
				reservationHealthSvc.updateReservationHealthLot(reservationIndexHealthVo);
			}
			
			int remainTempCnt = riTempCnt - countT;
			
			if(remainTempCnt > 0 && countR > 0) {
				
				List<String> readyList = reservationHealthSvc.retrieveListReservationHealthRandom(reservationIndexHealthVo);
				
				for(String ruIdx : readyList) {
					if(remainTempCnt <= 0) {
						break;
					}
					ruIdxListTemp.add(ruIdx);
					remainTempCnt--;
				}
				
				reservationIndexHealthVo.setStrList(ruIdxListTemp);
				reservationHealthSvc.updateReservationHealthLotTemp(reservationIndexHealthVo);
				
			}
		}

		return alert(request.getContextPath()+"/boffice/ex/reservationHealth/reservationHealthView.do?riIdx="+reservationIndexHealthVo.getRiIdx(), "추첨완료", model);
	}

	

	/* 신청자 추첨확정 */
	@RequestMapping("/boffice/ex/reservationHealth/reservationHealthUserInfoConfrim.do")
	public String reservationHealthUserInfoConfirm(HttpServletRequest request, @ModelAttribute("ReservationUserInfoHealthVo") ReservationUserInfoHealthVo reservationUserInfoHealthVo, ModelMap model) throws Exception {

		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		reservationUserInfoHealthVo.setModId(userid);

		reservationHealthSvc.updateReservationHealthUserInfoConfirm(reservationUserInfoHealthVo);

		return alert("/boffice/ex/reservationHealth/reservationHealthView.do?riIdx="+reservationUserInfoHealthVo.getRiIdx(), "추첨확정되었습니다.", model);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/boffice/ex/reservationHealth/reservationHealthExcel_bak.do")
	public void webzineExcel(@ModelAttribute("ReservationUserInfoHealthVo") ReservationUserInfoHealthVo reservationUserInfoHealthVo, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ReservationIndexHealthVo reservationIndexHealthVo = new ReservationIndexHealthVo();

		reservationIndexHealthVo.setRiIdx(reservationUserInfoHealthVo.getRiIdx());

		List<ReservationUserInfoHealthVo> result = bizExcelService.selectList("ReservationHealthDao.selectListReservationHealthUserInfo", reservationIndexHealthVo);


		String createString = "reservationHealth_" + EgovStringUtil.getTimeStamp() +".xls";

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
			ReservationUserInfoHealthVo resultVO = (ReservationUserInfoHealthVo)result.get(i);
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
	@RequestMapping("/boffice/ex/reservationHealth/reservationHealthExcel.do")
	public String reservationHealthExcel( @ModelAttribute("ReservationUserInfoHealthVo") ReservationUserInfoHealthVo reservationUserInfoVo, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ReservationIndexHealthVo reservationIndexHealthVo = new ReservationIndexHealthVo();

		reservationIndexHealthVo.setRiIdx(reservationUserInfoVo.getRiIdx());

		//행사신청자 목록
		List<ReservationUserInfoHealthVo> resultList = bizExcelService.selectList("ReservationHealthDao.selectListReservationHealthUserInfo", reservationIndexHealthVo);
		
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

		return "injeinc/boffice/ex/reservationHealth/reservation_health_excel";
		
	}
}
