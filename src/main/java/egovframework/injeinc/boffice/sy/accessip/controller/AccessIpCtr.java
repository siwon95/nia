package egovframework.injeinc.boffice.sy.accessip.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.util.WebUtils;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.Message;
import egovframework.cmm.service.EgovProperties;
import egovframework.injeinc.boffice.cn.common.Util;
import egovframework.injeinc.boffice.sy.accessip.service.AccessIpSvc;
import egovframework.injeinc.boffice.sy.accessip.vo.AccessIpVo;
import egovframework.injeinc.boffice.sy.prohibit.vo.ProhibitIdVo;
import egovframework.injeinc.common.util.EgovStringUtil;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class AccessIpCtr extends CmmLogCtr {
	
	@Resource(name = "AccessIpSvc")
	private AccessIpSvc accessIpSvc;
	
	@Autowired(required=true)
	private MappingJackson2JsonView jsonView;
	
	/* 접속 IP 목록*/
	@RequestMapping(value = "/boffice/sy/accessIp/accessIpList.do")
	public String accessIpList(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("AccessIpVo") AccessIpVo accessIpVo, ModelMap model) throws Exception {
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(accessIpVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(accessIpVo.getPageUnit());
		paginationInfo.setPageSize(accessIpVo.getPageSize());
		
		accessIpVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		accessIpVo.setLastIndex(paginationInfo.getLastRecordIndex());
		accessIpVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		int totCnt = accessIpSvc.retrievePagAccessIp(accessIpVo);
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("totCnt",totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("resultList", accessIpSvc.retrieveListAccessIp(accessIpVo));
		return "injeinc/boffice/sy/accessIp/accessIpList";
	}
	
	/* 접속 IP 등록/수정 */
	@RequestMapping(value = "/boffice/sy/accessIp/accessIpFormAx.do")
	public void accessIpFormAx(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("AccessIpVo") AccessIpVo accessIpVo, ModelMap model) throws Exception {

		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		
		AccessIpVo resultVo = accessIpSvc.retrieveAccessIp(accessIpVo);
		
		if(resultVo != null) {
			resultVo.setActionkey("modify");
			jsonMap.put("result", resultVo);
		}else{
			accessIpVo.setActionkey("regist");
			accessIpVo.setAi_idx("");
			accessIpVo.setS_ip("");
			accessIpVo.setE_ip("");
			accessIpVo.setNote("");
			jsonMap.put("result", accessIpVo);
		}
		
		jsonView.render(jsonMap, request, response);
			
	}
	
	@RequestMapping(value = "/boffice/sy/accessIp/accessIpRegAx.do")
	public void accessIpRegAx(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("AccessIpVo") AccessIpVo accessIpVo, ModelMap model) throws Exception {

		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		
		boolean result = false;
		String message = "";
			
		if(!EgovStringUtil.isEmpty(userid)) {

			accessIpVo.setReg_id(userid);
			accessIpSvc.registAccessIp(accessIpVo);
			String SVC_MSGE = Message.getMessage("100.message");
			message = SVC_MSGE;
			result = true;
			
			doMakeAccessIpXML(request);
		}else{
			message = "잘못된 접근입니다.";
		}
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", result);
		jsonMap.put("message", message);
		jsonView.render(jsonMap, request, response);
			
	}
	
	@RequestMapping(value = "/boffice/sy/accessIp/accessIpModAx.do")
	public void accessIpModAx(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("AccessIpVo") AccessIpVo accessIpVo, ModelMap model) throws Exception {

		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		
		boolean result = false;
		String message = "";

		if(!EgovStringUtil.isEmpty(userid)) {
			
			AccessIpVo resultVo = accessIpSvc.retrieveAccessIp(accessIpVo);
			
			if(resultVo != null) {

				accessIpVo.setMod_id(userid);
				accessIpSvc.modifyAccessIp(accessIpVo);
				String SVC_MSGE = Message.getMessage("401.message");
				message = SVC_MSGE;
				result = true;
				
				doMakeAccessIpXML(request);
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
	
	@RequestMapping(value = "/boffice/sy/accessIp/accessIpRmvAx.do")
	public void accessIpRmvAx(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("AccessIpVo") AccessIpVo accessIpVo, ModelMap model) throws Exception {

		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		
		boolean result = false;
		String message = "";
			
		if(!EgovStringUtil.isEmpty(userid)) {
			
			accessIpVo = accessIpSvc.retrieveAccessIp(accessIpVo);
			
			if(accessIpVo != null) {

				accessIpVo.setMod_id(userid);
				accessIpSvc.removeAccessIp(accessIpVo);
				message = Message.getMessage("501.message");
				result = true;
				doMakeAccessIpXML(request);
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

	@RequestMapping(value = "/boffice/sy/accessIp/applyAccessIpFilter.do")
	public String applyAccessIpFilter(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("AccessIpVo") AccessIpVo accessIpVo, ModelMap model) throws Exception {
		return "redirect:/boffice/sy/accessIp/accessIpList.do";
	}
	
	
	private void doMakeAccessIpXML(HttpServletRequest request) throws Exception {
		
		String filePath = "/WEB-INF/config/egovframework/accessip/";
		String fileName = "access.xml";
		
		String strContent="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
		strContent+="<accessip>\n";
		strContent+="<allow-ip>0:0:0:0:0:0:0:1</allow-ip>\n";
		
		List<AccessIpVo> list = accessIpSvc.retrieveAllListAccessIp();
		
		for (int i = 0; i < list.size(); i++) {
			AccessIpVo accessIpVo = list.get(i);
			strContent+="<allow-ip>"+accessIpVo.getS_ip()+"</allow-ip>\n";
			
			if(accessIpVo.getE_ip() != null && !"".equals(accessIpVo.getE_ip())){
				String[] sIps = accessIpVo.getS_ip().split("\\.");
				String[] eIps = accessIpVo.getE_ip().split("\\.");
				
				if("*".equals(sIps[0])){
					strContent+="<allow-ip>*</allow-ip>\n";
					continue;
				}

				if("*".equals(sIps[1])){
					strContent+="<allow-ip>"+sIps[0] + ".*</allow-ip>\n";
					continue;
				}

				if("*".equals(sIps[2])){
					strContent+="<allow-ip>"+sIps[0] + "." + sIps[1]+".*</allow-ip>\n";
					continue;
				}
				
				int sIp3 = Integer.parseInt(sIps[3]);
				int eIp3 = Integer.parseInt(eIps[3]);
				
				for(int j = sIp3+1; j <= eIp3; j++){
					
					String ip = sIps[0] + "." + sIps[1] + "." + sIps[2] + "." + String.valueOf(j);
					
					strContent+="<allow-ip>"+ip+"</allow-ip>\n";
				}
				
			}
		}
		
		strContent+="</accessip>\n";
		
		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
		
		Util util = new Util();
		
		String fileChk = util.fileWriteRootPath(request, filePath, fileName, strContent, rootPath);
		
	}
	
	
}