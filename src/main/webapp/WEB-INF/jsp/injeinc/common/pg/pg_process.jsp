<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ page import="java.security.MessageDigest, java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page import="java.net.*" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="javax.servlet.http.HttpServletResponse" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="egovframework.injeinc.common.util.WebUtil" %>

<c:set var="cstMid" value="${PgVo.cstMid}"/> 
<c:set var="lgdOid" value="${PgVo.lgdOid}"/> 
<c:set var="lgdMertkey" value="${PgVo.lgdMertkey}"/> 
<c:set var="lgdAmount" value="${PgVo.lgdAmount}"/>
<c:set var="lgdBuyer" value="${PgVo.lgdBuyer}"/> 
<c:set var="lgdProductInfo" value="${PgVo.lgdProductInfo}"/> 
<c:set var="lgdbuyerEmail" value="${PgVo.lgdbuyerEmail}"/> 
<c:set var="lgdCustomUsablePay" value="${PgVo.lgdCustomUsablePay}"/> 
<c:set var="lgdReturnUrl" value="${PgVo.lgdReturnUrl}"/> <!-- returnURL -->
<%
Date date = new Date();
SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSS",Locale.KOREA);
String timestamp = fmt.format(date);
String strHost = "";
InetAddress MyHost = InetAddress.getLocalHost();
/*
 * [결제 인증요청 페이지(STEP2-1)]
 *
 * 샘플페이지에서는 기본 파라미터만 예시되어 있으며, 별도로 필요하신 파라미터는 연동메뉴얼을 참고하시어 추가 하시기 바랍니다.
 */

/*
 * 1. 기본결제 인증요청 정보 변경
 *
 * 기본정보를 변경하여 주시기 바랍니다.(파라미터 전달시 POST를 사용하세요)
 */
    
    String CST_PLATFORM         = "service";                  //LG유플러스 결제서비스 선택(test:테스트, service:서비스)
    String CST_MID              = pageContext.getAttribute("cstMid")+"";                  //LG유플러스로 부터 발급받으신 상점아이디를 입력하세요.
    //String LGD_MID              = ("service".equals(CST_PLATFORM.trim())?"t":"")+CST_MID;  //테스트 아이디는 't'를 제외하고 입력하세요.
    String LGD_MID              = ("test".equals(CST_PLATFORM.trim())?"t":"")+CST_MID;  //테스트 아이디는 't'를 제외하고 입력하세요.
  //상점아이디(자동생성)
    String LGD_OID              = pageContext.getAttribute("lgdOid")+"";                	//주문번호(상점정의 유니크한 주문번호를 입력하세요)
    String LGD_AMOUNT           = pageContext.getAttribute("lgdAmount")+"";                //결제금액("," 를 제외한 결제금액을 입력하세요)
    String LGD_MERTKEY          = pageContext.getAttribute("lgdMertkey")+"";               //상점MertKey(mertkey는 상점관리자 -> 계약정보 -> 상점정보관리에서 확인하실수 있습니다)
    String LGD_BUYER            = pageContext.getAttribute("lgdBuyer")+"";                
    String LGD_PRODUCTINFO      = pageContext.getAttribute("lgdProductInfo")+"";           
    String LGD_BUYEREMAIL       = pageContext.getAttribute("lgdbuyerEmail")+"";            
    String LGD_TIMESTAMP        = timestamp;              
    String LGD_CUSTOM_USABLEPAY  = "";       
    String LGD_CUSTOM_SKIN      = "red";                                               
    String LGD_WINDOW_VER       = "2.5";                                                
    String LGD_AS_RETURNURL = pageContext.getAttribute("lgdReturnUrl")+""; 
    String LGD_ENCODING  = "UTF-8";
     /*
     * 가상계좌(무통장) 결제 연동을 하시는 경우 아래 LGD_CASNOTEURL 을 설정하여 주시기 바랍니다.
     */
    String LGD_CASNOTEURL ="/boffice/common/pg/Pg_Process_Reg.do";		//= "http://占쏙옙占쏙옙URL/cas_noteurl.jsp";

    /*
     * LGD_RETURNURL 을 설정하여 주시기 바랍니다. 반드시 현재 페이지와 동일한 프로트콜 및  호스트이어야 합니다. 아래 부분을 반드시 수정하십시요.
     */
    String LGD_RETURNURL	="/common/LG/returnurl.jsp";// FOR MANUAL
   
    /*
     *************************************************
     * 2. MD5 해쉬암호화 (수정하지 마세요) - BEGIN
     *
     * MD5 해쉬암호화는 거래 위변조를 막기위한 방법입니다.
     *************************************************
     *
     * 해쉬 암호화 적용( LGD_MID + LGD_OID + LGD_AMOUNT + LGD_TIMESTAMP + LGD_MERTKEY )
     * LGD_MID          : 상점아이디
     * LGD_OID          : 주문번호
     * LGD_AMOUNT       : 금액
     * LGD_TIMESTAMP    : 타임스탬프
     * LGD_MERTKEY      : 상점MertKey (mertkey는 상점관리자 -> 계약정보 -> 상점정보관리에서 확인하실수 있습니다)
     *
     * MD5 해쉬데이터 암호화 검증을 위해
     * LG유플러스에서 발급한 상점키(MertKey)를 환경설정 파일(lgdacom/conf/mall.conf)에 반드시 입력하여 주시기 바랍니다.
     */
    
    StringBuffer sb = new StringBuffer();
    sb.append(LGD_MID);
    sb.append(LGD_OID);
    sb.append(LGD_AMOUNT);
    sb.append(LGD_TIMESTAMP);
    sb.append(LGD_MERTKEY);

    byte[] bNoti = sb.toString().getBytes();
    MessageDigest md = MessageDigest.getInstance("MD5");
    byte[] digest = md.digest(bNoti);

    StringBuffer strBuf = new StringBuffer();
    for (int i=0 ; i < digest.length ; i++) {
        int c = digest[i] & 0xff;
        if (c <= 15){
            strBuf.append("0");
        }
        strBuf.append(Integer.toHexString(c));
    }

    String LGD_HASHDATA = strBuf.toString();
    String LGD_CUSTOM_PROCESSTYPE = "TWOTR";
    /*
     *************************************************
     * 2. MD5 해쉬암호화 (수정하지 마세요) - END
     *************************************************
     */
   
     String CST_WINDOW_TYPE = "popup";
     HashMap payReqMap = new HashMap();
     payReqMap.put("CST_PLATFORM"                , CST_PLATFORM);                   	
     payReqMap.put("CST_MID"                     , CST_MID );                        	
     payReqMap.put("CST_WINDOW_TYPE"             , CST_WINDOW_TYPE );                        
     payReqMap.put("LGD_MID"                     , LGD_MID );                        
     payReqMap.put("LGD_OID"                     , LGD_OID );                        
     payReqMap.put("LGD_BUYER"                   , LGD_BUYER );                      
     payReqMap.put("LGD_PRODUCTINFO"             , LGD_PRODUCTINFO );                	
     payReqMap.put("LGD_AMOUNT"                  , LGD_AMOUNT );                     	
     payReqMap.put("LGD_BUYEREMAIL"              , LGD_BUYEREMAIL );                 
     payReqMap.put("LGD_CUSTOM_SKIN"             , LGD_CUSTOM_SKIN );                	
     payReqMap.put("LGD_WINDOW_VER"              , LGD_WINDOW_VER );                
     payReqMap.put("LGD_CUSTOM_PROCESSTYPE"      , LGD_CUSTOM_PROCESSTYPE );         	
     payReqMap.put("LGD_TIMESTAMP"               , LGD_TIMESTAMP );                  	
     payReqMap.put("LGD_HASHDATA"                , LGD_HASHDATA );      	           
     payReqMap.put("LGD_RETURNURL"   			 , LGD_RETURNURL );      			 
     payReqMap.put("LGD_CUSTOM_USABLEPAY"  		 , LGD_CUSTOM_USABLEPAY );	
     payReqMap.put("LGD_ENCODING"  				 , LGD_ENCODING );
     payReqMap.put("LGD_CUSTOM_SWITCHINGTYPE"  	, "SUBMIT" );							
  // 가상계좌(무통장) 결제연동을 하시는 경우  할당/입금 결과를 통보받기 위해 반드시 LGD_CASNOTEURL 정보를 LG 유플러스에 전송해야 합니다 .
      payReqMap.put("LGD_CASNOTEURL"          , LGD_CASNOTEURL );            

      /*Return URL에서 인증 결과 수신 시 셋팅될 파라미터 입니다.*/
	payReqMap.put("LGD_RESPCODE"  		 , "" );
	payReqMap.put("LGD_RESPMSG"  		 , "" );
	payReqMap.put("LGD_PAYKEY"  		 , "" );

	session.setAttribute("PAYREQ_MAP", payReqMap);
	
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<script src="https://xpay.uplus.co.kr/xpay/js/xpay_crossplatform.js" type="text/javascript"></script>
<title>Basic Board List</title>
<script type="text/javascript">
//<![CDATA[

/*
* iframe으로 결제창을 호출하시기를 원하시면 iframe으로 설정 (변수명 수정 불가)
*/
	var LGD_window_type = '<%=CST_WINDOW_TYPE%>';
	/*
	* 수정불가
	*/
function launchCrossPlatform(){
      lgdwin = open_paymentwindow(document.getElementById('LGD_PAYINFO'), '<%= CST_PLATFORM %>', LGD_window_type);
     
}
/*
* FORM 명만  수정 가능
*/
function getFormObject() {
        return document.getElementById("LGD_PAYINFO");
}
/*
 * 인증결과 처리
 */
function payment_return(LGD_RESPCODE, LGD_RESPMSG, LGD_PAYKEY) {
	
	if (LGD_RESPCODE == "0000") {
		/*
		* 인증성공 화면 처리
		*/
		
		//return url 체크 
		var winPop = window.open("","payPop","width=300,height=300,scrollbars=yes");
		document.getElementById("LGD_RESPCODE").value = LGD_RESPCODE;
		document.getElementById("LGD_RESPMSG").value = LGD_RESPMSG;
		document.getElementById("LGD_PAYKEY").value = LGD_PAYKEY;
		document.getElementById("LGD_PAYINFO").action = "/site/ta/boffice/common/pg/Pg_Process_PayRes.do";
		document.getElementById("LGD_PAYINFO").target = "payPop";
		document.getElementById("LGD_PAYINFO").submit();
	} else {
		//alert('인증실패 : ' + LGD_RESPMSG);
		//인증실패시 결제창닫아버리면 미결제로 만들어버림 
		var Urluse = "<c:out value='${UrlStr}'/>";
		var Urlval = "";
		var LocationUrlval = "";
		
		if(Urluse == "ta"){
			 document.frm.LGD_CMMCODE.value = Urluse;
			 Urlval = "<c:url value='/site/ta/boffice/common/pg/Pg_Mgr_Miss.do' />";
			 LocationUrlval = "<c:url value='/site/ta/ex/management/Ta_Room_Content.do' />";
		}else if(Urluse == "hs"){
			 document.frm.LGD_CMMCODE.value = Urluse;
			 Urlval = "<c:url value='/site/hs/boffice/common/pg/Pg_Mgr_Miss.do' />";
			 LocationUrlval = "<c:url value='/site/hs/ex/management/Hs_Room_Content.do' />";
		}else if(Urluse == "lt"){
		     document.frm.LGD_CMMCODE.value = Urluse;
			 Urlval = "<c:url value='/site/lt/boffice/common/pg/Pg_Mgr_Miss.do' />";
			 LocationUrlval = "<c:url value='/site/seocho/ex/lecture/List.do' />";
		}
	
		
		$.ajax({
			type: "POST",
			url: Urlval,
		    data : $("#frm").serialize(),
			dataType : "json",
			success:function(object){
				if(object.SVC_MSGE == "100"){
					alert("결제가 취소되거나 중단되었습니다.");
					location.href=LocationUrlval;
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
		/*
		* 인증실패 화면 처리
		*/
	}
}


function init(code){
	
	var Urluse = "<c:out value='${UrlStr}'/>";
	var Urlval = "";
	
	if(Urluse == "ta"){
		Urlval = 'http://www.seocho.go.kr/site/ta/boffice/common/pg/Pg_Process_Success.do';
	}else if(Urluse == "hs"){
		Urlval = 'http://www.seocho.go.kr/site/hs/boffice/common/pg/Pg_Process_Success.do';
	}else if(Urluse == "lt"){
		Urlval = 'http://www.seocho.go.kr/site/seocho/boffice/common/pg/Pg_Process_Success.do';
	}
	
	document.finishFrm.LGD_TID.value = code;
	document.finishFrm.action = Urlval;
	document.finishFrm.submit();
	
}

$(window).load(function() {
	launchCrossPlatform();
});


//]]>
</script>


</head>
<body style="text-align:center; margin:0 auto; display:inline; padding-top:100px;" onload="launchCrossPlatform();">
<form method="post" name="finishFrm" id="finishFrm">
<input type="hidden" name="LGD_MID"  value="<%=LGD_MID %>"/> <!-- 상점코드 -->
<input type="hidden" name="LGD_OID"  value="<%=LGD_OID %>"/> <!--주문번호  -->
<input type="hidden" name="LGD_TID" value=""/> <!--주문번호  -->
</form>
<form method="post" name="frm" id="frm">
<input type="hidden" name="LGD_BUYER" id="LGD_BUYER" value="<%=LGD_BUYER %>"/><!-- 거래자명 -->
<input type="hidden" name="LGD_MID" id="LGD_MID" value="<%=LGD_MID %>"/> <!-- 상점코드 -->
<input type="hidden" name="LGD_OID" id="LGD_OID" value="<%=LGD_OID %>"/> <!--주문번호  -->
<input type="hidden" name="LGD_TID" id="LGD_TID" /> <!-- 거래번호 -->
<input type="hidden" name="LGD_CMMCODE" id="LGD_CMMCODE" /> <!-- 공통코드 -->
</form>
<form method="post" name="LGD_PAYINFO" id="LGD_PAYINFO" >

<%
	for(Iterator i = payReqMap.keySet().iterator(); i.hasNext();){
		Object key = i.next();
		out.println("<input type='hidden' name='" + key + "' id='"+key+"' value='" + payReqMap.get(key) + "'>" );
		//System.out.println("<input type='hidden' name='" + key + "' id='"+key+"' value='" + payReqMap.get(key) + "'>" );
	}
%>
<c:choose>
	<c:when test="${UrlStr == 'lt'}">
		<table summary="결제 진행중" border="0">
		<caption class="blind">결제 진행중</caption>
			<colgroup>
				<col style="width: 580px;" />
			</colgroup>
			<tbody>
			<tr>
				<td class="acenter" style="font-size:12px;"><span class="point_c">전자결제가 진행중입니다.</span><br/><br/>
				창을 닫거나 이동하실경우 결제가 완료되어도 정상적으로 예약이 완료되지 않습니다.<br/><br/>
				결제가 완료되었으나 예약이 완료되지 않은경우 자동으로 결제가 취소되지 않을수도 있으므로 주의하셔야 합니다.
				</td>
			</tr>
			<tr>
				<td style="font-size:12px;">
				☞ 다양한 문제로 인해 <strong style="color:#b3714d;">결제가 되지 않는 경우</strong> 이를 해결할 수 있는 방법을 알려드립니다.<br/>
				<span style="color:#b3714d;">문의전화 : 1544-7772</span><br/>
				<span style="color:#b3714d;">eCredit 결제오류 해결방안 :</span> <a href="http://pgweb.uplus.co.kr/help.html" target="_blank" style="color:blue; text-decoration:underline;"><strong>바로가기</strong></a>
				</td>
			</tr>
			</tbody>
		</table>
	</c:when>
	<c:otherwise>
		<table summary="결제 진행중" border="0">
		<caption class="blind">결제 진행중</caption>
			<colgroup>
				<col style="width: 580px;" />
			</colgroup>
			<tbody>
			<tr>
				<td class="acenter" style="font-size:12px;"><span class="point_c">전자결제가 진행중입니다.</span><br/><br/>
				창을 닫거나 이동하실경우 결제가 완료되어도 정상적으로 예약이 완료되지 않습니다.<br/><br/>
				결제가 완료되었으나 예약이 완료되지 않은경우 자동으로 결제가 취소되지 않을수도 있으므로 주의하시기 바랍니다.<br/><br/>
				</td>
			</tr>
			<tr>
				<td style="font-size:12px;">
				☞ 다양한 문제로 인해 <strong style="color:#b3714d;">결제가 되지 않는 경우</strong> 이를 해결할 수 있는 방법을 알려드립니다.<br/>
				<span style="color:#b3714d;">문의전화 : 1544-7772</span><br/>
				<span style="color:#b3714d;">eCredit 결제오류 해결방안 :</span> <a href="http://pgweb.uplus.co.kr/help.html" target="_blank" style="color:blue; text-decoration:underline;"><strong>바로가기</strong></a>
				</td>
			</tr>
			</tbody>
		</table>
	</c:otherwise>
</c:choose>
</form>
</body>
</html>

