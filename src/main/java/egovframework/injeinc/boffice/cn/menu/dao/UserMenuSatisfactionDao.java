package egovframework.injeinc.boffice.cn.menu.dao;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.injeinc.boffice.cn.menu.vo.UserMenuContentsVo;
import egovframework.injeinc.boffice.cn.menu.vo.UserMenuSatisfactionVo;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("UserMenuSatisfactionDao") 
public class UserMenuSatisfactionDao extends EgovAbstractMapper {

	/**  user_menu_satisfaction 등록 */
	public void insertUserMenuSatisfaction(UserMenuSatisfactionVo userMenuSatisfactionVo) throws Exception {
		insert("UserMenuSatisfactionDao.insertUserMenuSatisfaction", userMenuSatisfactionVo);
	}
	/**  user_menu_satisfaction 목록 */
	@SuppressWarnings("unchecked")
	public List pageUserMenuSatisfaction(UserMenuSatisfactionVo userMenuSatisfactionVo) throws Exception {
		return selectList("UserMenuSatisfactionDao.pageUserMenuSatisfaction", userMenuSatisfactionVo);
	}
	/**  user_menu_satisfaction 카운트 */
	public int selectUserMenuSatisfactionCnt(UserMenuSatisfactionVo userMenuSatisfactionVo) throws Exception {
		return (Integer)selectOne("UserMenuSatisfactionDao.selectUserMenuSatisfactionCnt", userMenuSatisfactionVo);
	}
	
	/**  user_menu_satisfaction 카운트 */
	public int selectUserMenuSatisfactionIpCnt(UserMenuSatisfactionVo userMenuSatisfactionVo) throws Exception {
		return (Integer)selectOne("UserMenuSatisfactionDao.selectUserMenuSatisfactionIpCnt", userMenuSatisfactionVo);
	}
	
	/** user_menu_satisfaction 삭제*/
	public void deleteUserMenuSatisfaction(UserMenuSatisfactionVo userMenuSatisfactionVo) throws Exception {
		delete("UserMenuSatisfactionDao.deleteUserMenuSatisfaction", userMenuSatisfactionVo);
	}
}
