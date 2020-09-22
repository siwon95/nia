package egovframework.injeinc.boffice.ex.mainImage.vo;

import egovframework.cmm.ComDefaultVO;

public class MainImage extends ComDefaultVO {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 이미지 ID
	 */
	private String imageId;
	/**
	 * 이미지명
	 */
	private String imageNm;
	/**
	 * 메인 이미지
	 */
	private String image;
	/**
	 * 메인 이미지 파일
	 */
	private String imageFile;
	/**
	 * 이미지 설명
	 */
	private String imageDc;
	/**
	 * 반영여부
	 */
	private String reflctYn;
	/**
	 * 사용자 ID
	 */
	private String userId;
	/**
	 * 등록일자
	 */
	private String regDt;
	/**
	 * 파일첨부여부
	 */
	private boolean isAtchFile;

	/** 게시시작일 */
	private String ntceBgnde;
	/** 게시종료일 */
	private String ntceEndde;
	/** 게시시작일(시간) */
    private String ntceBgndeHH;
    /** 게시시작일(분) */
    private String ntceBgndeMM;
    /** 게시종료일(시간) */
    private String ntceEnddeHH;
    /** 게시종료일(분) */
    private String ntceEnddeMM;
	/** 상시 게시여부 */
	private String ordtmNtceYn;
	/** 링크주소 */
	private String linkUrl;
	/** 새창여부 */
	private String newWindowYn;
	/** 정렬순서 */
	private String sortOrdr;
	/** 게시상태 */
	private String ntceSttus;
	/** 타입*/
	private String type;
	/** 순서*/
	private String sort;
	/** 임의순서*/
	private String tempSort;
	/** 임의id*/
	private String tempImageId;
	public String getImageId() {
		return imageId;
	}
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	public String getImageNm() {
		return imageNm;
	}
	public void setImageNm(String imageNm) {
		this.imageNm = imageNm;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getImageFile() {
		return imageFile;
	}
	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}
	public String getImageDc() {
		return imageDc;
	}
	public void setImageDc(String imageDc) {
		this.imageDc = imageDc;
	}
	public String getReflctYn() {
		return reflctYn;
	}
	public void setReflctYn(String reflctYn) {
		this.reflctYn = reflctYn;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public boolean isAtchFile() {
		return isAtchFile;
	}
	public void setAtchFile(boolean isAtchFile) {
		this.isAtchFile = isAtchFile;
	}
	public String getNtceBgnde() {
		return ntceBgnde;
	}
	public void setNtceBgnde(String ntceBgnde) {
		this.ntceBgnde = ntceBgnde;
	}
	public String getNtceEndde() {
		return ntceEndde;
	}
	public void setNtceEndde(String ntceEndde) {
		this.ntceEndde = ntceEndde;
	}
	public String getNtceBgndeHH() {
		return ntceBgndeHH;
	}
	public void setNtceBgndeHH(String ntceBgndeHH) {
		this.ntceBgndeHH = ntceBgndeHH;
	}
	public String getNtceBgndeMM() {
		return ntceBgndeMM;
	}
	public void setNtceBgndeMM(String ntceBgndeMM) {
		this.ntceBgndeMM = ntceBgndeMM;
	}
	public String getNtceEnddeHH() {
		return ntceEnddeHH;
	}
	public void setNtceEnddeHH(String ntceEnddeHH) {
		this.ntceEnddeHH = ntceEnddeHH;
	}
	public String getNtceEnddeMM() {
		return ntceEnddeMM;
	}
	public void setNtceEnddeMM(String ntceEnddeMM) {
		this.ntceEnddeMM = ntceEnddeMM;
	}
	public String getOrdtmNtceYn() {
		return ordtmNtceYn;
	}
	public void setOrdtmNtceYn(String ordtmNtceYn) {
		this.ordtmNtceYn = ordtmNtceYn;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public String getNewWindowYn() {
		return newWindowYn;
	}
	public void setNewWindowYn(String newWindowYn) {
		this.newWindowYn = newWindowYn;
	}
	public String getSortOrdr() {
		return sortOrdr;
	}
	public void setSortOrdr(String sortOrdr) {
		this.sortOrdr = sortOrdr;
	}
	public String getNtceSttus() {
		return ntceSttus;
	}
	public void setNtceSttus(String ntceSttus) {
		this.ntceSttus = ntceSttus;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getTempSort() {
		return tempSort;
	}
	public void setTempSort(String tempSort) {
		this.tempSort = tempSort;
	}
	public String getTempImageId() {
		return tempImageId;
	}
	public void setTempImageId(String tempImageId) {
		this.tempImageId = tempImageId;
	}

}
