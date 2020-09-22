package egovframework.injeinc.boffice.cn.layout.vo;

import egovframework.cmm.ComDefaultVO;

public class LayoutVo extends ComDefaultVO {
	
	//도메인cd
	private String siteCd;
	
	private String siteNm;

	//레이아웃코드
	private String layoutCode;

	//레이아웃설명
	private String layoutDesc;

	//레이아웃컨텐츠
	private String layoutContent;

	//파일경로
	private String filePath;

	//등록자아이디
	private String regId;

	//등록 일시
	private String regDt;

	//수정자아이디
	private String modId;

	//수정 일시
	private String modDt;
	
	//페이지 모드
	private String pageMode;
	
	//레이아웃타입
	private String layoutType;

	
	public String getSiteNm() {
		return siteNm;
	}

	public void setSiteNm(String siteNm) {
		this.siteNm = siteNm;
	}

	public String getSiteCd() {
		return siteCd;
	}

	public void setSiteCd(String siteCd) {
		this.siteCd = siteCd;
	}

	public String getLayoutCode() {
		return layoutCode;
	}

	public void setLayoutCode(String layoutCode) {
		this.layoutCode = layoutCode;
	}

	public String getLayoutDesc() {
		return layoutDesc;
	}

	public void setLayoutDesc(String layoutDesc) {
		this.layoutDesc = layoutDesc;
	}

	public String getLayoutContent() {
		return layoutContent;
	}

	public void setLayoutContent(String layoutContent) {
		this.layoutContent = layoutContent;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
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

	public String getModId() {
		return modId;
	}

	public void setModId(String modId) {
		this.modId = modId;
	}

	public String getModDt() {
		return modDt;
	}

	public void setModDt(String modDt) {
		this.modDt = modDt;
	}

	public String getPageMode() {
		return pageMode;
	}

	public void setPageMode(String pageMode) {
		this.pageMode = pageMode;
	}

	public String getLayoutType() {
		return layoutType;
	}

	public void setLayoutType(String layoutType) {
		this.layoutType = layoutType;
	}
	
}
