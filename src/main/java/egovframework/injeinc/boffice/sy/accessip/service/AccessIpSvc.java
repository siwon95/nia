package egovframework.injeinc.boffice.sy.accessip.service;

import java.util.List;

import egovframework.injeinc.boffice.sy.accessip.vo.AccessIpVo;


public interface AccessIpSvc {

	void registAccessIp(AccessIpVo accessIpVo) throws Exception;

	List retrieveListAccessIp(AccessIpVo accessIpVo) throws Exception;

	int retrievePagAccessIp(AccessIpVo accessIpVo) throws Exception;

	AccessIpVo retrieveAccessIp(AccessIpVo accessIpVo) throws Exception;

	void modifyAccessIp(AccessIpVo accessIpVo) throws Exception;

	void removeAccessIp(AccessIpVo accessIpVo) throws Exception;

	List<AccessIpVo> retrieveAllListAccessIp() throws Exception;
	
	
}