package egovframework.cmm.service;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @Class Name : FileVO.java
 * @Description : 파일정보 처리를 위한 VO 클래스
 * @Modification Information
 *
 *    수정일       수정자         수정내용
 *    -------        -------     -------------------
 *    2009. 3. 25.     이삼섭
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 25.
 * @version
 * @see
 *
 */
@SuppressWarnings("serial")
public class FileListVO implements Serializable {

    /**
     * 첨부파일 아이디
     */
    public String atchFileId = "";
    
    public List<FileVO> fileVoList = null;

    /**
     * toString 메소드를 대치한다.
     */
    public String toString() {
	return ToStringBuilder.reflectionToString(this);
    }

	public String getAtchFileId() {
		return atchFileId;
	}

	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}

	public List<FileVO> getFileVoList() {
		return fileVoList;
	}

	public void setFileVoList(List<FileVO> fileVoList) {
		this.fileVoList = fileVoList;
	}
	
}
