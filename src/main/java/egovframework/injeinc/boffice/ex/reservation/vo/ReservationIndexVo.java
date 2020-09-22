package egovframework.injeinc.boffice.ex.reservation.vo;

import java.util.ArrayList;
import java.util.List;

import egovframework.cmm.ComDefaultVO;

@SuppressWarnings("serial")
public class ReservationIndexVo extends ComDefaultVO  {
	private String riIdx;    //예약 일련번호
	private String ruIdx;    //접수자 일련번호
	private String riType;    //예약구분
	private String riTitle;    //제목
	private String riSupervisionOrg;    //주관기관코드
	private String riSupervisionOrgName;    //주관기관명
	private String riManageDept;    //관리부서코드
	private String riManageDeptTxt;    //관리부서명
	private String riTel1;    //전화번호1
	private String riTel2;    //전화번호2
	private String riTel3;    //전화번호3
	private String riPlace;    //장소
	private String riPayYn = "N";    //참가비여부
	private String riPay;    //참가비
	private String riGuide;    //상세소개
	private String riNameCheck = "Y";    //입력항목 사용여부(이름)
	private String riAddressCheck = "N";    //입력항목 사용여부(주소)
	private String riTelCheck = "N";    //입력항목 사용여부(전화번호)
	private String riHpCheck = "Y";    //입력항목 사용여부(핸드폰번호)
	private String riEmailCheck = "N";    //입력항목 사용여부(이메일)
	private String riProgress;    //진행상황
	private String riDayWeek;    //이용가능요일
	private String riLotUse = "OL";    //추첨방법
	private String riOffLotUse;    //오프라인 추첨방법
	private String riTermType = "O";    //행사기간 구분
	private String riSdate;    //행사기간(시작일)
	private String riEdate;    //행사기간(종료일)
	private String riRedundancy;    //다른접수와 중복접수제한
	private String riHaveFac;    //구비시설
	private String riSiteUrl;    //사이트주소
	private String riUserloginType;    //등록아이디 로그인 타입
	private String riRecruitType;    //모집인원 제한여부
	private String riRecruitYn = "Y";    //모집인원 제한여부
	private String riRecruitCnt;    //모집인원/모집수량
	private String riRecruitCntById;    //아이디별 신청수량(기타예약에서 모집수량 선택 시)
	private String riRecruitMaxCnt;    //최대수용인원
	private String riReservationSdt;    //예약기간(시작일)
	private String riReservationSdate;    //예약기간(시작일)
	private String riReservationSdateHh;    //예약기간(시작일)
	private String riReservationSdateMm;    //예약기간(시작일)
	private String riReservationEdt;    //예약기간(종료일)
	private String riReservationEdate;    //예약기간(종료일)
	private String riReservationEdateHh;    //예약기간(종료일)
	private String riReservationEdateMm;    //예약기간(종료일)
	private String riConfirmSdate;    //발표기간,등록기간(시작일)
	private String riConfirmSdateHh;    //발표기간,등록기간(시작일)
	private String riConfirmSdateMm;    //발표기간,등록기간(시작일)
	private String riConfirmEdate;    //발표기간,등록기간(종료일)
	private String riConfirmEdateHh;    //발표기간,등록기간(종료일)
	private String riConfirmEdateMm;    //발표기간,등록기간(종료일)
	private String riAfterRegsterYn = "N";    //발표후 등록여부
	private String riConfirmYn;    //승인여부
	private String riImageFileId;    //대표이미지 파일 아이디
	private int riAccCnt = 0;    //접수인원
	private int riReadCnt = 0;    //조회수
	private String ruReservationDay;    //예약일자
	private String ruReservationTime;    //예약시간
	private String ruLotResult;    //예약결과
	private String ruDupkey;    //본인인증키
	private String useYn;    //삭제여부
	
	private int riTempCnt = 0;    //예비접수
	
	private String regId;    //등록아이디
	private String regName;    //등록이름
	private String regDt;    //등록일
	private String modId;    //수정아이디
	private String modDt;    //수정일
	private String userReg;    //사용자에서 등록했는지 여부
	private String mgrName;    //관리자이름

	private String  actionkey;    //등록수정여부
	private List<String> strList;
	
	private String searchOrg;	//시설기관검색

	private String  riFileYn;    //등록수정여부
	private int riFileCnt = 0;    //접수자 파일첨부 가능 수
	private String riReservationCnt;
	private String riReservationstd;
	private String riReservationetd;
	private String riUserCnt;
	private String riMainFileNm;
	private String riOnlinePayYn;
	private String riRservationminTime;
	private String riRservationmaxTime;
	private String eventRiNum;
	private String riPoYn; //개인단체 구분
	private String riFeeGubun;
	private int riRentCnt;
	private String siteCd = "";
	private String siteNm = "";
	private String riEventType = "";
	private String riEventTypeNm = "";
	private String payStatus = "";
	private String raTitle = "";
	private String raContent = "";
	private String raTitleT = "";
	private String raContentT = "";
	private String pgKey = "";
	private List<ReservationIndexVo> ralist		= null;
	
	private List<String> mgrSiteCdList = null; 
	
	
	
	public String getPgKey() {
		return pgKey;
	}
	public void setPgKey(String pgKey) {
		this.pgKey = pgKey;
	}
	public String getRaTitleT() {
		return raTitleT;
	}
	public void setRaTitleT(String raTitleT) {
		this.raTitleT = raTitleT;
	}
	public String getRaContentT() {
		return raContentT;
	}
	public void setRaContentT(String raContentT) {
		this.raContentT = raContentT;
	}
	public String getRaTitle() {
		return raTitle;
	}
	public void setRaTitle(String raTitle) {
		this.raTitle = raTitle;
	}
	public String getRaContent() {
		return raContent;
	}
	public void setRaContent(String raContent) {
		this.raContent = raContent;
	}
	public List<ReservationIndexVo> getRalist() {
		return ralist;
	}
	public void setRalist(List<ReservationIndexVo> ralist) {
		this.ralist = ralist;
	}
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	public String getRiEventType() {
		return riEventType;
	}
	public void setRiEventType(String riEventType) {
		this.riEventType = riEventType;
	}
	public int getRiRentCnt() {
		return riRentCnt;
	}
	public void setRiRentCnt(int riRentCnt) {
		this.riRentCnt = riRentCnt;
	}
	public String getSiteCd() {
		return siteCd;
	}
	public void setSiteCd(String siteCd) {
		this.siteCd = siteCd;
	}
	public String getSiteNm() {
		return siteNm;
	}
	public void setSiteNm(String siteNm) {
		this.siteNm = siteNm;
	}
	
	public String getRiFeeGubun() {
		return riFeeGubun;
	}
	public void setRiFeeGubun(String riFeeGubun) {
		this.riFeeGubun = riFeeGubun;
	}
	public String getRiPoYn() {
		return riPoYn;
	}
	public void setRiPoYn(String riPoYn) {
		this.riPoYn = riPoYn;
	}
	public String getEventRiNum() {
		return eventRiNum;
	}
	public void setEventRiNum(String eventRiNum) {
		this.eventRiNum = eventRiNum;
	}
	public String getRiRservationminTime() {
		return riRservationminTime;
	}
	public void setRiRservationminTime(String riRservationminTime) {
		this.riRservationminTime = riRservationminTime;
	}
	public String getRiRservationmaxTime() {
		return riRservationmaxTime;
	}
	public void setRiRservationmaxTime(String riRservationmaxTime) {
		this.riRservationmaxTime = riRservationmaxTime;
	}
	public String getRiOnlinePayYn() {
		return riOnlinePayYn;
	}
	public void setRiOnlinePayYn(String riOnlinePayYn) {
		this.riOnlinePayYn = riOnlinePayYn;
	}
	public String getRiMainFileNm() {
		return riMainFileNm;
	}
	public void setRiMainFileNm(String riMainFileNm) {
		this.riMainFileNm = riMainFileNm;
	}
	public String getRiReservationstd() {
		return riReservationstd;
	}
	public void setRiReservationstd(String riReservationstd) {
		this.riReservationstd = riReservationstd;
	}
	public String getRiReservationetd() {
		return riReservationetd;
	}
	public void setRiReservationetd(String riReservationetd) {
		this.riReservationetd = riReservationetd;
	}
	public String getRiUserCnt() {
		return riUserCnt;
	}
	public void setRiUserCnt(String riUserCnt) {
		this.riUserCnt = riUserCnt;
	}
	public String getRiReservationCnt() {
		return riReservationCnt;
	}
	public void setRiReservationCnt(String riReservationCnt) {
		this.riReservationCnt = riReservationCnt;
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
	public String getRiType() {
		return riType == null ? "" : riType;
	}
	public void setRiType(String riType) {
		this.riType = riType;
	}
	public String getRiTitle() {
		return riTitle == null ? "" : riTitle;
	}
	public void setRiTitle(String riTitle) {
		this.riTitle = riTitle;
	}
	public String getRiSupervisionOrg() {
		return riSupervisionOrg == null ? "" : riSupervisionOrg;
	}
	public void setRiSupervisionOrg(String riSupervisionOrg) {
		this.riSupervisionOrg = riSupervisionOrg;
	}
	public String getRiSupervisionOrgName() {
		return riSupervisionOrgName;
	}
	public void setRiSupervisionOrgName(String riSupervisionOrgName) {
		this.riSupervisionOrgName = riSupervisionOrgName;
	}
	public String getRiManageDept() {
		return riManageDept == null ? "" : riManageDept;
	}
	public void setRiManageDept(String riManageDept) {
		this.riManageDept = riManageDept;
	}
	public String getRiManageDeptTxt() {
		return riManageDeptTxt == null ? "" : riManageDeptTxt;
	}
	public void setRiManageDeptTxt(String riManageDeptTxt) {
		this.riManageDeptTxt = riManageDeptTxt;
	}
	public String getRiTel1() {
		return riTel1 == null ? "" : riTel1;
	}
	public void setRiTel1(String riTel1) {
		this.riTel1 = riTel1;
	}
	public String getRiTel2() {
		return riTel2 == null ? "" : riTel2;
	}
	public void setRiTel2(String riTel2) {
		this.riTel2 = riTel2;
	}
	public String getRiTel3() {
		return riTel3 == null ? "" : riTel3;
	}
	public void setRiTel3(String riTel3) {
		this.riTel3 = riTel3;
	}
	public String getRiPlace() {
		return riPlace == null ? "" : riPlace;
	}
	public void setRiPlace(String riPlace) {
		this.riPlace = riPlace;
	}
	public String getRiPayYn() {
		return riPayYn == null ? "" : riPayYn;
	}
	public void setRiPayYn(String riPayYn) {
		this.riPayYn = riPayYn;
	}
	public String getRiPay() {
		return riPay == null ? "" : riPay;
	}
	public void setRiPay(String riPay) {
		this.riPay = riPay;
	}
	public String getRiGuide() {
		return riGuide == null ? "" : riGuide;
	}
	public void setRiGuide(String riGuide) {
		this.riGuide = riGuide;
	}
	public String getRiNameCheck() {
		return riNameCheck == null ? "" : riNameCheck;
	}
	public void setRiNameCheck(String riNameCheck) {
		this.riNameCheck = riNameCheck;
	}
	public String getRiAddressCheck() {
		return riAddressCheck == null ? "" : riAddressCheck;
	}
	public void setRiAddressCheck(String riAddressCheck) {
		this.riAddressCheck = riAddressCheck;
	}
	public String getRiTelCheck() {
		return riTelCheck == null ? "" : riTelCheck;
	}
	public void setRiTelCheck(String riTelCheck) {
		this.riTelCheck = riTelCheck;
	}
	public String getRiHpCheck() {
		return riHpCheck == null ? "" : riHpCheck;
	}
	public void setRiHpCheck(String riHpCheck) {
		this.riHpCheck = riHpCheck;
	}
	public String getRiEmailCheck() {
		return riEmailCheck == null ? "" : riEmailCheck;
	}
	public void setRiEmailCheck(String riEmailCheck) {
		this.riEmailCheck = riEmailCheck;
	}
	public String getRiProgress() {
		return riProgress == null ? "" : riProgress;
	}
	public void setRiProgress(String riProgress) {
		this.riProgress = riProgress;
	}
	public String getRiDayWeek() {
		return riDayWeek == null ? "" : riDayWeek;
	}
	public void setRiDayWeek(String riDayWeek) {
		this.riDayWeek = riDayWeek;
	}
	public String getRiLotUse() {
		return riLotUse == null ? "" : riLotUse;
	}
	public void setRiLotUse(String riLotUse) {
		this.riLotUse = riLotUse;
	}
	public String getRiOffLotUse() {
		return riOffLotUse == null ? "" : riOffLotUse;
	}
	public void setRiOffLotUse(String riOffLotUse) {
		this.riOffLotUse = riOffLotUse;
	}
	public String getRiTermType() {
		return riTermType == null ? "" : riTermType;
	}
	public void setRiTermType(String riTermType) {
		this.riTermType = riTermType;
	}
	public String getRiSdate() {
		return riSdate == null ? "" : riSdate;
	}
	public void setRiSdate(String riSdate) {
		this.riSdate = riSdate;
	}
	public String getRiEdate() {
		return riEdate == null ? "" : riEdate;
	}
	public void setRiEdate(String riEdate) {
		this.riEdate = riEdate;
	}
	public String getRiRedundancy() {
		return riRedundancy == null ? "" : riRedundancy;
	}
	public void setRiRedundancy(String riRedundancy) {
		this.riRedundancy = riRedundancy;
	}
	public String getRiHaveFac() {
		return riHaveFac == null ? "" : riHaveFac;
	}
	public void setRiHaveFac(String riHaveFac) {
		this.riHaveFac = riHaveFac;
	}
	public String getRiSiteUrl() {
		return riSiteUrl == null ? "" : riSiteUrl;
	}
	public void setRiSiteUrl(String riSiteUrl) {
		this.riSiteUrl = riSiteUrl;
	}
	public String getRiUserloginType() {
		return riUserloginType == null ? "" : riUserloginType;
	}
	public void setRiUserloginType(String riUserloginType) {
		this.riUserloginType = riUserloginType;
	}
	public String getRiRecruitYn() {
		return riRecruitYn == null ? "" : riRecruitYn;
	}
	public void setRiRecruitYn(String riRecruitYn) {
		this.riRecruitYn = riRecruitYn;
	}
	public String getRiRecruitCnt() {
		return riRecruitCnt == null ? "" : riRecruitCnt;
	}
	public void setRiRecruitCnt(String riRecruitCnt) {
		this.riRecruitCnt = riRecruitCnt;
	}
	public String getRiRecruitMaxCnt() {
		return riRecruitMaxCnt == null ? "" : riRecruitMaxCnt;
	}
	public void setRiRecruitMaxCnt(String riRecruitMaxCnt) {
		this.riRecruitMaxCnt = riRecruitMaxCnt;
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
	public String getRiConfirmYn() {
		return riConfirmYn == null ? "" : riConfirmYn;
	}
	public void setRiConfirmYn(String riConfirmYn) {
		this.riConfirmYn = riConfirmYn;
	}
	public String getRiImageFileId() {
		return riImageFileId == null ? "" : riImageFileId;
	}
	public void setRiImageFileId(String riImageFileId) {
		this.riImageFileId = riImageFileId;
	}
	public int getRiAccCnt() {
		return riAccCnt;
	}
	public void setRiAccCnt(int riAccCnt) {
		this.riAccCnt = riAccCnt;
	}
	public int getRiReadCnt() {
		return riReadCnt;
	}
	public void setRiReadCnt(int riReadCnt) {
		this.riReadCnt = riReadCnt;
	}
	public String getActionkey() {
		return actionkey == null ? "" : actionkey;
	}
	public void setActionkey(String actionkey) {
		this.actionkey = actionkey;
	}
	public String getRuReservationDay() {
		return ruReservationDay == null ? "" : ruReservationDay;
	}
	public void setRuReservationDay(String ruReservationDay) {
		this.ruReservationDay = ruReservationDay;
	}
	public String getRuReservationTime() {
		return ruReservationTime == null ? "" : ruReservationTime;
	}
	public void setRuReservationTime(String ruReservationTime) {
		this.ruReservationTime = ruReservationTime;
	}
	public String getRuLotResult() {
		return ruLotResult == null ? "" : ruLotResult;
	}
	public void setRuLotResult(String ruLotResult) {
		this.ruLotResult = ruLotResult;
	}
	public String getRuDupkey() {
		return ruDupkey == null ? "" : ruDupkey;
	}
	public void setRuDupkey(String ruDupkey) {
		this.ruDupkey = ruDupkey;
	}
	public String getUseYn() {
		return useYn == null ? "" : useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public int getRiTempCnt() {
		return riTempCnt;
	}
	public void setRiTempCnt(int riTempCnt) {
		this.riTempCnt = riTempCnt;
	}
	public String getRegId() {
		return regId == null ? "" : regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getRegName() {
		return regName;
	}
	public void setRegName(String regName) {
		this.regName = regName;
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
	public String getUserReg() {
		return userReg;
	}
	public void setUserReg(String userReg) {
		this.userReg = userReg;
	}
	public String getMgrName() {
		return mgrName;
	}
	public void setMgrName(String mgrName) {
		this.mgrName = mgrName;
	}
	public List<String> getStrList() {
		List<String> arrRet = new ArrayList<String>(); 
		if(this.strList != null){
			arrRet.addAll(strList);
		}
		return arrRet;
	}
	public void setStrList(List<String> strList) {
		this.strList = new ArrayList<String>();
		if(strList != null){
			this.strList.addAll(strList);
		}
	}
	public String getSearchOrg() {
		return searchOrg;
	}
	public void setSearchOrg(String searchOrg) {
		this.searchOrg = searchOrg;
	}
	
	public String getRiFileYn() {
		return riFileYn  == null ? "" : riFileYn;
	}
	public void setRiFileYn(String riFileYn) {
		this.riFileYn = riFileYn;
	}
	public int getRiFileCnt() {
		return riFileCnt;
	}
	public void setRiFileCnt(int riFileCnt) {
		this.riFileCnt = riFileCnt;
	}
	public String getRiRecruitType() {
		return riRecruitType;
	}
	public void setRiRecruitType(String riRecruitType) {
		this.riRecruitType = riRecruitType;
	}
	public String getRiRecruitCntById() {
		return riRecruitCntById;
	}
	public void setRiRecruitCntById(String riRecruitCntById) {
		this.riRecruitCntById = riRecruitCntById;
	}
	public List<String> getMgrSiteCdList() {
		return mgrSiteCdList;
	}
	public void setMgrSiteCdList(List<String> mgrSiteCdList) {
		this.mgrSiteCdList = mgrSiteCdList;
	}
	public String getRiReservationSdt() {
		return riReservationSdt;
	}
	public void setRiReservationSdt(String riReservationSdt) {
		this.riReservationSdt = riReservationSdt;
	}
	public String getRiReservationEdt() {
		return riReservationEdt;
	}
	public void setRiReservationEdt(String riReservationEdt) {
		this.riReservationEdt = riReservationEdt;
	}
	public String getRiEventTypeNm() {
		return riEventTypeNm;
	}
	public void setRiEventTypeNm(String riEventTypeNm) {
		this.riEventTypeNm = riEventTypeNm;
	}
	
	
}
