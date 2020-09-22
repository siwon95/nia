package egovframework.injeinc.boffice.ex.reservationHealth.vo;

public class ReservationHealthDayVo {
    private String riIdx;    //예약일련번호
    private String raIdx;    //추가항목 일련번호
    private String ruReservationDay;    //예약일자
    private String riReservationYn;    //예약가능여부
    private String year;    //년
    private String month;    //월
    private String day;    //일
    private String restdeAt;    //휴일여부
    private String riReservationSdate;    //날짜지정방식(시작일)
	private String riReservationSdateHh;    //날짜지정방식(시작시간)
	private String riReservationSdateMm;    //날짜지정방식(시작분)
	private String riReservationEdate;    //날짜지정방식(종료일)
	private String riReservationEdateHh;    //날짜지정방식(종료시간)
	private String riReservationEdateMm;    //날짜지정방식(종료분)
	private String riConfirmSdate;    //이용일 XX일전(시작일)
	private String riConfirmSdateHh;    //이용일 XX일전(시작시간)
	private String riConfirmSdateMm;    //이용일 XX일전(시작분)
	private String riConfirmEdate;    //이용일 XX일전(종료일)
	private String riConfirmEdateHh;    //이용일 XX일전(종료시간)
	private String riConfirmEdateMm;    //이용일 XX일전(종료분)
	private String riAfterRegsterYn = "Y";    //날짜 지정방식Y/이용일 지정방식N
	private int cellNum = 0;    //달력셀
    private int weeks = 0;    //월별 주순위
    private int maxWeeks = 0;    //월 주수
    private int week = 0;    //요일
    private int startWeekMonth = 0;    //시작요일
    private int lastDayMonth   = 0;    //마지막 일자

	public String getRiIdx() {
		return riIdx == null ? "" : riIdx;
	}
	public void setRiIdx(String riIdx) {
		this.riIdx = riIdx;
	}
	public String getRaIdx() {
		return raIdx == null ? "" : raIdx;
	}
	public void setRaIdx(String raIdx) {
		this.raIdx = raIdx;
	}
	public String getRuReservationDay() {
		return ruReservationDay == null ? "" : ruReservationDay;
	}
	public void setRuReservationDay(String ruReservationDay) {
		this.ruReservationDay = ruReservationDay;
	}
	public String getRiReservationYn() {
		return riReservationYn == null ? "" : riReservationYn;
	}
	public void setRiReservationYn(String riReservationYn) {
		this.riReservationYn = riReservationYn;
	}
	public String getYear() {
		return year == null ? "" : year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month == null ? "" : month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDay() {
		return day == null ? "" : day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getRestdeAt() {
		return restdeAt == null ? "" : restdeAt;
	}
	public void setRestdeAt(String restdeAt) {
		this.restdeAt = restdeAt;
	}
	public int getCellNum() {
		return cellNum;
	}
	public void setCellNum(int cellNum) {
		this.cellNum = cellNum;
	}
	public int getWeeks() {
		return weeks;
	}
	public void setWeeks(int weeks) {
		this.weeks = weeks;
	}
	public int getMaxWeeks() {
		return maxWeeks;
	}
	public void setMaxWeeks(int maxWeeks) {
		this.maxWeeks = maxWeeks;
	}
	public int getWeek() {
		return week;
	}
	public void setWeek(int week) {
		this.week = week;
	}
	public int getStartWeekMonth() {
		return startWeekMonth;
	}
	public void setStartWeekMonth(int startWeekMonth) {
		this.startWeekMonth = startWeekMonth;
	}
	public int getLastDayMonth() {
		return lastDayMonth;
	}
	public void setLastDayMonth(int lastDayMonth) {
		this.lastDayMonth = lastDayMonth;
	}
	
	
	
	
	public String getRiReservationSdate() {
		return riReservationSdate == null ? "" : riReservationSdate;
	}
	public void setRiReservationSdate(String riReservationSdate) {
		this.riReservationSdate = riReservationSdate;
	}
	public String getRiReservationSdateHh() {
		return riReservationSdateHh == null ? "" : riReservationSdateHh;
	}
	public void setRiReservationSdateHh(String riReservationSdateHh) {
		this.riReservationSdateHh = riReservationSdateHh;
	}
	public String getRiReservationSdateMm() {
		return riReservationSdateMm == null ? "" : riReservationSdateMm;
	}
	public void setRiReservationSdateMm(String riReservationSdateMm) {
		this.riReservationSdateMm = riReservationSdateMm;
	}
	public String getRiReservationEdate() {
		return riReservationEdate == null ? "" : riReservationEdate;
	}
	public void setRiReservationEdate(String riReservationEdate) {
		this.riReservationEdate = riReservationEdate;
	}
	public String getRiReservationEdateHh() {
		return riReservationEdateHh == null ? "" : riReservationEdateHh;
	}
	public void setRiReservationEdateHh(String riReservationEdateHh) {
		this.riReservationEdateHh = riReservationEdateHh;
	}
	public String getRiReservationEdateMm() {
		return riReservationEdateMm == null ? "" : riReservationEdateMm;
	}
	public void setRiReservationEdateMm(String riReservationEdateMm) {
		this.riReservationEdateMm = riReservationEdateMm;
	}
	public String getRiConfirmSdate() {
		return riConfirmSdate == null ? "" : riConfirmSdate;
	}
	public void setRiConfirmSdate(String riConfirmSdate) {
		this.riConfirmSdate = riConfirmSdate;
	}
	public String getRiConfirmSdateHh() {
		return riConfirmSdateHh == null ? "" : riConfirmSdateHh;
	}
	public void setRiConfirmSdateHh(String riConfirmSdateHh) {
		this.riConfirmSdateHh = riConfirmSdateHh;
	}
	public String getRiConfirmSdateMm() {
		return riConfirmSdateMm == null ? "" : riConfirmSdateMm;
	}
	public void setRiConfirmSdateMm(String riConfirmSdateMm) {
		this.riConfirmSdateMm = riConfirmSdateMm;
	}
	public String getRiConfirmEdate() {
		return riConfirmEdate == null ? "" : riConfirmEdate;
	}
	public void setRiConfirmEdate(String riConfirmEdate) {
		this.riConfirmEdate = riConfirmEdate;
	}
	public String getRiConfirmEdateHh() {
		return riConfirmEdateHh == null ? "" : riConfirmEdateHh;
	}
	public void setRiConfirmEdateHh(String riConfirmEdateHh) {
		this.riConfirmEdateHh = riConfirmEdateHh;
	}
	public String getRiConfirmEdateMm() {
		return riConfirmEdateMm == null ? "" : riConfirmEdateMm;
	}
	public void setRiConfirmEdateMm(String riConfirmEdateMm) {
		this.riConfirmEdateMm = riConfirmEdateMm;
	}
	public String getRiAfterRegsterYn() {
		return riAfterRegsterYn == null ? "" : riAfterRegsterYn;
	}
	public void setRiAfterRegsterYn(String riAfterRegsterYn) {
		this.riAfterRegsterYn = riAfterRegsterYn;
	}
	
	
	
}
