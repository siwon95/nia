package egovframework.injeinc.boffice.group.emp.service;

import java.util.HashMap;
import java.util.List;

import egovframework.injeinc.boffice.group.dept.vo.GroupDeptVo;
import egovframework.injeinc.boffice.group.emp.vo.EmpTempVo;
import egovframework.injeinc.boffice.group.emp.vo.EmpVo;

public interface EmpSvc {
	
	/**셀렉트박스 리스트 조회 */
	public List retrieveListSbEmp(EmpVo empVO) throws Exception;
	/** 리스트 조회(전체, 미등록 제외) */
	public List retrieveListEmp(EmpVo empVO) throws Exception;
	/** 리스트 조회(전체, 미등록) */
	public List retrieveListAllNoEmp(EmpVo empVO) throws Exception;
	
	public int selectAllEmpListTotCnt(EmpVo empVO) throws Exception;
	
	public int selectEmpListTotCnt(EmpVo empVO) throws Exception;
	
	/** 사용 여부 수정 */
	public void modifyUseCheck(EmpVo empVO) throws Exception;
	/** 셀렉트박스1(국코드,국명 조회) */
	public List retrieveListDepstep1() throws Exception;
	/** 셀렉트박스2(과코드,과명 조회) */
	public List retrieveListEmpDep2(HashMap<String, String> param) throws Exception;
	/** 셀렉트박스3(팀코드,팀명 조회) */
	public List retrieveListEmpDep3(HashMap<String, String> param) throws Exception;
	/** 등록 */
	public void registEmp(EmpVo empVO) throws Exception;
	/** 등록 */
	public void registEmp2(EmpTempVo empTempVO) throws Exception;
	/** ceIdx 최대값 조회 */
	public String retrieveMaxCeIdx() throws Exception;
	/** cdIdx 조회 */
	public String retrieveCdIdx(EmpVo empVO) throws Exception;
	/** ceSort 최대값 조회 */
	public String retrieveMaxCeSort(EmpVo empVO) throws Exception;
	/** 사용자 정보 조회 */
	public EmpVo retrieveEmp(EmpVo empVO) throws Exception;
	/** 사용자 삭제(업데이트) */
	public void modifyEmp(EmpVo empVO) throws Exception;
	/** 사용자 수정 */
	public void modifyEmpDetail(EmpVo empVO) throws Exception;
	/** 사용자 수정 */
	public void modifyEmpDetail(EmpTempVo empTempVO) throws Exception;
	/** 최소상위 ceSort 조회 */
	public String retrieveMinUpCeSort(EmpVo empVO) throws Exception;
	/** 최대하위 ceSort 조회 */
	public String retrieveMaxDownCeSort(EmpVo empVO) throws Exception;
	/** 임의값으로 수정 */
	public void modifyTempCeSort(EmpVo empVO) throws Exception;
	/** 아래 → 위 수정 */
	public void modifyUpCeSort(EmpVo empVO) throws Exception;
	/** 위 → 아래 수정 */
	public void modifyDownCeSort(EmpVo empVO) throws Exception;
	/** 미사용자 미등록으로 수정 */
	public void modifyNoEmp(String ceIdx) throws Exception;
	
	/** 최대userIdx 조회 */
	public int retrieveMaxUserIdx() throws Exception;
	/** EmpTemp 등록*/
	public void registEmpTemp(EmpTempVo EmpTempVO) throws Exception;
	/**  직원테이블에 해당userId 유무 조회 */
	public int retrieveCeIdx(EmpTempVo EmpTempVO) throws Exception;
	/**  뷰테이블에 해당코드 조회 */
	public EmpTempVo retrieveEmpView(EmpVo EmpVO) throws Exception;
	/**  조직도 연동 리스트 */
	public List retrieveListEmpTemp(EmpTempVo EmpTempVO) throws Exception;
	/** 조직도 연동 페이지수*/
	public int retrievePagEmpTemp(EmpTempVo EmpTempVO) throws Exception;
	/** temp회원 조회*/
	public EmpTempVo retrieveEmpTemp(EmpTempVo EmpTempVO) throws Exception;
	/** 해당 부서 회원 조회*/
	public EmpVo retrieveDeptEmp(EmpTempVo EmpTempVO) throws Exception;
	/** temp 등록여부 수정*/
	public void modifyEmpTempInsertYn(EmpTempVo empTempVO) throws Exception;
	/** temp 삭제*/
	public void modifyEmpTempD(EmpTempVo empTempVO) throws Exception;
	/** temp 복원*/
	public void modifyEmpTempRes(EmpTempVo empTempVO) throws Exception;
	/** 세올 넘어옴 등록*/
	public void registEmpTempOutside(EmpTempVo empTempVO) throws Exception;
	/** temp 일괄 등록*/
	public String registEmpTempAll(EmpTempVo empTempVO) throws Exception;
	/** temp 일괄 삭제*/
	public void removeEmpTempAll(EmpTempVo empTempVO) throws Exception;
	/** temp 일괄 삭제*/
	public void modifyEmpTempAllRestore(EmpTempVo empTempVO) throws Exception;
	/** temp 일괄 마크 변경*/
	public void modifyEmpTempAllMark(EmpTempVo empTempVO) throws Exception;
	/** temp 수정*/
	public void modifyEmpTemp(EmpTempVo empTempVO) throws Exception;
	/** temp 상세조회*/
	public EmpTempVo retrieveEmpTempDetail(int userIdx) throws Exception;
	/** */
	public void insertGkEmployee(EmpVo empVO) throws Exception;
	
	public void deleteGkEmployee(EmpVo empVO) throws Exception;
	
	public void insertRenewEmp(EmpVo empVO) throws Exception;
	
	public void updateRenewEmp(EmpVo empVO) throws Exception;
	
	public String selectDepartmentStep(EmpVo empVO) throws Exception;
}
	
