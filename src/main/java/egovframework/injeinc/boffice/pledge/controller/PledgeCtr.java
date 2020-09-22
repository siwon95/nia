package egovframework.injeinc.boffice.pledge.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.hsqldb.lib.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.util.WebUtils;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.Message;
import egovframework.cmm.vo.AuthorityVo;
import egovframework.injeinc.boffice.cn.common.UtilSvc;
import egovframework.injeinc.boffice.ex.board.vo.BbsContentVo;
import egovframework.injeinc.boffice.ex.board.vo.ContentFileVo;
import egovframework.injeinc.boffice.excel.controller.ExcelCtr;
import egovframework.injeinc.boffice.pledge.service.PledgeFileSvc;
import egovframework.injeinc.boffice.pledge.service.PledgeSvc;
import egovframework.injeinc.boffice.pledge.vo.PledgeFileVo;
import egovframework.injeinc.boffice.pledge.vo.PledgeVo;
import egovframework.injeinc.boffice.sy.board.vo.BoardVo;
import egovframework.injeinc.boffice.sy.board.vo.CmsBbsVo;
import egovframework.injeinc.boffice.sy.code.service.CmmCodeSvc;
import egovframework.injeinc.common.util.DateUtil;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class PledgeCtr extends CmmLogCtr{
	
	@Resource(name = "PledgeSvc")
	private PledgeSvc pledgeSvc;
	
	@Resource(name = "CmmCodeSvc")
	private CmmCodeSvc cmmCodeSvc;
	
	@Resource(name = "PledgeFileSvc")
	private PledgeFileSvc pledgeFileSvc;
	
	@Resource(name = "UtilSvc")
	private UtilSvc utilSvc;
	
	@Autowired(required=true)
	private MappingJackson2JsonView jsonView;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/boffice/pledge/PledgeList.do")
	public String pledgeList(HttpServletRequest request, @ModelAttribute("PledgeVo") PledgeVo pledgeVo, ModelMap model) throws Exception {
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(pledgeVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(pledgeVo.getPageUnit());
		paginationInfo.setPageSize(pledgeVo.getPageSize());
		
		pledgeVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		pledgeVo.setLastIndex(paginationInfo.getLastRecordIndex());
		pledgeVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List resultList = pledgeSvc.retrievePagePledge(pledgeVo);
		int totCnt = pledgeSvc.retrieveTotalCntPledge(pledgeVo);
		
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", resultList);
		model.addAttribute("resultCnt", totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		model.addAttribute("plCateList", cmmCodeSvc.retrieveListCmmCode("PLCATE"));
		model.addAttribute("plWiwidList1", pledgeSvc.retrieveListWiwid(1));
		if(pledgeVo.getSearchPlWiwid1()!=0) {
			model.addAttribute("plWiwidList2", pledgeSvc.retrieveListWiwid(pledgeVo.getSearchPlWiwid1()));
		}
		
		return "injeinc/boffice/pledge/pledge_list";
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/boffice{strNodeco}/pledge/PledgeForm.do")
	public String PledgeForm(@PathVariable("strNodeco") String strNodeco, HttpServletRequest request, @ModelAttribute("PledgeVo") PledgeVo pledgeVo,@ModelAttribute("PledgeFileVo") PledgeFileVo pledgeFileVo, @RequestParam Map requestMap, ModelMap model) throws Exception {
		if (pledgeVo.getActionKey().equals("modify")) {
			PledgeVo result = pledgeSvc.retrievePledge(pledgeVo);
			if(result == null) {
				String SVC_MSGE = "잘못된 접근입니다.";
				return alert(request.getContextPath() + "/boffice" + strNodeco + "/pledge/PledgeList.do", SVC_MSGE, model);
			}
			result.setActionKey("modify");
			model.addAttribute("PledgeVo", result);
			if(result.getPlWiwid1()!=0) {
				model.addAttribute("plWiwidList2", pledgeSvc.retrieveListWiwid(result.getPlWiwid1()));
			}
			List fileList = pledgeFileSvc.retrieveListPledgeFile(pledgeFileVo);
			model.addAttribute("fileList", fileList);
		} else {
			pledgeVo.setActionKey("regist");
			model.addAttribute("PledgeVo", pledgeVo);
		}

		model.addAttribute("emailList", cmmCodeSvc.retrieveListCmmCode("EMAIL_CD"));
		model.addAttribute("plCateList", cmmCodeSvc.retrieveListCmmCode("PLCATE"));
		model.addAttribute("plWiwidList1", pledgeSvc.retrieveListWiwid(1));
		
		return "injeinc/boffice/pledge/pledge_form";
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/boffice{strNodeco}/pledge/PledgeView.do")
	public String PledgeView(@PathVariable("strNodeco") String strNodeco, HttpServletRequest request, @ModelAttribute("PledgeVo") PledgeVo pledgeVo,@ModelAttribute("PledgeFileVo") PledgeFileVo pledgeFileVo, @RequestParam Map requestMap, ModelMap model) throws Exception {		
		PledgeVo result = pledgeSvc.retrievePledge(pledgeVo);
		String SVC_MSGE;
		if (result != null) {
			model.addAttribute("PledgeVo", result);
		} else {
			SVC_MSGE = "잘못된 접근입니다.";
			return alert(request.getContextPath() + "/boffice" + strNodeco + "/pledge/PledgeList.do", SVC_MSGE, model);
		}
		List fileList = pledgeFileSvc.retrieveListPledgeFile(pledgeFileVo);
		model.addAttribute("fileList", fileList);
		
		return "injeinc/boffice/pledge/pledge_view";
	}
	
	@RequestMapping("/boffice{strNodeco}/pledge/PledgeReg.do")
	public String PledgeReg(@PathVariable("strNodeco") String strNodeco, HttpServletRequest request, @ModelAttribute("PledgeVo") PledgeVo pledgeVo, ModelMap model) throws Exception {
		model.addAttribute("strNodeco", strNodeco);
		// 파라미터 값
		StringBuffer addParam = new StringBuffer();
		addParam.append("?pageIndex=").append(pledgeVo.getPageIndex());
		addParam.append("&pageUnit=").append(pledgeVo.getPageUnit());
		addParam.append("&searchPlWiwid1=").append(pledgeVo.getSearchPlWiwid1());
		addParam.append("&searchPlWiwid2=").append(pledgeVo.getSearchPlWiwid2());
		addParam.append("&searchCateCont=").append(pledgeVo.getSearchCateCont());
		addParam.append("&searchCondition=").append(pledgeVo.getSearchCondition());
		addParam.append("&searchKeyword=").append(pledgeVo.getSearchKeyword());
		addParam.append("&searchStartDate=").append(pledgeVo.getSearchStartDate());
		addParam.append("&searchEndDate=").append(pledgeVo.getSearchEndDate());

		String SVC_MSGE;
		
		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		pledgeVo.setRegId(userid);
		pledgeVo.setRegIp(request.getRemoteAddr());
		
		request.setAttribute("userid", userid);
		request.setAttribute("fileMaxSize", pledgeVo.getFileMaxSize());

		try {
			pledgeSvc.registpledge(request, pledgeVo);
		} catch (FileUploadException e) {
			e.printStackTrace();
			SVC_MSGE = Message.getMessage("905.message");
			return alert("/boffice" + strNodeco + "/pledge/PledgeList.do" + addParam.toString(), SVC_MSGE, model);
		}

		utilSvc.cacheSelectClear("mainCache");
		
		SVC_MSGE = Message.getMessage("100.message");
		return alert("/boffice/pledge/PledgeList.do" + addParam.toString(), SVC_MSGE, model);
	}
	
	@RequestMapping("/boffice{strNodeco}/pledge/PledgeMod.do")
	public String PledgeMod(@PathVariable("strNodeco") String strNodeco, HttpServletRequest request,
			@ModelAttribute("PledgeVo") PledgeVo pledgeVo, ModelMap model) throws Exception {
		model.addAttribute("strNodeco", strNodeco);
		// 파라미터 값
		StringBuffer addParam = new StringBuffer();
		addParam.append("?pageIndex=").append(pledgeVo.getPageIndex());
		addParam.append("&pageUnit=").append(pledgeVo.getPageUnit());
		addParam.append("&searchPlWiwid1=").append(pledgeVo.getSearchPlWiwid1());
		addParam.append("&searchPlWiwid2=").append(pledgeVo.getSearchPlWiwid2());
		addParam.append("&searchCateCont=").append(pledgeVo.getSearchCateCont());
		addParam.append("&searchCondition=").append(pledgeVo.getSearchCondition());
		addParam.append("&searchKeyword=").append(pledgeVo.getSearchKeyword());
		addParam.append("&searchStartDate=").append(pledgeVo.getSearchStartDate());
		addParam.append("&searchEndDate=").append(pledgeVo.getSearchEndDate());

		String SVC_MSGE;
		
		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		String username = (String) WebUtils.getSessionAttribute(request, "SesUserName");
		pledgeVo.setModId(userid);
		pledgeVo.setModName(username);
		pledgeVo.setModIp(request.getRemoteAddr());
		
		request.setAttribute("userid", userid);
		request.setAttribute("fileMaxSize", pledgeVo.getFileMaxSize());
		try {
			pledgeSvc.modifyPledge(request, pledgeVo);
		} catch (FileUploadException e) {
			e.printStackTrace();
			SVC_MSGE = Message.getMessage("905.message");
			return alert("/boffice" + strNodeco + "/pledge/PledgeList.do" + addParam.toString(), SVC_MSGE, model);
		}

		utilSvc.cacheSelectClear("mainCache");
		
		SVC_MSGE = Message.getMessage("401.message");
		
		return alert("/boffice" + strNodeco + "/pledge/PledgeList.do" + addParam.toString(), SVC_MSGE, model);
	}
	
	@RequestMapping("/boffice{strNodeco}/pledge/PledgeRmv.do")
	public String PledgeRmv(@PathVariable("strNodeco") String strNodeco, HttpServletRequest request,
			@ModelAttribute("PledgeVo") PledgeVo pledgeVo, ModelMap model) throws Exception {
		model.addAttribute("strNodeco", strNodeco);
		// 파라미터 값
		StringBuffer addParam = new StringBuffer();
		addParam.append("?pageIndex=").append(pledgeVo.getPageIndex());
		addParam.append("&pageUnit=").append(pledgeVo.getPageUnit());
		addParam.append("&searchPlWiwid1=").append(pledgeVo.getSearchPlWiwid1());
		addParam.append("&searchPlWiwid2=").append(pledgeVo.getSearchPlWiwid2());
		addParam.append("&searchCateCont=").append(pledgeVo.getSearchCateCont());
		addParam.append("&searchCondition=").append(pledgeVo.getSearchCondition());
		addParam.append("&searchKeyword=").append(pledgeVo.getSearchKeyword());
		addParam.append("&searchStartDate=").append(pledgeVo.getSearchStartDate());
		addParam.append("&searchEndDate=").append(pledgeVo.getSearchEndDate());
		
		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		pledgeVo.setModId(userid);
		pledgeVo.setModIp(request.getRemoteAddr());
		
		pledgeSvc.removePledge(pledgeVo);
		
		utilSvc.cacheSelectClear("mainCache");

		String SVC_MSGE = Message.getMessage("501.message");
		
		return alert("/boffice" + strNodeco + "/pledge/PledgeList.do" + addParam.toString(), SVC_MSGE, model);
	}
	
	@RequestMapping("/boffice/pledge/PledgeExcel.do")
	public void BbsContentListExcel(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("PledgeVo") PledgeVo pledgeVo, ModelMap model) throws Exception {
		
		List resultList = pledgeSvc.retrieveListPledge(pledgeVo);
		
		String templateFile = "pledgeListExcel.xls";
    	String filename = "공약은행_" + getTimeStamp();
    	
    	Map<String, Object> beans = new HashMap<String, Object>();
        // 데이터를 담는다.
    	beans.put("resultList", resultList);        
        
    	ExcelCtr.writeToExcel(templateFile, filename, beans, request, response);  // jxls 적용

	}
	
	@RequestMapping("/boffice{strNodeco}/pledge/WiwIdListAx.do")
	public void WiwIdListAx(@PathVariable("strNodeco") String strNodeco, HttpServletRequest request, HttpServletResponse response, @ModelAttribute("PledgeVo") PledgeVo pledgeVo, ModelMap model) throws Exception {
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		if(pledgeVo.getSearchPlWiwid1()!=0) {
			jsonMap.put("plWiwidList2", pledgeSvc.retrieveListWiwid(pledgeVo.getSearchPlWiwid1()));
		}
		jsonMap.put("result", true);
		
		jsonView.render(jsonMap, request, response);
	}
}