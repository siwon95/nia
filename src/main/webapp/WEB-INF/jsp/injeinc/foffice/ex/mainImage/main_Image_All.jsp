<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<ul class="popupzone-list">
	<c:forEach items="${mainImage }" var="result" varStatus="status">
	<li>
		<c:choose>
			<c:when test="${result.linkUrl == null || result.linkUrl eq ''}">
					<img src="http://www.seocho.go.kr/cmm/fms/getImage.do?atchFileId=<c:out value="${result.imageFile}"/>" alt="<c:out value="${resul.imageNm }"/>" width="290" height="150">
					<p><c:out value="${resul.imageNm }"/></p>
			</c:when>
			<c:otherwise>
				<a href="<c:out value="${result.linkUrl }"/>">
					<img src="http://www.seocho.go.kr/cmm/fms/getImage.do?atchFileId=<c:out value="${result.imageFile}"/>" alt="<c:out value="${resul.imageNm }"/>" width="290" height="150">
					<p><c:out value="${resul.imageNm }"/></p>
				</a>
			</c:otherwise>
		</c:choose>
	</li>
	</c:forEach>
</ul>