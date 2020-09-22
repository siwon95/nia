package egovframework.injeinc.boffice.cn.menu.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.injeinc.boffice.cn.menu.vo.MenuVo;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("MenuDao") 
public class MenuDao extends EgovAbstractMapper {
	/** 메뉴코드생성 */
	public MenuVo selectCreateSortOrder(MenuVo menuVo) throws Exception {
		MenuVo sortOrder = (MenuVo)selectOne("MenuDao.selectCreateSortOrder", menuVo);
		return sortOrder;
	}
	
	/** 메뉴코드생성 */
	public int selectHomeCodeChk(MenuVo menuVo) throws Exception {
		int cnt = (Integer)selectOne("MenuDao.selectHomeCodeChk", menuVo);
		return cnt;
	}
	
	/** 메뉴생성 */
	public void insertMenu(MenuVo menuVo) throws Exception {
		insert("MenuDao.insertMenu", menuVo);
	}
	
	/** 메뉴생성 */
	public void insertMenuSubUrls(MenuVo menuVo) throws Exception {
		insert("MenuDao.insertMenuSubUrls", menuVo);
	}
	
	/** 메뉴서브삭제 */
	public void deleteMenuSubUrls(MenuVo menuVo) throws Exception {
		delete("MenuDao.deleteMenuSubUrls", menuVo);
	}
	
	/** 메뉴삭제 */
	public void deleteMenu(MenuVo menuVo) throws Exception {
		delete("MenuDao.deleteMenu", menuVo);
	}
	
	public String selectMenuUnderCount(MenuVo menuVo) throws Exception {
		return (String)selectOne("MenuDao.selectMenuUnderCount", menuVo);
	}
	
	public List<MenuVo> selectMenuSubUrls(MenuVo menuVo) throws Exception {
		return (List<MenuVo>)list("MenuDao.selectMenuSubUrls", menuVo);
	}
	
	/** 메뉴수정 */
	public void updateMenu(MenuVo menuVo) throws Exception {
		update("MenuDao.updateMenu", menuVo);
	}
	
	/** 메뉴히스토리 쌓기 */
	public void insertMenuHistory(MenuVo menuVo) throws Exception {
		insert("MenuDao.insertMenuHistory", menuVo);
	}
	
	/** 링크수정 */
	public void updateLinkMenu(MenuVo menuVo) throws Exception {
		update("MenuDao.updateLinkMenu", menuVo);
	}
	
	/** 메뉴코드상세보기 */
	public MenuVo selectEzUserMenu(MenuVo menuVo) throws Exception {
		return (MenuVo)selectOne("MenuDao.selectEzUserMenu", menuVo);
	}
	
	/** 사용자메뉴 생성 */
	public List<MenuVo> selectEzUserMenuCreate(MenuVo menuVo) throws Exception {
		return (List<MenuVo>)list("MenuDao.selectEzUserMenuCreate", menuVo);
	}
	
	public List<MenuVo> selectEzUserMenuCreateSub(MenuVo menuVo) throws Exception {
		return (List<MenuVo>)list("MenuDao.selectEzUserMenuCreateSub", menuVo);
	}
	
	/** 메뉴경로*/
	public String selectMenuPath(MenuVo menuVo) throws Exception {
		return (String)selectOne("MenuDao.selectMenuPath", menuVo);
	}
	
	/** 메뉴경로*/
	public List selectMenu(MenuVo menuVo) throws Exception {
		return selectList("MenuDao.selectMenu", menuVo);
	}
	
	/** 메뉴레벨*/
	public  String menuOrderLevel(String sortOrder) throws Exception {
		return (String)selectOne("MenuDao.menuOrderLevel", sortOrder);
	}
	
	/** 타켓 정렬*/
	public  String targetSortOrder(MenuVo menuVo) throws Exception {
		return (String)selectOne("MenuDao.targetSortOrder", menuVo);
	}
	
	/** 메뉴 정렬**/
	public void updateMenuOrder1(MenuVo menuVo) throws Exception {
		update("MenuDao.updateMenuOrder1", menuVo);
	}
	
	/** 메뉴 정렬**/
	public void updateMenuOrder2(MenuVo menuVo) throws Exception {
		update("MenuDao.updateMenuOrder2", menuVo);
	}
	
	/** 메뉴 정렬**/
	public void updateMenuOrder3(MenuVo menuVo) throws Exception {
		update("MenuDao.updateMenuOrder3", menuVo);
	}
	
	/** 메뉴 경로 저장**/
	public void updateMenuPath(MenuVo menuVo) throws Exception {
		update("MenuDao.updateMenuPath", menuVo);
	}
	
	@SuppressWarnings("rawtypes")
	public List selectEzUserMenuAndSubUrls(MenuVo menuVo) throws Exception {
		return selectList("MenuDao.selectEzUserMenuAndSubUrls", menuVo);
	}
	
	@SuppressWarnings("rawtypes")
	public List selectListTopMenu(MenuVo menuVo) throws Exception {
		return selectList("MenuDao.selectListTopMenu", menuVo);
	}
	
	@SuppressWarnings("rawtypes")
	public List selectListLeftMenu(MenuVo menuVo) throws Exception {
		return selectList("MenuDao.selectListLeftMenu", menuVo);
	}

}