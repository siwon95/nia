package egovframework.cmm.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import egovframework.cmm.vo.AuthorityVo;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("AuthorityDao")
public class AuthorityDao extends EgovAbstractMapper {
	
	@SuppressWarnings("rawtypes")
	public List selectListAuthorityMenu(AuthorityVo authorityVO) throws Exception {
		return selectList("AuthorityDao.selectListAuthorityMenu", authorityVO);
	}
	
	@SuppressWarnings("rawtypes")
	public List selectListAuthorityMenuMaster(AuthorityVo authorityVO) throws Exception {
		return selectList("AuthorityDao.selectListAuthorityMenuMaster", authorityVO);
	}
	
	@SuppressWarnings("rawtypes")
	public List selectListAuthorityBoardGroup(AuthorityVo authorityVO) throws Exception {
		return selectList("AuthorityDao.selectListAuthorityBoardGroup", authorityVO);
	}
	
	@SuppressWarnings("rawtypes")
	public List selectListAuthorityBoardGroupMaster() throws Exception {
		return selectList("AuthorityDao.selectListAuthorityBoardGroupMaster", "");
	}
	
	@SuppressWarnings("rawtypes")
	public List selectListAuthorityBoard(AuthorityVo authorityVO) throws Exception {
		return selectList("AuthorityDao.selectListAuthorityBoard", authorityVO);
	}
	
	@SuppressWarnings("rawtypes")
	public List selectListAuthorityBoardMaster(AuthorityVo authorityVO) throws Exception {
		return selectList("AuthorityDao.selectListAuthorityBoardMaster", authorityVO);
	}
	
	@SuppressWarnings("rawtypes")
	public List selectListAuthorityCbIdx(AuthorityVo authorityVO) throws Exception {
		return selectList("AuthorityDao.selectListAuthorityCbIdx", authorityVO);
	}
	
	@SuppressWarnings("rawtypes")
	public List selectListAuthorityCbIdxMaster(AuthorityVo authorityVO) throws Exception {
		return selectList("AuthorityDao.selectListAuthorityCbIdxMaster", authorityVO);
	}
	
	@SuppressWarnings("rawtypes")
	public String selectUprCd(String code) throws Exception {
		return (String)selectOne("AuthorityDao.selectUprCd", code);
	}
	
}