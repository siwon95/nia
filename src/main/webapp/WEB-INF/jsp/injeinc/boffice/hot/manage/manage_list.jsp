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
<title>배너관리</title>
<script type="text/javascript">
//<![CDATA[

	//리스트 조회
	function doHotManageList(){
		document.HotManageVo.action = "<c:url value='/boffice/hot/manage/HotManage_List.do' />";
		document.HotManageVo.submit();
	}
	
	//삭제
	function doHotManageRmv(idx){
		$("input[name=hlIdx]").val(idx);
		if(confirm("정말 삭제 하시겠습니까?")){
			document.HotManageVo.action = "/boffice/hot/manage/HotManage_Rmv.do";
			document.HotManageVo.submit();
		}
	}
	
	//페이징처리 
	/* function doHotManagePag(pageIndex) {
		document.HotManageVo.pageIndex.value = pageIndex;
		document.HotManageVo.action = "<c:url value='/boffice/hot/manage/HotManage_List.do' />";
		document.HotManageVo.submit();
	} */
	
	//등록/수정폼 보이기
	function doHotManageForm(){
		$("input[name=hlTitle]").val('');
		$("input[name=hlLink]").val('http://');
		$('select:eq(0)').find('option:first').attr('selected', 'selected');
		$("input:radio[name='useYn']:radio[value='Y']").prop('checked', true);
		document.getElementById("fileWrap").innerHTML = document.getElementById("fileWrap").innerHTML;  
		$("#HotManageForm").show();
	}
	
	//등록/수정폼 숨기기
	function hotManageFormHide(){
		$("input[name=hlTitle]").val('');
		$("input[name=hlLink]").val('http://');
		$('select:eq(0)').find('option:first').attr('selected', 'selected');
		$("input:radio[name='useYn']:radio[value='Y']").prop('checked', true);
		document.getElementById("fileWrap").innerHTML = document.getElementById("fileWrap").innerHTML;
		$("#HotManageForm").hide();
	}
	
	//신규등록
	function doHotManageReg(){
		var msg = "저장 하시겠습니까?";
		
		if($("input[name=hlIdx]").val() != null && $("input[name=hlIdx]").val() != ''){
			if(confirm(msg)){
				document.HotManageVo.action = "/boffice/hot/manage/HotManage_Mod.do";
				document.HotManageVo.submit();
			}
		}else{
			if(confirm(msg)){
				document.HotManageVo.action = "/boffice/hot/manage/HotManage_Reg.do";
				document.HotManageVo.submit();
			}
		}
		
	}
	
	//수정폼 호출
	function doHotManageModForm(idx,name,link,target,useYn,sort){
		$("input[name=hlIdx]").val(idx);
		$("input[name=hlTitle]").val(name);
		$("input[name=hlLink]").val(link);
		$("input[name=hlSort]").val(sort);
		$("#hlTarget").val(target);
		if(useYn == 'Y'){
			$("input:radio[name='useYn']:radio[value='Y']").prop('checked', true);
		}else{
			$("input:radio[name='useYn']:radio[value='N']").prop('checked', true);
		}
		$("#HotManageForm").show();
	}
	
	//삭제
	function doHotManageRmv(idx){
		$("input[name=hlIdx]").val(idx);
		if(confirm("정말 삭제하시겠습니까?")){
			document.HotManageVo.action="/boffice/hot/manage/HotManage_Rmv.do";
			document.HotManageVo.submit();
		}
	}
	
	function doStepdown(idx,sort){
		$("input[name=hlIdx]").val(idx);
		$("input[name=hlSort]").val(sort);
		document.HotManageVo.action="/boffice/hot/manage/Step_Mod.do?type=down";
		document.HotManageVo.submit();
	}
	
	function doStepup(idx,sort){
		$("input[name=hlIdx]").val(idx);
		$("input[name=hlSort]").val(sort);
		document.HotManageVo.action="/boffice/hot/manage/Step_Mod.do?type=up";
		document.HotManageVo.submit();
	}
	$(document).ready(function(){
		
	});
//]]>
</script>
</head>
<body style="text-align: center; margin: 0 auto; display: inline; padding-top: 100px;">
<form:form commandName="HotManageVo" name="HotManageVo" method="post" enctype="multipart/form-data">
	<form:hidden path="hlIdx" />
	<form:hidden path="hlSort" />
	<!-- 컨텐츠 영역 -->
	<div id="contents">
		<div class="btn_zone" style="text-align: center;">
			<a href="javascript:doHotManageForm();" class="btn1">등록</a>
		</div>
		<br />
		<!-- //빠른서비스 등록/수정 폼 -->
		<div id="HotManageForm" style="display: none;">
			<table summary="" class="write">
				<caption></caption>
				<colgroup>
					<col width="15%" />
					<col width="*" />
				</colgroup>
				<tbody>
					<tr>
						<th colspan="2">신규 빠른서비스 등록/수정</th>
					</tr>
					<tr>
						<th><label for="hlTitle">이름</label></th>
						<td><form:input path="hlTitle" maxlength="18" size="30" /></td>
					</tr>
					<tr>
						<th><label for="hlLink">링크</label></th>
						<td><form:input path="hlLink" maxlength="150" size="100" /></td>
					</tr>
					<tr>
						<th><label for="hlTarget">타겟</label></th>
						<td>
							<form:select path="hlTarget" id="hlTarget">
								<form:option value="blank">새창</form:option>
								<form:option value="self">현재창</form:option>
							</form:select>
						</td>
					</tr>
					<tr>
						<th><label for="fileUpload">이미지 파일</label></th>
						<td>
							<span id="fileWrap">
								<input type="file" name="fileUpload" id="fileUpload" size="40" />&nbsp;
								<span style="color: red;"><b>※이미지 크기 가로:50px 세로:50px</b></span>
							</span>
						</td>
					</tr>
					<!-- <tr>
							<th>이미지 파일2<br>(마우스 오버시)</th>
							<td>
								<span id="fileWrap">
									<input type="file" name="fileUpload2" id="fileUpload2" size="40" />&nbsp;<span style="color:red;"><b>※이미지 크기 가로:50px 세로:50px</b></span>
								</span>
							</td>
						</tr> -->
					<tr>
						<th><label for="useYn">사용여부</label></th>
						<td>
							<form:radiobutton path="useYn" id="useYn" label="사용" value="Y" />&nbsp;&nbsp;
							<form:radiobutton path="useYn" label="미사용" value="N" />
						</td>
					</tr>
				</tbody>
			</table>
			<br />
			<div class="btn_zone" style="text-align: center;">
				<a href="javascript:doHotManageReg();" class="btn2">저장</a>
				<a href="javascript:hotManageFormHide();" class="btn1">취소</a>
			</div>
		</div>
		<!-- //빠른서비스 등록/수정 폼 -->
		<br /> <br />
		<table summary="" class="list1" style="table-layout: fixed; word-break: break-all;">
			<caption></caption>
			<colgroup>
				<col width="5%" />
				<col width="15%" />
				<col width="15%" />
				<col width="25%" />
				<col width="5%" />
				<col width="10%" />
				<col width="10%" />
				<col width="10%" />
			</colgroup>
			<thead>
				<tr>
					<th>NO</th>
					<th>이미지</th>
					<th>제목</th>
					<th>링크</th>
					<th>타겟</th>
					<th>순위</th>
					<th>수정</th>
					<th>삭제</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${fn:length(resultList) == 0}">
						<tr>
							<td colspan="9" align="center">빠른서비스 내용이 없습니다.</td>
						</tr>
					</c:when>
					<c:when test="${fn:length(resultList) > 0}">
						<c:forEach items="${resultList}" var="result" varStatus="status">
							<tr>
								<td><c:out value="${status.count }" /></td>
								<td>
									<c:if test="${result.hlFileName ne '' && result.hlFileName != null}">
										<img src="<c:out value="${result.hlFilePath}"/><c:out value="${result.hlFileName}"/>" width="160" height="80" />
									</c:if>
								</td>
								<td style="text-align: left"><c:out value="${result.hlTitle}" /></td>
								<td style="text-align: left"><c:out value="${result.hlLink}" /></td>
								<td><c:out value="${result.hlTarget}" /></td>
								<td>
									<c:if test="${result.hlSort != 0}">
										<input type="button" value="▼" onclick="doStepdown('<c:out value="${result.hlIdx }" />','<c:out value="${result.hlSort }" />');" />&nbsp;
										<input type="button" value="▲" onclick="doStepup('<c:out value="${result.hlIdx }" />','<c:out value="${result.hlSort }" />');" />
									</c:if>
								</td>
								<td>
									<input type="button" value="수정" onclick="doHotManageModForm('<c:out value="${result.hlIdx}" />','<c:out value="${result.hlTitle}" />','<c:out value="${result.hlLink}" />','<c:out value="${result.hlTarget}" />','<c:out value="${result.useYn}" />','<c:out value="${result.hlSort}" />');" />
								</td>
								<td><input type="button" value="삭제" onclick="doHotManageRmv('<c:out value="${result.hlIdx}" />');" /></td>
							</tr>
						</c:forEach>
					</c:when>
				</c:choose>
			</tbody>
		</table>

		<!--paginate -->
		<%-- <div class="paginate">
					<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="doHotManagePag" />
			</div> --%>
		<!--//paginate -->

	</div>
	<!-- //컨텐츠 영역 -->
</form:form>
</body>
</html>