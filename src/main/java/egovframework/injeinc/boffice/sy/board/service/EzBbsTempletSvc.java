package egovframework.injeinc.boffice.sy.board.service;

import java.util.HashMap;
import java.util.List;
import egovframework.injeinc.boffice.sy.board.vo.EzBbsTempletVo;

public interface EzBbsTempletSvc {
	/** 리스트 조회 */
	public List selectPageEzBbsTemplet(EzBbsTempletVo ezBbsTempletVo) throws Exception;

	/** 레코드수 조회 */
	public int selectTotalCountEzBbsTemplet(EzBbsTempletVo vo) throws Exception;

	/** 상세 조회 */
	public EzBbsTempletVo selectEzBbsTemplet(EzBbsTempletVo ezBbsTempletVo) throws Exception;

	/** 등록 */
	public void insertEzBbsTemplet(EzBbsTempletVo ezBbsTempletVo) throws Exception;

	/** 수정 */
	public void modifyEzBbsTemplet(EzBbsTempletVo ezBbsTempletVo) throws Exception;

	/** 삭제 */
	public void deleteEzBbsTemplet(EzBbsTempletVo ezBbsTempletVo) throws Exception;
}
