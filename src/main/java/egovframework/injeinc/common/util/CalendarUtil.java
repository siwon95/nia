package egovframework.injeinc.common.util;

import java.util.Calendar;

@SuppressWarnings({ "serial", "rawtypes" })
public class CalendarUtil extends java.util.HashMap {
	
	public CalendarUtil() {
		this(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH)+1);
	}
	
	public CalendarUtil(String year, String month) {
		this(Integer.parseInt(year), Integer.parseInt(month));
	}
	
	@SuppressWarnings("unchecked")
	public CalendarUtil(int year, int month) {
		
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month-1);
		c.set(Calendar.DATE, 1);
		
		int startDayPosi = c.get(Calendar.DAY_OF_WEEK); 
		int maxDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		c.set(Calendar.DATE, maxDay);
		int maxWeek  = c.get(Calendar.WEEK_OF_MONTH);
		int endDayPosi = c.get(Calendar.DAY_OF_WEEK);
		
		this.put("YEAR", year);
		this.put("MONTH", month);
		this.put("DATE", c.get(Calendar.DATE));
		this.put("START_DAY_IN_WEEK", startDayPosi);
		this.put("END_DAY_IN_WEEK", endDayPosi);
		this.put("MAX_DAY", maxDay);
		this.put("MAX_WEEK", maxWeek);
	}
}