package egovframework.injeinc.boffice.sy.progrm.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.ComDefaultVO;
import egovframework.cmm.EgovMessageSource;
import egovframework.injeinc.boffice.sy.progrm.service.ProgrmSvc;
import egovframework.injeinc.boffice.sy.progrm.vo.ProgrmVo;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


@Controller
public class ProgrmCtr extends CmmLogCtr {

    /** progrmSvc */
	@Resource(name = "progrmSvc")
    private ProgrmSvc progrmSvc;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
    @Autowired(required=true)
	private MappingJackson2JsonView jsonView;


    /**
     * 프로그램목록을 상세화면 호출 및 상세조회한다.
     * @param tmp_progrmNm  String
     * @return 출력페이지정보 "sym/prm/EgovProgramListDetailSelectUpdt"
     * @exception Exception
     */
    @RequestMapping(value="/boffice_noDeco/sy/progrm/ProgramDetail.do")
    public String selectProgrm(
    		@RequestParam("tmp_progrmNm") String tmp_progrmNm ,
   		    @ModelAttribute("searchVO") ComDefaultVO searchVO,
    		ModelMap model)
            throws Exception {
    	searchVO.setSearchKeyword(tmp_progrmNm);
    	ProgrmVo progrmVo = progrmSvc.selectProgrm(searchVO);
        model.addAttribute("progrmVo", progrmVo);
        return "injeinc/boffice/sy/progrm/progrm_updt";
    }

    /**
     * 프로그램목록 리스트조회한다.
     * @param searchVO ComDefaultVO
     * @return 출력페이지정보 "sym/prm/EgovProgramListManage"
     * @exception Exception
     */
    @RequestMapping(value="/boffice/sy/progrm/ProgramList.do")
    public String selectProgrmList(
    		@ModelAttribute("searchVO") ComDefaultVO searchVO,
    		ModelMap model)
            throws Exception {

    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        List list_progrmmanage = progrmSvc.selectProgrmList(searchVO);
        model.addAttribute("list_progrmmanage", list_progrmmanage);
        model.addAttribute("searchVO", searchVO);

        int totCnt = progrmSvc.selectProgrmListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return "injeinc/boffice/sy/progrm/progrm_list";
    }

    /**
     * 프로그램목록을 등록화면으로 이동 및 등록 한다.
     * @param ProgrmVo progrmVo
     * @param commandMap     Map
     * @exception Exception
     */
    @RequestMapping(value="/boffice_noDeco/sy/progrm/ProgramRegist.do")
    public String insertProgrmList(
    		String cmd,
    		@ModelAttribute("progrmVo") ProgrmVo progrmVo,
			ModelMap model)
            throws Exception {
        String resultMsg = "";
        String sLocationUrl = null;

        String sCmd = cmd == null ? "" : cmd;
        
        if(sCmd.equals("insert")){
	        
			if(progrmVo.getProgrmDc()==null || progrmVo.getProgrmDc().equals("")){
				progrmVo.setProgrmDc(" ");
			}
			
	    	progrmSvc.insertProgrm(progrmVo);
			resultMsg = egovMessageSource.getMessage("success.common.insert");
	        return alert("/boffice/sy/progrm/ProgramList.do", resultMsg, model);
        }else{
        	
            sLocationUrl = "injeinc/boffice/sy/progrm/progrm_form";
        }
    	model.addAttribute("resultMsg", resultMsg);
		return sLocationUrl;
    }

    /**
     * 프로그램목록을 수정 한다.
     * @param ProgrmVo progrmVo
     * @exception Exception
     */
    /*프로그램목록수정*/
    @RequestMapping(value="/boffice/sy/progrm/ProgramUpdt.do")
    public String updateProgrmList(
    		@ModelAttribute("progrmVo") ProgrmVo progrmVo,
    		ModelMap model)
            throws Exception {
		String resultMsg = "";

		if(progrmVo.getProgrmDc()==null || progrmVo.getProgrmDc().equals("")){
			progrmVo.setProgrmDc(" ");
		}
		
		progrmSvc.updateProgrm(progrmVo);
		resultMsg = egovMessageSource.getMessage("success.common.update");
    	model.addAttribute("resultMsg", resultMsg);
		return alert("/boffice/sy/progrm/ProgramList.do", resultMsg, model);
    }

    /**
     * 프로그램목록을 삭제 한다.
     * @param ProgrmVo progrmVo
     * @exception Exception
     */
    @RequestMapping(value="/boffice/sy/progrm/ProgramDelete.do")
    public String deleteProgrmList(
    		@ModelAttribute("progrmVo")
    		ProgrmVo progrmVo,
    		ModelMap model)
            throws Exception {
    	String resultMsg = "";
    	
        progrmSvc.deleteProgrm(progrmVo);
        resultMsg = egovMessageSource.getMessage("success.common.delete");
    	model.addAttribute("resultMsg", resultMsg);
    	return alert("/boffice/sy/progrm/ProgramList.do", resultMsg, model);
    }
    
    /**
     * 프로그램파일명을 조회한다.
     * @param searchVO ComDefaultVO
     * @return 출력페이지정보 "sym/prm/EgovFileNmSearch"
     * @exception Exception
     */
    @RequestMapping(value="/boffice_noDeco/sy/progrm/ProgramListSearch.do")
    public String selectProgrmListSearch(
    		@ModelAttribute("searchVO") ComDefaultVO searchVO,
    		ModelMap model)
            throws Exception {
    	
    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        List list_progrmmanage = progrmSvc.selectProgrmList(searchVO);
        model.addAttribute("list_progrmmanage", list_progrmmanage);

        int totCnt = progrmSvc.selectProgrmListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

      	return "injeinc/boffice/sy/progrm/progrm_search";
    }
    
    @RequestMapping("/boffice/sy/progrm/ProgramDupChkAx.do")
	public void ProgramDupChkAx(
			HttpServletRequest request, 
			HttpServletResponse response, 
			@ModelAttribute("progrmVo") ProgrmVo progrmVo, 
			ModelMap model) 
			throws Exception {

		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		
		int dup_cnt = progrmSvc.selectProgrmDupCnt(progrmVo);
				
		jsonMap.put("result", dup_cnt);
		
		jsonView.render(jsonMap, request, response);
			
	}
    
}