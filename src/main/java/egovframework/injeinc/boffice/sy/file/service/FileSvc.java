package egovframework.injeinc.boffice.sy.file.service;

import java.util.HashMap;

public interface FileSvc {
	
	/**
	 *  파일 업로드
	 * @param paramObject
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> registFile(HashMap<String, Object> paramObject) throws Exception;
	
}