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

	function doTopImagesReg(){
		if(confirm("등록 하시겠습니까?")){
			if($('input[name=tiTitle]').val() == ""){
				alert("제목을 입력해주세요");
				$('input[name=title]').focus();
				return;
			}
			
			var startday = document.TopImagesVo.startday.value.replace(/-/gi,"");
			var starthour = document.TopImagesVo.starthour.value;
			var startminute = document.TopImagesVo.startminute.value;
			
			$('input[name=tiPostSdt]').val(startday+starthour+startminute);
			
			var endday = document.TopImagesVo.endday.value.replace(/-/gi,"");
			var endhour = document.TopImagesVo.endhour.value;
			var endminute = document.TopImagesVo.endminute.value;
			
			if($("#divCombo option:selected").val() == '') {
				alert("구분을 선택해주세요");
				return;
			}
			$('input[name=tiPostEdt]').val(endday+endhour+endminute);
			
			if(cm_check_empty($('input[id=startday]').attr("title"), $('input[id=startday]')) == false) {
				return;
			}
			if(cm_check_empty($('input[id=endday]').attr("title"), $('input[id=endday]')) == false) {
				return;
			}
			
			
			if($('input[name=tiPostSdt]').val() > $('input[name=tiPostEdt]').val()){
				alert("게시시작일이 게시종료일보다 큽니다");
				return;
			}
			
			if($('input:checkbox[name=chktarget]').is(":checked") == true){
				$('input[name=tiTarget]').val('blank');
			}else{
				$('input[name=tiTarget]').val('self');
			}
			
			var thumbext = $('input[name=fileUpload]').val(); //파일을 추가한 input 박스의 값
			if(thumbext != null && thumbext != ''){
				thumbext = thumbext.slice(thumbext.indexOf(".") + 1).toLowerCase(); //파일 확장자를 잘라내고, 비교를 위해 소문자로 만듭니다.
		
				if(thumbext != "jpg" && thumbext != "png" &&  thumbext != "gif" &&  thumbext != "bmp"){ //확장자를 확인합니다.
					alert('이미지 파일(jpg, png, gif, bmp)만 등록 가능합니다.');
					$('input[name=fileUpload]').select();
					document.selection.clear();
					return;
				}
			}
			document.TopImagesVo.action = "/boffice/main/top/Top_Images_Reg.do";
			
			document.TopImagesVo.submit();
		}
	}
	       
	function doTopImagesMod(idx){
		if(confirm("수정 하시겠습니까?")){
			if($('input[name=tiTitle]').val() == ""){
				alert("제목을 입력해주세요");
				$('input[name=tiTitle]').focus();
				return;
			}
			var startday = document.TopImagesVo.startday.value.replace(/-/gi,"");
			var starthour = document.TopImagesVo.starthour.value;
			var startminute = document.TopImagesVo.startminute.value;
			
			$('input[name=tiPostSdt]').val(startday+starthour+startminute);
			
			var endday = document.TopImagesVo.endday.value.replace(/-/gi,"");
			var endhour = document.TopImagesVo.endhour.value;
			var endminute = document.TopImagesVo.endminute.value;
			
			$('input[name=tiPostEdt]').val(endday+endhour+endminute);
			
			if($("#divCombo option:selected").val() == '') {
				alert("구분을 선택해주세요");
				return;
			}
			if(cm_check_empty($('input[id=startday]').attr("title"), $('input[id=startday]')) == false) {
				return;
			}
			if(cm_check_empty($('input[id=endday]').attr("title"), $('input[id=endday]')) == false) {
				return;
			}
			
			
			if($('input[name=tiPostSdt]').val() > $('input[name=tiPostEdt]').val()){
				alert("게시시작일이 게시종료일보다 큽니다");
				return;
			}
			if($('input:checkbox[name=chktarget]').is(":checked") == true){
				$('input[name=tiTarget]').val('blank');
			}else{
				$('input[name=tiTarget]').val('self');
			}
			
			var thumbext = $('input[name=fileUpload]').val(); //파일을 추가한 input 박스의 값
			if(thumbext != null && thumbext != ''){
				thumbext = thumbext.slice(thumbext.indexOf(".") + 1).toLowerCase(); //파일 확장자를 잘라내고, 비교를 위해 소문자로 만듭니다.
		
				if(thumbext != "jpg" && thumbext != "png" &&  thumbext != "gif" &&  thumbext != "bmp"){ //확장자를 확인합니다.
					alert('이미지 파일(jpg, png, gif, bmp)만 등록 가능합니다.');
					$('input[name=fileUpload]').select();
					document.selection.clear();
					return;
				}
			}
			
			document.TopImagesVo.action = "/boffice/main/top/Top_Images_Mod.do";
			
			document.TopImagesVo.submit();
		}
	}
	
	function doTopImagesList(){
		document.TopImagesVo.action = "<c:url value='/boffice/main/top/Top_Images_List.do' />";
		document.TopImagesVo.submit();
	}
	
	function changeGroupName(){
		$('input[name=groupCd]').val($('select[name=group]').val());
	}
	
	function doFileRmv(tiIdx,fileName){
		$.ajax({
			type: "GET",
			url: "<c:url value='/boffice/main/top/Top_ImagesFileRmv_Ax.do'/>",
			data : "tiIdx=" + tiIdx +"&tiFileName=" + fileName,
			dataType : "json",
			success:function(obj){
				$('#fileimg')[0].innerHTML = "";
				$("input[name=tiFileName]").val('');
				$("input[name=orignlFileNm]").val('');
				$("input[name=tiFilePath]").val('');
				alert(obj.message);
			 },
	        error: function(xhr,status,error){
	        	alert(status);
	        }
		});	
	}
	
	function doTopImagesRmv(){
		if(confirm("정말 삭제하시겠습니까?")){
			document.TopImagesVo.action = "/boffice/main/top/Top_Images_Rmv.do";
			document.TopImagesVo.submit();
		}
	}
	
	function doChangeText(){
		if($("#divCombo option:selected").val() == ''){
			$("#typeId").hide();
			$("#textId")[0].innerHTML = "";
		}else if($("#divCombo option:selected").val() == '26010000'){
			$("#typeId").show();
			$("#textId")[0].innerHTML = " <b>※이미지 크기 : (대표사이트: 545px * 407px) (태안,횡성: 850px * 477px) (동,보건소: 800px * 357px)</b>";
		}else{
			$("#typeId").hide();
			$("#textId")[0].innerHTML = " <b>※이미지 크기: 220px * 284px</b>";
		}
	}
	
	$(window).load(function() {
		<c:if test="${TopImagesVo.tiIdx eq ''}">
			$("input[name=tiLink]").val('http://');
		</c:if>
			$("input[id=startday], input[id=startday]").datepicker();
			$("input[id=startday], input[id=startday]").mask("9999-99-99");
		    
		    $("input[id=endday], input[id=endday]").datepicker();
			$("input[id=endday], input[id=endday]").mask("9999-99-99");
			
			cm_combo_cmm("#divCombo","26000000");
		    var divCombo  = 	'<c:out value="${TopImagesVo.division}" />';
		    $("#divCombo").val(divCombo).attr("selected","selected");
		    doChangeText();
	});
//]]>
</script>
</head>
<body style="text-align: center; margin: 0 auto; display: inline; padding-top: 100px;">
<form:form commandName="TopImagesVo" name="TopImagesVo" method="post" enctype="multipart/form-data">
	<form:hidden path="pageIndex" />
	<form:hidden path="searchKeyword" />
	<form:hidden path="searchDivision" />
	<form:hidden path="searchSite" />
	<form:hidden path="tiIdx" />
	<form:hidden path="tiFileName" />
	<form:hidden path="tiFileOrgName" />
	<form:hidden path="tiFilePath" />
	<form:hidden path="tiPostSdt" />
	<form:hidden path="tiPostEdt" />
	<form:hidden path="tiTarget" />

	<!-- 컨텐츠 영역 -->
	<div id="contents">
		<table summary="" class="write">
			<caption></caption>
			<colgroup>
				<col width="10%" />
				<col width="*" />
			</colgroup>
			<tbody>
				<tr>
					<th>* <label for="tiTitle">제목</label></th>
					<td><form:input path="tiTitle" title="제목" size="50" /></td>
				</tr>
				<tr>
					<th>* <label for="divCombo">구분</label></th>
					<td>
						<select name="division" id="divCombo" onchange="doChangeText();">
							<option value="">선택해주세요</option>
						</select>
					</td>
				</tr>
				<tr id="typeId">
					<th>* <label for="<c:out value="${result.code }"/>">사이트</label></th>
					<td>
						<table border="0">
							<tr>
								<c:forEach var="result" items="${rowDataList }" varStatus="status">
									<td>
										<input type="checkbox" name="type" id="<c:out value="${result.code }"/>" value="<c:out value="${result.code }"/>" <c:if test="${fn:indexOf(TopImagesVo.type, result.code) >= 0 }">checked</c:if> />
										<label for="<c:out value="${result.code }"/>"><c:out value="${result.codeName }" /></label>
									</td>
									<c:if test="${status.count % 8 == 0 }">
							</tr>
							<tr>
									</c:if>
								</c:forEach>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<th>* <label for="startday">게시기간</label></th>
					<td>시작일
						<span class="cal_in"><form:input path="startday" title="게시기간 시작일" /></span>일
						<form:select path="starthour">
							<c:set var="hnum" value="0" />
							<c:forEach begin="1" end="23">
								<c:if test="${hnum <10 }">
									<c:set var="sHnum" value="0${hnum}" />
									<option value="<c:out value="${sHnum}"/>"
										<c:if test="${TopImagesVo.starthour eq sHnum}">selected="selected"</c:if>>0
										<c:out value="${hnum}" /></option>
									<c:set var="hnum" value="${hnum+1}" />
								</c:if>
								<c:if test="${hnum >=10 }">
									<option value="<c:out value="${hnum}"/>"
										<c:if test="${TopImagesVo.starthour eq hnum}">selected="selected"</c:if>><c:out value="${hnum}" /></option>
									<c:set var="hnum" value="${hnum+1}" />
								</c:if>
							</c:forEach>
						</form:select>
						<label for="starthour">시</label>&nbsp;
						<form:select path="startminute">
							<c:set var="num" value="0" />
							<c:forEach begin="1" end="59">
								<c:if test="${num <10 }">
									<c:set var="sNum" value="0${num}" />
									<option value="<c:out value="${sNum}"/>"
										<c:if test="${TopImagesVo.startminute eq sNum}">selected="selected"</c:if>><c:out value="${sNum}" /></option>
									<c:set var="num" value="${num+1}" />
								</c:if>
								<c:if test="${num >=10 }">
									<option value="<c:out value="${num}"/>"
										<c:if test="${TopImagesVo.startminute eq num}">selected="selected"</c:if>><c:out value="${num}" /></option>
									<c:set var="num" value="${num+1}" />
								</c:if>
							</c:forEach>
						</form:select>
						<label for="startminute">분</label><br /> 종료일
						<span class="cal_in"><form:input path="endday" title="게시기간 종료일" /></span>일
						<form:select path="endhour">
							<c:set var="hnum2" value="0" />
							<c:forEach begin="1" end="23">
								<c:if test="${hnum2 <10 }">
									<c:set var="sHnum2" value="0${hnum2}" />
									<option value="<c:out value="${sHnum2}"/>"
										<c:if test="${TopImagesVo.endhour eq sHnum2}">selected="selected"</c:if>><c:out value="${sHnum2}" /></option>
									<c:set var="hnum2" value="${hnum2+1}" />
								</c:if>
								<c:if test="${hnum2 >=10 }">
									<option value="<c:out value="${hnum2}"/>"
										<c:if test="${TopImagesVo.endhour eq hnum2}">selected="selected"</c:if>><c:out value="${hnum2}" /></option>
									<c:set var="hnum2" value="${hnum2+1}" />
								</c:if>
							</c:forEach>
						</form:select>
						<label for="endhour">시</label>&nbsp;
						<form:select path="endminute">
							<c:set var="num2" value="0" />
							<c:forEach begin="1" end="59">
								<c:if test="${num2 <10 }">
									<c:set var="sNum2" value="0${num2}" />
									<option value="<c:out value="${sNum2}"/>"
										<c:if test="${TopImagesVo.endminute eq sNum2}">selected="selected"</c:if>><c:out value="${sNum2}" /></option>
									<c:set var="num2" value="${num2+1}" />
								</c:if>
								<c:if test="${num2 >=10 }">
									<option value="<c:out value="${num2}"/>"
										<c:if test="${TopImagesVo.endminute eq num2}">selected="selected"</c:if>><c:out value="${num2}" /></option>
									<c:set var="num2" value="${num2+1}" />
								</c:if>
							</c:forEach>
						</form:select>
						<label for="endminute">분</label>
					</td>
				</tr>
				<tr>
					<th><label for="tiLink">링크 URL</label></th>
					<td><form:input path="tiLink" title="tiLink" size="50" />&nbsp;&nbsp;<input type="checkbox" name="chktarget" <c:if test="${TopImagesVo.tiTarget eq 'blank'}" >checked="true"</c:if> />새창 띄우기</td>
				</tr>
				<tr>
					<th>* <label for="fileUpload">이미지 첨부</label></th>
					<td>
						<c:if test="${TopImagesVo.tiFileName ne '' && TopImagesVo.tiFileName != null}">
							<span id="fileimg">
								<img src="<c:out value="${TopImagesVo.tiFilePath}"/><c:out value="${TopImagesVo.tiFileName}"/>" width="150" height="150" />
								<a href="javascript:doFileRmv('<c:out value="${TopImagesVo.tiIdx}"/>','<c:out value="${TopImagesVo.tiFileName}"/>');" class="btn2">삭제</a><br />
							</span>
						</c:if>
						<input type="file" name="fileUpload" id="fileUpload" size="40" />
						<span id="textId" style="color: red;"> <b>※이미지 크기 가로:220px 세로:284px</b></span>
					</td>
				</tr>
				<tr>
					<th><label for="tiTitle">이미지 설명</label></th>
					<td><form:textarea path="tiFileEx" rows="5" title="소개" /></td>
				</tr>
				<tr>
					<th><label for="tiTitle">게시상태</label></th>
					<td>
						<form:radiobutton path="useYn" value="Y" label="게시중" checked="true" />
						<form:radiobutton path="useYn" value="N" label="게시중지" />
					</td>
				</tr>
				<c:if test="${TopImagesVo.tiIdx ne '' }">
					<tr>
						<th>조회수</th>
						<td><c:out value="${TopImagesVo.viewCnt }" /></td>
					</tr>
				</c:if>
			</tbody>
		</table>
		<!-- 버튼 -->
		<div class="btn_zone">
			<c:if test="${TopImagesVo.tiIdx eq '' }">
				<a href="javascript:doTopImagesReg();" class="btn2">저장</a>
			</c:if>
			<c:if test="${TopImagesVo.tiIdx ne '' }">
				<a href="javascript:doTopImagesMod();" class="btn2">저장</a>
				<a href="javascript:doTopImagesRmv();" class="btn1">삭제</a>
			</c:if>
			<a href="javascript:doTopImagesList();" class="btn1">취소</a>
		</div>
		<!-- //버튼 -->
		<!-- //컨텐츠 영역 -->
	</div>
</form:form>
</body>
</html>