package egovframework.injeinc.foffice.ex.reservation.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.WebUtils;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.Message;
import egovframework.injeinc.boffice.ex.reservation.service.ReservationEventSvc;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationAddItemVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationIndexVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationItemVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationUserAnswerVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationUserInfoVo;
import egovframework.injeinc.common.code.service.CodeSvc;
import egovframework.injeinc.common.code.vo.CodeVo;
import egovframework.injeinc.foffice.login.vo.LoginFVo;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import sun.misc.BASE64Encoder;

@Controller
public class ReservationSiteEventCtr extends CmmLogCtr{

	 @Resource(name = "CodeSvc")
	 private CodeSvc codeSvc;

	 @Resource(name = "ReservationEventSvc")
	 private ReservationEventSvc reservationEventSvc;

	 @Resource(name = "reservationIdGnrService")
	 private EgovIdGnrService reservationIdgenService;

	 @Resource(name = "reservationUserIdGnrService")
	 private EgovIdGnrService reservationUserIdGnrService;

	@SuppressWarnings("rawtypes")	
	@RequestMapping(value= "/{strSitePath}/{strDomain}/ex/reservation/List.do")
	public String reservationSiteEventPag(
			HttpServletRequest request
			, @ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo
			, ModelMap model
			) throws Exception {
		if(model == null){
			return "injeinc/common/code500";
		} 
		int isMobile = 0;
		String agent = request.getHeader("USER-AGENT");
		String[] mobileos = {"iPhone","iPod","Android","BlackBerry","Windows CE","Nokia","Webos","Opera Mini","SonyEricsson","Opera Mobi","IEMobile"};
		int j = -1;

		for(int i=0 ; i<mobileos.length ; i++) {
			j=agent.indexOf(mobileos[i]);
			if(j > -1 ) {
				// 모바일로 접근했을 때
				isMobile = 1;
				break;
			}
		}

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(reservationIndexVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(8);
		
		//웹 사용 여부 
		if(isMobile > 0){
			paginationInfo.setPageSize(5);
		}else{
			paginationInfo.setPageSize(reservationIndexVo.getPageSize());
			
		}

		reservationIndexVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		reservationIndexVo.setLastIndex(paginationInfo.getLastRecordIndex());
		reservationIndexVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		

		reservationIndexVo.setRiType("E");
		reservationIndexVo.setRiConfirmYn("Y");

		Map<String, Object> map = reservationEventSvc.retrievePagReservationEvent(reservationIndexVo);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));

		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);

		return "injeinc/foffice/ex/reservation/reservationEvent_list";
	}
	
	@SuppressWarnings("rawtypes")	
	@RequestMapping(value= "/{strSitePath}/{strDomain}/ex/reservation/LibList.do")
	public String reservationLibEventPag(
			HttpServletRequest request
			, @ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo
			, ModelMap model
			) throws Exception {
		if(model == null){
			return "injeinc/common/code500";
		} 
		int isMobile = 0;
		String agent = request.getHeader("USER-AGENT");
		String[] mobileos = {"iPhone","iPod","Android","BlackBerry","Windows CE","Nokia","Webos","Opera Mini","SonyEricsson","Opera Mobi","IEMobile"};
		int j = -1;

		for(int i=0 ; i<mobileos.length ; i++) {
			j=agent.indexOf(mobileos[i]);
			if(j > -1 ) {
				// 모바일로 접근했을 때
				isMobile = 1;
				break;
			}
		}

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(reservationIndexVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(8);
		
		//웹 사용 여부 
		if(isMobile > 0){
			paginationInfo.setPageSize(5);
		}else{
			paginationInfo.setPageSize(reservationIndexVo.getPageSize());
			
		}

		reservationIndexVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		reservationIndexVo.setLastIndex(paginationInfo.getLastRecordIndex());
		reservationIndexVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		

		reservationIndexVo.setRiType("E");
		reservationIndexVo.setRiConfirmYn("Y");

		Map<String, Object> map = reservationEventSvc.retrievePagReservationEventLib(reservationIndexVo);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));

		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);

		return "injeinc/foffice/ex/lib/reservationEvent_list";
	}

	@RequestMapping("/site/{strDomain}/foffice/ex/reservation/re00402.do")
	public String reservationSiteEventForm(
			@ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo
			, ModelMap model
			) throws Exception {
		if(model == null){
			return "injeinc/common/code500";
		}
		HashMap<String, String> param = new HashMap<String, String>();

		HashMap<String, Object> serviceMap = codeSvc.retrieveDeptCodeAll(param);
		List<CodeVo> rowDataList = (List<CodeVo>) serviceMap.get("rowDataList");

		model.addAttribute("deptList", rowDataList);
		
		//관리부서목록
		List deptCode = reservationEventSvc.retrieveDeptCode();
		model.addAttribute("dept", deptCode);

		return "injeinc/foffice/ex/RE004/re00402";
	}

	@RequestMapping("/site/{strDomain}/foffice/ex/reservation/re00402Reg.do")
	public String reservationSiteEventReg(
			final MultipartHttpServletRequest multiRequest
			, @ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo
			, ModelMap model
			) throws Exception {
		if(multiRequest == null){
			return "injeinc/common/code500";
		}
		ReservationAddItemVo reservationAddItemVo;

		String riIdx = reservationIdgenService.getNextStringId();
		reservationIndexVo.setRiIdx(riIdx);

		HttpSession ses = multiRequest.getSession();
		String userid = (String)ses.getAttribute("ss_id");

		reservationIndexVo.setRegId(userid);
		reservationIndexVo.setRiType("E");

		String raTitle[] = multiRequest.getParameterValues("raTitle");
		String raContent[] = multiRequest.getParameterValues("raContent");


		for(int i = 0; i < raTitle.length; i++){
			reservationAddItemVo = new ReservationAddItemVo();

			reservationAddItemVo.setRiIdx(riIdx);
			reservationAddItemVo.setRaIdx(i+1);
			reservationAddItemVo.setRaTitle(raTitle[i]);
			reservationAddItemVo.setRaContent(raContent[i]);
			reservationAddItemVo.setRegId(userid);

			reservationEventSvc.registReservationEventAddItem(reservationAddItemVo);
		}

		reservationIndexVo.setRiOffLotUse(reservationIndexVo.getRiOffLotUse().replaceAll(",", "||"));
		reservationIndexVo.setRiConfirmYn("N");
		reservationIndexVo.setRiUserloginType("U");

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
		return alert(multiRequest.getContextPath()+"/site/{strDomain}/ex/reservation/re00401.do", SVC_MSGE, model);
	}

	@RequestMapping("/{strSitePath}/{strDomain}/ex/reservation/View.do")
	public String reservationSiteEventView(
			HttpServletRequest request
			, @ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo
			, ModelMap model
			) throws Exception {
		if(model == null){
			return "injeinc/common/code500";
		}
		ReservationIndexVo result = reservationEventSvc.retrieveReservationEvent(reservationIndexVo);

		reservationEventSvc.modifyReservationEventReadCnt(reservationIndexVo);

		//List<ReservationUserInfoVo> userList = reservationEventSvc.retrieveListReservationEventUserInfo(reservationIndexVo);

		result.setPageIndex(reservationIndexVo.getPageIndex());
		result.setSearchCondition(reservationIndexVo.getSearchCondition());
		result.setSearchKeyword(reservationIndexVo.getSearchKeyword());
		
		HttpSession ses = request.getSession();
		String cuId = (String)ses.getAttribute("ss_id");
		String cuName = (String)ses.getAttribute("ss_name");
		String cuTel = (String)ses.getAttribute("ss_tel");
		String cuBirth = (String)ses.getAttribute("ss_birth");
		if(cuTel != null && !cuTel.equals("")){
			cuTel=cuTel.replaceAll("-", "");
		}
		LoginFVo userVo = new LoginFVo();
		userVo.setCuName(cuName);
		userVo.setHpNum(cuTel);
		userVo.setBirth(cuBirth);
		
		BASE64Encoder encoder = new BASE64Encoder();
		
		if(cuBirth != null && !cuBirth.equals("")){			
			model.addAttribute("Base64", encoder.encode((cuBirth+cuTel+"|"+"50110000"+"|").getBytes()));
		}
		model.addAttribute("cuId", cuId);
		model.addAttribute("LoginFVo", userVo);

		model.addAttribute("ReservationIndexVo", result);
		model.addAttribute("ReservationAddItemList", reservationEventSvc.retrieveListReservationEventAddItem(reservationIndexVo));
		//model.addAttribute("ReservationUserList", userList);

		return "injeinc/foffice/ex/reservation/reservationEvent_view";
	}
	
	@RequestMapping("/{strSitePath}/{strDomain}/foffice/ex/reservationMy/View.do")
	public String reservationSiteEventMyView(
			HttpServletRequest request
			, @ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo
			, ModelMap model
			) throws Exception {
		if(model == null){
			return "injeinc/common/code500";
		}
		
		HttpSession ses = request.getSession();
		String ss_dupkey = (String)ses.getAttribute("ss_dupkey");

		reservationIndexVo.setRuDupkey(ss_dupkey);
		
		//ReservationIndexVo result = reservationEventSvc.retrieveReservationEvent(reservationIndexVo);
		ReservationIndexVo result = reservationEventSvc.retrieveReservationEvent(reservationIndexVo);
		//reservationEventSvc.modifyReservationEventReadCnt(reservationIndexVo);

		//List<ReservationUserInfoVo> userList = reservationEventSvc.retrieveListReservationEventUserInfo(reservationIndexVo);

		if(result == null) {
			String SVC_MSGE = "삭제된 게시물입니다.";
			return alert(request.getContextPath()+"/site/{strDomain}/foffice/ex/reservationMy/list.do", SVC_MSGE, model);
		}
		
		result.setPageIndex(reservationIndexVo.getPageIndex());
		result.setSearchCondition(reservationIndexVo.getSearchCondition());
		result.setSearchKeyword(reservationIndexVo.getSearchKeyword());

		model.addAttribute("ReservationIndexVo", result);
		model.addAttribute("ReservationAddItemList", reservationEventSvc.retrieveListReservationEventAddItem(reservationIndexVo));
		//model.addAttribute("ReservationUserList", userList);

		return "injeinc/foffice/ex/reservation/reservationEvent_myview";
	}

	@RequestMapping("/{strSitePath}/{strDomain}/foffice/ex/reservation/reservationForm.do")
	public String reservationSiteEventUserForm(
			HttpServletRequest request
			, @ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo
			, @ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo
			, ModelMap model
			) throws Exception {
		if(model == null){
			return "injeinc/common/code500";
		}
		HttpSession ses = request.getSession();

		reservationUserInfoVo.setRuName((String)ses.getAttribute("ss_name"));
		reservationUserInfoVo.setRuDupkey((String)ses.getAttribute("ss_dupkey"));

		if(ses.getAttribute("ss_level").equals("7")){
			reservationUserInfoVo.setRuEmail((String)ses.getAttribute("ss_email"));
			reservationUserInfoVo.setRuZipcode((String)ses.getAttribute("ss_zip"));
			reservationUserInfoVo.setRuAddress((String)ses.getAttribute("ss_addr1"));
			reservationUserInfoVo.setRuAddressDtl((String)ses.getAttribute("ss_addr2"));
			reservationUserInfoVo.setRuTel((String)ses.getAttribute("ss_tel"));
			reservationUserInfoVo.setRuHp((String)ses.getAttribute("ss_hp"));
		}

		reservationIndexVo = reservationEventSvc.retrieveReservationEvent(reservationIndexVo);
		
		ArrayList riIdxList = new ArrayList();
		System.out.println("-------------  : "+reservationIndexVo.getRiRedundancy());
		if(reservationIndexVo.getRiRedundancy() != null){
			String redundancyList[] = reservationIndexVo.getRiRedundancy().split(";");

			for(int i=0; i < redundancyList.length; i++){
				riIdxList.add(redundancyList[i]);
			}

			reservationUserInfoVo.setRiIdxList(riIdxList);
			
			if(reservationEventSvc.retrieveReservationEventRedundancyDupCnt(reservationUserInfoVo) > 0){
				return alert(request.getContextPath()+"/reservation/reservation/ex/reservation/View.do?riIdx="+reservationIndexVo.getRiIdx(), "일부 행사와 중복 예약을 하실 수 없습니다.", model);
			}
		}
				
		if(reservationEventSvc.retrieveReservationEventUserInfoDupCnt(reservationUserInfoVo) > 0){
			return alert(request.getContextPath()+"/reservation/reservation/ex/reservation/View.do?riIdx="+reservationIndexVo.getRiIdx(), "기존에 신청한 내역이 있습니다.", model);
		}
		
		if(reservationIndexVo != null){
			if(reservationIndexVo.getRiRecruitYn().equals("Y") && reservationIndexVo.getRiLotUse().equals("OO")){
				if(Integer.parseInt(reservationIndexVo.getRiRecruitCnt())  <= reservationIndexVo.getRiAccCnt() ){
					return alert(request.getContextPath()+"/reservation/reservation/ex/reservation/View.do?riIdx="+reservationIndexVo.getRiIdx(), "모집인원이 초과되었습니다.", model);
				}
			}
		}

		model.addAttribute("ReservationItemList", reservationEventSvc.retrieveListReservationEventItem(reservationIndexVo));
		model.addAttribute("ReservationIndexVo", reservationIndexVo);

		return "injeinc/foffice/ex/reservation/reservation_userform";
	}

	@RequestMapping("/{strSitePath}/{strDomain}/foffice/ex/reservation/reservationReg.do")
	public String reservationSiteEventUserInfoReg(
			HttpServletRequest request
			, @ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo
			, @ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo
			, @ModelAttribute("ReservationUserAnswerVo") ReservationUserAnswerVo reservationUserAnswerVo
			, ModelMap model
			) throws Exception {
		HttpSession ses = request.getSession();
		ArrayList riIdxList = new ArrayList();
		String ruIdx = "";

		String redundancyList[] = reservationIndexVo.getRiRedundancy().split(";");

		for(int i=0; i < redundancyList.length; i++){
			riIdxList.add(redundancyList[i]);
		}

		reservationUserInfoVo.setRiIdxList(riIdxList);
		String strReturnUrl = "";
		if(reservationEventSvc.retrieveReservationEventUserInfoDupCnt(reservationUserInfoVo) > 0){
			return alert(request.getContextPath()+"/reservation/reservation/ex/reservation/View.do?riIdx="+reservationIndexVo.getRiIdx(), "기존에 신청한 내역이 있습니다.", model);
		}else if(reservationEventSvc.retrieveReservationEventRedundancyDupCnt(reservationUserInfoVo) > 0){
			return alert(request.getContextPath()+"/reservation/reservation/ex/reservation/View.do?riIdx="+reservationIndexVo.getRiIdx(), "일부 행사와 중복 예약을 하실 수 없습니다.", model);
		}else{
			ruIdx = reservationUserIdGnrService.getNextStringId();
			reservationUserInfoVo.setRuIdx(ruIdx);

			List<ReservationItemVo> itemList = reservationEventSvc.retrieveListReservationEventItem(reservationIndexVo);

			reservationUserInfoVo.setRegId((String)ses.getAttribute("ss_id"));
			reservationIndexVo.setRuDupkey("");
			ReservationIndexVo result = reservationEventSvc.retrieveReservationEvent(reservationIndexVo);

			if(result.getRiLotUse().equals("OO")){
				reservationUserInfoVo.setRuLotResult("S");
			}else{
				reservationUserInfoVo.setRuLotResult("R");
			}
			
			if(result.getRiPayYn().equals("Y")){
				if(result.getRiPay() != null && !result.getRiPay().equals("") && Integer.parseInt(result.getRiPay()) > 0){
					if(result.getRiOnlinePayYn() != null && !result.getRiOnlinePayYn().equals("") && result.getRiOnlinePayYn().equals("Y")){
						reservationUserInfoVo.setPayStatus("1");
						reservationUserInfoVo.setPayPrice(result.getRiPay());
					}
				}
			}							
			reservationEventSvc.registReservationEventUserInfo(reservationUserInfoVo);

			String[] raEtcAnswer = request.getParameterValues("raEtcAnswer");
			
			for(int i = 0; i < itemList.size(); i++) {
				reservationUserAnswerVo = new ReservationUserAnswerVo();

				reservationUserAnswerVo.setRuIdx(ruIdx);
				reservationUserAnswerVo.setRiIdx(reservationUserInfoVo.getRiIdx());
				reservationUserAnswerVo.setRtIdx(i+1);
				String raAnswerVal = "";
				String[] raAnswer =  request.getParameterValues("raAnswer"+(i+1));
				System.out.println("raAnswer : "+raAnswer);
				if(raAnswer != null && raAnswer.length >0){
					for(int j = 0; j < raAnswer.length; j++){
						raAnswerVal = raAnswerVal + raAnswer[j] + "||";
					}
					reservationUserAnswerVo.setRaAnswer(raAnswerVal);

					if(raEtcAnswer != null && raEtcAnswer.length > i){
						reservationUserAnswerVo.setRaEtcAnswer(raEtcAnswer[i]);
					}
					reservationEventSvc.registReservationEventUserAnswer(reservationUserAnswerVo);
				}								
			}
			if(result.getRiLotUse().equals("OO") && result.getRiPayYn().equals("Y")){
				if(result.getRiPay() != null && !result.getRiPay().equals("") && Integer.parseInt(result.getRiPay()) > 0){
					if(result.getRiOnlinePayYn() != null && !result.getRiOnlinePayYn().equals("") && result.getRiOnlinePayYn().equals("Y")){
						strReturnUrl = "redirect:/reservation/reservation/foffice/ex/reservation/reservationPayform.do?riIdx="+reservationUserInfoVo.getRiIdx()+"&ruIdx="+ruIdx;
					}else{
						strReturnUrl = "redirect:/reservation/reservation/foffice/ex/reservation/reservationReceipt.do?riIdx="+reservationUserInfoVo.getRiIdx()+"&ruIdx="+ruIdx;
					}
				}else{
					strReturnUrl = "redirect:/reservation/reservation/foffice/ex/reservation/reservationReceipt.do?riIdx="+reservationUserInfoVo.getRiIdx()+"&ruIdx="+ruIdx;
				}
			}else{
				strReturnUrl = "redirect:/reservation/reservation/foffice/ex/reservation/reservationReceipt.do?riIdx="+reservationUserInfoVo.getRiIdx()+"&ruIdx="+ruIdx;
			}
		}

		return strReturnUrl;
	}
	
	@RequestMapping("/{strSitePath}/{strDomain}/foffice/ex/reservation/reservationPayform.do")
	public String reservationSiteEventPay(
			HttpServletRequest request
			, @ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo
			, @ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo
			, ModelMap model
			) throws Exception {
		if(model == null){
			return "injeinc/common/code500";
		}
		reservationIndexVo = reservationEventSvc.retrieveReservationEvent(reservationIndexVo);
		ReservationUserInfoVo result = reservationEventSvc.retrieveReservationEventUserInfo(reservationUserInfoVo);
		
		reservationIndexVo.setRuIdx(reservationUserInfoVo.getRuIdx());

		model.addAttribute("ReservationUserInfoVo", result);
		model.addAttribute("ReservationItemList", reservationEventSvc.retrieveListReservationEventItem(reservationIndexVo));
		model.addAttribute("ReservationIndexVo", reservationIndexVo);

		return "injeinc/foffice/ex/reservation/reservationEvent_payform";
	}
	
	@RequestMapping("/{strSitePath}/{strDomain}/foffice/ex/reservation/reservationPayRes.do")
	public String reservationSiteEventPayRes(
			HttpServletRequest request
			, @ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo
			, @ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo
			, ModelMap model
			) throws Exception {
		if(model == null){
			return "injeinc/common/code500";
		}
		reservationIndexVo = reservationEventSvc.retrieveReservationEvent(reservationIndexVo);
		ReservationUserInfoVo result = reservationEventSvc.retrieveReservationEventUserInfo(reservationUserInfoVo);
		ReservationUserInfoVo returnVo = (ReservationUserInfoVo)reservationEventSvc.retrievePayResult(reservationUserInfoVo,request);
		
		reservationIndexVo.setRuIdx(reservationUserInfoVo.getRuIdx());

		model.addAttribute("ReservationUserInfoVo", result);
		model.addAttribute("PayInfoVo",returnVo);
		model.addAttribute("ReservationItemList", reservationEventSvc.retrieveListReservationEventItem(reservationIndexVo));
		model.addAttribute("ReservationIndexVo", reservationIndexVo);

		return "redirect:/reservation/reservation/foffice/ex/reservation/reservationReceipt.do?riIdx="+reservationUserInfoVo.getRiIdx()+"&ruIdx="+reservationUserInfoVo.getRuIdx();
	}

	@RequestMapping("/{strSitePath}/{strDomain}/foffice/ex/reservation/reservationReceipt.do")
	public String reservationSiteEventComp(
			HttpServletRequest request
			, @ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo
			, @ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo
			, ModelMap model
			) throws Exception {
		if(model == null){
			return "injeinc/common/code500";
		}
		reservationIndexVo = reservationEventSvc.retrieveReservationEvent(reservationIndexVo);
		ReservationUserInfoVo result = reservationEventSvc.retrieveReservationEventUserInfo(reservationUserInfoVo);

		reservationIndexVo.setRuIdx(reservationUserInfoVo.getRuIdx());

		model.addAttribute("ReservationUserInfoVo", result);
		model.addAttribute("ReservationItemList", reservationEventSvc.retrieveListReservationEventItem(reservationIndexVo));
		model.addAttribute("ReservationIndexVo", reservationIndexVo);

		return "injeinc/foffice/ex/reservation/reservationEvent_receipt";
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/site/{strDomain}/foffice/ex/reservation/ms00110.do")
	public String reservationSiteMyEventPag(
			HttpServletRequest request
			, @ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo
			, ModelMap model
			) throws Exception {
		if(model == null){
			return "injeinc/common/code500";
		}
		HttpSession ses = request.getSession();
		String ss_dupkey = (String)ses.getAttribute("ss_dupkey");
		
		int isMobile = 0;
		String agent = request.getHeader("USER-AGENT");
		String[] mobileos = {"iPhone","iPod","Android","BlackBerry","Windows CE","Nokia","Webos","Opera Mini","SonyEricsson","Opera Mobi","IEMobile"};
		int j = -1;

		for(int i=0 ; i<mobileos.length ; i++) {
			j=agent.indexOf(mobileos[i]);
			if(j > -1 ) {
				// 모바일로 접근했을 때
				isMobile = 1;
				break;
			}
		}

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(reservationIndexVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(reservationIndexVo.getPageUnit());
		
		//웹 사용 여부 
		if(isMobile > 0){
			paginationInfo.setPageSize(5);
		}else{
			paginationInfo.setPageSize(reservationIndexVo.getPageSize());
			
		}

		reservationIndexVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		reservationIndexVo.setLastIndex(paginationInfo.getLastRecordIndex());
		reservationIndexVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		reservationIndexVo.setRuDupkey(ss_dupkey);

		Map<String, Object> map = reservationEventSvc.retrievePagReservationEvent(reservationIndexVo);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));

		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);

		return "injeinc/foffice/ex/MS001/ms00110";
	}

	@RequestMapping("/site/{strDomain}/foffice/ex/reservation/ms00111.do")
	public String reservationSiteMyEventView(
			HttpServletRequest request
			, @ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo
			, ModelMap model
			) throws Exception {
		if(model == null){
			return "injeinc/common/code500";
		}
		HttpSession ses = request.getSession();
		String ss_dupkey = (String)ses.getAttribute("ss_dupkey");

		reservationIndexVo.setRuDupkey(ss_dupkey);

		ReservationIndexVo result = reservationEventSvc.retrieveReservationEvent(reservationIndexVo);

		//List<ReservationUserInfoVo> userList = reservationEventSvc.retrieveListReservationEventUserInfo(reservationIndexVo);
		
		result.setPageIndex(reservationIndexVo.getPageIndex());
		result.setSearchCondition(reservationIndexVo.getSearchCondition());
		result.setSearchKeyword(reservationIndexVo.getSearchKeyword());

		model.addAttribute("ReservationIndexVo", result);
		model.addAttribute("ReservationAddItemList", reservationEventSvc.retrieveListReservationEventAddItem(reservationIndexVo));
		//model.addAttribute("ReservationUserList", userList);

		return "injeinc/foffice/ex/MS001/ms00111";
	}

	/* 신청 취소 */
	@RequestMapping("/site/{strDomain}/foffice/ex/reservation/ms00111Rmv.do")
	public String reservationEventUserInfoRmv(
			HttpServletRequest request
			, @ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo
			, ModelMap model
			) throws Exception {

		String userid = (String) WebUtils.getSessionAttribute(request, "ss_id");
		reservationUserInfoVo.setModId(userid);

		reservationEventSvc.removeReservationEventUserInfo(reservationUserInfoVo);

		return alert("/site/{strDomain}/foffice/ex/reservationMy/list.do", "행사예약이 취소되었습니다.", model);
	}

	/* 예약 확정 */
	@RequestMapping("/site/{strDomain}/foffice/ex/reservation/ms00111Confrim.do")
	public String reservationEventUserInfoConfirm(
			HttpServletRequest request
			, @ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo
			, ModelMap model
			) throws Exception {

		String userid = (String) WebUtils.getSessionAttribute(request, "ss_id");
		reservationUserInfoVo.setModId(userid);

		reservationEventSvc.updateReservationEventUserInfoConfirm(reservationUserInfoVo);

		return alert(request.getContextPath()+"/site/{strDomain}/foffice/ex/reservation/ms00111.do?riIdx="+reservationUserInfoVo.getRiIdx()+"&ruIdx="+reservationUserInfoVo.getRuIdx(), "예약 확정 등록 하셨습니다.", model);
	}


}
