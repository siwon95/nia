package egovframework.cmm.service;

import java.util.List;

import egovframework.cmm.vo.AuthorityVo;

public interface AuthoritySvc {

	@SuppressWarnings("rawtypes")
	public List<AuthorityVo> retrieveListAuthorityMenu(AuthorityVo authorityVo) throws Exception;

	@SuppressWarnings("rawtypes")
	public List retrieveListAuthorityMenuMaster(AuthorityVo authorityVo) throws Exception;

	@SuppressWarnings("rawtypes")
	public List retrieveListAuthorityBoardGroup(AuthorityVo authorityVo) throws Exception;

	@SuppressWarnings("rawtypes")
	public List retrieveListAuthorityBoardGroupMaster() throws Exception;

	@SuppressWarnings("rawtypes")
	public List retrieveListAuthorityBoard(AuthorityVo authorityVo) throws Exception;

	@SuppressWarnings("rawtypes")
	public List retrieveListAuthorityBoardMaster(AuthorityVo authorityVo) throws Exception;

	@SuppressWarnings("rawtypes")
	public List retrieveListAuthorityCbIdx(AuthorityVo authorityVo) throws Exception;

	@SuppressWarnings("rawtypes")
	public List retrieveListAuthorityCbIdxMaster(AuthorityVo authorityVo) throws Exception;

	public String retrieveUprCd(String code) throws Exception;

}