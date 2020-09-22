package egovframework.injeinc.common.weblog.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.cmm.service.impl.EgovComAbstractDAO;
import egovframework.injeinc.common.weblog.vo.WeblogVo;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Repository("WeblogDao")
public class WeblogDao extends EgovComAbstractDAO {
	
	/** Site 웹로그 등록 */
	public void insertSiteWebLog(Map<String,String> params) throws Exception {
		insert("WeblogDao.insertWebLog", params);
	}

	/** Site 웹로그 삭제 */
	public void deleteSiteWebLog() throws Exception {
		delete("WeblogDao.deleteWebLog");
	}

	/** Site 접속 IP수 요약 등록 */
	public void insertSiteVisitrCoSummary() throws Exception {
		insert("WeblogDao.insertVisitrCoSummary");
	}
	
	/** Site 방문수 요약 등록 */
	public void insertSiteSessionVisitrCoSummary() throws Exception {
		insert("WeblogDao.insertSessionVisitrCoSummary");
	}
	
	/** Site 페이지뷰 요약 등록 */
	public void insertSitePageviewSummary() throws Exception {
		insert("WeblogDao.insertPageviewSummary");
	}
	
	/** Site 메뉴별 페이지뷰 요약 등록 */
	public void insertSiteMenuPageviewSummary() throws Exception {
		insert("WeblogDao.insertMenuPageviewSummary");
	}
	
	/** Site 오늘 접속 IP수 */
	@SuppressWarnings("unchecked")
	public List<EgovMap> selectSiteVisitrCoTodayList(WeblogVo weblogVo) throws Exception {
		return selectList("WeblogDao.selectVisitrCoTodayList", weblogVo);
	}
	
	/** Site 오늘 방문수 */
	@SuppressWarnings("unchecked")
	public List<EgovMap> selectSiteSessionVisitrCoTodayList(WeblogVo weblogVo) throws Exception {
		return selectList("WeblogDao.selectSessionVisitrCoTodayList", weblogVo);
	}
	
	/** Site 오늘 페이지뷰 */
	@SuppressWarnings("unchecked")
	public List<EgovMap> selectSitePageviewTodayList(WeblogVo weblogVo) throws Exception {
		return selectList("WeblogDao.selectPageviewTodayList", weblogVo);
	}
	
	/** Site 오늘 메뉴별 페이지뷰 */
	@SuppressWarnings("unchecked")
	public List<EgovMap> selectSiteMenuPageviewTodayList(WeblogVo weblogVo) throws Exception {
		return selectList("WeblogDao.selectMenuPageviewTodayList", weblogVo);
	}	
	
	/** Site 요약 접속 IP수 */
	@SuppressWarnings("unchecked")
	public List<EgovMap> selectSiteVisitrCoSummaryList(WeblogVo weblogVo) throws Exception {
		return selectList("WeblogDao.selectVisitrCoSummaryList", weblogVo);
	}	

	/** Site 요약 방문수 */
	@SuppressWarnings("unchecked")
	public List<EgovMap> selectSiteSessionVisitrCoSummaryList(WeblogVo weblogVo) throws Exception {
		return selectList("WeblogDao.selectSessionVisitrCoSummaryList", weblogVo);
	}	
	
	/** Site 요약 페이지뷰 */
	@SuppressWarnings("unchecked")
	public List<EgovMap> selectSitePageviewSummaryList(WeblogVo weblogVo) throws Exception {
		return selectList("WeblogDao.selectPageviewSummaryList", weblogVo);
	}
	
	@SuppressWarnings("unchecked")
	public List<EgovMap> selectSiteMenuPageviewSummaryList(WeblogVo weblogVo) throws Exception {
		return selectList("WeblogDao.selectMenuPageviewSummaryList", weblogVo);
	}	
	
	/** Site 기간별 접속 IP수 */
	@SuppressWarnings("unchecked")
	public List<WeblogVo> selectSiteVisitrCoPeriodList(WeblogVo weblogVo) throws Exception {
		return selectList("WeblogDao.selectVisitrCoPeriodList", weblogVo);
	}	
	
	/** Site 기간별 방문수 */
	@SuppressWarnings("unchecked")
	public List<WeblogVo> selectSiteSessionVisitrCoPeriodList(WeblogVo weblogVo) throws Exception {
		return selectList("WeblogDao.selectSessionVisitrCoPeriodList", weblogVo);
	}	
	
	/** Site 기간별 페이지뷰 */
	@SuppressWarnings("unchecked")
	public List<WeblogVo> selectSitePageviewPeriodList(WeblogVo weblogVo) throws Exception {
		return selectList("WeblogDao.selectPageviewPeriodList", weblogVo);
	}
	
	/** Site 기간 메뉴별 페이지뷰 */
	@SuppressWarnings("unchecked")
	public List<WeblogVo> selectMenuPageviewPeriodList(WeblogVo weblogVo) throws Exception {
		return selectList("WeblogDao.selectMenuPageviewPeriodList", weblogVo);
	}	
	
}
