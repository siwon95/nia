package egovframework.injeinc.foffice.ex.reservationEtc.controller;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.WebUtils;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.Message;
import egovframework.cmm.service.FileListVO;
import egovframework.cmm.service.FileVO;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationAddItemVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationIndexVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationItemVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationUserAnswerVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationUserFileVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationUserInfoVo;
import egovframework.injeinc.boffice.ex.reservationEtc.service.ReservationEtcSvc;
import egovframework.injeinc.boffice.sy.code.service.CmmCodeSvc;
import egovframework.injeinc.common.code.service.CodeSvc;
import egovframework.injeinc.common.code.vo.CodeVo;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class ReservationSiteEtcCtr extends CmmLogCtr {

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
	
	/** 통합예약_기타예약 목록 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/{strSitePath}/{strDomain}/ex/reservationEtc/reservationList.do")
	public String reservationList(
			HttpServletRequest request,
			@ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo
			, @PathVariable("strDomain") String strDomain
			, @PathVariable("strSitePath") String strSitePath
			, ModelMap model) throws Exception {
		if (model == null) {
			return "injeinc/common/code500";
		}
		int isMobile = 0;
		String agent = request.getHeader("USER-AGENT");
		String[] mobileos = { "iPhone", "iPod", "Android", "BlackBerry",
				"Windows CE", "Nokia", "Webos", "Opera Mini", "SonyEricsson",
				"Opera Mobi", "IEMobile" };
		int j = -1;

		for (int i = 0; i < mobileos.length; i++) {
			j = agent.indexOf(mobileos[i]);
			if (j > -1) {
				// 모바일로 접근했을 때
				isMobile = 1;
				break;
			}
		}
		
		String returnUrl = "injeinc/foffice/ex/reservationEtc/reservationList";
		if(!"reservation".equals(strSitePath)){
			reservationIndexVo.setRiSupervisionOrg(strDomain);
			returnUrl = "injeinc/foffice/ex/reservationEtc/reservationList_subSite";
		}
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(reservationIndexVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(8);

		// 웹 사용 여부
		if (isMobile > 0) {
			paginationInfo.setPageSize(5);
		} else {
			paginationInfo.setPageSize(reservationIndexVo.getPageSize());

		}

		reservationIndexVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		reservationIndexVo.setLastIndex(paginationInfo.getLastRecordIndex());
		reservationIndexVo.setRecordCountPerPage(paginationInfo
				.getRecordCountPerPage());

		reservationIndexVo.setRiType("T");
		reservationIndexVo.setRiConfirmYn("Y");

		Map<String, Object> map = reservationEtcSvc.retrievePagReservationEtc(reservationIndexVo);
		int totCnt = Integer.parseInt((String) map.get("resultCnt"));

		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("resEtcCodeList", cmmCodeSvc.retrieveListCmmCode("RES_ETC_CODE"));
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);

		return returnUrl;
	}
	
	/** 통합예약_기타예약 상세화면*/
	@RequestMapping("/{strSitePath}/{strDomain}/ex/reservationEtc/reservationView.do")
	public String reservationView(
			HttpServletRequest request
			, @ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo
			, ModelMap model
			) throws Exception {
		if(model == null){
			return "injeinc/common/code500";
		}
		ReservationIndexVo result = reservationEtcSvc.retrieveReservationEtc(reservationIndexVo);

		reservationEtcSvc.modifyReservationEtcReadCnt(reservationIndexVo);

		//List<ReservationUserInfoVo> userList = reservationEtcSvc.retrieveListReservationEtcUserInfo(reservationIndexVo);

		result.setPageIndex(reservationIndexVo.getPageIndex());
		result.setSearchCondition(reservationIndexVo.getSearchCondition());
		result.setSearchKeyword(reservationIndexVo.getSearchKeyword());

		model.addAttribute("ReservationIndexVo", result);
		model.addAttribute("ReservationAddItemList", reservationEtcSvc.retrieveListReservationEtcAddItem(reservationIndexVo));
		//model.addAttribute("ReservationUserList", userList);

		return "injeinc/foffice/ex/reservationEtc/reservationView";
	}
	
	/** 통합예약_기타예약 신청폼*/
	@RequestMapping("/{strSitePath}/{strDomain}/foffice/ex/reservationEtc/reservationForm.do")
	public String reservationForm(
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

		reservationIndexVo = reservationEtcSvc.retrieveReservationEtc(reservationIndexVo);
		
		ArrayList riIdxList = new ArrayList();
		System.out.println("-------------  : "+reservationIndexVo.getRiRedundancy());
		if(reservationIndexVo.getRiRedundancy() != null){
			String redundancyList[] = reservationIndexVo.getRiRedundancy().split(";");

			for(int i=0; i < redundancyList.length; i++){
				riIdxList.add(redundancyList[i]);
			}

			reservationUserInfoVo.setRiIdxList(riIdxList);
			
			if(reservationEtcSvc.retrieveReservationEtcRedundancyDupCnt(reservationUserInfoVo) > 0){
				return alert(request.getContextPath()+"/reservation/reservation/ex/reservationEtc/reservationView.do?riIdx="+reservationIndexVo.getRiIdx(), "일부 기타예약과 중복 예약을 하실 수 없습니다.", model);
			}
		}
				
		if(reservationEtcSvc.retrieveReservationEtcUserInfoDupCnt(reservationUserInfoVo) > 0){
			return alert(request.getContextPath()+"/reservation/reservation/ex/reservationEtc/reservationView.do?riIdx="+reservationIndexVo.getRiIdx(), "기존에 신청한 내역이 있습니다.", model);
		}
		
		if(reservationIndexVo != null){
			if(reservationIndexVo.getRiRecruitYn().equals("Y") && reservationIndexVo.getRiLotUse().equals("OO")){
				if(Integer.parseInt(reservationIndexVo.getRiRecruitCnt())  <= reservationIndexVo.getRiAccCnt() ){
					return alert(request.getContextPath()+"/reservation/reservation/ex/reservationEtc/reservationView.do?riIdx="+reservationIndexVo.getRiIdx(), "모집인원이 초과되었습니다.", model);
				}
			}
		}
		
		List<ReservationAddItemVo> ReservationAddItemList = reservationEtcSvc.retrieveListReservationEtcAddItem(reservationIndexVo);
		model.addAttribute("ReservationAddItemList", ReservationAddItemList);
		model.addAttribute("ReservationItemList", reservationEtcSvc.retrieveListReservationEtcItem(reservationIndexVo));
		model.addAttribute("ReservationIndexVo", reservationIndexVo);

		return "injeinc/foffice/ex/reservationEtc/reservationForm";
	}
	
	/** 통합예약_기타예약 신청 등록 프로세스*/
	@RequestMapping("/{strSitePath}/{strDomain}/foffice/ex/reservationEtc/reservationReg.do")
	public String reservationReg(
			HttpServletRequest request
			, final MultipartHttpServletRequest multiRequest
			, @ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo
			, @ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo
			, @ModelAttribute("ReservationUserAnswerVo") ReservationUserAnswerVo reservationUserAnswerVo
			, ModelMap model
			) throws Exception {
		HttpSession ses = request.getSession();
		ArrayList riIdxList = new ArrayList();
		String ruIdx = "";
		
		reservationIndexVo = reservationEtcSvc.retrieveReservationEtc(reservationIndexVo);
		
		String redundancyList[] = reservationIndexVo.getRiRedundancy().split(";");
		for(int i=0; i < redundancyList.length; i++){
			riIdxList.add(redundancyList[i]);
		}

		reservationUserInfoVo.setRiIdxList(riIdxList);
		
		/* 기존 신청내역이 있는지 확인 */
		String strReturnUrl = "";
		if(reservationEtcSvc.retrieveReservationEtcUserInfoDupCnt(reservationUserInfoVo) > 0){
			return alert("/reservation/reservation/ex/reservationEtc/reservationView.do?riIdx="+reservationIndexVo.getRiIdx(), "기존에 신청한 내역이 있습니다.", model);
		}
		
		List<ReservationAddItemVo> ReservationAddItemList = reservationEtcSvc.retrieveListReservationEtcAddItem(reservationIndexVo);
		/* 항목별 정원 시 정원모집 초과 체크 */
		if(reservationIndexVo.getRiRecruitYn().equals("I") && reservationIndexVo.getRiLotUse().equals("OO")){
			
			for(int i = 0; i < ReservationAddItemList.size(); i ++){
				ReservationAddItemVo reservationAddItemVo = ReservationAddItemList.get(i);
				
				int raLimitCnt = reservationAddItemVo.getRaLimitCnt() == null?0:Integer.parseInt(reservationAddItemVo.getRaLimitCnt());
				int raApplyCnt = reservationAddItemVo.getRaApplyCnt() == null?0:Integer.parseInt(reservationAddItemVo.getRaApplyCnt());
				int ruObjectCnt = reservationUserInfoVo.getRuObjectCnt() == null?0:Integer.parseInt(reservationUserInfoVo.getRuObjectCnt());
				
				if(reservationAddItemVo.getRaIdx() == Integer.parseInt(reservationUserInfoVo.getRaIdx())){
					if(raLimitCnt < raApplyCnt + ruObjectCnt){
						return alert(request.getContextPath()+"/reservation/reservation/ex/reservationEtc/reservationView.do?riIdx="+reservationIndexVo.getRiIdx(), "모집인원이 초과되었습니다.", model);
					}
				}
			}
		}
		
		if(reservationEtcSvc.retrieveReservationEtcRedundancyDupCnt(reservationUserInfoVo) > 0){
			return alert(request.getContextPath()+"/reservation/reservation/ex/reservationEtc/reservationView.do?riIdx="+reservationIndexVo.getRiIdx(), "일부 기타예약과 중복 예약을 하실 수 없습니다.", model);
		}else{
			ruIdx = reservationUserIdGnrService.getNextStringId();
			reservationUserInfoVo.setRuIdx(ruIdx);

			List<ReservationItemVo> itemList = reservationEtcSvc.retrieveListReservationEtcItem(reservationIndexVo);

			reservationUserInfoVo.setRegId((String)ses.getAttribute("ss_id"));
			reservationIndexVo.setRuDupkey("");
			ReservationIndexVo result = reservationEtcSvc.retrieveReservationEtc(reservationIndexVo);

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
			
			/** 신청자 정보 저장 */
			reservationEtcSvc.registReservationEtcUserInfo(reservationUserInfoVo);
			
			/** 신청자 제출한 첨부파일 저장 */
			final Map<String, MultipartFile> files = multiRequest.getFileMap();
			ReservationUserFileVo reservationUserFileVo = new ReservationUserFileVo();
			FileListVO fileListVO = reservationEtcSvc.uploadUserFile(files, reservationUserFileVo);
			String atchFileId = fileListVO.getAtchFileId();
			List<FileVO> fileVoList = fileListVO.getFileVoList();
			
			/** 신청자 답변정보 저장 */
			String[] raEtcAnswer = request.getParameterValues("raEtcAnswer");
			int fileIdx = 0;
			for(int i = 0; i < itemList.size(); i++) {
				ReservationItemVo vo = itemList.get(i);
				
				reservationUserAnswerVo = new ReservationUserAnswerVo();
				reservationUserAnswerVo.setRuIdx(ruIdx);
				reservationUserAnswerVo.setRiIdx(reservationUserInfoVo.getRiIdx());
				reservationUserAnswerVo.setRtIdx(i+1);
				String raAnswerVal = "";
				String[] raAnswer =  request.getParameterValues("raAnswer"+(i+1));
				if(raAnswer != null && raAnswer.length >0){
					for(int j = 0; j < raAnswer.length; j++){
						raAnswerVal = raAnswerVal + raAnswer[j] + "||";
					}
					reservationUserAnswerVo.setRaAnswer(raAnswerVal);

					if(raEtcAnswer != null && raEtcAnswer.length > i){
						reservationUserAnswerVo.setRaEtcAnswer(raEtcAnswer[i]);
					}
					reservationEtcSvc.registReservationEtcUserAnswer(reservationUserAnswerVo);
				}
				
				/* 첨부파일 DB저장 */
				if("FL".equals(vo.getRtItemType())){
					reservationEtcSvc.registReservationEtcUserAnswer(reservationUserAnswerVo);
					/*db저장*/
					reservationEtcSvc.insertReservationEtcUserFile(fileVoList.get(fileIdx), atchFileId, reservationIndexVo.getRiIdx(), String.valueOf(reservationUserAnswerVo.getRtIdx()), reservationUserInfoVo.getRuIdx());
					fileIdx++;
				}
			}
			
			if(result.getRiLotUse().equals("OO") && result.getRiPayYn().equals("Y")){
				if(result.getRiPay() != null && !result.getRiPay().equals("") && Integer.parseInt(result.getRiPay()) > 0){
					if(result.getRiOnlinePayYn() != null && !result.getRiOnlinePayYn().equals("") && result.getRiOnlinePayYn().equals("Y")){
						strReturnUrl = "redirect:/reservation/reservation/foffice/ex/reservationEtc/reservationPayform.do?riIdx="+reservationUserInfoVo.getRiIdx()+"&ruIdx="+ruIdx;
					}else{
						strReturnUrl = "redirect:/reservation/reservation/foffice/ex/reservationEtc/reservationReceipt.do?riIdx="+reservationUserInfoVo.getRiIdx()+"&ruIdx="+ruIdx;
					}
				}else{
					strReturnUrl = "redirect:/reservation/reservation/foffice/ex/reservationEtc/reservationReceipt.do?riIdx="+reservationUserInfoVo.getRiIdx()+"&ruIdx="+ruIdx;
				}
			}else{
				strReturnUrl = "redirect:/reservation/reservation/foffice/ex/reservationEtc/reservationReceipt.do?riIdx="+reservationUserInfoVo.getRiIdx()+"&ruIdx="+ruIdx;
			}
		}

		return strReturnUrl;
	}
	
	/** 통합예약_기타예약 신청완료페이지 */
	@RequestMapping("/{strSitePath}/{strDomain}/foffice/ex/reservationEtc/reservationReceipt.do")
	public String reservationReceipt(
			HttpServletRequest request
			, @ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo
			, @ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo
			, ModelMap model
			) throws Exception {
		if(model == null){
			return "injeinc/common/code500";
		}
		reservationIndexVo = reservationEtcSvc.retrieveReservationEtc(reservationIndexVo);
		ReservationUserInfoVo result = reservationEtcSvc.retrieveReservationEtcUserInfo(reservationUserInfoVo);

		reservationIndexVo.setRuIdx(reservationUserInfoVo.getRuIdx());

		model.addAttribute("ReservationUserInfoVo", result);
		model.addAttribute("ReservationAddItemList", reservationEtcSvc.retrieveListReservationEtcAddItem(reservationIndexVo));
		model.addAttribute("ReservationItemList", reservationEtcSvc.retrieveListReservationEtcItem(reservationIndexVo));
		model.addAttribute("ReservationIndexVo", reservationIndexVo);

		return "injeinc/foffice/ex/reservationEtc/reservationReceipt";
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/{strSitePath}/{strDomain}/ex/reservationEtc/re00501.do")
	public String reservationSiteEtcPag(
			HttpServletRequest request,
			@ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo,
			ModelMap model) throws Exception {
		if (model == null) {
			return "injeinc/common/code500";
		}
		int isMobile = 0;
		String agent = request.getHeader("USER-AGENT");
		String[] mobileos = { "iPhone", "iPod", "Android", "BlackBerry",
				"Windows CE", "Nokia", "Webos", "Opera Mini", "SonyEricsson",
				"Opera Mobi", "IEMobile" };
		int j = -1;

		for (int i = 0; i < mobileos.length; i++) {
			j = agent.indexOf(mobileos[i]);
			if (j > -1) {
				// 모바일로 접근했을 때
				isMobile = 1;
				break;
			}
		}

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(reservationIndexVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(20);

		// 웹 사용 여부
		if (isMobile > 0) {
			paginationInfo.setPageSize(5);
		} else {
			paginationInfo.setPageSize(reservationIndexVo.getPageSize());

		}

		reservationIndexVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		reservationIndexVo.setLastIndex(paginationInfo.getLastRecordIndex());
		reservationIndexVo.setRecordCountPerPage(paginationInfo
				.getRecordCountPerPage());

		reservationIndexVo.setRiType("T");
		reservationIndexVo.setRiConfirmYn("Y");

		Map<String, Object> map = reservationEtcSvc
				.retrievePagReservationEtc(reservationIndexVo);
		int totCnt = Integer.parseInt((String) map.get("resultCnt"));

		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);

		return "injeinc/foffice/ex/RE005/re00501";
	}

	@RequestMapping("/{strSitePath}/{strDomain}/foffice/ex/reservationEtc/re00502.do")
	public String reservationSiteEtcForm(
			@ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo,
			ModelMap model) throws Exception {
		if (model == null) {
			return "injeinc/common/code500";
		}
		HashMap<String, String> param = new HashMap<String, String>();

		HashMap<String, Object> serviceMap = codeSvc.retrieveDeptCodeAll(param);
		List<CodeVo> rowDataList = (List<CodeVo>) serviceMap.get("rowDataList");

		model.addAttribute("deptList", rowDataList);

		// 관리부서목록
		List deptCode = reservationEtcSvc.retrieveDeptCode();
		model.addAttribute("dept", deptCode);

		return "injeinc/foffice/ex/RE005/re00502";
	}

	@RequestMapping("/{strSitePath}/{strDomain}/foffice/ex/reservationEtc/re00502Reg.do")
	public String reservationSiteEtcReg(
			final MultipartHttpServletRequest multiRequest,
			@ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo,
			ModelMap model) throws Exception {
		if (multiRequest == null) {
			return "injeinc/common/code500";
		}
		ReservationAddItemVo reservationAddItemVo;

		String riIdx = reservationIdgenService.getNextStringId();
		reservationIndexVo.setRiIdx(riIdx);

		HttpSession ses = multiRequest.getSession();
		String userid = (String) ses.getAttribute("ss_id");

		reservationIndexVo.setRegId(userid);
		reservationIndexVo.setRiType("T");

		String raTitle[] = multiRequest.getParameterValues("raTitle");
		String raContent[] = multiRequest.getParameterValues("raContent");

		for (int i = 0; i < raTitle.length; i++) {
			reservationAddItemVo = new ReservationAddItemVo();

			reservationAddItemVo.setRiIdx(riIdx);
			reservationAddItemVo.setRaIdx(i + 1);
			reservationAddItemVo.setRaTitle(raTitle[i]);
			reservationAddItemVo.setRaContent(raContent[i]);
			reservationAddItemVo.setRegId(userid);

			reservationEtcSvc.registReservationEtcAddItem(reservationAddItemVo);
		}

		reservationIndexVo.setRiOffLotUse(reservationIndexVo.getRiOffLotUse()
				.replaceAll(",", "||"));
		reservationIndexVo.setRiConfirmYn("N");
		reservationIndexVo.setRiUserloginType("U");

		if (reservationIndexVo.getRiTermType().equals("O")) {
			reservationIndexVo
					.setRiSdate(multiRequest.getParameter("riSdate1"));
		}

		// 파일업로드
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		ReservationIndexVo uploadVO = new ReservationIndexVo();
		uploadVO = reservationEtcSvc.uploadFile(files, reservationIndexVo);

		reservationIndexVo.setRiImageFileId(uploadVO.getRiImageFileId());

		reservationEtcSvc.registReservationEtc(reservationIndexVo);

		String SVC_MSGE = Message.getMessage("100.message");
		return alert(multiRequest.getContextPath()
				+ "/site/{strDomain}/ex/reservationEtc/re00501.do", SVC_MSGE,
				model);
	}

	@RequestMapping("/{strSitePath}/{strDomain}/ex/reservationEtc/re00503.do")
	public String reservationSiteEtcView(
			HttpServletRequest request,
			@ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo,
			ModelMap model) throws Exception {
		if (model == null) {
			return "injeinc/common/code500";
		}
		ReservationIndexVo result = reservationEtcSvc
				.retrieveReservationEtc(reservationIndexVo);

		reservationEtcSvc.modifyReservationEtcReadCnt(reservationIndexVo);

		// List<ReservationUserInfoVo> userList =
		// reservationEtcSvc.retrieveListReservationEtcUserInfo(reservationIndexVo);

		result.setPageIndex(reservationIndexVo.getPageIndex());
		result.setSearchCondition(reservationIndexVo.getSearchCondition());
		result.setSearchKeyword(reservationIndexVo.getSearchKeyword());

		model.addAttribute("ReservationIndexVo", result);
		model.addAttribute("ReservationAddItemList", reservationEtcSvc
				.retrieveListReservationEtcAddItem(reservationIndexVo));
		// model.addAttribute("ReservationUserList", userList);

		return "injeinc/foffice/ex/RE005/re00503";
	}

	@RequestMapping("/{strSitePath}/{strDomain}/foffice/ex/reservationEtc/re00504.do")
	public String reservationSiteEtcUserForm(
			HttpServletRequest request,
			@ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo,
			@ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo,
			ModelMap model) throws Exception {
		if (model == null) {
			return "injeinc/common/code500";
		}
		HttpSession ses = request.getSession();

		reservationUserInfoVo.setRuName((String) ses.getAttribute("ss_name"));
		reservationUserInfoVo.setRuDupkey((String) ses
				.getAttribute("ss_dupkey"));

		if (ses.getAttribute("ss_level").equals("7")) {
			reservationUserInfoVo.setRuEmail((String) ses
					.getAttribute("ss_email"));
			reservationUserInfoVo.setRuZipcode((String) ses
					.getAttribute("ss_zip"));
			reservationUserInfoVo.setRuAddress((String) ses
					.getAttribute("ss_addr1"));
			reservationUserInfoVo.setRuAddressDtl((String) ses
					.getAttribute("ss_addr2"));
			reservationUserInfoVo.setRuTel((String) ses.getAttribute("ss_tel"));
			reservationUserInfoVo.setRuHp((String) ses.getAttribute("ss_hp"));
		}

		reservationIndexVo = reservationEtcSvc
				.retrieveReservationEtc(reservationIndexVo);

		ArrayList riIdxList = new ArrayList();
		String redundancyList[] = reservationIndexVo.getRiRedundancy().split(
				";");

		for (int i = 0; i < redundancyList.length; i++) {
			riIdxList.add(redundancyList[i]);
		}

		reservationUserInfoVo.setRiIdxList(riIdxList);

		if (reservationEtcSvc
				.retrieveReservationEtcUserInfoDupCnt(reservationUserInfoVo) > 0) {
			return alert(request.getContextPath()
					+ "/site/{strDomain}/ex/reservationEtc/re00503.do?riIdx="
					+ reservationIndexVo.getRiIdx(),
					"기존에 신청한 내역이 있습니다. \\n신청내용은 마이인재 > 나의예약에서 확인할 수 있습니다.",
					model);
		} else if (reservationEtcSvc
				.retrieveReservationEtcRedundancyDupCnt(reservationUserInfoVo) > 0) {
			return alert(request.getContextPath()
					+ "/site/{strDomain}/ex/reservationEtc/re00503.do?riIdx="
					+ reservationIndexVo.getRiIdx(),
					"일부 행사와 중복 예약을 하실 수 없습니다.", model);
		}

		if (reservationIndexVo != null) {
			if (reservationIndexVo.getRiRecruitYn().equals("Y")
					&& reservationIndexVo.getRiLotUse().equals("OO")) {
				if (Integer.parseInt(reservationIndexVo.getRiRecruitCnt()) <= reservationIndexVo
						.getRiAccCnt()) {
					return alert(
							request.getContextPath()
									+ "/site/{strDomain}/ex/reservationEtc/re00503.do?riIdx="
									+ reservationIndexVo.getRiIdx(),
							"모집인원이 초과되었습니다.", model);
				}
			}
		}

		model.addAttribute("ReservationItemList", reservationEtcSvc
				.retrieveListReservationEtcItem(reservationIndexVo));
		model.addAttribute("ReservationIndexVo", reservationIndexVo);

		return "injeinc/foffice/ex/RE005/re00504";
	}

	@RequestMapping("/{strSitePath}/{strDomain}/foffice/ex/reservationEtc/re00504Reg.do")
	public String reservationSiteEtcUserInfoReg(
			HttpServletRequest request,
			@ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo,
			@ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo,
			@ModelAttribute("ReservationUserAnswerVo") ReservationUserAnswerVo reservationUserAnswerVo,
			ModelMap model) throws Exception {
		HttpSession ses = request.getSession();
		ArrayList riIdxList = new ArrayList();
		String ruIdx = "";

		String redundancyList[] = reservationIndexVo.getRiRedundancy().split(
				";");

		for (int i = 0; i < redundancyList.length; i++) {
			riIdxList.add(redundancyList[i]);
		}

		reservationUserInfoVo.setRiIdxList(riIdxList);

		if (reservationEtcSvc
				.retrieveReservationEtcUserInfoDupCnt(reservationUserInfoVo) > 0) {
			return alert(request.getContextPath()
					+ "/site/{strDomain}/ex/reservationEtc/re00503.do?riIdx="
					+ reservationIndexVo.getRiIdx(),
					"기존에 신청한 내역이 있습니다. \\n신청내용은 마이인재 > 나의예약에서 확인할 수 있습니다.",
					model);
		} else if (reservationEtcSvc
				.retrieveReservationEtcRedundancyDupCnt(reservationUserInfoVo) > 0) {
			return alert(request.getContextPath()
					+ "/site/{strDomain}/ex/reservationEtc/re00503.do?riIdx="
					+ reservationIndexVo.getRiIdx(),
					"일부 행사와 중복 예약을 하실 수 없습니다.", model);
		} else {
			ruIdx = reservationUserIdGnrService.getNextStringId();
			reservationUserInfoVo.setRuIdx(ruIdx);

			List<ReservationItemVo> itemList = reservationEtcSvc
					.retrieveListReservationEtcItem(reservationIndexVo);

			reservationUserInfoVo.setRegId((String) ses.getAttribute("ss_id"));
			reservationIndexVo.setRuDupkey("");
			ReservationIndexVo result = reservationEtcSvc
					.retrieveReservationEtc(reservationIndexVo);

			if (result.getRiLotUse().equals("OO")) {
				reservationUserInfoVo.setRuLotResult("S");
			} else {
				reservationUserInfoVo.setRuLotResult("R");
			}

			reservationEtcSvc
					.registReservationEtcUserInfo(reservationUserInfoVo);

			String[] raEtcAnswer = request.getParameterValues("raEtcAnswer");

			for (int i = 0; i < itemList.size(); i++) {
				reservationUserAnswerVo = new ReservationUserAnswerVo();

				reservationUserAnswerVo.setRuIdx(ruIdx);
				reservationUserAnswerVo.setRiIdx(reservationUserInfoVo
						.getRiIdx());
				reservationUserAnswerVo.setRtIdx(i + 1);
				String raAnswerVal = "";
				String[] raAnswer = request.getParameterValues("raAnswer"
						+ (i + 1));
				for (int j = 0; j < raAnswer.length; j++) {
					raAnswerVal = raAnswerVal + raAnswer[j] + "||";
				}
				reservationUserAnswerVo.setRaAnswer(raAnswerVal);

				if (raEtcAnswer != null && raEtcAnswer.length > i) {
					reservationUserAnswerVo.setRaEtcAnswer(raEtcAnswer[i]);
				}
				reservationEtcSvc
						.registReservationEtcUserAnswer(reservationUserAnswerVo);
			}
		}

		return "redirect:/site/{strDomain}/ex/reservationEtc/re00505.do?riIdx="
				+ reservationUserInfoVo.getRiIdx() + "&ruIdx=" + ruIdx;
	}

	@RequestMapping("/{strSitePath}/{strDomain}/ex/reservationEtc/re00505.do")
	public String reservationSiteEtcComp(
			HttpServletRequest request,
			@ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo,
			@ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo,
			ModelMap model) throws Exception {
		if (model == null) {
			return "injeinc/common/code500";
		}
		reservationIndexVo = reservationEtcSvc
				.retrieveReservationEtc(reservationIndexVo);
		ReservationUserInfoVo result = reservationEtcSvc
				.retrieveReservationEtcUserInfo(reservationUserInfoVo);

		reservationIndexVo.setRuIdx(reservationUserInfoVo.getRuIdx());

		model.addAttribute("ReservationUserInfoVo", result);
		model.addAttribute("ReservationItemList", reservationEtcSvc
				.retrieveListReservationEtcItem(reservationIndexVo));
		model.addAttribute("ReservationIndexVo", reservationIndexVo);

		return "injeinc/foffice/ex/RE005/re00505";
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/{strSitePath}/{strDomain}/foffice/ex/reservationEtc/ms00110.do")
	public String reservationSiteMyEtcPag(
			HttpServletRequest request,
			@ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo,
			ModelMap model) throws Exception {
		if (model == null) {
			return "injeinc/common/code500";
		}
		HttpSession ses = request.getSession();
		String ss_dupkey = (String) ses.getAttribute("ss_dupkey");

		int isMobile = 0;
		String agent = request.getHeader("USER-AGENT");
		String[] mobileos = { "iPhone", "iPod", "Android", "BlackBerry",
				"Windows CE", "Nokia", "Webos", "Opera Mini", "SonyEricsson",
				"Opera Mobi", "IEMobile" };
		int j = -1;

		for (int i = 0; i < mobileos.length; i++) {
			j = agent.indexOf(mobileos[i]);
			if (j > -1) {
				// 모바일로 접근했을 때
				isMobile = 1;
				break;
			}
		}

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(reservationIndexVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(reservationIndexVo.getPageUnit());

		// 웹 사용 여부
		if (isMobile > 0) {
			paginationInfo.setPageSize(5);
		} else {
			paginationInfo.setPageSize(reservationIndexVo.getPageSize());

		}

		reservationIndexVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		reservationIndexVo.setLastIndex(paginationInfo.getLastRecordIndex());
		reservationIndexVo.setRecordCountPerPage(paginationInfo
				.getRecordCountPerPage());

		reservationIndexVo.setRuDupkey(ss_dupkey);

		Map<String, Object> map = reservationEtcSvc
				.retrievePagReservationEtc(reservationIndexVo);
		int totCnt = Integer.parseInt((String) map.get("resultCnt"));

		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);

		return "injeinc/foffice/ex/MS001/ms00110";
	}

	@RequestMapping("/{strSitePath}/{strDomain}/foffice/ex/reservationEtc/ms00111.do")
	public String reservationSiteMyEtcView(
			HttpServletRequest request,
			@ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo,
			ModelMap model) throws Exception {
		if (model == null) {
			return "injeinc/common/code500";
		}
		HttpSession ses = request.getSession();
		String ss_dupkey = (String) ses.getAttribute("ss_dupkey");

		reservationIndexVo.setRuDupkey(ss_dupkey);

		ReservationIndexVo result = reservationEtcSvc
				.retrieveReservationEtc(reservationIndexVo);

		// List<ReservationUserInfoVo> userList =
		// reservationEtcSvc.retrieveListReservationEtcUserInfo(reservationIndexVo);

		result.setPageIndex(reservationIndexVo.getPageIndex());
		result.setSearchCondition(reservationIndexVo.getSearchCondition());
		result.setSearchKeyword(reservationIndexVo.getSearchKeyword());

		model.addAttribute("ReservationIndexVo", result);
		model.addAttribute("ReservationAddItemList", reservationEtcSvc
				.retrieveListReservationEtcAddItem(reservationIndexVo));
		// model.addAttribute("ReservationUserList", userList);

		return "injeinc/foffice/ex/MS001/ms00111";
	}

	/* 신청 취소 */
	@RequestMapping("/{strSitePath}/{strDomain}/foffice/ex/reservationEtc/ms00111Rmv.do")
	public String reservationEtcUserInfoRmv(
			HttpServletRequest request,
			@PathVariable("strSitePath") String strSitePath,
			@PathVariable("strDomain") String strDomain,
			@ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo,
			ModelMap model) throws Exception {

		String userid = (String) WebUtils.getSessionAttribute(request, "ss_id");
		reservationUserInfoVo.setModId(userid);

		reservationEtcSvc.removeReservationEtcUserInfo(reservationUserInfoVo);

		return alert("/"+strSitePath+"/"+strDomain+"/foffice/ex/reservationMy/list.do","예약이 취소되었습니다.", model);
	}

	/* 예약 확정 */
	@RequestMapping("/{strSitePath}/{strDomain}/foffice/ex/reservationEtc/ms00111Confrim.do")
	public String reservationEtcUserInfoConfirm(
			HttpServletRequest request,
			@ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo,
			ModelMap model) throws Exception {

		String userid = (String) WebUtils.getSessionAttribute(request, "ss_id");
		reservationUserInfoVo.setModId(userid);

		reservationEtcSvc
				.updateReservationEtcUserInfoConfirm(reservationUserInfoVo);

		return alert(
				request.getContextPath()
						+ "/site/{strDomain}/foffice/ex/reservationEtc/ms00111.do?riIdx="
						+ reservationUserInfoVo.getRiIdx() + "&ruIdx="
						+ reservationUserInfoVo.getRuIdx(), "예약 확정 등록 하셨습니다.",
				model);
	}
	
	@RequestMapping("/{strSitePath}/{strDomain}/foffice/ex/reservationEtcMy/View.do")
	public String reservationEtcMyView(
			HttpServletRequest request
			, @ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo
			, @ModelAttribute("reservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo
			, ModelMap model
			) throws Exception {
		HttpSession ses = request.getSession();
		String ss_dupkey = (String)ses.getAttribute("ss_dupkey");

		reservationIndexVo.setRuDupkey(ss_dupkey);
		
		//ReservationIndexVo result = reservationEventSvc.retrieveReservationEvent(reservationIndexVo);
		ReservationIndexVo result = reservationEtcSvc.retrieveReservationEtc(reservationIndexVo);
		//reservationEventSvc.modifyReservationEventReadCnt(reservationIndexVo);

		//List<ReservationUserInfoVo> userList = reservationEventSvc.retrieveListReservationEventUserInfo(reservationIndexVo);

		result.setPageIndex(reservationIndexVo.getPageIndex());
		result.setSearchCondition(reservationIndexVo.getSearchCondition());
		result.setSearchKeyword(reservationIndexVo.getSearchKeyword());

		model.addAttribute("ReservationIndexVo", result);
		model.addAttribute("ReservationAddItemList", reservationEtcSvc.retrieveListReservationEtcAddItem(reservationIndexVo));
		//model.addAttribute("ReservationUserList", userList);

		return "injeinc/foffice/ex/reservationEtc/reservationEtc_myview";
	}

}
