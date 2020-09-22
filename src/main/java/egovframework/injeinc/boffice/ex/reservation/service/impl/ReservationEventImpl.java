package egovframework.injeinc.boffice.ex.reservation.service.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import egovframework.cmm.service.EgovFileMngService;
import egovframework.cmm.service.EgovFileMngUtil;
import egovframework.cmm.service.EgovProperties;
import egovframework.cmm.service.FileVO;
import egovframework.injeinc.boffice.ex.reservation.dao.ReservationEventDao;
import egovframework.injeinc.boffice.ex.reservation.service.ReservationEventSvc;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationAddItemVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationIndexVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationItemVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationUserAnswerVo;
import egovframework.injeinc.boffice.ex.reservation.vo.ReservationUserInfoVo;
import egovframework.injeinc.boffice.ex.reservationFac.vo.ReservationFacVo;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import lgdacom.XPayClient.XPayClient;

@Service("ReservationEventSvc")
public class ReservationEventImpl implements ReservationEventSvc{

	@Resource(name = "ReservationEventDao")
	private ReservationEventDao reservationEventDao;

    // 파일정보 관리
    @Resource(name = "EgovFileMngService")
    private EgovFileMngService egovFileMngService;

    // 파일 처리 관련 유틸리티
    @Resource(name = "EgovFileMngUtil")
    private EgovFileMngUtil egovFileMngUtil;

	/**
     * 예약등록
     * @param ReservationIndexVo
     * @throws Exception
     */
	public void registReservationEvent(ReservationIndexVo reservationIndexVo) throws Exception{
		reservationEventDao.createReservationEvent(reservationIndexVo);
	}

	/**
     * 조회수 증가
     * @param ReservationIndexVo
     * @throws Exception
     */
	public void modifyReservationEventReadCnt(ReservationIndexVo reservationIndexVo) throws Exception{
		reservationEventDao.updateReservationEventReadCnt(reservationIndexVo);
	}

	/**
     * 예약등록(추가항목1)
     * @param reservationAddItemVo
     * @throws Exception
     */
	public void registReservationEventAddItem(ReservationAddItemVo reservationAddItemVo) throws Exception{
		reservationEventDao.createReservationEventAddItem(reservationAddItemVo);
	}
	
	public void registReservationEventAddItem2(ReservationAddItemVo reservationAddItemVo) throws Exception{
		reservationEventDao.createReservationEventAddItem2(reservationAddItemVo);
	}

	/**
     * 예약등록(추가항목2)
     * @param reservationItemVo
     * @throws Exception
     */
	public void registReservationEventItem(ReservationItemVo reservationItemVo) throws Exception{
		reservationEventDao.createReservationEventItem(reservationItemVo);
	}

	/**
     * 예약수정
     * @param ReservationIndexVo
     * @throws Exception
     */
	public void modifyReservationEvent(ReservationIndexVo reservationIndexVo) throws Exception{
		reservationEventDao.updateReservationEvent(reservationIndexVo);
	}

	/**
     * 예약삭제
     * @param ReservationIndexVo
     * @throws Exception
     */
	public void removeReservationEvent(ReservationIndexVo reservationIndexVo) throws Exception{
		reservationEventDao.deleteReservationEvent(reservationIndexVo);
	}

	/**
     * 예약상세
     * @param ReservationIndexVo
     * @return ReservationIndexVo
     * @throws Exception
     */
	public ReservationIndexVo retrieveReservationEvent(ReservationIndexVo reservationIndexVo) throws Exception{
		return reservationEventDao.selectReservationEvent(reservationIndexVo);
	}
	
	public ReservationIndexVo retrieveReservationArt(ReservationIndexVo reservationIndexVo) throws Exception{
		return reservationEventDao.selectReservationArt(reservationIndexVo);
	}
	
	
	/**
     * 페이징
     * @param ReservationIndexVo
     * @return Map<String, Object>
     * @throws Exception
     */
	public Map<String, Object> retrievePagReservationEvent(ReservationIndexVo reservationIndexVo) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();

		List<ReservationIndexVo> result = reservationEventDao.selectPagReservationEvent(reservationIndexVo);
		int cnt = reservationEventDao.selectReservationEventCnt(reservationIndexVo);

		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}
	
	public Map<String, Object> retrievePagReservationEventLib(ReservationIndexVo reservationIndexVo) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();

		List<ReservationIndexVo> result = reservationEventDao.selectPagReservationEventLib(reservationIndexVo);
		int cnt = reservationEventDao.selectReservationEventCntLib(reservationIndexVo);

		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}
	
	public Map<String, Object> retrievePagArtMovie(ReservationIndexVo reservationIndexVo) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();

		List<ReservationIndexVo> result = reservationEventDao.selectPagArtMovieList(reservationIndexVo);
		int cnt = reservationEventDao.selectArtMovieListCnt(reservationIndexVo);

		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}
	
	public Map<String, Object> retrievePagArtReservation(ReservationIndexVo reservationIndexVo) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();

		List<ReservationIndexVo> result = reservationEventDao.selectPagArtReservationAll(reservationIndexVo);
		int cnt = reservationEventDao.selectPagArtReservationAllCnt();

		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}
	
	public List<ReservationIndexVo> selectPagArtAllList() throws Exception {
		return reservationEventDao.selectPagArtAllList();
	}
	
	public List<ReservationIndexVo> selectPagArtAllList2() throws Exception {
		return reservationEventDao.selectPagArtAllList2();
	}
	
	public List<ReservationIndexVo> selectArtReservationAll() throws Exception{
		return reservationEventDao.selectArtReservationAll();
	}
	
	public List<EgovMap> selectArtCalInfo(ReservationFacVo reservationFacVo) throws Exception{
		return (List<EgovMap>)reservationEventDao.selectArtCalInfo(reservationFacVo);
	}
	
	public List<EgovMap> selectArtCalInfoText(ReservationFacVo reservationFacVo) throws Exception{
		return (List<EgovMap>)reservationEventDao.selectArtCalInfoText(reservationFacVo);
	}
	
	/**
     * 추가항목 리스트
     * @param ReservationIndexVo
     * @return List<ReservationAddItemVo>
     * @throws Exception
     */
	public List<ReservationAddItemVo> retrieveListReservationEventAddItem(ReservationIndexVo reservationIndexVo) throws Exception{
		return reservationEventDao.selectListReservationEventAddItem(reservationIndexVo);
	}
	
	public List<ReservationAddItemVo> retrieveListReservationEventAddItem2(ReservationIndexVo reservationIndexVo) throws Exception{
		return reservationEventDao.selectListReservationEventAddItem2(reservationIndexVo);
	}
	
	/**
     * 추가항목 리스트
     * @param ReservationIndexVo
     * @return List<ReservationItemVo>
     * @throws Exception
     */
	public List<ReservationItemVo> retrieveListReservationEventItem(ReservationIndexVo reservationIndexVo) throws Exception{
		return reservationEventDao.selectListReservationEventItem(reservationIndexVo);
	}
	
	public List<ReservationIndexVo> retrieveListSuervisionOrg() throws Exception {
		return reservationEventDao.selectListSuervisionOrg();		
	}
	/**
     * 추가항목삭제
     * @param ReservationIndexVo
     * @throws Exception
     */
	public void removeReservationEventAddItem(ReservationIndexVo reservationIndexVo) throws Exception{
		reservationEventDao.deleteReservationEventAddItem(reservationIndexVo);
	}
	
	public void removeReservationEventAddItem2(ReservationIndexVo reservationIndexVo) throws Exception{
		reservationEventDao.deleteReservationEventAddItem2(reservationIndexVo);
	}

	/**
     * 추가항목삭제2
     * @param ReservationIndexVo
     * @throws Exception
     */
	public void removeReservationEventItem(ReservationIndexVo reservationIndexVo) throws Exception{
		reservationEventDao.deleteReservationEventItem(reservationIndexVo);
	}

	/**
     * 예약접수자 리스트
     * @param ReservationIndexVo
     * @return List<ReservationUserInfoVo>
     * @throws Exception
     */
	public List<ReservationUserInfoVo> retrieveListReservationEventUserInfo(ReservationIndexVo reservationIndexVo) throws Exception{
		return reservationEventDao.selectListReservationEventUserInfo(reservationIndexVo);
	}
	
	public List<ReservationUserInfoVo> retrieveListReservationEventUserInfoReturnFee(ReservationIndexVo reservationIndexVo) throws Exception{
		return reservationEventDao.selectListReservationEventUserInfoReturnFee(reservationIndexVo);
	}
	
	/**
     * 접수자 등록
     * @param ReservationUserInfoVo
     * @throws Exception
     */
	public void registReservationEventUserInfo(ReservationUserInfoVo reservationUserInfoVo) throws Exception{
		reservationEventDao.createReservationEventUserInfo(reservationUserInfoVo);
	}

	/**
     * 접수자 답변 등록
     * @param ReservationUserAnswerVo
     * @throws Exception
     */
	public void registReservationEventUserAnswer(ReservationUserAnswerVo reservationUserAnswerVo) throws Exception{
		reservationEventDao.createReservationEventUserAnswer(reservationUserAnswerVo);
	}

	/**
     * 접수자 상세
     * @param ReservationUserInfoVo
     * @return ReservationUserInfoVo
     * @throws Exception
     */
	public ReservationUserInfoVo retrieveReservationEventUserInfo(ReservationUserInfoVo reservationUserInfoVo) throws Exception{
		return reservationEventDao.selectReservationEventUserInfo(reservationUserInfoVo);
	}
	
	public ReservationUserInfoVo retrieveReservationEventUserInfoReturnFee(ReservationUserInfoVo reservationUserInfoVo) throws Exception{
		return reservationEventDao.selectReservationEventUserInfoReturnFee(reservationUserInfoVo);
	}

	public int retrieveReservationEventUserInfoDupCnt(ReservationUserInfoVo reservationUserInfoVo) throws Exception{
		return reservationEventDao.selectReservationEventUserInfoDupCnt(reservationUserInfoVo);
	}

	public int retrieveReservationEventRedundancyDupCnt(ReservationUserInfoVo reservationUserInfoVo) throws Exception{
		return reservationEventDao.selectReservationEventRedundancyDupCnt(reservationUserInfoVo);
	}

	/**
     * 접수자수정
     * @param ReservationUserInfoVo
     * @throws Exception
     */
	public void modifyReservationEventUserInfo(ReservationUserInfoVo reservationUserInfoVo) throws Exception{
		reservationEventDao.updateReservationEventUserInfo(reservationUserInfoVo);
	}

	/**
     * 접수자답변삭제
     * @param ReservationUserInfoVo
     * @throws Exception
     */
	public void removeReservationEventUserAnswer(ReservationUserInfoVo reservationUserInfoVo) throws Exception{
		reservationEventDao.deleteReservationEventUserAnswer(reservationUserInfoVo);
	}

	 /** 파일업로드 */
    public ReservationIndexVo uploadFile(Map<String, MultipartFile> files, ReservationIndexVo reservationIndexVo) throws Exception {
    	// 첨부파일
		List<FileVO> fvoList = null;
		String riImageFileId = "";
		if(reservationIndexVo != null){
			riImageFileId = reservationIndexVo.getRiImageFileId();
		}
	    if(files != null){
			if (!files.isEmpty()) {
				// 첨부파일 업로드
	    	    Map<String, MultipartFile> file = new HashMap<String, MultipartFile>();
	    	    MultipartFile uploadFile = files.get("riImageFile");
	    	    if (uploadFile.getSize() > 0) {
	    	    	file.put("riImageFile", files.get("riImageFile"));
		    	    if ("".equals(riImageFileId)) {
		    	    	// 파일등록
		    	    	fvoList = egovFileMngUtil.parseFileInf(file, "RI_", 0, "", "reservation.file.upload.path");
		    	    	riImageFileId = egovFileMngService.insertFileInfs(fvoList);
		    	    } else {
		    	    	// 파일수정
		    	    	FileVO fvo = new FileVO();
			    		fvo.setAtchFileId(riImageFileId);
			    		fvo.setFileSn("0");
			    		fvoList = egovFileMngUtil.parseFileInf(file, "RI_", 0, riImageFileId, "reservation.file.upload.path");
			    		egovFileMngService.deleteFileInf(fvo);
					    egovFileMngService.updateFileInfs(fvoList);
		    	    }
	    	    }
		    }
	    }
	    if(reservationIndexVo != null){
	    	reservationIndexVo.setRiImageFileId(riImageFileId);
	    }
    	return reservationIndexVo;
    }

    /**
     * 신청자 추첨
     * @param ReservationIndexVo
     * @throws Exception
     */
	public void updateReservationEventLot(ReservationIndexVo reservationIndexVo) throws Exception{
		reservationEventDao.updateReservationEventLot(reservationIndexVo);
	}
	
	public void updateReservationEventLotTemp(ReservationIndexVo reservationIndexVo) throws Exception{
		reservationEventDao.updateReservationEventLotTemp(reservationIndexVo);
	}

	/**
     * 접수자 삭제
     * @param ReservationUserInfoVo
     * @throws Exception
     */
	public void removeReservationEventUserInfo(ReservationUserInfoVo reservationUserInfoVo) throws Exception{
		reservationEventDao.deleteReservationEventUserInfo(reservationUserInfoVo);
	}

	/**
     * 접수자 추첨확정
     * @param ReservationUserInfoVo
     * @throws Exception
     */
	public void updateReservationEventUserInfoConfirm(ReservationUserInfoVo reservationUserInfoVo) throws Exception{
		reservationEventDao.updateReservationEventUserInfoConfirm(reservationUserInfoVo);
	}
	
	/** 관리부서목록 (과,관,동,보건소 목록) */
	public List retrieveDeptCode() throws Exception {
		return reservationEventDao.selectDeptCode();
	}
	
	public List<String> retrieveListReservationEventRandom(ReservationIndexVo reservationIndexVo) throws Exception{
		return reservationEventDao.selectListReservationEventRandom(reservationIndexVo);
	}
	
	public List<String> retrieveListReservationEventRandomTemp(ReservationIndexVo reservationIndexVo) throws Exception{
		return reservationEventDao.selectListReservationEventRandomTemp(reservationIndexVo);
	}

	public int retrieveReservationEventCntForLot(ReservationUserInfoVo reservationUserInfoVo) throws Exception{
		return reservationEventDao.selectReservationEventCntForLot(reservationUserInfoVo);
	}
	
	public boolean modReservationEventReturn(ReservationUserInfoVo reservationUserInfoVo) throws Exception{
		return reservationEventDao.updateReservationEventPayInfo(reservationUserInfoVo);
	}
	
	
	
	@SuppressWarnings("unchecked")
	public ReservationUserInfoVo retrievePayResult(ReservationUserInfoVo reservationUserInfoVo,HttpServletRequest request) throws Exception {
		 String payResult = null;		
		 String configPath = EgovProperties.getProperty("lgdacom.configPath"); //LG텔레콤에서 제공한 환경파일("/conf/lgdacom.conf,/conf/mall.conf") 위치 지정.
		 String CST_PLATFORM                 = (String) request.getParameter("CST_PLATFORM");
		 String LGD_MID                      = (String) request.getParameter("LGD_MID");
		 String LGD_PAYKEY                   = (String) request.getParameter("LGD_PAYKEY");
		 XPayClient xpay = new XPayClient();
		 boolean isInitOK = xpay.Init(configPath, CST_PLATFORM); 
		 
	   	if( !isInitOK ) {
	    	//API 초기화 실패 화면처리
	        /*
	   		System.out.println( "결제요청을 초기화 하는데 실패하였습니다.<br>");
	        System.out.println( "LG텔레콤에서 제공한 환경파일이 정상적으로 설치 되었는지 확인하시기 바랍니다.<br>");        
	        System.out.println( "mall.conf에는 Mert ID = Mert Key 가 반드시 등록되어 있어야 합니다.<br><br>");
	        System.out.println( "문의전화 LG텔레콤 1544-7772<br>");
	   		*/
	   	}else{      
	   		try{
	   			/*
	   	   	     *************************************************
	   	   	     * 1.최종결제 요청(수정하지 마세요) - END
	   	   	     *************************************************
	   	   	     */
		    	xpay.Init_TX(LGD_MID);
		    	xpay.Set("LGD_TXNAME", "PaymentByKey");
		    	xpay.Set("LGD_PAYKEY", LGD_PAYKEY);
		    
		    	//금액을 체크하시기 원하는 경우 아래 주석을 풀어서 이용하십시요.
		    	//String DB_AMOUNT = "DB나 세션에서 가져온 금액"; //반드시 위변조가 불가능한 곳(DB나 세션)에서 금액을 가져오십시요.
		    	//xpay.Set("LGD_AMOUNTCHECKYN", "Y");
		    	//xpay.Set("LGD_AMOUNT", DB_AMOUNT);
		    	
	    	}catch(Exception e) {
	    		System.out.println("LG텔레콤 제공 API를 사용할 수 없습니다. 환경파일 설정을 확인해 주시기 바랍니다. ");
	    		System.out.println(""+e.getMessage());    	
	    	}
	   	}
	   	
	    /*
	     * 2. 최종결제 요청 결과처리
	     *
	     * 최종 결제요청 결과 리턴 파라미터는 연동메뉴얼을 참고하시기 바랍니다.
	     */
	    
	   	/*응답 메세지 용*/
 	    StringBuffer pay_memo = new StringBuffer(); 
 	    
 	    /*응답 메세지 용 현재 시간 만들기*/
    	Calendar rightNow = Calendar.getInstance();
    	String ryear  = Integer.toString(rightNow.get(Calendar.YEAR));
    	String rmonth = Integer.toString(rightNow.get(Calendar.MONTH)+1);
    	String rday   = Integer.toString(rightNow.get(Calendar.DAY_OF_MONTH));
    	String rhour  = Integer.toString(rightNow.get(Calendar.HOUR_OF_DAY));
    	String rmin	  = Integer.toString(rightNow.get(Calendar.MINUTE));
    	String rnow   = ryear+ "-" + rmonth + "-" + rday + " " + rhour +" : "+ rmin;  
    	/*결제기관승인번호 /현금영수증 승인번호*/
   	 	String pay_auth 	= "";
   	 	String msgtype 		= "";
   	 	
   	 	String paytype 		= "";
    	if ( xpay.TX() ) {
	         //1)결제결과 화면처리(성공,실패 결과 처리를 하시기 바랍니다.)
	    	/*
    		 System.out.println( "결제요청이 완료되었습니다.  <br>");
	    	 System.out.println( "TX 결제요청 Response_code = " + xpay.m_szResCode + "<br>");
	    	 System.out.println( "TX 결제요청 Response_msg = " + xpay.m_szResMsg + "<p>");
	         
	    	 System.out.println("거래번호 : " + xpay.Response("LGD_TID",0) + "<br>");
	    	 System.out.println("상점아이디 : " + xpay.Response("LGD_MID",0) + "<br>");
	    	 System.out.println("상점주문번호 : " + xpay.Response("LGD_OID",0) + "<br>");
	    	 System.out.println("결제금액 : " + xpay.Response("LGD_AMOUNT",0) + "<br>");
	    	 System.out.println("결과코드 : " + xpay.Response("LGD_RESPCODE",0) + "<br>");
	    	 System.out.println("결과메세지 : " + xpay.Response("LGD_RESPMSG",0) + "<p>");
	        */ 
	    	 /*상점 거래 번호 == ordno*/
	    	 String oid			= xpay.Response("LGD_OID",0);		
	    	 /*결제 수단 코드SC0010:신용카드 , SC0030:계좌이체 */
	    	 paytype 	= xpay.Response("LGD_PAYTYPE",0);
	    	 /*LG유플러스 거래번호 */
	    	 String pay_tid 	= xpay.Response("LGD_TID",0);	    	 
	    	
	    	 
	    	 if(paytype != null && "SC0010".equals(paytype)){
	    		 pay_auth = xpay.Response("LGD_FINANCEAUTHNUM",0); /*신용카드 : 결제기관승인번호 */
	    		 msgtype	 = "신용카드";	    		 
	    	 }else if(paytype!=null && "SC0030".equals(paytype)){
	    		 pay_auth = xpay.Response("LGD_CASHRECEIPTNUM",0); /*계좌이체 : 현금영수증 승인번호 현금영수증 건이 아니거나 실패인경우 "0" */
	    		 msgtype	 = "계좌이체";
	    	 }
				
	         for (int i = 0; i < xpay.ResponseNameCount(); i++)
	         {
	        	/* System.out.println(xpay.ResponseName(i) + " = ");*/
	             for (int j = 0; j < xpay.ResponseCount(); j++)
	             {
	            	/* System.out.println("\t" + xpay.Response(xpay.ResponseName(i), j) + "<br>");*/
	             }
	         }
	        /* System.out.println("<p>");*/
	       
	         if( "0000".equals( xpay.m_szResCode ) ) {
	        	 
	         	//최종결제요청 결과 성공 DB처리
	        	
	        	//최종결제요청 결과 성공 DB처리하시기 바랍니다.
	        	 /*응답 메세지*/ 
				pay_memo.append("결제자동확인 : 결제확인시간(");
				pay_memo.append(rnow);
				pay_memo.append(")<br>");
				pay_memo.append("거래종류 : ");		    	
				pay_memo.append(msgtype);
				pay_memo.append("<br>");
				pay_memo.append("거래번호 : ");
				pay_memo.append(pay_tid);
				pay_memo.append("<br>");	    			
				pay_memo.append("승인번호 : ");
				pay_memo.append(pay_auth);				
				pay_memo.append("<br>");
				pay_memo.append("결제일시 : ");
				pay_memo.append(xpay.Response("LGD_PAYDATE",0));
				pay_memo.append("<br>");
				pay_memo.append("응답코드 : ");
				pay_memo.append(xpay.Response("LGD_RESPCODE",0));
				pay_memo.append("<br>");
				pay_memo.append("응답메시지 : ");
				pay_memo.append(xpay.Response("LGD_RESPMSG",0));		
				
				reservationUserInfoVo.setPayTid(pay_tid);
				reservationUserInfoVo.setPayAuth(pay_auth);
				reservationUserInfoVo.setMethod(paytype);
				reservationUserInfoVo.setPayMemo(pay_memo.toString());
				reservationUserInfoVo.setPayStatus("3");
				reservationUserInfoVo.setOrdno(oid);		 			
	        	 

	         	payResult = "결제 완료";           	
	         	//최종결제요청 결과 성공 DB처리 실패시 Rollback 처리
	         	
	         	boolean isDBOK = reservationEventDao.updateReservationEventPayInfo(reservationUserInfoVo);	         	
	         	if( !isDBOK ) {
	         		xpay.Rollback("상점 DB처리 실패로 인하여 Rollback 처리 [TID:" +xpay.Response("LGD_TID",0)+",MID:" + xpay.Response("LGD_MID",0)+",OID:"+xpay.Response("LGD_OID",0)+"]");
	         		/*
	         		System.out.println( "TX Rollback Response_code = " + xpay.Response("LGD_RESPCODE",0) + "<br>");
	         		System.out.println( "TX Rollback Response_msg = " + xpay.Response("LGD_RESPMSG",0) + "<p>");
	         		*/
	                 if( "0000".equals( xpay.m_szResCode ) ) {
	                	 /*System.out.println("자동취소가 정상적으로 완료 되었습니다.<br>");*/
	                	 payResult = "결제 실패";
	                 }else{
	                	/* System.out.println("자동취소가 정상적으로 처리되지 않았습니다.<br>");*/
	                	 payResult = "카드사 결제는 성공했으나 처리 상황이 미결제로 남을수 있습니다. 관리자에게 문의 하세요";
	                 }
	         	}	         	
	         }else{
				//최종결제요청 결과 실패 DB처리
				 payResult = "결제 요청 실패(죄송 합니다.결제를 재시도 해주세요)";
				 
				/*응답 메세지*/		 	    	
				pay_memo.append("결제자동확인 : 결제확인시간(");
				pay_memo.append(rnow);
				pay_memo.append(")<br>");
				pay_memo.append("거래종류 : ");		    	
				pay_memo.append(msgtype);
				pay_memo.append("<br>");
				pay_memo.append("취소코드 : ");
				pay_memo.append(xpay.Response("LGD_RESPCODE",0));
				pay_memo.append("<br>");
				pay_memo.append("취소메세지 : ");
				pay_memo.append(xpay.Response("LGD_RESPMSG",0));																				
				
				reservationUserInfoVo.setPayTid(pay_tid);
				reservationUserInfoVo.setPayAuth(pay_auth);
				reservationUserInfoVo.setMethod(paytype);
				reservationUserInfoVo.setPayMemo(pay_memo.toString());
				reservationUserInfoVo.setPayStatus("2");
				reservationUserInfoVo.setOrdno(oid);
				
				/*최종결제요청 결과 실패 DB처리하시기 바랍니다.*/
			    
			    /*최종결제 완료후 현재 페이지 새로 고침하거나 백스페이스 누르면 자동 아래구문 자동 업데이트 방지
			     * 취소단계는 PG사 관리자 페이지 관리업체가 수동 취소한다고 함. 
			     */				
				String strpaystatus = (String)reservationEventDao.selectReservationEventUserPayStatus(reservationUserInfoVo);
				if(!"3".equals(strpaystatus)){
					reservationEventDao.updateReservationEventPayInfo(reservationUserInfoVo);
				}
	         }
	     }else {
			payResult = "결제요청이 실패하였습니다.";
			//2)API 요청실패 화면처리
			/*
				System.out.println( "결제요청이 실패하였습니다.  <br>");
				System.out.println( "TX 결제요청 Response_code = " + xpay.m_szResCode + "<br>");
				System.out.println( "TX 결제요청 Response_msg = " + xpay.m_szResMsg + "<p>");
			 */
			//최종결제요청 결과 실패 DB처리
			/*응답 메세지*/		 	    	
			pay_memo.append("결제자동확인 : 결제확인시간(");
			pay_memo.append(rnow);
			pay_memo.append("<br>");
			pay_memo.append("취소코드 : ");
			pay_memo.append(xpay.m_szResCode);
			pay_memo.append("<br>");
			pay_memo.append("취소메세지 : ");
			pay_memo.append(xpay.m_szResMsg);								    
		    
			reservationUserInfoVo.setAmount(xpay.Response("LGD_AMOUNT",0));
		    reservationUserInfoVo.setPayTid(xpay.Response("LGD_TID",0));
		    reservationUserInfoVo.setPaytype(xpay.Response("LGD_PAYTYPE",0));
		    reservationUserInfoVo.setRespmsg(xpay.Response("LGD_RESPMSG",0));		    
			reservationUserInfoVo.setPayAuth(pay_auth);
			reservationUserInfoVo.setMethod(xpay.Response("LGD_PAYTYPE",0));
			reservationUserInfoVo.setPayMemo(pay_memo.toString());
			reservationUserInfoVo.setOrdno(xpay.Response("LGD_OID",0));
		    reservationUserInfoVo.setPayStatus("2");
		    /*최종결제요청 결과 실패 DB처리하시기 바랍니다.*/
		    
		    /*최종결제 완료후 현재 페이지 새로 고침하거나 백스페이스 누르면 자동 아래구문 자동 업데이트 방지
		     * 취소단계는 PG사 관리자 페이지 관리업체가 수동 취소한다고 함. 
		     */		   
		    String strpaystatus = (String)reservationEventDao.selectReservationEventUserPayStatus(reservationUserInfoVo);
			if(!"3".equals(strpaystatus)){
				reservationEventDao.updateReservationEventPayInfo(reservationUserInfoVo);
			}
	    }
    	
    	reservationUserInfoVo.setPayresult(payResult); //결제 결과
    	reservationUserInfoVo.setPaytype(paytype);  /*결제 수단 코드SC0010:신용카드 , SC0030:계좌이체 */
    	reservationUserInfoVo.setPayAuth(pay_auth);//승인번호
    	reservationUserInfoVo.setRespmsg(xpay.Response("LGD_RESPMSG",0));//응답메세지
    	reservationUserInfoVo.setAmount(xpay.Response("LGD_AMOUNT",0));//결제 금액	
		return  reservationUserInfoVo;
	}
	
	public EgovMap selectPgInfo(ReservationUserInfoVo reservationUserInfoVo) throws Exception {
		return reservationEventDao.selectPgInfo(reservationUserInfoVo);
	}

}
