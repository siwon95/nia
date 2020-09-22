package egovframework.injeinc.boffice.cn.file.vo;

import egovframework.cmm.ComDefaultVO;

@SuppressWarnings("serial")
public class EzFileVo extends ComDefaultVO {
	//첨부파일ID
	private String atchFileId;
	//파일순번
	private String fileSn;
	//파일저장경로
	private String fileStreCours;
	//저장파일명
	private String streFileNm;
	//원파일명
	private String orignlFileNm;
	//파일확장자
	private String fileExtsn;
	//파일내용
	private String fileCn;
	//파일크기
	private String fileSize;


	public String getAtchFileId() {
		return atchFileId;
	}
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}
	public String getFileSn() {
		return fileSn;
	}
	public void setFileSn(String fileSn) {
		this.fileSn = fileSn;
	}
	public String getFileStreCours() {
		return fileStreCours;
	}
	public void setFileStreCours(String fileStreCours) {
		this.fileStreCours = fileStreCours;
	}
	public String getStreFileNm() {
		return streFileNm;
	}
	public void setStreFileNm(String streFileNm) {
		this.streFileNm = streFileNm;
	}
	public String getOrignlFileNm() {
		return orignlFileNm;
	}
	public void setOrignlFileNm(String orignlFileNm) {
		this.orignlFileNm = orignlFileNm;
	}
	public String getFileExtsn() {
		return fileExtsn;
	}
	public void setFileExtsn(String fileExtsn) {
		this.fileExtsn = fileExtsn;
	}
	public String getFileCn() {
		return fileCn;
	}
	public void setFileCn(String fileCn) {
		this.fileCn = fileCn;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	
	
}
