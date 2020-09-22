package egovframework.injeinc.boffice.sy.group.service;

import java.util.HashMap;
import java.util.List;

import egovframework.injeinc.boffice.sy.group.vo.GroupConfigVo;
import egovframework.injeinc.boffice.sy.group.vo.UserGroupVo;
import egovframework.injeinc.boffice.sy.user.vo.UserVo;

public interface GroupConfigSvc {
	/** 리스트 조회 */
	public List retrieveListGroupConfig(GroupConfigVo vo) throws Exception;
	
	/** 총 건수 조회 */
	public int retrievePagGroupConfig(GroupConfigVo vo) throws Exception;

	 /** 등록 */
	public void registGroupConfig(GroupConfigVo vo) throws Exception;
	
	/** 상세내용 조회 */
	public GroupConfigVo retrieveGroupConfig(int gcIdx) throws Exception;
	
	/** 수정 */
	public void modifyGroupConfig(GroupConfigVo vo) throws Exception;

    /** 삭제 */
	public void removeGroupConfig(String gcIdx) throws Exception;
	
	/** 그룹회원목록 총 건수 조회*/
	public int retrievePagtUserGroupMember(int gcIdx) throws Exception;

	/** 그룹회원목록 리스트 조회 */
	public List retrieveListUserGroupMember(UserGroupVo vo) throws Exception;
	
	/** 그룹회원 삭제 */
	public int userGroupRmvAx(int cuIdx) throws Exception;
	
	/** 회원그룹관리 회원 추가 총건수 */
	public int retrievePagUserGroupAdd(UserVo vo) throws Exception;
	
	/** 회원그룹관리 회원 리스트 */
	public List retrieveListUserGroupAdd(UserVo vo) throws Exception;
	
	
	/** 회원그룹 회원 추가 */
	public HashMap<String, Object> userGroupAddRegAx(HashMap<String, Object> paramObject) throws Exception;	
	
	@SuppressWarnings("rawtypes")
	public List retrieveListGroupConfigAll() throws Exception;
}
