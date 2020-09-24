package egovframework.injeinc.foffice.nia.search.controller;

import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import egovframework.cmm.CmmLogCtr;
import egovframework.injeinc.foffice.nia.search.service.SearchSvc;
import egovframework.injeinc.foffice.nia.search.vo.SearchVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class SearchCtr extends CmmLogCtr{
	
	@Resource(name = "SearchSvc")
	private SearchSvc searchSvc;
	
	@RequestMapping(value = "/site/nia/search/search.do")
	public String Search(HttpServletRequest request, @ModelAttribute("SearchVO") SearchVO searchVO, ModelMap mode) throws Exception {
		
		return "injeinc/foffice/nia/search";
	}
	
	@RequestMapping("/site/{strDomain}/search/searchList.do")
	public String SearchList(@PathVariable("strDomain") String strDomain, HttpServletRequest request, @ModelAttribute("SearchVO") SearchVO searchVO, ModelMap model) throws Exception {
	
		searchVO.setArea("");
		//searchVO.setPageUnit(8);
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		//if(isMobile) {
		//	paginationInfo.setPageSize(5);
		//}else{
			paginationInfo.setPageSize(searchVO.getPageSize());
		//}
		
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		Map<String, Object> map = searchSvc.searchList(searchVO);
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "injeinc/foffice/nia/search";
	}
	
}
