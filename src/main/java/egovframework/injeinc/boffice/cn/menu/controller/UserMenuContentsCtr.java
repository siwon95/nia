package egovframework.injeinc.boffice.cn.menu.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.cmm.GlobalConst;
import egovframework.cmm.Message;
import egovframework.injeinc.boffice.cn.menu.service.MenuSvc;
import egovframework.injeinc.boffice.cn.menu.vo.MenuVo;
import egovframework.injeinc.boffice.cn.menu.service.UserMenuContentsSvc;
import egovframework.injeinc.boffice.cn.menu.vo.UserMenuContentsVo;
import egovframework.injeinc.boffice.cn.menu.service.UserMenuSatisfactionSvc;
import egovframework.injeinc.boffice.cn.menu.vo.UserMenuSatisfactionVo;
import egovframework.injeinc.boffice.login.vo.LoginVo;
import egovframework.injeinc.boffice.cn.common.Util;
import egovframework.cmm.CmmLogCtr;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;




import egovframework.cmm.service.EgovProperties;

@Controller
public class UserMenuContentsCtr extends CmmLogCtr {
	
	@Resource(name = "MenuSvc")
	private MenuSvc menuSvc;
	
	@Resource(name = "UserMenuContentsSvc")
	private UserMenuContentsSvc userMenuContentsSvc;
	
	@Resource(name = "UserMenuSatisfactionSvc")
	private UserMenuSatisfactionSvc userMenuSatisfactionSvc;
	
	/**  user_menu_contents 등록 */
	@RequestMapping(value= "/boffice/cn/menu/User_Menu_Contents_Reg.do")
	public String userMenuContentsReg(
			HttpServletRequest request,
			@ModelAttribute("UserMenuContentsVo") UserMenuContentsVo userMenuContentsVo,
			ModelMap model) throws Exception {
		
		HttpSession ses = request.getSession();
		String modId = (String)ses.getAttribute("SesUserId");		
		
		int userMenuContentsVoCount = userMenuContentsSvc.selectUserMenuContentsCount(userMenuContentsVo);
		if(userMenuContentsVoCount>0){
			userMenuContentsVo.setContentSeqNo("");
			UserMenuContentsVo userMenuContentsMax = userMenuContentsSvc.retrieveUserMenuContentsMax(userMenuContentsVo);
			
			if(userMenuContentsMax.getProgressStatus() != null && !userMenuContentsMax.getProgressStatus().equals("publish_complete")){
				userMenuContentsMax.setProgressStatus("publish_cancel");
				userMenuContentsSvc.modifyUserMenuContents(userMenuContentsMax);
			}
		}
		
		userMenuContentsVo.setApplyYn("N");
		userMenuContentsVo.setProgressStatus("content_reg");
		userMenuContentsVo.setRegId(modId);
		userMenuContentsVo.setModId(modId);

		userMenuContentsSvc.registUserMenuContents(userMenuContentsVo);

		String returnUrl =  "/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do";
		returnUrl += "?siteCd="+userMenuContentsVo.getSiteCd()+"&menuCode="+userMenuContentsVo.getMenuCode();
		
		String SVC_MSGE = Message.getMessage("201.message"); //등록 성공
		return alert(returnUrl, SVC_MSGE, model);
	}

	/**  user_menu_contents 상세보기 */
	@RequestMapping(value= "/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do")
	public String User_Menu_ContentsForm(
		HttpServletRequest request,
		@ModelAttribute("UserMenuContentsVo") UserMenuContentsVo userMenuContentsVo,
		ModelMap model) throws Exception {
		
		HttpSession ses = request.getSession();
		LoginVo cmsUser = (LoginVo)ses.getAttribute("CMSUSER");
		
		String repairYn = userMenuContentsVo.getRepairYn();
		
		if(repairYn == null){
			repairYn = "N";
		}
		
		int userMenuContentsVoCount = userMenuContentsSvc.selectUserMenuContentsCount(userMenuContentsVo);
		if(userMenuContentsVoCount>0){
			userMenuContentsVo = userMenuContentsSvc.retrieveUserMenuContentsMax(userMenuContentsVo);
			if(repairYn.equals("Y")){ // 가져오기로 컨텐츠를 불러왔을 경우 
				userMenuContentsVo.setProgressStatus("publish_complete");
			}
		}
		if(model != null){
			model.addAttribute("UserMenuContentsVo", userMenuContentsVo);
			if(cmsUser.getPermCd().equals("05010000") || cmsUser.getPublishAuthYn().equals("Y")){
				model.addAttribute("publishAuthYn", "Y");
			}
		}
		return GlobalConst.SITE_ROOT_BOFFICE_PATH + "/cn/menu/user_menu_contents_form";
	}

	/**  user_menu_contents 목록 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value= "/boffice_noDeco/cn/menu/User_Menu_Contents_List.do")
	public String User_Menu_ContentsList(
		HttpServletRequest request,
		@ModelAttribute("UserMenuContentsVo") UserMenuContentsVo userMenuContentsVo,
		ModelMap model) throws Exception {
		List<UserMenuContentsVo> userMenuContentsVoList = userMenuContentsSvc.retrieveListUserMenuContents(userMenuContentsVo);
		if(model != null){
			model.addAttribute("UserMenuContentsVoList", userMenuContentsVoList);
			model.addAttribute("userMenuContentsVo", userMenuContentsVo);
		}
		
		return GlobalConst.SITE_ROOT_BOFFICE_PATH + "/cn/menu/user_menu_contents_list";
	}

	/**  user_menu_contents 수정 */
	@RequestMapping(value= "/boffice/cn/menu/User_Menu_Contents_Mod.do")
	public String userMenuContentsMod(
			HttpServletRequest request,
			@ModelAttribute("UserMenuContentsVo") UserMenuContentsVo userMenuContentsVo,
			ModelMap model) throws Exception {
		
		HttpSession ses = request.getSession();
		String modId = (String)ses.getAttribute("SesUserId");		
		
		userMenuContentsVo.setApplyYn("N");
		userMenuContentsVo.setModId(modId);
		userMenuContentsSvc.modifyUserMenuContents(userMenuContentsVo);
 
		String returnUrl =  "/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do";
		returnUrl += "?siteCd="+userMenuContentsVo.getSiteCd()+"&menuCode="+userMenuContentsVo.getMenuCode();
		
		String SVC_MSGE = Message.getMessage("401.message"); //수정 성공
		return alert(returnUrl, SVC_MSGE, model);
	}
	
	/**  user_menu_contents 발행 */
	@SuppressWarnings("unused")
	@RequestMapping(value= "/boffice/cn/menu/User_Menu_Contents_Pub.do")
	public String userMenuContentsPub(
			HttpServletRequest request,
			@ModelAttribute("UserMenuContentsVo") UserMenuContentsVo userMenuContentsVo,
			ModelMap model) throws Exception {
		
		HttpSession ses = request.getSession();
		String modId = (String)ses.getAttribute("SesUserId");		
		
		String sitePath = userMenuContentsVo.getSitePath();
		if(!(sitePath+"").replaceAll("null", "").trim().equals("")){
			sitePath = "/"+sitePath; 
		}
		String filePath = sitePath+"/"+userMenuContentsVo.getSiteCd()+"/"+userMenuContentsVo.getMenuCode().substring(1,3)+"/";
		String fileName = userMenuContentsVo.getMenuCode()+".jsp";
		
		MenuVo menuVo = new MenuVo();
		menuVo.setSiteCd(userMenuContentsVo.getSiteCd());
		menuVo.setMenuCode(userMenuContentsVo.getMenuCode());
		menuVo.setUserMenuUrl(filePath+fileName);
		menuSvc.modifyLinkMenu(menuVo);
		
		String strContent="<%@ page language=\"java\" contentType=\"text/html; charset=utf-8\" pageEncoding=\"utf-8\"%>";
		strContent += "\r\n"+userMenuContentsVo.getBodyContent();
		
		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
		
		Util util = new Util();
		String fileChk = util.fileWriteRootPath(request, filePath, fileName, strContent, rootPath);
		
		userMenuContentsVo.setApplyYn("Y");
		userMenuContentsVo.setProgressStatus("publish_complete");
		userMenuContentsVo.setModId(modId);
		userMenuContentsSvc.modifyUserMenuContents(userMenuContentsVo);
 
		String returnUrl =  "/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do";
		returnUrl += "?siteCd="+userMenuContentsVo.getSiteCd()+"&menuCode="+userMenuContentsVo.getMenuCode();
		
		String SVC_MSGE = Message.getMessage("202.message"); //발행 성공
		return alert(returnUrl, SVC_MSGE, model);
	}
	
	/**  user_menu_contents 전체 메뉴의 contents로 컨텐츠 파일 갱신 */
	@SuppressWarnings("unused")
	@RequestMapping(value= "/boffice/cn/menu/User_Menu_Contents_Create_File.do")
	public String userMenuContentsCreateFile(
			HttpServletRequest request,
			@ModelAttribute("UserMenuContentsVo") UserMenuContentsVo userMenuContentsVo,
			ModelMap model) throws Exception {
		
		List<UserMenuContentsVo> userMenuContentsVoList = userMenuContentsSvc.selectUserMenuContentsNew(userMenuContentsVo);
		UserMenuContentsVo userMenuContentsVo2 = new UserMenuContentsVo();
		for(int i=0;i<userMenuContentsVoList.size();i++){
			System.out.println(i+":");
			
			userMenuContentsVo2 = userMenuContentsVoList.get(i);
			
			String sitePath = userMenuContentsVo2.getSitePath();
			if(!(sitePath+"").replaceAll("null", "").trim().equals("")){
				sitePath = "/"+sitePath; 
			}
			String filePath = sitePath+"/"+userMenuContentsVo2.getSiteCd()+"/"+userMenuContentsVo2.getMenuCode().substring(1,3)+"/";
			String fileName = userMenuContentsVo2.getMenuCode()+".jsp";
			
				
			String strContent="<%@ page language=\"java\" contentType=\"text/html; charset=utf-8\" pageEncoding=\"utf-8\"%>";
			strContent += "\r\n"+userMenuContentsVo2.getBodyContent();
			
			String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
			//String rootPath = "D:/contents";
			
			System.out.println("filePath:"+filePath);
			System.out.println("fileName:"+fileName);
			System.out.println("rootPath:"+rootPath);
			
			Util util = new Util();
			String fileChk = util.fileWriteRootPath(request, filePath, fileName, strContent, rootPath);
			
			
		}
			
		String returnUrl =  "/boffice/cn/menu/menu_list.do?cmm_code=ES020000";
		
		String SVC_MSGE = Message.getMessage("202.message"); //발행 성공
		return alert(returnUrl, SVC_MSGE, model);
	}
	
	/**  user_menu_satisfaction 목록 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value= "/boffice_noDeco/cn/menu/User_Menu_Satisfaction_List.do")
	public String User_Menu_Satisfaction(
		HttpServletRequest request,
		@ModelAttribute("UserMenuSatisfactionVo") UserMenuSatisfactionVo userMenuSatisfactionVo,
		ModelMap model) throws Exception {
		/*
		List<UserMenuContentsVo> userMenuContentsVoList = userMenuContentsSvc.retrieveListUserMenuContents(userMenuContentsVo);
		if(model != null){
			model.addAttribute("UserMenuContentsVoList", userMenuContentsVoList);
			model.addAttribute("userMenuContentsVo", userMenuContentsVo);
		}
		*/
		int rowCnt = 10;
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(userMenuSatisfactionVo.getPageIndex());
		if(rowCnt > 0){
			paginationInfo.setRecordCountPerPage(rowCnt);
		}else{
			paginationInfo.setRecordCountPerPage(userMenuSatisfactionVo.getPageUnit());
		}
		
		int contentCnt = 0;
		contentCnt = userMenuSatisfactionSvc.selectUserMenuSatisfactionCnt(userMenuSatisfactionVo);

		paginationInfo.setPageSize(userMenuSatisfactionVo.getPageSize());
		userMenuSatisfactionVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		userMenuSatisfactionVo.setLastIndex(paginationInfo.getLastRecordIndex());
		userMenuSatisfactionVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		paginationInfo.setTotalRecordCount(contentCnt);
		
		List satisfactionList = null;
		satisfactionList = userMenuSatisfactionSvc.pageUserMenuSatisfaction(userMenuSatisfactionVo);
		
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("satisfactionList", satisfactionList);
		
		
		return GlobalConst.SITE_ROOT_BOFFICE_PATH + "/cn/menu/user_menu_satisfaction_list";
	}
	
	/**  user_menu_contents 등록 */
	@RequestMapping(value= "/boffice/cn/menu/User_Menu_Satisfaction_Action.do")
	public String userMenuSatisfactionReg(
			HttpServletRequest request,
			@ModelAttribute("UserMenuSatisfactionVo") UserMenuSatisfactionVo userMenuSatisfactionVo,
			ModelMap model) throws Exception {
		
		HttpSession ses = request.getSession();
	//	String modId = (String)ses.getAttribute("SesUserId");
		String mode = request.getParameter("mode");
		if(mode.equals("satisfaction_delete")){
			userMenuSatisfactionSvc.deleteUserMenuSatisfaction(userMenuSatisfactionVo);
		}

		String returnUrl =  request.getParameter("returnUrl");
		
		String SVC_MSGE = Message.getMessage("501.message"); //등록 성공
		return alert(returnUrl, SVC_MSGE, model);
	}
}
