package egovframework.injeinc.foffice.cm.popup.print;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.cmm.CmmLogCtr;

@Controller
public class Print extends CmmLogCtr{
	
    
	@RequestMapping(value= "/site/{strDomain}/common/Print.do")
	public String gisList(
			@PathVariable("strDomain") String strDomain,
			HttpServletRequest request,
			ModelMap model ) throws Exception {
		if(model != null){
			model.addAttribute("strDomain", strDomain);
		}
		return "/injeinc/common/print";		
 	}
}