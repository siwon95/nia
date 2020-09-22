package egovframework.injeinc.foffice.ex.board.controller;

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
import egovframework.injeinc.common.util.EgovStringUtil;
import egovframework.injeinc.foffice.ex.board.service.ContentMinwonResultFSvc;
import egovframework.injeinc.foffice.ex.board.vo.ContentMinwonResultFVo;

@Controller
public class ContentMinwonResultFCtr extends CmmLogCtr {
	
	@Resource(name = "ContentMinwonResultFSvc")
	private ContentMinwonResultFSvc contentMinwonResultFSvc;
	
	@Autowired(required=true)
	private MappingJackson2JsonView jsonView;
	
	@RequestMapping("/site/{strDomain}/ex/board/ContentMinwonResultFSurveyRegAx.do")
	public void ContentMinwonResultFSurveyRegAx(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("ContentMinwonResultFVo") ContentMinwonResultFVo contentMinwonResultFVo, ModelMap model) throws Exception {

		String userid = (String) WebUtils.getSessionAttribute(request, "ss_id");
		
		int bcIdx = contentMinwonResultFVo.getBcIdx();
		int cbIdx = contentMinwonResultFVo.getCbIdx();
		String mcIdx = contentMinwonResultFVo.getMcIdx();
		String mcSurvey = contentMinwonResultFVo.getMcSurvey();
		String mcSurveyDesc = contentMinwonResultFVo.getMcSurveyDesc();
		
		boolean result = false;
		String message = "";
		
		if(!EgovStringUtil.isEmpty(userid) && bcIdx > 0 && cbIdx > 0 && !mcIdx.equals("") && !mcSurvey.equals("") && !mcSurveyDesc.equals("")) {
			
			contentMinwonResultFSvc.modifyContentMinwonResultFSurvey(contentMinwonResultFVo);
			
			message = "만족도조사가 등록되었습니다.";
			result = true;
		}else{
			message = "필요한 자료가 없습니다.";
		}

		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", result);
		jsonMap.put("message", message);
		jsonView.render(jsonMap, request, response);
			
	}
	
}