package egovframework.injeinc.boffice.stat.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.cmm.EgovWebUtil;
import egovframework.cmm.service.EgovProperties;
import egovframework.injeinc.boffice.cn.site.service.SiteSvc;
import egovframework.injeinc.boffice.cn.site.vo.SiteVo;
import egovframework.injeinc.boffice.excel.controller.ExcelCtr;
import egovframework.injeinc.common.weblog.service.WeblogSvc;
import egovframework.injeinc.common.weblog.vo.WeblogVo;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Controller
public class StatCtr {
	
	private static final Log log = LogFactory.getLog(StatCtr.class);
	
	@Resource(name = "WeblogSvc")
	private WeblogSvc weblogSvc;
	
	@Resource(name = "SiteSvc")
	private SiteSvc siteSvc;
	
	/** 웹로그현황(오늘) 조회 */
    @RequestMapping(value= "/boffice/stat/selectWebLogStatToday.do")
	public String selectWebLogStatToday(
			@ModelAttribute("weblogVo") WeblogVo weblogVo
			, ModelMap model) throws Exception {
    	if(model == null){
			return "injeinc/common/code500";
		}
    	System.out.println("weblogVo1 : "+weblogVo);
    	if (StringUtils.isBlank(weblogVo.getSearchSiteNo())) {
    		SiteVo sVo = siteSvc.selectSiteDefault();
    		weblogVo.setSearchSiteNo(sVo.getSiteCd());
		}
    	
    	System.out.println("weblogVo2 : "+weblogVo);
    	SiteVo siteVo = new SiteVo();
    	List<SiteVo> selectListSiteAll = siteSvc.selectListSiteAll(siteVo);
    	model.addAttribute("selectListSiteAll", selectListSiteAll);
    	
		// 오늘 접속 IP수, 오늘 방문수, 오늘 페이지뷰, 오늘 메뉴별 페이지뷰
		model.addAttribute("siteVisitrCoTodayList", weblogSvc.selectSiteVisitrCoTodayList(weblogVo));
		model.addAttribute("siteSessionVisitrCoTodayList", weblogSvc.selectSiteSessionVisitrCoTodayList(weblogVo));
		model.addAttribute("sitePageviewTodayList", weblogSvc.selectSitePageviewTodayList(weblogVo));
		model.addAttribute("siteMenuPageviewTodayList", weblogSvc.selectSiteMenuPageviewTodayList(weblogVo));
		
		return "injeinc/boffice/stat/webLogStatToday";    	
 	} 
    
	/** 웹로그현황(요약) 조회 */    
    @RequestMapping(value= "/boffice/stat/selectWebLogStatSummary.do")
	public String selectWebLogStatSummary(
			@ModelAttribute("weblogVo") WeblogVo weblogVo
			, ModelMap model) throws Exception {
    	if(model == null){
			return "injeinc/common/code500";
		}
    	if (StringUtils.isBlank(weblogVo.getSearchSiteNo())) {
    		SiteVo sVo = siteSvc.selectSiteDefault();
    		weblogVo.setSearchSiteNo(sVo.getSiteCd());
		}
    	
    	// 어제날짜
		Date selDate = new Date();
		selDate.setTime(new java.util.Date().getTime() + (1000 * 60 * 60 * 24) * -1);
		String selDe = new SimpleDateFormat("yyyy-MM-dd").format(selDate);
		
    	// 7일전
		Date selStartDate = new Date();
		selStartDate.setTime(new java.util.Date().getTime() + (1000 * 60 * 60 * 24) * -7);
		String selStartDe = new SimpleDateFormat("yyyy-MM-dd").format(selStartDate);
		
		if (StringUtils.isBlank(weblogVo.getSearchCondition())) {
			weblogVo.setSearchCondition("0");
		}
		if (StringUtils.isBlank(weblogVo.getSearchDe())) {
			weblogVo.setSearchDe(selDe);
		}
		if (StringUtils.isBlank(weblogVo.getSearchStartDe())) {
			weblogVo.setSearchStartDe(selStartDe);
		}
		if (StringUtils.isBlank(weblogVo.getSearchEndDe())) {
			weblogVo.setSearchEndDe(selDe);
		}
		
		SiteVo siteVo = new SiteVo();
    	List<SiteVo> selectListSiteAll = siteSvc.selectListSiteAll(siteVo);
    	model.addAttribute("selectListSiteAll", selectListSiteAll);
		
    	// 요약 접속 IP수, 요약 방문수, 요약 페이지뷰, 요약 메뉴별 페이지뷰
		model.addAttribute("siteVisitrCoSummaryList", weblogSvc.selectSiteVisitrCoSummaryList(weblogVo));
		model.addAttribute("siteSessionVisitrCoSummaryList", weblogSvc.selectSiteSessionVisitrCoSummaryList(weblogVo));
		model.addAttribute("sitePageviewSummaryList", weblogSvc.selectSitePageviewSummaryList(weblogVo));		
		model.addAttribute("siteMenuPageviewSummaryList", weblogSvc.selectSiteMenuPageviewSummaryList(weblogVo));		
		
		return "injeinc/boffice/stat/webLogStatSummary";
 	}    
    
	/** 기간별 접속통계 조회 */
    @RequestMapping(value= "/boffice/stat/selectWebLogStatPeriod.do")
	public String selectWebLogStatPeriod(
			@ModelAttribute("weblogVo") WeblogVo weblogVo
			, ModelMap model) throws Exception {
		if(model == null){
			return "injeinc/common/code500";
		}
    	if (StringUtils.isBlank(weblogVo.getSearchSiteNo())) {
    		SiteVo sVo = siteSvc.selectSiteDefault();
    		weblogVo.setSearchSiteNo(sVo.getSiteCd());
		}
    	
    	// 어제날짜
		Date selDate = new Date();
		selDate.setTime(new java.util.Date().getTime() + (1000 * 60 * 60 * 24) * -1);
		String selDe = new SimpleDateFormat("yyyy-MM-dd").format(selDate);
		
		// 7일전
		Date selStartDate = new Date();
		selStartDate.setTime(new java.util.Date().getTime() + (1000 * 60 * 60 * 24) * -7);
		String selStartDe = new SimpleDateFormat("yyyy-MM-dd").format(selStartDate);
		
		if (StringUtils.isBlank(weblogVo.getSearchCondition())) {
			weblogVo.setSearchCondition("day");
		}
		if (StringUtils.isBlank(weblogVo.getSearchStartDe())) {
			weblogVo.setSearchStartDe(selStartDe);
		}
		if (StringUtils.isBlank(weblogVo.getSearchEndDe())) {
			weblogVo.setSearchEndDe(selDe);
		}
		
		SiteVo siteVo = new SiteVo();
    	List<SiteVo> selectListSiteAll = siteSvc.selectListSiteAll(siteVo);
    	model.addAttribute("selectListSiteAll", selectListSiteAll);
    	
    	// 기간별 접속 IP수, 기간별 방문수, 기간별 페이지뷰
		model.addAttribute("siteVisitrCoPeriodList", weblogSvc.selectSiteVisitrCoPeriodList(weblogVo));
		model.addAttribute("siteSessionVisitrCoPeriodList", weblogSvc.selectSiteSessionVisitrCoPeriodList(weblogVo));
		model.addAttribute("sitePageviewPeriodList", weblogSvc.selectSitePageviewPeriodList(weblogVo));		

		return "injeinc/boffice/stat/webLogStatPeriod";
 	}
    
    @RequestMapping(value= "/boffice/stat/selectWebLogStatPeriodExcel.do")
	public void selectWebLogStatPeriodExcel(
			@ModelAttribute("weblogVo") WeblogVo weblogVo
			, HttpServletRequest request,HttpServletResponse response
			, ModelMap model) throws Exception {
			
		if (StringUtils.isBlank(weblogVo.getSearchSiteNo())) {
    		SiteVo sVo = siteSvc.selectSiteDefault();
    		weblogVo.setSearchSiteNo(sVo.getSiteCd());
		}
    	
    	// 어제날짜
		Date selDate = new Date();
		selDate.setTime(new java.util.Date().getTime() + (1000 * 60 * 60 * 24) * -1);
		String selDe = new SimpleDateFormat("yyyy-MM-dd").format(selDate);
		
		// 7일전
		Date selStartDate = new Date();
		selStartDate.setTime(new java.util.Date().getTime() + (1000 * 60 * 60 * 24) * -7);
		String selStartDe = new SimpleDateFormat("yyyy-MM-dd").format(selStartDate);
		
		if (StringUtils.isBlank(weblogVo.getSearchCondition())) {
			weblogVo.setSearchCondition("day");
		}
		if (StringUtils.isBlank(weblogVo.getSearchStartDe())) {
			weblogVo.setSearchStartDe(selStartDe);
		}
		if (StringUtils.isBlank(weblogVo.getSearchEndDe())) {
			weblogVo.setSearchEndDe(selDe);
		}
		
		String label = "날짜";
		if ("day".equals(weblogVo.getSearchCondition())) {
			label = "일";
		} else if ("month".equals(weblogVo.getSearchCondition())) {
			label = "월";
		} else if ("year".equals(weblogVo.getSearchCondition())) {
			label = "년도";
		}
    	
    	List<WeblogVo> vList = weblogSvc.selectSiteVisitrCoPeriodList(weblogVo);
    	List<WeblogVo> sList = weblogSvc.selectSiteSessionVisitrCoPeriodList(weblogVo);
    	List<WeblogVo> pList = weblogSvc.selectSitePageviewPeriodList(weblogVo);
    	List<WeblogVo> mList = weblogSvc.selectMenuPageviewPeriodList(weblogVo);
    	
    	String templateFile = "excel.xls" ;
    	String filename = "기간별접속통계_" + getTimeStamp();
    	
    	
    	Map<String, Object> beans = new HashMap<String, Object>();
        // 데이터를 담는 다.
    	beans.put("label", label);        
    	beans.put("period", weblogVo.getSearchStartDe() + " ~ " + weblogVo.getSearchEndDe());        
    	beans.put("vdata", vList);        
    	beans.put("sdata", sList);        
    	beans.put("pdata", pList);        
    	beans.put("mdata", mList);        
        
    	ExcelCtr.writeToExcel(templateFile, filename, beans, request, response);
        
    }
    
    @RequestMapping(value= "/boffice/stat/selectWebLogStatSummaryExcel.do")
	public void selectWebLogStatSummaryExcel(
			@ModelAttribute("weblogVo") WeblogVo weblogVo
			, HttpServletRequest request,HttpServletResponse response
			, ModelMap model) throws Exception {
			
		if (StringUtils.isBlank(weblogVo.getSearchSiteNo())) {
    		SiteVo sVo = siteSvc.selectSiteDefault();
    		weblogVo.setSearchSiteNo(sVo.getSiteCd());
		}
    	
    	// 어제날짜
		Date selDate = new Date();
		selDate.setTime(new java.util.Date().getTime() + (1000 * 60 * 60 * 24) * -1);
		String selStartDe = new SimpleDateFormat("yyyy-MM-dd").format(selDate);
		String selDe = new SimpleDateFormat("yyyy-MM-dd").format(selDate);
		
		if (weblogVo.getSearchCondition().equals("0") || StringUtils.isBlank(weblogVo.getSearchStartDe())) {
			weblogVo.setSearchStartDe(selStartDe);
		}
		if (weblogVo.getSearchCondition().equals("0") || StringUtils.isBlank(weblogVo.getSearchEndDe())) {
			weblogVo.setSearchEndDe(selDe);
		}
		
		String label = "일";
		String period = weblogVo.getSearchStartDe() + " ~ " + weblogVo.getSearchEndDe();
		
    	if (weblogVo.getSearchCondition().equals("0")) {
    		period = selDe;
    	}
    	List<EgovMap> vList = weblogSvc.selectSiteVisitrCoSummaryList(weblogVo);
    	List<EgovMap> sList = weblogSvc.selectSiteSessionVisitrCoSummaryList(weblogVo);
    	List<EgovMap> pList = weblogSvc.selectSitePageviewSummaryList(weblogVo);
    	List<EgovMap> mList = weblogSvc.selectSiteMenuPageviewSummaryList(weblogVo);
    	
    	String templateFile = "excel.xls" ;
    	String filename = "웹로그현황_요약_" + getTimeStamp();
    	
    	Map<String, Object> beans = new HashMap<String, Object>();
        // 데이터를 담는 다.
    	beans.put("label", label);        
    	beans.put("period", period);        
    	beans.put("vdata", vList);        
    	beans.put("sdata", sList);        
    	beans.put("pdata", pList);        
    	beans.put("mdata", mList);        
        
    	ExcelCtr.writeToExcel(templateFile, filename, beans, request, response);
        
    }
    
    
    /**
     * @param
     * @return Timestamp 값
     * @exception MyException
     * @see
     */
    private static String getTimeStamp() {

		String rtnStr = null;
	
		// 문자열로 변환하기 위한 패턴 설정(년도-월-일 시:분:초:초(자정이후 초))
		String pattern = "yyyyMMddhhmmssSSS";
		
	    SimpleDateFormat sdfCurrent = new SimpleDateFormat(pattern, Locale.KOREA);
	    Timestamp ts = new Timestamp(System.currentTimeMillis());

	    rtnStr = sdfCurrent.format(ts.getTime());

		return rtnStr;
    }

}

