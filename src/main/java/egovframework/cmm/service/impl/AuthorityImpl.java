package egovframework.cmm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.cmm.dao.AuthorityDao;
import egovframework.cmm.service.AuthoritySvc;
import egovframework.cmm.vo.AuthorityVo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("AuthoritySvc")
public class AuthorityImpl extends EgovAbstractServiceImpl implements AuthoritySvc {

	@Resource(name="AuthorityDao")
	private AuthorityDao authorityDao;
	
	@SuppressWarnings("rawtypes")
	public List retrieveListAuthorityMenu(AuthorityVo authorityVo) throws Exception {
		return authorityDao.selectListAuthorityMenu(authorityVo);
	}
	
	@SuppressWarnings("rawtypes")
	public List retrieveListAuthorityMenuMaster(AuthorityVo authorityVo) throws Exception {
		return authorityDao.selectListAuthorityMenuMaster(authorityVo);
	}
	
	@SuppressWarnings("rawtypes")
	public List retrieveListAuthorityBoardGroup(AuthorityVo authorityVo) throws Exception {
		return authorityDao.selectListAuthorityBoardGroup(authorityVo);
	}
	
	@SuppressWarnings("rawtypes")
	public List retrieveListAuthorityBoardGroupMaster() throws Exception {
		return authorityDao.selectListAuthorityBoardGroupMaster();
	}
	
	@SuppressWarnings("rawtypes")
	public List retrieveListAuthorityBoard(AuthorityVo authorityVo) throws Exception {
		return authorityDao.selectListAuthorityBoard(authorityVo);
	}
	
	@SuppressWarnings("rawtypes")
	public List retrieveListAuthorityBoardMaster(AuthorityVo authorityVo) throws Exception {
		return authorityDao.selectListAuthorityBoardMaster(authorityVo);
	}
	
	@SuppressWarnings("rawtypes")
	public List retrieveListAuthorityCbIdx(AuthorityVo authorityVo) throws Exception {
		return authorityDao.selectListAuthorityCbIdx(authorityVo);
	}
	
	@SuppressWarnings("rawtypes")
	public List retrieveListAuthorityCbIdxMaster(AuthorityVo authorityVo) throws Exception {
		return authorityDao.selectListAuthorityCbIdxMaster(authorityVo);
	}

	public String retrieveUprCd(String code) throws Exception {
		return authorityDao.selectUprCd(code);
	}
	
}