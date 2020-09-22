package egovframework.injeinc.boffice.hot.stat.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.cmm.CmmLogCtr;
import egovframework.injeinc.boffice.hot.stat.service.HotStatSvc;
import egovframework.injeinc.boffice.hot.stat.vo.HotStatVo;
import egovframework.injeinc.common.util.EgovDateUtil;


@Controller
public class HotStatCtr extends CmmLogCtr{
	
	@Resource(name = "HotStatSvc")
	private HotStatSvc hotStatSvc;
	
	private EgovDateUtil egovDateUtil;
	
	/** 빠른서비스관리 리스트*/
	@RequestMapping(value= "/boffice/hot/stat/HotStat_List.do")
	public String hotStatList(
			HttpServletRequest request, @ModelAttribute("HotStatVo") HotStatVo hotStatVO
			, ModelMap model ) throws Exception {
		
		Calendar calendar = Calendar.getInstance();
		int year;
		int month;
		
		List retrieveListHotStat = new ArrayList();
		
		if(hotStatVO.getSchyear().equals("")){	//년도 초기값
			hotStatVO.setSchyear(""+calendar.get(Calendar.YEAR));
			year = calendar.get(calendar.YEAR);
		}else{
			year = Integer.parseInt(hotStatVO.getSchyear());
		}
		
		if(!hotStatVO.getSchmonth().equals("all")){
			if(hotStatVO.getSchmonth().equals("")){	//월 초기값
				if((calendar.get(Calendar.MONTH)+1) < 10){
					hotStatVO.setSchmonth("0"+(calendar.get(Calendar.MONTH)+1));			//1월~9월까지는 데이터가 01~09 형식으로 들어있기때문에 앞에 0을 붙임
				}else{
					hotStatVO.setSchmonth(""+(calendar.get(Calendar.MONTH)+1));
				}
				month = calendar.get(Calendar.MONTH);
			}else{
				month = Integer.parseInt(hotStatVO.getSchmonth())-1;
			}
			
			calendar.set(year, month, 1);
			if(model != null){
				model.addAttribute("maxDay",(calendar.getActualMaximum(Calendar.DAY_OF_MONTH)));
			}
			retrieveListHotStat = hotStatSvc.retrieveListHotStatDay(hotStatVO);		//일별 리스트 조회
		}else{
			retrieveListHotStat = hotStatSvc.retrieveListHotStatMonth(hotStatVO);		//월별 리스트 조회
		}
		
		int totCnt = hotStatSvc.retrievePagHotStat(hotStatVO);
		
		if(model != null){
			model.addAttribute("year", calendar.get(Calendar.YEAR));		//현재년도
			model.addAttribute("totCnt", totCnt);
			model.addAttribute("HotStatVo", hotStatVO);
			model.addAttribute("resultList", retrieveListHotStat);
		}
		
		return "injeinc/boffice/hot/stat/stat_list";		
 	}
	
	/** 엑셀 다운 */
	@RequestMapping(value= "/excel/HotStat_Excel.do")
	public String hotStatExcel(
			HttpServletRequest request, HttpServletResponse response, @ModelAttribute("HotStatVo") HotStatVo hotStatVO
			, ModelMap model ) throws Exception {
		
		Calendar calendar = Calendar.getInstance();
		int year;
		int month;
		
		List retrieveListHotStat = new ArrayList();
		
		if(hotStatVO.getSchyear().equals("")){	//년도 초기값
			hotStatVO.setSchyear(""+calendar.get(Calendar.YEAR));
			year = calendar.get(calendar.YEAR);
		}else{
			year = Integer.parseInt(hotStatVO.getSchyear());
		}
		
		if(!hotStatVO.getSchmonth().equals("all")){
			if(hotStatVO.getSchmonth().equals("")){	//월 초기값
				if((calendar.get(Calendar.MONTH)+1) < 10){
					hotStatVO.setSchmonth("0"+(calendar.get(Calendar.MONTH)+1));			//1월~9월까지는 데이터가 01~09 형식으로 들어있기때문에 앞에 0을 붙임
				}else{
					hotStatVO.setSchmonth(""+(calendar.get(Calendar.MONTH)+1));
				}
				month = calendar.get(Calendar.MONTH);
			}else{
				month = Integer.parseInt(hotStatVO.getSchmonth())-1;
			}
			
			calendar.set(year, month, 1);
			if(model != null){
				model.addAttribute("maxDay",(calendar.getActualMaximum(Calendar.DAY_OF_MONTH)));
			}
			retrieveListHotStat = hotStatSvc.retrieveListHotStatDay(hotStatVO);		//일별 리스트 조회
		}else{
			retrieveListHotStat = hotStatSvc.retrieveListHotStatMonth(hotStatVO);		//월별 리스트 조회
		}
		
		int totCnt = hotStatSvc.retrievePagHotStat(hotStatVO);
		if(model != null){
			model.addAttribute("year", calendar.get(Calendar.YEAR));		//현재년도
			model.addAttribute("totCnt", totCnt);
			model.addAttribute("HotStatVo", hotStatVO);
			model.addAttribute("resultList", retrieveListHotStat);
		}
		
		return "injeinc/boffice/hot/stat/stat_excel";		
 	}
}