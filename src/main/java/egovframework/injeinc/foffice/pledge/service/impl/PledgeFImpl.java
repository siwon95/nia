package egovframework.injeinc.foffice.pledge.service.impl;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import egovframework.cmm.EgovWebUtil;
import egovframework.cmm.service.EgovProperties;
import egovframework.injeinc.foffice.pledge.dao.PledgeFDao;
import egovframework.injeinc.boffice.pledge.service.PledgeFileSvc;
import egovframework.injeinc.foffice.pledge.service.PledgeFSvc;
import egovframework.injeinc.boffice.pledge.vo.PledgeFileVo;
import egovframework.injeinc.foffice.pledge.vo.PledgeFVo;
import egovframework.injeinc.common.util.EgovStringUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("PledgeFSvc")
public class PledgeFImpl extends EgovAbstractServiceImpl implements PledgeFSvc {

	@Resource(name="PledgeFDao")
	private PledgeFDao pledgeFDao;
	
	@Resource(name = "pledgeIdGnrService")
	private EgovIdGnrService idgenService;
	
	@Resource(name = "PledgeFileSvc")
	private PledgeFileSvc pledgeFileSvc;
	
	@SuppressWarnings("rawtypes")
	public List retrieveListWiwid(int wiwParent) throws Exception {
		return pledgeFDao.selectListWiwid(wiwParent);
	}
	
	public int registpledge(HttpServletRequest request, PledgeFVo pledgeFVo) throws Exception {
		int plIdx = idgenService.getNextIntegerId();
		pledgeFVo.setPlIdx(plIdx);
		pledgeFDao.insertPledge(pledgeFVo);
		if(plIdx > 0) {
			PledgeFileVo pledgeFileVo = new PledgeFileVo();
			pledgeFileVo.setPlIdx(plIdx);
			pledgeFileVo.setRegId((String)request.getAttribute("userid"));
			pledgeFileVo.setFileMaxSize((String)request.getAttribute("fileMaxSize"));	//첨부파일 등록
			pledgeFileSvc.registPledgeFile(request, pledgeFileVo);
		}
		return plIdx;
	}
	
	public void modifyPledge(HttpServletRequest request, PledgeFVo pledgeFVo) throws Exception {
		pledgeFDao.updatePledge(pledgeFVo);
		
		PledgeFileVo pledgeFileVo = new PledgeFileVo();
		pledgeFileVo.setPlIdx(pledgeFVo.getPlIdx());
		pledgeFileVo.setRegId((String)request.getAttribute("userid"));
		pledgeFileVo.setFileMaxSize((String)request.getAttribute("fileMaxSize"));	//첨부파일 등록
		
		pledgeFileSvc.registPledgeFile(request, pledgeFileVo);
	}
	
	public void removePledge(PledgeFVo pledgeFVo) throws Exception {
		pledgeFDao.deletePledge(pledgeFVo);
	}
	
	@SuppressWarnings("rawtypes")
	public List retrievePagePledge(PledgeFVo pledgeFVo) throws Exception {
		return pledgeFDao.selectPagePledge(pledgeFVo);
	}
	
	@SuppressWarnings("rawtypes")
	public List retrieveListPledge(PledgeFVo pledgeFVo) throws Exception {
		return pledgeFDao.selectListPledge(pledgeFVo);
	}
	
	public int retrieveTotalCntPledge(PledgeFVo pledgeFVo) throws Exception {
		return pledgeFDao.selectTotalCntPledge(pledgeFVo);
	}
	
	public PledgeFVo retrievePledge(PledgeFVo pledgeFVo) throws Exception {
		return pledgeFDao.selectPledge(pledgeFVo);
	}
	
	public void PledgeCountUpdate(PledgeFVo pledgeFVo) throws Exception {
		pledgeFDao.updateCountPledge(pledgeFVo);
	}
	
	public PledgeFVo retrievePledgeRecommend(PledgeFVo pledgeFVo) throws Exception {
		return pledgeFDao.selectPledgeRecommend(pledgeFVo);
	}
	
	public void registPledgeRecommend(PledgeFVo pledgeFVo) throws Exception {
		pledgeFDao.insertPledgeRecommend(pledgeFVo);
		pledgeFDao.updateRecommendPledge(pledgeFVo);
	}
	
}