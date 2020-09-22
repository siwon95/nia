package egovframework.injeinc.boffice.ex.reservation.vo;

import java.util.ArrayList;
import java.util.List;

import egovframework.cmm.ComDefaultVO;

@SuppressWarnings("serial")
public class ReservationUserInfoVo  extends ComDefaultVO  {
	private String riIdx;    //예약 일련번호
	private String riType = "ALL";    //예약 구분값(마이페이지에서 사용)
	private String ruIdx;    //접수자 일련번호
	private String raIdx;    //추가항목 일련번호
	private String ruName;    //접수자명
	private String ruDupkey;    //본인인증키
	private String ruZipcode;    //우편번호
	private String ruAddress;    //주소
	private String ruAddressDtl;    //상세주소
	private String ruTel;    //접수자 전화번호
	private String ruTel1;
	private String ruTel2;
	private String ruTel3;
	private String ruHp;    //접수자 핸드폰번호
	private String ruEmail;    //접수자 Email
	private String ruObjectCnt;    //신청수량
	private String ruReservationDay;    //예약일자
	private String ruReservationName;    //신청자단체명
	private String ruLotResult;    //추첨결과
	private String ruUserloginType;    //로그인 타입
	private String ruIp;    //등록 IP
	private String ruDelYn;    //삭제여부
	private List<String> riIdxList;

	private String regId;
	private String regDt;
	private String modId;
	private String modDt;
	
	private String riUserFileId; // 접수자 첨부파일 아이디
	
	private String payStatus;
	private String payTid;
	private String payAuth;
	private String payMemo;
	private String payPrice;
	private String ordno;
	private String method;
	private String respmsg;
	private String amount;
	private String paytype;
	private String payresult;
	private String rtmIdx;
	private String rtmType;
	private String stfacTime;
	private String edfacTime;
	private String rtmWeek;
	private String ruReservationRetDay;
	private String ruReDay;
	private String ruBirth;
	private String canReason = "";
	
	
	
	
	public String getCanReason() {
		return canReason;
	}
	public void setCanReason(String canReason) {
		this.canReason = canReason;
	}
	public String getRuTel1() {
		return ruTel1;
	}
	public void setRuTel1(String ruTel1) {
		this.ruTel1 = ruTel1;
	}
	public String getRuTel2() {
		return ruTel2;
	}
	public void setRuTel2(String ruTel2) {
		this.ruTel2 = ruTel2;
	}
	public String getRuTel3() {
		return ruTel3;
	}
	public void setRuTel3(String ruTel3) {
		this.ruTel3 = ruTel3;
	}
	public String getRuBirth() {
		return ruBirth;
	}
	public void setRuBirth(String ruBirth) {
		this.ruBirth = ruBirth;
	}
	public String getRuReservationRetDay() {
		return ruReservationRetDay;
	}
	public void setRuReservationRetDay(String ruReservationRetDay) {
		this.ruReservationRetDay = ruReservationRetDay;
	}
	public String getRuReDay() {
		return ruReDay;
	}
	public void setRuReDay(String ruReDay) {
		this.ruReDay = ruReDay;
	}
	public String getRtmIdx() {
		return rtmIdx;
	}
	public void setRtmIdx(String rtmIdx) {
		this.rtmIdx = rtmIdx;
	}
	public String getRtmWeek() {
		return rtmWeek;
	}
	public void setRtmWeek(String rtmWeek) {
		this.rtmWeek = rtmWeek;
	}
	
	public String getRtmType() {
		return rtmType;
	}
	public void setRtmType(String rtmType) {
		this.rtmType = rtmType;
	}
	public String getStfacTime() {
		return stfacTime;
	}
	public void setStfacTime(String stfacTime) {
		this.stfacTime = stfacTime;
	}
	public String getEdfacTime() {
		return edfacTime;
	}
	public void setEdfacTime(String edfacTime) {
		this.edfacTime = edfacTime;
	}
	
	public String getRespmsg() {
		return respmsg;
	}
	public void setRespmsg(String respmsg) {
		this.respmsg = respmsg;
	}
	public String getPayresult() {
		return payresult;
	}
	public void setPayresult(String payresult) {
		this.payresult = payresult;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getPaytype() {
		return paytype;
	}
	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	public String getPayTid() {
		return payTid;
	}
	public void setPayTid(String payTid) {
		this.payTid = payTid;
	}
	public String getPayAuth() {
		return payAuth;
	}
	public void setPayAuth(String payAuth) {
		this.payAuth = payAuth;
	}
	public String getPayMemo() {
		return payMemo;
	}
	public void setPayMemo(String payMemo) {
		this.payMemo = payMemo;
	}
	public String getPayPrice() {
		return payPrice;
	}
	public void setPayPrice(String payPrice) {
		this.payPrice = payPrice;
	}
	public String getOrdno() {
		return ordno;
	}
	public void setOrdno(String ordno) {
		this.ordno = ordno;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
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
	public String getRaIdx() {
		return raIdx == null ? "" : raIdx;
	}
	public void setRaIdx(String raIdx) {
		this.raIdx = raIdx;
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
	
	public String getRiUserFileId() {
		return riUserFileId == null ? "" : riUserFileId;
	}
	public void setRiUserFileId(String riUserFileId) {
		this.riUserFileId = riUserFileId;
	}
	public String getRuObjectCnt() {
		return ruObjectCnt;
	}
	public void setRuObjectCnt(String ruObjectCnt) {
		this.ruObjectCnt = ruObjectCnt;
	}
	public String getRiType() {
		return riType;
	}
	public void setRiType(String riType) {
		this.riType = riType;
	}

}
