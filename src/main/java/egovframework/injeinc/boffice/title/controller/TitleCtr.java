package egovframework.injeinc.boffice.title.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.cmm.CmmLogCtr;
import egovframework.injeinc.boffice.title.service.TitleSvc;
import egovframework.rte.psl.dataaccess.util.EgovMap;


/**
 * AppCtr
 * 2015.06.05 : LDY
 */

@Controller
public class TitleCtr extends CmmLogCtr{
	
	@Resource(name = "TitleSvc")
	private TitleSvc titleSvc;
	
	@RequestMapping(value= "/boffice/Title.do")
	public String DeptList(
			HttpServletRequest request, ModelMap model) throws Exception {
		
		return "injeinc/boffice/title/title";		
	}
	
}