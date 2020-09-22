package egovframework.injeinc.boffice.sy.prohibit.controller;

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
import org.springframework.web.util.WebUtils;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.Message;
import egovframework.injeinc.boffice.sy.prohibit.service.ProhibitIdSvc;
import egovframework.injeinc.boffice.sy.prohibit.vo.ProhibitIdVo;
import egovframework.injeinc.common.util.EgovStringUtil;

@Controller
public class ProhibitIdCtr extends CmmLogCtr {
	
	@Resource(name = "ProhibitIdSvc")
	private ProhibitIdSvc prohibitIdSvc;
	
	@Autowired(required=true)
	private MappingJackson2JsonView jsonView;
	
	@RequestMapping("/boffice/sy/prohibit/ProhibitIdFormAx.do")
	public void ProhibitIdFormAx(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("ProhibitIdVo") ProhibitIdVo prohibitIdVo, ModelMap model) throws Exception {

		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		
		ProhibitIdVo resultVo = prohibitIdSvc.retrieveProhibitId(prohibitIdVo);
		
		if(resultVo != null) {
			resultVo.setActionkey("modify");
			jsonMap.put("result", resultVo);
		}else{
			prohibitIdVo.setActionkey("regist");
			prohibitIdVo.setPiIdx("");
			prohibitIdVo.setPiId("");
			jsonMap.put("result", prohibitIdVo);
		}
		
		jsonView.render(jsonMap, request, response);
			
	}
	
	@RequestMapping("/boffice/sy/prohibit/ProhibitIdRegAx.do")
	public void ProhibitIdRegAx(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("ProhibitIdVo") ProhibitIdVo prohibitIdVo, ModelMap model) throws Exception {

		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		
		boolean result = false;
		String message = "";
			
		if(!EgovStringUtil.isEmpty(userid)) {

			prohibitIdVo.setRegId(userid);
			prohibitIdVo.setRegIp(request.getRemoteAddr());
			prohibitIdSvc.registProhibitId(prohibitIdVo);
			String SVC_MSGE = Message.getMessage("100.message");
			message = SVC_MSGE;
			result = true;
		}else{
			message = "잘못된 접근입니다.";
		}
	
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", result);
		jsonMap.put("message", message);
		jsonView.render(jsonMap, request, response);
			
	}
	
	@RequestMapping("/boffice/sy/prohibit/ProhibitIdModAx.do")
	public void ProhibitIdModAx(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("ProhibitIdVo") ProhibitIdVo prohibitIdVo, ModelMap model) throws Exception {

		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		
		boolean result = false;
		String message = "";
			
		if(!EgovStringUtil.isEmpty(userid)) {
			
			ProhibitIdVo resultVo = prohibitIdSvc.retrieveProhibitId(prohibitIdVo);
			
			if(resultVo != null) {

				prohibitIdVo.setModId(userid);
				prohibitIdVo.setModIp(request.getRemoteAddr());
				prohibitIdSvc.modifyProhibitId(prohibitIdVo);
				String SVC_MSGE = Message.getMessage("401.message");
				message = SVC_MSGE;
				result = true;
				
			}else{
				message = "잘못된 접근입니다.";
			}
		}else{
			message = "잘못된 접근입니다.";
		}
	
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", result);
		jsonMap.put("message", message);
		jsonView.render(jsonMap, request, response);
		
	}
	
	@RequestMapping("/boffice/sy/prohibit/ProhibitIdRmvAx.do")
	public void ProhibitIdRmvAx(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("ProhibitIdVo") ProhibitIdVo prohibitIdVo, ModelMap model) throws Exception {

		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		
		boolean result = false;
		String message = "";
			
		if(!EgovStringUtil.isEmpty(userid)) {
			
			prohibitIdVo = prohibitIdSvc.retrieveProhibitId(prohibitIdVo);
			
			if(prohibitIdVo != null) {

				prohibitIdVo.setModId(userid);
				prohibitIdVo.setModIp(request.getRemoteAddr());
				prohibitIdSvc.removeProhibitId(prohibitIdVo);
				message = Message.getMessage("501.message");
				result = true;
			}else{
				message = "잘못된 접근입니다.";
			}
			
		}else{
			message = "잘못된 접근입니다.";
		}
	
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", result);
		jsonMap.put("message", message);
		jsonView.render(jsonMap, request, response);
			
	}
	
	@RequestMapping("/boffice/sy/prohibit/ProhibitIdList.do")
	public String ProhibitIdList(@ModelAttribute("ProhibitIdVo") ProhibitIdVo prohibitIdVo, ModelMap model) throws Exception {
		
		model.addAttribute("resultList", prohibitIdSvc.retrieveListProhibitId(prohibitIdVo));
		return "injeinc/boffice/sy/prohibit/prohibit_id_list";
	}
}