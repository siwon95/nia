package egovframework.injeinc.boffice.ex.poll.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.injeinc.boffice.ex.poll.dao.PollQuestionDao;
import egovframework.injeinc.boffice.ex.poll.service.PollQuestionSvc;
import egovframework.injeinc.boffice.ex.poll.vo.PollQuestionVo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("PollQuestionSvc")
public class PollQuestionImpl extends EgovAbstractServiceImpl implements PollQuestionSvc {

	@Resource(name = "PollQuestionDao")
	private PollQuestionDao pollQuestionDao;

	@Resource(name = "pollQuestionIdGnrService")
	private EgovIdGnrService idgenService;

	public void registPollQuestion(PollQuestionVo PollQuestionVo) throws Exception {
		
		String pqIdx = idgenService.getNextStringId();
		if(PollQuestionVo != null){
			PollQuestionVo.setPqIdx(pqIdx);
		}
		pollQuestionDao.createPollQuestion(PollQuestionVo);
	}

	public void modifyPollQuestion(PollQuestionVo PollQuestionVo) throws Exception {
		pollQuestionDao.updatePollQuestion(PollQuestionVo);
	}

	public void removePollQuestion(PollQuestionVo PollQuestionVo) throws Exception {
		pollQuestionDao.updatePollQuestionPqSortReAlign(PollQuestionVo);
		pollQuestionDao.deletePollQuestion(PollQuestionVo);
	}

	public PollQuestionVo retrievePollQuestion(PollQuestionVo PollQuestionVo) throws Exception {
		return pollQuestionDao.selectPollQuestion(PollQuestionVo);
	}

	@SuppressWarnings("rawtypes")
	public List retrieveListPollQuestion(PollQuestionVo PollQuestionVo) throws Exception {
		return pollQuestionDao.selectListPollQuestion(PollQuestionVo);
	}

	public int retrievePollQuestionMaxSort(PollQuestionVo PollQuestionVo) throws Exception {
		return pollQuestionDao.selectPollQuestionMaxSort(PollQuestionVo);
	}

	public void modifyPollQuestionSortUp(PollQuestionVo PollQuestionVo) throws Exception {
		int pqSort = 0;
		if(PollQuestionVo != null){
			pqSort = PollQuestionVo.getPqSort();
		}
		int targetSort = pqSort - 1;
		if(PollQuestionVo != null){
			PollQuestionVo.setChangeSort(pqSort);
			PollQuestionVo.setPqSort(targetSort);
		}
		pollQuestionDao.updatePollQuestionPqSortChange(PollQuestionVo);
		pollQuestionDao.updatePollQuestionPqSort(PollQuestionVo);
		
	}

	public void modifyPollQuestionSortDown(PollQuestionVo PollQuestionVo) throws Exception {
		int pqSort = 0;
		if(PollQuestionVo != null){
			pqSort = PollQuestionVo.getPqSort();
		}
		int targetSort = pqSort + 1;
		
		if(PollQuestionVo != null){
			PollQuestionVo.setChangeSort(pqSort);
			PollQuestionVo.setPqSort(targetSort);
		}
		pollQuestionDao.updatePollQuestionPqSortChange(PollQuestionVo);
		pollQuestionDao.updatePollQuestionPqSort(PollQuestionVo);
		//순위 밑으로
	}
}