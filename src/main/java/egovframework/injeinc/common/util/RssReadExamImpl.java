package egovframework.injeinc.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.springframework.stereotype.Service;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import com.googlecode.ehcache.annotations.Cacheable;

@Service("RssReadExamSvc")
public class RssReadExamImpl extends EgovAbstractServiceImpl implements RssReadExamSvc {
	
	 //http://www.kma.go.kr/wid/queryDFS.jsp?gridx=59&gridy=125
	private String rssFeedNation = "http://www.kma.go.kr/XML/weather/sfc_web_map.xml"; //전국 현재 날씨

	@Cacheable(cacheName = "minute30Cache")
    public HashMap<String, HashMap<String, String>> getTownForecastNation() {        
        
        System.out.println("WeatherCache Start");
   	 HashMap<String, HashMap<String, String>> resultList = new HashMap<String, HashMap<String, String>>();
               
       try {
			String url = String.format(rssFeedNation);
			URL send = new URL(url);
			   HttpURLConnection con = (HttpURLConnection)send.openConnection();
			   BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			   InputSource is = new InputSource(br);
			   DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			   factory.setIgnoringElementContentWhitespace(true);
			   DocumentBuilder db = factory.newDocumentBuilder();
			   
			   org.w3c.dom.Document doc = db.parse(is);
			   org.w3c.dom.Element root = doc.getDocumentElement();
			   
			   //결과물 출력 되었는지 체크
			   NodeList locals = root.getElementsByTagName("local");
			   
			   //결과물이 있을 경우
			   if(locals.getLength() > 0) {
			    
			    String nodeValue = null;
			    HashMap<String, String> itemData = null;
			    
			    for(int i=0; i<locals.getLength(); i++) {
			     
		    	org.w3c.dom.Element n = (org.w3c.dom.Element)locals.item(i);
			     
			     nodeValue = n.getFirstChild().getNodeValue();
			     
			     itemData = new HashMap<String, String>();
			     itemData.put("stn_id", n.getAttribute("stn_id"));
			     itemData.put("icon", n.getAttribute("icon"));
			     

			     itemData.put("desc", n.getAttribute("desc"));
			     itemData.put("ta", n.getAttribute("ta"));
			     itemData.put("nodeValue", nodeValue);
			     
			     if(nodeValue!=null && itemData!=null) resultList.put(nodeValue, itemData);
			    }   
			   } 
       } catch (Exception  e) {
           e.printStackTrace();
       }
       
       return resultList;
   }
	
}