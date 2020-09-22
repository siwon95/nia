package egovframework.injeinc.common.weblog.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.injeinc.common.weblog.dao.WeblogDao;
import egovframework.injeinc.common.weblog.service.WeblogSvc;
import egovframework.injeinc.common.weblog.vo.WeblogVo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Service("WeblogSvc")
public class WeblogSvcImpl extends EgovAbstractServiceImpl implements WeblogSvc {

	@Resource(name = "WeblogDao")
    private WeblogDao weblogDao;
	
	/** Site 웹로그 등록 */
	public void insertSiteWebLog(Map<String,String> params) throws Exception {
		weblogDao.insertSiteWebLog(params);
	}
	
	/*20200910 요약등록 추가 START*/
	/** Site 접속 IP수 요약 등록 */
	public void insertSiteVisitrCoSummary() throws Exception {
		weblogDao.insertSiteVisitrCoSummary();
	}
	
	/** Site 방문수 요약 등록 */
	public void insertSiteSessionVisitrCoSummary() throws Exception {
		weblogDao.insertSiteSessionVisitrCoSummary();
	}
	
	/** Site 페이지뷰 요약 등록 */
	public void insertSitePageviewSummary() throws Exception {
		weblogDao.insertSitePageviewSummary();
	}
	
	/** Site 메뉴별 페이지뷰 요약 등록 */
	public void insertSiteMenuPageviewSummary() throws Exception {
		weblogDao.insertSiteMenuPageviewSummary();
	}
	/*20200910 요약등록 추가 END*/
	
	/** Site 오늘 접속 IP수 */
	public List<EgovMap> selectSiteVisitrCoTodayList(WeblogVo weblogVo) throws Exception {
		return (List<EgovMap>) weblogDao.selectSiteVisitrCoTodayList(weblogVo);
	}
	
	/** Site 오늘 방문수 */
	public List<EgovMap> selectSiteSessionVisitrCoTodayList(WeblogVo weblogVo) throws Exception {
		return (List<EgovMap>) weblogDao.selectSiteSessionVisitrCoTodayList(weblogVo);
	}
	
	/** Site 오늘 페이지뷰 */
	public List<EgovMap> selectSitePageviewTodayList(WeblogVo weblogVo) throws Exception {
		return (List<EgovMap>) weblogDao.selectSitePageviewTodayList(weblogVo);
	}
	
	/** Site 오늘 메뉴별 페이지뷰 */
	public List<EgovMap> selectSiteMenuPageviewTodayList(WeblogVo weblogVo) throws Exception {
		return (List<EgovMap>) weblogDao.selectSiteMenuPageviewTodayList(weblogVo);
	}
	
	/** Site 요약 접속 IP수 */
	public List<EgovMap> selectSiteVisitrCoSummaryList(WeblogVo weblogVo) throws Exception {
		return (List<EgovMap>) weblogDao.selectSiteVisitrCoSummaryList(weblogVo);
	}
	
	/** Site 요약 방문수 */
	public List<EgovMap> selectSiteSessionVisitrCoSummaryList(WeblogVo weblogVo) throws Exception {
		return (List<EgovMap>) weblogDao.selectSiteSessionVisitrCoSummaryList(weblogVo);
	}
	
	/** Site 요약 페이지뷰 */
	public List<EgovMap> selectSitePageviewSummaryList(WeblogVo weblogVo) throws Exception {
		return (List<EgovMap>) weblogDao.selectSitePageviewSummaryList(weblogVo);
	}
	
	/** Site 요약 메뉴별 페이지뷰 */
	public List<EgovMap> selectSiteMenuPageviewSummaryList(WeblogVo weblogVo) throws Exception {
		return (List<EgovMap>) weblogDao.selectSiteMenuPageviewSummaryList(weblogVo);
	}
	
	/** Site 기간별 접속 IP수 */
	public List<WeblogVo> selectSiteVisitrCoPeriodList(WeblogVo weblogVo) throws Exception {
		return (List<WeblogVo>) weblogDao.selectSiteVisitrCoPeriodList(weblogVo);
	}	
	
	/** Site 기간별 방문수 */
	public List<WeblogVo> selectSiteSessionVisitrCoPeriodList(WeblogVo weblogVo) throws Exception {
		return (List<WeblogVo>) weblogDao.selectSiteSessionVisitrCoPeriodList(weblogVo);
	}
	
	/** Site 기간별 페이지뷰 */
	public List<WeblogVo> selectSitePageviewPeriodList(WeblogVo weblogVo) throws Exception {
		return (List<WeblogVo>) weblogDao.selectSitePageviewPeriodList(weblogVo);
	}
	
	/** Site 기간 메뉴별 페이지뷰 */
	public List<WeblogVo> selectMenuPageviewPeriodList(WeblogVo weblogVo) throws Exception {
		return (List<WeblogVo>) weblogDao.selectMenuPageviewPeriodList(weblogVo);
	}
	
}
