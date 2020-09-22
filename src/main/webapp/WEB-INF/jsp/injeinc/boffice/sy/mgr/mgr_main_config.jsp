<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>관리자 메인 화면설정</title>

<script type="text/javascript">
//<![CDATA[
	function doMgrMainConfigMod(){
		if (!confirm("수정하시겠습니까?")) return;
		
		$("#MgrMainConfigVo").attr("action", "/boffice/sy/mgr/MgrMainConfigMod.do").submit();
	}
//]]>
</script>
</head>
<fmt:setLocale value="ko_kr"/>
<body style="text-align:center; margin:0 auto; display:inline; padding-top:100px;">
<form:form commandName="MgrMainConfigVo" name="MgrMainConfigVo" method="post" >
	<div id="contents">
		<table summary="관리자메인 화면설정 목록" class="list1" style="table-layout:fixed;">
			<caption>관리자메인 화면설정 목록</caption>
			<colgroup>
				<col width="7%" />
				<col  />
				<col width="25%" />				
			</colgroup>
			<thead>
				<tr>
					<th scope="col">연번</th>
					<th scope="col">화면명</th>
					<th scope="col">사용여부</th>					
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>1</td>
					<td>관리자 공지게시판</td>
					<td><form:radiobutton path="noticeYn" label="사용" value="Y"/>&nbsp;&nbsp;<form:radiobutton path="noticeYn" label="미사용" value="N"/> </td>					
				</tr>				
				<tr>
					<td>2</td>
					<td>민원 목록</td>
					<td><form:radiobutton path="minwonYn" label="사용" value="Y"/>&nbsp;&nbsp;<form:radiobutton path="minwonYn" label="미사용" value="N"/> </td>					
				</tr>				
				<tr>
					<td>3</td>
					<td>묻고답하기 목록</td>
					<td><form:radiobutton path="qnaYn" label="사용" value="Y"/>&nbsp;&nbsp;<form:radiobutton path="qnaYn" label="미사용" value="N"/> </td>					
				</tr>				
				<tr>
					<td>4</td>
					<td>게시판 목록</td>
					<td><form:radiobutton path="boardYn" label="사용" value="Y"/>&nbsp;&nbsp;<form:radiobutton path="boardYn" label="미사용" value="N"/> </td>					
				</tr>				
				<tr>
					<td>5</td>
					<td>통합예약 시설홍보</td>
					<td><form:radiobutton path="expandYn" label="사용" value="Y"/>&nbsp;&nbsp;<form:radiobutton path="expandYn" label="미사용" value="N"/> </td>					
				</tr>				
				<tr>
					<td>6</td>
					<td>통합예약 강좌홍보</td>
					<td><form:radiobutton path="lectureYn" label="사용" value="Y"/>&nbsp;&nbsp;<form:radiobutton path="lectureYn" label="미사용" value="N"/> </td>					
				</tr>				
				<tr>
					<td>7</td>
					<td>통합예약 행사홍보</td>
					<td><form:radiobutton path="eventYn" label="사용" value="Y"/>&nbsp;&nbsp;<form:radiobutton path="eventYn" label="미사용" value="N"/> </td>					
				</tr>				
			</tbody>
		</table>
		<!-- 버튼 -->
		<div class="btn_zone">
			<a href="javascript:doMgrMainConfigMod();" class="btn2">수정</a>
		</div>
		<!-- //버튼 -->
	</div>
</form:form>
</body>
</html>
