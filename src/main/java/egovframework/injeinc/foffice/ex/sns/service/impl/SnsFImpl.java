package egovframework.injeinc.foffice.ex.sns.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.injeinc.foffice.ex.sns.dao.SnsFDao;
import egovframework.injeinc.foffice.ex.sns.service.SnsFSvc;
import egovframework.injeinc.foffice.ex.sns.vo.SnsContentsFVo;
import egovframework.injeinc.foffice.ex.sns.vo.SnsHashTagFVo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("SnsFSvc")
public class SnsFImpl extends EgovAbstractServiceImpl implements SnsFSvc {

	@Resource(name = "SnsFDao")
	private SnsFDao socialFDao;

	
	public List<SnsContentsFVo> retrieveListSnsContents(SnsContentsFVo snsContentsFVo) throws Exception {
		
		if(snsContentsFVo.getPageIndex() == 1 && snsContentsFVo.getSearchHashTag() != null && !"".equals(snsContentsFVo.getSearchHashTag())){
			socialFDao.updateHashtgForViewCnt(snsContentsFVo.getSearchHashTag());
		}
		
		return socialFDao.selectListSnsContents(snsContentsFVo);
	}

	
	public List<SnsHashTagFVo> retrieveHashtagList() throws Exception {
		return socialFDao.selectHashtagList();
	}

}