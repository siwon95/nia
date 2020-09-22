package egovframework.injeinc.foffice.ex.board.controller;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.util.WebUtils;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.Message;
import egovframework.cmm.service.AuthoritySvc;
import egovframework.injeinc.foffice.ex.board.service.BbsContentFSvc;
import egovframework.injeinc.boffice.ex.board.service.ContentFileSvc;
import egovframework.injeinc.foffice.ex.board.vo.BbsContentFVo;
import egovframework.injeinc.boffice.ex.board.vo.ContentFileVo;
import egovframework.injeinc.boffice.sy.board.service.BoardSvc;
import egovframework.injeinc.boffice.sy.board.vo.BoardVo;
import egovframework.injeinc.boffice.sy.board.vo.ConfigPropertyVo;
import egovframework.injeinc.common.util.DateUtil;
import egovframework.injeinc.common.util.EgovStringUtil;
import egovframework.injeinc.common.util.WebUtil;

@Controller
public class BbsContentFCtr extends CmmLogCtr {
	
	@Resource(name = "AuthoritySvc")
	private AuthoritySvc authoritySvc;
	
	@Resource(name = "BbsContentFSvc")
	private BbsContentFSvc bbsContentFSvc;
	
	@Resource(name = "BoardSvc")
	private BoardSvc boardSvc;
	
	@Resource(name = "ContentFileSvc")
	private ContentFileSvc contentFileSvc;
	
	@Autowired(required=true)
	private MappingJackson2JsonView jsonView;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/site/{strDomain}/ex/board/View.do")
	public String BbsContentFView(HttpServletRequest request, @PathVariable("strDomain") String strDomain, @ModelAttribute("BoardVo") BoardVo boardVo, @ModelAttribute("ConfigPropertyVo") ConfigPropertyVo configPropertyVo, @ModelAttribute("BbsContentFVo") BbsContentFVo bbsContentFVo, @ModelAttribute("ContentFileVo") ContentFileVo contentFileVo, ModelMap model) throws Exception {

		boardVo = boardSvc.retrieveBoard(boardVo);
		
		if(boardVo != null) {
			model.addAttribute("BoardVo", boardVo);
		}else{
			String SVC_MSGE ="잘못된 접근입니다.";
			return alert(request.getContextPath()+"/site/"+strDomain+"/main.do", SVC_MSGE, model);
		}
		
		Map<String, Object> result = bbsContentFSvc.retrieveBbsContentF(bbsContentFVo);
		
		if(result != null) {
			result.put("pageIndex", bbsContentFVo.getPageIndex());
			result.put("searchCondition", bbsContentFVo.getSearchCondition());
			result.put("searchKeyword", bbsContentFVo.getSearchKeyword());
			result.put("searchCateCont", bbsContentFVo.getSearchCateCont());
			model.addAttribute("BbsContentFVo", result);
		}else{
			String SVC_MSGE ="잘못된 접근입니다.";
			return alert(request.getContextPath()+"/site/"+strDomain+"/ex/board/List.do?cbIdx="+boardVo.getCbIdx(), SVC_MSGE, model);
		}

		String ss_level = EgovStringUtil.isNullToString(WebUtils.getSessionAttribute(request, "ss_level"));
		String ss_dupkey = EgovStringUtil.isNullToString(WebUtils.getSessionAttribute(request, "ss_dupkey"));
		String dupcode = EgovStringUtil.isNullToString(result.get("dupcode"));
		String openYnCont = EgovStringUtil.isNullToString(result.get("openYnCont"));
		String mwAdOpenYn = EgovStringUtil.isNullToString(result.get("mwAdOpenYn"));
		
		//읽기 권한 체크
		String readGbn = boardVo.getReadGbn(); //모두:16010100, 회원:16010300, 회원(본인게시물):16010200, 본인인증:16010400, 관리자:16010500
		boolean isReadGbn = false;
		if((openYnCont.equals("21000100") || mwAdOpenYn.equals("Y")) && (ss_dupkey.equals("") || !ss_dupkey.equals(dupcode))) {
			//비공개고 본인 아닌사람이 접근할때
		}else if(readGbn.equals("16010200") && ss_level.equals("7") && !ss_dupkey.equals("") && ss_dupkey.equals(dupcode)) {
			isReadGbn = true;
		}else if(readGbn.equals("16010300") && ss_level.equals("7")) {
			isReadGbn = true;
		}else if(readGbn.equals("16010400") && !ss_dupkey.equals("")) {
			isReadGbn = true;
		}else if(readGbn.equals("16010100")) {
			isReadGbn = true;
		}
		
		if(!isReadGbn) {
			String SVC_MSGE ="읽기 권한이 없습니다.";
			return alert(request.getContextPath()+"/site/"+strDomain+"/ex/board/List.do?cbIdx="+boardVo.getCbIdx(), SVC_MSGE, model);
		}
		
		String bbsTempletGbn = boardVo.getBbsTempletGbn();
		String bbsTempletName = "";
		if(bbsTempletGbn.equals("16050100")) {	// 일반게시판
			bbsTempletName = "basic";
		}else if(bbsTempletGbn.equals("16050200")) {	// 포토(리스트)
			bbsContentFVo.setPageUnit(5);
			bbsTempletName = "basic";
		}else if(bbsTempletGbn.equals("16050300")) {	// 포토(앨범)
			bbsContentFVo.setPageUnit(8);
			bbsTempletName = "album";
		}else if(bbsTempletGbn.equals("16050400")) {	// 동영상
			bbsContentFVo.setPageUnit(8);
			bbsTempletName = "basic";
		}else if(bbsTempletGbn.equals("16050500")) {	// 묻고답하기
			bbsTempletName = "basic";
		}else if(bbsTempletGbn.equals("16050600")) {	// 목록게시판
			bbsTempletName = "basic";
		}else if(bbsTempletGbn.equals("16050700")) {	// 민원게시판
			bbsTempletName = "basic";
		}else{
			String SVC_MSGE ="잘못된 접근입니다.";
			return alert(request.getContextPath()+"/site/"+strDomain+"/main.do", SVC_MSGE, model);
		}
		model.addAttribute("bbsTempletName", bbsTempletName);
		
		bbsContentFSvc.modifyBbsContentFCount(bbsContentFVo);
		int countCont = Integer.parseInt(String.valueOf(result.get("countCont")));
		result.put("countCont", countCont+1);
		
		List propertylist = boardSvc.retrieveListConfigProperty(configPropertyVo);
		model.addAttribute("propertylist", propertylist);
		
		List fileList = contentFileSvc.retrieveListContentFile(contentFileVo);
		model.addAttribute("fileList", fileList);
		
		model.addAttribute("strDomain", strDomain);
		
		//삭제 권한 체크
		boolean isDeleteGbn = false;
		String deleteGbn = boardVo.getDelGbn(); //모두:16010100, 회원:16010300, 회원(본인게시물):16010200, 본인인증:16010400, 관리자:16010500
		if(deleteGbn.equals("16010500")) {
			
		}else if(deleteGbn.equals("16010200") && ss_level.equals("7") && !ss_dupkey.equals("") && ss_dupkey.equals(dupcode)) {
			isDeleteGbn = true;
		}else if(deleteGbn.equals("16010300") && ss_level.equals("7") && !ss_dupkey.equals("") && ss_dupkey.equals(dupcode)) {
			isDeleteGbn = true;
		}else if(deleteGbn.equals("16010400") && !ss_dupkey.equals("") && ss_dupkey.equals(dupcode)) {
			isDeleteGbn = true;
		}else if(deleteGbn.equals("16010100") && !ss_dupkey.equals("") && ss_dupkey.equals(dupcode)) {
			isDeleteGbn = true;
		}
		model.addAttribute("isDeleteGbn", isDeleteGbn);
		
		//수정 권한 체크
		boolean isModifyGbn = false;
		String modifyGbn = boardVo.getModGbn(); //모두:16010100, 회원:16010300, 회원(본인게시물):16010200, 본인인증:16010400, 관리자:16010500
		if(modifyGbn.equals("16010500")) {
			
		}else if(modifyGbn.equals("16010200") && ss_level.equals("7") && !ss_dupkey.equals("") && ss_dupkey.equals(dupcode)) {
			isModifyGbn = true;
		}else if(modifyGbn.equals("16010300") && ss_level.equals("7") && !ss_dupkey.equals("") && ss_dupkey.equals(dupcode)) {
			isModifyGbn = true;
		}else if(modifyGbn.equals("16010400") && !ss_dupkey.equals("") && ss_dupkey.equals(dupcode)) {
			isModifyGbn = true;
		}else if(modifyGbn.equals("16010100") && !ss_dupkey.equals("") && ss_dupkey.equals(dupcode)) {
			isModifyGbn = true;
		}
		model.addAttribute("isModifyGbn", isModifyGbn);
		
		return "injeinc/foffice/ex/board/view";
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/site/{strDomain}/foffice/board/Form.do")
	public String BbsContentFForm(HttpServletRequest request, @PathVariable("strDomain") String strDomain, @ModelAttribute("BoardVo") BoardVo boardVo, @ModelAttribute("ConfigPropertyVo") ConfigPropertyVo configPropertyVo, @ModelAttribute("BbsContentFVo") BbsContentFVo bbsContentFVo, @ModelAttribute("ContentFileVo") ContentFileVo contentFileVo, @RequestParam Map requestMap, ModelMap model) throws Exception {

		boardVo = boardSvc.retrieveBoard(boardVo);
		
		if(boardVo != null) {
			model.addAttribute("BoardVo", boardVo);
		}else{
			String SVC_MSGE ="잘못된 접근입니다.";
			return alert(request.getContextPath()+"/site/"+strDomain+"/main.do", SVC_MSGE, model);
		}

		String ss_level = EgovStringUtil.isNullToString(WebUtils.getSessionAttribute(request, "ss_level"));
		String ss_dupkey = EgovStringUtil.isNullToString(WebUtils.getSessionAttribute(request, "ss_dupkey"));
		
		Map<String, Object> result = bbsContentFSvc.retrieveBbsContentF(bbsContentFVo);
		
		if(result != null) {
			String dupcode = EgovStringUtil.isNullToString(result.get("dupcode"));
			
			//수정 권한 체크
			String modifyGbn = boardVo.getModGbn(); //모두:16010100, 회원:16010300, 회원(본인게시물):16010200, 본인인증:16010400, 관리자:16010500
			boolean isModifyGbn = false;
			if(modifyGbn.equals("16010200") && ss_level.equals("7") && !ss_dupkey.equals("") && ss_dupkey.equals(dupcode)) {
				isModifyGbn = true;
			}else if(modifyGbn.equals("16010300") && ss_level.equals("7") && !ss_dupkey.equals("") && ss_dupkey.equals(dupcode)) {
				isModifyGbn = true;
			}else if(modifyGbn.equals("16010400") && !ss_dupkey.equals("") && ss_dupkey.equals(dupcode)) {
				isModifyGbn = true;
			}else if(modifyGbn.equals("16010100") && !ss_dupkey.equals("") && ss_dupkey.equals(dupcode)) {
				isModifyGbn = true;
			}
			
			if(!isModifyGbn) {
				String SVC_MSGE ="수정 권한이 없습니다.";
				return alert(request.getContextPath()+"/site/"+strDomain+"/ex/board/List.do?cbIdx="+boardVo.getCbIdx(), SVC_MSGE, model);
			}
			
			result.put("actionkey", "modify");
			result.put("pageIndex", bbsContentFVo.getPageIndex());
			result.put("pageUnit", bbsContentFVo.getPageUnit());
			result.put("searchCondition", bbsContentFVo.getSearchCondition());
			result.put("searchKeyword", bbsContentFVo.getSearchKeyword());
			result.put("searchCateCont", bbsContentFVo.getSearchCateCont());
			
			model.addAttribute("BbsContentFVo", result);
			
			List fileList = contentFileSvc.retrieveListContentFile(contentFileVo);
			model.addAttribute("fileList", fileList);
			
		}else{
			
			//쓰기 권한 체크
			String writeGbn = boardVo.getWriteGbn(); //모두:16010100, 회원:16010300, 회원(본인게시물):16010200, 본인인증:16010400, 관리자:16010500
			boolean isWriteGbn = false;
			if(writeGbn.equals("16010200") && ss_level.equals("7")) {
				isWriteGbn = true;
			}else if(writeGbn.equals("16010300") && ss_level.equals("7")) {
				isWriteGbn = true;
			}else if(writeGbn.equals("16010400") && !ss_dupkey.equals("")) {
				isWriteGbn = true;
			}else if(writeGbn.equals("16010100") && !ss_dupkey.equals("")) {
				isWriteGbn = true;
			}
			
			if(!isWriteGbn) {
				String SVC_MSGE ="쓰기 권한이 없습니다.";
				return alert(request.getContextPath()+"/site/"+strDomain+"/ex/board/List.do?cbIdx="+boardVo.getCbIdx(), SVC_MSGE, model);
			}
			
			requestMap.put("actionkey", "regist");
			model.addAttribute("BbsContentFVo", requestMap);
		}
		
		List propertylist = boardSvc.retrieveListConfigProperty(configPropertyVo);
		model.addAttribute("propertylist", propertylist);
		
		StringBuffer addParam = new StringBuffer();
		addParam.append("?pageIndex=").append(bbsContentFVo.getPageIndex());
		addParam.append("&searchCondition=").append(bbsContentFVo.getSearchCondition());
		addParam.append("&searchKeyword=").append(URLEncoder.encode(bbsContentFVo.getSearchKeyword(), "UTF-8"));
		addParam.append("&searchCateCont=").append(bbsContentFVo.getSearchCateCont());
		addParam.append("&cbIdx=").append(bbsContentFVo.getCbIdx());
		model.addAttribute("addParam", addParam.toString());
		
		model.addAttribute("strDomain", strDomain);
		
		return "injeinc/foffice/ex/board/form";
	}
	
	@RequestMapping("/site/{strDomain}/foffice/board/RegProcess.do")
	public String BbsContentFReg(HttpServletRequest request, @PathVariable("strDomain") String strDomain, @ModelAttribute("BoardVo") BoardVo boardVo, @ModelAttribute("BbsContentFVo") BbsContentFVo bbsContentFVo, ModelMap model) throws Exception {

		boardVo = boardSvc.retrieveBoard(boardVo);
		
		if(boardVo == null) {
			String SVC_MSGE ="잘못된 접근입니다.";
			return alert("/site/"+strDomain+"/main.do", SVC_MSGE, model);
		}
		
		String ss_level = EgovStringUtil.isNullToString(WebUtils.getSessionAttribute(request, "ss_level"));
		String ss_dupkey = EgovStringUtil.isNullToString(WebUtils.getSessionAttribute(request, "ss_dupkey"));
		
		//쓰기 권한 체크
		String writeGbn = boardVo.getWriteGbn(); //모두:16010100, 회원:16010300, 회원(본인게시물):16010200, 본인인증:16010400, 관리자:16010500
		boolean isWriteGbn = false;
		if(writeGbn.equals("16010200") && ss_level.equals("7")) {
			isWriteGbn = true;
		}else if(writeGbn.equals("16010300") && ss_level.equals("7")) {
			isWriteGbn = true;
		}else if(writeGbn.equals("16010400") && !ss_dupkey.equals("")) {
			isWriteGbn = true;
		}else if(writeGbn.equals("16010100") && !ss_dupkey.equals("")) {
			isWriteGbn = true;
		}
		
		if(!isWriteGbn) {
			String SVC_MSGE ="쓰기 권한이 없습니다.";
			return alert("", SVC_MSGE, model);
		}
		
		String ss_id = (String) WebUtils.getSessionAttribute(request, "ss_id");
		bbsContentFVo.setRegId(ss_id);
		bbsContentFVo.setRegIp(request.getRemoteAddr());
		bbsContentFVo.setDupcode(ss_dupkey);
		
		if(boardVo.getBbsTempletGbn().equals("16050500") || boardVo.getBbsTempletGbn().equals("16050700")) {
			if(EgovStringUtil.isEmpty(bbsContentFVo.getAnsYnCont())) {
				bbsContentFVo.setAnsYnCont("22000100"); //묻고답하기 게시판이거나 민원게시판일 경우 글을 쓰면 자동으로 답변요청 상태로 저장됨
			}
		}
		
		if(boardVo.getBbsApprYn().equals("2")) {
			bbsContentFVo.setApprYn("N");			//게시글 승인 기능이 있는 게시판은 기본값으로 승인요청 상태로 저장됨
		}
		
		int bcIdx = bbsContentFSvc.registBbsContentF(bbsContentFVo);
		
		if(bcIdx > 0) {
			ContentFileVo contentFileVo = new ContentFileVo();
			contentFileVo.setBcIdx(bcIdx);
			contentFileVo.setCbIdx(bbsContentFVo.getCbIdx());
			contentFileVo.setRegId(ss_id);
			contentFileSvc.registContentFile(request, contentFileVo);
		}
		
		StringBuffer addParam = new StringBuffer();
		addParam.append("?pageIndex=").append(bbsContentFVo.getPageIndex());
		addParam.append("&searchCondition=").append(bbsContentFVo.getSearchCondition());
		addParam.append("&searchKeyword=").append(bbsContentFVo.getSearchKeyword());
		addParam.append("&searchCateCont=").append(bbsContentFVo.getSearchCateCont());
		addParam.append("&cbIdx=").append(bbsContentFVo.getCbIdx());
		
		String SVC_MSGE = Message.getMessage("100.message");
		return alert("/site/"+strDomain+"/ex/board/List.do"+addParam.toString(), SVC_MSGE, model); 
	}
	
	@RequestMapping("/site/{strDomain}/foffice/board/ModProcess.do")
	public String BbsContentFMod(HttpServletRequest request, @PathVariable("strDomain") String strDomain, @ModelAttribute("BoardVo") BoardVo boardVo, @ModelAttribute("BbsContentFVo") BbsContentFVo bbsContentFVo, ModelMap model) throws Exception {

		boardVo = boardSvc.retrieveBoard(boardVo);
		
		if(boardVo == null) {
			String SVC_MSGE ="잘못된 접근입니다.";
			return alert("/site/"+strDomain+"/main.do", SVC_MSGE, model);
		}
		
		Map<String, Object> result = bbsContentFSvc.retrieveBbsContentF(bbsContentFVo);
		
		if(result == null) {
			String SVC_MSGE ="잘못된 접근입니다.";
			return alert(request.getContextPath()+"/site/"+strDomain+"/ex/board/List.do?cbIdx="+boardVo.getCbIdx(), SVC_MSGE, model);
		}
		
		String ss_level = EgovStringUtil.isNullToString(WebUtils.getSessionAttribute(request, "ss_level"));
		String ss_dupkey = EgovStringUtil.isNullToString(WebUtils.getSessionAttribute(request, "ss_dupkey"));
		String dupcode = EgovStringUtil.isNullToString(result.get("dupcode"));
		
		//수정 권한 체크
		String modifyGbn = boardVo.getModGbn(); //모두:16010100, 회원:16010300, 회원(본인게시물):16010200, 본인인증:16010400, 관리자:16010500
		boolean isModifyGbn = false;
		if(modifyGbn.equals("16010200") && ss_level.equals("7") && !ss_dupkey.equals("") && ss_dupkey.equals(dupcode)) {
			isModifyGbn = true;
		}else if(modifyGbn.equals("16010300") && ss_level.equals("7") && !ss_dupkey.equals("") && ss_dupkey.equals(dupcode)) {
			isModifyGbn = true;
		}else if(modifyGbn.equals("16010400") && !ss_dupkey.equals("") && ss_dupkey.equals(dupcode)) {
			isModifyGbn = true;
		}else if(modifyGbn.equals("16010100") && !ss_dupkey.equals("") && ss_dupkey.equals(dupcode)) {
			isModifyGbn = true;
		}
		
		if(!isModifyGbn) {
			String SVC_MSGE ="수정 권한이 없습니다.";
			return alert("", SVC_MSGE, model);
		}
		
		String ss_id = (String) WebUtils.getSessionAttribute(request, "ss_id");
		bbsContentFVo.setModId(ss_id);
		bbsContentFVo.setModIp(request.getRemoteAddr());
		bbsContentFVo.setSearchDupcode(dupcode);
		
		bbsContentFSvc.modifyBbsContentF(bbsContentFVo);

		ContentFileVo contentFileVo = new ContentFileVo();
		contentFileVo.setBcIdx(bbsContentFVo.getBcIdx());
		contentFileVo.setCbIdx(bbsContentFVo.getCbIdx());
		contentFileVo.setRegId(ss_id);
		contentFileSvc.registContentFile(request, contentFileVo);
		
		StringBuffer addParam = new StringBuffer();
		addParam.append("?pageIndex=").append(bbsContentFVo.getPageIndex());
		addParam.append("&searchCondition=").append(bbsContentFVo.getSearchCondition());
		addParam.append("&searchKeyword=").append(bbsContentFVo.getSearchKeyword());
		addParam.append("&searchCateCont=").append(bbsContentFVo.getSearchCateCont());
		addParam.append("&cbIdx=").append(bbsContentFVo.getCbIdx());
		addParam.append("&bcIdx=").append(bbsContentFVo.getBcIdx());

		String SVC_MSGE = Message.getMessage("401.message");
		return alert("/site/"+strDomain+"/ex/board/View.do"+addParam.toString(), SVC_MSGE, model);
	}
	
	@RequestMapping("/site/{strDomain}/foffice/board/RmvProcess.do")
	public String BbsContentFRmv(HttpServletRequest request, @PathVariable("strDomain") String strDomain, @ModelAttribute("BoardVo") BoardVo boardVo, @ModelAttribute("BbsContentFVo") BbsContentFVo bbsContentFVo, ModelMap model) throws Exception {

		boardVo = boardSvc.retrieveBoard(boardVo);
		
		if(boardVo == null) {
			String SVC_MSGE ="잘못된 접근입니다.";
			return alert("/site/"+strDomain+"/main.do", SVC_MSGE, model);
		}
		
		Map<String, Object> result = bbsContentFSvc.retrieveBbsContentF(bbsContentFVo);
		
		if(result == null) {
			String SVC_MSGE ="잘못된 접근입니다.";
			return alert(request.getContextPath()+"/site/"+strDomain+"/ex/board/List.do?cbIdx="+boardVo.getCbIdx(), SVC_MSGE, model);
		}
		
		String ss_level = EgovStringUtil.isNullToString(WebUtils.getSessionAttribute(request, "ss_level"));
		String ss_dupkey = EgovStringUtil.isNullToString(WebUtils.getSessionAttribute(request, "ss_dupkey"));
		String dupcode = EgovStringUtil.isNullToString(result.get("dupcode"));
		
		//삭제 권한 체크
		String deleteGbn = boardVo.getDelGbn(); //모두:16010100, 회원:16010300, 회원(본인게시물):16010200, 본인인증:16010400, 관리자:16010500
		boolean isDeleteGbn = false;
		if(deleteGbn.equals("16010200") && ss_level.equals("7") && !ss_dupkey.equals("") && ss_dupkey.equals(dupcode)) {
			isDeleteGbn = true;
		}else if(deleteGbn.equals("16010300") && ss_level.equals("7") && !ss_dupkey.equals("") && ss_dupkey.equals(dupcode)) {
			isDeleteGbn = true;
		}else if(deleteGbn.equals("16010400") && !ss_dupkey.equals("") && ss_dupkey.equals(dupcode)) {
			isDeleteGbn = true;
		}else if(deleteGbn.equals("16010100") && !ss_dupkey.equals("") && ss_dupkey.equals(dupcode)) {
			isDeleteGbn = true;
		}
		
		if(!isDeleteGbn) {
			String SVC_MSGE ="삭제 권한이 없습니다.";
			return alert("", SVC_MSGE, model);
		}
		
		String ss_id = (String) WebUtils.getSessionAttribute(request, "ss_id");
		bbsContentFVo.setModId(ss_id);
		bbsContentFVo.setModIp(request.getRemoteAddr());
		bbsContentFVo.setSearchDupcode(dupcode);
		
		bbsContentFSvc.removeBbsContentF(bbsContentFVo);
		
		StringBuffer addParam = new StringBuffer();
		addParam.append("?pageIndex=").append(bbsContentFVo.getPageIndex());
		addParam.append("&searchCondition=").append(bbsContentFVo.getSearchCondition());
		addParam.append("&searchKeyword=").append(bbsContentFVo.getSearchKeyword());
		addParam.append("&searchCateCont=").append(bbsContentFVo.getSearchCateCont());
		addParam.append("&cbIdx=").append(bbsContentFVo.getCbIdx());
		
		String SVC_MSGE = Message.getMessage("501.message");
		return alert("/site/"+strDomain+"/ex/board/List.do"+addParam.toString(), SVC_MSGE, model);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/site/{strDomain}/ex/board/List.do")
	public String BbsContentFList(HttpServletRequest request, @PathVariable("strDomain") String strDomain, @ModelAttribute("BoardVo") BoardVo boardVo, @ModelAttribute("ConfigPropertyVo") ConfigPropertyVo configPropertyVo, @ModelAttribute("BbsContentFVo") BbsContentFVo bbsContentFVo, ModelMap model) throws Exception {
		
		boardVo = boardSvc.retrieveBoard(boardVo);
		if(boardVo != null) {
			model.addAttribute("BoardVo", boardVo);
		}else{
			String SVC_MSGE ="잘못된 접근입니다.";
			return alert(request.getContextPath()+"/site/"+strDomain+"/main.do", SVC_MSGE, model);
		}
		
		String bbsTempletGbn = boardVo.getBbsTempletGbn();
		String bbsTempletName = "";
		if(bbsTempletGbn.equals("16050100")) {	// 일반게시판
			bbsTempletName = "basic";
		}else if(bbsTempletGbn.equals("16050200")) {	// 포토(리스트)
			bbsContentFVo.setPageUnit(5);
			bbsTempletName = "basic";
		}else if(bbsTempletGbn.equals("16050300")) {	// 포토(앨범)
			bbsContentFVo.setPageUnit(8);
			bbsTempletName = "album";
		}else if(bbsTempletGbn.equals("16050400")) {	// 동영상
			bbsContentFVo.setPageUnit(8);
			bbsTempletName = "video";
		}else if(bbsTempletGbn.equals("16050500")) {	// 묻고답하기
			bbsTempletName = "qna";
		}else if(bbsTempletGbn.equals("16050600")) {	// 목록게시판
			bbsTempletName = "basic";
		}else if(bbsTempletGbn.equals("16050700")) {	// 민원게시판
			bbsTempletName = "basic";
		}else{
			String SVC_MSGE ="잘못된 접근입니다.";
			return alert(request.getContextPath()+"/site/"+strDomain+"/main.do", SVC_MSGE, model);
		}
		model.addAttribute("bbsTempletName", bbsTempletName);

		String listGbn = boardVo.getListGbn();
		if(listGbn.equals("16010200")) { // 회원(본인게시물) 만 조건이 생김. 나머지는 추후에 구현
			String ss_dupkey = (String) WebUtils.getSessionAttribute(request, "ss_dupkey");
			bbsContentFVo.setSearchDupcode(ss_dupkey);
		}

		//모바일에서 접속인지 PC에서 접속인지 체크
		boolean isMobile = WebUtil.isMobile(request);
		if(isMobile) {
			bbsContentFVo.setPageSize(5);
			configPropertyVo.setSearchListType("mobile");
		}else{
			configPropertyVo.setSearchListType("web");
		}
		model.addAttribute("isMobile", isMobile);
		
		List propertylist = boardSvc.retrieveListConfigProperty(configPropertyVo);
		model.addAttribute("propertylist", propertylist);

		configPropertyVo.setSearchListType("search");
		List conditionlist = boardSvc.retrieveListConfigProperty(configPropertyVo);
		model.addAttribute("conditionlist", conditionlist);
		
		if(boardVo.getCategoryUseYn().equals("Y")) {
			model.addAttribute("categoryList", boardSvc.retrieveListBbsCategory(boardVo));
		}
		
		int cbIdx = boardVo.getCbIdx();
		
		if(cbIdx == 57 || cbIdx == 58 || cbIdx == 59) { // 공지사항, 행사소식, 고시공고일 경우
			bbsContentFVo.setSearchPlaceType("B");
			bbsContentFVo.setSearchNameCont(strDomain);
			
		}
		
		if(cbIdx == 414) {	
			bbsContentFVo.setSearchOpenYnCont("21000200");
		}
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(bbsContentFVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(bbsContentFVo.getPageUnit());
		paginationInfo.setPageSize(bbsContentFVo.getPageSize());
		
		bbsContentFVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		bbsContentFVo.setLastIndex(paginationInfo.getLastRecordIndex());
		bbsContentFVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		Map<String, Object> map = bbsContentFSvc.retrievePagBbsContentF(bbsContentFVo); 
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("strDomain", strDomain);
		
		//글쓰기 권한 체크
		boolean isWriteGbn = false;
		String writeGbn = boardVo.getWriteGbn(); //모두:16010100, 회원:16010300, 회원(본인게시물):16010200, 본인인증:16010400, 관리자:16010500
		if(!writeGbn.equals("16010500")) {
			isWriteGbn = true;
		}
		model.addAttribute("isWriteGbn", isWriteGbn);
		
		model.addAttribute("nowDate", DateUtil.getCurrentDate("yyyyMMdd"));
		
		return "injeinc/foffice/ex/board/list";
	}	
		
}