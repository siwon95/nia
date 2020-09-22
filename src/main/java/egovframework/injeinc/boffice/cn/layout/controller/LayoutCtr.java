package egovframework.injeinc.boffice.cn.layout.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.GlobalConst;
import egovframework.cmm.Message;
import egovframework.cmm.service.EgovProperties;
import egovframework.injeinc.boffice.cn.layout.service.LayoutSvc;
import egovframework.injeinc.boffice.cn.layout.vo.LayoutVo;
import egovframework.injeinc.boffice.cn.site.service.SiteSvc;
import egovframework.injeinc.boffice.cn.site.vo.SiteVo;
import egovframework.injeinc.boffice.cn.common.Util;


@Controller
public class LayoutCtr extends CmmLogCtr{
    
	@Resource(name = "SiteSvc")
	private SiteSvc siteSvc;
	
	@Resource(name = "LayoutSvc")
	private LayoutSvc layoutSvc;
	
	//리스트
	@SuppressWarnings("unchecked")
	@RequestMapping(value= "/boffice/cn/layout/layout_list.do")
	public String layoutList(
		HttpServletRequest request,
		@ModelAttribute("LayoutVo") LayoutVo layoutVo,
		ModelMap model) throws Exception {
		
		List<LayoutVo> selectListLayout = layoutSvc.retrieveListLayout(layoutVo);
		
		if(model != null){
			model.addAttribute("listLayout", selectListLayout);
		}
		
		return GlobalConst.SITE_ROOT_BOFFICE_PATH + "/cn/layout/layout_list";
	}
	
	//상세 및 수정 form
	@SuppressWarnings("unchecked")
	@RequestMapping(value= "/boffice_noDeco/cn/layout/layout_form.do")
	public String layoutForm(
		HttpServletRequest request,
		@ModelAttribute("LayoutVo") LayoutVo layoutVo,
		ModelMap model) throws Exception {
	    SiteVo siteVo = new SiteVo();
	    
		List<SiteVo> selectListSiteAll = siteSvc.selectListSiteAll(siteVo);
		
		if(layoutVo.getPageMode() != null && layoutVo.getPageMode().equals("update")){
			//System.out.println("layoutVo.getLayoutCode():"+layoutVo.getLayoutCode());
			LayoutVo result = layoutSvc.retrieveLayout(layoutVo);
			if(model != null){
				model.addAttribute("LayoutVo",result);
			}
		}
		
		if(model != null){	
			model.addAttribute("pageMode", layoutVo.getPageMode());
			model.addAttribute("listSiteAll", selectListSiteAll);
		}
		
		return GlobalConst.SITE_ROOT_BOFFICE_PATH + "/cn/layout/layout_form";
	}
	
	//저장
	@RequestMapping("/boffice/cn/layout/layout_reg.do")
	public String layoutReg(
		HttpServletRequest request,
		@ModelAttribute("LayoutVo") LayoutVo layoutVo,
		ModelMap model) throws Exception {
	
		HttpSession ses = request.getSession();
		String regId = (String)ses.getAttribute("SesUserId");		
		
		String filePath = "/WEB-INF/jsp/injeinc/site/" +layoutVo.getSiteCd()+"/";
		String decoFilePath = "/WEB-INF/common/decorator/web/"+layoutVo.getSiteCd()+"/";
		String fileName = layoutVo.getLayoutCode()+".jsp";
		
		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
	   		
   		if(rootPath.equals("")){
   			rootPath = request.getSession().getServletContext().getRealPath("/");
   		}
		
		//layoutContent parse start (2020.04.13)
   		//커스텀 선택시 해당경로에 직접 파일 생성이므로 예외처리
   		if(!"3".equals(layoutVo.getLayoutType())) {
   			String content = layoutVo.getLayoutContent();
   			String startDelimiter = "<jsp:include page=\"";
   			String endDelimiter = "\" flush=";
   			
   			String topDefault = "<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\"%>\n"
   									+ "<%@ page import=\"egovframework.injeinc.boffice.cn.common.Util\" %>\n"
   									+ "<%@ page import=\"java.util.List\" %>\n"
   									+ "<%@ page import=\"org.jdom2.Element\" %>\n"
   									+ "<%@ page import=\"org.springframework.web.context.WebApplicationContext\" %>\n"
   									+ "<%@ page import=\"org.springframework.web.context.support.WebApplicationContextUtils\" %>\n"
   									+ "<%@ page import=\"egovframework.injeinc.boffice.cn.common.UtilSvc\" %>\n"
   									+ "<%@ taglib uri=\"http://java.sun.com/jsp/jstl/core\" prefix=\"c\" %>\n"
   									+ "<%@ taglib uri=\"http://java.sun.com/jsp/jstl/functions\" prefix=\"fn\" %>\n"
   									+ "<c:set var=\"depth1\" value=\"${fn:substring(ssSortOrder,1,3)}\"/>\n"
   									+ "<c:set var=\"depth2\" value=\"${fn:substring(ssSortOrder,3,5)}\"/>\n"
   									+ "<c:set var=\"depth3\" value=\"${fn:substring(ssSortOrder,5,7)}\"/>\n";

   			String headDefault = "<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\"%>\n"
   								+ "<%@ page import=\"egovframework.injeinc.boffice.cn.common.Util\" %>\n"
   								+ "<%@ page import=\"java.util.List\" %>\n"	
   								+ "<%@ page import=\"org.jdom2.Element\" %>\n"
   								+ "<%@ taglib uri=\"http://java.sun.com/jsp/jstl/core\" prefix=\"c\" %>\n"
   								+ "<%@ taglib uri=\"http://java.sun.com/jsp/jstl/functions\" prefix=\"fn\" %>\n"
   								+ "<!DOCTYPE html>\n"
   								+ "<html lang=\"ko\">\n"
   								+ "<head>\n"
   								+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=euc-kr\">\n"
   								+ "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
   								+ "<meta name=\"viewport\" content=\"width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no\"/>\n"
   								+ "<c:if test=\"${!empty ssSearchEngine}\">\n"
   								+ "<meta name=\"keywords\" content = \"<c:out value='${ssSearchEngine}'/>\" />\n"
   								+ "</c:if>\n"
   								+ "<c:if test=\"${!empty ssDescriptionContent}\">\n"
   								+ "<meta name = \"description\" content=\"<c:out value='${ssDescriptionContent}'/>\" />\n"
   								+ "</c:if>\n"
   								+ "<title>\n"
   								+ "\t<c:if test=\"${ssSortOrder ne '1000000000000'}\">\n"
   								+ "\t\t<c:if test=\"${empty ssMenuTitle}\">\n"
   								+ "\t\t\t<c:out value=\"${ssMenuNm}\"/>\n"
   								+ "\t</c:if>\n"
   								+ "\t\t<c:if test=\"${!empty ssMenuTitle}\">\n"
   								+ "\t\t\t<c:out value=\"${ssMenuTitle}\"/>\n"
   								+ "\t\t</c:if>\n"
   								+ "\t</c:if>\n"
   								+ "\t<c:out value=\"${ssSiteNm}\"/>\n"
   								+ "</title>\n";

   			String bottomDefault = "<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\"%>\n"
   									+ "<%@ taglib uri=\"http://java.sun.com/jsp/jstl/core\" prefix=\"c\" %>\n"
   									+ "</body>\n"
   									+ "</html>\n";
   			
   			int start = -1, end = -1;
   			while (true) {
   			       start = content.indexOf(startDelimiter, end);
   			       end = content.indexOf(endDelimiter, start + startDelimiter.length());
   			       if (start != -1 && end != -1) {
   			    	   String filePathName = content.substring(start + startDelimiter.length(), end);
   			           int lastIndex = filePathName.lastIndexOf("/");
   			           String tempFileName = filePathName.substring(lastIndex+1);
   			           String tempFilePath = filePathName.substring(0, lastIndex+1);
   			           String tempContent = "";
   			           if(tempFileName.indexOf("top.jsp") > -1) {
   			        	   tempContent = topDefault + "<%-- 상단 include file --%>\n<%-- <div id=\"gnb\">\n\t<jsp:include page=\"/layout/module/gnb.jsp\" flush=\"true\"/>\n</div> --%>";
   			           } else if(tempFileName.indexOf("bottom.jsp") > -1) {
   			        	   tempContent = bottomDefault;
   			           } else if(tempFileName.indexOf("head.jsp") > -1){
   			        	   tempContent = headDefault;
   			           } else if(tempFileName.indexOf("sub_left.jsp") > -1) {
   			        	   tempContent = topDefault + "<%-- 컨텐츠 좌측 include file --%>\n<%-- <div id=\"lnb\">\n\t<jsp:include page=\"/layout/module/lnb.jsp\" flush=\"true\"/>\n</div> --%>";
   			           } else if(tempFileName.indexOf("sub_right.jsp") > -1) {
   			        	   tempContent = topDefault + "<%-- 컨텐츠 우측 include file --%>\n";
   			           } else {
   			        	tempContent = "<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\"%>\n"
   									+ "<%@ taglib uri=\"http://java.sun.com/jsp/jstl/core\" prefix=\"c\" %>\n";
   			           }
   			           Util util = new Util();
   			           util.fileWriteRootPath(request, tempFilePath, tempFileName, tempContent, rootPath);
   			       } else {
   			           break;
   			       }
   			}
   		}
		//layoutContent parse end
		
		
		Util util = new Util();
		//String fileChk = util.fileWriteRootPath(request, filePath, fileName, layoutVo.getLayoutContent(), EgovProperties.getProperty("WasServer.ROOT_PATH"));
		String fileChk = util.fileWriteRootPath(request, filePath, fileName, layoutVo.getLayoutContent(), rootPath);
		String fileChk2 = util.fileWriteRootPath(request, decoFilePath, fileName, layoutVo.getLayoutContent(), rootPath);
		if(fileChk != null && fileChk2 != null){
			if(fileChk.equals("yes")){
				layoutVo.setFilePath(filePath+fileName);
				layoutVo.setRegId(regId);
				layoutSvc.registLayout(layoutVo);
			}
		}
		
		String SVC_MSGE = Message.getMessage("201.message"); //등록 성공
		return alert("/boffice/cn/layout/layout_list.do", SVC_MSGE, model);
	}
	
	//수정
	@RequestMapping("/boffice/cn/layout/layout_mod.do")
	public String layoutMod(
		HttpServletRequest request,
		@ModelAttribute("LayoutVo") LayoutVo layoutVo,
		ModelMap model) throws Exception {
		
		HttpSession ses = request.getSession();
		String regId = (String)ses.getAttribute("SesUserId");		
		
		//String filePath = "/WEB-INF/common/decorator/web/"+layoutVo.getSiteCd()+"/";
		String filePath = "/WEB-INF/jsp/injeinc/site/" +layoutVo.getSiteCd()+"/";
		String fileName = layoutVo.getLayoutCode()+".jsp";
		
		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
		
		Util util = new Util();
		String fileChk = util.fileWriteRootPath(request, filePath, fileName, layoutVo.getLayoutContent(), "");
		if(fileChk != null){
			if(fileChk.equals("yes")){
				layoutVo.setFilePath(filePath+fileName);
				layoutVo.setModId(regId);
				layoutSvc.modifyLayout(layoutVo);
			}
		}
	
		
		String SVC_MSGE = Message.getMessage("401.message"); //수정 성공
		return alert("/boffice/cn/layout/layout_list.do", SVC_MSGE, model);
		
	}
	
	//삭제
	@RequestMapping("/boffice/cn/layout/layout_rmv.do")
	public String layoutRmv(
		HttpServletRequest request,
		@ModelAttribute("LayoutVo") LayoutVo layoutVo,
		ModelMap model) throws Exception {
	
		layoutSvc.removeLayout(layoutVo);
		
		String SVC_MSGE = Message.getMessage("501.message"); //삭제 성공
		return alert("/boffice/cn/layout/layout_list.do", SVC_MSGE, model);
		
	}
	
	//미리보기 URL
	@RequestMapping("/boffice/cn/layout/layout_preview.do")
	public String layoutPreview(
		HttpServletRequest request,
		@ModelAttribute("LayoutVo") LayoutVo layoutVo,
		ModelMap model) throws Exception {
		return "injeinc/site/"+layoutVo.getSiteCd()+"/"+layoutVo.getLayoutCode();
	}
	
}