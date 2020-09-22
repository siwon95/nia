package egovframework.injeinc.foffice.ex.twitter.web;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import egovframework.injeinc.foffice.ex.twitter.service.EgovTwitterRecptnService;
import egovframework.injeinc.foffice.ex.twitter.service.EgovTwitterTrnsmitService;
import egovframework.injeinc.foffice.ex.twitter.service.TwitterInfo;
import egovframework.cmm.CmmLogCtr;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
/**
 * 트위터 수신, 송신를 처리하는 Controller Class 구현
 * @author 공통서비스 장동한
 * @since 2010.10.04
 * @version 1.0
 * @see
 * <pre>
 * &lt;&lt; 개정이력(Modification Information) &gt;&gt;
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.10.04  장동한          최초 생성
 *   2011.8.26	정진오			IncludedInfo annotation 추가
 *
 * </pre>
 */

@Controller
public class EgovTwitterController extends CmmLogCtr{

    @Autowired
    private DefaultBeanValidator beanValidator;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /** 트위터 수신(조회) 서비스 */
    @Resource(name = "egovTwitterRecptnService")
    private EgovTwitterRecptnService egovTwitterRecptnService;

    /** 트위터 송신(목록) 서비스 */
    @Resource(name = "egovTwitterTrnsmitService")
    private EgovTwitterTrnsmitService egovTwitterTrnsmitService;

    String sCONSUMER_KEY = "";
    String sCONSUMER_SECRET = "";
    String atoken = "";
    String astoken = "";

    /**
     * 트위터를 수신을 조회 처리한다.
     * @param searchVO 		-트위터 Model
     * @param commandMap 	-Request Variable
     * @param twitterInfo 	-트위터 Model
     * @param model 		-Spring 제공하는 ModelMap
     * @param request 		-HttpServletRequest 객체
     * @param response 		-HttpServletResponse 객체
     * @return String 		-리턴 URL
     * @throws Exception	-Exception Throws
     */
    @RequestMapping(value= "/site/{strDomain}/ex/sns/sns.do")
    public String EgovTwitterRecptnPost2(
    		@ModelAttribute("searchVO") TwitterInfo searchVO,
            @ModelAttribute("twitterInfo") TwitterInfo twitterInfo,
            HttpServletRequest request,
            HttpServletResponse response,
            ModelMap model) throws Exception {

    	//리턴 URL
    	String sLocationUrl = "injeinc/foffice/ex/sns/sns";



	return sLocationUrl;

    }

    @RequestMapping(value= "/site/{strDomain}/ex/sns/snsAjax.do")
    public String EgovTwitterRecptnPostAjax(
    		@ModelAttribute("searchVO") TwitterInfo searchVO,
            @ModelAttribute("twitterInfo") TwitterInfo twitterInfo,
            HttpServletRequest request,
            HttpServletResponse response,
            ModelMap model) throws Exception {
	    	if(model == null){
				return "injeinc/common/code500";
			}
	    	//리턴 URL
	    	String sLocationUrl = "injeinc/foffice/ex/sns/snsAjaxList";

	    	 /** 페이징 정보 설정 */
	        searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
	        searchVO.setPageSize(propertiesService.getInt("pageSize"));

	        /** 네비게이션 정보 설정 */
	        PaginationInfo paginationInfo = new PaginationInfo();
	        paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
	        paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
	        paginationInfo.setPageSize(searchVO.getPageSize());

	        /** 페이징 정보 설정 */
	        searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
	        searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
	        searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

	        int nNextPage = 0;
	        int nPageSize = 6;

	    	HashMap hmParam = new HashMap();

	        try {
	            if(request.getParameter("pageSize") != null){
	            	if( !((String)request.getParameter("pageSize")).equals("") ){
	            		nPageSize = Integer.parseInt(String.valueOf(request.getParameter("pageSize")));
	            	}
	            }

	            if(request.getParameter("nextPage") != null){
	            	if( !((String)request.getParameter("nextPage")).equals("") ){
	            		nNextPage = Integer.parseInt(String.valueOf(request.getParameter("nextPage")));
	            	}
	            }

	          //인증키값 설정
	        	hmParam.put("sCONSUMER_KEY", sCONSUMER_KEY);
	        	hmParam.put("sCONSUMER_SECRET", sCONSUMER_SECRET);
	        	hmParam.put("atoken", atoken);
	        	hmParam.put("astoken", astoken);

	        	//트위터 객체선언
	        	Twitter twitter =  new TwitterFactory().getInstance();
	        	//CONSUMER KEY, CONSUMER SECRET 설정
	        	twitter.setOAuthConsumer(sCONSUMER_KEY, sCONSUMER_SECRET);
	        	//엑서스 토큰 키 설정
	        	AccessToken accessToken = new AccessToken(atoken, astoken);
	        	//엑서스 토큰 설정
	        	twitter.setOAuthAccessToken(accessToken);

	        	//twitter.verifyCredentials();
	        	//twitter.getFriendsTimeline().subList(0, 100);

	        	model.addAttribute("resultList", egovTwitterRecptnService.twitterRecptnList(hmParam, nPageSize, nNextPage));
	        	model.addAttribute("pageSize", nPageSize);
	        	model.addAttribute("nextPage", nNextPage);
	        	//전체 페이지 갯수
	        } catch(Exception e){
	    	    //e.printStackTrace();
	    	    throw e;	// 2011.10.10 보안점검 후속조치
	        }

    		return sLocationUrl;
	}


}
