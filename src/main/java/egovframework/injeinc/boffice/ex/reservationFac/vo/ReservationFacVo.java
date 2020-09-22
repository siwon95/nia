package egovframework.injeinc.boffice.ex.reservationFac.vo;

public class ReservationFacVo {
    private String riIdx;    //예약일련번호
    private String raIdx;    //추가항목 일련번호
    private String ruReservationDay;    //예약일자
    private String riReservationYn;    //예약가능여부
    private String year;    //년
    private String month;    //월
    private String day;    //일
    private String restdeAt;    //휴일여부
	private int cellNum = 0;    //달력셀
    private int weeks = 0;    //월별 주순위
    private int maxWeeks = 0;    //월 주수
    private int week = 0;    //요일
    private int startWeekMonth = 0;    //시작요일
    private int lastDayMonth   = 0;    //마지막 일자
    private String riAfterRegsterYn;
    private String riConfirmSdate;    //발표기간,등록기간(시작일)
	private String riConfirmSdateHh;    //발표기간,등록기간(시작일)
	private String riConfirmSdateMm;    //발표기간,등록기간(시작일)
	private String riConfirmEdate;    //발표기간,등록기간(종료일)
	private String riConfirmEdateHh;    //발표기간,등록기간(종료일)
	private String riConfirmEdateMm;    //발표기간,등록기간(종료일)
	private String rtmIdx;
	private String timeweek;
	private String cnt;
	private String restAt;
	
	

	public String getRestAt() {
		return restAt;
	}
	public void setRestAt(String restAt) {
		this.restAt = restAt;
	}
	public String getCnt() {
		return cnt;
	}
	public void setCnt(String cnt) {
		this.cnt = cnt;
	}
	public String getTimeweek() {
		return timeweek;
	}
	public void setTimeweek(String timeweek) {
		this.timeweek = timeweek;
	}
	public String getRtmIdx() {
		return rtmIdx;
	}
	public void setRtmIdx(String rtmIdx) {
		this.rtmIdx = rtmIdx;
	}
	public String getRiConfirmSdate() {
		return riConfirmSdate;
	}
	public void setRiConfirmSdate(String riConfirmSdate) {
		this.riConfirmSdate = riConfirmSdate;
	}
	public String getRiConfirmSdateHh() {
		return riConfirmSdateHh;
	}
	public void setRiConfirmSdateHh(String riConfirmSdateHh) {
		this.riConfirmSdateHh = riConfirmSdateHh;
	}
	public String getRiConfirmSdateMm() {
		return riConfirmSdateMm;
	}
	public void setRiConfirmSdateMm(String riConfirmSdateMm) {
		this.riConfirmSdateMm = riConfirmSdateMm;
	}
	public String getRiConfirmEdate() {
		return riConfirmEdate;
	}
	public void setRiConfirmEdate(String riConfirmEdate) {
		this.riConfirmEdate = riConfirmEdate;
	}
	public String getRiConfirmEdateHh() {
		return riConfirmEdateHh;
	}
	public void setRiConfirmEdateHh(String riConfirmEdateHh) {
		this.riConfirmEdateHh = riConfirmEdateHh;
	}
	public String getRiConfirmEdateMm() {
		return riConfirmEdateMm;
	}
	public void setRiConfirmEdateMm(String riConfirmEdateMm) {
		this.riConfirmEdateMm = riConfirmEdateMm;
	}
	public String getRiAfterRegsterYn() {
		return riAfterRegsterYn;
	}
	public void setRiAfterRegsterYn(String riAfterRegsterYn) {
		this.riAfterRegsterYn = riAfterRegsterYn;
	}
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
}
