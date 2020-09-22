package egovframework.injeinc.foffice.ex.reservationMy.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.cmm.CmmLogCtr;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationUserInfoVo;
import egovframework.injeinc.common.files.service.CmmFilesSvc;
import egovframework.injeinc.foffice.ex.reservationMain.service.ReservationMainUserSvc;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class ReservationMyUserCtr extends CmmLogCtr {

	@Resource(name = "ReservationMainUserSvc")
	private ReservationMainUserSvc reservationMainUserSvc;
	
	@Resource(name = "CmmFilesSvc")
	private CmmFilesSvc cmmFilesSvc;
	
	/** 마이페이지 나의예약목록 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/{strSitePath}/{strDomain}/foffice/ex/reservationMy/list.do")
	public String list(
			HttpServletRequest request,
			ModelMap model,
			@PathVariable("strSitePath") String strSitePath,
			@PathVariable("strDomain") String strDomain,
			@ModelAttribute("ReservationUserInfoVo") ReservationUserInfoVo reservationUserInfoVo)
			throws Exception {
		
		
		String dupkey = (String) request.getSession().getAttribute("ss_dupkey");
		reservationUserInfoVo.setRuDupkey(dupkey);

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(reservationUserInfoVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(reservationUserInfoVo
				.getPageUnit());
		paginationInfo.setPageSize(reservationUserInfoVo.getPageSize());

		reservationUserInfoVo.setFirstIndex(paginationInfo
				.getFirstRecordIndex());
		reservationUserInfoVo.setLastIndex(paginationInfo.getLastRecordIndex());
		reservationUserInfoVo.setRecordCountPerPage(paginationInfo
				.getRecordCountPerPage());
		
		
		List<EgovMap> reservationMapList = reservationMainUserSvc
				.selectTotalReservationPagMapList(reservationUserInfoVo);

		paginationInfo.setTotalRecordCount(reservationMainUserSvc
				.selectTotalReservationPagMapListCnt(reservationUserInfoVo));

		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("reservationMapList", reservationMapList);
		return "injeinc/foffice/ex/reservationMy/reservationMy_list";
	}

	

}
