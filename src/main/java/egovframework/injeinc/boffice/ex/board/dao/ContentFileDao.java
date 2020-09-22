package egovframework.injeinc.boffice.ex.board.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.injeinc.boffice.ex.board.vo.ContentFileVo;

@Repository("ContentFileDao")
public class ContentFileDao extends EgovAbstractMapper {
	
	public void createContentFile(ContentFileVo contentFileVo) throws Exception {
		insert("ContentFileDao.insertContentFile", contentFileVo);
	}
	
	public void deleteContentFile(ContentFileVo contentFileVo) throws Exception {
		delete("ContentFileDao.deleteContentFile", contentFileVo);
	}
	
	public ContentFileVo selectContentFile(ContentFileVo contentFileVo) throws Exception {
		return (ContentFileVo)selectOne("ContentFileDao.selectContentFile", contentFileVo);
	}

	@SuppressWarnings("rawtypes")
	public List selectListContentFile(ContentFileVo contentFileVo) throws Exception {
		return selectList("ContentFileDao.selectListContentFile", contentFileVo);
	}

	public ContentFileVo selectListContentFileOne(ContentFileVo contentFileVo) throws Exception {
		return (ContentFileVo)selectOne("ContentFileDao.selectListContentFileOne", contentFileVo);
	}
	
}