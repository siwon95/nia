package egovframework.injeinc.boffice.sy.board.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.cmm.Message;
import egovframework.cmm.CmmLogCtr;
import egovframework.injeinc.boffice.sy.board.service.EzBbsTempletSvc;
import egovframework.injeinc.boffice.sy.board.vo.EzBbsTempletVo;
import egovframework.injeinc.boffice.sy.code.service.CmmCodeSvc;

@Controller
public class EzBbsTempletCtr extends CmmLogCtr{

	@Resource(name = "EzBbsTempletSvc")
	private EzBbsTempletSvc ezBbsTempletSvc;
	
	@Resource(name = "CmmCodeSvc")
	private CmmCodeSvc cmmCodeSvc;

	@RequestMapping(value="/boffice/sy/board/ezBbsTemplet_list.do")
	public String ezBbsTempletList(HttpServletRequest request,
			@ModelAttribute("EzBbsTempletVo") EzBbsTempletVo ezBbsTempletVo,
			ModelMap model) throws Exception {

		List<EzBbsTempletVo> selectPageEzBbsTemplet = ezBbsTempletSvc.selectPageEzBbsTemplet(ezBbsTempletVo);
		model.addAttribute("resultList", selectPageEzBbsTemplet);
		model.addAttribute("bsrcList", cmmCodeSvc.retrieveListCmmCode("bsrc_list"));
		model.addAttribute("bsrcView", cmmCodeSvc.retrieveListCmmCode("bsrc_view"));
		model.addAttribute("bsrcRegist", cmmCodeSvc.retrieveListCmmCode("bsrc_regist"));

		return "injeinc/boffice/sy/board/ezBbsTemplet_list";
	}

	@RequestMapping(value="/boffice/sy/board/ezBbsTemplet_view.do")
	public String ezBbsTempletView(HttpServletRequest request,
			@ModelAttribute("EzBbsTempletVo") EzBbsTempletVo ezBbsTempletVo,
			ModelMap model) throws Exception {

		EzBbsTempletVo view = ezBbsTempletSvc.selectEzBbsTemplet(ezBbsTempletVo);
		model.addAttribute("EzBbsTempletVo",view);
		return "injeinc/boffice/sy/board/ezBbsTemplet_view";
	}

	@RequestMapping(value="/boffice/sy/board/ezBbsTemplet_mod.do")
	public String ezBbsTempletModify(HttpServletRequest request,
			@ModelAttribute("EzBbsTempletVo") EzBbsTempletVo ezBbsTempletVo,
			ModelMap model) throws Exception {

		EzBbsTempletVo view = ezBbsTempletSvc.selectEzBbsTemplet(ezBbsTempletVo);
		model.addAttribute("EzBbsTempletVo",view);
		return "injeinc/boffice/sy/board/ezBbsTemplet_form";
	}

	@RequestMapping(value="/boffice/sy/board/ezBbsTemplet_form.do")
	public String ezBbsTempletForm(HttpServletRequest request,
			@ModelAttribute("EzBbsTempletVo") EzBbsTempletVo ezBbsTempletVo,
			ModelMap model) throws Exception {

		model.addAttribute("EzBbsTempletVo",ezBbsTempletVo);
		return "injeinc/boffice/sy/board/ezBbsTemplet_form";
	}

	@RequestMapping(value="/boffice/sy/board/ezBbsTemplet_action.do")
	public String ezBbsTempletAction(HttpServletRequest request,
			@ModelAttribute("EzBbsTempletVo") EzBbsTempletVo ezBbsTempletVo,
			ModelMap model) throws Exception {

		String mode = request.getParameter("mode")!=null?request.getParameter("mode").replaceAll("null",""):"";
		String SVC_MSGE = "";
		
		int tempCount = ezBbsTempletSvc.selectTotalCountEzBbsTemplet(ezBbsTempletVo);
		
		if(tempCount==0){
			ezBbsTempletSvc.insertEzBbsTemplet(ezBbsTempletVo);
			SVC_MSGE = Message.getMessage("201.message");
			return alert("/boffice/sy/board/ezBbsTemplet_list.do", SVC_MSGE, model);
			
		}else{
			ezBbsTempletSvc.modifyEzBbsTemplet(ezBbsTempletVo);
			SVC_MSGE = Message.getMessage("401.message"); 
			return alert("/boffice/sy/board/ezBbsTemplet_list.do", SVC_MSGE, model);
		}
	}
}
