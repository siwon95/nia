package egovframework.injeinc.boffice.cn.file.dao;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.injeinc.boffice.cn.file.vo.EzUserFileVo;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("EzUserFileDao") 
public class EzUserFileDao extends EgovAbstractMapper {

	/**  ez_user_file 등록 */
	public void insertEzUserFile(EzUserFileVo ezUserFileVo) throws Exception {
		insert("EzUserFileDao.insertEzUserFile", ezUserFileVo);
	}

	/**  ez_user_file 상세보기 */
	public EzUserFileVo  selectEzUserFile(EzUserFileVo ezUserFileVo) throws Exception {
		return (EzUserFileVo)selectOne("EzUserFileDao.selectPkEzUserFile", ezUserFileVo);
	}
	
	/**  ez_user_file 카운트 */
	public String  selectEzUserFileCount(EzUserFileVo ezUserFileVo) throws Exception {
		return (String)selectOne("EzUserFileDao.selectPkEzUserFileCount", ezUserFileVo);
	}

	/**  ez_user_file 목록 */
	public List selectListEzUserFile(EzUserFileVo ezUserFileVo) throws Exception {
		return selectList("EzUserFileDao.selectEzUserFile", ezUserFileVo);
	}

	/** ez_user_file 수정 */
	public void updateEzUserFile(EzUserFileVo ezUserFileVo) throws Exception {
		update("EzUserFileDao.updateEzUserFile", ezUserFileVo);
	}

	/** ez_user_file 삭제*/
	public void deleteEzUserFile(EzUserFileVo ezUserFileVo) throws Exception {
		delete("EzUserFileDao.deleteEzUserFile", ezUserFileVo);
	}
}
