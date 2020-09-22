	package egovframework.injeinc.boffice.cn.menu.service.impl;
	
	import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.injeinc.boffice.cn.menu.dao.UserMenuSatisfactionDao;
import egovframework.injeinc.boffice.cn.menu.service.UserMenuSatisfactionSvc;
import egovframework.injeinc.boffice.cn.menu.vo.UserMenuContentsVo;
import egovframework.injeinc.boffice.cn.menu.vo.UserMenuSatisfactionVo;
import egovframework.injeinc.boffice.ex.bbs.boardAdmin.vo.BoardAdminVo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
	
	@Service("UserMenuSatisfactionSvc")
	public class UserMenuSatisfactionImpl extends EgovAbstractServiceImpl implements UserMenuSatisfactionSvc {
	
		@Resource(name="UserMenuSatisfactionDao")
		UserMenuSatisfactionDao userMenuSatisfactionDao;
		
		/**  user_menu_satisfaction 등록 */
		public void registUserMenuSatisfaction(UserMenuSatisfactionVo userMenuSatisfactionVo) throws Exception {
			userMenuSatisfactionDao.insertUserMenuSatisfaction(userMenuSatisfactionVo);
		}
		
		/**  user_menu_satisfaction 목록 */
		public List pageUserMenuSatisfaction(UserMenuSatisfactionVo userMenuSatisfactionVo) throws Exception {
			return userMenuSatisfactionDao.pageUserMenuSatisfaction(userMenuSatisfactionVo);
		}
		
		public int selectUserMenuSatisfactionCnt(UserMenuSatisfactionVo userMenuSatisfactionVo) throws Exception {
			return userMenuSatisfactionDao.selectUserMenuSatisfactionCnt(userMenuSatisfactionVo);
		}
		
		public int selectUserMenuSatisfactionIpCnt(UserMenuSatisfactionVo userMenuSatisfactionVo) throws Exception {
			return userMenuSatisfactionDao.selectUserMenuSatisfactionIpCnt(userMenuSatisfactionVo);
		}
		
		/** user_menu_contents 삭제*/
		public void deleteUserMenuSatisfaction(UserMenuSatisfactionVo userMenuSatisfactionVo) throws Exception {
			userMenuSatisfactionDao.deleteUserMenuSatisfaction(userMenuSatisfactionVo);
		}
	}
