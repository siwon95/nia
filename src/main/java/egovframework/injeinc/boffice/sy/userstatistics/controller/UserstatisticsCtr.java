package egovframework.injeinc.boffice.sy.userstatistics.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.cmm.CmmLogCtr;
import egovframework.injeinc.boffice.sy.userstatistics.service.UserStatisticsSvc;
import egovframework.injeinc.boffice.sy.userstatistics.vo.UserStatisticsVo;

@Controller
public class UserstatisticsCtr extends CmmLogCtr {
	@Resource(name="UserStatisticsSvc")
	private UserStatisticsSvc userStatisticsSvc; 
	
	@RequestMapping(value="/boffice/sy/userstatistics/UserStatistics_List.do")
	public String userStatisticsList(HttpServletRequest request,
			@ModelAttribute("UserStatisticsVo") UserStatisticsVo userStatisticsVo,
			ModelMap model) throws Exception {

		List<UserStatisticsVo> resultList = userStatisticsSvc.userStatisticsList(userStatisticsVo);
		if(model != null){
			model.addAttribute("resultList", resultList);
		}
		return "injeinc/boffice/sy/userstatistics/userstatistics_list";
	}
	
	@RequestMapping(value="/boffice/sy/userstatistics/UserSta_Excel.do")
	public String userStaExcel(HttpServletRequest request,
								@ModelAttribute("UserStatisticsVo") UserStatisticsVo userStatisticsVo,
								ModelMap model) throws Exception {

		List<UserStatisticsVo> resultList = userStatisticsSvc.userStatisticsList(userStatisticsVo);
		if(model != null){
			model.addAttribute("resultList", resultList);
		}
		return "injeinc/boffice/sy/userstatistics/user_sta_excel";
	}
	
}
