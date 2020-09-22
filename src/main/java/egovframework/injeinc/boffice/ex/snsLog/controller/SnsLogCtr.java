package egovframework.injeinc.boffice.ex.snsLog.controller;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import egovframework.cmm.CmmLogCtr;
import egovframework.injeinc.boffice.cn.site.service.SiteSvc;
import egovframework.injeinc.boffice.cn.site.vo.SiteVo;
import egovframework.injeinc.boffice.ex.snsLog.vo.SnsLogVo;
import egovframework.injeinc.boffice.ex.snsLog.service.SnsLogSvc;
import egovframework.injeinc.common.util.DateUtil;

@Controller
public class SnsLogCtr extends CmmLogCtr {
	
	@Resource(name = "SnsLogSvc")
	private SnsLogSvc snsLogSvc;
	
	@Resource(name = "SiteSvc")
	private SiteSvc siteSvc;
	
	@Autowired(required=true)
	private MappingJackson2JsonView jsonView;
	
	@RequestMapping("/common/ex/snsLog/SnsLogReg.do")
	public void SnsLogReg(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("SnsLogVo") SnsLogVo snsLogVo, ModelMap model) throws Exception {

			boolean result = false;
			String message = "";
			
			String slSiteCd = snsLogVo.getSlSiteCd();
			String slSortOrder = snsLogVo.getSlSortOrder();
			String slType = snsLogVo.getSlType();
			
			if(!slSiteCd.equals("") && !slSortOrder.equals("") && !slType.equals("")) {
				snsLogSvc.registSnsLog(snsLogVo);

				message = "저장성공";
				result = true;
			}else{
				message = "필요한 자료가 없습니다.";
			}
	
			HashMap<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("result", result);
			jsonMap.put("message", message);
			jsonView.render(jsonMap, request, response);
			
	}
	
	@RequestMapping("/boffice/ex/snsLog/SnsLogList.do")
	public String SnsLogList(HttpServletRequest request, @ModelAttribute("SnsLogVo") SnsLogVo snsLogVo, ModelMap model) throws Exception {
		
		String searchSiteCd = snsLogVo.getSearchSiteCd();
		if(searchSiteCd.equals("")) {
			snsLogVo.setSearchSiteCd("injeinc");
			String nowDate = DateUtil.getCurrentDate();
			String ago7Date = DateUtil.add(nowDate, -7);
			snsLogVo.setSearchStartDt(ago7Date);
			snsLogVo.setSearchEndDt(nowDate);
		}

		model.addAttribute("resultList", snsLogSvc.retrieveListSnsLog(snsLogVo));
		
		SiteVo siteVo = new SiteVo();
		model.addAttribute("siteList", siteSvc.selectListSiteAll(siteVo));
		
		return "injeinc/boffice/ex/snsLog/sns_log_list";
	}
}