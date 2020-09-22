package egovframework.injeinc.boffice.cn.menu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.injeinc.boffice.cn.menu.dao.MenuDao;
import egovframework.injeinc.boffice.cn.menu.service.MenuSvc;
import egovframework.injeinc.boffice.cn.menu.vo.MenuVo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("MenuSvc")
public class MenuImpl extends EgovAbstractServiceImpl implements MenuSvc {

	@Resource(name="MenuDao")
	MenuDao menuDao;
	
	/** 메뉴코드생성 */
	public MenuVo selectCreateSortOrder(MenuVo menuVo) throws Exception {
		return menuDao.selectCreateSortOrder(menuVo);
	}
	
	/** HOME메뉴확인 카운트 */
	public int selectHomeCodeChk(MenuVo menuVo) throws Exception {
		return menuDao.selectHomeCodeChk(menuVo);
	}
	
	/** 메뉴생성 */
	public void insertMenu(MenuVo menuVo) throws Exception {
		menuDao.insertMenu(menuVo);
		menuDao.insertMenuHistory(menuVo);
	}
	
	public void insertMenuSubUrls(MenuVo menuVo) throws Exception {
		menuDao.insertMenuSubUrls(menuVo);
	}
	
	public void deleteMenuSubUrls(MenuVo menuVo) throws Exception {
		menuDao.deleteMenuSubUrls(menuVo);
	}
	
	public void deleteMenu(MenuVo menuVo) throws Exception {
		menuDao.insertMenuHistory(menuVo);
		menuDao.deleteMenu(menuVo);
	}
	
	public String selectMenuUnderCount(MenuVo menuVo) throws Exception {
		return menuDao.selectMenuUnderCount(menuVo);
	}
	
	public List<MenuVo> selectMenuSubUrls(MenuVo menuVo) throws Exception {
		return menuDao.selectMenuSubUrls(menuVo);
	}
	
	/** 메뉴생성 */
	public void modifyMenu(MenuVo menuVo) throws Exception {
		menuDao.updateMenu(menuVo);
		menuDao.insertMenuHistory(menuVo);
	}
	
	/** 메뉴링크생성 */
	public void modifyLinkMenu(MenuVo menuVo) throws Exception {
		menuDao.updateLinkMenu(menuVo);
	}
	
	/** 메뉴상세보기 */
	public MenuVo selectEzUserMenu(MenuVo menuVo) throws Exception {
		return menuDao.selectEzUserMenu(menuVo);
	}
	
	/** 메뉴생성레코드 */
	public List<MenuVo> selectEzUserMenuCreate(MenuVo menuVo) throws Exception {
		return menuDao.selectEzUserMenuCreate(menuVo);
	}
	
	public List<MenuVo> selectEzUserMenuCreateSub(MenuVo menuVo) throws Exception {
		return menuDao.selectEzUserMenuCreateSub(menuVo);
	}

	/** 메뉴경로 */
	public String selectMenuPath(MenuVo menuVo) throws Exception {
		return menuDao.selectMenuPath(menuVo);
	}

	/** 메뉴목록 */
	public List selectMenu(MenuVo menuVo) throws Exception {
		return menuDao.selectMenu(menuVo);
	}
	
	/** 메뉴레벨 */
	public String menuOrderLevel(String sortOrder) throws Exception {
		return menuDao.menuOrderLevel(sortOrder);
	}
	
	/** 타켓정렬 */
	public String targetSortOrder(MenuVo menuVo) throws Exception {
		return menuDao.targetSortOrder(menuVo);
	}
	
	/** 메뉴정렬 */
	public void modifyMenuOrder1(MenuVo menuVo) throws Exception {
		menuDao.updateMenuOrder1(menuVo);
	}
	
	/** 메뉴정렬 */
	public void modifyMenuOrder2(MenuVo menuVo) throws Exception {
		menuDao.updateMenuOrder2(menuVo);
	}
	
	/** 메뉴정렬 */
	public void modifyMenuOrder3(MenuVo menuVo) throws Exception {
		menuDao.updateMenuOrder3(menuVo);
	}
	
	/** 메뉴경로 저장 */
	public void updateMenuPath(MenuVo menuVo) throws Exception {
		menuDao.updateMenuPath(menuVo);
	}
	
	@SuppressWarnings("rawtypes")
	public List selectEzUserMenuAndSubUrls(MenuVo menuVo) throws Exception {
		return menuDao.selectEzUserMenuAndSubUrls(menuVo);
	}
	
	@SuppressWarnings("rawtypes")
	public List selectListTopMenu(MenuVo menuVo) throws Exception {
		return menuDao.selectListTopMenu(menuVo);
	}
	
	@SuppressWarnings("rawtypes")
	public List selectListLeftMenu(MenuVo menuVo) throws Exception {
		return menuDao.selectListLeftMenu(menuVo);
	}
}