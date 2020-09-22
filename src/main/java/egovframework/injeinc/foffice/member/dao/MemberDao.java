package egovframework.injeinc.foffice.member.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.injeinc.foffice.user.vo.UserFVo;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("MemberDao")
public class MemberDao extends EgovAbstractMapper {

	@SuppressWarnings("rawtypes")
	public List memberList(UserFVo userFVo) throws Exception {
		return selectList("UserFDao.selectMemberList", userFVo);
	}

}