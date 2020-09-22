package egovframework.injeinc.boffice.ex.poll.util;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import egovframework.injeinc.boffice.ex.poll.service.PollAnswerSvc;
import egovframework.injeinc.boffice.ex.poll.service.PollListSvc;
import egovframework.injeinc.boffice.ex.poll.vo.PollAnswerViewVo;
import egovframework.injeinc.boffice.ex.poll.vo.PollListVo;
import egovframework.injeinc.common.util.EgovStringUtil;

@Controller
public class PollUtil {
	
	public static int getCount(String pqIdx, String pqType, String paAnswer) throws Exception {
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		ServletContext conext = session.getServletContext();
		WebApplicationContext wContext = WebApplicationContextUtils.getWebApplicationContext(conext);
		PollAnswerSvc pollAnswerSvc = (PollAnswerSvc)wContext.getBean("PollAnswerSvc");
		
		int count = 0;
		
		System.out.println("pollutil pqIdx : "+pqIdx);
		System.out.println("pollutil pqType : "+pqType);
		System.out.println("pollutil paAnswer : "+paAnswer);
		
		if(!EgovStringUtil.isEmpty(pqIdx) && !EgovStringUtil.isEmpty(pqType) && !EgovStringUtil.isEmpty(paAnswer)) {
			PollAnswerViewVo pollAnswerViewVo = new PollAnswerViewVo();
			pollAnswerViewVo.setPqIdx(pqIdx);
			pollAnswerViewVo.setPqType(pqType);
			pollAnswerViewVo.setPaAnswer(paAnswer);
			count =pollAnswerSvc.retrievePollAnswerCnt(pollAnswerViewVo);
		}
		
		return count;
		
	}
	
	@SuppressWarnings("rawtypes")
	public static List getContentListAll(String puIdx) throws Exception {
		
		if(EgovStringUtil.isEmpty(puIdx)) {
			return null;
		}
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		ServletContext conext = session.getServletContext();
		WebApplicationContext wContext = WebApplicationContextUtils.getWebApplicationContext(conext);
		PollAnswerSvc PollAnswerSvc = (PollAnswerSvc)wContext.getBean("PollAnswerSvc");
		
		List list = null;
		
		PollAnswerViewVo PollAnswerViewVo = new PollAnswerViewVo();
		PollAnswerViewVo.setPuIdx(puIdx);
		list = PollAnswerSvc.retrieveListPollAnswer(PollAnswerViewVo);
			
		return list;
		
	}
	
	public static PollListVo getPollListInfo(String puPlidx) throws Exception {
		
		if(EgovStringUtil.isEmpty(puPlidx)) {
			return null;
		}
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		ServletContext conext = session.getServletContext();
		WebApplicationContext wContext = WebApplicationContextUtils.getWebApplicationContext(conext);
		PollListSvc PollListSvc = (PollListSvc)wContext.getBean("PollListSvc");
		
		PollListVo result = null;
		
		PollListVo PollListVo = new PollListVo();
		PollListVo.setPlIdx(puPlidx);
		result = PollListSvc.retrievePollList(PollListVo);
		
		
		return result;
		
	}
}