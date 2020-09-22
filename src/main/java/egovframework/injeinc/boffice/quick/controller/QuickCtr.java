package egovframework.injeinc.boffice.quick.controller;

import java.util.HashMap;
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
import egovframework.injeinc.boffice.quick.service.QuickSvc;
import egovframework.injeinc.boffice.quick.vo.QuickVo;
import egovframework.injeinc.common.code.service.CodeSvc;
import egovframework.injeinc.common.util.EgovStringUtil;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class QuickCtr extends CmmLogCtr{
	
	@Autowired
	private CodeSvc codeSvc;
	
	@Resource(name = "QuickSvc")
	private QuickSvc quickSvc;
	
	@Autowired(required=true)
	private MappingJackson2JsonView jsonView;
	
	@RequestMapping("/boffice/quick/QuickFormAx.do")
	public void QuickFormAx(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("QuickVo") QuickVo quickVo, ModelMap model) throws Exception {

		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		
		QuickVo resultVo = quickSvc.retrieveQuick(quickVo);
		
		if(resultVo != null) {
			resultVo.setActionkey("modify");
			jsonMap.put("resultVo", resultVo);
		}else{
			quickVo.setActionkey("regist");
			quickVo.setCqlCode("");
			quickVo.setCqlIdx("");
			quickVo.setCqlName("");
			quickVo.setCqlLink("");
			quickVo.setCqlTarget("_blank");
			quickVo.setCqlHotYn("N");
			quickVo.setCqlNewYn("N");
			quickVo.setCqlUse("Y");
			jsonMap.put("resultVo", quickVo);
		}
		
		jsonMap.put("result", true);
		jsonMap.put("message", "성공");
		
		jsonView.render(jsonMap, request, response);
			
	}
	
	@RequestMapping("/boffice/quick/QuickRegAx.do")
	public void QuickRegAx(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("QuickVo") QuickVo quickVo, ModelMap model) throws Exception {

		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		
		boolean result = false;
		String message = "";
			
		if(!EgovStringUtil.isEmpty(userid)) {

			quickVo.setRegId(userid);
			quickSvc.registQuick(quickVo);
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
	
	@RequestMapping("/boffice/quick/QuickModAx.do")
	public void QuickModAx(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("QuickVo") QuickVo quickVo, ModelMap model) throws Exception {

		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		
		boolean result = false;
		String message = "";
			
		if(!EgovStringUtil.isEmpty(userid)) {
			
			QuickVo resultVo = quickSvc.retrieveQuick(quickVo);
			
			if(resultVo != null) {

				quickVo.setModId(userid);
				quickSvc.modifyQuick(quickVo);
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
	
	@RequestMapping("/boffice/quick/QuickRmvAx.do")
	public void QuickRmvAx(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("QuickVo") QuickVo quickVo, ModelMap model) throws Exception {

		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		
		boolean result = false;
		String message = "";
			
		if(!EgovStringUtil.isEmpty(userid)) {
			
			quickVo = quickSvc.retrieveQuick(quickVo);
			
			if(quickVo != null) {

				quickVo.setModId(userid);
				quickSvc.removeQuick(quickVo);
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
	
	@RequestMapping("/boffice/quick/QuickList.do")
	public String QuickList(@ModelAttribute("QuickVo") QuickVo quickVo, ModelMap model) throws Exception {
		
		if(quickVo.getSearchOrder().equals("")) {
			quickVo.setSearchOrder("1");
		}
		if(quickVo.getSearchCqlUse().equals("")) {
			quickVo.setSearchCqlUse("Y");
		}
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(quickVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(quickVo.getPageUnit());
		paginationInfo.setPageSize(quickVo.getPageSize());
		
		quickVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		quickVo.setLastIndex(paginationInfo.getLastRecordIndex());
		quickVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		Map<String, Object> map = quickSvc.retrievePagQuick(quickVo); 
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);
		
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("codeAxuse", "19000000");
		HashMap<String, Object> codeMap = codeSvc.retrieveCommonCode(param);
		model.addAttribute("codeList", codeMap.get("rowDataList"));
		
		return "injeinc/boffice/quick/quick_list";
	}
	
	@RequestMapping("/boffice/quick/QuickModEtcInfoAx.do")
	public void QuickModEtcInfoAx(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("QuickVo") QuickVo quickVo) throws Exception {
		
		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		quickVo.setModId(userid);
		
		String cqlIdx = quickVo.getCqlIdx();
		String type = EgovStringUtil.nullConvert(request.getParameter("type"));
		String value = EgovStringUtil.nullConvert(request.getParameter("value"));
		
		boolean result = false;
		String message = "";
		
		if(!cqlIdx.equals("") && !EgovStringUtil.isEmpty("type") && !EgovStringUtil.isEmpty("value")) {
			
			if(type.equals("hot")) {
				quickVo.setCqlHotYn(value);
			}else if(type.equals("new")) {
				quickVo.setCqlNewYn(value);
			}else if(type.equals("use")) {
				quickVo.setCqlUse(value);
			}
			
			quickSvc.modifyQuickEtcInfo(quickVo);
			message = "수정 성공";
			result = true;
			
		}else{
			message = "필요한 자료가 없습니다.";
		}
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", result);
		jsonMap.put("message", message);
		jsonView.render(jsonMap, request, response);
			
		
	}
	
	@RequestMapping("/boffice/quick/QuickSortUpAx.do")
	public void QuickSortUpAx(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("QuickVo") QuickVo quickVo) throws Exception {
		
		QuickVo resultVo = quickSvc.retrieveQuick(quickVo);
		
		boolean result = false;
		String message = "";
		
		if(resultVo != null) {
			int cqlSort = resultVo.getCqlSort();
			
			if(cqlSort > 1) {
				quickSvc.modifyQuickSortUp(resultVo);
				message = "수정에 성공하였습니다.";
				result = true;
			}else{
				message = "더 이상 순위를 올릴 수 없습니다.";
			}
		}else{
			message = "잘못된 접근가 없습니다.";
		}

		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", result);
		jsonMap.put("message", message);
		jsonView.render(jsonMap, request, response);
			
	}
	
	@RequestMapping("/boffice/quick/QuickSortDownAx.do")
	public void QuickSortDownAx(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("QuickVo") QuickVo quickVo) throws Exception {
		
		QuickVo resultVo = quickSvc.retrieveQuick(quickVo);
		
		boolean result = false;
		String message = "";
		
		if(resultVo != null) {
			int cqlSort = resultVo.getCqlSort();
			int maxSort = quickSvc.retrieveQuickMaxSort(resultVo);
			
			if(maxSort > cqlSort) {
				quickSvc.modifyQuickSortDown(resultVo);
				message = "수정에 성공하였습니다.";
				result = true;
			}else{
				message = "더 이상 순위를 내릴 수 없습니다.";
			}
		}else{
			message = "잘못된 접근가 없습니다.";
		}

		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", result);
		jsonMap.put("message", message);
		jsonView.render(jsonMap, request, response);
			
	}
}