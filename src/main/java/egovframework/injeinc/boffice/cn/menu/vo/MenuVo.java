package egovframework.injeinc.boffice.cn.menu.vo;

import egovframework.cmm.ComDefaultVO;

public class MenuVo extends ComDefaultVO {
	//도메인cd
	private String siteCd;
	//메뉴 코드
	private String menuCode;
	//사이트명
	private String siteNm;
	//메뉴 명
	private String menuNm;
	//제목
	private String title;
	//기본경로
	private String basePath;
	//메뉴경로
	private String menuPath;
	//메뉴깊이
	private String menuDepth;
	//정렬 순서
	private String sortOrder;
	//관리자메뉴url
	private String adminMenuUrl;
	//사용자메뉴url
	private String userMenuUrl;
	
	private String subMenuUrl;
	
	private String subMenuTitle;
	
	//레이아웃코드
	private String layoutCode;
	//부모레이아웃사용여부
	private String parentLayoutUseYn;
	//메뉴유형
	private String menuType;
	//링크유형
	private String linkType;
	//컨텐츠유형
	private String contentType;
	//컨트롤명
	private String controlNm;
	//함수명
	private String methodNm;
	//담당부서코드
	private String chargeDeptCode;
	//국코드
	private String chargeDeptCode1;
	//과코드
	private String chargeDeptCode2;
	//팀코드
	private String chargeDeptCode3;
	//담당자 아이디
	private String chargerId;
	//권한유형
	private String authType;
	//권한부서코드
	private String authDeptCd;
	//컨텐츠일련번호
	private String contentSeqNo;
	//head컨텐츠
	private String headContents;
	//foot컨텐츠
	private String footContents;
	//제목이미지
	private String titleImg;
	//메뉴제목이미지
	private String menuTitleImg;
	//메뉴온이미지
	private String menuOnImg;
	//메뉴오프이미지
	private String menuOffImg;
	//파일일련번호
	private String fileSeqNo;
	//승인여부
	private String approvalYn;
	//통계사용여부
	private String statisticsUseYn;
	//만족도표시여부
	private String satisfyShowYn;
	//담당자표시여부
	private String chargerShowYn;
	//표시여부
	private String showYn;
	//사용여부
	private String useYn;
	//등록자아이디
	private String regId;
	//등록 일시
	private String regDt;
	//수정자아이디
	private String modId;
	//수정 일시
	private String modDt;
	//sns사용여부
	private String snsUseYn;
	//페이지코드
	private String pageCd;
	//메뉴고정용부서
	private String orgChargeDeptCode;
	//메뉴고정전화
	private String orgTel;
	
	private String sameLevel;
	
	private String layoutUrl;
	
	private String preSortOrder;
	
	private String moveOrderStep;
	
	private String moveSortOrder;
	
	private String nowSortOrder;
	
	private String nowMenuLevel;
	
	private String moveMenuLevel;
	
	private String targetSortOrder;
	
	private String searchUrl = "";
	
	private int cnt = 0;
	
	private String searchEngine;
	
	private String boonyaYn;
	
	private String deptInfo = "";
	
	private String socialShowYn = "";
	
	private String roleIdx = "";
	
	private String publishYn = "";
	
	/* 메타태그 관련*/
	private String metaTagContent = "";
	private String metaProperty = "";
	private String metaValue = "";
	private String metaContent = "";
	
	//상단 컨텐츠(게시판,응용프로그램일시)
	private String headContent;
	
	//페이지 설명(메타태그)
	private String descriptionContent;
	
	public String getTargetSortOrder() {
		return targetSortOrder;
	}
	public void setTargetSortOrder(String targetSortOrder) {
		this.targetSortOrder = targetSortOrder;
	}
	public String getNowMenuLevel() {
		return nowMenuLevel;
	}
	public void setNowMenuLevel(String nowMenuLevel) {
		this.nowMenuLevel = nowMenuLevel;
	}
	public String getMoveMenuLevel() {
		return moveMenuLevel;
	}
	public void setMoveMenuLevel(String moveMenuLevel) {
		this.moveMenuLevel = moveMenuLevel;
	}
	public String getPreSortOrder() {
		return preSortOrder;
	}
	public void setPreSortOrder(String preSortOrder) {
		this.preSortOrder = preSortOrder;
	}
	public String getMoveOrderStep() {
		return moveOrderStep;
	}
	public void setMoveOrderStep(String moveOrderStep) {
		this.moveOrderStep = moveOrderStep;
	}
	public String getMoveSortOrder() {
		return moveSortOrder;
	}
	public void setMoveSortOrder(String moveSortOrder) {
		this.moveSortOrder = moveSortOrder;
	}
	public String getNowSortOrder() {
		return nowSortOrder;
	}
	public void setNowSortOrder(String nowSortOrder) {
		this.nowSortOrder = nowSortOrder;
	}
	public String getSubMenuUrl() {
		return subMenuUrl;
	}
	public void setSubMenuUrl(String subMenuUrl) {
		this.subMenuUrl = subMenuUrl;
	}
	public String getSubMenuTitle() {
		return subMenuTitle;
	}
	public void setSubMenuTitle(String subMenuTitle) {
		this.subMenuTitle = subMenuTitle;
	}
	public String getSiteNm() {
		return siteNm;
	}
	public void setSiteNm(String siteNm) {
		this.siteNm = siteNm;
	}
	public String getSameLevel() {
		return sameLevel;
	}
	public void setSameLevel(String sameLevel) {
		this.sameLevel = sameLevel;
	}
	public String getSiteCd() {
		return siteCd;
	}
	public void setSiteCd(String siteCd) {
		this.siteCd = siteCd;
	}
	public String getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	public String getMenuNm() {
		return menuNm;
	}
	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBasePath() {
		return basePath;
	}
	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}
	public String getMenuPath() {
		return menuPath;
	}
	public void setMenuPath(String menuPath) {
		this.menuPath = menuPath;
	}
	public String getMenuDepth() {
		return menuDepth;
	}
	public void setMenuDepth(String menuDepth) {
		this.menuDepth = menuDepth;
	}
	public String getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	public String getAdminMenuUrl() {
		return adminMenuUrl;
	}
	public void setAdminMenuUrl(String adminMenuUrl) {
		this.adminMenuUrl = adminMenuUrl;
	}
	public String getUserMenuUrl() {
		return userMenuUrl;
	}
	public void setUserMenuUrl(String userMenuUrl) {
		this.userMenuUrl = userMenuUrl;
	}
	public String getLayoutCode() {
		return layoutCode;
	}
	public void setLayoutCode(String layoutCode) {
		this.layoutCode = layoutCode;
	}
	public String getParentLayoutUseYn() {
		return parentLayoutUseYn;
	}
	public void setParentLayoutUseYn(String parentLayoutUseYn) {
		this.parentLayoutUseYn = parentLayoutUseYn;
	}
	public String getMenuType() {
		return menuType;
	}
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	public String getLinkType() {
		return linkType;
	}
	public void setLinkType(String linkType) {
		this.linkType = linkType;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getControlNm() {
		return controlNm;
	}
	public void setControlNm(String controlNm) {
		this.controlNm = controlNm;
	}
	public String getMethodNm() {
		return methodNm;
	}
	public void setMethodNm(String methodNm) {
		this.methodNm = methodNm;
	}
	public String getChargeDeptCode() {
		return chargeDeptCode;
	}
	public void setChargeDeptCode(String chargeDeptCode) {
		this.chargeDeptCode = chargeDeptCode;
	}
	public String getChargeDeptCode1() {
		return chargeDeptCode1;
	}
	public void setChargeDeptCode1(String chargeDeptCode1) {
		this.chargeDeptCode1 = chargeDeptCode1;
	}
	public String getChargeDeptCode2() {
		return chargeDeptCode2;
	}
	public void setChargeDeptCode2(String chargeDeptCode2) {
		this.chargeDeptCode2 = chargeDeptCode2;
	}
	public String getChargeDeptCode3() {
		return chargeDeptCode3;
	}
	public void setChargeDeptCode3(String chargeDeptCode3) {
		this.chargeDeptCode3 = chargeDeptCode3;
	}
	public String getChargerId() {
		return chargerId;
	}
	public void setChargerId(String chargerId) {
		this.chargerId = chargerId;
	}
	public String getAuthType() {
		return authType;
	}
	public void setAuthType(String authType) {
		this.authType = authType;
	}
	public String getAuthDeptCd() {
		return authDeptCd;
	}
	public void setAuthDeptCd(String authDeptCd) {
		this.authDeptCd = authDeptCd;
	}
	public String getContentSeqNo() {
		return contentSeqNo;
	}
	public void setContentSeqNo(String contentSeqNo) {
		this.contentSeqNo = contentSeqNo;
	}
	public String getHeadContents() {
		return headContents;
	}
	public void setHeadContents(String headContents) {
		this.headContents = headContents;
	}
	public String getFootContents() {
		return footContents;
	}
	public void setFootContents(String footContents) {
		this.footContents = footContents;
	}
	public String getTitleImg() {
		return titleImg;
	}
	public void setTitleImg(String titleImg) {
		this.titleImg = titleImg;
	}
	public String getMenuTitleImg() {
		return menuTitleImg;
	}
	public void setMenuTitleImg(String menuTitleImg) {
		this.menuTitleImg = menuTitleImg;
	}
	public String getMenuOnImg() {
		return menuOnImg;
	}
	public void setMenuOnImg(String menuOnImg) {
		this.menuOnImg = menuOnImg;
	}
	public String getMenuOffImg() {
		return menuOffImg;
	}
	public void setMenuOffImg(String menuOffImg) {
		this.menuOffImg = menuOffImg;
	}
	public String getFileSeqNo() {
		return fileSeqNo;
	}
	public void setFileSeqNo(String fileSeqNo) {
		this.fileSeqNo = fileSeqNo;
	}
	public String getApprovalYn() {
		return approvalYn;
	}
	public void setApprovalYn(String approvalYn) {
		this.approvalYn = approvalYn;
	}
	public String getStatisticsUseYn() {
		return statisticsUseYn;
	}
	public void setStatisticsUseYn(String statisticsUseYn) {
		this.statisticsUseYn = statisticsUseYn;
	}
	public String getSatisfyShowYn() {
		return satisfyShowYn;
	}
	public void setSatisfyShowYn(String satisfyShowYn) {
		this.satisfyShowYn = satisfyShowYn;
	}
	public String getChargerShowYn() {
		return chargerShowYn;
	}
	public void setChargerShowYn(String chargerShowYn) {
		this.chargerShowYn = chargerShowYn;
	}
	public String getShowYn() {
		return showYn;
	}
	public void setShowYn(String showYn) {
		this.showYn = showYn;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
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
	public String getSnsUseYn() {
		return snsUseYn;
	}
	public void setSnsUseYn(String snsUseYn) {
		this.snsUseYn = snsUseYn;
	}
	public String getPageCd() {
		return pageCd;
	}
	public void setPageCd(String pageCd) {
		this.pageCd = pageCd;
	}
	public String getOrgChargeDeptCode() {
		return orgChargeDeptCode;
	}
	public void setOrgChargeDeptCode(String orgChargeDeptCode) {
		this.orgChargeDeptCode = orgChargeDeptCode;
	}
	public String getOrgTel() {
		return orgTel;
	}
	public void setOrgTel(String orgTel) {
		this.orgTel = orgTel;
	}
	public String getLayoutUrl() {
		return layoutUrl;
	}
	public void setLayoutUrl(String layoutUrl) {
		this.layoutUrl = layoutUrl;
	}
	public String getHeadContent() {
		return headContent;
	}
	public void setHeadContent(String headContent) {
		this.headContent = headContent;
	}
	public String getSearchUrl() {
		return searchUrl;
	}
	public void setSearchUrl(String searchUrl) {
		this.searchUrl = searchUrl;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public String getBoonyaYn() {
		return boonyaYn;
	}
	public void setBoonyaYn(String boonyaYn) {
		this.boonyaYn = boonyaYn;
	}
	public String getDeptInfo() {
		return deptInfo;
	}
	public void setDeptInfo(String deptInfo) {
		this.deptInfo = deptInfo;
	}
	public String getSocialShowYn() {
		return socialShowYn;
	}
	public void setSocialShowYn(String socialShowYn) {
		this.socialShowYn = socialShowYn;
	}
	public String getRoleIdx() {
		return roleIdx;
	}
	public void setRoleIdx(String roleIdx) {
		this.roleIdx = roleIdx;
	}
	public String getPublishYn() {
		return publishYn;
	}
	public void setPublishYn(String publishYn) {
		this.publishYn = publishYn;
	}
	public String getMetaTagContent() {
		return metaTagContent;
	}
	public void setMetaTagContent(String metaTagContent) {
		this.metaTagContent = metaTagContent;
	}
	public String getMetaProperty() {
		return metaProperty;
	}
	public void setMetaProperty(String metaProperty) {
		this.metaProperty = metaProperty;
	}
	public String getMetaValue() {
		return metaValue;
	}
	public void setMetaValue(String metaValue) {
		this.metaValue = metaValue;
	}
	public String getMetaContent() {
		return metaContent;
	}
	public void setMetaContent(String metaContent) {
		this.metaContent = metaContent;
	}
	
}
