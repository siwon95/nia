package egovframework.injeinc.foffice.ex.emp.controller;

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
import egovframework.injeinc.boffice.group.emp.vo.EmpVo;
import egovframework.injeinc.foffice.ex.emp.service.EmpFSvc;

import javax.annotation.Resource;
@Controller
public class EmpFCtr extends CmmLogCtr{
		
		@Resource(name = "EmpFSvc")
		private EmpFSvc empFSvc;
		
		@Autowired(required=true)
		private MappingJackson2JsonView jsonView;

		/** 직원 리스트*/
		@RequestMapping(value= "/site/{strDomain}/ex/emp/Emp_List.do")
		public String empList(
				@PathVariable("strDomain") String strDomain,
				HttpServletRequest request, @ModelAttribute("EmpVo") EmpVo empVo
				, ModelMap model ) throws Exception {
			
			List departList = empFSvc.retrieveListDepartment();
			if(empVo.getCdIdx() == null || empVo.getCdIdx().equals("")){
				empVo.setCdIdx("all");
			}
			if(!empVo.getCdIdx().equals("")){
				if(!empVo.getCdIdx().equals("all")){
					EmpVo emp = empFSvc.retrieveCdCode(empVo);
					//empVo.setCdDepstep2(emp.getCdDepstep2());
					if(emp.getCdSubject() != null && !emp.getCdSubject().equals("")){
						empVo.setCdSubject(emp.getCdSubject().replace("OK", "오-케이"));
					}					
					System.out.println("dept:"+emp.getCdDepstep1()+emp.getCdDepstep2()+emp.getCdDepstep3());
					empVo.setCdDepstep(emp.getCdDepstep1()+emp.getCdDepstep2()+emp.getCdDepstep3());		//국 set
					empVo.setCdDepstep1(emp.getCdDepstep1());		//국 set
					empVo.setCdDepstep2(emp.getCdDepstep2());		//과 set
					empVo.setCdDepstep3(emp.getCdDepstep3());		//팀 set
				}
				
				
				
				
				
				
				List empList = empFSvc.retrieveListEmp(empVo);
				if(model != null){
					model.addAttribute("empList", empList);
				}
			}
			if(model != null){
				model.addAttribute("EmpVo", empVo);
				model.addAttribute("departList", departList);
				model.addAttribute("strDomain", strDomain);
			}
			
			return "injeinc/foffice/ex/emp/emp_list";		
	 	}
		
		/** 직원 리스트 팝업*/
		@RequestMapping(value= "/site/{strDomain}/ex/emp/Emp_List_Pop.do")
		public String empListPop(
				@PathVariable("strDomain") String strDomain,
				HttpServletRequest request, @ModelAttribute("EmpVo") EmpVo empVo
				, ModelMap model ) throws Exception {
			
			
			return "injeinc/foffice/ex/emp/emp_list";		
	 	}
		
}