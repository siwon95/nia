package egovframework.injeinc.boffice.ex.poll.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.injeinc.boffice.ex.poll.dao.PollUserDao;
import egovframework.injeinc.boffice.ex.poll.service.PollUserSvc;
import egovframework.injeinc.boffice.ex.poll.vo.PollUserVo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;


@Service("PollUserSvc")
public class PollUserImpl extends EgovAbstractServiceImpl implements PollUserSvc {

	@Resource(name = "PollUserDao")
	private PollUserDao pollUserDao;

	@Resource(name = "pollUserIdGnrService")
	private EgovIdGnrService idgenService;

	public String registPollUser(PollUserVo pollUserVo) throws Exception {
		
		String puIdx = idgenService.getNextStringId();
		if(pollUserVo != null){
			pollUserVo.setPuIdx(puIdx);
		}
		pollUserDao.createPollUser(pollUserVo);
		
		return puIdx;
	}

	public void modifyPollUser(PollUserVo pollUserVo) throws Exception {
		pollUserDao.updatePollUser(pollUserVo);
	}

	public void removePollUser(PollUserVo pollUserVo) throws Exception {
		pollUserDao.deletePollUser(pollUserVo);
	}

	public PollUserVo retrievePollUser(PollUserVo pollUserVo) throws Exception {
		return pollUserDao.selectPollUser(pollUserVo);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> retrievePagPollUser(PollUserVo pollUserVo) throws Exception {
		
		List<PollUserVo> result = pollUserDao.selectPagPollUser(pollUserVo);
		int cnt = pollUserDao.selectPollUserCnt(pollUserVo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}

	@SuppressWarnings("rawtypes")
	public List retrieveListPollUser(PollUserVo pollUserVo) throws Exception {
		return pollUserDao.selectListPollUser(pollUserVo);
	}

	public int retrievePollUserCnt(PollUserVo pollUserVo) throws Exception {
		return pollUserDao.selectPollUserCnt(pollUserVo);
	}
}