package egovframework.injeinc.boffice.sy.group.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.injeinc.boffice.sy.group.vo.GroupConfigVo;
import egovframework.injeinc.boffice.sy.group.vo.UserGroupVo;
import egovframework.injeinc.boffice.sy.user.vo.UserVo;

@Repository("GroupConfigDao") 
public class GroupConfigDao extends EgovAbstractMapper {
	
	/** 등록 */
	public void createGroupConfig(GroupConfigVo vo) throws Exception {
		insert("GroupConfigDao.insertGroupConfig",vo);
	}
	
	/** 총 건수 조회 */
	public int selectPagGroupConfig(GroupConfigVo vo) throws Exception {
		return (Integer)selectOne("GroupConfigDao.selectPagGroupConfig",vo);
	}
	
	/** 리스트 조회 */
	public List selectListGroupConfig(GroupConfigVo vo) throws Exception {
		return selectList("GroupConfigDao.selectListGroupConfig",vo);
	}
	
	/** 아이디 중복 조회 */
	public int GroupConfigIdCheckAx(String cuId) throws Exception {
		return (Integer)selectOne("GroupConfigDao.GroupConfigIdCheckAx",cuId);
	}
	
	/** 비밀번호 초기화 */
	public int GroupConfigPassModAx(GroupConfigVo vo) throws Exception {
		return (Integer)update("GroupConfigDao.GroupConfigPassModAx",vo);
	}	
	
	/** 상세내용 조회 */
	public GroupConfigVo selectGroupConfig(int cuIdx) {
		return (GroupConfigVo)selectOne("GroupConfigDao.viewGroupConfig",cuIdx); 
	}
	
	/** 수정 */
	public void updateGroupConfig(GroupConfigVo vo) throws Exception {
		update("GroupConfigDao.updateGroupConfig",vo);
	}
	
	/** 삭제 */
	public void deleteGroupConfig(String gcIdx) throws Exception {
		delete("GroupConfigDao.deleteGroupConfig",gcIdx);
	}
	
	/** 그룹회원목록 총 건수 조회*/
	public int selectPagUserGroupMember(int gcIdx) throws Exception {
		return (Integer)selectOne("GroupConfigDao.selectPagUserGroupMember",gcIdx);
	}
	
	/** 그룹회원목록 리스트 조회 */
	public List selectListUserGroupMember(UserGroupVo vo) throws Exception {
		return selectList("GroupConfigDao.selectListUserGroupMember", vo);
	}
	
	/** 그룹회원 삭제 */
	public int userGroupRmvAx(int cuIdx) throws Exception {
		return delete("GroupConfigDao.userGroupRmvAx",cuIdx);
	}
	
	public int selectPagGroupAdd(UserVo vo) throws Exception {
		return (Integer)selectOne("GroupConfigDao.selectPagUserGroupAdd", vo);
	}
	
	public List selectListGroupAdd(UserVo vo) throws Exception {
		return selectList("GroupConfigDao.selectListUserGroupAdd", vo);
	}
	
	public boolean userGroupAddRegAx(HashMap<String, Object> paramObject) throws Exception {
		Integer nResult = 0;
		nResult = update("GroupConfigDao.userGroupAddRegAx", paramObject);
		return nResult > 0;
	}
	
	@SuppressWarnings("rawtypes")
	public List selectListGroupConfigAll() throws Exception {
		return selectList("GroupConfigDao.selectListGroupConfigAll", "");
	}
	
}