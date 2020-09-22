package egovframework.injeinc.boffice.title.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.injeinc.boffice.title.dao.TitleDao;
import egovframework.injeinc.boffice.title.service.TitleSvc;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;


@Service("TitleSvc")
public class TitleImpl extends EgovAbstractServiceImpl implements TitleSvc {

	@Resource(name="TitleDao")
	private TitleDao titleDao;

	
	public EgovMap retriveTitle(String cmmCode) throws Exception {
		return titleDao.selectTitle(cmmCode);
	}
	
}