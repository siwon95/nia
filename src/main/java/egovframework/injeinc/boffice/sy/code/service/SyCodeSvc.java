package egovframework.injeinc.boffice.sy.code.service;

import java.util.HashMap;
import java.util.List;

import egovframework.injeinc.boffice.sy.code.vo.CmmCodeVo;

/**
 * DeptSvc
 * 2015.06.05 : LDY
 */
 

public interface SyCodeSvc {
	
	/** 트리  */
	public HashMap<String, Object> retriveListCode(HashMap<String, String> param) throws Exception;
	
	public HashMap<String, Object> registAllCode(HashMap<String, Object> paramObject) throws Exception;
	
	/**
	 * 팝업존 코드 리스트
	 * @param popupZoneCode
	 * @return
	 * @throws Exception
	 */
	public List<CmmCodeVo> selectPopupZoneCodeList(CmmCodeVo popupZoneCode) throws Exception;

	public void registCode(CmmCodeVo cmmCodeVo) throws Exception;

	public void modifyCode(CmmCodeVo cmmCodeVo) throws Exception;

	public void removeCode(CmmCodeVo cmmCodeVo) throws Exception;

	public void modifyCodeOrdNo(CmmCodeVo cmmCodeVo, String ordType) throws Exception;
	
}