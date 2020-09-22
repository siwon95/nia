package egovframework.injeinc.boffice.cn.content.dao;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.injeinc.boffice.cn.content.vo.ContentVo;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("ContentDao") 
public class ContentDao extends EgovAbstractMapper {

	/** 컨텐츠 등록 */
	public void insertContent(ContentVo contentVo) throws Exception {
		update("ContentDao.insertContent", contentVo);
	}
	
	/**  컨텐츠 리스트 조회 */
	public List<ContentVo> selectListContent() throws Exception {
		return selectList("ContentDao.selectListContent", null);
	}
}
