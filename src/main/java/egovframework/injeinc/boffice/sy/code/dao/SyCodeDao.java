package egovframework.injeinc.boffice.sy.code.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import egovframework.injeinc.boffice.main.popupZone.vo.MainPopupZoneVo;
import egovframework.injeinc.boffice.sy.code.vo.CmmCodeVo;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

/**
 * DeptDao
 * 2015.06.05 : LDY
 */


@Repository("SyCodeDao")
public class SyCodeDao extends EgovAbstractMapper {
	

	/** 트리 */
	@SuppressWarnings("unchecked")
	public List<Object> selectCode(HashMap<String, String> param) throws Exception {
		return (List<Object>) selectList("SyCodeDao.selectCode", param);
	}
	

	/**
	 * 입력
	 * 
	 * @param paramObject
	 * @return
	 */
	public boolean createCode(HashMap<String, Object> paramObject) throws Exception{
		Integer nResult = 0;
		nResult = update("SyCodeDao.insertCode", paramObject);
		return nResult > 0;
	}
	
	/** 삭제 */
	public void deleteAllCode(HashMap<String, Object> paramObject) throws Exception {
		delete("SyCodeDao.deleteAllCode", paramObject);
	}

	/** 팝업존 코드(메인/서브)*/
	@SuppressWarnings("unchecked")
	public List<CmmCodeVo> selectPopupZoneCodeList(CmmCodeVo popupZoneCode) throws Exception{
		return selectList("SyCodeDao.selectPopupZoneCodeList", popupZoneCode);
	}


	public int selectLastCodeOrdNoByCodeUpr(CmmCodeVo cmmCodeVo) throws Exception{
		return (Integer)selectOne("SyCodeDao.selectLastCodeOrdNoByCodeUpr", cmmCodeVo);
	}
	
	public int selectNowOrdNo(CmmCodeVo cmmCodeVo) throws Exception{
		return (Integer)selectOne("SyCodeDao.selectNowOrdNo", cmmCodeVo);
	}


	public void updateCodeOrdNoPlusAfterOrdNo(int ordNo) throws Exception{
		update("SyCodeDao.updateCodeOrdNoPlusAfterOrdNo", ordNo);
	}


	public void updateCode(CmmCodeVo cmmCodeVo) throws Exception{
		update("SyCodeDao.updateCode", cmmCodeVo);
	}


	public void deleteCode(CmmCodeVo cmmCodeVo) {
		delete("SyCodeDao.deleteCode", cmmCodeVo);
	}


	public void updateCodeOrdNoMinusAfterOrdNo(int ordNo) {
		update("SyCodeDao.updateCodeOrdNoMinusAfterOrdNo", ordNo);
	}


	public void updateCodeOrdNoForUp(CmmCodeVo cmmCodeVo) {
		update("SyCodeDao.updateCodeOrdNoForUp", cmmCodeVo);
	}


	public void updateCodeOrdNoForDown(CmmCodeVo cmmCodeVo) {
		update("SyCodeDao.updateCodeOrdNoForDown", cmmCodeVo);
	}
	
	/*

	public boolean createDept(HashMap<String, Object> paramObject) throws SQLException  {
		Integer nResult = 0;
		SqlMapClient client = getSqlMapClient();
		
		try {
			HashMap<String, Object> output = new HashMap<String, Object>();
			HashMap<String, Object> param = new HashMap<String, Object>();
			
			
			int cnt = (Integer) paramObject.get("objsize");
			
			

			client.startTransaction();

			client.startBatch();
			client.delete("DeptDao.deleteDept", paramObject);

			for(int i=0;i<cnt;i++){
				param.put("sid",paramObject.get("id"+"_"+i));
				param.put("sparent",paramObject.get("parent"+"_"+i));
				param.put("stext",paramObject.get("text"+"_"+i));
				param.put("sposition",paramObject.get("position"+"_"+i));

			    client.insert("DeptDao.insertDept", param);

			}

			client.executeBatch();
 
			client.commitTransaction();
		
			
		} catch (SQLException e1) {
			client.getCurrentConnection().rollback();
			return false;
		}finally{
			client.endTransaction();			
		}
		
		return nResult > 0;
	}
	*/

	
}