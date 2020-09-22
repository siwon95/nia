package egovframework.injeinc.boffice.cn.menu.service;

import java.util.List;

import egovframework.injeinc.boffice.cn.menu.vo.MenuVo;

public interface MenuSvc {
	/** 메뉴코드생성 */
	public MenuVo selectCreateSortOrder(MenuVo menuVo) throws Exception;
	
	/** HOME메뉴확인 카운트 */
	public int selectHomeCodeChk(MenuVo menuVo) throws Exception;
	
	/** 메뉴생성 */
	public void insertMenu(MenuVo menuVo) throws Exception;
	
	public void insertMenuSubUrls(MenuVo menuVo) throws Exception;
	
	public void deleteMenuSubUrls(MenuVo menuVo) throws Exception;
	
	public void deleteMenu(MenuVo menuVo) throws Exception;
	
	public String selectMenuUnderCount(MenuVo menuVo) throws Exception;
	
	public List<MenuVo> selectMenuSubUrls(MenuVo menuVo) throws Exception;
	
	/** 메뉴수정 */
	public void modifyMenu(MenuVo menuVo) throws Exception;
	
	/** 메뉴링크수정 */
	public void modifyLinkMenu(MenuVo menuVo) throws Exception;
	
	/** 메뉴상세보기 */
	public MenuVo selectEzUserMenu(MenuVo menuVo) throws Exception;
	
	/** 메뉴생성 */
	public List<MenuVo> selectEzUserMenuCreate(MenuVo menuVo) throws Exception;
	
	public List<MenuVo> selectEzUserMenuCreateSub(MenuVo menuVo) throws Exception;
	
	/** 메뉴상세보기 */
	public String selectMenuPath(MenuVo menuVo) throws Exception;
	
	/** 메뉴목록 */
	public List selectMenu(MenuVo menuVo) throws Exception;
	
	/** 메뉴레벨 */	
	public String menuOrderLevel(String sortOrder) throws Exception;
	
	/** 타겟정렬 */	
	public String targetSortOrder(MenuVo menuVo) throws Exception;
	
	/** 메뉴정렬 */
	public void modifyMenuOrder1(MenuVo menuVo) throws Exception;
	
	/** 메뉴정렬 */
	public void modifyMenuOrder2(MenuVo menuVo) throws Exception;
	
	/** 메뉴정렬 */
	public void modifyMenuOrder3(MenuVo menuVo) throws Exception;
	
	/** 메뉴경로 저장 */
	public void updateMenuPath(MenuVo menuVo) throws Exception;
	
	@SuppressWarnings("rawtypes")
	public List selectEzUserMenuAndSubUrls(MenuVo menuVo) throws Exception;
	
	@SuppressWarnings("rawtypes")
	public List selectListTopMenu(MenuVo menuVo) throws Exception;
	
	@SuppressWarnings("rawtypes")
	public List selectListLeftMenu(MenuVo menuVo) throws Exception;
}