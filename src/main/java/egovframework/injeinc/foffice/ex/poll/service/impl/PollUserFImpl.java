package egovframework.injeinc.foffice.ex.poll.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.injeinc.foffice.ex.poll.dao.PollUserFDao;
import egovframework.injeinc.foffice.ex.poll.service.PollUserFSvc;
import egovframework.injeinc.foffice.ex.poll.vo.PollUserFVo;

@Service("PollUserFSvc")
public class PollUserFImpl extends EgovAbstractServiceImpl implements PollUserFSvc {

	@Resource(name = "PollUserFDao")
	private PollUserFDao pollUserFDao;

	@Resource(name = "pollUserIdGnrService")
	private EgovIdGnrService idgenService;

	public String registPollUserF(PollUserFVo pollUserFVo) throws Exception {
		if(pollUserFVo == null){
			return "";
		}
		String puIdx = idgenService.getNextStringId();
		pollUserFVo.setPuIdx(puIdx);
		pollUserFDao.createPollUserF(pollUserFVo);
		
		return puIdx;
	}

	public int retrievePollUserFCnt(PollUserFVo pollUserFVo) throws Exception {
		return pollUserFDao.selectPollUserFCnt(pollUserFVo);
	}

	public int retrievePollUserFTotCnt(PollUserFVo pollUserFVo) throws Exception {
		return pollUserFDao.selectPollUserFTotCnt(pollUserFVo);
	}
}