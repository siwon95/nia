package egovframework.injeinc.boffice.sy.board.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.util.WebUtils;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.Message;
import egovframework.injeinc.boffice.sy.board.service.CmsBbsSvc;
import egovframework.injeinc.boffice.sy.board.vo.CmsBbsVo;
import egovframework.injeinc.boffice.sy.board.vo.TreeBoardVo;
import egovframework.injeinc.common.util.EgovStringUtil;

@Controller
public class CmsBbsCtr extends CmmLogCtr {
	
	@Resource(name = "CmsBbsSvc")
	private CmsBbsSvc cmsBbsSvc;
	
	@Autowired(required=true)
	private MappingJackson2JsonView jsonView;
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/boffice/sy/board/CmsBbsListAx.do")
	public void CmsBbsListAx(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ArrayList<TreeBoardVo> boardList = (ArrayList<TreeBoardVo>) cmsBbsSvc.retrieveListCmsBbs();
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", "true");
		jsonMap.put("message", Message.getMessage("100.message"));
		jsonMap.put("boardList", boardList);
		jsonView.render(jsonMap, request, response);
			
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/boffice/sy/board/CmsBbsListSiteAx.do")
	public void CmsBbsListSiteAx(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("CmsBbsVo") CmsBbsVo cmsBbsVo) throws Exception {
		
		ArrayList<TreeBoardVo> boardList = (ArrayList<TreeBoardVo>) cmsBbsSvc.retrieveListCmsBbsSite(cmsBbsVo);
		
		System.out.println("cmsBbsVo.getMgrSiteCd():"+cmsBbsVo.getMgrSiteCd());
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", "true");
		jsonMap.put("message", Message.getMessage("100.message"));
		jsonMap.put("boardList", boardList);
		jsonView.render(jsonMap, request, response);
			
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/boffice/sy/board/CmsBbsListRoleAx.do")
	public void CmsBbsListRoleAx(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ArrayList<TreeBoardVo> boardList = (ArrayList<TreeBoardVo>) cmsBbsSvc.retrieveListCmsBbs();
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", "true");
		jsonMap.put("message", Message.getMessage("100.message"));
		jsonMap.put("boardList", boardList);
		jsonView.render(jsonMap, request, response);
			
	}

	@RequestMapping("/boffice/sy/board/CmsBbsList.do")
	public String CmsBbsList(HttpServletRequest request, @ModelAttribute("CmsBbsVo") CmsBbsVo cmsBbsVo, ModelMap model) throws Exception {
		return "injeinc/boffice/sy/board/cms_bbs_list";
	}
	
	@RequestMapping("/boffice/sy/board/CmsBbsReg.do")
	public String CmsBbsReg(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("CmsBbsVo") CmsBbsVo cmsBbsVo, ModelMap model) throws Exception {
		
		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		CmsBbsVo cmsBbsVo2 = new CmsBbsVo();
		cmsBbsVo2 = cmsBbsSvc.selectCmsBbs(cmsBbsVo);
		String sameLevel = request.getParameter("sameLevel");
		
		if(sameLevel.equals("Y")){
			cmsBbsVo.setCbUprCd(cmsBbsVo2.getCbUprCd());
			cmsBbsVo.setCbUse("Y");
			cmsBbsVo.setUseYn("Y");
			cmsBbsVo.setRegId(userid);
			cmsBbsVo.setPublicYn("N");
			cmsBbsVo.setCbDepth(cmsBbsVo2.getCbDepth());
		}else{
			cmsBbsVo.setCbUprCd(cmsBbsVo2.getCbCd());
			cmsBbsVo.setCbUse("Y");
			cmsBbsVo.setUseYn("Y");
			cmsBbsVo.setRegId(userid);
			cmsBbsVo.setPublicYn("N");
			cmsBbsVo.setCbDepth((Integer.parseInt(cmsBbsVo2.getCbDepth())+1)+"");
		}
		cmsBbsSvc.createCmsBbs(cmsBbsVo);
		cmsBbsVo.setCbCd(cmsBbsVo.getCbCd());
		//cmsBbsVo.setCbIdx("");
		cmsBbsVo2 = cmsBbsSvc.selectCmsBbs(cmsBbsVo);
		
		model.addAttribute("cmsBbsVo", cmsBbsVo2);
		model.addAttribute("mode", "regEnd");
		model.addAttribute("cbCd", cmsBbsVo2.getCbCd());
		//model.addAttribute("cbIdx", cmsBbsVo2.getCbIdx());
		return "redirect:/boffice/sy/board/BoardSetPop.do?cbIdx="+cmsBbsVo2.getCbIdx();
		
	}
	
	@RequestMapping("/boffice/sy/board/CmsBbsRegAx.do")
	public void CmsBbsRegAx(HttpServletRequest request, HttpServletResponse response, @RequestBody String tree) throws Exception {
		
		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		
		JSONArray jsonArray = (JSONArray)JSONValue.parse(tree);
		
		List<CmsBbsVo> list = new ArrayList<CmsBbsVo>();
		
		for(int i = 0; i < jsonArray.size(); i++) {
			
			CmsBbsVo cmsBbsVo = new CmsBbsVo();
			cmsBbsVo.setRegId(userid);
			cmsBbsVo.setModId(userid);
			
			JSONObject jsonObject = (JSONObject) jsonArray.get(i);
			cmsBbsVo.setCbCd(EgovStringUtil.isNullToString(jsonObject.get("id")));
			cmsBbsVo.setCbUprCd(EgovStringUtil.isNullToString(jsonObject.get("parent")));
			cmsBbsVo.setCbName(EgovStringUtil.isNullToString(jsonObject.get("text")));
			cmsBbsVo.setOrdNo(i);
			cmsBbsVo.setCbIdx(EgovStringUtil.isNullToString(jsonObject.get("cbIdx")));
			cmsBbsVo.setPublicYn(EgovStringUtil.isNullToString(jsonObject.get("publicYn")));
			cmsBbsVo.setCbUse(EgovStringUtil.isNullToString(jsonObject.get("cbUse")));
			cmsBbsVo.setCbDepth(EgovStringUtil.isNullToString(jsonObject.get("cbDepth")));
			
			list.add(cmsBbsVo);
		}
		
		cmsBbsSvc.registCmsBbs(userid, list);

		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", "true");
		jsonMap.put("message", Message.getMessage("100.message"));
		jsonView.render(jsonMap, request, response);
		
	}
	
	@RequestMapping("/boffice/sy/board/CmsBbsCntAx.do")
	public void CmsBbsCntAx(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("CmsBbsVo") CmsBbsVo cmsBbsVo) throws Exception {

		String cbCd = cmsBbsVo.getCbCd();
		
		boolean result = false;
		String message = "";
		int resultCnt = 0;
		
		if(!cbCd.equals("")) {
			
			resultCnt = cmsBbsSvc.retrieveCmsBbsCnt(cmsBbsVo);
			result = true;
			
		}else{
			message = "필요한 자료가 없습니다.";
		}

		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", result);
		jsonMap.put("message", message);
		jsonMap.put("resultCnt", resultCnt);
		jsonView.render(jsonMap, request, response);
		
	}
}