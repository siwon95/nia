package egovframework.injeinc.common.code.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import egovframework.cmm.CmmLogCtr;
import egovframework.injeinc.boffice.group.emp.service.EmpSvc;
import egovframework.injeinc.common.code.service.CodeSvc;
import egovframework.injeinc.common.code.vo.CodeVo;
import egovframework.injeinc.common.util.EgovStringUtil;

/**
 * <PRE>
 * 1. FileName : CodeCtr.java
 * 2. Package  : egovframework.injeinc.common.code.controller;
 * 3. Comment  : 
 * 4. Programmer   : jsh
 * 5. Date   : 2014. 5. 24.
 * </PRE>
 */

@Controller
public class CodeCtr extends CmmLogCtr{
	
	@Autowired
	private CodeSvc codeSvc;
	
	@Autowired(required=true)
	private MappingJackson2JsonView jsonView;
	
	@Resource(name = "EmpSvc")
	private EmpSvc empSvc;
	
	/**
	 * 콤보박스 
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
//	@RequestMapping(value = "/common/comboAx", method = RequestMethod.POST)
	@RequestMapping(value = "/common/comboAx.do")
	public void comboAx(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String codeAxuse = EgovStringUtil.nullConvert(request.getParameter("DataUse"));
		
		HashMap<String, String> param = new HashMap<String, String>();			
		
		if(codeAxuse != null){
			if(!codeAxuse.equals("")){
				param.put("codeAxuse", codeAxuse);
			}
		}
		
		HashMap<String, Object> serviceMap = codeSvc.retrieveCodeAll(param);
		
		List<CodeVo> rowDataList = (List<CodeVo>) serviceMap.get("rowDataList");
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("SVC_CODE", serviceMap.get("SVC_CODE"));
		jsonMap.put("SVC_MSGE", serviceMap.get("SVC_MSGE"));
		jsonMap.put("rowDataList", rowDataList);
		
		jsonView.render(jsonMap, request, response);
		
	}
	
	
	@SuppressWarnings("unchecked")
//	@RequestMapping(value = "/common/comboAx", method = RequestMethod.POST)
	@RequestMapping(value = "/common/combo_commonAx.do")
	public void combocommonAx(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String codeAxuse = EgovStringUtil.nullConvert(request.getParameter("DataUse"));
		
		HashMap<String, String> param = new HashMap<String, String>();
		if(codeAxuse != null){
			if(!codeAxuse.equals("")){
				param.put("codeAxuse", codeAxuse);
			}
		}
		
		HashMap<String, Object> serviceMap = codeSvc.retrieveCommonCode(param);
		
		List<CodeVo> rowDataList = (List<CodeVo>) serviceMap.get("rowDataList");
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("SVC_CODE", serviceMap.get("SVC_CODE"));
		jsonMap.put("SVC_MSGE", serviceMap.get("SVC_MSGE"));
		jsonMap.put("rowDataList", rowDataList);
		
		jsonView.render(jsonMap, request, response);
			
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/common/DeptcomboAx.do")
	public void deptcomboAx(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HashMap<String, String> param = new HashMap<String, String>();			

		HashMap<String, Object> serviceMap = codeSvc.retrieveDeptCodeAll(param);
		List<CodeVo> rowDataList = (List<CodeVo>) serviceMap.get("rowDataList");
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("SVC_CODE", serviceMap.get("SVC_CODE"));
		jsonMap.put("SVC_MSGE", serviceMap.get("SVC_MSGE"));
		jsonMap.put("rowDataList", rowDataList);
	
		
		jsonView.render(jsonMap, request, response);
			
	}
	
	@RequestMapping(value = "/common/PG_ComboAx.do")
	public void pgComboAx(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HashMap<String, String> param = new HashMap<String, String>();			

		HashMap<String, Object> serviceMap = codeSvc.retrievePgCodeAll(param);
		List<CodeVo> rowDataList = (List<CodeVo>) serviceMap.get("rowDataList");
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("SVC_CODE", serviceMap.get("SVC_CODE"));
		jsonMap.put("SVC_MSGE", serviceMap.get("SVC_MSGE"));
		jsonMap.put("rowDataList", rowDataList);
	
		
		jsonView.render(jsonMap, request, response);
		
	}
	
	/**
	 * 콤보박스 
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
//	@RequestMapping(value = "/common/comboGrpAx", method = RequestMethod.POST)
	@RequestMapping(value = "/common/comboGrpAx.do")
	public void comboGrpAx(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession ses = request.getSession();
		
		String codeGroup = EgovStringUtil.nullConvert(request.getParameter("code_group"));	
		
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("codeGroup", codeGroup);
		
		HashMap<String, Object> serviceMap = codeSvc.retrieveCodeGroup(param);
		
		ArrayList<CodeVo> rowDataList = (ArrayList<CodeVo>) serviceMap.get("rowDataList");
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("SVC_CODE", serviceMap.get("SVC_CODE"));
		jsonMap.put("SVC_MSGE", serviceMap.get("SVC_MSGE"));
		jsonMap.put("rowDataList", rowDataList);
		
		jsonView.render(jsonMap, request, response);
			
	}
	
}