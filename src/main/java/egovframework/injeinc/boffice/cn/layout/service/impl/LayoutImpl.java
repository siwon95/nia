package egovframework.injeinc.boffice.cn.layout.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.injeinc.boffice.cn.layout.dao.LayoutDao;
import egovframework.injeinc.boffice.cn.layout.service.LayoutSvc;
import egovframework.injeinc.boffice.cn.layout.vo.LayoutVo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("LayoutSvc")
public class LayoutImpl extends EgovAbstractServiceImpl implements LayoutSvc {

	@Resource(name="LayoutDao")
	LayoutDao layoutDao;
	
	/** 총 리스트 조회 */
	public List retrieveListLayout(LayoutVo layoutVo) throws Exception {
		return layoutDao.selectListLayout(layoutVo);
	}
	
	/** 상세 조회 */
	public LayoutVo retrieveLayout(LayoutVo layoutVo) throws Exception {
		return layoutDao.selectLayout(layoutVo);
	}
	
	/** 등록 */
	public void registLayout(LayoutVo layoutVo) throws Exception {
		layoutDao.createLayout(layoutVo);
	}
	
	/** 수정 */
	public void modifyLayout(LayoutVo layoutVo) throws Exception {
		layoutDao.updateLayout(layoutVo);
	}
	
	/** 삭제 */
	public void removeLayout(LayoutVo layoutVo) throws Exception {
		layoutDao.deleteLayout(layoutVo);
	}
	
}