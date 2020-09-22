package egovframework.injeinc.boffice.main.popupZone.controller;

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
import egovframework.injeinc.boffice.main.popupZone.service.MainPopupZoneSvc;
import egovframework.injeinc.boffice.main.popupZone.vo.MainPopupZoneVo;
import egovframework.injeinc.boffice.sy.code.service.SyCodeSvc;
import egovframework.injeinc.boffice.sy.code.vo.CmmCodeVo;
import egovframework.injeinc.common.util.DateUtil;
import egovframework.injeinc.common.util.EgovFormBasedUUID;
import egovframework.injeinc.common.util.EgovStringUtil;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


@Controller
public class MainPopupZoneCtr extends CmmLogCtr {
	
	@Resource(name = "MainPopupZoneSvc")
	private MainPopupZoneSvc mainPopupZoneSvc;
	
	@Resource(name = "SiteSvc")
	private SiteSvc siteSvc;
	
	@Resource(name = "SyCodeSvc")
	private SyCodeSvc codeSvc;
	
	@Autowired(required=true)
	private MappingJackson2JsonView jsonView;
	
	/**
	 * 설명 : 운영관리 > 팝업존 관리 > 목록
	 * @param mainPopupZoneVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/boffice/main/popupZone/MainPopupZoneList.do")
	public String MainPopupZoneList(HttpServletRequest request, @ModelAttribute("MainPopupZoneVo") MainPopupZoneVo mainPopupZoneVo, ModelMap model) throws Exception {
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(mainPopupZoneVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(mainPopupZoneVo.getPageUnit());
		paginationInfo.setPageSize(mainPopupZoneVo.getPageSize());
		
		mainPopupZoneVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		mainPopupZoneVo.setLastIndex(paginationInfo.getLastRecordIndex());
		mainPopupZoneVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		String SesUserRoleIdx = (String) WebUtils.getSessionAttribute(request, "SesUserRoleIdx");
		String SesUserPermCd = (String) WebUtils.getSessionAttribute(request, "SesUserPermCd");
		
		
		Map<String, Object> map = mainPopupZoneSvc.retrievePagMainPopupZone(mainPopupZoneVo); 
		
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);
		
		SiteVo siteVo = new SiteVo();
		
		if(!SesUserPermCd.equals("05010000")){ //슈퍼관리자일 경우 제외
			siteVo.setRoleIdx(SesUserRoleIdx);
		}
		model.addAttribute("siteList", siteSvc.selectListSiteAll(siteVo));
		
		String nowDate = DateUtil.getCurrentDate("yyyyMMddHHmm");
		model.addAttribute("nowDate", nowDate);
		
		/** 팝업존 코드리스트(메인/서브)  */
		CmmCodeVo popupZoneCode = new CmmCodeVo();
		List<CmmCodeVo> popupZonecodeList = codeSvc.selectPopupZoneCodeList(popupZoneCode);
		model.addAttribute("popupZonecodeList", popupZonecodeList);
		
		return "injeinc/boffice/main/popupZone/main_popup_zone_list";
	}
	
	
	@RequestMapping("/boffice_noDeco/main/popupZone/MainPopupZoneSite.do")
	public String MainPopupZoneSite(@ModelAttribute("MainPopupZoneVo") MainPopupZoneVo mainPopupZoneVo, ModelMap model) throws Exception {
		MainPopupZoneVo result = mainPopupZoneSvc.retrieveMainPopupZone(mainPopupZoneVo);
		
		if(result != null) {
			result.setActionkey("modify");
			result.setPageIndex(mainPopupZoneVo.getPageIndex());
			result.setPageUnit(mainPopupZoneVo.getPageUnit());
			result.setSearchSiteCd(mainPopupZoneVo.getSearchSiteCd());
			result.setSearchUse(mainPopupZoneVo.getSearchUse());
			result.setSearchKeyword(mainPopupZoneVo.getSearchKeyword());
			
			String mzStartDate = result.getMzStartDate();
			result.setMzStartDate1(mzStartDate.substring(0, 10));
			result.setMzStartDate2(mzStartDate.substring(11, 13));
			result.setMzStartDate3(mzStartDate.substring(14, 16));
			
			String mzEndDate = result.getMzEndDate();
			result.setMzEndDate1(mzEndDate.substring(0, 10));
			result.setMzEndDate2(mzEndDate.substring(11, 13));
			result.setMzEndDate3(mzEndDate.substring(14, 16));
			
			model.addAttribute("MainPopupZoneVo", result);
		}else{
			String nowDate = DateUtil.getCurrentDate();
			String date7next = DateUtil.add(nowDate, 8);
			mainPopupZoneVo.setMzStartDate1(nowDate);
			mainPopupZoneVo.setMzEndDate1(date7next);
			mainPopupZoneVo.setMzLink("http://");
			mainPopupZoneVo.setMzUse("Y");
			mainPopupZoneVo.setActionkey("regist");
		}

		/** 팝업존 코드리스트(메인/서브)  */
		CmmCodeVo popupZoneCode = new CmmCodeVo();
		List<CmmCodeVo> popupZonecodeList = codeSvc.selectPopupZoneCodeList(popupZoneCode);
		model.addAttribute("popupZonecodeList", popupZonecodeList);
		
		SiteVo siteVo = new SiteVo();
		model.addAttribute("siteList", siteSvc.selectListSiteAll(siteVo));
		
		model.addAttribute("hourVar", "00,01,02,03,04,05,06,07,08,09,10,11,12,13,14,15,16,17,18,19,20,21,22,23");
		model.addAttribute("minuteVar", "00,10,20,30,40,50");
		
		return "injeinc/boffice/main/popupZone/main_popup_zone_site";
	}
	
	/**
	 * 설명 : 운영관리 > 팝업존 관리 > 상세화면
	 * @param mainPopupZoneVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/boffice_noDeco/main/popupZone/MainPopupZoneForm.do")
	public String MainPopupZoneForm(HttpServletRequest request,@ModelAttribute("MainPopupZoneVo") MainPopupZoneVo mainPopupZoneVo, ModelMap model) throws Exception {
		MainPopupZoneVo result = mainPopupZoneSvc.retrieveMainPopupZone(mainPopupZoneVo);
		
		String SesUserRoleIdx = (String) WebUtils.getSessionAttribute(request, "SesUserRoleIdx");
		String SesUserPermCd = (String) WebUtils.getSessionAttribute(request, "SesUserPermCd");
		
		if(result != null) {
			result.setActionkey("modify");
			result.setPageIndex(mainPopupZoneVo.getPageIndex());
			result.setPageUnit(mainPopupZoneVo.getPageUnit());
			result.setSearchSiteCd(mainPopupZoneVo.getSearchSiteCd());
			result.setSearchUse(mainPopupZoneVo.getSearchUse());
			result.setSearchKeyword(mainPopupZoneVo.getSearchKeyword());
			
			String mzStartDate = result.getMzStartDate();
			result.setMzStartDate1(mzStartDate.substring(0, 10));
			result.setMzStartDate2(mzStartDate.substring(11, 13));
			result.setMzStartDate3(mzStartDate.substring(14, 16));
			
			String mzEndDate = result.getMzEndDate();
			result.setMzEndDate1(mzEndDate.substring(0, 10));
			result.setMzEndDate2(mzEndDate.substring(11, 13));
			result.setMzEndDate3(mzEndDate.substring(14, 16));
			
			model.addAttribute("MainPopupZoneVo", result);
		}else{
			String nowDate = DateUtil.getCurrentDate();
			String date7next = DateUtil.add(nowDate, 8);
			mainPopupZoneVo.setMzStartDate1(nowDate);
			mainPopupZoneVo.setMzEndDate1(date7next);
			mainPopupZoneVo.setMzLink("http://");
			mainPopupZoneVo.setMzUse("Y");
			mainPopupZoneVo.setActionkey("regist");
		}

		/** 팝업존 코드리스트(메인/서브)  */
		CmmCodeVo popupZoneCode = new CmmCodeVo();
		List<CmmCodeVo> popupZonecodeList = codeSvc.selectPopupZoneCodeList(popupZoneCode);
		model.addAttribute("popupZonecodeList", popupZonecodeList);
		
		SiteVo siteVo = new SiteVo();
		
		if(!SesUserPermCd.equals("05010000")){ //슈퍼관리자일 경우 제외
			siteVo.setRoleIdx(SesUserRoleIdx);
		}
		model.addAttribute("siteList", siteSvc.selectListSiteAll(siteVo));
		
		model.addAttribute("hourVar", "00,01,02,03,04,05,06,07,08,09,10,11,12,13,14,15,16,17,18,19,20,21,22,23");
		model.addAttribute("minuteVar", "00,10,20,30,40,50");
		
		return "injeinc/boffice/main/popupZone/main_popup_zone_form";
	}
	
	@RequestMapping("/boffice/main/popupZone/MainPopupZoneReg.do")
	public String MainPopupZoneReg(HttpServletRequest request, @ModelAttribute("MainPopupZoneVo") MainPopupZoneVo mainPopupZoneVo, ModelMap model) throws Exception {
		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		mainPopupZoneVo.setRegId(userid);

		String[] siteCdArray = request.getParameterValues("mzSitecd");

		if(siteCdArray != null) {
			StringBuffer sb = new StringBuffer();
			sb.append("｜");
			for(int i = 0; i < siteCdArray.length ; i++) {
				if(i > 0) {
					sb.append("｜");
				}
				sb.append(siteCdArray[i]);
			}
			sb.append("｜");
			
			mainPopupZoneVo.setMzSitecd(sb.toString());
		}else{
			mainPopupZoneVo.setMzSitecd("｜｜");
		}
		
		/* 등록/수정 후 선택한 사이트 쪽으로 */
		String searchSiteCd=mainPopupZoneVo.getSearchSiteCd();
		if(siteCdArray != null && mainPopupZoneVo.getMzSitecd().indexOf(searchSiteCd)<=-1){
			searchSiteCd=siteCdArray[0];
		}
		
		mainPopupZoneVo.setMzStartDate(mainPopupZoneVo.getMzStartDate1()+" "+mainPopupZoneVo.getMzStartDate2()+":"+mainPopupZoneVo.getMzStartDate3()+":00");
		mainPopupZoneVo.setMzEndDate(mainPopupZoneVo.getMzEndDate1()+" "+mainPopupZoneVo.getMzEndDate2()+":"+mainPopupZoneVo.getMzEndDate3()+":00");
		
		final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		
		MultipartFile file = multiRequest.getFile("dataFile");
		
		String SVC_MSGE = "";
		

		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
		String uploadPath = Message.getMessage("mainPopupZone.file.upload.path");
		
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
	
				mainPopupZoneVo.setMzFilename(fileSystemName);
				mainPopupZoneSvc.registMainPopupZone(mainPopupZoneVo);
				SVC_MSGE = Message.getMessage("100.message");
			
			}else{
				SVC_MSGE = "파일을 찾을수가 없습니다.";
			}
			
		}else{
			SVC_MSGE = "파일을 찾을수가 없습니다.";
		}
		
		StringBuffer addParam = new StringBuffer();
		addParam.append("?pageIndex=").append(mainPopupZoneVo.getPageIndex());
		addParam.append("&pageUnit=").append(mainPopupZoneVo.getPageUnit());
		addParam.append("&searchSiteCd=").append(searchSiteCd);
		addParam.append("&searchUse=").append(mainPopupZoneVo.getSearchUse());
		addParam.append("&searchKeyword=").append(mainPopupZoneVo.getSearchKeyword());
		addParam.append("&code=").append(mainPopupZoneVo.getCode());

		return alert("/boffice/main/popupZone/MainPopupZoneList.do"+addParam.toString(), SVC_MSGE, model); 
	}

	@RequestMapping("/boffice/main/popupZone/MainPopupZoneMod.do")
	public String MainPopupZoneMod(HttpServletRequest request, @ModelAttribute("MainPopupZoneVo") MainPopupZoneVo mainPopupZoneVo, ModelMap model) throws Exception {
		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		mainPopupZoneVo.setModId(userid);

		String[] siteCdArray = request.getParameterValues("mzSitecd");

		if(siteCdArray != null) {
			StringBuffer sb = new StringBuffer();
			sb.append("｜");
			for(int i = 0; i < siteCdArray.length ; i++) {
				if(i > 0) {
					sb.append("｜");
				}
				sb.append(siteCdArray[i]);
			}
			sb.append("｜");
			
			mainPopupZoneVo.setMzSitecd(sb.toString());
		}else{
			mainPopupZoneVo.setMzSitecd("｜｜");
		}
		
		/* 등록/수정 후 선택한 사이트 쪽으로 */
		String searchSiteCd=mainPopupZoneVo.getSearchSiteCd();
		if(siteCdArray != null && mainPopupZoneVo.getMzSitecd().indexOf(searchSiteCd)<=-1){
			searchSiteCd=siteCdArray[0];
		}
		
		mainPopupZoneVo.setMzStartDate(mainPopupZoneVo.getMzStartDate1()+" "+mainPopupZoneVo.getMzStartDate2()+":"+mainPopupZoneVo.getMzStartDate3()+":00");
		mainPopupZoneVo.setMzEndDate(mainPopupZoneVo.getMzEndDate1()+" "+mainPopupZoneVo.getMzEndDate2()+":"+mainPopupZoneVo.getMzEndDate3()+":00");

		final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		
		MultipartFile file = multiRequest.getFile("dataFile");
		
		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
		String uploadPath = Message.getMessage("mainPopupZone.file.upload.path");
		
		File saveFolder = new File(rootPath + uploadPath);
		if (!saveFolder.exists() || saveFolder.isFile()) saveFolder.mkdirs();
		
		if(file != null) {
			
			String fileName = file.getOriginalFilename();
			
			if(!EgovStringUtil.isEmpty(fileName)) {
			
				String oldFileName = mainPopupZoneVo.getMzFilename();
				
				if(!oldFileName.equals("")) {
	
					File fileDelete = new File(rootPath + uploadPath + oldFileName);
					
					fileDelete.delete();
					
				}
				debugLog(fileName+" : 파일명");
						
				int index = fileName.lastIndexOf(".");
				String fileExt = fileName.substring(index + 1);
				String fileSystemName = EgovFormBasedUUID.randomUUID().toString().toLowerCase()+"."+fileExt;	// 전자정부 UTIL 방법
			
				file.transferTo(new File(rootPath + uploadPath + fileSystemName));
	
				mainPopupZoneVo.setMzFilename(fileSystemName);
				
			}
		}
		
		mainPopupZoneSvc.modifyMainPopupZone(mainPopupZoneVo);
		
		StringBuffer addParam = new StringBuffer();
		addParam.append("?pageIndex=").append(mainPopupZoneVo.getPageIndex());
		addParam.append("&pageUnit=").append(mainPopupZoneVo.getPageUnit());
		addParam.append("&searchSiteCd=").append(mainPopupZoneVo.getSearchSiteCd());
		addParam.append("&searchUse=").append(mainPopupZoneVo.getSearchUse());
		addParam.append("&searchKeyword=").append(mainPopupZoneVo.getSearchKeyword());
		addParam.append("&code=").append(mainPopupZoneVo.getCode());

		String SVC_MSGE = Message.getMessage("401.message");
		return alert("/boffice/main/popupZone/MainPopupZoneList.do"+addParam.toString(), SVC_MSGE, model);
	}
	
	@RequestMapping("/boffice/main/popupZone/MainPopupZoneRmv.do")
	public String MainPopupZoneRmv(HttpServletRequest request, @ModelAttribute("MainPopupZoneVo") MainPopupZoneVo mainPopupZoneVo, ModelMap model) throws Exception {

		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		mainPopupZoneVo.setModId(userid);
		
		mainPopupZoneSvc.removeMainPopupZone(mainPopupZoneVo);
		
		StringBuffer addParam = new StringBuffer();
		addParam.append("?pageIndex=").append(mainPopupZoneVo.getPageIndex());
		addParam.append("&pageUnit=").append(mainPopupZoneVo.getPageUnit());
		addParam.append("&searchSiteCd=").append(mainPopupZoneVo.getSearchSiteCd());
		addParam.append("&searchUse=").append(mainPopupZoneVo.getSearchUse());
		addParam.append("&searchKeyword=").append(mainPopupZoneVo.getSearchKeyword());
		addParam.append("&code=").append(mainPopupZoneVo.getCode());
		
		String SVC_MSGE = Message.getMessage("501.message");
		return alert("/boffice/main/popupZone/MainPopupZoneList.do"+addParam.toString(), SVC_MSGE, model);
	}

	@RequestMapping("/boffice/main/popupZone/MainPopupZoneMzUseChangeAx.do")
	public void MainPopupZoneMlUseChangeAx(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("MainPopupZoneVo") MainPopupZoneVo mainPopupZoneVo) throws Exception {
	
		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		mainPopupZoneVo.setModId(userid);
		
		String mzIdx = mainPopupZoneVo.getMzIdx();
		String mzUse = mainPopupZoneVo.getMzUse();
		
		boolean result = false;
		String message = "";
		
		if(!mzIdx.equals("") && !mzUse.equals("")) {
			mainPopupZoneSvc.modifyMainPopupZoneMzUse(mainPopupZoneVo);
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
	
	@RequestMapping("/boffice/popupZone/MainPopupZoneSortUpAx.do")
	public void MainPopupZoneSortUpAx(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("MainPopupZoneVo") MainPopupZoneVo mainPopupZoneVo) throws Exception {
		
		MainPopupZoneVo resultVo = mainPopupZoneSvc.retrieveMainPopupZone(mainPopupZoneVo);
		
		boolean result = false;
		String message = "";
		
		if(resultVo != null) {
			int mzSort = resultVo.getMzSort();
			
			if(mzSort > 1) {
				mainPopupZoneSvc.modifyMainPopupZoneSortUp(resultVo);
				message = "수정에 성공하였습니다.";
				result = true;
			}else{
				message = "더 이상 순위를 올릴 수 없습니다.";
			}
		}else{
			message = "잘못된 접근가 없습니다.";
		}

		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", result);
		jsonMap.put("message", message);
		jsonView.render(jsonMap, request, response);
			
	}

	@RequestMapping("/boffice/popupZone/MainPopupZoneSortDownAx.do")
	public void MainPopupZoneSortDownAx(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("MainPopupZoneVo") MainPopupZoneVo mainPopupZoneVo) throws Exception {
		MainPopupZoneVo resultVo = mainPopupZoneSvc.retrieveMainPopupZone(mainPopupZoneVo);
		
		boolean result = false;
		String message = "";
		
		if(resultVo != null) {
			int mzSort = resultVo.getMzSort();
			int maxSort = mainPopupZoneSvc.retrieveMainPopupZoneMaxSort();
			
			if(maxSort > mzSort) {
				mainPopupZoneSvc.modifyMainPopupZoneSortDown(resultVo);
				message = "수정에 성공하였습니다.";
				result = true;
			}else{
				message = "더 이상 순위를 내릴 수 없습니다.";
			}
		}else{
			message = "잘못된 접근가 없습니다.";
		}

		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", result);
		jsonMap.put("message", message);
		jsonView.render(jsonMap, request, response);
	}

}