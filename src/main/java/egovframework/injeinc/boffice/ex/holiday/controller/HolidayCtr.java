package egovframework.injeinc.boffice.ex.holiday.controller;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.Message;
import egovframework.injeinc.boffice.ex.holiday.service.HolidaySvc;
import egovframework.injeinc.boffice.ex.holiday.vo.HolidayVo;
import egovframework.injeinc.boffice.sy.code.service.CmmCodeSvc;
import egovframework.injeinc.common.util.EgovStringUtil;

@Controller
public class HolidayCtr extends CmmLogCtr {
	
	@Resource(name = "HolidaySvc")
	private HolidaySvc holidaySvc;
	
	@Resource(name = "CmmCodeSvc")
	private CmmCodeSvc cmmCodeSvc;
	
	@Autowired(required=true)
	private MappingJackson2JsonView jsonView;
	
	@RequestMapping("/boffice/ex/holiday/HolidayFormAx.do")
	public void HolidayFormAx(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("HolidayVo") HolidayVo holidayVo, ModelMap model) throws Exception {

			HashMap<String, Object> jsonMap = new HashMap<String, Object>();
			
			HolidayVo resultVo = holidaySvc.retrieveHoliday(holidayVo);
			
	
	
			if(resultVo != null) {
				resultVo.setActionkey("modify");
				jsonMap.put("resultVo", resultVo);
			}else{
				holidayVo.setActionkey("regist");
				holidayVo.setHolDate("");
				holidayVo.setHolName("");
				holidayVo.setHolCode("");
				jsonMap.put("resultVo", holidayVo);
			}
			
			jsonMap.put("result", true);
			jsonMap.put("message", "성공");
			
			jsonView.render(jsonMap, request, response);
			
	}
	
	@RequestMapping("/boffice/ex/holiday/HolidayRegAx.do")
	public void HolidayRegAx(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("HolidayVo") HolidayVo holidayVo, ModelMap model) throws Exception {

			String holDate = holidayVo.getHolDate();
			String holName = holidayVo.getHolName();
			String holCode = holidayVo.getHolCode();
			
			boolean result = false;
			String message = "";
				
			if(!EgovStringUtil.isEmpty(holDate) && !EgovStringUtil.isEmpty(holName)) {
				

				int count = holidaySvc.retrieveHolidayCnt(holidayVo);
				
				if(count > 0) {
					message = "이미 등록된 날짜입니다.";
				}else{
					holidaySvc.registHoliday(holidayVo);
					String SVC_MSGE = Message.getMessage("100.message");
					message = SVC_MSGE;
					result = true;
				}
				
			}else{
				message = "필요한 정보가 없습니다.";
			}
		
			HashMap<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("result", result);
			jsonMap.put("message", message);
			jsonView.render(jsonMap, request, response);
		
	}
	
	@RequestMapping("/boffice/ex/holiday/HolidayModAx.do")
	public void HolidayModAx(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("HolidayVo") HolidayVo holidayVo, ModelMap model) throws Exception {

			String holDate = holidayVo.getHolDate();
			String holName = holidayVo.getHolName();
			String oldHolDate = holidayVo.getOldHolDate();
			
			boolean result = false;
			String message = "";
			
			if(!EgovStringUtil.isEmpty(holDate) && !EgovStringUtil.isEmpty(holName) && !EgovStringUtil.isEmpty(oldHolDate)) {
				
				HolidayVo oldHolidayVo = new HolidayVo();
				oldHolidayVo.setHolDate(oldHolDate);
				HolidayVo resultVo = holidaySvc.retrieveHoliday(oldHolidayVo);
				
				int count = holidaySvc.retrieveHolidayCnt(holidayVo);
				if(!holDate.equals(oldHolDate) && count > 0) {
					message = "이미 등록된 날짜입니다.";
				}else if(resultVo != null) {
					
					holidaySvc.modifyHoliday(holidayVo);
					String SVC_MSGE = Message.getMessage("401.message");
					message = SVC_MSGE;
					result = true;
					
				}else{
					message = "잘못된 접근입니다.";
				}
				
			}else{
				message = "필요한 정보가 없습니다.";
			}
		
			HashMap<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("result", result);
			jsonMap.put("message", message);
			jsonView.render(jsonMap, request, response);
			
	}
	
	@RequestMapping("/boffice/ex/holiday/HolidayRmvAx.do")
	public void HolidayRmvAx(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("HolidayVo") HolidayVo holidayVo, ModelMap model) throws Exception {

			boolean result = false;
			String message = "";
				
			holidayVo = holidaySvc.retrieveHoliday(holidayVo);
			
			if(holidayVo != null) {
				holidaySvc.removeHoliday(holidayVo);
				message = Message.getMessage("501.message");
				result = true;
			}else{
				message = "잘못된 접근입니다.";
			}
		
			HashMap<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("result", result);
			jsonMap.put("message", message);
			jsonView.render(jsonMap, request, response);
			
	}
	
	@RequestMapping("/boffice/ex/holiday/HolidayList.do")
	public String HolidayList(@ModelAttribute("HolidayVo") HolidayVo holidayVo, ModelMap model) throws Exception {
		model.addAttribute("holiday", cmmCodeSvc.retrieveListCmmCode("holiday"));
		model.addAttribute("yearList", holidaySvc.retrieveHolYearGroup());
		holidayVo.setHolCode(holidayVo.getSearchHolCode());
		model.addAttribute("resultList", holidaySvc.retrieveListHoliday(holidayVo));
		
		return "injeinc/boffice/ex/holiday/holiday_list";
	}
	
}