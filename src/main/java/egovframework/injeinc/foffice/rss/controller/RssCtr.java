package egovframework.injeinc.foffice.rss.controller;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.injeinc.boffice.ex.board.service.BbsContentSvc;
import egovframework.injeinc.boffice.ex.board.vo.BbsContentVo;
import egovframework.injeinc.boffice.sy.board.service.BoardSvc;
import egovframework.injeinc.boffice.sy.board.vo.BoardVo;
import egovframework.injeinc.boffice.sy.board.vo.CmsBbsVo;
import egovframework.injeinc.boffice.sy.board.vo.ConfigPropertyVo;
import egovframework.injeinc.common.util.EgovStringUtil;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


@Controller
public class RssCtr {

	@Resource(name = "BbsContentSvc")
	private BbsContentSvc bbsContentSvc;
	
	@Resource(name = "BoardSvc")
	private BoardSvc boardSvc;
	
	/** RSS 목록 페이지*/
	@RequestMapping(value = "/rss/{strDomain}/board/{cbIdx}.do")
	public String rssboardList(@PathVariable int cbIdx, ModelMap model,HttpServletRequest request, @ModelAttribute CmsBbsVo cmsBbsVo) throws Exception{

		BbsContentVo bbsContentVo = new BbsContentVo(); 
		bbsContentVo.setCbIdx(cbIdx); 
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(bbsContentVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(20);
		paginationInfo.setPageSize(bbsContentVo.getPageSize());
		
		bbsContentVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		bbsContentVo.setLastIndex(paginationInfo.getLastRecordIndex());
		bbsContentVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		int totCnt = bbsContentSvc.retrievePagRssListBbsContent(bbsContentVo); 
		paginationInfo.setTotalRecordCount(totCnt);
		
	
		List rssBoardList = bbsContentSvc.retrieveRssListBbsContent(bbsContentVo); 			
		model.addAttribute("rssBoardList", rssBoardList); 
		model.addAttribute("strDomain", request.getParameter("siteCd"));
		return "injeinc/rss/{strDomain}/board/board_list"; 
	}
	
	
	/** Rss 사용자 페이지 */
	@RequestMapping(value= "/rss/{strDomain}/board/Board_test.do")
	public String rssUrlCheck(@ModelAttribute("BoardVo") BoardVo boardVo, ModelMap model) throws Exception{		
		
		List<BoardVo> rssUrlCheck = boardSvc.retrieveRssUrlCheck(boardVo);	
			
		model.addAttribute("rssUrlCheck", rssUrlCheck);
	return "injeinc/rss/{strDomain}/board/board_test";
	}
}
