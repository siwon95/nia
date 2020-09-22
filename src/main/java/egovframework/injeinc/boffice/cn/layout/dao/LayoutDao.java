package egovframework.injeinc.boffice.cn.layout.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.injeinc.boffice.cn.layout.vo.LayoutVo;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("LayoutDao") 
public class LayoutDao extends EgovAbstractMapper {
	
	/** 목록 조회 */
	public List selectListLayout(LayoutVo layoutVo) throws Exception {
        return selectList("LayoutDao.selectListLayout", layoutVo);
    }
	
	/** 상세내용 조회 */
	public LayoutVo selectLayout(LayoutVo layoutVo) throws Exception {
		layoutVo = (LayoutVo)selectOne("LayoutDao.viewLayout", layoutVo);
		return layoutVo;
	}
	
	 /** 등록 */
  	public void createLayout(LayoutVo layoutVo) throws Exception {
  		insert("LayoutDao.insertLayout", layoutVo);
  	}
  	
  	/** 수정 */
  	public void updateLayout(LayoutVo layoutVo) throws Exception {
  		insert("LayoutDao.updateLayout", layoutVo);
  	}
  	
  	/** 수정 */
  	public void deleteLayout(LayoutVo layoutVo) throws Exception {
  		delete("LayoutDao.deleteLayout", layoutVo);
  	}
  	
}