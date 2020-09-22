<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ page import="java.security.MessageDigest, java.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<script src="http://xpay.uplus.co.kr/xpay/js/xpay_crossplatform.js" type="text/javascript"></script>
<title>Basic Board List</title>
<script type="text/javascript">
function doXpayNewProcess(){
	
	document.PgVo.action = "http://www.seocho.go.kr/boffice/common/pg/Pg_Process_Reg.do";
	document.PgVo.submit();
	
}

$(function(){
	var test1 = document.PgVo.cstMid.value;
	var test2 = document.PgVo.lgdMertkey.value;
	$("#CST_MID").val(test1);
	
});

</script>

</head>
<body style="text-align:center; margin:0 auto; display:inline; padding-top:100px;" >
<form:form commandName="PgVo" id="PgVo" name="PgVo"  method="post">
<form:hidden path="cstMid" id="cstMid"/>
<form:hidden path="lgdMertkey" id="lgdMertkey"/>
    <div>
        <table>
            <tr>
                <td>상점아이디(t를 제외한 아이디) </td>
                <td><input type="text" name="CST_MID" /></td>
            </tr>
            <tr>
                <td>서비스,테스트 </td>
                <td><input type="text" name="CST_PLATFORM" value="test"/></td>
            </tr>
            <tr>
                <td>구매자 이름 </td>
                <td><input type="text" name="LGD_BUYER" value="홍길동"/></td>
            </tr>
            <tr>
                <td>상품정보 </td>
                <td><input type="text" name="LGD_PRODUCTINFO" value="태안4인실"/></td>
            </tr>
            <tr>
                <td>결제금액 </td>
                <td><input type="text" name="LGD_AMOUNT" value="50000"/></td>
            </tr>
            <tr>
                <td>구매자 이메일 </td>
                <td><input type="text" name="LGD_BUYEREMAIL" value=""/></td>
            </tr>
            <tr>
                <td>주문번호 </td>
                <td><input type="text" name="LGD_OID" value="test_1234567890020"/></td>
            </tr>
<!--             <tr> -->
<!--                 <td>타임스탬프 </td> -->
<!--                 <td><input type="text" name="LGD_TIMESTAMP" value="1234567890"/></td> -->
<!--             </tr> -->
            <tr>
                <td>초기결제수단 </td>
                <td><select name="LGD_CUSTOM_USABLEPAY">
							<option value="SC0010">신용카드</option>				
<!-- 							<option value="SC0030">계좌이체</option>				 -->
<!-- 							<option value="SC0040">무통장입금</option>				 -->
<!-- 							<option value="SC0060">휴대폰</option>				 -->
<!-- 							<option value="SC0070">유선전화결제</option>				 -->
<!-- 							<option value="SC0090">OK캐쉬백</option>				 -->
<!-- 							<option value="SC0111">문화상품권</option>				 -->
<!-- 							<option value="SC0112">게임문화상품권</option>				 -->
				</select></td>
            <tr>
                <td>
               <a href="javascript:doXpayNewProcess();" class="btn2">결제하기</a>
                </td>
            </tr>
        </table>
    </div>
</form:form>
</body>
</html>

