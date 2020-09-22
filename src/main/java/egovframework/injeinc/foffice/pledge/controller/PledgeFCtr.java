package egovframework.injeinc.foffice.pledge.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.Message;
import egovframework.injeinc.boffice.cn.common.UtilSvc;
import egovframework.injeinc.boffice.pledge.service.PledgeFileSvc;
import egovframework.injeinc.boffice.pledge.vo.PledgeFileVo;
import egovframework.injeinc.foffice.pledge.service.PledgeFSvc;
import egovframework.injeinc.foffice.pledge.vo.PledgeFVo;
import egovframework.injeinc.boffice.sy.code.service.CmmCodeSvc;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class PledgeFCtr extends CmmLogCtr{
	
	@Resource(name = "PledgeFSvc")
	private PledgeFSvc pledgeFSvc;
	
	@Resource(name = "CmmCodeSvc")
	private CmmCodeSvc cmmCodeSvc;
	
	@Resource(name = "PledgeFileSvc")
	private PledgeFileSvc pledgeFileSvc;
	
	@Resource(name = "UtilSvc")
	private UtilSvc utilSvc;
	
	@Autowired(required=true)
	private MappingJackson2JsonView jsonView;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/site/{strDomain}/pledge/List.do")
	public String pledgeList(@PathVariable("strDomain") String strDomain, @ModelAttribute("PledgeFVo") PledgeFVo pledgeFVo, HttpServletRequest request, ModelMap model) throws Exception {
		
		Map authorMap;
		authorMap = PledgeAuthorChk(pledgeFVo ,request,"read");
		if(!Boolean.valueOf((Boolean)authorMap.get("flag"))) {
			return alert((String)authorMap.get("returnUrl"), (String)authorMap.get("msg"), model);
		}
		
		authorMap = PledgeAuthorChk(pledgeFVo ,request,"write");
		if(Boolean.valueOf((Boolean)authorMap.get("flag"))) {
			pledgeFVo.setWriteAuthor("Y");
		}
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(pledgeFVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(pledgeFVo.getPageUnit());
		paginationInfo.setPageSize(pledgeFVo.getPageSize());
		
		pledgeFVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		pledgeFVo.setLastIndex(paginationInfo.getLastRecordIndex());
		pledgeFVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List resultList = pledgeFSvc.retrievePagePledge(pledgeFVo);
		int totCnt = pledgeFSvc.retrieveTotalCntPledge(pledgeFVo);
		
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", resultList);
		model.addAttribute("resultCnt", totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		model.addAttribute("plCateList", cmmCodeSvc.retrieveListCmmCode("PLCATE"));
		model.addAttribute("plWiwidList1", pledgeFSvc.retrieveListWiwid(1));
		if(pledgeFVo.getSearchPlWiwid1()!=0) {
			model.addAttribute("plWiwidList2", pledgeFSvc.retrieveListWiwid(pledgeFVo.getSearchPlWiwid1()));
		}
		
		return "injeinc/foffice/pledge/pledge_list";
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/site/{strDomain}/pledge/Form.do")
	public String PledgeForm(@PathVariable("strDomain") String strDomain, HttpServletRequest request, @ModelAttribute("PledgeFVo") PledgeFVo pledgeFVo,@ModelAttribute("PledgeFileVo") PledgeFileVo pledgeFileVo, @RequestParam Map requestMap, ModelMap model) throws Exception {
		// 파라미터 값
		StringBuffer addParam = new StringBuffer();
		addParam.append("?pageIndex=").append(pledgeFVo.getPageIndex());
		addParam.append("&pageUnit=").append(pledgeFVo.getPageUnit());
		addParam.append("&searchPlWiwid1=").append(pledgeFVo.getSearchPlWiwid1());
		addParam.append("&searchPlWiwid2=").append(pledgeFVo.getSearchPlWiwid2());
		addParam.append("&searchCateCont=").append(pledgeFVo.getSearchCateCont());
		addParam.append("&searchCondition=").append(pledgeFVo.getSearchCondition());
		addParam.append("&searchKeyword=").append(pledgeFVo.getSearchKeyword());
		addParam.append("&searchStartDate=").append(pledgeFVo.getSearchStartDate());
		addParam.append("&searchEndDate=").append(pledgeFVo.getSearchEndDate());
		addParam.append("&orderBy=").append(pledgeFVo.getOrderBy());
		
		Map authorMap;
		PledgeFVo result = pledgeFSvc.retrievePledge(pledgeFVo);
		String SVC_MSGE;
		
		if (result != null) {
			authorMap = PledgeAuthorChk(result ,request,"mod");
			if(!Boolean.valueOf((Boolean)authorMap.get("flag"))) {
				SVC_MSGE =(String)authorMap.get("msg");
				return alert((String)authorMap.get("returnUrl")+"?"+addParam, SVC_MSGE, model);
			}
			result.setActionKey("modify");
			model.addAttribute("PledgeVo", result);
			if(result.getPlWiwid1()!=0) {
				model.addAttribute("plWiwidList2", pledgeFSvc.retrieveListWiwid(result.getPlWiwid1()));
			}
			List fileList = pledgeFileSvc.retrieveListPledgeFile(pledgeFileVo);
			model.addAttribute("fileList", fileList);
		} else {
			authorMap = PledgeAuthorChk(pledgeFVo ,request,"write");
			if(!Boolean.valueOf((Boolean)authorMap.get("flag"))) {
				SVC_MSGE ="실명인증이 필요한 페이지입니다.\\n실명인증 페이지로 이동합니다.";
				return alert(request.getContextPath() + "/site/"+strDomain+"/login/Login.do?reUrl="+request.getContextPath()+"/site/"+strDomain+"/pledge/Form.do"+addParam, SVC_MSGE, model);
			}
			String regName = (String) request.getSession().getAttribute("ss_name");
			pledgeFVo.setRegName(regName);
			pledgeFVo.setActionKey("regist");
			model.addAttribute("PledgeVo", pledgeFVo);
		}

		model.addAttribute("emailList", cmmCodeSvc.retrieveListCmmCode("EMAIL_CD"));
		model.addAttribute("plCateList", cmmCodeSvc.retrieveListCmmCode("PLCATE"));
		model.addAttribute("plWiwidList1", pledgeFSvc.retrieveListWiwid(1));
		
		return "injeinc/foffice/pledge/pledge_form";
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/site/{strDomain}/pledge/View.do")
	public String PledgeView(@PathVariable("strDomain") String strDomain, HttpServletRequest request, @ModelAttribute("pledgeFVo") PledgeFVo pledgeFVo,@ModelAttribute("PledgeFileVo") PledgeFileVo pledgeFileVo, @RequestParam Map requestMap, ModelMap model) throws Exception {		
		// 파라미터 값
		StringBuffer addParam = new StringBuffer();
		addParam.append("?pageIndex=").append(pledgeFVo.getPageIndex());
		addParam.append("&pageUnit=").append(pledgeFVo.getPageUnit());
		addParam.append("&searchPlWiwid1=").append(pledgeFVo.getSearchPlWiwid1());
		addParam.append("&searchPlWiwid2=").append(pledgeFVo.getSearchPlWiwid2());
		addParam.append("&searchCateCont=").append(pledgeFVo.getSearchCateCont());
		addParam.append("&searchCondition=").append(pledgeFVo.getSearchCondition());
		addParam.append("&searchKeyword=").append(pledgeFVo.getSearchKeyword());
		addParam.append("&searchStartDate=").append(pledgeFVo.getSearchStartDate());
		addParam.append("&searchEndDate=").append(pledgeFVo.getSearchEndDate());
		addParam.append("&orderBy=").append(pledgeFVo.getOrderBy());
		
		PledgeFVo result = pledgeFSvc.retrievePledge(pledgeFVo);
		String SVC_MSGE;
		Map authorMap;
		
		if (result != null) {
			authorMap = PledgeAuthorChk(result ,request,"mod");
			if(Boolean.valueOf((Boolean)authorMap.get("flag"))) {
				result.setModifyAuthor("Y");
			}
			
			authorMap = PledgeAuthorChk(result ,request,"del");
			if(Boolean.valueOf((Boolean)authorMap.get("flag"))) {
				result.setDeleteAuthor("Y");
			}
			
			result.setCountCont(result.getCountCont()+1);
			
			model.addAttribute("PledgeFVo", result);
			List fileList = pledgeFileSvc.retrieveListPledgeFile(pledgeFileVo);
			model.addAttribute("fileList", fileList);
			
			//조회수
			pledgeFSvc.PledgeCountUpdate(result);
		} else {
			SVC_MSGE = "잘못된 접근입니다.";
			return alert(request.getContextPath() + "/site/" + strDomain + "/pledge/PledgeList.do"+addParam, SVC_MSGE, model);
		}
		
		return "injeinc/foffice/pledge/pledge_view";
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/site/{strDomain}/pledge/RegProcess.do")
	public String PledgeReg(@PathVariable("strDomain") String strDomain, HttpServletRequest request, @ModelAttribute("PledgeFVo") PledgeFVo pledgeFVo, ModelMap model) throws Exception {
		model.addAttribute("strDomain", strDomain);
		// 파라미터 값
		StringBuffer addParam = new StringBuffer();
		addParam.append("?pageIndex=").append(pledgeFVo.getPageIndex());
		addParam.append("&pageUnit=").append(pledgeFVo.getPageUnit());
		addParam.append("&searchPlWiwid1=").append(pledgeFVo.getSearchPlWiwid1());
		addParam.append("&searchPlWiwid2=").append(pledgeFVo.getSearchPlWiwid2());
		addParam.append("&searchCateCont=").append(pledgeFVo.getSearchCateCont());
		addParam.append("&searchCondition=").append(pledgeFVo.getSearchCondition());
		addParam.append("&searchKeyword=").append(pledgeFVo.getSearchKeyword());
		addParam.append("&searchStartDate=").append(pledgeFVo.getSearchStartDate());
		addParam.append("&searchEndDate=").append(pledgeFVo.getSearchEndDate());
		addParam.append("&orderBy=").append(pledgeFVo.getOrderBy());

		String SVC_MSGE;
		
		Map authorMap = PledgeAuthorChk(pledgeFVo ,request,"write");
		if(!Boolean.valueOf((Boolean)authorMap.get("flag"))) {
			SVC_MSGE =(String)authorMap.get("msg");
			return alert((String)authorMap.get("returnUrl")+"?"+addParam, SVC_MSGE, model);
		}
		
		String userid = (String)authorMap.get("ssId");
		String userip = (String)authorMap.get("regIp");
		String regType = (String)authorMap.get("regType");
		
		pledgeFVo.setRegId(userid);
		pledgeFVo.setRegIp(userip);
		pledgeFVo.setRegType(regType);
		
		request.setAttribute("userid", userid);
		request.setAttribute("fileMaxSize", pledgeFVo.getFileMaxSize());
		
		try {
			pledgeFSvc.registpledge(request, pledgeFVo);
		} catch (FileUploadException e) {
			e.printStackTrace();
			SVC_MSGE = Message.getMessage("905.message");
			return alert("/foffice" + strDomain + "/pledge/PledgeList.do" + addParam.toString(), SVC_MSGE, model);
		}

		utilSvc.cacheSelectClear("mainCache");
		
		SVC_MSGE = Message.getMessage("100.message");
		return alert("/site/"+strDomain+"/pledge/List.do" + addParam.toString(), SVC_MSGE, model);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/site/{strDomain}/pledge/ModProcess.do")
	public String PledgeMod(@PathVariable("strDomain") String strDomain, HttpServletRequest request,
			@ModelAttribute("PledgeFVo") PledgeFVo pledgeFVo, ModelMap model) throws Exception {
		model.addAttribute("strDomain", strDomain);
		// 파라미터 값
		StringBuffer addParam = new StringBuffer();
		addParam.append("?pageIndex=").append(pledgeFVo.getPageIndex());
		addParam.append("&pageUnit=").append(pledgeFVo.getPageUnit());
		addParam.append("&searchPlWiwid1=").append(pledgeFVo.getSearchPlWiwid1());
		addParam.append("&searchPlWiwid2=").append(pledgeFVo.getSearchPlWiwid2());
		addParam.append("&searchCateCont=").append(pledgeFVo.getSearchCateCont());
		addParam.append("&searchCondition=").append(pledgeFVo.getSearchCondition());
		addParam.append("&searchKeyword=").append(pledgeFVo.getSearchKeyword());
		addParam.append("&searchStartDate=").append(pledgeFVo.getSearchStartDate());
		addParam.append("&searchEndDate=").append(pledgeFVo.getSearchEndDate());
		addParam.append("&orderBy=").append(pledgeFVo.getOrderBy());

		String SVC_MSGE;
		
		PledgeFVo result = pledgeFSvc.retrievePledge(pledgeFVo);
		
		if (result == null) {
			SVC_MSGE = "잘못된 접근입니다.";
			return alert(request.getContextPath() + "/site/" + strDomain + "/pledge/PledgeList.do"+addParam, SVC_MSGE, model);
		}
		
		Map authorMap = PledgeAuthorChk(result ,request,"mod");
		if(!Boolean.valueOf((Boolean)authorMap.get("flag"))) {
			SVC_MSGE =(String)authorMap.get("msg");
			return alert((String)authorMap.get("returnUrl")+"?"+addParam, SVC_MSGE, model);
		}
		
		String userid = (String)authorMap.get("ssId");
		String userip = (String)authorMap.get("regIp");
		
		pledgeFVo.setModId(userid);
		pledgeFVo.setModIp(userip);
		
		request.setAttribute("userid", userid);
		request.setAttribute("fileMaxSize", pledgeFVo.getFileMaxSize());
		
		try {
			pledgeFSvc.modifyPledge(request, pledgeFVo);
		} catch (FileUploadException e) {
			e.printStackTrace();
			SVC_MSGE = Message.getMessage("905.message");
			return alert("/site/" + strDomain + "/pledge/List.do" + addParam.toString(), SVC_MSGE, model);
		}

		utilSvc.cacheSelectClear("mainCache");
		
		SVC_MSGE = Message.getMessage("401.message");
		
		return alert("/site/" + strDomain + "/pledge/List.do" + addParam.toString(), SVC_MSGE, model);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/site/{strDomain}/pledge/Delete.do")
	public String PledgeRmv(@PathVariable("strDomain") String strDomain, HttpServletRequest request,
			@ModelAttribute("PledgeFVo") PledgeFVo pledgeFVo, ModelMap model) throws Exception {
		model.addAttribute("strDomain", strDomain);
		// 파라미터 값
		StringBuffer addParam = new StringBuffer();
		addParam.append("?pageIndex=").append(pledgeFVo.getPageIndex());
		addParam.append("&pageUnit=").append(pledgeFVo.getPageUnit());
		addParam.append("&searchPlWiwid1=").append(pledgeFVo.getSearchPlWiwid1());
		addParam.append("&searchPlWiwid2=").append(pledgeFVo.getSearchPlWiwid2());
		addParam.append("&searchCateCont=").append(pledgeFVo.getSearchCateCont());
		addParam.append("&searchCondition=").append(pledgeFVo.getSearchCondition());
		addParam.append("&searchKeyword=").append(pledgeFVo.getSearchKeyword());
		addParam.append("&searchStartDate=").append(pledgeFVo.getSearchStartDate());
		addParam.append("&searchEndDate=").append(pledgeFVo.getSearchEndDate());
		addParam.append("&orderBy=").append(pledgeFVo.getOrderBy());
		
		String SVC_MSGE;
		
		PledgeFVo result=pledgeFSvc.retrievePledge(pledgeFVo);
		
		if (result == null) {
			SVC_MSGE = "잘못된 접근입니다.";
			return alert(request.getContextPath() + "/site/" + strDomain + "/pledge/PledgeList.do"+addParam, SVC_MSGE, model);
		}
		
		Map authorMap = PledgeAuthorChk(result ,request,"del");
		if(!Boolean.valueOf((Boolean)authorMap.get("flag"))) {
			SVC_MSGE =(String)authorMap.get("msg");
			return alert((String)authorMap.get("returnUrl")+"?"+addParam, SVC_MSGE, model);
		}
		String userid = (String)authorMap.get("ssid");
		String userip = (String)authorMap.get("regip");
		
		pledgeFVo.setModId(userid);
		pledgeFVo.setModIp(userip);
		
		pledgeFSvc.removePledge(pledgeFVo);
		
		utilSvc.cacheSelectClear("mainCache");

		SVC_MSGE = Message.getMessage("501.message");
		
		return alert("/site/" + strDomain + "/pledge/List.do" + addParam.toString(), SVC_MSGE, model);
	}
	
	@RequestMapping("/site/{strDomain}/pledge/WiwIdListAx.do")
	public void WiwIdListAx(@PathVariable("strDomain") String strDomainin, HttpServletRequest request, HttpServletResponse response, @ModelAttribute("PledgeFVo") PledgeFVo pledgeFVo, ModelMap model) throws Exception {
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		if(pledgeFVo.getSearchPlWiwid1()!=0) {
			jsonMap.put("plWiwidList2", pledgeFSvc.retrieveListWiwid(pledgeFVo.getSearchPlWiwid1()));
		}
		jsonMap.put("result", true);
		
		jsonView.render(jsonMap, request, response);
	}
	
	@RequestMapping("/site/{strDomain}/pledge/RecommendAx.do")
	public void RecommendAx(@PathVariable("strDomain") String strDomainin, HttpServletRequest request, HttpServletResponse response, @ModelAttribute("PledgeFVo") PledgeFVo pledgeFVo, ModelMap model) throws Exception {
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		
		String regIp = request.getRemoteAddr();
		
		PledgeFVo pledge=pledgeFSvc.retrievePledge(pledgeFVo);
		
		pledgeFVo.setRegIp(regIp);
		PledgeFVo recommend=pledgeFSvc.retrievePledgeRecommend(pledgeFVo);
		
		Boolean result=false;
		
		if(pledge == null) {
			jsonMap.put("msg", "잘못된 접근입니다.");
		}else if(recommend != null) {
			jsonMap.put("msg", "이미 추천하셨습니다.");
		}else{
			jsonMap.put("msg", "추천 완료했습니다.");
			pledgeFSvc.registPledgeRecommend(pledgeFVo);
			result=true;
			jsonMap.put("recommendCnt", pledge.getRecommendCont()+1);
		}
		jsonMap.put("result", result);
		jsonView.render(jsonMap, request, response);
	}
	
	//권한 체크
	public Map<String, Object> PledgeAuthorChk(PledgeFVo pledgeFVo, HttpServletRequest request, String type)throws Exception {
		HttpSession ses = request.getSession();
		String ssLevel = (String)ses.getAttribute("ss_level");
		System.out.println("ssLevel :: "+ssLevel);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		boolean flag = false;
		String ssId = "";
		String dupcode = "";
		String strMsg = "";
		String regIp = "";
		String returnUrl = null;
		String regType = "";
		
		if( !"".equals(ssLevel) && null != ssLevel ){
			if( "7".equals(ssLevel) ){
				ssId = (String)ses.getAttribute("ss_id"); 
				dupcode = (String)ses.getAttribute("ss_dupkey");
				regIp = (String)ses.getAttribute("ss_ip");
				regType = "member";
			}else if( ssLevel.equals("8") ){
				ssId = "실명인증";
				dupcode = (String)ses.getAttribute("ss_dupkey");
				regIp = (String)ses.getAttribute("ss_ip");
			}else if( ssLevel.equals("11") ){
				EgovMap emap = (EgovMap)ses.getAttribute("SessionTeenagerVo");				
				ssId = (String)emap.get("userid");
				dupcode = (String)emap.get("userid");
				regIp = (String)emap.get("ip");
			}
		}
		
		//리스트
		if(type.equals("read")){
			flag = true;
		}
		//작성
		if(type.equals("write")){
			if(ssLevel != null){
				flag = true;
			}else {
				flag = false;
				strMsg = "쓰기권한이 없습니다.";
				returnUrl = "List.do";
			}
		}
		//수정
		if(type.equals("mod") && !"admin".equals(pledgeFVo.getRegType())){
			String regId = pledgeFVo.getRegId();
			if(ssLevel != null && ssId.equals(regId)){
				flag = true;
			}else{
				flag = false;
				strMsg = "수정 권한이 없습니다.";
				returnUrl = "List.do";
			}
		}
		//삭제
		if(type.equals("del")){
			String regId = pledgeFVo.getRegId();
			if(ssLevel != null && ssId.equals(regId) && !"admin".equals(pledgeFVo.getRegType())){
				flag = true;
			}else{
				flag = false;
				strMsg = "삭제 권한이 없습니다.";
				returnUrl = "List.do";
			}
		}
				
		resultMap.put("flag", flag); 
		resultMap.put("ssId", ssId); 
		resultMap.put("dupcode", dupcode); 
		resultMap.put("regIp", regIp); 
		resultMap.put("msg", strMsg); 
		resultMap.put("returnUrl", returnUrl); 
		resultMap.put("regType", regType); 
		
		
		return resultMap;
	}
}