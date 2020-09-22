package egovframework.injeinc.boffice.sy.fileupload.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import egovframework.injeinc.boffice.sy.fileupload.vo.FileUploadVo;


public interface FileUploadSvc {

	public Map<String, Object> retrievePagFileUpload(FileUploadVo fileUploadVo) throws Exception;
	public void registFileUpload(HttpServletRequest request,FileUploadVo fileUploadVo) throws Exception;
	public void removeFileUploadByGroupAndRename(FileUploadVo fileUploadVo) throws Exception;
	@SuppressWarnings("rawtypes")
	public List retrieveListFileUpload(FileUploadVo fileUploadVo) throws Exception;
	public FileUploadVo retrieveFileUploadByGroupAndRename(FileUploadVo fileUploadVo) throws Exception;
	public void modifyFileUploadDown(FileUploadVo fileUploadVo)throws Exception;

	
	

}
