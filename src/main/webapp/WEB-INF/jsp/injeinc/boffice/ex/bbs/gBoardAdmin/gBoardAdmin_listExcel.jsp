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
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>구청장에게바란다</title>
</head>
<body>
	<table summary="구청장에게 바란다" border="1">
		<caption>구청장에게 바란다 목록</caption>
		<thead>
			<tr>
				<th scope="col">no</th>
				<th scope="col">글번호</th>
				<th scope="col">작성일</th>
				<th scope="col">민원인 성명</th>
				<th scope="col">민원인 연락처</th>
				<th scope="col">민원인 핸드폰</th>
				<th scope="col">주소</th>
				<th scope="col">민원제목</th>
				<th scope="col">민원내용</th>
				<th scope="col">담당부서명</th>
				<th scope="col">민원발생지역</th>
				<th scope="col">민원분야</th>
				<th scope="col">민원유형</th>
				<th scope="col">처리결과</th>
				<th scope="col">부서지정일</th>
				<th scope="col">답변일</th>
				<th scope="col">처리기일</th>
				<th scope="col">담당자</th>
				<th scope="col">답변내용</th>
				<th scope="col">처리결과</th>
				<th scope="col">기한연기</th>
				<th scope="col">기한연기사유</th>
				<th scope="col">공개여부</th>
				<th scope="col">조회수</th>
				<th scope="col">1차발신자</th>
				<th scope="col">1차통화일시</th>
				<th scope="col">1차통화내역</th>
				<th scope="col">2차발신자</th>
				<th scope="col">2차통화일시</th>
				<th scope="col">2차통화내역</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="result" items="${resultList}" varStatus="status">
			<tr>
				<td><c:out value="${result.rn}"/></td>
				<td><c:out value="${result.bcIdx}"/></td>
				<td><c:out value="${result.aRegDt}"/></td>
				<td><c:out value="${result.nameCont}"/></td>
				<td><c:out value="${result.telCont}"/></td>
				<td><c:out value="${result.phoneCont}"/></td>
				<td><c:out value="${result.addrCont}"/></td>
				<td><c:out value="${result.subCont}"/></td>
				<td><c:out value="${result.clobCont}"/></td>
				<td><c:out value="${result.mcDeptCd}"/></td>
				<td><c:out value="${result.mcAreaCd}"/></td>
				<td><c:out value="${result.mcFiledCd}"/></td>
				<td><c:out value="${result.mcKdCd}"/></td>
				<td><c:out value="${result.mcResult}"/></td>
				<td><c:out value="${result.bRegDt}"/></td>
				<td><c:out value="${result.modDt}"/></td>
				<td><c:out value="${result.modDt2}"/></td>
				<td><c:out value="${result.mcReplyer}"/></td>
				<td><c:out value="${result.contentClob}"/></td>
				<td>
					<c:choose>
						<c:when test="${result.mwStatusCont eq '20000100'}"><span class="status-move">삭제</span></c:when>        
						<c:when test="${result.mwStatusCont eq '20000200'}"><span class="status-move">비공개처리</span></c:when>  
						<c:when test="${result.mwStatusCont eq '20000300'}"><span class="status-move">이관</span></c:when>        
						<c:when test="${result.mwStatusCont eq '20000400'}"><span class="status-move">답변없음</span></c:when>    
						<c:when test="${result.mwStatusCont eq '20000500'}"><span class="status-end">답변완료</span></c:when>     
						<c:when test="${result.mwStatusCont eq '20000600'}"><span class="status-move">부서답변완료</span></c:when>
						<c:when test="${result.mwStatusCont eq '20000700'}"><span class="adno-open">중간답변</span></c:when>      
						<c:when test="${result.mwStatusCont eq '20000800'}"><span class="status-ing">처리중</span></c:when>       
						<c:when test="${result.mwStatusCont eq '20000900'}"><span class="status-re">접수중</span></c:when>        
						<c:when test="${result.mwStatusCont eq '22000200'}"><span class="status-move">답변요청없음</span></c:when>        
					</c:choose>
				</td>
				<td><c:out value="${result.mcDelayDay}"/></td>
				<td><c:out value="${result.mcDelayRsn}"/></td>
				<td><c:out value="${result.openYnCont}"/></td>
				<td><c:out value="${result.countCont}"/></td>
				<td><c:out value="${result.mcBSender}"/></td>
				<td><c:out value="${result.mcBDay}"/></td>
				<td><c:out value="${result.mcBTxt}"/></td>
				<td><c:out value="${result.mcASender}"/></td>
				<td><c:out value="${result.mcADay}"/></td>
				<td><c:out value="${result.mcATxt}"/></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>			
</body>
</html>
