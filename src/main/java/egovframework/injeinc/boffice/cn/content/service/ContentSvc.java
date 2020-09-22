package egovframework.injeinc.boffice.cn.content.service;

import java.util.List;

import egovframework.injeinc.boffice.cn.content.vo.ContentVo;

public interface ContentSvc {
	/**  컨텐츠 내용 insert */
	public void registContent(ContentVo contentVo) throws Exception;
	/**  컨텐츠 조회 */
	public List<ContentVo> selectListContent() throws Exception;
	
}
