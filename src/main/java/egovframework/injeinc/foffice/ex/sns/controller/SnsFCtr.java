package egovframework.injeinc.foffice.ex.sns.controller;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import egovframework.cmm.CmmLogCtr;
import egovframework.injeinc.foffice.ex.sns.service.SnsFSvc;
import egovframework.injeinc.foffice.ex.sns.vo.SnsContentsFVo;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class SnsFCtr extends CmmLogCtr {
	
	@Resource(name = "SnsFSvc")
	private SnsFSvc snsFSvc;
	
	
	@Autowired(required=true)
	private MappingJackson2JsonView jsonView;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/site/{strDomain}/ex/sns/snsList.do")
	public String snsList(HttpServletRequest request, @ModelAttribute("SnsContentsFVo") SnsContentsFVo snsContentsFVo, ModelMap model) throws Exception {
		
		model.addAttribute("hashtagList", snsFSvc.retrieveHashtagList());
		return "injeinc/foffice/ex/sns/snsList";
	}
	
	/* SNS호출 */
	@RequestMapping(value="/{strSitePath}/{strDomain}/ex/sns/snsDataAx.do")
	public void snsDataAx(
			HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("strDomain") String strDomain,
			@ModelAttribute("SnsContentsFVo") SnsContentsFVo snsContentsFVo,
			ModelMap model) throws Exception{
			
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(snsContentsFVo.getPageIndex());
			paginationInfo.setRecordCountPerPage(snsContentsFVo.getPageUnit());
			paginationInfo.setPageSize(snsContentsFVo.getPageSize());
			paginationInfo.setPageSize(snsContentsFVo.getRecordCountPerPage());
			paginationInfo.setRecordCountPerPage(snsContentsFVo.getRecordCountPerPage());
			
			snsContentsFVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			snsContentsFVo.setLastIndex(paginationInfo.getLastRecordIndex());
			snsContentsFVo.setSiteDomain(strDomain);
			
			HashMap<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("result", "true");
			jsonMap.put("snsData", snsFSvc.retrieveListSnsContents(snsContentsFVo));
			jsonView.render(jsonMap, request, response);

	}
}