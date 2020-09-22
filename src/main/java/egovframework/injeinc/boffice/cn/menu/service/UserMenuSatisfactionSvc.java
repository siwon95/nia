package egovframework.injeinc.boffice.cn.menu.service;

import java.util.List;

import egovframework.injeinc.boffice.cn.menu.vo.UserMenuContentsVo;
import egovframework.injeinc.boffice.cn.menu.vo.UserMenuSatisfactionVo;

public interface UserMenuSatisfactionSvc {
	/**  user_menu_satisfaction 등록 */
	public void registUserMenuSatisfaction(UserMenuSatisfactionVo userMenuSatisfactionVo) throws Exception;
	
	public List pageUserMenuSatisfaction(UserMenuSatisfactionVo userMenuSatisfactionVo) throws Exception;
	
	public int selectUserMenuSatisfactionCnt(UserMenuSatisfactionVo userMenuSatisfactionVo) throws Exception;
	
	public int selectUserMenuSatisfactionIpCnt(UserMenuSatisfactionVo userMenuSatisfactionVo) throws Exception;
	
	/** user_menu_satisfaction 삭제*/
	public void deleteUserMenuSatisfaction(UserMenuSatisfactionVo userMenuSatisfactionVo) throws Exception;
}
