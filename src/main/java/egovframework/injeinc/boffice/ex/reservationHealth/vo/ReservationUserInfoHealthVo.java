package egovframework.injeinc.boffice.ex.reservationHealth.vo;

import java.util.ArrayList;
import java.util.List;

import egovframework.cmm.ComDefaultVO;

public class ReservationUserInfoHealthVo  extends ComDefaultVO  {
	private String ruIdx;    //접수자 일련번호
	private String riIdx;    //예약 일련번호
	private String rhIdx;    //요일별 회차
	private String riWeek;		//예약요일
	private String rhRecruitCnt;	//신청인원
	private String ruName;    //접수자명
	private String ruDupkey;    //본인인증키
	private String ruZipcode;    //우편번호
	private String ruAddress;    //주소
	private String ruAddressDtl;    //상세주소
	private String ruTel;    //접수자 전화번호
	private String ruHp;    //접수자 핸드폰번호
	private String ruEmail;    //접수자 Email
	private String ruReservationDay;    //예약일자
	private String ruReservationName;    //신청자단체명
	private String ruLotResult;    //추첨결과
	private String ruUserloginType;    //로그인 타입
	private String ruIp;    //등록 IP
	private String ruDelYn;    //삭제여부
	private String riConfirmSdateHh;    //회차시작시간
	private String riConfirmSdateMm;    //회차시작분
	
	private List<String> riIdxList;

	private String regId;
	private String regDt;
	private String modId;
	private String modDt;
	
	private String hYear;
	private String hMonth;
	private String hDay;
	private String hWeek;

	public String getRiIdx() {
		return riIdx == null ? "" : riIdx;
	}
	public void setRiIdx(String riIdx) {
		this.riIdx = riIdx;
	}
	public String getRuIdx() {
		return ruIdx == null ? "" : ruIdx;
	}
	public void setRuIdx(String ruIdx) {
		this.ruIdx = ruIdx;
	}
	public String getRhIdx() {
		return rhIdx == null ? "" : rhIdx;
	}
	public void setRhIdx(String rhIdx) {
		this.rhIdx = rhIdx;
	}
	public String getRiWeek() {
		return riWeek == null ? "" : riWeek;
	}
	public void setRiWeek(String riWeek) {
		this.riWeek = riWeek;
	}
	public String getRhRecruitCnt() {
		return rhRecruitCnt == null ? "" : rhRecruitCnt;
	}
	public void setRhRecruitCnt(String rhRecruitCnt) {
		this.rhRecruitCnt = rhRecruitCnt;
	}
	public String getRuName() {
		return ruName == null ? "" : ruName;
	}
	public void setRuName(String ruName) {
		this.ruName = ruName;
	}
	public String getRuDupkey() {
		return ruDupkey == null ? "" : ruDupkey;
	}
	public void setRuDupkey(String ruDupkey) {
		this.ruDupkey = ruDupkey;
	}
	public String getRuZipcode() {
		return ruZipcode == null ? "" : ruZipcode;
	}
	public void setRuZipcode(String ruZipcode) {
		this.ruZipcode = ruZipcode;
	}
	public String getRuAddress() {
		return ruAddress == null ? "" : ruAddress;
	}
	public void setRuAddress(String ruAddress) {
		this.ruAddress = ruAddress;
	}
	public String getRuAddressDtl() {
		return ruAddressDtl == null ? "" : ruAddressDtl;
	}
	public void setRuAddressDtl(String ruAddressDtl) {
		this.ruAddressDtl = ruAddressDtl;
	}
	public String getRuTel() {
		return ruTel == null ? "" : ruTel;
	}
	public void setRuTel(String ruTel) {
		this.ruTel = ruTel;
	}
	public String getRuHp() {
		return ruHp == null ? "" : ruHp;
	}
	public void setRuHp(String ruHp) {
		this.ruHp = ruHp;
	}
	public String getRuEmail() {
		return ruEmail == null ? "" : ruEmail;
	}
	public void setRuEmail(String ruEmail) {
		this.ruEmail = ruEmail;
	}
	public String getRuReservationDay() {
		return ruReservationDay == null ? "" : ruReservationDay;
	}
	public void setRuReservationDay(String ruReservationDay) {
		this.ruReservationDay = ruReservationDay;
	}
	public String getRuReservationName() {
		return ruReservationName == null ? "" : ruReservationName;
	}
	public void setRuReservationName(String ruReservationName) {
		this.ruReservationName = ruReservationName;
	}
	public String getRuLotResult() {
		return ruLotResult == null ? "" : ruLotResult;
	}
	public void setRuLotResult(String ruLotResult) {
		this.ruLotResult = ruLotResult;
	}
	public String getRuUserloginType() {
		return ruUserloginType == null ? "" : ruUserloginType;
	}
	public void setRuUserloginType(String ruUserloginType) {
		this.ruUserloginType = ruUserloginType;
	}
	public String getRuIp() {
		return ruIp == null ? "" : ruIp;
	}
	public void setRuIp(String ruIp) {
		this.ruIp = ruIp;
	}
	public String getRuDelYn() {
		return ruDelYn == null ? "" : ruDelYn;
	}
	public void setRuDelYn(String ruDelYn) {
		this.ruDelYn = ruDelYn;
	}
	public List<String> getRiIdxList() {
		List<String> arrRet = new ArrayList<String>(); 
		if(this.riIdxList != null){
			arrRet.addAll(riIdxList);
		}
		return arrRet;
	}
	public void setRiIdxList(List<String> riIdxList) {
		this.riIdxList = new ArrayList<String>();
		if(riIdxList != null){
			this.riIdxList.addAll(riIdxList);
		}
	}
	public String getRegId() {
		return regId == null ? "" : regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getRegDt() {
		return regDt == null ? "" : regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getModId() {
		return modId == null ? "" : modId;
	}
	public void setModId(String modId) {
		this.modId = modId;
	}
	public String getModDt() {
		return modDt == null ? "" : modDt;
	}
	public void setModDt(String modDt) {
		this.modDt = modDt;
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
	public String gethYear() {
		return hYear == null ? "" : hYear;
	}
	public void sethYear(String hYear) {
		this.hYear = hYear;
	}
	public String gethMonth() {
		return hMonth == null ? "" : hMonth;
	}
	public void sethMonth(String hMonth) {
		this.hMonth = hMonth;
	}
	public String gethDay() {
		return hDay == null ? "" : hDay;
	}
	public void sethDay(String hDay) {
		this.hDay = hDay;
	}
	public String gethWeek() {
		return hWeek == null ? "" : hWeek;
	}
	public void sethWeek(String hWeek) {
		this.hWeek = hWeek;
	}
	
	
	
	

}
