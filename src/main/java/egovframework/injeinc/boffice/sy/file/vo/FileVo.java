package egovframework.injeinc.boffice.sy.file.vo;

import egovframework.cmm.ComDefaultVO;

@SuppressWarnings("serial")
public class FileVo extends ComDefaultVO{
	
	private String fileNo;
	private String fileName;
	private String fileSize;
	private String filePath;
	private String fileType;
	private String useYnFile;
	private String regDt;
	private String regIdCode;
	private String modDt;
	private String modId;
	
	public String getFileNo() {
		return fileNo;
	}
	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getUseYnFiel() {
		return useYnFile;
	}
	public void setUseYnFile(String useYnFile) {
		this.useYnFile = useYnFile;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getRegIdCode() {
		return regIdCode;
	}
	public void setRegIdCode(String regIdCode) {
		this.regIdCode = regIdCode;
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
	
}