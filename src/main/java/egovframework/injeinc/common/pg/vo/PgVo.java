package egovframework.injeinc.common.pg.vo;

import egovframework.cmm.ComDefaultVO;

@SuppressWarnings("serial")
public class PgVo extends ComDefaultVO {
	
	/** 상점ID */
	private String cstMid = "";
	/** 상점메뉴 */
	private String mertCode = "";
	/** 이전상점ID */
	private String beforecstmid = "";
	/** 별칭 */
	private String pgAls = "";
	/** 설명 */
	private String pgMemo = "";
	/** 상점MERT_KEY */
	private String lgdMertkey = "";
	/** LG데이콤설정 */
	private String lgdacomConfPath = "";
	/** LG데이콤풀경로 */
	private String lgdacomConfFullPath = "";
	/** 상점설정PATH */
	private String mallConfPath = "";
	/** 결제관련 테이블 */
	private String pgTableName = "";
	/** 담당자 이름 */
	private String pgDeptNm = "";
	/** 담당자연락처 */
	private String pgDeptTelNum = "";
	/** 담당자연락처(앞자리) */
	private String tel1 = "";
	/** 담당자연락처(중간자리) */
	private String tel2 = "";
	/** 담당자연락처(끝자리) */
	private String tel3 = "";
	/** 등록날짜 */
	private String regDt = "";
	/** 등록자id */
	private String regId = "";
	/** 수정날짜 */
	private String modDt = "";
	/** 수정자id */
	private String modId = "";
	/** 타입 */
	private String type = "";
	/** 테스트 결재금액*/
	private String lgdAmount = "";
	/** 테스트 주문번호*/
	private String lgdOid = "";
	/** 테스트 구매자 명*/
	private String lgdBuyer = "";
	/** 테스트 상픔명 */
	private String lgdProductInfo = "";
	/** 테스트 구매자 이메일 */
	private String lgdbuyerEmail = "";
	/** 테스트 구매자 이메일 */
	private String lgdCustomUsablePay = "";
	/** 테스트 반환값 코드 1*/
	private String lgdRespCode = "";
	/** 테스트 반환값 코드 2*/
	private String lgdRespMsg = "";
	/** 테스트 반환값 코드 3*/
	private String lgdPayKey = "";
	/** 테스트 반환값 코드 3*/
	private String lgdPlatform = "";
	/** 테스트 반환값 코드 4*/
	private String lgdReturnUrl = "";

	private String lgdTimeStamp = "";
	
	/** 결재성공 실패 후 처리 되는 변수*/
	private String pgOid;
	private String cmmCode;
	private String pgAmount;
	private String pgName;
	private String pgUse;
	private String pgMid;
	private String pgRespcode;
	private String pgTid;
	private String pgRespmsg;
	/** 결재성공 실패 후 처리 되는 변수*/
	
	
	
	public String getCstMid() {
		return cstMid;
	}
	public void setCstMid(String cstMid) {
		this.cstMid = cstMid;
	}
	public String getMertCode() {
		return mertCode;
	}
	public void setMertCode(String mertCode) {
		this.mertCode = mertCode;
	}
	public String getBeforecstmid() {
		return beforecstmid;
	}
	public void setBeforecstmid(String beforecstmid) {
		this.beforecstmid = beforecstmid;
	}
	public String getPgAls() {
		return pgAls;
	}
	public void setPgAls(String pgAls) {
		this.pgAls = pgAls;
	}
	public String getPgMemo() {
		return pgMemo;
	}
	public void setPgMemo(String pgMemo) {
		this.pgMemo = pgMemo;
	}
	public String getLgdMertkey() {
		return lgdMertkey;
	}
	public void setLgdMertkey(String lgdMertkey) {
		this.lgdMertkey = lgdMertkey;
	}
	public String getLgdacomConfPath() {
		return lgdacomConfPath;
	}
	public void setLgdacomConfPath(String lgdacomConfPath) {
		this.lgdacomConfPath = lgdacomConfPath;
	}
	public String getLgdacomConfFullPath() {
		return lgdacomConfFullPath;
	}
	public void setLgdacomConfFullPath(String lgdacomConfFullPath) {
		this.lgdacomConfFullPath = lgdacomConfFullPath;
	}
	public String getMallConfPath() {
		return mallConfPath;
	}
	public void setMallConfPath(String mallConfPath) {
		this.mallConfPath = mallConfPath;
	}
	public String getPgTableName() {
		return pgTableName;
	}
	public void setPgTableName(String pgTableName) {
		this.pgTableName = pgTableName;
	}
	public String getPgDeptNm() {
		return pgDeptNm;
	}
	public void setPgDeptNm(String pgDeptNm) {
		this.pgDeptNm = pgDeptNm;
	}
	public String getPgDeptTelNum() {
		return pgDeptTelNum;
	}
	public void setPgDeptTelNum(String pgDeptTelNum) {
		this.pgDeptTelNum = pgDeptTelNum;
	}
	public String getTel1() {
		return tel1;
	}
	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}
	public String getTel2() {
		return tel2;
	}
	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}
	public String getTel3() {
		return tel3;
	}
	public void setTel3(String tel3) {
		this.tel3 = tel3;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getModDt() {
		return modDt;
	}
	public void setModDt(String modDt) {
		this.modDt = modDt;
	}
	public String getModId() {
		return modId;
	}
	public void setModId(String modId) {
		this.modId = modId;
	}
	public String getLgdAmount() {
		return lgdAmount;
	}
	public void setLgdAmount(String lgdAmount) {
		this.lgdAmount = lgdAmount;
	}
	public String getLgdOid() {
		return lgdOid;
	}
	public void setLgdOid(String lgdOid) {
		this.lgdOid = lgdOid;
	}
	public String getLgdBuyer() {
		return lgdBuyer;
	}
	public void setLgdBuyer(String lgdBuyer) {
		this.lgdBuyer = lgdBuyer;
	}
	public String getLgdProductInfo() {
		return lgdProductInfo;
	}
	public void setLgdProductInfo(String lgdProductInfo) {
		this.lgdProductInfo = lgdProductInfo;
	}
	public String getLgdbuyerEmail() {
		return lgdbuyerEmail;
	}
	public void setLgdbuyerEmail(String lgdbuyerEmail) {
		this.lgdbuyerEmail = lgdbuyerEmail;
	}
	public String getLgdCustomUsablePay() {
		return lgdCustomUsablePay;
	}
	public void setLgdCustomUsablePay(String lgdCustomUsablePay) {
		this.lgdCustomUsablePay = lgdCustomUsablePay;
	}
	public String getLgdRespCode() {
		return lgdRespCode;
	}
	public void setLgdRespCode(String lgdRespCode) {
		this.lgdRespCode = lgdRespCode;
	}
	public String getLgdRespMsg() {
		return lgdRespMsg;
	}
	public void setLgdRespMsg(String lgdRespMsg) {
		this.lgdRespMsg = lgdRespMsg;
	}
	public String getLgdPayKey() {
		return lgdPayKey;
	}
	public void setLgdPayKey(String lgdPayKey) {
		this.lgdPayKey = lgdPayKey;
	}
	public String getLgdPlatform() {
		return lgdPlatform;
	}
	public void setLgdPlatform(String lgdPlatform) {
		this.lgdPlatform = lgdPlatform;
	}
	public String getLgdReturnUrl() {
		return lgdReturnUrl;
	}
	public void setLgdReturnUrl(String lgdReturnUrl) {
		this.lgdReturnUrl = lgdReturnUrl;
	}
	public String getPgOid() {
		return pgOid;
	}
	public void setPgOid(String pgOid) {
		this.pgOid = pgOid;
	}
	public String getCmmCode() {
		return cmmCode;
	}
	public void setCmmCode(String cmmCode) {
		this.cmmCode = cmmCode;
	}
	public String getPgAmount() {
		return pgAmount;
	}
	public void setPgAmount(String pgAmount) {
		this.pgAmount = pgAmount;
	}
	public String getPgName() {
		return pgName;
	}
	public void setPgName(String pgName) {
		this.pgName = pgName;
	}
	public String getPgUse() {
		return pgUse;
	}
	public void setPgUse(String pgUse) {
		this.pgUse = pgUse;
	}
	public String getPgMid() {
		return pgMid;
	}
	public void setPgMid(String pgMid) {
		this.pgMid = pgMid;
	}
	public String getPgRespcode() {
		return pgRespcode;
	}
	public void setPgRespcode(String pgRespcode) {
		this.pgRespcode = pgRespcode;
	}
	public String getPgTid() {
		return pgTid;
	}
	public void setPgTid(String pgTid) {
		this.pgTid = pgTid;
	}
	public String getPgRespmsg() {
		return pgRespmsg;
	}
	public void setPgRespmsg(String pgRespmsg) {
		this.pgRespmsg = pgRespmsg;
	}
	public String getLgdTimeStamp() {
		return lgdTimeStamp;
	}
	public void setLgdTimeStamp(String lgdTimeStamp) {
		this.lgdTimeStamp = lgdTimeStamp;
	}
}
