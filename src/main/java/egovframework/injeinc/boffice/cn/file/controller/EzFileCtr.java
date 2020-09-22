package egovframework.injeinc.boffice.cn.file.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.cmm.GlobalConst;
import egovframework.cmm.Message;
import egovframework.injeinc.boffice.cn.file.service.EzFileSvc;
import egovframework.injeinc.boffice.cn.file.vo.EzFileVo;
import egovframework.injeinc.boffice.cn.common.Util;
import egovframework.cmm.CmmLogCtr;

//파일 첨부를 위해 추가 import 구문
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Controller
public class EzFileCtr extends CmmLogCtr {

	@Resource(name = "EzFileSvc")
	private EzFileSvc ezFileSvc;

	/**  ez_file 등록 */
	@RequestMapping(value= "/boffice/cn/file/Ez_File_Reg.do")
	public String ezFileReg(
		HttpServletRequest request,
		@ModelAttribute("EzFileVo") EzFileVo ezFileVo,
		ModelMap model) throws Exception {

		ezFileSvc.registEzFile(ezFileVo, request, "atch_file");

		String SVC_MSGE = Message.getMessage("201.message"); //등록 성공
		return alert("/boffice/cn/file/Ez_File_List.do", SVC_MSGE, model);
	}

	/**  ez_file 상세보기 */
	@RequestMapping(value= "/boffice/cn/file/Ez_File_Form.do")
	public String Ez_FileForm(
		HttpServletRequest request,
		@ModelAttribute("EzFileVo") EzFileVo ezFileVo,
		ModelMap model) throws Exception {
		EzFileVo ezFile = ezFileSvc.retrieveEzFile(ezFileVo);
		if(ezFile != null){
			model.addAttribute("EzFileVo", ezFile);
		}else{
			model.addAttribute("EzFileVo", ezFileVo);
		}

		return GlobalConst.SITE_ROOT_BOFFICE_PATH + "/cn/file/ez_file_form";
	}

	/**  ez_file 목록 */
	@RequestMapping(value= "/boffice/cn/file/Ez_File_List.do")
	public String Ez_FileList(
		HttpServletRequest request,
		@ModelAttribute("EzFileVo") EzFileVo ezFileVo,
		ModelMap model) throws Exception {
		List<EzFileVo> ezFileVoList = ezFileSvc.retrieveListEzFile(ezFileVo);
		if(model != null){
			model.addAttribute("EzFileVoList", ezFileVoList);
		}

		return GlobalConst.SITE_ROOT_BOFFICE_PATH + "/cn/file/ez_file_list";
	}

	/**  ez_file 수정 */
	@RequestMapping(value= "/boffice/cn/menu/Ez_File_Mod.do")
	public String ezFileMod(
			HttpServletRequest request,
			@ModelAttribute("EzFileVo") EzFileVo ezFileVo,
			ModelMap model) throws Exception {
 
		ezFileSvc.modifyEzFile(ezFileVo);
 
		String SVC_MSGE = Message.getMessage("401.message"); //수정 성공
		return alert("/boffice/cn/file/Ez_File_List.do", SVC_MSGE, model);
	}
	/**  ez_file 삭제 */
	@RequestMapping(value= "/boffice/cn/menu/Ez_File_Del.do")
	public String ezFileDel(
			HttpServletRequest request,
			@ModelAttribute("EzFileVo") EzFileVo ezFileVo,
			ModelMap model) throws Exception {
 
		ezFileSvc.modifyEzFile(ezFileVo);
 
		String SVC_MSGE = Message.getMessage("501.message"); //삭제 성공
		return alert("/boffice/cn/file/Ez_File_List.do", SVC_MSGE, model);
	}
}