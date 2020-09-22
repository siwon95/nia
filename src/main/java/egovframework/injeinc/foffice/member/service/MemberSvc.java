package egovframework.injeinc.foffice.member.service;

import java.util.List;

import egovframework.injeinc.foffice.user.vo.UserFVo;

public interface MemberSvc {

	@SuppressWarnings("rawtypes")
	public List memberList(UserFVo userFVo) throws Exception;

}