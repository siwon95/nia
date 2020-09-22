package egovframework.injeinc.boffice.ex.poll.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.injeinc.boffice.ex.poll.dao.PollListDao;
import egovframework.injeinc.boffice.ex.poll.service.PollListSvc;
import egovframework.injeinc.boffice.ex.poll.vo.PollListVo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("PollListSvc")
public class PollListImpl extends EgovAbstractServiceImpl implements PollListSvc {

	@Resource(name = "PollListDao")
	private PollListDao pollListDao;

	@Resource(name = "pollListIdGnrService")
	private EgovIdGnrService idgenService;

	public void registPollList(PollListVo pollListVo) throws Exception {
		
		String plIdx = idgenService.getNextStringId();
		if(pollListVo != null){
			pollListVo.setPlIdx(plIdx);
		}
		pollListDao.createPollList(pollListVo);
	}

	public void modifyPollList(PollListVo pollListVo) throws Exception {
		pollListDao.updatePollList(pollListVo);
	}

	public void modifyPollListPlUse(PollListVo pollListVo) throws Exception {
		pollListDao.updatePollListPlUse(pollListVo);
	}

	public void removePollList(PollListVo pollListVo) throws Exception {
		pollListDao.deletePollList(pollListVo);
	}

	public PollListVo retrievePollList(PollListVo pollListVo) throws Exception {
		return pollListDao.selectPollList(pollListVo);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> retrievePagPollList(PollListVo pollListVo) throws Exception {
		
		List<PollListVo> result = pollListDao.selectPagPollList(pollListVo);
		int cnt = pollListDao.selectPollListCnt(pollListVo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}

	@SuppressWarnings("rawtypes")
	public List retrieveListPollList(PollListVo pollListVo) throws Exception {
		return pollListDao.selectListPollList(pollListVo);
	}

	@SuppressWarnings("rawtypes")
	public List retrieveListPollDepart(PollListVo pollListVo) throws Exception {
		return pollListDao.selectListPollDepart(pollListVo);
	}
}