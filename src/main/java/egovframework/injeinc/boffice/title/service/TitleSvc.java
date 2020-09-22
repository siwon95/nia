package egovframework.injeinc.boffice.title.service;

import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface TitleSvc {
	
	public EgovMap retriveTitle(String cmmCode) throws Exception;
	
}