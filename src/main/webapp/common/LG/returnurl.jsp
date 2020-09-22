<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.*" %>
<%
//절대지우면 안되는파일 입니다.
String LGD_RESPCODE = request.getParameter("LGD_RESPCODE");
String LGD_RESPMSG 	= request.getParameter("LGD_RESPMSG");
String LGD_PAYKEY	= request.getParameter("LGD_PAYKEY");
%>
<html>
<head>
	<script type="text/javascript">
		function setLGDResult() {
			try {
				var RESP = document.getElementById("LGD_RESPCODE");
				var MSG = document.getElementById("LGD_RESPMSG");
				var LGD_PAYKEY = document.getElementById("LGD_PAYKEY");
alert('LGD_PAYKEY.value=' +LGD_PAYKEY.value);
				opener.payment_return(RESP.value, MSG.value, LGD_PAYKEY.value);
			} catch (e) {
				alert(e.message);
			}
			window.close();
		}
		
	</script>
</head>
<body onload="setLGDResult()">
	<form method="post" name="LGD_RETURNINFO" id="LGD_RETURNINFO">
		<input type="hidden" id="LGD_RESPCODE" name="LGD_RESPCODE" value="<%=LGD_RESPCODE %>" />
		<input type="hidden" id="LGD_RESPMSG" name="LGD_RESPMSG" value="<%=LGD_RESPMSG %>" />
		<input type="hidden" id="LGD_PAYKEY" name="LGD_PAYKEY" value="<%=LGD_PAYKEY %>" />
	</form>
	<table summary="결제 진행중" border="0">
<caption class="blind">결제 진행중</caption>
	<colgroup>
		<col style="width: 580px;" />
	</colgroup>
	<tbody>
	<tr>
		<td class="acenter" style="font-size:12px;"><span class="point_c">전자결제가 진행중입니다.</span><br/><br/>
		창을 닫거나 이동하실경우 결제가 완료되어도 정상적으로 예약이 완료되지 않습니다.<br/><br/>
		결제가 완료되었으나 예약이 완료되지 않은경우 자동으로 결제가 취소되지 않을수도 있으므로 주의하셔야 하며 <strong style="color:blue;">예약관련 기타문의</strong>는 서초휴양소 대표전화 <strong style="color:blue;">041)673-8470~3</strong>를 이용해주세요.<br/><br/>
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
</body>
</html>