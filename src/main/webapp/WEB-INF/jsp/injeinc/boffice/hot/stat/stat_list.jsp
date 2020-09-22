<%@page import="java.util.Calendar"%>
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
<title>Basic Board List</title>
<script type="text/javascript">
//<![CDATA[
	//리스트 조회
	function doHotStatList(){
		document.HotStatVo.action = "<c:url value='/boffice/hot/stat/HotStat_List.do' />";
		document.HotStatVo.submit();
	}
	
	//엑셀
	function getExcel() {
		document.HotStatVo.action = "<c:url value='/excel/HotStat_Excel.do' />";
		document.HotStatVo.submit();
	}
	$(document).ready(function(){
		
	});
//]]>
</script>
</head>
<body style="text-align: center; margin: 0 auto; display: inline; padding-top: 100px;">

	<!-- 컨텐츠 영역 -->
	<div id="contents">
		<form:form commandName="HotStatVo" name="HotStatVo" method="post" enctype="multipart/form-data">
			<table summary="" class="write">
				<caption></caption>
				<colgroup>
					<col width="30%" />
					<col width="70%" />
				</colgroup>
				<tbody>
					<tr>
						<th><label for="schyear">연 월 선택</label></th>
						<td>
							<select name="schyear">
								<%
									Calendar calendar = Calendar.getInstance();
									int year = calendar.get(calendar.YEAR);
									for(int i=2012;i<=year;i++) {
								%>
								<c:set var="chkyear" value="<%=i %>" />
								<option value="<%=i%>"
									<c:if test="${HotStatVo.schyear eq chkyear}">selected="selected"</c:if>><%=i%>년
								</option>
								<%}%>
							</select>&nbsp;
							<label for="schmonth" class="hidden">월선택</label>
							<select name="schmonth" id="schmonth">
								<option value="all" <c:if test="${HotStatVo.schmonth eq 'all'}">selected="selected"</c:if>>전체</option>
								<option value="01" <c:if test="${HotStatVo.schmonth eq '01'}">selected="selected"</c:if>>1월</option>
								<option value="02" <c:if test="${HotStatVo.schmonth eq '02'}">selected="selected"</c:if>>2월</option>
								<option value="03" <c:if test="${HotStatVo.schmonth eq '03'}">selected="selected"</c:if>>3월</option>
								<option value="04" <c:if test="${HotStatVo.schmonth eq '04'}">selected="selected"</c:if>>4월</option>
								<option value="05" <c:if test="${HotStatVo.schmonth eq '05'}">selected="selected"</c:if>>5월</option>
								<option value="06" <c:if test="${HotStatVo.schmonth eq '06'}">selected="selected"</c:if>>6월</option>
								<option value="07" <c:if test="${HotStatVo.schmonth eq '07'}">selected="selected"</c:if>>7월</option>
								<option value="08" <c:if test="${HotStatVo.schmonth eq '08'}">selected="selected"</c:if>>8월</option>
								<option value="09" <c:if test="${HotStatVo.schmonth eq '09'}">selected="selected"</c:if>>9월</option>
								<option value="10" <c:if test="${HotStatVo.schmonth eq '10'}">selected="selected"</c:if>>10월</option>
								<option value="11" <c:if test="${HotStatVo.schmonth eq '11'}">selected="selected"</c:if>>11월</option>
								<option value="12" <c:if test="${HotStatVo.schmonth eq '12'}">selected="selected"</c:if>>12월</option>
							</select>
							<button class="btn2 btn_input" onclick="doHotStatList();"><i class="fa fa-search"></i> 검색</button>
						</td>
					</tr>
				</tbody>
			</table>
			<br/>
			<div class="btn_zone" style="text-align: right;">
				<a href="javascript:getExcel();" class="btn1">엑셀다운</a>
			</div>
		</form:form>
		<br/>

		<c:if test="${HotStatVo.schmonth eq 'all'}">
			<table summary="" class="list1">
				<caption></caption>
				<colgroup>
					<col width="5%" />
					<col width="*%" />
					<col width="5%" />
					<col width="5%" />
					<col width="5%" />
					<col width="5%" />
					<col width="5%" />
					<col width="5%" />
					<col width="5%" />
					<col width="5%" />
					<col width="5%" />
					<col width="5%" />
					<col width="5%" />
					<col width="5%" />
					<col width="5%" />
				</colgroup>
				<thead>
					<tr>
						<th>NO</th>
						<th>제목</th>
						<th>1월</th>
						<th>2월</th>
						<th>3월</th>
						<th>4월</th>
						<th>5월</th>
						<th>6월</th>
						<th>7월</th>
						<th>8월</th>
						<th>9월</th>
						<th>10월</th>
						<th>11월</th>
						<th>12월</th>
						<th>계</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${fn:length(resultList) == 0}">
							<tr>
								<td colspan="14" align="center">내용이 없습니다.</td>
							</tr>
						</c:when>
						<c:when test="${fn:length(resultList) > 0}">
							<c:forEach items="${resultList}" var="result" varStatus="status">
								<tr>
									<td><c:out value="${status.count}" /></td>
									<td><c:out value="${result.hlTitle}" /></td>
									<td><c:out value="${result.m1}" /></td>
									<td><c:out value="${result.m2}" /></td>
									<td><c:out value="${result.m3}" /></td>
									<td><c:out value="${result.m4}" /></td>
									<td><c:out value="${result.m5}" /></td>
									<td><c:out value="${result.m6}" /></td>
									<td><c:out value="${result.m7}" /></td>
									<td><c:out value="${result.m8}" /></td>
									<td><c:out value="${result.m9}" /></td>
									<td><c:out value="${result.m10}" /></td>
									<td><c:out value="${result.m11}" /></td>
									<td><c:out value="${result.m12}" /></td>
									<td><c:out value="${result.sum}" /></td>
								</tr>
							</c:forEach>
						</c:when>
					</c:choose>
				</tbody>
			</table>
		</c:if>
		<c:if test="${HotStatVo.schmonth ne 'all' && HotStatVo.schmonth ne ''}">
			<table summary="" class="list1">
				<caption></caption>
				<colgroup>
					<col width="2%" />
					<col width="*%" />
					<c:forEach begin="1" end="${maxDay}">
						<col width="2%" />
					</c:forEach>
					<col width="2%" />
					<col width="2%" />
				</colgroup>
				<thead>
					<tr>
						<th>NO</th>
						<th>제목</th>
						<c:forEach begin="1" end="${maxDay}" varStatus="status">
							<th><c:out value="${status.index }" />일</th>
						</c:forEach>
						<th>계</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${fn:length(resultList) == 0}">
							<tr>
								<td colspan="<c:out value="${maxDay+4}"/>" align="center">내용이 없습니다.</td>
							</tr>
						</c:when>
						<c:when test="${fn:length(resultList) > 0}">
							<c:forEach items="${resultList}" var="result" varStatus="status">
								<tr>
									<td><c:out value="${status.count}" /></td>
									<td><c:out value="${result.hlTitle}" /></td>
									<td><c:out value="${result.d1}" /></td>
									<td><c:out value="${result.d2}" /></td>
									<td><c:out value="${result.d3}" /></td>
									<td><c:out value="${result.d4}" /></td>
									<td><c:out value="${result.d5}" /></td>
									<td><c:out value="${result.d6}" /></td>
									<td><c:out value="${result.d7}" /></td>
									<td><c:out value="${result.d8}" /></td>
									<td><c:out value="${result.d9}" /></td>
									<td><c:out value="${result.d10}" /></td>
									<td><c:out value="${result.d11}" /></td>
									<td><c:out value="${result.d12}" /></td>
									<td><c:out value="${result.d13}" /></td>
									<td><c:out value="${result.d14}" /></td>
									<td><c:out value="${result.d15}" /></td>
									<td><c:out value="${result.d16}" /></td>
									<td><c:out value="${result.d17}" /></td>
									<td><c:out value="${result.d18}" /></td>
									<td><c:out value="${result.d19}" /></td>
									<td><c:out value="${result.d20}" /></td>
									<td><c:out value="${result.d21}" /></td>
									<td><c:out value="${result.d22}" /></td>
									<td><c:out value="${result.d23}" /></td>
									<td><c:out value="${result.d24}" /></td>
									<td><c:out value="${result.d25}" /></td>
									<td><c:out value="${result.d26}" /></td>
									<td><c:out value="${result.d27}" /></td>
									<td><c:out value="${result.d28}" /></td>
									<c:if test="${maxDay > 28 }">
										<td><c:out value="${result.d29}" /></td>
										<c:if test="${maxDay > 29 }">
											<td><c:out value="${result.d30}" /></td>
											<c:if test="${maxDay > 30 }">
												<td><c:out value="${result.d31}" /></td>
											</c:if>
										</c:if>
									</c:if>
									<td><c:out value="${result.sum}" /></td>
								</tr>
							</c:forEach>
						</c:when>
					</c:choose>
				</tbody>
			</table>
		</c:if>
	</div>
	<!-- //컨텐츠 영역 -->

</body>
</html>