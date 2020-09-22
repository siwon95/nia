package egovframework.injeinc.common.files.util;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import egovframework.injeinc.common.files.service.CmmFilesSvc;
import egovframework.injeinc.common.files.vo.CmmFilesVo;
import egovframework.injeinc.common.files.vo.enums.CfType;
import egovframework.injeinc.common.util.EgovStringUtil;

@Controller
public class CmmFilesUtil {
	
	@SuppressWarnings("rawtypes")
	public static List getCmmFilesList(String cfGroup, String cfPkidx) throws Exception {
		
		if(EgovStringUtil.isEmpty(cfGroup) || EgovStringUtil.isEmpty(cfPkidx)) {
			return null;
		}
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		ServletContext conext = session.getServletContext();
		WebApplicationContext wContext = WebApplicationContextUtils.getWebApplicationContext(conext);
		CmmFilesSvc cmmFilesSvc = (CmmFilesSvc)wContext.getBean("CmmFilesSvc");
		
		List list = null;
	
		CmmFilesVo cmmFilesVo = new CmmFilesVo();
		cmmFilesVo.setCfGroup(cfGroup);
		cmmFilesVo.setCfPkidx(cfPkidx);
		list = cmmFilesSvc.retrieveListCmmFiles(cmmFilesVo);
		
		return list;
		
	}
	
	public static CmmFilesVo getCmmFiles(String cfGroup, String cfPkidx) throws Exception {
		
		if(EgovStringUtil.isEmpty(cfGroup) || EgovStringUtil.isEmpty(cfPkidx)) {
			return null;
		}
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		ServletContext conext = session.getServletContext();
		WebApplicationContext wContext = WebApplicationContextUtils.getWebApplicationContext(conext);
		CmmFilesSvc cmmFilesSvc = (CmmFilesSvc)wContext.getBean("CmmFilesSvc");
		
		CmmFilesVo resultVo = null;
		
		CmmFilesVo cmmFilesVo = new CmmFilesVo();
		cmmFilesVo.setCfGroup(cfGroup);
		cmmFilesVo.setCfPkidx(cfPkidx);
		resultVo = cmmFilesSvc.retrieveListCmmFilesOne(cmmFilesVo);
		
		return resultVo;
		
	}
}