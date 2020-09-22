package egovframework.injeinc.boffice.ex.reservationRent.vo;

import egovframework.cmm.ComDefaultVO;

@SuppressWarnings("serial")
public class ReservationRentVo extends ComDefaultVO {
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
