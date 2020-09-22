package egovframework.injeinc.boffice.pledge.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import egovframework.injeinc.boffice.pledge.dao.PledgeDao;
import egovframework.injeinc.boffice.pledge.service.PledgeFileSvc;
import egovframework.injeinc.boffice.pledge.service.PledgeSvc;
import egovframework.injeinc.boffice.pledge.vo.PledgeFileVo;
import egovframework.injeinc.boffice.pledge.vo.PledgeVo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("PledgeSvc")
public class PledgeImpl extends EgovAbstractServiceImpl implements PledgeSvc {

	@Resource(name="PledgeDao")
	private PledgeDao pledgeDao;
	
	@Resource(name = "pledgeIdGnrService")
	private EgovIdGnrService idgenService;
	
	@Resource(name = "PledgeFileSvc")
	private PledgeFileSvc pledgeFileSvc;
	
	@SuppressWarnings("rawtypes")
	public List retrieveListWiwid(int wiwParent) throws Exception {
		return pledgeDao.selectListWiwid(wiwParent);
	}
	
	public int registpledge(HttpServletRequest request, PledgeVo pledgeVo) throws Exception {
		int plIdx = idgenService.getNextIntegerId();
		pledgeVo.setPlIdx(plIdx);
		pledgeDao.insertPledge(pledgeVo);
		if(plIdx > 0) {
			PledgeFileVo pledgeFileVo = new PledgeFileVo();
			pledgeFileVo.setPlIdx(plIdx);
			pledgeFileVo.setRegId((String)request.getAttribute("userid"));
			pledgeFileVo.setFileMaxSize((String)request.getAttribute("fileMaxSize"));	//첨부파일 등록
			pledgeFileSvc.registPledgeFile(request, pledgeFileVo);
		}
		return plIdx;
	}
	
	public void modifyPledge(HttpServletRequest request, PledgeVo pledgeVo) throws Exception {
		pledgeDao.updatePledge(pledgeVo);
		
		PledgeFileVo pledgeFileVo = new PledgeFileVo();
		pledgeFileVo.setPlIdx(pledgeVo.getPlIdx());
		pledgeFileVo.setRegId((String)request.getAttribute("userid"));
		pledgeFileVo.setFileMaxSize((String)request.getAttribute("fileMaxSize"));	//첨부파일 등록
		
		pledgeFileSvc.registPledgeFile(request, pledgeFileVo);
	}
	
	public void removePledge(PledgeVo pledgeVo) throws Exception {
		pledgeDao.deletePledge(pledgeVo);
	}
	
	@SuppressWarnings("rawtypes")
	public List retrievePagePledge(PledgeVo pledgeVo) throws Exception {
		return pledgeDao.selectPagePledge(pledgeVo);
	}
	
	@SuppressWarnings("rawtypes")
	public List retrieveListPledge(PledgeVo pledgeVo) throws Exception {
		return pledgeDao.selectListPledge(pledgeVo);
	}
	
	@SuppressWarnings("rawtypes")
	public int retrieveTotalCntPledge(PledgeVo pledgeVo) throws Exception {
		return pledgeDao.selectTotalCntPledge(pledgeVo);
	}
	
	@SuppressWarnings("rawtypes")
	public PledgeVo retrievePledge(PledgeVo pledgeVo) throws Exception {
		return pledgeDao.selectPledge(pledgeVo);
	}
	
}