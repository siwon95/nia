package egovframework.injeinc.foffice.ex.dept.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import egovframework.cmm.CmmLogCtr;
import egovframework.injeinc.boffice.group.dept.vo.GroupDeptVo;
import egovframework.injeinc.boffice.group.emp.vo.EmpVo;
import egovframework.injeinc.foffice.ex.dept.service.DeptFSvc;

import javax.annotation.Resource;
@Controller
public class DeptFCtr extends CmmLogCtr{
		
		@Resource(name = "DeptFSvc")
		private DeptFSvc deptFSvc;
		
		@Autowired(required=true)
		private MappingJackson2JsonView jsonView;
		
		/** 부서 리스트*/
		@RequestMapping(value= "/{strSitePath}/{strDomain}/ex/dept/Dept_List.do")
		public String chasupanList(
				@PathVariable("strSitePath") String strSitePath,
				@PathVariable("strDomain") String strDomain,
				HttpServletRequest request, @ModelAttribute("GroupDeptVo") GroupDeptVo groupDeptVo
				, ModelMap model ) throws Exception {
			List departList = deptFSvc.retrieveListDepartment();	//부서 리스트 조회
			if(groupDeptVo.getCdDepstep().equals("")){
				groupDeptVo.setCdDepstep("010000");
			}
			groupDeptVo.setCdDepstep1(groupDeptVo.getCdDepstep().substring(0, 2));		//국 set
			
			groupDeptVo.setCdDepstep2(groupDeptVo.getCdDepstep().substring(2,4));		//과 set
			
			groupDeptVo.setCdDepstep3(groupDeptVo.getCdDepstep().substring(4, 6));		//팀 set
			
			List empList = deptFSvc.retrieveListEmp(groupDeptVo);			//지원리스트 조회
			
			GroupDeptVo groupDept = new GroupDeptVo();
			groupDept = deptFSvc.retrieveDepartment(groupDeptVo);		//부서 상세 조회
			if(model != null){
				model.addAttribute("empList", empList);
				model.addAttribute("groupDept", groupDept);
				model.addAttribute("departList", departList);
				model.addAttribute("strDomain", strDomain);
			}
			return "injeinc/foffice/ex/dept/dept_list";		
	 	}
		
		/** 부서 리스트*/
		@RequestMapping(value= "/{strSitePath}/{strDomain}/ex/dept/org_map.do")
		public String orgMap(
				@PathVariable("strSitePath") String strSitePath,
				@PathVariable("strDomain") String strDomain,
				HttpServletRequest request, @ModelAttribute("GroupDeptVo") GroupDeptVo groupDeptVo
				, ModelMap model ) throws Exception {
			
			List orgMap = deptFSvc.selectOrgMap();	//조직도 리스트 조회
			
			model.addAttribute("siteCd", strDomain);
			model.addAttribute("orgMap", orgMap);
			
			return "injeinc/foffice/ex/dept/org_map";		
	 	}
		
}