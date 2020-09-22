package egovframework.injeinc.foffice.user.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.injeinc.foffice.ex.bbs.vo.BbsFVo;
import egovframework.injeinc.foffice.user.vo.UserFVo;
import egovframework.injeinc.common.util.XecureUtil;

@Repository("UserFDao")
public class UserFDao extends EgovAbstractMapper {
	
	//기존회원 여부 조회
	public int selectUserFCnt(String dupkey) throws Exception{
		return (Integer)selectOne("UserFDao.selectUserFCnt", dupkey);
	}
	
	//아이디 사용 여부 조회
	public int selectCuIdCnt(HashMap<String, String> cuId) throws Exception{
		return (Integer)selectOne("UserFDao.selectCuIdCnt", cuId);
	}
	
	//아이디 사용 여부 조회(탈퇴회원)
	public int selectCuIdCnt2(HashMap<String, String> cuId) throws Exception{
		return (Integer)selectOne("UserFDao.selectCuIdCnt2", cuId);
	}
	
	//회원가입 등록
	public void insertUserF(UserFVo userFVO) throws Exception{
		insert("UserFDao.insertUserF", userFVO);
	}
	
	//비밀번호 조회
	public String selectUserFPwd(UserFVo userFVO) throws Exception{
		return (String)selectOne("UserFDao.selectUserFPwd", userFVO);
	}
	
	//비밀번호 조회
		public String selectUserFPwd2(UserFVo userFVO) throws Exception{
			return (String)selectOne("UserFDao.selectUserFPwd2", userFVO);
		}
	
	//회원상세조회
	public UserFVo selectUserF(UserFVo userFVO) throws Exception{
		return (UserFVo)selectOne("UserFDao.selectUserF", userFVO);
	}
	
	public List<UserFVo> selectUserListF(UserFVo userFVO) throws Exception{
		return selectList("UserFDao.selectUserListF", userFVO);
	}
	//회원 탈퇴
	public void deleteUserF(UserFVo userFVO) throws Exception{
		delete("UserFDao.deleteUserF", userFVO);
	}
	
	//회원정보 수정
	public void updateUserF(UserFVo userFVO) throws Exception{
		update("UserFDao.updateUserF", userFVO);
	}
	
	public void updateUserFSelectConf(UserFVo userFVO) throws Exception{
		update("UserFDao.updateUserFSelectConf", userFVO);
	}
	
	//회원탈퇴 테이블 (insert)
	public void insertUserFDrop(UserFVo userFVO) throws Exception{
		insert("UserFDao.insertUserFDrop", userFVO);
	}
	
	//회원탈퇴 테이블 (insert)
	public void insertUserFDrop2(UserFVo userFVO) throws Exception{
		insert("UserFDao.insertUserFDrop2", userFVO);
	}
	
	//회원비밀번호 변경
	public void updateUserFPwd(UserFVo userFVO) throws Exception{
		update("UserFDao.updateUserFPwd", userFVO);
	}
	
	//회원비밀번호 변경
	public void updateUserFPwdmodify(UserFVo userFVO) throws Exception{
		update("UserFDao.updateUserFPwdmodify", userFVO);
	}
	
	//회원 조회
/*	public String selectUserFId(String ownAuth) throws Exception{
		return (String)selectOne("UserFDao.selectUserFId", ownAuth);
	}*/
	public UserFVo selectUserFId(UserFVo userFVO) throws Exception{
		return (UserFVo)selectOne("UserFDao.selectUserFId", userFVO);
	}
	
	//회원 조회
	public List selectUserFIds(String ownAuth) throws Exception{
		return selectList("UserFDao.selectUserFIds", ownAuth);
	}
	
	/** 마이페이지 > 나의게시글 리스트  */
	public List selectMyIntegBoardList(BbsFVo bbsFVo) throws Exception{
		return selectList("UserFDao.selectMyIntegBoardList",bbsFVo);
	}	
	
	/** 마이페이지 > 나의게시글 리스트 갯수  */
	public int selectMyIntegBoardCnt(BbsFVo bbsFVo) throws Exception{
		return (Integer)selectOne("UserFDao.selectMyIntegBoardCnt",bbsFVo);
	}
	
	/** 마이페이지 > 나의민원 리스트  */
	public List selectMyIntegMinwonBoardList(BbsFVo bbsFVo) throws Exception{
		return selectList("UserFDao.selectMyIntegMinwonBoardList",bbsFVo);
	}
	
	/** 마이페이지 > 나의민원 리스트 갯수  */
	public int retrieveMyIntegMinwonBoardCnt(BbsFVo bbsFVo) throws Exception {
		return (Integer)selectOne("UserFDao.selectMyIntegMinwonBoardCnt",bbsFVo);
	}
	
	/** 마이페이지 > 마일리지현황 리스트  */
	public List selectListMileage(UserFVo userFVo) throws Exception {
		return selectList("UserFDao.selectListMileage",userFVo);
	}
	
	/** 마이페이지 > 마일리지현황 리스트카운트  */
	public int selectListMileageCnt(String id) throws Exception {
		return (Integer)selectOne("UserFDao.selectListMileageCnt",id);
	}
	
	public int selectListSingoCnt(String ownAuth) throws Exception {
		return (Integer)selectOne("UserFDao.selectListSingoCnt",ownAuth);
	}
	
	public int selectListScrapCnt(String cuId) throws Exception {
		return (Integer)selectOne("UserFDao.selectListScrapCnt",cuId);
	}
	
	public int selectListReserveCnt(String ownAuth) throws Exception {
		return (Integer)selectOne("UserFDao.selectListReserveCnt",ownAuth);
	}
	
	/*public int selectListPollCnt(String ownAuth) throws Exception {
		return (Integer)selectOne("UserFDao.selectListPollCnt",ownAuth);
	}*/
	
	/*2020.09.08 이솔이*/
	public int selectListPollCnt(UserFVo userFVo) throws Exception {
		return (Integer)selectOne("UserFDao.selectListPollCnt",userFVo);
	}
	
	public int selectLimmitDt(String cuId) throws Exception {
		return (Integer)selectOne("UserFDao.selectLimmitDt",cuId);
	}

	public List selectEmailList() {
		return selectList("UserFDao.selectEmailList","");
	}
	
	
	
}