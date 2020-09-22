package egovframework.injeinc.boffice.ex.board.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.Message;
import egovframework.injeinc.boffice.ex.board.service.BbsCommentSvc;
import egovframework.injeinc.boffice.ex.board.vo.BbsCommentVo;

import egovframework.injeinc.common.util.EgovStringUtil;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


@Controller
public class BbsCommentCtr extends CmmLogCtr {
	
	private Logger logger = Logger.getLogger(getClass());
	
	@Resource(name = "BbsCommentSvc")
	private BbsCommentSvc bbsCommentSvc;
	
	
	
	@Autowired(required=true)
	private MappingJackson2JsonView jsonView;
	

	@RequestMapping("/boffice{strNodeco}/ex/board/BbsCommentRegAx.do")
	public void BbsCommentRegAx(@PathVariable("strNodeco") String strNodeco, HttpServletRequest request, HttpServletResponse response, @ModelAttribute("BbsCommentVo") BbsCommentVo bbsCommentVo, ModelMap model) throws Exception {
		HttpSession ses = request.getSession();
		String userid = (String)ses.getAttribute("SesUserId");	
		String userName = (String)ses.getAttribute("SesUserName");	
		
		boolean result = false;
		String message = "";
		if(!EgovStringUtil.isEmpty(userid)) {
			bbsCommentVo.setRegId(userid);
			bbsCommentVo.setRegName(userName);
			bbsCommentVo.setRegIp(request.getRemoteAddr());

			String cmCont=bbsCommentVo.getCmCont();
			cmCont = org.springframework.web.util.HtmlUtils.htmlEscape(cmCont);
			bbsCommentVo.setCmCont(cmCont);
			
			if(bbsCommentVo.getUprCmIdx()>0) {
				bbsCommentVo.setCmIdx(bbsCommentVo.getUprCmIdx());
				BbsCommentVo bbsCommentVo2 = bbsCommentSvc.retrieveBbsComment(bbsCommentVo);
				bbsCommentVo.setCmDepth(bbsCommentVo2.getCmDepth() + 1);
			}

			bbsCommentSvc.registBbsComment(bbsCommentVo);
			String SVC_MSGE = Message.getMessage("100.message");
			message = SVC_MSGE;
			result = true;
		}else {
			message = "잘못된 접근입니다.";
		}
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", result);
		jsonMap.put("message", message);
		jsonView.render(jsonMap, request, response);
	}
	
	@RequestMapping("/boffice{strNodeco}/ex/board/BbsCommentModAx.do")
	public void BbsCommentModAx(@PathVariable("strNodeco") String strNodeco, HttpServletRequest request, HttpServletResponse response, @ModelAttribute("BbsCommentVo") BbsCommentVo bbsCommentVo, ModelMap model) throws Exception {
		HttpSession ses = request.getSession();
		String userid = (String)ses.getAttribute("SesUserId");
		boolean result = false;
		String message = "";
		BbsCommentVo bbsCommentVo2 = bbsCommentSvc.retrieveBbsComment(bbsCommentVo);
		if(!EgovStringUtil.isEmpty(userid) && bbsCommentVo2!=null) {
			bbsCommentVo.setModId(userid);
			bbsCommentVo.setModIp(request.getRemoteAddr());
			
			String cmCont=bbsCommentVo.getCmCont();
			cmCont = org.springframework.web.util.HtmlUtils.htmlEscape(cmCont);
			bbsCommentVo.setCmCont(cmCont);
			
			bbsCommentSvc.modifyBbsComment(bbsCommentVo);
			String SVC_MSGE = Message.getMessage("100.message");
			message = SVC_MSGE;
			result = true;
		}else {
			message = "잘못된 접근입니다.";
		}
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", result);
		jsonMap.put("message", message);
		jsonView.render(jsonMap, request, response);
	}
	@RequestMapping("/boffice{strNodeco}/ex/board/BbsCommentRmvAx.do")
	public void BbsCommentRmvAx(@PathVariable("strNodeco") String strNodeco, HttpServletRequest request, HttpServletResponse response, @ModelAttribute("BbsCommentVo") BbsCommentVo bbsCommentVo, ModelMap model) throws Exception {
		HttpSession ses = request.getSession();
		String userid = (String)ses.getAttribute("SesUserId");
		boolean result = false;
		String message = "";
		BbsCommentVo bbsCommentVo2 = bbsCommentSvc.retrieveBbsComment(bbsCommentVo);
		if(bbsCommentVo2!=null) {
			bbsCommentVo.setModId(userid);
			bbsCommentVo.setModIp(request.getRemoteAddr());
			bbsCommentSvc.removeBbsComment(bbsCommentVo);
			String SVC_MSGE = Message.getMessage("100.message");
			message = SVC_MSGE;
			result = true;
		}else {
			message = "잘못된 접근입니다.";
		}
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", result);
		jsonMap.put("message", message);
		jsonView.render(jsonMap, request, response);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/boffice{strNodeco}/ex/board/BbsCommentList.do")
	public String BbsCommentList(@PathVariable("strNodeco") String strNodeco, HttpServletRequest request, @ModelAttribute("BbsCommentVo") BbsCommentVo bbsCommentVo, ModelMap model) throws Exception {
		boolean result=false;
		String message="";
		int totCnt=0;
		/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(bbsCommentVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(bbsCommentVo.getPageUnit());
		paginationInfo.setPageSize(bbsCommentVo.getPageSize());

		bbsCommentVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		bbsCommentVo.setLastIndex(paginationInfo.getLastRecordIndex());
		bbsCommentVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List<BbsCommentVo> resultList = new ArrayList<BbsCommentVo>();
		if(bbsCommentVo!=null || bbsCommentVo.getBcIdx()!=0){
			resultList = bbsCommentSvc.retrieveListComment(bbsCommentVo);
			totCnt = bbsCommentSvc.selectAllCommentTotCnt(bbsCommentVo); 
			paginationInfo.setTotalRecordCount(totCnt);
			result=true;
		}
		model.addAttribute("message", message);
		model.addAttribute("totCnt", totCnt);
		model.addAttribute("resultList", resultList);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("BbsCommentVo", bbsCommentVo);
		model.addAttribute("result", result);
		return "injeinc/boffice/ex/board/bbs_comment_list";
	}
}