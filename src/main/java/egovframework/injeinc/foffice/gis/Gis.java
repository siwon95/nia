package egovframework.injeinc.foffice.gis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.EgovMessageSource;
import egovframework.cmm.EgovResourceCloseHelper;
import egovframework.cmm.service.EgovProperties;
import egovframework.injeinc.common.util.EgovStringUtil;
import egovframework.injeinc.common.util.WebUtil;

@Controller
public class Gis extends CmmLogCtr{
	
	@Autowired(required=true)
	private MappingJackson2JsonView jsonView;
	
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
	@RequestMapping(value= "/site/{strDomain}/gis/Gis_List.do")
	public String gisList(
			@PathVariable("strDomain") String strDomain,
			HttpServletRequest request,
			@ModelAttribute("GisVo") GisVo gisVo,
			ModelMap model ) throws Exception {
		if(model == null){
			return "injeinc/common/code500";
		}
		HashMap<String,String> type = new HashMap<String,String>();
		
		int isMobile = 0;
		String agent = request.getHeader("USER-AGENT");
		String[] mobileos = {"iPhone","iPod","Android","BlackBerry","Windows CE","Nokia","Webos","Opera Mini","SonyEricsson","Opera Mobi","IEMobile"};
		int j = -1;

		for(int i=0 ; i<mobileos.length ; i++) {
			j=agent.indexOf(mobileos[i]);
			if(j > -1 ) {
				// 모바일로 접근했을 때
				isMobile = 1;
				break;
			}
		}
		
		model.addAttribute("isMobile", isMobile);
		
		String returnUrl = "";
		if(gisVo.getType1().equals("edu")){
			type.put("어린이집", "어린이집");
			type.put("유치원", "유치원");
			type.put("초등학교", "초등학교");
			type.put("중학교", "중학교");
			type.put("고등학교", "고등학교");
			type.put("특수학교", "특수학교");
			gisVo.setTheme_id("100175");
			gisVo.setSubcate_id("100175,1");
			returnUrl = "injeinc/foffice/gis/gis_list";
		}else if(gisVo.getType1().equals("welfare")){
			
			type.put("경로당", "경로당");
			type.put("지역아동센터", "아동센터");
			type.put("장애인복지시설", "장애인");
			type.put("서울형 데이케어센터", "서울형 데이케어");
			type.put("보육지원센터", "보육지원");
			type.put("육아종합지원센터", "육아종합");
			type.put("종합사회복지관", "종합사회복지");
			type.put("기타복지시설", "기타복지");
			type.put("노인요양센터", "노인요양");
			type.put("복지문화회관", "복지문화");
			gisVo.setTheme_id("100175");
			gisVo.setSubcate_id("100175,2");
			returnUrl = "injeinc/foffice/gis/gis_list";
		}else if(gisVo.getType1().equals("depository")){
			type.put("지하철", "지하철");
			type.put("학교", "학교");
			type.put("기타", "기타");
			type.put("관공서", "관공서");
			type.put("공동주택", "공동주택");
			type.put("공원", "공원");
			gisVo.setValue_01("자전거보관대");
			gisVo.setTheme_id("100175");
			gisVo.setSubcate_id("100175,6");
			returnUrl = "injeinc/foffice/gis/gis_bike_list";
		}else if(gisVo.getType1().equals("parking")){
			type.put("공영주차장", "공영주차장");
			type.put("민영주차장", "민영주차장");
			gisVo.setTheme_id("100175");
			gisVo.setSubcate_id("100175,4");
			returnUrl = "injeinc/foffice/gis/gis_parking_list";
		}else if(gisVo.getType1().equals("snow")){
			gisVo.setTheme_id("100144");
			gisVo.setSubcate_id("100144,2");
			returnUrl = "injeinc/foffice/gis/gis_snow_list";
		}else if(gisVo.getType1().equals("hospital")){
			gisVo.setTheme_id("100175");
			gisVo.setSubcate_id("100175,3");
			returnUrl = "injeinc/foffice/gis/gis_hospital_list";
		}else if(gisVo.getType1().equals("road")){
			gisVo.setTheme_id("100175");
			gisVo.setSubcate_id("100175,6");
			returnUrl = "injeinc/foffice/gis/gis_bike_road_list";
		}else if(gisVo.getType1().equals("safe")){
			gisVo.setTheme_id("100218");
			returnUrl = "injeinc/foffice/gis/gis_safe";
		}else if(gisVo.getType1().equals("defibrillator")){
			gisVo.setTheme_id("100175");
			gisVo.setSubcate_id("100175,3");
			returnUrl = "injeinc/foffice/gis/gis_defibrillator_list";
		}else if(gisVo.getType1().equals("pharmacy")){
			gisVo.setTheme_id("100175");
			gisVo.setSubcate_id("100175,3");
			returnUrl = "injeinc/foffice/gis/gis_pharmacy_list";
		}else if(gisVo.getType1().equals("postnatal")){
			gisVo.setTheme_id("100175");
			gisVo.setSubcate_id("100175,3");
			returnUrl = "injeinc/foffice/gis/gis_postnatal_list";
		}
		model.addAttribute("type", type);
		model.addAttribute("strDomain", strDomain);
		model.addAttribute("GisVo", gisVo);
		model.addAttribute("NaverApiKey", EgovProperties.getProperty("naver.map.api.key"));
		
		return returnUrl;		
 	}
	/**
	 * gis컨텐츠 상세화면
	 */
	@RequestMapping(value= "/site/{strDomain}/gis/Gis_View.do")
	public String gisView(
			@PathVariable("strDomain") String strDomain,
			HttpServletRequest request,
			@ModelAttribute("GisVo") GisVo gisVo,
			ModelMap model ) throws Exception {
		if(model == null){
			return "injeinc/common/code500";
		}
		String theme_id = gisVo.getTheme_id();
		String conts_id = gisVo.getConts_id();
		
		String getUrl = "http://map.seoul.go.kr/smgis/apps/poi.do?cmd=getNewContentsDetail&key=5fb4b90ea33d4ce7b1010ca91af70d1e&theme_id="+theme_id+"&conts_id="+conts_id;

		//System.out.println(getUrl);

		URL url = null;
		InputStream in = null;
		InputStreamReader inr = null;
		BufferedReader reader = null;
		String returnUrl = "";
		try{ 
			url = new URL(getUrl);
			URLConnection connection = url.openConnection();

			connection.setDoOutput(true);
			connection.setRequestProperty("CONTENT-TYPE","text/html"); 

			in = url.openStream();
			inr = new InputStreamReader(in, "utf-8");
			reader = new BufferedReader(inr);

			int inputLine = 0;
			StringBuffer buffer = new StringBuffer();
			
			while ((inputLine = reader.read()) != -1) {
				buffer.append((char)inputLine);
			}
			
			 JSONParser jsonParser = new JSONParser();
			 
			 JSONObject jsonObject = (JSONObject) jsonParser.parse(buffer.toString());
			 
			 JSONArray bodyArray = (JSONArray) jsonObject.get("body");
			 JSONObject body = (JSONObject)bodyArray.get(0);
			 
			 gisVo.setCOT_COORD_DATA(body.get("COT_COORD_DATA").toString());
			 gisVo.setCOT_NAME_18((String)body.get("COT_NAME_18"));
			 gisVo.setCOT_IMG_MAIN_URL3((String)body.get("COT_IMG_MAIN_URL3"));
			 gisVo.setCOT_VALUE_19((String)body.get("COT_VALUE_19"));
			 gisVo.setCOT_VALUE_03((String)body.get("COT_VALUE_03"));
			 gisVo.setCOT_NAME_09((String)body.get("COT_NAME_09 "));
			 gisVo.setCOT_VALUE_16((String)body.get("COT_VALUE_16"));
			 gisVo.setCOT_VALUE_20((String)body.get("COT_VALUE_20"));
			 gisVo.setCOT_VALUE_05((String)body.get("COT_VALUE_05"));
			 gisVo.setCOT_EXTRA_DATA_01((String)body.get("COT_EXTRA_DATA_01"));
			 gisVo.setCOT_NAME_07((String)body.get("COT_NAME_07"));
			 gisVo.setCOT_CONTS_ID((String)body.get("COT_CONTS_ID"));
			 gisVo.setCOT_IMG_MAIN_URL4((String)body.get("COT_IMG_MAIN_URL4"));
			 gisVo.setCOT_NAME_06((String)body.get("COT_NAME_06"));
			 gisVo.setCOT_ASSESS_SCORE((String)body.get("COT_ASSESS_SCORE"));
			 gisVo.setCOT_MOVIE_URL((String)body.get("COT_MOVIE_URL"));
			 gisVo.setTHM_COORD_TYPE((String)body.get("THM_COORD_TYPE"));
			 gisVo.setCOT_NAME_05((String)body.get("COT_NAME_05"));
			 gisVo.setCOT_VALUE_04((String)body.get("COT_VALUE_04"));
			 gisVo.setCOT_DONG_NAME((String)body.get("COT_DONG_NAME"));
			 gisVo.setCOT_NAME_11((String)body.get("COT_NAME_11"));
			 gisVo.setCOT_IMG_MAIN_URL2((String)body.get("COT_IMG_MAIN_URL2"));
			 gisVo.setCOT_GU_NAME((String)body.get("COT_GU_NAME"));
			 gisVo.setCOT_WRITER((String)body.get("COT_WRITER"));
			 gisVo.setCOT_VALUE_17((String)body.get("COT_VALUE_17"));
			 gisVo.setCOT_NAME_12((String)body.get("COT_NAME_12"));
			 gisVo.setCOT_NAME_16((String)body.get("COT_NAME_16"));
			 gisVo.setCOT_IMG_MAIN_URL((String)body.get("COT_IMG_MAIN_URL"));
			 gisVo.setCOT_NAME_01((String)body.get("COT_NAME_01"));
			 gisVo.setCOT_LINE_PATTERN((String)body.get("COT_LINE_PATTERN"));
			 gisVo.setCOT_NAME_20((String)body.get("COT_NAME_20"));
			 gisVo.setCOT_VALUE_01((String)body.get("COT_VALUE_01"));
			 gisVo.setCOT_VALUE_18((String)body.get("COT_VALUE_18"));
			 gisVo.setCOT_VALUE_07((String)body.get("COT_VALUE_07"));
			 gisVo.setCOT_VALUE_12((String)body.get("COT_VALUE_12"));
			 gisVo.setCOT_NAME_15((String)body.get("COT_NAME_15"));
			 gisVo.setCOT_LINE_WEIGHT((String)body.get("COT_LINE_WEIGHT"));
			 gisVo.setCOT_CONTS_NAME((String)body.get("COT_CONTS_NAME"));
			 gisVo.setCOT_CONTS_DETAIL((String)body.get("COT_CONTS_DETAIL"));
			 gisVo.setCOT_VALUE_11((String)body.get("COT_VALUE_11"));
			 gisVo.setCOT_THEME_SUB_ID((String)body.get("COT_THEME_SUB_ID"));
			 gisVo.setCOT_NAME_10((String)body.get("COT_NAME_10"));
			 gisVo.setCOT_EXTRA_NAME((String)body.get("COT_EXTRA_NAME"));
			 gisVo.setCOT_NAME_03((String)body.get("COT_NAME_03"));
			 gisVo.setCOT_VALUE_08((String)body.get("COT_VALUE_08"));
			 gisVo.setCOT_SLAVE_NO((String)body.get("COT_SLAVE_NO"));
			 gisVo.setCOT_LINE_COLOR((String)body.get("COT_LINE_COLOR"));
			 gisVo.setCOT_VALUE_10((String)body.get("COT_VALUE_10"));
			 gisVo.setCOT_VALUE_09((String)body.get("COT_VALUE_09"));
			 gisVo.setCOT_NAME_04((String)body.get("COT_NAME_04"));
			 gisVo.setCOT_VALUE_13((String)body.get("COT_VALUE_13"));
			 gisVo.setCOT_VALUE_06((String)body.get("COT_VALUE_06"));
			 gisVo.setCOT_COORD_TYPE((String)body.get("COT_COORD_TYPE"));
			 gisVo.setCOT_REG_DATE((String)body.get("COT_REG_DATE"));
			 gisVo.setCOT_NATION_POINT_NUMBER((String)body.get("COT_NATION_POINT_NUMBER"));
			 gisVo.setCOT_NAME_19((String)body.get("COT_NAME_19"));
			 gisVo.setCOT_NAME_02((String)body.get("COT_NAME_02"));
			 gisVo.setCOT_TEL_NO((String)body.get("COT_TEL_NO"));
			 gisVo.setCOT_VOICE_URL((String)body.get("COT_VOICE_URL"));
			 gisVo.setCOT_NATION_BASE_AREA((String)body.get("COT_NATION_BASE_AREA"));
			 gisVo.setCOT_NAME_14((String)body.get("COT_NAME_14"));
			 gisVo.setCOT_THEME_ID((String)body.get("COT_THEME_ID"));
			 gisVo.setCOT_ADDR_FULL_NEW((String)body.get("COT_ADDR_FULL_NEW"));
			 gisVo.setCOT_VALUE_14((String)body.get("COT_VALUE_14"));
			 gisVo.setCOT_VALUE_15((String)body.get("COT_VALUE_15"));
			 gisVo.setCOT_COORD_X((String)body.get("COT_COORD_X"));
			 gisVo.setCOT_UPDATE_DATE((String)body.get("COT_UPDATE_DATE"));
			 gisVo.setCOT_IMG_MAIN_URL5((String)body.get("COT_IMG_MAIN_URL5"));
			 gisVo.setCOT_NAME_17((String)body.get("COT_NAME_17"));
			 gisVo.setCOT_EXTRA_DATA_02((String)body.get("COT_EXTRA_DATA_02"));
			 gisVo.setCOT_SAN_NAME((String)body.get("COT_SAN_NAME"));
			 gisVo.setCOT_ADDR_FULL_OLD((String)body.get("COT_ADDR_FULL_OLD"));
			 gisVo.setCOT_VALUE_02((String)body.get("COT_VALUE_02"));
			 gisVo.setCOT_CONTS_TYPE((String)body.get("COT_CONTS_TYPE"));
			 gisVo.setCOT_COORD_Y((String)body.get("COT_COORD_Y"));
			 gisVo.setCOT_CONTS_STAT((String)body.get("COT_CONTS_STAT"));
			 gisVo.setCOT_ASSESS_UCNT((String)body.get("COT_ASSESS_UCNT"));
			 gisVo.setCOT_NAME_08((String)body.get("COT_NAME_08"));
			 gisVo.setCOT_MASTER_NO ((String)body.get("COT_MASTER_NO"));
			 gisVo.setCOT_NAME_13((String)body.get("COT_NAME_13"));
			
			model.addAttribute("GisVo", gisVo);
			if(gisVo.getType1().equals("edu")){
				returnUrl = "injeinc/foffice/gis/gis_edu_view";
			}else if(gisVo.getType1().equals("welfare")){
				returnUrl = "injeinc/foffice/gis/gis_welfare_view";
			}else if(gisVo.getType1().equals("parking")){
				returnUrl = "injeinc/foffice/gis/gis_parking_view";
			}else if(gisVo.getType1().equals("snow")){
				returnUrl = "injeinc/foffice/gis/gis_snow_view";
			}else if(gisVo.getType1().equals("hospital")){
				returnUrl = "injeinc/foffice/gis/gis_hospital_view";
			}else if(gisVo.getType1().equals("pharmacy")){
				returnUrl = "injeinc/foffice/gis/gis_pharmacy_view";
			}else if(gisVo.getType1().equals("postnatal")){
				returnUrl = "injeinc/foffice/gis/gis_postnatal_view";
			}else{
				returnUrl = "";
			}
			
			model.addAttribute("NaverApiKey", EgovProperties.getProperty("naver.map.api.key"));
			
		}catch(MalformedURLException e){ //예외처리
			debugLog("IGNORED: " + e.getMessage());
		}catch(IOException e){ //예외처리
			debugLog("IGNORED: " + e.getMessage());
		}catch(Exception e){ //예외처리
			debugLog("IGNORED: " + e.getMessage());
		}finally{
			EgovResourceCloseHelper.close(reader);
			EgovResourceCloseHelper.close(inr);
			EgovResourceCloseHelper.close(in);
		}
		
		return returnUrl;		
 	}
	
    /**
	 * gis컨텐츠 리스트
	 */
	@RequestMapping(value = "/site/{strDomain}/gis/Gis_Content_List.do")
	public void gisContentList(
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String page_size = WebUtil.nvl(request.getParameter("page_size"));
		String page_no = WebUtil.nvl(request.getParameter("page_no"));
		String search_name = WebUtil.nvl(request.getParameter("search_name"));
		String theme_id   = WebUtil.nvl(request.getParameter("theme_id"));
		String subcate_id = WebUtil.nvl(request.getParameter("subcate_id"));
		String value_01 = WebUtil.nvl(request.getParameter("value_01"));

		String getUrl = "http://map.seoul.go.kr/smgis/apps/theme.do?cmd=getContentsList&key=5fb4b90ea33d4ce7b1010ca91af70d1e&page_size="+page_size+"&page_no="+page_no+"&coord_x=127.0145021&coord_y=37.4883454&distance=&search_type=1&search_name="+search_name+"&theme_id="+theme_id+"&content_id=&subcate_id="+subcate_id+"&value_01="+value_01;
		//System.out.println(getUrl);

		URL url = null;
		InputStream in = null;
		InputStreamReader inr = null;
		BufferedReader reader = null;

		try{ 
			url = new URL(getUrl);
			URLConnection connection = url.openConnection();

			connection.setDoOutput(true);
			connection.setRequestProperty("CONTENT-TYPE","text/html"); 

			in = url.openStream();
			inr = new InputStreamReader(in, "utf-8");
			reader = new BufferedReader(inr);

			int inputLine = 0;	
			StringBuffer buffer = new StringBuffer();
			
			while ((inputLine = reader.read()) != -1) {
				buffer.append((char)inputLine);
			}
			
			HashMap<String, Object> jsonMap = new HashMap<String, Object>();
			
			jsonMap.put("buffer", buffer);
			
			jsonView.render(jsonMap, request, response);
			
		}catch(MalformedURLException e){ //예외처리
			debugLog("IGNORED: " + e.getMessage());
		}catch(IOException e){ //예외처리
			debugLog("IGNORED: " + e.getMessage());
		}catch(Exception e){ //예외처리
			debugLog("IGNORED: " + e.getMessage());
		}finally{
			EgovResourceCloseHelper.close(reader);
			EgovResourceCloseHelper.close(inr);
			EgovResourceCloseHelper.close(in);
		}
 	}
	
	 /**
	 * gis 컨텐츠 상세
	 */
	@RequestMapping(value = "/site/{strDomain}/gis/Gis_Content_Detail.do")
	public void gisContentDetail(
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String theme_id = WebUtil.nvl(request.getParameter("theme_id"));
		String conts_id = WebUtil.nvl(request.getParameter("conts_id"));

		String getUrl = "http://map.seoul.go.kr/smgis/apps/poi.do?cmd=getNewContentsDetail&key=5fb4b90ea33d4ce7b1010ca91af70d1e&theme_id="+theme_id+"&conts_id="+conts_id;

		//System.out.println("테스트");
		//System.out.println(getUrl);

		URL url = null;
		InputStream in = null;
		InputStreamReader inr = null;
		BufferedReader reader = null;

		try{ 
			url = new URL(getUrl);
			URLConnection connection = url.openConnection();

			connection.setDoOutput(true);
			connection.setRequestProperty("CONTENT-TYPE","text/html"); 

			in = url.openStream();
			inr = new InputStreamReader(in, "utf-8");
			reader = new BufferedReader(inr);

			int inputLine = 0;
			StringBuffer buffer = new StringBuffer();
			
			while ((inputLine = reader.read()) != -1) {
				buffer.append((char)inputLine);
			}
			
			HashMap<String, Object> jsonMap = new HashMap<String, Object>();
			
			jsonMap.put("buffer", buffer);
			
			jsonView.render(jsonMap, request, response);
		}catch(MalformedURLException e){ //예외처리
			debugLog("IGNORED: " + e.getMessage());
		}catch(IOException e){ //예외처리
			debugLog("IGNORED: " + e.getMessage());
		}catch(Exception e){ //예외처리
			debugLog("IGNORED: " + e.getMessage());
		}finally{
			EgovResourceCloseHelper.close(reader);
			EgovResourceCloseHelper.close(inr);
			EgovResourceCloseHelper.close(in);
		}
 	}
	
	/**
	 * 청사안내
	 */
	@RequestMapping(value= "/site/{strDomain}/Gis_Map.do")
	public String gisMap(
			@PathVariable("strDomain") String strDomain,
			HttpServletRequest request,
			ModelMap model ) throws Exception {
		if(model == null){
			return "injeinc/common/code500";
		}
		String xMap="";
		String yMap="";
		String width="";
		String height="";
		String title="";
		
		model.addAttribute("NaverApiKey", EgovProperties.getProperty("naver.map.api.key"));
		model.addAttribute("xMap", xMap);
		model.addAttribute("yMap", yMap);
		model.addAttribute("width", width);
		model.addAttribute("height", height);
		model.addAttribute("title", title);
		
		return "injeinc/foffice/gis/gis_map";
	}
}