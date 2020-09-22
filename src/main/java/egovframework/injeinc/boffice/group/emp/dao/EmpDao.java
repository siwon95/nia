package egovframework.injeinc.boffice.group.emp.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.injeinc.boffice.group.dept.vo.GroupDeptVo;
import egovframework.injeinc.boffice.group.emp.vo.EmpTempVo;
import egovframework.injeinc.boffice.group.emp.vo.EmpVo;
import egovframework.injeinc.common.util.DateUtil;

@Repository("EmpDao") 
public class EmpDao extends EgovAbstractMapper {
	/** 셀렉트박스 리스트 조회 */
	public List selectListSbEmp(EmpVo empVo) throws Exception {
		return selectList("EmpDao.selectListSbEmp", empVo);
	}
	/** 리스트 조회(전체, 미등록제외) */
	public List selectListEmp(EmpVo empVo) throws Exception {
		return selectList("EmpDao.selectListEmp", empVo);
	}
	/** 리스트 조회(전체, 미등록) */
	public List selectListAllNoEmp(EmpVo empVo) throws Exception {
		return selectList("EmpDao.selectListAllNoEmp", empVo);
	}
	/** 리스트 총건수(전체, 미등록제외) */
	public int selectAllEmpListTotCnt(EmpVo empVo) throws Exception {
		return (Integer)selectOne("EmpDao.selectAllEmpListTotCnt", empVo);
	}
	/** 리스트 총건수(전체, 미등록) */
	public int selectEmpListTotCnt(EmpVo empVo) throws Exception {
		return (Integer)selectOne("EmpDao.selectEmpListTotCnt", empVo);
	}
	/** 사용여부 수정 */
	public void updateUseCheck(EmpVo empVo) throws Exception {
		update("EmpDao.updateUseCheck", empVo);
	}
	/** 셀렉트박스1(국코드,국명 조회) */
	public List selectListDepstep1() throws Exception {
		return selectList("EmpDao.selectListDepstep1", null);
	}
	/** 셀렉트박스2(과코드,과명 조회) */
	public List selectListDepstep2(HashMap<String, String> param) throws Exception {
		return selectList("EmpDao.selectListDepstep2", param);
	}
	/** 셀렉트박스3(팀코드,팀명 조회) */
	public List selectListDepstep3(HashMap<String, String> param) throws Exception {
		return selectList("EmpDao.selectListDepstep3", param);
	}
	/** 등록 */
	public void insertEmp(EmpVo empVo) throws Exception {
		insert("EmpDao.insertEmp", empVo);
	}
	/** 등록 */
	public void insertEmp2(EmpTempVo empTempVo) throws Exception {
		insert("EmpDao.insertEmp2", empTempVo);
	}
	/** ceIdx 최대값 조회 */
	public String selectMaxCeIdx() throws Exception {
		return (String)selectOne("EmpDao.selectMaxCeIdx", null);
	}
	/** cdIdx 조회 */
	public String selectCdIdx(EmpVo empVo) throws Exception {
		return (String)selectOne("EmpDao.selectCdIdx", empVo);
	}
	/** ceSort 최대값 조회 */
	public String selectMaxCeSort(EmpVo empVo) throws Exception {
		return (String)selectOne("EmpDao.selectMaxCeSort", empVo);
	}
	
	/** ceSort 최대값 조회 */
	public EmpVo selectEmp(EmpVo empVo) throws Exception {
		return (EmpVo)selectOne("EmpDao.selectEmp", empVo);
	}
	
	/**  사용자 삭제(업데이트) */
	public void updateEmp(EmpVo empVo) throws Exception {
		update("EmpDao.updateEmp", empVo);
	}
	
	/**  사용자 수정 */
	public void updateEmpDetail(EmpVo empVo) throws Exception {
		update("EmpDao.updateEmpDetail", empVo);
	}
	
	/**  사용자 수정 */
	public void updateEmpDetail(EmpTempVo empTempVo) throws Exception {
		update("EmpDao.updateEmpDetail2", empTempVo);
	}
	
	/**  최소상위 ceSort 조회 */
	public String selectMinUpCeSort(EmpVo empVo) throws Exception {
		return (String)selectOne("EmpDao.selectMinUpCeSort", empVo);
	}
	
	/**  최대하위 ceSort 조회 */
	public String selectMaxDownCeSort(EmpVo empVo) throws Exception {
		return (String)selectOne("EmpDao.selectMaxDownCeSort", empVo);
	}
	
	/**  임의값으로 수정 */
	public void updateTempCeSort(EmpVo empVo) throws Exception {
		update("EmpDao.updateTempCeSort", empVo);
	}
	
	/** 아래 → 위 수정 */
	public void updateUpCeSort(EmpVo empVo) throws Exception {
		update("EmpDao.updateUpCeSort", empVo);
	}
	
	/**  위 → 아래 수정 */
	public void updateDownCeSort(EmpVo empVo) throws Exception {
		update("EmpDao.updateDownCeSort", empVo);
	}
	
	/**  미사용자 미등록으로 수정 */
	public void updateNoEmp(String ceIdx) throws Exception {
		update("EmpDao.updateNoEmp", ceIdx);
	}
	
	/**  미사용자 미등록으로 수정 */
	public int selectMaxUserIdx() throws Exception {
		return (Integer)selectOne("EmpDao.selectMaxUserIdx", null);
	}
	
	/**  EmpTemp 등록 */
	public void insertEmpTemp(EmpTempVo EmpTempVO) throws Exception {
		insert("EmpDao.insertEmpTemp", EmpTempVO);
	}
	
	/** Emp 등록 */
	public void insertEmpTemp(HashMap<String, String> param) throws Exception {
		
		boolean insertResult = false;
		
		EmpTempVo empTempVo = new EmpTempVo();
		if(param != null){
			empTempVo.setUserIdx(Integer.parseInt(param.get("userIdx")));
		}
		
		empTempVo = (EmpTempVo)selectOne("EmpDao.selectEmpTemp", empTempVo);	// empTemp 조회
		
		if(empTempVo != null){
			
			HashMap<String, Object> paramSet = new HashMap<String, Object>();
			
			paramSet.put("ceIdx", empTempVo.getUserId());
			paramSet.put("ceUpdate", DateUtil.getCurrentDatetime());
			
			if(empTempVo.getOdr().length() == 1){
				empTempVo.setSort("0"+empTempVo.getOdr());
			}else{
				empTempVo.setSort(empTempVo.getOdr());
			}
			
			paramSet.put("ceName",empTempVo.getUserName());
			paramSet.put("ceMailid",empTempVo.getMail());
			paramSet.put("ceTel",empTempVo.getTel());
			paramSet.put("ceHp",empTempVo.getMobile());
			    
			if(empTempVo.getMarkYn().equals("N") || empTempVo.getUseYn().equals("0") || !(empTempVo.getUserStatus().equals("AAA") || empTempVo.getUserStatus().equals("AAB"))){
				paramSet.put("ceSort","99");
				paramSet.put("ceUse","N");
			}else{
				paramSet.put("ceSort",empTempVo.getSort());
				paramSet.put("ceUse","Y");
			}
			
			paramSet.put("ceDepstep2",empTempVo.getDeptName());
		    paramSet.put("ceDepstep4",empTempVo.getRankName());
		    paramSet.put("ceWorks",empTempVo.getWorkDivisionContents());
		    paramSet.put("ceDuty",Integer.toString(empTempVo.getUserIdx()));
			
		    
			if(empTempVo.getDeptName().equals("오-케이민원센터")){
				paramSet.put("ceDepstep2","OK민원센터");
			}
			
			String sectionTemp=empTempVo.getSectionName();
			if(sectionTemp.equals("")){
				paramSet.put("ceDepstep3",empTempVo.getDeptName());
			}else{
				paramSet.put("ceDepstep3",empTempVo.getSectionName());
			}		
			
			GroupDeptVo result = (GroupDeptVo)selectOne("GroupDeptDao.selectDepartment", paramSet);	// 부서명 조회
			
			if(result != null){
				paramSet.put("ceDepstep1",result.getDvDepth1());
				paramSet.put("ceCdIdx",result.getDvIdxdepth3());
				
				int sortYn = (Integer)selectOne("EmpDao.selectSortYn", paramSet);//해당 부서의 순서 유무 조회
				
				if(sortYn > 0){
					if(empTempVo.getMarkYn().equals("Y") && empTempVo.getUseYn().equals("1") && (empTempVo.getUserStatus().equals("AAA") || empTempVo.getUserStatus().equals("AAB"))){
						update("EmpDao.updateSort",paramSet);	//직원정보(insert,update) 순서(번호) 들어오기전 순서 변경
					}
				}
				
				int userCnt = (Integer)selectOne("EmpDao.selectCeIdx", empTempVo);
				
				if(userCnt>0){
					update("EmpDao.updateEmpUser",paramSet);	//직원정보 수정
				}else{
					update("EmpDao.insertEmpUser",paramSet);	//직원 등록
				}
				insertResult = true;
			}
			
			if(insertResult){
				update("EmpDao.updateEmpTempYn",empTempVo.getUserIdx());	//등록여부 수정
			}
		}
	}
	
	/**  직원테이블에 해당userId 유무 조회 */
	public int selectCeIdx(EmpTempVo EmpTempVO) throws Exception {
		return (Integer)selectOne("EmpDao.selectCeIdx", EmpTempVO);
	}
	
	/**  뷰테이블에 해당부서 조회 */
	public EmpTempVo selectEmpView(EmpVo EmpVO) throws Exception {
		return (EmpTempVo)selectOne("EmpDao.selectEmpView", EmpVO);
	}
	
	/** 조직도연동 리스트 */
	public List selectListEmpTemp(EmpTempVo EmpTempVO) throws Exception {
		return selectList("EmpDao.selectListEmpTemp", EmpTempVO);
	}
	
	/** 조직도연동 페이징 */
	public int selectPagEmpTemp(EmpTempVo EmpTempVO) throws Exception {
		return (Integer)selectOne("EmpDao.selectPagEmpTemp", EmpTempVO);
	}
	
	/** EmpTemt 조회*/
	public EmpTempVo selectEmpTemp(EmpTempVo EmpTempVO) throws Exception {
		return (EmpTempVo)selectOne("EmpDao.selectEmpTemp", EmpTempVO);
	}
	
	/** 해당 부서 회원 조회*/
	public EmpVo selectDeptEmp(EmpTempVo EmpTempVO) throws Exception {
		return (EmpVo)selectOne("EmpDao.selectDeptEmp", EmpTempVO);
	}
	
	/** temp 등록여부 수정*/
	public void updateEmpTempInsertYn(EmpTempVo empTempVO) throws Exception {
		int userIdx = 0;
		if(empTempVO != null){
			userIdx = empTempVO.getUserIdx();
		}
		update("EmpDao.updateEmpTempYn", userIdx);
	}
	
	/** temp 등록여부 수정(삭제 누를시)*/
	public void updateEmpTempD(EmpTempVo empTempVO) throws Exception {
		int userIdx = 0;
		if(empTempVO != null){
			userIdx = empTempVO.getUserIdx();
		}
		update("EmpDao.updateEmpTempD", userIdx);
	}
	
	/** temp 등록여부 수정(복원 누를시)*/
	public void updateEmpTempRes(EmpTempVo empTempVO) throws Exception {
		int userIdx = 0;
		if(empTempVO != null){
			userIdx = empTempVO.getUserIdx();
		}
		update("EmpDao.updateEmpTempYn2", userIdx);
	}
	
	/** EmpTemp 일괄등록*/
	public String insertEmpTempAll(HashMap<String, String> param) throws Exception {
		
		Boolean insertResult = false; 	 
	    Boolean updateResult = false;
	    String insertOK = "No";
		String resultMap = "";
		
		EmpTempVo empTempVo = new EmpTempVo();
		if(param != null){
			empTempVo.setUserIdx(Integer.parseInt(param.get("userIdx")));
		}
		
		empTempVo = (EmpTempVo)selectOne("EmpDao.selectEmpTemp", empTempVo);	// empTemp 조회
		
		if(empTempVo != null){
			
			HashMap<String, Object> paramSet = new HashMap<String, Object>();
			
			paramSet.put("ceIdx", empTempVo.getUserId());
			paramSet.put("ceUpdate", DateUtil.getCurrentDatetime());
			
			if(empTempVo.getOdr().length() == 1){
				empTempVo.setSort("0"+empTempVo.getOdr());
			}
			
			paramSet.put("ceName",empTempVo.getUserName());
			paramSet.put("ceMailid",empTempVo.getMail());
			paramSet.put("ceTel",empTempVo.getTel());
			paramSet.put("ceHp",empTempVo.getMobile());
			    
			if(empTempVo.getMarkYn().equals("N") || empTempVo.getUseYn().equals("0") || !(empTempVo.getUserStatus().equals("AAA") || empTempVo.getUserStatus().equals("AAB"))){
				paramSet.put("ceSort","99");
				paramSet.put("ceUse","N");
			}else{
				paramSet.put("ceSort",empTempVo.getSort());
				paramSet.put("ceUse","Y");
			}
			
			paramSet.put("ceDepstep2",empTempVo.getDeptName());
			if(empTempVo.getRankName() == null || empTempVo.getRankName().equals("")){
				empTempVo.setRankName("주무관");
			}
		    paramSet.put("ceDepstep4",empTempVo.getRankName());
		    paramSet.put("ceWorks",empTempVo.getWorkDivisionContents());
		    paramSet.put("ceDuty",Integer.toString(empTempVo.getUserIdx()));
			
		    
			if(empTempVo.getDeptName().equals("오-케이민원센터")){
				paramSet.put("ceDepstep2","OK민원센터");
			}
			
			String sectionTemp=empTempVo.getSectionName();
			if(sectionTemp.equals("")){
				paramSet.put("ceDepstep3",empTempVo.getDeptName());
			}else{
				paramSet.put("ceDepstep3",empTempVo.getSectionName());
			}		
			
			GroupDeptVo result = (GroupDeptVo)selectOne("GroupDeptDao.selectDepartment", paramSet);	// 부서명 조회
			
			if(result != null){
				paramSet.put("ceDepstep1",result.getDvDepth1());
				paramSet.put("ceCdIdx",result.getDvIdxdepth3());
				
				int sortYn = (Integer)selectOne("EmpDao.selectSortYn", paramSet);//해당 부서의 순서 유무 조회
				
				if(sortYn > 0){
					if(empTempVo.getMarkYn().equals("Y") && empTempVo.getUseYn().equals("1") && (empTempVo.getUserStatus().equals("AAA") || empTempVo.getUserStatus().equals("AAB"))){
						update("EmpDao.updateSort",paramSet);	//직원정보(insert,update) 순서(번호) 들어오기전 순서 변경
					}
				}
				
				int userCnt = (Integer)selectOne("EmpDao.selectCeIdx", empTempVo);
				
				if(userCnt>0){
					update("EmpDao.updateEmpUser",paramSet);	//직원정보 수정
					insertOK = "update";
				}else{
					update("EmpDao.insertEmpUser",paramSet);	//직원 등록
					insertOK = "insert";
				}
				insertResult = true;
			}
			
			if(insertResult){
				update("EmpDao.updateEmpTempYn",empTempVo.getUserIdx());	//등록여부 수정
			}
		}
		resultMap = insertResult+"/"+insertOK;
		return resultMap;
	}
	
	/** EmpTemp 일괄삭제*/
	public void deleteEmpTempAll(HashMap<String, String> param) throws Exception {
		int userIdx = 0;
		if(param != null){
			userIdx = Integer.parseInt(param.get("userIdx"));
		}
		update("EmpDao.updateEmpTempD", userIdx);	//등록여부 수정
	}
	
	/** EmpTemp 일괄복원*/
	public void updateEmpTempAllRestore(HashMap<String, String> param) throws Exception {
		int userIdx = 0;
		if(param != null){
			userIdx = Integer.parseInt(param.get("userIdx"));
		}
		update("EmpDao.updateEmpTempYn2", userIdx);	//등록여부 수정(N)
	}
	
	/** EmpTemp 일괄 마크 변경*/
	public void updateEmpTempAllMark(HashMap<String, String> param) throws Exception {
		int userIdx = 0;
		if(param != null){
			userIdx = Integer.parseInt(param.get("userIdx"));
		}
		update("EmpDao.updateEmpTempAllMark", userIdx);	//등록여부 수정(N)
	}
	
	/** EmpTemp 수정*/
	public void updateEmpTemp(EmpTempVo empTempVo) throws Exception {
		update("EmpDao.updateEmpTemp",empTempVo);	//등록여부 수정(N)
	}
	
	/** EmpTemp 상세조회*/
	public EmpTempVo selectEmpTempDetail(int userIdx) throws Exception {
		return (EmpTempVo)selectOne("EmpDao.selectEmpTempDetail",userIdx);	
	}
	
	/** 등록 */
	public void insertGkEmployee(EmpVo vo) throws Exception {
		insert("EmpDao.insertGkEmployee", vo);
	}
	
	/** 삭제 */
	public void deleteGkEmployee(EmpVo vo) throws Exception {
		insert("EmpDao.deleteGkEmployee", vo);
	}
	public void insertRenewEmp(EmpVo vo) throws Exception {
		insert("EmpDao.insertRenewEmp", vo);
	}
	public void updateRenewEmp(EmpVo vo) throws Exception {
		update("EmpDao.updateRenewEmp", vo);
	}

	public String selectDepartmentStep(EmpVo vo) throws Exception {
		return (String)selectOne("EmpDao.selectDepartmentStep",vo);	
	}
}