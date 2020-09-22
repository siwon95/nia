package egovframework.injeinc.boffice.ex.reservationEtc.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
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
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationAddItemVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationIndexVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationItemVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationUserAnswerVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationUserInfoVo;
import egovframework.injeinc.boffice.ex.reservationEtc.service.ReservationEtcSvc;
import egovframework.injeinc.boffice.excel.controller.ExcelCtr;
import egovframework.injeinc.boffice.excel.service.BizExcelSvc;
import egovframework.injeinc.boffice.login.vo.LoginVo;
import egovframework.injeinc.boffice.sy.code.service.CmmCodeSvc;
import egovframework.injeinc.common.code.service.CodeSvc;
import egovframework.injeinc.common.code.vo.CodeVo;
import egovframework.injeinc.common.util.DateUtil;
import egovframework.injeinc.common.util.EgovStringUtil;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class ReservationEtcCtr extends CmmLogCtr{
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationEtcCtr.class);
	
	 @Resource(name = "CodeSvc")
	 private CodeSvc codeSvc;
	 
	 @Resource(name = "CmmCodeSvc")
	 private CmmCodeSvc cmmCodeSvc;
	 
	 @Resource(name = "ReservationEtcSvc")
	 private ReservationEtcSvc reservationEtcSvc;

	 @Resource(name = "reservationIdGnrService")
	 private EgovIdGnrService reservationIdgenService;

	 @Resource(name = "reservationUserIdGnrService")
	 private EgovIdGnrService reservationUserIdGnrService;

	@Resource(name = "BizExcelService")
	private BizExcelSvc bizExcelService;
	
	/** 기타예약 목록 */
	@RequestMapping(value = "/boffice/ex/reservationEtc/reservationEtcList.do")
	public String reservationEtcList(HttpServletRequest request , @ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo , ModelMap model) throws Exception {

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(reservationIndexVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(reservationIndexVo.getPageUnit());
		paginationInfo.setPageSize(reservationIndexVo.getPageSize());

		reservationIndexVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		reservationIndexVo.setLastIndex(paginationInfo.getLastRecordIndex());
		reservationIndexVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		reservationIndexVo.setRiType("T");
		
		/* 게시물 권한 체크 - 최고관리자가 아니면 각부서에 해당하는 게시물만 보인다.
		*/
		HttpSession ses = request.getSession();
		LoginVo cmsUser = (LoginVo)ses.getAttribute("CMSUSER");
		if(!cmsUser.getPermCd().equals("05010000")){
			reservationIndexVo.setRiManageDept(cmsUser.getpDeptCd());
		}
		
		/* 권한 체크 - 최고관리자가 아니면 담당교육기관 강좌목록만 조회 */
		if(!cmsUser.getIsAdmin()){
			//lectureVo.setDeptCd(cmsUser.getpDeptCd());
			reservationIndexVo.setMgrSiteCdList(cmsUser.getMgrSiteCdList());
		}
		
		Map<String, Object> map = reservationEtcSvc.retrievePagReservationEtc(reservationIndexVo);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));

		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);

		return "injeinc/boffice/ex/reservationEtc/reservation_Etc_list";
	}
	
	/** 기타예약 등록폼 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/boffice/ex/reservationEtc/reservationEtcForm.do")
	public String reservationEtcForm(
			HttpServletRequest request,
			@ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo
			, ModelMap model
			) throws Exception {
		
		HttpSession ses = request.getSession();
		LoginVo cmsUser = (LoginVo)ses.getAttribute("CMSUSER");
		HashMap<String, String> param = new HashMap<String, String>();
		ReservationIndexVo result = reservationEtcSvc.retrieveReservationEtc(reservationIndexVo);

		if(result != null) {
			result.setActionkey("modify");
			result.setPageIndex(reservationIndexVo.getPageIndex());
			result.setSearchCondition(reservationIndexVo.getSearchCondition());
			result.setSearchKeyword(reservationIndexVo.getSearchKeyword());
			
			model.addAttribute("ReservationAddItemList", reservationEtcSvc.retrieveListReservationEtcAddItem(reservationIndexVo));
			model.addAttribute("ReservationItemList", reservationEtcSvc.retrieveListReservationEtcItem(reservationIndexVo));
			model.addAttribute("ReservationIndexVo", result);
		}else{
			reservationIndexVo.setActionkey("regist");
		}

		HashMap<String, Object> serviceMap = codeSvc.retrieveDeptCodeAll(param);
		List<CodeVo> rowDataList = (List<CodeVo>) serviceMap.get("rowDataList");

		model.addAttribute("deptList", rowDataList);
		
		
		model.addAttribute("resEtcCodeList", cmmCodeSvc.retrieveListCmmCodeForResEtcCode(cmsUser.getIsAdmin(), cmsUser.getMgrSiteCdList()));
		//관리부서목록
		model.addAttribute("dept", reservationEtcSvc.retrieveDeptCode());
		model.addAttribute("resultList", reservationEtcSvc.retrieveListSuervisionOrg(cmsUser.getIsAdmin(), cmsUser.getMgrSiteCdList()));
		return "injeinc/boffice/ex/reservationEtc/reservation_Etc_form";
	}
	
	/** 기타예약 등록 프로세스*/
	@RequestMapping("/boffice/ex/reservationEtc/reservationEtcReg.do")
	public String reservationEtcReg(
			final MultipartHttpServletRequest multiRequest
			, @ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo
			, ModelMap model
			) throws Exception {

		ReservationAddItemVo reservationAddItemVo;
		ReservationItemVo reservationItemVo;

		String riIdx = reservationIdgenService.getNextStringId();
		reservationIndexVo.setRiIdx(riIdx);

		String userid = (String) WebUtils.getSessionAttribute(multiRequest, "SesUserId");

		reservationIndexVo.setRegId(userid);
		reservationIndexVo.setRiType("T");

		String raTitle[] = multiRequest.getParameterValues("raTitle");
		String raContent[] = multiRequest.getParameterValues("raContent");
		String raLimitCnt[] = multiRequest.getParameterValues("raLimitCnt");

		String tableNum[] = multiRequest.getParameterValues("tableNum");
		String rtItemTitle[] = multiRequest.getParameterValues("rtItemTitle");
		String rtItemType[] = multiRequest.getParameterValues("rtItemType");
		String rtItemNotice[] = multiRequest.getParameterValues("rtItemNotice");
		String rtItemExampleText = "";
		if(raTitle != null){
			for(int i = 0; i < raTitle.length; i++){
				reservationAddItemVo = new ReservationAddItemVo();
				
				reservationAddItemVo.setRiIdx(riIdx);
				reservationAddItemVo.setRaIdx(i+1);
				reservationAddItemVo.setRaTitle(raTitle[i]);
				if(raContent != null && raContent.length > 0){
					reservationAddItemVo.setRaContent(raContent[i]);
				}
				if(raLimitCnt != null && raLimitCnt.length > 0){
					reservationAddItemVo.setRaLimitCnt(raLimitCnt[i]);
				}
				reservationAddItemVo.setRegId(userid);
				
				reservationEtcSvc.registReservationEtcAddItem(reservationAddItemVo);
			}
		}
		if(tableNum != null){
			
			for(int i=0; i < tableNum.length; i++){
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
				
				reservationEtcSvc.registReservationEtcItem(reservationItemVo);
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
    	uploadVO = reservationEtcSvc.uploadFile(files, reservationIndexVo);

    	reservationIndexVo.setRiImageFileId(uploadVO.getRiImageFileId());
    	
    	/* 예약 마스터 정보 저장 */
		reservationEtcSvc.registReservationEtc(reservationIndexVo);

		String SVC_MSGE = Message.getMessage("100.message");
		return alert("/boffice/ex/reservationEtc/reservationEtcList.do", SVC_MSGE, model);
	}

	@RequestMapping("/boffice/ex/reservationEtc/reservationEtcMod.do")
	public String reservationEtcMod(
			final MultipartHttpServletRequest multiRequest
			, @ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo
			, ModelMap model
			) throws Exception {

		ReservationAddItemVo reservationAddItemVo;
		ReservationItemVo reservationItemVo;

		String userid = (String) WebUtils.getSessionAttribute(multiRequest, "SesUserId");
		reservationIndexVo.setModId(userid);

		String raTitle[] = multiRequest.getParameterValues("raTitle");
		String raContent[] = multiRequest.getParameterValues("raContent");
		String raLimitCnt[] = multiRequest.getParameterValues("raLimitCnt");

		String tableNum[] = multiRequest.getParameterValues("tableNum");
		String rtItemTitle[] = multiRequest.getParameterValues("rtItemTitle");
		String rtItemType[] = multiRequest.getParameterValues("rtItemType");
		String rtItemNotice[] = multiRequest.getParameterValues("rtItemNotice");
		String rtItemExampleText = "";

		reservationEtcSvc.removeReservationEtcAddItem(reservationIndexVo);
		
		if(raTitle != null){
			for(int i = 0; i < raTitle.length; i++){
				reservationAddItemVo = new ReservationAddItemVo();
				
				reservationAddItemVo.setRiIdx(reservationIndexVo.getRiIdx());
				reservationAddItemVo.setRaIdx(i+1);
				reservationAddItemVo.setRaTitle(raTitle[i]);
				if(raContent != null && raContent.length > 0){
					reservationAddItemVo.setRaContent(raContent[i]);
				}
				if(raLimitCnt != null && raLimitCnt.length > 0){
					reservationAddItemVo.setRaLimitCnt(raLimitCnt[i]);
				}
				reservationAddItemVo.setRegId(userid);
				
				reservationEtcSvc.registReservationEtcAddItem(reservationAddItemVo);
			}
		}

		reservationEtcSvc.removeReservationEtcItem(reservationIndexVo);
		
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
				
				reservationEtcSvc.registReservationEtcItem(reservationItemVo);
			}
		}

		reservationIndexVo.setRiOffLotUse(reservationIndexVo.getRiOffLotUse().replaceAll(",", "||"));

		if(reservationIndexVo.getRiTermType().equals("O")){
			reservationIndexVo.setRiSdate(multiRequest.getParameter("riSdate1"));
		}

		// 파일업로드
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		ReservationIndexVo uploadVO = new ReservationIndexVo();
    	uploadVO = reservationEtcSvc.uploadFile(files, reservationIndexVo);

    	reservationIndexVo.setRiImageFileId(uploadVO.getRiImageFileId());

		reservationEtcSvc.modifyReservationEtc(reservationIndexVo);

		StringBuffer addParam = new StringBuffer();
		addParam.append("?pageIndex=").append(reservationIndexVo.getPageIndex());
		addParam.append("&searchCondition=").append(reservationIndexVo.getSearchCondition());
		addParam.append("&searchKeyword=").append(reservationIndexVo.getSearchKeyword());

		String SVC_MSGE = Message.getMessage("401.message");
		return alert("/boffice/ex/reservationEtc/reservationEtcList.do"+addParam.toString(), SVC_MSGE, model);
	}

	@RequestMapping("/boffice/ex/reservationEtc/reservationEtcRmv.do")
	public String reservationEtcRmv(
			HttpServletRequest request
			, @ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo
			, ModelMap model
			) throws Exception {

		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		reservationIndexVo.setModId(userid);

		reservationEtcSvc.removeReservationEtc(reservationIndexVo);

		StringBuffer addParam = new StringBuffer();
		addParam.append("?pageIndex=").append(reservationIndexVo.getPageIndex());
		addParam.append("&searchCondition=").append(reservationIndexVo.getSearchCondition());
		addParam.append("&searchKeyword=").append(reservationIndexVo.getSearchKeyword());

		String SVC_MSGE = Message.getMessage("501.message");
		return alert("/boffice/ex/reservationEtc/reservationEtcList.do"+addParam.toString(), SVC_MSGE, model);
	}

	@RequestMapping("/boffice/ex/reservationEtc/reservationEtcView.do")
	public String reservationEtcView(
			HttpServletRequest request
			, @ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo
			, ModelMap model
			) throws Exception {

		ReservationIndexVo result = reservationEtcSvc.retrieveReservationEtc(reservationIndexVo);

		List<ReservationUserInfoVo> userList = reservationEtcSvc.retrieveListReservationEtcUserInfo(reservationIndexVo);

		result.setPageIndex(reservationIndexVo.getPageIndex());
		result.setSearchCondition(reservationIndexVo.getSearchCondition());
		result.setSearchKeyword(reservationIndexVo.getSearchKeyword());

		model.addAttribute("ReservationIndexVo", result);
		model.addAttribute("ReservationItemList", reservationEtcSvc.retrieveListReservationEtcItem(reservationIndexVo));

		model.addAttribute("ReservationUserList", userList);
		
		ReservationUserInfoVo reservationUserInfoVo = new ReservationUserInfoVo();
		reservationUserInfoVo.setRiIdx(result.getRiIdx());
		reservationUserInfoVo.setRuLotResult("T"); //예비
		int countT = reservationEtcSvc.retrieveReservationEtcCntForLot(reservationUserInfoVo);
		reservationUserInfoVo.setRuLotResult("C"); // 확정대기
		int countC = reservationEtcSvc.retrieveReservationEtcCntForLot(reservationUserInfoVo);
		reservationUserInfoVo.setRuLotResult("S"); //추첨확정
		int countS = reservationEtcSvc.retrieveReservationEtcCntForLot(reservationUserInfoVo);
		
		model.addAttribute("countT", countT);
		model.addAttribute("countC", countC);
		model.addAttribute("countS", countS);

		return "injeinc/boffice/ex/reservationEtc/reservation_Etc_view";
	}

	@RequestMapping("/boffice/ex/reservationEtc/reservationEtcUserForm.do")
	public String reservationEtcUserForm(
			HttpServletRequest request
			, @ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo
			, ModelMap model
			) throws Exception {

		ReservationIndexVo reservationIndexVo = new ReservationIndexVo();

		reservationIndexVo.setRiIdx(reservationUserInfoVo.getRiIdx());

		reservationIndexVo = reservationEtcSvc.retrieveReservationEtc(reservationIndexVo);
		ReservationUserInfoVo result = reservationEtcSvc.retrieveReservationEtcUserInfo(reservationUserInfoVo);

		if(result != null) {
			reservationIndexVo.setActionkey("modify");

			model.addAttribute("ReservationUserInfoVo", result);
		}else{
			if(reservationIndexVo != null){
				if(reservationIndexVo.getRiRecruitYn().equals("N") && reservationIndexVo.getRiLotUse().equals("OO")){
					if(Integer.parseInt(reservationIndexVo.getRiRecruitCnt())  <= reservationIndexVo.getRiAccCnt() ){
						return alert(request.getContextPath()+"/boffice/ex/reservationEtc/reservationEtcView.do?riIdx="+reservationIndexVo.getRiIdx(), "모집인원이 초과되었습니다.", model);
					}
				}
			}

			reservationIndexVo.setActionkey("regist");
		}

		reservationIndexVo.setRuIdx(reservationUserInfoVo.getRuIdx());

		model.addAttribute("ReservationItemList", reservationEtcSvc.retrieveListReservationEtcItem(reservationIndexVo));
		model.addAttribute("ReservationIndexVo", reservationIndexVo);

		return "injeinc/boffice/ex/reservationEtc/reservation_Etc_user_form";
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/boffice/ex/reservationEtc/reservationUserInfoReg.do")
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

		List<ReservationItemVo> itemList = reservationEtcSvc.retrieveListReservationEtcItem(reservationIndexVo);

		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		reservationUserInfoVo.setRegId(userid);

		ReservationIndexVo result = reservationEtcSvc.retrieveReservationEtc(reservationIndexVo);

		ArrayList riIdxList = new ArrayList();
		String redundancyList[] = reservationIndexVo.getRiRedundancy().split(";");

		for(int i=0; i < redundancyList.length; i++){
			riIdxList.add(redundancyList[i]);
		}

		reservationUserInfoVo.setRiIdxList(riIdxList);
		if(!reservationUserInfoVo.getRuDupkey().equals("")){
			if(reservationEtcSvc.retrieveReservationEtcUserInfoDupCnt(reservationUserInfoVo) > 0){
				return alert("/boffice/ex/reservationEtc/reservationEtcUserForm.do?riIdx="+reservationIndexVo.getRiIdx(), "이미 예약을 하셨습니다.", model);
			}else if(reservationEtcSvc.retrieveReservationEtcRedundancyDupCnt(reservationUserInfoVo) > 0){
				return alert("/boffice/ex/reservationEtc/reservationEtcUserForm.do?riIdx="+reservationIndexVo.getRiIdx(), "일부 행사와 중복 예약을 하실 수 없습니다.", model);
			}
		}

		if(result.getRiLotUse().equals("OO")){
			reservationUserInfoVo.setRuLotResult("S");
		}else{
			reservationUserInfoVo.setRuLotResult("R");
		}

		reservationEtcSvc.registReservationEtcUserInfo(reservationUserInfoVo);

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
			reservationEtcSvc.registReservationEtcUserAnswer(reservationUserAnswerVo);
		}

		String SVC_MSGE = Message.getMessage("100.message");
		return alert("/boffice/ex/reservationEtc/reservationEtcView.do?riIdx="+riIdx, SVC_MSGE, model);
	}

	@RequestMapping("/boffice/ex/reservationEtc/reservationUserInfoMod.do")
	public String reservationUserInfoMod(HttpServletRequest request, @ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo, @ModelAttribute("ReservationUserAnswerVo") ReservationUserAnswerVo reservationUserAnswerVo, ModelMap model) throws Exception {

		ReservationIndexVo reservationIndexVo = new ReservationIndexVo();

		String riIdx = reservationUserInfoVo.getRiIdx();
		reservationIndexVo.setRiIdx(riIdx);

		List<ReservationItemVo> itemList = reservationEtcSvc.retrieveListReservationEtcItem(reservationIndexVo);

		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		reservationUserInfoVo.setModId(userid);

		ReservationIndexVo result = reservationEtcSvc.retrieveReservationEtc(reservationIndexVo);

		if(result.getRiLotUse().equals("OO")){
			reservationUserInfoVo.setRuLotResult("S");
		}else{
			reservationUserInfoVo.setRuLotResult("R");
		}

		reservationEtcSvc.modifyReservationEtcUserInfo(reservationUserInfoVo);

		reservationEtcSvc.removeReservationEtcUserAnswer(reservationUserInfoVo);

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
			reservationEtcSvc.registReservationEtcUserAnswer(reservationUserAnswerVo);
		}

		String SVC_MSGE = Message.getMessage("100.message");
		return alert("/boffice/ex/reservationEtc/reservationEtcView.do?riIdx="+riIdx, SVC_MSGE, model);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/boffice/ex/popup/reservationEtcListPopup.do")
	public String reservationEtcPagPopup(HttpServletRequest request, @ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo, ModelMap model) throws Exception {

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(reservationIndexVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(reservationIndexVo.getPageUnit());
		paginationInfo.setPageSize(reservationIndexVo.getPageSize());

		reservationIndexVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		reservationIndexVo.setLastIndex(paginationInfo.getLastRecordIndex());
		reservationIndexVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		reservationIndexVo.setRiType("T");

		Map<String, Object> map = reservationEtcSvc.retrievePagReservationEtc(reservationIndexVo);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));

		paginationInfo.setTotalRecordCount(totCnt);

		HashMap<String, String> param = new HashMap<String, String>();
		HashMap<String, Object> serviceMap = codeSvc.retrieveDeptCodeAll(param);

		List<CodeVo> rowDataList = (List<CodeVo>) serviceMap.get("rowDataList");

		model.addAttribute("deptList", rowDataList);

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);

		return "injeinc/boffice/ex/reservationEtc/reservation_Etc_list_popup";
	}

	/* 신청자 추첨 */
	@RequestMapping("/boffice/ex/reservationEtc/reservationEtcLot.do")
	public String reservationEtcLot(HttpServletRequest request
										, @ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo
										, ModelMap model
										, String[] ruIdx
										, int procRecruitCnt) throws Exception {
		
		ReservationUserInfoVo reservationUserInfoVo = new ReservationUserInfoVo();
		reservationUserInfoVo.setRiIdx(reservationIndexVo.getRiIdx());
		reservationUserInfoVo.setRuLotResult("R"); //접수
		int countR = reservationEtcSvc.retrieveReservationEtcCntForLot(reservationUserInfoVo);
		
		if(countR > 0){
			return alert(request.getContextPath()+"/boffice/ex/reservationEtc/reservationEtcView.do?riIdx="+reservationIndexVo.getRiIdx(), "이미 추첨을 하셨습니다.", model);
		}
		
		ReservationIndexVo result = reservationEtcSvc.retrieveReservationEtc(reservationIndexVo);
		/* 모집구분이 수량 or 인원 */
		if("O".equals(result.getRiRecruitType())){ // 수량인 경우
			
			List<ReservationUserInfoVo> userList = reservationEtcSvc.retrieveListReservationEtcUserInfo(result);
			
			Random random = new Random();
			
			List<String> ruIdxList = new ArrayList<String>();
			int StandardObjectCnt = procRecruitCnt; // 기준수량
			int objectCnt = 0; // 현재수량			

			while(true){
				int index = random.nextInt(userList.size() -1);
				ReservationUserInfoVo vo = userList.get(index);
				objectCnt += Integer.parseInt(vo.getRuObjectCnt());
				userList.remove(index); // userList를 random으로 뽑으면 userList에서 제거(중복선택 방지)

				System.out.println(vo.getRuObjectCnt());
				if(objectCnt > StandardObjectCnt){ // 기준수량을 넘으면 while문에서 나옴.
					break;
				}
				ruIdxList.add(vo.getRuIdx());
			}
			
			reservationIndexVo.setStrList(ruIdxList);
			reservationEtcSvc.updateReservationEtcLot(reservationIndexVo);
			
		}else{
			
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
			reservationEtcSvc.updateReservationEtcLot(reservationIndexVo);
			
		}
		return alert(request.getContextPath()+"/boffice/ex/reservationEtc/reservationEtcView.do?riIdx="+reservationIndexVo.getRiIdx(), "추첨완료", model);
	}

	/* 신청자 추첨 */
	@RequestMapping("/boffice/ex/reservationEtc/reservationEtcLot2015.do")
	public String reservationEtcLot2015(HttpServletRequest request, @ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo, ModelMap model) throws Exception {

		ReservationIndexVo result = reservationEtcSvc.retrieveReservationEtc(reservationIndexVo);
		
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
		int countR = reservationEtcSvc.retrieveReservationEtcCntForLot(reservationUserInfoVo);
		reservationUserInfoVo.setRuLotResult("T"); //예비
		int countT = reservationEtcSvc.retrieveReservationEtcCntForLot(reservationUserInfoVo);
		reservationUserInfoVo.setRuLotResult("C"); // 확정대기
		int countC = reservationEtcSvc.retrieveReservationEtcCntForLot(reservationUserInfoVo);
		reservationUserInfoVo.setRuLotResult("S"); //추첨확정
		int countS = reservationEtcSvc.retrieveReservationEtcCntForLot(reservationUserInfoVo);
		
		int remainCnt = riRecruitCnt - (countS+countC);
		
		if(remainCnt > 0) {
			
			if(countT > 0) {
				List<String> tempList = reservationEtcSvc.retrieveListReservationEtcRandomTemp(reservationIndexVo);
				
				for(String ruIdx : tempList) {
					if(remainCnt <= 0) {
						break;
					}
					ruIdxList.add(ruIdx);
					remainCnt--;
					countT--;
				}
				
				reservationIndexVo.setStrList(ruIdxList);
				reservationEtcSvc.updateReservationEtcLot(reservationIndexVo);
			}
			
			if(remainCnt > 0) {
				
				List<String> readyList = reservationEtcSvc.retrieveListReservationEtcRandom(reservationIndexVo);
				
				for(String ruIdx : readyList) {
					if(remainCnt <= 0) {
						break;
					}
					ruIdxList.add(ruIdx);
					remainCnt--;
					countR--;
				}
				
				reservationIndexVo.setStrList(ruIdxList);
				reservationEtcSvc.updateReservationEtcLot(reservationIndexVo);
			}
			
			int remainTempCnt = riTempCnt - countT;
			
			if(remainTempCnt > 0 && countR > 0) {
				
				List<String> readyList = reservationEtcSvc.retrieveListReservationEtcRandom(reservationIndexVo);
				
				for(String ruIdx : readyList) {
					if(remainTempCnt <= 0) {
						break;
					}
					ruIdxListTemp.add(ruIdx);
					remainTempCnt--;
				}
				
				reservationIndexVo.setStrList(ruIdxListTemp);
				reservationEtcSvc.updateReservationEtcLotTemp(reservationIndexVo);
				
			}
		}

		return alert(request.getContextPath()+"/boffice/ex/reservationEtc/reservationEtcView.do?riIdx="+reservationIndexVo.getRiIdx(), "추첨완료", model);
	}

	/* 신청자 상세보기 */
	@RequestMapping(value="/boffice/ex/reservationEtc/doReservationUserInfoDetailAx.do", produces="application/json;charset=UTF-8")
	@ResponseBody
	public String doReservationUserInfoDetailAx(HttpServletRequest request
													, @ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo
													, @ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo
													, ModelMap model) throws Exception {

		HashMap<String, Object> map = new HashMap<String, Object>(); 
		map.put("reservationUser", reservationEtcSvc.retrieveReservationEtcUserInfo(reservationUserInfoVo));
		map.put("reservationItemList", reservationEtcSvc.retrieveListReservationEtcItem(reservationIndexVo));
		return returnJsonType(true, "msg", map);
	}

	/* 신청자 삭제 */
	@RequestMapping("/boffice/ex/reservationEtc/reservationEtcUserInfoRmv.do")
	public String reservationEtcUserInfoRmv(HttpServletRequest request, @ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo, ModelMap model) throws Exception {
		
		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		reservationUserInfoVo.setModId(userid);
		
		reservationEtcSvc.removeReservationEtcUserInfo(reservationUserInfoVo);
		
		return alert("/boffice/ex/reservationEtc/reservationEtcView.do?riIdx="+reservationUserInfoVo.getRiIdx(), "접수자 삭제완료", model);
	}

	/* 신청자 추첨확정 */
	@RequestMapping("/boffice/ex/reservationEtc/reservationEtcUserInfoConfrim.do")
	public String reservationEtcUserInfoConfirm(HttpServletRequest request, @ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo, ModelMap model) throws Exception {

		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		reservationUserInfoVo.setModId(userid);

		reservationEtcSvc.updateReservationEtcUserInfoConfirm(reservationUserInfoVo);

		return alert("/boffice/ex/reservationEtc/reservationEtcView.do?riIdx="+reservationUserInfoVo.getRiIdx(), "처리되었습니다.", model);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/boffice/ex/reservationEtc/reservationEtcExcel_bak.do")
	public void webzineExcel(@ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ReservationIndexVo reservationIndexVo = new ReservationIndexVo();

		reservationIndexVo.setRiIdx(reservationUserInfoVo.getRiIdx());

		List<ReservationUserInfoVo> result = bizExcelService.selectList("ReservationEtcDao.selectListReservationEtcUserInfo", reservationIndexVo);


		String createString = "reservationEtc_" + EgovStringUtil.getTimeStamp() +".xls";

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
	@RequestMapping("/boffice/ex/reservationEtc/reservationEtcExcel.do")
	public String reservationEtcExcel( @ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ReservationIndexVo reservationIndexVo = new ReservationIndexVo();

		reservationIndexVo.setRiIdx(reservationUserInfoVo.getRiIdx());

		//행사신청자 목록
		List<ReservationUserInfoVo> resultList = bizExcelService.selectList("ReservationEtcDao.selectListReservationEtcUserInfo", reservationIndexVo);
		
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

		return "injeinc/boffice/ex/reservationEtc/reservation_Etc_excel";
		
	}
}
