package egovframework.injeinc.boffice.cn.file.service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import egovframework.injeinc.boffice.cn.file.vo.EzFileVo;

public interface EzFileSvc {
	/**  ez_file 등록 */
	public void registEzFile(EzFileVo ezFileVo,HttpServletRequest request, String formName) throws Exception;
	
	/**  ez_file 등록 */
	public void registEzFileDetail(EzFileVo ezFileVo) throws Exception;
	
	/**  ez_file 상세조회 */
	public EzFileVo retrieveEzFile(EzFileVo ezFileVo) throws Exception;
	
	/**  ez_file MAX */
	public String retrieveEzFileKey(EzFileVo ezFileVo) throws Exception;
	
	/**  ez_filedetail sn MAX */
	public String retrieveEzFileDetailSn(EzFileVo ezFileVo) throws Exception;
	
	/**  ez_file 목록 */
	public List retrieveListEzFile(EzFileVo ezFileVo) throws Exception;
	
	/** ez_file 수정 */
	public void modifyEzFile(EzFileVo ezFileVo) throws Exception;
	
	/** ez_file 삭제*/
	public void removeEzFile(EzFileVo ezFileVo) throws Exception;
	
	/** ez_file 재정렬*/
	public void removeEzFileSort(EzFileVo ezFileVo) throws Exception;
}
