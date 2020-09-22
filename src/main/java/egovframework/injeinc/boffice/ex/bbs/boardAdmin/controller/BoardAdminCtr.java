package egovframework.injeinc.boffice.ex.bbs.boardAdmin.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.Message;
import egovframework.injeinc.boffice.ex.bbs.boardAdmin.service.BoardAdminSvc;
import egovframework.injeinc.boffice.ex.bbs.boardAdmin.vo.BoardAdminVo;
import egovframework.injeinc.common.util.EgovDateUtil;
import egovframework.injeinc.common.util.EgovStringUtil;
import egovframework.injeinc.foffice.ex.bbs.vo.BbsFVo;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


@Controller
public class BoardAdminCtr extends CmmLogCtr{

	@Resource(name = "BoardAdminSvc")
	private BoardAdminSvc boardAdminSvc;
	
	@Autowired(required=true)
	private MappingJackson2JsonView jsonView;
	
	private EgovDateUtil egovDateUtil;
	
	/**
	 * 통합게시물(일반)관리 리스트 화면
	 * @param request
	 * @param boardAdminVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/boffice/ex/bbs/boardAdmin/boardAdmin_list.do")
	public String BoardList(HttpServletRequest request, @ModelAttribute("BoardAdminVo") BoardAdminVo boardAdminVo, ModelMap model) throws Exception {
		
		String SVC_MSGE = "";
		String returnUrl = "";
		Map paramMap = null;
		String date = egovDateUtil.yearMonthDay();
		
		try {
			HttpSession ses = request.getSession();
			
			int rowCnt = 10;
			String stringRowCnt = EgovStringUtil.nullConvert(request.getParameter("rowCntValue"));
			String searchCondition = EgovStringUtil.nullConvert(request.getParameter("searchCondition"));
			String searchBbsGroup = EgovStringUtil.nullConvert(request.getParameter("searchBbsGroup"));
			String searchBbs = EgovStringUtil.nullConvert(request.getParameter("searchBbs"));
			
			int cbIdx = EgovStringUtil.zeroConvert(request.getParameter("cbIdx"));
			
			/*System.out.println("cbIdx=========>"+cbIdx);
			System.out.println("cbIdx=========>"+cbIdx);
			System.out.println("cbIdx=========>"+cbIdx);*/
			String webUseYn = EgovStringUtil.nullConvert(request.getParameter("webUseYn"));
			String bbsTempletGbn = EgovStringUtil.nullConvert(request.getParameter("bbsTempletGbn"));
			String gbn = EgovStringUtil.nullConvert(request.getParameter("gbn"));
			String chkGbn = EgovStringUtil.nullConvert(request.getParameter("chkGbn"));
			String bcDelYn = EgovStringUtil.nullConvert(request.getParameter("bcDelYn"));
			
			String scRegDtSt = EgovStringUtil.nullConvert(request.getParameter("scRegDtSt"));
			String scRegDtEd = EgovStringUtil.nullConvert(request.getParameter("scRegDtEd"));
			//String startTime = request.getParameter("startTime");
			//String endTime = request.getParameter("endTime");
			
			String cateTypeCd = EgovStringUtil.nullConvert(request.getParameter("cateTypeCd"));
			String tgtTypeCd = EgovStringUtil.nullConvert(request.getParameter("tgtTypeCd"));
			String searchKey = EgovStringUtil.nullConvert(request.getParameter("searchKey"));
			int pageIndex = boardAdminVo.getPageIndex();
			
			if(stringRowCnt == null || stringRowCnt == ""){
				rowCnt = 10;
			}else{
				rowCnt = Integer.parseInt(stringRowCnt);
			}
			
			if(bbsTempletGbn == null || bbsTempletGbn == ""){
				bbsTempletGbn = "16050100";
			}
			
			
			boardAdminVo.setAdminId((String)ses.getAttribute("SesUserId"));
			boardAdminVo.setPermCd((String)ses.getAttribute("SesUserPermCd"));//권한
			
			List firstSelectBox = boardAdminSvc.retrieveFirstSelect(boardAdminVo);
			
			//List timeSelect = boardAdminSvc.retrieveTimeSelect(boardAdminVo);
			
			BoardAdminVo boardAdminVo2 = new BoardAdminVo();
			if(cbIdx > 0){
				boardAdminVo.setCbIdx(cbIdx);
				boardAdminVo = boardAdminSvc.boardInfo(boardAdminVo);
				boardAdminVo2.setCbIdx(cbIdx);
			}/*else{
				searchBbsGroup = "16006000";
				searchBbs = "16006002";
				boardAdminVo.setCbIdx("57");
				boardAdminVo = boardAdminSvc.boardInfo(boardAdminVo);
				boardAdminVo2.setCbIdx("57");
			}*/
			
			
			boardAdminVo2.setAdminId((String)ses.getAttribute("SesUserId"));
			boardAdminVo2.setWebUseYn("Y");
			
			//boardAdminVo2.setStartTime(startTime);
			//boardAdminVo2.setEndTime(endTime);
			
			if(scRegDtSt == null || scRegDtEd == null){
				//boardAdminVo2.setScRegDtSt(date.replaceAll("_", ""));
				//boardAdminVo2.setScRegDtEd(date.replaceAll("_", ""));
				boardAdminVo2.setScRegDtSt("20070101");
				boardAdminVo2.setScRegDtEd(date.replaceAll("_", ""));
			}else{
				boardAdminVo2.setScRegDtSt(scRegDtSt.replaceAll("-", ""));
				boardAdminVo2.setScRegDtEd(scRegDtEd.replaceAll("-", ""));
			}
			
			if(bcDelYn == null || bcDelYn == ""){
				bcDelYn = "N";
			}
			
			boardAdminVo2.setBbsTempletGbn(bbsTempletGbn);
			boardAdminVo2.setBcDelYn(bcDelYn);
			//boardAdminVo2.setStartTime(startTime);
			//boardAdminVo2.setEndTime(endTime);
			boardAdminVo2.setCateTypeCd(cateTypeCd);
			boardAdminVo2.setTgtTypeCd(tgtTypeCd);
			boardAdminVo2.setSearchKey(searchKey);
			boardAdminVo2.setPageIndex(pageIndex);
			
			//게시판 라벨 리스트 조회
			List labelList = boardAdminSvc.boardLabelList(boardAdminVo2);
			
			//게시판 컨텐츠  조회할 목록 조회
			List contMappList = boardAdminSvc.boardContMappList(boardAdminVo2);
			
			//게시판 검색항목 리스트 조회
			List searchList = boardAdminSvc.boardSearchList(boardAdminVo2);
			
			/** 게시판 컨텐츠 조회*/
			List contentList = null;
			
			/** 게시판 컨텐츠 갯수*/
			int contentCnt = 0;
			
			contentCnt = boardAdminSvc.boardcontentCnt(boardAdminVo2);
			//일반게시판일 경우
			if( "16050100".equals(bbsTempletGbn) ){
				returnUrl = "injeinc/boffice/ex/bbs/boardAdmin/boardAdmin_list";
			}else if( "16050200".equals(bbsTempletGbn) ){
				boardAdminVo2.setPageUnit(8);
				returnUrl = "injeinc/boffice/ex/bbs/boardAdmin/boardAdmin_listPhoto";
			}else if( "16050300".equals(bbsTempletGbn) ){
				returnUrl = "injeinc/boffice/ex/bbs/boardAdmin/boardAdmin_listPhoto2";
			}else if( "16050400".equals(bbsTempletGbn) ){
				boardAdminVo2.setPageUnit(8);
				returnUrl = "injeinc/boffice/ex/bbs/boardAdmin/boardAdmin_listVideo";
			}else if( "16050500".equals(bbsTempletGbn) ){
				returnUrl = "injeinc/boffice/ex/bbs/boardAdmin/boardAdmin_list";
			}else if( "16050700".equals(bbsTempletGbn) ){
				returnUrl = "injeinc/boffice/ex/bbs/boardAdmin/boardAdmin_list";
			}
			
			/*System.out.println("returnUrl========>"+returnUrl);
			System.out.println("returnUrl========>"+returnUrl);
			System.out.println("returnUrl========>"+returnUrl);*/
			
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(boardAdminVo2.getPageIndex());
			if(rowCnt > 0){
				paginationInfo.setRecordCountPerPage(rowCnt);
			}else{
				paginationInfo.setRecordCountPerPage(boardAdminVo2.getPageUnit());
			}
			paginationInfo.setPageSize(boardAdminVo2.getPageSize());
			boardAdminVo2.setFirstIndex(paginationInfo.getFirstRecordIndex());
			boardAdminVo2.setLastIndex(paginationInfo.getLastRecordIndex());
			boardAdminVo2.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			paginationInfo.setTotalRecordCount(contentCnt);

			contentList = boardAdminSvc.boardContentList(boardAdminVo2);
			
			model.addAttribute("paginationInfo", paginationInfo);
			model.addAttribute("firstSelectBox", firstSelectBox);
			//model.addAttribute("timeSelect", timeSelect);
			model.addAttribute("labelList", labelList);
			model.addAttribute("searchList", searchList);
			model.addAttribute("totCnt",contentCnt);
			model.addAttribute("rowCnt",rowCnt);
			model.addAttribute("bbsFileCnt",boardAdminVo.getBbsFileCnt());
			model.addAttribute("searchKey", searchKey);
			model.addAttribute("searchCondition", searchCondition);
			model.addAttribute("searchBbsGroup", searchBbsGroup);
			model.addAttribute("searchBbs", searchBbs);
			model.addAttribute("cbIdx", cbIdx);
			model.addAttribute("bbsTempletGbn", bbsTempletGbn);
			model.addAttribute("contMappList", contMappList);
			model.addAttribute("contentList", contentList);
			model.addAttribute("contentCnt", contentCnt);
			model.addAttribute("gbn", gbn);
			model.addAttribute("chkGbn", chkGbn);
			//model.addAttribute("startTime", startTime);
			//model.addAttribute("endTime", endTime);
		} catch (Exception e) {
			SVC_MSGE = Message.getMessage("901.code");	//에러
			throw e;
		}
		return returnUrl;
		
	}
	
	/**
	 * 엑셀 다운로드
	 * @param request
	 * @param boardAdminVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/boffice/ex/bbs/boardAdmin/boardAdmin_listExcel.do")
	public String BoardListExcel(
			HttpServletRequest request, @ModelAttribute("BoardAdminVo") BoardAdminVo boardAdminVo
			, ModelMap model) throws Exception {
		
		String SVC_MSGE = "";
		String returnUrl = "";
		Map paramMap = null;
		String date = egovDateUtil.yearMonthDay();
		
		try {
			HttpSession ses = request.getSession();
			
			int rowCnt = 10;
			String stringRowCnt = EgovStringUtil.nullConvert(request.getParameter("rowCntValue"));
//			String searchKeyWord = request.getParameter("searchKeyWord");
			String searchCondition = EgovStringUtil.nullConvert(request.getParameter("searchCondition"));
			String searchBbsGroup = EgovStringUtil.nullConvert(request.getParameter("searchBbsGroup"));
			String searchBbs = EgovStringUtil.nullConvert(request.getParameter("searchBbs"));
			//String searchBbsGroup = boardAdminVo.getSearchBbsGroup();
			//String searchBbs = boardAdminVo.getSearchBbs();
			
			int cbIdx = EgovStringUtil.zeroConvert(request.getParameter("cbIdx"));
			String webUseYn = EgovStringUtil.nullConvert(request.getParameter("webUseYn"));
			String bbsTempletGbn = EgovStringUtil.nullConvert(request.getParameter("bbsTempletGbn"));
			String gbn = EgovStringUtil.nullConvert(request.getParameter("gbn"));
			
			String scRegDtSt = EgovStringUtil.nullConvert(request.getParameter("scRegDtSt"));
			String scRegDtEd = EgovStringUtil.nullConvert(request.getParameter("scRegDtEd"));
			String startTime = EgovStringUtil.nullConvert(request.getParameter("startTime"));
			String endTime = EgovStringUtil.nullConvert(request.getParameter("endTime"));
			
			String cateTypeCd = EgovStringUtil.nullConvert(request.getParameter("cateTypeCd"));
			String tgtTypeCd = EgovStringUtil.nullConvert(request.getParameter("tgtTypeCd"));
			String searchKey = EgovStringUtil.nullConvert(request.getParameter("searchKey"));
			
			if(stringRowCnt == null || stringRowCnt == ""){
				rowCnt = 10;
			}else{
				rowCnt = Integer.parseInt(stringRowCnt);
			}
			
			if(webUseYn == null || webUseYn == ""){
				webUseYn = "Y";
			}
			
			if(bbsTempletGbn == null || bbsTempletGbn == ""){
				bbsTempletGbn = "16050100";
			}
			
			boardAdminVo.setCbIdx(cbIdx);
			boardAdminVo.setAdminId((String)ses.getAttribute("SesUserId"));
			
			List firstSelectBox = boardAdminSvc.retrieveFirstSelect(boardAdminVo);
			
			List timeSelect = boardAdminSvc.retrieveTimeSelect(boardAdminVo);
			
			
			
//			boardAdminVo.setSearchKeyword(searchKeyWord);
			
			if(cbIdx > 0){
				boardAdminVo = boardAdminSvc.boardInfo(boardAdminVo);
			}
			
			boardAdminVo.setWebUseYn(webUseYn);
			if(scRegDtSt == null || scRegDtEd == null){
				boardAdminVo.setScRegDtSt(date.replaceAll("_", ""));
				boardAdminVo.setScRegDtEd(date.replaceAll("_", ""));
			}else{
				boardAdminVo.setScRegDtSt(scRegDtSt.replaceAll("-", ""));
				boardAdminVo.setScRegDtEd(scRegDtEd.replaceAll("-", ""));
			}
			
			boardAdminVo.setBbsTempletGbn(bbsTempletGbn);
			boardAdminVo.setStartTime(startTime);
			boardAdminVo.setEndTime(endTime);
			boardAdminVo.setCateTypeCd(cateTypeCd);
			boardAdminVo.setTgtTypeCd(tgtTypeCd);
			boardAdminVo.setSearchKey(searchKey);
			
			//게시판 라벨 리스트 조회
			List labelList = boardAdminSvc.boardLabelList(boardAdminVo);
			
			//게시판 컨텐츠  조회할 목록 조회
			List contMappList = boardAdminSvc.boardContMappList(boardAdminVo);
			
			//게시판 검색항목 리스트 조회
			List searchList = boardAdminSvc.boardSearchList(boardAdminVo);
			
			/** 게시판 컨텐츠 조회*/
			List contentList = null;
			
			/** 게시판 컨텐츠 갯수*/
			int contentCnt = 0;
			
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(boardAdminVo.getPageIndex());
			
			if(rowCnt > 0){
				paginationInfo.setRecordCountPerPage(rowCnt);
			}else{
				paginationInfo.setRecordCountPerPage(boardAdminVo.getPageUnit());
			}
			paginationInfo.setPageSize(boardAdminVo.getPageSize());
			boardAdminVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			boardAdminVo.setLastIndex(paginationInfo.getLastRecordIndex());
			boardAdminVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			
			
			//일반게시판일 경우
			if( "16050100".equals(bbsTempletGbn) ){
				contentCnt = boardAdminSvc.boardcontentCnt(boardAdminVo);
				returnUrl = "injeinc/boffice/ex/bbs/boardAdmin/boardAdmin_listExcel";
			}else if( "16050200".equals(bbsTempletGbn) ){
				contentCnt = boardAdminSvc.boardcontentCnt(boardAdminVo);
				boardAdminVo.setPageUnit(8);
				returnUrl = "injeinc/boffice/ex/bbs/boardAdmin/boardAdmin_listPhoto";
			}else if( "16050300".equals(bbsTempletGbn) ){
				contentCnt = boardAdminSvc.boardcontentCnt(boardAdminVo);
				returnUrl = "injeinc/boffice/ex/bbs/boardAdmin/boardAdmin_listPhoto2";
			}else if( "16050400".equals(bbsTempletGbn) ){
				contentCnt = boardAdminSvc.boardcontentCnt(boardAdminVo);
				boardAdminVo.setPageUnit(8);
				returnUrl = "injeinc/boffice/ex/bbs/boardAdmin/boardAdmin_listVideo";
			}else if( "16050500".equals(bbsTempletGbn) ){
				contentCnt = boardAdminSvc.boardcontentCnt(boardAdminVo);
				returnUrl = "injeinc/boffice/ex/bbs/boardAdmin/boardAdmin_list";
			}else if( "16050700".equals(bbsTempletGbn) ){
				contentCnt = boardAdminSvc.minwonContentCnt(boardAdminVo);
				returnUrl = "injeinc/boffice/ex/bbs/boardAdmin/boardAdmin_listMw";
			}
			
			paginationInfo.setTotalRecordCount(contentCnt);
			
			if( "16050700".equals(bbsTempletGbn) ){
				contentList = boardAdminSvc.minwonContentList(boardAdminVo);
			}else{
				contentList = boardAdminSvc.boardContentList(boardAdminVo);
			}
			/*System.out.println("boardAdminVo ====> " + boardAdminVo);*/
			model.addAttribute("paginationInfo", paginationInfo);
			model.addAttribute("firstSelectBox", firstSelectBox);
			model.addAttribute("timeSelect", timeSelect);
			model.addAttribute("labelList", labelList);
			model.addAttribute("searchList", searchList);
			model.addAttribute("totCnt",contentCnt);
			model.addAttribute("rowCnt",rowCnt);
			model.addAttribute("bbsFileCnt",boardAdminVo.getBbsFileCnt());
			model.addAttribute("searchKey", searchKey);
			model.addAttribute("searchCondition", searchCondition);
			model.addAttribute("searchBbsGroup", searchBbsGroup);
			model.addAttribute("searchBbs", searchBbs);
			model.addAttribute("cbIdx", cbIdx);
			model.addAttribute("bbsTempletGbn", bbsTempletGbn);
			model.addAttribute("contMappList", contMappList);
			model.addAttribute("contentList", contentList);
			model.addAttribute("contentCnt", contentCnt);
			model.addAttribute("gbn", gbn);
			model.addAttribute("startTime", startTime);
			model.addAttribute("endTime", endTime);
		} catch (Exception e) {
			SVC_MSGE = Message.getMessage("901.code");	//에러
			throw e;
		}
		return returnUrl;
		
	}
	
	/**
	 * 검색범위 second SelectBox
	 * @param request
	 * @param response
	 */
	@RequestMapping(value= "/boffice/ex/bbs/boardAdmin/secondSelectBox_list.do")
	public void secondSelectBoxList(HttpServletRequest request, HttpServletResponse response) throws Exception {

			HttpSession ses = request.getSession();
			
			String cbUprCd = EgovStringUtil.nullConvert(request.getParameter("cbUprCd"));
			
			HashMap<String, String> param = new HashMap<String, String>();
			param.put("cbUprCd", cbUprCd);
			param.put("adminId", (String)ses.getAttribute("SesUserId"));
			param.put("permCd", (String)ses.getAttribute("SesUserPermCd"));
			
			HashMap<String, Object> serviceMap = boardAdminSvc.retrieveSecondSelect(param);
			jsonView.render(serviceMap, request, response);
		
	}
	
	/**
	 * 선택게시물 삭제 및 승인
	 * @param request
	 * @param boardAdminVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/boffice/ex/bbs/boardAdmin/boardAdmin_Mod.do")
	public String BoardMod(
			HttpServletRequest request, @ModelAttribute("BoardAdminVo") BoardAdminVo boardAdminVo
			, ModelMap model) throws Exception {
		
		String SVC_MSGE = "";
		String returnUrl = "";
		Map paramMap = null;
		
		
		
		try {
			HttpSession ses = request.getSession();
			
			int cbIdx = EgovStringUtil.zeroConvert(request.getParameter("cbIdx"));
			
			String scRegDtSt = EgovStringUtil.nullConvert(request.getParameter("scRegDtSt"));
			String scRegDtEd = EgovStringUtil.nullConvert(request.getParameter("scRegDtEd"));
			String startTime = EgovStringUtil.nullConvert(request.getParameter("startTime"));
			String endTime = EgovStringUtil.nullConvert(request.getParameter("endTime"));
			
			String cateTypeCd = EgovStringUtil.nullConvert(request.getParameter("cateTypeCd"));
			String tgtTypeCd = EgovStringUtil.nullConvert(request.getParameter("tgtTypeCd"));
			String searchKey = EgovStringUtil.nullConvert(request.getParameter("searchKey"));
			String bbsTempletGbn = EgovStringUtil.nullConvert(request.getParameter("bbsTempletGbn"));
			
			String searchBbsGroup = EgovStringUtil.nullConvert(request.getParameter("searchBbsGroup"));
			String searchBbs = EgovStringUtil.nullConvert(request.getParameter("searchBbs"));
			String gbn = EgovStringUtil.nullConvert(request.getParameter("gbn"));
			String chkGbn = EgovStringUtil.nullConvert(request.getParameter("chkGbn"));
			String idxValueArray[] = request.getParameter("idxValue").split(",");
			if(idxValueArray != null){
				
				for(int i = 0; i < idxValueArray.length; i++){
					boardAdminVo.setCbIdx(cbIdx);
					boardAdminVo.setBcIdx(Integer.parseInt(idxValueArray[i]));
					
					if("D".equals(chkGbn)){
						boardAdminSvc.removeBoard(boardAdminVo);
						SVC_MSGE = Message.getMessage("501.message"); //삭제성공
					}else if("C".equals(chkGbn)){
						boardAdminSvc.modifyBoard(boardAdminVo);
						SVC_MSGE = Message.getMessage("402.message"); //승인성공
					}
				}
			}
			
			returnUrl = "?cbIdx="+cbIdx+"&searchBbsGroup="+searchBbsGroup+"&searchBbs="+searchBbs+"&gbn="+gbn+"&chkGbn="+chkGbn+
					"&scRegDtSt="+scRegDtSt+"&scRegDtEd="+scRegDtEd+"&startTime="+startTime+"&endTime="+endTime+"&cateTypeCd="+cateTypeCd+
					"&tgtTypeCd="+tgtTypeCd+"&searchKey="+searchKey+"&bbsTempletGbn="+bbsTempletGbn;
			/*System.out.println("returnUrl =============>> : "+ returnUrl);*/
		} catch (Exception e) {
			SVC_MSGE = Message.getMessage("901.code");	//에러
			throw e;
		}
		
		return alert("/boffice/ex/bbs/boardAdmin/boardAdmin_list.do"+returnUrl, SVC_MSGE, model);
	}
	
	/**
	 * 통합게시물(일반)관리 글등록 화면
	 * @param request
	 * @param boardAdminVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/boffice/ex/bbs/boardAdmin/boardAdmin_RegForm.do")
	public String BoardRegForm(
			HttpServletRequest request, @ModelAttribute("BoardAdminVo") BoardAdminVo boardAdminVo
			, ModelMap model) throws Exception {
		
		String SVC_MSGE = "";
		String returnUrl = "";
		Map paramMap = null;
		
		try {
			HttpSession ses = request.getSession();
			
			String ssId = (String)ses.getAttribute("SesUserId");
			String ssName = (String)ses.getAttribute("SesUserDeptNm");
			
			String mode = boardAdminVo.getMode();
			int parentSeq = boardAdminVo.getParentSeq();
			int answerStep = boardAdminVo.getAnswerStep();
			int answerDepth = boardAdminVo.getAnswerDepth();
			String fileCnt = EgovStringUtil.nullConvert(request.getParameter("bbsFileCnt"));
			
			int cbIdx = EgovStringUtil.zeroConvert(request.getParameter("cbIdx"));
			int bcIdx = EgovStringUtil.zeroConvert(request.getParameter("bcIdx"));
			String webUseYn = EgovStringUtil.nullConvert(request.getParameter("webUseYn"));
			String bbsTempletGbn = EgovStringUtil.nullConvert(request.getParameter("bbsTempletGbn"));
//			String answerDepth = request.getParameter("answerDepth");
//			String answerDepthGbn = request.getParameter("answerDepthGbn");
			String searchBbsGroupText = EgovStringUtil.nullConvert(request.getParameter("searchBbsGroupText"));
			String searchBbsText = EgovStringUtil.nullConvert(request.getParameter("searchBbsText"));
			
			String searchBbsGroup = EgovStringUtil.nullConvert(request.getParameter("searchBbsGroup"));
			String searchBbs = EgovStringUtil.nullConvert(request.getParameter("searchBbs"));
			
			if(webUseYn == ""){
				webUseYn = "Y";
			}
			
			if(bbsTempletGbn == null || bbsTempletGbn == ""){
				bbsTempletGbn = "16050100";
			}
			
			//List boardLabelProGbnList = boardAdminSvc.selectboardlabelPropGbnList(boardAdminVo);
			
			boardAdminVo.setCbIdx(cbIdx);
			boardAdminVo.setBcIdx(bcIdx);
			
			HashMap<String, String> resultMap = null;
			List labelPropGbnList  = boardAdminSvc.retrieveBoardlabelPropGbnList(boardAdminVo);
			Map authorMap = null;
			//게시판 카테고리
			List categoryList = boardAdminSvc.retrieveBoardCategoryList(boardAdminVo);
			
			if( "U".equals(mode) ){
				
				//글 수정 권한 체크
//				authorMap = BoardAuthorChk(boardAdminVo.getModGbn() ,request);
//				if( !Boolean.valueOf((Boolean)authorMap.get("flag")) ){
//					return alert((String)authorMap.get("returnUrl") ,(String)authorMap.get("msg"), model);
//				}
				
				//Map detailMap = boardAdminSvc.retrieveBoardlabelPropGbnList(boardAdminVo);
				Map detailMap = boardAdminSvc.boardDetail(boardAdminVo);
				List fileList = boardAdminSvc.boardFileList(boardAdminVo);
				
				resultMap = BoardHtmlCreate(labelPropGbnList, "U", detailMap, categoryList, request, fileCnt, fileList);
			}else{
//				if( "C".equals(mode) ){//글쓰기 권한 체크
//					return alert((String)authorMap.get("returnUrl") ,(String)authorMap.get("msg"), model);
//				}else if( "R".equals(mode) ){//리플 권한 체크
//					return alert((String)authorMap.get("returnUrl") ,(String)authorMap.get("msg"), model);
//				}
				resultMap = BoardHtmlCreate(labelPropGbnList, mode, null, categoryList, request, fileCnt, null);
			}
			
			//System.out.println("resultMap=============>> : " + resultMap);
			
			boardAdminVo.setMode(mode);
			boardAdminVo.setParentSeq(parentSeq);
			boardAdminVo.setAnswerStep(answerStep);
			boardAdminVo.setAnswerDepth(answerDepth);
			
			if( resultMap.containsKey("clobCont") )boardAdminVo.setClobCont(resultMap.get("clobCont"));
			if( resultMap.containsKey("subCont") )boardAdminVo.setSubContTemp(resultMap.get("subCont"));
			
			model.addAttribute("resultMap", resultMap);
			model.addAttribute("boardAdminVo", boardAdminVo);
			model.addAttribute("mode", mode);
			model.addAttribute("searchBbsGroupText", searchBbsGroupText);
			model.addAttribute("searchBbsText", searchBbsText);
			model.addAttribute("searchBbsGroup", searchBbsGroup);
			model.addAttribute("searchBbs", searchBbs);
			model.addAttribute("ssId", ssId);
			model.addAttribute("ssName", ssName);
			
			
			returnUrl = "injeinc/boffice/ex/bbs/boardAdmin/boardAdmin_RegForm";
			
		} catch (Exception e) {
			SVC_MSGE = Message.getMessage("901.code");	//에러
			throw e;
		}
		
		return returnUrl;
	}
	
	/**
	 * 게시글 저장
	 * @param request
	 * @param boardAdminVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/boffice/ex/bbs/boardAdmin/boardAdmin_Reg.do")
	public String BoardReg(
			HttpServletRequest request, @ModelAttribute("BoardAdminVo") BoardAdminVo boardAdminVo
			, ModelMap model) throws Exception {
		
		HttpSession ses = request.getSession();
		
		String SVC_MSGE = "";
		String returnParam = "";
		String mode = boardAdminVo.getMode();
		int cbIdx = boardAdminVo.getCbIdx();
		String bbsApprYn = boardAdminVo.getBbsApprYn();
		
		int answerStep = boardAdminVo.getAnswerStep();
		int answerDepth = boardAdminVo.getAnswerDepth();
		
		String searchBbsGroup = EgovStringUtil.nullConvert(request.getParameter("searchBbsGroup"));
		String searchBbs = EgovStringUtil.nullConvert(request.getParameter("searchBbs"));
		
		//String notiYnVal = request.getParameter("notiYn");
		String notiYnVal = EgovStringUtil.nullConvert(request.getParameter("notiYnVal"));
		String fileTagId = EgovStringUtil.nullConvert(request.getParameter("fileTagId"));
		//String answerDepth = request.getParameter("answerDepth");
		//String answerDepthGbn = request.getParameter("answerDepthGbn");
		String beForBcIdx = EgovStringUtil.nullConvert(request.getParameter("bcIdx"));
		String nameCont = EgovStringUtil.nullConvert(request.getParameter("nameCont"));
		
		String noticeGbn = EgovStringUtil.nullConvert(request.getParameter("noticeGbn"));
		String ssNameVal = EgovStringUtil.nullConvert(request.getParameter("ssNameVal"));
		
		boardAdminVo.setCbIdx(cbIdx);

		try {
			if("C".equals(mode)){
				
				int bcIdx = boardAdminSvc.registBcIdxNo(boardAdminVo);
				
				/*System.out.println("bcIdx ==============>>> : " + bcIdx);*/
				
				boardAdminVo.setBcIdx(bcIdx); 
				boardAdminVo.setCbIdx(cbIdx); 
				boardAdminVo.setNotiYn(notiYnVal);
//				boardAdminVo.setRegId(nameCont);
				boardAdminVo.setRegId((String)ses.getAttribute("SesUserId"));
				boardAdminVo.setApprYn("Y");
				if( "2".equals(bbsApprYn) ){
					boardAdminVo.setApprYn("N");
				}
				
//				if(noticeGbn.equals("Z")){
//					boardAdminSvc.boardRegistFiles(request ,boardAdminVo);
//					boardAdminSvc.boardContentInsert(boardAdminVo);
//					String cbIdxValue = boardAdminSvc.boardCbIdxSelect(ssNameVal);
//					
//					boardAdminVo.setCbIdx(cbIdxValue);
//					
//					boardAdminSvc.boardRegistFiles(request ,boardAdminVo);
//					boardAdminSvc.boardContentInsert(boardAdminVo);
//				}else if(noticeGbn.equals("A")){
//					boardAdminSvc.boardRegistFiles(request ,boardAdminVo);
//					boardAdminSvc.boardContentInsert(boardAdminVo);
//				}else if(noticeGbn.equals("B")){
					boardAdminSvc.boardRegistFiles(request ,boardAdminVo);
					boardAdminSvc.boardContentInsert(boardAdminVo);
//				}
				
				
				//민원결과 테이블
				//boardAdminSvc.boardContentMinwonResultInsert(boardAdminVo);
				
				//returnParam = "?cbIdx="+cbIdx+"&searchBbsGroup="+searchBbsGroup+"&searchBbs="+searchBbs;
				SVC_MSGE = Message.getMessage("201.message");
				//return alert("/boffice/ex/bbs/boardAdmin/boardAdmin_list.do", SVC_MSGE, model);
			} else if( "R".equals(mode) ){
				int bcIdx = boardAdminSvc.registBcIdxNo(boardAdminVo);
				boardAdminVo.setBcIdx(bcIdx); 
				boardAdminVo.setNotiYn("N");
				boardAdminVo.setApprYn("Y");
				boardAdminSvc.boardRegistFiles(request ,boardAdminVo);
				boardAdminSvc.boardContentInsert(boardAdminVo);
				
				SVC_MSGE = Message.getMessage("201.message");
				//return alert("/boffice/ex/bbs/boardAdmin/boardAdmin_list.do", SVC_MSGE, model);
			} else if( "U".equals(mode) ){
				
				boardAdminVo.setCbIdx(cbIdx);
				boardAdminVo.setRegId(nameCont);
				boardAdminVo.setNotiYn(notiYnVal);
				boardAdminSvc.boardRegistFiles(request ,boardAdminVo);
				boardAdminSvc.boardContentUpdate(boardAdminVo);
				//returnParam = "?cbIdx="+cbIdx+"&searchBbsGroup="+searchBbsGroup+"&searchBbs="+searchBbs;
				SVC_MSGE = Message.getMessage("201.message");
				//return alert("/boffice/ex/bbs/boardAdmin/boardAdmin_list.do", SVC_MSGE, model);
			}
			model.addAttribute("searchBbsGroup", searchBbsGroup);
			model.addAttribute("searchBbs", searchBbs);
			returnParam = "?cbIdx="+cbIdx+"&searchBbsGroup="+searchBbsGroup+"&searchBbs="+searchBbs;
		} catch (Exception e) {
			SVC_MSGE = Message.getMessage("901.code");	//에러
			throw e;
		}
		
		//return "";
		return alert("/boffice/ex/bbs/boardAdmin/boardAdmin_list.do"+returnParam, SVC_MSGE, model);
	}
	
	/**
	 * 통합게시물(일반)관리 상세 화면
	 * @param request
	 * @param boardAdminVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/boffice/ex/bbs/boardAdmin/boardAdmin_view.do")
	public String BoardView(
			HttpServletRequest request, @ModelAttribute("BoardAdminVo") BoardAdminVo boardAdminVo
			, ModelMap model) throws Exception {
		
		String returnUrl = "";
		
		int cbIdx = EgovStringUtil.zeroConvert(request.getParameter("cbIdx"));
		int bcIdx = EgovStringUtil.zeroConvert(request.getParameter("bcIdx"));
		String searchBbsGroupText = EgovStringUtil.nullConvert(request.getParameter("searchBbsGroupText"));
		String searchBbsText = EgovStringUtil.nullConvert(request.getParameter("searchBbsText"));
		String searchBbsGroup = EgovStringUtil.nullConvert(request.getParameter("searchBbsGroup"));
		String searchBbs = EgovStringUtil.nullConvert(request.getParameter("searchBbs"));
		String bbsFileCnt = EgovStringUtil.nullConvert(request.getParameter("bbsFileCnt"));
		
		boardAdminVo.setCbIdx(cbIdx);	
		boardAdminVo.setBcIdx(bcIdx);
		
		//게시판 정보 조회
		boardAdminVo = boardAdminSvc.boardInfo(boardAdminVo);
		
		String BbsTempletFilePath = boardAdminVo.getBbsTempletFilePath();
		String strFileName = boardAdminVo.getFileName();
		String strTempletcode = boardAdminVo.getBbsTempletGbn();
		
		
		boardAdminVo.setCbIdx(cbIdx);	
		boardAdminVo.setBcIdx(bcIdx);	
		boardAdminVo.setBbsTempletGbn(strTempletcode);
		
		//조회수
		boardAdminSvc.boardCountUpdate(boardAdminVo);
		
		//게시글 상세 조회
		List detailList = boardAdminSvc.retrieveBoard(boardAdminVo);
		Map detailMap = boardAdminSvc.boardDetail(boardAdminVo);
		
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("cbIdx", boardAdminVo.getCbIdx());
		param.put("bcIdx", boardAdminVo.getBcIdx());
		
		//게시글 첨부파일 조회
		//List boardFileList = boardAdminSvc.retrieveBoardFile(boardAdminVo);
		List<BoardAdminVo> boardFileList = (List<BoardAdminVo>)boardAdminSvc.retrieveBoardFile(param);
		
		model.addAttribute("detailList", detailList);
		model.addAttribute("detailMap", detailMap);
		model.addAttribute("boardFileList", boardFileList);
		model.addAttribute("boardAdminVo", boardAdminVo);
		model.addAttribute("cbIdx", cbIdx);
		model.addAttribute("bcIdx", bcIdx);
		model.addAttribute("searchBbsGroupText", searchBbsGroupText);
		model.addAttribute("searchBbsText", searchBbsText);
		model.addAttribute("searchBbsGroup", searchBbsGroup);
		model.addAttribute("searchBbs", searchBbs);
		model.addAttribute("bbsFileCnt", bbsFileCnt);
		
		//returnUrl = BbsTempletFilePath + strFileName;
		
		//민원게시글 답변 
		if( "16050700".equals(strTempletcode) ){
			List minwonReList = boardAdminSvc.minwonReList(boardAdminVo);
			String McIdx ="";
			for(int nLoop=0; nLoop<minwonReList.size(); nLoop++){
				BbsFVo fileMcIdx = (BbsFVo) minwonReList.get(nLoop);
				McIdx += fileMcIdx.getMcIdx()+",";
			}
			if( McIdx.length() > 0){
				McIdx = McIdx.substring(0,McIdx.length()-1);
				boardAdminVo.setMcIdx(McIdx); 
				List minwonFileList = boardAdminSvc.boardFileList(boardAdminVo);
				List fileMinwonImgList = boardAdminSvc.boardFileImgList(boardAdminVo);
				model.addAttribute("minwonFileList", minwonFileList);
				model.addAttribute("fileMinwonImgList", fileMinwonImgList);
			}
			model.addAttribute("minwonReList", minwonReList);
		}
		
		//포토 게시판
		if( "16050200".equals(strTempletcode) || "16050300".equals(strTempletcode) ){
			List fileImgList = boardAdminSvc.boardFileImgList(boardAdminVo);
			model.addAttribute("fileImgList", fileImgList);
		}
		
		//return returnUrl;
		return "injeinc/boffice/ex/bbs/boardAdmin/boardAdmin_view";		
	}
	
	/**
	 * 공지 설정 및 해제
	 * @param request
	 * @param boardAdminVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/boffice/ex/bbs/boardAdmin/boardAdmin_BbsMod.do")
	public String BoardBbsMod(
			HttpServletRequest request, @ModelAttribute("BoardAdminVo") BoardAdminVo boardAdminVo
			, ModelMap model) throws Exception {
		
		String SVC_MSGE = "";
		String returnUrl = "";
		Map paramMap = null;
		
		try {
			HttpSession ses = request.getSession();
			
			String cbIdx = EgovStringUtil.nullConvert(request.getParameter("cbIdx"));
			String gbn = EgovStringUtil.nullConvert(request.getParameter("gbn"));
			
			String gbnVal = boardAdminVo.getGbnVal();

			String notiYn = boardAdminVo.getNotiYn();
			String apprYn = boardAdminVo.getApprYn();
			String bcDelYn = boardAdminVo.getBcDelYn();
			
			if("notiYn".equals(gbnVal)){
				boardAdminSvc.modifyBoardNoti(boardAdminVo);
			}else if("apprYn".equals(gbnVal)){
				boardAdminSvc.modifyBoardApprYn(boardAdminVo);
			}else if("mwDelYn".equals(gbnVal)){
				boardAdminSvc.modifyBoardmwDelYn(boardAdminVo);
			}
			SVC_MSGE = Message.getMessage("100.message"); //정상처리
			
		} catch (Exception e) {
			SVC_MSGE = Message.getMessage("901.code");	//에러
			throw e;
		}
		
		return alert("/boffice/ex/bbs/boardAdmin/boardAdmin_list.do", SVC_MSGE, model);
	}
	
	
	/**
	 * 파일 다운로드
	 * @param request
	 * @param response
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("/boffice/ex/bbs/boardAdmin/boardAdmin_fileDownload.do")
	public void fileDownload(HttpServletRequest request, HttpServletResponse response, 
			@ModelAttribute("BoardAdminVo") BoardAdminVo boardAdminVo) throws FileNotFoundException, IOException, Exception {
		
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("cbIdx", boardAdminVo.getCbIdx());
		param.put("bcIdx", boardAdminVo.getBcIdx());
		param.put("fileNo", boardAdminVo.getFileNo());
		//게시글 첨부파일 조회
		List<BoardAdminVo> boardFileList = (List<BoardAdminVo>)boardAdminSvc.retrieveBoardFile(param);
		
		String fd_file_name = "";
		String fd_file_path = "";
		
		for(int i = 0; i < boardFileList.size(); i++){
			fd_file_name = boardFileList.get(i).getOrignlFileNm();
			fd_file_path = boardFileList.get(i).getFileStreCours();
		}

		response.setContentType("application/octet-stream; charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fd_file_name, "UTF-8")  + "\"");
		response.setHeader("Content-Transfer-Encoding", "binary");
		FileCopyUtils.copy(new FileInputStream(new File(fd_file_path,fd_file_name)), response.getOutputStream());    

	}

	//게시판 등록, 수정 html,script 생성
	public HashMap<String, String> BoardHtmlCreate(List labelPropGbnList, String mode, Map detailMap, List categoryList, HttpServletRequest request ,String bbsFileCnt, List fileList ) throws Exception{
		HttpSession ses = request.getSession();
		
		StringBuffer outHtml = new StringBuffer();
		StringBuffer outHtml2 = new StringBuffer();
		StringBuffer outFileHtml = new StringBuffer();
		StringBuffer outScript = new StringBuffer();
		StringBuffer outColSum = new StringBuffer();
		StringBuffer outScriptfn = new StringBuffer();
		StringBuffer outDetailDataSet = new StringBuffer();
		
		String ssId = "";
		String ssName = "";
		String ssEmail = "";
		String ssZip = "";
		String ssAddr1 = ""; 
		String ssAddr2 = "";
		String ssTel = "";
		String ssHp = "";
		String ssDeptNm = "";
		
		ssId = (String)ses.getAttribute("SesUserId");
		ssName = (String)ses.getAttribute("SesUserDeptNm");
		
//		String ssLevel = (String)ses.getAttribute("ss_level");
//		if( "7".equals(ssLevel) ){
//			ssId = (String)ses.getAttribute("ss_id");
//			ssName = (String)ses.getAttribute("ss_name");
//			ssEmail = (String)ses.getAttribute("ss_email");
//			ssZip = (String)ses.getAttribute("ss_zip");
//			ssAddr1 = (String)ses.getAttribute("ss_addr1");
//			ssAddr2 = (String)ses.getAttribute("ss_addr2");
//			ssTel = (String)ses.getAttribute("ss_tel");
//			ssHp = (String)ses.getAttribute("ss_hp");
//		}else if( "8".equals(ssLevel) ){
//			ssName = (String)ses.getAttribute("ss_name");
//			ssId = (String)ses.getAttribute("ss_dupkey");
//		}
		
		Map labelPropGbnMap;
		HashMap<String, String> resultMap = new HashMap<String, String>();
		String strCompYn = "";
		
		/*if( "C".equals(mode) ){*/
		outScript.append("function doBbsFReg() { \t");
		//outScript.append("document.getElementById('clobCont').value = CrossEditor.GetBodyValue(); \t ");
		if( "U".equals(mode) ){
			//수정일 경우 데이터 셋팅을 위한 Function
			outDetailDataSet.append("function doDetailDataSet(){ \t");
		}
		
		for(int nLoop=0; nLoop<labelPropGbnList.size(); nLoop++){
			labelPropGbnMap = (Map) labelPropGbnList.get(nLoop);
			if( "Y".equals(labelPropGbnMap.get("LABEL_COMP_YN")) ){
				strCompYn="*";
			}else{
				strCompYn="";
			}
			
			//수정 데이터
			if( "U".equals(mode) ){
				if( "16020200".equals(labelPropGbnMap.get("LABEL_PROP_GBN")) ){
					String strValue =  BoardValue(labelPropGbnMap, detailMap);
					resultMap.put("clobCont", strValue);
					
				}else if( "16020400".equals(labelPropGbnMap.get("LABEL_PROP_GBN")) ){
					String strValue =  BoardValue(labelPropGbnMap, detailMap);
					
					outDetailDataSet.append("var vTemp"+nLoop+" = '"+ strValue +"'; \t");
					outDetailDataSet.append("var vArray"+nLoop+" = vTemp"+nLoop+".split('|'); \t");
					outDetailDataSet.append("$('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"').val(vArray"+nLoop+"[0]); \t");
					outDetailDataSet.append("$('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"').val(vArray"+nLoop+"[1]); \t");
					outDetailDataSet.append("$('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"').val(vArray"+nLoop+"[2]); \t");
				}else if( "16020500".equals(labelPropGbnMap.get("LABEL_PROP_GBN")) ){
					
					String strValue =  BoardValue(labelPropGbnMap, detailMap);
					outDetailDataSet.append("var vTemp"+nLoop+" = '"+ strValue +"'; \t");
					outDetailDataSet.append("var vArray"+nLoop+" = vTemp"+nLoop+".split('-'); \t");
					outDetailDataSet.append("$('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"').val(vArray"+nLoop+"[0]); \t");
					outDetailDataSet.append("$('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"').val(vArray"+nLoop+"[1]); \t");
					outDetailDataSet.append("$('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"').val(vArray"+nLoop+"[2]); \t");
				}else if( "16020600".equals(labelPropGbnMap.get("LABEL_PROP_GBN")) ){
					
					
				}else if( "16020700".equals(labelPropGbnMap.get("LABEL_PROP_GBN")) ){
					String strValue =  BoardValue(labelPropGbnMap, detailMap);
					outDetailDataSet.append("$('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"').val('"+strValue+"'); \t");
					
				}else if( "16020800".equals(labelPropGbnMap.get("LABEL_PROP_GBN")) ){
					String strValue =  BoardValue(labelPropGbnMap, detailMap);
					outDetailDataSet.append("$(quot;input:radio[name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"']:radio[value='"+strValue+"']quot;).prop('checked',true); \t");
					
				}else if( "16020900".equals(labelPropGbnMap.get("LABEL_PROP_GBN")) ){
					String strValue =  BoardValue(labelPropGbnMap, detailMap);
					outDetailDataSet.append("$(quot;input:checkbox[name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"']:checkbox[value='"+strValue+"']quot;).prop('checked',true); \t");
					
				}
				else{
					String strValue =  BoardValue(labelPropGbnMap, detailMap);
					
					if( "subCont".equals(labelPropGbnMap.get("CONTENT_MAPPING_L")) ){
						resultMap.put((String)labelPropGbnMap.get("CONTENT_MAPPING_L"), strValue);
						outDetailDataSet.append("$('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"').val($('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"Temp').val()); \t");
					}else{
						outDetailDataSet.append("$('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"').val('"+strValue+"'); \t");
					}
				}
				
			}
			
			
			//Text Box
			if( "16020100".equals(labelPropGbnMap.get("LABEL_PROP_GBN")) ){
				
				if( ("C".equals(mode)||"R".equals(mode)) && "nameCont".equals( labelPropGbnMap.get("CONTENT_MAPPING_L")) ){
					if( ssName != "" ){
						outHtml2.append("<tr><th scope='row'><label for='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"'><span class='required'>"+strCompYn+"</span>"+labelPropGbnMap.get("LABEL_NAME")+"</label></th><td><input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"' value='"+ssName+"' class='w90'></td></tr>");
					}else{
						outHtml2.append("<tr><th scope='row'><label for='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"'><span class='required'>"+strCompYn+"</span>"+labelPropGbnMap.get("LABEL_NAME")+"</label></th><td><input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"' class='w90'></td></tr>");
					}
				}else if( ("C".equals(mode)||"R".equals(mode)) && "emailCont".equals( labelPropGbnMap.get("CONTENT_MAPPING_L")) ){
					if( ssEmail != "" ){
						outHtml2.append("<tr><th scope='row'><label for='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"'><span class='required'>"+strCompYn+"</span>"+labelPropGbnMap.get("LABEL_NAME")+"</label></th><td><input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"' value='"+ssEmail+"' class='w90'></td></tr>");
					}else{
						outHtml2.append("<tr><th scope='row'><label for='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"'><span class='required'>"+strCompYn+"</span>"+labelPropGbnMap.get("LABEL_NAME")+"</label></th><td><input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"' class='w90'></td></tr>");
					}
				}else{
					outHtml2.append("<tr><th scope='row'><label for='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"'><span class='required'>"+strCompYn+"</span>"+labelPropGbnMap.get("LABEL_NAME")+"</label></th><td><input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"' class='w90'></td></tr>");
				}
				//outHtml2.append("<tr><th scope='row'><label for='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"'><span class='required'>"+strCompYn+"</span>"+labelPropGbnMap.get("LABEL_NAME")+"</label></th><td><input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"' class='w90'></td></tr>");
				//필수값 체크
				if( "Y".equals(labelPropGbnMap.get("LABEL_COMP_YN")) ){
					outScript.append("if( cm_is_empty($('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"').val()) ){ \t alert('"+labelPropGbnMap.get("LABEL_NAME")+"을(를) 입력해주세요.'); \t $('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"').focus; \t return; \t } \t ");
				}
				
			//Text Area	
			}else if( "16020200".equals(labelPropGbnMap.get("LABEL_PROP_GBN")) ){
//				outScript.append("document.getElementById('clobCont').value = CrossEditor.GetBodyValue(); \t ");
				outScript.append("document.getElementById('"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"').value = CrossEditor.GetBodyValue(); \t ");
				/*글내용

				글 내용에 표와 이미지를 올리시면 장애인차별금지법에 위반이 됩니다. 표와 이미지는 첨부로 올리십시오.

				게시글 올리기 전, 민원인의 상세정보(주민번호, 상세주소, 핸드폰번호, 차량번호 등)이 포함되어 있는지 다시 확인 하시기 바랍니다.
				본문내용이나 첨부파일에 민원인의 상세정보를 숨김처리(***)하여 개인정보가 노출되지 않도록 하시기 바랍니다.*/
				
				
				
				
				outHtml2.append("<tr><th><label for='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"'><span class='required'>"+strCompYn+"</span>"+labelPropGbnMap.get("LABEL_NAME")+"</label></th><td>");
//				outHtml2.append("<font color=blue>글 내용에 표와 이미지를 올리시면 장애인차별금지법에 위반이 됩니다. 표와 이미지는 첨부로 올리십시오.</font><br/><br/>");
				
				outHtml2.append("<span style='text-align:left;'><font color=blue>글 내용에 표와 이미지를 올리시면 장애인차별금지법에 위반이 됩니다. 표와 이미지는 첨부로 올리십시오.</font><br/><br/><font color=\"red\">게시글 올리기 전, 민원인의 상세정보(주민번호, 상세주소, 핸드폰번호, 차량번호 등)이 포함되어 있는지 다시 확인 하시기 바랍니다.<br/>본문내용이나 첨부파일에 민원인의 상세정보를 숨김처리(***)하여 개인정보가 노출되지 않도록 하시기 바랍니다.</font></span><br/><br/>");
				
				if(labelPropGbnMap.get("CONTENT_MAPPING_L").equals("clobCont")){
					/*outHtml2.append("<form:textarea path='clobCont' rows='30' title='clob 컨텐츠'/>");*/
					outHtml2.append("<script type='text/javascript' language='javascript'> \t");
					outHtml2.append("var CrossEditor = new NamoSE('"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"'); \t");		
					outHtml2.append("CrossEditor.params.Width = '100%'; \t");	
					outHtml2.append("CrossEditor.params.UserLang = 'auto'; \t");		
					outHtml2.append("CrossEditor.params.FullScreen = false; \t");		
					outHtml2.append("CrossEditor.EditorStart(); \t");		
					outHtml2.append("function OnInitCompleted(e){ \t");
					outHtml2.append("CrossEditor.SetBodyValue(document.getElementById('clobCont').value); \t");		
					outHtml2.append("} \t");		
					outHtml2.append("</script>");
				}

				outHtml2.append("</td></tr>");
				//필수값 체크
				if( "Y".equals(labelPropGbnMap.get("LABEL_COMP_YN")) ){
					outScript.append("if( cm_is_empty($('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"').val()) ){ \t alert('"+labelPropGbnMap.get("LABEL_NAME")+"을(를) 입력해주세요.'); \t $('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"').focus; \t return; \t } \t ");
				}
				
			//Link
			}else if( "16020300".equals(labelPropGbnMap.get("LABEL_PROP_GBN")) ){
				outHtml2.append("<tr><th scope='row'><label for='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"'><span class='required'>"+strCompYn+"</span>"+labelPropGbnMap.get("LABEL_NAME")+"</label></th><td><input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"' class='w90'></td></tr>");
				//필수값 체크  cript.append("if( cm_is_empty($('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"').val()) ){ \t alert('"+labelPropGbnMap.get("LABEL_NAME")+"을(를) 입력해주세요.'); \t $('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"').focus; \t return; \t } \t ");
				
			//Address
			}else if( "16020400".equals(labelPropGbnMap.get("LABEL_PROP_GBN")) ){
				outHtml.append("<input type='hidden' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"' name ='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"'>");
				
				if( ("C".equals(mode)||"R".equals(mode)) ){
					if(ssZip !=""){
						outHtml2.append("<tr><th scope='row'><label for='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"'><span class='required'>"+strCompYn+"</span> "+labelPropGbnMap.get("LABEL_NAME")+"</label></th><td class='addr'><div><input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"' value='"+ssZip+"' size='10' readonly='true' /><a href='javascript:openAddrSearch();' class='btn-inner' title='새창'>우편번호검색</a></div><div class='mgt5'><input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"' value='"+ssAddr1+"' class='w80' readonly='true' /></div><div class='mgt5'><input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"' value='"+ssAddr2+"' class='w80'></div></td></tr>");
					}else{
						outHtml2.append("<tr><th scope='row'><label for='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"'><span class='required'>"+strCompYn+"</span> "+labelPropGbnMap.get("LABEL_NAME")+"</label></th><td class='addr'><div><input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"' size='6' readonly='true' /><a href='javascript:openAddrSearch();' class='btn-inner' title='새창'>우편번호검색</a></div><div class='mgt5'><input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"' class='w80' readonly='true' /></div><div class='mgt5'><input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"' class='w80'></div></td></tr>");
					}
				}else if( "U".equals(mode) ){
					outHtml2.append("<tr><th scope='row'><label for='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"'><span class='required'>"+strCompYn+"</span> "+labelPropGbnMap.get("LABEL_NAME")+"</label></th><td class='addr'><div><input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"' size='6' readonly='true' /><a href='javascript:openAddrSearch();' class='btn-inner' title='새창'>우편번호검색</a></div><div class='mgt5'><input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"' class='w80' readonly='true' /></div><div class='mgt5'><input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"' class='w80'></div></td></tr>");
				}
				
				//필수값 체크
				if( "Y".equals(labelPropGbnMap.get("LABEL_COMP_YN")) ){
					outScript.append("if( cm_is_empty($('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"').val()) ){ \t alert('"+labelPropGbnMap.get("LABEL_NAME")+"을(를) 입력해주세요.'); \t  $('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"').focus; \t return; \t } \t ");
					outScript.append("if( cm_is_empty($('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"').val()) ){ \t alert('"+labelPropGbnMap.get("LABEL_NAME")+"을(를) 입력해주세요.'); \t  $('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"').focus; \t return; \t } \t ");
					outScript.append("if( cm_is_empty($('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"').val()) ){ \t alert('"+labelPropGbnMap.get("LABEL_NAME")+"을(를) 입력해주세요.'); \t  $('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"').focus; \t return; \t } \t ");
					
				}
								
				outColSum.append("document.BoardAdminVo."+labelPropGbnMap.get("CONTENT_MAPPING_L")+".value = $('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"').val() +quot;|quot;+ $('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"').val() +quot;|quot;+ $('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"').val(); \t");
//				outScriptfn.append("function testFun() { \t alert('1111'); \t } \t");
				outScriptfn.append("function openAddrSearch() { \t var from = document.form; \t doJusoPop(from); \t } \t function setAddrValue(addr1, addr2, zip) { \t $('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"').val(zip); \t $('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"').val(addr1); \t $('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"').val(addr2); \t $('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"').focus(); \t } \t");
				
			//Phone
			}else if( "16020500".equals(labelPropGbnMap.get("LABEL_PROP_GBN")) ){
				outHtml.append("<input type='hidden' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"' name ='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"'>");
				if( ("C".equals(mode)||"R".equals(mode)) && "telCont".equals( labelPropGbnMap.get("CONTENT_MAPPING_L")) ){
					if( ssTel != "" ){
						String[] arrayTel = ssTel.split("-");
						outHtml2.append("<tr><th scope='row'><label for='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"'><span class='required'>"+strCompYn+"</span> "+labelPropGbnMap.get("LABEL_NAME")+"</label></th><td><input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"' value='"+arrayTel[0]+"' onkeypress='return showKeyCode(event)' maxlength='4' size='5' title='전화 앞번호'> -<input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"' value='"+arrayTel[1]+"' onkeypress='return showKeyCode(event)' maxlength='4' size='5' title='전화 중간번호'> -<input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"' onkeypress='return showKeyCode(event)' maxlength='4' value='"+arrayTel[2]+"' size='5' title='전화 뒷번호'></td></tr>");
					}else{
						outHtml2.append("<tr><th scope='row'><label for='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"'><span class='required'>"+strCompYn+"</span> "+labelPropGbnMap.get("LABEL_NAME")+"</label></th><td><input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"' size='5' onkeypress='return showKeyCode(event)' maxlength='4' title='전화 앞번호'> -<input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"' size='5' onkeypress='return showKeyCode(event)' maxlength='4' title='전화 중간번호'> -<input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"' size='5' onkeypress='return showKeyCode(event)' maxlength='4' title='전화 뒷번호'></td></tr>");
					}
				}else if( ("C".equals(mode)||"R".equals(mode)) && "phoneCont".equals( labelPropGbnMap.get("CONTENT_MAPPING_L")) ){
					if( ssHp != "" ){
						String[] arrayHp = ssHp.split("-");
						outHtml2.append("<tr><th scope='row'><label for='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"'><span class='required'>"+strCompYn+"</span> "+labelPropGbnMap.get("LABEL_NAME")+"</label></th><td><input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"' value='"+arrayHp[0]+"' onkeypress='return showKeyCode(event)' maxlength='4' size='5' title='전화 앞번호'> -<input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"' value='"+arrayHp[1]+"' size='5' onkeypress='return showKeyCode(event)' maxlength='4' title='전화 중간번호'> -<input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"' value='"+arrayHp[2]+"' size='5' onkeypress='return showKeyCode(event)' maxlength='4' title='전화 뒷번호'></td></tr>");
					}else{
						outHtml2.append("<tr><th scope='row'><label for='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"'><span class='required'>"+strCompYn+"</span> "+labelPropGbnMap.get("LABEL_NAME")+"</label></th><td><input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"' size='5' onkeypress='return showKeyCode(event)' maxlength='4' title='전화 앞번호'> -<input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"' size='5' onkeypress='return showKeyCode(event)' maxlength='4' title='전화 중간번호'> -<input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"' size='5' onkeypress='return showKeyCode(event)' maxlength='4' title='전화 뒷번호'></td></tr>");
					}
				}else{
					outHtml2.append("<tr><th scope='row'><label for='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"'><span class='required'>"+strCompYn+"</span> "+labelPropGbnMap.get("LABEL_NAME")+"</label></th><td><input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"' size='5' onkeypress='return showKeyCode(event)' maxlength='4' title='전화 앞번호'> -<input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"' size='5' onkeypress='return showKeyCode(event)' maxlength='4' title='전화 중간번호'> -<input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"' size='5' onkeypress='return showKeyCode(event)' maxlength='4' title='전화 뒷번호'></td></tr>");
				}
				/*if( ("C".equals(mode)||"R".equals(mode)) && "telCont".equals( labelPropGbnMap.get("CONTENT_MAPPING_L")) ){
					if( ssTel != "" ){
						String[] arrayTel = ssTel.split("-");
						outHtml2.append("<tr><th scope='row'><label for='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"'><span class='required'>"+strCompYn+"</span> "+labelPropGbnMap.get("LABEL_NAME")+"</label></th><td><input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"' value='"+arrayTel[0]+"' onkeypress='return showKeyCode(event)' maxlength='4' size='5' title='전화 앞번호'> -<input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"' value='"+arrayTel[1]+"' onkeypress='return showKeyCode(event)' maxlength='4' size='5' title='전화 중간번호'> -<input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"' value='"+arrayTel[2]+"' onkeypress='return showKeyCode(event)' maxlength='4' size='5' title='전화 뒷번호'></td></tr>");
					}else{
						outHtml2.append("<tr><th scope='row'><label for='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"'><span class='required'>"+strCompYn+"</span> "+labelPropGbnMap.get("LABEL_NAME")+"</label></th><td><input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"' onkeypress='return showKeyCode(event)' maxlength='4' size='5' title='전화 앞번호'> -<input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"' onkeypress='return showKeyCode(event)' maxlength='4' size='5' title='전화 중간번호'> -<input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"' onkeypress='return showKeyCode(event)' maxlength='4' size='5' title='전화 뒷번호'></td></tr>");
					}
				}else if( ("C".equals(mode)||"R".equals(mode)) && "phoneCont".equals( labelPropGbnMap.get("CONTENT_MAPPING_L")) ){
					if( ssHp != "" ){
						String[] arrayHp = ssTel.split("-");
						outHtml2.append("<tr><th scope='row'><label for='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"'><span class='required'>"+strCompYn+"</span> "+labelPropGbnMap.get("LABEL_NAME")+"</label></th><td><input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"' value='"+arrayHp[0]+"' onkeypress='return showKeyCode(event)' maxlength='4' size='5' title='전화 앞번호'> -<input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"' value='"+arrayHp[1]+"' onkeypress='return showKeyCode(event)' maxlength='4' size='5' title='전화 중간번호'> -<input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"' value='"+arrayHp[2]+"' onkeypress='return showKeyCode(event)' maxlength='4' size='5' title='전화 뒷번호'></td></tr>");
					}else{
						outHtml2.append("<tr><th scope='row'><label for='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"'><span class='required'>"+strCompYn+"</span> "+labelPropGbnMap.get("LABEL_NAME")+"</label></th><td><input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"' onkeypress='return showKeyCode(event)' maxlength='4' size='5' title='전화 앞번호'> -<input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"' onkeypress='return showKeyCode(event)' maxlength='4' size='5' title='전화 중간번호'> -<input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"' onkeypress='return showKeyCode(event)' maxlength='4' size='5' title='전화 뒷번호'></td></tr>");
					}
				}else{
					outHtml2.append("<tr><th scope='row'><label for='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"'><span class='required'>"+strCompYn+"</span> "+labelPropGbnMap.get("LABEL_NAME")+"</label></th><td><input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"' onkeypress='return showKeyCode(event)' maxlength='4' size='5' title='전화 앞번호'> -<input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"' onkeypress='return showKeyCode(event)' maxlength='4' size='5' title='전화 중간번호'> -<input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"' onkeypress='return showKeyCode(event)' maxlength='4' size='5' title='전화 뒷번호'></td></tr>");
				}*/
				
				//필수값 체크
				if( "Y".equals(labelPropGbnMap.get("LABEL_COMP_YN")) ){
					outScript.append("if( cm_is_empty($('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"').val()) ){ \t alert('"+labelPropGbnMap.get("LABEL_NAME")+"을(를) 입력해주세요.'); \t $('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"').focus; \t  return; \t } \t ");
					outScript.append("if( cm_is_empty($('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"').val()) ){ \t alert('"+labelPropGbnMap.get("LABEL_NAME")+"을(를) 입력해주세요.'); \t $('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"').focus; \t  return; \t } \t ");
					outScript.append("if( cm_is_empty($('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"').val()) ){ \t alert('"+labelPropGbnMap.get("LABEL_NAME")+"을(를) 입력해주세요.'); \t $('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"').focus; \t  return; \t } \t ");
				}
								
				outColSum.append("document.BoardAdminVo."+labelPropGbnMap.get("CONTENT_MAPPING_L")+".value = $('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"').val() +quot;-quot;+ $('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"').val() +quot;-quot;+ $('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"').val(); \t");
			//File
			}else if( "16020600".equals(labelPropGbnMap.get("LABEL_PROP_GBN")) ){
				if( ( "C".equals(mode)||"R".equals(mode)) ){
					outFileHtml.append("<tr><th scope='row'><label for=''>"+labelPropGbnMap.get("LABEL_NAME")+"</label></th><td>");
					for(int fLoop=0; fLoop<Integer.parseInt(bbsFileCnt); fLoop++){
						outFileHtml.append("<input type='file' name='fileTagId' class='w90'>");
					}
					outFileHtml.append("</td></tr>");
					if( "Y".equals(labelPropGbnMap.get("LABEL_COMP_YN")) ){
						outScript.append("if( cm_is_empty($('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"').val()) ){ \t alert('"+labelPropGbnMap.get("LABEL_NAME")+"을(를) 입력해주세요.'); \t $('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"').focus; \t return; \t } \t ");
					}
				}else if( "U".equals(mode)){
					outFileHtml.append("<tr><th scope='row'><label for=''>"+labelPropGbnMap.get("LABEL_NAME")+"</label></th><td>");
					BoardAdminVo bbsFileVo = null;
					int tLoop=0;
					if( fileList.size() !=0 || fileList != null ){
						for(int fLoop=0; fLoop<fileList.size(); fLoop++){
							bbsFileVo = (BoardAdminVo) fileList.get(fLoop);
							outFileHtml.append("<p class='uploaded_file' id='fileRmv"+bbsFileVo.getFileNo()+"'><a href='/site/{strDomain}/ex/bbs/fileDownload.do?bcIdx="+bbsFileVo.getBcIdx()+"&cbIdx="+bbsFileVo.getCbIdx()+"&fileNo="+bbsFileVo.getFileNo()+"' class='file'>"+bbsFileVo.getOrignlFileNm()+"["+getComma(bbsFileVo.getFileSize())+"KB]</a><a href='javascript:doBbsFileRmvAx("+bbsFileVo.getCbIdx()+","+bbsFileVo.getBcIdx()+","+bbsFileVo.getFileNo()+")' class='delete'>삭제</a></p>");
							outFileHtml.append("<span id='upFile"+bbsFileVo.getFileNo()+"'></span>");
							tLoop++;
						}
					}
					for(tLoop=tLoop; tLoop<Integer.parseInt(bbsFileCnt); tLoop++){
						outFileHtml.append("<input type='file' name='fileTagId' class='w90'>");
					}
					outFileHtml.append("</tr>");
					
				}
				//outScriptfn.append("");
			//Select Box
			}else if( "16020700".equals(labelPropGbnMap.get("LABEL_PROP_GBN")) ){
				outHtml2.append("<tr><th scope='row'><label for='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"'><span class='required'>*</span> "+labelPropGbnMap.get("LABEL_NAME")+"</label></th>");
				outHtml2.append("<td><select name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"'>");
				Map categoryMap;
				if( "cateCont".equals(labelPropGbnMap.get("CONTENT_MAPPING_L")) && categoryList != null ){
					for(int cLoop=0; cLoop<categoryList.size(); cLoop++){
						categoryMap = (Map) categoryList.get(cLoop);
						outHtml2.append("<option value="+categoryMap.get("CATEGORY_CODE")+">"+categoryMap.get("CATEGORY_NAME")+"</option>");
					}
				}else{
					BoardAdminVo item = new BoardAdminVo();
					item.setItemCode((String)labelPropGbnMap.get("ITEM_CODE"));
					List itemList = boardAdminSvc.itemList(item);
					for(int iLoop=0; iLoop<itemList.size(); iLoop++){
						categoryMap = (Map)itemList.get(iLoop);
						outHtml2.append("<option value="+categoryMap.get("CODE")+">"+categoryMap.get("CODE_NAME")+"</option>");
					}
				}
				outHtml2.append("</select></td>");
			//Radio Box
			}else if( "16020800".equals(labelPropGbnMap.get("LABEL_PROP_GBN")) ){
				BoardAdminVo item = new BoardAdminVo();
				Map itemMap;
				item.setItemCode((String)labelPropGbnMap.get("ITEM_CODE"));
				List itemList = boardAdminSvc.itemList(item);
				outHtml2.append("<tr><th scope='row'><label for="+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"'><span class='required'>"+strCompYn+"</span>"+labelPropGbnMap.get("LABEL_NAME")+"</th><td>");
				for(int iLoop=0; iLoop<itemList.size(); iLoop++){
					itemMap = (Map)itemList.get(iLoop);
					outHtml2.append("<input type='radio' id="+itemMap.get("CODE")+"' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"' value='"+itemMap.get("CODE")+"' />"+itemMap.get("CODE_NAME"));
				}
				outHtml2.append("</td></tr>");
				
				//필수값 체크
				/*if( "Y".equals(labelPropGbnMap.get("LABEL_COMP_YN")) ){
					outScript.append("if( cm_is_empty($('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"').val()) ){ \t alert('"+labelPropGbnMap.get("LABEL_NAME")+"을(를) 입력해주세요.'); \t $('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"').focus; \t return; \t } \t ");
				}*/
			//CheckBox
			}else if( "16020900".equals(labelPropGbnMap.get("LABEL_PROP_GBN")) ){
				BoardAdminVo item = new BoardAdminVo();
				Map itemMap;
				item.setItemCode((String)labelPropGbnMap.get("ITEM_CODE"));
				List itemList = boardAdminSvc.itemList(item);
				outHtml2.append("<tr><th scope='row'><label for="+labelPropGbnMap.get("CONTENT_MAPPING_L")+"'><span class='required'>"+strCompYn+"</span>"+labelPropGbnMap.get("LABEL_NAME")+"</th><td>");
				for(int iLoop=0; iLoop<itemList.size(); iLoop++){
					itemMap = (Map)itemList.get(iLoop);
					outHtml2.append("<input type='checkbox' id='"+itemMap.get("CODE")+"' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"' value='"+itemMap.get("CODE")+"' />"+itemMap.get("CODE_NAME"));
					
				}
				/*nLoop
				var vChkVal
				$("input[name=box]:checked").each(function() {

					var test = $(this).val();

					console.log(test);

				});*/

				
				outHtml2.append("</td></tr>");
			//Caption(자막)
			}else if( "16021000".equals(labelPropGbnMap.get("LABEL_PROP_GBN")) ){
				outHtml2.append("<tr><th scope='row'><label for='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"'><span class='required'>"+strCompYn+"</span>"+labelPropGbnMap.get("LABEL_NAME")+"</label></th><td><textarea rows='10' cols='50' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"' class='w90 board_input'></textarea></td></tr>");
				//필수값 체크
				if( "Y".equals(labelPropGbnMap.get("LABEL_COMP_YN")) ){
					outScript.append("if( cm_is_empty($('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"').val()) ){ \t alert('"+labelPropGbnMap.get("LABEL_NAME")+"을(를) 입력해주세요.'); \t $('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"').focus; \t return; \t } \t ");
				}
			}
			
		}
		
		if( "U".equals(mode) ){
			outDetailDataSet.append(" } \t");
		}
		//파일 맨밑으로
		outHtml2.append(outFileHtml.toString());
		outHtml.append(outHtml2.toString());
		outScript.append(outColSum.toString());
		//공지 체크여부
		outScript.append("doNotiYnChk(); \t ");

		
		outScript.append("document.BoardAdminVo.submit(); \t} \t");
		outScript.append(outScriptfn.toString());
		outScript.append(outDetailDataSet.toString());
		
		resultMap.put("outHtml", outHtml.toString()); 
		resultMap.put("outScript", outScript.toString()); 
		
		return resultMap;
	}
	
	
	public String BoardValue(Map mappingMap, Map detailMap){
		String strValue = "";
		String strMappingKey = (String)mappingMap.get( "CONTENT_MAPPING_L" );
		
		/*System.out.println("strMappingKey ==========>> : " + strMappingKey);*/
		
		Iterator iter = detailMap.keySet().iterator();
		 while( iter.hasNext() ) {
			 String key = (String)iter.next();
			 if( strMappingKey.equals(key) ){
				 strValue = (String)detailMap.get( key );
			 }
		 }
		return strValue;
	}
	
//	/** 이미지 호출 및 다운로드 */
//	@ResponseBody
//	@RequestMapping("/site/{strDomain}/ex/bbs/fileDownload.do")
//	public void fileDownload(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		CmmFilesCtr cfc = new CmmFilesCtr(); 
//		String bcIdx=request.getParameter("bcIdx") != null ? request.getParameter("bcIdx").trim() : "";
//		String cbIdx=request.getParameter("cbIdx") != null ? request.getParameter("cbIdx").trim() : "";
//		String fileNo=request.getParameter("fileNo") != null ? request.getParameter("fileNo").trim() : "";
//		String mcIdx=request.getParameter("mcIdx") != null ? request.getParameter("mcIdx").trim() : "";
//		
//		Map param = new HashMap<String, String>();
//		
//		param.put("bcIdx", bcIdx);
//		param.put("cbIdx", cbIdx);
//		param.put("fileNo", fileNo);
//		param.put("mcIdx", mcIdx);
//		
//		Map fileDownMap = boardAdminSvc.boardFileDown(param);
//		
//		if( fileDownMap == null ){
//			throw new Exception("Download File is null");
//		}
//		
//		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
//		String downFileName = rootPath + (String)fileDownMap.get("FILE_STRE_COURS") + (String)fileDownMap.get("STRE_FILE_NM");
//		/*response.setContentType("application/octet-stream; charset=UTF-8");
//		response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode((String)fileDownMap.get("STRE_FILE_NM"), "UTF-8")  + "\"");
//		response.setHeader("Content-Transfer-Encoding", "binary");
//		FileCopyUtils.copy(new FileInputStream(new File(rootPath + (String)fileDownMap.get("FILE_STRE_COURS"),(String)fileDownMap.get("STRE_FILE_NM"))), response.getOutputStream());*/    
//
//		
//		File file = new File(EgovWebUtil.filePathBlackList(downFileName));
//		int fSize = (int) file.length();
//		
//		if (fSize > 0) {
//			BufferedInputStream fin = null;
//
//			try {
//	
//				fin = new BufferedInputStream(new FileInputStream(file));
//				String mimetype = "application/x-msdownload";
//	 
//				response.setBufferSize(fSize);
//				response.setContentType(mimetype);
//				cfc.setDisposition((String)fileDownMap.get("ORIGNL_FILE_NM"), request, response);
//				response.setContentLength(fSize);
//				
//				FileCopyUtils.copy(fin, response.getOutputStream());
//				fin.close();
//			}catch(Exception e) {
//				throw e;
//			}finally {
//				if (fin != null) {
//					try { fin.close(); } catch (Exception e) { }
//				}
//			}
//			response.getOutputStream().flush();
//			response.getOutputStream().close();
//			
//		}
//		
//		
//	}
	
	public String getComma(String strVal) {
        DecimalFormat formatter = new DecimalFormat("#,###,###,##0");
        return formatter.format(Long.parseLong(strVal));
    }
	
	/**
	 * 통합게시물 민원삭제 팝업 화면
	 * @param request
	 * @param boardAdminVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/boffice_nodeco/ex/bbs/boardAdmin/MwRmvPop.do")
	public String mwRmvPop(HttpServletRequest request, @ModelAttribute("BoardAdminVo") BoardAdminVo boardAdminVo
							, ModelMap model) throws Exception {
		//삭제 사유 리스트
		List delRsnList =  boardAdminSvc.retrieveContentDelRsn();
		model.addAttribute("boardAdminVo", boardAdminVo);
		model.addAttribute("delRsnList", delRsnList);
		return "injeinc/boffice/ex/bbs/boardAdmin/mwRmv_pop";		
	}
	
	/**
	 * 통합게시물 민원삭제
	 * @param request
	 * @param boardAdminVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/boffice/ex/bbs/boardAdmin/Mw_RmvAx.do")
	public void mwRmvAx(HttpServletRequest request ,HttpServletResponse response) throws Exception {
		
		Map param = new HashMap<String, String>();
		HttpSession ses = request.getSession();
		
		param.put("bcIdx", request.getParameter("bcIdx"));
		param.put("cbIdx", request.getParameter("cbIdx"));
		param.put("delRsnCd", request.getParameter("delRsnCd"));
		param.put("adminId", (String)ses.getAttribute("SesUserId"));
		
		boardAdminSvc.removeMw(param);
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", "ok");
		jsonView.render(jsonMap, request, response);
	}
	
	/**
	 * 통합게시물 민원복구
	 * @param request
	 * @param boardAdminVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/boffice/ex/bbs/boardAdmin/Mw_RcvAx.do")
	public void mwRcvAx(HttpServletRequest request ,HttpServletResponse response) throws Exception {
		
		Map param = new HashMap<String, String>();
		HttpSession ses = request.getSession();
		
		param.put("bcIdx", request.getParameter("bcIdx"));
		param.put("cbIdx", request.getParameter("cbIdx"));
		param.put("adminId", (String)ses.getAttribute("SesUserId"));
		
		boardAdminSvc.recoveryMw(param);
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", "ok");
		jsonView.render(jsonMap, request, response);
	}
	
	/**
	 * 통합게시물  이관
	 * @param request
	 * @param boardAdminVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/boffice/ex/bbs/boardAdmin/TransAx.do")
public void transAx(HttpServletRequest request ,HttpServletResponse response, @ModelAttribute("BoardAdminVo") BoardAdminVo boardAdminVo) throws Exception {
		
		
		Map param = new HashMap<String, String>();
		HttpSession ses = request.getSession();
		
		int bcIdx = EgovStringUtil.zeroConvert(request.getParameter("bcIdx"));
		int cbIdx = EgovStringUtil.zeroConvert(request.getParameter("cbIdx"));
		int transCbIdx = EgovStringUtil.zeroConvert(request.getParameter("transCbIdx"));
		String adminId = (String)ses.getAttribute("SesUserId");
		
		/*BoardAdminVo boardAdminVo = null;*/
		boardAdminVo.setCbIdx(transCbIdx);
		int transBcIdx = boardAdminSvc.registBcIdxNo(boardAdminVo);
		
		param.put("bcIdx", bcIdx);
		param.put("cbIdx", cbIdx);
		param.put("transCbIdx", transCbIdx);
		param.put("adminId", adminId);
		param.put("transBcIdx", transBcIdx);
		
		boardAdminSvc.registTrans(param);
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", "ok");
		jsonView.render(jsonMap, request, response);
	}
	
	
	
//	@RequestMapping(value = "/site/{strDomain}/ex/bbs/File_RmvAx.do")
//	public void FileRmvAx(HttpServletRequest request ,HttpServletResponse response) {
//		
//		try {
//			Map param = new HashMap<String, String>();
//			
//			param.put("bcIdx", request.getParameter("bcIdx"));
//			param.put("cbIdx", request.getParameter("cbIdx"));
//			param.put("fileNo", request.getParameter("fileNo"));
//			
//			boardAdminSvc.boardRemoveFiles(param);
//			
//			HashMap<String, Object> jsonMap = new HashMap<String, Object>();
//			jsonMap.put("result", "ok");
//			jsonView.render(jsonMap, request, response);
//			
//		} catch(Exception e) {
//			e.getStackTrace();
//		}
//	}
	
}