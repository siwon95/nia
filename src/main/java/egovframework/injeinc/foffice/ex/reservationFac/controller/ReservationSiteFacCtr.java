package egovframework.injeinc.foffice.ex.reservationFac.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import egovframework.cmm.Message;
import egovframework.injeinc.boffice.ex.reservation.service.ReservationEventSvc;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationAddItemVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationIndexVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationUserInfoVo;
import egovframework.injeinc.boffice.ex.reservationEtc.service.ReservationEtcSvc;
import egovframework.injeinc.boffice.ex.reservationFac.service.ReservationFacSvc;
import egovframework.injeinc.boffice.ex.reservationFac.vo.ReservationFacTimeVo;
import egovframework.injeinc.boffice.ex.reservationFac.vo.ReservationFacVo;
import egovframework.injeinc.common.code.service.CodeSvc;
import egovframework.injeinc.common.code.vo.CodeVo;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class ReservationSiteFacCtr extends CmmLogCtr{

	@Resource(name = "CodeSvc")
	private CodeSvc codeSvc;

	@Resource(name = "ReservationFacSvc")
	private ReservationFacSvc reservationFacSvc;

	@Resource(name = "ReservationEventSvc")
	private ReservationEventSvc reservationEventSvc;
	
	@Resource(name = "ReservationEtcSvc")
	 private ReservationEtcSvc reservationEtcSvc;

	@Resource(name = "reservationIdGnrService")
	private EgovIdGnrService reservationIdgenService;

	@Resource(name = "reservationUserIdGnrService")
	private EgovIdGnrService reservationUserIdGnrService;

	@Autowired(required=true)
    private MappingJackson2JsonView jsonView;

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/{strSitePath}/{strDomain}/ex/reservationFac/List.do")
	public String reservationFacSitePag(
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

		reservationIndexVo.setRiType("F");
		reservationIndexVo.setRiConfirmYn("Y");

		List strList = new ArrayList();
		String strList2[] =reservationIndexVo.getSearchKeywordTo().split(",");

		if(reservationIndexVo.getSearchKeywordTo() != null && !reservationIndexVo.getSearchKeywordTo().equals("")){
			for(int i = 0; i < strList2.length; i++ ){
				strList.add(strList2[i]);
			}
		}

		reservationIndexVo.setStrList(strList);

		Map<String, Object> map = reservationFacSvc.retrievePagReservationFac(reservationIndexVo);
		
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		
		paginationInfo.setTotalRecordCount(totCnt);
		
		List orglist = reservationEtcSvc.retrieveListSuervisionOrgF();
		
		model.addAttribute("orglist", orglist);		
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);

		return "injeinc/foffice/ex/reservationFac/reservationFac_list";
	}

	@RequestMapping("/site/{strDomain}/foffice/ex/reservationFac/re00104.do")
	public String reservationFacSiteForm(
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
		List deptCode = reservationFacSvc.retrieveDeptCode();
		model.addAttribute("dept", deptCode);

		return "injeinc/foffice/ex/RE001/re00104";
	}

	@RequestMapping("/site/{strDomain}/foffice/ex/reservationFac/re00104Reg.do")
	public String reservationFacSiteReg(
			final MultipartHttpServletRequest multiRequest
			, @ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo
			, ModelMap model
			) throws Exception {
		if(multiRequest == null){
			return "injeinc/common/code500";
		}
		String riIdx = reservationIdgenService.getNextStringId();
		reservationIndexVo.setRiIdx(riIdx);
		reservationIndexVo.setRiType("F");

		HttpSession ses = multiRequest.getSession();
		String userid = (String)ses.getAttribute("ss_id");

		reservationIndexVo.setRegId(userid);

		// 파일업로드
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		ReservationIndexVo uploadVO = new ReservationIndexVo();
    	uploadVO = reservationFacSvc.uploadFile(files, reservationIndexVo);

    	reservationIndexVo.setRiImageFileId(uploadVO.getRiImageFileId());

    	reservationIndexVo.setRiOffLotUse(reservationIndexVo.getRiOffLotUse().replaceAll(",", "||"));
		reservationIndexVo.setRiConfirmYn("N");
		reservationIndexVo.setRiUserloginType("U");

		reservationFacSvc.registReservationFac(reservationIndexVo);

		String SVC_MSGE = Message.getMessage("100.message");
		return alert(multiRequest.getContextPath()+"/site/{strDomain}/ex/reservationFac/re00103.do", SVC_MSGE, model);
	}

	@RequestMapping("/{strSitePath}/{strDomain}/ex/reservationFac/View.do")
	public String reservationFacSiteView(
			HttpServletRequest request
			, @ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo
			, @ModelAttribute("ReservationFacVo") ReservationFacVo reservationFacVo
			, String tab
			, ModelMap model
			) throws Exception {

		if(model == null){
			return "injeinc/common/code500";
		}
		ReservationIndexVo result = reservationFacSvc.retrieveReservationFacView(reservationIndexVo);
		reservationEventSvc.modifyReservationEventReadCnt(reservationIndexVo);

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
		reservationFacVo.setRiAfterRegsterYn(result.getRiAfterRegsterYn());		
		List CalInfoList = reservationFacSvc.retrieveListReservationFacCalUser(reservationFacVo);
		String addcnt = reservationFacSvc.retrieveReservationFacAddCnt(reservationIndexVo);
		result.setPageIndex(reservationIndexVo.getPageIndex());
		result.setSearchCondition(reservationIndexVo.getSearchCondition());
		result.setSearchKeyword(reservationIndexVo.getSearchKeyword());
		List OtherFacList = reservationFacSvc.retrieveListAnotherFac();

		model.addAttribute("ReservationIndexVo", result);
		model.addAttribute("CalInfoList", CalInfoList);
		model.addAttribute("OtherFacList", OtherFacList);
		model.addAttribute("ReservationAddCnt",addcnt);
		model.addAttribute("tab", tab);

		return "injeinc/foffice/ex/reservationFac/reservationFac_view";
	}
	
	@RequestMapping("/site/{strDomain}/ex/reservationFac/libsearchform.do")
	public String libTotSearch(
			HttpServletRequest request			
			, ModelMap model
			) throws Exception {

		return "injeinc/foffice/ex/RE001/libsearchform";
	}
	
	

	@RequestMapping("/{strSitePath}/{strDomain}/foffice/ex/reservationFac/userForm.do")
	public String reservationFacSiteUserForm(
			HttpServletRequest request
			, @ModelAttribute("ReservationFacVo") ReservationFacVo reservationFacVo
			, @ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo
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

		ReservationIndexVo reservationIndexVo = new ReservationIndexVo();

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

		reservationIndexVo = reservationFacSvc.retrieveReservationFac(reservationIndexVo);
		if(reservationIndexVo.getRiSupervisionOrg().equals("aysfac")){
			reservationUserInfoVo.setRuReservationName((String)ses.getAttribute("ss_team"));
		}
		ReservationAddItemVo reservationAddItemVo = reservationFacSvc.retrievReservationFacAddItemOne(reservationIndexVo);
		reservationIndexVo.setRuIdx(reservationUserInfoVo.getRuIdx());
		reservationUserInfoVo.setRaIdx(reservationAddItemVo.getRaIdx()+"");
		reservationUserInfoVo.setRuReservationDay(ruReservationDay);
		reservationUserInfoVo.setRtmWeek(reservationFacVo.getTimeweek());
		//System.out.println("reservationIndexVo.getRiLotUse()====================================================>"+reservationIndexVo.getRiLotUse());
		if(reservationIndexVo.getRiLotUse().equals("OL")){
			List item = reservationFacSvc.retrieveItemCountList(reservationUserInfoVo);
			model.addAttribute("item", item);
		}else if(reservationIndexVo.getRiLotUse().equals("OO")){
		}
		
		List<ReservationFacTimeVo>  timeList = reservationFacSvc.retrieveReservationFacUserTimeInfo2(reservationFacVo);

		model.addAttribute("ReservationIndexVo", reservationIndexVo);
		model.addAttribute("timeList", timeList);
		model.addAttribute("ReservationFacVo", reservationFacVo);		

		return "injeinc/foffice/ex/reservationFac/reservationFac_userform";
	}

	@RequestMapping("/site/{strDomain}/foffice/ex/reservationFac/facConfirmAx.do")
	public void reservationFacSiteConfirmAx(
			HttpServletRequest request
			, HttpServletResponse response
			, @ModelAttribute("ReservationFacVo") ReservationFacVo reservationFacVo
			) throws Exception {

			int cnt = reservationFacSvc.retrieveReservationFacConfirmCnt(reservationFacVo);

			HashMap<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("confirmCnt", cnt);

			jsonView.render(jsonMap, request, response);
	}

	@RequestMapping("/site/{strDomain}/ex/reservationFac/re00105FixAx.do")
	public void reservationFacSiteFixAx(
			HttpServletRequest request
			, HttpServletResponse response
			, @ModelAttribute("ReservationFacVo") ReservationFacVo reservationFacVo
			) throws Exception {

			List fixUserList = reservationFacSvc.retrieveListReservationFacFix(reservationFacVo);

			HashMap<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("fixUserList", fixUserList);

			jsonView.render(jsonMap, request, response);
	}

	@RequestMapping("/{strSitePath}/{strDomain}/foffice/ex/reservationFac/userReg.do")
	public String reservationFacSiteUserInfoReg(
			HttpServletRequest request
			, @ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo
			, @ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo
			, @ModelAttribute("ReservationFacVo") ReservationFacVo reservationFacVo
			, ModelMap model
			) throws Exception {

		
		String ruIdx = "";
		String strReturnUrl = "";
		if(reservationFacSvc.retrieveReservationFacUserInfoDupCnt(reservationUserInfoVo) > 0){
			
			return alert(request.getContextPath()+"/reservation/reservation/ex/reservationFac/View.do?riIdx="+reservationIndexVo.getRiIdx(), "기존에 신청한 내역이 있습니다.", model);
		
		}else{
			
			String userid = (String) WebUtils.getSessionAttribute(request, "ss_id");
			reservationUserInfoVo.setRegId(userid);
			
			ReservationIndexVo result = reservationFacSvc.retrieveReservationFacView(reservationIndexVo);			

			if(result.getRiLotUse().equals("OO")){
				reservationUserInfoVo.setRuLotResult("S");
			}else{
				reservationUserInfoVo.setRuLotResult("R");
			}
			
			List<ReservationFacTimeVo>  timeList = reservationFacSvc.retrieveReservationFacUserTimeInfo2(reservationFacVo);
			
			if(result.getRiPayYn().equals("Y")){
				if(result.getRiPay() != null && !result.getRiPay().equals("") && Integer.parseInt(result.getRiPay()) > 0){
					if(result.getRiOnlinePayYn() != null && !result.getRiOnlinePayYn().equals("") && result.getRiOnlinePayYn().equals("Y")){
						reservationUserInfoVo.setPayStatus("1");										
					}
				}
			}
									
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
				ReservationFacTimeVo timeVo = (ReservationFacTimeVo)timeList.get(0);
				if(result.getRiPayYn().equals("Y")){
					int ss = (s)*Integer.parseInt(timeVo.getRtmTerm());
					String temp = (Integer.parseInt(result.getRiPay()) * ss)+"";
					reservationUserInfoVo.setPayPrice(temp);
				}				
				ruIdx = reservationUserIdGnrService.getNextStringId();
				reservationUserInfoVo.setRtmIdx(strrtmidx);		
				reservationUserInfoVo.setRuIdx(ruIdx);
				reservationFacSvc.registReservationFacUserInfo(reservationUserInfoVo);
			}else{
				ReservationFacTimeVo timeVo = null;
				int ss = 0;
				if(result.getRiPayYn().equals("Y")){
					for(int i = 0 ; i < timeList.size(); i++){
						timeVo =  (ReservationFacTimeVo)timeList.get(i);
						if(timeVo.getRtmIdx() == Integer.parseInt(reservationUserInfoVo.getStfacTime())){
							ss = Integer.parseInt(timeVo.getEtTime()) - Integer.parseInt(timeVo.getStTime());
						}
					}
					String temp = (Integer.parseInt(result.getRiPay()) * ss)+"";
					reservationUserInfoVo.setPayPrice(temp);
				}				
				ruIdx = reservationUserIdGnrService.getNextStringId();					
				reservationUserInfoVo.setRuIdx(ruIdx);
				reservationUserInfoVo.setRtmIdx(reservationUserInfoVo.getStfacTime());
				reservationFacSvc.registReservationFacUserInfo(reservationUserInfoVo);
			}
			

			//reservationFacSvc.registReservationFacUserInfo(reservationUserInfoVo);
			if(result.getRiLotUse().equals("OO") && result.getRiPayYn().equals("Y")){
				if(result.getRiPay() != null && !result.getRiPay().equals("") && Integer.parseInt(result.getRiPay()) > 0){
					if(result.getRiOnlinePayYn() != null && !result.getRiOnlinePayYn().equals("") && result.getRiOnlinePayYn().equals("Y")){
						strReturnUrl = "redirect:/reservation/reservation/foffice/ex/reservationFac/Payform.do?riIdx="+reservationUserInfoVo.getRiIdx()+"&ruIdx="+ruIdx;
					}else{
						strReturnUrl = "redirect:/reservation/reservation/foffice/ex/reservationFac/Receipt.do?riIdx="+reservationUserInfoVo.getRiIdx()+"&ruIdx="+ruIdx;
					}
				}else{
					strReturnUrl = "redirect:/reservation/reservation/foffice/ex/reservationFac/Receipt.do?riIdx="+reservationUserInfoVo.getRiIdx()+"&ruIdx="+ruIdx;
				}
			}else{
				strReturnUrl = "redirect:/reservation/reservation/foffice/ex/reservationFac/Receipt.do?riIdx="+reservationUserInfoVo.getRiIdx()+"&ruIdx="+ruIdx;
			}
		}				
		return strReturnUrl;
	}
	
	@RequestMapping("/{strSitePath}/{strDomain}/foffice/ex/reservationFac/Payform.do")
	public String reservationSiteFacPay(
			HttpServletRequest request
			, @ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo
			, @ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo
			, ModelMap model
			) throws Exception {
		if(model == null){
			return "injeinc/common/code500";
		}
		reservationIndexVo = reservationFacSvc.retrieveReservationFac(reservationIndexVo);
		ReservationUserInfoVo result = reservationFacSvc.retrieveReservationFacUserInfo(reservationUserInfoVo);
		
		reservationIndexVo.setRuIdx(reservationUserInfoVo.getRuIdx());

		model.addAttribute("ReservationUserInfoVo", result);
		model.addAttribute("ReservationIndexVo", reservationIndexVo);

		return "injeinc/foffice/ex/reservationFac/reservationFac_payform";
	}
	
	@RequestMapping("/{strSitePath}/{strDomain}/foffice/ex/reservationFac/PayRes.do")
	public String reservationSiteFacPayRes(
			HttpServletRequest request
			, @ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo
			, @ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo
			, ModelMap model
			) throws Exception {
		if(model == null){
			return "injeinc/common/code500";
		}
		reservationIndexVo = reservationFacSvc.retrieveReservationFac(reservationIndexVo);
		ReservationUserInfoVo result = reservationFacSvc.retrieveReservationFacUserInfo(reservationUserInfoVo);
		ReservationUserInfoVo returnVo = (ReservationUserInfoVo)reservationEventSvc.retrievePayResult(reservationUserInfoVo,request);
		
		reservationIndexVo.setRuIdx(reservationUserInfoVo.getRuIdx());

		model.addAttribute("ReservationUserInfoVo", result);
		model.addAttribute("PayInfoVo",returnVo);
		model.addAttribute("ReservationItemList", reservationEventSvc.retrieveListReservationEventItem(reservationIndexVo));
		model.addAttribute("ReservationIndexVo", reservationIndexVo);

		return "redirect:/reservation/reservation/foffice/ex/reservationFac/Receipt.do?riIdx="+reservationUserInfoVo.getRiIdx()+"&ruIdx="+reservationUserInfoVo.getRuIdx();
	}

	@RequestMapping("/{strSitePath}/{strDomain}/foffice/ex/reservationFac/Receipt.do")
	public String reservationSiteFacComp(
			HttpServletRequest request
			, @ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo
			, @ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo
			, ModelMap model
			) throws Exception {
		if(model == null){
			return "injeinc/common/code500";
		}
		reservationIndexVo = reservationFacSvc.retrieveReservationFac(reservationIndexVo);
		ReservationUserInfoVo result = reservationFacSvc.retrieveReservationFacUserInfo(reservationUserInfoVo);
		ReservationFacVo reservationFacVo = new ReservationFacVo();
		
		List<ReservationFacTimeVo>  timeList = reservationFacSvc.retrieveReservationFacUserTimeInfo4(reservationUserInfoVo);
		reservationIndexVo.setRuIdx(reservationUserInfoVo.getRuIdx());

		model.addAttribute("ReservationUserInfoVo", result);	
		model.addAttribute("timeList", timeList);
		model.addAttribute("ReservationIndexVo", reservationIndexVo);

		return "injeinc/foffice/ex/reservationFac/reservationFac_receipt";
	}

	@RequestMapping("/site/{strDomain}/ex/reservationFac/re00108.do")
	public String reservationFacSiteComp(
			HttpServletRequest request
			, @ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo
			, @ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo
			, ModelMap model
			) throws Exception {
		if(model == null){
			return "injeinc/common/code500";
		}
		reservationIndexVo = reservationFacSvc.retrieveReservationFac(reservationIndexVo);
		ReservationUserInfoVo result = reservationFacSvc.retrieveReservationFacUserInfo(reservationUserInfoVo);

		reservationIndexVo.setRuIdx(reservationUserInfoVo.getRuIdx());

		model.addAttribute("ReservationUserInfoVo", result);
		model.addAttribute("ReservationAddItemList", reservationFacSvc.retrieveListReservationFacAddItem(reservationIndexVo));
		model.addAttribute("ReservationIndexVo", reservationIndexVo);

		return "injeinc/foffice/ex/RE001/re00108";
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/site/{strDomain}/foffice/ex/reservationFac/ms00108.do")
	public String reservationMyFacSitePag(
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
		paginationInfo.setRecordCountPerPage(reservationIndexVo.getRecordCountPerPage());
		
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

		Map<String, Object> map = reservationFacSvc.retrievePagReservationFac(reservationIndexVo);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));

		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);

		return "injeinc/foffice/ex/MS001/ms00108";
	}

	@RequestMapping("/site/{strDomain}/foffice/ex/reservationFac/ms00109.do")
	public String reservationMyFacSiteView2(
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

		ReservationIndexVo result = reservationFacSvc.retrieveReservationFac(reservationIndexVo);

		result.setPageIndex(reservationIndexVo.getPageIndex());
		result.setSearchCondition(reservationIndexVo.getSearchCondition());
		result.setSearchKeyword(reservationIndexVo.getSearchKeyword());

		model.addAttribute("ReservationIndexVo", result);
		model.addAttribute("ReservationAddItemList", reservationFacSvc.retrieveListReservationFacAddItem(reservationIndexVo));
		//model.addAttribute("ReservationUserList", userList);

		return "injeinc/foffice/ex/MS001/ms00109";
	}
	
	@RequestMapping("/{strSitePath}/{strDomain}/foffice/ex/reservationFacMy/View.do")
	public String reservationMyFacSiteView(
			HttpServletRequest request
			, @ModelAttribute("ReservationIndexVo") ReservationIndexVo reservationIndexVo
			, @ModelAttribute("reservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo
			, ModelMap model
			) throws Exception {
		if(model == null){
			return "injeinc/common/code500";
		}
		HttpSession ses = request.getSession();
		String ss_dupkey = (String)ses.getAttribute("ss_dupkey");

		reservationIndexVo.setRuDupkey(ss_dupkey);

		ReservationIndexVo result = reservationFacSvc.retrieveReservationFac(reservationIndexVo);

		
		
		List<ReservationFacTimeVo>  timeList = reservationFacSvc.retrieveReservationFacUserTimeInfo4(reservationUserInfoVo);

		model.addAttribute("ReservationIndexVo", result);
		model.addAttribute("timeList", timeList);
		model.addAttribute("ReservationAddItemList", reservationFacSvc.retrieveListReservationFacAddItem(reservationIndexVo));
		//model.addAttribute("ReservationUserList", userList);

		return "injeinc/foffice/ex/reservationFac/reservationFac_myview";
	}

	/* 시설예약 접수 취소 */
	@RequestMapping("/site/{strDomain}/foffice/ex/reservationFac/ms00109Rmv.do")
	public String reservationMyFacSiteUserInfoRmv(
			HttpServletRequest request
			, @ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo
			, ModelMap model
			) throws Exception {

		String userid = (String) WebUtils.getSessionAttribute(request, "ss_id");
		reservationUserInfoVo.setModId(userid);

		reservationFacSvc.removeReservationFacUserInfo(reservationUserInfoVo);

		return alert("/site/{strDomain}/foffice/ex/reservationMy/list.do", "시설예약이 취소되었습니다.", model);
	}

}
