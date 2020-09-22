package egovframework.injeinc.common.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory; 

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

import egovframework.injeinc.boffice.ex.board.service.ContentFileSvc;
import egovframework.injeinc.common.util.RssReadExamSvc;


public class RssReadExam {

	
    //http://www.kma.go.kr/wid/queryDFS.jsp?gridx=59&gridy=125
    private String rssFeed = "http://www.kma.go.kr/wid/queryDFS.jsp?gridx=%s&gridy=%s%s"; //주소
    
    
    private String rssFeedNation = "http://www.kma.go.kr/XML/weather/sfc_web_map.xml"; //전국 현재 날씨
    
    /**
     *  테스트를 위한 메인메소드
     */
    public static void main(String[] args) {
        
        RssReadExam r = new RssReadExam();
        r.getTownForecast("59","125","");        
        
    }  
    
    
    /**
     *  해당 좌표의 날씨 정보를 반환.
     */
    public List<Map<String, String>> getTownForecast(String x, String y, String etc) {        
        
               
        List<Map<String, String>> result = new ArrayList<Map<String, String>>();
                
        try {
        
            SAXBuilder parser = new SAXBuilder();        
            parser.setIgnoringElementContentWhitespace(true);
            
            //URL url = new URL(rssFeed);
            //InputSource is = new InputSource(url.openStream());
            //Document doc = parser.build(is);
            
            String url = String.format(rssFeed, x, y, etc);
            Document doc = parser.build(url);
            Element root = doc.getRootElement();
            
            Element channel = root.getChild("body");
            List<Element> list = channel.getChildren("data");
           
            
           
            for (int i=0; i<list.size(); i++) {
              
                Element el = (Element)list.get(i);
                
                Map<String, String> data = new LinkedHashMap<String, String>();
                
                data.put("seq", el.getAttributeValue("seq"));   //48시간중 몇번째 인지 가르킴
                data.put("hour",el.getChildTextTrim("hour") );     //동네예보 3시간 단위 
                data.put("day",el.getChildTextTrim("day") );     //1번째날 (0: 오늘/1: 내일/2: 모레)
                data.put("temp",el.getChildTextTrim("temp") );    //현재 시간온도
                data.put("tmx",el.getChildTextTrim("tmx") );    //최고 온도 
                data.put("tmn",el.getChildTextTrim("tmn") );    //최저 온도
                data.put("sky",el.getChildTextTrim("sky") );    //하늘 상태코드 (1: 맑음, 2: 구름조금, 3:구름많음, 4:흐림)
                data.put("pty",el.getChildTextTrim("pty") );    //강수 상태코드 (0: 없음, 1: 비, 2: 비/눈, 3: 눈/비, 4: 눈)
                data.put("wfkor",el.getChildTextTrim("wfKor") ); //날씨 한국어
                data.put("wfEn",el.getChildTextTrim("wfEn") );      //날씨 영어
                data.put("pop",el.getChildTextTrim("pop") );     //강수 확률%
                data.put("r12",el.getChildTextTrim("r12") );     //12시간 예상 강수량
                data.put("s12",el.getChildTextTrim("s12") );     //12시간 예상 적설량
                data.put("ws",el.getChildTextTrim("ws") );         //풍속(m/s)
                data.put("wd",el.getChildTextTrim("wd") );         //풍향 (0~7:북, 북동, 동, 남동, 남, 남서, 서, 북서)
                data.put("wdKor",el.getChildTextTrim("wdKor") ); //풍향 한국어
                data.put("wdEn",el.getChildTextTrim("wdEn") );     //풍향 영어
                data.put("reh",el.getChildTextTrim("reh") );     //습도 %
                data.put("r06",el.getChildTextTrim("r06") );     //6시간 예상 강수량
                data.put("s06",el.getChildTextTrim("s06") );     //6시간 예상 적설량
                
                System.out.println(data);
                result.add(data);
            }

        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return result;
    }    
    
    /**
     *  해당 좌표의 날씨 정보를 반환.
     */
    public HashMap<String, HashMap<String, String>> getTownForecastNation() {        
        	
    	HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		ServletContext conext = session.getServletContext();
		WebApplicationContext wContext = WebApplicationContextUtils.getWebApplicationContext(conext);
		RssReadExamSvc rssReadExamSvc = (RssReadExamSvc)wContext.getBean("RssReadExamSvc");
    	
    	
    			HashMap<String, HashMap<String, String>> resultList = new HashMap<String, HashMap<String, String>>();
    			try {
    				resultList = rssReadExamSvc.getTownForecastNation();
    			
    			}catch (Exception  e) {
    	            e.printStackTrace();
    	        }
    			return resultList;
               
    	/* HashMap<String, HashMap<String, String>> resultList = new HashMap<String, HashMap<String, String>>();
                
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
        
        return resultList;*/
    }
}