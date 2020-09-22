package egovframework.injeinc.foffice.ex.reservationMain.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import egovframework.cmm.CmmLogCtr;
import egovframework.injeinc.common.util.EgovStringUtil;
import egovframework.injeinc.foffice.ex.reservationMain.service.ReservationMainUserSvc;

@Controller
public class ReservationMainUserCtr extends CmmLogCtr{
	
	@Resource(name = "ReservationMainUserSvc")
	private ReservationMainUserSvc reservationMainUserSvc;
	
	@Autowired(required=true)
	private MappingJackson2JsonView jsonView;
	
	//통합예약 메인
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/site/{strDomain}/ex/reservationMain/Sub_Main.do")
	public String reservationMain(HttpServletRequest request
								, ModelMap model
								) throws Exception {
		if(model == null){
			return "injeinc/common/code500";
		}
		List imgList = reservationMainUserSvc.retrieveListReservationMainImg();
		
		String sort = EgovStringUtil.nullConvert(request.getParameter("sort"));
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("sort", sort);
		//통합예약 리스트
		List resultList = reservationMainUserSvc.retrieveListReservationMainNew(map);
		
		model.addAttribute("imgList", imgList);
		model.addAttribute("resultList", resultList);
		model.addAttribute("sort", sort);

		return "injeinc/foffice/ex/reservationMain/res_main";
	}
	
	//동별 홈페이지 강좌/행사안내
	@RequestMapping(value = "/site/{strDomain}/ex/reservationMain/ResList_Ax.do")
	public void resListAx(HttpServletRequest request
								, HttpServletResponse response
								) throws Exception {
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			String org = request.getParameter("org");
			String dept = request.getParameter("dept");
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("org", org);
			map.put("dept", dept);
			List resList = reservationMainUserSvc.retrieveListDongMain(map);
			jsonMap.put("resList", resList);
			jsonView.render(jsonMap, request, response);
		} catch (Exception e) {
			errorLog("error:"+e);
		}

	}
	

}
