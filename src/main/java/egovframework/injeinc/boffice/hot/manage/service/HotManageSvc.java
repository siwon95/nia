package egovframework.injeinc.boffice.hot.manage.service;

import java.util.List;

import egovframework.injeinc.boffice.hot.manage.vo.HotManageVo;


public interface HotManageSvc {
	
	/** 리스트 조회 */
	public List retrieveListHotManage(HotManageVo hotManageVO) throws Exception;
	
	/** 총 건수 조회 */
	public int retrievePagHotManage(HotManageVo hotManageVO) throws Exception;
	
	/** 등록 */
	public void registHotManage(HotManageVo hotManageVO) throws Exception;
	
	/** 수정 */
	public void modifyHotManage(HotManageVo hotManageVO) throws Exception;
	
	/** 상세 조회 */
	public HotManageVo retrieveFile(HotManageVo hotManageVO) throws Exception;
	
	/** 최소상위 step 조회 */
	public HotManageVo retrieveMinStep(HotManageVo hotManageVO) throws Exception;
	
	/** 최대하위 step 조회 */
	public HotManageVo retrieveMaxStep(HotManageVo hotManageVO) throws Exception;
	
	/** 정렬 순서 수정(화살표 클릭시) */
	public void modifyStep(HotManageVo hotManageVO) throws Exception;
	
	/** 삭제 */
	public void removeHotManage(HotManageVo hotManageVO) throws Exception;
	
	/** 메인화면 리스트 조회 */
	public List retrieveListHotManageMain() throws Exception;
	
}