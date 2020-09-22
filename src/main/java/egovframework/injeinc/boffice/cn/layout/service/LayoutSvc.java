package egovframework.injeinc.boffice.cn.layout.service;

import java.util.List;

import egovframework.injeinc.boffice.cn.layout.vo.LayoutVo;
import egovframework.injeinc.boffice.cn.site.vo.SiteVo;

public interface LayoutSvc {
	/** 리스트 조회 */
	public List retrieveListLayout(LayoutVo vo) throws Exception;
	
	/**상세 조회 */
	public LayoutVo retrieveLayout(LayoutVo vo) throws Exception;
	
	 /** 등록 */
	public void registLayout(LayoutVo vo) throws Exception;
	
	/** 수정 */
	public void modifyLayout(LayoutVo vo) throws Exception;
	
	/** 삭제 */
	public void removeLayout(LayoutVo vo) throws Exception;
	
}