package egovframework.injeinc.foffice.user.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.metamodel.SetAttribute;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.Message;
import egovframework.injeinc.boffice.cn.menu.service.UserMenuSatisfactionSvc;
import egovframework.injeinc.boffice.cn.menu.vo.UserMenuSatisfactionVo;
import egovframework.injeinc.boffice.sy.board.service.CmsBbsSvc;
import egovframework.injeinc.boffice.sy.board.vo.CmsBbsVo;
import egovframework.injeinc.common.util.DateUtil;
import egovframework.injeinc.common.util.EgovFileScrty;
import egovframework.injeinc.common.util.EgovStringUtil;
import egovframework.injeinc.foffice.ex.bbs.service.BbsFSvc;
import egovframework.injeinc.foffice.ex.bbs.vo.BbsFVo;
import egovframework.injeinc.foffice.ex.myscrap.service.MyscrapUserSvc;
import egovframework.injeinc.foffice.ex.myscrap.vo.MyscrapVO;
import egovframework.injeinc.foffice.ex.poll.service.PollListFSvc;
import egovframework.injeinc.foffice.ex.poll.vo.PollListFVo;
import egovframework.injeinc.foffice.user.service.UserFSvc;
import egovframework.injeinc.foffice.user.vo.UserFVo;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import jcifs.util.MimeMap;
import scala.annotation.meta.setter;

@Controller
public class UserFCtr extends CmmLogCtr{
	
	@Resource(name = "UserFSvc")
	private UserFSvc userSvc;
	
	@Resource(name = "CmsBbsSvc")
	private CmsBbsSvc cmsBbsSvc;
	
	@Autowired
	private BbsFSvc bbsFSvc;
	
	@Autowired
	private UserMenuSatisfactionSvc userMenuSatisfactionSvc;

	@Resource(name = "PollListFSvc")
	private PollListFSvc pollListFSvc;

	@Resource(name ="MyscrapUserSvc")
	private MyscrapUserSvc myscrapUserSvc;
	
	@Autowired(required=true)
	private MappingJackson2JsonView jsonView;
	
	@RequestMapping(value = "/site/{strDomain}/foffice/user/modSelectConf.do")
	public void modSelectConf(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("UserFVo") UserFVo userFVo) throws Exception {

		HttpSession ses = request.getSession();
		String cuId = (String)ses.getAttribute("ss_id");
		userFVo.setCuId(cuId);
		userSvc.modifyUserFSelectConf(userFVo);
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", true);
		jsonMap.put("message", "선택항목 설정을 수정하였습니다.");		
		jsonView.render(jsonMap, request, response);
	}
	
	/** 회원 탈퇴  */
	@RequestMapping(value= "/site/{strDomain}/foffice/user/User_Out.do")
	public String userOut(
			@PathVariable("strDomain") String strDomain,
			HttpServletRequest request, @ModelAttribute("UserFVo") UserFVo userFVo
			, ModelMap model ) throws Exception {
		
		String SVC_MSGE = "";
		
		try{
			HttpSession ses = request.getSession();
			
			String ip = request.getHeader("X-FORWARDED-FOR");
		       if (ip == null){
		           ip = request.getRemoteAddr();
		       }
		    userFVo.setIp(ip);
			
			userSvc.removeUserF(userFVo);	//회원탈퇴
			
			ses.invalidate();
			
			SVC_MSGE = Message.getMessage("502.message");	//회원탈퇴
			
		}catch(RuntimeException e){
			SVC_MSGE = Message.getMessage("901.message");	//오류
		}catch(Exception e){
			SVC_MSGE = Message.getMessage("901.message");	//오류
		}
		
		return alert("/site/" + strDomain + "/main.do", SVC_MSGE, model);
	}
	
	/** 아이디 찾기 폼*/
	@RequestMapping(value= "/site/{strDomain}/user/User_Id_Search_Step_01.do")
	public String userIdSearchStep01(
			@PathVariable("strDomain") String strDomain,
			HttpServletRequest request,HttpServletResponse response, @ModelAttribute("UserFVo") UserFVo userFVo
			, ModelMap model ) throws Exception {
		HttpSession ses = request.getSession();		
		
	    String cert_return_url = request.getScheme() + "://" + request.getServerName();

	    if ((!"80".equals(Integer.valueOf(request.getServerPort()))) && (!"443".equals(Integer.valueOf(request.getServerPort())))) {
	      cert_return_url = cert_return_url + ":" + request.getServerPort();
	    }
	    
		return "injeinc/foffice/user/user_id_search_step_01"; 
	}
	
/*
	  
	*//** 아이디 찾기 결과*//*
	@RequestMapping(value= "/site/{strDomain}/user/User_Id_Selected.do")
	public String userIdSelected(
			@PathVariable("strDomain") String strDomain,
			HttpServletRequest request, @ModelAttribute("UserFVo") UserFVo userFVo
			, ModelMap model ) throws Exception {
		
		HttpSession ses = request.getSession();
		ses.setAttribute("ss_user", "user");
		
		String returnUrl = "";
		String SVC_MSGE = "";
		
		try{
			
			String ownAuth = (String)ses.getAttribute("ss_dupkey");
			List list = userSvc.retrieveUserFIds(ownAuth);
			String ip = request.getHeader("X-FORWARDED-FOR");
		       if (ip == null){
		           ip = request.getRemoteAddr();
		       }
		    
			UserFVo tempvo = null;
			for(int i = 0; i < list.size();i++){
				tempvo = (UserFVo)list.get(i);
				if(!(tempvo.getCuId()+"^"+tempvo.getCuIdx()).equals(userFVo.getCumax())){
					System.out.println(tempvo.getCuId()+"^"+tempvo.getCuIdx());
					userSvc.removeUserF(tempvo);					
				}else{
					userFVo = tempvo;
				}
			}
			model.addAttribute("UserFVo", userFVo);
			returnUrl = "injeinc/foffice/user/user_pw_ch";
		}catch(RuntimeException e){
			SVC_MSGE = Message.getMessage("901.message");
			returnUrl = alert("/site/" + strDomain + "/main.do", SVC_MSGE, model);
		}catch(Exception e){
			SVC_MSGE = Message.getMessage("901.message");
			returnUrl = alert("/site/" + strDomain + "/main.do", SVC_MSGE, model);
		}		
		return returnUrl; 
	}*/
	
	/** 아이디 찾기 결과*/
	@RequestMapping(value= "/site/{strDomain}/user/User_Id_Search_Step_02.do")
	public String userIdSearchStep02(
			@PathVariable("strDomain") String strDomain,
			HttpServletRequest request, @ModelAttribute("UserFVo") UserFVo userFVo
			,RedirectAttributes redirectAttributes, ModelMap model ) throws Exception {
		
	/*	기존 소스
	 * HttpSession ses = request.getSession();
		ses.setAttribute("ss_user", "user");
		
		String returnUrl = "";
		String SVC_MSGE = "";
		
		try{
			
			String ownAuth = (String)ses.getAttribute("ss_dupkey");
			List list = userSvc.retrieveUserFIds(ownAuth);
			if(list.size() < 1){		//회원 조회
				SVC_MSGE = Message.getMessage("606.message");
				returnUrl =  alert("/site/" + strDomain + "/main.do", SVC_MSGE, model);
			}else if(list.size() == 1){
				UserFVo resultvo = (UserFVo)list.get(0);
				model.addAttribute("name", ses.getAttribute("ss_name"));
				model.addAttribute("cuId", resultvo.getCuId());
				returnUrl = "injeinc/foffice/user/user_id_search_step_02";
			}else{				
				model.addAttribute("userlist", list);
				returnUrl = "injeinc/foffice/user/user_step_dupuserlist";
			}
			
		}catch(RuntimeException e){
			SVC_MSGE = Message.getMessage("901.message");
			returnUrl = alert("/site/" + strDomain + "/main.do", SVC_MSGE, model);
		}catch(Exception e){
			SVC_MSGE = Message.getMessage("901.message");
			returnUrl = alert("/site/" + strDomain + "/main.do", SVC_MSGE, model);
		}
		
		return returnUrl; */
		
		//2020.09.08 핸드폰인증 X 버전
		String returnUrl = "";
		String SVC_MSGE = "";
		
		try{
			//String ownAuth = (String)ses.getAttribute("ss_dupkey");
			int userlistCnt = userSvc.retrieveListPollCnt(userFVo);

			if(userlistCnt == 0){
				String loginExclude = request.getParameter("loginExclude");
				redirectAttributes.addAttribute("loginExclude", loginExclude);
				 
				model.addAttribute("userlistCnt", userlistCnt);
				returnUrl = "injeinc/foffice/user/user_id_search_step_02";
			}else {
				UserFVo userlist = userSvc.retrieveUserFId(userFVo);
				String loginExclude = request.getParameter("loginExclude");
				redirectAttributes.addAttribute("loginExclude", loginExclude);
				
				model.addAttribute("userlist", userlist);
				returnUrl = "injeinc/foffice/user/user_id_search_step_02";
			}
		}catch(RuntimeException e){
			SVC_MSGE = Message.getMessage("606.message");
			returnUrl = alert("/site/" + strDomain + "/main.do", SVC_MSGE, model);
		}catch(Exception e){
			SVC_MSGE = Message.getMessage("901.message");
			returnUrl = alert("/site/" + strDomain + "/main.do", SVC_MSGE, model);
		}

		return returnUrl;
	}
	
	/** 비밀번호 찾기 폼*/
	@RequestMapping(value= "/site/{strDomain}/user/User_Pwd_Search_Step_01.do")
	public String userPwdSearchStep01(
			@PathVariable("strDomain") String strDomain,
			HttpServletRequest request,HttpServletResponse response, @ModelAttribute("UserFVo") UserFVo userFVo
			, ModelMap model ) throws Exception {
		HttpSession ses = request.getSession();		
		
	    String cert_return_url = request.getScheme() + "://" + request.getServerName();

	    if ((!"80".equals(Integer.valueOf(request.getServerPort()))) && (!"443".equals(Integer.valueOf(request.getServerPort())))) {
	      cert_return_url = cert_return_url + ":" + request.getServerPort();
	    }
	    
		return "injeinc/foffice/user/user_pwd_search_step_01"; 
	}
	
	/** 비밀번호 찾기 step2*/
	@RequestMapping(value= "/site/{strDomain}/user/User_Pwd_Search_Step_02.do")
	public String userPwdSearchStep02(
			@PathVariable("strDomain") String strDomain,
			HttpServletRequest request, @ModelAttribute("UserFVo") UserFVo userFVo
			, ModelMap model ) throws Exception {
		
		HttpSession ses = request.getSession();
		ses.setAttribute("ss_user", "user");
		String returnUrl = "";
		String SVC_MSGE = "";
		
		try{
			
			/*String ownAuth = (String)ses.getAttribute("ss_dupkey");
			List list = userSvc.retrieveUserFIds(ownAuth);
			if(list.size() < 1){		//회원 조회
				SVC_MSGE = Message.getMessage("606.message");
				returnUrl =  alert("/site/" + strDomain +"/user/User_Step_01.do", SVC_MSGE, model);
			}else if(list.size() == 1){
				UserFVo resultvo = (UserFVo)list.get(0);
				model.addAttribute("name", ses.getAttribute("ss_name"));
				model.addAttribute("cuId", resultvo.getCuId());
				returnUrl = "injeinc/foffice/user/user_pwd_search_step_02";
			}*/
			
			String cuId = request.getParameter("cuId");
			String userPwd = userSvc.retrieveUserFPwd(userFVo);
			int userlistCnt = userSvc.retrieveListPollCnt(userFVo);
			
			model.addAttribute("userPwd", userPwd);
			model.addAttribute("cuId", cuId);
			
			if(userlistCnt == 0){
				SVC_MSGE = Message.getMessage("622.message");
				returnUrl =  alert("/site/"+ strDomain +"/user/User_Pwd_Search_Step_01.do", SVC_MSGE, model);
			}else {
				model.addAttribute("userPwd", userPwd);
				returnUrl = "injeinc/foffice/user/user_pwd_search_step_02";
			}
			

		}catch(RuntimeException e){
			SVC_MSGE = Message.getMessage("901.message");
			returnUrl = alert("/site/" + strDomain + "/main.do", SVC_MSGE, model);
		}catch(Exception e){
			SVC_MSGE = Message.getMessage("901.message");
			returnUrl = alert("/site/" + strDomain + "/main.do", SVC_MSGE, model);
		}
		
		return returnUrl;  
	}
	
	/** 비밀번호 찾기 step3*/
	@RequestMapping(value= "/site/{strDomain}/user/User_Pwd_Search_Step_03.do")
	public String userPwdSearchStep03(
			@PathVariable("strDomain") String strDomain,
			HttpServletRequest request, @ModelAttribute("UserFVo") UserFVo userFVo
			, ModelMap model ) throws Exception {
		
		HttpSession ses = request.getSession();
		
		String returnUrl = "";
		String SVC_MSGE = "";
		
		try{
			/*userFVo.setNewpwd(EgovFileScrty.encryptPassword(userFVo.getNewpwd()));
			userSvc.modifyUserFPwd(userFVo);	//비밀번호 변경
			returnUrl =  alert("/site/" + strDomain + "/login/Login.do", "성공적으로 변경되었습니다.로그인 후 사용해 주시기바랍니다.", model);*/
			
			int index = 0;
			char[] charset = new char[] {
				 '0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','I','J','K','L','M'
				,'N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m'
				,'n','o','p','q','r','s','t','u','v','w','x','y','z','~','!','@','#','$','%','^','&','*','(',')','-','_','+','+','|'
			};

			StringBuffer sb = new StringBuffer();
			
			for(int i = 0 ; i < 10; i++){
				index = (int) (charset.length * Math.random());
				sb.append(charset[index]);
			}
			String cuPwd = sb.toString(); // 암호화 전
			String newpwd = EgovFileScrty.encryptPassword(sb.toString()); // 암호화 후
			String cuId = request.getParameter("cuId");
			
			userFVo.setCuPwd(cuPwd); 
			userFVo.setNewpwd(newpwd);
			userFVo.setCuId(cuId);
			userSvc.modifyUserFPwd(userFVo);

			model.addAttribute("userFVo",userFVo );
			returnUrl =  "injeinc/foffice/user/user_pwd_search_step_03";
			
		}catch(RuntimeException e){
			SVC_MSGE = Message.getMessage("901.message");
			returnUrl = alert("/site/" + strDomain + "/main.do", SVC_MSGE, model);
		}catch(Exception e){
			SVC_MSGE = Message.getMessage("901.message");
			returnUrl = alert("/site/" + strDomain + "/main.do", SVC_MSGE, model);
		}
		
		return returnUrl;  
	}
	
	/** 비밀번호 찾기 step4*/
	@RequestMapping(value= "/site/{strDomain}/user/User_Npwd_do.do")
	public String userNpwdDo(
			@PathVariable("strDomain") String strDomain,
			HttpServletRequest request, @ModelAttribute("UserFVo") UserFVo userFVo
			, RedirectAttributes redirectAttributes ) throws Exception {
		
		String returnUrl = "";
		String SVC_MSGE = "";
		
		String searchPwd = request.getParameter("searchPwd");
		
		try{
			
			ModelMap model = new ModelMap();
			redirectAttributes.addAttribute("searchPwd", searchPwd);
			returnUrl = "redirect:/site/DRP0000/login/Login.do";
			
		}catch(RuntimeException e){
			SVC_MSGE = Message.getMessage("901.message");
			returnUrl = "redirect:/site/DRP0000/login/Login.do";
		}catch(Exception e){
			SVC_MSGE = Message.getMessage("901.message");
			returnUrl = "redirect:/site/DRP0000/login/Login.do";
		}
		
		return returnUrl;  
	}
	
	/** 비밀번호 변경하기 폼*/
	@RequestMapping(value= "/site/{strDomain}/user/User_Pwd_Chg_Form.do")
	public String userPwdChgForm(
			@PathVariable("strDomain") String strDomain,
			HttpServletRequest request,HttpServletResponse response, @ModelAttribute("UserFVo") UserFVo userFVo
			, ModelMap model ) throws Exception {
		HttpSession ses = request.getSession();		
		
	    String cert_return_url = request.getScheme() + "://" + request.getServerName();

	    if ((!"80".equals(Integer.valueOf(request.getServerPort()))) && (!"443".equals(Integer.valueOf(request.getServerPort())))) {
	      cert_return_url = cert_return_url + ":" + request.getServerPort();
	    }
		return "injeinc/foffice/user/user_pwd_chg_form"; 
	}
	
	/** 비밀번호 변경 */
	@RequestMapping(value= "/site/{strDomain}/foffice/user/User_Pwd_Mod.do")
	public String userPwdMod(
			@PathVariable("strDomain") String strDomain,
			HttpServletRequest request, @ModelAttribute("UserFVo") UserFVo userFVo
			,RedirectAttributes redirectAttributes, ModelMap model ) throws Exception {
		HttpSession ses = request.getSession();
		String SVC_MSGE = "";
		String returnUrl = "";
		String result = "";
		String type = userFVo.getType();
		try{
			
			String cuPwd = userSvc.retrieveUserFPwd(userFVo);// 기존 비밀번호 조회
			String newPwd = request.getParameter("newPwd");//새 비밀번호
			
			if(!cuPwd.equals(EgovFileScrty.encryptPassword(userFVo.getCuPwd()))){
				String encryp = EgovFileScrty.encryptPassword(userFVo.getCuPwd());
				SVC_MSGE = Message.getMessage("605.message");
				returnUrl = alert("/site/" + strDomain + "/user/User_Pwd_Chg_Form.do", SVC_MSGE, model); 
			}else{
				userFVo.setNewpwd(EgovFileScrty.encryptPassword(newPwd));
				userSvc.modifyUserFPwd(userFVo);		//비밀번호 변경
				model.addAttribute("result","Y");
				ses.invalidate();
				
				String loginExclude = request.getParameter("loginExclude");
				redirectAttributes.addAttribute("loginExclude", loginExclude);
				SVC_MSGE = Message.getMessage("401.message");
				
				//return "redirect:/site/DRP0000/login/Login.do";
				returnUrl = alert("/site/" + strDomain + "/login/Login.do", SVC_MSGE, model);
			}
		}catch(RuntimeException e){
			SVC_MSGE = Message.getMessage("901.message");
			returnUrl = alert("/site/" + strDomain + "/main.do", SVC_MSGE, model);
		}catch(Exception e){
			SVC_MSGE = Message.getMessage("901.message");
			returnUrl = alert("/site/" + strDomain + "/main.do", SVC_MSGE, model);
		}
		
		return returnUrl;
	}
	
	/**
	 * 나의게시글
	 * @param request
	 * @param UserFVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/site/{strDomain}/foffice/user/MyIntegBoardList.do")
	public String myIntegBoardList(HttpServletRequest request, @ModelAttribute("BbsFVo") BbsFVo bbsFVo
						   , ModelMap model, @PathVariable("strDomain") String strDomain) throws Exception {
		
		HttpSession ses = request.getSession();
		String SVC_MSGE = "";
		try{
			bbsFVo.setDupcode((String)ses.getAttribute("ss_dupkey"));
			
			int myIntegBoardCnt =  userSvc.retrieveMyIntegBoardCnt(bbsFVo);
			
			//페이징
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(bbsFVo.getPageIndex());
			paginationInfo.setRecordCountPerPage(bbsFVo.getPageUnit());
			paginationInfo.setPageSize(bbsFVo.getPageSize());
			bbsFVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			bbsFVo.setLastIndex(paginationInfo.getLastRecordIndex());
			bbsFVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			paginationInfo.setTotalRecordCount(myIntegBoardCnt);
			
			// 나의게시글 리스트 조회
			List myIntegBoardList = userSvc.retrieveMyIntegBoardList(bbsFVo);
			
			model.addAttribute("myIntegBoardCnt", myIntegBoardCnt);
			model.addAttribute("paginationInfo", paginationInfo);
			model.addAttribute("myIntegBoardList", myIntegBoardList);
			model.addAttribute("bbsFVo", bbsFVo);
			model.addAttribute("strDomain", strDomain);
			
		}catch(RuntimeException e){
			SVC_MSGE = Message.getMessage("901.message");
			//returnUrl = alert("/site/" + strDomain + "/main.do", SVC_MSGE, model);
		}catch(Exception e){
			SVC_MSGE = Message.getMessage("901.message");
			//returnUrl = alert("/site/" + strDomain + "/main.do", SVC_MSGE, model);
		}
		return "injeinc/foffice/user/myIntegBoardList";  
	}
	
	/**
	 * 나의민원
	 * @param request
	 * @param UserFVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/site/{strDomain}/foffice/user/MyIntegMinwonBoardList.do")
	public String myIntegMinwonBoardList(HttpServletRequest request, @ModelAttribute("BbsFVo") BbsFVo bbsFVo
						   , ModelMap model, @PathVariable("strDomain") String strDomain) throws Exception {
		
		HttpSession ses = request.getSession();
		String SVC_MSGE = "";
		try{
			bbsFVo.setDupcode((String)ses.getAttribute("ss_dupkey"));
			
			int myIntegMinwonBoardCnt =  userSvc.retrieveMyIntegMinwonBoardCnt(bbsFVo);
			
			//페이징
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(bbsFVo.getPageIndex());
			paginationInfo.setRecordCountPerPage(bbsFVo.getPageUnit());
			paginationInfo.setPageSize(bbsFVo.getPageSize());
			bbsFVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			bbsFVo.setLastIndex(paginationInfo.getLastRecordIndex());
			bbsFVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			paginationInfo.setTotalRecordCount(myIntegMinwonBoardCnt);
			
			// 나의게시글 리스트 조회
			List myIntegMinwonBoardList = userSvc.retrieveMyIntegMinwonBoardList(bbsFVo);
			
			model.addAttribute("myIntegMinwonBoardCnt", myIntegMinwonBoardCnt);
			model.addAttribute("paginationInfo", paginationInfo);
			model.addAttribute("myIntegMinwonBoardList", myIntegMinwonBoardList);
			model.addAttribute("bbsFVo", bbsFVo);
			model.addAttribute("strDomain", strDomain);
			
		}catch(RuntimeException e){
			SVC_MSGE = Message.getMessage("901.message");
			//returnUrl = alert("/site/" + strDomain + "/main.do", SVC_MSGE, model);
		}catch(Exception e){
			SVC_MSGE = Message.getMessage("901.message");
			//returnUrl = alert("/site/" + strDomain + "/main.do", SVC_MSGE, model);
		}
		return "injeinc/foffice/user/myIntegMinwonBoardList";  
	}
	
	
	/**
	 * 나의민원
	 * @param request
	 * @param UserFVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/site/{strDomain}/foffice/user/MileageList.do")
	public String mileageList(HttpServletRequest request, @ModelAttribute("UserFVo") UserFVo userFVo
						   , ModelMap model, @PathVariable("strDomain") String strDomain) throws Exception {
		
		HttpSession ses = request.getSession();
		String SVC_MSGE = "";
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(userFVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(userFVo.getPageUnit());
		paginationInfo.setPageSize(userFVo.getPageSize());
		
		userFVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		userFVo.setLastIndex(paginationInfo.getLastRecordIndex());
		userFVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		userFVo.setId((String)ses.getAttribute("ss_id"));
		
		try{
			
			int totCnt = userSvc.retrieveListMileageCnt((String)ses.getAttribute("ss_id")); 
			paginationInfo.setTotalRecordCount(totCnt);
			
			List<UserFVo> mileageList = userSvc.retrieveListMileage(userFVo);
			
			model.addAttribute("totCnt", totCnt);
			model.addAttribute("paginationInfo", paginationInfo);
			model.addAttribute("mileageList", mileageList);
		}catch(RuntimeException e){
			SVC_MSGE = Message.getMessage("901.message");
			return alert("/site/" + strDomain + "/main.do", SVC_MSGE, model);
		}catch(Exception e){
			SVC_MSGE = Message.getMessage("901.message");
			return alert("/site/" + strDomain + "/main.do", SVC_MSGE, model);
		}
		
		return "injeinc/foffice/user/mileage_list";
	}
	
	public static String requestReplace (String paramValue, String gubun) {
        String result = "";
        
        if (paramValue != null) {
        	
        	paramValue = paramValue.replaceAll("<", "&lt;").replaceAll(">", "&gt;");

        	paramValue = paramValue.replaceAll("\\*", "");
        	paramValue = paramValue.replaceAll("\\?", "");
        	paramValue = paramValue.replaceAll("\\[", "");
        	paramValue = paramValue.replaceAll("\\{", "");
        	paramValue = paramValue.replaceAll("\\(", "");
        	paramValue = paramValue.replaceAll("\\)", "");
        	paramValue = paramValue.replaceAll("\\^", "");
        	paramValue = paramValue.replaceAll("\\$", "");
        	paramValue = paramValue.replaceAll("'", "");
        	paramValue = paramValue.replaceAll("@", "");
        	paramValue = paramValue.replaceAll("%", "");
        	paramValue = paramValue.replaceAll(";", "");
        	paramValue = paramValue.replaceAll(":", "");
        	paramValue = paramValue.replaceAll("-", "");
        	paramValue = paramValue.replaceAll("#", "");
        	paramValue = paramValue.replaceAll("--", "");
        	paramValue = paramValue.replaceAll("-", "");
        	paramValue = paramValue.replaceAll(",", "");
        	
        	if(gubun != "encodeData"){
        		paramValue = paramValue.replaceAll("\\+", "");
        		paramValue = paramValue.replaceAll("/", "");
            paramValue = paramValue.replaceAll("=", "");
        	}
        	
        	result = paramValue;
            
        }
        return result;
  }
		
	@RequestMapping(value= "/site/{strDomain}/user/satisfaction.do")
	public String satisfaction(@PathVariable("strDomain") String strDomain,HttpServletRequest request, 
			@ModelAttribute("UserMenuSatisfactionVo") UserMenuSatisfactionVo userMenuSatisfactionVo, ModelMap model ) throws Exception {
		HttpSession ses = request.getSession();
		
		
		//if(ses.getAttribute("ss_id") !=null){
			//String userId = ses.getAttribute("ss_id").toString();
			String userId = request.getRemoteAddr();
			userMenuSatisfactionVo.setUserId(userId);
			userMenuSatisfactionVo.setSiteCd(strDomain);
			int userCount = userMenuSatisfactionSvc.selectUserMenuSatisfactionIpCnt(userMenuSatisfactionVo);
			
			if(userCount==0){
				userMenuSatisfactionSvc.registUserMenuSatisfaction(userMenuSatisfactionVo);
				String returnUrl = request.getParameter("returnUrl");
				model.addAttribute("returnUrl", returnUrl);
				
				String message = "저장되었습니다.";
				return alert(returnUrl, message, model);
			}else{
				String message = "하루 한번에 한해서 입력가능합니다!";
				String returnUrl = request.getParameter("returnUrl");
				return alert(returnUrl, message, model);
			}
		//}
		
 	}
	
	private Document parseXML(InputStream stream) throws Exception{
		DocumentBuilderFactory objDocumentBuilderFactory = null;
		DocumentBuilder objDocumentBuilder = null;
		Document doc = null;
		try{
			objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
			objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();
			doc = objDocumentBuilder.parse(stream);
		}catch(Exception ex){
			throw ex;
		}
		return doc;
	}
	
	public static String escape(String src) {   
        int i;   
        char j;   
        StringBuffer tmp = new StringBuffer();   
        tmp.ensureCapacity(src.length() * 6);   
        for (i = 0; i < src.length(); i++) {   
                j = src.charAt(i);   
                if (Character.isDigit(j) || Character.isLowerCase(j) || Character.isUpperCase(j))   
                        tmp.append(j);   
                else if (j < 256) {   
                        tmp.append("%");   
                        if (j < 16)   
                                tmp.append("0");   
                        tmp.append(Integer.toString(j, 16));   
                } else {   
                        tmp.append("%u");   
                        tmp.append(Integer.toString(j, 16));   
                }   
        }   
        return tmp.toString();   
	}   

	public static String unescape(String src) {   
	        StringBuffer tmp = new StringBuffer();   
	        tmp.ensureCapacity(src.length());   
	        int lastPos = 0, pos = 0;   
	        char ch;   
	        while (lastPos < src.length()) {   
	                pos = src.indexOf("%", lastPos);   
	                if (pos == lastPos) {   
	                        if (src.charAt(pos + 1) == 'u') {   
	                                ch = (char) Integer.parseInt(src   
	                                                .substring(pos + 2, pos + 6), 16);   
	                                tmp.append(ch);   
	                                lastPos = pos + 6;   
	                        } else {   
	                                ch = (char) Integer.parseInt(src   
	                                                .substring(pos + 1, pos + 3), 16);   
	                                tmp.append(ch);   
	                                lastPos = pos + 3;   
	                        }   
	                } else {   
	                        if (pos == -1) {   
	                                tmp.append(src.substring(lastPos));   
	                                lastPos = src.length();   
	                        } else {   
	                                tmp.append(src.substring(lastPos, pos));   
	                                lastPos = pos;   
	                        }   
	                }   
	        }   
	        return tmp.toString();   
	}  
	
	/** 아이디 사용여부 체크 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/site/{strDomain}/ex/foffice/user/userPwdChk.do")
	public void SearchthemeUseYn_Ax
	(@PathVariable("strDomain") String strDomain,
    HttpServletRequest request,
    HttpServletResponse response) throws Exception {

		HttpSession ses = request.getSession();		
		
		UserFVo uservo  = new UserFVo();
		uservo.setCuId((String)ses.getAttribute("ss_id"));
		uservo.setOwnAuth((String)ses.getAttribute("ss_dupkey"));
		
		String cuPwd = request.getParameter("cuPwd");
		
		String strPwd = userSvc.retrieveUserFPwd2(uservo); // 아이디 사용여부 조회 (탈퇴 회원 조회)
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		
		if(EgovFileScrty.encryptPassword(cuPwd).equals(strPwd)){
			jsonMap.put("message", "Y");
		}else{
			jsonMap.put("message", "N");
		}		
		jsonView.render(jsonMap, request, response);
	}
	
}