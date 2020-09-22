<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="lgdacom.XPayClient.XPayClient"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>

<c:set var="lgdacomConfFullPath" value="${PgVo.lgdacomConfFullPath}"/>
<c:set var="cstMid" value="${PgVo.cstMid}"/>
<c:set var="lgdOid" value="${PgVo.lgdOid}"/>
<c:set var="lgdPlatform" value="${PgVo.lgdPlatform}"/>
<c:set var="lgdPayKey" value="${PgVo.lgdPayKey}"/>
<c:set var="lgdMertkey" value="${PgVo.lgdMertkey}"/>
<c:set var="lgdbuyerEmail" value="${PgVo.lgdbuyerEmail}"/>
<c:set var="regId" value="${PgVo.regId}"/>

<%
/*
 * [최종결제요청 페이지(STEP2-2)]
 *
 * LG유플러스으로 부터 내려받은 LGD_PAYKEY(인증Key)를 가지고 최종 결제요청.(파라미터 전달시 POST를 사용하세요)
 */

/* ※ 중요
* 환경설정 파일의 경우 반드시 외부에서 접근이 가능한 경로에 두시면 안됩니다.
* 해당 환경파일이 외부에 노출이 되는 경우 해킹의 위험이 존재하므로 반드시 외부에서 접근이 불가능한 경로에 두시기 바랍니다. 
* 예) [Window 계열] C:\inetpub\wwwroot\lgdacom ==> 절대불가(웹 디렉토리)
*/
	
    String configPath =pageContext.getAttribute("lgdacomConfFullPath")+"";      //LG占쏙옙占시뤄옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 환占쏙옙占쏙옙占쏙옙("/conf/lgdacom.conf,/conf/mall.conf") 占쏙옙치 占쏙옙占쏙옙.
    configPath = configPath.trim();
    /*
     *************************************************
     * 1.최종결제 요청 - BEGIN
     *  (단, 최종 금액체크를 원하시는 경우 금액체크 부분 주석을 제거 하시면 됩니다.)
     *************************************************
     */
    
    String CST_PLATFORM                 = "service";
    String CST_MID                      = pageContext.getAttribute("cstMid")+"";
    String LGD_MID                      = ("test".equals(CST_PLATFORM.trim())?"t":"")+CST_MID;
    String LGD_PAYKEY                   = pageContext.getAttribute("lgdPayKey")+"";
    String LGD_MERT_KEY                 = pageContext.getAttribute("lgdMertkey")+"";
    String LGD_BUYEREMAIL               = pageContext.getAttribute("lgdbuyerEmail")+"";
    String LGD_REGID                    = pageContext.getAttribute("regId")+""; 
  //해당 API를 사용하기 위해 WEB-INF/lib/XPayClient.jar 를 Classpath 로 등록하셔야 합니다. 
    XPayClient xpay = new XPayClient();
   	boolean isInitOK = xpay.Init(configPath, CST_PLATFORM);   	

   	if( !isInitOK ) {
   	//해당 API를 사용하기 위해 WEB-INF/lib/XPayClient.jar 를 Classpath 로 등록하셔야 합니다. 
   	 out.println( "결제요청을 초기화 하는데 실패하였습니다.<br>");
     out.println( "LG유플러스에서 제공한 환경파일이 정상적으로 설치 되었는지 확인하시기 바랍니다.<br>");        
     out.println( "mall.conf에는 Mert ID = Mert Key 가 반드시 등록되어 있어야 합니다.<br><br>");
     out.println( "문의전화 LG유플러스 1544-7772<br>");
     return;
   	
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
    		out.println("LG유플러스 제공 API를 사용할 수 없습니다. 환경파일 설정을 확인해 주시기 바랍니다. ");
    		//out.println(""+e.getMessage());    	
    		return;
    	}
   	}

   	/*
     * 2. 최종결제 요청 결과처리
     *
     * 최종 결제요청 결과 리턴 파라미터는 연동메뉴얼을 참고하시기 바랍니다.
     */
     if ( xpay.TX() ) {
         //1)결제결과 화면처리(성공,실패 결과 처리를 하시기 바랍니다.)
         System.out.println( "결제요청이 완료되었습니다.  <br>");
         System.out.println( "TX 결제요청 Response_code = " + xpay.m_szResCode + "<br>");
         System.out.println( "TX 결제요청 Response_msg = " + xpay.m_szResMsg + "<p>");
         
         System.out.println("거래번호 : " + xpay.Response("LGD_TID",0) + "<br>");
         System.out.println("상점아이디 : " + xpay.Response("LGD_MID",0) + "<br>");
         System.out.println("상점주문번호 : " + xpay.Response("LGD_OID",0) + "<br>");
         System.out.println("결제금액 : " + xpay.Response("LGD_AMOUNT",0) + "<br>");
         System.out.println("결과코드 : " + xpay.Response("LGD_RESPCODE",0) + "<br>");
         System.out.println("결과메세지 : " + xpay.Response("LGD_RESPMSG",0) + "<p>");
         
         for (int i = 0; i < xpay.ResponseNameCount(); i++)
         {
             out.println(xpay.ResponseName(i) + " = ");
             for (int j = 0; j < xpay.ResponseCount(); j++)
             {
                 out.println("\t" + xpay.Response(xpay.ResponseName(i), j) + "<br>");
             }
         }
         out.println("<p>");
         
         if( "0000".equals( xpay.m_szResCode ) ) {
         	//최종결제요청 결과 성공 DB처리
         	out.println("최종결제요청 결과 성공 DB처리하시기 바랍니다.<br>");
         	         %>
         	         <script  type="text/javascript">
         	          $(window).load(function() {
         	          
         	            document.PgForm.pgOid.value = "<%=xpay.Response("LGD_OID",0)%>";  
         	        	document.PgForm.pgAmount.value = "<%=xpay.Response("LGD_AMOUNT",0)%>";  
         	        	document.PgForm.pgName.value = "<%=xpay.Response("LGD_BUYER",0)%>";  
         	        	document.PgForm.pgUse.value = "<%=xpay.Response("LGD_PAYTYPE",0)%>";     
         	        	document.PgForm.pgMid.value = "<%=xpay.Response("LGD_MID",0)%>";     
         	        	document.PgForm.pgRespcode.value = "<%=xpay.Response("LGD_RESPCODE",0)%>";  
         	        	document.PgForm.pgTid.value = "<%=xpay.Response("LGD_TID",0)%>";  
         	        	document.PgForm.pgRespmsg.value = "<%=xpay.Response("LGD_RESPMSG",0)%>";
         	        	
         	        	document.PgForm.cmmCode.value = "<%=LGD_MERT_KEY%>";
         	        	document.PgForm.lgdbuyerEmail.value = "<%=LGD_BUYEREMAIL%>";
         	        	document.PgForm.regId.value = "<%=LGD_REGID%>";
         	        	
         	        	$.ajax({
         	        		type: "POST",
         	        		url: "<c:url value='/site/ta/boffice/common/pg/Pg_Mgr_Reg.do' />",
         	        	    data : $("#PgForm").serialize(),
         	        		dataType : "json",
         	        		success:function(object){
         	        			if(object.SVC_MSGE == "100"){
         	        				//alert("정상처리되었습니다.");
         	        				opener.init(object.Tid);
         	        			}else{
         	        				alert("처리중 오류가 발생하였습니다. 관리자에게 문의하세요.");
         	        				return;
         	        			}
         	        				self.close();
         	        		 },
         	                error: function(xhr,status,error){
         	                	alert("처리중 오류가 발생하였습니다. 관리자에게 문의하세요.");
         	                	return;
         	                }
         	        	});	

         	        
         	        	
         	          });
         	         </script>
         	         <% 	
         	//최종결제요청 결과 성공 DB처리 실패시 Rollback 처리
         	boolean isDBOK = true; //DB처리 실패시 false로 변경해 주세요.
         	if( !isDBOK ) {
         		xpay.Rollback("상점 DB처리 실패로 인하여 Rollback 처리 [TID:" +xpay.Response("LGD_TID",0)+",MID:" + xpay.Response("LGD_MID",0)+",OID:"+xpay.Response("LGD_OID",0)+"]");
         		
                 System.out.println( "TX Rollback Response_code = " + xpay.Response("LGD_RESPCODE",0) + "<br>");
                 System.out.println( "TX Rollback Response_msg = " + xpay.Response("LGD_RESPMSG",0) + "<p>");
         		
                 if( "0000".equals( xpay.m_szResCode ) ) {
                 	System.out.println("자동취소가 정상적으로 완료 되었습니다.<br>");
                 }else{
         			System.out.println("자동취소가 정상적으로 처리되지 않았습니다.<br>");
                 }
         	}
         	
         }else{
         	//최종결제요청 결과 실패 DB처리
         	out.println("최종결제요청 결과 실패 DB처리하시기 바랍니다.<br>");            	
         }
     }else {
         //2)API 요청실패 화면처리
         System.out.println( "결제요청이 실패하였습니다.  <br>");
         System.out.println( "TX 결제요청 Response_code = " + xpay.m_szResCode + "<br>");
         System.out.println( "TX 결제요청 Response_msg = " + xpay.m_szResMsg + "<p>");
         
     	//최종결제요청 결과 실패 DB처리
     	System.out.println("최종결제요청 결과 실패 DB처리하시기 바랍니다.<br>");            	            
     }
%>

<html>
<head>
<link href="<c:url value='/css/admin.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/css/board.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/css/jquery-ui.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/css/jquery.timepicker.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/css/style.min.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/js/vakata-jstree-5bece58/dist/themes/default/style.min.css' />" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<c:url value='/js/vakata-jstree-5bece58/dist/jquery.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/vakata-jstree-5bece58/dist/jstree.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/jquery/jquery-ui.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/jquery/jquery.plugin.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/common.js' />"></script>
</head>
<body>
<form name="PgForm" id="PgForm" method="POST">
<input type="hidden" name="pgOid"/>
<input type="hidden" name="pgAmount"/>
<input type="hidden" name="pgName"/>
<input type="hidden" name="pgUse"/>
<input type="hidden" name="pgMid"/>
<input type="hidden" name="pgRespcode"/>
<input type="hidden" name="pgTid"/>
<input type="hidden" name="pgRespmsg"/>
<input type="hidden" name="cmmCode"/>
<input type="hidden" name="lgdbuyerEmail"/>
<input type="hidden" name="regId"/>
</form>
</body>
</html>
