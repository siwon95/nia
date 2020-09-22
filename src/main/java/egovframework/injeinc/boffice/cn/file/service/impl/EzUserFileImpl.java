	package egovframework.injeinc.boffice.cn.file.service.impl;
	
	import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.injeinc.boffice.cn.file.dao.EzUserFileDao;
import egovframework.injeinc.boffice.cn.file.service.EzUserFileSvc;
import egovframework.injeinc.boffice.cn.file.vo.EzUserFileVo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
	
	@Service("EzUserFileSvc")
	public class EzUserFileImpl extends EgovAbstractServiceImpl implements EzUserFileSvc {
	
		@Resource(name="EzUserFileDao")
		EzUserFileDao ezUserFileDao;
		
		/**  ez_user_file 등록 */
		public void registEzUserFile(EzUserFileVo ezUserFileVo) throws Exception {
			ezUserFileDao.insertEzUserFile(ezUserFileVo);
		}
		
		/**  ez_user_file 상세조회 */
		public EzUserFileVo retrieveEzUserFile(EzUserFileVo ezUserFileVo) throws Exception {
			return ezUserFileDao.selectEzUserFile(ezUserFileVo);
		}
		/**  ez_user_file count */
		public String retrieveEzUserFileCount(EzUserFileVo ezUserFileVo) throws Exception {
			return ezUserFileDao.selectEzUserFileCount(ezUserFileVo);
		}
			
		/**  ez_user_file 목록 */
		public List retrieveListEzUserFile(EzUserFileVo ezUserFileVo) throws Exception {
			return ezUserFileDao.selectListEzUserFile(ezUserFileVo);
		}
		
		/** ez_user_file 수정 */
		public void modifyEzUserFile(EzUserFileVo ezUserFileVo) throws Exception {
			ezUserFileDao.updateEzUserFile(ezUserFileVo);
		}
		
		/** ez_user_file 삭제*/
		public void removeEzUserFile(EzUserFileVo ezUserFileVo) throws Exception {
			ezUserFileDao.deleteEzUserFile(ezUserFileVo);
		}
	}
