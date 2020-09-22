<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="lgdacom.XPayClient.XPayClient"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>

<c:set var="lgdacomConfPath" value="${PgVo.lgdacomConfFullPath}"/>
<c:set var="lgdmid" value="${PgVo.pgMid}"/>
<c:set var="lgdtid" value="${PgVo.pgTid}"/>

<%
    /*
     * [결제취소 요청 페이지]
     *
     * LG유플러스으로 부터 내려받은 거래번호(LGD_TID)를 가지고 취소 요청을 합니다.(파라미터 전달시 POST를 사용하세요)
     * (승인시 LG유플러스으로 부터 내려받은 PAYKEY와 혼동하지 마세요.)
     */
    String CST_PLATFORM         = "test";                 //LG유플러스 결제서비스 선택(test:테스트, service:서비스)
    String CST_MID              = pageContext.getAttribute("lgdmid")+"";                  //LG유플러스으로 부터 발급받으신 상점아이디를 입력하세요.
    String LGD_MID              = ("test".equals(CST_PLATFORM.trim())?"t":"")+CST_MID;  //테스트 아이디는 't'를 제외하고 입력하세요.
                                                                                        //상점아이디(자동생성)
    String LGD_TID              = pageContext.getAttribute("lgdtid")+"";                  //LG유플러스으로 부터 내려받은 거래번호(LGD_TID)

	/* ※ 중요
	* 환경설정 파일의 경우 반드시 외부에서 접근이 가능한 경로에 두시면 안됩니다.
	* 해당 환경파일이 외부에 노출이 되는 경우 해킹의 위험이 존재하므로 반드시 외부에서 접근이 불가능한 경로에 두시기 바랍니다. 
	* 예) [Window 계열] C:\inetpub\wwwroot\lgdacom ==> 절대불가(웹 디렉토리)
	*/
    String configPath 			= pageContext.getAttribute("lgdacomConfPath")+"";									//LG유플러스에서 제공한 환경파일("/conf/lgdacom.conf") 위치 지정.
        
    LGD_TID     				= ( LGD_TID == null )?"":LGD_TID; 
    
    XPayClient xpay = new XPayClient();
    xpay.Init(configPath, CST_PLATFORM);
    xpay.Init_TX(LGD_MID);
    xpay.Set("LGD_TXNAME", "Cancel");
    xpay.Set("LGD_TID", LGD_TID);
 
    /*
     * 1. 결제취소 요청 결과처리
     *
     * 취소결과 리턴 파라미터는 연동메뉴얼을 참고하시기 바랍니다.
	 *
	 * [[[중요]]] 고객사에서 정상취소 처리해야할 응답코드
	 * 1. 신용카드 : 0000, AV11  
	 * 2. 계좌이체 : 0000, RF00, RF10, RF09, RF15, RF19, RF23, RF25 (환불진행중 응답건-> 환불결과코드.xls 참고)
	 * 3. 나머지 결제수단의 경우 0000(성공) 만 취소성공 처리
	 *
     */
    if (xpay.TX()) {
        //1)결제취소결과 화면처리(성공,실패 결과 처리를 하시기 바랍니다.)
        out.println("결제 취소요청이 완료되었습니다.  <br>");
        out.println( "TX Response_code = " + xpay.m_szResCode + "<br>");
        out.println( "TX Response_msg = " + xpay.m_szResMsg + "<p>");
        %>
        <script  type="text/javascript">
        $(window).load(function() {
			   	document.PgForm.pgRespmsg.value = "<%=xpay.Response("LGD_RESPMSG",0)%>";
				document.PgForm.pgRespcode.value = "<%=xpay.Response("LGD_RESPCODE",0)%>";  
					$.ajax({
					type: "POST",
					url: "<c:url value='/site/ta/boffice/common/pg/Pg_Mgr_Mod.do' />",
				    data : $("#PgForm").serialize(),
					dataType : "json",
					success:function(object){
						if(object.SVC_MSGE == "100"){
							alert("정상처리되었습니다.");
							opener.location.reload();
							this.close();
						}else{
							alert("처리중 오류가 발생하였습니다. 관리자에게 문의하세요.");
							return;
						}
						
					 },
			        error: function(xhr,status,error){
			        	alert("처리중 오류가 발생하였습니다. 관리자에게 문의하세요.");
			        	return;
			        }
				});	
			});
			
			</script>
			 
        <%
    }else {
        //2)API 요청 실패 화면처리
        out.println("결제 취소요청이 실패하였습니다.  <br>");
        out.println( "TX Response_code = " + xpay.m_szResCode + "<br>");
        out.println( "TX Response_msg = " + xpay.m_szResMsg + "<p>");
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
	<input type="hidden" name="pgRespcode"/>
	<input type="hidden" name="pgRespmsg"/>
	<input type="hidden" name="pgTid" value="<c:out value='${PgVo.pgTid}'/>"/><!-- 거래번호 -->
	<input type="hidden" name="pgMid" value="<c:out value='${PgVo.pgMid}'/>"/><!-- 거래번호 -->
</form>
</body>
</html>