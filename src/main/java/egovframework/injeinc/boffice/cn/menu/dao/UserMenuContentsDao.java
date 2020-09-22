package egovframework.injeinc.boffice.cn.menu.dao;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.injeinc.boffice.cn.menu.vo.UserMenuContentsVo;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("UserMenuContentsDao") 
public class UserMenuContentsDao extends EgovAbstractMapper {

	/**  user_menu_contents 등록 */
	public void insertUserMenuContents(UserMenuContentsVo userMenuContentsVo) throws Exception {
		insert("UserMenuContentsDao.insertUserMenuContents", userMenuContentsVo);
	}

	/**  user_menu_contents 상세보기 */
	public UserMenuContentsVo  selectUserMenuContents(UserMenuContentsVo userMenuContentsVo) throws Exception {
		return (UserMenuContentsVo)selectOne("UserMenuContentsDao.selectPkUserMenuContents", userMenuContentsVo);
	}
	
	/**  user_menu_contents 최신데이터 상세보기 */
	public UserMenuContentsVo  selectUserMenuContentsMax(UserMenuContentsVo userMenuContentsVo) throws Exception {
		return (UserMenuContentsVo)selectOne("UserMenuContentsDao.selectUserMenuContentsMax", userMenuContentsVo);
	}
	
	/**  user_menu_contents 레코드수 */
	public int  selectUserMenuContentsCount(UserMenuContentsVo userMenuContentsVo) throws Exception {
		return (Integer)selectOne("UserMenuContentsDao.selectPkUserMenuContentsCount", userMenuContentsVo);
	}

	/**  user_menu_contents 목록 */
	public List selectListUserMenuContents(UserMenuContentsVo userMenuContentsVo) throws Exception {
		return selectList("UserMenuContentsDao.selectUserMenuContents", userMenuContentsVo);
	}
	
	public List selectUserMenuContentsNew(UserMenuContentsVo userMenuContentsVo) throws Exception {
		return selectList("UserMenuContentsDao.selectUserMenuContentsNew", userMenuContentsVo);
	}

	/** user_menu_contents 수정 */
	public void updateUserMenuContents(UserMenuContentsVo userMenuContentsVo) throws Exception {
		update("UserMenuContentsDao.updateUserMenuContents", userMenuContentsVo);
	}

	/** user_menu_contents 삭제*/
	public void deleteUserMenuContents(UserMenuContentsVo userMenuContentsVo) throws Exception {
		delete("UserMenuContentsDao.deleteUserMenuContents", userMenuContentsVo);
	}
	
	/**  user_menu_contents 발행대기 컨텐츠 수 */
	public int  selectPublishReqContentsCount(UserMenuContentsVo userMenuContentsVo) throws Exception {
		return (Integer)selectOne("UserMenuContentsDao.selectPublishReqContentsCount", userMenuContentsVo);
	}
	
	/**  user_menu_contents 알림메세지 */
	public List selectUserContentsProgressList(UserMenuContentsVo userMenuContentsVo) throws Exception {
		return selectList("UserMenuContentsDao.selectUserContentsProgressList", userMenuContentsVo);
	}
}
