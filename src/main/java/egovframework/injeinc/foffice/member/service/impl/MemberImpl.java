package egovframework.injeinc.foffice.member.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.injeinc.foffice.member.dao.MemberDao;
import egovframework.injeinc.foffice.member.service.MemberSvc;
import egovframework.injeinc.foffice.user.vo.UserFVo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("MemberSvc")
public class MemberImpl extends EgovAbstractServiceImpl  implements MemberSvc {
	
	@Resource(name = "MemberDao")
	private MemberDao memberDao;

	@SuppressWarnings("rawtypes")
	public List memberList(UserFVo userFVo) throws Exception {
		return memberDao.memberList(userFVo);
	}
	
}