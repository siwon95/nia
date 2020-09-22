package egovframework.injeinc.foffice.ex.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.GlobalConst;


@Controller
public class ExCtr extends CmmLogCtr {
	//리스트
	@RequestMapping(value= "/site/{strDomain}/design/ex/{strBusiess}/{strFile}.do")
	public String menuList(
			HttpServletRequest request,
			@PathVariable("strDomain") String strDomain,
			@PathVariable("strBusiess") String strBusiess,
			@PathVariable("strFile") String strFile,
			ModelMap model) throws Exception {

		
	
		return GlobalConst.SITE_ROOT_FOFFICE_PATH + "/ex/"+strBusiess+"/"+strFile;
	}
	
	@RequestMapping(value= "/site/{strDomain}/nolayout/ex/{strBusiess}/{strFile}.do")
	public String menuListNolayout(
			HttpServletRequest request,
			@PathVariable("strDomain") String strDomain,
			@PathVariable("strBusiess") String strBusiess,
			@PathVariable("strFile") String strFile,
			ModelMap model) throws Exception {
		return GlobalConst.SITE_ROOT_FOFFICE_PATH + "/ex/"+strBusiess+"/"+strFile;
	}

}


