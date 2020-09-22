package egovframework.injeinc.boffice.ex.bbs.gBoardAdmin.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.util.WebUtils;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.EgovResourceCloseHelper;
import egovframework.cmm.EgovWebUtil;
import egovframework.cmm.Message;
import egovframework.cmm.service.EgovProperties;
import egovframework.injeinc.boffice.ex.bbs.gBoardAdmin.service.GBoardAdminSvc;
import egovframework.injeinc.boffice.ex.bbs.gBoardAdmin.vo.GBoardAdminVo;
import egovframework.injeinc.boffice.ex.board.service.BbsContentSvc;
import egovframework.injeinc.boffice.ex.board.vo.BbsContentVo;
import egovframework.injeinc.boffice.ex.mail.vo.MailVo;
import egovframework.injeinc.boffice.login.vo.LoginVo;
import egovframework.injeinc.boffice.sy.code.service.CmmCodeSvc;
import egovframework.injeinc.boffice.sy.mgr.service.MgrListSvc;
import egovframework.injeinc.boffice.sy.mgr.vo.MgrListVo;
import egovframework.injeinc.boffice.sy.mgr.vo.RoleVo;
import egovframework.injeinc.common.files.controller.CmmFilesCtr;
import egovframework.injeinc.common.util.DateUtil;
import egovframework.injeinc.common.util.EgovDateUtil;
import egovframework.injeinc.common.util.EgovStringUtil;
import egovframework.injeinc.common.util.MailUtil;
import egovframework.injeinc.common.util.UmsUtil;
import egovframework.injeinc.common.vo.UmsVo;
import egovframework.injeinc.foffice.ex.bbs.service.BbsFSvc;
import egovframework.injeinc.foffice.ex.bbs.vo.BbsFVo;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


@Controller
public class GBoardAdminCtr extends CmmLogCtr{

	@Resource(name = "GBoardAdminSvc")
	private GBoardAdminSvc gBoardAdminSvc;
	
	@Resource(name = "BbsContentSvc")
	private BbsContentSvc bbsContentSvc;
	
	@Resource(name = "BbsFSvc")
	private BbsFSvc bbsFSvc;
	
	@Resource(name = "CmmCodeSvc")
	private CmmCodeSvc cmmCodeSvc;
	
	@Resource(name = "MgrListSvc")
	private MgrListSvc mgrListSvc;
	
	 
	@Autowired(required=true)
	private MappingJackson2JsonView jsonView;
	
	private EgovDateUtil egovDateUtil;
	
	/**
	 * 인바라게시물관리 리스트 화면
	 * @param request
	 * @param boardAdminVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/boffice/ex/bbs/gBoardAdmin/gBoardAdmin_list.do")
	public String gBoardList(
			HttpServletRequest request, @ModelAttribute("GBoardAdminVo") GBoardAdminVo gBoardAdminVo
			, ModelMap model) throws Exception {
		
		
		
		HttpSession ses = request.getSession();
		
		String yearMonthDay = egovDateUtil.yearMonthDay().replace("_", "");

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");
	    String yearMonthDayB = dateFormatter.format(cal.getTime());
	    String yearMonthDayC = Calendar.YEAR+"01"+"01";
		
		String SVC_MSGE = "";
		String returnUrl = "";
		
		String searchField 		= gBoardAdminVo.getSearchField();
		String searchKeyWord 	= gBoardAdminVo.getSearchKeyWord();
		
		String mwStatusCont 	= gBoardAdminVo.getMwStatusCont();
		
				
		String scRegDtSt 		= gBoardAdminVo.getScRegDtSt();
		String startTime 		= gBoardAdminVo.getStartTime();
		String scRegDtEd 		= gBoardAdminVo.getScRegDtEd();
		String endTime   		= gBoardAdminVo.getEndTime();
		
		String schMbpERegDtSt	= gBoardAdminVo.getSchMbpERegDtSt();
		String schMbpERegDtStTime = gBoardAdminVo.getSchMbpERegDtStTime();
		String schMbpERegDtEd	= gBoardAdminVo.getSchMbpERegDtEd();
		String schMbpERegDtEdTime = gBoardAdminVo.getSchMbpERegDtEdTime();
		
		String bcDelYn	 		= gBoardAdminVo.getBcDelYn();

		
		String rowCnt	 		= gBoardAdminVo.getRowCnt();
		
		String gbnVal = gBoardAdminVo.getGbnVal();
		
		String permCd = (String)ses.getAttribute("SesUserPermCd"); //권한
		String deptCd = (String)ses.getAttribute("SesUserDeptCd"); //부서코드
		String deptNm = (String)ses.getAttribute("SesUserDeptNm"); //부서명
		String RoleIdx = (String)ses.getAttribute("SesUserRoleIdx");
		
		System.out.println("RoleIdx ====================>>"+RoleIdx);
		
		System.out.println("deptNm ==============>> : " + deptNm);
		System.out.println("permCd ==============>> : " + permCd);
			
		
		if(rowCnt == null || rowCnt == ""){
			rowCnt = "15";
		}
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(gBoardAdminVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(Integer.parseInt(rowCnt.replace(",", "")));
		paginationInfo.setPageSize(gBoardAdminVo.getPageSize());

		gBoardAdminVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		gBoardAdminVo.setLastIndex(paginationInfo.getLastRecordIndex());
		gBoardAdminVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		gBoardAdminVo.setSearchField(searchField);
		gBoardAdminVo.setSearchKeyWord(searchKeyWord);
		
		if(scRegDtSt == null || scRegDtEd == null){
			gBoardAdminVo.setScRegDtSt(yearMonthDayB);
			gBoardAdminVo.setScRegDtEd(yearMonthDay);
		}else{
			gBoardAdminVo.setScRegDtSt(scRegDtSt.replaceAll("-", ""));
			gBoardAdminVo.setScRegDtEd(scRegDtEd.replaceAll("-", ""));
		}
		
		if( "".equals(EgovStringUtil.nullConvert(schMbpERegDtSt)) || "".equals(EgovStringUtil.nullConvert(schMbpERegDtEd)) ){
			gBoardAdminVo.setSchMbpERegDtSt("");
			gBoardAdminVo.setSchMbpERegDtEd("");
		}else{
			gBoardAdminVo.setSchMbpERegDtEd(schMbpERegDtEd.replaceAll("-", "")+schMbpERegDtEdTime);
			gBoardAdminVo.setSchMbpERegDtSt(schMbpERegDtSt.replaceAll("-", "")+schMbpERegDtStTime);
		}
		
		if(bcDelYn == null || bcDelYn == ""){
			bcDelYn = "N";
		}
		
		
		// 2016_09_22 인바라 담당자 지정되면 d00000008 값 부서코드로 변경할것. 감사담당관
		if( "RL00000022".equals(RoleIdx) || "05010000".equals(permCd)){
		}else{
			
			
			gBoardAdminVo.setMcDeptCd(deptCd.trim());
			gBoardAdminVo.setScRegDtSt(yearMonthDayC); //연초
			gBoardAdminVo.setScRegDtEd(yearMonthDay);  // 오늘날짜
		}
		
		
		gBoardAdminVo.setMwStatusCont(mwStatusCont);
		gBoardAdminVo.setBcDelYn(bcDelYn);
		
		int totCnt = gBoardAdminSvc.retrieveListGubaraCount(gBoardAdminVo); 
		paginationInfo.setTotalRecordCount(totCnt);
		
		List cdSelectBoxList = gBoardAdminSvc.cdSelectBox();
		
		List timeSelect = gBoardAdminSvc.retrieveTimeSelect(gBoardAdminVo);
		
		List gubaraSelectList = gBoardAdminSvc.retrieveListGubara(gBoardAdminVo);
		
		//TEST
		ArrayList<String> subList = new ArrayList<String>();
		for(int i = 0; i < gubaraSelectList.size(); i++){
			gBoardAdminVo = (GBoardAdminVo) gubaraSelectList.get(i);
			gBoardAdminVo.setBcIdx(gBoardAdminVo.getBcIdx());
			gBoardAdminVo.setGbnVal(gBoardAdminVo.getMinwonDeptDt());
			//gBoardAdminVo.setToday(yearMonthDay);
			List retrieveSubList = gBoardAdminSvc.retrieveSubListGubara(gBoardAdminVo);
			subList.addAll(retrieveSubList);
		}
		
		if(model != null){
			model.addAttribute("cdSelectBoxList", cdSelectBoxList);
			model.addAttribute("timeSelect", timeSelect);
			model.addAttribute("gubaraSelectList", gubaraSelectList);
			model.addAttribute("subList", subList);
			model.addAttribute("totCnt", totCnt);
			model.addAttribute("paginationInfo", paginationInfo);
			
			model.addAttribute("permCd", permCd);
			model.addAttribute("deptCd", deptCd);
			model.addAttribute("RoleIdx", RoleIdx);
			
			model.addAttribute("selectDw1List", gBoardAdminSvc.selectDw1());
			model.addAttribute("selectReplDeptList", gBoardAdminSvc.selectReplDept2());
		}
		
//			if("EX".equals(gbnVal)){
//				returnUrl = "injeinc/boffice/ex/bbs/gBoardAdmin/gBoardAdmin_listExcel";
//			}else{
				returnUrl = "injeinc/boffice/ex/bbs/gBoardAdmin/gBoardAdmin_list";
//			}
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
	@RequestMapping(value= "/boffice/ex/bbs/gBoardAdmin/gBoardAdmin_listExcel.do")
	public String gBoardListExcel(
			HttpServletRequest request, HttpServletResponse response, @ModelAttribute("GBoardAdminVo") GBoardAdminVo gBoardAdminVo
			, ModelMap model) throws Exception {
		
		String SVC_MSGE = "";
		String returnUrl = "";
		HttpSession ses = request.getSession();
		
		String yearMonthDay = egovDateUtil.yearMonthDay().replace("_", "");

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");
	    String yearMonthDayB = dateFormatter.format(cal.getTime());
	    String yearMonthDayC = Calendar.YEAR+"01"+"01";
		
		String searchField 		= gBoardAdminVo.getSearchField();
		String searchKeyWord 	= gBoardAdminVo.getSearchKeyWord();
		
		String mwStatusCont 	= gBoardAdminVo.getMwStatusCont();
		
		String scRegDtSt 		= gBoardAdminVo.getScRegDtSt();
		String startTime 		= gBoardAdminVo.getStartTime();
		String scRegDtEd 		= gBoardAdminVo.getScRegDtEd();
		String endTime   		= gBoardAdminVo.getEndTime();
		
		String schMbpERegDtSt	= gBoardAdminVo.getSchMbpERegDtSt();
		String schMbpERegDtStTime = gBoardAdminVo.getSchMbpERegDtStTime();
		String schMbpERegDtEd	= gBoardAdminVo.getSchMbpERegDtEd();
		String schMbpERegDtEdTime = gBoardAdminVo.getSchMbpERegDtEdTime();
		
		String bcDelYn	 		= gBoardAdminVo.getBcDelYn();
		String rowCnt	 		= gBoardAdminVo.getRowCnt();
		
		String gbnVal = gBoardAdminVo.getGbnVal();
		
		String permCd = (String)ses.getAttribute("SesUserPermCd"); //권한
		String deptCd = (String)ses.getAttribute("SesUserDeptCd"); //부서코드
		String deptNm = (String)ses.getAttribute("SesUserDeptNm"); //부서명
		
		if(scRegDtSt == null || scRegDtEd == null){
			gBoardAdminVo.setScRegDtSt(yearMonthDayB);
			gBoardAdminVo.setScRegDtEd(yearMonthDay);
		}else{
			gBoardAdminVo.setScRegDtSt(scRegDtSt.replaceAll("-", ""));
			gBoardAdminVo.setScRegDtEd(scRegDtEd.replaceAll("-", ""));
		}
		
		if( "".equals(EgovStringUtil.nullConvert(schMbpERegDtSt)) || "".equals(EgovStringUtil.nullConvert(schMbpERegDtEd)) ){
			gBoardAdminVo.setSchMbpERegDtSt("");
			gBoardAdminVo.setSchMbpERegDtEd("");
		}else{
			gBoardAdminVo.setSchMbpERegDtEd(schMbpERegDtEd.replaceAll("-", "")+schMbpERegDtEdTime);
			gBoardAdminVo.setSchMbpERegDtSt(schMbpERegDtSt.replaceAll("-", "")+schMbpERegDtStTime);
		}
		
		gBoardAdminVo.setMcDeptName(deptNm);
		
		List retrieveExcelList = gBoardAdminSvc.retrieveListExcelGubara(gBoardAdminVo);
		if(model != null){
			model.addAttribute("resultList", retrieveExcelList);
		}
		
		String fileName = "인재에게바란다_"+DateUtil.getCurrentDate("yyyyMMdd")+".xls";
		
		String encodedFilename = URLEncoder.encode(fileName, "UTF-8");
		
		request.setCharacterEncoding("utf-8");
		response.setHeader("Content-Disposition", "attachment; filename="+encodedFilename);
		response.setHeader("Content-Description", "JSP Generated Data");
		response.setContentType("application/vnd.ms-excel");
		
		returnUrl = "injeinc/boffice/ex/bbs/gBoardAdmin/gBoardAdmin_listExcel";
		
		return returnUrl;
		
	}
	
	/**
	 * 인바라게시물관리 상세 화면
	 * @param request
	 * @param boardAdminVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/boffice/ex/bbs/gBoardAdmin/gBoardAdmin_view.do")
	public String BoardView(
			HttpServletRequest request, @ModelAttribute("GBoardAdminVo") GBoardAdminVo gBoardAdminVo
			, ModelMap model) throws Exception {
		
		HttpSession ses = request.getSession();
		
		String SVC_MSGE = "";
		String returnUrl = "";
		int pageIndex = gBoardAdminVo.getPageIndex();
		
		String permCd = (String)ses.getAttribute("SesUserPermCd"); //권한
		String deptNm = (String)ses.getAttribute("SesUserDeptNm"); //부서명
		String deptCd = (String)ses.getAttribute("SesUserDeptCd"); //부서코드
		String RoleIdx = (String)ses.getAttribute("SesUserRoleIdx");
		
		String gbnVal = gBoardAdminVo.getGbnVal();
		
		//조회수 업데이트1
		gBoardAdminSvc.gubaraBoardCountUpdate(gBoardAdminVo);
		//상세내용 조회
		Map detailMap = gBoardAdminSvc.gubaraBoardDetail(gBoardAdminVo);
		//File List
		List detailFileList = gBoardAdminSvc.retrieveFileList(gBoardAdminVo);
		
		//상세내용 이전글/다음글
		Map detailPreNextMap = gBoardAdminSvc.gubaraBoardDetailPreNext(gBoardAdminVo);
		
		List detailDepList = gBoardAdminSvc.retrieveDepList(gBoardAdminVo);
		
		List detailDeptFileList = gBoardAdminSvc.retrieveFileList(gBoardAdminVo);

		int contentClobCnt = gBoardAdminSvc.selectContentClobChk(gBoardAdminVo);
		
		List retrieveListCode = gBoardAdminSvc.retrieveListCode("27000000");
		

		
		Map selectIntergRespMap = gBoardAdminSvc.selectIntergResp(gBoardAdminVo);
		if(model != null){
			model.addAttribute("selectIntergRespMap", selectIntergRespMap);
		}
		
		
		if(detailDepList.size() > 0){
			if(model != null){
				model.addAttribute("detailDepList", detailDepList);
			}
		}else{
			if(model != null){
				model.addAttribute("detailDepList", "");
			}
		}
		if(model != null){
			model.addAttribute("cdSelectBoxList", gBoardAdminSvc.cdSelectBox());
			model.addAttribute("detailMap", detailMap);
			model.addAttribute("detailFileList", detailFileList);
			model.addAttribute("detailDeptFileList", detailDeptFileList);
			model.addAttribute("contentClobCnt", contentClobCnt);
			model.addAttribute("retrieveListCode", retrieveListCode);
			model.addAttribute("detailPreNextMap", detailPreNextMap);
			model.addAttribute("gBoardAdminVo", gBoardAdminVo);
			model.addAttribute("pageIndex", pageIndex);
			
			model.addAttribute("permCd", permCd);
			model.addAttribute("deptNm", deptNm);
			model.addAttribute("deptCd", deptCd);
			model.addAttribute("RoleIdx", RoleIdx);
		}
		
		returnUrl = "injeinc/boffice/ex/bbs/gBoardAdmin/gBoardAdmin_view";
		
		return returnUrl;		
	}
	
	
	/**
	 * 인바라게시물관리 상세 화면 인쇄
	 * @param request
	 * @param boardAdminVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/boffice/ex/bbs/gBoardAdmin/gBoardAdmin_viewPop.do")
	public String BoardViewPop(
			HttpServletRequest request, @ModelAttribute("GBoardAdminVo") GBoardAdminVo gBoardAdminVo
			, ModelMap model) throws Exception {
		
		HttpSession ses = request.getSession();
		
		String SVC_MSGE = "";
		String returnUrl = "";
		int pageIndex = gBoardAdminVo.getPageIndex();
		
		String permCd = (String)ses.getAttribute("SesUserPermCd"); //권한
		String deptNm = (String)ses.getAttribute("SesUserDeptNm"); //부서명
		String deptCd = (String)ses.getAttribute("SesUserDeptCd"); //부서코드
		String RoleIdx = (String)ses.getAttribute("SesUserRoleIdx");
		
		String gbnVal = gBoardAdminVo.getGbnVal();
		
		
		//조회수 업데이트
		gBoardAdminSvc.gubaraBoardCountUpdate(gBoardAdminVo);
		//상세내용 조회
		Map detailMap = gBoardAdminSvc.gubaraBoardDetail(gBoardAdminVo);
		//File List
		List detailFileList = gBoardAdminSvc.retrieveFileList(gBoardAdminVo);
		
		//상세내용 이전글/다음글
		Map detailPreNextMap = gBoardAdminSvc.gubaraBoardDetailPreNext(gBoardAdminVo);
		
		List detailDepList = gBoardAdminSvc.retrieveDepList(gBoardAdminVo);
		
		List detailDeptFileList = gBoardAdminSvc.retrieveFileList(gBoardAdminVo);

		int contentClobCnt = gBoardAdminSvc.selectContentClobChk(gBoardAdminVo);
		
		List retrieveListCode = gBoardAdminSvc.retrieveListCode("27000000");
		

		
		Map selectIntergRespMap = gBoardAdminSvc.selectIntergResp(gBoardAdminVo);
		if(model != null){
			model.addAttribute("selectIntergRespMap", selectIntergRespMap);
		}
		
		
		if(detailDepList.size() > 0){
			if(model != null){
				model.addAttribute("detailDepList", detailDepList);
			}
		}else{
			if(model != null){
				model.addAttribute("detailDepList", "");
			}
		}
		if(model != null){
			model.addAttribute("pageIndex", pageIndex);
			model.addAttribute("contentClobCnt", contentClobCnt);
			
			model.addAttribute("detailMap", detailMap);
			model.addAttribute("detailFileList", detailFileList);
			model.addAttribute("detailDeptFileList", detailDeptFileList);
			model.addAttribute("retrieveListCode", retrieveListCode);
			model.addAttribute("detailPreNextMap", detailPreNextMap);
			model.addAttribute("gBoardAdminVo", gBoardAdminVo);
			model.addAttribute("permCd", permCd);
			model.addAttribute("deptNm", deptNm);
			
			model.addAttribute("deptCd", deptCd);
			model.addAttribute("RoleIdx", RoleIdx);
		}
		
		returnUrl = "injeinc/boffice/ex/bbs/gBoardAdmin/gBoardAdmin_viewPrint";
		
		return returnUrl;		
	}
	
	
	/**
	 * 파일 다운로드
	 * @param request
	 * @param response
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("/boffice/ex/bbs/gBoardAdmin/gBoardAdmin_fileDownload.do")
	public void fileDownload(HttpServletRequest request, HttpServletResponse response, 
			@ModelAttribute("GBoardAdminVo") GBoardAdminVo gBoardAdminVo) throws FileNotFoundException, IOException, Exception {
		
		CmmFilesCtr cfc = new CmmFilesCtr(); 
		String bcIdx=EgovStringUtil.nullConvert(request.getParameter("bcIdx"));
		String cbIdx=EgovStringUtil.nullConvert(request.getParameter("cbIdx"));
		String fileNo=EgovStringUtil.nullConvert(request.getParameter("fileNo"));
		String mcIdx=EgovStringUtil.nullConvert(request.getParameter("mcIdx"));
		
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("bcIdx", bcIdx);
		param.put("cbIdx", cbIdx);
		param.put("fileNo", fileNo);
		param.put("mcIdx", mcIdx);

		Map fileDownMap = gBoardAdminSvc.boardFileDown(param);
		
		if( fileDownMap == null ){
			throw new Exception("Download File is null");
		}
		
		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
		String downFileName = rootPath + (String)fileDownMap.get("FILE_STRE_COURS") + (String)fileDownMap.get("STRE_FILE_NM");
		
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
				EgovResourceCloseHelper.close(fin);
				throw e;
			}catch(IOException e) {
				EgovResourceCloseHelper.close(fin);
				throw e;
			}catch(Exception e) {
				EgovResourceCloseHelper.close(fin);
				throw e;
			}finally {
				EgovResourceCloseHelper.close(fin);
			}
			response.getOutputStream().flush();
			response.getOutputStream().close();
		}   
	}
	
	/**
	 * 인바라게시물관리 글수정 화면
	 * @param request
	 * @param boardAdminVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/boffice/ex/bbs/gBoardAdmin/gBoardAdmin_modForm.do")
	public String BoardRegForm(
			HttpServletRequest request, @ModelAttribute("GBoardAdminVo") GBoardAdminVo gBoardAdminVo
			, ModelMap model) throws Exception {
		
		String returnUrl = "";
		
		HttpSession ses = request.getSession();
		
		//상세내용 조회
		Map detailMap = gBoardAdminSvc.gubaraBoardDetail(gBoardAdminVo);
		//인바라 첨부파일 갯수
		Map bbsConfigMap = gBoardAdminSvc.gubaraBoardConfig(gBoardAdminVo);
		//File List
		List detailFileList = gBoardAdminSvc.retrieveFileList(gBoardAdminVo);
		
		String fileCnt = (String) bbsConfigMap.get("bbsFileCnt");
		if(model != null){
			model.addAttribute("detailMap", detailMap);
			model.addAttribute("bbsConfigMap", bbsConfigMap);
			model.addAttribute("fileCnt", fileCnt);
			model.addAttribute("detailFileList", detailFileList);
			model.addAttribute("detailFileListSize", detailFileList.size());
			model.addAttribute("gBoardAdminVo", gBoardAdminVo);
		}
		
		returnUrl = "injeinc/boffice/ex/bbs/gBoardAdmin/gBoardAdmin_modForm";
		
		return returnUrl;
	}
	
	/**
	 * 인바라게시물관리 글수정 화면 첨부파일 삭제
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/boffice/ex/bbs/gBoardAdmin/File_RmvAx.do")
	public void FileRmvAx(HttpServletRequest request ,HttpServletResponse response)  throws Exception{
		
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
		
		param.put("bcIdx", request.getParameter("bcIdx"));
		param.put("cbIdx", request.getParameter("cbIdx"));
		param.put("fileNo", request.getParameter("fileNo"));
		param.put("mcIdx", request.getParameter("mcIdx"));
		param.put("regId", regId);
		
		gBoardAdminSvc.boardRemoveFiles(param);
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", "ok");
		jsonView.render(jsonMap, request, response);
	}	
	
	/**
	 * 게시글 수정/삭제
	 * @param request
	 * @param boardAdminVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/boffice/ex/bbs/gBoardAdmin/gBoardAdmin_mod.do")
	public String BoardReg(
			HttpServletRequest request, @ModelAttribute("GBoardAdminVo") GBoardAdminVo gBoardAdminVo
			, ModelMap model) throws Exception {
		
		String SVC_MSGE = "";
		String returnUrl = "";
		String param = "";
		String modGbn = gBoardAdminVo.getModGbn();
		
		String addrCont1 = gBoardAdminVo.getAddrCont1();
		String addrCont2 = gBoardAdminVo.getAddrCont2();
		String addrCont3 = gBoardAdminVo.getAddrCont3();
		
		String telCont1 = gBoardAdminVo.getTelCont1();
		String telCont2 = gBoardAdminVo.getTelCont2();
		String telCont3 = gBoardAdminVo.getTelCont3();
		
		String PhoneCont1 = gBoardAdminVo.getPhoneCont1();
		String PhoneCont2 = gBoardAdminVo.getPhoneCont2();
		String PhoneCont3 = gBoardAdminVo.getPhoneCont3();
		
		param = "?cbIdx="+gBoardAdminVo.getCbIdx()+"&bcIdx="+gBoardAdminVo.getBcIdx();
		
		try{
			HttpSession ses = request.getSession();
			gBoardAdminVo.setModId((String)ses.getAttribute("SesUserId"));
			
			if("U".equals(modGbn)){
				
				if( "Y".equals(gBoardAdminVo.getMwAdOpenYn()) ){
					gBoardAdminVo.setOpenYnCont("AD_Y");
				}else{
					gBoardAdminVo.setOpenYnCont(gBoardAdminVo.getOpenYnCont());
				}
			
				gBoardAdminVo.setAddrCont(addrCont1+"|"+addrCont2+"|"+addrCont3);
				gBoardAdminVo.setTelCont(telCont1+"-"+telCont2+"-"+telCont3);
				gBoardAdminVo.setPhoneCont(PhoneCont1+"-"+PhoneCont2+"-"+PhoneCont3);
				
				gBoardAdminSvc.boardRegistFiles(request ,gBoardAdminVo);
				
				SVC_MSGE = Message.getMessage("401.message");	//수정완료
				
				returnUrl = "/boffice/ex/bbs/gBoardAdmin/gBoardAdmin_view.do" + param;
				
			}else if("D".equals(modGbn)){
				
				gBoardAdminVo.setBcDelYn("Y");
				SVC_MSGE = Message.getMessage("501.message");	//삭제완료
				returnUrl = "/boffice/ex/bbs/gBoardAdmin/gBoardAdmin_list.do" + param;
			}
			
			gBoardAdminSvc.gubaraBoardUpdate(gBoardAdminVo);
			
			//상세내용 조회
			Map detailMap = gBoardAdminSvc.gubaraBoardDetail(gBoardAdminVo);
			if(model != null){
				model.addAttribute("detailMap", detailMap);
				model.addAttribute("gBoardAdminVo", gBoardAdminVo);
			}
			
		} catch (Exception e) {
			SVC_MSGE = Message.getMessage("901.message");	//에러
			throw e;
		}
		
		return alert(returnUrl, SVC_MSGE, model);
	}
	
	@RequestMapping(value= "/boffice/ex/bbs/gBoardAdmin/gBoardAdmin_mod2.do")
	public String BoardReg2(HttpServletRequest request, @ModelAttribute("GBoardAdminVo") GBoardAdminVo gBoardAdminVo , ModelMap model) throws Exception {
		
		String SVC_MSGE = "";
		String returnUrl = "";
		String param = "";
		
		String addrCont1 = gBoardAdminVo.getAddrCont1();
		String addrCont2 = gBoardAdminVo.getAddrCont2();
		String addrCont3 = gBoardAdminVo.getAddrCont3();
		
		String telCont1 = gBoardAdminVo.getTelCont1();
		String telCont2 = gBoardAdminVo.getTelCont2();
		String telCont3 = gBoardAdminVo.getTelCont3();
		
		String PhoneCont1 = gBoardAdminVo.getPhoneCont1();
		String PhoneCont2 = gBoardAdminVo.getPhoneCont2();
		String PhoneCont3 = gBoardAdminVo.getPhoneCont3();
		
		param = "?cbIdx="+gBoardAdminVo.getCbIdx()+"&bcIdx="+gBoardAdminVo.getBcIdx();
		
		try{
			HttpSession ses = request.getSession();
			gBoardAdminVo.setRegId((String)ses.getAttribute("SesUserId"));
			gBoardAdminVo.setModId((String)ses.getAttribute("SesUserId"));
			gBoardAdminVo.setModIp(request.getRemoteAddr());
			
			gBoardAdminVo.setAddrCont(addrCont1+"_"+addrCont2+"_"+addrCont3);
			gBoardAdminVo.setTelCont(telCont1+"-"+telCont2+"-"+telCont3);
			gBoardAdminVo.setPhoneCont(PhoneCont1+"-"+PhoneCont2+"-"+PhoneCont3);
				
			gBoardAdminSvc.boardRegistFiles(request ,gBoardAdminVo);
				
			SVC_MSGE = Message.getMessage("401.message");	//수정완료
				
			returnUrl = "/boffice/ex/bbs/gBoardAdmin/gBoardAdmin_view.do" + param;
			
			gBoardAdminSvc.gubaraBoardUpdate2(gBoardAdminVo);
			
			//상세내용 조회
			Map detailMap = gBoardAdminSvc.gubaraBoardDetail(gBoardAdminVo);
			if(model != null){
				model.addAttribute("detailMap", detailMap);
				model.addAttribute("gBoardAdminVo", gBoardAdminVo);
			}
			
		} catch (Exception e) {
			SVC_MSGE = Message.getMessage("901.message");	//에러
			throw e;
		}
		
		return alert(returnUrl, SVC_MSGE, model);
	}
		
	/**
	 * 인바라게시물관리 상세 > 자유게시판 이동
	 * @param request
	 * @param boardAdminVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/boffice/ex/bbs/gBoardAdmin/gBoard_boardTrans.do")
	public String FreeboardTrans(HttpServletRequest request, @ModelAttribute("GBoardAdminVo") GBoardAdminVo gBoardAdminVo
			, ModelMap model) throws Exception {
		try {
			HttpSession ses = request.getSession();
			gBoardAdminVo.setModId((String)ses.getAttribute("SesUserId"));
			gBoardAdminSvc.trnasBoard(gBoardAdminVo);
		} catch (Exception e) {
			throw e;
		}
		return alert("/boffice/ex/bbs/gBoardAdmin/gBoardAdmin_list.do", "이관 되었습니다.", model);
	}
	
	
	/**
	 * 인바라게시물관리 상세 > 인바라게시물 답변미대상민원 처리
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/boffice/ex/bbs/gBoardAdmin/gBoard_NoAnswerAx.do")
	public void NoAnswerAx(HttpServletRequest request ,HttpServletResponse response) throws Exception {
		
		Map param = new HashMap<String, String>();
		HttpSession ses = request.getSession();
		
		param.put("bcIdx", request.getParameter("bcIdx"));
		param.put("cbIdx", request.getParameter("cbIdx"));
		param.put("modId", (String)ses.getAttribute("SesUserId"));
		
		gBoardAdminSvc.gubaraBoardNoAnswer(param);
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", "ok");
		jsonView.render(jsonMap, request, response);
	}
	
	/**
	 * 인바라게시물관리 상세 > 복사 팝업 화면
	 * @param request
	 * @param GBoardAdminVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/boffice_nodeco/ex/bbs/gBoardAdmin/gBoardCopy_Pop.do")
	public String GBoardCopyPop(HttpServletRequest request, @ModelAttribute("GBoardAdminVo") GBoardAdminVo gBoardAdminVo
							, ModelMap model) throws Exception {
		
		//인바라 게시글 복사할 게시판 대분류 리스트
		List boardTgtGroupList = gBoardAdminSvc.retrieveCopyBoardTgtGroup(gBoardAdminVo);
		if(model != null){
			model.addAttribute("boardTgtGroupList", boardTgtGroupList);
			model.addAttribute("gBoardAdminVo", gBoardAdminVo);
		}
		//System.out.println("gBoardAdminVo:::::::"+gBoardAdminVo);
		return "/injeinc/boffice/ex/bbs/gBoardAdmin/gBoardCopy_Pop";		
	}
	
	/**
	 * 인바라게시물관리 상세 > 소분류 게시판 조회
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/boffice/ex/bbs/gBoardAdmin/secondSelectListAx.do")
	public void SecondSelectListAx(HttpServletRequest request ,HttpServletResponse response) throws Exception {
		
		List boardTgtSecondList = gBoardAdminSvc.retrieveCopyBoardTgtSecond(request.getParameter("cbUprCd"));
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", "ok");
		jsonMap.put("boardTgtSecondList", boardTgtSecondList);
		jsonView.render(jsonMap, request, response);
	}
	
	/**
	 * 인바라게시물관리 상세 > 소분류 게시판 조회
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/boffice/ex/bbs/gBoardAdmin/copyBoardAx.do")
	public void CopyBoardAx(HttpServletRequest request ,HttpServletResponse response, GBoardAdminVo gBoardAdminVo) throws Exception {
		
		HttpSession ses = request.getSession();
		
		int cbIdx = EgovStringUtil.zeroConvert(request.getParameter("cbIdx"));
		int bcIdx = EgovStringUtil.zeroConvert(request.getParameter("bcIdx"));
		String copyCbIdx =  EgovStringUtil.nullConvert(request.getParameter("copyCbIdx"));
		gBoardAdminVo.setCbIdx(cbIdx);
		gBoardAdminVo.setBcIdx(bcIdx);
		gBoardAdminVo.setCopyCbIdx(copyCbIdx);
		gBoardAdminVo.setModId((String)ses.getAttribute("SesUserId"));
		String copyBcIdx = gBoardAdminSvc.registBcIdxNo(copyCbIdx);
		gBoardAdminVo.setCopyBcIdx(copyBcIdx);
		//System.out.println("::::::::::::gBoardAdminVo:::::::::" + gBoardAdminVo);
		gBoardAdminSvc.copyBoard(gBoardAdminVo);
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", "ok");
		jsonView.render(jsonMap, request, response);
	}
	
	/**
	 * 인바라게시물관리 상세 > 답변기한 연기
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/boffice_nodeco/ex/bbs/gBoardAdmin/gBoardAnswerDelay_Pop.do")
	public String GBoardAnswerDelayPop(HttpServletRequest request, @ModelAttribute("GBoardAdminVo") GBoardAdminVo gBoardAdminVo
			, ModelMap model) throws Exception {
		if(model != null){
			model.addAttribute("gBoardAdminVo", gBoardAdminVo);
		}
		return "/injeinc/boffice/ex/bbs/gBoardAdmin/gBoardAnswerDelay_Pop";
	}
	
	/**
	 * 인바라게시물관리 상세 > 통합답변 입력/수정 화면
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/boffice_nodeco/ex/bbs/gBoardAdmin/gBoardIntergResp_Pop.do")
	public String GBoardIntergRespPop(HttpServletRequest request, @ModelAttribute("GBoardAdminVo") GBoardAdminVo gBoardAdminVo
			, ModelMap model) throws Exception {
		
		String gbnVal = EgovStringUtil.nullConvert(request.getParameter("gbnVal"));
		
		List retrieveListCode = gBoardAdminSvc.retrieveListCode("27000000");
		
		if("U".equals(gbnVal)){
			//System.out.println("gBoardAdminVo ===========>>>>>>>>>>>> : " + gBoardAdminVo);
			Map selectIntergRespMap = gBoardAdminSvc.selectIntergResp(gBoardAdminVo);
			List detailDeptFileList = gBoardAdminSvc.retrieveFileList(gBoardAdminVo);
			if(model != null){
				model.addAttribute("selectIntergRespMap", selectIntergRespMap);
				model.addAttribute("detailDeptFileList", detailDeptFileList);
		
	
			}
		}
		if(model != null){
			model.addAttribute("retrieveListCode", retrieveListCode);
			model.addAttribute("gBoardAdminVo", gBoardAdminVo);
			
		}
		//gBoardAdminSvc.updateGBoardStatus2(gBoardAdminVo);
		//model.addAttribute("gubara_Category", cmmCodeSvc.retrieveListCmmCode("gubara_Category"));
		return "/injeinc/boffice/ex/bbs/gBoardAdmin/gBoardIntergResp_Pop";
	}
	
	/**
	 * 인바라게시물관리 상세 > 통합답변 입력/수정 화면
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/boffice/ex/bbs/gBoardAdmin/gBoardIntergRespSaveAx.do", method = RequestMethod.POST)
	@ResponseBody
	public void GBoardIntergRespSaveAx(MultipartHttpServletRequest request ,HttpServletResponse response, GBoardAdminVo gBoardAdminVo, @ModelAttribute("BbsContentVo")BbsContentVo bbsContentVo) throws Exception {
		HttpSession ses = request.getSession();
		
		String gbnVal = EgovStringUtil.nullConvert(request.getParameter("gbnVal"));
		
		gBoardAdminVo.setBcIdx(EgovStringUtil.zeroConvert(request.getParameter("bcIdx")));
		gBoardAdminVo.setCbIdx(EgovStringUtil.zeroConvert(request.getParameter("cbIdx")));
		gBoardAdminVo.setAuditYn(EgovStringUtil.nullConvert(request.getParameter("auditYn")));
		gBoardAdminVo.setMcPointTxt(EgovStringUtil.nullConvert(request.getParameter("mcPointTxt")));
		gBoardAdminVo.setContentClob(EgovStringUtil.nullConvert(request.getParameter("contentClob")));
		gBoardAdminVo.setRegId((String)ses.getAttribute("SesUserId"));
		gBoardAdminVo.setModId((String)ses.getAttribute("SesUserId"));
		
		if("I".equals(gbnVal)){
			gBoardAdminVo.setMcIdx(gBoardAdminSvc.registMcIdxNo(gBoardAdminVo));
			//인바라 게시물 상태값
			if( "27000200".equals(EgovStringUtil.nullConvert(request.getParameter("auditYn"))) ){
				gBoardAdminVo.setMwStatusCont("20000600");
			}else{
				gBoardAdminVo.setAuditYn("27000100");
				gBoardAdminVo.setMwStatusCont("20000800");
			}
			gBoardAdminSvc.saveIntergResp(request, gBoardAdminVo);
			
		}else if("U".equals(gbnVal)){
			gBoardAdminVo.setMcIdx(EgovStringUtil.nullConvert(request.getParameter("mcIdx")));
			gBoardAdminSvc.updateIntergResp(request, gBoardAdminVo);
		}
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", "ok");
		jsonView.render(jsonMap, request, response);
		
		//return "/injeinc/boffice/ex/bbs/gBoardAdmin/gBoardIntergResp_Pop";
	}
	
	
	
	/**
	 * 인바라게시물관리 상세 > 인바라게시물 답변미대상민원 처리
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/boffice/ex/bbs/gBoardAdmin/gBoardDelayDtModAx.do")
	public void GBoardDelayDtModAx(HttpServletRequest request ,HttpServletResponse response, GBoardAdminVo gBoardAdminVo) throws Exception {
		
		HttpSession ses = request.getSession();
		
		gBoardAdminVo.setBcIdx(EgovStringUtil.zeroConvert(request.getParameter("bcIdx")));
		gBoardAdminVo.setCbIdx(EgovStringUtil.zeroConvert(request.getParameter("cbIdx")));
		gBoardAdminVo.setMcIdx(request.getParameter("mcIdx"));
		gBoardAdminVo.setModId((String)ses.getAttribute("SesUserId"));
		gBoardAdminVo.setMcDelayRsn(EgovStringUtil.nullConvert(request.getParameter("mcDelayRsn")));
		gBoardAdminVo.setMcDelayDay(EgovStringUtil.nullConvert(request.getParameter("mcDelayDay")).replace("-", ""));
		
		//System.out.println("gBoardAdminVo ============>> : "+gBoardAdminVo);
		
		gBoardAdminSvc.gubaraBoardDelayDtMod(gBoardAdminVo);
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", "ok");
		jsonView.render(jsonMap, request, response);
	}
	
	
	
	/**
	 * 인바라게시물관리 상세 > 담당부서지정
	 * @param request
	 * @param GBoardAdminVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/boffice/ex/bbs/gBoardAdmin/gBoardDep_Pop.do")
	public String GBoardDepPop(HttpServletRequest request, @ModelAttribute("GBoardAdminVo") GBoardAdminVo gBoardAdminVo
							, ModelMap model) throws Exception {
		
		//인바라 게시글 담당부서
		
		List cdSelectBoxList = gBoardAdminSvc.cdSelectBox();
		
		if(model != null){
			model.addAttribute("cdSelectBoxList", cdSelectBoxList);
			//model.addAttribute("gubara_Category", cmmCodeSvc.retrieveListCmmCode("gubara_Category"));
		}
		
	
		//model.addAttribute("gBoardAdminVo", gBoardAdminVo);
		
		return "/injeinc/boffice/ex/bbs/gBoardAdmin/gBoardDep_Pop";		
	}
	
	/**
	 * 담당부서지정 등록
	 * @param request
	 * @param gBoardAdminVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/boffice/ex/bbs/gBoardAdmin/gBoardDep_RegPop.do")
	public void BoardDepRegPop(
			HttpServletRequest request, HttpServletResponse response,@ModelAttribute("GBoardAdminVo") GBoardAdminVo gBoardAdminVo
			, @ModelAttribute("BbsFVo") BbsFVo bbsFVo
			, ModelMap model) throws Exception {
		HttpSession ses = request.getSession();
		
		int cbIdx = EgovStringUtil.zeroConvert(request.getParameter("cbIdx"));
		int bcIdx = EgovStringUtil.zeroConvert(request.getParameter("bcIdx"));
		String mode = EgovStringUtil.nullConvert(request.getParameter("mode"));
		String cdSelectBoxList[] = request.getParameterValues("cdSelectBoxList");
		
		gBoardAdminVo.setRegId((String)ses.getAttribute("SesUserId"));
		
		gBoardAdminVo.setCbIdx(cbIdx);
		gBoardAdminVo.setBcIdx(bcIdx);
		
		String cdSubjects = "";
		boolean isFirst = false;
		for (int i=0; i < cdSelectBoxList.length; i++) {
			
			if( "".equals(EgovStringUtil.nullConvert(cdSelectBoxList[i])) ){
			}else{
				
				gBoardAdminVo.setMcDeptCd(cdSelectBoxList[i]);
			
				gBoardAdminVo.setMcStatus("Y");
				//gBoardAdminSvc.insertContentMinwonResult(gBoardAdminVo); 
				
				if(!isFirst){
					isFirst = true;
					cdSubjects += cdSelectBoxList[i];
				}else{
					cdSubjects += "," + cdSelectBoxList[i];
				}
			}
		}
		
		gBoardAdminVo.setMcDeptCd(cdSubjects);
		gBoardAdminSvc.updateGBoardCdSubject(gBoardAdminVo);
		
		//상태값 : 20000700(부서확인) 
		gBoardAdminVo.setMwStatusCont("20000700");
		gBoardAdminSvc.updateGBoardStatus(gBoardAdminVo);
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", "ok");
		jsonView.render(jsonMap, request, response);

	}
	
	
	/**
	 * 담당부서담당자 등록
	 * @param request
	 * @param gBoardAdminVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/boffice/ex/bbs/gBoardAdmin/gBoardMcReplyer_RegPop.do")
	public void BoardMcReplyerRegPop(
			HttpServletRequest request, HttpServletResponse response, @ModelAttribute("GBoardAdminVo") GBoardAdminVo gBoardAdminVo
			, ModelMap model) throws Exception {
		HttpSession ses = request.getSession();
		
		int cbIdx = EgovStringUtil.zeroConvert(request.getParameter("cbIdx"));
		int bcIdx = EgovStringUtil.zeroConvert(request.getParameter("bcIdx"));
		String mcIdx = EgovStringUtil.nullConvert(request.getParameter("mcIdx"));
		String mcReplyer = EgovStringUtil.nullConvert(request.getParameter("mcReplyer"));
		
		GBoardAdminVo gBoardAdminVo2 = new GBoardAdminVo();
		
		gBoardAdminVo2.setModId((String)ses.getAttribute("SesUserId"));
		
		gBoardAdminVo2.setCbIdx(cbIdx);
		gBoardAdminVo2.setBcIdx(bcIdx);
		gBoardAdminVo2.setMcIdx(mcIdx);
		gBoardAdminVo2.setMcReplyer(mcReplyer);
		
		gBoardAdminSvc.updateContentMinwonResult(gBoardAdminVo2);

		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", "ok");
		jsonView.render(jsonMap, request, response);

	}
	
	/**
	 * 민원결과 조회
	 * @param request
	 * @param gBoardAdminVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
//	@RequestMapping(value= "/boffice/ex/bbs/gBoardAdmin/gBoardMwResult_Pop.do")
//	public void BoardMwResultPop(
//			HttpServletRequest request, HttpServletResponse response, @ModelAttribute("GBoardAdminVo") GBoardAdminVo gBoardAdminVo
//			, ModelMap model) throws Exception {
//		HttpSession ses = request.getSession();
//		
//		String cbIdx = request.getParameter("cbIdx");
//		String bcIdx = request.getParameter("bcIdx");
//		String mcIdx = request.getParameter("mcIdx");
//		
//		gBoardAdminVo.setCbIdx(cbIdx);
//		gBoardAdminVo.setBcIdx(bcIdx);
//		gBoardAdminVo.setMcIdx(mcIdx);
//		
//		Map mwResultMap = gBoardAdminSvc.selectMwResultMap(gBoardAdminVo);
//		
//		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
//		jsonMap.put("result", mwResultMap);
//		jsonView.render(jsonMap, request, response);
//
//	}
	
	
	/**
	 * 부서삭제
	 * @param request
	 * @param gBoardAdminVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/boffice/ex/bbs/gBoardAdmin/gBoardDep_Del.do")
	public void BoardDepDel(
			HttpServletRequest request, HttpServletResponse response, @ModelAttribute("GBoardAdminVo") GBoardAdminVo gBoardAdminVo
			, ModelMap model) throws Exception {
		HttpSession ses = request.getSession();
		
		int cbIdx = EgovStringUtil.zeroConvert(request.getParameter("cbIdx"));
		int bcIdx = EgovStringUtil.zeroConvert(request.getParameter("bcIdx"));
		String mcIdx = EgovStringUtil.nullConvert(request.getParameter("mcIdx"));
		
		gBoardAdminVo.setCbIdx(cbIdx);
		gBoardAdminVo.setBcIdx(bcIdx);
		gBoardAdminVo.setMcIdx(mcIdx);
		
		gBoardAdminSvc.deleteDep(gBoardAdminVo);
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", "ok");
		jsonView.render(jsonMap, request, response);

	}
	
	
	/**
	 * 인바라게시물관리 상세 > 담당부서변경
	 * @param request
	 * @param GBoardAdminVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/boffice/ex/bbs/gBoardAdmin/gBoardUpdateDep_Pop.do")
	public String GBoardUpdateDepPop(HttpServletRequest request, @ModelAttribute("GBoardAdminVo") GBoardAdminVo gBoardAdminVo
							, ModelMap model) throws Exception {
		
		//인바라 게시글 담당부서
		List cdSelectBoxList = gBoardAdminSvc.cdSelectBox();
		if(model != null){
			model.addAttribute("cdSelectBoxList", cdSelectBoxList);
		}
		//model.addAttribute("gBoardAdminVo", gBoardAdminVo);
		
		return "/injeinc/boffice/ex/bbs/gBoardAdmin/gBoardDeptChange_Pop";		
	}
	
	/**
	 * 담당부서변겅
	 * @param request
	 * @param gBoardAdminVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/boffice/ex/bbs/gBoardAdmin/gBoardUpdateDep_RegPop.do")
	public void BoardUpdateDepPop(
			HttpServletRequest request, HttpServletResponse response, @ModelAttribute("GBoardAdminVo") GBoardAdminVo gBoardAdminVo
			, ModelMap model) throws Exception {
		HttpSession ses = request.getSession();
		
		int cbIdx = EgovStringUtil.zeroConvert(request.getParameter("cbIdx"));
		int bcIdx = EgovStringUtil.zeroConvert(request.getParameter("bcIdx"));
		String mcIdx = EgovStringUtil.nullConvert(request.getParameter("mcIdx"));
		String mcDeptHist = EgovStringUtil.nullConvert(request.getParameter("mcDeptHist"));
		String mcDeptCd = EgovStringUtil.nullConvert(request.getParameter("cdSelectBoxList"));
		
		gBoardAdminVo.setModId((String)ses.getAttribute("SesUserId"));
		gBoardAdminVo.setCbIdx(cbIdx);
		gBoardAdminVo.setBcIdx(bcIdx);
		gBoardAdminVo.setMcIdx(mcIdx);
		gBoardAdminVo.setMcDeptCd(mcDeptCd);
		gBoardAdminVo.setMcDeptHist(mcDeptHist);
		gBoardAdminSvc.updateDep(gBoardAdminVo);
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", "ok");
		jsonView.render(jsonMap, request, response);

	}
	
	
	/**
	 * 1, 2차 대화내용 저장
	 * @param request
	 * @param gBoardAdminVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/boffice/ex/bbs/gBoardAdmin/gBoardMcContent_Reg.do")
	public void BoardMcContentReg(
			HttpServletRequest request, HttpServletResponse response, @ModelAttribute("GBoardAdminVo") GBoardAdminVo gBoardAdminVo
			, ModelMap model) throws Exception {
		HttpSession ses = request.getSession();
		
		int cbIdx = EgovStringUtil.zeroConvert(request.getParameter("cbIdx"));
		int bcIdx = EgovStringUtil.zeroConvert(request.getParameter("bcIdx"));
		String mcIdx = EgovStringUtil.nullConvert(request.getParameter("mcIdx"));
		
		String gbn = EgovStringUtil.nullConvert(request.getParameter("gbnVal"));
		
		String mcBSender	 = EgovStringUtil.nullConvert(request.getParameter("mcBSender_"+mcIdx));
		String mcBMth		 = EgovStringUtil.nullConvert(request.getParameter("mcBMth_"+mcIdx));
		String mcBTxt		 = EgovStringUtil.nullConvert(request.getParameter("mcBTxt_"+mcIdx));
		String mcASender	 = EgovStringUtil.nullConvert(request.getParameter("mcASender_"+mcIdx));
		String mcAMth		 = EgovStringUtil.nullConvert(request.getParameter("mcAMth_"+mcIdx));
		String mcATxt		 = EgovStringUtil.nullConvert(request.getParameter("mcATxt_"+mcIdx));
		
		GBoardAdminVo gBoardAdminVo2 = new GBoardAdminVo();
		
		gBoardAdminVo2.setModId((String)ses.getAttribute("SesUserId"));
		gBoardAdminVo2.setCbIdx(cbIdx);
		gBoardAdminVo2.setBcIdx(bcIdx);
		gBoardAdminVo2.setMcIdx(mcIdx);
		
		if("B".equals(gbn)){
			gBoardAdminVo2.setMcBSender(mcBSender);
			gBoardAdminVo2.setMcBMth(mcBMth);
			gBoardAdminVo2.setMcBTxt(mcBTxt);
		}else if("A".equals(gbn)){
			gBoardAdminVo2.setMcASender(mcASender);
			gBoardAdminVo2.setMcAMth(mcAMth);
			gBoardAdminVo2.setMcATxt(mcATxt);
		}
		
		gBoardAdminSvc.updateMcContentReg(gBoardAdminVo2);
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", "ok");
		jsonView.render(jsonMap, request, response);

	}
	
	/**
	 * 답변초기화
	 * @param request
	 * @param gBoardAdminVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/boffice/ex/bbs/gBoardAdmin/gBoardRepl_Del.do")
	public void BoardReplDel(
			HttpServletRequest request, HttpServletResponse response, @ModelAttribute("GBoardAdminVo") GBoardAdminVo gBoardAdminVo
			, ModelMap model) throws Exception {
		HttpSession ses = request.getSession();
		
		int cbIdx = EgovStringUtil.zeroConvert(request.getParameter("cbIdx"));
		int bcIdx = EgovStringUtil.zeroConvert(request.getParameter("bcIdx"));
		String mcIdx = EgovStringUtil.nullConvert(request.getParameter("mcIdx"));
		
		
		GBoardAdminVo gBoardAdminVo2 = new GBoardAdminVo();
		
		gBoardAdminVo2.setModId((String)ses.getAttribute("SesUserId"));
		gBoardAdminVo2.setCbIdx(cbIdx);
		gBoardAdminVo2.setBcIdx(bcIdx);
		gBoardAdminVo2.setMcIdx(mcIdx);
		
		Map param = new HashMap<String, String>();
		
		param.put("cbIdx", cbIdx);
		param.put("bcIdx", bcIdx);
		param.put("mcIdx", mcIdx);
		param.put("regId", (String)ses.getAttribute("SesUserId"));
		
		gBoardAdminSvc.updateReplDel(gBoardAdminVo2);
		gBoardAdminSvc.boardRemoveFiles(param);
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", "ok");
		jsonView.render(jsonMap, request, response);

	}
	
	/**
	 * 답변등록 화면
	 * @param request
	 * @param gBoardAdminVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/boffice/ex/bbs/gBoardAdmin/gBoardRepl_Reg.do")
	public String BoardReplReg(
			HttpServletRequest request, HttpServletResponse response, @ModelAttribute("GBoardAdminVo") GBoardAdminVo gBoardAdminVo
			, ModelMap model) throws Exception {
		
		HttpSession ses = request.getSession();
		LoginVo cmsUser = (LoginVo)ses.getAttribute("CMSUSER");
		
		String modGbn = gBoardAdminVo.getModGbn();
		
		//상태값 : 20000700(부서확인) 
//		gBoardAdminVo.setMwStatusCont("20000700");
//		gBoardAdminSvc.updateGBoardStatus(gBoardAdminVo);
		
		//상세내용 조회
		Map detailMap = gBoardAdminSvc.gubaraBoardDetail(gBoardAdminVo);
		
		/* 등록화면에서 세션값 넣기 */
		if("I".equals(gBoardAdminVo.getModGbn())){
			detailMap.put("mcReplyer",cmsUser.getUserName());
		}
		
		//File List
		List detailFileList = gBoardAdminSvc.retrieveFileList(gBoardAdminVo);
		
		List detailDepList = gBoardAdminSvc.retrieveDepList(gBoardAdminVo);
		
		/*답변리스트*/
		Map detailDepMap = gBoardAdminSvc.retrieveDepMap(gBoardAdminVo);
		
		List detailDeptFileList = gBoardAdminSvc.retrieveFileList(gBoardAdminVo);
		
		gBoardAdminVo.setMcDeptCd(gBoardAdminVo.getMcDeptCd().trim());
		
		

		//인재 selectbox
		List selectReplDeptList = gBoardAdminSvc.selectReplDept(gBoardAdminVo);
		//단위업무명1차 selectbox
		List selectDw1List = gBoardAdminSvc.selectDw1();
		
		//역대 인재 select
		Map chiefHistoryMap = gBoardAdminSvc.selectChiefHistory();
		
		model.addAttribute("gubara_Category", cmmCodeSvc.retrieveListCmmCode("gubara_Category"));
		
		if(detailDepList.size() > 0){
			if(model != null){
				model.addAttribute("detailDepList", detailDepList);
				
			}
		}else{
			if(model != null){
				model.addAttribute("detailDepList", "");
			}
		}
		if(model != null){
			model.addAttribute("detailDepMap", detailDepMap);
			model.addAttribute("detailMap", detailMap);
			model.addAttribute("detailFileList", detailFileList);
			model.addAttribute("detailDeptFileList", detailDeptFileList);
			model.addAttribute("selectReplDeptList", selectReplDeptList);
			model.addAttribute("selectDw1List", selectDw1List);
			model.addAttribute("gBoardAdminVo", gBoardAdminVo);
			model.addAttribute("chiefHistoryMap", chiefHistoryMap);
			
			
		}
		
		
		return "/injeinc/boffice/ex/bbs/gBoardAdmin/gBoardAdmin_replForm";	

	}
	
	/**
	 * 답변등록
	 * @param request
	 * @param gBoardAdminVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/boffice/ex/bbs/gBoardAdmin/gBoardLastRepl_Reg.do")
	public String BoardLastReplReg(
			MultipartHttpServletRequest request, @ModelAttribute("GBoardAdminVo") GBoardAdminVo gBoardAdminVo, @ModelAttribute("BbsContentVo") BbsContentVo bbsContentVo, @ModelAttribute("BbsFVo") BbsFVo bbsFVo
			, ModelMap model) throws Exception {
		
		String SVC_MSGE = "";
		String returnUrl = "";
		String param = "";
		
		int cbIdx = gBoardAdminVo.getCbIdx();
		int bcIdx = gBoardAdminVo.getBcIdx();
		String mcIdx = gBoardAdminVo.getMcIdx();
		
		String gbnVal = gBoardAdminVo.getGbnVal();
		
		param = "?cbIdx="+cbIdx+"&bcIdx="+bcIdx+"&mcIdx="+mcIdx;
		
		String multiComplainClose = request.getParameter("multiComplainClose");
	
		try{
			HttpSession ses = request.getSession();

			gBoardAdminVo.setRegId((String)ses.getAttribute("SesUserId"));
			
			LoginVo cmsUser = (LoginVo)ses.getAttribute("CMSUSER");
			
			if( "N".equals(gbnVal) ){
				// gbnVal -> N:중간답변
				// 사용안함
				gBoardAdminVo.setTempYn(gbnVal);
				gBoardAdminSvc.boardLastReplReg(gBoardAdminVo);
				gBoardAdminSvc.boardReplReg(gBoardAdminVo);
			}else if( "Y".equals(gbnVal) ){
				// gbnVal -> Y:최종답변
				model.addAttribute("gubara_Category", cmmCodeSvc.retrieveListCmmCode("gubara_Category"));
				
				/* 답변등록 */
				gBoardAdminVo.setMwStatusCont("20001100");
				gBoardAdminVo.setMcDeptCd(cmsUser.getDeptCd());
				gBoardAdminVo.setMcStatus("Y");
				gBoardAdminSvc.insertContentMinwonResult(gBoardAdminVo);
				
				//상태값 : 20001100(답변게재) 
				gBoardAdminVo.setMwStatusCont("20001100");
				
				if("Y".equals(multiComplainClose)){
					gBoardAdminVo.setMwStatusCont("20001400");
				}
				
				gBoardAdminSvc.updateGBoardStatus(gBoardAdminVo);
				
				int minwonReCnt = bbsFSvc.minwonReCnt(bbsFVo);
				
				if(minwonReCnt == 1){
					
					Map<String, Object> gBoardMap = bbsContentSvc.retrieveBbsContent(bbsContentVo);
					
					String ansCompCont = (String)gBoardMap.get("ansCompCont");
					
					if(ansCompCont != null){
						if(ansCompCont.indexOf("23000100") > -1) { //문자
							
							UmsVo umsVo = new UmsVo();

							umsVo.setSubject((String)gBoardMap.get("subCont"));				// 문자제목
							umsVo.setAddress((String)gBoardMap.get("nameCont")+"^"+((String)gBoardMap.get("phoneCont")).replaceAll("-", ""));	// 반는사람 핸드폰번호 ex)홍길동^01012341234, 2명이상때:홍길동^01012341234|홍길순^01112341234|
							umsVo.setContent("귀하께서 인재아이앤씨 홈페이지에 올리신 인재에게 바란다 답변처리가 완료되었습니다. 감사합니다.");	// 문자 내용 80byte 이하
							umsVo.setCallbackNo(gBoardAdminVo.getMcTel1().replace("-", ""));	// 발송번호 ex) 0221556264
		
							UmsUtil umsUtil = new UmsUtil();
							umsUtil.send(umsVo);
						}
						
						if(ansCompCont.indexOf("23000200") > -1) { //음성
							
							UmsVo umsVo = new UmsVo();

							umsVo.setSendType("vms");				// 발송타입(sms 문자, vms 음성, fms 펙스)
							umsVo.setSubject((String)gBoardMap.get("subCont"));				// 문자제목
							umsVo.setAddress((String)gBoardMap.get("nameCont")+"^"+((String)gBoardMap.get("phoneCont")).replaceAll("-", ""));	// 반는사람 핸드폰번호 ex)홍길동^01012341234, 2명이상때:홍길동^01012341234|홍길순^01112341234|
							umsVo.setContent("안녕하세요 인재아이앤씨청 입니다. "+(String)gBoardMap.get("nameCont")+"님께서 인재아이앤씨청 홈페이지 인재에게 바란다에 올리신 글제목 :"+(String)gBoardMap.get("subCont")+"에 대한 답변처리가 완료 되었습니다. 홈페이지에서 확인하시기 바랍니다. 감사합니다.");	// 문자 내용 80byte 이하
							umsVo.setCallbackNo(gBoardAdminVo.getMcTel1().replace("-", ""));	// 발송번호 ex) 0221556264
		
							UmsUtil umsUtil = new UmsUtil();
							umsUtil.send(umsVo);
							
						}
						
						if(ansCompCont.indexOf("23000300") > -1) { //이메일
							
							String strMsg = "안녕하세요 인재아이앤씨청입니다."
								   + "<br/><br/>"+(String)gBoardMap.get("nameCont")+"님께서 인재아이앤씨청 홈페이지 인재에게 바란다에 올리신"
								   + "<br/><br/>글제목 : "+(String)gBoardMap.get("subCont")+"에 대한 답변처리가 완료 되었습니다."
								   + "<br/><br/><strong>답변내용은 인재아이앤씨청 홈페이지에서 확인 가능하며, <a href=\"/site/om/ex/bbs/View.do?cbIdx="+String.valueOf(gBoardMap.get("cbIdx"))+"&bcIdx="+String.valueOf(gBoardMap.get("bcIdx"))+"\" target=\"_blank\">바로가기</a>를 누르시면 답변으로 이동됩니다. 감사합니다.</strong>";
		
							MailVo mailVo = new MailVo();
									
							mailVo.setSubject((String)gBoardMap.get("subCont"));			//메일 제목
							mailVo.setTo((String)gBoardMap.get("nameCont"));							//수신자 이름
							mailVo.setReceipt((String)gBoardMap.get("emailCont"));			//수신자 이메일
							mailVo.setBody(strMsg);	//메일 내용
							
							MailUtil mailUtil = new MailUtil();
							mailUtil.send(mailVo);
							
						}
					}
				}
				
			}else if( "U".equals(gbnVal) ){
				// gbnVal -> U:수정
				gBoardAdminVo.setMcDeptCd(cmsUser.getDeptCd());
				gBoardAdminSvc.boardLastReplReg(gBoardAdminVo);
				
				//상태값 : 20001100(답변게재) 
				gBoardAdminVo.setMwStatusCont("20001100");
				if("Y".equals(multiComplainClose)){
					gBoardAdminVo.setMwStatusCont("20001400");
				}
				gBoardAdminSvc.updateGBoardStatus(gBoardAdminVo);
			}
			
			gBoardAdminSvc.boardRegistFiles(request ,gBoardAdminVo);
			
			SVC_MSGE = "처리되었습니다.";	//수정완료
			returnUrl = "/boffice/ex/bbs/gBoardAdmin/gBoardAdmin_view.do"+param;
			
			model.addAttribute("gBoardAdminVo", gBoardAdminVo);
			
		} catch (Exception e) {
			SVC_MSGE = Message.getMessage("901.message");	//에러
			throw e;
		}
		
	
		
		return alert(returnUrl, SVC_MSGE, model);
	}
	

	@RequestMapping(value= "/boffice/ex/bbs/gBoardAdmin/gBoardLastReplArrReg.do")
	public void gBoardLastReplArrReg(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("GBoardAdminVo") GBoardAdminVo gBoardAdminVo, @ModelAttribute("BbsContentVo") BbsContentVo bbsContentVo, @ModelAttribute("BbsFVo") BbsFVo bbsFVo, ModelMap model) throws Exception {

		
		boolean result = true;
		String message = "";
		
		String userid = EgovStringUtil.isNullToString(WebUtils.getSessionAttribute(request, "SesUserId"));
		
		String searchBcIdx = EgovStringUtil.isNullToString(request.getParameter("searchBcIdxArr"));
		System.out.println("searchBcIdx=========>"+searchBcIdx);
		
		String[] searchBcIdxArr = searchBcIdx.split(",");
		
		System.out.println("searchBcIdxArr.length=========>"+searchBcIdxArr.length);
		
		int successCnt = 0;
		int failCnt = 0;
		
		if(searchBcIdxArr.length > 0 && !userid.equals("")) {

			gBoardAdminVo.setRegId(userid);
			gBoardAdminVo.setTempYn("Y");

			for(int i = 0; i < searchBcIdxArr.length; i++) {
				
				try {
					gBoardAdminVo.setBcIdx(Integer.parseInt(searchBcIdxArr[i]));
					gBoardAdminSvc.boardLastReplRegArr(gBoardAdminVo);
					
					bbsContentVo.setBcIdx(Integer.parseInt(searchBcIdxArr[i]));
					bbsContentVo.setCbIdx(414);
					Map<String, Object> gBoardMap = bbsContentSvc.retrieveBbsContent(bbsContentVo);
					
					String ansCompCont = EgovStringUtil.isNullToString(gBoardMap.get("ansCompCont"));
					String subCont = EgovStringUtil.isNullToString(gBoardMap.get("subCont"));
					String nameCont = EgovStringUtil.isNullToString(gBoardMap.get("nameCont"));
					String phoneCont = EgovStringUtil.isNullToString(gBoardMap.get("phoneCont"));
					String emailCont = EgovStringUtil.isNullToString(gBoardMap.get("emailCont"));
					String cbIdx = String.valueOf(gBoardMap.get("cbIdx"));
					String bcIdx = String.valueOf(gBoardMap.get("bcIdx"));
					
					if(ansCompCont.indexOf("23000100") > -1) { //문자
						
						UmsVo umsVo = new UmsVo();
	
						umsVo.setSubject(subCont);				// 문자제목
						umsVo.setAddress(nameCont+"^"+phoneCont.replaceAll("-", ""));	// 반는사람 핸드폰번호 ex)홍길동^01012341234, 2명이상때:홍길동^01012341234|홍길순^01112341234|
						umsVo.setContent("귀하께서 인재아이앤씨 홈페이지에 올리신 인재에게 바란다 답변처리가 완료되었습니다. 감사합니다.");	// 문자 내용 80byte 이하
						umsVo.setCallbackNo(gBoardAdminVo.getMcTel1().replace("-", ""));	// 발송번호 ex) 0221556264
	
						UmsUtil umsUtil = new UmsUtil();
						umsUtil.send(umsVo);
					}
					
					if(ansCompCont.indexOf("23000300") > -1) { //이메일
						
						String strMsg = "안녕하세요 인재아이앤씨청입니다."
							+ "<br/><br/>"+nameCont+"님께서 인재아이앤씨 홈페이지 인재에게 바란다에 올리신"
							+ "<br/><br/>글제목 : "+subCont+"에 대한 답변처리가 완료 되었습니다."
							+ "<br/><br/><strong>답변내용은 인재아이앤씨 홈페이지에서 확인 가능하며, <a href=\"/site/om/ex/bbs/View.do?cbIdx="+cbIdx+"&bcIdx="+bcIdx+"\" target=\"_blank\">바로가기</a>를 누르시면 답변으로 이동됩니다. 감사합니다.</strong>";
	
						MailVo mailVo = new MailVo();
								
						mailVo.setSubject(subCont);			//메일 제목
						mailVo.setTo(nameCont);				//수신자 이름
						mailVo.setReceipt(emailCont);		//수신자 이메일
						mailVo.setBody(strMsg);	//메일 내용
						
						MailUtil mailUtil = new MailUtil();
						mailUtil.send(mailVo);
						
					}
					
					successCnt++;
				} catch (Exception e) {
					e.printStackTrace();
					failCnt++;
				}
			}
			
			result = true;
			message = "일괄 답변 되었습니다.(성공:"+successCnt+"개, 실패:"+failCnt+"개)";
		
		}else{
			message = "필요한 자료가 없습니다.";
		}
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", result);
		jsonMap.put("message", message);
		jsonView.render(jsonMap, request, response);

	}
	
	
	/**
	 * 인바라게시물관리 상세 > 담당자 저장 화면
	 * @param request
	 * @param GBoardAdminVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/boffice_nodeco/ex/bbs/gBoardAdmin/gBoardDepReplyer_RegPop.do")
	public String GBoardDepReplyerPop(HttpServletRequest request, @ModelAttribute("GBoardAdminVo") GBoardAdminVo gBoardAdminVo
							, ModelMap model) throws Exception {
		
		return "/injeinc/boffice/ex/bbs/gBoardAdmin/gBoardDepReplyer_Pop";		
	}
	
	/**
	 * 인바라 만족도조사
	 * @param request
	 * @param GBoardAdminVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/boffice/ex/bbs/gBoardAdmin/gBoardAdmin_Surveylist.do")
	public String GBoardAdminSurveylist(HttpServletRequest request, @ModelAttribute("GBoardAdminVo") GBoardAdminVo gBoardAdminVo
										, ModelMap model) throws Exception {
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(gBoardAdminVo.getPageIndex());
		if(gBoardAdminVo.getPageUnit() < 15){
			gBoardAdminVo.setPageUnit(15);
		}
		paginationInfo.setRecordCountPerPage(gBoardAdminVo.getPageUnit());
		paginationInfo.setPageSize(gBoardAdminVo.getPageSize());
		
		gBoardAdminVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		gBoardAdminVo.setLastIndex(paginationInfo.getLastRecordIndex());
		gBoardAdminVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		
		int totCnt = gBoardAdminSvc.retrievePagSurvey(gBoardAdminVo);
		paginationInfo.setTotalRecordCount(totCnt);
		
		@SuppressWarnings("rawtypes")
		List resultList = gBoardAdminSvc.retrieveSurveyList(gBoardAdminVo);
		if(model != null){
			model.addAttribute("totCnt",totCnt);
			model.addAttribute("resultList", resultList);
			model.addAttribute("paginationInfo", paginationInfo);
		}
		
		return "/injeinc/boffice/ex/bbs/gBoardAdmin/gBoardAdmin_Surveylist";		
	}
	
	/**
	 * 인바라 만족도조사 엑셀
	 * @param request
	 * @param GBoardAdminVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/boffice/ex/bbs/gBoardAdmin/gBoardAdmin_SurveyExcel.do")
	public String GBoardAdminSurveyExcel(HttpServletRequest request,
										 HttpServletResponse response,
										 @ModelAttribute("GBoardAdminVo") GBoardAdminVo gBoardAdminVo, 
										 ModelMap model) throws Exception {
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(gBoardAdminVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(gBoardAdminVo.getPageUnit());
		paginationInfo.setPageSize(gBoardAdminVo.getPageSize());
		
		gBoardAdminVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		gBoardAdminVo.setLastIndex(paginationInfo.getLastRecordIndex());
		gBoardAdminVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		
		int totCnt = gBoardAdminSvc.retrievePagSurvey(gBoardAdminVo);
		paginationInfo.setTotalRecordCount(totCnt);
		
		@SuppressWarnings("rawtypes")
		List resultList = gBoardAdminSvc.retrieveSurveyExcel(gBoardAdminVo);
		if(model != null){
			model.addAttribute("totCnt",totCnt);
			model.addAttribute("resultList", resultList);
			model.addAttribute("paginationInfo", paginationInfo);
		}
		
		String nowDate = DateUtil.getCurrentDate("yyyyMMdd");
		String fileName = "";
		if(gBoardAdminVo.getScSDate() != "" && gBoardAdminVo.getScEDate() != ""){
			fileName = "인재에게바란다(만족도조사)_"+gBoardAdminVo.getScSDate()+"~"+gBoardAdminVo.getScEDate()+".xls";
		}else{
			if(gBoardAdminVo.getScSDate() != ""){
				fileName = "인재에게바란다(만족도조사)_"+gBoardAdminVo.getScSDate()+"이후.xls";
			}else if(gBoardAdminVo.getScEDate() != ""){
				fileName = "인재에게바란다(만족도조사)_"+gBoardAdminVo.getScEDate()+"이전.xls";
			}else{
				fileName = "인재에게바란다(만족도조사).xls";
			}
		}
		
		String encodedFilename = URLEncoder.encode(fileName, "UTF-8");
		
		request.setCharacterEncoding("utf-8");
		response.setHeader("Content-Disposition", "attachment; filename="+encodedFilename);
		response.setHeader("Content-Description", "JSP Generated Data");
		response.setContentType("application/vnd.ms-excel");
		
		return "/injeinc/boffice/ex/bbs/gBoardAdmin/gBoardAdmin_SurveyExcel";		
	}
	
	//부서일괄지정
	@RequestMapping(value= "/boffice/ex/bbs/gBoardAdmin/gBoardSelectDepartArr.do")
	public void gBoardSelectDepartArr(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		
		boolean result = true;
		String message = "";
		
		String userid = EgovStringUtil.isNullToString(WebUtils.getSessionAttribute(request, "SesUserId"));
		
		String searchBcIdx = EgovStringUtil.isNullToString(request.getParameter("searchBcIdx"));
		String cdSelectBoxList = EgovStringUtil.isNullToString(request.getParameter("cdSelectBoxList"));
		
		System.out.println("searchBcIdx=========>"+searchBcIdx);
		System.out.println("cdSelectBoxList=========>"+cdSelectBoxList);
		
		String[] searchBcIdxArr = searchBcIdx.split(",");
		String[] cdSelectBoxListArr = cdSelectBoxList.split(",");
		
		System.out.println("searchBcIdxArr.length=========>"+searchBcIdxArr.length);
		System.out.println("cdSelectBoxListArr.length=========>"+cdSelectBoxListArr.length);
		
		if(searchBcIdxArr.length > 0 && cdSelectBoxListArr.length > 0 && !userid.equals("")) {
		
			GBoardAdminVo gBoardAdminVo = new GBoardAdminVo();
			
			gBoardAdminVo.setCbIdx(414);
			gBoardAdminVo.setRegId(userid);

			for (int i = 0; i < searchBcIdxArr.length; i++) {
				
				System.out.println("searchBcIdxArr[i]=========>"+searchBcIdxArr[i]);

				gBoardAdminVo.setBcIdx(Integer.parseInt(searchBcIdxArr[i]));
				
				for (int j = 0; j < cdSelectBoxListArr.length; j++) {
					
					if(!cdSelectBoxListArr[j].equals("")) {
						gBoardAdminVo.setMcDeptCd(cdSelectBoxListArr[j]);
						gBoardAdminVo.setMcStatus("Y");
						gBoardAdminSvc.insertContentMinwonResult(gBoardAdminVo);
					}
				}
			}
			
			result = true;
			message = "답변부서를 지정하였습니다.";
		
		}else{
			message = "필요한 자료가 없습니다.";
		}
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", result);
		jsonMap.put("message", message);
		jsonView.render(jsonMap, request, response);

	}
	
	/**
	 * 인바라 처리상황 변경
	 * @param request
	 * @param GBoardAdminVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/boffice/ex/bbs/gBoardAdmin/gBoardAdmin_status.do")
	public void BoardStatus(
			HttpServletRequest request, HttpServletResponse response, @ModelAttribute("GBoardAdminVo") GBoardAdminVo gBoardAdminVo
			, ModelMap model) throws Exception {
		HttpSession ses = request.getSession();
		
		int cbIdx = EgovStringUtil.zeroConvert(request.getParameter("cbIdx"));
		int bcIdx = EgovStringUtil.zeroConvert(request.getParameter("bcIdx"));
		String mcIdx = EgovStringUtil.nullConvert(request.getParameter("mcIdx"));
		String mwStatusCont =  EgovStringUtil.isNullToString(request.getParameter("mwStatusCont"));
		

		
		gBoardAdminVo.setCbIdx(cbIdx);
		gBoardAdminVo.setBcIdx(bcIdx);
		gBoardAdminVo.setMcIdx(mcIdx);
		gBoardAdminVo.setMwStatusCont(mwStatusCont);
		
		gBoardAdminSvc.updateBoardStatus(gBoardAdminVo);
		
		/* 자유게시판으로 이전 */
		if("20001300".equals(mwStatusCont)){
			
			try {
				gBoardAdminVo.setModId((String)ses.getAttribute("SesUserId"));
				gBoardAdminSvc.trnasBoard(gBoardAdminVo);
			} catch (Exception e) {
				throw e;
			}
		}
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", "ok");
		jsonView.render(jsonMap, request, response);

	}

	/**
	 * 인바라 답변처리일자 변경
	 * @param request
	 * @param GBoardAdminVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/boffice/ex/bbs/gBoardAdmin/changeAnsDeadlineDt.do")
	public void changeAnsDeadlineDt(
			HttpServletRequest request, HttpServletResponse response, @ModelAttribute("GBoardAdminVo") GBoardAdminVo gBoardAdminVo
			, ModelMap model) throws Exception {
		HttpSession ses = request.getSession();
		
		int cbIdx = EgovStringUtil.zeroConvert(request.getParameter("cbIdx"));
		int bcIdx = EgovStringUtil.zeroConvert(request.getParameter("bcIdx"));
		String ansDeadlineDt = request.getParameter("ansDeadlineDt");
		
		
		
		gBoardAdminVo.setCbIdx(cbIdx);
		gBoardAdminVo.setBcIdx(bcIdx);
		gBoardAdminVo.setAnsDeadlineDt(ansDeadlineDt);
		gBoardAdminSvc.updateAnsDeadlineDt(gBoardAdminVo);
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", "ok");
		jsonView.render(jsonMap, request, response);
		
	}
	
	
}