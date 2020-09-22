package egovframework.injeinc.boffice.sy.user.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.injeinc.boffice.sy.user.vo.UserVo;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("UserDao") 
public class UserDao extends EgovAbstractMapper {
	
	/** 등록 */
	public void createUser(UserVo vo) throws Exception {
		insert("UserDao.insertUser", vo);
	}
	
	/** 히스토리 등록 */
	public void insertUserHistory(UserVo vo) throws Exception {
		insert("UserDao.insertUserHistory", vo);
	}
	
	/** 총 건수 조회 */
	public int selectPagUser(UserVo vo) throws Exception {
		return (Integer)selectOne("UserDao.selectPagUser", vo);
	}
	
	/** 리스트 조회 */
	public List selectListUser(UserVo vo) throws Exception {
		return selectList("UserDao.selectListUser",vo);
	}
	
	/** 아이디 중복 조회 */
	public int userIdCheckAx(String cuId) throws Exception {
		return (Integer)selectOne("UserDao.userIdCheckAx",cuId);
	}
	
	/** 비밀번호 초기화 */
	public int userPassModAx(UserVo vo) throws Exception {
		return (Integer)update("UserDao.userPassModAx",vo);
	}	
	
	/** 상세내용 조회 */
	public UserVo selectUser(int cuIdx) {
		return (UserVo)selectOne("UserDao.viewUser", cuIdx); 
	}
	
	/** 수정 */
	public void updateUser(UserVo vo) throws Exception {
		update("UserDao.updateUser", vo);
	}
	
	/** 삭제 */
	public void saveUser(UserVo vo) throws Exception {
		update("UserDao.deleteUser",vo);
	}
	
	/** 회원그룹 리스트 */
	public List retrievePagUserGroup(UserVo vo) throws Exception {
		return selectList("UserDao.selecPagtUserGroup", vo);
	}
	
	/** 회원그룹 리스트 총 건수 조회*/
	public int selectUserGroupListTotCnt(UserVo vo) throws Exception {
		return (Integer)selectOne("UserDao.selectUserGroupListTotCnt",vo);
	}
	
	/** */
	public boolean userRegListAx(HashMap<String, Object> paramObject) throws Exception{
		Integer nResult = 0;
		nResult = update("UserDao.userRegListAx", paramObject);
		return nResult > 0;
	}
	
	public List selectEmailList() {
		return selectList("UserDao.selectEmailList","");
	}

	/** 아이디와 비번으로 회원정보 가져오기 */
	public UserVo selectUserByIdAndPwd(UserVo userVo) throws Exception {
		return (UserVo)selectOne("UserDao.selectUserByIdAndPwd", userVo);
	}

	/** Di로 회원정보 가져오기 */
	public UserVo selectUserByDi(UserVo userVo) throws Exception {
		return (UserVo)selectOne("UserDao.selectUserByDi", userVo);
	}
	
	/** 회원관리 로그 등록 */
	public void insertUserControlLog(UserVo userVo) throws Exception {
		insert("UserDao.insertUserControlLog", userVo);
	}
	
	@SuppressWarnings("rawtypes")
	public List selectListUserSearch(UserVo userVo) throws Exception {
		return selectList("UserDao.selectListUserSearch",userVo);
	}

	public List<UserVo> selectRePiAgreeUserList() throws Exception {
		return selectList("UserDao.selectRePiAgreeUserList");
	}
	
}