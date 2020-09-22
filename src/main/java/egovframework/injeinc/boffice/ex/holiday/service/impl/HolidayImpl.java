package egovframework.injeinc.boffice.ex.holiday.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.injeinc.boffice.ex.holiday.dao.HolidayDao;
import egovframework.injeinc.boffice.ex.holiday.service.HolidaySvc;
import egovframework.injeinc.boffice.ex.holiday.vo.HolidayVo;

@Service("HolidaySvc")
public class HolidayImpl implements HolidaySvc {

	@Resource(name = "HolidayDao")
	private HolidayDao holidayDao;
	
	public void registHoliday(HolidayVo holidayVo) throws Exception {
		holidayDao.insertHoliday(holidayVo);
	}
	
	public void modifyHoliday(HolidayVo holidayVo) throws Exception {
		holidayDao.updateHoliday(holidayVo);
	}
	
	public void removeHoliday(HolidayVo holidayVo) throws Exception {
		holidayDao.deleteHoliday(holidayVo);
	}

	public HolidayVo retrieveHoliday(HolidayVo holidayVo) throws Exception {
		return holidayDao.selectHoliday(holidayVo);
	}

	public int retrieveHolidayCnt(HolidayVo holidayVo) throws Exception {
		return holidayDao.selectHolidayCnt(holidayVo);
	}

	@SuppressWarnings("rawtypes")
	public List retrieveListHoliday(HolidayVo holidayVo) throws Exception {
		return holidayDao.selectListHoliday(holidayVo);
	}

	public List<String> retrieveListHolidayAll() throws Exception {
		return holidayDao.selectListHolidayAll();
	}

	public List<String> retrieveHolYearGroup() throws Exception {
		return holidayDao.selectHolYearGroup();
	}
}
