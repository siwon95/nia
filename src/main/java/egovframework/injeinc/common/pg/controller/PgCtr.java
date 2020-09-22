package egovframework.injeinc.common.pg.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
//import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.EgovMessageSource;
import egovframework.cmm.EgovWebUtil;
import egovframework.cmm.Message;
import egovframework.cmm.service.EgovProperties;
import egovframework.injeinc.boffice.ex.reservation.service.ReservationEventSvc;
import egovframework.injeinc.common.code.service.CodeSvc;
import egovframework.injeinc.common.pg.service.PgSvc;
import egovframework.injeinc.common.pg.vo.PgVo;
import egovframework.injeinc.common.util.EgovStringUtil;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 공통로그인 모델 클래스
 * @author 공통서비스 개발팀 이동열
 */

@Controller
public class PgCtr extends CmmLogCtr{
	
	@Autowired
	private PgSvc pgSvc;
	
	@Autowired(required=true)
	private MappingJackson2JsonView jsonView;
	
	@Autowired(required=true)
	private CodeSvc codeSvc;
	
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
    @Autowired
	private DataSourceTransactionManager transactionManager;

    @Resource(name = "ReservationEventSvc")
	 private ReservationEventSvc reservationEventSvc;

    /**
     * PG 리스트
     * @param pgVo
     * @param model
     * @return
     * @throws Exception
     */
	@RequestMapping(value= "/boffice/common/pg/Pg_List.do")
	public String pgList(@ModelAttribute("PgVo") PgVo pgVo, ModelMap model) throws Exception {
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(pgVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(pgVo.getPageUnit());
		paginationInfo.setPageSize(pgVo.getPageSize());

		pgVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		pgVo.setLastIndex(paginationInfo.getLastRecordIndex());
		pgVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		@SuppressWarnings("rawtypes")
		List resultList = pgSvc.retrieveListPg(pgVo);

		int totCnt = pgSvc.retrievePagPg(pgVo); 
		paginationInfo.setTotalRecordCount(totCnt);
		if(model != null){
			model.addAttribute("totCnt", totCnt);
			model.addAttribute("paginationInfo", paginationInfo);
			model.addAttribute("PgVo", pgVo);
			model.addAttribute("resultList", resultList);
		}
		return "injeinc/common/pg/pg_list";
 	}

	/**
	 * PG 등록폼
	 * @param pgVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/boffice/common/pg/Pg_RegForm.do")
	public String Pg_RegForm(@ModelAttribute("PgVo") PgVo pgVo, ModelMap model) throws Exception {
		pgVo.setType("regist");
		@SuppressWarnings("rawtypes")
		List resultList = reservationEventSvc.retrieveListSuervisionOrg();
		model.addAttribute("resultList", resultList);
		model.addAttribute("PgVo", pgVo);
		return "injeinc/common/pg/pg_form";
	}

	/**
	 * PG 등록
	 * @param request
	 * @param pgVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/boffice/common/pg/Pg_Reg.do")
	public String pgReg(HttpServletRequest request, @ModelAttribute("PgVo") PgVo pgVo, ModelMap model) throws Exception {
		String SVC_MSGE = "";
		HttpSession ses = request.getSession();

		BufferedWriter out = null;
		BufferedWriter out2 = null;

		try {
			pgVo.setRegId((String) ses.getAttribute("SesUserId")); // 등록자 아이디set

			SVC_MSGE = Message.getMessage("201.message"); // 등록
			String fullPathUpload = "";
			String fullPath = "";
			String confPath = pgVo.getLgdacomConfPath();

			String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
			String uploadPath = Message.getMessage("Globals.fileLgdaconPath"); // 첨부파일경로 fileUploadProperties.properties에서 경로 설정
			fullPathUpload = rootPath + uploadPath + confPath + "/lgdacom/conf/";
			fullPath = uploadPath + confPath + "/lgdacom/";
			pgVo.setLgdacomConfFullPath(fullPath);
			pgSvc.registPg(pgVo); // 등록
			debugLog("fullPath :" + fullPath);
			File saveFolder = new File(EgovWebUtil.filePathBlackList(fullPathUpload));
			if (!saveFolder.exists() || saveFolder.isFile())
			saveFolder.mkdirs();
			out = new BufferedWriter(new FileWriter(EgovWebUtil.filePathBlackList(fullPathUpload) + "mall.conf"));
			out.write("server_id = 01");
			out.newLine();
			out.newLine();
			out.write("timeout = 60");
			out.newLine();
			out.newLine();
			out.write(";log_level      0: FATAL; 1: ERROR; 2: WARNING; 3: INFO; 4: DEBUG");
			out.newLine();
			out.write("log_level = 4");
			out.newLine();
			out.newLine();
			out.write("verify_cert = 0");
			out.newLine();
			out.newLine();
			out.write("verify_host = 0");
			out.newLine();
			out.newLine();
			out.write("report_error = 1");
			out.newLine();
			out.newLine();
			out.write("output_UTF8 = 1");
			out.newLine();
			out.newLine();
			out.write("auto_rollback = 1");
			out.newLine();
			out.newLine();
			out.newLine();
			// 추후 운영에 올릴시 경로수정 시작!!!!!!!!
			out.write("log_dir = /was/schome/lgdacom/log");
			out.newLine();
			out.newLine();
			out.write(";log_dir = C:/eGovFrameDev-2.7.1-64bit/workspace/SCGHOME/src/main/webapp/lgdacom/log");
			out.write(";log_dir =");
			out.newLine();
			out.newLine();
			// 추후 운영에 올릴시 경로수정 끝!!!!!!!
			out.newLine();
			out.newLine();
			out.newLine();
			out.newLine();
			out.write("t" + pgVo.getCstMid() + " = " + pgVo.getLgdMertkey());
			out.newLine();
			out.write(pgVo.getCstMid() + " = " + pgVo.getLgdMertkey());
			out.newLine();
			out.newLine();

			out2 = new BufferedWriter(new FileWriter(EgovWebUtil.filePathBlackList(fullPathUpload) + "lgdacom.conf"));
			out2.write("url = https://xpayclient.lgdacom.net/xpay/Gateway.do");
			out2.newLine();
			out2.write("test_url = https://xpayclient.lgdacom.net:7443/xpay/Gateway.do");
			out2.newLine();
			out2.write("aux_url = http://xpayclient.lgdacom.net:7080/xpay/Gateway.do");
			out2.newLine();

			out2.close();

		} catch (IOException e) {
			out.close();
			out2.close();
			SVC_MSGE = Message.getMessage("901.message"); // 오류
		} catch (Exception e) {
			out.close();
			out2.close();
			SVC_MSGE = Message.getMessage("901.message"); // 오류
		} finally {
			out.close();
			out2.close();
		}
		return alert("/boffice/common/pg/Pg_List.do", SVC_MSGE, model);
	}

	/**
	 * 아이디 중복 체크
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/boffice/common/pg/PgId_Ax.do")
	public void pgIdAx(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String cstMid = EgovStringUtil.nullConvert(request.getParameter("cstMid"));

		HashMap<String, String> param = new HashMap<String, String>();
		param.put("cstMid", cstMid);

		HashMap<String, Object> jsonMap = new HashMap<String, Object>();

		int idCnt = pgSvc.retrieveCstMidCnt(param); // 아이디 사용여부 조회

		if (idCnt > 0) {
			jsonMap.put("message", "N");
		} else {
			jsonMap.put("message", "Y");
		}

		jsonView.render(jsonMap, request, response);
	}

	/**
	 * 상점아이디 상점키 조회
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/site/{strDomain}/boffice/common/pg/Pg_MidAx.do")
	public void pgMidAx(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PgVo pgVo = new PgVo();
		String Code = EgovStringUtil.nullConvert(request.getParameter("DataCode")); // 해당페이지 코드
		debugLog("Code===>" + Code);
		pgVo.setMertCode(Code);
		PgVo result = pgSvc.retrieveMidPg(pgVo); // path 사용여부 조회
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("mertId", result.getCstMid());
		jsonMap.put("mertKey", result.getLgdMertkey());

		jsonView.render(jsonMap, request, response);
	}

	/**
	 * PG 수정폼
	 * @param pgVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/boffice/common/pg/Pg_ModForm.do")
	public String Pg_ModForm(@ModelAttribute("PgVo") PgVo pgVo, ModelMap model) throws Exception {
		PgVo result = pgSvc.retrievePg(pgVo);

		if (!(result.getPgDeptTelNum().equals(""))) {
			String[] tel= result.getPgDeptTelNum().split("-");
			String tel1 = tel[0];
			String tel2 = tel[1];
			String tel3 = tel[2];
			result.setTel1(tel1);
			result.setTel2(tel2);
			result.setTel3(tel3);
		}

		result.setType("modify");
		@SuppressWarnings("rawtypes")
		List resultList = reservationEventSvc.retrieveListSuervisionOrg();
		model.addAttribute("resultList", resultList);
		model.addAttribute("PgVo", result);

		return "injeinc/common/pg/pg_form";
	}

	/**
	 * PG 수정
	 * @param request
	 * @param pgVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/boffice/common/pg/Pg_Mod.do")
	public String pgMod(HttpServletRequest request, @ModelAttribute("PgVo") PgVo pgVo, ModelMap model) throws Exception {
		String SVC_MSGE = "";
		HttpSession ses = request.getSession();

		try {
			pgVo.setModId((String) ses.getAttribute("SesUserId")); // 등록자 아이디set
			pgSvc.modifyPg(pgVo); // 수정
			SVC_MSGE = Message.getMessage("401.message"); // 수정
		} catch (RuntimeException e) {
			SVC_MSGE = Message.getMessage("901.message"); // 오류
		} catch (Exception e) {
			SVC_MSGE = Message.getMessage("901.message"); // 오류
		}

		return alert("/boffice/common/pg/Pg_List.do", SVC_MSGE, model);
	}

	/**
	 * PG 삭제
	 * @param request
	 * @param pgVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/boffice/common/pg/Pg_Rmv.do")
	public String pgRmv(HttpServletRequest request, @ModelAttribute("PgVo") PgVo pgVo, ModelMap model) throws Exception {
		String SVC_MSGE = "";
		String filePath = pgVo.getLgdacomConfFullPath() + "/conf/"; // 첨부파일경로 fileUploadProperties.properties에서 경로 설정
		String fileName = "mall.conf";
		String fileName2 = "lgdacom.conf";

		try {
			pgSvc.removePg(pgVo); // 삭제
			File file = new File(EgovWebUtil.filePathBlackList(filePath + fileName));
			File file2 = new File(EgovWebUtil.filePathBlackList(filePath + fileName2));

			if (!file.exists()) {
				// System.out.println(fileName + " File not exist!");
			} else if (file.isFile()) {
				file.delete(); // 파일 삭제
				file2.delete(); // 파일 삭제
			}
			SVC_MSGE = Message.getMessage("501.message"); // 수정
		} catch (RuntimeException e) {
			SVC_MSGE = Message.getMessage("901.message"); // 오류
		} catch (Exception e) {
			SVC_MSGE = Message.getMessage("901.message"); // 오류
		}

		return alert("/boffice/common/pg/Pg_List.do", SVC_MSGE, model);
	}

	/** PG 모듈 STEP-1 */
	@RequestMapping(value = "/site/{strDomain}/boffice/common/pg/Pg_Process_Reg.do")
	public String pgProcessReg(HttpServletRequest request, @ModelAttribute("PgVo") PgVo pgVo, ModelMap model) throws Exception {
		HttpSession ses = request.getSession();

		String cmmCode = pgVo.getCstMid();
		System.out.println("pgProcessReg==>=====cmmCode==>" + cmmCode);
		System.out.println("pgProcessReg==>=====cmmCode==>" + cmmCode);
		System.out.println("pgProcessReg==>=====cmmCode==>" + cmmCode);
		pgVo.setPgMid(cmmCode);
		PgVo CmmPgVo = pgSvc.retrieveCmmPg(pgVo);

		String CmmMertCode = CmmPgVo.getMertCode(); // 상점아이디로 받아온 코드 구분자

		debugLog("CmmMertCode===>" + CmmMertCode);
		if (CmmMertCode.equals("RM030200")) {
			model.addAttribute("UrlStr", "hs");
		} else if (CmmMertCode.equals("RM020200")) {
			model.addAttribute("UrlStr", "ta");
		}

		pgVo.setLgdOid(EgovStringUtil.nullConvert(request.getParameter("lgdOid")));
		pgVo.setLgdMertkey(EgovStringUtil.nullConvert(request.getParameter("lgdMertkey")));
		pgVo.setLgdAmount(EgovStringUtil.nullConvert(request.getParameter("lgdAmount")));

		if (request.getParameter("lgdBuyer") == null || request.getParameter("lgdBuyer") == "") {
			pgVo.setLgdBuyer((String) ses.getAttribute("ss_name")); // 추후사용자세션변경
		} else {
			pgVo.setLgdBuyer(request.getParameter("lgdBuyer"));
		}

		pgVo.setLgdProductInfo(EgovStringUtil.nullConvert(request.getParameter("lgdProductInfo")));
		pgVo.setLgdbuyerEmail(EgovStringUtil.nullConvert(request.getParameter("lgdbuyerEmail")));
		pgVo.setLgdCustomUsablePay(EgovStringUtil.nullConvert(request.getParameter("lgdCustomUsablePay")));
		pgVo.setLgdReturnUrl(EgovStringUtil.nullConvert(request.getParameter("lgdReturnUrl")));

		return "injeinc/common/pg/pg_process";
	}

	/** PG 모듈 STEP-2 성공실패(리턴페이지) */
	@RequestMapping(value = "/site/{strDomain}/boffice/common/pg/Pg_Process_PayRes.do")
	public String pgProcessPayRes(HttpServletRequest request, @ModelAttribute("PgVo") PgVo pgVo, ModelMap model) throws Exception {
		// HttpSession ses = request.getSession();
		String lgId = EgovStringUtil.nullConvert(request.getParameter("LGD_MID"));
		// lgId = lgId.substring(1,lgId.length()); //lgId 나중에 지워야됨. t삭제
		// //20151027 주석
		// debugLog("LG아이디 SUBSTR TEST(SERVICE)모드 일때 바꿔야됨:::::::::::::::"+lgId);
		pgVo.setCstMid(lgId);
		PgVo result = pgSvc.retrievePg(pgVo);
		pgVo.setLgdacomConfFullPath(EgovProperties.getProperty("WasServer.ROOT_PATH") + result.getLgdacomConfFullPath());

		String lgdRespCode = EgovStringUtil.nullConvert(request.getParameter("LGD_RESPCODE"));
		String lgdRespMsg = EgovStringUtil.nullConvert(request.getParameter("LGD_RESPMSG"));
		String lgdPayKey = EgovStringUtil.nullConvert(request.getParameter("LGD_PAYKEY"));
		String lgdPlatform = EgovStringUtil.nullConvert(request.getParameter("CST_PLATFORM"));
		String lgMertKey = EgovStringUtil.nullConvert(request.getParameter("LGD_MERTKEY"));

		pgVo.setLgdMertkey(lgMertKey);
		pgVo.setLgdPlatform(lgdPlatform); // test,service 인지
		pgVo.setLgdRespCode(lgdRespCode);
		pgVo.setLgdRespMsg(lgdRespMsg);
		pgVo.setLgdPayKey(lgdPayKey);

		return "injeinc/common/pg/pg_process_payres";
	}

	/** PG 모듈 STEP-2 성공실패(데이터저장) */
	@RequestMapping(value = "/site/{strDomain}/boffice/common/pg/Pg_Mgr_Reg.do")
	public void pgMgrReg(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("PgVo") PgVo pgVo, ModelMap model) throws Exception {
		// HttpSession ses = request.getSession();

		String SVC_MSGE = "";
		pgVo.setPgOid(EgovStringUtil.nullConvert(request.getParameter("pgOid")));
		pgVo.setPgAmount(EgovStringUtil.nullConvert(request.getParameter("pgAmount")));
		pgVo.setPgName(EgovStringUtil.nullConvert(request.getParameter("pgName")));
		pgVo.setPgUse(EgovStringUtil.nullConvert(request.getParameter("pgUse")));
		pgVo.setPgMid(EgovStringUtil.nullConvert(request.getParameter("pgMid")));
		pgVo.setPgRespcode(EgovStringUtil.nullConvert(request.getParameter("pgRespcode")));
		pgVo.setPgTid(EgovStringUtil.nullConvert(request.getParameter("pgTid")));
		pgVo.setPgRespmsg(EgovStringUtil.nullConvert(request.getParameter("pgRespmsg")));
		pgVo.setCmmCode(EgovStringUtil.nullConvert(request.getParameter("cmmCode")));
		pgVo.setLgdbuyerEmail(EgovStringUtil.nullConvert(request.getParameter("lgdbuyerEmail")));
		pgVo.setRegId(EgovStringUtil.nullConvert(request.getParameter("regId")));

		try {
			pgSvc.registMgrPg(pgVo);
			SVC_MSGE = Message.getMessage("100.code"); // 저장완료
		} catch (RuntimeException e) {
			SVC_MSGE = Message.getMessage("901.code"); // 오류
		} catch (Exception ex) {
			SVC_MSGE = Message.getMessage("901.code"); // 에러
		}

		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("SVC_MSGE", SVC_MSGE);
		jsonMap.put("Tid", pgVo.getPgTid());
		jsonView.render(jsonMap, request, response);
	}

	/** 결재완료 페이지 */
	@RequestMapping(value = "/site/{strDomain}/boffice/common/pg/Pg_Process_Success.do")
	public String pgProcessSuccess(HttpServletRequest request, @ModelAttribute("PgVo") PgVo pgVo, ModelMap model) throws Exception {
		debugLog("111====>" + request.getParameter("LGD_OID"));
		debugLog("222====>" + request.getParameter("LGD_MID")); // 상점코드
		String cmmCode = EgovStringUtil.nullConvert(request.getParameter("LGD_MID"));
		String returnUrl = "";
		// cmmCode = cmmCode.substring(1,cmmCode.length());
		// debugLog("cmmCode==>"+cmmCode);
		pgVo.setPgMid(cmmCode);
		PgVo CmmPgVo = pgSvc.retrieveCmmPg(pgVo);

		String CmmMertCode = CmmPgVo.getMertCode(); // 상점아이디로 받아온 코드 구분자
		String PayCode = EgovStringUtil.nullConvert(request.getParameter("LGD_OID"));
		// 결재접수에서 완료로 떨어지면 결재완료 태안 업데이트
		if (CmmMertCode.equals("RM030200")) {
			//TaFVo taFVo = new TaFVo();
			//taFVo.setPayCode(PayCode);
			//taFSvc.updateRoomItemTa(taFVo); // 업데이트 치고 내부로직
			//TaFVo taFSuccessVo = taFSvc.retrievePgRoomItem(taFVo);
			//model.addAttribute("Vo", taFSuccessVo);
			model.addAttribute("CmmVo", "hs");
			returnUrl = "injeinc/common/pg/pg_success";
		} else if (CmmMertCode.equals("RM020200")) {
			//TaFVo taFVo = new TaFVo();
			//taFVo.setPayCode(PayCode);
			//taFSvc.updateRoomItemTa(taFVo); // 업데이트 치고 내부로직
			//TaFVo taFSuccessVo = taFSvc.retrievePgRoomItem(taFVo);
			//model.addAttribute("Vo", taFSuccessVo);
			model.addAttribute("CmmVo", "ta");
			returnUrl = "injeinc/common/pg/pg_success";
		}

		return returnUrl;
	}

	/** 취소 페이지 */
	@RequestMapping(value = "/site/{strDomain}/boffice/common/pg/Pg_Process_Cancel.do")
	public String pgProcessCancel(HttpServletRequest request, @ModelAttribute("PgVo") PgVo pgVo, ModelMap model) throws Exception {
		// 결재접수에서 완료로 떨어지면 결재완료 태안 업데이트
		// 추가 결재 할때 상점아이디를 받아서 상점아이디에 공통 코드 를 받아서 구분으로 데이터 수정 추가로직 추후만들예정
		pgVo.setPgMid((String) EgovStringUtil.nullConvert(request.getParameter("LGD_MID")));
		pgVo.setPgTid((String) EgovStringUtil.nullConvert(request.getParameter("LGD_TID")));

		String lgId = EgovStringUtil.nullConvert(request.getParameter("LGD_MID"));

		pgVo.setCstMid(lgId);
		PgVo result = pgSvc.retrievePg(pgVo);
		pgVo.setLgdacomConfFullPath(EgovProperties.getProperty("WasServer.ROOT_PATH") + result.getLgdacomConfFullPath());

		return "injeinc/common/pg/pg_cancel";
	}

	/** PG 모듈 STEP-2 취소(데이터저장) */
	@RequestMapping(value = "/site/{strDomain}/boffice/common/pg/Pg_Mgr_Mod.do")
	public void pgMgrMod(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("PgVo") PgVo pgVo, ModelMap model) throws Exception {
		HttpSession ses = request.getSession();
		// 추가 결재 할때 상점아이디를 받아서 상점아이디에 공통 코드 를 받아서 구분으로 데이터 수정 추가로직 추후만들예정
		String SVC_MSGE = "";
		pgVo.setPgTid(EgovStringUtil.nullConvert(request.getParameter("pgTid")));
		pgVo.setPgRespcode(EgovStringUtil.nullConvert(request.getParameter("pgRespcode")));
		pgVo.setPgRespmsg(EgovStringUtil.nullConvert(request.getParameter("pgRespmsg")));
		pgVo.setPgMid(EgovStringUtil.nullConvert(request.getParameter("pgMid")));

		pgVo.setModId((String) ses.getAttribute("ss_id"));
		debugLog("pgMidUse======>" + pgVo.getPgMid());
		// 상점아이디로 공통코드조회 (PG)
		pgVo.setPgMid(pgVo.getPgMid());
		PgVo CmmPgVo = pgSvc.retrieveCmmPg(pgVo);
		debugLog("CmmPgVo.getMertCode()======>" + CmmPgVo.getMertCode());
		pgVo.setCmmCode(CmmPgVo.getMertCode());

		try {
			pgSvc.updatePgMgr(pgVo); // 업데이트 치고 내부로직
			SVC_MSGE = Message.getMessage("100.code"); // 저장완료
		} catch (RuntimeException ex) {
			SVC_MSGE = Message.getMessage("901.code"); // 에러
		} catch (Exception ex) {
			SVC_MSGE = Message.getMessage("901.code"); // 에러
		}

		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("SVC_MSGE", SVC_MSGE);
		jsonMap.put("Tid", pgVo.getPgTid());
		jsonView.render(jsonMap, request, response);
	}

	/** PG 모듈 STEP-2 실패(데이터수정) */
	@RequestMapping(value = "/site/{strDomain}/boffice/common/pg/Pg_Mgr_Miss.do")
	public void pgMgrMiss(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("PgVo") PgVo pgVo, ModelMap model) throws Exception {
		// HttpSession ses = request.getSession();
		String SVC_MSGE = "";
		String CmmStr = EgovStringUtil.nullConvert(request.getParameter("LGD_CMMCODE"));

		// 주문번호로 해당테이블을 다시조회 하여 업데이트 미결재로
		// 추가 결재 할때 상점아이디를 받아서 상점아이디에 공통 코드 를 받아서 구분으로 데이터 수정 추가로직 추후만들예정
		debugLog("1--->" + request.getParameter("LGD_OID"));
		debugLog("CmmStr--->" + CmmStr);
		pgVo.setPgOid(EgovStringUtil.nullConvert(request.getParameter("LGD_OID")));
		try {
			pgVo.setCmmCode(CmmStr);
			pgSvc.updatePgMissMgr(pgVo); // 업데이트 치고 내부로직
			SVC_MSGE = Message.getMessage("100.code"); // 저장완료
		} catch (RuntimeException ex) {
			SVC_MSGE = Message.getMessage("901.code"); // 에러
		} catch (Exception ex) {
			SVC_MSGE = Message.getMessage("901.code"); // 에러
		}

		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("SVC_MSGE", SVC_MSGE);
		jsonView.render(jsonMap, request, response);
	}
	
	@RequestMapping(value = "/boffice/common/pg/PgByMertCodeAx.do")
	public void PgByMertCodeAx(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String mertCode = EgovStringUtil.nullConvert(request.getParameter("mertCode"));

		HashMap<String, String> param = new HashMap<String, String>();
		param.put("mertCode", mertCode);
		List<PgVo> pgVoList = pgSvc.retrievePgByMertCodeAx(param);

		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("resultList", pgVoList);

		jsonView.render(jsonMap, request, response);
	}

}