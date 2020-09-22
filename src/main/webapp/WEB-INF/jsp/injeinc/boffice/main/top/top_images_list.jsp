<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate value="${now}" pattern="yyyy-MM-dd 24HHmmss" var="now" /> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Basic Board List</title>
<script type="text/javascript">
//<![CDATA[

	//리스트 조회
	function doSearchList(){
		document.TopImagesVo.action = "<c:url value='/boffice/main/top/Top_Images_List.do' />";
		document.TopImagesVo.submit();
	}
	
	//페이징처리 
	function doTopImagesPag(pageIndex) {
		document.TopImagesVo.pageIndex.value = pageIndex;
		document.TopImagesVo.action = "<c:url value='/boffice/main/top/Top_Images_List.do' />";
		document.TopImagesVo.submit();
	}
	
	//등록페이지
	function doTopImagesForm(){
		document.TopImagesVo.tiIdx.value = "";
		document.TopImagesVo.action = "<c:url value='/boffice/main/top/Top_Images_Form.do' />";
		document.TopImagesVo.submit();
	}
	
	//수정페이지
	function doTopImagesModForm(idx){
		document.TopImagesVo.tiIdx.value = idx;
		document.TopImagesVo.action = "<c:url value='/boffice/main/top/Top_Images_Form.do' />";
		document.TopImagesVo.submit();
	}
	$(document).ready(function(){
	});
//]]>
</script>
</head>
<body style="text-align: center; margin: 0 auto; display: inline; padding-top: 100px;">
<form:form commandName="TopImagesVo" name="TopImagesVo" method="post" onsubmit="doSearchList();">
	<form:hidden path="pageIndex" />
	<form:hidden path="tiIdx" />
	<!-- 컨텐츠 영역 -->
	<div id="contents">
		<!-- 검색 -->
		<table summary="" class="write">
			<caption></caption>
			<colgroup>
				<col width="15%" />
				<col width="*" />
			</colgroup>
			<tbody>
				<tr>
					<th><label for="searchDivision">영역</label></th>
					<td>
						<select name="searchDivision" id="searchDivision">
							<option value="">전체</option>
							<c:forEach items="${divisionList }" var="divisionList" varStatus="status">
								<option value="<c:out value="${divisionList.code}"/>" <c:if test="${TopImagesVo.searchDivision eq divisionList.code}">selected="selected"</c:if>>
									<c:out value="${divisionList.codeName }" />
								</option>
							</c:forEach>
						</select>
					</td>
					<th><label for="searchSite">사이트</label></th>
					<td>
						<select name="searchSite" id="searchSite">
							<option value="">전체</option>
							<c:forEach items="${rowDataList }" var="siteList" varStatus="status">
								<option value="<c:out value="${siteList.code}"/>" <c:if test="${TopImagesVo.searchSite eq siteList.code}">selected="selected"</c:if>>
									<c:out value="${siteList.codeName }" />
								</option>
							</c:forEach>
						</select>
					</td>
					<th><label for="searchKeyword">제목</label></th>
					<td>
						<form:input path="searchKeyword" />&nbsp;
						<button class="btn2 btn_input" onclick="doSearchList();"><i class="fa fa-search"></i> 검색</button>
					</td>
				</tr>
			</tbody>
		</table>
		<!-- //검색 -->

		<br />
		<table summary="" class="list1">
			<caption></caption>
			<colgroup>
				<col width="5%" />
				<col width="10%" />
				<col width="10%" />
				<col width="15%" />
				<col width="*" />
				<col width="15%" />
				<col width="10%" />
				<col width="7%" />
				<col width="10%" />
			</colgroup>
			<thead>
				<tr>
					<th>NO</th>
					<th>영역</th>
					<th>이미지</th>
					<th>사이트</th>
					<th>제목</th>
					<th>게시기간</th>
					<th>작성일</th>
					<th>상태</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${fn:length(resultList) == 0}">
						<tr>
							<td colspan="10" align="center">테마 내용이 없습니다.</td>
						</tr>
					</c:when>
					<c:when test="${fn:length(resultList) > 0}">
						<c:forEach items="${resultList}" var="result" varStatus="status">
							<tr>
								<td><c:out value="${(totCnt+1)-(status.count+(TopImagesVo.pageIndex-1)*TopImagesVo.recordCountPerPage)}" /></td>
								<td><c:out value="${result.division}" /></td>
								<td><img src="<c:out value="${result.tiFilePath}${result.tiFileName }" />" width="150" height="150" /></td>
								<td>
									<c:set var="cnt" value="0" />
									<c:forEach items="${rowDataList }" var="siteList" varStatus="status">
										<c:if test="${fn:indexOf(result.type, siteList.code)>=0}">
											<c:set var="cnt" value="${cnt+1 }" />
											<c:if test="${cnt > 1 }">, </c:if>
											<c:out value="${siteList.codeName}" />
										</c:if>
									</c:forEach>
									<c:if test="${fn:indexOf(result.division,'게시판영역') >=0 }">대표사이트</c:if>
								</td>
								<td><a href="javascript:doTopImagesModForm('<c:out value="${result.tiIdx}"/>');"><b><c:out value="${result.tiTitle}" /></b></a></td>
								<td><c:out value="${result.tiPostSdt}" /><br />~<br /><c:out value="${result.tiPostEdt}" /></td>
								<td><c:out value="${result.regDt}" /></td>
								<td>
									<c:if test="${result.useYn eq 'Y'  && (result.tiPostEdt >= now) && (result.tiPostSdt <= now)}">게시중</c:if>
									<c:if test="${result.useYn eq 'N' || (result.tiPostEdt < now)}">게시중지</c:if>
									<c:if test="${result.useYn eq 'Y' && (result.tiPostEdt > now) && (result.tiPostSdt > now)}">게시예정</c:if>
								</td>
								<td><c:out value="${result.viewCnt}" /></td>
							</tr>
						</c:forEach>
					</c:when>
				</c:choose>
			</tbody>
		</table>

		<!--paginate -->
		<div class="paginate">
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="doTopImagesPag" />
		</div>
		<!--//paginate -->
		<br />

		<!-- 등록 -->
		<div class="btn_zone">
			<a href="javascript:doTopImagesForm();" class="btn2">등록</a>
		</div>
		<!-- 등록 -->
	</div>
	<!-- //컨텐츠 영역 -->
</form:form>
</body>
</html>