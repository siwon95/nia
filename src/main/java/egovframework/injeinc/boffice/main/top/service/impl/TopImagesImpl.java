package egovframework.injeinc.boffice.main.top.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;

import egovframework.injeinc.boffice.main.top.dao.TopImagesDao;
import egovframework.injeinc.boffice.main.top.service.TopImagesSvc;
import egovframework.injeinc.boffice.main.top.vo.TopImagesVo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;


@Service("TopImagesSvc")
public class TopImagesImpl extends EgovAbstractServiceImpl implements
        TopImagesSvc {
    
    @Resource(name="TopImagesDao")
    private TopImagesDao topImagesDao;
    
    /** ID Generation */
    @Resource(name = "topImagesIdService")
    private EgovIdGnrService topImagesIdService;
    
	@Autowired
	private DataSourceTransactionManager transactionManager;

	public List retrieveListTopImages(TopImagesVo topImagesVO) throws Exception {
		return topImagesDao.selectListTopImages(topImagesVO);
	}

	public int retrievePagTopImages(TopImagesVo topImagesVO) throws Exception {
		return topImagesDao.selectPagTopImages(topImagesVO);
	}

	public void registTopImages(TopImagesVo topImagesVO) throws Exception {
		if(topImagesVO != null){
			topImagesVO.setTiIdx(topImagesIdService.getNextStringId());
		}
		topImagesDao.insertTopImages(topImagesVO);
	}

	public TopImagesVo retrieveTopImages(TopImagesVo topImagesVO)
			throws Exception {
		return topImagesDao.selectTopImages(topImagesVO);
	}

	public String retrieveFilePath(String tiIdx) throws Exception {
		return topImagesDao.selectFilePath(tiIdx);
	}

	public void removeTopImagesFile(HashMap<String, String> param)
			throws Exception {
		topImagesDao.deleteTopImagesFile(param);
	}

	public void modifyTopImages(TopImagesVo topImagesVO) throws Exception {
		topImagesDao.updateTopImages(topImagesVO);
	}

	public void removeTopImages(TopImagesVo topImagesVO) throws Exception {
		topImagesDao.deleteTopImages(topImagesVO);
	}

	public List retrieveListTopImagesMain() throws Exception {
		return topImagesDao.selectListTopImagesMain();
	}

	public void modifyTopImagesViewCnt(String tiIdx) throws Exception {
		topImagesDao.updateTopImagesViewCnt(tiIdx);
	}

}