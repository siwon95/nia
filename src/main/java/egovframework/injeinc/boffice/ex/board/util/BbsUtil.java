package egovframework.injeinc.boffice.ex.board.util;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import egovframework.injeinc.boffice.ex.board.service.ContentFileSvc;
import egovframework.injeinc.boffice.ex.board.vo.ContentFileVo;
import egovframework.injeinc.boffice.ex.holiday.service.HolidaySvc;
import egovframework.injeinc.common.util.DateUtil;

import egovframework.injeinc.foffice.ex.bbs.service.BbsFSvc;
import egovframework.injeinc.foffice.ex.bbs.vo.BbsFVo;

public class BbsUtil {
	
	public static Object getValue(Map<String, Object> map, String key) {
		Object obj = null;
		if(map != null && key != null){
			obj = map.get(key);
		}
		return obj;
	}
	
	public static ContentFileVo getBbsFileOne(int bcIdx, int cbIdx) throws Exception {
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		ServletContext conext = session.getServletContext();
		WebApplicationContext wContext = WebApplicationContextUtils.getWebApplicationContext(conext);
		ContentFileSvc contentFileSvc = (ContentFileSvc)wContext.getBean("ContentFileSvc");
		
		ContentFileVo contentFileVo = new ContentFileVo();
		
		if(bcIdx > 0 && cbIdx > 0) {
			
			contentFileVo.setBcIdx(bcIdx);
			contentFileVo.setCbIdx(cbIdx);
			contentFileVo = contentFileSvc.retrieveListContentFileOne(contentFileVo);
			
		}
		
		return contentFileVo;
	}
	
	public static List boardNewRecordNoCache(String cbIdx) throws Exception {
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		ServletContext conext = session.getServletContext();
		WebApplicationContext wContext = WebApplicationContextUtils.getWebApplicationContext(conext);
		BbsFSvc bbsFSvc = (BbsFSvc)wContext.getBean("BbsFSvc");
		
		BbsFVo bbsFVo = new BbsFVo();
		
		String[] arrCbIdx = (cbIdx+",empty").split(",");
		
		String[] searchCbIdxArr = new String[arrCbIdx.length-1];
		for(int i=0;i<arrCbIdx.length-1;i++){
			searchCbIdxArr[i] = arrCbIdx[i];
		}
		
		bbsFVo.setSearchCbIdxArr(searchCbIdxArr);
		
		return bbsFSvc.boardNewRecordMultiNoCache(bbsFVo);
	}
	
	
	public static List boardNewRecordCate(String cbIdx,String cateCont,String fileViewYn,String contentViewYn,String rowNum) throws Exception {
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		ServletContext conext = session.getServletContext();
		WebApplicationContext wContext = WebApplicationContextUtils.getWebApplicationContext(conext);
		BbsFSvc bbsFSvc = (BbsFSvc)wContext.getBean("BbsFSvc");
		
		BbsFVo bbsFVo = new BbsFVo();
		bbsFVo.setCbIdx(Integer.parseInt(cbIdx));
		bbsFVo.setCateCont(cateCont);
		bbsFVo.setFileViewYn(fileViewYn);
		bbsFVo.setContentViewYn(contentViewYn);
		bbsFVo.setRowNum(rowNum);
		return bbsFSvc.boardNewRecordCate(bbsFVo);
	}
	
	public static String getRssUseYn(String cbIdx) throws Exception {
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		ServletContext conext = session.getServletContext();
		WebApplicationContext wContext = WebApplicationContextUtils.getWebApplicationContext(conext);
		BbsFSvc bbsFSvc = (BbsFSvc)wContext.getBean("BbsFSvc");
						
		return bbsFSvc.selectRssUseYn(cbIdx);
	}
	
	public static List boardContentNewRecordNoCache(String cbIdx) throws Exception {
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		ServletContext conext = session.getServletContext();
		WebApplicationContext wContext = WebApplicationContextUtils.getWebApplicationContext(conext);
		BbsFSvc bbsFSvc = (BbsFSvc)wContext.getBean("BbsFSvc");
		
		BbsFVo bbsFVo = new BbsFVo();
		
		String[] arrCbIdx = (cbIdx+",empty").split(",");
		
		String[] searchCbIdxArr = new String[arrCbIdx.length-1];
		for(int i=0;i<arrCbIdx.length-1;i++){
			searchCbIdxArr[i] = arrCbIdx[i];
		}
		
		bbsFVo.setSearchCbIdxArr(searchCbIdxArr);
		
		return bbsFSvc.boardContentNewRecordMultiNoCache(bbsFVo);
	}
	
	public static String byteCalculation(String bytes) {
		
		String retFormat = "0";
		
		Double size = Double.parseDouble(bytes);

		String[] s = { "BYTE", "KB", "MB", "GB", "TB", "PB" };

		if(!"0".equals(bytes)) {
			int idx = (int) Math.floor(Math.log(size) / Math.log(1024));
			DecimalFormat df = new DecimalFormat("#,###.##");
			double ret = ((size / Math.pow(1024, Math.floor(idx))));
			retFormat = df.format(ret) + " " + s[idx];
		}else {
			retFormat += " " + s[0];
		}

		return retFormat;
	}

	public static int getProcessDate(String bRegDt, String modDt) throws Exception {
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		ServletContext conext = session.getServletContext();
		WebApplicationContext wContext = WebApplicationContextUtils.getWebApplicationContext(conext);
		HolidaySvc holidaySvc = (HolidaySvc)wContext.getBean("HolidaySvc");

		List<String> alStr = holidaySvc.retrieveListHolidayAll();
		
		int dayOfWeek = 0;
		int processDate = 1;
		if(bRegDt != null){
			if(Integer.parseInt(bRegDt.substring(11, 13)) >= 18) {
				processDate = 0; //18시 이후엔 해당일 제외하고 처리기일 처리
			}
		}

		if(bRegDt != null && modDt != null){
			for(String startDate = bRegDt.substring(0, 10); !startDate.equals(modDt.substring(0, 10)); startDate = DateUtil.add(startDate, 1)) {
	
				dayOfWeek = DateUtil.getDayOfWeek(startDate);
				if(dayOfWeek == 1) {//일요일일때 계산에서 제외
					
				}else if(dayOfWeek == 7) {//토요일일때 계산에서 제외
					
				}else if(alStr.contains(startDate) ){//공휴일일때 계산에서 제외
					
				}else{
					processDate++;
				}
			}
		}
		
		return processDate;
	}
	
	public static String boardContentRecommend(String cbIdx, String bcIdx) throws Exception {
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		ServletContext conext = session.getServletContext();
		WebApplicationContext wContext = WebApplicationContextUtils.getWebApplicationContext(conext);
		BbsFSvc bbsFSvc = (BbsFSvc)wContext.getBean("BbsFSvc");
	
		BbsFVo bbsFVo = new BbsFVo();
		
		bbsFVo.setCbIdx(Integer.parseInt(cbIdx));
		bbsFVo.setBcIdx(Integer.parseInt(bcIdx));
		bbsFVo.setMode("userPageCount");
		return (bbsFSvc.bbsRecommentCount(bbsFVo)+"");
	}
}