package egovframework.injeinc.common.cms;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import egovframework.injeinc.boffice.cn.common.Util;
import egovframework.injeinc.boffice.cn.common.UtilSvc;
import egovframework.injeinc.boffice.cn.menu.service.MenuSvc;
import egovframework.injeinc.boffice.cn.menu.vo.MenuVo;

import com.opensymphony.module.sitemesh.Config;
import com.opensymphony.module.sitemesh.Decorator;
import com.opensymphony.module.sitemesh.DecoratorMapper;
import com.opensymphony.module.sitemesh.Page;
import com.opensymphony.module.sitemesh.mapper.AbstractDecoratorMapper;





/*import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
*/
import org.jdom2.Element;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
/**
 * 사용자 매의 레이아웃 매핑 
 * 20150611/진철/메뉴에서 레이아웃 정보를 불러오기 위한 작업
 **/
public class EzDecoratorMapper extends AbstractDecoratorMapper {
	public Decorator getDecorator(HttpServletRequest request, Page page) {
		String decorator = null;
		
		Decorator result = null;
		return result;
	}
	/**
     * 실 사용자 메뉴 데코레이터를 설정
     * 
     * @param request
     */
    @SuppressWarnings("unchecked")
    public static MenuVo getMenuDecorator(HttpServletRequest request, String userMenuUrl) throws Exception {
    	
    	HttpSession hs = request.getSession();
        String reqUri = userMenuUrl;
        
        if(Util.isEmpty(reqUri)) {
            reqUri = request.getRequestURI();
        }
        
        if(reqUri.indexOf("jsessionid") > -1) {
            reqUri = reqUri.substring(0, reqUri.indexOf(";jsessionid"));
        }
        
        /*
         * QueryString()으로는 post 형식의 메뉴를 매핑 할 수 없으므로 만들어 낸다.
         */
        String enumStr = "";
        Enumeration<String> enumer = request.getParameterNames();

        StringBuilder sb = new StringBuilder();

        while(enumer.hasMoreElements()) {
            enumStr = enumer.nextElement();
            // 30 이상은 필요 없는 데이터라 판정하여 생성하지 않는다.
            
            if(request.getParameter(enumStr).length() < 30 && !"targetUrl".equals(enumStr)) {
            	if(reqUri.indexOf("/bbs/")>-1){
	            	if(enumStr.equals("cbIdx")){
	            		sb.append(enumStr).append("=").append(request.getParameter(enumStr)).append("&");
	            		break;
	            	}
            	}else{
            		sb.append(enumStr).append("=").append(request.getParameter(enumStr)).append("&");
            	}
            }
        }

        if(!enumStr.equals("")) {
            sb.insert(0, reqUri + "?");
        } else {
            sb.append(reqUri);
        }
        
        String reqUrl = sb.toString();
        String contextPath = request.getContextPath();

        reqUrl = reqUrl.replaceFirst(contextPath, "");
        
        
    	String siteCd = Util.getSiteCode(request)+"";
    	
    	/*캐쉬에서 가져 오기 위함*/
    	
    	HttpSession session = request.getSession();
		ServletContext conext = session.getServletContext();
    	WebApplicationContext wContext = WebApplicationContextUtils.getWebApplicationContext(conext);
    	UtilSvc utilSvc = (UtilSvc)wContext.getBean("UtilSvc");
    	
    	String oldCacheDate = utilSvc.cacheCheck();
    	utilSvc.cacheCheckReal(oldCacheDate);
    	
    	List<Element> menuList = utilSvc.makeMenuLayoutXml(siteCd);
    	
    	//List<Element> menuList = Util.makeMenuLayoutXml(siteCd,request);
    	
    	String requestUri = userMenuUrl;
    	if(Util.isEmpty(requestUri)){
    		requestUri = request.getRequestURI();
    	} 
    	
    	
    	int idx = 0;
    	boolean find = false;

    	String menuUrl = "";
    	String[] arrMenu = { "", "" };

    	reqUrl = reqUrl.replaceFirst(contextPath, "");
    	String url = "";
		String sortOrder = "";
		String layoutUrl = "";
		String menuType = "";
		String satisfyShowYn = "";
		String chargerShowYn = "";
		String menuPath = "";
		String siteNm = "";
		String menuNm = "";
		String title = "";
		String menuCode = "";
		String siteCode = "";
		String deptInfo = "";
		String headContent = "";
		String showYn = "";
		String socialShowYn = "";
		String metaTagContent = "";
		
		MenuVo menuVo = new MenuVo();
    	 for(Element element:menuList){
    		url = element.getChild("user-menu-url").getText();
    		url = url.replace("&amp;", "&");
    		sortOrder = element.getChild("sort-order").getText();
    		layoutUrl = element.getChild("layout-url").getText();
    		satisfyShowYn = element.getChild("satisfy-show-yn").getText();
    		socialShowYn = element.getChild("social-show-yn").getText();
    		chargerShowYn = element.getChild("charger-show-yn").getText();
    		menuNm = element.getChild("menu-nm").getText();
    		title = element.getChild("menu-title").getText();
    		menuPath = element.getChild("menu-path").getText();
    		siteNm = element.getChild("site-nm").getText();
    		menuCode = element.getChild("menu-code").getText();
    		siteCode = element.getChild("site-cd").getText();
    		deptInfo = element.getChild("dept-info").getText();
    		metaTagContent = element.getChild("metatag-content").getText();
    		if((sortOrder+"0000000000").substring(7,9).equals("00")){ // 3뎁스 일경우 headContent 비워주고 이하 headContent가 있을경우 내용을 넣어줌
    			headContent = "";
    		}
    		if(element.getChild("head-content").getText().replaceAll("null", "").trim().length()>5){
    			headContent = element.getChild("head-content").getText();
    		}
    		showYn = element.getChild("show-yn").getText();
    		menuType = element.getChild("menu-type").getText();
    		
    		if(!menuType.equals("link")){
	    		if(reqUri.equals(url)) {
	    	        find = true;
	    	        break;
	    	    } else {
	    	        idx = url.indexOf("?");
	    	        if(idx > -1) {
	    	            find = true;
	    	            arrMenu[0] = url.substring(0, idx);
	    	            arrMenu[1] = url.substring(idx + 1);
	    	            
	    	            if(reqUrl.indexOf(arrMenu[0]) > -1) {
	    	            	String strUrl = reqUrl.substring(reqUrl.indexOf("?")+1);
	    	                String[] arrRequestParam = arrMenu[1].split("&");
	    	                for(String requestParam : arrRequestParam) {
	    	                	String[] fvwdfew = requestParam.split("=");
	    	                	if(!fvwdfew[1].equals(request.getParameter(fvwdfew[0]))){
	    	                		 find = false;
	    	                		 break;
	    	                	}
	    	                }
	    	                if(find) {
	    	                    break;
	    	                }
	    	            } else {
	    	                find = false;
	    	            }
	    	        }
	    	    }
    		}
    	 }
    	 if(find){
     		menuVo.setSortOrder(sortOrder);
     		menuVo.setUserMenuUrl(url);
     		menuVo.setLayoutUrl(layoutUrl);
     		menuVo.setMenuPath(menuPath);
     		menuVo.setSiteNm(siteNm);
     		menuVo.setMenuNm(menuNm);
     		menuVo.setTitle(title);
     		menuVo.setMenuCode(menuCode);
     		menuVo.setSiteCd(siteCode);
     		menuVo.setDeptInfo(deptInfo);
     		menuVo.setHeadContent(headContent);
     		menuVo.setSatisfyShowYn(satisfyShowYn);
     		menuVo.setChargerShowYn(chargerShowYn);
     		menuVo.setShowYn(showYn);
     		menuVo.setMenuType(menuType);
     		menuVo.setSocialShowYn(socialShowYn);
     		menuVo.setMetaTagContent(metaTagContent);
     	}
    	
    	return menuVo;
    }
    
    /*public static void setMenuCache(String siteCode,HashMap arrSite){
    	 
		//1. Create a cache manager
		CacheManager cm = CacheManager.getInstance();
 
		//2. Create a cache called 
		cm.addCache(siteCode);
 
		//3. Get a cache called 
		Cache cache = cm.getCache(siteCode);
 
		//4. Put few elements in cache
		cache.put(new Element(siteCode,arrSite));

 
		//5. Get element from cache
		Element ele = cache.get("1");
 
		//6. Print out the element
		String output = (ele == null ? null : ele.getObjectValue().toString());
		System.out.println(output);
 
		//7. Is key in cache?
		System.out.println(cache.isKeyInCache("1"));
		System.out.println(cache.isKeyInCache("5"));
 
		//8. shut down the cache manager
		//cm.shutdown();
 	}
    
    public static List getMenuCache(String siteCode){
    	CacheManager cm = CacheManager.getInstance();
		Cache cache = cm.getCache(siteCode);
		return (List)cache.get(siteCode);
 	}
    */
    

}
