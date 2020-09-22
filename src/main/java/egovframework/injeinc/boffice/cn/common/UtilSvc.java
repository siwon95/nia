package egovframework.injeinc.boffice.cn.common;
import java.util.List;

import org.jdom2.Element;
import org.w3c.dom.NodeList;

public interface UtilSvc {
	public String makeMenu(String inSiteCd) throws Exception;
	
	public List<Element> makeMenuXml(String inSiteCd) throws Exception;
	
	public List<Element> makeMenuLayoutXml(String inSiteCd) throws Exception;
	
	public NodeList libXml(String searchString) throws Exception;
	
	//public List<Element> makeMenuDb(String inSiteCd) throws Exception;
	
	//public List<Element> makeMenuLayoutDb(String inSiteCd) throws Exception;
	public String cacheCheck() throws Exception;
	public void cacheCheckReal(String oldCacheDate) throws Exception;
	public void cacheClear() throws Exception;
	public void cacheSelectClear(String chacheName) throws Exception;
}
