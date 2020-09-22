package egovframework.injeinc.boffice.ex.reservationFac.vo;

import java.util.List;

public class ReservationFacTimeVo {		
	
    private String riIdx;
    private int rtmIdx;
    private int rtmWeek;
    private String stTime;
    private String etTime;
    private String rtmType;
    private String rtmTerm;
    
    private String year;
	private String month;
	private String day;
	private String cellNum;
	private String weeks;
	private String week;
	private String restAt;
	private String riReservationYn;
	private String riReservationYn2;
	private String riReservationCnt;
	private String tTypeCnt;
	private String ruReservationDay;
	private String ruLotResult;
	private List<ReservationFacTimeVo> timelist		= null;
    private List<ReservationFacTimeVo> tlist = null;
    
	
    
	public String getRuLotResult() {
		return ruLotResult;
	}
	public void setRuLotResult(String ruLotResult) {
		this.ruLotResult = ruLotResult;
	}
	public String getRiReservationYn2() {
		return riReservationYn2;
	}
	public void setRiReservationYn2(String riReservationYn2) {
		this.riReservationYn2 = riReservationYn2;
	}
	public String getRuReservationDay() {
		return ruReservationDay;
	}
	public void setRuReservationDay(String ruReservationDay) {
		this.ruReservationDay = ruReservationDay;
	}
	public List<ReservationFacTimeVo> getTlist() {
		return tlist;
	}
	public void setTlist(List<ReservationFacTimeVo> tlist) {
		this.tlist = tlist;
	}
	public String gettTypeCnt() {
		return tTypeCnt;
	}
	public void settTypeCnt(String tTypeCnt) {
		this.tTypeCnt = tTypeCnt;
	}
	public List<ReservationFacTimeVo> getTimelist() {
		return timelist;
	}
	public void setTimelist(List<ReservationFacTimeVo> timelist) {
		this.timelist = timelist;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getCellNum() {
		return cellNum;
	}
	public void setCellNum(String cellNum) {
		this.cellNum = cellNum;
	}
	public String getWeeks() {
		return weeks;
	}
	public void setWeeks(String weeks) {
		this.weeks = weeks;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getRestAt() {
		return restAt;
	}
	public void setRestAt(String restAt) {
		this.restAt = restAt;
	}
	public String getRiReservationYn() {
		return riReservationYn;
	}
	public void setRiReservationYn(String riReservationYn) {
		this.riReservationYn = riReservationYn;
	}
	public String getRiReservationCnt() {
		return riReservationCnt;
	}
	public void setRiReservationCnt(String riReservationCnt) {
		this.riReservationCnt = riReservationCnt;
	}

	public String getRiIdx() {
		return riIdx;
	}
	public void setRiIdx(String riIdx) {
		this.riIdx = riIdx;
	}
	public int getRtmIdx() {
		return rtmIdx;
	}
	public void setRtmIdx(int rtmIdx) {
		this.rtmIdx = rtmIdx;
	}
	public int getRtmWeek() {
		return rtmWeek;
	}
	public void setRtmWeek(int rtmWeek) {
		this.rtmWeek = rtmWeek;
	}
	public String getStTime() {
		return stTime;
	}
	public void setStTime(String stTime) {
		this.stTime = stTime;
	}
	public String getEtTime() {
		return etTime;
	}
	public void setEtTime(String etTime) {
		this.etTime = etTime;
	}
	public String getRtmType() {
		return rtmType;
	}
	public void setRtmType(String rtmType) {
		this.rtmType = rtmType;
	}
	public String getRtmTerm() {
		return rtmTerm;
	}
	public void setRtmTerm(String rtmTerm) {
		this.rtmTerm = rtmTerm;
	}
    
    
    
	
    
}
