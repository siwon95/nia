package egovframework.injeinc.boffice.cn.menu.service;

import java.util.List;

import egovframework.injeinc.boffice.cn.menu.vo.UserMenuContentsVo;

public interface UserMenuContentsSvc {
	/**  user_menu_contents 등록 */
	public void registUserMenuContents(UserMenuContentsVo userMenuContentsVo) throws Exception;
	
	/**  user_menu_contents 상세조회 */
	public UserMenuContentsVo retrieveUserMenuContents(UserMenuContentsVo userMenuContentsVo) throws Exception;
	
	/**  user_menu_contents 최신데이터 상세조회 */
	public UserMenuContentsVo retrieveUserMenuContentsMax(UserMenuContentsVo userMenuContentsVo) throws Exception;
	
	/**  user_menu_contents 레코드수 */
	public int selectUserMenuContentsCount(UserMenuContentsVo userMenuContentsVo) throws Exception;
	
	/**  user_menu_contents 목록 */
	public List retrieveListUserMenuContents(UserMenuContentsVo userMenuContentsVo) throws Exception;
	
	public List selectUserMenuContentsNew(UserMenuContentsVo userMenuContentsVo) throws Exception;
	
	/** user_menu_contents 수정 */
	public void modifyUserMenuContents(UserMenuContentsVo userMenuContentsVo) throws Exception;
	
	/** user_menu_contents 삭제*/
	public void removeUserMenuContents(UserMenuContentsVo userMenuContentsVo) throws Exception;
	
	/**  user_menu_contents 발행대기 컨텐츠 수 */
	public int selectPublishReqContentsCount(UserMenuContentsVo userMenuContentsVo) throws Exception;
	
	/**  user_menu_contents 알림메세지 */
	public List selectUserContentsProgressList(UserMenuContentsVo userMenuContentsVo) throws Exception;
}
