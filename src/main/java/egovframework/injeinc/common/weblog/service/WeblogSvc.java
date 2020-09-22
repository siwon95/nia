package egovframework.injeinc.common.weblog.service;

import java.util.List;
import java.util.Map;

import egovframework.injeinc.common.weblog.vo.WeblogVo;
import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface WeblogSvc {
	
	/** Site 웹로그 등록 */
	public void insertSiteWebLog(Map<String,String> params) throws Exception;
	
	/*20200910 요약등록 추가 START*/
	/** Site 접속 IP수 요약 등록 */
	public void insertSiteVisitrCoSummary() throws Exception;
	/** Site 방문수 요약 등록 */
	public void insertSiteSessionVisitrCoSummary() throws Exception;
	/** Site 페이지뷰 요약 등록 */
	public void insertSitePageviewSummary() throws Exception;
	/** Site 메뉴별 페이지뷰 요약 등록 */
	public void insertSiteMenuPageviewSummary() throws Exception;
	/*20200910 요약등록 추가 END*/
	
	/** Site 오늘 접속 IP수 */
	public List<EgovMap> selectSiteVisitrCoTodayList(WeblogVo weblogVo) throws Exception;
	
	/** Site 오늘 방문수 */
	public List<EgovMap> selectSiteSessionVisitrCoTodayList(WeblogVo weblogVo) throws Exception;

	/** Site 오늘 페이지뷰 */
	public List<EgovMap> selectSitePageviewTodayList(WeblogVo weblogVo) throws Exception;
	
	/** Site 오늘 메뉴별 페이지뷰 */
	public List<EgovMap> selectSiteMenuPageviewTodayList(WeblogVo weblogVo) throws Exception;
	
	/** Site 요약 접속 IP수 */
	public List<EgovMap> selectSiteVisitrCoSummaryList(WeblogVo weblogVo) throws Exception;
	
	/** Site 요약 방문수 */
	public List<EgovMap> selectSiteSessionVisitrCoSummaryList(WeblogVo weblogVo) throws Exception;

	/** Site 요약 페이지뷰 */
	public List<EgovMap> selectSitePageviewSummaryList(WeblogVo weblogVo) throws Exception;
	
	/** Site 요약 메뉴별 페이지뷰 */
	public List<EgovMap> selectSiteMenuPageviewSummaryList(WeblogVo weblogVo) throws Exception;
	
	/** Site 기간별 접속 IP수 */
	public List<WeblogVo> selectSiteVisitrCoPeriodList(WeblogVo weblogVo) throws Exception;
	
	/** Site 기간별 방문수 */
	public List<WeblogVo> selectSiteSessionVisitrCoPeriodList(WeblogVo weblogVo) throws Exception;
	
	/** Site 기간별 페이지뷰 */
	public List<WeblogVo> selectSitePageviewPeriodList(WeblogVo weblogVo) throws Exception;
	
	/** Site 기간 메뉴별 페이지뷰 */
	public List<WeblogVo> selectMenuPageviewPeriodList(WeblogVo weblogVo) throws Exception;

	
}