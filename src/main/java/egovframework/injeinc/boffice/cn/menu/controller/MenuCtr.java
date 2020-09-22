package egovframework.injeinc.boffice.cn.menu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.GlobalConst;
import egovframework.cmm.Message;
import egovframework.cmm.service.EgovProperties;
import egovframework.injeinc.boffice.cn.common.Util;
import egovframework.injeinc.boffice.cn.common.UtilSvc;
import egovframework.injeinc.boffice.cn.layout.service.LayoutSvc;
import egovframework.injeinc.boffice.cn.layout.vo.LayoutVo;
import egovframework.injeinc.boffice.cn.menu.service.MenuSvc;
import egovframework.injeinc.boffice.cn.menu.service.UserMenuContentsSvc;
import egovframework.injeinc.boffice.cn.menu.vo.MenuVo;
import egovframework.injeinc.boffice.cn.menu.vo.UserMenuContentsVo;
import egovframework.injeinc.boffice.cn.site.service.SiteSvc;
import egovframework.injeinc.boffice.cn.site.vo.SiteVo;
import egovframework.injeinc.boffice.group.dept.service.GroupDeptSvc;
import egovframework.injeinc.boffice.group.emp.service.EmpSvc;
import egovframework.injeinc.boffice.group.emp.vo.EmpVo;
import egovframework.injeinc.boffice.sy.board.service.CmsBbsSvc;
import egovframework.injeinc.boffice.sy.board.vo.CmsBbsVo;
//파일 첨부를 위해 추가 import 구문
import egovframework.injeinc.boffice.sy.board.vo.TreeBoardVo;
import egovframework.injeinc.boffice.sy.code.service.CmmCodeSvc;
import egovframework.injeinc.boffice.sy.code.vo.CmmCodeVo;
import egovframework.injeinc.common.util.EgovStringUtil;
import egovframework.injeinc.common.weblog.service.WeblogSvc;
import egovframework.injeinc.foffice.ex.dept.service.DeptFSvc;


@Controller
public class MenuCtr extends CmmLogCtr {
    
	@Resource(name = "SiteSvc")
	private SiteSvc siteSvc;

	@Resource(name = "LayoutSvc")
	private LayoutSvc layoutSvc;

	@Resource(name = "MenuSvc")
	private MenuSvc menuSvc;
	
	@Resource(name = "EmpSvc")
	private EmpSvc empSvc;
	
	@Resource(name = "DeptFSvc")
	private DeptFSvc deptFSvc;
	
	@Resource(name = "GroupDeptSvc")
	private GroupDeptSvc groupDeptSvc;
	
	@Resource(name = "WeblogSvc")
	private WeblogSvc weblogSvc;

	@Resource(name = "CmsBbsSvc")
	private CmsBbsSvc cmsBbsSvc;
	
	@Resource(name = "UtilSvc")
	private UtilSvc utilSvc;
	
	@Resource(name = "CmmCodeSvc")
	private CmmCodeSvc cmmCodeSvc;
	
	@Resource(name = "UserMenuContentsSvc")
	private UserMenuContentsSvc userMenuContentsSvc;
	
	//리스트
	@RequestMapping(value= "/boffice/cn/menu/menu_list.do")
	public String menuList(
			HttpServletRequest request,
			@ModelAttribute("MenuVo") MenuVo menuVo,
			ModelMap model) throws Exception {
		
		String SesUserRoleIdx = (String) WebUtils.getSessionAttribute(request, "SesUserRoleIdx");
		String SesUserPermCd = (String) WebUtils.getSessionAttribute(request, "SesUserPermCd");
		
		
		
		SiteVo siteVo = new SiteVo();
		
		if(!SesUserPermCd.equals("05010000")){ //슈퍼관리자일 경우 제외
			siteVo.setRoleIdx(SesUserRoleIdx);
		}
		List<SiteVo> selectListSiteAll = siteSvc.selectListSiteAll(siteVo);
		
		LayoutVo layoutVo = new LayoutVo();
		layoutVo.setSiteCd(menuVo.getSiteCd());
		List<LayoutVo> selectListLayout = layoutSvc.retrieveListLayout(layoutVo);

		if((menuVo.getMenuCode()+"").replaceAll("null", "").equals("")){
			menuVo.setMenuCode("10000000000000000000000");
		}
		
		if((menuVo.getSiteCd()+"").replaceAll("null", "").equals("")){
			List<CmmCodeVo> cmmSiteCdList = cmmCodeSvc.retrieveListCmmCode("SITE0001");
			if(cmmSiteCdList!=null && cmmSiteCdList.size()!=0)
				menuVo.setSiteCd(cmmSiteCdList.get(0).getCode());
			else
				menuVo.setSiteCd("");
		}
		
		if(!(menuVo.getSiteCd()+"").replaceAll("null", "").equals("")){
			if(menuVo.getMenuCode().equals("10000000000000000000000")){
				int homeCodeChk = menuSvc.selectHomeCodeChk(menuVo);
				menuVo.setMenuCode("10000000000000000000000");
				menuVo.setSortOrder("10000000000000000000000");
				menuVo.setMenuNm("HOME");
				menuVo.setMenuPath("HOME");
				if(homeCodeChk == 0){
					String basePath = "/site/"+menuVo.getSiteCd()+"/";
					menuVo.setBasePath(basePath);
					menuVo.setMenuDepth("0");
					menuSvc.insertMenu(menuVo);
				}
			}
			
			
			menuVo = menuSvc.selectEzUserMenu(menuVo);
			debugLog("menuVo:"+menuVo);
			

			menuVo.setSameLevel("N");
			if((menuVo.getUseYn()+"").replaceAll("null","").equals("")){
				menuVo.setUseYn("Y");
			}
			if((menuVo.getShowYn()+"").replaceAll("null","").equals("")){
				menuVo.setShowYn("Y");
			}
			if((menuVo.getMenuType()+"").replaceAll("null","").equals("")){
				menuVo.setMenuType("contents");
			}
			if((menuVo.getLinkType()+"").replaceAll("null","").equals("")){
				menuVo.setLinkType("normal");
			}
			if((menuVo.getSatisfyShowYn()+"").replaceAll("null","").equals("")){
				menuVo.setSatisfyShowYn("N");
			}
			if((menuVo.getSatisfyShowYn()+"").replaceAll("null","").equals("")){
				menuVo.setSatisfyShowYn("N");
			}
			if((menuVo.getShowYn()+"").replaceAll("null","").equals("")){
				menuVo.setShowYn("Y");
			}
			if((menuVo.getChargerShowYn()+"").replaceAll("null","").equals("")){
				menuVo.setChargerShowYn("Y");
			}
			if((menuVo.getSocialShowYn()+"").replaceAll("null","").equals("")){
				menuVo.setSocialShowYn("N");
			}
			if((menuVo.getSocialShowYn()+"").replaceAll("null","").equals("")){
				menuVo.setSocialShowYn("N");
			}
		}
		
		List<EmpVo> depstep1List = empSvc.retrieveListDepstep1();
		if(model != null){
			model.addAttribute("depstep1", depstep1List);
		}
		
		
		if(menuVo.getChargeDeptCode() != null && !menuVo.getChargeDeptCode().equals("")){
			
			String cdCode = deptFSvc.retrieveCdCode(menuVo.getChargeDeptCode());		//부서 코드 조회
			debugLog("cdCode:"+cdCode);
			if(cdCode != null && !cdCode.equals("")){
				//담당부서 시작
				menuVo.setChargeDeptCode1(cdCode.substring(1, 3));
				menuVo.setChargeDeptCode2(cdCode.substring(3, 5));
				menuVo.setChargeDeptCode3(cdCode.substring(5, 7));
				HashMap<String, String> param = new HashMap<String, String>();
				
				if(cdCode != null){
					param.put("cdDepstep1", cdCode.substring(1, 3));
					debugLog("getChargeDeptCode().substring(1, 3) : "+menuVo.getChargeDeptCode().substring(1, 3));
					param.put("cdDepstep2", cdCode.substring(3, 5));
					debugLog("menuVo.getChargeDeptCode().substring(3, 5) : " + menuVo.getChargeDeptCode().substring(3, 5));
				}
				List cdDepstep2 = empSvc.retrieveListEmpDep2(param);		//과코드,명 리스트 조회
				List cdDepstep3 = empSvc.retrieveListEmpDep3(param);		//팀코드,명 리스트 조회
				if(model != null){
					model.addAttribute("dep2", cdDepstep2);
					model.addAttribute("dep3", cdDepstep3);
				}
				//담당부서 끝
			}
		}
		if(!SesUserPermCd.equals("05010000")){ //슈퍼관리자일 경우 제외
			menuVo.setRoleIdx(SesUserRoleIdx);
		}
		List<MenuVo> menuVoList = menuSvc.selectMenu(menuVo);
		
		List<MenuVo> menuSubUrlsList = menuSvc.selectMenuSubUrls(menuVo);
		
		String menuUnderCount = menuSvc.selectMenuUnderCount(menuVo);
		if(model != null){
			model.addAttribute("MenuVo", menuVo);
			model.addAttribute("listSiteAll", selectListSiteAll);
			model.addAttribute("listLayout", selectListLayout);
			model.addAttribute("menuVoList", menuVoList);
			model.addAttribute("menuSubList", menuSubUrlsList);
			model.addAttribute("menuUnderCount", menuUnderCount);
		}
		
		List<TreeBoardVo> retrieveListCmsBbs = cmsBbsSvc.retrieveListCmsBbs();
		model.addAttribute("treeBoardVoList", retrieveListCmsBbs);

		return GlobalConst.SITE_ROOT_BOFFICE_PATH + "/cn/menu/menu_list";
	}
	
	//상세보기
	@RequestMapping(value= "/boffice_noDeco/cn/menu/menu_detail.do")
	public String menuDetail(
				HttpServletRequest request,
				@ModelAttribute("MenuVo") MenuVo menuVo,
				ModelMap model) throws Exception {

			SiteVo siteVo = new SiteVo();
			LayoutVo layoutVo = new LayoutVo();
			layoutVo.setSiteCd(menuVo.getSiteCd());
			List<LayoutVo> selectListLayout = layoutSvc.retrieveListLayout(layoutVo);

			if((menuVo.getMenuCode()+"").replaceAll("null", "").equals("")){
				menuVo.setMenuCode("10000000000000000000000");
			}

			if(!(menuVo.getSiteCd()+"").replaceAll("null", "").equals("")){
				if(menuVo.getMenuCode().equals("10000000000000000000000")){
					int homeCodeChk = menuSvc.selectHomeCodeChk(menuVo);
					menuVo.setMenuCode("10000000000000000000000");
					menuVo.setSortOrder("10000000000000000000000");
					menuVo.setMenuNm("HOME");
					menuVo.setMenuPath("HOME");
					if(homeCodeChk == 0){
						String basePath = "/site/"+menuVo.getSiteCd()+"/";
						menuVo.setBasePath(basePath);
						menuVo.setMenuDepth("0");
						menuSvc.insertMenu(menuVo);
					}
				}
				
				
				menuVo = menuSvc.selectEzUserMenu(menuVo);
				debugLog("menuVo:"+menuVo);
				

				if((menuVo.getSiteCd()+"").replaceAll("null", "").equals("")){
					menuVo.setSiteCd("");
				}
				menuVo.setSameLevel("N");
				if((menuVo.getUseYn()+"").replaceAll("null","").equals("")){
					menuVo.setUseYn("Y");
				}
				if((menuVo.getShowYn()+"").replaceAll("null","").equals("")){
					menuVo.setShowYn("Y");
				}
				if((menuVo.getMenuType()+"").replaceAll("null","").equals("")){
					menuVo.setMenuType("contents");
				}
				if((menuVo.getLinkType()+"").replaceAll("null","").equals("")){
					menuVo.setLinkType("normal");
				}
				if((menuVo.getSatisfyShowYn()+"").replaceAll("null","").equals("")){
					menuVo.setSatisfyShowYn("N");
				}
				if((menuVo.getSatisfyShowYn()+"").replaceAll("null","").equals("")){
					menuVo.setSatisfyShowYn("N");
				}
				if((menuVo.getShowYn()+"").replaceAll("null","").equals("")){
					menuVo.setShowYn("Y");
				}
				if((menuVo.getChargerShowYn()+"").replaceAll("null","").equals("")){
					menuVo.setChargerShowYn("Y");
				}
				if((menuVo.getSocialShowYn()+"").replaceAll("null","").equals("")){
					menuVo.setSocialShowYn("N");
				}
				if((menuVo.getSocialShowYn()+"").replaceAll("null","").equals("")){
					menuVo.setSocialShowYn("N");
				}
			}
			
			List<EmpVo> depstep1List = empSvc.retrieveListDepstep1();
			if(model != null){
				model.addAttribute("depstep1", depstep1List);
			}
			
			
			if(menuVo.getChargerId() != null && !menuVo.getChargerId().equals("")){
				
				
				EmpVo empVo = new EmpVo();
				
				empVo.setCeIdx(menuVo.getChargerId());
				empVo.setCeUse("Y");
				EmpVo emp = empSvc.retrieveEmp(empVo);	//사용자정보 조회
				model.addAttribute("emp",emp);
				
				String cdCode = deptFSvc.retrieveCdCode(menuVo.getChargeDeptCode());		//부서 코드 조회
				debugLog("cdCode:"+cdCode);
				if(cdCode != null && !cdCode.equals("")){
					//담당부서 시작
					menuVo.setChargeDeptCode1(cdCode.substring(1, 3));
					menuVo.setChargeDeptCode2(cdCode.substring(3, 5));
					menuVo.setChargeDeptCode3(cdCode.substring(5, 7));
					HashMap<String, String> param = new HashMap<String, String>();
					
					if(cdCode != null){
						param.put("cdDepstep1", cdCode.substring(1, 3));
						debugLog("getChargeDeptCode().substring(1, 3) : "+menuVo.getChargeDeptCode().substring(1, 3));
						param.put("cdDepstep2", cdCode.substring(3, 5));
						debugLog("menuVo.getChargeDeptCode().substring(3, 5) : " + menuVo.getChargeDeptCode().substring(3, 5));
					}
					List cdDepstep2 = empSvc.retrieveListEmpDep2(param);		//과코드,명 리스트 조회
					List cdDepstep3 = empSvc.retrieveListEmpDep3(param);		//팀코드,명 리스트 조회
					if(model != null){
						model.addAttribute("dep2", cdDepstep2);
						model.addAttribute("dep3", cdDepstep3);
					}
					//담당부서 끝
				}
			}else{
				EmpVo emp = new EmpVo();	//사용자 정보가 없을시 빈값 출력
				model.addAttribute("emp",emp);
			}
			
			
			//메타태그
			List<HashMap<String,String>> metaTagList=new ArrayList<HashMap<String,String>>();
			List<CmmCodeVo> metaTagPropertyList = cmmCodeSvc.retrieveListCmmCode("METATAG_PROPERTY");
			List<CmmCodeVo> metaTagValueList = cmmCodeSvc.retrieveListSubCmmCode("METATAG_PROPERTY");
			
			//메타태그 값이 빈값일경우
			if(menuVo.getMetaTagContent() != null && !menuVo.getMetaTagContent().equals("")){
				//메타 태그 자르기
				String[] metaTagLine = null;
				String[] tmp = null;
				metaTagLine=menuVo.getMetaTagContent().split("\n");
				for(int i=0;i<metaTagLine.length;i++){
					HashMap<String, String> map = new HashMap<String, String>();
			      	tmp=metaTagLine[i].split("<meta ");
			      	tmp=tmp[1].split("='");
			      	map.put("metaProperty",tmp[0]);
			      	map.put("metaValue",tmp[1].split("'")[0]);
			      	tmp=tmp[2].split("'>");
			      	map.put("metaCont",tmp[0]);
			      	metaTagList.add(map);
			    }
			}
			
			List<MenuVo> menuSubUrlsList = menuSvc.selectMenuSubUrls(menuVo);
			
			String menuUnderCount = menuSvc.selectMenuUnderCount(menuVo);
			
			UserMenuContentsVo userMenuContentsVo = new UserMenuContentsVo();
			userMenuContentsVo.setMenuCode(menuVo.getMenuCode());
			userMenuContentsVo.setSiteCd(menuVo.getSiteCd());
			
			int userMenuContentsVoCount = userMenuContentsSvc.selectUserMenuContentsCount(userMenuContentsVo);
			if(userMenuContentsVoCount>0){
				userMenuContentsVo = userMenuContentsSvc.retrieveUserMenuContentsMax(userMenuContentsVo);
			}
			
			if(model != null){
				model.addAttribute("MenuVo", menuVo);
				model.addAttribute("listLayout", selectListLayout);
				model.addAttribute("menuSubList", menuSubUrlsList);
				model.addAttribute("menuUnderCount", menuUnderCount);
				model.addAttribute("UserMenuContentsVo", userMenuContentsVo);
				model.addAttribute("metaTagPropertyList", metaTagPropertyList);
				model.addAttribute("metaTagValueList", metaTagValueList);
				model.addAttribute("metaTagList", metaTagList);
			}
			
			CmsBbsVo cmsBbsVo = new CmsBbsVo();
			cmsBbsVo.setMgrSiteCd(menuVo.getSiteCd());
			List<TreeBoardVo> retrieveListCmsBbs = cmsBbsSvc.retrieveListCmsBbsSite(cmsBbsVo);
			model.addAttribute("treeBoardVoList", retrieveListCmsBbs);

			return GlobalConst.SITE_ROOT_BOFFICE_PATH + "/cn/menu/menu_detail";
		}

	//수정
	@RequestMapping("/boffice/cn/menu/menu_mod.do")
	public String menuMod(
			HttpServletRequest request,
			@ModelAttribute("MenuVo") MenuVo menuVo,
			ModelMap model) throws Exception {
		HttpSession ses = request.getSession();
		String regId = (String)ses.getAttribute("SesUserId");
		
		String sortOrder = menuVo.getSortOrder();
		int menuDepth = 0;
		String tmpSortOrder = "";
		if(menuVo.getMenuCode().equals("10000000000000000000000")){
			menuVo.setSortOrder("1000000000000");
			menuVo.setMenuPath("HOME");
		}else{
			menuDepth = Integer.parseInt(menuVo.getMenuDepth()+"");
			tmpSortOrder = sortOrder.substring(0,((menuDepth-1)*2+1));
			menuVo.setSortOrder(tmpSortOrder);
			menuVo.setMenuPath(menuSvc.selectMenuPath(menuVo)+" > "+menuVo.getMenuNm());
			menuVo.setSortOrder(sortOrder);
		}
		
		String[] subUrls = request.getParameterValues("userMenuSubUrl"); 
		String[] subTitles = request.getParameterValues("userMenuSubTitle"); 
		menuSvc.deleteMenuSubUrls(menuVo);
		
		if(subUrls != null){
			for(int i=0;i<subUrls.length;i++){
				if(!(subUrls[i]+"").replaceAll("null", "").trim().equals("")){
					menuVo.setSubMenuUrl(subUrls[i]);
					menuVo.setSubMenuTitle(subTitles[i]);
					menuSvc.insertMenuSubUrls(menuVo);
				}
			}
		}
		
		//메타태그
		String[] metaProperty = request.getParameterValues("metaProperty"); 
		String[] metaValue = request.getParameterValues("metaValue"); 
		String[] metaCont = request.getParameterValues("metaCont"); 
		String strMetaTagContent="";
		
		if(metaValue != null){
			for(int i=0;i<metaProperty.length;i++){
				if(!(metaProperty[i]+"").replaceAll("null", "").trim().equals("")
					&& !(metaValue[i]+"").replaceAll("null", "").trim().equals("")
					&& !(metaCont[i]+"").replaceAll("null", "").trim().equals("")){
					strMetaTagContent+="<meta "+metaProperty[i]+"='"+metaValue[i]+"' content='"+metaCont[i]+"'>\n";
				}
			}
		}
		menuVo.setMetaTagContent(strMetaTagContent);
		
		menuVo.setModId(regId);
		
		if(menuVo.getTitle().equals("")){
			menuVo.setTitle(menuVo.getMenuNm());
		}
		
		//XML 생성시 특수문자 제거
		menuVo.setHeadContent(menuVo.getHeadContent().replaceAll("[^\\u0009\\u000A\\u000D\\u0020-\\uD7FF\\uE000-\\uFFFD\\u10000-\\u10FFF]+",""));
		menuVo.setMetaTagContent(menuVo.getMetaTagContent().replaceAll("[^\\u0009\\u000A\\u000D\\u0020-\\uD7FF\\uE000-\\uFFFD\\u10000-\\u10FFF]+",""));
		
		menuSvc.modifyMenu(menuVo);

		String SVC_MSGE = Message.getMessage("401.message"); //등록 성공
		return alert("/boffice_noDeco/cn/menu/menu_detail.do?siteCd="+menuVo.getSiteCd()+"&menuCode="+menuVo.getMenuCode(), SVC_MSGE, model);
	}
	
	//수정
		@RequestMapping("/boffice_noDeco/cn/menu/menu_del.do")
		public String menuDel(
				HttpServletRequest request,
				@ModelAttribute("MenuVo") MenuVo menuVo,
				ModelMap model) throws Exception {
	
			menuSvc.deleteMenu(menuVo);

			String SVC_MSGE = Message.getMessage("501.message"); //삭제 성공
			return alertParentUrl("/boffice/cn/menu/menu_list.do?siteCd="+menuVo.getSiteCd()+"&menuCode=", SVC_MSGE, model);
		}

	//저장
	@RequestMapping("/boffice_noDeco/cn/menu/menu_add.do")
	public String menuReg(
			HttpServletRequest request,
			@ModelAttribute("MenuVo") MenuVo menuVo,
			ModelMap model) throws Exception {
		HttpSession ses = request.getSession();
		String regId = (String)ses.getAttribute("SesUserId");
		
		if(menuVo.getSameLevel().equals("Y")){
			String sortOrder = menuVo.getSortOrder();
			int menuDepth = Integer.parseInt(menuVo.getMenuDepth()+"");
			
			String tmpSortOrder = sortOrder.substring(0,((menuDepth-1)*2+1));
			
			menuVo.setSortOrder(tmpSortOrder);
			menuVo.setMenuPath(menuSvc.selectMenuPath(menuVo)+" > "+menuVo.getMenuNm());
			menuVo.setSortOrder(sortOrder);
		}else{
			menuVo.setMenuPath(menuSvc.selectMenuPath(menuVo)+" > "+menuVo.getMenuNm());
		}

		// 최초 코드와 메뉴 정렬 코드는 동일하게 생성하고 정렬는 추후 달라 질수 있음 
		MenuVo menuVoCode = menuSvc.selectCreateSortOrder(menuVo);
		menuVo.setMenuCode(menuVoCode.getMenuCode());
		menuVo.setSortOrder(menuVoCode.getSortOrder());

		String basePath = "/site/"+menuVo.getSiteCd()+"/"+menuVo.getMenuCode().substring(1,3);
		menuVo.setBasePath(basePath);
		//menuVo.setMenuDepth(((menuVo.getSortOrder().replaceAll("00","").length()-1)/2)+"");

		menuSvc.insertMenu(menuVo);

		String SVC_MSGE = Message.getMessage("201.message"); //등록 성공
		return alertParentUrl("/boffice/cn/menu/menu_list.do?siteCd="+menuVo.getSiteCd()+"&menuCode="+menuVo.getMenuCode(), SVC_MSGE, model);
	}

	//컨텐츠 수정및 발행
	@RequestMapping("/boffice_noDeco/cn/menu/Menu_Contents.do")
	public String menuContents(
			HttpServletRequest request,
			@ModelAttribute("MenuVo") MenuVo menuVo,
			ModelMap model) throws Exception {
		HttpSession ses = request.getSession();
		String regId = (String)ses.getAttribute("SesUserId");

		return GlobalConst.SITE_ROOT_BOFFICE_PATH + "/cn/menu/menu_contents";
		//String SVC_MSGE = Message.getMessage("201.message"); //등록 성공
		//return alert(request.getContextPath()+"/boffice/cn/menu/menu_contents.do", SVC_MSGE, model);
	}
	
	//메뉴생성
		@RequestMapping("/boffice_noDeco/cn/menu/Menu_User_Create.do")
		public String menuUserCreate(
				HttpServletRequest request,
				@ModelAttribute("MenuVo") MenuVo menuVo,
				ModelMap model) throws Exception {
			HttpSession ses = request.getSession();
			String regId = (String)ses.getAttribute("SesUserId");
			
			String filePath = "/site/"+menuVo.getSiteCd()+"/";
			String fileName = "menu.xml";
			String fileNameLayout = "menu_layout.xml";
			
			String strContent="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
			
			List<MenuVo> menuVoList = menuSvc.selectEzUserMenuCreate(menuVo);
			List<MenuVo> menuVoSubList = menuSvc.selectEzUserMenuCreateSub(menuVo);
			strContent+="<menu>";
			int oldDepth = 0;
			String oldSortOrder = "";
			String tempContent2 = "";
			String headContent = "";
			tempContent2 += strContent;
			
			for(int i=0;i<menuVoList.size();i++){
				MenuVo mvList = menuVoList.get(i);
				String tempContent = "";
				
				int curDepth = Integer.parseInt(mvList.getMenuDepth());
				
				String tempSpace = "";
				for(int k=0;k<curDepth;k++){
					tempSpace+="  ";
				}
								
				tempContent="\n"+tempSpace+"<menu-part-"+mvList.getMenuDepth()+">\n";
				tempContent+=tempSpace+"  <site-cd>"+mvList.getSiteCd()+"</site-cd>\n";
				tempContent+=tempSpace+"  <menu-code>"+mvList.getMenuCode()+"</menu-code>\n";
				tempContent+=tempSpace+"  <sort-order>"+mvList.getSortOrder()+"</sort-order>\n";
				if(mvList.getMenuNm() == null){
					tempContent+=tempSpace+"  <menu-nm>"+mvList.getMenuNm()+"</menu-nm>\n";
				}else{
					tempContent+=tempSpace+"  <menu-nm>"+mvList.getMenuNm().replaceAll("&", "&amp;")+"</menu-nm>\n";
				}
				if(mvList.getTitle() == null){
					tempContent+=tempSpace+"  <menu-title>"+mvList.getTitle()+"</menu-title>\n";
				}else{
					tempContent+=tempSpace+"  <menu-title>"+mvList.getTitle().replaceAll("&", "&amp;")+"</menu-title>\n";
				}
				tempContent+=tempSpace+"  <menu-depth>"+mvList.getMenuDepth()+"</menu-depth>\n";
				if(mvList.getMenuPath() == null){
					tempContent+=tempSpace+"  <menu-path>"+mvList.getMenuPath()+"</menu-path>\n";
				}else{
					tempContent+=tempSpace+"  <menu-path>"+mvList.getMenuPath().replaceAll("&", "&amp;")+"</menu-path>\n";
				}
				if(mvList.getUserMenuUrl() == null){
					tempContent+=tempSpace+"  <user-menu-url>"+mvList.getUserMenuUrl()+"</user-menu-url>\n";
				}else{
					tempContent+=tempSpace+"  <user-menu-url>"+mvList.getUserMenuUrl().replaceAll("&", "&amp;")+"</user-menu-url>\n";
				}
				
				if (mvList.getHeadContent() == null) {
					tempContent+=tempSpace + "  <head-content></head-content>\n";
				} else {
					tempContent+=tempSpace + "  <head-content>" + mvList.getHeadContent() + "</head-content>\n";
				}

				tempContent+=tempSpace+"  <layout-url>"+mvList.getLayoutUrl()+"</layout-url>\n";
				tempContent+=tempSpace+"  <link-type>"+mvList.getLinkType()+"</link-type>\n";
				tempContent+=tempSpace+"  <main-or-sub>main</main-or-sub>\n";
				tempContent+=tempSpace+"  <show-yn>"+mvList.getShowYn()+"</show-yn>\n";
				tempContent+=tempSpace+"  <child-menu>childMenu"+mvList.getSortOrder().substring(0,curDepth*2+1)+"</child-menu>\n";
				tempContent+=tempSpace+"</menu-part-"+mvList.getMenuDepth()+">\n";
				tempContent+=tempSpace+"sameMenu"+mvList.getSortOrder().substring(0,curDepth*2+1)+"_\n";
				
				
				tempContent2+="<menu-layout>\n";
				tempContent2+="  <site-cd>"+mvList.getSiteCd()+"</site-cd>\n";
				if(mvList.getSiteNm() == null){
					tempContent2+="  <site-nm>"+mvList.getSiteNm()+"</site-nm>\n";
				}else{
					tempContent2+="  <site-nm>"+mvList.getSiteNm().replaceAll("&", "&amp;")+"</site-nm>\n";
				}
				tempContent2+="  <menu-code>"+mvList.getMenuCode()+"</menu-code>\n";
				tempContent2+="  <sort-order>"+mvList.getSortOrder()+"</sort-order>\n";
				if(mvList.getMenuNm() == null){
					tempContent2+="  <menu-nm>"+mvList.getMenuNm()+"</menu-nm>\n";
				}else{
					tempContent2+="  <menu-nm>"+mvList.getMenuNm().replaceAll("&", "&amp;")+"</menu-nm>\n";
				}
				if(mvList.getTitle() == null){
					tempContent2+="  <menu-title>"+mvList.getTitle()+"</menu-title>\n";
				}else{
					tempContent2+="  <menu-title>"+mvList.getTitle().replaceAll("&", "&amp;")+"</menu-title>\n";
				}
				tempContent2+="  <satisfy-show-yn>"+mvList.getSatisfyShowYn()+"</satisfy-show-yn>\n";
				tempContent2+="  <charger-show-yn>"+mvList.getChargerShowYn()+"</charger-show-yn>\n";
				tempContent2+="  <social-show-yn>"+mvList.getSocialShowYn()+"</social-show-yn>\n";
				if(mvList.getMenuPath() == null){
					tempContent2+="  <menu-path>"+mvList.getMenuPath()+"</menu-path>\n";
				}else{
					tempContent2+="  <menu-path>"+mvList.getMenuPath().replaceAll("&", "&amp;")+"</menu-path>\n";
				}
				if(mvList.getUserMenuUrl()  == null){
					tempContent2+="  <user-menu-url>"+mvList.getUserMenuUrl()+"</user-menu-url>\n";
				}else{
					tempContent2+="  <user-menu-url>"+mvList.getUserMenuUrl().replaceAll("&", "&amp;")+"</user-menu-url>\n";
				}
				tempContent2+="  <layout-url>"+mvList.getLayoutUrl()+"</layout-url>\n";
				tempContent2+="  <menu-type>"+mvList.getMenuType()+"</menu-type>\n";
				tempContent2+="  <dept-info>"+mvList.getDeptInfo()+"</dept-info>\n";
				tempContent2+="  <head-content><![CDATA["+mvList.getHeadContent()+"]]></head-content>\n";
				tempContent2+="  <metatag-content><![CDATA["+mvList.getMetaTagContent()+"]]></metatag-content>\n";
				tempContent2+="  <show-yn>"+mvList.getShowYn()+"</show-yn>\n";
				tempContent2+="</menu-layout>\n";
				
				String tempSort ="";
				if(i==0){
					strContent += tempContent;
				}else if(oldDepth<curDepth){
					tempSort = "<child-menu>childMenu"+mvList.getSortOrder().substring(0,(curDepth-1)*2+1)+"</child-menu>";
					strContent = strContent.replaceAll(tempSort, "<child-menu>"+tempContent+tempSpace+"</child-menu>");
				}else if(oldDepth==curDepth){
					tempSort = "sameMenu"+oldSortOrder.substring(0,curDepth*2+1)+"_";
					strContent = strContent.replaceAll(tempSort, tempContent);
				}else if(oldDepth>curDepth){
					tempSort = "sameMenu"+oldSortOrder.substring(0,curDepth*2+1)+"_";
					strContent = strContent.replaceAll(tempSort, tempContent);
				}
				
				oldDepth = Integer.parseInt(mvList.getMenuDepth());
				oldSortOrder = mvList.getSortOrder();
			}
			
			for(int i=0;i<menuVoSubList.size();i++){
				MenuVo mvListSub = menuVoSubList.get(i);
				tempContent2+="<menu-layout>\n";
				tempContent2+="  <site-cd>"+mvListSub.getSiteCd()+"</site-cd>\n";
				if(mvListSub.getSiteNm() == null){
					tempContent2+="  <site-nm>"+mvListSub.getSiteNm()+"</site-nm>\n";
				}else{
					tempContent2+="  <site-nm>"+mvListSub.getSiteNm().replaceAll("&", "&amp;")+"</site-nm>\n";
				}
				tempContent2+="  <menu-code>"+mvListSub.getMenuCode()+"</menu-code>\n";
				tempContent2+="  <sort-order>"+mvListSub.getSortOrder()+"</sort-order>\n";
				if(mvListSub.getMenuNm() == null){
					tempContent2+="  <menu-nm>"+mvListSub.getMenuNm()+"</menu-nm>\n";
				}else{
					if(mvListSub.getSubMenuTitle() == null){
						tempContent2+="  <menu-nm>"+mvListSub.getMenuNm().replaceAll("&", "&amp;")+"</menu-nm>\n";
					}else{
						tempContent2+="  <menu-nm>"+mvListSub.getMenuNm().replaceAll("&", "&amp;")+"_"+mvListSub.getSubMenuTitle().replaceAll("&", "&amp;")+"</menu-nm>\n";
					}
				}
				if(mvListSub.getTitle() == null){
					tempContent2+="  <menu-title>"+mvListSub.getTitle()+"</menu-title>\n";
				}else{
					tempContent2+="  <menu-title>"+mvListSub.getTitle().replaceAll("&", "&amp;")+"</menu-title>\n";
				}
				tempContent2+="  <satisfy-show-yn>"+mvListSub.getSatisfyShowYn()+"</satisfy-show-yn>\n";
				tempContent2+="  <charger-show-yn>"+mvListSub.getChargerShowYn()+"</charger-show-yn>\n";
				tempContent2+="  <social-show-yn>"+mvListSub.getSocialShowYn()+"</social-show-yn>\n";
				if(mvListSub.getMenuPath() ==  null){
					tempContent2+="  <menu-path>"+mvListSub.getMenuPath()+"</menu-path>\n";
				}else{
					tempContent2+="  <menu-path>"+mvListSub.getMenuPath().replaceAll("&", "&amp;")+"</menu-path>\n";
				}
				if(mvListSub.getUserMenuUrl() == null){
					tempContent2+="  <user-menu-url>"+mvListSub.getUserMenuUrl()+"</user-menu-url>\n";
				}else{
					tempContent2+="  <user-menu-url>"+mvListSub.getUserMenuUrl().replaceAll("&", "&amp;")+"</user-menu-url>\n";
				}
				tempContent2+="  <layout-url>"+mvListSub.getLayoutUrl()+"</layout-url>\n";
				tempContent2+="  <menu-type>"+mvListSub.getMenuType()+"</menu-type>\n";
				tempContent2+="  <dept-info>"+mvListSub.getDeptInfo()+"</dept-info>\n";
				tempContent2+="  <head-content><![CDATA["+mvListSub.getHeadContent()+"]]></head-content>\n";
				tempContent2+="  <metatag-content><![CDATA["+mvListSub.getMetaTagContent()+"]]></metatag-content>\n";
				tempContent2+="  <show-yn>"+mvListSub.getShowYn()+"</show-yn>\n";
				tempContent2+="</menu-layout>\n";
				

			}
			
			for(int i=0;i<menuVoList.size();i++){
				MenuVo mvList = menuVoList.get(i);
				int curDepth = Integer.parseInt(mvList.getMenuDepth());
				
				strContent=strContent.replaceAll("childMenu"+mvList.getSortOrder().substring(0,curDepth*2+1),"");
				strContent=strContent.replaceAll("sameMenu"+mvList.getSortOrder().substring(0,curDepth*2+1)+"_","");
			}
			strContent+="</menu>\n";
			
			tempContent2+="</menu>\n";
			
			String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
			
			Util util = new Util();
			String fileChk = util.fileWriteRootPath(request, filePath, fileName, strContent, rootPath);
			strContent = tempContent2;
			String fileChk2 = util.fileWriteRootPath(request, filePath, fileNameLayout, strContent, rootPath);
			
			utilSvc.cacheSelectClear("menuCache");
			
			String SVC_MSGE = Message.getMessage("201.message"); //등록 성공
			return alert("/boffice/cn/menu/menu_list.do?siteCd="+menuVo.getSiteCd()+"&menuCode="+menuVo.getMenuCode(), SVC_MSGE, model);
		}
		
		
		@RequestMapping("/boffice_noDeco/cn/menu/Menu_User_Create_All.do")
		public String menuUserCreateAll(
				HttpServletRequest request,
				@ModelAttribute("MenuVo") MenuVo menuVo,
				ModelMap model) throws Exception {
			
			
			SiteVo siteVo = new SiteVo();
			List<SiteVo> siteVoList = siteSvc.selectListSiteAll(siteVo);
			for(int i=0;i<siteVoList.size();i++){
				siteVo = siteVoList.get(i);
				
				System.out.println(siteVo.getSiteNm()+" 메뉴생성시작 ###########################");
				menuVo.setSiteCd(siteVo.getSiteCd());
				menuUserCreate(request,menuVo,model);
				System.out.println(siteVo.getSiteNm()+" 메뉴생성끝 ###########################");
				
			}
			
			
			String SVC_MSGE = Message.getMessage("201.message"); //등록 성공
			return alert("/boffice/cn/menu/menu_list.do?siteCd="+menuVo.getSiteCd()+"&menuCode="+menuVo.getMenuCode(), SVC_MSGE, model);
		}
		
		
		
		
		//수정
		@RequestMapping("/boffice/cn/menu/menu_order.do")
		public String menuOrder(
				HttpServletRequest request,
				@ModelAttribute("MenuVo") MenuVo menuVo,
				ModelMap model) throws Exception {
			
			menuVo.setTargetSortOrder(menuSvc.targetSortOrder(menuVo));
			
			String nowMenuLevel = menuSvc.menuOrderLevel(menuVo.getNowSortOrder());
			String moveMenuLevel = menuSvc.menuOrderLevel(menuVo.getTargetSortOrder());
			
			//System.out.println("nowMenuLevel:"+nowMenuLevel);
			//System.out.println("moveMenuLevel:"+moveMenuLevel);
			menuVo.setNowMenuLevel(nowMenuLevel);			
			menuVo.setMoveMenuLevel(moveMenuLevel);
			
			menuSvc.modifyMenuOrder1(menuVo);
			
			menuSvc.modifyMenuOrder2(menuVo);
			
			menuSvc.modifyMenuOrder3(menuVo);
			
			menuSvc.updateMenuPath(menuVo);

			String SVC_MSGE = Message.getMessage("401.message"); //등록 성공
			return alert("/boffice/cn/menu/menu_list.do?siteCd="+menuVo.getSiteCd()+"&menuCode="+menuVo.getMenuCode(), SVC_MSGE, model);
		}
		
		//A레이아웃 sub_right.jsp 동적 조회
		@RequestMapping(value={"/layout/{strDomain}/{strDomain}_a/sub_right.do","/layout/{strDomain}/{strDomain}_a/bottom.do"})
		public String rightLayoutA(
				@PathVariable("strDomain") String strDomain,
				HttpServletRequest request,
				@ModelAttribute("MenuVo") MenuVo menuVo,
				ModelMap model) throws Exception {
								
			String menuCode = EgovStringUtil.nullConvert(request.getAttribute("ssMenuCode"));
			String siteCd = EgovStringUtil.nullConvert(request.getAttribute("ssSiteCode"));
			String nowUrl = request.getRequestURI();
			if(nowUrl.indexOf("sub_right") == -1){
				nowUrl = "bottom";
			}else{
				nowUrl = "sub_right";
			}
			/*
			if(!menuCode.equals("1000000000000")){ //메인 페이지가 아닐경우 적용
				
				
				GroupDeptVo groupDeptVo= new GroupDeptVo();
				
				HashMap<String, Object> param = new HashMap<String, Object>();
				param.put("menuCode", menuCode);
				param.put("siteCd", siteCd);
				
				menuVo.setSiteCd(siteCd);
				menuVo.setMenuCode(menuCode);
				
				MenuVo menu = new MenuVo();
				if(menuVo != null){
					menu = menuSvc.selectEzUserMenu(menuVo);
				}
				
				
				
				if(model != null){
					model.addAttribute("menu", menu);
					if(nowUrl.indexOf("sub_right") == -1){ 
						EmpVo empVo = new EmpVo();
						if(!(menu.getChargerId()+"").replaceAll("null", "").equals("")){
							empVo.setCeUserId(menu.getChargerId());
							empVo = empSvc.retrieveEmp(empVo);
						}
						model.addAttribute("department", empVo);
					}else{ // 프로젝트 완료후 삭제
						groupDeptVo = groupDeptSvc.retrieveGroupDeptLayout(param);	//각메뉴 담당부서 뿌리기
						model.addAttribute("department", groupDeptVo);
					}
				}
			}	
			*/
			return GlobalConst.SITE_ROOT_FOFFICE_PATH + "/layout/"+strDomain+"_a/"+nowUrl;
		}
		
		//A레이아웃 sub_left.jsp 동적 조회
		@RequestMapping("/layout/{strDomain}/{strDomain}_a/sub_left.do")
		public String leftLayoutA(
				@PathVariable("strDomain") String strDomain,
				HttpServletRequest request,
				@ModelAttribute("MenuVo") MenuVo menuVo,
				ModelMap model) throws Exception {
			String menuCode = (String) WebUtils.getSessionAttribute(request, "ssMenuCode");
			String siteCd = (String) WebUtils.getSessionAttribute(request, "ssSiteCode");
			menuVo.setMenuCode(menuCode);
			menuVo.setSiteCd(siteCd);
			MenuVo menu = new MenuVo();
			if(menuVo != null){
				menu = menuSvc.selectEzUserMenu(menuVo);
			}
			
			if(model != null){
				model.addAttribute("menu", menu);
			}
			
			Map<String,String> params  = new HashMap<String,String>();
    		params.put("siteUpperMenuNo", siteCd);
        	params.put("siteMenuNo", menuCode);
        	if(request.getSession().getAttribute("ss_id") == null){
        		 params.put("rqesterId", "ANONYMOUS");
        	}else{
        		 params.put("rqesterId", (String)request.getSession().getAttribute("ss_id"));
        	}
    		params.put("rqesterIp", request.getRemoteAddr());
    		params.put("sessionId", request.getSession().getId());
    		
    		weblogSvc.insertSiteWebLog(params);
			
			return GlobalConst.SITE_ROOT_FOFFICE_PATH + "/layout/"+strDomain+"_a/sub_left";
		}
		
		
		//A레이아웃 static.jsp 동적 조회
		@RequestMapping("/common/siteStates.do")
		public String siteStats(
				HttpServletRequest request,
				@ModelAttribute("MenuVo") MenuVo menuVo,
				ModelMap model) throws Exception {
			
		
			return "/injeinc/common/site_states";
		}
		
}