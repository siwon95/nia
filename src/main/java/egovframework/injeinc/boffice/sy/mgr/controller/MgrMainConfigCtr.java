package egovframework.injeinc.boffice.sy.mgr.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.Message;
import egovframework.injeinc.boffice.sy.mgr.service.MgrMainConfigSvc;
import egovframework.injeinc.boffice.sy.mgr.vo.MgrMainConfigVo;
import egovframework.injeinc.common.util.EgovStringUtil;

@Controller
public class MgrMainConfigCtr extends CmmLogCtr {
	
	@Resource(name = "MgrMainConfigSvc")
	private MgrMainConfigSvc mgrMainConfigSvc;
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/boffice/sy/mgr/MgrMainConfig.do")
	public String mgrMainConfig(HttpServletRequest request, @ModelAttribute("MgrMainConfigVo") MgrMainConfigVo mgrMainConfigVo
				  , ModelMap model) throws Exception {
			
		if(model == null){
			return "injeinc/common/code500";
		}
		
		HttpSession ses = request.getSession();
			
		mgrMainConfigVo.setMgrId(EgovStringUtil.isNullToString(ses.getAttribute("SesUserId")));
		mgrMainConfigVo = mgrMainConfigSvc.retrieveMgrMainConfig(mgrMainConfigVo);
		if(mgrMainConfigVo==null){
			mgrMainConfigVo = new MgrMainConfigVo();
		}
		model.addAttribute("MgrMainConfigVo", mgrMainConfigVo);
		
		
		return "injeinc/boffice/sy/mgr/mgr_main_config";
	}
	
	@RequestMapping("/boffice/sy/mgr/MgrMainConfigMod.do")
	public String mgrMainConfigMod(HttpServletRequest request, @ModelAttribute("MgrMainConfigVo") MgrMainConfigVo mgrMainConfigVo
				  , ModelMap model) throws Exception {
			
		if(model == null){
			return "injeinc/common/code500";
		}
		
		HttpSession ses = request.getSession();
			
		mgrMainConfigVo.setMgrId(EgovStringUtil.isNullToString(ses.getAttribute("SesUserId")));
		
		MgrMainConfigVo mgrMainConfigVo2 = mgrMainConfigSvc.retrieveMgrMainConfig(mgrMainConfigVo);
		
		if(mgrMainConfigVo2==null){
			mgrMainConfigSvc.registMgrMainConfig(mgrMainConfigVo);
		}
		mgrMainConfigSvc.modifyMgrMainConfig(mgrMainConfigVo);
		
		String SVC_MSGE = Message.getMessage("100.message");
		
		return alert("/boffice/sy/mgr/MgrMainConfig.do", SVC_MSGE, model);
	}
}