package egovframework.injeinc.boffice.ex.board.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import egovframework.injeinc.boffice.ex.board.vo.BbsContentVo;

public interface BbsContentSvc {
	
	public int registBbsContent(HttpServletRequest request, BbsContentVo bbsContentVo) throws Exception;
	public int registBbsContentNofile(HttpServletRequest request, BbsContentVo bbsContentVo) throws Exception;
	public void modifyBbsContent(HttpServletRequest request, BbsContentVo bbsContentVo) throws Exception;
	public void removeBbsContent(BbsContentVo bbsContentVo) throws Exception;
	public Map<String, Object> retrieveBbsContent(BbsContentVo bbsContentVo) throws Exception;
	public Map<String, Object> retrievePagBbsContent(BbsContentVo bbsContentVo) throws Exception;
	public int retrieveBbsContentCnt(BbsContentVo bbsContentVo) throws Exception;
	@SuppressWarnings("rawtypes")
	public List retrieveListBbsContent(BbsContentVo bbsContentVo) throws Exception;
	public void modifyBbsContentBcDelYn(BbsContentVo bbsContentVo) throws Exception;
	public void modifyBbsContentDelRsnCd(BbsContentVo bbsContentVo) throws Exception;
	public void modifyBbsContentAnswer(BbsContentVo bbsContentVo) throws Exception;
	public void modifyBbsContentCount(BbsContentVo bbsContentVo) throws Exception;
	public void modifyBbsContentClobAutoMake(BbsContentVo bbsContentVo) throws Exception;
	@SuppressWarnings("rawtypes")
	public List retrieveListBbsContentQnA(BbsContentVo bbsContentVo) throws Exception;
	
	/* RSS LIST */
	public List retrieveRssListBbsContent(BbsContentVo bbsContentVo) throws Exception;
	/* RSS PAGE */
	public int retrievePagRssListBbsContent(BbsContentVo bbsContentVo) throws Exception;
	
	public void updateBbsContentMove(BbsContentVo bbsContentVo) throws Exception;
	
	public void insertBbsContentCopy(BbsContentVo bbsContentVo) throws Exception;
	
	public String selectCmsbbsGroup(BbsContentVo bbsContentVo) throws Exception;
	
	public void updateBbsContentUpDown(BbsContentVo bbsContentVo) throws Exception;
	
	public void boardUpdateStatusR(BbsContentVo bbsContentVo) throws Exception;
}