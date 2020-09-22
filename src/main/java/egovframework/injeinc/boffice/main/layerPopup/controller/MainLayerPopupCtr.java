package egovframework.injeinc.boffice.main.layerPopup.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.util.WebUtils;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.Message;
import egovframework.cmm.service.EgovProperties;
import egovframework.injeinc.boffice.cn.site.service.SiteSvc;
import egovframework.injeinc.boffice.cn.site.vo.SiteVo;
import egovframework.injeinc.boffice.main.layerPopup.service.MainLayerPopupSvc;
import egovframework.injeinc.boffice.main.layerPopup.vo.MainLayerPopupVo;
import egovframework.injeinc.common.util.EgovFormBasedUUID;
import egovframework.injeinc.common.util.EgovStringUtil;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class MainLayerPopupCtr extends CmmLogCtr {
	
	@Resource(name = "MainLayerPopupSvc")
	private MainLayerPopupSvc mainLayerPopupSvc;
	
	@Resource(name = "SiteSvc")
	private SiteSvc siteSvc;
	
	@Autowired(required=true)
	private MappingJackson2JsonView jsonView;
	
	@RequestMapping("/boffice_noDeco/main/layerPopup/MainLayerPopupForm.do")
	public String MainLayerPopupForm(@ModelAttribute("MainLayerPopupVo") MainLayerPopupVo mainLayerPopupVo, ModelMap model) throws Exception {
		
		MainLayerPopupVo result = mainLayerPopupSvc.retrieveMainLayerPopup(mainLayerPopupVo);
		
		if(result != null) {
			result.setActionkey("modify");
			result.setPageIndex(mainLayerPopupVo.getPageIndex());
			result.setSearchSiteCd(mainLayerPopupVo.getSearchSiteCd());
			result.setSearchUse(mainLayerPopupVo.getSearchUse());
			result.setSearchKeyword(mainLayerPopupVo.getSearchKeyword());
			
			String mlStartDate = result.getMlStartDate();
			result.setMlStartDate1(mlStartDate.substring(0, 10));
			result.setMlStartDate2(mlStartDate.substring(11, 13));
			result.setMlStartDate3(mlStartDate.substring(14, 16));
			
			String mlEndDate = result.getMlEndDate();
			result.setMlEndDate1(mlEndDate.substring(0, 10));
			result.setMlEndDate2(mlEndDate.substring(11, 13));
			result.setMlEndDate3(mlEndDate.substring(14, 16));
			
			model.addAttribute("MainLayerPopupVo", result);
		}else{
			mainLayerPopupVo.setMlSitecd(mainLayerPopupVo.getSearchSiteCd());
			mainLayerPopupVo.setMlEndDate2("19");
			mainLayerPopupVo.setMlStyle("top:0px;left:0px;");
			mainLayerPopupVo.setActionkey("regist");
		}
		
		SiteVo siteVo = new SiteVo();
		model.addAttribute("siteList", siteSvc.selectListSiteAll(siteVo));
		
		model.addAttribute("hourVar", "00,01,02,03,04,05,06,07,08,09,10,11,12,13,14,15,16,17,18,19,20,21,22,23");
		model.addAttribute("minuteVar", "00,10,20,30,40,50");
		
		return "injeinc/boffice/main/layerPopup/main_layer_popup_form";
	}
	
	@RequestMapping("/boffice/main/layerPopup/MainLayerPopupReg.do")
	public String MainLayerPopupReg(HttpServletRequest request, @ModelAttribute("MainLayerPopupVo") MainLayerPopupVo mainLayerPopupVo, ModelMap model) throws Exception {

		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		mainLayerPopupVo.setRegId(userid);
		mainLayerPopupVo.setMlStartDate(mainLayerPopupVo.getMlStartDate1()+" "+mainLayerPopupVo.getMlStartDate2()+":"+mainLayerPopupVo.getMlStartDate3()+":00");
		mainLayerPopupVo.setMlEndDate(mainLayerPopupVo.getMlEndDate1()+" "+mainLayerPopupVo.getMlEndDate2()+":"+mainLayerPopupVo.getMlEndDate3()+":00");
		
		final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		
		MultipartFile file = multiRequest.getFile("dataFile");
		
		String SVC_MSGE = "";
		

		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
		String uploadPath = Message.getMessage("mainLayerPopup.file.upload.path");
		
		File saveFolder = new File(rootPath + uploadPath);
		if (!saveFolder.exists() || saveFolder.isFile()) saveFolder.mkdirs();
		
		if(file != null) {
			
			String fileName = file.getOriginalFilename();
			
			if(!EgovStringUtil.isEmpty(fileName)) {
				  
				debugLog(fileName+" : 파일명");
						
				int index = fileName.lastIndexOf(".");
				String fileExt = fileName.substring(index + 1);
				String fileSystemName = EgovFormBasedUUID.randomUUID().toString().toLowerCase()+"."+fileExt;	// 전자정부 UTIL 방법
			
				file.transferTo(new File(rootPath + uploadPath + fileSystemName));
	
				mainLayerPopupVo.setMlFilename(fileSystemName);
				mainLayerPopupSvc.registMainLayerPopup(mainLayerPopupVo);
				SVC_MSGE = Message.getMessage("100.message");
			
			}else{
				SVC_MSGE = "파일을 찾을수가 없습니다.";
			}
			
		}else{
			SVC_MSGE = "파일을 찾을수가 없습니다.";
		}

		StringBuffer addParam = new StringBuffer();
		addParam.append("?pageIndex=").append(mainLayerPopupVo.getPageIndex());
		addParam.append("&searchSiteCd=").append(mainLayerPopupVo.getMlSitecd());
		addParam.append("&searchUse=").append(mainLayerPopupVo.getSearchUse());
		addParam.append("&searchKeyword=").append(mainLayerPopupVo.getSearchKeyword());
		
		return alert("/boffice/main/layerPopup/MainLayerPopupList.do"+addParam.toString(), SVC_MSGE, model); 
	}
	
	@RequestMapping("/boffice/main/layerPopup/MainLayerPopupMod.do")
	public String MainLayerPopupMod(HttpServletRequest request, @ModelAttribute("MainLayerPopupVo") MainLayerPopupVo mainLayerPopupVo, ModelMap model) throws Exception {

		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		mainLayerPopupVo.setModId(userid);
		mainLayerPopupVo.setMlStartDate(mainLayerPopupVo.getMlStartDate1()+" "+mainLayerPopupVo.getMlStartDate2()+":"+mainLayerPopupVo.getMlStartDate3()+":00");
		mainLayerPopupVo.setMlEndDate(mainLayerPopupVo.getMlEndDate1()+" "+mainLayerPopupVo.getMlEndDate2()+":"+mainLayerPopupVo.getMlEndDate3()+":00");

		final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		
		MultipartFile file = multiRequest.getFile("dataFile");
		

		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
		String uploadPath = Message.getMessage("mainLayerPopup.file.upload.path");
		
		File saveFolder = new File(rootPath + uploadPath);
		if (!saveFolder.exists() || saveFolder.isFile()) saveFolder.mkdirs();
		
		if(file != null) {
			
			String fileName = file.getOriginalFilename();
			
			if(!EgovStringUtil.isEmpty(fileName)) {
			
				String oldFileName = mainLayerPopupVo.getMlFilename();
				
				if(!oldFileName.equals("")) {
	
					File fileDelete = new File(rootPath + uploadPath + oldFileName);
					
					fileDelete.delete();
				}
				debugLog(fileName+" : 파일명");
						
				int index = fileName.lastIndexOf(".");
				String fileExt = fileName.substring(index + 1);
				String fileSystemName = EgovFormBasedUUID.randomUUID().toString().toLowerCase()+"."+fileExt;	// 전자정부 UTIL 방법
			
				file.transferTo(new File(rootPath + uploadPath + fileSystemName));
	
				mainLayerPopupVo.setMlFilename(fileSystemName);
				
			}
		}
		
		mainLayerPopupSvc.modifyMainLayerPopup(mainLayerPopupVo);
		
		StringBuffer addParam = new StringBuffer();
		addParam.append("?pageIndex=").append(mainLayerPopupVo.getPageIndex());
		addParam.append("&searchSiteCd=").append(mainLayerPopupVo.getMlSitecd());
		addParam.append("&searchUse=").append(mainLayerPopupVo.getSearchUse());
		addParam.append("&searchKeyword=").append(mainLayerPopupVo.getSearchKeyword());

		String SVC_MSGE = Message.getMessage("401.message");
		return alert("/boffice/main/layerPopup/MainLayerPopupList.do"+addParam.toString(), SVC_MSGE, model);
	}
	
	@RequestMapping("/boffice/main/layerPopup/MainLayerPopupRmv.do")
	public String MainLayerPopupRmv(HttpServletRequest request, @ModelAttribute("MainLayerPopupVo") MainLayerPopupVo mainLayerPopupVo, ModelMap model) throws Exception {

		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		mainLayerPopupVo.setModId(userid);
		
		mainLayerPopupSvc.removeMainLayerPopup(mainLayerPopupVo);
		
		StringBuffer addParam = new StringBuffer();
		addParam.append("?pageIndex=").append(mainLayerPopupVo.getPageIndex());
		addParam.append("&searchSiteCd=").append(mainLayerPopupVo.getSearchSiteCd());
		addParam.append("&searchUse=").append(mainLayerPopupVo.getSearchUse());
		addParam.append("&searchKeyword=").append(mainLayerPopupVo.getSearchKeyword());
		
		String SVC_MSGE = Message.getMessage("501.message");
		return alert("/boffice/main/layerPopup/MainLayerPopupList.do"+addParam.toString(), SVC_MSGE, model);
	}
	
	@RequestMapping("/boffice/main/layerPopup/MainLayerPopupList.do")
	public String MainLayerPopupList(@ModelAttribute("MainLayerPopupVo") MainLayerPopupVo mainLayerPopupVo, ModelMap model) throws Exception {
		//사이트목록
		List siteList = siteSvc.selectListSiteAll(null);
		
		//검색조건에 사이트 정보가 없을시 첫번째 항목을 default로 변경
		if(mainLayerPopupVo.getSearchSiteCd()==null || mainLayerPopupVo.getSearchSiteCd().equals("")) {
			if (siteList!=null && siteList.size()>0) {
				SiteVo siteVO = (SiteVo)siteList.get(0);
				mainLayerPopupVo.setSearchSiteCd(siteVO.getSiteCd());
			}
		}
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mainLayerPopupVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(mainLayerPopupVo.getPageUnit());
		paginationInfo.setPageSize(mainLayerPopupVo.getPageSize());
		
		mainLayerPopupVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mainLayerPopupVo.setLastIndex(paginationInfo.getLastRecordIndex());
		mainLayerPopupVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		Map<String, Object> map = mainLayerPopupSvc.retrievePagMainLayerPopup(mainLayerPopupVo); 
		
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("siteList", siteList);
		
		return "injeinc/boffice/main/layerPopup/main_layer_popup_list";
	}
	
	@RequestMapping("/boffice/main/layerPopup/MainLayerPopupMlUseChangeAx.do")
	public void MainLayerPopupMlUseChangeAx(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("MainLayerPopupVo") MainLayerPopupVo mainLayerPopupVo) throws Exception {
		
		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		mainLayerPopupVo.setModId(userid);
		
		String mlIdx = mainLayerPopupVo.getMlIdx();
		String mlUse = mainLayerPopupVo.getMlUse();
		
		boolean result = false;
		String message = "";
		
		if(!mlIdx.equals("") && !mlUse.equals("")) {
			mainLayerPopupSvc.modifyMainLayerPopupMlUse(mainLayerPopupVo);
			message = "수정에 성공하였습니다.";
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