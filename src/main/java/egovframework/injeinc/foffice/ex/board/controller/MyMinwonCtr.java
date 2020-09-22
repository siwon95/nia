package egovframework.injeinc.foffice.ex.board.controller;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.util.WebUtils;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.Message;
import egovframework.cmm.service.AuthoritySvc;
import egovframework.injeinc.foffice.ex.board.service.BbsContentFSvc;
import egovframework.injeinc.boffice.ex.board.service.ContentFileSvc;
import egovframework.injeinc.foffice.ex.board.vo.BbsContentFVo;
import egovframework.injeinc.boffice.ex.board.vo.ContentFileVo;
import egovframework.injeinc.boffice.sy.board.service.BoardSvc;
import egovframework.injeinc.boffice.sy.board.vo.BoardVo;
import egovframework.injeinc.boffice.sy.board.vo.ConfigPropertyVo;
import egovframework.injeinc.common.util.DateUtil;
import egovframework.injeinc.common.util.EgovStringUtil;
import egovframework.injeinc.common.util.WebUtil;

@Controller
public class MyMinwonCtr extends CmmLogCtr {
	
	@Resource(name = "AuthoritySvc")
	private AuthoritySvc authoritySvc;
	
	@Resource(name = "BbsContentFSvc")
	private BbsContentFSvc bbsContentFSvc;
	
	@Resource(name = "BoardSvc")
	private BoardSvc boardSvc;
	
	@Resource(name = "ContentFileSvc")
	private ContentFileSvc contentFileSvc;
	
	@Autowired(required=true)
	private MappingJackson2JsonView jsonView;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/site/{strDomain}/foffice/board/MyMinwonView.do")
	public String MyMinwonView(HttpServletRequest request, @ModelAttribute("BoardVo") BoardVo boardVo, @ModelAttribute("ConfigPropertyVo") ConfigPropertyVo configPropertyVo, @ModelAttribute("BbsContentFVo") BbsContentFVo bbsContentFVo, @ModelAttribute("ContentFileVo") ContentFileVo contentFileVo, @RequestParam Map requestMap, ModelMap model) throws Exception {

		boardVo = boardSvc.retrieveBoard(boardVo);
		
		if(boardVo != null) {
			model.addAttribute("BoardVo", boardVo);
		}else{
			String SVC_MSGE = "잘못된 접근입니다.";
			return alert(request.getContextPath()+"/site/{strDomain}/foffice/board/MyMinwonList.do", SVC_MSGE, model);
		}
		
		Map<String, Object> result = bbsContentFSvc.retrieveBbsContentF(bbsContentFVo);
		
		if(result == null) {
			String SVC_MSGE = "잘못된 접근입니다.";
			return alert(request.getContextPath()+"/site/{strDomain}/foffice/board/MyMinwonList.do", SVC_MSGE, model);
		}
		
		String ss_dupkey = (String) WebUtils.getSessionAttribute(request, "ss_dupkey");
		String dupcode= EgovStringUtil.isNullToString(result.get("dupcode"));
		
		if(!ss_dupkey.equals(dupcode)) {
			String SVC_MSGE = "읽기 권한이 없습니다.";
			return alert(request.getContextPath()+"/site/{strDomain}/foffice/board/MyMinwonList.do", SVC_MSGE, model);
		}
		
		bbsContentFSvc.modifyBbsContentFCount(bbsContentFVo);
		int countCont = Integer.parseInt(String.valueOf(result.get("countCont")));
		result.put("countCont", countCont+1);
		
		
		result.put("pageIndex", bbsContentFVo.getPageIndex());
		result.put("searchCondition", bbsContentFVo.getSearchCondition());
		result.put("searchKeyword", bbsContentFVo.getSearchKeyword());
		
		model.addAttribute("BbsContentFVo", result);
		
		List fileList = contentFileSvc.retrieveListContentFile(contentFileVo);
		model.addAttribute("fileList", fileList);
		
		List propertylist = boardSvc.retrieveListConfigProperty(configPropertyVo);
		model.addAttribute("propertylist", propertylist);
		
		return "injeinc/foffice/ex/board/my_minwon_view";
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/site/{strDomain}/foffice/board/MyMinwonForm.do")
	public String MyMinwonForm(HttpServletRequest request, @ModelAttribute("BoardVo") BoardVo boardVo, @ModelAttribute("ConfigPropertyVo") ConfigPropertyVo configPropertyVo, @ModelAttribute("BbsContentFVo") BbsContentFVo bbsContentFVo, @ModelAttribute("ContentFileVo") ContentFileVo contentFileVo, @RequestParam Map requestMap, ModelMap model) throws Exception {

		boardVo = boardSvc.retrieveBoard(boardVo);
		
		if(boardVo != null) {
			model.addAttribute("BoardVo", boardVo);
		}else{
			String SVC_MSGE = "잘못된 접근입니다.";
			return alert(request.getContextPath()+"/site/{strDomain}/foffice/board/MyMinwonList.do", SVC_MSGE, model);
		}
		
		Map<String, Object> result = bbsContentFSvc.retrieveBbsContentF(bbsContentFVo);
		
		if(result == null) {
			String SVC_MSGE = "잘못된 접근입니다.";
			return alert(request.getContextPath()+"/site/{strDomain}/foffice/board/MyMinwonList.do", SVC_MSGE, model);
		}
		
		String ss_dupkey = (String) WebUtils.getSessionAttribute(request, "ss_dupkey");
		String dupcode= EgovStringUtil.isNullToString(result.get("dupcode"));
		
		if(!ss_dupkey.equals(dupcode)) {
			String SVC_MSGE = "수정 권한이 없습니다.";
			return alert(request.getContextPath()+"/site/{strDomain}/foffice/board/MyMinwonList.do", SVC_MSGE, model);
		}
		
		result.put("pageIndex", bbsContentFVo.getPageIndex());
		result.put("searchCondition", bbsContentFVo.getSearchCondition());
		result.put("searchKeyword", bbsContentFVo.getSearchKeyword());
		
		model.addAttribute("BbsContentFVo", result);
		
		List fileList = contentFileSvc.retrieveListContentFile(contentFileVo);
		model.addAttribute("fileList", fileList);
		
		List propertylist = boardSvc.retrieveListConfigProperty(configPropertyVo);
		model.addAttribute("propertylist", propertylist);
		
		StringBuffer addParam = new StringBuffer();
		addParam.append("?pageIndex=").append(bbsContentFVo.getPageIndex());
		addParam.append("&searchCondition=").append(bbsContentFVo.getSearchCondition());
		addParam.append("&searchKeyword=").append(URLEncoder.encode(bbsContentFVo.getSearchKeyword(), "UTF-8"));
		addParam.append("&cbIdx=").append(bbsContentFVo.getCbIdx());
		addParam.append("&bcIdx=").append(bbsContentFVo.getBcIdx());
		model.addAttribute("addParam", addParam.toString());
		
		return "injeinc/foffice/ex/board/my_minwon_form";
	}
	
	@RequestMapping("/site/{strDomain}/foffice/board/MyMinwonMod.do")
	public String MyMinwonMod(HttpServletRequest request, @ModelAttribute("BoardVo") BoardVo boardVo, @ModelAttribute("BbsContentFVo") BbsContentFVo bbsContentFVo, ModelMap model) throws Exception {

		boardVo = boardSvc.retrieveBoard(boardVo);
		
		if(boardVo != null) {
			model.addAttribute("BoardVo", boardVo);
		}else{
			String SVC_MSGE = "잘못된 접근입니다.";
			return alert(request.getContextPath()+"/site/{strDomain}/foffice/board/MyMinwonList.do", SVC_MSGE, model);
		}
		
		Map<String, Object> result = bbsContentFSvc.retrieveBbsContentF(bbsContentFVo);
		
		if(result == null) {
			String SVC_MSGE = "잘못된 접근입니다.";
			return alert(request.getContextPath()+"/site/{strDomain}/foffice/board/MyMinwonList.do", SVC_MSGE, model);
		}
		
		String ss_dupkey = (String) WebUtils.getSessionAttribute(request, "ss_dupkey");
		String dupcode= EgovStringUtil.isNullToString(result.get("dupcode"));
		
		if(!ss_dupkey.equals(dupcode)) {
			String SVC_MSGE = "수정 권한이 없습니다.";
			return alert(request.getContextPath()+"/site/{strDomain}/foffice/board/MyMinwonList.do", SVC_MSGE, model);
		}
		
		String ss_id = (String) WebUtils.getSessionAttribute(request, "ss_id");
		bbsContentFVo.setModId(ss_id);
		bbsContentFVo.setModIp(request.getRemoteAddr());
		bbsContentFVo.setSearchDupcode(ss_dupkey);
		
		bbsContentFSvc.modifyBbsContentF(bbsContentFVo);

		ContentFileVo contentFileVo = new ContentFileVo();
		contentFileVo.setBcIdx(bbsContentFVo.getBcIdx());
		contentFileVo.setCbIdx(bbsContentFVo.getCbIdx());
		contentFileVo.setRegId(ss_id);
		contentFileSvc.registContentFile(request, contentFileVo);
		
		StringBuffer addParam = new StringBuffer();
		addParam.append("?pageIndex=").append(bbsContentFVo.getPageIndex());
		addParam.append("&searchCondition=").append(bbsContentFVo.getSearchCondition());
		addParam.append("&searchKeyword=").append(bbsContentFVo.getSearchKeyword());
		addParam.append("&cbIdx=").append(bbsContentFVo.getCbIdx());
		addParam.append("&bcIdx=").append(bbsContentFVo.getBcIdx());

		String SVC_MSGE = Message.getMessage("401.message");
		return alert("/site/{strDomain}/foffice/board/MyMinwonView.do"+addParam.toString(), SVC_MSGE, model);
	}
	
	@RequestMapping("/site/{strDomain}/foffice/board/MyMinwonRmv.do")
	public String MyMinwonRmv(HttpServletRequest request, @ModelAttribute("BoardVo") BoardVo boardVo, @ModelAttribute("BbsContentFVo") BbsContentFVo bbsContentFVo, ModelMap model) throws Exception {

		boardVo = boardSvc.retrieveBoard(boardVo);
		
		if(boardVo != null) {
			model.addAttribute("BoardVo", boardVo);
		}else{
			String SVC_MSGE = "잘못된 접근입니다.";
			return alert(request.getContextPath()+"/site/{strDomain}/foffice/board/MyMinwonList.do", SVC_MSGE, model);
		}
		
		Map<String, Object> result = bbsContentFSvc.retrieveBbsContentF(bbsContentFVo);
		
		if(result == null) {
			String SVC_MSGE = "잘못된 접근입니다.";
			return alert(request.getContextPath()+"/site/{strDomain}/foffice/board/MyMinwonList.do", SVC_MSGE, model);
		}
		
		String ss_dupkey = (String) WebUtils.getSessionAttribute(request, "ss_dupkey");
		String dupcode= EgovStringUtil.isNullToString(result.get("dupcode"));
		
		if(!ss_dupkey.equals(dupcode)) {
			String SVC_MSGE = "삭제 권한이 없습니다.";
			return alert(request.getContextPath()+"/site/{strDomain}/foffice/board/MyMinwonList.do", SVC_MSGE, model);
		}

		String ss_id = (String) WebUtils.getSessionAttribute(request, "ss_id");
		bbsContentFVo.setModId(ss_id);
		bbsContentFVo.setModIp(request.getRemoteAddr());
		bbsContentFVo.setSearchDupcode(ss_dupkey);
		
		bbsContentFSvc.removeBbsContentF(bbsContentFVo);
		
		StringBuffer addParam = new StringBuffer();
		addParam.append("?pageIndex=").append(bbsContentFVo.getPageIndex());
		addParam.append("&searchCondition=").append(bbsContentFVo.getSearchCondition());
		addParam.append("&searchKeyword=").append(bbsContentFVo.getSearchKeyword());
		
		String SVC_MSGE = Message.getMessage("501.message");
		return alert("/site/{strDomain}/foffice/board/MyMinwonList.do"+addParam.toString(), SVC_MSGE, model);
	}
	
	@RequestMapping("/site/{strDomain}/foffice/board/MyMinwonList.do")
	public String MyMinwonList(HttpServletRequest request, @ModelAttribute("BbsContentFVo") BbsContentFVo bbsContentFVo, ModelMap model) throws Exception {
		
		String ss_dupkey = (String) WebUtils.getSessionAttribute(request, "ss_dupkey");
		bbsContentFVo.setSearchDupcode(ss_dupkey);
		
		boolean isMobile = WebUtil.isMobile(request);
		if(isMobile) {
			bbsContentFVo.setPageSize(5);
		}
		model.addAttribute("isMobile", isMobile);
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(bbsContentFVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(bbsContentFVo.getPageUnit());
		paginationInfo.setPageSize(bbsContentFVo.getPageSize());
		
		bbsContentFVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		bbsContentFVo.setLastIndex(paginationInfo.getLastRecordIndex());
		bbsContentFVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		Map<String, Object> map = bbsContentFSvc.retrievePagBbsContentFMyMinwon(bbsContentFVo); 
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);
		
		model.addAttribute("nowDate", DateUtil.getCurrentDate("yyyyMMdd"));
		
		return "injeinc/foffice/ex/board/my_minwon_list";
	}
	
}