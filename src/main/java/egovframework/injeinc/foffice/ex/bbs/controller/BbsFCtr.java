package egovframework.injeinc.foffice.ex.bbs.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileUploadException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.EgovMessageSource;
import egovframework.cmm.EgovResourceCloseHelper;
import egovframework.cmm.EgovWebUtil;
import egovframework.cmm.Message;
import egovframework.cmm.service.EgovProperties;
import egovframework.injeinc.boffice.ex.bbs.gBoardAdmin.service.GBoardAdminSvc;
import egovframework.injeinc.boffice.ex.board.service.BbsCommentSvc;
import egovframework.injeinc.boffice.ex.board.service.ContentFileSvc;
import egovframework.injeinc.boffice.ex.board.vo.BbsCommentVo;
import egovframework.injeinc.boffice.ex.board.vo.BbsContentVo;
import egovframework.injeinc.boffice.ex.board.vo.ContentFileVo;
import egovframework.injeinc.boffice.group.dept.service.GroupDeptSvc;
import egovframework.injeinc.boffice.sy.board.service.EzBbsTempletSvc;
import egovframework.injeinc.boffice.sy.board.vo.EzBbsTempletVo;
import egovframework.injeinc.boffice.sy.code.service.CmmCodeSvc;
import egovframework.injeinc.common.code.service.CodeSvc;
import egovframework.injeinc.common.files.controller.CmmFilesCtr;
import egovframework.injeinc.common.util.DESCrypto;
import egovframework.injeinc.common.util.EgovFormBasedUUID;
import egovframework.injeinc.common.util.EgovStringUtil;
import egovframework.injeinc.foffice.ex.bbs.service.BbsCommentFSvc;
import egovframework.injeinc.foffice.ex.bbs.service.BbsFSvc;
import egovframework.injeinc.foffice.ex.bbs.vo.BbsCommentFVo;
import egovframework.injeinc.foffice.ex.bbs.vo.BbsFVo;
import egovframework.injeinc.foffice.ex.dept.service.DeptFSvc;
import egovframework.injeinc.foffice.pledge.vo.PledgeFVo;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


/**
 * 공통로그인 모델 클래스
 * @author 공통서비스 개발팀 이동열
 */

@Controller
public class BbsFCtr extends CmmLogCtr{
	
	@Autowired
	private BbsFSvc bbsFSvc;
	
	@Autowired(required=true)
	private MappingJackson2JsonView jsonView;
	
	@Autowired(required=true)
	private CodeSvc codeSvc;
	
	@Resource(name = "DeptFSvc")
	private DeptFSvc deptFSvc;
	
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
    @Resource(name = "EzBbsTempletSvc")
	private EzBbsTempletSvc ezBbsTempletSvc;
    
	@Resource(name = "CmmCodeSvc")
	private CmmCodeSvc cmmCodeSvc;
    
	@Resource(name = "ContentFileSvc")
	private ContentFileSvc contentFileSvc;
	

	@Resource(name = "GroupDeptSvc")
	private GroupDeptSvc groupDeptSvc;
	
	@Resource(name = "GBoardAdminSvc")
	private GBoardAdminSvc gBoardAdminSvc;
	
	@Resource(name = "BbsCommentFSvc")
	private BbsCommentFSvc bbsCommentFSvc;
    /**
  	 * 게시판 리스트 화면
  	 * @param request
  	 * @param BbsFVo
  	 * @param model
  	 * @return
  	 * @throws Exception
  	 */
  	@SuppressWarnings("rawtypes")
	@RequestMapping(value= "/{strSitePath}/{strDomain}/ex/bbs/BoardCommon_List.do")
  	public void BoardCommonList(HttpServletRequest request,HttpServletResponse response, @ModelAttribute("BbsFVo") BbsFVo bbsFVo
  						   , ModelMap model, @PathVariable("strSitePath") String strSitePath, @PathVariable("strDomain") String strDomain) throws Exception {
  		
  		int cbIdx = EgovStringUtil.zeroConvert(request.getParameter("cbIdx"));
  		String flag = EgovStringUtil.nullConvert(request.getParameter("flag"));
  		bbsFVo.setCbIdx(cbIdx);
  		bbsFVo.setFlag(flag);
  		
  		//메인제외한 홈페이지일 경우
  		if( !"".equals(EgovStringUtil.nullConvert(request.getParameter("deptCode"))) ){
	  		String deptCode = EgovStringUtil.nullConvert(request.getParameter("deptCode"));
	  		bbsFVo.setDeptCode(deptCode);
  		}
  		
  		List boardCommonList = bbsFSvc.boardCommonList(bbsFVo);
  		
  		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
  		jsonMap.put("boardCommonList",boardCommonList);
  		jsonView.render(jsonMap, request, response);
  	}
  	
  	@SuppressWarnings("rawtypes")
	@RequestMapping(value= "/{strSitePath}/{strDomain}/ex/bbs/BoardCommonAll_List.do")
  	public void BoardCommonAllList(HttpServletRequest request,HttpServletResponse response, @ModelAttribute("BbsFVo") BbsFVo bbsFVo
  						   , ModelMap model, @PathVariable("strDomain") String strDomain) throws Exception {
  		
  		int cbIdx = EgovStringUtil.zeroConvert(request.getParameter("cbIdx"));
  		bbsFVo.setCbIdx(cbIdx);
  		List boardCommonAllList = bbsFSvc.boardCommonAllList(bbsFVo);
  		
  		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
  		jsonMap.put("boardCommonAllList",boardCommonAllList);
  		jsonView.render(jsonMap, request, response);
  	}
    
    /**
	 * 게시판 리스트 화면
	 * @param request
	 * @param BbsFVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	@RequestMapping(value= "/{strSitePath}/{strDomain}/ex/bbs/List.do")
	public String BoardList(HttpServletRequest request, @ModelAttribute("BbsFVo") BbsFVo bbsFVo
			    		   , RedirectAttributes redirectAttributes
						   , ModelMap model, @PathVariable("strDomain") String strDomain) throws Exception {
	
	
		int isMobile = 0;
		String agent = request.getHeader("USER-AGENT");
		String[] mobileos = {"iPhone","iPod","Android","BlackBerry","Windows CE","Nokia","Webos","Opera Mini","SonyEricsson","Opera Mobi","IEMobile"};
		int j = -1;
		
		for(int i=0 ; i<mobileos.length ; i++) {
			j=agent.indexOf(mobileos[i]);
			if(j > -1 ) {
				// 모바일로 접근했을 때
				isMobile = 1;
				break;
			}
		}
		
		String ssId = "";
		HttpSession ses = request.getSession();
		ssId = (String)ses.getAttribute("ss_id");
		
		BbsFVo tempVo = bbsFVo;
		String strMsg = "";
		String returnUrl = "";
		try {
			
			int cbIdx = EgovStringUtil.zeroConvert(request.getParameter("cbIdx"));
			int bcIdx = EgovStringUtil.zeroConvert(request.getParameter("bcIdx"));
			String detailType = request.getParameter("detailType");
			//공지,공고/공시 서브도메인 CODE_NAME
			//String deptCode = EgovStringUtil.nullConvert(request.getParameter("deptCode"));
			String cateTypeCd = bbsFVo.getCateTypeCd();
			String tgtTypeCd = bbsFVo.getTgtTypeCd();            // 2020.07.31 이솔이 검색조건 추가
			String searchKey = bbsFVo.getSearchKey();
			String searchDateType = bbsFVo.getSearchDateType();
			String searchDateFrom = bbsFVo.getSearchDateFrom();
			String searchDateTo = bbsFVo.getSearchDateTo();
			String searchType = bbsFVo.getSearchType();          // 2020.08.06 이솔이 검색조건 이름 수정
			String searchTarget = bbsFVo.getSearchTarget();      // 2020.08.06 이솔이 검색조건 이름 수정
			String searchArea = bbsFVo.getSearchArea();          // 2020.08.06 이솔이 검색조건 이름 수정
			String searchYear = bbsFVo.getSearchYear();          // 2020.08.06 이솔이 검색조건 이름 수정
			String searchCenter = bbsFVo.getSearchCenter();      // 2020.08.10 이솔이 추가
			String searchRegDt = bbsFVo.getSearchRegDt();	     // 2020.08.12 안정환 추가
			String searchContent = bbsFVo.getSearchContent();	 // 2020.08.12 이솔이 추가
			String searchDepth = bbsFVo.getSearchDepth(); 		 // 2020.08.24 안정환 추가 
			String searchKind = bbsFVo.getSearchKind();
			String checkAll = bbsFVo.getCheckAll(); 			 //2020.09.02 전진형 추가
			String searchLaw = bbsFVo.getSearchLaw(); 			 //2020.09.02 이솔이 추가
			String regId = bbsFVo.getRegId(); 					 //2020.09.03 전진형 추가
			//String cdDepstep = bbsFVo.getCdDepstep();
			int pageIndex = bbsFVo.getPageIndex();
			Map authorMap = null;
			String author = "N";
			
			String abType = bbsFVo.getAbType();									//2020.09.14 안정환추가
			
			bbsFVo.setCbIdx(cbIdx);
			//게시판 정보 조회
			bbsFVo = bbsFSvc.boardInfo(bbsFVo);
			
			/*if(bbsFVo.getBbsTempletGbn().equals("bbs_calendar")){
				Calendar cal = Calendar.getInstance();

				if(searchDateType!=null){
					searchDateType = searchDateType.replaceAll("null","");					
				}else{
					searchDateType = "";
				}
				
				if(searchDateType.equals("")){
					String searchYear = Integer.toString(cal.get(Calendar.YEAR));
					String searchMonth = Integer.toString(cal.get(Calendar.MONTH)+1);
					
					searchDateFrom = searchYear+"-"+searchMonth+"-01";			
					searchDateTo = searchYear+"-"+searchMonth+"-31";
					searchDateType = "EXT2";
				}
			}*/
			
			
			if(bbsFVo == null) {
				return alert("", "잘못된 접근입니다.", model);
			}
			
			//리스트 권한 체크
			authorMap = BoardAuthorChk(bbsFVo.getListGbn() ,request, "list");
			if( Boolean.valueOf((Boolean)authorMap.get("flag")) ){
					bbsFVo.setDupcode((String)authorMap.get("dupcode"));
			}else{
				return alert((String)authorMap.get("returnUrl") ,(String)authorMap.get("msg"), model);
			}
			
			//글쓰기권한 체크
			authorMap = BoardAuthorChk(bbsFVo.getWriteGbn() ,request,"write");
			if( Boolean.valueOf((Boolean)authorMap.get("flag")) ){
				author = "Y";
			}
			
			bbsFVo.setCateTypeCd(cateTypeCd);
			bbsFVo.setTgtTypeCd(tgtTypeCd);        // 2020.07.31 이솔이 검색조건 추가
			bbsFVo.setSearchKey(searchKey);
			bbsFVo.setSearchDateType(searchDateType);
			bbsFVo.setSearchDateFrom(searchDateFrom);
			bbsFVo.setSearchDateTo(searchDateTo);
			bbsFVo.setSearchKey(searchKey);
			bbsFVo.setSearchType(searchType);      // 2020.08.06 이솔이 검색조건 이름 수정
			bbsFVo.setSearchTarget(searchTarget);  // 2020.08.06 이솔이 검색조건 이름 수정
			bbsFVo.setSearchArea(searchArea);      // 2020.08.06 이솔이 검색조건 이름 수정
			bbsFVo.setSearchYear(searchYear);      // 2020.08.06 이솔이 검색조건 이름 수정
			bbsFVo.setSearchCenter(searchCenter);  // 2020.08.10 이솔이 추가
			bbsFVo.setSearchRegDt(searchRegDt);    // 2020.08.12 안정환 추가
			bbsFVo.setSearchContent(searchContent);// 2020.08.13 이솔이 추가
			bbsFVo.setSearchDepth(searchDepth);    // 2020.08.24 안정환 추가 
			bbsFVo.setSearchKind(searchKind);      // 2020.08.26 전진형 추가 
			bbsFVo.setSearchLaw(searchLaw); 	   // 2020.09.02 이솔이 추가 
			bbsFVo.setRegId(regId);				   // 2020.09.03 전진형 추가 
			/*if( !"".equals(deptCode) ) { 
				bbsFVo.setDeptCodeName(bbsFSvc.deptCodeName(deptCode));
				bbsFVo.setDeptCode(deptCode);
			}*/
			bbsFVo.setAuthor(author);
			bbsFVo.setPageIndex(pageIndex);
			bbsFVo.setSsId(ssId);
			
			bbsFVo.setAbType(abType); 				//2020.09.14 안정환 추가 
			
			
			//웹 사용 여부 
			if(isMobile > 0) {
				bbsFVo.setWebUseYn("");
				bbsFVo.setMobileUseYn("Y");
				bbsFVo.setPageSize(4);
			}else{
				bbsFVo.setWebUseYn("Y");
				bbsFVo.setMobileUseYn("");
			}
			//게시판 구분 코드
			String strTempletcode = bbsFVo.getBbsTempletGbn();
			//게시판 라벨 리스트 조회
			List labelList = bbsFSvc.boardLabelList(bbsFVo);
			//게시판 컨텐츠  조회할 목록 조회
			List contMappList = bbsFSvc.boardContMappList(bbsFVo);
			//게시판 카테고리
			List categoryList = bbsFSvc.boardCategoryList(bbsFVo);
			//게시판 검색항목 리스트 조회
			List searchList = bbsFSvc.boardSearchList(bbsFVo);
			//게시판 컨텐츠 조회
			List contentList = null;
			// 게시판 컨텐츠 갯수
			int contentCnt = 0;
			
			EzBbsTempletVo ezBbsTempletVo= new EzBbsTempletVo();
			ezBbsTempletVo.setBbsTempletGbn(bbsFVo.getBbsTempletGbn());
			ezBbsTempletVo = ezBbsTempletSvc.selectEzBbsTemplet(ezBbsTempletVo);
			
			returnUrl = "/injeinc/foffice/ex/bbs/"+ezBbsTempletVo.getListCode();
			
			//로그인이 필요한 page 
			StringBuffer b = request.getRequestURL();
			String loginReferer = b.toString() +"?"+ request.getQueryString();
			
			if(ssId == null || ssId == ""){
				if(ezBbsTempletVo.getListCode().startsWith("bsrc_list_commu_propose") == true || ezBbsTempletVo.getListCode().startsWith("bsrc_list_jobTraining") == true
						|| ezBbsTempletVo.getListCode().startsWith("bsrc_list_proMpPositivity") == true ) {
					
					redirectAttributes.addAttribute("loginReferer", loginReferer);
					
					return "redirect:/site/DRP0000/login/Login.do";
				}else {
					returnUrl = "/injeinc/foffice/ex/bbs/"+ezBbsTempletVo.getListCode();
				}
			}
			contentCnt = bbsFSvc.boardcontentCnt(bbsFVo);
			
			//페이징
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(bbsFVo.getPageIndex());
			paginationInfo.setRecordCountPerPage(bbsFVo.getPageUnit());
			paginationInfo.setPageSize(bbsFVo.getPageSize());
			bbsFVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			bbsFVo.setLastIndex(paginationInfo.getLastRecordIndex());
			bbsFVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			paginationInfo.setTotalRecordCount(contentCnt);
			
			
			if ((bbsFVo.getCbIdx()==1178)) {
				bbsFVo.setCheckAll(checkAll);  // 2020.09. 02.전진형추가
				List allSearchList = bbsFSvc.boardAllSearchList(bbsFVo); //전체조회 20200831-전진형 통합검색 전체조회
				List allSearchCntList = bbsFSvc.boardAllSearchCntList(bbsFVo); //전체조회 20200901-전진형 통합검색 카운트조회
		
				if( allSearchCntList.size() > 0 ){
					Map allSearchCnt = (Map)allSearchCntList.get(0);
					paginationInfo.setTotalRecordCount(EgovStringUtil.zeroConvert(allSearchCnt.get("NOTICE_CNT").toString()));
				}
				
				model.addAttribute("allSearchList", allSearchList);
				model.addAttribute("allSearchCntList", allSearchCntList);
			}
			
			contentList = bbsFSvc.boardContentList(bbsFVo);
			
			BbsFVo bbsTvo = new BbsFVo();
			String[] arrBcIdx = new String[contentList.size()];
			for(int i=0;i<contentList.size();i++){
				Map detailMap = (Map)contentList.get(i);
				arrBcIdx[i] = detailMap.get("BC_IDX").toString();
			}
			
			bbsFVo.setSearchBcIdxArr(arrBcIdx);
			
			//게시글 파일 조회
			List fileList = bbsFSvc.boardFileList(bbsFVo);
			
			//게시글 파일 조회2 -- 리스트에서 다운가능
			//List fileBoardList = bbsFSvc.boardFileList2(bbsFVo);
			

			if(bcIdx>0){
				bbsFVo.setBcIdx(bcIdx);
			}else{
				if( contentList.size() > 0 ){
					Map detailMap = (Map)contentList.get(0);
					bbsFVo.setBcIdx(EgovStringUtil.zeroConvert(detailMap.get("BC_IDX").toString()));
				}
			}
			
			List detailList = null;
			detailList = bbsFSvc.boardContentDetail(bbsFVo);						
			
			model.addAttribute("detailList", detailList);
			
			model.addAttribute("deptSetp", request.getParameter("deptSetp"));
			model.addAttribute("paramVo", tempVo);
			model.addAttribute("bbsFVo", bbsFVo);
			model.addAttribute("labelList", labelList);
			model.addAttribute("contMappList", contMappList);
			model.addAttribute("contentList", contentList);
			model.addAttribute("contentCnt", contentCnt);
			model.addAttribute("paginationInfo", paginationInfo);
			model.addAttribute("categoryList", categoryList);
			model.addAttribute("searchList", searchList);
			model.addAttribute("strDomain", strDomain);
			model.addAttribute("fileList", fileList);
			//model.addAttribute("fileBoardList", fileBoardList);
			model.addAttribute("departList", groupDeptSvc.retrieveListCmsDepartGroup3());
			//전체조회 20200831-전진형 통합검색 전체조회, 통합검색 전체카운트조회
			
			
		} catch (Exception e) {
			strMsg = Message.getMessage("901.code");	//에러
			throw e;
		}
			
			
		return returnUrl;
		
	}
	
	//2020.07.31 이솔이 디지털 역기능 검색 > 공통코드 ajax 
	@RequestMapping("/site/{strDomain}/ex/bbs/CmmCodeAx.do")
	public void WiwIdListAx(HttpServletRequest request,HttpServletResponse response, String cmmCode,String cmmCode2th, String cmmCode3th , String cmmCode4th  
			   , @ModelAttribute("ContentFileVo") ContentFileVo contentFileVo,@ModelAttribute("BbsFVo") BbsFVo bbsFVo
			   , ModelMap model, @PathVariable("strDomain") String strDomain) throws Exception {
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("cmmCode", cmmCodeSvc.retrieveListCmmCode(cmmCode));
		jsonMap.put("cmmCode2th", cmmCodeSvc.retrieveListCmmCode(cmmCode2th)); // 2020.07.31 박종범 콩통코드추가
		jsonMap.put("cmmCode3th", cmmCodeSvc.retrieveListCmmCode(cmmCode3th)); // 2020.09.08 이솔이 추가
		jsonMap.put("cmmCode4th", cmmCodeSvc.retrieveListCmmCode(cmmCode4th)); // 2020.09.08 이솔이 추가
		jsonMap.put("result", true);
		
		jsonView.render(jsonMap, request, response);
	}
	
	
	
	/**
	 * 게시판 등록/수정 화면
	 * @param request
	 * @param BbsFVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	@RequestMapping(value= {"/{strSitePath}/{strDomain}/ex/bbs/Regist.do","/{strSitePath}/{strDomain}/foffice/user/MyIntegBoardRegist.do"
						  , "/{strSitePath}/{strDomain}/foffice/user/MyIntegMinwonBoardRegist.do"})
	public String BoardRegist(HttpServletRequest request, @ModelAttribute("ContentFileVo") ContentFileVo contentFileVo,@ModelAttribute("BbsFVo") BbsFVo bbsFVo
						   , ModelMap model, @PathVariable("strDomain") String strDomain) throws Exception {
		
		String strMsg = "";
		String returnUrl = "";
		
		BbsContentVo bbsContentVo = new BbsContentVo();
		try {
			//수정,삭제 구분
			String mode = bbsFVo.getMode();
			if(mode == null || mode.equals("")){
				return "redirect:/site/"+strDomain+"/ex/bbs/List.do?cbIdx="+bbsFVo.getCbIdx();
			}
			
			int bcIdx = bbsFVo.getBcIdx();
 			int parentSeq = bbsFVo.getParentSeq();
			int answerStep = bbsFVo.getAnswerStep();
			int answerDepth = bbsFVo.getAnswerDepth();
			String searchKey = bbsFVo.getSearchKey();
			String tgtTypeCd = bbsFVo.getTgtTypeCd();
			String deptCode = bbsFVo.getDeptCode();
			int pageIndex = bbsFVo.getPageIndex();
			String gubPassword = bbsFVo.getGubPassword();
			
			Map authorMap = null;
			//게시판 정보 조회
			bbsFVo = bbsFSvc.boardInfo(bbsFVo);
			
			bbsFVo.setBcIdx(bcIdx);
			
			//게시판 카테고리
			List categoryList  = null;
			if( "Y".equals(bbsFVo.getCategoryUseYn()) ){
				categoryList = bbsFSvc.boardCategoryList(bbsFVo);
			}
			
			String code = bbsFVo.getBbsTempletGbn();

			//일반게시판일 경우
			HashMap<String, String> resultMap = null;
			List labelPropGbnList  = bbsFSvc.boardlabelPropGbnList(bbsFVo);
			
			if( "U".equals(mode) ){
				//글 수정 권한 체크
				request.setAttribute("cbIdx", bbsFVo.getCbIdx());
				request.setAttribute("bcIdx", bbsFVo.getBcIdx());
				//비회원 비밀번호 맞을시 수정가능 20200602
				request.setAttribute("gubPassword", gubPassword);
				
				authorMap = BoardAuthorChk(bbsFVo.getModGbn() ,request,"mod");
				/*20200821 테스트용 임시주석 전진형*/
				/*if( !Boolean.valueOf((Boolean)authorMap.get("flag"))){
					return alert((String)authorMap.get("returnUrl") ,(String)authorMap.get("msg"), model);
				}else{
					if(bbsFVo.getWriteGbn().equals("16010700")){
						HttpSession ses = request.getSession();
						EgovMap emap = (EgovMap)ses.getAttribute("SessionTeenagerVo");
						bbsFVo.setDupcode((String)emap.get("userid"));							
					}else{
						bbsFVo.setDupcode((String)authorMap.get("dupcode"));
					}						
				}*/
				if(bbsFVo.getWriteGbn().equals("16010700")){
					HttpSession ses = request.getSession();
					EgovMap emap = (EgovMap)ses.getAttribute("SessionTeenagerVo");
					bbsFVo.setDupcode((String)emap.get("userid"));							
				}else{
					bbsFVo.setDupcode((String)authorMap.get("dupcode"));
				}	
				
				Map detailMap = bbsFSvc.boardUpdateDetail(bbsFVo);
				List fileList = bbsFSvc.boardFileList(bbsFVo);
				JSONArray jarr = null;
				JSONArray jarr2 = new JSONArray();
		        JSONObject jobj = null;
		        JSONObject jobj2 = null;
				BbsFVo bbsFileVo = null;					
				if( fileList.size() !=0 || fileList != null ){
					for(int fLoop=0; fLoop<fileList.size(); fLoop++){
						bbsFileVo = (BbsFVo) fileList.get(fLoop);
						jobj = new JSONObject();
						jobj2 = new JSONObject();
						jarr = new JSONArray();
						jobj.put("tempname", bbsFileVo.getStreFileNm());							
		                jobj.put("size", Integer.parseInt(bbsFileVo.getFileSize())/1024+" Kb");
		                jobj.put("rsize", bbsFileVo.getFileSize());
		                jobj.put("name", bbsFileVo.getOrignlFileNm());
		                jobj.put("fileno", bbsFileVo.getFileNo());
		                System.out.println("aaaaaa  :  /common/board/Download.do?bcIdx="+bcIdx+"&cbIdx="+bbsFVo.getCbIdx()+"&streFileNm="+bbsFileVo.getStreFileNm());
		                jobj.put("url","/common/board/Download.do?bcIdx="+bcIdx+"&cbIdx="+bbsFVo.getCbIdx()+"&streFileNm="+bbsFileVo.getStreFileNm());
		                jarr.add(jobj);
		                jobj2.put("files", jarr);
		                jarr2.add(jobj2);
					}
				}
				System.out.println("multijson : "+jarr2.toJSONString());
				detailMap.put("multijson", jarr2.toJSONString());
				if(detailMap == null ){
					return alert("", "잘못된 접근경로 입니다.", model);
				}
				resultMap = BoardHtmlCreate(labelPropGbnList, mode, detailMap, categoryList, request, bbsFVo.getBbsFileCnt(),bbsFVo.getFileMaxSize(), fileList);
				model.addAttribute("detailMap",detailMap);
			}else{
				Map detailMap = new HashMap();
				if( "C".equals(mode) ){//글쓰기 권한 체크
					authorMap = BoardAuthorChk(bbsFVo.getWriteGbn() ,request,"write");
					if( !Boolean.valueOf((Boolean)authorMap.get("flag")) ){
						return alert((String)authorMap.get("returnUrl") ,(String)authorMap.get("msg"), model);
					}
				}else if( "R".equals(mode) ){//리플 권한 체크
					request.setAttribute("cbIdx", bbsFVo.getCbIdx());
					request.setAttribute("parentSeq", parentSeq);
					authorMap = BoardAuthorChk(bbsFVo.getAnswerGbn() ,request,"answer");
					if( !Boolean.valueOf((Boolean)authorMap.get("flag")) ){
						return alert((String)authorMap.get("returnUrl") ,(String)authorMap.get("msg"), model);
					}
				}
				String ssId = "";
				String ssName = "";
				String ssEmail = "";
				String ssZip = "";
				String ssAddr1 = ""; 
				String ssAddr2 = "";
				String ssTel = "";
				String ssHp = "";
				String dupcode = "";
				String ssNumgubun = "";
				if(bbsFVo.getWriteGbn().equals("16010700")){
					HttpSession ses = request.getSession();
					EgovMap emap = (EgovMap)ses.getAttribute("SessionTeenagerVo");
					System.out.println(" PARAM : "+(String)emap.get("userid"));
					System.out.println(" PARAM : "+(String)emap.get("hname"));
					System.out.println(" PARAM : "+(String)emap.get("eMail"));
					System.out.println(" PARAM : "+(String)emap.get("tel"));
					System.out.println(" PARAM : "+(String)emap.get("hPhone"));
					ssId = (String)emap.get("userid");
					ssName = (String)emap.get("hname");
					ssEmail = (String)emap.get("eMail");
					ssZip = (String)emap.get("");
					ssAddr1 = (String)emap.get("");
					ssAddr2 = (String)emap.get("");
					ssTel = (String)emap.get("tel");
					ssHp = (String)emap.get("hPhone");
					dupcode = (String)emap.get("");
					
				}else{
					HttpSession ses = request.getSession();
					ssId = (String)ses.getAttribute("ss_id");
					ssName = (String)ses.getAttribute("ss_name");
					ssEmail = (String)ses.getAttribute("ss_email");
					ssZip = (String)ses.getAttribute("ss_zip");
					ssAddr1 = (String)ses.getAttribute("ss_addr1");
					ssAddr2 = (String)ses.getAttribute("ss_addr2");
					ssNumgubun = (String)ses.getAttribute("ss_numgubun");
					
					if("H".equals(ssNumgubun) || "C".equals(ssNumgubun)){ /*집전화 || 회사전화*/
						ssTel = (String)ses.getAttribute("ss_tel");
					}else if("M".equals(ssNumgubun)){ /*핸드폰*/
						ssHp = (String)ses.getAttribute("ss_tel");
					}
					
					dupcode = (String)ses.getAttribute("ss_dupkey");												
				}
				
				if(ssZip != null && ssZip != ""){
					detailMap.put("addrCont", ssZip+"_"+ssAddr1+"_"+ssAddr2);
				}
				if(ssEmail != null && ssEmail != ""){
					detailMap.put("emailCont", ssEmail);
				}
				if(ssTel != null && ssTel != ""){
					detailMap.put("telCont", ssTel);
				}
				if(ssHp != null && ssHp != ""){
					detailMap.put("phoneCont", ssHp);
				}
				if(ssName != null && ssName != ""){
					detailMap.put("nameCont", ssName);
				}
				
				resultMap = BoardHtmlCreate(labelPropGbnList, mode, null, categoryList, request, bbsFVo.getBbsFileCnt(),bbsFVo.getFileMaxSize(), null);
				model.addAttribute("detailMap",detailMap);
			}
			
			Map labelPropGbnMap;
			
			
			List itemList = new ArrayList();
			List itemList2;
			for(int i=0;i<labelPropGbnList.size();i++){
				labelPropGbnMap = (Map)labelPropGbnList.get(i);
				if(
						labelPropGbnMap.get("LABEL_PROP_GBN").equals("16020700")
						|| labelPropGbnMap.get("LABEL_PROP_GBN").equals("16020800")
						|| labelPropGbnMap.get("LABEL_PROP_GBN").equals("16020900")
				){
					BbsFVo item = new BbsFVo();
					item.setItemCode((String)labelPropGbnMap.get("ITEM_CODE"));
					itemList.add(bbsFSvc.itemList(item));
				}
			}
			
			bbsFVo.setMode(mode);
			bbsFVo.setParentSeq(parentSeq);
			bbsFVo.setAnswerStep(answerStep);
			bbsFVo.setAnswerDepth(answerDepth);
			
			if( resultMap.containsKey("clobCont") )bbsFVo.setClobContTemp(resultMap.get("clobCont"));
			if( resultMap.containsKey("subCont") )bbsFVo.setSubContTemp(resultMap.get("subCont"));
			
			bbsFVo.setSearchKey(searchKey);
			bbsFVo.setTgtTypeCd(tgtTypeCd);
			bbsFVo.setPageIndex(pageIndex);
			bbsFVo.setDeptCode(deptCode);
			
			List fileList = contentFileSvc.retrieveListContentFile(contentFileVo);
			model.addAttribute("fileList", fileList);
			
			model.addAttribute("labelPropGbnList",labelPropGbnList);
			model.addAttribute("resultMap", resultMap);
			model.addAttribute("bbsFVo", bbsFVo);
			model.addAttribute("strDomain", strDomain);
			model.addAttribute("itemList", itemList);
			model.addAttribute("emailList", cmmCodeSvc.retrieveListCmmCode("EMAIL_CD"));
			model.addAttribute("telList", cmmCodeSvc.retrieveListCmmCode("TEL_CD"));
			model.addAttribute("phoneList", cmmCodeSvc.retrieveListCmmCode("PHONE_CD"));
		
			
			EzBbsTempletVo ezBbsTempletVo= new EzBbsTempletVo();
			ezBbsTempletVo.setBbsTempletGbn(bbsFVo.getBbsTempletGbn());
			ezBbsTempletVo = ezBbsTempletSvc.selectEzBbsTemplet(ezBbsTempletVo);			
			
			returnUrl = "/injeinc/foffice/ex/bbs/"+ezBbsTempletVo.getRegistCode();
			
			System.out.println("returnUrl:"+returnUrl);
				
		} catch (Exception e) {
			strMsg = Message.getMessage("901.code");	//에러
			//returnUrl = alert("/site/"+strDomain+"/main.do",SVC_MSGE, model);
			throw e;
		}
			
		

		return returnUrl;
		
	}
	
	
	/**
	 * 게시판 등록/수정
	 * @param request
	 * @param BbsFVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	@RequestMapping(value= {"/{strSitePath}/{strDomain}/ex/bbs/BoardRegProc.do","/{strSitePath}/{strDomain}/foffice/user/MyIntegBoardRegProc.do"})
	public String BoardRegProc(HttpServletRequest request, @ModelAttribute("BbsFVo") BbsFVo bbsFVo
						   , ModelMap model, @PathVariable("strDomain") String strDomain) throws Exception {
		
		String SVC_MSGE = "";
		BbsFVo  infoVo = null;
		try {
			//bcIdx 생성 
			String mode = bbsFVo.getMode();
			Map authorMap = null;
			System.out.println("checked mode : "+mode);
			//게시판 정보 조회
			infoVo = bbsFSvc.boardInfo(bbsFVo);
			bbsFVo.setBbsTempletGbn(infoVo.getBbsTempletGbn());
			//게시글 승인 여부
			if( "2".equals(infoVo.getBbsApprYn()) ){
				bbsFVo.setApprYn("N");
			}else{
				bbsFVo.setApprYn("Y");
			}
			
			if(infoVo.getBbsTempletGbn().equals("bbs_minwon") || infoVo.getBbsTempletGbn().equals("16050700")) {
				if(EgovStringUtil.isEmpty(bbsFVo.getAnsYnCont())) {
					bbsFVo.setAnsYnCont("22000100"); //묻고답하기 게시판이거나 민원게시판일 경우 글을 쓰면 자동으로 답변요청 상태로 저장됨
				}
			}
			
			//인바라 게시판 등록시 "접수중"으로 등록됨.
			if(bbsFVo.getCbIdx() == 414){
				bbsFVo.setMwStatusCont("20000600");
				
				Calendar cal = Calendar.getInstance();
				
				int yoil = cal.get(Calendar.DAY_OF_WEEK);
				int plus;
				
				if(yoil == 1 || yoil == 2 || yoil == 3) plus = 3;
				else if(yoil == 4 || yoil == 5 || yoil == 6) plus = 5;
				else plus = 4;

				cal.add(cal.DAY_OF_MONTH, +plus);
				
				String month = String.valueOf(cal.get(cal.MONTH)+1);
				String day = String.valueOf(cal.get(cal.DAY_OF_MONTH));
				if(month.length() == 1){
					month = "0" + month;
				}
				if(day.length() == 1){
					day = "0" + day;
				}
				
				
				String ansDeadlineDt = cal.get(cal.YEAR)+"-"+month+"-"+day;
				bbsFVo.setAnsDeadlineDt(ansDeadlineDt);
				
				bbsFVo.setGubPassword(DESCrypto.encrypt(bbsFVo.getGubPassword()));
			}
			
			HttpSession ses = request.getSession();
			String ssLevel = (String)ses.getAttribute("ss_level");
			if( ssLevel != null ){
				if( "7".equals(ssLevel) ){
					bbsFVo.setRegId((String)ses.getAttribute("ss_id"));
					bbsFVo.setDupcode((String)ses.getAttribute("ss_dupkey"));
					bbsFVo.setRegIp((String)ses.getAttribute("ss_ip"));
				}else if( "8".equals(ssLevel) ){
					bbsFVo.setRegId("실명인증");
					bbsFVo.setDupcode((String)ses.getAttribute("ss_dupkey"));
					bbsFVo.setRegIp((String)ses.getAttribute("ss_ip"));
				}else if("11".equals(ssLevel)){
					EgovMap emap = (EgovMap)ses.getAttribute("SessionTeenagerVo");
					bbsFVo.setRegId((String)emap.get("userid"));
					bbsFVo.setDupcode((String)emap.get("userid"));
					bbsFVo.setRegIp((String)emap.get("ip"));
				}
			}else{ //비회원일 경우.. 덤프키,비밀번호 20200602
				bbsFVo.setDupcode("guest");
				bbsFVo.setRegIp((String)request.getRemoteAddr());
			}
			
			if( "C".equals(mode) ){
				authorMap = BoardAuthorChk(infoVo.getWriteGbn() ,request,"writer");
				if( !Boolean.valueOf((Boolean)authorMap.get("flag")) ){
					return alert((String)authorMap.get("returnUrl") ,(String)authorMap.get("msg"), model);
				}else{
					System.out.println("bbsFVo check : "+bbsFVo);
					bbsFSvc.boardContentInsert(request, bbsFVo);
					SVC_MSGE = Message.getMessage("201.message");
				}
			}else if( "R".equals(mode) ){
				authorMap = BoardAuthorChk(infoVo.getAnswerGbn() ,request,"answer");
				if( !Boolean.valueOf((Boolean)authorMap.get("flag")) ){
					return alert((String)authorMap.get("returnUrl") ,(String)authorMap.get("msg"), model);
				}else{
					bbsFSvc.boardContentInsert(request, bbsFVo);
					SVC_MSGE = Message.getMessage("201.message");
				}
			}else if( "U".equals(mode) ){
				request.setAttribute("cbIdx", bbsFVo.getCbIdx());
				request.setAttribute("bcIdx", bbsFVo.getBcIdx());
				request.setAttribute("gubPassword", bbsFVo.getOldGubPassword());
				authorMap = BoardAuthorChk(infoVo.getModGbn() ,request,"mod");
				/*20200824 - 전진형 수정테스트를 하기 위한  주석*/
				/*if( !Boolean.valueOf((Boolean)authorMap.get("flag")) ){
					return alert((String)authorMap.get("returnUrl") ,(String)authorMap.get("msg"), model);
				}else{
					bbsFSvc.boardContentUpdate(request, bbsFVo);
					SVC_MSGE = Message.getMessage("401.message");
				}*/
				System.out.println("bbsFVo : "+bbsFVo);
				bbsFSvc.boardContentUpdate(request, bbsFVo);
				
				SVC_MSGE = Message.getMessage("401.message");
			}
			
		}catch (FileUploadException e) {
			SVC_MSGE = Message.getMessage("905.message");	//파일 업로드 에러
			e.printStackTrace();
//			request.getHeader("referer")
			return alert("/site/"+strDomain+"/ex/bbs/List.do?cbIdx="+bbsFVo.getCbIdx(),SVC_MSGE,model);
		}
		if(infoVo.getBbsTempletGbn().equals("bbs_callcenter")){
			return alert("/site/"+strDomain+"/ex/bbs/Regist.do?cbIdx="+bbsFVo.getCbIdx()+"&mode=C", Message.getMessage("201.message"), model);
		}
		if(infoVo.getCbUprCd().equals("s010101")) {
			return alert("", Message.getMessage("201.message"), model);
		}else{
			return alert("/site/"+strDomain+"/ex/bbs/List.do?cbIdx="+bbsFVo.getCbIdx(), Message.getMessage("201.message"), model);
		}
		
	}
	
	/**
	 * 파일 업로드
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	
	@RequestMapping(value = "/{strSitePath}/{strDomain}/ex/bbs/File_Upload.do", produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public String upload(MultipartHttpServletRequest request,HttpServletResponse response) {
		 
        //1. build an iterator
         Iterator<String> itr =  request.getFileNames();
         MultipartFile mpf = null;
         JSONArray jarr = new JSONArray();
         JSONObject jobj = null;
         String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
		 String fileStreCours = Message.getMessage("board.file.upload.path");
	     String cbIdx = request.getParameter("cbIdx");
	     if(cbIdx != null && !cbIdx.equals("")){
	    	 fileStreCours = fileStreCours+cbIdx+"/";
	     }
         //2. get each file
         while(itr.hasNext()){
 
             //2.1 get next MultipartFile
             mpf = request.getFile(itr.next()); 
 
             //2.3 create new fileMeta
             jobj = new JSONObject();

             jobj.put("name", mpf.getOriginalFilename());
             jobj.put("size", mpf.getSize()/1024+" Kb");
             jobj.put("rsize", mpf.getSize());
             jobj.put("type", mpf.getContentType());
             
             
             System.out.println("name : "+mpf.getOriginalFilename());
             System.out.println("size : "+mpf.getSize()/1024+" Kb");
             System.out.println("filetype : "+mpf.getContentType());
             System.out.println("rsize : "+mpf.getSize());
 
             try {
 
                 // copy file to local disk (make sure the path "e.g. D:/temp/files" exists)            
                 
                 String orignlFileNm = mpf.getOriginalFilename();
                 String fileExtsn = orignlFileNm.substring(orignlFileNm.lastIndexOf(".") + 1, orignlFileNm.length());
				 String streFileNm = EgovFormBasedUUID.randomUUID().toString().toLowerCase()+"."+fileExtsn;	// 전자정부 UTIL 방법
				 mpf.transferTo(new File(EgovWebUtil.filePathBlackList(rootPath + fileStreCours + streFileNm)));
				 //FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream("D:/temp/files/"+streFileNm));
				 jobj.put("savename", streFileNm);
				 System.out.println("savename : "+streFileNm);
 
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
             //2.4 add to files
             
             jarr.add(jobj);
             
             
         }
         

        // result will be like this
        // [{"fileName":"app_engine-85x77.png","fileSize":"8 Kb","fileType":"image/png"},...]
        System.out.println(JSONValue.toJSONString(jarr));       
        return JSONValue.toJSONString(jarr);
    }
	
    //게시판 등록, 수정 html,script 생성
	@SuppressWarnings({ "rawtypes", "unused" })
	public HashMap<String, String> BoardHtmlCreate(List labelPropGbnList, String mode, Map detailMap, List categoryList, HttpServletRequest request ,int bbsFileCnt,String bbsFileMaxSize, List fileList ) throws Exception{
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
		String dupcode = "";
		
		String ssLevel = (String)ses.getAttribute("ss_level");
		if( "7".equals(ssLevel) ){
			ssId = (String)ses.getAttribute("ss_id");
			ssName = (String)ses.getAttribute("ss_name");
			ssEmail = (String)ses.getAttribute("ss_email");
			ssZip = (String)ses.getAttribute("ss_zip");
			ssAddr1 = (String)ses.getAttribute("ss_addr1");
			ssAddr2 = (String)ses.getAttribute("ss_addr2");
			ssTel = (String)ses.getAttribute("ss_tel");
			ssHp = (String)ses.getAttribute("ss_hp");
			dupcode = (String)ses.getAttribute("ss_dupkey");
		}else if( "8".equals(ssLevel) ){
			ssName = (String)ses.getAttribute("ss_name");
			ssId = "실명인증";
			dupcode = (String)ses.getAttribute("ss_dupkey");
			
		}
		Map labelPropGbnMap;
		HashMap<String, String> resultMap = new HashMap<String, String>();
		String strCompYn = "";
		
		/*if( "C".equals(mode) ){*/
		outScript.append("function doBbsFReg() { \t");
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
			/*resultMap.put("clobCont", strValue);*/
			//수정 데이터
			if( "U".equals(mode) ){
				if( "16020200".equals(labelPropGbnMap.get("LABEL_PROP_GBN")) ){
					String strValue =  BoardValue(labelPropGbnMap, detailMap);
					resultMap.put((String)labelPropGbnMap.get("CONTENT_MAPPING_L"), strValue);
					outDetailDataSet.append("$('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"').val($('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"Temp').val()); \t");
				}else if( "16020400".equals(labelPropGbnMap.get("LABEL_PROP_GBN")) ){
					String strValue =  BoardValue(labelPropGbnMap, detailMap);
					
					outDetailDataSet.append("var vTemp"+nLoop+" = '"+ strValue +"'; \t");
					outDetailDataSet.append("var vArray"+nLoop+" = vTemp"+nLoop+".split('_'); \t");
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
					outDetailDataSet.append("var vChkVal"+nLoop+"='"+strValue+"'; \t var vChkArray"+nLoop+" = vChkVal"+nLoop+".split(','); for(var vLoop in vChkArray"+nLoop+") { \t $(quot;input[name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"0'][value=quot;+vChkArray"+nLoop+"[vLoop] + quot;]quot;).prop('checked', true); } \t");
				}else{
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
					outScript.append("if( cm_is_empty($('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"').val()) ){ \t alert('"+labelPropGbnMap.get("LABEL_NAME")+"을(를) 입력해주세요.'); \t $('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"').focus(); \t return; \t } \t ");
				}
				
			//Text Area	
			}else if( "16020200".equals(labelPropGbnMap.get("LABEL_PROP_GBN")) ){
				outHtml2.append("<tr><th scope='row'><label for='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"'><span class='required'>"+strCompYn+"</span>"+labelPropGbnMap.get("LABEL_NAME")+"</label></th><td><textarea rows='10' cols='50' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"' class='w90 board_input'></textarea></td></tr>");
				//필수값 체크
				if( "Y".equals(labelPropGbnMap.get("LABEL_COMP_YN")) ){
					outScript.append("if( cm_is_empty($('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"').val()) ){ \t alert('"+labelPropGbnMap.get("LABEL_NAME")+"을(를) 입력해주세요.'); \t $('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"').focus(); \t return; \t } \t ");
				}
				
			//Link
			/*}else if( "16020300".equals(labelPropGbnMap.get("LABEL_PROP_GBN")) ){
				outHtml2.append("<tr><th scope='row'><label for='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"'><span class='required'>"+strCompYn+"</span>"+labelPropGbnMap.get("LABEL_NAME")+"</label></th><td><input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"' class='w90'></td></tr>");
				//필수값 체크
				if( "Y".equals(labelPropGbnMap.get("LABEL_COMP_YN")) ){
					outScript.append("if( cm_is_empty($('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"').val()) ){ \t alert('"+labelPropGbnMap.get("LABEL_NAME")+"을(를) 입력해주세요.'); \t $('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"').focus; \t return; \t } \t ");
				}*/
				
			//Address
			}else if( "16020400".equals(labelPropGbnMap.get("LABEL_PROP_GBN")) ){
				outHtml.append("<input type='hidden' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"' name ='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"'>");
				
				if( ("C".equals(mode)||"R".equals(mode)) ){
					if(ssZip !=""){
						outHtml2.append("<tr><th scope='row'><label for='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"'><span class='required'>"+strCompYn+"</span> "+labelPropGbnMap.get("LABEL_NAME")+"</label></th><td class='addr'><div><input type='text' title='우편번호' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"' value='"+ssZip+"' size='10' readonly='true' /><a href='javascript:openAddrSearch();' class='btn-inner' title='새창'>우편번호검색</a></div><div class='mgt5'><input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"' value='"+ssAddr1+"' class='w80' readonly='true' /></div><div class='mgt5'><input type='text' title='상세주소' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"' value='"+ssAddr2+"' class='w80'></div></td></tr>");
					}else{
						outHtml2.append("<tr><th scope='row'><label for='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"'><span class='required'>"+strCompYn+"</span> "+labelPropGbnMap.get("LABEL_NAME")+"</label></th><td class='addr'><div><input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"' size='6' readonly='true' /><a href='javascript:openAddrSearch();' class='btn-inner' title='새창'>우편번호검색</a></div><div class='mgt5'><input type='text' title='기본주소' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"' class='w80' readonly='true' /></div><div class='mgt5'><input type='text' title='상세주소' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"' class='w80'></div></td></tr>");
					}
				}else if( "U".equals(mode) ){
					outHtml2.append("<tr><th scope='row'><label for='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"'><span class='required'>"+strCompYn+"</span> "+labelPropGbnMap.get("LABEL_NAME")+"</label></th><td class='addr'><div><input type='text' title='우편번호' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"' size='6' readonly='true' /><a href='javascript:openAddrSearch();' class='btn-inner' title='새창'>우편번호검색</a></div><div class='mgt5'><input type='text' title='기본주소' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"' class='w80' readonly='true' /></div><div class='mgt5'><input type='text' title='상세주소' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"' class='w80'></div></td></tr>");
				}
				
				//필수값 체크
				if( "Y".equals(labelPropGbnMap.get("LABEL_COMP_YN")) ){
					outScript.append("if( cm_is_empty($('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"').val()) ){ \t alert('"+labelPropGbnMap.get("LABEL_NAME")+"을(를) 입력해주세요.'); \t  $('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"').focus(); \t return; \t } \t ");
					outScript.append("if( cm_is_empty($('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"').val()) ){ \t alert('"+labelPropGbnMap.get("LABEL_NAME")+"을(를) 입력해주세요.'); \t  $('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"').focus(); \t return; \t } \t ");
					outScript.append("if( cm_is_empty($('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"').val()) ){ \t alert('"+labelPropGbnMap.get("LABEL_NAME")+"을(를) 입력해주세요.'); \t  $('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"').focus(); \t return; \t } \t ");
					
				}
								
				outColSum.append("document.bbsFVo."+labelPropGbnMap.get("CONTENT_MAPPING_L")+".value = $('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"').val() +quot;_quot;+ $('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"').val() +quot;_quot;+ $('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"').val(); \t");
				//outScriptfn.append("function openAddrSearch() { \t var from = document.form; \t doJusoPop(from); \t } \t function setAddrValue(addr1, addr2, zip) { \t $('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"').val(zip); \t $('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"').val(addr1); \t $('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"').val(addr2); \t $('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"').focus(); \t } \t");
				
			//Phone
			}else if( "16020500".equals(labelPropGbnMap.get("LABEL_PROP_GBN")) ){
				outHtml.append("<input type='hidden' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"' name ='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"'>");
				if( ("C".equals(mode)||"R".equals(mode)) && "telCont".equals( labelPropGbnMap.get("CONTENT_MAPPING_L")) ){
					if( null != ssTel && ssTel != ""  ){
						String[] arrayTel = ssTel.split("-", 3);
						
						/*
						String arrayTel1 = "";
						String arrayTel2 = "";
						String arrayTel3 = "";
						
						if(arrayTel.length > 0) {
							arrayTel1 = arrayTel[0];
						}
						if(arrayTel.length > 1) {
							arrayTel2 = arrayTel[1];
						}
						if(arrayTel.length > 2) {
							arrayTel3 = arrayTel[2];
						}*/
						
						outHtml2.append("<tr><th scope='row'><label for='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"'><span class='required'>"+strCompYn+"</span> "+labelPropGbnMap.get("LABEL_NAME")+"</label></th><td><input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"' value='"+arrayTel[0]+"' onkeypress='return showKeyCode(event)' maxlength='4' size='5' title='전화 앞번호'> -<input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"' value='"+arrayTel[1]+"' onkeypress='return showKeyCode(event)' maxlength='4' size='5' title='전화 중간번호'> -<input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"' onkeypress='return showKeyCode(event)' maxlength='4' value='"+arrayTel[2]+"' size='5' title='전화 뒷번호'></td></tr>");
					}else{
						outHtml2.append("<tr><th scope='row'><label for='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"'><span class='required'>"+strCompYn+"</span> "+labelPropGbnMap.get("LABEL_NAME")+"</label></th><td><input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"' size='5' onkeypress='return showKeyCode(event)' maxlength='4' title='전화 앞번호'> -<input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"' size='5' onkeypress='return showKeyCode(event)' maxlength='4' title='전화 중간번호'> -<input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"' size='5' onkeypress='return showKeyCode(event)' maxlength='4' title='전화 뒷번호'></td></tr>");
					}
				}else if( ("C".equals(mode)||"R".equals(mode)) && "phoneCont".equals( labelPropGbnMap.get("CONTENT_MAPPING_L")) ){
					
					//2016_11_15  null != ssHp 추가
					if( null != ssHp && ssHp != "" ){
						String[] arrayHp = ssHp.split("-");
						outHtml2.append("<tr><th scope='row'><label for='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"'><span class='required'>"+strCompYn+"</span> "+labelPropGbnMap.get("LABEL_NAME")+"</label></th><td><input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"' value='"+arrayHp[0]+"' onkeypress='return showKeyCode(event)' maxlength='4' size='5' title='전화 앞번호'> -<input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"' value='"+arrayHp[1]+"' size='5' onkeypress='return showKeyCode(event)' maxlength='4' title='전화 중간번호'> -<input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"' value='"+arrayHp[2]+"' size='5' onkeypress='return showKeyCode(event)' maxlength='4' title='전화 뒷번호'></td></tr>");
					}else{
						outHtml2.append("<tr><th scope='row'><label for='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"'><span class='required'>"+strCompYn+"</span> "+labelPropGbnMap.get("LABEL_NAME")+"</label></th><td><input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"' size='5' onkeypress='return showKeyCode(event)' maxlength='4' title='전화 앞번호'> -<input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"' size='5' onkeypress='return showKeyCode(event)' maxlength='4' title='전화 중간번호'> -<input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"' size='5' onkeypress='return showKeyCode(event)' maxlength='4' title='전화 뒷번호'></td></tr>");
					}
				}else{
					outHtml2.append("<tr><th scope='row'><label for='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"'><span class='required'>"+strCompYn+"</span> "+labelPropGbnMap.get("LABEL_NAME")+"</label></th><td><input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"' size='5' onkeypress='return showKeyCode(event)' maxlength='4' title='전화 앞번호'> -<input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"' size='5' onkeypress='return showKeyCode(event)' maxlength='4' title='전화 중간번호'> -<input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"' size='5' onkeypress='return showKeyCode(event)' maxlength='4' title='전화 뒷번호'></td></tr>");
				}
				
				//필수값 체크
				if( "Y".equals(labelPropGbnMap.get("LABEL_COMP_YN")) ){
					outScript.append("if( cm_is_empty($('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"').val()) ){ \t alert('"+labelPropGbnMap.get("LABEL_NAME")+"을(를) 입력해주세요.'); \t $('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"').focus(); \t  return; \t } \t ");
					outScript.append("if( cm_is_empty($('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"').val()) ){ \t alert('"+labelPropGbnMap.get("LABEL_NAME")+"을(를) 입력해주세요.'); \t $('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"').focus(); \t  return; \t } \t ");
					outScript.append("if( cm_is_empty($('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"').val()) ){ \t alert('"+labelPropGbnMap.get("LABEL_NAME")+"을(를) 입력해주세요.'); \t $('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"').focus(); \t  return; \t } \t ");
				}
								
				outColSum.append("document.bbsFVo."+labelPropGbnMap.get("CONTENT_MAPPING_L")+".value = $('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"').val() +quot;-quot;+ $('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"2"+"').val() +quot;-quot;+ $('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"3"+"').val(); \t");
			//File
			}else if( "16020600".equals(labelPropGbnMap.get("LABEL_PROP_GBN")) ){
				if( ( "C".equals(mode)||"R".equals(mode)) ){
					outFileHtml.append("<tr><th scope='row'><label for='fileTagId0'>"+labelPropGbnMap.get("LABEL_NAME")+"</label></th><td>");
					for(int fLoop=0; fLoop<bbsFileCnt; fLoop++){
						outFileHtml.append("<input type='file' name='fileTagId' id='fileTagId"+fLoop+"' class='w90'>");
					}
					outFileHtml.append("<br><span style='color:#FF0000'>※파일1개당 최대"+bbsFileMaxSize+"MB까지만 가능합니다</span>");
					outFileHtml.append("</td></tr>");
					if( "Y".equals(labelPropGbnMap.get("LABEL_COMP_YN")) ){
						outScript.append("if( cm_is_empty($('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"').val()) ){ \t alert('"+labelPropGbnMap.get("LABEL_NAME")+"을(를) 입력해주세요.'); \t $('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"').focus(); \t return; \t } \t ");
					}
				}else if( "U".equals(mode)){
					outFileHtml.append("<tr><th scope='row'><label for='fileTagId0'>"+labelPropGbnMap.get("LABEL_NAME")+"</label></th><td>");
					BbsFVo bbsFileVo = null;
					int tLoop=0;
					if( fileList.size() !=0 || fileList != null ){
						for(int fLoop=0; fLoop<fileList.size(); fLoop++){
							bbsFileVo = (BbsFVo) fileList.get(fLoop);
							outFileHtml.append("<p class='uploaded_file' id='fileRmv"+bbsFileVo.getFileNo()+"'><a href='/site/{strDomain}/ex/bbs/fileDownload.do?bcIdx="+bbsFileVo.getBcIdx()+"&cbIdx="+bbsFileVo.getCbIdx()+"&fileNo="+bbsFileVo.getFileNo()+"' class='file'>"+bbsFileVo.getOrignlFileNm()+"["+getComma(bbsFileVo.getFileSize())+"Byte]</a><a href='javascript:doBbsFileRmvAx("+bbsFileVo.getCbIdx()+","+bbsFileVo.getBcIdx()+","+bbsFileVo.getFileNo()+")' class='delete'>삭제</a></p>");
							outFileHtml.append("<span id='upFile"+bbsFileVo.getFileNo()+"'></span>");
							tLoop++;
						}
					}
					for(tLoop=tLoop; tLoop<bbsFileCnt; tLoop++){
						outFileHtml.append("<input type='file' name='fileTagId' id='fileTagId"+tLoop+"' class='w90'>");
						outFileHtml.append("<br><span style='color:#FF0000'>※파일1개당 최대"+bbsFileMaxSize+"MB까지만 가능합니다</span>");
					}
					outFileHtml.append("</td></tr>");
					
					
				}
				//outScriptfn.append("");
			//Select Box
			}else if( "16020700".equals(labelPropGbnMap.get("LABEL_PROP_GBN")) ){
				outHtml2.append("<tr><th scope='row'><label for='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"'><span class='required'>*</span> "+labelPropGbnMap.get("LABEL_NAME")+"</label></th>");
				outHtml2.append("<td><select name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"'>");
				Map categoryMap;
				if( "cateCont".equals(labelPropGbnMap.get("CONTENT_MAPPING_L")) && categoryList != null ){
					for(int cLoop=0; cLoop<categoryList.size(); cLoop++){
						categoryMap = (Map) categoryList.get(cLoop);
						outHtml2.append("<option value="+categoryMap.get("CATEGORY_CODE")+">"+categoryMap.get("CATEGORY_NAME")+"</option>");
					}
				}else{
					BbsFVo item = new BbsFVo();
					item.setItemCode((String)labelPropGbnMap.get("ITEM_CODE"));
					List itemList = bbsFSvc.itemList(item);
					for(int iLoop=0; iLoop<itemList.size(); iLoop++){
						categoryMap = (Map)itemList.get(iLoop);
						outHtml2.append("<option value="+categoryMap.get("CODE")+">"+categoryMap.get("CODE_NAME")+"</option>");
					}
				}
				outHtml2.append("</select></td>");
				
			//Radio Box
			}else if( "16020800".equals(labelPropGbnMap.get("LABEL_PROP_GBN")) ){
				BbsFVo item = new BbsFVo();
				Map itemMap;
				item.setItemCode((String)labelPropGbnMap.get("ITEM_CODE"));
				List itemList = bbsFSvc.itemList(item);
				outHtml2.append("<tr><th scope='row'><label for="+labelPropGbnMap.get("CONTENT_MAPPING_L")+"1"+"'><span class='required'>"+strCompYn+"</span>"+labelPropGbnMap.get("LABEL_NAME")+"</label></th><td>");
				for(int iLoop=0; iLoop<itemList.size(); iLoop++){
					itemMap = (Map)itemList.get(iLoop);
					if(iLoop == 0) {
						outHtml2.append("<input type='radio' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+(iLoop+1)+"' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"' value='"+itemMap.get("CODE")+"' checked='checked'/><label for='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+(iLoop+1)+"'>"+itemMap.get("CODE_NAME")+"</label>");
					}else{
						outHtml2.append("<input type='radio' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+(iLoop+1)+"' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"' value='"+itemMap.get("CODE")+"' /><label for='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+(iLoop+1)+"'>"+itemMap.get("CODE_NAME")+"</label>");
					}
				}
				outHtml2.append("</td></tr>");
				
				//필수값 체크
				/*if( "Y".equals(labelPropGbnMap.get("LABEL_COMP_YN")) ){
					outScript.append("if( cm_is_empty($('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"').val()) ){ \t alert('"+labelPropGbnMap.get("LABEL_NAME")+"을(를) 입력해주세요.'); \t $('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"').focus; \t return; \t } \t ");
				}*/
			//CheckBox
			}else if( "16020900".equals(labelPropGbnMap.get("LABEL_PROP_GBN")) ){
				BbsFVo item = new BbsFVo();
				Map itemMap;
				item.setItemCode((String)labelPropGbnMap.get("ITEM_CODE"));
				List itemList = bbsFSvc.itemList(item);
				outHtml.append("<input type='hidden' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"' value='' />");
				outHtml2.append("<tr><th scope='row'><label for="+labelPropGbnMap.get("CONTENT_MAPPING_L")+"><span class='required'>"+strCompYn+"</span>"+labelPropGbnMap.get("LABEL_NAME")+"</label></th><td>");
				//인바라 답변여부알림 일 경우
				if( "ansCompCont".equals(labelPropGbnMap.get("CONTENT_MAPPING_L")) ){
					for(int iLoop=0; iLoop<itemList.size(); iLoop++){
						itemMap = (Map)itemList.get(iLoop);
						outHtml2.append("<input type='checkbox' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+(iLoop+1)+"' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"0' value='"+itemMap.get("CODE")+"' onchange='javascript:doASnsCompChk();' /><label for='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+(iLoop+1)+"'>"+itemMap.get("CODE_NAME")+"</label>");
					}
					outHtml2.append("&nbsp;<span style='color:red;'>※문의내용에 대한 답변여부를 알려드립니다.</span</td></tr>");
					outScript.append("\t if( !doASnsCompChk() ){ return; } \t");
				}else{
					for(int iLoop=0; iLoop<itemList.size(); iLoop++){
						itemMap = (Map)itemList.get(iLoop);
						outHtml2.append("<input type='checkbox' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+(iLoop+1)+"' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"0' value='"+itemMap.get("CODE")+"' /><label for='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+(iLoop+1)+"'>"+itemMap.get("CODE_NAME")+"</label>");
					}
				}
				outColSum.append("var vChkVal"+nLoop+"=''; \t $('input[name="+labelPropGbnMap.get("CONTENT_MAPPING_L")+"0]:checked').each(function() { \t vChkVal"+nLoop+" += $(this).val()+','; \t }); $('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"').val(vChkVal"+nLoop+".substring(0,vChkVal"+nLoop+".length-1)); \t ");
				
			//Writer
			}else if( "16021000".equals(labelPropGbnMap.get("LABEL_PROP_GBN")) ){
				if( ("C".equals(mode)||"R".equals(mode)) && "nameCont".equals( labelPropGbnMap.get("CONTENT_MAPPING_L")) ){
					if( ssName != "" ){
						outHtml2.append("<tr><th scope='row'><label for='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"'><span class='required'>"+strCompYn+"</span>"+labelPropGbnMap.get("LABEL_NAME")+"</label></th><td><input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"' value='"+ssName+"' class='w15' readonly='readonly'></td></tr>");
					}else{
						outHtml2.append("<tr><th scope='row'><label for='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"'><span class='required'>"+strCompYn+"</span>"+labelPropGbnMap.get("LABEL_NAME")+"</label></th><td><input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"' class='w15' readonly='readonly'></td></tr>");
					}
				}else if( ("C".equals(mode)||"R".equals(mode)) && "emailCont".equals( labelPropGbnMap.get("CONTENT_MAPPING_L")) ){
					if( ssEmail != "" ){
						outHtml2.append("<tr><th scope='row'><label for='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"'><span class='required'>"+strCompYn+"</span>"+labelPropGbnMap.get("LABEL_NAME")+"</label></th><td><input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"' value='' class='w15' readonly='readonly'></td></tr>");
					}else{
						outHtml2.append("<tr><th scope='row'><label for='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"'><span class='required'>"+strCompYn+"</span>"+labelPropGbnMap.get("LABEL_NAME")+"</label></th><td><input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"' class='w15' readonly='readonly'></td></tr>");
					}
				}else{
					outHtml2.append("<tr><th scope='row'><label for='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"'><span class='required'>"+strCompYn+"</span>"+labelPropGbnMap.get("LABEL_NAME")+"</label></th><td><input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"' class='w15' readonly='readonly'></td></tr>");
				}
				//outHtml2.append("<tr><th scope='row'><label for='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"'><span class='required'>"+strCompYn+"</span>"+labelPropGbnMap.get("LABEL_NAME")+"</label></th><td><input type='text' name='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"' id='"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"' class='w90'></td></tr>");
				//필수값 체크
				if( "Y".equals(labelPropGbnMap.get("LABEL_COMP_YN")) ){
					outScript.append("if( cm_is_empty($('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"').val()) ){ \t alert('"+labelPropGbnMap.get("LABEL_NAME")+"을(를) 입력해주세요.'); \t $('#"+labelPropGbnMap.get("CONTENT_MAPPING_L")+"').focus(); \t return; \t } \t ");
				}
			}
			
		}
		
		if( "U".equals(mode) ){
			outDetailDataSet.append(" } \t");
		}

		if( !"U".equals(mode) ){
			//수정일 경우 데이터 셋팅을 위한 Function
			outDetailDataSet.append("function doDetailDataSet(){ }\t");
		}
		
		//파일 맨밑으로
		outHtml2.append(outFileHtml.toString());
		outHtml.append(outHtml2.toString());
		outScript.append(outColSum.toString());
		outScript.append("if( !$('#userChk').is(':checked') ){ alert('개인정보수집 및 이용 확인하시고 동의하여 주세요.'); $('#userChk').focus(); return; }");
		outScript.append("document.bbsFVo.submit(); \t} \t");
		outScript.append(outScriptfn.toString());
		
		
		outScript.append(outDetailDataSet.toString());
		/*}*/ 
		resultMap.put("outHtml", outHtml.toString()); 
		resultMap.put("outScript", outScript.toString()); 
		
		System.out.println(outHtml.toString());
		
		return resultMap;
	}
	
	
	@SuppressWarnings("rawtypes")
	public String BoardValue(Map mappingMap, Map detailMap){
		String strValue = "";
		String strMappingKey = (String)mappingMap.get( "CONTENT_MAPPING_L" );
		Iterator iter = detailMap.keySet().iterator();
		 while( iter.hasNext() ) {
			 String key = (String)iter.next();
			 if( strMappingKey.equals(key) ){
				 strValue = (String)detailMap.get( key );
			 }
		 }
		 return strValue;
	}
	
	/**
	 * 게시판 상세화면
	 * @param request
	 * @param BbsFVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	@RequestMapping(value= {"/site/{strDomain}/ex/bbs/View.do","/site/{strDomain}/foffice/user/MyIntegBoardView.do"
						  , "/site/{strDomain}/foffice/user/MyIntegMinwonBoardView.do"})
	public String BoardView(HttpServletRequest request, @ModelAttribute("BbsFVo") BbsFVo bbsFVo, @ModelAttribute("BbsCommentFVo") BbsCommentFVo bbsCommentFVo
						   , ModelMap model, @PathVariable("strDomain") String strDomain) throws Exception {
		
		String strMsg = "";
		String returnUrl = "";
		try {
			int strBcIdx = 0;
			int strCbIdx = 0;
			String searchKey = "";
			String searchType = "";
			String searchTarget = ""; // 2020.08.03 이솔이 검색기능 
			String searchArea = "";   // 2020.08.03 이솔이 검색기능 
			String cdDepstep = "";    // 2020.08.03 이솔이 검색기능 
			String tgtTypeCd = "";
			int pageIndex = 1;
			int parentSeq = 0;
			String cateTypeCd = "";
			String searchYear = "";   // 2020.07.31 박종범 검색기능
			String searchCenter = ""; // 2020.08.10 이솔이 추가
			String searchRegDt = "";  // 2020.08.12 안정환 추가 
			String searchContent = "";  // 2020.08.12 이솔이 추가 
			String searchDepth = "";	//2020.08.24 안정환 추가 
			String cnt = ""; //2020.08.26 전진형 추가
			String userId="";
			String detailType = ""; //2020.09.07 전진형 추가
			String searchKind = ""; //2020.09.07 전진형 추가
			
			String abType = "";  //2020.09.14 안정환 추가 
					
			HttpSession ses = request.getSession();//2020.09.01 안정환추가 
			userId = (String)ses.getAttribute("ss_id");//2020.09.01 안정환추가 
					
			/*if( !equals(request.getParameter("bcIdx")) || null != request.getParameter("bcIdx") ){
				strBcIdx = request.getParameter("bcIdx");
				strCbIdx = request.getParameter("cbIdx");*/
			
				strBcIdx = bbsFVo.getBcIdx();
				strCbIdx = bbsFVo.getCbIdx();
				searchKey = bbsFVo.getSearchKey();
				searchType = bbsFVo.getSearchType();       // 2020.08.06 이솔이 검색조건 이름 수정
				searchTarget = bbsFVo.getSearchTarget();   // 2020.08.06 이솔이 검색조건 이름 수정 
				searchArea = bbsFVo.getSearchArea();       // 2020.08.06 이솔이 검색조건 이름 수정 
				cdDepstep = bbsFVo.getCdDepstep();
				pageIndex = bbsFVo.getPageIndex();
				tgtTypeCd = bbsFVo.getTgtTypeCd();
				parentSeq = bbsFVo.getParentSeq();
				cateTypeCd = bbsFVo.getCateTypeCd();
				searchYear = bbsFVo.getSearchYear();       //2020.07.31 박종범 검색조건추가
				searchCenter = bbsFVo.getSearchCenter();   // 2020.08.10 이솔이 추가
				searchContent = bbsFVo.getSearchContent(); // 2020.08.12 이솔이 추가
				searchDepth = bbsFVo.getSearchDepth();     // 2020.08.24 안정환 추가 
				cnt = bbsFVo.getCnt();  				   //2020.08.26 전진형 추가
				searchKind = bbsFVo.getSearchKind(); 	   //2020.09.07 전진형 추가
				detailType = request.getParameter("detailType"); //2020.09.07 전진형 추가
				
				abType = bbsFVo.getAbType();		//2020.09.14 안정환 추가 
			
			//게시판 정보 조회
			bbsFVo = bbsFSvc.boardInfo(bbsFVo);
			
			/*String BbsTempletFilePath = bbsFVo.getBbsTempletFilePath();
			String strFileName = bbsFVo.getFileName();*/
			String strTempletcode = bbsFVo.getBbsTempletGbn();
			String author = "N";
			
			
			Map authorMap = null;
			
			//게시글 읽기 권한 체크
			request.setAttribute("cbIdx", strCbIdx);
			request.setAttribute("bcIdx", strBcIdx);
			authorMap = BoardAuthorChk(bbsFVo.getReadGbn() ,request,"read");
			if( !Boolean.valueOf((Boolean)authorMap.get("flag")) ){
				return alert((String)authorMap.get("returnUrl") ,(String)authorMap.get("msg"), model);
			}
			
			//수정 권한 체크
			authorMap = BoardAuthorChk(bbsFVo.getModGbn() ,request,"mod");
			if( Boolean.valueOf((Boolean)authorMap.get("flag")) ){
				bbsFVo.setRegId((String)authorMap.get("ssId"));
				bbsFVo.setDupcode((String)authorMap.get("dupcode"));
			}
			//답글 권한 체크
			authorMap = BoardAuthorChk(bbsFVo.getAnswerGbn() ,request,"answer");
			if( Boolean.valueOf((Boolean)authorMap.get("flag")) ){
				author = "Y";
			}
			
			bbsFVo.setBcIdx(strBcIdx);
			bbsFVo.setCbIdx(strCbIdx);
			bbsFVo.setSearchKey(searchKey);
			bbsFVo.setSearchType(searchType);      // 2020.08.06 이솔이 검색조건 이름 수정 
			bbsFVo.setSearchTarget(searchTarget);  // 2020.08.06 이솔이 검색조건 이름 수정 
			bbsFVo.setSearchArea(searchArea);      // 2020.08.06 이솔이 검색조건 이름 수정  
			bbsFVo.setPageIndex(pageIndex);
			System.out.println("index check"+bbsFVo.getPageIndex());
			bbsFVo.setTgtTypeCd(tgtTypeCd);
			bbsFVo.setAuthor(author);
			bbsFVo.setBbsTempletGbn(strTempletcode);
			bbsFVo.setSearchYear(searchYear);
			bbsFVo.setSearchContent(searchContent);
			bbsFVo.setSearchDepth(searchDepth);
			bbsFVo.setCnt(cnt);  //2020.08.26 전진형 추가
			bbsFVo.setParentSeq(parentSeq); //2020.08.26 전진형 추가
			bbsFVo.setUserId(userId);//2020.09.01 안정환추가 
			bbsFVo.setDetailType(detailType); //2020.09.07 전진형 추가
			bbsFVo.setSearchKind(searchKind); //2020.09.07 전진형 추가
			bbsFVo.setAbType(abType);		//2020.09.14 안정환 추가 
			//조회수
			bbsFSvc.boardCountUpdate(bbsFVo);
			
			//게시글 상세 조회
			List detailList = null;
			detailList = bbsFSvc.boardContentDetail(bbsFVo);
			
			Map mediaMap = bbsFSvc.boardMediaPlayInfo(bbsFVo);//2020.09.01 안정환추가 
			
			//게시글 상세 조회 - 전체
			Map detailMap = bbsFSvc.boardUpdateDetail(bbsFVo);
			System.out.println("abType : "+bbsFVo.getAbType());
			System.out.println("rqabType : "+request.getParameter("abType"));
			if (cnt != null) {
				if (cnt.equals("2")) {
					bbsFVo.setBcIdx(strBcIdx+1);
					Map reDetailMap = bbsFSvc.boardReDetail(bbsFVo);
					List refileList = bbsFSvc.boardFileList(bbsFVo);
					model.addAttribute("reDetailMap", reDetailMap);
					model.addAttribute("refileList", refileList);
				}				
			}
			//bbsFVo.setDupcode((String)ses.getAttribute("ss_dupkey"));
			//비회원 Dupcode 20200602
			bbsFVo.setBcIdx(strBcIdx);
			bbsFVo.setDupcode(ses.getAttribute("ss_dupkey")!=null?(String)ses.getAttribute("ss_dupkey"):"guest");
			
			//비공개 게시글 접근할 경우
			if( detailList.size() > 0 ){
				BbsFVo bbsFVo1 = (BbsFVo)detailList.get(0); 
				if( "21000100".equals(bbsFVo1.getOpenynCont()) || "AD_Y".equals(bbsFVo1.getOpenynCont()) ){
					if( bbsFVo.getDupcode() == null || !bbsFVo.getDupcode().equals(bbsFVo1.getDupcode()) ){
						return alert("/site/"+strDomain+"/ex/bbs/List.do?cbIdx="+bbsFVo.getCbIdx(), "비공개 게시글 입니다.", model);
					}
				}
				
			}else{
				return alert("", "잘못된 접근경로 입니다.", model);
			}
			
			Map MinwonMap = bbsFSvc.boardMinwonDetail(bbsFVo);
			
			//게시글 파일 조회
			List fileList = bbsFSvc.boardFileList(bbsFVo);
		
			//이전글 다음글
			Map prevNextList = bbsFSvc.boardPrevNextList(bbsFVo);
			/*20200910 page index 추가*/
			Map nebfPageIndex = bbsFSvc.selectNewPageIndex(bbsFVo);
			//일반 민원 답변
			/*if( "16050700".equals(strTempletcode) && 230 != bbsFVo.getCbIdx() ){
				List minwonReList = bbsFSvc.minwonReList(bbsFVo);
				String McIdx ="";
				for(int nLoop=0; nLoop<minwonReList.size(); nLoop++){
					BbsFVo fileMcIdx = (BbsFVo) minwonReList.get(nLoop);
					McIdx += fileMcIdx.getMcIdx()+",";
				}
				if( McIdx.length() > 0){
					McIdx = McIdx.substring(0,McIdx.length()-1);
					bbsFVo.setMcIdx(McIdx);
				}
				List minwonFileList = bbsFSvc.boardFileList(bbsFVo);
				List fileMinwonImgList = bbsFSvc.boardFileImgList(bbsFVo);
				
				model.addAttribute("minwonReList", minwonReList);
				model.addAttribute("minwonFileList", minwonFileList);
				model.addAttribute("fileMinwonImgList", fileMinwonImgList);
			//인바라 민원 답변
			}else*/ if( "bbs_minwon".equals(strTempletcode) && 414 == bbsFVo.getCbIdx() ){
				int minwonReCnt = bbsFSvc.minwonReCnt(bbsFVo);
				String audit = bbsFSvc.minwonReAuditCode(bbsFVo);
				
				BbsFVo bbsFVo1 = (BbsFVo)detailList.get(0);
				String aaaaaa = bbsFVo1.getMwStatusCont();
			
				String aaa = bbsFVo.getMwStatusCont();
				
				int minwonDeptCount = bbsFSvc.retrieveMinwonDeptCount(bbsFVo);
				bbsFVo.setAuditYn(audit);
				
				bbsFVo.setDupcode((String)ses.getAttribute("ss_dupkey"));
				
				List minwonReTotalList = null;
				if("27000300".equals(audit) ){
					minwonReTotalList = bbsFSvc.minwonReDeptList(bbsFVo);
				}else if(minwonReCnt > 1){
						minwonReTotalList = bbsFSvc.minwonReTotalList(bbsFVo);
				}else{
					minwonReTotalList = bbsFSvc.minwonReDeptList(bbsFVo);
				}
				String mcIdx ="";
				//int bcIdx = 0;
				for(int nLoop=0; nLoop<minwonReTotalList.size(); nLoop++){
					BbsFVo fileMcIdx = (BbsFVo) minwonReTotalList.get(nLoop);
					mcIdx += fileMcIdx.getMcIdx()+",";
					/*bcIdx += fileMcIdx.getBcIdx()+",";*/
				}
				if( mcIdx.length() > 0){
					mcIdx = mcIdx.substring(0,mcIdx.length()-1);
					/*bcIdx = bcIdx.substring(0,bcIdx.length()-1);*/
					bbsFVo.setMcIdx(mcIdx);
					//bbsFVo.setBcIdx(bcIdx);
				}								
				
				List minwonFileList = bbsFSvc.boardFileList(bbsFVo);
				List fileMinwonImgList = bbsFSvc.boardFileImgList(bbsFVo);
				
				model.addAttribute("minwonReTotalList", minwonReTotalList);
				model.addAttribute("minwonFileList", minwonFileList);
				model.addAttribute("fileMinwonImgList", fileMinwonImgList);
				model.addAttribute("minwonReCnt", minwonReCnt);
				model.addAttribute("minwonDeptCount", minwonDeptCount);
			}
			
			
/*			Map labelPropGbnMap;
			List labelPropGbnList  = bbsFSvc.boardlabelPropGbnList(bbsFVo);
			
			List itemList = new ArrayList();
			System.out.println("labelPropGbnList:"+labelPropGbnList.size());
			
			for(int i=0;i<labelPropGbnList.size();i++){
				labelPropGbnMap = (Map)labelPropGbnList.get(i);
				if(labelPropGbnMap.get("LABEL_PROP_GBN")!=null){
					if(
							labelPropGbnMap.get("LABEL_PROP_GBN").equals("16020700")
							|| labelPropGbnMap.get("LABEL_PROP_GBN").equals("16020800")
							|| labelPropGbnMap.get("LABEL_PROP_GBN").equals("16020900")
					){
						BbsFVo item = new BbsFVo();
						item.setItemCode((String)labelPropGbnMap.get("ITEM_CODE"));
						itemList.add(bbsFSvc.itemList(item));
					}
				}
			}
			
			
			model.addAttribute("itemList", itemList);
*/
			/*if(strCbIdx == 356){
				System.out.println("----------------------------------------------------------------");					
				bbsFVo.setSearchKey2(searchKey2);
				bbsFVo.setCdDepstep(cdDepstep);
				System.out.println("----------------------------------------------------------------");
			}*/
			bbsFVo.setCateTypeCd(cateTypeCd);
			model.addAttribute("detailList", detailList);
			model.addAttribute("detailMap", detailMap);
			model.addAttribute("prevNextList", prevNextList);
			model.addAttribute("MinwonMap", MinwonMap);
			model.addAttribute("bbsFVo", bbsFVo);
			model.addAttribute("fileList", fileList);
			model.addAttribute("strDomain", strDomain);
			model.addAttribute("mediaMap", mediaMap);
			model.addAttribute("changePage", nebfPageIndex);

			EzBbsTempletVo ezBbsTempletVo= new EzBbsTempletVo();
			ezBbsTempletVo.setBbsTempletGbn(bbsFVo.getBbsTempletGbn());
			ezBbsTempletVo = ezBbsTempletSvc.selectEzBbsTemplet(ezBbsTempletVo);
			//댓글 List
			if(bbsFVo.getCommentYn().equals("Y")){
				//권한
				authorMap = BoardAuthorChk(bbsFVo.getReadGbn() ,request,"read");
				if( Boolean.valueOf((Boolean)authorMap.get("flag")) ){
					bbsCommentFVo.setRegId((String)authorMap.get("ssId"));
				}
				int totCnt=0;
				/** paging */
				PaginationInfo paginationInfo = new PaginationInfo();
				paginationInfo.setCurrentPageNo(bbsCommentFVo.getPageIndex());
				paginationInfo.setRecordCountPerPage(bbsCommentFVo.getPageUnit());
				paginationInfo.setPageSize(bbsCommentFVo.getPageSize());
	
				bbsCommentFVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
				bbsCommentFVo.setLastIndex(paginationInfo.getLastRecordIndex());
				bbsCommentFVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
				
				List<BbsCommentFVo> resultList = new ArrayList<BbsCommentFVo>();
				if(bbsFVo!=null || bbsFVo.getBcIdx()!=0){
					resultList = bbsCommentFSvc.retrieveListComment(bbsCommentFVo);
					totCnt = bbsCommentFSvc.selectAllCommentTotCnt(bbsCommentFVo); 
					paginationInfo.setTotalRecordCount(totCnt);
				}
				model.addAttribute("totCnt", totCnt);
				model.addAttribute("resultList", resultList);
				model.addAttribute("paginationInfo", paginationInfo);
				model.addAttribute("BbsCommentVo", bbsCommentFVo);
			}
			returnUrl = "/injeinc/foffice/ex/bbs/"+ezBbsTempletVo.getViewCode();
			
		} catch (Exception e) {
			strMsg = Message.getMessage("901.code");	//에러
			//returnUrl = alert("/site/"+strDomain+"/main.do",SVC_MSGE, model);
			throw e;
		}
			
			

		return returnUrl;
		
	}
	
	
	
	/**
	 * 게시판 상세화면
	 * @param request
	 * @param BbsFVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
	@RequestMapping(value= {"/{strSitePath}/{strDomain}/ex/bbs/Delete.do","/{strSitePath}/{strDomain}/foffice/user/MyIntegBoardDelete.do"
						  , "/{strSitePath}/{strDomain}/foffice/user/MyIntegMinwonBoardDelete.do"})
	public String BoardDelete(HttpServletRequest request, @ModelAttribute("BbsFVo") BbsFVo bbsFVo
						   , ModelMap model, @PathVariable("strDomain") String strDomain) throws Exception {
		
		String SVC_MSGE = "";
		String returnUrl = "";
		try {
			int bcIdx = bbsFVo.getBcIdx();
			String gubPassword=bbsFVo.getGubPassword();
			String dupcode=bbsFVo.getDupcode();
			
			//게시판 정보 조회
			bbsFVo = bbsFSvc.boardInfo(bbsFVo);
			
			bbsFVo.setBcIdx(bcIdx);
			bbsFVo.setDupcode(dupcode);
			
			/*String BbsTempletFilePath = bbsFVo.getBbsTempletFilePath();
			String strFileName = bbsFVo.getFileName();*/
			String strTempletcode = bbsFVo.getBbsTempletGbn();
			Map authorMap = null;
			Map FileRmvMap = new HashMap<String, String>();
			
			//비회원 비밀번호 맞을시 수정가능 20200602
			request.setAttribute("gubPassword", bbsFVo.getGubPassword());
			request.setAttribute("cbIdx", bbsFVo.getCbIdx());
			request.setAttribute("bcIdx", bbsFVo.getBcIdx());
			
			//수정 권한 체크
			authorMap = BoardAuthorChk(bbsFVo.getModGbn() ,request,"mod");
			if( Boolean.valueOf((Boolean)authorMap.get("flag")) ){
				bbsFVo.setRegId((String)authorMap.get("ssId"));
				bbsFVo.setDupcode((String)authorMap.get("dupcode"));
				bbsFVo.setRegIp((String)authorMap.get("ssIp"));
			}
			
			FileRmvMap.put("bcIdx", bbsFVo.getBcIdx());
			FileRmvMap.put("cbIdx", bbsFVo.getCbIdx());
			FileRmvMap.put("regId", bbsFVo.getRegId());
			FileRmvMap.put("dupcode", bbsFVo.getDupcode());
			//게시글 삭제
			bbsFSvc.boardContentDelete(bbsFVo);
			bbsFSvc.boardRemoveFiles(FileRmvMap);
			
			
			
		} catch (Exception e) {
			SVC_MSGE = Message.getMessage("901.code");	//에러
			//returnUrl = alert("/site/"+strDomain+"/main.do",SVC_MSGE, model);
			throw e;
		}
		
		//마이페이지 나의게시글 상세
		if( "/site/{strDomain}/foffice/user/MyIntegBoardDelete.do".equals(request.getRequestURI()) ){
			return alert("/site/{strDomain}/foffice/user/MyIntegBoardList.do", Message.getMessage("501.message"), model);
    	}else if( "/site/{strDomain}/foffice/user/MyIntegMinwonBoardDelete.do".equals(request.getRequestURI()) ){ 
    		return alert("/site/{strDomain}/foffice/user/MyInteMinwongBoardList.do", Message.getMessage("501.message"), model);
    	}else{
    		return alert("/site/"+strDomain+"/ex/bbs/List.do?cbIdx="+bbsFVo.getCbIdx(), Message.getMessage("501.message"), model);
    	}
		
	}
	
	
	//게시판권한 체크
	/*세션체크
	16010100	모두
	16010200	본인글만
	16010300	회원
	16010400	본인확인
	16010500	관리자
	16010600	스마트환경
	16010700	아고라회원*/
	@SuppressWarnings("rawtypes")
	public Map<String, Object> BoardAuthorChk(String Gbn, HttpServletRequest request, String type)throws Exception {
		HttpSession ses = request.getSession();
		String ssLevel = (String)ses.getAttribute("ss_level");
		System.out.println("ssLevel :: "+ssLevel);
//		List ssUserGroup = (List)ses.getAttribute("userGroup");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		boolean flag = false;
		String ssId = "";
		String dupcode = "";
		String strMsg = "";
		String regIp = "";
		String returnUrl = null;
//		UserFVo userFVo = null;
		
		if( !"".equals(ssLevel) && null != ssLevel ){
			if( "7".equals(ssLevel) ){
				ssId = (String)ses.getAttribute("ss_id"); 
				dupcode = (String)ses.getAttribute("ss_dupkey");
				regIp = (String)ses.getAttribute("ss_ip");
			}else if( ssLevel.equals("8") ){
				ssId = "실명인증";
				dupcode = (String)ses.getAttribute("ss_dupkey");
				regIp = (String)ses.getAttribute("ss_ip");
			}else if( ssLevel.equals("11") ){
				EgovMap emap = (EgovMap)ses.getAttribute("SessionTeenagerVo");				
				ssId = (String)emap.get("userid");
				dupcode = (String)emap.get("userid");
				regIp = (String)emap.get("ip");
			}
		}
		
		if( "16010100".equals(Gbn) ){//권한없음
			flag = true;
		}else if( "16010400".equals(Gbn) && ssLevel != null ){//관리자
			flag = true;
		}else if( "16010300".equals(Gbn) && "7".equals(ssLevel) ){//회원
			flag = true;
		}else if( "16010200".equals(Gbn) && ssLevel != null ){//본인게시물
			HashMap<Object, Object> map = new HashMap<Object, Object>();
			map.put("parentSeq", request.getAttribute("parentSeq"));
			map.put("bcIdx", request.getAttribute("bcIdx"));
			map.put("cbIdx", request.getAttribute("cbIdx"));
			String regId = bbsFSvc.retrieveRegId(map);
			
			if(type.equals("read") || type.equals("answer")){
				if(ssId.equals(regId)){
					flag = true;
				}else{
					flag = false;
					strMsg = "읽기(본인게시물) 권한이 없습니다.";
					returnUrl = "";
				}
			}else if(type.equals("list")){
				flag = true;
			}else if(type.equals("mod")){
				if(ssId.equals(regId)){
					flag = true;
				}else{
					flag = false;
					strMsg = "수정(본인게시물) 권한이 없습니다.";
					returnUrl = "";
				}
				//flag = true;
			}else if(type.equals("answer")){
				if(ssId.equals(regId)){
					flag = true;
				}else{
					flag = false;
					strMsg = "읽기(본인게시물) 권한이 없습니다.";
					returnUrl = "";
				}
			}else{
				flag = false;
			}
			
		}else if("16010200".equals(Gbn) && ssLevel == null && request.getAttribute("gubPassword") != null && type.equals("mod")){//비회원 비밀번호 맞을시 수정가능 20200602
			flag = false;
			String gubPassword = (String) request.getAttribute("gubPassword");
			BbsFVo bbsFVo = new BbsFVo();
			Map result=null;
			if(request.getAttribute("bcIdx") != null && request.getAttribute("cbIdx") != null) {
				bbsFVo.setBcIdx((int) request.getAttribute("bcIdx"));
				bbsFVo.setCbIdx((int) request.getAttribute("cbIdx"));
				result=bbsFSvc.boardUpdateDetail(bbsFVo);
			}
			if(result!=null && result.get("gubPassword").equals(gubPassword)) {
				flag = true;
				dupcode="guest";
			}else{
				strMsg = "비밀번호가 틀립니다.";
				returnUrl = "";
			}
		}else{
			flag = false;
			if( "16010300".equals(Gbn) && "8".equals(ssLevel) ){
				strMsg = Message.getMessage("602.message");
				returnUrl = "";
			}else{
				strMsg = Message.getMessage("602.message");
				returnUrl = "";
			}
		}
				
		resultMap.put("flag", flag); 
		resultMap.put("ssId", ssId); 
		resultMap.put("dupcode", dupcode); 
		resultMap.put("regIp", regIp); 
		resultMap.put("msg", strMsg); 
		resultMap.put("returnUrl", returnUrl); 
		
		
		return resultMap;
	}
	
	
	/** 권한체크 */
	/*@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/site/{strDomain}/ex/bbs/BoardAuthorChkAx.do")
	public void boardAuthorChkAx(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String gbn = EgovStringUtil.nullConvert(request.getParameter("gbn"));
		Map authorMap = BoardAuthorChk(gbn, request);
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("returnUrl", authorMap.get("returnUrl"));
		jsonMap.put("flag", authorMap.get("flag"));
		jsonMap.put("msg", authorMap.get("msg"));
		jsonView.render(jsonMap, request, response);
		
	}*/
	
	
	/** 이미지 호출 및 다운로드 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ResponseBody
	@RequestMapping("/site/{strDomain}/ex/bbs/fileDownload.do")
	public void fileDownload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CmmFilesCtr cfc = new CmmFilesCtr(); 
		int bcIdx = EgovStringUtil.zeroConvert((request.getParameter("bcIdx")));
		int cbIdx = EgovStringUtil.zeroConvert((request.getParameter("cbIdx")));
		int fileNo = EgovStringUtil.zeroConvert((request.getParameter("fileNo")));
		String mcIdx = EgovStringUtil.nullConvert(request.getParameter("mcIdx"));
		
		Map param = new HashMap<String, String>();
		
		param.put("bcIdx", bcIdx);
		param.put("cbIdx", cbIdx);
		param.put("fileNo", fileNo);
		param.put("mcIdx", mcIdx);
		
		Map fileDownMap = bbsFSvc.boardFileDown(param);
		
		if( fileDownMap == null ){
			System.out.println("bcIdx="+bcIdx+",cbIdx="+cbIdx+",fileNo="+fileNo+",mcIdx="+mcIdx);
			throw new Exception("Download File is null");
		}
		
		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
		String downFileName = rootPath + (String)fileDownMap.get("FILE_STRE_COURS") + (String)fileDownMap.get("STRE_FILE_NM");
		/*response.setContentType("application/octet-stream; charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode((String)fileDownMap.get("STRE_FILE_NM"), "UTF-8")  + "\"");
		response.setHeader("Content-Transfer-Encoding", "binary");
		FileCopyUtils.copy(new FileInputStream(new File(rootPath + (String)fileDownMap.get("FILE_STRE_COURS"),(String)fileDownMap.get("STRE_FILE_NM"))), response.getOutputStream());*/    

		
		File file = new File(EgovWebUtil.filePathBlackList(downFileName));
		int fSize = (int) file.length();
		
		if (fSize > 0) {
			BufferedInputStream fin = null;

			try {
	
				fin = new BufferedInputStream(new FileInputStream(file));
				String mimetype = "application/x-msdownload";
	 
				response.setBufferSize(fSize);
				response.setContentType(mimetype);
				cfc.setDisposition((String)fileDownMap.get("ORIGNL_FILE_NM"), request, response);
				response.setContentLength(fSize);
				
				FileCopyUtils.copy(fin, response.getOutputStream());
				fin.close();
			}catch(FileNotFoundException e) {
				throw e;
			}catch(IOException e) {
				throw e;
			}finally {
				EgovResourceCloseHelper.close(fin);
			}
			response.getOutputStream().flush();
			response.getOutputStream().close();
			
		}
		
		
	}
	
	public String getComma(String strVal) {
        DecimalFormat formatter = new DecimalFormat("#,###,###,##0");
        return formatter.format(Long.parseLong(strVal));
    }
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/site/{strDomain}/ex/bbs/File_RmvAx.do")
	public void FileRmvAx(HttpServletRequest request ,HttpServletResponse response) throws Exception {
		
			HttpSession ses = request.getSession();
			String ssLevel = (String)ses.getAttribute("ss_level");
			String regId = "";
				if( !"".equals(ssLevel) && null != ssLevel ){
					if( "7".equals(ssLevel) ){
						regId = (String)ses.getAttribute("ss_id"); 
					}else if( ssLevel.equals("8") ){
						regId = (String)ses.getAttribute("ss_dupkey");
					}
				}
			
			Map param = new HashMap<String, String>();
			
			param.put("bcIdx", EgovStringUtil.nullConvert(request.getParameter("bcIdx")));
			param.put("cbIdx", EgovStringUtil.nullConvert(request.getParameter("cbIdx")));
			param.put("fileNo", EgovStringUtil.nullConvert(request.getParameter("fileNo")));
			param.put("mcIdx", EgovStringUtil.nullConvert(request.getParameter("mcIdx")));
			param.put("regId", regId);
			
			bbsFSvc.boardRemoveFiles(param);
			
			HashMap<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("result", "ok");
			jsonView.render(jsonMap, request, response);
			
	}
	
	
    /** 게시판추천 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ResponseBody
	@RequestMapping("/site/{strDomain}/ex/bbs/BoardRecommendSaveAx.do")
	public void boardRecommendSaveAx(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("BbsFVo") BbsFVo bbsFVo) throws Exception {
		
		HttpSession ses = request.getSession();
		bbsFVo.setRegId((String)ses.getAttribute("ss_id"));
		bbsFVo.setRegIp(request.getRemoteAddr()+"");
		
		int recommendCount = bbsFSvc.bbsRecommentCount(bbsFVo);
		String message = "";
		if(recommendCount > 0){
			message = "no";
		}else{
			message = "yes";
			bbsFSvc.bbsRecommentInsert(bbsFVo);
		}
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", message);
		jsonView.render(jsonMap, request, response);
		
	}
	
	
	/* 댓글 */
	@RequestMapping("/site/{strDomain}/ex/bbs/BbsCommentReg.do")
	public String BbsCommentReg(@PathVariable("strDomain") String strDomain, HttpServletRequest request, @ModelAttribute("BbsCommentFVo") BbsCommentFVo bbsCommentFVo, @ModelAttribute("BbsFVo") BbsFVo bbsFVo, ModelMap model) throws Exception {
		String message = "";
		String returnUrl="/site/"+strDomain+"/ex/bbs/View.do?cbIdx="+bbsCommentFVo.getCbIdx()+"&bcIdx="+bbsCommentFVo.getBcIdx()+"&pageIndex="+bbsCommentFVo.getPageIndex()+"#comment_box";
		//권한
		bbsFVo=bbsFSvc.boardInfo(bbsFVo);
		Map authorMap = BoardAuthorChk(bbsFVo.getReadGbn() ,request,"read");
		String regId="";
		if( Boolean.valueOf((Boolean)authorMap.get("flag")) ){
			regId=(String)authorMap.get("ssId");
			bbsCommentFVo.setRegIp((String)authorMap.get("regIp"));
		}
		
		if(!EgovStringUtil.isEmpty(regId)) {			
			if(bbsCommentFVo.getUprCmIdx()>0) {
				bbsCommentFVo.setCmIdx(bbsCommentFVo.getUprCmIdx());
				BbsCommentFVo bbsCommentVo2 = bbsCommentFSvc.retrieveBbsComment(bbsCommentFVo);
				
				if(bbsCommentVo2 != null) bbsCommentFVo.setCmDepth(bbsCommentVo2.getCmDepth() + 1);
			}
			HttpSession ses = request.getSession();
			String userName = (String)ses.getAttribute("ss_name");	
			bbsCommentFVo.setRegName(userName);
			bbsCommentFVo.setRegId(regId);
			
			String cmCont=bbsCommentFVo.getCmCont();
			cmCont = org.springframework.web.util.HtmlUtils.htmlEscape(cmCont);
			bbsCommentFVo.setCmCont(cmCont);
			
			bbsCommentFSvc.registBbsComment(bbsCommentFVo);
			String SVC_MSGE = Message.getMessage("100.message");
			message = SVC_MSGE;
		}else {
			message = "잘못된 접근입니다.";
		}
		return alert(returnUrl, message, model);
	}
	
	@RequestMapping("/site/{strDomain}/ex/bbs/BbsCommentMod.do")
	public String BbsCommentMod(@PathVariable("strDomain") String strDomain, HttpServletRequest request, @ModelAttribute("BbsCommentFVo") BbsCommentFVo bbsCommentFVo, @ModelAttribute("BbsFVo") BbsFVo bbsFVo, ModelMap model) throws Exception {
		String message = "";
		String returnUrl="/site/"+strDomain+"/ex/bbs/View.do?cbIdx="+bbsFVo.getCbIdx()+"&bcIdx="+bbsFVo.getBcIdx()+"&pageIndex="+bbsFVo.getPageIndex()+"#comment_box";
		//권한
		bbsFVo=bbsFSvc.boardInfo(bbsFVo);
		Map authorMap = BoardAuthorChk(bbsFVo.getReadGbn() ,request,"read");
		if( Boolean.valueOf((Boolean)authorMap.get("flag")) ){
			bbsCommentFVo.setRegId((String)authorMap.get("ssId"));
			bbsCommentFVo.setModIp((String)authorMap.get("regIp"));
		}

		BbsCommentFVo bbsCommentFVo2=null;
		if(bbsCommentFVo.getRegId()!=null && bbsCommentFVo.getRegId()!="") {
			bbsCommentFVo2 = bbsCommentFSvc.retrieveBbsComment(bbsCommentFVo);
		}
		
		if(bbsCommentFVo2!=null) {
			bbsCommentFVo.setModId(bbsCommentFVo.getRegId());
			bbsCommentFSvc.modifyBbsComment(bbsCommentFVo);
			String SVC_MSGE = Message.getMessage("100.message");
			message = SVC_MSGE;
		}else {
			message = "잘못된 접근입니다.";
		}
		
		return alert(returnUrl, message, model);
		
	}
	@RequestMapping("/site/{strDomain}/ex/bbs/BbsCommentRmv.do")
	public String BbsCommentRmv(@PathVariable("strDomain") String strDomain, HttpServletRequest request, @ModelAttribute("BbsCommentFVo") BbsCommentFVo bbsCommentFVo, @ModelAttribute("BbsFVo") BbsFVo bbsFVo, ModelMap model) throws Exception {
		String message = "";
		String returnUrl="/site/"+strDomain+"/ex/bbs/View.do?cbIdx="+bbsFVo.getCbIdx()+"&bcIdx="+bbsFVo.getBcIdx()+"&pageIndex="+bbsFVo.getPageIndex()+"#comment_box";
		//권한
		bbsFVo=bbsFSvc.boardInfo(bbsFVo);
		int bcIdx = bbsCommentFVo.getBcIdx();
		int cbIdx = bbsCommentFVo.getCbIdx();
		request.setAttribute("bcIdx",  bcIdx);
		request.setAttribute("cbIdx", cbIdx);
		Map authorMap = BoardAuthorChk(bbsFVo.getReadGbn() ,request,"read");
		if( Boolean.valueOf((Boolean)authorMap.get("flag")) ){
			bbsCommentFVo.setRegId((String)authorMap.get("ssId"));
			bbsCommentFVo.setModIp((String)authorMap.get("regIp"));
		}
		BbsCommentFVo bbsCommentFVo2=null;
		if(bbsCommentFVo.getRegId()!=null && bbsCommentFVo.getRegId()!="") {
			bbsCommentFVo2 = bbsCommentFSvc.retrieveBbsComment(bbsCommentFVo);
		}
		
		if(bbsCommentFVo2!=null) {
			bbsCommentFSvc.removeBbsComment(bbsCommentFVo);
			String SVC_MSGE = Message.getMessage("100.message");
			message = SVC_MSGE;
		}else {
			message = "잘못된 접근입니다.";
		}
		
		return alert(returnUrl, message, model);
	}
	
	@RequestMapping(value= {"/site/{strDomain}/ex/bbs/mediaPlay.do"})
	public String mediaBoardView(HttpServletRequest request, @ModelAttribute("BbsFVo") BbsFVo bbsFVo, @ModelAttribute("BbsCommentFVo") BbsCommentFVo bbsCommentFVo
				   , ModelMap model, @PathVariable("strDomain") String strDomain) throws Exception {
		
		String returnUrl="/site/"+strDomain+"/ex/bbs/View.do?cbIdx="+bbsFVo.getCbIdx()+"&bcIdx="+bbsFVo.getBcIdx()+"&pageIndex="+bbsFVo.getPageIndex();
		System.out.println("bbsFVo : {}"+bbsFVo);
		
		HttpSession ses = request.getSession();
		
		int cbIdx2 = EgovStringUtil.zeroConvert(request.getParameter("cbIdx"));
		int bcIdx2 = EgovStringUtil.zeroConvert(request.getParameter("bcIdx"));
		
		String userId ="";
		int cbIdx = 0;
		int bcIdx = 0;
		double playTime = 0;
		String courseYn = "";
		double duration = 0;
		
		userId = (String)ses.getAttribute("ss_id");
		cbIdx = bbsFVo.getCbIdx();
		bcIdx = bbsFVo.getBcIdx();
		playTime = bbsFVo.getCurrentTime();
		duration = bbsFVo.getDuration();
		courseYn = "";
		
		playTime = Math.floor(playTime);
		duration = Math.floor(duration);
		
		if(duration - playTime < 30){
			courseYn = "Y";
		}
		bbsFVo.setUserId(userId);
		bbsFVo.setCbIdx(cbIdx);
		bbsFVo.setBcIdx(bcIdx);
		bbsFVo.setPlayTime(playTime);
		bbsFVo.setCourseYn(courseYn);
		
		bbsFSvc.boardMediaPlayInsert(bbsFVo);
		
		return returnUrl;
	}
}