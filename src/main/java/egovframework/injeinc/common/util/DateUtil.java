package egovframework.injeinc.common.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.time.DateUtils;

public class DateUtil {

	private static final String LONG_DATE_STR = "yyyy-MM-dd HH:mm:ss";
	private static final String SHORT_DATE_STR = "yyyy-MM-dd";
	private static final SimpleDateFormat DEFAULT_DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final SimpleDateFormat DEFAULT_DATEMINUTE_FORMAT = new SimpleDateFormat("mm");
	private static final SimpleDateFormat DEFAULT_DATEHOUR_FORMAT = new SimpleDateFormat("HH");

	private static final SimpleDateFormat MIN_DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");
	private static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat(SHORT_DATE_STR);

	private static final int MILLI_SEC_A_DAY = 1000 * 60 * 60 * 24;

	/**
	 * DEFAULT_DATETIME_FORMAT("yyyy-MM-dd HH:mm:ss") 포멧으로 날짜를 구해오는 메소드
	 * 예)getCurrentDate("yyyyMM") => 200705 로 값이 리턴된다.
	 * @return
	 */
	public static String getCurrentDatetime() {
		return DEFAULT_DATETIME_FORMAT.format(new Date());
	}

	public static String getCurrentDateHour() {
		return DEFAULT_DATEHOUR_FORMAT.format(new Date());
	}

	public static String getCurrentDateMinute() {
		return DEFAULT_DATEMINUTE_FORMAT.format(new Date());
	}

	public static String getCurrentDate() {
		return DEFAULT_DATE_FORMAT.format(new Date());
	}

	public static String getCurrentDate(int i) {
		long now = System.currentTimeMillis();
		now += (i * MILLI_SEC_A_DAY);
		return DEFAULT_DATE_FORMAT.format(new Date(now));
	}

	public static String getCurrentDate(String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		long now = System.currentTimeMillis();
		return dateFormat.format(new Date(now));
	}

	/**
	 * 날짜 String을 Calendar 객체로 변환해주는 메소드.
	 * @param dateStr
	 * @return
	 */
	public static Calendar toCalendar(String dateStr) {
		DateFormat format = getProperFormat(dateStr);

		Date convDate = null;
		try {
			convDate = format.parse(dateStr);
		} catch (ParseException e) {
			//System.out.println(e);
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(convDate);

		return calendar;
	}

	/**
	 * 날짜 String의 길이에 따라 적절한 DateFormat을 반환하는 메소드.
	 * @param dateStr
	 * @return
	 */
	private static DateFormat getProperFormat(String dateStr) {
		DateFormat format = null;
		if(dateStr != null){
			if (dateStr.length() == SHORT_DATE_STR.length()) {
				format = DEFAULT_DATE_FORMAT;
			} else {
				format = DEFAULT_DATETIME_FORMAT;
			}
		}
		return format;
	}

	/**
	 * 날짜 String을 받아서 i 값에서 지정한 날짜만큼 더한 날짜 String을 반환하는 메소드.
	 * @param dateStr
	 * @param i
	 * @return
	 */
	public static String add(String dateStr, int i) {
		String retStr = dateStr;
		if (!dateStr.equals("") && dateStr.length() >= SHORT_DATE_STR.length()) {
			dateStr = dateStr.trim();
			Calendar calendar = toCalendar(dateStr);
			calendar.add(Calendar.DATE, i);
			retStr = getProperFormat(dateStr).format(calendar.getTime());
		}

		return retStr;
	}

	/**
	 * 날짜 String을 받아서 i 값에서 지정한 월만큼 더한 날짜 String을 반환하는 메소드.
	 * @param dateStr
	 * @param i
	 * @return
	 */
	public static String addMonth(String dateStr, int i) {
		String retStr = dateStr;
		if (!dateStr.equals("") && dateStr.length() >= SHORT_DATE_STR.length()) {
			dateStr = dateStr.trim();
			Calendar calendar = toCalendar(dateStr);
			calendar.add(Calendar.MONTH, i);
			retStr = getProperFormat(dateStr).format(calendar.getTime());
		}

		return retStr;
	}

	/**
	 * 날짜 String을 받아서 i 값에서 지정한 연도만큼 더한 날짜 String을 반환하는 메소드.
	 * @param dateStr
	 * @param i
	 * @return
	 */
	public static String addYear(String dateStr, int i) {
		String retStr = dateStr;
		if (!dateStr.equals("") && dateStr.length() >= SHORT_DATE_STR.length()) {
			dateStr = dateStr.trim();
			Calendar calendar = toCalendar(dateStr);
			calendar.add(Calendar.YEAR, i);
			retStr = getProperFormat(dateStr).format(calendar.getTime());
		}

		return retStr;
	}

	/**
	 * "YYYYMMDD" -> "YYYY-MM-DD"
	 * @param date
	 * @return
	 */
	public static String toDefaultDateFormat(String srcDate) {
		Date date = null;
		try {
			date = MIN_DATE_FORMAT.parse(srcDate);
		} catch (ParseException e) {
			//System.out.println(e);
			return srcDate;
		}
		return DEFAULT_DATE_FORMAT.format(date);
	}

	//  입력받은 날짜의 요일을 숫자로 반환
	public static int getDayOfWeek(String dateStr) {
		int day_of_week = 0;
		if (!dateStr.equals("") && dateStr.length() >= SHORT_DATE_STR.length()) {
			dateStr = dateStr.trim();
			Calendar calendar = toCalendar(dateStr);
			day_of_week = calendar.get(Calendar.DAY_OF_WEEK);
		}
		return day_of_week;
	}

	//  추가 20110412
	//  입력받은 int의 숫자에 따라 요일을 반환
	public static String weekToString(int val) {
		String str="";
		
		if(val == 1) {
			str="일";
		}else if(val == 2) {
			str="월";
		}else if(val == 3) {
			str="화";
		}else if(val == 4) {
			str="수";
		}else if(val == 5) {
			str="목";
		}else if(val == 6) {
			str="금";
		}else if(val == 7) {
			str="토";
		}
		
		return str;
	}
	
	//트위터 글쓴 시간차에 따른 표시 
	public static String diffDate(Date regDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY년 MM월 dd일");
		String returnStr = "";

		Date nowDate = new Date();
		long nowTime = nowDate.getTime()/1000;
		long regTime = regDate == null ? 0 : regDate.getTime()/1000;
		long diffTime = nowTime - regTime;
		
		if(diffTime < 60) {
			returnStr = diffTime + "초";
		}else if(diffTime < 60*60) {
			returnStr = diffTime/60 + "분";
		}else if(diffTime < 60*60*24) {
			returnStr = diffTime/(60*60) + "시간";
		}else if(diffTime < 60*60*24*2) {
			returnStr = "1일";
		}else if(diffTime < 60*60*24*3) {
			returnStr = "2일";
		}else if(diffTime < 60*60*24*4) {
			returnStr = "3일";
		}else {
			returnStr = dateFormat.format(regDate);
		}
		
		if(returnStr.startsWith("0")) {
			returnStr = returnStr.substring(1);
		}
		
		return returnStr;
	}
	
	public static String toString(Date date, String format, Locale locale) {

		if (EgovStringUtil.isEmpty(format)) {
			format = LONG_DATE_STR;
		}

		if (locale == null) {
			locale = java.util.Locale.KOREA;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(format, locale);

		return sdf.format(date);
	}
	
	/**
	 * 현재년월일시분(yyyy-MM-dd/HHmm)이 from에서 to사이에 포함되는지
	 * @param from 시작년월일시분 "yyyy-MM-dd/HHmm"
	 * @param to 종료년월일시분 "yyyy-MM-dd/HHmm"
	 * @return true/false
	 */
	public static boolean isTodayBetween(String from, String to, String fmt) {
		
		SimpleDateFormat format = new SimpleDateFormat(fmt);
		
		Date fromDate = null;
		Date toDate = null;
		Date today = null;
		try {
			fromDate = format.parse(from);
			toDate = format.parse(to);
			today = format.parse(format.format(new Date()));
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}

		int compare = fromDate.compareTo( today );
		int compare2 = toDate.compareTo( today );
		 
//		 System.out.println(format.format(today));
//		 System.out.println(compare2);
		 
		 if(compare <= 0 && compare2 >= 0){
			 return true;
		 }
		 
	    return false;
	}
	
	/**
     * @param
     * @return Timestamp 값
     * @exception MyException
     * @see
     */
    public static String getTimeStamp() {

		String rtnStr = null;
	
		// 문자열로 변환하기 위한 패턴 설정(년도-월-일 시:분:초:초(자정이후 초))
		String pattern = "yyyyMMddhhmmssSSS";
		
	    SimpleDateFormat sdfCurrent = new SimpleDateFormat(pattern, Locale.KOREA);
	    Timestamp ts = new Timestamp(System.currentTimeMillis());

	    rtnStr = sdfCurrent.format(ts.getTime());

		return rtnStr;
    }
    
    public static boolean isTodayExpire(String dt, String fmt){
        if(dt.isEmpty() || dt.trim().equals("")){
            return false;
        }else{
                SimpleDateFormat sdf =  new SimpleDateFormat(fmt); // Jan-20-2015 1:30:55 PM
                   Date d=null;
                   Date d1=null;
                String today=   getToday(fmt);
                try {
                    System.out.println("today>> "+today+"\n\n");
                    d = sdf.parse(dt);
                    d1 = sdf.parse(today);
                    if(d1.compareTo(d) <0){// not expired
                        return false;
                    }else if(d.compareTo(d1)==0){// both date are same
                                if(d.getTime() < d1.getTime()){// not expired
                                    return false;
                                }else if(d.getTime() == d1.getTime()){//expired
                                    return true;
                                }else{//expired
                                    return true;
                                }
                    }else{//expired
                        return true;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();                    
                    return false;
                }
        }
    }
    public static boolean isExpire(String standardDt, String compareDt, String fmt){
    	if(standardDt.isEmpty() || standardDt.trim().equals("")){
    		return false;
    	}else{
    		SimpleDateFormat sdf =  new SimpleDateFormat(fmt); // Jan-20-2015 1:30:55 PM
    		Date d=null;
    		Date d1=null;
    		try {
    			d = sdf.parse(standardDt);
    			d1 = sdf.parse(compareDt);
    			if(d1.compareTo(d) <0){// not expired
    				return false;
    			}else if(d.compareTo(d1)==0){// both date are same
    				if(d.getTime() < d1.getTime()){// not expired
    					return false;
    				}else if(d.getTime() == d1.getTime()){//expired
    					return true;
    				}else{//expired
    					return true;
    				}
    			}else{//expired
    				return true;
    			}
    		} catch (ParseException e) {
    			e.printStackTrace();                    
    			return false;
    		}
    	}
    }

	public static String getToday(String format) {
		Date date = new Date();
		return new SimpleDateFormat(format).format(date);
	}

	public static ArrayList<String> getYYYYMMForSdateAndEdate(String pSdate, String pEdate){
		
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 =  new SimpleDateFormat("yyyyMM");
		Date sdate = null;
		Date edate = null;
		Date tmp_date = null;
		ArrayList yyyymm = new ArrayList<String>();
		
		try {
			sdate = sdf.parse(pSdate);
			edate = sdf.parse(pEdate);
			sdate = new Date(sdate.getYear(), sdate.getMonth(), 1);
			edate = new Date(edate.getYear(), edate.getMonth(), 1);
			
			int i = 0;
			while(true){
				
				if(sdate.after(edate)){
					break;
				}
				System.out.println("!!!!"+i);
				yyyymm.add(String.valueOf(sdf2.format(sdate)));
				sdate = DateUtils.addMonths(sdate, 1);
				i++;
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		return yyyymm;
	}
	
	
	public static void main(String[] args) {
		DateUtil du = new DateUtil();
		//System.out.println(du.isExpire("2016-10-10", "2016-10-09", "yyyy-MM-dd"));
		List yyyymm = du.getYYYYMMForSdateAndEdate("2016-10-15","2017-03-16");
		
		for (int i = 0; i < yyyymm.size(); i++) {
			System.out.println(yyyymm.get(i));
		}
	}
}