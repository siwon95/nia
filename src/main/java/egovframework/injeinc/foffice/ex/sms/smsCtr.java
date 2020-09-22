package egovframework.injeinc.foffice.ex.sms;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.cmm.CmmLogCtr;

@Controller
public class smsCtr extends CmmLogCtr {
	
	@RequestMapping("/site/{strDomain}/foffice/ex/sms/sms.do")
	public String sms(HttpServletRequest request, @PathVariable("strDomain") String strDomain, ModelMap model) throws Exception {
		
		HttpSession ses = request.getSession();
		
		String user_id = (String)ses.getAttribute("ss_id");
		String hp_no = (String)ses.getAttribute("ss_hp");
		
		model.addAttribute("user_id", user_id);
		model.addAttribute("hp_no", hp_no);
		return "injeinc/foffice/ex/sms/sms";
	}

}
