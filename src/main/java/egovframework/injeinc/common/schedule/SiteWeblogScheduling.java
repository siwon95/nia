package egovframework.injeinc.common.schedule;

import java.text.SimpleDateFormat;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.cmm.CmmLogCtr;
import egovframework.injeinc.common.weblog.dao.WeblogDao;

@Service("siteWeblogScheduling")
public class SiteWeblogScheduling extends CmmLogCtr {
	
	@Resource(name = "WeblogDao")
    private WeblogDao weblogDao;

	/** Site 웹로그 요약 작업 */
	public void siteWebLogSummaryJob() throws Exception {
		String currentDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new java.util.Date());
		debugLog("========== Site 웹로그 요약 작업 출력(" + currentDate + ") ========== ");
		
		
		// 접속 IP수 요약 등록
		debugLog("========== 1. 접속 IP수 요약 ========== ");
		weblogDao.insertSiteVisitrCoSummary();
		
		// 방문수 요약 등록
		debugLog("========== 2. 방문수 요약 ========== ");
		weblogDao.insertSiteSessionVisitrCoSummary();
		
		// 페이지뷰 요약 등록
		debugLog("========== 3. 페이지뷰 요약 ========== ");		
		weblogDao.insertSitePageviewSummary();
		
		// 메뉴별 페이지뷰 요약 등록
		debugLog("========== 4. 메뉴별 페이지뷰 요약 ========== ");
		weblogDao.insertSiteMenuPageviewSummary();
		
		// 현재일 포함 3일치 로그만 남기고 모두 삭제
		debugLog("========== 5. 웹로그 삭제 ========== ");
		weblogDao.deleteSiteWebLog();
		
	}
	
}
