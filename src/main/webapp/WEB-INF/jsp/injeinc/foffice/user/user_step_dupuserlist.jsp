<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<head>
<script type="text/javascript" src="<c:url value='/js/common.js' />"></script>
<script type="text/javascript">
	function send(){
		var cuId = $(":input:radio[name=cumax]:checked").val();
		if(cuId == ''){
			alert('아이디를 선택하여 주십시요.');
			return false;
		}
		document.UserChForm.submit();
	}
</script>
</head>
<div class="login-tab-container">
	<ul>
		<li class="item1"><a href="#" class="on">로그인</a></li>
		<li class="item2"><a href="#">아이디찾기</a></li>
		<li class="item3"><a href="#">비밀번호찾기</a></li>
	</ul>
</div>

<div class="use-id-combine-container">
	<p class="use-id-combine-text"><span>회원님은 현재 양천구청에 <c:out value="${fn:length(userlist)}"/>개의 아이디가 있습니다.</span><span>통합으로 사용하실 아이디를 하나만 선택해 주시기 바랍니다.</span></p>
<form name="UserChForm" id="UserChForm" method="post" action="/site/yangcheon/user/User_Id_Selected.do" onsubmit="send();return false;">
<table class="use-id-combine-list">
<caption>통합 가능한 아이디 목록</caption>
<thead>
<tr>
	<th scope="col" class="use-id">아이디</th>
	<th scope="col" class="join-route">가입경로</th>
	<th scope="col" class="use-choice">사용선택</th>
</tr>
</thead>
<tbody>
<c:forEach var="result" items="${userlist}" varStatus="status">
<tr>
	<td class="use-id"><label for="chkId-1"><c:out value="${result.cuId}"/></label></td>
	<td class="join-route">
		<c:choose>			
			<c:when test="${result.homepageType eq 'L'}">양천구청 도서관</c:when>
			<c:otherwise>양천구청 대표 홈페이지</c:otherwise>
		</c:choose>		
	</td>
	<td class="use-choice"><input type="radio" name="cumax" id="chkId-1" value="<c:out value='${result.cuId}'/>^<c:out value='${result.cuIdx}'/>" /></td><!-- Input ID 값은 반드시 뒤에 숫자를 증가시켜 주세요. 해당 아이디는 label의 for 값과 일치하여야 합니다.  -->
</tr>
</c:forEach>
</tbody>
</table>					


<input type="submit" value="확인" class="btn btn-total-id-chk" />
</form>
</div>