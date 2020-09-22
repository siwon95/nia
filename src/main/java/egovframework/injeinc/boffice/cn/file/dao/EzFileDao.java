package egovframework.injeinc.boffice.cn.file.dao;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.injeinc.boffice.cn.file.vo.EzFileVo;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("EzFileDao") 
public class EzFileDao extends EgovAbstractMapper {

	/**  ez_file 등록 */
	public void insertEzFile(EzFileVo ezFileVo) throws Exception {
		insert("EzFileDao.insertEzFile", ezFileVo);
	}
	/**  ez_file 등록 */
	public void insertEzFileDetail(EzFileVo ezFileVo) throws Exception {
		insert("EzFileDao.insertEzFileDetail", ezFileVo);
	}

	
	/**  ez_file 상세보기 */
	public EzFileVo  selectEzFile(EzFileVo ezFileVo) throws Exception {
		return (EzFileVo)selectOne("EzFileDao.selectPkEzFile", ezFileVo);
	}
	
	/**  ez_file MAX */
	public String  selectEzFileKey(EzFileVo ezFileVo) throws Exception {
		return (String)selectOne("EzFileDao.selectPkEzFileKey", ezFileVo);
	}
	
	/**  ez_filedetail Sn MAX */
	public String  selectEzFileDetailSn(EzFileVo ezFileVo) throws Exception {
		return (String)selectOne("EzFileDao.selectPkEzFileDetailSn", ezFileVo);
	}

	/**  ez_file 목록 */
	public List selectListEzFile(EzFileVo ezFileVo) throws Exception {
		return selectList("EzFileDao.selectEzFile", ezFileVo);
	}

	/** ez_file 수정 */
	public void updateEzFile(EzFileVo ezFileVo) throws Exception {
		update("EzFileDao.updateEzFile", ezFileVo);
	}

	/** ez_file 삭제*/
	public void deleteEzFile(EzFileVo ezFileVo) throws Exception {
		delete("EzFileDao.deleteEzFile", ezFileVo);
	}
	
	/** ez_file 제정렬*/
	public void deleteEzFileSort(EzFileVo ezFileVo) throws Exception {
		delete("EzFileDao.deleteEzFileSort", ezFileVo);
	}
}
