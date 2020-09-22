package egovframework.injeinc.foffice.ex.reservationRent.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.util.WebUtils;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.Message;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationIndexVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationUserInfoVo;
import egovframework.injeinc.boffice.ex.reservationRent.service.ReservationRentSvc;
import egovframework.injeinc.boffice.ex.reservationRent.vo.ReservationRentVo;
import egovframework.injeinc.boffice.sy.user.service.UserSvc;
import egovframework.injeinc.boffice.sy.user.vo.UserVo;
import egovframework.injeinc.common.util.EgovStringUtil;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class ReservationSiteRentCtr extends CmmLogCtr{

	 @Resource(name = "ReservationRentSvc")
	 private ReservationRentSvc reservationRentSvc;

	 @Resource(name = "reservationUserIdGnrService")
	 private EgovIdGnrService reservationUserIdGnrService;

	@Resource(name ="UserSvc")
	private UserSvc userSvc;
	
	@Autowired(required=true)
	private MappingJackson2JsonView jsonView;

	@RequestMapping("/{strSitePath}/{strDomain}/ex/reservationRent/RentInfo.do")
	public String reservationRentUserFormView(
			HttpServletRequest request
			, @ModelAttribute("ReservationRentVo") ReservationRentVo reservationRentVo
			, ModelMap model
			) throws Exception {

		ReservationIndexVo reservationIndexVo = new ReservationIndexVo();
		reservationRentVo.setRiIdx("RI000057");
		reservationRentVo.setRaIdx("1");
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
		
		Map<String, Object> map = reservationRentSvc.retrieveListReservationRentFUserInfo(reservationRentVo);
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

		return "injeinc/foffice/ex/reservationRent/reservation_rent_info";
	}
	
	@RequestMapping("/{strSitePath}/{strDomain}/fofiice/ex/reservationRent/RentForm.do")
	public String reservationRentUserForm(
			HttpServletRequest request			
			,HttpServletResponse response
			, ModelMap model
			) throws Exception {

		ReservationIndexVo reservationIndexVo = new ReservationIndexVo();
		
		String ss_dupkey = (String) WebUtils.getSessionAttribute(request, "ss_dupkey");		
		
		if(EgovStringUtil.isEmpty(ss_dupkey)) {
			String reUrl = request.getRequestURI();			
			return "redirect:/site/{strDomain}/login/Login_Session_Empty.do?reUrl="+reUrl;
		}
		
		reservationIndexVo.setRiIdx("RI000057");
		reservationIndexVo = reservationRentSvc.retrieveReservationRent(reservationIndexVo);
		if((reservationIndexVo.getRiRentCnt() - reservationIndexVo.getRiAccCnt()) == 0){
			String SVC_MSGE = "현재 대여가능한 수량이 없어 신청이 불가능합니다.";
			return alert("/health/health/ex/reservationRent/RentInfo.do", SVC_MSGE, model);
		}
		
		UserVo view = userSvc.retrieveUser(Integer.valueOf((String) WebUtils.getSessionAttribute(request, "ss_idx")));
		if(view.getNumGubun() != null && view.getNumGubun().equals("M")){
			if(view.getCoNum() != null && !view.getCoNum().equals("") && !view.getCoNum().equals("--")){
				String[] telNum = view.getCoNum().split("-");
				view.setTelNum1(telNum[0]);
				view.setTelNum2(telNum[1]);
				view.setTelNum3(telNum[2]);
			}
		}
		model.addAttribute("ReservationIndexVo", reservationIndexVo);
		model.addAttribute("UserVo", view);
		
		return "injeinc/foffice/ex/reservationRent/reservation_rent_user_form";
	}
	
	@RequestMapping("/{strSitePath}/{strDomain}/fofiice/ex/reservationRent/RentModForm.do")
	public String reservationRentUserModForm(
			HttpServletRequest request			
			,HttpServletResponse response
			, @ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo
			, ModelMap model
			) throws Exception {

		ReservationIndexVo reservationIndexVo = new ReservationIndexVo();
		
		String ss_dupkey = (String) WebUtils.getSessionAttribute(request, "ss_dupkey");		
		
		if(EgovStringUtil.isEmpty(ss_dupkey)) {
			String reUrl = request.getRequestURI();			
			return "redirect:/site/{strDomain}/login/Login_Session_Empty.do?reUrl="+reUrl;
		}
				
		reservationUserInfoVo.setRiIdx("RI000057");
		reservationUserInfoVo.setRaIdx("1");
		ReservationUserInfoVo resultVo = (ReservationUserInfoVo)reservationRentSvc.retrieveReservationRentUserInfo(reservationUserInfoVo);	
		
		if(resultVo.getRuHp() != null && !resultVo.getRuHp().equals("")){
			String[] telNum = resultVo.getRuHp().split("-");
			resultVo.setRuTel1(telNum[0]);
			resultVo.setRuTel2(telNum[1]);
			resultVo.setRuTel3(telNum[2]);
		}
		
		model.addAttribute("ReservationIndexVo", reservationIndexVo);
		model.addAttribute("ReservationUserInfoVo", resultVo);
		
		return "injeinc/foffice/ex/reservationRent/reservation_rent_user_mod_form";
	}
	
	@RequestMapping("/{strSitePath}/{strDomain}/fofiice/ex/reservationRent/UserReg.do")
	public String reservationUserInfoReg(
			HttpServletRequest request
			, @ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo
			, ModelMap model
			) throws Exception {

		ReservationIndexVo reservationIndexVo = new ReservationIndexVo();
		String riIdx = "";
		String ss_dupkey = (String) WebUtils.getSessionAttribute(request, "ss_dupkey");
		reservationUserInfoVo.setRiIdx("RI000057");
		riIdx = reservationUserInfoVo.getRiIdx();
		reservationIndexVo.setRiIdx(riIdx);
		reservationUserInfoVo.setRuDupkey(ss_dupkey);
		String userid = (String) WebUtils.getSessionAttribute(request, "ss_id");
		reservationUserInfoVo.setRaIdx("1");
		reservationUserInfoVo.setRegId(userid);
		if(reservationRentSvc.retrieveReservationRentUserInfoDupCnt(reservationUserInfoVo) > 0){
			return alert("/health/health/ex/reservationRent/RentInfo.do", "이미 예약을 하셨습니다.", model);
		}else{
						
			String ruIdx = reservationUserIdGnrService.getNextStringId();
			reservationUserInfoVo.setRuIdx(ruIdx);			
			reservationUserInfoVo.setRuHp(reservationUserInfoVo.getRuTel1()+"-"+reservationUserInfoVo.getRuTel2()+"-"+reservationUserInfoVo.getRuTel3());
			ReservationIndexVo result = reservationRentSvc.retrieveReservationRent(reservationIndexVo);

			if(result.getRiLotUse().equals("OO")){
				reservationUserInfoVo.setRuLotResult("S");
			}else{
				reservationUserInfoVo.setRuLotResult("R");
			}
			reservationRentSvc.registReservationRentUserInfo(reservationUserInfoVo);
		}

		String SVC_MSGE = Message.getMessage("100.message");
		return alert("/health/health/fofiice/ex/reservationRent/UserList.do?riIdx="+riIdx, SVC_MSGE, model);
	}
	
	@RequestMapping("/{strSitePath}/{strDomain}/fofiice/ex/reservationRent/UserMod.do")
	public String reservationUserInfoMod(
			HttpServletRequest request
			, @ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo
			, ModelMap model
			) throws Exception {

		ReservationIndexVo reservationIndexVo = new ReservationIndexVo();
		String riIdx = "";
		String ss_dupkey = (String) WebUtils.getSessionAttribute(request, "ss_dupkey");
		reservationUserInfoVo.setRiIdx("RI000057");
		riIdx = reservationUserInfoVo.getRiIdx();
		reservationIndexVo.setRiIdx(riIdx);
		reservationUserInfoVo.setRuDupkey(ss_dupkey);
		String userid = (String) WebUtils.getSessionAttribute(request, "ss_id");
		reservationUserInfoVo.setRaIdx("1");
		reservationUserInfoVo.setModId(userid);
					
		reservationUserInfoVo.setRuHp(reservationUserInfoVo.getRuTel1()+"-"+reservationUserInfoVo.getRuTel2()+"-"+reservationUserInfoVo.getRuTel3());
		
		reservationRentSvc.modifyReservationRentUserInfo2(reservationUserInfoVo);
	
		String SVC_MSGE = Message.getMessage("100.message");
		return alert("/health/health/fofiice/ex/reservationRent/UserList.do?riIdx="+riIdx, SVC_MSGE, model);
	}
	
	
	@RequestMapping("/{strSitePath}/{strDomain}/fofiice/ex/reservationRent/UserList.do")
	public String reservationUserList(
			HttpServletRequest request
			, @ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo
			, ModelMap model
			) throws Exception {

		String ss_dupkey = (String) WebUtils.getSessionAttribute(request, "ss_dupkey");
		if(EgovStringUtil.isEmpty(ss_dupkey)) {
			String reUrl = request.getRequestURI();			
			return "redirect:/site/{strDomain}/login/Login_Session_Empty.do?reUrl="+reUrl;
		}
		reservationUserInfoVo.setRiIdx("RI000057");
		reservationUserInfoVo.setRuDupkey(ss_dupkey);
				
		List resultList = reservationRentSvc.retrieveReservationRentUserList(reservationUserInfoVo);
	
		model.addAttribute("resultList", resultList);
				
		return "injeinc/foffice/ex/reservationRent/reservation_rent_user_list";
	}
	
	@RequestMapping("/{strSitePath}/{strDomain}/fofiice/ex/reservationRent/RentCancel.do")
	public void reservationRentUserInfoConfirm(
			HttpServletRequest request
			,HttpServletResponse response
			, @ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo			
			) throws Exception {

		String userid = (String) WebUtils.getSessionAttribute(request, "ss_id");
		reservationUserInfoVo.setModId(userid);		
		reservationRentSvc.removeReservationRentUserInfo(reservationUserInfoVo);

		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", true);
		jsonMap.put("message", "취소되었습니다.");
		jsonView.render(jsonMap, request, response);
	}
}
