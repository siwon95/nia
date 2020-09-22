package egovframework.injeinc.boffice.sy.mgr.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.EgovMessageSource;
import egovframework.cmm.service.AuthoritySvc;
import egovframework.cmm.vo.AuthorityVo;
import egovframework.injeinc.boffice.ex.board.service.BbsContentSvc;
import egovframework.injeinc.boffice.ex.board.vo.BbsContentVo;
import egovframework.injeinc.boffice.sy.mgr.service.MgrMainConfigSvc;
import egovframework.injeinc.boffice.sy.mgr.service.MgrMainSvc;
import egovframework.injeinc.boffice.sy.mgr.vo.MgrMainConfigVo;
import egovframework.injeinc.boffice.sy.mgr.vo.MgrMainVo;
import egovframework.injeinc.common.code.service.CodeSvc;
import egovframework.injeinc.common.util.DateUtil;
import egovframework.injeinc.common.util.EgovStringUtil;

@Controller
public class MgrMainCtr extends CmmLogCtr {
	
	@Resource(name = "AuthoritySvc")
	private AuthoritySvc authoritySvc;
	
	@Autowired
	private MgrMainSvc mgrMainSvc;
	
	@Autowired(required=true)
	private CodeSvc codeSvc;
	
	@Resource(name = "BbsContentSvc")
	private BbsContentSvc bbsContentSvc;
	
	@Resource(name = "MgrMainConfigSvc")
	private MgrMainConfigSvc mgrMainConfigSvc;
	
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/boffice/sy/mgr/MgrMain.do")
	public String mgrMain(HttpServletRequest request, @ModelAttribute("MgrMainVo") MgrMainVo mgrMainVo
				  , @ModelAttribute("MgrMainConfigVo") MgrMainConfigVo mgrMainConfigVo
				  , ModelMap model) throws Exception {
			
			HttpSession ses = request.getSession();
			
			if(mgrMainVo.getScRegDtSt() == null){
				String nowDate = DateUtil.getCurrentDate();
				String tenAgoDate = DateUtil.add(nowDate, -10);
				mgrMainVo.setScRegDtSt(tenAgoDate);
				mgrMainVo.setScRegDtEd(nowDate);
			}
			
			//시설홍보 리스트
			List cmmExpandList =  mgrMainSvc.retrieveMgrMainCommExpandList(mgrMainVo);
			//행사홍보 리스트
			List commEventList =  mgrMainSvc.retrieveMgrMainCommEventList(mgrMainVo);
			
			//통합게시판 건수
			int boardCount = mgrMainSvc.retrieveMgrMainBoardCount(mgrMainVo);
			//통합예약 홍보 신청건수
			int commCount = mgrMainSvc.retrieveMgrMainCommCount(mgrMainVo);
			if(model != null){
				model.addAttribute("mgrMainVo", mgrMainVo);
				model.addAttribute("cmmExpandList", cmmExpandList);
				model.addAttribute("commEventList", commEventList);
				model.addAttribute("boardCount", boardCount);
				model.addAttribute("commCount", commCount);
			}
			//관리자 공지사항 리스트
			BbsContentVo bbsContentVo = new BbsContentVo();
			bbsContentVo.setFirstIndex(0);
			bbsContentVo.setLastIndex(5);
			bbsContentVo.setCbIdx(280);
			bbsContentVo.setSearchCbIdx("280");
			bbsContentVo.setSearchDelYn("N");
			Map<String, Object> map = bbsContentSvc.retrievePagBbsContent(bbsContentVo);
			if(model != null){
				model.addAttribute("noticeList", map.get("resultList"));
			}
			//묻고답히기 리스트
			String SesUserDeptNm = (String) WebUtils.getSessionAttribute(request, "SesUserDeptNm");
			BbsContentVo bbsContentQnAVo = new BbsContentVo();
			
			String SesUserPermCd = (String) WebUtils.getSessionAttribute(request, "SesUserPermCd");
			if(!SesUserPermCd.equals("05010000")) {
				bbsContentQnAVo.setSearchAnsWriter(SesUserDeptNm);
			}
			if(model != null){
				model.addAttribute("qnaList", bbsContentSvc.retrieveListBbsContentQnA(bbsContentQnAVo));
			}
			//통합게시판 리스트
			BbsContentVo boardListVo = new BbsContentVo();
			String SesMgrIdx = (String) WebUtils.getSessionAttribute(request, "SesMgrIdx");
			
			AuthorityVo authorityVo = new AuthorityVo();
			authorityVo.setCbUprCd("");
			authorityVo.setMgrIdx(SesMgrIdx);
			
			ArrayList<String> searchCbIdxArr = null;
			
			if(SesUserPermCd.equals("05010000")) {
				searchCbIdxArr = (ArrayList<String>)authoritySvc.retrieveListAuthorityCbIdxMaster(authorityVo);
			}else{
				searchCbIdxArr = (ArrayList<String>)authoritySvc.retrieveListAuthorityCbIdx(authorityVo);
			}
			
			boardListVo.setSearchCbIdxArr(searchCbIdxArr);

			boardListVo.setFirstIndex(0);
			boardListVo.setLastIndex(5);
			boardListVo.setSearchDelYn("N");
			Map<String, Object> boardMap = bbsContentSvc.retrievePagBbsContent(boardListVo);
			if(model != null){
				model.addAttribute("boardList", boardMap.get("resultList"));
			}
			
			mgrMainConfigVo.setMgrId(EgovStringUtil.isNullToString(ses.getAttribute("SesUserId")));
			model.addAttribute("MgrMainConfigVo", mgrMainConfigSvc.retrieveMgrMainConfig(mgrMainConfigVo));
		
		return "injeinc/boffice/sy/mgr/mgr_main";
	}
}