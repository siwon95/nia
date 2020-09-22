package egovframework.injeinc.boffice.ex.poll.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.injeinc.boffice.ex.poll.dao.PollAnswerDao;
import egovframework.injeinc.boffice.ex.poll.service.PollAnswerSvc;
import egovframework.injeinc.boffice.ex.poll.vo.PollAnswerViewVo;
import egovframework.injeinc.boffice.ex.poll.vo.PollAnswerVo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("PollAnswerSvc")
public class PollAnswerImpl extends EgovAbstractServiceImpl implements PollAnswerSvc {

	@Resource(name = "PollAnswerDao")
	private PollAnswerDao pollAnswerDao;

	public void registPollAnswer(PollAnswerVo pollAnswerVo) throws Exception {
		pollAnswerDao.createPollAnswer(pollAnswerVo);
	}

	public void removePollAnswer(PollAnswerVo pollAnswerVo) throws Exception {
		pollAnswerDao.deletePollAnswer(pollAnswerVo);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> retrievePagPollAnswer(PollAnswerViewVo PollAnswerViewVo) throws Exception {
		
		List<PollAnswerVo> result = pollAnswerDao.selectPagPollAnswer(PollAnswerViewVo);
		int cnt = pollAnswerDao.selectPollAnswerCnt(PollAnswerViewVo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}
	
	@SuppressWarnings("rawtypes")
	public List retrieveListPollAnswer(PollAnswerViewVo PollAnswerViewVo) throws Exception {
		return pollAnswerDao.selectListPollAnswer(PollAnswerViewVo);
	}
	
	public int retrievePollAnswerCnt(PollAnswerViewVo PollAnswerViewVo) throws Exception {
		return pollAnswerDao.selectPollAnswerCnt(PollAnswerViewVo);
	}
}