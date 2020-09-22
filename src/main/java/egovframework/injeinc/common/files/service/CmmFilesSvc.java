package egovframework.injeinc.common.files.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import egovframework.injeinc.common.files.vo.CmmFilesVo;

public interface CmmFilesSvc {
	
	public void registCmmFiles(HttpServletRequest request, CmmFilesVo cmmFilesVo) throws Exception;
	public void registCmmFiles(List<MultipartFile> file, CmmFilesVo cmmFilesVo) throws Exception;
	public void removeCmmFiles(CmmFilesVo cmmFilesVo) throws Exception;
	public CmmFilesVo retrieveCmmFiles(CmmFilesVo cmmFilesVo) throws Exception;
	public void removeCmmFilesByGroupAndRename(CmmFilesVo cmmFilesVo) throws Exception;
	public CmmFilesVo retrieveCmmFilesByGroupAndRename(CmmFilesVo cmmFilesVo) throws Exception;
	public Map<String, Object> retrievePagCmmFiles(CmmFilesVo cmmFilesVo) throws Exception;
	@SuppressWarnings("rawtypes")
	public List retrieveListCmmFiles(CmmFilesVo cmmFilesVo) throws Exception;
	public CmmFilesVo retrieveListCmmFilesOne(CmmFilesVo cmmFilesVo) throws Exception;
	public List<CmmFilesVo> retrieveListCmmFilesByCfType(CmmFilesVo cmmFilesVo) throws Exception;
	public void modifyCmmFilesDown(CmmFilesVo cmmFilesVo) throws Exception;
	public CmmFilesVo retrieveCmmFilesByCfIdx(String cfIdx) throws Exception;
	
}