<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>

<div class="reserve-complete">
	<p><em class="blue">객실 신청이 완료</em>되었습니다.</p>
<!-- 	<p>관악구 휴양소를 예약해 주셔서 감사합니다.</p> -->
</div>				

<h4 class="con-title1">시설정보</h4>
<div class="board">
	<table class="view">
		<caption>시설정보</caption>
		<colgroup>
			<col style="width:15%">
			<col style="width:35%">
			<col style="width:15%">
			<col style="width:35%">
		</colgroup>
		<tbody>
		<tr>
			<th scope="row">시설기관</th>
			<td><c:out value="${Vo.roomEngine }"/></td>
			<th scope="row">관리부서</th>
			<td><c:out value="${Vo.mntDept }"/></td>
		</tr>
		<tr>
			<th scope="row">위치주소</th>
			<td colspan="3"><c:out value="${Vo.mntDept }"/></td>
		</tr>
		<tr>
			<th scope="row">이용일</th>
			<td><c:out value="${Vo.roomDt }"/></td>
			<th scope="row">숙박일</th>
			<td><c:out value="${Vo.roomMaxDt }"/></td>
		</tr>
		<tr>
			<th scope="row">요금</th>
			<td colspan="3"><c:out value="${Vo.price }"/></td>
		</tr>
		<tr>
			<th scope="row">구비시설</th>
			<td colspan="3"><c:out value="${Vo.equipMentRoom }"/></td>
		</tr>
		<tr>
			<th scope="row">전화문의</th>
			<td colspan="3"><c:out value="${Vo.roomTel }"/></td>
		</tr>
		<tr>
			<th scope="row">관련사이트</th>
			<td colspan="3"><c:out value="${Vo.siteAddr }"/></td>
		</tr>			
		</tbody>
	</table><!-- //view -->
</div><!-- //board -->
<br>

<h4 class="con-title1">신청자 정보</h4>
<div class="board">
	<table class="view">
		<caption>신청자 정보</caption>
		<colgroup>
			<col style="width:15%"><col style="width:35%">
			<col style="width:15%"><col style="width:35%">
		</colgroup>
		<tbody>
		<tr>
			<th scope="row">이름</th>
			<td colspan="3"><c:out value="${Vo.itemName }"/></td>
		</tr>
		<tr>
			<th scope="row">주소</th>
			<td colspan="3">
				<c:out value="${Vo.itemZip }"/><br>
				<c:out value="${Vo.itemAddr1 }"/><c:out value="${Vo.itemAddr2 }"/>
			</td>
		</tr>
		<tr>
			<th scope="row">전화번호</th>
			<td><c:out value="${Vo.itemTel }"/></td>
			<th scope="row">핸드폰번호</th>
			<td><c:out value="${Vo.itemHp }"/></td>
		</tr>
		<tr>
			<th scope="row">이메일</th>
			<td colspan="3"><c:out value="${Vo.itemEmail }"/></td>
		</tr>
		</tbody>
	</table><!-- //view -->
	<div class="btns">
		<a href="/site/<c:out value='${CmmVo}'/>/main.do" class="type1">메인으로 가기</a>
	</div>
</div><!-- //board -->