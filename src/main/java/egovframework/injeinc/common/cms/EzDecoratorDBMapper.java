package egovframework.injeinc.common.cms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.util.WebUtils;

import egovframework.injeinc.boffice.cn.common.Util;
import egovframework.injeinc.boffice.cn.menu.service.MenuSvc;
import egovframework.injeinc.boffice.cn.menu.vo.MenuVo;
import egovframework.injeinc.boffice.group.dept.service.GroupDeptSvc;
import egovframework.injeinc.boffice.group.dept.vo.GroupDeptVo;
import egovframework.injeinc.common.util.EgovStringUtil;

public class EzDecoratorDBMapper {
	
	@SuppressWarnings("unchecked")
	public static MenuVo getMenuDecorator(HttpServletRequest request) throws Exception {
		
		MenuVo returnVo = null;
		
		String requestURI = request.getRequestURI();
		
		if(requestURI.indexOf("jsessionid") > -1) {
			requestURI = requestURI.substring(0, requestURI.indexOf(";jsessionid"));
		}
		
		String contextPath = request.getContextPath();
		requestURI = requestURI.replaceFirst(contextPath, "");
		
		String siteCd = Util.getSiteCode(request);
		
		MenuVo menuVo = new MenuVo();
		menuVo.setSiteCd(siteCd);
		menuVo.setSearchUrl(requestURI);
		
		HttpSession session = request.getSession();
		ServletContext conext = session.getServletContext();
		WebApplicationContext wContext = WebApplicationContextUtils.getWebApplicationContext(conext);
		MenuSvc menuSvc = (MenuSvc)wContext.getBean("MenuSvc");
		
		List<MenuVo> menuList = menuSvc.selectEzUserMenuAndSubUrls(menuVo);
		
		boolean find = false;
		
		for(MenuVo resultVo: menuList) {
			String userMenuUrl = resultVo.getUserMenuUrl();
			userMenuUrl = userMenuUrl.replace("&amp;", "&");
			
			if(requestURI.equals(userMenuUrl)) {
				find = true;
			}else{
				int idx = userMenuUrl.indexOf("?");
				if(idx > -1) {
					find = true;
					
					String[] menuParamArr = userMenuUrl.substring(idx + 1).split("&");
					for(String menuParam : menuParamArr) {
						String[] paramArr = menuParam.split("=");
						if(!EgovStringUtil.isNullToString(request.getParameter(paramArr[0])).equals(paramArr[1])) {
							find = false;
							break;
						}
					}
				}
			}

			if(find) {
				returnVo = resultVo;
				break;
			}
			
		}
		
		return returnVo;
	}
	
	
	public static GroupDeptVo getDepartInfo(HttpServletRequest request, MenuVo menuVo) throws Exception {
		
		HttpSession session = request.getSession();
		ServletContext conext = session.getServletContext();
		WebApplicationContext wContext = WebApplicationContextUtils.getWebApplicationContext(conext);
		GroupDeptSvc groupDeptSvc = (GroupDeptSvc)wContext.getBean("GroupDeptSvc");
		
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("siteCd", menuVo.getSiteCd());
		param.put("menuCode", menuVo.getMenuCode());
		
		return groupDeptSvc.retrieveGroupDeptLayout(param);
	}
	
	@SuppressWarnings("unchecked")
	public static String getTopMenuList(HttpServletRequest request, MenuVo menuVo) throws Exception {
		
		HttpSession session = request.getSession();
		ServletContext conext = session.getServletContext();
		WebApplicationContext wContext = WebApplicationContextUtils.getWebApplicationContext(conext);
		MenuSvc menuSvc = (MenuSvc)wContext.getBean("MenuSvc");
		
		List<MenuVo> list = menuSvc.selectListTopMenu(menuVo);
		
		StringBuffer sb = new StringBuffer();
		
		String tempShowYn = "N";
		String lastDepth = "";
		String currDepth = "";
		
		for(MenuVo resultVo : list) {
			
			currDepth = resultVo.getMenuDepth();
			
			if(currDepth.equals("1")) {
				if(resultVo.getShowYn().equals("Y")) {
					tempShowYn = "Y";
				}else{
					tempShowYn = "N";
				}
			}
			
			if(tempShowYn.equals("Y")) {
				
				if(currDepth.equals("1") && lastDepth.equals("")) {
					
				}else if(currDepth.equals("1") && lastDepth.equals("2")) {
					sb.append("</li></ul></div></li>");
				}else if(currDepth.equals("2") && lastDepth.equals("1")) {
					sb.append("<div><ul>");
				}else{
					sb.append("</li>");
				}
				
				sb.append("<li>");
				
				sb = makeMenuLink(sb, resultVo);
				
			}
			
			lastDepth = currDepth;
		}
		
		if(lastDepth.equals("2")) {
			sb.append("</ul></div></li>");
		}
		
		return sb.toString();
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getLeftMenuList(HttpServletRequest request, MenuVo menuVo) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		HttpSession session = request.getSession();
		ServletContext conext = session.getServletContext();
		WebApplicationContext wContext = WebApplicationContextUtils.getWebApplicationContext(conext);
		MenuSvc menuSvc = (MenuSvc)wContext.getBean("MenuSvc");
		
		List<MenuVo> list = menuSvc.selectListLeftMenu(menuVo);
		
		StringBuffer sb = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		
		String lastDepth = "";
		String currDepth = "";
		boolean arrYn = false;
		boolean onYn = false;
		
		String ss_level = EgovStringUtil.isNullToString(WebUtils.getSessionAttribute(request, "ss_level"));
		
		for(MenuVo resultVo : list) {
			
			currDepth = resultVo.getMenuDepth();

			if(currDepth.equals("1") && lastDepth.equals("")) {
				sb.append("<h2>").append(resultVo.getMenuNm()).append("</h2><nav>");
			}else if(ss_level.equals("8") && (resultVo.getMenuCode().equals("10701000000002015070605") || resultVo.getMenuCode().equals("10702000000002015070803"))) {
			
			}else if(!currDepth.equals("5")) {
				
				if(currDepth.equals("2") && lastDepth.equals("")) {
					sb.append("<ul class=\"dep1\">");
				}else if(currDepth.equals("3") && lastDepth.equals("2")) {
					sb.append("<ul class=\"dep2\">");
				}else if(currDepth.equals("4") && lastDepth.equals("3")) {
					sb.append("<ul class=\"dep3\">");
				}else if(Integer.parseInt(currDepth) < Integer.parseInt(lastDepth)) {
					for(int i = 0; i < Integer.parseInt(lastDepth)-Integer.parseInt(currDepth); i++) {
						sb.append("</ul></li>");
					}
				}else{
					sb.append("</li>");
				}
				
				if((currDepth.equals("2") || currDepth.equals("3")) && resultVo.getCnt() > 0) {
					arrYn = true;
				}
				
				if(currDepth.equals("2") && resultVo.getSortOrder().substring(0, 5).equals(menuVo.getSortOrder().substring(0, 5))) {
					onYn = true;
				}else if(currDepth.equals("3") && resultVo.getSortOrder().substring(0, 7).equals(menuVo.getSortOrder().substring(0, 7))) {
					onYn = true;
				}else if(currDepth.equals("4") && resultVo.getSortOrder().substring(0, 9).equals(menuVo.getSortOrder().substring(0, 9))) {
					onYn = true;
				}
				
				if(arrYn && onYn) {
					sb.append("<li class=\"arr on\">");
				}else if(arrYn) {
					sb.append("<li class=\"arr\">");
				}else if(onYn) {
					sb.append("<li class=\"on\">");
				}else{
					sb.append("<li>");
				}
				
				sb = makeMenuLink(sb, resultVo);
				
				lastDepth = currDepth;
			}else{
				if(currDepth.equals("5") && resultVo.getSortOrder().substring(0, 11).equals(menuVo.getSortOrder().substring(0, 11))) {
					sb2.append("<li class=\"on\">");
				}else{
					sb2.append("<li>");
				}
				sb2 = makeMenuLink(sb2, resultVo);
				sb2.append("</li>");
			}

			arrYn = false;
			onYn = false;
		}
		
		if(Integer.parseInt(currDepth) < Integer.parseInt(lastDepth)) {
			for(int i = 0; i < Integer.parseInt(lastDepth)-Integer.parseInt(currDepth); i++) {
				sb.append("</ul></li>");
			}
		}
		
		sb.append("</ul></nav>");
		
		map.put("leftMenu", sb.toString());
		map.put("tabMenu", sb2.toString());
		
		return map;
	}
	
	public static StringBuffer makeMenuLink(StringBuffer sb, MenuVo menuVo) {
		if(sb != null && menuVo != null){
			sb.append("<a href=\"").append(menuVo.getUserMenuUrl()).append("\"");
		}
		if(menuVo != null){
			if(menuVo.getLinkType().equals("popup")) {
				if(sb != null){
					sb.append(" target=\"_blank\" title=\"새창 열림\"");
				}
			}
		}
		if(sb != null && menuVo != null){
			sb.append(">").append(menuVo.getMenuNm()).append("</a>");
		}
		return sb;
	}

}
