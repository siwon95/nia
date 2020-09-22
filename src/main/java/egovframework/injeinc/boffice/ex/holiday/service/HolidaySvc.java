package egovframework.injeinc.boffice.ex.holiday.service;

import java.util.List;

import egovframework.injeinc.boffice.ex.holiday.vo.HolidayVo;

public interface HolidaySvc {
	
	public void registHoliday(HolidayVo holidayVO) throws Exception;
	public void modifyHoliday(HolidayVo holidayVO) throws Exception;
	public void removeHoliday(HolidayVo holidayVO) throws Exception;
	public HolidayVo retrieveHoliday(HolidayVo holidayVo) throws Exception;
	public int retrieveHolidayCnt(HolidayVo holidayVo) throws Exception;
	@SuppressWarnings("rawtypes")
	public List retrieveListHoliday(HolidayVo holidayVO) throws Exception;
	public List<String> retrieveListHolidayAll() throws Exception;
	public List<String> retrieveHolYearGroup() throws Exception;
	
}
