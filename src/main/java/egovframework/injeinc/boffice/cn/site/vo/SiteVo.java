package egovframework.injeinc.boffice.cn.site.vo;

import egovframework.cmm.ComDefaultVO;

public class SiteVo extends ComDefaultVO {
	
	//사이트키
	private String siteIdx;
	
	//사이트코드
	private String siteCd;
	
	//사이트명
	private String siteNm;
	
	//사이트도메인
	private String siteDomain;
	
	//사이트 유형
	private String siteKdNm;
	
	//사이트 유형코드
	private String siteKdCd;
	
	//등록자 이름
	private String regNm;
	
	//등록자ID
	private String regId;
	
	//등록일시
	private String regDt;
	
	//수정일시
	private String modDt;
	
	//수정자ID
	private String modId;
	
	//Form 구분값
	private String siteMenu;
	
	//				CMM_CODE 테이블 컬럼			//
	
	private String code;
	
	private String codeName;
	
	private String codeAls;
	
	private String codeUpr;
	
	private String sitePath;
	
	private String roleIdx;

	public String getSiteIdx() {
		return siteIdx;
	}

	public void setSiteIdx(String siteIdx) {
		this.siteIdx = siteIdx;
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

	public String getSiteDomain() {
		return siteDomain;
	}

	public void setSiteDomain(String siteDomain) {
		this.siteDomain = siteDomain;
	}

	public String getSiteKdNm() {
		return siteKdNm;
	}

	public void setSiteKdNm(String siteKdNm) {
		this.siteKdNm = siteKdNm;
	}

	public String getSiteKdCd() {
		return siteKdCd;
	}

	public void setSiteKdCd(String siteKdCd) {
		this.siteKdCd = siteKdCd;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getCodeAls() {
		return codeAls;
	}

	public void setCodeAls(String codeAls) {
		this.codeAls = codeAls;
	}

	public String getCodeUpr() {
		return codeUpr;
	}

	public void setCodeUpr(String codeUpr) {
		this.codeUpr = codeUpr;
	}

	public String getRegNm() {
		return regNm;
	}

	public void setRegNm(String regNm) {
		this.regNm = regNm;
	}

	public String getSiteMenu() {
		return siteMenu;
	}

	public void setSiteMenu(String siteMenu) {
		this.siteMenu = siteMenu;
	}
	

	public String getSitePath() {
		return sitePath;
	}

	public void setSitePath(String sitePath) {
		this.sitePath = sitePath;
	}
	
	

	public String getRoleIdx() {
		return roleIdx;
	}

	public void setRoleIdx(String roleIdx) {
		this.roleIdx = roleIdx;
	}

	@Override
	public String toString() {
		return "SiteVo [siteIdx=" + siteIdx + ", siteCd=" + siteCd
				+ ", siteNm=" + siteNm + ", siteDomain=" + siteDomain
				+ ", siteKdNm=" + siteKdNm + ", siteKdCd=" + siteKdCd
				+ ", sitePath=" + sitePath
				+ ", regNm=" + regNm + ", regId=" + regId + ", regDt=" + regDt
				+ ", modDt=" + modDt + ", modId=" + modId + ", siteMenu="
				+ siteMenu + ", code=" + code + ", codeName=" + codeName
				+ ", codeAls=" + codeAls + ", codeUpr=" + codeUpr
				+ ", getSiteIdx()=" + getSiteIdx() + ", getSiteCd()="
				+ getSiteCd() + ", getSiteNm()=" + getSiteNm()
				+ ", getSiteDomain()=" + getSiteDomain() + ", getSiteKdNm()="
				+ getSiteKdNm() + ", getSiteKdCd()=" + getSiteKdCd()
				+ ", getRegId()=" + getRegId() + ", getRegDt()=" + getRegDt()
				+ ", getModDt()=" + getModDt() + ", getModId()=" + getModId()
				+ ", getCode()=" + getCode() + ", getCodeName()="
				+ getCodeName() + ", getCodeAls()=" + getCodeAls()
				+ ", getCodeUpr()=" + getCodeUpr() + ", getRegNm()="
				+ getRegNm() + ", getSiteMenu()=" + getSiteMenu()
				+ ", getFirstIndex()=" + getFirstIndex() + ", getLastIndex()="
				+ getLastIndex() + ", getRecordCountPerPage()="
				+ getRecordCountPerPage() + ", getSearchCondition()="
				+ getSearchCondition() + ", getSearchKeyword()="
				+ getSearchKeyword() + ", getSearchUseYn()=" + getSearchUseYn()
				+ ", getPageIndex()=" + getPageIndex() + ", getPageUnit()="
				+ getPageUnit() + ", getPageSize()=" + getPageSize()
				+ ", toString()=" + super.toString()
				+ ", getSearchKeywordFrom()=" + getSearchKeywordFrom()
				+ ", getSearchKeywordTo()=" + getSearchKeywordTo()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ "]";
	}
	

}
