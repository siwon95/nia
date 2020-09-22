package egovframework.injeinc.boffice.ex.reservationHealth.util;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import egovframework.injeinc.boffice.ex.reservationHealth.service.ReservationHealthSvc;
import egovframework.injeinc.boffice.ex.reservationHealth.vo.ReservationIndexHealthVo;
import egovframework.injeinc.common.util.EgovStringUtil;

@Controller
public class ReservationHealthUtil {
	
	@SuppressWarnings("rawtypes")
	public static List getQuestionNAnswerList(String riIdx, String ruIdx) throws Exception {
		
		if(EgovStringUtil.isEmpty(riIdx)) {
			return null;
		}
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		ServletContext conext = session.getServletContext();
		WebApplicationContext wContext = WebApplicationContextUtils.getWebApplicationContext(conext);
		ReservationHealthSvc reservationHealthSvc = (ReservationHealthSvc)wContext.getBean("ReservationHealthSvc");
		
		List list = null;
		
		ReservationIndexHealthVo reservationIndexVo = new ReservationIndexHealthVo();
		reservationIndexVo.setRiIdx(riIdx);
		reservationIndexVo.setRuIdx(ruIdx);
		list = reservationHealthSvc.retrieveListReservationHealthItem(reservationIndexVo);
		
		return list;
		
	}
}