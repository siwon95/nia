package egovframework.injeinc.boffice.ex.sns.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import egovframework.cmm.CmmLogCtr;
import egovframework.injeinc.boffice.ex.sns.service.SnsSvc;
import egovframework.injeinc.boffice.ex.sns.vo.SnsContentsVo;
import egovframework.injeinc.boffice.ex.sns.vo.SnsProhibitWordVo;
import egovframework.injeinc.boffice.ex.sns.vo.SnsHashtagVo;
import egovframework.injeinc.boffice.ex.sns.vo.SnsSearchKeywordVo;
import egovframework.injeinc.common.schedule.SnsRecentlyCollectScheduling;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class SnsCtr extends CmmLogCtr {
	
	@Resource(name = "SnsSvc")
	private SnsSvc snsSvc;
	
	@Autowired(required=true)
	private MappingJackson2JsonView jsonView;

	@Resource(name = "SnsRecentlyCollectScheduling")
	private SnsRecentlyCollectScheduling snsRecentlyCollectScheduling;
	
	@RequestMapping(value="/boffice/ex/sns/validationSns.do")
	public String validationSns(
			HttpServletRequest request,
			HttpServletResponse response,
			@ModelAttribute("SnsContentsVo") SnsContentsVo snsContentsVo,
			ModelMap model) throws Exception{
		
		/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(snsContentsVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(20);
		paginationInfo.setPageSize(snsContentsVo.getPageSize());

		snsContentsVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		snsContentsVo.setLastIndex(paginationInfo.getLastRecordIndex());
		snsContentsVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		snsContentsVo.setSiteDomain("injeinc");
		
		List<SnsContentsVo> snsList = snsSvc.getSnsListForValidation(snsContentsVo);
		
		int totCnt = snsSvc.getSnsListCntForValidation(snsContentsVo);
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("snsList", snsList);
		
		return "injeinc/boffice/ex/sns/validationSns";
	}

	@RequestMapping(value="/boffice/ex/sns/hashtag.do")
	public String hashtag(
			HttpServletRequest request,
			HttpServletResponse response,
			@ModelAttribute("SnsHashtagVo") SnsHashtagVo snsHashtagVo,
			ModelMap model) throws Exception{
		
		/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(snsHashtagVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(snsHashtagVo.getPageUnit());
		paginationInfo.setPageSize(snsHashtagVo.getPageSize());

		snsHashtagVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		snsHashtagVo.setLastIndex(paginationInfo.getLastRecordIndex());
		snsHashtagVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List<SnsHashtagVo> snsHashtagList = snsSvc.getSnsHashtagList(snsHashtagVo);
		
		int totCnt = snsSvc.getSnsHashtagListCnt(snsHashtagVo);
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("snsHashtagList", snsHashtagList);
		
		return "injeinc/boffice/ex/sns/hashtag";
	}
	
	@RequestMapping(value="/boffice/ex/sns/doInsertHashtagAx.do")
	public void doInsertHashtagAx(
			HttpServletRequest request,
			HttpServletResponse response,
			@ModelAttribute("SnsHashtagVo") SnsHashtagVo snsHashtagVo,
			ModelMap model) throws Exception{
		
		snsSvc.insertHashtag(snsHashtagVo);
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", "true");
		jsonView.render(jsonMap, request, response);
		
	}

	@RequestMapping(value="/boffice/ex/sns/deleteHashtagAx.do")
	public void deleteHashtagAx(
			HttpServletRequest request,
			HttpServletResponse response,
			@ModelAttribute("SnsHashtagVo") SnsHashtagVo snsHashtagVo,
			ModelMap model) throws Exception{
		
		snsSvc.deleteHashtag(snsHashtagVo);
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", "true");
		jsonView.render(jsonMap, request, response);
		
	}

	@RequestMapping(value="/boffice/ex/sns/prohibitWord.do")
	public String prohibitWord(
			HttpServletRequest request,
			HttpServletResponse response,
			@ModelAttribute("SnsProhibitWordVo") SnsProhibitWordVo snsProhibitWordVo,
			ModelMap model) throws Exception{
		
		/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(snsProhibitWordVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(snsProhibitWordVo.getPageUnit());
		paginationInfo.setPageSize(snsProhibitWordVo.getPageSize());

		snsProhibitWordVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		snsProhibitWordVo.setLastIndex(paginationInfo.getLastRecordIndex());
		snsProhibitWordVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List<SnsProhibitWordVo> snsProhibitWordList = snsSvc.getSnsProhibitWordList(snsProhibitWordVo);
		
		int totCnt = snsSvc.getSnsProhibitWordListCnt(snsProhibitWordVo);
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("snsProhibitWordList", snsProhibitWordList);
		
		return "injeinc/boffice/ex/sns/prohibitWord";
	}

	@RequestMapping(value="/boffice/ex/sns/doInsertProhibitWordAx.do")
	public void doInsertProhibitWordAx(
			HttpServletRequest request,
			HttpServletResponse response,
			@ModelAttribute("SnsProhibitWordVo") SnsProhibitWordVo snsProhibitWordVo,
			ModelMap model) throws Exception{
		
		snsSvc.insertProhibitWord(snsProhibitWordVo);
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", "true");
		jsonView.render(jsonMap, request, response);
		
	}

	@RequestMapping(value="/boffice/ex/sns/deleteProhibitWordAx.do")
	public void deleteProhibitWordAx(
			HttpServletRequest request,
			HttpServletResponse response,
			@ModelAttribute("SnsProhibitWordVo") SnsProhibitWordVo snsProhibitWordVo,
			ModelMap model) throws Exception{
		
		snsSvc.deleteProhibitWord(snsProhibitWordVo);
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", "true");
		jsonView.render(jsonMap, request, response);
		
	}
	
	@RequestMapping(value="/boffice/ex/sns/searchKeyword.do")
	public String searchKeyword(
			HttpServletRequest request,
			HttpServletResponse response,
			@ModelAttribute("SnsSearchKeywordVo") SnsSearchKeywordVo snsSearchKeywordVo,
			ModelMap model) throws Exception{
		
		/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(snsSearchKeywordVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(snsSearchKeywordVo.getPageUnit());
		paginationInfo.setPageSize(snsSearchKeywordVo.getPageSize());

		snsSearchKeywordVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		snsSearchKeywordVo.setLastIndex(paginationInfo.getLastRecordIndex());
		snsSearchKeywordVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List<SnsSearchKeywordVo> snsSearchKeywordList = snsSvc.getSnsSearchKeywordList(snsSearchKeywordVo);
		
		int totCnt = snsSvc.getSnsSearchKeywordListCnt(snsSearchKeywordVo);
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("snsSearchKeywordList", snsSearchKeywordList);
		
		return "injeinc/boffice/ex/sns/searchKeyword";
	}
	
	@RequestMapping(value="/boffice/ex/sns/doInsertSearchKeywordAx.do")
	public void doInsertSearchKeywordAx(
			HttpServletRequest request,
			HttpServletResponse response,
			@ModelAttribute("SnsSearchKeywordVo") SnsSearchKeywordVo snsSearchKeywordVo,
			ModelMap model) throws Exception{
		
		snsSvc.insertSearchKeyword(snsSearchKeywordVo);
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", "true");
		jsonView.render(jsonMap, request, response);
		
	}

	@RequestMapping(value="/boffice/ex/sns/deleteSearchKeywordAx.do")
	public void deleteSearchKeywordAx(
			HttpServletRequest request,
			HttpServletResponse response,
			@ModelAttribute("SnsSearchKeywordVo") SnsSearchKeywordVo snsSearchKeywordVo,
			ModelMap model) throws Exception{
		
		snsSvc.deleteSearchKeyword(snsSearchKeywordVo);
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", "true");
		jsonView.render(jsonMap, request, response);
		
	}

	@RequestMapping(value="/boffice/ex/sns/updateSnsByUseYnDo.do")
	public String updateSnsByUseYnDo(
			HttpServletRequest request,
			HttpServletResponse response,
			@ModelAttribute("SnsContentsVo") SnsContentsVo snsContentsVo,
			ModelMap model) throws Exception{
		
		
		snsSvc.updateSnsByUseYn(snsContentsVo);
		
		return "injeinc/boffice/ex/sns/updateSnsByUseYnDo";
	}

	@RequestMapping(value="/boffice/ex/sns/snsRecentlyCollect.do")
	public void snsRecentlyCollect(
			HttpServletRequest request,
			HttpServletResponse response,
			ModelMap model) throws Exception{
		
		snsRecentlyCollectScheduling.SnsRecentlyCollectMethod();
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", "true");
		jsonView.render(jsonMap, request, response);
	}
	
}