package egovframework.injeinc.boffice.cn.common;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.parsers.DocumentBuilderFactory;

import org.jdom2.CDATA;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.w3c.dom.NodeList;

import com.googlecode.ehcache.annotations.Cacheable;

import egovframework.cmm.EgovResourceCloseHelper;
import egovframework.cmm.service.EgovProperties;
import egovframework.injeinc.boffice.cn.menu.service.MenuSvc;
import egovframework.injeinc.common.util.WebUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import net.sf.ehcache.CacheManager;

@Service("UtilSvc")
public class UtilImpl extends EgovAbstractServiceImpl implements UtilSvc {
	@Resource(name = "MenuSvc")
	private MenuSvc menuSvc;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Util.class);
	
	private org.w3c.dom.Document parseXML(InputStream stream) throws Exception{
		javax.xml.parsers.DocumentBuilderFactory objDocumentBuilderFactory = null;
		javax.xml.parsers.DocumentBuilder objDocumentBuilder = null;
		org.w3c.dom.Document doc = null;
		try{
			objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
			objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();
			doc = objDocumentBuilder.parse(stream);
		}catch(Exception ex){
			throw ex;
		}
		return doc;
	}
	
	@Cacheable(cacheName = "menuCache")
	public String makeMenu(String inSiteCd) throws Exception {
		System.out.println("ehCacheTest:"+inSiteCd);
		return inSiteCd;
	}
	
	@Cacheable(cacheName = "menuCache")
	public List<Element> makeMenuXml(String inSiteCd) throws Exception {
		SAXBuilder builder = new SAXBuilder();
		System.out.println("ehCacheMenuCacheStart:"+inSiteCd);
		String filePath = EgovProperties.getProperty("WasServer.ROOT_PATH")+"/site/"+inSiteCd+"/menu.xml";
		File file = new File(filePath);
		
		InputStream ins = null;
		InputStreamReader isr = null;
		
		Element element = null;
		List<Element> elements = null;
		
		try {
			ins = new FileInputStream(file);
			isr = new InputStreamReader(ins,"UTF-8");
			Document doc = builder.build(isr);
		
			element = doc.getRootElement();
			elements = element.getChildren();
		}catch(FileNotFoundException fne) {
			LOGGER.debug("IGNORED: " + fne.getMessage());
			EgovResourceCloseHelper.close(isr);
			EgovResourceCloseHelper.close(ins);
		}catch(IOException ioe) {
			LOGGER.debug("IGNORED: " + ioe.getMessage());
			EgovResourceCloseHelper.close(isr);
			EgovResourceCloseHelper.close(ins);
		}catch(Exception e) {
			LOGGER.debug("IGNORED: " + e.getMessage());
			EgovResourceCloseHelper.close(isr);
			EgovResourceCloseHelper.close(ins);
		}finally{
			EgovResourceCloseHelper.close(isr);
			EgovResourceCloseHelper.close(ins);
		}
		return elements;
	}
	
	@Cacheable(cacheName = "menuCache")
	public String cacheCheck() throws Exception {
		WebUtil webUtil = new WebUtil();
		String cacheCheck = webUtil.readFile("/site/cache/cache_check",0);
		return cacheCheck;
	}
	
	public void cacheCheckReal(String oldCacheDate) throws Exception {
		WebUtil webUtil = new WebUtil();
		String cacheCheck = oldCacheDate;
		//System.out.println("cacheCheck():"+cacheCheck()+"/"+cacheCheck);
		if(!cacheCheck().equals(cacheCheck)){
			cacheClearAll();
		}
	}
	
	@Cacheable(cacheName = "menuCache")
	public List<Element> makeMenuLayoutXml(String inSiteCd) throws Exception {
		SAXBuilder builder = new SAXBuilder();
		System.out.println("ehCacheMakeMenuLayoutXmlStart:"+inSiteCd);
		String filePath = EgovProperties.getProperty("WasServer.ROOT_PATH")+"/site/"+inSiteCd+"/menu_layout.xml";
		File file = new File(filePath);
		
		Document doc = builder.build(file);
		
		Element element = doc.getRootElement();
		List<Element> elements = element.getChildren();
		return elements;
	}
	
	//@Cacheable(cacheName = "libCache") 
	public NodeList libXml(String searchString) throws Exception {
		System.out.println("libXmlStart:"+searchString);
		URL url = new URL("http://202.68.227.126/sync/sync_xml.asp");
		HttpURLConnection  connection = (HttpURLConnection)url.openConnection();
		HttpURLConnection  connection2 = null;
		connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=euc-kr");
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setUseCaches(false);
		connection.setRequestMethod("POST");		
		
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(connection.getOutputStream()));
		pw.write(searchString);
		pw.flush();
		org.w3c.dom.Document doc = parseXML(connection.getInputStream());
		NodeList descNodes = doc.getElementsByTagName("item");
		pw.close();
		return descNodes;
	}
	
	public void cacheClearAll() throws Exception {
		System.out.println("ehCache clear!");
		CacheManager.getInstance().clearAll();
	}
	
	public void cacheClear() throws Exception {
		System.out.println("ehCache file Create!");
		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
		Util util = new Util();
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmss");
		String str = sd.format(new Date()); // 0000년 00월 00 일 00:00:00 형식으로 얻어옴

		String fileChk = util.fileWriteAll(rootPath,"/site/cache/", "cache_check", str);
	//	CacheManager.getInstance().clearAll();
	}
	
	public void cacheSelectClear(String chacheName) throws Exception {
		System.out.println("ehCache "+chacheName+"  file Create!");
		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
		Util util = new Util();
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmss");
		String str = sd.format(new Date()); // 0000년 00월 00 일 00:00:00 형식으로 얻어옴
		
		String fileChk = util.fileWriteAll(rootPath,"/site/cache/", "cache_check",  str);
	//	CacheManager.getInstance().clearAllStartingWith(chacheName);
	}
	
	/*
	@Cacheable(cacheName = "menuCache")
	public List<Element> makeMenuDb(String inSiteCd) throws Exception {
		
		
		Element element = null;
		
		List<Element> elements = element.getChildren();
		
		

		
		MenuVo menuVo = new MenuVo();
		menuVo.setSiteCd(inSiteCd);
		
		List<MenuVo> menuVoList = menuSvc.selectEzUserMenuCreate(menuVo);
		List<MenuVo> menuVoSubList = menuSvc.selectEzUserMenuCreateSub(menuVo);
		
		LinkedHashMap<String, Object> dataDepth = new LinkedHashMap<String, Object>();
		LinkedHashMap<String, Object> dataDepthSub = new LinkedHashMap<String, Object>();

		int oldDepth = 0;
		String oldSortOrder = "";
		String tempContent2 = "";
		String headContent = "";
		
		for(int i=0;i<menuVoList.size();i++){
			MenuVo mvList = menuVoList.get(i);
			String tempContent = "";
			
			int curDepth = Integer.parseInt(mvList.getMenuDepth());

			dataDepthSub.put("site-cd", mvList.getSiteCd());
			dataDepthSub.put("menu-code", mvList.getMenuCode());
			dataDepthSub.put("sort-order", mvList.getSortOrder());
			dataDepthSub.put("menu-nm", mvList.getMenuNm());
			dataDepthSub.put("menu-title", mvList.getTitle());
			dataDepthSub.put("menu-depth", mvList.getMenuDepth());
			dataDepthSub.put("menu-path", mvList.getMenuPath());
			dataDepthSub.put("user-menu-url", mvList.getUserMenuUrl());
			dataDepthSub.put("search-engine", mvList.getSearchEngine());
			dataDepthSub.put("layout-url", mvList.getLayoutUrl());
			dataDepthSub.put("link-type", mvList.getLinkType());
			dataDepthSub.put("main-or-sub", "main");
			dataDepthSub.put("show-yn", mvList.getShowYn());
			dataDepthSub.put("child-menu", "");
			dataDepth.put("menu-part-"+mvList.getMenuDepth(), dataDepthSub);

//			dataDepth1.put("menu-part-"+mvList.getMenuDepth(), dataDepth1Sub);
			
			
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
			if(mvList.getSearchEngine() == null){
				tempContent+=tempSpace+"  <search-engine>"+mvList.getSearchEngine()+"</search-engine>\n";
			}else{
				tempContent+=tempSpace+"  <search-engine>"+mvList.getSearchEngine().replaceAll("&", "&amp;")+"</search-engine>\n";
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
			if(mvList.getSearchEngine()  == null){
				tempContent2+="  <search-engine>"+mvList.getSearchEngine()+"</search-engine>\n";
			}else{
				tempContent2+="  <search-engine>"+mvList.getSearchEngine().replaceAll("&", "&amp;")+"</search-engine>\n";
			}
			tempContent2+="  <layout-url>"+mvList.getLayoutUrl()+"</layout-url>\n";
			tempContent2+="  <menu-type>"+mvList.getMenuType()+"</menu-type>\n";
			tempContent2+="  <dept-info>"+mvList.getDeptInfo()+"</dept-info>\n";
			tempContent2+="  <head-content><![CDATA["+mvList.getHeadContent()+"]]></head-content>\n";
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
			tempContent2+="  <dept-info>"+mvListSub.getShowYn()+"</dept-info>\n";
			tempContent2+="  <head-content><![CDATA["+mvListSub.getHeadContent()+"]]></head-content>\n";
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
		
		return elements;
	}
	
	
	@Cacheable(cacheName = "menuCache")
	public Element makeMenuLayoutDb(String inSiteCd) throws Exception {
		SAXBuilder builder = new SAXBuilder();
		System.out.println("ehCacheMakeMenuLayoutXmlStart:"+inSiteCd);
		String filePath = EgovProperties.getProperty("WasServer.ROOT_PATH")+"/site/"+inSiteCd+"/menu_layout.xml";
		File file = new File(filePath);
		
		Document doc = builder.build(file);
		
		Element element = doc.getRootElement();
		
		return element;
	}
	*/
	
	public Document make(String rootStr){
        Element element = new Element(rootStr);
        Document document = new Document(element);
        return document;
	 }
	
	public Element addElement(Element targetElement, LinkedHashMap<String,Object> data){
        Iterator<String> iter = data.keySet().iterator();  
	        while(iter.hasNext()){
	                String key = iter.next();
	                Element childElement = new Element(key);
	                if(data.get(key) instanceof LinkedHashMap){
	                       addElement(childElement, (LinkedHashMap)data.get(key));
	                }else{
	                       //childElement.setText((String)data.get(key));
	                       childElement.addContent(new CDATA((String)data.get(key)));
	                }
	                targetElement.addContent(childElement);
	        }
	        return targetElement;
	 }


}



