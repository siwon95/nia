package egovframework.injeinc.foffice.user.service;

import java.util.HashMap;
import java.util.List;

import egovframework.injeinc.foffice.ex.bbs.vo.BbsFVo;
import egovframework.injeinc.foffice.user.vo.UserFVo;


public interface UserFSvc {
	
	/** 기존회원 여부 조회 */
	public int retrieveUserFCnt(String dupkey) throws Exception;
	
	/** 아이디 사용여부 조회 */
	public int retrieveCuIdCnt(HashMap<String, String> cuId) throws Exception;
	
	/** 아이디 사용여부 조회(탈퇴 회원) */
	public int retrieveCuIdCnt2(HashMap<String, String> cuId) throws Exception;
	
	/** 회원가입 등록 */
	public void registUserF(UserFVo userFVO) throws Exception;
	
	public List<UserFVo> retrieveUserListF(UserFVo userFVO) throws Exception;
	
	/** 비밀번호 조회 */
	public String retrieveUserFPwd(UserFVo userFVO) throws Exception;
	
	public String retrieveUserFPwd2(UserFVo userFVO) throws Exception;
	
	/** 회원 상세조회 */
	public UserFVo retrieveUserF(UserFVo userFVO) throws Exception;
	
	/** 회원 탈퇴 */
	public void removeUserF(UserFVo userFVO) throws Exception;
	
	public void removeOtherUserF(UserFVo userFVO) throws Exception;
	
	/** 회원 수정 */
	public void modifyUserF(UserFVo userFVO) throws Exception;
	
	public void modifyUserFSelectConf(UserFVo userFVO) throws Exception;
	
	/** 비밀번호 수정 */
	public void modifyUserFPwd(UserFVo userFVO) throws Exception;
	
	public void modifyUserFPwdmodify(UserFVo userFVO) throws Exception;
	
	/** 회원 조회 */
	/*public String retrieveUserFId(String ownAuth) throws Exception;*/
	
	/*2020.09.08 이솔이*/
	public UserFVo retrieveUserFId(UserFVo userFVo) throws Exception;
	
	public List retrieveUserFIds(String ownAuth) throws Exception;
	
	/** 마이페이지 > 나의게시글 리스트  */
	public List retrieveMyIntegBoardList(BbsFVo bbsFVo) throws Exception;
	
	/** 마이페이지 > 나의게시글 리스트 갯수  */
	public int retrieveMyIntegBoardCnt(BbsFVo bbsFVo) throws Exception;
	
	/** 마이페이지 > 나의민원 리스트  */
	public List retrieveMyIntegMinwonBoardList(BbsFVo bbsFVo) throws Exception;
	
	/** 마이페이지 > 나의민원 리스트 갯수  */
	public int retrieveMyIntegMinwonBoardCnt(BbsFVo bbsFVo) throws Exception;
	
	/** 마이페이지 > 마일리지현황리스트  */
	public List retrieveListMileage(UserFVo userFVo) throws Exception;
	
	/** 마이페이지 > 마일리지현황리스트 카운트  */
	public int retrieveListMileageCnt(String id) throws Exception;
	
	public int retrieveListSingoCnt(String ownauth) throws Exception;
	
	public int retrieveListScrapCnt(String cuId) throws Exception;
	
	public int retrieveListReserveCnt(String ownauth) throws Exception;
	
	/*public int retrieveListPollCnt(String ownauth) throws Exception;*/
	/*2020.09.08 이솔이*/
	public int retrieveListPollCnt(UserFVo userFVo) throws Exception;
	
	public int retrieveLimmitDt(String cuId) throws Exception;

	/** 공통코드 이메일 값 가져오기 */
	public List selectEmailList() throws Exception;
}