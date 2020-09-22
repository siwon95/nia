package egovframework.injeinc.foffice.ex.poll.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.injeinc.boffice.ex.poll.vo.PollListVo;
import egovframework.injeinc.foffice.ex.poll.dao.PollListFDao;
import egovframework.injeinc.foffice.ex.poll.service.PollListFSvc;
import egovframework.injeinc.foffice.ex.poll.vo.PollListFVo;

@Service("PollListFSvc")
public class PollListFImpl extends EgovAbstractServiceImpl implements PollListFSvc {

	@Resource(name = "PollListFDao")
	private PollListFDao pollListFDao;

	public PollListFVo retrievePollListF(PollListFVo pollListFVo) throws Exception {
		return pollListFDao.selectPollListF(pollListFVo);
	}

	@SuppressWarnings("rawtypes")
	public List retrieveListPollListF(PollListFVo pollListFVo) throws Exception {
		return pollListFDao.selectListPollListF(pollListFVo);
	}

	@SuppressWarnings("rawtypes")
	public List retrieveListPollDepartF(PollListFVo pollListFVo) throws Exception {
		return pollListFDao.selectListPollDepartF(pollListFVo);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> retrievePagPollList(PollListFVo pollListVo) throws Exception {
		
		List<PollListVo> result = pollListFDao.selectPagPollList(pollListVo);
		int cnt = pollListFDao.selectPollListCnt(pollListVo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> retrievePagAllPollList(PollListFVo pollListVo) throws Exception {
		
		List<PollListVo> result = pollListFDao.selectPagAllPollList(pollListVo);
		int cnt = pollListFDao.selectAllPollListCnt(pollListVo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}
}