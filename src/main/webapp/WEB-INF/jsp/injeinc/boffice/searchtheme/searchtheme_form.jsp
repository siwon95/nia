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
	function doSearchthemeReg(){
		if(confirm("등록 하시겠습니까?")){
			if($('input[name=groupCd]').val() == ""){
				alert("그룹을 입력해주세요");
				$('input[name=groupCd]').focus();
				return;
			}
			if($('input[name=title]').val() == ""){
				alert("제목을 입력해주세요");
				$('input[name=title]').focus();
				return;
			}
			var tel1 = document.SearchthemeVo.tel1.value;
			var tel2 = document.SearchthemeVo.tel2.value;
			var tel3 = document.SearchthemeVo.tel3.value;
			
			document.SearchthemeVo.telNum.value = tel1+"-"+tel2+"-"+tel3;
			
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
			document.SearchthemeVo.action = "/boffice/searchtheme/Searchtheme_Reg.do";
			
			document.SearchthemeVo.submit();
		}
	}
	       
	function doSearchthemeMod(idx){
		if(confirm("수정 하시겠습니까?")){
			if($('input[name=groupCd]').val() == ""){
				alert("그룹을 입력해주세요");
				$('input[name=groupCd]').focus();
				return;
			}
			if($('input[name=title]').val() == ""){
				alert("제목을 입력해주세요");
				$('input[name=title]').focus();
				return;
			}
			var tel1 = document.SearchthemeVo.tel1.value;
			var tel2 = document.SearchthemeVo.tel2.value;
			var tel3 = document.SearchthemeVo.tel3.value;
			
			document.SearchthemeVo.telNum.value = tel1+"-"+tel2+"-"+tel3;
			
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
			
			document.SearchthemeVo.action = "/boffice/searchtheme/Searchtheme_Mod.do";
			
			document.SearchthemeVo.submit();
		}
	}
	
	function doSearchthemeList(){
		document.SearchthemeVo.action = "<c:url value='/boffice/searchtheme/Searchtheme_List.do' />";
		document.SearchthemeVo.submit();
	}
	
	function changeGroupName(){
		$('input[name=groupCd]').val($('select[name=group]').val());
	}
	
	function doFileRmv(stIdx,fileName){
		$.ajax({
			type: "GET",
			url: "<c:url value='/boffice/searchtheme/SearchthemeFileRmv_Ax.do' />",
			data : "stIdx=" + stIdx +"&streFileNm=" + fileName,
			dataType : "json",
			success:function(obj){
				$('#fileimg')[0].innerHTML = "";
				$("input[name=streFileNm]").val('');
				$("input[name=orignlFileNm]").val('');
				$("input[name=filePath]").val('');
				alert(obj.message);
			 },
	        error: function(xhr,status,error){
	        	alert(status);
	        }
		});	
	}
	
	$(document).ready(function(){
		<c:if test="${SearchthemeVo.stIdx eq ''}">
			$("input[name=url]").val('http://');
		</c:if>
		<c:if test="${SearchthemeVo.groupCd eq ''}">
			$("input[name=groupCd]").val('기타');
		</c:if>
	});
//]]>
</script>
</head>
<body style="text-align: center; margin: 0 auto; display: inline; padding-top: 100px;">
	<form:form commandName="SearchthemeVo" name="SearchthemeVo" method="post" enctype="multipart/form-data">
		<form:hidden path="pageIndex" />
		<form:hidden path="searchCondition" />
		<form:hidden path="searchKeyword" />
		<form:hidden path="stIdx" />
		<form:hidden path="useYn" />
		<form:hidden path="schgroupcd" />
		<form:hidden path="telNum" />
		<form:hidden path="streFileNm" />
		<form:hidden path="orignlFileNm" />
		<form:hidden path="filePath" />

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
						<th>* <label for="groupCd">그룹</label></th>
						<td>
							<form:input path="groupCd" title="그룹" maxlength="20" readonly />&nbsp;
							<label for="group" class="hidden">그룹선택</label>
							<select name="group" id="group" onchange="changeGroupName();">
								<c:forEach items="${groupCdList }" var="group" varStatus="status">
									<option value="<c:out value="${group.groupCd }"/>"
										<c:if test="${SearchthemeVo.groupCd eq group.groupCd}">selected="selected"</c:if>><c:out value="${group.groupCd }" />
									</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th>* <label for="title">제목</label></th>
						<td><form:input path="title" title="제목" size="50" /></td>
					</tr>
					<tr>
						<th><label for="url">URL</label></th>
						<td><form:input path="url" title="URL" size="50" /></td>
					</tr>
					<tr>
						<th><label for="introduce">소개</label></th>
						<td><form:textarea path="introduce" rows="5" title="소개" /></td>
					</tr>
					<tr>
						<th><label for="tags">태그</label></th>
						<td><form:input path="tags" title="태그" size="50" /></td>
					</tr>
					<tr>
						<th><label for="department">담당부서</label></th>
						<td><form:input path="department" title="담당부서" size="50" /></td>
					</tr>
					<tr>
						<th><label for="tel1">연락처</label></th>
						<td>
							<form:input path="tel1" size="3" maxlength="3" title="연락처앞자리" onkeypress="return showKeyCode(event)" /> - <form:input path="tel2" size="4" maxlength="4" title="연락처중간자리" onkeypress="return showKeyCode(event)" /> - <form:input path="tel3" size="4" maxlength="4" title="연락처끝자리" onkeypress="return showKeyCode(event)" />
						</td>
					</tr>
					<tr>
						<th><label for="fileUpload">이미지</label></th>
						<td>
							<c:if test="${SearchthemeVo.streFileNm ne '' && SearchthemeVo.streFileNm != null}">
								<span id="fileimg">
									<img src="/upload/search_theme/<c:out value="${SearchthemeVo.streFileNm}"/>" width="150" height="150" />
									<a href="javascript:doFileRmv('<c:out value="${SearchthemeVo.stIdx}"/>','<c:out value="${SearchthemeVo.streFileNm}"/>');" class="btn2">삭제</a><br/>
								</span>
							</c:if>
							<input type="file" name="fileUpload" id="fileUpload" size="40" />
						</td>
					</tr>
				</tbody>
			</table>
			<!-- 버튼 -->
			<div class="btn_zone">
				<c:if test="${SearchthemeVo.stIdx eq '' }">
					<a href="javascript:doSearchthemeReg();" class="btn1">저장</a>
				</c:if>
				<c:if test="${SearchthemeVo.stIdx ne '' }">
					<a href="javascript:doSearchthemeMod();" class="btn1">저장</a>
				</c:if>
				<a href="javascript:doSearchthemeList();" class="btn2">취소</a>
			</div>
			<!-- //버튼 -->
		<!-- //컨텐츠 영역 -->
		</div>
	</form:form>
</body>
</html>