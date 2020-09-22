package egovframework.injeinc.foffice.ex.siteMap.controller;

import java.util.HashMap;
import java.util.List;

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
import egovframework.injeinc.common.util.EgovStringUtil;
import egovframework.injeinc.foffice.ex.siteMap.service.SiteMapSvc;
import egovframework.injeinc.foffice.ex.siteMap.vo.SiteMapVo;

@Controller
public class SiteMapCtr extends CmmLogCtr{
	
	@Resource(name = "SiteMapSvc")
	private SiteMapSvc siteMapSvc;
	
	@Autowired(required=true)
	private MappingJackson2JsonView jsonView;
	
	@RequestMapping(value = "/site/{strDomain}/ex/siteMap/SiteMap.do")
	public String SiteMap(ModelMap model) throws Exception {
		return "injeinc/foffice/ex/siteMap/siteMap";
	}
	
}
