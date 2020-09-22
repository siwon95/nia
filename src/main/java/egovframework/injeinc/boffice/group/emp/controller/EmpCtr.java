package egovframework.injeinc.boffice.group.emp.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.GlobalConst;
import egovframework.cmm.Message;
import egovframework.injeinc.boffice.group.emp.service.EmpSvc;
import egovframework.injeinc.boffice.group.emp.vo.EmpTempVo;
import egovframework.injeinc.boffice.group.emp.vo.EmpVo;
import egovframework.injeinc.common.util.EgovStringUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.injeinc.boffice.sy.mgr.service.MgrListSvc;
import egovframework.injeinc.boffice.sy.mgr.vo.RoleVo;

@Controller
public class EmpCtr extends CmmLogCtr{
	
	@Resource(name = "EmpSvc")
	private EmpSvc empSvc;
	
	@Autowired(required=true)
	private MappingJackson2JsonView jsonView;
	
	@Resource(name = "MgrListSvc")
	private MgrListSvc mgrListSvc;
	
	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	 
	//리스트
	@RequestMapping(value= "/boffice{strDomain}/group/emp/Emp_List.do")
	public String empList(
			@PathVariable("strDomain") String strDomain,
			HttpServletRequest request,
			@ModelAttribute("empVo") EmpVo empVo,
			ModelMap model) throws Exception {
		
		empVo.setPageUnit(propertiesService.getInt("pageUnit"));
		empVo.setPageSize(propertiesService.getInt("pageSize"));
		
		/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(empVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(empVo.getPageUnit());
		paginationInfo.setPageSize(empVo.getPageSize());

		empVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		empVo.setLastIndex(paginationInfo.getLastRecordIndex());
		empVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		if(strDomain.equals("_noDeco")){
			empVo.setCeUse("Y");
		}
		
		HttpSession ses = request.getSession();
		if(empVo.getSelBox()==null || empVo.getSelBox()==""|| empVo.getSelBox().equals("dd")) {
			empVo.setSelBox("D000000");
		}
		
		String SesUserPermCd = ses.getAttribute("SesUserPermCd").toString();
		if(!SesUserPermCd.equals("05010000")){
			empVo.setMode("department_admin");
			empVo.setCdIdx(ses.getAttribute("SesUserDeptCd").toString());
		}
		
		List<EmpVo> selectList  = empSvc.retrieveListSbEmp(empVo);	 // 셀렉트 박스에 뿌릴 리스트 조회
		
		List<EmpVo> resultList = new ArrayList<EmpVo>(); 
		
		if(empVo.getActionKey().equals("update")){
			
			if(empVo.getCeUse().equals("Y")){	//미사용 → 사용
				String maxCeSort = empSvc.retrieveMaxCeSort(empVo);
				if(maxCeSort == null || maxCeSort.equals("")){
					maxCeSort = "1";
				}else{
					int maxValue = Integer.parseInt(maxCeSort);
					if(maxValue < 10){
						maxCeSort = maxCeSort;
					}
				}
				empVo.setCeSort(maxCeSort); // 임시로 변환한 1을 제외하고 잘라낸후 set
			}
			
			empSvc.modifyUseCheck(empVo);
		}else if(empVo.getActionKey().equals("jUpdate")){
			empSvc.modifyUseCheck(empVo);
		}
		if(empVo.getSrchAll().equals("Y") || empVo.getSelBox().equals("D000000")){
			
			resultList = empSvc.retrieveListAllNoEmp(empVo);	//리스트 조회(전체, 미등록)
			int totCnt = empSvc.selectAllEmpListTotCnt(empVo);
			paginationInfo.setTotalRecordCount(totCnt);
			
		}else if(empVo.getSelBox() != null && !empVo.getSelBox().equals("")){	//부서를 선택 했을경우
			
			empVo.setCdDepstep1(empVo.getSelBox().substring(0,2));
			empVo.setCdDepstep2(empVo.getSelBox().substring(2,4));
			empVo.setCdDepstep3(empVo.getSelBox().substring(4,6));
			
			resultList = empSvc.retrieveListEmp(empVo);	//리스트 조회(전체, 미등록 제외)
			int totCnt = empSvc.selectEmpListTotCnt(empVo);
			paginationInfo.setTotalRecordCount(totCnt);
		}
		
		String step = empSvc.selectDepartmentStep(empVo);
		RoleVo roleVo = new RoleVo();
		Map<String, Object> map = mgrListSvc.retrieveRoleMap(roleVo);
		
		if(model != null){
			model.addAttribute("resultList", resultList);		
			model.addAttribute("selectList", selectList);
			model.addAttribute("step", step);
			model.addAttribute("roleList", map.get("resultList"));
			model.addAttribute("paginationInfo", paginationInfo);
		}
		
		if(strDomain.equals("_noDeco")){
			return GlobalConst.SITE_ROOT_BOFFICE_PATH +"/group/emp/emp_list_pop";
		}else{
			return GlobalConst.SITE_ROOT_BOFFICE_PATH +"/group/emp/emp_list";
		}
	}
	
	//등록, 수정 폼 호출
	@RequestMapping(value= "/boffice_noDeco/group/emp/Emp_Form.do")
	public String empForm(
			HttpServletRequest request,
			@ModelAttribute("empVo") EmpVo empVo,
			ModelMap model) throws Exception {
		
		List<EmpVo> depstep1List = empSvc.retrieveListDepstep1();		//국코드, 국명 조회
		if(model != null){
			model.addAttribute("depstep1", depstep1List);
		}
		if(empVo.getCeIdx() != null && !empVo.getCeIdx().equals("new") && !empVo.getCeIdx().equals("close")){		//신규등록이 아닐때(상세보기, 수정폼 호출)
			EmpVo emp = empSvc.retrieveEmp(empVo);	//사용자정보 조회
			
			if(emp != null){
				HashMap<String, String> param = new HashMap<String, String>();
				
				param.put("cdDepstep1", emp.getCdDepstep1());
				
				List cdDepstep2 = empSvc.retrieveListEmpDep2(param);		//과코드,명 리스트 조회
				
				param.put("cdDepstep2", emp.getCdDepstep2());
				
				List cdDepstep3 = empSvc.retrieveListEmpDep3(param);		//팀코드,명 리스트 조회
				
				String selBox = emp.getCeCdIdx();
				if(selBox == null){
					selBox = "";
				}
				empVo.setSelBox(selBox);
				
				if(model != null){
					model.addAttribute("empVo", emp);
					model.addAttribute("dep2", cdDepstep2);
					model.addAttribute("dep3", cdDepstep3);
				}
			}
		}
		if(empVo.getCeIdx().equals("new")){
			empVo.setCeCdIdx(empVo.getSelBox());
		}
		String step = empSvc.selectDepartmentStep(empVo);
		
		model.addAttribute("step", step);
		
		RoleVo roleVo = new RoleVo();
		Map<String, Object> map = mgrListSvc.retrieveRoleMap(roleVo);
		
		model.addAttribute("roleList", map.get("resultList"));
		
		
		return GlobalConst.SITE_ROOT_BOFFICE_PATH +"/group/emp/emp_form";
	}
	
	//셀렉트박스 선택시 하위셀렉트박스 조회
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/emp/Emp_ListAx.do")
	public void empListAx(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession ses = request.getSession();
		
		String cdDepstep1 = EgovStringUtil.nullConvert(request.getParameter("cdDepstep1"));
		String cdDepstep2 = EgovStringUtil.nullConvert(request.getParameter("cdDepstep2"));	
		
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("cdDepstep1", cdDepstep1);
		param.put("cdDepstep2", cdDepstep2);

		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		List<EmpVo> depstepList = new ArrayList<EmpVo>(); 
		if(cdDepstep2 != null){
			if(cdDepstep2.equals("00")){
				depstepList = empSvc.retrieveListEmpDep2(param);
			}else{
				depstepList = empSvc.retrieveListEmpDep3(param);
			}
		}
		jsonMap.put("depstepList", depstepList);
			jsonView.render(jsonMap, request, response);
			
	}
	
	//등록
	@RequestMapping(value= "/boffice/group/emp/Emp_Reg.do")
	public String empReg(
			HttpServletRequest request,
			@ModelAttribute("empVo") EmpVo empVo,
			ModelMap model) throws Exception {
		
		String maxCeIdx = "";
		if(empVo.getCeIdx().equals("new")){
			maxCeIdx = empSvc.retrieveMaxCeIdx();		//최대 ceIdx 조회
			int newCeIdx = Integer.parseInt(maxCeIdx.replaceFirst("e", "1")) + 1;
			empVo.setCeIdx("e" + (Integer.toString(newCeIdx).substring(1, 9))); // 임시로 변환한 1을 제외하고 잘라낸후 e를 연결한후 set
		}
		
		if((empVo.getCdDepstep2()+"").replaceAll("null", "").equals("") && (empVo.getCdDepstep3()+"").replaceAll("null", "").equals("")){
			empVo.setCdDepstep2("00");
			empVo.setCdDepstep3("00");
		}else if((empVo.getCdDepstep3()+"").replaceAll("null", "").equals("")){
			empVo.setCdDepstep3("00");
		}
		
		String maxCeSort = empSvc.retrieveMaxCeSort(empVo);
		
		if(maxCeSort == null || maxCeSort.equals("")){
			maxCeSort = "01";
		}else{
			int maxValue = Integer.parseInt(maxCeSort);
			if(maxValue < 10){
				maxCeSort = '0'+maxCeSort;
			}
		}
		empVo.setCeSort(maxCeSort);
		
		empSvc.registEmp(empVo);		//등록
		
		String SVC_MSGE = Message.getMessage("201.message"); // 등록메시지
		return alert("/boffice/group/emp/Emp_List.do?selBox=" + empVo.getCeCdIdx(), SVC_MSGE, model);
	}
	
	//수정
	@RequestMapping(value= "/boffice/group/emp/Emp_Mod.do")
	public String empMod(
			HttpServletRequest request,
			@ModelAttribute("empVo") EmpVo empVo,
			ModelMap model) throws Exception {
		
		if((empVo.getCdDepstep2()+"").replaceAll("null", "").equals("") && (empVo.getCdDepstep3()+"").replaceAll("null", "").equals("")){
			empVo.setCdDepstep2("00");
			empVo.setCdDepstep3("00");
		}else if((empVo.getCdDepstep3()+"").replaceAll("null", "").equals("")){
			empVo.setCdDepstep3("00");
		}
		
		empSvc.modifyEmpDetail(empVo);		//수정
		
		String SVC_MSGE = Message.getMessage("401.message"); // 수정메시지
		return alert("/boffice/group/emp/Emp_List.do?selBox=" + empVo.getCeCdIdx(), SVC_MSGE, model);
	}
	
	//사용자정보 삭제(업데이트)
	@RequestMapping(value= "/emp/Emp_Rmv.do")
	public String empRmv(
			HttpServletRequest request,
			@ModelAttribute("empVo") EmpVo empVo,
			ModelMap model) throws Exception {
		
		empSvc.modifyEmp(empVo); 	//사용자 삭제(업데이트)
		
		String SVC_MSGE = Message.getMessage("501.message"); // 삭제메시지
		return alert("/boffice/group/emp/Emp_List.do?selBox=" + empVo.getCeCdIdx(), SVC_MSGE, model);
	}
	
	//순서 바꾸기
	@RequestMapping(value= "/emp/EmpStep_Mod.do")
	public String empStepMod(
			HttpServletRequest request,
			@ModelAttribute("empVo") EmpVo empVo,
			ModelMap model) throws Exception {
		
		if(empVo.getStep().equals("up") && !empVo.getCeSort().equals("99") && !empVo.getCeSort().equals("01")){		//위로 올릴때
			empVo.setMinUpCeSort(empSvc.retrieveMinUpCeSort(empVo));	//최소상위 ceSort 조회후 Vo에 set
			empSvc.modifyTempCeSort(empVo);	//밀려나는 값의 ceSort를 임의로(999) 변경
			
			empVo.setTempCeSort(empVo.getCeSort());	//올리기전 현재ceSort 값 Vo에 set
			
			empSvc.modifyUpCeSort(empVo);	//아래 → 위
			
			empSvc.modifyDownCeSort(empVo);	// 위 → 아래
			
			
		}else if(empVo.getStep().equals("down") && !empVo.getCeSort().equals("99")){
			if(empSvc.retrieveMaxDownCeSort(empVo) != null && !empSvc.retrieveMaxDownCeSort(empVo).equals("99")){
				empVo.setMaxDownCeSort(empSvc.retrieveMaxDownCeSort(empVo));	//최대하위 ceSort 조회후 Vo에 set
				empSvc.modifyTempCeSort(empVo);	//밀려나는 값의 ceSort를 임의로(999) 변경
				
				empVo.setTempCeSort(empVo.getCeSort());	//내리기전 현재ceSort 값 Vo에 set
				
				empSvc.modifyDownCeSort(empVo);	// 위 → 아래
				
				empSvc.modifyUpCeSort(empVo);	//아래 → 위
			}
		}
		
		String SVC_MSGE = Message.getMessage("401.message"); // 수정메시지
		//return "redirect:/boffice/group/emp/Emp_List.do?selBox=" + empVo.getCeCdIdx() + "&ceName=" + empVo.getCeName();
		return alert("/boffice/group/emp/Emp_List.do?selBox=" + empVo.getCeCdIdx() + "&ceName=" + empVo.getCeName(), SVC_MSGE, model);
	}
	
	//미사용자 미등록으로 수정
	@RequestMapping(value= "/emp/NoEmp_Mod.do")
	public String noEmpMod(
			HttpServletRequest request,
			@ModelAttribute("empVo") EmpVo empVo,
			ModelMap model) throws Exception {
		
		List<EmpVo> resultList = new ArrayList<EmpVo>();
		if(empVo.getSrchAll().equals("Y") || empVo.getSelBox().equals("d999999")){
			
			resultList = empSvc.retrieveListAllNoEmp(empVo);	//리스트 조회(전체, 미등록)
			
		}else if(empVo.getSelBox() != null && !empVo.getSelBox().equals("")){	//부서를 선택 했을경우
			
			empVo.setCdDepstep1(empVo.getSelBox().substring(0,2));
			empVo.setCdDepstep2(empVo.getSelBox().substring(2,4));
			empVo.setCdDepstep3(empVo.getSelBox().substring(4,6));
			
			resultList = empSvc.retrieveListEmp(empVo);	//리스트 조회(전체, 미등록 제외)
			
		}
		
		for(int i=0; i<resultList.size(); i++){
			if(resultList.get(i).getCeUse().equals("N")){
				empSvc.modifyNoEmp(resultList.get(i).getCeIdx());		//미사용자 미등록으로 수정
			}
		}
		
		return "redirect:/boffice/group/emp/Emp_List.do?selBox=" + empVo.getCeCdIdx();
	}
	
	//외부(세올)로 온 사용자정보 수정 or 등록
	@RequestMapping(value= "/share/emp/EmpTemp_SavList.do")
	public String empTempSavList(
			HttpServletRequest request,
			@ModelAttribute("empTempVo") EmpTempVo empTempVo,
			ModelMap model) throws Exception {
		
		String result = "fail";
		//파라미터 넘어오는값 set
		empTempVo.setUserId(EgovStringUtil.nullConvert(request.getParameter("user_id")));
		empTempVo.setUserName(EgovStringUtil.nullConvert(request.getParameter("user_name")));
		empTempVo.setSectionId(EgovStringUtil.nullConvert(request.getParameter("section_id")));
		if((request.getParameter("section_name") == null || request.getParameter("section_name").equals("")) && request.getParameter("dept_name") != null && !request.getParameter("dept_name").equals("")){
			empTempVo.setSectionName(request.getParameter("dept_name"));
		}else{
			empTempVo.setSectionName(request.getParameter("section_name"));
		}
		empTempVo.setDeptId(EgovStringUtil.nullConvert(request.getParameter("dept_id")));
		empTempVo.setDeptName(EgovStringUtil.nullConvert(request.getParameter("dept_name")));
		empTempVo.setRankName(EgovStringUtil.nullConvert(request.getParameter("rank_name")));
		if(empTempVo.getRankName() == null || empTempVo.getRankName().equals("")){
			empTempVo.setRankName("주무관");
		}
		empTempVo.setTel(EgovStringUtil.nullConvert(request.getParameter("tel")));
		empTempVo.setMobile(EgovStringUtil.nullConvert(request.getParameter("mobile")));
		empTempVo.setMail(EgovStringUtil.nullConvert(request.getParameter("mail")));
		empTempVo.setWorkDivisionContents(EgovStringUtil.nullConvert(request.getParameter("work_division_contents")));
		empTempVo.setOdr(EgovStringUtil.nullConvert(request.getParameter("odr")));
		empTempVo.setUseYn(EgovStringUtil.nullConvert(request.getParameter("use_yn")));
		empTempVo.setMarkYn(EgovStringUtil.nullConvert(request.getParameter("mark_yn")));
		empTempVo.setUserStatus(EgovStringUtil.nullConvert(request.getParameter("user_status")));
		empTempVo.setUserStatusName(EgovStringUtil.nullConvert(request.getParameter("user_status_name")));

		if(empTempVo.getRankName() == null){
			empTempVo.setRankName("주무관");
		}
		empTempVo.setUserIdx(empSvc.retrieveMaxUserIdx()+1);	//최대 userIdx 조회
		
		try{
			
			if(empTempVo.getUserId().equals("")){
				result="fail";
			}else{
				empSvc.registEmpTemp(empTempVo);		//임시직원테이블 등록
		  
				empSvc.registEmpTempOutside(empTempVo);			//임시직원테이블에 등록된 정보를 직원테이블에 update 혹은 insert
				
				result="succes";
			}
		}catch(RuntimeException e){
			result="fail";
		}catch(Exception e){
			result="fail";
		}
		if(model != null){
			model.addAttribute("result", result);
		}
		
		return GlobalConst.SITE_ROOT_BOFFICE_PATH +"/group/emp/emp_result";
	}
	
	@RequestMapping(value= "/emp/EmpTemp_test.do")	//임시 request 페이지 호출
	public String empTempTest(
			HttpServletRequest request,
			@ModelAttribute("empTempVo") EmpTempVo empTempVo,
			ModelMap model) throws Exception {
		
		return GlobalConst.SITE_ROOT_BOFFICE_PATH +"/group/emp/emp_request";
	}
	
	@RequestMapping(value= "/boffice/emp/Emp_Temp_List.do")
	public String empTempList(
			HttpServletRequest request,
			@ModelAttribute("EmpTempVo") EmpTempVo empTempVo,
			ModelMap model) throws Exception {
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(empTempVo.getPageIndex());
		
		if(!empTempVo.getListcnt().equals("")){
			empTempVo.setPageUnit(Integer.parseInt(empTempVo.getListcnt()));
		}
		
		paginationInfo.setRecordCountPerPage(empTempVo.getPageUnit());
		paginationInfo.setPageSize(empTempVo.getPageSize());
		
		empTempVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		empTempVo.setLastIndex(paginationInfo.getLastRecordIndex());
		empTempVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		
		if(empTempVo.getYear().equals("")){
			Calendar c = Calendar.getInstance(); 
			empTempVo.setYear(String.valueOf(c.get(Calendar.YEAR))); 
		}
		if(empTempVo.getInsertYn().equals("")){
			empTempVo.setInsertYn("N");
		}
		
		int totCnt = empSvc.retrievePagEmpTemp(empTempVo); 
		paginationInfo.setTotalRecordCount(totCnt);
		
		List resultList = empSvc.retrieveListEmpTemp(empTempVo);		//조직도연동 리스트 조회
		if(model != null){
			model.addAttribute("totCnt", totCnt);
			model.addAttribute("paginationInfo", paginationInfo);
			model.addAttribute("resultList", resultList);
			model.addAttribute("EmpTempVo", empTempVo);
		}
		
		return GlobalConst.SITE_ROOT_BOFFICE_PATH +"/group/emp/emp_temp_list";
	}
	
	@RequestMapping(value= "/boffice/emp/Emp_Temp_Reg.do")
	public String empTempReg(
			HttpServletRequest request,
			@ModelAttribute("EmpTempVo") EmpTempVo empTempVo,
			ModelMap model) throws Exception {
		
		String SVC_MSGE = "";
		String returnUrl = "";
		try{
			
			EmpVo empVo = new EmpVo();
			EmpTempVo resultEmpTemp = empSvc.retrieveEmpTemp(empTempVo);
			
			empSvc.registEmpTempOutside(resultEmpTemp);			//임시직원테이블에 등록된 정보를 직원테이블에 update 혹은 insert
			SVC_MSGE = Message.getMessage("201.message"); // 등록메시지
			
		}catch(RuntimeException e){
			SVC_MSGE = Message.getMessage("901.message"); // 오류메시지
		}catch(Exception e){
			SVC_MSGE = Message.getMessage("901.message"); // 오류메시지
		}
		
		return alert("/boffice/emp/Emp_Temp_List.do", SVC_MSGE, model);
	}
	
	//삭제
	@RequestMapping(value= "/boffice/emp/Emp_Temp_Rmv.do")
	public String empTempRmv(
			HttpServletRequest request,
			@ModelAttribute("EmpTempVo") EmpTempVo empTempVo,
			ModelMap model) throws Exception {
		String SVC_MSGE = "";
		
		try{
			debugLog("empTempVo : " + empTempVo.getUserIdx());
			empSvc.modifyEmpTempD(empTempVo);
			SVC_MSGE = Message.getMessage("501.message"); // 삭제메시지
		}catch(RuntimeException e){
			SVC_MSGE = Message.getMessage("901.message"); // 오류메시지
		}catch(Exception e){
			SVC_MSGE = Message.getMessage("901.message"); // 오류메시지
		}
		return alert("/boffice/emp/Emp_Temp_List.do", SVC_MSGE, model);
	}
	
	//복원
	@RequestMapping(value= "/boffice/emp/Emp_Temp_Res.do")
	public String empTempRes(
			HttpServletRequest request,
			@ModelAttribute("EmpTempVo") EmpTempVo empTempVo,
			ModelMap model) throws Exception {
		String SVC_MSGE = "";
		
		try{
			debugLog("empTempVo : " + empTempVo.getUserIdx());
			empSvc.modifyEmpTempRes(empTempVo);
			SVC_MSGE = Message.getMessage("403.message"); // 복원메시지
		}catch(RuntimeException e){
			SVC_MSGE = Message.getMessage("901.message"); // 오류메시지
		}catch(Exception e){
			SVC_MSGE = Message.getMessage("901.message"); // 오류메시지
		}
		return alert("/boffice/emp/Emp_Temp_List.do", SVC_MSGE, model);
	}
	
	@RequestMapping(value= "/boffice/emp/Emp_Temp_Regist_All.do")
	public String empTempRegistAll(
			HttpServletRequest request,
			@ModelAttribute("EmpTempVo") EmpTempVo empTempVo,
			ModelMap model) throws Exception {
		String SVC_MSGE = "";
		try{
			String resultTotal = empSvc.registEmpTempAll(empTempVo);	//일괄등록
			String[] resultArray = resultTotal.split("/");
			SVC_MSGE = "성공 "+resultArray[0]+"(등록:"+resultArray[2]+"/수정:"+resultArray[3]+")명 , 실패 "+resultArray[1]+"명 하였습니다";
		}catch(RuntimeException e){
			SVC_MSGE = Message.getMessage("901.message"); // 오류메시지
		}catch(Exception e){
			SVC_MSGE = Message.getMessage("901.message"); // 오류메시지
		}
		return alert("/boffice/emp/Emp_Temp_List.do", SVC_MSGE, model);
	}
	
	//일괄 삭제
	@RequestMapping(value= "/boffice/emp/Emp_Temp_Remove_All.do")
	public String empTempRemoveAll(
			HttpServletRequest request,
			@ModelAttribute("EmpTempVo") EmpTempVo empTempVo,
			ModelMap model) throws Exception {
		String SVC_MSGE = "";
		try{
			empSvc.removeEmpTempAll(empTempVo);	//일괄삭제
			SVC_MSGE = "일괄삭제 하였습니다";
		}catch(RuntimeException e){
			SVC_MSGE = Message.getMessage("901.message"); // 오류메시지
		}catch(Exception e){
			SVC_MSGE = Message.getMessage("901.message"); // 오류메시지
		}
		return alert("/boffice/emp/Emp_Temp_List.do", SVC_MSGE, model);
	}
	
	//일괄 복원
	@RequestMapping(value= "/boffice/emp/Emp_Temp_Restore_All.do")
	public String empTempRestoreAll(
			HttpServletRequest request,
			@ModelAttribute("EmpTempVo") EmpTempVo empTempVo,
			ModelMap model) throws Exception {
		String SVC_MSGE = "";
		try{
			empSvc.modifyEmpTempAllRestore(empTempVo);	//일괄복원
			SVC_MSGE = "일괄복원 하였습니다";
		}catch(RuntimeException e){
			SVC_MSGE = Message.getMessage("901.message"); // 오류메시지
		}catch(Exception e){
			SVC_MSGE = Message.getMessage("901.message"); // 오류메시지
		}
		return alert("/boffice/emp/Emp_Temp_List.do", SVC_MSGE, model);
	}
	
	//일괄 마크변경
	@RequestMapping(value= "/boffice/emp/Emp_Temp_Mark_All.do")
	public String empTempMarkAll(
			HttpServletRequest request,
			@ModelAttribute("EmpTempVo") EmpTempVo empTempVo,
			ModelMap model) throws Exception {
		String SVC_MSGE = "";
		try{
			empSvc.modifyEmpTempAllMark(empTempVo);	//일괄마크 변경
			SVC_MSGE = "일괄mark변경 하였습니다";
		}catch(RuntimeException e){
			SVC_MSGE = Message.getMessage("901.message"); // 오류메시지
		}catch(Exception e){
			SVC_MSGE = Message.getMessage("901.message"); // 오류메시지
		}
		return alert("/boffice/emp/Emp_Temp_List.do", SVC_MSGE, model);
	}
	
	//팝업창 띄우기
	@RequestMapping(value= "/boffice/emp/EmpTemp_Pop.do")
	public String empTempPop(
			HttpServletRequest request,
			@ModelAttribute("EmpTempVo") EmpTempVo empTempVo,
			ModelMap model) throws Exception {
		
		EmpTempVo result = empSvc.retrieveEmpTempDetail(empTempVo.getUserIdx()); //직원조회
		if(model != null){
			model.addAttribute("EmpTempVo", result);
		}
		debugLog("asdfasdfasd : " + request.getParameter("message"));
		if(request.getParameter("message") != null){
			if(model != null){
				model.addAttribute("message", request.getParameter("message"));
			}
		}
		return GlobalConst.SITE_ROOT_BOFFICE_PATH +"/group/emp/emp_temp_pop";
	}
	
	//팝업창 띄우기
	@RequestMapping(value= "/boffice/emp/Modify_EmpTemp.do")
	public String modifyEmpTemp(
			HttpServletRequest request,
			@ModelAttribute("EmpTempVo") EmpTempVo empTempVo,
			ModelMap model) throws Exception {
		
		String SVC_MSGE = "";
		try{
			
			empSvc.modifyEmpTemp(empTempVo);
			SVC_MSGE = Message.getMessage("401.message"); // 수정메시지
		}catch(RuntimeException e){
			SVC_MSGE = Message.getMessage("901.message"); // 오류메시지
		}catch(Exception e){
			SVC_MSGE = Message.getMessage("901.message"); // 오류메시지
		}
		
		return alert("/boffice/emp/EmpTemp_Pop.do?userIdx="+empTempVo.getUserIdx()+"&message=update", SVC_MSGE, model);
	}
	
}