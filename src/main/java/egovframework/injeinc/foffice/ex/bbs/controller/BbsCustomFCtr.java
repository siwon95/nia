package egovframework.injeinc.foffice.ex.bbs.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.Message;
import egovframework.injeinc.boffice.ex.board.service.ContentFileSvc;
import egovframework.injeinc.boffice.ex.board.vo.ContentFileVo;
import egovframework.injeinc.boffice.sy.code.service.CmmCodeSvc;
import egovframework.injeinc.common.util.EgovStringUtil;
import egovframework.injeinc.foffice.ex.bbs.service.BbsCustomFSvc;
import egovframework.injeinc.foffice.ex.bbs.vo.BbsCustomFVo;

@Controller
public class BbsCustomFCtr extends CmmLogCtr {
	
	@Resource(name = "CmmCodeSvc")
	private CmmCodeSvc cmmCodeSvc;
	
	@Resource(name = "BbsCustomFSvc")
	private BbsCustomFSvc bbsCustomFSvc;
	
	@Resource(name = "ContentFileSvc")
	private ContentFileSvc contentFileSvc;
	
	@RequestMapping("/site/{strDomain}/ex/bbs/internet/{strCustomType}/BbsCustomFView.do")
	public String BbsCustomFForm(HttpServletRequest request, @PathVariable("strCustomType") String strCustomType, @ModelAttribute("BbsCustomFVo") BbsCustomFVo bbsCustomFVo, ModelMap model) throws Exception {
		
		bbsCustomFVo.setCbIdx(63);
		BbsCustomFVo result = bbsCustomFSvc.retrieveBbsCustomF(bbsCustomFVo);
		
		if(result != null) {
			result.setPageIndex(bbsCustomFVo.getPageIndex());
			result.setSearchCateCont(bbsCustomFVo.getSearchCateCont());
			result.setSearchCondition(bbsCustomFVo.getSearchCondition());
			result.setSearchKeyword(bbsCustomFVo.getSearchKeyword());
			result.setCustomType(strCustomType);
			model.addAttribute("BbsCustomFVo", result);
		}else{
			String SVC_MSGE = "잘못된 접근입니다.";
			return alert(request.getContextPath()+"/site/{strDomain}/ex/bbs/internet/"+strCustomType+"/BbsCustomFList.do", SVC_MSGE, model);
		}

		model.addAttribute("prevNextInfo", bbsCustomFSvc.retrieveBbsCustomFPrevNext(bbsCustomFVo));
		model.addAttribute("categoryList", cmmCodeSvc.retrieveListCmmCode("25010000"));

		return "/injeinc/foffice/ex/bbs/internet/view";
	}
	
	@RequestMapping("/site/{strDomain}/ex/bbs/internet/{strCustomType}/BbsCustomFList.do")
	public String BbsCustomFList(HttpServletRequest request, @PathVariable("strCustomType") String strCustomType, @ModelAttribute("BbsCustomFVo") BbsCustomFVo bbsCustomFVo, ModelMap model) throws Exception {
		
		boolean isMobile = false;
		String agent = request.getHeader("USER-AGENT");
		String[] mobileos = {"iPhone","iPod","Android","BlackBerry","Windows CE","Nokia","Webos","Opera Mini","SonyEricsson","Opera Mobi","IEMobile"};
		
		for(int i=0 ; i<mobileos.length ; i++) {
			if(agent.indexOf(mobileos[i]) > -1 ) {
				// 모바일로 접근했을 때
				isMobile = true;
				break;
			}
		}

		String method = request.getMethod();
		
		String searchCateCont = bbsCustomFVo.getSearchCateCont();
		
		if(method.equals("GET") && searchCateCont.equals("")) {
		
			if(strCustomType.equals("focus")) {
				searchCateCont = "25010100";
			}else if(strCustomType.equals("fun")) {
				searchCateCont = "25010200";
			}else if(strCustomType.equals("news")) {
				searchCateCont = "25010300";
			}else if(strCustomType.equals("council")) {
				searchCateCont = "25010400";
			}else if(strCustomType.equals("culture")) {
				searchCateCont = "25010500";
			}
			bbsCustomFVo.setSearchCateCont(searchCateCont);
		
		}
		
		bbsCustomFVo.setCbIdx(63);
		
		model.addAttribute("categoryList", cmmCodeSvc.retrieveListCmmCode("25010000"));

		bbsCustomFVo.setCustomType(strCustomType);
		
		bbsCustomFVo.setPageUnit(8);
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(bbsCustomFVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(bbsCustomFVo.getPageUnit());
		if(isMobile) {
			paginationInfo.setPageSize(5);
		}else{
			paginationInfo.setPageSize(bbsCustomFVo.getPageSize());
		}
		
		bbsCustomFVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		bbsCustomFVo.setLastIndex(paginationInfo.getLastRecordIndex());
		bbsCustomFVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		Map<String, Object> map = bbsCustomFSvc.retrievePagBbsCustomF(bbsCustomFVo); 
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "/injeinc/foffice/ex/bbs/internet/list";
	}
	
	@RequestMapping(value = "/site/ex/bbs/IdeaBoardReg.do", method = RequestMethod.POST)
	public String IdeaBoardReg(HttpServletRequest request, @ModelAttribute("BbsCustomFVo") BbsCustomFVo bbsCustomFVo, ModelMap model) throws Exception {
		
		String md_title		= EgovStringUtil.isNullToString(request.getParameter("title")); 
		String md_writer	= EgovStringUtil.isNullToString(request.getParameter("writer"));
		String md_email		= EgovStringUtil.isNullToString(request.getParameter("email"));
		String md_hp		= EgovStringUtil.isNullToString(request.getParameter("hp"));
		String md_content	= EgovStringUtil.isNullToString(request.getParameter("content"));
		String md_dupcode	= EgovStringUtil.isNullToString(request.getParameter("dupcode"));

		if(md_title.equals("") || md_writer.equals("") || md_content.equals("")) {
			String SVC_MSGE = "필요한 자료가 없습니다.";
			errorLog(SVC_MSGE);
			model.addAttribute("message", SVC_MSGE);
			return "injeinc/common/blank";
		}else if(md_dupcode.equals("")) {
			String SVC_MSGE = "본인 확인을 해야 합니다.";
			errorLog(SVC_MSGE);
			model.addAttribute("message", SVC_MSGE);
			return "injeinc/common/blank";
		}

		StringBuffer url = request.getRequestURL();
		
		if(url.indexOf("https://") >= 0) {
			//통과
		}else{
			//String SVC_MSGE = "보안서버로만 접근이 가능합니다.";
			//errorLog(SVC_MSGE);
			//model.addAttribute("message", SVC_MSGE);
			//return "injeinc/common/blank";
		}

		bbsCustomFVo.setSubCont(md_title);
		bbsCustomFVo.setNameCont(md_writer);
		bbsCustomFVo.setEmailCont(md_email);
		bbsCustomFVo.setTelCont(md_hp);
		bbsCustomFVo.setPhoneCont(md_hp);
		bbsCustomFVo.setClobCont(md_content);
		bbsCustomFVo.setClobContSearch(EgovStringUtil.cutString(EgovStringUtil.removeTag(md_content),1000));
		bbsCustomFVo.setDupcode(md_dupcode);
		bbsCustomFVo.setRegId("커맵연계");
		bbsCustomFVo.setRegIp(request.getRemoteAddr());
		bbsCustomFVo.setCbIdx(15);
		int bcIdx = bbsCustomFSvc.registBbsCustomFIdea(bbsCustomFVo);
		
		if(bcIdx > 0) {
			ContentFileVo contentFileVo = new ContentFileVo();
			contentFileVo.setBcIdx(bcIdx);
			contentFileVo.setCbIdx(15);
			contentFileVo.setRegId("커맵연계");
			contentFileSvc.registContentFile(request, contentFileVo);
		}

		String SVC_MSGE = Message.getMessage("100.message");
		model.addAttribute("message", SVC_MSGE);
		return "injeinc/common/blank";
	}
}