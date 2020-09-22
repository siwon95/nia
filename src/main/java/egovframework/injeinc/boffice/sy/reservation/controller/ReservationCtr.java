package egovframework.injeinc.boffice.sy.reservation.controller;

import java.util.ArrayList;
import java.util.HashMap;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.EgovMessageSource;
import egovframework.injeinc.boffice.sy.reservation.service.ReservationSvc;
import egovframework.injeinc.boffice.sy.reservation.vo.ReservationVo;
import egovframework.injeinc.common.code.vo.CodeVo;
import egovframework.injeinc.common.util.EgovStringUtil;


/**
 * MenuCtr
 * 2015.06.05 : LDY
 */

@Controller
public class ReservationCtr extends CmmLogCtr{
	
	@Resource(name = "ReservationSvc")
	private ReservationSvc reservationSvc;
	
	@Autowired(required=true)
	private MappingJackson2JsonView jsonView;
	
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
    @RequestMapping(value= "/boffice/sy/reservation/Reservation_List.do")
	public String DeptList(
			HttpServletRequest request, @ModelAttribute("ReservationVo") ReservationVo syMenuVo
			, ModelMap model) throws Exception {
		debugLog("dddd");
		return "injeinc/boffice/sy/reservation/reservation_list";		
	}
	
	/**
	 * tree ajax
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/boffice/sy/reservation/Reservation_ListAx.do")
	public void treeListAjax(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession ses = request.getSession();
		
		HashMap<String, String> param = new HashMap<String, String>();
		
		HashMap<String, Object> serviceMap = reservationSvc.retriveListReservation(param);
		
		jsonView.render(serviceMap, request, response);
		
	}
	
	@RequestMapping(value="/boffice/sy/reservation/Reservation_FormAx.do",method=RequestMethod.POST)
	@ResponseBody
	public void appTreeFormAx(HttpServletRequest request, HttpServletResponse response,@RequestBody String tree) throws Exception {
		infoLog("TreeFormTest");
		HttpSession ses = request.getSession();
		HashMap<String,Object> param = new HashMap<String,Object> ();
		
		JSONArray arrobj = (JSONArray)JSONValue.parse(tree);
		
		for(int i=0;i<arrobj.size();i++){
			JSONObject jsonobj = (JSONObject) arrobj.get(i);
			param.put("id_"+i, jsonobj.get("id"));
			param.put("parent_"+i, jsonobj.get("parent"));
			param.put("text_"+i, jsonobj.get("text"));
			param.put("mgrUrl_"+i, jsonobj.get("mgrUrl"));
			param.put("usrUrl_"+i, jsonobj.get("usrUrl"));
			param.put("useYn_"+i, jsonobj.get("useYn"));
			param.put("position_"+i, i);
		}
		param.put("regid", ses.getAttribute("SesUserId"));
		param.put("objsize", arrobj.size());
		
		HashMap<String, Object> serviceMap = reservationSvc.registTreeReservation(param);
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("SVC_CODE", serviceMap.get("SVC_CODE"));
		jsonMap.put("SVC_MSGE", serviceMap.get("SVC_MSGE"));
		
		jsonView.render(jsonMap, request, response);	
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/boffice/sy/reservation/Reservation_ViewAx.do")
	public void ReservationViewAx(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession ses = request.getSession();
		
		String searchCode = EgovStringUtil.nullConvert(request.getParameter("searchCode"));	
		
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("rmCd", searchCode);
		
		HashMap<String, Object> serviceMap = reservationSvc.retriveReservationAx(param);
		
		ArrayList<CodeVo> rowDataList = (ArrayList<CodeVo>) serviceMap.get("rowDataList");
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("ReservationAxValue", serviceMap.get("ReservationAxValue"));
		jsonMap.put("rowDataList", rowDataList);
		
		jsonView.render(jsonMap, request, response);
		
	}
}