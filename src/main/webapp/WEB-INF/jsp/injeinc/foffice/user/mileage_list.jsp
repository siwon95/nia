<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<script type="text/javascript">
//<![CDATA[
   //페이징처리 
	function doMileageFPag(pageIndex) {
		document.UserFVo.pageIndex.value = pageIndex;
		document.UserFVo.action = "<c:url value='/site/${strDomain}/foffice/user/MileageList.do' />";
		document.UserFVo.submit();
	} 
//]]>
</script>
<div class="board board-list">
	<form:form commandName="UserFVo" method="post" >
	<form:hidden path="pageIndex" />
		<p id="b_summary" class="hide">마일리지현황의 사용일시, 내역, 적립포인트, , 보관대조별, 보관대수 , 더보기링크 안내</p>
		<table class="list" aria-describedby="b_summary">
			<caption>자전거 도로 구축현황 목록</caption>
			<colgroup>
				<col style="width:10%">
				<col style="width:33%">
				<col style="width:33%">
				<col style="width:34%">
			</colgroup>
			<thead>
				<tr>
					<th scope="col">사용일시</th>
					<th scope="col">내역</th>
					<th scope="col">적립포인트</th>
					<th scope="col">현재포인트</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${mileageList }" var="list" varStatus="status">
					<tr>
						<td><c:out value="${list.insdat }" /></td>
						<td><c:out value="${list.title }" /></td>
						<td>
							<c:if test="${list.act eq 'minus'}">
								-
							</c:if>
							<c:if test="${list.act eq 'plus'}">
								+
							</c:if>
							<c:out value="${list.usePoint }" />
						</td>
						<td><c:out value="${list.nowPoint }" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</form:form>
	<!--paginate -->
	<div class="paging">
		<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="doMileageFPag" />
	</div>
	<!--//paginate -->
	
</div><!-- //board -->
