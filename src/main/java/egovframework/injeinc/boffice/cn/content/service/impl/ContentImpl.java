	package egovframework.injeinc.boffice.cn.content.service.impl;
	
	import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.injeinc.boffice.cn.content.dao.ContentDao;
import egovframework.injeinc.boffice.cn.content.service.ContentSvc;
import egovframework.injeinc.boffice.cn.content.vo.ContentVo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
	
	@Service("ContentSvc")
	public class ContentImpl extends EgovAbstractServiceImpl implements ContentSvc {
	
		@Resource(name="ContentDao")
		ContentDao contentDao;

		public void registContent(ContentVo contentVo) throws Exception {
			contentDao.insertContent(contentVo);
		}

		public List<ContentVo> selectListContent() throws Exception {
			return contentDao.selectListContent();
		}
		
	}
