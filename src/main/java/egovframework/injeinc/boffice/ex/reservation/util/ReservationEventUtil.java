package egovframework.injeinc.boffice.ex.reservation.util;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import egovframework.injeinc.boffice.ex.reservation.service.ReservationEventSvc;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationIndexVo;
import egovframework.injeinc.common.util.EgovStringUtil;

@Controller
public class ReservationEventUtil {
	
	@SuppressWarnings("rawtypes")
	public static List getQuestionNAnswerList(String riIdx, String ruIdx) throws Exception {
		
		if(EgovStringUtil.isEmpty(riIdx)) {
			return null;
		}
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		ServletContext conext = session.getServletContext();
		WebApplicationContext wContext = WebApplicationContextUtils.getWebApplicationContext(conext);
		ReservationEventSvc reservationEventSvc = (ReservationEventSvc)wContext.getBean("ReservationEventSvc");
		
		List list = null;
		
		ReservationIndexVo reservationIndexVo = new ReservationIndexVo();
		reservationIndexVo.setRiIdx(riIdx);
		reservationIndexVo.setRuIdx(ruIdx);
		list = reservationEventSvc.retrieveListReservationEventItem(reservationIndexVo);
		
		return list;
		
	}
}