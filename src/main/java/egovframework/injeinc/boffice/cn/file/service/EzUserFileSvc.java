package egovframework.injeinc.boffice.cn.file.service;

import java.util.List;

import egovframework.injeinc.boffice.cn.file.vo.EzUserFileVo;

public interface EzUserFileSvc {
	/**  ez_user_file 등록 */
	public void registEzUserFile(EzUserFileVo ezUserFileVo) throws Exception;
	
	/**  ez_user_file 상세조회 */
	public EzUserFileVo retrieveEzUserFile(EzUserFileVo ezUserFileVo) throws Exception;
	
	/**  ez_user_file 상세조회 */
	public String retrieveEzUserFileCount(EzUserFileVo ezUserFileVo) throws Exception;
	
	/**  ez_user_file 목록 */
	public List retrieveListEzUserFile(EzUserFileVo ezUserFileVo) throws Exception;
	
	/** ez_user_file 수정 */
	public void modifyEzUserFile(EzUserFileVo ezUserFileVo) throws Exception;
	
	/** ez_user_file 삭제*/
	public void removeEzUserFile(EzUserFileVo ezUserFileVo) throws Exception;
}
