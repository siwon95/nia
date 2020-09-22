package egovframework.injeinc.boffice.sy.code.controller;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.Message;
import egovframework.injeinc.boffice.sy.code.service.SyCodeSvc;
import egovframework.injeinc.boffice.sy.code.vo.SyCodeVo;
import egovframework.injeinc.boffice.sy.code.vo.CmmCodeVo;


/**
 * DeptCtr
 * 2015.06.05 : LDY
 */

@Controller
public class SyCodeCtr extends CmmLogCtr{
	
	@Resource(name = "SyCodeSvc")
	private SyCodeSvc codeSvc;
	
	@Autowired(required=true)
	private MappingJackson2JsonView jsonView;
	
	
	@RequestMapping(value= "/boffice/sy/code/Code_List.do")
	public String CodeList(
			HttpServletRequest request, @ModelAttribute("SyCodeVo") SyCodeVo syCodeVo
			, ModelMap model) throws Exception {
		
		return "injeinc/boffice/sy/code/code_list";		
	}
	
	@RequestMapping(value= "/boffice/sy/code/Code_Save.do")
	public String CodeSave(
			HttpServletRequest request, @ModelAttribute("SyCodeVo") SyCodeVo syCodeVo
			, ModelMap model) throws Exception {
		
		if(request.getParameter("mode")!=null){
			String mode = request.getParameter("mode");
			String returnUrl="";
			if(mode.equals("write")){
				String code = request.getParameter("code");
				String codeUpr = request.getParameter("codeUpr");
				String codeName = request.getParameter("codeName");
				CmmCodeVo cmmCodeVo = new CmmCodeVo();
				cmmCodeVo.setCode(code);
				cmmCodeVo.setCodeUpr(codeUpr);
				cmmCodeVo.setCodeName(codeName);
				
				codeSvc.registCode(cmmCodeVo);
				returnUrl="/boffice/sy/code/Code_List.do?nodeId="+cmmCodeVo.getCodeUpr();
			}else if(mode.equals("modify")){
				CmmCodeVo cmmCodeVo = new CmmCodeVo();
				cmmCodeVo.setCode(syCodeVo.getNodeId());
				cmmCodeVo.setCodeUpr(syCodeVo.getParentId());
				cmmCodeVo.setCodeName(syCodeVo.getText());
				
				codeSvc.modifyCode(cmmCodeVo);
				returnUrl="/boffice/sy/code/Code_List.do?nodeId="+cmmCodeVo.getCode();
			}else if(mode.equals("delete")){
				CmmCodeVo cmmCodeVo = new CmmCodeVo();
				cmmCodeVo.setCode(syCodeVo.getNodeId());
				cmmCodeVo.setCodeUpr(syCodeVo.getParentId());
				cmmCodeVo.setCodeName(syCodeVo.getText());
				
				codeSvc.removeCode(cmmCodeVo);
				returnUrl="/boffice/sy/code/Code_List.do?nodeId="+cmmCodeVo.getCodeUpr();
			}
			
			
			String SVC_MSGE = Message.getMessage("100.message");
			return alert(returnUrl, SVC_MSGE, model);
		}else{
			String returnUrl="/boffice/sy/code/Code_List.do";
			
			String SVC_MSGE = Message.getMessage("901.message");
			return alert(returnUrl, SVC_MSGE, model);
		}
	}
	
	@RequestMapping(value= "/boffice_noDeco/sy/code/Code_reg.do")
	public String CodeReg(
			HttpServletRequest request, @ModelAttribute("CmmCodeVo") CmmCodeVo cmmCodeVo
			, ModelMap model) throws Exception {
		
		codeSvc.modifyCode(cmmCodeVo);

		String returnUrl = request.getParameter("returnUrl");
		
		String SVC_MSGE = Message.getMessage("100.message");
		return alert(returnUrl, SVC_MSGE, model);
	}
	
	/**
	 * tree ajax
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/boffice/sy/code/Code_ListAx.do")
	public void codetreeListAjax(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession ses = request.getSession();
		
		HashMap<String, String> param = new HashMap<String, String>();
		
		HashMap<String, Object> serviceMap = codeSvc.retriveListCode(param);
		
		jsonView.render(serviceMap, request, response);
			
	}
	

	@RequestMapping(value="/boffice/sy/code/Code_FormAx.do",method=RequestMethod.POST)
	@ResponseBody
	public void codetreeFormAx(HttpServletRequest request, HttpServletResponse response,@RequestBody String tree) throws Exception {
		infoLog("TreeFormTest");
		HashMap<String,Object> param = new HashMap<String,Object> ();
			 
		JSONArray arrobj = (JSONArray)JSONValue.parse(tree);
		
		for(int i=0;i<arrobj.size();i++){
			JSONObject jsonobj = (JSONObject) arrobj.get(i);
			param.put("id_"+i, jsonobj.get("id"));
			param.put("parent_"+i, jsonobj.get("parent"));
			param.put("text_"+i, jsonobj.get("text"));
			param.put("position_"+i, i);
			param.put("useryn_"+i, jsonobj.get("useryn"));
		}
		param.put("objsize", arrobj.size());
		
		HashMap<String, Object> serviceMap = codeSvc.registAllCode(param);
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("SVC_CODE", serviceMap.get("SVC_CODE"));
		jsonMap.put("SVC_MSGE", serviceMap.get("SVC_MSGE"));
		
		jsonView.render(jsonMap, request, response);	
		
	}


	
}