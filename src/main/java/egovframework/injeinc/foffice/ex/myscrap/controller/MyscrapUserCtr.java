package egovframework.injeinc.foffice.ex.myscrap.controller;



import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import egovframework.cmm.CmmLogCtr;
import egovframework.injeinc.boffice.cn.menu.service.MenuSvc;
import egovframework.injeinc.boffice.cn.menu.vo.MenuVo;
import egovframework.injeinc.boffice.sy.user.service.UserSvc;
import egovframework.injeinc.boffice.sy.user.vo.UserVo;
import egovframework.injeinc.foffice.ex.bbs.vo.BbsFVo;
import egovframework.injeinc.foffice.ex.myscrap.service.MyscrapUserSvc;
import egovframework.injeinc.foffice.ex.myscrap.vo.MyscrapVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


@Controller
public class MyscrapUserCtr  extends CmmLogCtr{
	
	@Resource(name ="MyscrapUserSvc")
	private MyscrapUserSvc myscrapUserSvc;
	
	@Resource(name ="UserSvc")
	private UserSvc userSvc;
	
	@Resource(name = "MenuSvc")
	private MenuSvc menuSvc;
	
	@Autowired(required=true)
	private MappingJackson2JsonView jsonView;
	
	
	@RequestMapping("/site/{strDomain}/foffice/ex/myscrap/myscrapUserList.do")
	public String myscrapUserList(HttpServletRequest request, @ModelAttribute("MyscrapVO") MyscrapVO myscrapVO
			, ModelMap model) throws Exception{
		
		HttpSession ses = request.getSession();
		String userid = (String)ses.getAttribute("ss_id");
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(myscrapVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(myscrapVO.getPageUnit());
		paginationInfo.setPageSize(myscrapVO.getPageSize());
		
		myscrapVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		myscrapVO.setLastIndex(paginationInfo.getLastRecordIndex());
		myscrapVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		myscrapVO.setUser_id_v(userid);
		
		Map<String, Object> map = myscrapUserSvc.selectMyscrapUserList(myscrapVO); 
		int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		
		paginationInfo.setTotalRecordCount(totCnt);
		
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);
		
		
		//return "injeinc/foffice/ex/myscrap/myscrapUser_list";
		return "injeinc/foffice/my{strDomain}/myscrapUser_list";
	}
	
	
	@RequestMapping("/site/{strDomain}/ex/myscrap/myscrapUserInsertForm.do")
	public String selectMyscrapRegist(HttpServletRequest request, @ModelAttribute("MyscrapVO") MyscrapVO myscrapVO
			 ,@ModelAttribute("MenuVo") MenuVo menuVo, @ModelAttribute("BbsFVo") BbsFVo bbsFVo, ModelMap model) throws Exception{
		
		return "injeinc/foffice/ex/myscrap/myscrapUser_form";
		
	}
	
	
	@RequestMapping("/site/{strDomain}/ex/myscrap/myscrapUserInsert.do")
	public String insertMyscrap(HttpServletRequest request, @ModelAttribute("MyscrapVO") MyscrapVO myscrapVO
			, ModelMap model) throws Exception{
		
		HttpSession ses = request.getSession();
		UserVo view = userSvc.retrieveUser(Integer.valueOf((String)ses.getAttribute("ss_idx")));
		myscrapVO.setUser_id_v(view.getCuId());
		
		int cnt  = myscrapUserSvc.selectdupCnt(myscrapVO);
		if(cnt > 0){
			if(myscrapVO.getBbs_seq_n() != 0){
				return alert(myscrapVO.getScrap_loc_v()+"&bcIdx="+myscrapVO.getBbs_seq_n(),"이미 스크랩하셨습니다.",model);
			}else{
				return alert(myscrapVO.getScrap_loc_v(),"이미 스크랩하셨습니다.",model);
			}
				
		}else{
			myscrapUserSvc.insertMyscrapUser(myscrapVO);
		}
		
				
		//return alert("/site/{strDomain}/foffice/ex/myscrap/myscrapUserList.do","마이페이지로 이동합니다.",model);
		return alertScrap("/site/{strDomain}/foffice/user/myscrapUserList.do",model);	
	}
	
	@RequestMapping("/site/{strDomain}/ex/myscrap/myscrapUserDelete.do")
	public void deleteMyscrap(HttpServletRequest request
			, HttpServletResponse response
			, @ModelAttribute("MyscrapVO") MyscrapVO myscrapVO
			) throws Exception{
		
		myscrapUserSvc.deleteMyscrap(myscrapVO);
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", true);
		jsonMap.put("message", "삭제되었습니다.");
		jsonView.render(jsonMap, request, response);
		
	}
	
	
	

}
