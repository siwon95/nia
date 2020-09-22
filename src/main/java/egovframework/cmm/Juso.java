package egovframework.cmm;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.RequestMapping;


/**
 * <PRE>
 * 1. FileName : Message.java
 * 2. Package  : kr.co.onefirst.common.message
 * 3. Comment  : 
 * 4. Programmer : khw
 * 5. Date   : 2014. 6. 5.
 * </PRE>
 */
@Controller
public class Juso extends CmmLogCtr {

	/** 관리자 등록 뷰 */
	@RequestMapping(value = "/cmm/Juso_Set.do")
	public String JusoSetting(HttpServletRequest request, ModelMap model) throws Exception {
		return "injeinc/boffice/sy/user/juso_pop";
	}

}