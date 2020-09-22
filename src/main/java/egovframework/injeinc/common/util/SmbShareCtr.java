package egovframework.injeinc.common.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.Message;
import egovframework.injeinc.common.files.vo.CmmFilesVo;

@Controller
public class SmbShareCtr extends CmmLogCtr {
	
	@RequestMapping("/foffice/common/files/CmmFilesReg.do")
	public String CmmFilesReg(HttpServletRequest request, @ModelAttribute("CmmFilesVo") CmmFilesVo cmmFilesVo, ModelMap model) throws Exception {

		cmmFilesVo.setCfGroup("COMMON"); 											//그룹명은 프로그램에 따라 다르게 합니다.
		cmmFilesVo.setCfPkidx(""); 													//프로그램 고유키
		cmmFilesVo.setCfPath(Message.getMessage("common.file.upload.path"));		//업로드 URL 입력
		
		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		cmmFilesVo.setRegId(userid);
		
		
		String SVC_MSGE = Message.getMessage("100.message");
		return alert("/boffice/common/files/CmmFilesList.do", SVC_MSGE, model); 
	}
	

}