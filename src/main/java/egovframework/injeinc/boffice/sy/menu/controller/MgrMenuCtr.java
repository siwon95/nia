package egovframework.injeinc.boffice.sy.menu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.util.WebUtils;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.Message;
import egovframework.injeinc.boffice.login.service.LoginSvc;
import egovframework.injeinc.boffice.login.vo.LoginVo;
import egovframework.injeinc.boffice.sy.menu.service.MgrMenuSvc;
import egovframework.injeinc.boffice.sy.menu.vo.MgrMenuVo;
import egovframework.injeinc.boffice.sy.menu.vo.TreeMenuVo;
import egovframework.injeinc.common.util.EgovStringUtil;

@Controller
public class MgrMenuCtr extends CmmLogCtr {
	
	@Resource(name = "MgrMenuSvc")
	private MgrMenuSvc mgrMenuSvc;
	
	@Autowired(required=true)
	private MappingJackson2JsonView jsonView;

	@Autowired
	private LoginSvc loginSvc;
	
	@RequestMapping("/boffice/sy/menu/MgrMenuList.do")
	public String MgrMenuList(HttpServletRequest request, @ModelAttribute("MgrMenuVo") MgrMenuVo mgrMenuVo, ModelMap model) throws Exception {
		return "injeinc/boffice/sy/menu/menu_list";
	}
	
	@RequestMapping("/boffice_noDeco/sy/menu/MgrMenuHelp.do")
	public String MgrMenuHelp(HttpServletRequest request, @ModelAttribute("MgrMenuVo") MgrMenuVo mgrMenuVo, ModelMap model) throws Exception {
		
		
		mgrMenuVo = mgrMenuSvc.retrieveMgrMenu(mgrMenuVo);
		model.addAttribute("MgrMenuVo", mgrMenuVo);
		
		return "injeinc/boffice/sy/menu/menu_help";
	}
	
	@RequestMapping("/boffice_noDeco/sy/menu/MgrMenuHelpView.do")
	public String MgrMenuHelpView(HttpServletRequest request, @ModelAttribute("MgrMenuVo") MgrMenuVo mgrMenuVo, ModelMap model) throws Exception {
		
		
		mgrMenuVo = mgrMenuSvc.retrieveMgrMenu(mgrMenuVo);
		model.addAttribute("MgrMenuVo", mgrMenuVo);
		
		return "injeinc/boffice/sy/menu/menu_help_view";
	}
	
	
	@RequestMapping("/boffice_noDeco/sy/menu/MgrMenuHelpMod.do")
	public String MgrMenuHelpMod(HttpServletRequest request, @ModelAttribute("MgrMenuVo") MgrMenuVo mgrMenuVo, ModelMap model) throws Exception {
		
		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		mgrMenuSvc.updateMgrMenuHelp(userid, mgrMenuVo);

		String SVC_MSGE = Message.getMessage("100.message"); //인서트성공
		return alert("/boffice_noDeco/sy/menu/MgrMenuHelp.do?mmCd="+mgrMenuVo.getMmCd(), SVC_MSGE, model);
	}
	 
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/boffice/sy/menu/MgrMenuListAx.do")
	public void MgrMenuListAx(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		ArrayList<TreeMenuVo> menuList = (ArrayList<TreeMenuVo>) mgrMenuSvc.retrieveListMgrMenu();
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("menuList", menuList);
		jsonView.render(jsonMap, request, response);
			
	}
	
	@RequestMapping("/boffice/sy/menu/MgrMenuRegAx.do")
	public void MgrMenuRegAx(HttpServletRequest request, HttpServletResponse response, @RequestBody String tree) throws Exception {
		
		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		
		JSONArray jsonArray = (JSONArray)JSONValue.parse(tree);
		
		List<MgrMenuVo> list = new ArrayList<MgrMenuVo>();
		
		for(int i = 0; i<jsonArray.size(); i++) {
			
			MgrMenuVo mgrMenuVo = new MgrMenuVo();
			mgrMenuVo.setRegId(userid);
			mgrMenuVo.setModId(userid);
			
			JSONObject jsonObject = (JSONObject) jsonArray.get(i);
			mgrMenuVo.setMmCd(EgovStringUtil.isNullToString(jsonObject.get("id")));
			mgrMenuVo.setMmUprCd(EgovStringUtil.isNullToString(jsonObject.get("parent")));
			mgrMenuVo.setMmName(EgovStringUtil.isNullToString(jsonObject.get("text")));
			mgrMenuVo.setMmImgCd(EgovStringUtil.isNullToString(jsonObject.get("mmImg")));
			mgrMenuVo.setOrdNo(i);
			mgrMenuVo.setMmIdx(EgovStringUtil.zeroConvert(jsonObject.get("mmIdx")));
			mgrMenuVo.setMgrUrl(EgovStringUtil.isNullToString(jsonObject.get("mgrUrl")));
			mgrMenuVo.setPublicYn(EgovStringUtil.isNullToString(jsonObject.get("publicYn")));
			mgrMenuVo.setMmDepth(EgovStringUtil.isNullToString(jsonObject.get("mmDepth")));
			mgrMenuVo.setMgrSiteCd(EgovStringUtil.isNullToString(jsonObject.get("mmMgrSiteCd")));
			mgrMenuVo.setMmHelp(EgovStringUtil.isNullToString(jsonObject.get("mmHelp")));
			list.add(mgrMenuVo);
		}
		
		mgrMenuSvc.registMgrMenu(userid, list);

		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", "true");
		jsonMap.put("message", Message.getMessage("100.message"));
		jsonView.render(jsonMap, request, response);
		
		HttpSession session = request.getSession();
		
		LoginVo loginVo = new LoginVo();
		loginVo.setPermCd((String)session.getAttribute("SesUserPermCd"));
		loginVo.setRoleIdx((String)session.getAttribute("SesUserRoleIdx"));
		
		ArrayList<Object> menuList = (ArrayList<Object>)mgrMenuSvc.retrieveListMgrMenuTop(loginVo);
		session.setAttribute("SesTopMenuList", menuList);
	
	}
	
	@RequestMapping("/boffice/sy/menu/MgrMenuCntAx.do")
	public void MgrMenuCntAx(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("MgrMenuVo") MgrMenuVo mgrMenuVo) throws Exception {

		String mmCd = mgrMenuVo.getMmCd();
		
		boolean result = false;
		String message = "";
		int resultCnt = 0;
		
		if(!mmCd.equals("")) {
			
			resultCnt = mgrMenuSvc.retrieveMgrMenuCnt(mgrMenuVo);
			result = true;
			
		}else{
			message = "필요한 자료가 없습니다.";
		}

		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", result);
		jsonMap.put("message", message);
		jsonMap.put("resultCnt", resultCnt);
		jsonView.render(jsonMap, request, response);
		
	}
}