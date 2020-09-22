package egovframework.injeinc.boffice.ex.holiday.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.injeinc.boffice.ex.holiday.vo.HolidayVo;

@Repository("HolidayDao") 
public class HolidayDao extends EgovAbstractMapper {

	public void insertHoliday(HolidayVo holidayVo) throws Exception {
		insert("HolidayDao.insertHoliday", holidayVo);
	}
	
	public void updateHoliday(HolidayVo holidayVo) throws Exception {
		update("HolidayDao.updateHoliday", holidayVo);
	}
	
	public void deleteHoliday(HolidayVo holidayVo) throws Exception {
		delete("HolidayDao.deleteHoliday", holidayVo);
	}
	
	public HolidayVo selectHoliday(HolidayVo holidayVo) throws Exception {
		return (HolidayVo)selectOne("HolidayDao.selectHoliday", holidayVo);
	}
	
	public int selectHolidayCnt(HolidayVo holidayVo) throws Exception {
		return (Integer)selectOne("HolidayDao.selectHolidayCnt", holidayVo);
	}
	
	@SuppressWarnings("rawtypes")
	public List selectListHoliday(HolidayVo holidayVo) throws Exception {
		return selectList("HolidayDao.selectListHoliday", holidayVo);
	}
	
	@SuppressWarnings("unchecked")
	public List<String> selectListHolidayAll() throws Exception {
		return selectList("HolidayDao.selectListHolidayAll", "");
	}
	
	@SuppressWarnings("unchecked")
	public List<String> selectHolYearGroup() throws Exception {
		return selectList("HolidayDao.selectHolYearGroup", "");
	}
}
