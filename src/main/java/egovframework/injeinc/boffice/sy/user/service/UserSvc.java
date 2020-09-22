package egovframework.injeinc.boffice.sy.user.service;

import java.util.HashMap;
import java.util.List;

import egovframework.injeinc.boffice.sy.user.vo.UserVo;

public interface UserSvc {
	/** 리스트 조회 */
	public List retrieveListUser(UserVo vo) throws Exception;
	
	/** 총 건수 조회 */
	public int retrievePagUser(UserVo vo) throws Exception;

	 /** 등록 */
	public void registUser(UserVo vo) throws Exception;
	
	/** 상세내용 조회 */
	public UserVo retrieveUser(int cuIdx) throws Exception;
	
	/** 수정 */
	public void modifyUser(UserVo vo) throws Exception;

    /** 삭제 */
	public void saveUser(UserVo vo) throws Exception;
	
	/** 아이디 중복 확인 */
	public int userIdCheckAx(String cuId) throws Exception;
	
	/** 비밀번호 초기화 */
	public int userPassModAx(UserVo vo) throws Exception;
	
	/** 회원그룹 리스트 */
	public List retrievePagUserGroup(UserVo vo) throws Exception;

	/** 회원그룹 총 건수 조회 */
	public int selectUserGroupListTotCnt(UserVo vo) throws Exception;
	
	/** 회원그룹 회원 추가 */
	public HashMap<String, Object> userRegListAx(HashMap<String, Object> paramObject) throws Exception;
	
	/** 공통코드 이메일 값 가져오기 */
	public List selectEmailList() throws Exception;
	
	/** 아이디와 비번으로 회원정보 가져오기 */
	public UserVo retrieveUserByIdAndPwd(UserVo userVo) throws Exception;
	
	/** DI로 회원정보 가져오기 */
	public UserVo retrieveUserByDi(UserVo userVo) throws Exception;
	
	/** 회원관리 로그 등록 */
	public void registUserControlLog(UserVo userVo) throws Exception;
	
	@SuppressWarnings("rawtypes")
	public List retrieveListUserSearch(UserVo userVo) throws Exception;
	
	/** 재동의가 필요한 사용자 목록 가져오기 */
	public List<UserVo> getRePiAgreeUserList() throws Exception;
	
}
