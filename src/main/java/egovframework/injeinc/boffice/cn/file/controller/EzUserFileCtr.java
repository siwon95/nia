package egovframework.injeinc.boffice.cn.file.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.GlobalConst;
import egovframework.cmm.Message;
import egovframework.cmm.service.EgovProperties;
import egovframework.injeinc.boffice.cn.common.Util;
import egovframework.injeinc.boffice.cn.file.service.EzFileSvc;
import egovframework.injeinc.boffice.cn.file.service.EzUserFileSvc;
import egovframework.injeinc.boffice.cn.file.vo.EzFileVo;
import egovframework.injeinc.boffice.cn.file.vo.EzUserFileVo;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class EzUserFileCtr extends CmmLogCtr {
	@Resource(name = "EzUserFileSvc")
	private EzUserFileSvc ezUserFileSvc;
	
	@Resource(name = "EzFileSvc")
	private EzFileSvc ezFileSvc;
	
	/**  ez_user_file 등록 */
	@RequestMapping(value= "/boffice/cn/file/Ez_User_File_Reg.do")
	public String ezUserFileReg(
			HttpServletRequest request,
			@ModelAttribute("EzUserFileVo") EzUserFileVo ezUserFileVo,
			ModelMap model) throws Exception {
 
		//첨부파일 처리 시작 ---------------------------------------------------
		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
		String uploadFolder = "/upload/contents"; 
		String formFileName = "upfile";
		
		String atchFileId = new Util().ezFileUpload(request, rootPath, ezFileSvc, uploadFolder, formFileName,"");
		
		//첨부파일 처리 끝 ------------------------------------------------------
		if(!atchFileId.equals("")){
			ezUserFileVo.setAtchFileId(atchFileId);
			ezUserFileSvc.registEzUserFile(ezUserFileVo);
		}
		//System.out.println("완료 테스트");
		String SVC_MSGE = Message.getMessage("201.message"); //등록 성공
		return alert("/boffice/cn/file/Ez_User_File_List.do", SVC_MSGE, model);
	}

	/**  ez_user_file 상세보기 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value= "/boffice/cn/file/Ez_User_File_Form.do")
	public String Ez_User_FileForm(
		HttpServletRequest request,
		@ModelAttribute("EzUserFileVo") EzUserFileVo ezUserFileVo,
		ModelMap model) throws Exception {
		
		if(!(ezUserFileVo.getSeq()+"").replaceAll("null","").equals("")){
			
		
			ezUserFileVo = ezUserFileSvc.retrieveEzUserFile(ezUserFileVo);
			
			/*첨부파일 시작 -------------------------------------*/
			String atchFileId = ezUserFileVo.getAtchFileId(); //테이블별 구성에 맞게 변경
			
			EzFileVo ezFileVo = new EzFileVo();
			ezFileVo.setAtchFileId(atchFileId);
			List<EzFileVo> ezFileVoList = ezFileSvc.retrieveListEzFile(ezFileVo);
			if(model != null){
				model.addAttribute("ezFileVoList", ezFileVoList);
			}
			/*첨부파일 끝 -------------------------------------*/
		}
		
		if(model != null){
			model.addAttribute("EzUserFileVo", ezUserFileVo);
		}
		
		return GlobalConst.SITE_ROOT_BOFFICE_PATH + "/cn/file/ez_user_file_form";
	}

	/**  ez_user_file 목록 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value= "/boffice/cn/file/Ez_User_File_List.do")
	public String Ez_User_FileList(
		HttpServletRequest request,
		@ModelAttribute("EzUserFileVo") EzUserFileVo ezUserFileVo,
		ModelMap model) throws Exception {
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(ezUserFileVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(ezUserFileVo.getPageUnit());
		paginationInfo.setPageSize(ezUserFileVo.getPageSize());
		
		String totCnt = ezUserFileSvc.retrieveEzUserFileCount(ezUserFileVo);
		List<EzUserFileVo> ezUserFileVoList = ezUserFileSvc.retrieveListEzUserFile(ezUserFileVo);
		
		if(model != null){
			model.addAttribute("totCnt",totCnt);
			model.addAttribute("paginationInfo", paginationInfo);
			model.addAttribute("ezUserFileVo", ezUserFileVo);
			model.addAttribute("resultList", ezUserFileVoList);
		}
		
		return GlobalConst.SITE_ROOT_BOFFICE_PATH + "/cn/file/ez_user_file_list";
	}

	/**  ez_user_file 수정 */
	@SuppressWarnings("unused")
	@RequestMapping(value= "/boffice/cn/file/Ez_User_File_Mod.do")
	public String ezUserFileMod(
			HttpServletRequest request,
			@ModelAttribute("EzUserFileVo") EzUserFileVo ezUserFileVo,
			ModelMap model) throws Exception {
		
		//첨부파일 처리 시작 ---------------------------------------------------
		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
		String uploadFolder = "/upload/contents"; 
		String formFileName = "upfile";
		String upFileId = ezUserFileVo.getAtchFileId();
		
		String atchFileId = new Util().ezFileUpload(request, rootPath, ezFileSvc, uploadFolder, formFileName,upFileId);
		//첨부파일 처리 끝 ------------------------------------------------------
 
		ezUserFileSvc.modifyEzUserFile(ezUserFileVo);
 
		String SVC_MSGE = Message.getMessage("401.message"); //수정 성공
		return alert("/boffice/cn/file/Ez_User_File_List.do", SVC_MSGE, model);
	}
	/**  ez_user_file 삭제 */
	@RequestMapping(value= "/boffice/cn/file/Ez_User_File_Del.do")
	public String ezUserFileDel(
			HttpServletRequest request,
			@ModelAttribute("EzUserFileVo") EzUserFileVo ezUserFileVo,
			ModelMap model) throws Exception {
 
		ezUserFileSvc.removeEzUserFile(ezUserFileVo);
 
		String SVC_MSGE = Message.getMessage("501.message"); //삭제 성공
		return alert("/boffice/cn/file/Ez_User_File_List.do", SVC_MSGE, model);
	}
}
