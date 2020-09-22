	package egovframework.injeinc.boffice.cn.menu.service.impl;
	
	import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.injeinc.boffice.cn.menu.dao.UserMenuContentsDao;
import egovframework.injeinc.boffice.cn.menu.service.UserMenuContentsSvc;
import egovframework.injeinc.boffice.cn.menu.vo.UserMenuContentsVo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
	
	@Service("UserMenuContentsSvc")
	public class UserMenuContentsImpl extends EgovAbstractServiceImpl implements UserMenuContentsSvc {
	
		@Resource(name="UserMenuContentsDao")
		UserMenuContentsDao userMenuContentsDao;
		
		/**  user_menu_contents 등록 */
		public void registUserMenuContents(UserMenuContentsVo userMenuContentsVo) throws Exception {
			userMenuContentsDao.insertUserMenuContents(userMenuContentsVo);
		}
		
		/**  user_menu_contents 상세조회 */
		public UserMenuContentsVo retrieveUserMenuContents(UserMenuContentsVo userMenuContentsVo) throws Exception {
			return userMenuContentsDao.selectUserMenuContents(userMenuContentsVo);
		}
		
		/**  user_menu_contents 최신데이터 상세조회 */
		public UserMenuContentsVo retrieveUserMenuContentsMax(UserMenuContentsVo userMenuContentsVo) throws Exception {
			return userMenuContentsDao.selectUserMenuContentsMax(userMenuContentsVo);
		}
		
		/**  user_menu_contents 레코드수 */
		public int selectUserMenuContentsCount(UserMenuContentsVo userMenuContentsVo) throws Exception {
			return userMenuContentsDao.selectUserMenuContentsCount(userMenuContentsVo);
		}
		
		/**  user_menu_contents 목록 */
		public List selectUserMenuContentsNew(UserMenuContentsVo userMenuContentsVo) throws Exception {
			return userMenuContentsDao.selectUserMenuContentsNew(userMenuContentsVo);
		}
		
		/**  user_menu_contents 목록 */
		public List retrieveListUserMenuContents(UserMenuContentsVo userMenuContentsVo) throws Exception {
			return userMenuContentsDao.selectListUserMenuContents(userMenuContentsVo);
		}
		
		/** user_menu_contents 수정 */
		public void modifyUserMenuContents(UserMenuContentsVo userMenuContentsVo) throws Exception {
			userMenuContentsDao.updateUserMenuContents(userMenuContentsVo);
		}
		
		/** user_menu_contents 삭제*/
		public void removeUserMenuContents(UserMenuContentsVo userMenuContentsVo) throws Exception {
			userMenuContentsDao.deleteUserMenuContents(userMenuContentsVo);
		}
		
		/**  user_menu_contents 발행대기 컨텐츠 수 */
		public int selectPublishReqContentsCount(UserMenuContentsVo userMenuContentsVo) throws Exception{
			return userMenuContentsDao.selectPublishReqContentsCount(userMenuContentsVo);
		}
		
		/**  user_menu_contents 알림메세지 */
		public List selectUserContentsProgressList(UserMenuContentsVo userMenuContentsVo) throws Exception {
			return userMenuContentsDao.selectUserContentsProgressList(userMenuContentsVo);
		}
	}
