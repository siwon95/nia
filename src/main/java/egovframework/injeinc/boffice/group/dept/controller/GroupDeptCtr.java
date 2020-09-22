package egovframework.injeinc.boffice.group.dept.controller;

import java.util.HashMap;
import java.util.List;

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
import egovframework.cmm.GlobalConst;
import egovframework.cmm.Message;
import egovframework.injeinc.boffice.group.dept.service.GroupDeptSvc;
import egovframework.injeinc.boffice.group.dept.vo.GroupDeptVo;
import egovframework.injeinc.boffice.group.emp.service.EmpSvc;
import egovframework.injeinc.boffice.group.emp.vo.EmpVo;
import egovframework.injeinc.common.util.EgovStringUtil;

@Controller
public class GroupDeptCtr extends CmmLogCtr{
	
	@Resource(name = "GroupDeptSvc")
	private GroupDeptSvc groupDeptSvc;
	
	@Resource(name = "EmpSvc")
	private EmpSvc empSvc;
	
	@Autowired(required=true)
	private MappingJackson2JsonView jsonView;
	
	//리스트
	@RequestMapping(value= "/boffice/group/dept/GroupDept_List.do")
	public String groupDeptList(
			HttpServletRequest request,
			@ModelAttribute("groupDeptVo") GroupDeptVo groupDeptVo,
			@ModelAttribute("empVo") EmpVo empVo,
			ModelMap model) throws Exception {
		
		HttpSession session = request.getSession();
		
		String selBox = request.getParameter("selBox");
		
		if(selBox == null){
			selBox = "";
		}
		
		String[] selBoxArr = selBox.split("\\/");
		
		if(selBoxArr.length > 4){
			groupDeptVo.setCdIdx(selBoxArr[4]);
		}
		
		String SesUserPermCd = session.getAttribute("SesUserPermCd").toString();
		if(!SesUserPermCd.equals("05010000")){
			empVo.setMode("department_admin");
			empVo.setCdIdx(session.getAttribute("SesUserDeptCd").toString());
		}
		
		List<EmpVo> selectList  = empSvc.retrieveListSbEmp(empVo);
		
		groupDeptVo = groupDeptSvc.retrieveGroupDept(groupDeptVo); //상세
		
		model.addAttribute("selectList", selectList);
		
		if(groupDeptVo == null){
			groupDeptVo = new GroupDeptVo();
		}
		
		model.addAttribute("groupDeptVo", groupDeptVo);
		
		return GlobalConst.SITE_ROOT_BOFFICE_PATH +"/group/dept/group_departmen_list";
	}
	
	//리스트(레이아웃 X)
	@RequestMapping(value= "/boffice_noDeco/group/dept/GroupDept_List.do")
	public String groupDeptListNo(
			HttpServletRequest request,
			@ModelAttribute("groupDeptVo") GroupDeptVo groupDeptVo,
			@ModelAttribute("empVo") EmpVo empVo,
			ModelMap model) throws Exception {
		
		HttpSession session = request.getSession();
		
		String SesUserPermCd = session.getAttribute("SesUserPermCd").toString();
		if(!SesUserPermCd.equals("05010000")){
			empVo.setMode("department_admin");
			empVo.setCdIdx(session.getAttribute("SesUserDeptCd").toString());
		}
		
		List<EmpVo> selectList  = empSvc.retrieveListSbEmp(empVo);
		
		model.addAttribute("selectList", selectList);
		
		return GlobalConst.SITE_ROOT_BOFFICE_PATH +"/group/dept/group_departmen_list";
	}
		
	//서브 리스트
	@RequestMapping(value= "/boffice_noDeco/group/dept/GroupDeptSub_List.do")
	public String groupDeptSubList(
			HttpServletRequest request,
			@ModelAttribute("groupDeptVo") GroupDeptVo groupDeptVo,
			ModelMap model) throws Exception {
		
		HttpSession ses = request.getSession();
		int frame = Integer.parseInt(groupDeptVo.getSubframe());
		
		if(frame == 1){
			groupDeptVo.setCdDepstep1("");
			groupDeptVo.setCdDepstep2("");
			groupDeptVo.setCdDepstep3("");
		}else if(frame == 2){
			groupDeptVo.setCdDepstep1(groupDeptVo.getCdCode().substring(1, 3));
			groupDeptVo.setCdDepstep2("");
			groupDeptVo.setCdDepstep3("");
		}else if(frame == 3){
			groupDeptVo.setCdDepstep1(groupDeptVo.getCdCode().substring(1, 3));
			groupDeptVo.setCdDepstep2(groupDeptVo.getCdCode().substring(3, 5));
			groupDeptVo.setCdDepstep3("");
		}

	
		String SesUserPermCd = ses.getAttribute("SesUserPermCd").toString();
		if(!SesUserPermCd.equals("05010000")){
			groupDeptVo.setMode("department_admin");
			groupDeptVo.setCdIdx(ses.getAttribute("SesUserDeptCd").toString());
		}
		List retrieveListGroupDeptSub= groupDeptSvc.retrieveListGroupDeptSub(groupDeptVo); //리스트 조회
		if(model != null){
			model.addAttribute("resultList", retrieveListGroupDeptSub);
		}
		return GlobalConst.SITE_ROOT_BOFFICE_PATH +"/group/dept/group_dept_sub_list"; 
	}
	
	//등록
	@RequestMapping(value= "/boffice/group/dept/GroupDept_Reg.do")
	public String groupDeptReg(
			HttpServletRequest request,
			@ModelAttribute("groupDeptVo") GroupDeptVo groupDeptVo,
			ModelMap model
			) throws Exception {
		
		String maxCdIdx = groupDeptSvc.retrieveMaxCdIdx(groupDeptVo); 	// 최대 cdIdx 조회
		maxCdIdx = maxCdIdx.replace("D", "1"); 		//조회된 cdIdx의 d를 임시로 숫자1로 변환 (ex: d020001 → 1020001)
		long newCdIdx = Long.parseLong(maxCdIdx) + 1; 		//숫자형으로 변환하여 1을 더함 (ex: 1020001 + 1)
		
		String subframe = EgovStringUtil.nullConvert(request.getParameter("subframe"));
		
		groupDeptVo.setCdIdx(Long.toString(newCdIdx).substring(1, Long.toString(newCdIdx).length())); // 임시로 변환한 1을 제외하고 잘라냄
		groupDeptVo.setSubframe(subframe);
		
		groupDeptSvc.registGroupDept(groupDeptVo);	//등록
		
		groupDeptVo.setCdIdx("D" + Long.toString(newCdIdx).substring(1, Long.toString(newCdIdx).length()));
		
		groupDeptVo = groupDeptSvc.retrieveGroupDept(groupDeptVo);
		
		String selBox = groupDeptVo.getCdDepstep1() + '/' + groupDeptVo.getCdDepstep2() + '/' + groupDeptVo.getCdDepstep3() + '/' +  subframe + '/' +  groupDeptVo.getCdIdx(); 
		
		//return "redirect:/boffice/group/dept/GroupDept_List.do?selBox=" + selBox; 
		String SVC_MSGE = Message.getMessage("201.message");
		return alert("/boffice/group/dept/GroupDept_List.do?selBox=" + selBox, SVC_MSGE, model);
	}
		
	//수정(체크,순서교체)
	@RequestMapping(value= "/boffice/group/dept/GroupDept_Mod.do")
	public String groupDeptMod(
			HttpServletRequest request,
			@ModelAttribute("groupDeptVo") GroupDeptVo groupDeptVo,
			ModelMap model
			) throws Exception {
		
		groupDeptVo.setMod(EgovStringUtil.nullConvert(request.getParameter("mod")));
		
		if(EgovStringUtil.nullConvert(request.getParameter("mod")).equals("up")){ //순서 바꾸기(위)
			
			groupDeptVo.setTempMaxCdDepstep(groupDeptSvc.retrieveMaxCdDepStep(groupDeptVo)); //최소상위코드select 하여 임시코드에 set
			groupDeptSvc.modiftyTempCdDepStepList(groupDeptVo); //밀려나는 번호를 임의값으로 수정
			
			if(groupDeptVo.getSubframe().equals("1")){
				groupDeptVo.setTempMinCdDepstep(groupDeptVo.getCdDepstep1()); //위로 올리기전 아래번호 담기
			}else if(groupDeptVo.getSubframe().equals("2")){
				groupDeptVo.setTempMinCdDepstep(groupDeptVo.getCdDepstep2()); //위로 올리기전 아래번호 담기
			}else if(groupDeptVo.getSubframe().equals("3")){
				groupDeptVo.setTempMinCdDepstep(groupDeptVo.getCdDepstep3()); //위로 올리기전 아래번호 담기
			}
			
			groupDeptSvc.modiftyCdDepStepList(groupDeptVo); // 아래번호 → 위번호 수정
			
			groupDeptSvc.modiftyCdDepStep2List(groupDeptVo); // 위번호 → 아래번호 수정
			
			groupDeptSvc.modiftyCdCodeList(groupDeptVo); //cdCode 조합하기
				
		}else if(EgovStringUtil.nullConvert(request.getParameter("mod")).equals("down")){ //순서 바꾸기(아래)
			
			groupDeptVo.setTempMinCdDepstep(groupDeptSvc.retrieveMinCdDepStep(groupDeptVo)); //최대하위코드select 하여 임시코드에 set
			groupDeptSvc.modiftyTempCdDepStepList(groupDeptVo); //밀려나는 번호를 임의값으로 수정
			
			if(groupDeptVo.getSubframe().equals("1")){
				groupDeptVo.setTempMaxCdDepstep(groupDeptVo.getCdDepstep1()); //아래로 내리기전 위번호 담기
			}else if(groupDeptVo.getSubframe().equals("2")){
				groupDeptVo.setTempMaxCdDepstep(groupDeptVo.getCdDepstep2()); //아래로 내리기전 위번호 담기
			}else if(groupDeptVo.getSubframe().equals("3")){
				groupDeptVo.setTempMaxCdDepstep(groupDeptVo.getCdDepstep3()); //아래로 내리기전 위번호 담기
			}
			
			groupDeptSvc.modiftyCdDepStepList(groupDeptVo); // 위번호 → 아래번호 수정
			
			groupDeptSvc.modiftyCdDepStep2List(groupDeptVo); // 아래번호 → 위번호 수정
			
			groupDeptSvc.modiftyCdCodeList(groupDeptVo); //cdCode 조합하기
			
		}else if(EgovStringUtil.nullConvert(request.getParameter("mod")).equals("chk")){ //체크or미체크 시 수정
			
			groupDeptSvc.mofityGroupDept(groupDeptVo);
		}
		
		return "redirect:/boffice/group/dept/GroupDept_List.do";
		
	}
	
	//수정(이름)
	@RequestMapping(value= "/boffice/group/dept/GroupDeptSub_Mod.do")
	public String groupDeptSubMod(
			HttpServletRequest request,
			@ModelAttribute("groupDeptVo") GroupDeptVo groupDeptVo,
			ModelMap model
			) throws Exception {
		
		groupDeptVo.setSubframe(EgovStringUtil.nullConvert(request.getParameter("subframe")));
		groupDeptSvc.mofityGroupDeptSub(groupDeptVo); //이름 수정
		
		groupDeptSvc.mofityGroupDeptStep(groupDeptVo); //직원 테이블 depstep 변경
		
		return "redirect:/boffice/group/dept/GroupDept_List.do";
	}
		
	//삭제
	@RequestMapping(value= "/boffice/group/dept/GroupDept_Rmv.do")
	public String groupDeptRmv(
			HttpServletRequest request,
			@ModelAttribute("groupDeptVo") GroupDeptVo groupDeptVo,
			ModelMap model
			) throws Exception {
		
		groupDeptSvc.removeGroupDept(groupDeptVo); //삭제
		
		//return "redirect:/boffice/group/dept/GroupDept_List.do?cdCode="+cdCode+"&cdCode2="+cdCode2+"&cdCode3="+cdCode3;
		return "redirect:/boffice/group/dept/GroupDept_List.do";
	}
	
	//상세보기
	@RequestMapping(value= "/boffice_noDeco/group/dept/GroupDept_View.do")
	public String groupDeptView(
			HttpServletRequest request,
			@ModelAttribute("groupDeptVo") GroupDeptVo groupDeptVo,
			ModelMap model
			) throws Exception {
		GroupDeptVo groupDeptView = groupDeptSvc.retrieveGroupDept(groupDeptVo); //상세
		if(model != null){
			model.addAttribute("groupDeptVo", groupDeptView);
		}
		return GlobalConst.SITE_ROOT_BOFFICE_PATH +"/group/dept/group_dept_view";
	}
	
	//상세보기 수정
	@RequestMapping(value= "/boffice/group/dept/GroupDeptDetail_Mod.do")
	public String groupDeptDetailMod(
			HttpServletRequest request,
			@ModelAttribute("groupDeptVo") GroupDeptVo groupDeptVo,
			ModelMap model
			) throws Exception {
		
		groupDeptSvc.modifyGroupDeptDetail(groupDeptVo); //수정
		
		String selBox = groupDeptVo.getCdDepstep1() + "/" + groupDeptVo.getCdDepstep2()  + "/" + groupDeptVo.getCdDepstep3() + "/" + groupDeptVo.getPosition() + "/" + groupDeptVo.getCdIdx();
		
		String SVC_MSGE = Message.getMessage("401.message"); //수정
		return alert("/boffice/group/dept/GroupDept_List.do?selBox=" + selBox, SVC_MSGE, model);
	}
	
	
	@RequestMapping(value= "/boffice/group/dept/deptSelectAx.do")
	public void deptSelectAx(
			HttpServletRequest request, 
			HttpServletResponse response, 
			@ModelAttribute("groupDeptVo") GroupDeptVo groupDeptVo) throws Exception {
		
		GroupDeptVo groupDeptView = groupDeptSvc.retrieveGroupDept(groupDeptVo); //상세

		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("groupDeptView", groupDeptView);
		jsonView.render(jsonMap, request, response);
			
	}
}