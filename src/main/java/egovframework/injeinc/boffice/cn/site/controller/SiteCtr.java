package egovframework.injeinc.boffice.cn.site.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import egovframework.cmm.GlobalConst;
import egovframework.cmm.Message;
import egovframework.cmm.service.EgovProperties;
import egovframework.injeinc.boffice.cn.common.Util;
import egovframework.injeinc.boffice.cn.site.service.SiteSvc;
import egovframework.injeinc.boffice.cn.site.vo.SiteVo;
import egovframework.injeinc.boffice.sy.board.vo.CmsBbsVo;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import egovframework.cmm.CmmLogCtr;

@Controller
public class SiteCtr extends CmmLogCtr{
	
	@Resource(name = "SiteSvc")
	private SiteSvc siteSvc;
	
	@Autowired(required=true)
	private MappingJackson2JsonView jsonView;
	
	//리스트
	@RequestMapping(value= "/boffice/cn/site/site_list.do")
	public String siteList(
			HttpServletRequest request,
			@ModelAttribute("SiteVo") SiteVo siteVo,
			ModelMap model) throws Exception {
			
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(siteVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(siteVo.getPageUnit());
		paginationInfo.setPageSize(siteVo.getPageSize());
		
		siteVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		siteVo.setLastIndex(paginationInfo.getLastRecordIndex());
		siteVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		int totCnt = siteSvc.selectSiteListTotCnt(siteVo);
		paginationInfo.setTotalRecordCount(totCnt);
		
		List<SiteVo> codeList = siteSvc.selectComSiteCode(siteVo);
		List<SiteVo> retrieveListSite = siteSvc.retrieveListSite(siteVo);
		
		if(model != null){
			model.addAttribute("totCnt",totCnt);
			model.addAttribute("paginationInfo", paginationInfo);
			model.addAttribute("codeList", codeList);
			model.addAttribute("resultList", retrieveListSite);
		}
		return GlobalConst.SITE_ROOT_BOFFICE_PATH + "/cn/site/site_list";
	}
	
	//등록
	@RequestMapping("/boffice_noDeco/cn/site/site_form.do")
	public String siteForm(
			HttpServletRequest request,
			@ModelAttribute("SiteVo") SiteVo siteVo,
			ModelMap model) throws Exception {
		if(model != null){
			model.addAttribute("menuGubun", siteVo.getSiteMenu());
		}
		List<SiteVo> codeList = siteSvc.selectComSiteCode(siteVo);
		
		if(siteVo.getSiteMenu().equals("update")) {	
			SiteVo view = siteSvc.retrieveSite(Integer.parseInt(siteVo.getSiteIdx()));
			view.setSearchCondition(siteVo.getSearchCondition());
			view.setSearchKeyword(siteVo.getSearchKeyword());
			view.setSiteMenu(siteVo.getSiteMenu());
			if(model != null){
				model.addAttribute("SiteVo",view);
			}
		}
		if(model != null){
			model.addAttribute("codeList", codeList);
		}
		return GlobalConst.SITE_ROOT_BOFFICE_PATH + "/cn/site/site_form";
	}
	
	//저장
	@RequestMapping("/boffice/cn/site/site_reg.do")
	public String siteReg(
			HttpServletRequest request,
			@ModelAttribute("SiteVo") SiteVo siteVo,
			ModelMap model) throws Exception {
		
		HttpSession ses = request.getSession();
		String regId = (String)ses.getAttribute("SesUserId");		
		
		String folderPath = "/" + siteVo.getSitePath() + "/"+siteVo.getSiteCd()+"/";
		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
		
		if(rootPath.equals("")){
			rootPath = request.getSession().getServletContext().getRealPath("/");
		}
		String realPath = "";
		realPath =  rootPath + folderPath;
		File file = new File(realPath);
		
		String SVC_MSGE = "";
		
		if(!file.isDirectory()) {
			file.mkdir();
			siteVo.setRegId(regId);
			siteSvc.registSite(siteVo);
			SVC_MSGE = Message.getMessage("201.message"); //등록 성공
		}else{
			SVC_MSGE = "해당 디렉토리명은 이미 존재합니다.";
		}
		
		return alert("/boffice/cn/site/site_list.do", SVC_MSGE, model);
	}
	
	//상세
	@RequestMapping("/boffice/cn/site/site_view.do")
	public String siteView(
			HttpServletRequest request,
			@ModelAttribute("SiteVo") SiteVo siteVo,
			ModelMap model) throws Exception {
		
		List<SiteVo> codeList = siteSvc.selectComSiteCode(siteVo);
		if(model != null){
			model.addAttribute("codeList", codeList);
		}
		
		SiteVo view = siteSvc.retrieveSite(Integer.parseInt(siteVo.getSiteIdx()));
		view.setSearchCondition(siteVo.getSearchCondition());
		view.setSearchKeyword(siteVo.getSearchKeyword());
		view.setPageIndex(siteVo.getPageIndex());
		if(model != null){
			model.addAttribute("SiteVo",view);
		}
		
		return GlobalConst.SITE_ROOT_BOFFICE_PATH + "/cn/site/site_view"; 
	}
	
	//수정
	@RequestMapping("/boffice/cn/site/site_mod.do")
	public String siteUpdate(
			HttpServletRequest request,
			@ModelAttribute("SiteVo") SiteVo siteVo,
			ModelMap model) throws Exception {
		
		HttpSession ses = request.getSession();
		String modId = (String)ses.getAttribute("SesUserId");		
		
		siteVo.setModId(modId);
		siteSvc.modifySite(siteVo);
		String SVC_MSGE = Message.getMessage("401.message"); //수정 성공
		
		return alert("/boffice/cn/site/site_list.do", SVC_MSGE, model);
	}
	
	//삭제
	@RequestMapping("/boffice/cn/site/site_rmv.do")
	public String siteRmv(
		HttpServletRequest request,
		@ModelAttribute("SiteVo") SiteVo siteVo,
		ModelMap model) throws Exception {

		siteSvc.removeSite(Integer.parseInt(siteVo.getSiteIdx()));
		String SVC_MSGE = Message.getMessage("501.message"); //삭제 성공
		
		return alert("/boffice/cn/site/site_list.do", SVC_MSGE, model);
	}
	
	@RequestMapping("/boffice/cn/site/site_cnt_ax.do")
	public void CmsBbsCntAx(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("CmsBbsVo") SiteVo siteVo) throws Exception {

		String siteCd = siteVo.getSiteCd();
		
		boolean result = false;
		String message = "";
		int resultCnt = 0;
		
		if(!siteCd.equals("")) {
			
			resultCnt = siteSvc.retrieveSiteCnt(siteVo);
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