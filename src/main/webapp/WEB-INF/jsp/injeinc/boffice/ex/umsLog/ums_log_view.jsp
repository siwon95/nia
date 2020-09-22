<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="cmm" uri="http://cms.injeinc.co.kr/cmm"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>UMS LOG 상세보기</title>
<script type="text/javascript">
//<![CDATA[
	function doUmsLogList() {
		$("#UmsLogVo").attr("action", "<c:url value='/boffice/ex/umsLog/UmsLogList.do' />").submit();
	}
//]]>
</script>
</head>
<body style="text-align:center; margin:0 auto; display:inline; padding-top:100px;">
<form id="UmsLogVo" name="UmsLogVo" method="post">

	<!-- 컨텐츠 영역 -->
			<div id="contents">
			
				<table summary="UMS LOG 상세보기" class="view">
					<caption>UMS LOG 상세보기</caption>
					<colgroup>
						<col width="15%"/>
						<col width="85%"/>
					</colgroup>
					<tbody>
						<tr>
							<th scope="row">카테고리</th>
							<td><c:out value="${cmm:getCodeName(UmsLogVo.ulSiteCd)}" /></td>
						</tr>
						<tr>
							<th scope="row">발송구분</th>
							<td><c:out value="${UmsLogVo.ulSendtype}" /></td>
						</tr>
						<tr>
							<th scope="row">제목</th>
							<td><c:out value="${UmsLogVo.ulSubject}" /></td>
						</tr>
						<tr>
							<th scope="row">수신자명^수신자번호</th>
							<td><c:out value="${UmsLogVo.ulAddress}" /></td>
						</tr>
						<tr>
							<th scope="row">내용</th>
							<td><c:out value="${UmsLogVo.ulContent}" /></td>
						</tr>
						<tr>
							<th scope="row">회신번호</th>
							<td><c:out value="${UmsLogVo.ulCallbackNo}" /></td>
						</tr>
						<tr>
							<th scope="row">발송업무구분명</th>
							<td><c:out value="${UmsLogVo.ulWorktypeNm}" /></td>
						</tr>
						<tr>
							<th scope="row">예약발송</th>
							<td>
								<c:if test="${UmsLogVo.ulScheduleType == 0}">즉시발송</c:if>
								<c:if test="${UmsLogVo.ulScheduleType == 1}">예약발송</c:if>
							</td>
						</tr>
						<tr>
							<th scope="row">예약발송시 발송년월일시분</th>
							<td><c:out value="${UmsLogVo.ulScheduleStime}" /></td>
						</tr>
					</tbody>
				</table>
				
				<!-- 버튼 -->
				<div class="btn_zone">
					<a href="javascript:doUmsLogList();" class="btn2">목록</a>
				</div>
				<!-- //버튼 -->
		
			</div>
			<!-- //컨텐츠 영역 -->
</form>
</body>
</html>
