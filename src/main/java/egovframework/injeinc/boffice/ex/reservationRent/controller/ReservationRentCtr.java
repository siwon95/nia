package egovframework.injeinc.boffice.ex.reservationRent.controller;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.util.WebUtils;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.EgovWebUtil;
import egovframework.cmm.Message;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationAddItemVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationIndexVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationUserAnswerVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationUserInfoVo;
import egovframework.injeinc.boffice.ex.reservationRent.service.ReservationRentSvc;
import egovframework.injeinc.boffice.ex.reservationRent.vo.ReservationRentVo;
import egovframework.injeinc.boffice.excel.controller.ExcelCtr;
import egovframework.injeinc.boffice.excel.service.BizExcelSvc;
import egovframework.injeinc.common.code.service.CodeSvc;
import egovframework.injeinc.common.code.vo.CodeVo;
import egovframework.injeinc.common.util.DateUtil;
import egovframework.injeinc.common.util.EgovStringUtil;
import egovframework.injeinc.common.util.UmsUtil;
import egovframework.injeinc.common.vo.UmsVo;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class ReservationRentCtr extends CmmLogCtr{
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationRentCtr.class);
	 @Resource(name = "CodeSvc")
	 private CodeSvc codeSvc;

	 @Resource(name = "ReservationRentSvc")
	 private ReservationRentSvc reservationRentSvc;

	 @Resource(name = "reservationIdGnrService")
	 private EgovIdGnrService reservationIdgenService;

	 @Resource(name = "reservationUserIdGnrService")
	 private EgovIdGnrService reservationUserIdGnrService;

	@Resource(name = "BizExcelService")
	private BizExcelSvc bizExcelService;
	
	@Autowired(required=true)
	private MappingJackson2JsonView jsonView;

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/boffice/ex/reservationRent/reservationRentList.do")
	public String reservationRentPag(
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

		reservationIndexVo.setRiType("R");
		
		//게시물 권한 체크 - 최고관리자가 아니면 각부서에 해당하는 게시물만 보인다.
		HttpSession ses = request.getSession();
		String PermCd = (String)ses.getAttribute("SesUserPermCd");
		if(!PermCd.equals("05010000")){
			String riManageDept = (String)ses.getAttribute("SesUserDeptCd");
			reservationIndexVo.setRiManageDept(riManageDept);
		}

		Map<String, Object> map = reservationRentSvc.retrievePagReservationRent(reservationIndexVo);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));

		paginationInfo.setTotalRecordCount(totCnt);
		if(model != null){
			model.addAttribute("resultList", map.get("resultList"));
			model.addAttribute("resultCnt", map.get("resultCnt"));
			model.addAttribute("paginationInfo", paginationInfo);
		}

		return "injeinc/boffice/ex/reservationRent/reservation_rent_list";
	}

	@RequestMapping("/boffice/ex/reservationRent/reservationRentForm.do")
	public String reservationRentForm(
			@ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo
			, ModelMap model
			) throws Exception {

		HashMap<String, String> param = new HashMap<String, String>();

		ReservationIndexVo result = reservationRentSvc.retrieveReservationRent(reservationIndexVo);

		if(result != null) {
			result.setActionkey("modify");
			result.setPageIndex(reservationIndexVo.getPageIndex());
			result.setSearchCondition(reservationIndexVo.getSearchCondition());
			result.setSearchKeyword(reservationIndexVo.getSearchKeyword());
			if(model != null){
				model.addAttribute("ReservationAddItemList", reservationRentSvc.retrieveListReservationRentAddItem(reservationIndexVo));
				model.addAttribute("ReservationIndexVo", result);
			}
		}else{
			reservationIndexVo.setActionkey("regist");
		}

		HashMap<String, Object> serviceMap = codeSvc.retrieveDeptCodeAll(param);
		List<CodeVo> rowDataList = (List<CodeVo>) serviceMap.get("rowDataList");

		//관리부서목록
		List deptCode = reservationRentSvc.retrieveDeptCode();
		
		if(model != null){
			model.addAttribute("deptList", rowDataList);
			model.addAttribute("dept", deptCode);
		}

		return "injeinc/boffice/ex/reservationRent/reservation_rent_form";
	}

	@RequestMapping("/boffice/ex/reservationRent/reservationRentReg.do")
	public String reservationRentReg(
			final MultipartHttpServletRequest multiRequest
			, @ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo
			, ModelMap model
			) throws Exception {

		ReservationAddItemVo reservationAddItemVo;

		String riIdx = reservationIdgenService.getNextStringId();
		reservationIndexVo.setRiIdx(riIdx);
		reservationIndexVo.setRiType("R");

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
				
				reservationRentSvc.registReservationRentAddItem(reservationAddItemVo);
			}
		}

		reservationIndexVo.setRiDayWeek(reservationIndexVo.getRiDayWeek().replaceAll(",", "||"));

		// 파일업로드
		final Map<String, MultipartFile> files = multiRequest == null ? null : multiRequest.getFileMap();
		ReservationIndexVo uploadVO = new ReservationIndexVo();
    	uploadVO = reservationRentSvc.uploadFile(files, reservationIndexVo);

    	reservationIndexVo.setRiImageFileId(uploadVO.getRiImageFileId());

    	reservationIndexVo.setRiOffLotUse(reservationIndexVo.getRiOffLotUse().replaceAll(",", "||"));
		reservationIndexVo.setRiConfirmYn("Y");
		reservationIndexVo.setRiUserloginType("A");

		reservationRentSvc.registReservationRent(reservationIndexVo);

		String SVC_MSGE = Message.getMessage("100.message");
		return alert("/boffice/ex/reservationRent/reservationRentList.do", SVC_MSGE, model);
	}

	@RequestMapping("/boffice/ex/reservationRent/reservationRentMod.do")
	public String reservationRentMod(
			final MultipartHttpServletRequest multiRequest
			, @ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo
			, ModelMap model
			) throws Exception {

		ReservationAddItemVo reservationAddItemVo;

		String userid = (String) WebUtils.getSessionAttribute(multiRequest, "SesUserId");
		reservationIndexVo.setModId(userid);
		String raContent[] = null;
		if(multiRequest != null){
			raContent = multiRequest.getParameterValues("raContent");
		}
		reservationRentSvc.removeReservationRentAddItem(reservationIndexVo);
		if(raContent != null){
			
			for(int i = 0; i < raContent.length; i++){
				reservationAddItemVo = new ReservationAddItemVo();
				
				reservationAddItemVo.setRiIdx(reservationIndexVo.getRiIdx());
				reservationAddItemVo.setRaIdx(i+1);
				reservationAddItemVo.setRaContent(raContent[i]);
				reservationAddItemVo.setRegId(userid);
				
				reservationRentSvc.registReservationRentAddItem(reservationAddItemVo);
			}
		}

		reservationIndexVo.setRiDayWeek(reservationIndexVo.getRiDayWeek().replaceAll(",", "||"));

		// 파일업로드
		final Map<String, MultipartFile> files = multiRequest == null ? null : multiRequest.getFileMap();
		ReservationIndexVo uploadVO = new ReservationIndexVo();
    	uploadVO = reservationRentSvc.uploadFile(files, reservationIndexVo);

    	reservationIndexVo.setRiImageFileId(uploadVO.getRiImageFileId());

		reservationRentSvc.modifyReservationRent(reservationIndexVo);

		StringBuffer addParam = new StringBuffer();
		addParam.append("?pageIndex=").append(reservationIndexVo.getPageIndex());
		addParam.append("&searchCondition=").append(reservationIndexVo.getSearchCondition());
		addParam.append("&searchKeyword=").append(reservationIndexVo.getSearchKeyword());

		String SVC_MSGE = Message.getMessage("401.message");
		return alert("/boffice/ex/reservationRent/reservationRentList.do"+addParam.toString(), SVC_MSGE, model);
	}

	@RequestMapping("/boffice/ex/reservationRent/reservationRentRmv.do")
	public String reservationRentRmv(
			HttpServletRequest request
			, @ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo
			, ModelMap model
			) throws Exception {

		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		reservationIndexVo.setModId(userid);

		reservationRentSvc.removeReservationRent(reservationIndexVo);

		StringBuffer addParam = new StringBuffer();
		addParam.append("?pageIndex=").append(reservationIndexVo.getPageIndex());
		addParam.append("&searchCondition=").append(reservationIndexVo.getSearchCondition());
		addParam.append("&searchKeyword=").append(reservationIndexVo.getSearchKeyword());

		String SVC_MSGE = Message.getMessage("501.message");
		return alert("/boffice/ex/reservationRent/reservationRentList.do"+addParam.toString(), SVC_MSGE, model);
	}

	@RequestMapping("/boffice/ex/reservationRent/reservationRentView.do")
	public String reservationRentView(
			HttpServletRequest request
			, @ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo
			, @ModelAttribute("ReservationRentVo") ReservationRentVo reservationRentVo
			, ModelMap model
			) throws Exception {


		ReservationIndexVo result = reservationRentSvc.retrieveReservationRent(reservationIndexVo);

		Calendar cal = Calendar.getInstance();

		if(reservationRentVo.getYear()==null || reservationRentVo.getYear().equals("")){
			reservationRentVo.setYear(Integer.toString(cal.get(Calendar.YEAR)));
		}
		if(reservationRentVo.getMonth()==null || reservationRentVo.getMonth().equals("")){
			reservationRentVo.setMonth(Integer.toString(cal.get(Calendar.MONTH)+1));
		}
		int iYear  = Integer.parseInt(reservationRentVo.getYear());
		int iMonth = Integer.parseInt(reservationRentVo.getMonth());

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
		reservationRentVo.setYear(Integer.toString(iYear));
		reservationRentVo.setMonth(Integer.toString(iMonth));

		cal.set(iYear,iMonth-1,1);

		reservationRentVo.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		reservationRentVo.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));

		List CalInfoList = reservationRentSvc.retrieveListReservationRentCal(reservationRentVo);

		result.setPageIndex(reservationIndexVo.getPageIndex());
		result.setSearchCondition(reservationIndexVo.getSearchCondition());
		result.setSearchKeyword(reservationIndexVo.getSearchKeyword());

		model.addAttribute("ReservationIndexVo", result);
		model.addAttribute("CalInfoList", CalInfoList);

		return "injeinc/boffice/ex/reservationRent/reservation_rent_view";
	}
	
	@RequestMapping("/boffice/ex/reservationRent/reservationRentUserInfoConfirm.do")
	public void reservationRentUserInfoConfirm(
			HttpServletRequest request
			,HttpServletResponse response
			, @ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo			
			) throws Exception {

		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		reservationUserInfoVo.setModId(userid);		
		reservationRentSvc.modReservationRentUserConfirm(reservationUserInfoVo);

		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", true);
		jsonMap.put("message", "승인되었습니다");
		jsonView.render(jsonMap, request, response);
	}
	
	
	@RequestMapping("/boffice/ex/reservationRent/reservationRentUserFormView.do")
	public String reservationRentUserFormView(
			HttpServletRequest request
			, @ModelAttribute("ReservationRentVo") ReservationRentVo reservationRentVo
			, ModelMap model
			) throws Exception {

		ReservationIndexVo reservationIndexVo = new ReservationIndexVo();
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(reservationRentVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(reservationRentVo.getPageUnit());
		paginationInfo.setPageSize(reservationRentVo.getPageSize());

		reservationRentVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		reservationRentVo.setLastIndex(paginationInfo.getLastRecordIndex());
		reservationRentVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		reservationIndexVo.setRiIdx(reservationRentVo.getRiIdx());

		String ruReservationDay = reservationRentVo.getYear();
		if(reservationRentVo.getMonth().length() >1){
			ruReservationDay += "-"+reservationRentVo.getMonth();
		}else{
			ruReservationDay += "-0"+reservationRentVo.getMonth();
		}
		if(reservationRentVo.getDay().length() >1){
			ruReservationDay += "-"+reservationRentVo.getDay();
		}else{
			ruReservationDay += "-0"+reservationRentVo.getDay();
		}

		reservationRentVo.setRuReservationDay(ruReservationDay);

		if(reservationRentVo.getRaIdx() == null || reservationRentVo.getRaIdx().equals("")){
			reservationRentVo.setRaIdx("1");
		}
		reservationIndexVo = reservationRentSvc.retrieveReservationRent(reservationIndexVo);				
		
		Map<String, Object> map = reservationRentSvc.retrieveListReservationRentUserInfo(reservationRentVo);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		paginationInfo.setTotalRecordCount(totCnt);
		if(model != null){
			model.addAttribute("ReservationAddItemList", reservationRentSvc.retrieveListReservationRentAddItem(reservationIndexVo));
			model.addAttribute("ReservationIndexVo", reservationIndexVo);
			model.addAttribute("ReservationRentVo", reservationRentVo);
			model.addAttribute("resultList", map.get("resultList"));
			model.addAttribute("resultCnt", map.get("resultCnt"));
			model.addAttribute("paginationInfo", paginationInfo);
			
		}

		return "injeinc/boffice/ex/reservationRent/reservation_rent_user_form_view";
	}

	@RequestMapping("/boffice/ex/reservationRent/reservationRentUserForm.do")
	public String reservationRentUserForm(
			HttpServletRequest request
			, @ModelAttribute("ReservationRentVo") ReservationRentVo reservationRentVo
			, @ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo
			, ModelMap model
			) throws Exception {

		ReservationIndexVo reservationIndexVo = new ReservationIndexVo();

		reservationIndexVo.setRiIdx(reservationUserInfoVo.getRiIdx());
		String ruReservationDay = "";
		if(reservationRentVo != null){
			ruReservationDay = reservationRentVo.getYear();
		}
		if(reservationRentVo != null){
			if(reservationRentVo.getMonth().length() >1){
				ruReservationDay += "-"+reservationRentVo.getMonth();
			}else{
				ruReservationDay += "-0"+reservationRentVo.getMonth();
			}
			if(reservationRentVo.getDay().length() >1){
				ruReservationDay += "-"+reservationRentVo.getDay();
			}else{
				ruReservationDay += "-0"+reservationRentVo.getDay();
			}
		}
		reservationRentVo.setRuReservationDay(ruReservationDay);

		reservationIndexVo = reservationRentSvc.retrieveReservationRent(reservationIndexVo);
		ReservationUserInfoVo result = reservationRentSvc.retrieveReservationRentUserInfo(reservationUserInfoVo);

		if(result != null) {
			reservationIndexVo.setActionkey("modify");
			if(model != null){
				model.addAttribute("ReservationUserInfoVo", result);
				reservationIndexVo.setRuReservationDay(result.getRuReservationDay());
			}
		}else{
			reservationIndexVo.setActionkey("regist");
		}

		reservationIndexVo.setRuIdx(reservationUserInfoVo.getRuIdx());
		
		reservationUserInfoVo.setRaIdx(reservationRentVo.getRaIdx());
		reservationUserInfoVo.setRuReservationDay(ruReservationDay);
		if(model != null){
			model.addAttribute("ReservationIndexVo", reservationIndexVo);		
			model.addAttribute("ReservationAddItemList", reservationRentSvc.retrieveListReservationRentAddItem(reservationIndexVo));
		}

		return "injeinc/boffice/ex/reservationRent/reservation_rent_user_form";
	}

	@RequestMapping("/boffice/ex/reservationRent/reservationRentUserInfoReg.do")
	public String reservationUserInfoReg(
			HttpServletRequest request
			, @ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo
			, @ModelAttribute("ReservationUserAnswerVo") ReservationUserAnswerVo reservationUserAnswerVo
			, ModelMap model
			) throws Exception {

		ReservationIndexVo reservationIndexVo = new ReservationIndexVo();
		String riIdx = "";

		if(reservationRentSvc.retrieveReservationRentUserInfoDupCnt(reservationUserInfoVo) > 0){
			return alert("/boffice/ex/reservationRent/reservationRentUserFormView.do?riIdx="+reservationUserInfoVo.getRiIdx(), "이미 예약을 하셨습니다.", model);
		}else{
			riIdx = reservationUserInfoVo.getRiIdx();
			reservationIndexVo.setRiIdx(riIdx);
			String ruIdx = reservationUserIdGnrService.getNextStringId();
			reservationUserInfoVo.setRuIdx(ruIdx);



			String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
			reservationUserInfoVo.setRegId(userid);

			ReservationIndexVo result = reservationRentSvc.retrieveReservationRent(reservationIndexVo);

			if(result.getRiLotUse().equals("OO")){
				reservationUserInfoVo.setRuLotResult("S");
			}else{
				reservationUserInfoVo.setRuLotResult("R");
			}

			reservationRentSvc.registReservationRentUserInfo(reservationUserInfoVo);
		}

		String SVC_MSGE = Message.getMessage("100.message");
		return alert("/boffice/ex/reservationRent/reservationRentUserFormView.do?riIdx="+riIdx, SVC_MSGE, model);
	}

	@RequestMapping("/boffice/ex/reservationRent/reservationRentUserInfoMod.do")
	public String reservationUserInfoMod(
			HttpServletRequest request
			, @ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo
			, ModelMap model
			) throws Exception {

		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		reservationUserInfoVo.setModId(userid);

		reservationRentSvc.modifyReservationRentUserInfo(reservationUserInfoVo);

		String SVC_MSGE = Message.getMessage("100.message");
		return alert("/boffice/ex/reservationRent/reservationRentView.do?riIdx="+reservationUserInfoVo.getRiIdx(), SVC_MSGE, model);
	}

	/* 신청자 추첨 */
	@RequestMapping("/boffice/ex/reservationRent/reservationRentLot.do")
	public String reservationEventLot(
			HttpServletRequest request
			, @ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo
			, @ModelAttribute("ReservationRentVo") ReservationRentVo reservationRentVo
			, ModelMap model
			, String[] ruIdx
			) throws Exception {

		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		reservationIndexVo.setModId(userid);
		reservationRentVo.setRaIdx(EgovStringUtil.nullConvert(request.getParameter("facType")));

		int r = (int) (Math.random() * ruIdx.length);

		reservationIndexVo.setRuIdx(ruIdx[r]);

		reservationRentSvc.updateReservationRentLot(reservationIndexVo);

		return alert("/boffice/ex/reservationRent/reservationRentUserFormView.do?riIdx="+reservationIndexVo.getRiIdx()+"&year="+reservationRentVo.getYear()+"&month="+reservationRentVo.getMonth()+"&day="+reservationRentVo.getDay()+"&raIdx="+reservationRentVo.getRaIdx(), "추첨완료", model);
	}

	/* 신청자 삭제 */
	@RequestMapping("/boffice/ex/reservationRent/reservationRentUserInfoRmv.do")
	public String reservationEventUserInfoRmv(
			HttpServletRequest request
			, @ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo
			, ModelMap model
			) throws Exception {

		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		reservationUserInfoVo.setModId(userid);

		reservationRentSvc.removeReservationRentUserInfo(reservationUserInfoVo);

		return alert("/boffice/ex/reservationRent/reservationRentUserFormView.do?riIdx="+reservationUserInfoVo.getRiIdx(), "접수자 삭제완료", model);
	}
	
	/* 신청자 삭제 */
	@RequestMapping("/boffice/ex/reservationRent/sendSmsFormPop.do")
	public String sendSmsForm(
			HttpServletRequest request			
			, ModelMap model
			) throws Exception {

		String ruHp = (String)request.getParameter("ruHp");
		
		model.addAttribute("ruHp", ruHp);
		return "injeinc/boffice/ex/reservationRent/reservation_sms_send";
	}
	
	@RequestMapping("/boffice/ex/reservationRent/sendSms.do")
	public void sendSms(
			HttpServletRequest request	
			,HttpServletResponse response
			, ModelMap model
			) throws Exception {

		String ruHp = (String)request.getParameter("ruHp");
		String callback = (String)request.getParameter("callback");
		String ruMessage = (String)request.getParameter("ruMessage");
		
		UmsUtil sms = new UmsUtil();
		UmsVo smsvo = new UmsVo();
		smsvo.setSent(ruHp);
		smsvo.setCallbackNo(callback);
		smsvo.setContent(ruMessage);
		int resultint = sms.send(smsvo);
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		if(resultint > 0){
			jsonMap.put("result", true);
			jsonMap.put("message", "발송되었습니다");
		}else{
			jsonMap.put("result", false);
			jsonMap.put("message", "발송실패하였습니다.");
		}
		
		jsonView.render(jsonMap, request, response);
	}
	
	@RequestMapping("/boffice/ex/reservationRent/reservationRentExcel_bak.do")
	public void webzineExcel(
			@ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo
			, @ModelAttribute("ReservationRentVo") ReservationRentVo reservationRentVo
			, ModelMap model
			,HttpServletRequest request
			,HttpServletResponse response
			) throws Exception {
		String ruReservationDay = reservationRentVo.getYear();
		if(reservationRentVo.getMonth().length() >1){
			ruReservationDay += "-"+reservationRentVo.getMonth();
		}else{
			ruReservationDay += "-0"+reservationRentVo.getMonth();
		}
		if(reservationRentVo.getDay().length() >1){
			ruReservationDay += "-"+reservationRentVo.getDay();
		}else{
			ruReservationDay += "-0"+reservationRentVo.getDay();
		}

		reservationRentVo.setRuReservationDay(ruReservationDay);

		List<ReservationUserInfoVo> result = bizExcelService.selectList("ReservationRentDao.selectListReservationRentUserInfo", reservationRentVo);


		String createString = "reservationRent_" + EgovStringUtil.getTimeStamp() +".xls";

		String storePathString = Message.getMessage("reservationRent.file.upload.path");
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
	
	@RequestMapping("/boffice/ex/reservationRent/reservationRentExcel.do")
	public String reservationRentExcel(
			@ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo
			, @ModelAttribute("ReservationRentVo") ReservationRentVo reservationRentVo
			, ModelMap model
			,HttpServletRequest request
			,HttpServletResponse response
			) throws Exception {
		String ruReservationDay = reservationRentVo.getYear();
		if(reservationRentVo.getMonth().length() >1){
			ruReservationDay += "-"+reservationRentVo.getMonth();
		}else{
			ruReservationDay += "-0"+reservationRentVo.getMonth();
		}
		if(reservationRentVo.getDay().length() >1){
			ruReservationDay += "-"+reservationRentVo.getDay();
		}else{
			ruReservationDay += "-0"+reservationRentVo.getDay();
		}

		reservationRentVo.setRuReservationDay(ruReservationDay);

		List<ReservationUserInfoVo> resultList = bizExcelService.selectList("ReservationRentDao.selectListReservationRentUserInfo", reservationRentVo);
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

		return "injeinc/boffice/ex/reservationRent/reservation_rent_excel";
	}
}
