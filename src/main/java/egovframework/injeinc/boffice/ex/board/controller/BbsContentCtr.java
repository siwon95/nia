package egovframework.injeinc.boffice.ex.board.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.hsqldb.lib.StringUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.util.WebUtils;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.EgovWebUtil;
import egovframework.cmm.Message;
import egovframework.cmm.service.AuthoritySvc;
import egovframework.cmm.service.EgovProperties;
import egovframework.cmm.vo.AuthorityVo;
import egovframework.injeinc.boffice.cn.common.UtilSvc;
import egovframework.injeinc.boffice.ex.bbs.boardAdmin.service.BoardAdminSvc;
import egovframework.injeinc.boffice.ex.board.service.BbsContentSvc;
import egovframework.injeinc.boffice.ex.board.service.ContentFileSvc;
import egovframework.injeinc.boffice.ex.board.vo.BbsContentVo;
import egovframework.injeinc.boffice.ex.board.vo.ContentFileVo;
import egovframework.injeinc.boffice.excel.controller.ExcelCtr;
import egovframework.injeinc.boffice.group.dept.service.GroupDeptSvc;
import egovframework.injeinc.boffice.sy.board.service.BoardSvc;
import egovframework.injeinc.boffice.sy.board.service.CmsBbsSvc;
import egovframework.injeinc.boffice.sy.board.vo.BoardVo;
import egovframework.injeinc.boffice.sy.board.vo.CmsBbsVo;
import egovframework.injeinc.boffice.sy.board.vo.ConfigPropertyVo;
import egovframework.injeinc.boffice.sy.board.vo.TreeBoardVo;
import egovframework.injeinc.common.util.DateUtil;
import egovframework.injeinc.common.util.EgovFormBasedUUID;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


@Controller
public class BbsContentCtr extends CmmLogCtr {
	
	private Logger logger = Logger.getLogger(getClass());
	
	@Resource(name = "AuthoritySvc")
	private AuthoritySvc authoritySvc;
	
	@Resource(name = "BbsContentSvc")
	private BbsContentSvc bbsContentSvc;
	
	@Resource(name = "BoardSvc")
	private BoardSvc boardSvc;
	
	@Resource(name = "ContentFileSvc")
	private ContentFileSvc contentFileSvc;

	@Resource(name = "BoardAdminSvc")
	private BoardAdminSvc boardAdminSvc;

	@Resource(name = "GroupDeptSvc")
	private GroupDeptSvc groupDeptSvc;
	
	@Resource(name = "CmsBbsSvc")
	private CmsBbsSvc cmsBbsSvc;
	
	@Resource(name = "UtilSvc")
	private UtilSvc utilSvc;
	
	
	
	@Autowired(required=true)
	private MappingJackson2JsonView jsonView;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/boffice{strNodeco}/ex/board/BbsContentView.do")
	public String BbsContentView(@PathVariable("strNodeco") String strNodeco, HttpServletRequest request,
			@ModelAttribute("BoardVo") BoardVo boardVo,
			@ModelAttribute("ConfigPropertyVo") ConfigPropertyVo configPropertyVo,
			@ModelAttribute("BbsContentVo") BbsContentVo bbsContentVo,
			@ModelAttribute("ContentFileVo") ContentFileVo contentFileVo, ModelMap model) throws Exception {
		model.addAttribute("strNodeco", strNodeco);

		boardVo = boardSvc.retrieveBoard(boardVo);
		
		if(boardVo != null) {
			model.addAttribute("BoardVo", boardVo);
		}else{
			String SVC_MSGE ="잘못된 접근입니다.";
			return alert(request.getContextPath() + "/boffice" + strNodeco + "/ex/board/BbsContentList.do", SVC_MSGE, model);
		}
		
		Map<String, Object> result = bbsContentSvc.retrieveBbsContent(bbsContentVo);
		
		if(result != null) {
			result.put("pageIndex", bbsContentVo.getPageIndex());
			result.put("pageUnit", bbsContentVo.getPageUnit());
			result.put("searchCondition", bbsContentVo.getSearchCondition());
			result.put("searchKeyword", bbsContentVo.getSearchKeyword());
			result.put("searchStartDate", bbsContentVo.getSearchStartDate());
			result.put("searchEndDate", bbsContentVo.getSearchEndDate());
			result.put("searchGroup", bbsContentVo.getSearchGroup());
			result.put("searchCbIdx", bbsContentVo.getSearchCbIdx());
			result.put("searchDelYn", bbsContentVo.getSearchDelYn());
			
			if(bbsContentVo.getCbIdx() == 280) { // 관리자 게시판만 카운트 추가
				bbsContentSvc.modifyBbsContentCount(bbsContentVo);
				int countCont = Integer.parseInt(String.valueOf(result.get("countCont")));
				result.put("countCont", countCont+1);
			}

			model.addAttribute("BbsContentVo", result);
		} else {
			String SVC_MSGE = "잘못된 접근입니다.";
			return alert(request.getContextPath() + "/boffice" + strNodeco + "/ex/board/BbsContentList.do", SVC_MSGE,
					model);
		}

		List propertylist = boardSvc.retrieveListConfigProperty(configPropertyVo);
		model.addAttribute("propertylist", propertylist);

		List fileList = contentFileSvc.retrieveListContentFile(contentFileVo);
		model.addAttribute("fileList", fileList);

		return "injeinc/boffice/ex/board/bbs_content_view";
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping("/boffice{strNodeco}/ex/board/nasSearch.do")
	public String nasSearch(@PathVariable("strNodeco") String strNodeco, HttpServletRequest request, ModelMap model)
			throws Exception {
		model.addAttribute("strNodeco", strNodeco);

		return "injeinc/boffice/ex/board/nas_search";
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/boffice{strNodeco}/ex/board/BbsContentForm.do")
	public String BbsContentForm(@PathVariable("strNodeco") String strNodeco, HttpServletRequest request,
			@ModelAttribute("BoardVo") BoardVo boardVo,
			@ModelAttribute("ConfigPropertyVo") ConfigPropertyVo configPropertyVo,
			@ModelAttribute("BbsContentVo") BbsContentVo bbsContentVo,
			@ModelAttribute("ContentFileVo") ContentFileVo contentFileVo, @RequestParam Map requestMap, ModelMap model)
			throws Exception {
		model.addAttribute("strNodeco", strNodeco);
		boardVo = boardSvc.retrieveBoard(boardVo);

		if (boardVo != null) {
			model.addAttribute("BoardVo", boardVo);
		} else {
			String SVC_MSGE = "잘못된 접근입니다.";
			return alert(request.getContextPath() + "/boffice" + strNodeco + "/ex/board/BbsContentList.do", SVC_MSGE,
					model);
		}

		Map<String, Object> result = bbsContentSvc.retrieveBbsContent(bbsContentVo);

		if (result != null) {
			result.put("actionkey", "modify");
			result.put("pageIndex", bbsContentVo.getPageIndex());
			result.put("pageUnit", bbsContentVo.getPageUnit());
			result.put("searchCondition", bbsContentVo.getSearchCondition());
			result.put("searchKeyword", bbsContentVo.getSearchKeyword());
			result.put("searchStartDate", bbsContentVo.getSearchStartDate());
			result.put("searchEndDate", bbsContentVo.getSearchEndDate());
			result.put("searchGroup", bbsContentVo.getSearchGroup());
			result.put("searchCbIdx", bbsContentVo.getSearchCbIdx());
			result.put("searchDelYn", bbsContentVo.getSearchDelYn());

			model.addAttribute("BbsContentVo", result);

			List fileList = contentFileSvc.retrieveListContentFile(contentFileVo);

			JSONArray jarr = null;
			JSONArray jarr2 = new JSONArray();
			JSONObject jobj = null;
			JSONObject jobj2 = null;
			ContentFileVo bbsFileVo = null;
			if (fileList.size() != 0 || fileList != null) {
				for (int fLoop = 0; fLoop < fileList.size(); fLoop++) {
					bbsFileVo = (ContentFileVo) fileList.get(fLoop);
					jobj = new JSONObject();
					jobj2 = new JSONObject();
					jarr = new JSONArray();
					jobj.put("tempname", bbsFileVo.getStreFileNm());
					jobj.put("size", Integer.parseInt(bbsFileVo.getFileSize()) / 1024 + " Kb");
					jobj.put("rsize", bbsFileVo.getFileSize());
					jobj.put("name", bbsFileVo.getOrignlFileNm());
					jobj.put("fileno", bbsFileVo.getFileNo());
					jarr.add(jobj);
					jobj2.put("files", jarr);
					jarr2.add(jobj2);
				}
			}
			System.out.println("multijson : " + jarr2.toJSONString());
			model.addAttribute("multijson", jarr2.toJSONString());
			model.addAttribute("fileList", fileList);
		} else {
			requestMap.put("actionkey", "regist");

			model.addAttribute("BbsContentVo", requestMap);
		}

		model.addAttribute("departList", groupDeptSvc.retrieveListCmsDepartGroup());

		List<TreeBoardVo> retrieveListCmsBbs = cmsBbsSvc.retrieveListCmsBbs();
		model.addAttribute("treeBoardVoList", retrieveListCmsBbs);

		List propertylist = boardSvc.retrieveListConfigProperty(configPropertyVo);
		model.addAttribute("propertylist", propertylist);

		return "injeinc/boffice/ex/board/bbs_content_form";
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/boffice{strNodeco}/ex/board/BbsContentReply.do")
	public String BbsContentReply(@PathVariable("strNodeco") String strNodeco, HttpServletRequest request,
			@ModelAttribute("BoardVo") BoardVo boardVo,
			@ModelAttribute("ConfigPropertyVo") ConfigPropertyVo configPropertyVo,
			@ModelAttribute("BbsContentVo") BbsContentVo bbsContentVo,
			@ModelAttribute("ContentFileVo") ContentFileVo contentFileVo, @RequestParam Map requestMap, ModelMap model)
			throws Exception {
		model.addAttribute("strNodeco", strNodeco);
		boardVo = boardSvc.retrieveBoard(boardVo);

		if (boardVo != null) {
			model.addAttribute("BoardVo", boardVo);
		} else {
			String SVC_MSGE = "잘못된 접근입니다.";
			return alert(request.getContextPath() + "/boffice" + strNodeco + "/ex/board/BbsContentList.do", SVC_MSGE,
					model);
		}

		Map<String, Object> result = bbsContentSvc.retrieveBbsContent(bbsContentVo);

		if (result == null) {
			String SVC_MSGE = "잘못된 접근입니다.";
			return alert(request.getContextPath() + "/boffice" + strNodeco + "/ex/board/BbsContentList.do", SVC_MSGE,
					model);
		}

		requestMap.put("actionkey", "regist");
		requestMap.put("bcIdx", "");
		requestMap.put("parentSeq", result.get("parentSeq"));
		requestMap.put("answerStep", result.get("answerStep"));
		requestMap.put("answerDepth", result.get("answerDepth"));
		requestMap.put("cateCont", result.get("cateCont"));
		model.addAttribute("BbsContentVo", requestMap);

		List propertylist = boardSvc.retrieveListConfigProperty(configPropertyVo);
		model.addAttribute("propertylist", propertylist);

		return "injeinc/boffice/ex/board/bbs_content_form";
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/boffice{strNodeco}/ex/board/BbsContentMove.do")
	public String BbsContentMove(@PathVariable("strNodeco") String strNodeco, HttpServletRequest request,
			@ModelAttribute("BbsContentVo") BbsContentVo bbsContentVo,
			@ModelAttribute("ConfigPropertyVo") ConfigPropertyVo configPropertyVo,
			@ModelAttribute("ContentFileVo") ContentFileVo contentFileVo, @RequestParam Map requestMap, ModelMap model)
			throws Exception {
		model.addAttribute("strNodeco", strNodeco);
		String mode = request.getParameter("mode");
		// 파라미터 값
		StringBuffer addParam = new StringBuffer();
		addParam.append("?searchCbIdx=").append(bbsContentVo.getSearchCbIdx());
		if (mode.equals("copy")) {
			bbsContentSvc.insertBbsContentCopy(bbsContentVo);
		} else {
			bbsContentSvc.updateBbsContentMove(bbsContentVo);
		}

		utilSvc.cacheSelectClear("mainCache");

		String SVC_MSGE;
		SVC_MSGE = Message.getMessage("100.message");
		return alert("/boffice" + strNodeco + "/ex/board/BbsContentList.do" + addParam.toString(), SVC_MSGE, model);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/boffice{strNodeco}/ex/board/BbsContentReg.do")
	public String BbsContentReg(@PathVariable("strNodeco") String strNodeco, HttpServletRequest request,
			@ModelAttribute("BoardVo") BoardVo boardVo, @ModelAttribute("BbsContentVo") BbsContentVo bbsContentVo,
			ModelMap model) throws Exception {
		model.addAttribute("strNodeco", strNodeco);
		// 파라미터 값
		StringBuffer addParam = new StringBuffer();
		addParam.append("?pageIndex=").append(bbsContentVo.getPageIndex());
		addParam.append("&pageUnit=").append(bbsContentVo.getPageUnit());
		addParam.append("&searchCondition=").append(bbsContentVo.getSearchCondition());
		addParam.append("&searchKeyword=").append(bbsContentVo.getSearchKeyword());
		addParam.append("&searchStartDate=").append(bbsContentVo.getSearchStartDate());
		addParam.append("&searchEndDate=").append(bbsContentVo.getSearchEndDate());
		addParam.append("&searchGroup=").append(bbsContentVo.getSearchGroup());
		addParam.append("&searchCbIdx=").append(bbsContentVo.getSearchCbIdx());
		addParam.append("&searchDelYn=").append(bbsContentVo.getSearchDelYn());
		if (bbsContentVo.getSearchCbIdx() != null && !bbsContentVo.getSearchCbIdx().isEmpty()) {
			CmsBbsVo cmsBbsVo = new CmsBbsVo();
			cmsBbsVo.setCbIdx(bbsContentVo.getSearchCbIdx() + "");
			cmsBbsVo = cmsBbsSvc.selectCmsBbs(cmsBbsVo);

			addParam.append("&mgrSiteCd=").append(cmsBbsVo.getMgrSiteCd());
			addParam.append("&searchCbCd=").append(cmsBbsVo.getCbCd());
		}

		String SVC_MSGE;

		boardVo = boardSvc.retrieveBoard(boardVo);

		if (boardVo == null) {
			SVC_MSGE = "잘못된 접근입니다.";
			return alert("/boffice" + strNodeco + "/ex/board/BbsContentList.do", SVC_MSGE, model);
		}

		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		bbsContentVo.setRegId(userid);
		bbsContentVo.setRegIp(request.getRemoteAddr());

		String notiYn = bbsContentVo.getNotiYn();
		if (!notiYn.equals("Y")) {
			bbsContentVo.setNotiYn("N");
		}

		if (boardVo.getBbsTempletGbn().equals("16050700")) {
			bbsContentVo.setAnsYnCont("22000100"); // 민원게시판일 경우 글을 쓰면 자동으로 답변요청
													// 상태로 저장됨
		}

		request.setAttribute("userid", userid);
		request.setAttribute("fileMaxSize", boardVo.getFileMaxSize());

		int bcIdx = 0;

		try {
			bcIdx = bbsContentSvc.registBbsContent(request, bbsContentVo);
		} catch (FileUploadException e) {
			e.printStackTrace();
			SVC_MSGE = Message.getMessage("905.message");
			return alert("/boffice" + strNodeco + "/ex/board/BbsContentList.do" + addParam.toString(), SVC_MSGE, model);
		}

		if (bbsContentVo.getClobAutoMakeYn().equals("Y")) {

			ContentFileVo contentFileVo = new ContentFileVo();
			contentFileVo.setBcIdx(bcIdx);
			contentFileVo.setCbIdx(bbsContentVo.getCbIdx());

			List<ContentFileVo> fileList = contentFileSvc.retrieveListContentFile(contentFileVo);

			StringBuffer sb = new StringBuffer();
			sb.append("<p>&nbsp;</p>");

			for (ContentFileVo resultVo : fileList) {
				sb.append("<p style=\"text-align: center\">");
				sb.append("<img style=\"WIDTH: 800px;\" alt=\"").append(bbsContentVo.getSubCont()).append(" 사진\"");
				sb.append(" src=\"").append("");
				sb.append(resultVo.getFileStreCours()).append(resultVo.getStreFileNm()).append("\" /></p>");
				sb.append("<p>&nbsp;</p>");
			}

			bbsContentVo.setBcIdx(bcIdx);
			bbsContentVo.setClobCont(sb.toString());
			bbsContentSvc.modifyBbsContentClobAutoMake(bbsContentVo);

		}

		utilSvc.cacheSelectClear("mainCache");

		SVC_MSGE = Message.getMessage("100.message");
		return alert("/boffice" + strNodeco + "/ex/board/BbsContentList.do" + addParam.toString(), SVC_MSGE, model);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/boffice{strNodeco}/ex/board/BbsContentMod.do")
	public String BbsContentMod(@PathVariable("strNodeco") String strNodeco, HttpServletRequest request,
			@ModelAttribute("BbsContentVo") BbsContentVo bbsContentVo, ModelMap model) throws Exception {
		model.addAttribute("strNodeco", strNodeco);
		// 파라미터 값
		StringBuffer addParam = new StringBuffer();
		addParam.append("?pageIndex=").append(bbsContentVo.getPageIndex());
		addParam.append("&pageUnit=").append(bbsContentVo.getPageUnit());
		addParam.append("&searchCondition=").append(bbsContentVo.getSearchCondition());
		addParam.append("&searchKeyword=").append(bbsContentVo.getSearchKeyword());
		addParam.append("&searchStartDate=").append(bbsContentVo.getSearchStartDate());
		addParam.append("&searchEndDate=").append(bbsContentVo.getSearchEndDate());
		addParam.append("&searchGroup=").append(bbsContentVo.getSearchGroup());
		addParam.append("&searchCbIdx=").append(bbsContentVo.getSearchCbIdx());
		addParam.append("&searchDelYn=").append(bbsContentVo.getSearchDelYn());
		addParam.append("&cbIdx=").append(bbsContentVo.getCbIdx());
		addParam.append("&bcIdx=").append(bbsContentVo.getBcIdx());
		if (bbsContentVo.getSearchCbIdx() != null && !bbsContentVo.getSearchCbIdx().isEmpty()) {
			CmsBbsVo cmsBbsVo = new CmsBbsVo();
			cmsBbsVo.setCbIdx(bbsContentVo.getSearchCbIdx() + "");
			cmsBbsVo = cmsBbsSvc.selectCmsBbs(cmsBbsVo);

			addParam.append("&mgrSiteCd=").append(cmsBbsVo.getMgrSiteCd());
			addParam.append("&searchCbCd=").append(cmsBbsVo.getCbCd());
		}
		String SVC_MSGE;

		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		bbsContentVo.setModId(userid);
		bbsContentVo.setModIp(request.getRemoteAddr());

		String notiYn = bbsContentVo.getNotiYn();
		if (!notiYn.equals("Y")) {
			bbsContentVo.setNotiYn("N");
		}

		String clobCont = bbsContentVo.getClobCont();
		if (!clobCont.equals("")) {
			bbsContentVo.setClobContSearch(clobCont);
		}

		BoardVo boardVo = new BoardVo();
		boardVo.setCbIdx(bbsContentVo.getCbIdx());

		boardVo = boardSvc.retrieveBoard(boardVo);

		request.setAttribute("userid", userid);
		request.setAttribute("fileMaxSize", boardVo.getFileMaxSize());
		try {
			bbsContentSvc.modifyBbsContent(request, bbsContentVo);
		} catch (FileUploadException e) {
			e.printStackTrace();
			SVC_MSGE = Message.getMessage("905.message");
			// return
			// alert("/boffice"+strNodeco+"/ex/board/BbsContentView.do"+addParam.toString(),
			// SVC_MSGE, model);
			return alert("/boffice" + strNodeco + "/ex/board/BbsContentList.do" + addParam.toString(), SVC_MSGE, model);
		}

		if (bbsContentVo.getClobAutoMakeYn().equals("Y")) {

			ContentFileVo contentFileVo = new ContentFileVo();
			contentFileVo.setBcIdx(bbsContentVo.getBcIdx());
			contentFileVo.setCbIdx(bbsContentVo.getCbIdx());

			List<ContentFileVo> fileList = contentFileSvc.retrieveListContentFile(contentFileVo);

			StringBuffer sb = new StringBuffer();
			sb.append("<p>&nbsp;</p>");

			for (ContentFileVo resultVo : fileList) {
				sb.append("<p style=\"text-align: center\">");
				sb.append("<img style=\"width:800px;\" alt=\"").append(bbsContentVo.getSubCont()).append(" 사진\"");
				sb.append(" src=\"").append("");
				sb.append(resultVo.getFileStreCours()).append(resultVo.getStreFileNm()).append("\" /></p>");
				sb.append("<p style=\"text-align: center\">&nbsp;</p>");
				sb.append("<p style=\"text-align: center\">&nbsp;</p>");
				sb.append("<p style=\"text-align: center\">&nbsp;</p>");
			}

			bbsContentVo.setClobCont(sb.toString());
			bbsContentSvc.modifyBbsContentClobAutoMake(bbsContentVo);
			model.addAttribute("departList", groupDeptSvc.retrieveListCmsDepartGroup());
		}

		utilSvc.cacheSelectClear("mainCache");

		SVC_MSGE = Message.getMessage("401.message");
		// return
		// alert("/boffice"+strNodeco+"/ex/board/BbsContentView.do"+addParam.toString(),
		// SVC_MSGE, model);
		return alert("/boffice" + strNodeco + "/ex/board/BbsContentList.do" + addParam.toString(), SVC_MSGE, model);
	}

	@RequestMapping(value = "/boffice/ex/board/File_Upload.do", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String upload(MultipartHttpServletRequest request, HttpServletResponse response) {

		// 1. build an iterator
		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf = null;
		JSONArray jarr = new JSONArray();
		JSONObject jobj = null;
		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
		String fileStreCours = Message.getMessage("board.file.upload.path");
		String cbIdx = request.getParameter("cbIdx");
		if (cbIdx != null && !cbIdx.equals("")) {
			fileStreCours = fileStreCours + cbIdx + "/";
		}
		// 2. get each file
		while (itr.hasNext()) {

			// 2.1 get next MultipartFile
			mpf = request.getFile(itr.next());

			// 2.3 create new fileMeta
			jobj = new JSONObject();

			jobj.put("name", mpf.getOriginalFilename());
			jobj.put("size", mpf.getSize() / 1024 + " Kb");
			jobj.put("rsize", mpf.getSize());
			jobj.put("type", mpf.getContentType());

			System.out.println("name : " + mpf.getOriginalFilename());
			System.out.println("size : " + mpf.getSize() / 1024 + " Kb");
			System.out.println("filetype : " + mpf.getContentType());
			System.out.println("rsize : " + mpf.getSize());

			try {

				// copy file to local disk (make sure the path "e.g.
				// D:/temp/files" exists)

				String orignlFileNm = mpf.getOriginalFilename();
				String fileExtsn = orignlFileNm.substring(orignlFileNm.lastIndexOf(".") + 1, orignlFileNm.length());
				String streFileNm = EgovFormBasedUUID.randomUUID().toString().toLowerCase() + "." + fileExtsn; // 전자정부
																												// UTIL
																												// 방법
				mpf.transferTo(new File(EgovWebUtil.filePathBlackList(rootPath + fileStreCours + streFileNm)));
				// FileCopyUtils.copy(mpf.getBytes(), new
				// FileOutputStream("D:/temp/files/"+streFileNm));
				jobj.put("savename", streFileNm);
				System.out.println("savename : " + streFileNm);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 2.4 add to files

			jarr.add(jobj);

		}

		System.out.println(JSONValue.toJSONString(jarr));
		return JSONValue.toJSONString(jarr);
	}

	@RequestMapping("/boffice{strNodeco}/ex/board/BbsContentRmv.do")
	public String BbsContentRmv(@PathVariable("strNodeco") String strNodeco, HttpServletRequest request,
			@ModelAttribute("BbsContentVo") BbsContentVo bbsContentVo, ModelMap model) throws Exception {
		model.addAttribute("strNodeco", strNodeco);
		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		bbsContentVo.setModId(userid);
		bbsContentVo.setModIp(request.getRemoteAddr());

		bbsContentSvc.removeBbsContent(bbsContentVo);

		StringBuffer addParam = new StringBuffer();
		addParam.append("?pageIndex=").append(bbsContentVo.getPageIndex());
		addParam.append("&pageUnit=").append(bbsContentVo.getPageUnit());
		addParam.append("&searchCondition=").append(bbsContentVo.getSearchCondition());
		addParam.append("&searchKeyword=").append(bbsContentVo.getSearchKeyword());
		addParam.append("&searchStartDate=").append(bbsContentVo.getSearchStartDate());
		addParam.append("&searchEndDate=").append(bbsContentVo.getSearchEndDate());
		addParam.append("&searchGroup=").append(bbsContentVo.getSearchGroup());
		addParam.append("&searchCbIdx=").append(bbsContentVo.getSearchCbIdx());
		addParam.append("&searchDelYn=").append(bbsContentVo.getSearchDelYn());
		if (bbsContentVo.getSearchCbIdx() != null && !bbsContentVo.getSearchCbIdx().isEmpty()) {
			CmsBbsVo cmsBbsVo = new CmsBbsVo();
			cmsBbsVo.setCbIdx(bbsContentVo.getSearchCbIdx() + "");
			cmsBbsVo = cmsBbsSvc.selectCmsBbs(cmsBbsVo);

			addParam.append("&mgrSiteCd=").append(cmsBbsVo.getMgrSiteCd());
			addParam.append("&searchCbCd=").append(cmsBbsVo.getCbCd());
		}
		utilSvc.cacheSelectClear("mainCache");

		String SVC_MSGE = Message.getMessage("501.message");
		return alert("/boffice" + strNodeco + "/ex/board/BbsContentList.do" + addParam.toString(), SVC_MSGE, model);
	}

	@RequestMapping("/boffice{strNodeco}/ex/board/BbsContentList.do")
	public String BbsContentList(HttpServletRequest request, @ModelAttribute("BbsContentVo") BbsContentVo bbsContentVo,
			ModelMap model) throws Exception {
		return "injeinc/boffice/ex/board/bbs_content_list";
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/boffice{strNodeco}/ex/board/BbsContentListPop.do")
	public String BbsContentListSet(@PathVariable("strNodeco") String strNodeco, HttpServletRequest request,
			@ModelAttribute("BbsContentVo") BbsContentVo bbsContentVo, ModelMap model) throws Exception {

		String treeLocationId = request.getParameter("treeLocationId"); // 게시판관리에서
																		// 게시물관리로
																		// 이동시
																		// set
																		// 되는
																		// treeID
		String searchCbCd = request.getParameter("searchCbCd");
		if (treeLocationId != null && !treeLocationId.equals("")) { // 게시판관리에서
																	// 게시물 관리로
																	// 이동시
																	// searchCbCd를
																	// treeLocationId로
																	// 변경한다.
			searchCbCd = treeLocationId;
		}
		String siteCd = "";
		String searchGroup = "";
		String searchCbIdx = "";
		String groupYn = "";

		if (searchCbCd == null || StringUtil.isEmpty(searchCbCd)) {
			siteCd = "";
			searchGroup = "";
			searchCbIdx = "";
			bbsContentVo.setSearchGroup("");
			bbsContentVo.setSearchCbIdx("");
		} else {
			siteCd = cmsBbsSvc.retrieveSiteCd(searchCbCd);
			searchGroup = cmsBbsSvc.retrieveGroup(searchCbCd);
			searchCbIdx = cmsBbsSvc.retrieveCbIdx(searchCbCd);
			groupYn = cmsBbsSvc.retrieveGroupYn(searchCbCd);

			if (searchGroup == null) {
				searchGroup = "";
			}
			if (searchCbIdx == null) {
				searchCbIdx = "";
			}

			bbsContentVo.setSearchGroup(searchGroup);
			bbsContentVo.setSearchCbIdx(searchCbIdx);
			bbsContentVo.setGroupYn(groupYn);
		}

		model.addAttribute("strNodeco", strNodeco);

		String SesMgrIdx = (String) WebUtils.getSessionAttribute(request, "SesMgrIdx");
		String SesUserRoleIdx = (String) WebUtils.getSessionAttribute(request, "SesUserRoleIdx");

		String SesUserPermCd = (String) WebUtils.getSessionAttribute(request, "SesUserPermCd");

		// String searchGroup = bbsContentVo.getSearchGroup();

		List<TreeBoardVo> retrieveListCmsBbs = cmsBbsSvc.retrieveListCmsBbs();
		model.addAttribute("treeBoardVoList", retrieveListCmsBbs);

		if (searchGroup.equals("")) {
			bbsContentVo.setSearchCbIdx("");
		}

		// String searchCbIdx = bbsContentVo.getSearchCbIdx();

		List searchGroupList = null;
		List searchCbIdxList = null;

		AuthorityVo authorityVo = new AuthorityVo();

		if (request.getParameter("siteCd") != null && searchGroup.equals("")) {
			searchGroup = request.getParameter("siteCd").replaceAll("null", "");
		}
		authorityVo.setCbUprCd(searchGroup);
		// authorityVo.setMgrIdx(SesMgrIdx);
		authorityVo.setMgrIdx(SesUserRoleIdx);

		if (SesUserPermCd.equals("05010000")) {
			searchGroupList = authoritySvc.retrieveListAuthorityBoardGroupMaster();
		} else {
			searchGroupList = authoritySvc.retrieveListAuthorityBoardGroup(authorityVo);
		}

		model.addAttribute("searchGroupList", searchGroupList);

		if (!searchGroup.equals("")) {

			if (SesUserPermCd.equals("05010000")) {
				searchCbIdxList = authoritySvc.retrieveListAuthorityBoardMaster(authorityVo);
			} else {
				searchCbIdxList = authoritySvc.retrieveListAuthorityBoard(authorityVo);
			}
		}

		model.addAttribute("searchCbIdxList", searchCbIdxList);

		if (searchCbIdx.equals("")) {

			ArrayList<String> searchCbIdxArr = null;

			if (SesUserPermCd.equals("05010000")) {
				searchCbIdxArr = (ArrayList<String>) authoritySvc.retrieveListAuthorityCbIdxMaster(authorityVo);
			} else {
				searchCbIdxArr = (ArrayList<String>) authoritySvc.retrieveListAuthorityCbIdx(authorityVo);
			}

			bbsContentVo.setSearchCbIdxArr(searchCbIdxArr);
		} else {
			if (!SesUserPermCd.equals("05010000")) {
				ArrayList<String> searchCbIdxArr = null;
				authorityVo.setCbIdx(searchCbIdx);
				searchCbIdxArr = (ArrayList<String>) authoritySvc.retrieveListAuthorityCbIdx(authorityVo);

				if (searchCbIdxArr.size() == 0) {
					String SVC_MSGE = "게시판 권한이 없습니다!";
					return alert("/boffice" + strNodeco + "/ex/board/BbsContentListPop.do", SVC_MSGE, model);
				}
			}
		}

		String searchStartDate = bbsContentVo.getSearchStartDate();
		String searchEndDate = bbsContentVo.getSearchEndDate();

		String nowDate = DateUtil.getCurrentDate();

		if (searchStartDate.equals("")) {

			if (SesUserPermCd.equals("05010000")) {
				bbsContentVo.setSearchStartDate(DateUtil.add(nowDate, -184));
			} else {
				bbsContentVo.setSearchStartDate(DateUtil.addMonth(nowDate, -1));
			}
		}

		if (searchEndDate.equals("")) {
			bbsContentVo.setSearchEndDate(nowDate);
		}

		if (bbsContentVo.getSearchDelYn().equals("")) {
			bbsContentVo.setSearchDelYn("N");
		}

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(bbsContentVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(bbsContentVo.getPageUnit());
		paginationInfo.setPageSize(bbsContentVo.getPageSize());

		bbsContentVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		bbsContentVo.setLastIndex(paginationInfo.getLastRecordIndex());
		bbsContentVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		Map<String, Object> map = bbsContentSvc.retrievePagBbsContent(bbsContentVo);
		int totCnt = Integer.parseInt((String) map.get("resultCnt"));

		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);

		return "injeinc/boffice/ex/board/bbs_content_list_set";
	}

	@RequestMapping("/boffice{strNodeco}/ex/board/BbsContentModBcDelYn.do")
	public String BbsContentModBcDelYn(@PathVariable("strNodeco") String strNodeco, HttpServletRequest request,
			@ModelAttribute("BbsContentVo") BbsContentVo bbsContentVo, ModelMap model) throws Exception {
		model.addAttribute("strNodeco", strNodeco);
		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		bbsContentVo.setModId(userid);
		bbsContentVo.setModIp(request.getRemoteAddr());

		String[] searchBcIdxArr = request.getParameterValues("searchBcIdx");

		String SVC_MSGE = "";

		if (searchBcIdxArr != null) {

			if (bbsContentVo.getSearchDelYn().equals("N")) {
				bbsContentVo.setBcDelYn("Y");
			} else {
				bbsContentVo.setBcDelYn("N");
			}

			bbsContentVo.setSearchBcIdxArr(searchBcIdxArr);
			bbsContentSvc.modifyBbsContentBcDelYn(bbsContentVo);
			SVC_MSGE = Message.getMessage("401.message");
		} else {
			SVC_MSGE = "수정할 게시글이 없습니다.";
		}

		StringBuffer addParam = new StringBuffer();
		addParam.append("?pageIndex=").append(bbsContentVo.getPageIndex());
		addParam.append("&pageUnit=").append(bbsContentVo.getPageUnit());
		addParam.append("&searchCondition=").append(bbsContentVo.getSearchCondition());
		addParam.append("&searchKeyword=").append(bbsContentVo.getSearchKeyword());
		addParam.append("&searchStartDate=").append(bbsContentVo.getSearchStartDate());
		addParam.append("&searchEndDate=").append(bbsContentVo.getSearchEndDate());
		addParam.append("&searchGroup=").append(bbsContentVo.getSearchGroup());
		addParam.append("&searchCbIdx=").append(bbsContentVo.getSearchCbIdx());
		addParam.append("&searchDelYn=").append(bbsContentVo.getSearchDelYn());
		if (bbsContentVo.getSearchCbIdx() != null && !bbsContentVo.getSearchCbIdx().isEmpty()) {
			CmsBbsVo cmsBbsVo = new CmsBbsVo();
			cmsBbsVo.setCbIdx(bbsContentVo.getSearchCbIdx() + "");
			cmsBbsVo = cmsBbsSvc.selectCmsBbs(cmsBbsVo);

			addParam.append("&mgrSiteCd=").append(cmsBbsVo.getMgrSiteCd());
			addParam.append("&searchCbCd=").append(cmsBbsVo.getCbCd());
		}
		return alert("/boffice" + strNodeco + "/ex/board/BbsContentList.do" + addParam.toString(), SVC_MSGE, model);
	}

	@RequestMapping("/boffice{strNodeco}/ex/board/BbsContentModDelRsnCd.do")
	public String BbsContentModDelRsnCd(@PathVariable("strNodeco") String strNodeco, HttpServletRequest request,
			@ModelAttribute("BbsContentVo") BbsContentVo bbsContentVo, ModelMap model) throws Exception {
		model.addAttribute("strNodeco", strNodeco);
		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		bbsContentVo.setModId(userid);
		bbsContentVo.setModIp(request.getRemoteAddr());

		String[] searchBcIdxArr = request.getParameterValues("searchBcIdx");

		String SVC_MSGE = "";

		if (searchBcIdxArr != null) {
			bbsContentVo.setSearchBcIdxArr(searchBcIdxArr);
			bbsContentSvc.modifyBbsContentDelRsnCd(bbsContentVo);
			SVC_MSGE = Message.getMessage("401.message");
		} else {
			SVC_MSGE = "수정할 게시글이 없습니다.";
		}

		StringBuffer addParam = new StringBuffer();
		addParam.append("?pageIndex=").append(bbsContentVo.getPageIndex());
		addParam.append("&pageUnit=").append(bbsContentVo.getPageUnit());
		addParam.append("&searchCondition=").append(bbsContentVo.getSearchCondition());
		addParam.append("&searchKeyword=").append(bbsContentVo.getSearchKeyword());
		addParam.append("&searchStartDate=").append(bbsContentVo.getSearchStartDate());
		addParam.append("&searchEndDate=").append(bbsContentVo.getSearchEndDate());
		addParam.append("&searchGroup=").append(bbsContentVo.getSearchGroup());
		addParam.append("&searchCbIdx=").append(bbsContentVo.getSearchCbIdx());
		addParam.append("&searchDelYn=").append(bbsContentVo.getSearchDelYn());
		if (bbsContentVo.getSearchCbIdx() != null && !bbsContentVo.getSearchCbIdx().isEmpty()) {
			CmsBbsVo cmsBbsVo = new CmsBbsVo();
			cmsBbsVo.setCbIdx(bbsContentVo.getSearchCbIdx() + "");
			cmsBbsVo = cmsBbsSvc.selectCmsBbs(cmsBbsVo);

			addParam.append("&mgrSiteCd=").append(cmsBbsVo.getMgrSiteCd());
			addParam.append("&searchCbCd=").append(cmsBbsVo.getCbCd());
		}
		return alert("/boffice" + strNodeco + "/ex/board/BbsContentList.do" + addParam.toString(), SVC_MSGE, model);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping("/boffice{strNodeco}/ex/board/BbsContentAnswerForm.do")
	public String BbsContentAnswerForm(@PathVariable("strNodeco") String strNodeco, HttpServletRequest request,
			@ModelAttribute("BoardVo") BoardVo boardVo,
			@ModelAttribute("ConfigPropertyVo") ConfigPropertyVo configPropertyVo,
			@ModelAttribute("BbsContentVo") BbsContentVo bbsContentVo,
			@ModelAttribute("ContentFileVo") ContentFileVo contentFileVo, @RequestParam Map requestMap, ModelMap model)
			throws Exception {
		model.addAttribute("strNodeco", strNodeco);
		boardVo = boardSvc.retrieveBoard(boardVo);

		if (boardVo != null) {
			model.addAttribute("BoardVo", boardVo);
		} else {
			String SVC_MSGE = "잘못된 접근입니다.";
			return alert(request.getContextPath() + "/boffice" + strNodeco + "/ex/board/BbsContentList.do", SVC_MSGE,
					model);
		}

		Map<String, Object> result = bbsContentSvc.retrieveBbsContent(bbsContentVo);

		if (result == null) {
			String SVC_MSGE = "잘못된 접근입니다.";
			return alert(request.getContextPath() + "/boffice" + strNodeco + "/ex/board/BbsContentList.do", SVC_MSGE,
					model);
		}

		result.put("actionkey", "modify");
		result.put("pageIndex", bbsContentVo.getPageIndex());
		result.put("pageUnit", bbsContentVo.getPageUnit());
		result.put("searchCondition", bbsContentVo.getSearchCondition());
		result.put("searchKeyword", bbsContentVo.getSearchKeyword());
		result.put("searchStartDate", bbsContentVo.getSearchStartDate());
		result.put("searchEndDate", bbsContentVo.getSearchEndDate());
		result.put("searchGroup", bbsContentVo.getSearchGroup());
		result.put("searchCbIdx", bbsContentVo.getSearchCbIdx());
		result.put("searchDelYn", bbsContentVo.getSearchDelYn());

		model.addAttribute("BbsContentVo", result);

		List fileList = contentFileSvc.retrieveListContentFile(contentFileVo);
		model.addAttribute("fileList", fileList);

		List propertylist = boardSvc.retrieveListConfigProperty(configPropertyVo);
		model.addAttribute("propertylist", propertylist);

		// bbsContentVo.setAnsRYn("C");
		// bbsContentSvc.boardUpdateStatusR(bbsContentVo);

		model.addAttribute("departList", groupDeptSvc.retrieveListCmsDepartGroup());

		String nowDate = DateUtil.getCurrentDate();
		model.addAttribute("nowDate", nowDate);

		return "injeinc/boffice/ex/board/bbs_content_answer_form";
	}

	@RequestMapping("/boffice{strNodeco}/ex/board/BbsContentAnswerMod.do")
	public String BbsContenAnswertMod(@PathVariable("strNodeco") String strNodeco, HttpServletRequest request,
			@ModelAttribute("BbsContentVo") BbsContentVo bbsContentVo, ModelMap model) throws Exception {
		model.addAttribute("strNodeco", strNodeco);
		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		bbsContentVo.setModId(userid);
		bbsContentVo.setModIp(request.getRemoteAddr());

		bbsContentSvc.modifyBbsContentAnswer(bbsContentVo);

		// bbsContentVo.setAnsRYn("C");
		// bbsContentSvc.boardUpdateStatusR(bbsContentVo);

		StringBuffer addParam = new StringBuffer();
		addParam.append("?pageIndex=").append(bbsContentVo.getPageIndex());
		addParam.append("&pageUnit=").append(bbsContentVo.getPageUnit());
		addParam.append("&searchCondition=").append(bbsContentVo.getSearchCondition());
		addParam.append("&searchKeyword=").append(bbsContentVo.getSearchKeyword());
		addParam.append("&searchStartDate=").append(bbsContentVo.getSearchStartDate());
		addParam.append("&searchEndDate=").append(bbsContentVo.getSearchEndDate());
		addParam.append("&searchGroup=").append(bbsContentVo.getSearchGroup());
		addParam.append("&searchCbIdx=").append(bbsContentVo.getSearchCbIdx());
		addParam.append("&searchDelYn=").append(bbsContentVo.getSearchDelYn());
		addParam.append("&cbIdx=").append(bbsContentVo.getCbIdx());
		addParam.append("&bcIdx=").append(bbsContentVo.getBcIdx());
		if (bbsContentVo.getSearchCbIdx() != null && !bbsContentVo.getSearchCbIdx().isEmpty()) {
			CmsBbsVo cmsBbsVo = new CmsBbsVo();
			cmsBbsVo.setCbIdx(bbsContentVo.getSearchCbIdx() + "");
			cmsBbsVo = cmsBbsSvc.selectCmsBbs(cmsBbsVo);

			addParam.append("&mgrSiteCd=").append(cmsBbsVo.getMgrSiteCd());
			addParam.append("&searchCbCd=").append(cmsBbsVo.getCbCd());
		}
		String SVC_MSGE = Message.getMessage("401.message");
		return alert("/boffice" + strNodeco + "/ex/board/BbsContentView.do" + addParam.toString(), SVC_MSGE, model);
	}

	@RequestMapping("/boffice{strNodeco}/ex/board/BbsContentLink.do")
	public String BbsContentLink(@PathVariable("strNodeco") String strNodeco, HttpServletRequest request,
			@ModelAttribute("BbsContentVo") BbsContentVo bbsContentVo, ModelMap model) throws Exception {
		model.addAttribute("strNodeco", strNodeco);

		String siteGroup = bbsContentSvc.selectCmsbbsGroup(bbsContentVo);

		String[] arrSiteGroup = siteGroup.split(">");
		String returnUrl = "";
		System.out.println("cbIdx:" + bbsContentVo.getCbIdx());
		if (bbsContentVo.getCbIdx() == 0) {
			returnUrl = "/boffice" + strNodeco + "/ex/board/BbsContentList.do";
		} else {
			returnUrl = "/boffice" + strNodeco + "/ex/board/BbsContentList.do";
			returnUrl += "?siteCd=" + arrSiteGroup[0];
			returnUrl += "&searchGroup=" + arrSiteGroup[arrSiteGroup.length - 2];
			returnUrl += "&searchCbIdx=" + bbsContentVo.getCbIdx();
		}

		return "redirect:" + returnUrl;

	}

	@RequestMapping("/boffice{strNodeco}/ex/board/BbsContentMain.do")
	public String BoardList(HttpServletRequest request, @ModelAttribute("BoardVo") BoardVo boardVo, ModelMap model)
			throws Exception {
		return "injeinc/boffice/ex/board/bbs_content_main";
	}

	@RequestMapping("/boffice{strNodeco}/ex/board/BbsContentSort.do")
	public String BbsContentSort(HttpServletRequest request, @ModelAttribute("BbsContentVo") BbsContentVo bbsContentVo,
			ModelMap model) throws Exception {
		bbsContentSvc.updateBbsContentUpDown(bbsContentVo);
		String returnUrl = request.getParameter("returnurl");
		return "redirect:" + returnUrl;
	}

	@RequestMapping("/boffice/ex/board/BbsContentListExcel.do")
	public void BbsContentListExcel(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("BbsContentVo") BbsContentVo bbsContentVo, ModelMap model) throws Exception {

		String searchCbCd = request.getParameter("searchCbCd");
		String searchGroup = "";
		String searchCbIdx = "";

		if (searchCbCd == null || StringUtil.isEmpty(searchCbCd)) {
			searchGroup = "";
			searchCbIdx = "";
			bbsContentVo.setSearchGroup("");
			bbsContentVo.setSearchCbIdx("");
		} else {
			searchGroup = cmsBbsSvc.retrieveGroup(searchCbCd);
			searchCbIdx = cmsBbsSvc.retrieveCbIdx(searchCbCd);

			if (searchGroup == null) {
				searchGroup = "";
			}
			if (searchCbIdx == null) {
				searchCbIdx = "";
			}

			bbsContentVo.setSearchGroup(searchGroup);
			bbsContentVo.setSearchCbIdx(searchCbIdx);
		}

		String SesUserRoleIdx = (String) WebUtils.getSessionAttribute(request, "SesUserRoleIdx");
		String SesUserPermCd = (String) WebUtils.getSessionAttribute(request, "SesUserPermCd");

		if (searchGroup.equals("")) {
			bbsContentVo.setSearchCbIdx("");
		}

		AuthorityVo authorityVo = new AuthorityVo();

		if (request.getParameter("siteCd") != null && searchGroup.equals("")) {
			searchGroup = request.getParameter("siteCd").replaceAll("null", "");
		}
		authorityVo.setCbUprCd(searchGroup);
		authorityVo.setMgrIdx(SesUserRoleIdx);

		if (searchCbIdx.equals("")) {
			ArrayList<String> searchCbIdxArr = null;

			if (SesUserPermCd.equals("05010000")) {
				searchCbIdxArr = (ArrayList<String>) authoritySvc.retrieveListAuthorityCbIdxMaster(authorityVo);
			} else {
				searchCbIdxArr = (ArrayList<String>) authoritySvc.retrieveListAuthorityCbIdx(authorityVo);
			}
			
			bbsContentVo.setSearchCbIdxArr(searchCbIdxArr);
		}
		
		String searchStartDate = bbsContentVo.getSearchStartDate();
		String searchEndDate = bbsContentVo.getSearchEndDate();
		
		String nowDate = DateUtil.getCurrentDate();
		
		if(searchStartDate.equals("")) {
			if(SesUserPermCd.equals("05010000")) {
				bbsContentVo.setSearchStartDate(DateUtil.add(nowDate, -184));
			}else{
				bbsContentVo.setSearchStartDate(DateUtil.addMonth(nowDate, -1));
			}
		}
		
		if(searchEndDate.equals("")) {
			bbsContentVo.setSearchEndDate(nowDate);
		}
		
		if(bbsContentVo.getSearchDelYn().equals("")) {
			bbsContentVo.setSearchDelYn ("N");
		}
		
		List resultList = bbsContentSvc.retrieveListBbsContent(bbsContentVo);
		
		String templateFile = "bbsContentListExcel.xls";
    	String filename = "게시물_" + getTimeStamp();
    	
    	Map<String, Object> beans = new HashMap<String, Object>();
        // 데이터를 담는 다.
    	beans.put("resultList", resultList);        
        
    	ExcelCtr.writeToExcel(templateFile, filename, beans, request, response);  // jxls 적용
    	
    	/*String curTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
  		String createString = curTime + java.lang.Math.random()+".xls";
  		
  		File path = new File(createString); //출력할 엑셀의 파일명.
  		
  		String[][] data = new String[resultList.size()+1][6];
		
		data[0][0] = "번호";
		data[0][1] = "게시판";
		data[0][2] = "제목";
		data[0][3] = "작성자";
		data[0][4] = "등록일";
		data[0][5] = "조회수";
		
		for(int i=0; i < resultList.size(); i++){
			BbsContentVo resultVO = (BbsContentVo)resultList.get(i);
			data[i+1][0] = String.valueOf( i + 1 );
			data[i+1][1] = resultVO.getCbName();
			data[i+1][2] = resultVO.getSubCont();
			data[i+1][3] = resultVO.getNameCont();
			data[i+1][4] = resultVO.getRegDt();
			data[i+1][5] = String.valueOf(resultVO.getCountCont());
		}
		
		short[] halign = new short[] {HSSFCellStyle.ALIGN_CENTER , HSSFCellStyle.ALIGN_CENTER , HSSFCellStyle.ALIGN_CENTER, HSSFCellStyle.ALIGN_CENTER, HSSFCellStyle.ALIGN_CENTER, HSSFCellStyle.ALIGN_CENTER, HSSFCellStyle.ALIGN_CENTER, HSSFCellStyle.ALIGN_CENTER, HSSFCellStyle.ALIGN_CENTER}; 			//정렬
		short[] valign = new short[] {HSSFCellStyle.VERTICAL_CENTER , HSSFCellStyle.VERTICAL_CENTER , HSSFCellStyle.VERTICAL_CENTER, HSSFCellStyle.VERTICAL_CENTER, HSSFCellStyle.VERTICAL_CENTER, HSSFCellStyle.VERTICAL_CENTER, HSSFCellStyle.VERTICAL_CENTER, HSSFCellStyle.VERTICAL_CENTER, HSSFCellStyle.VERTICAL_CENTER}; //정렬
	
		try {
			 ExcelCtr.writeToExcel( path ,  data , "게시물" , filename, halign, valign, request, response); // poi 적용
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}*/

	}
}