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

<script type="text/javascript">

function fncSelectMainImageList() {
    var varFrom = document.getElementById("mainImage");
    varFrom.action = "<c:url value='/boffice/ex/mainImage/mainImageList.do'/>";
    varFrom.submit();
}

function fncMainImageUpdate() {
    var varFrom = document.getElementById("mainImage");
    varFrom.action = "/boffice/ex/mainImage/mainImageMod.do";

    if(confirm("저장 하시겠습니까?")){
    	if(varFrom.imageNm.value == ''){
    		alert("제목은 필수 입력값입니다.");
    	}
		if($('input:checkbox[name="type"]:checked').length == 0){
			alert("팝업 위치 구분는 필수 입력값입니다.");
			return;
		}
       	if(document.getElementById('ordtmNtceYn').checked == false) {

			var ntceBgndeYYYMMDD = document.getElementById('ntceBgndeYYYMMDD').value;
			var ntceEnddeYYYMMDD = document.getElementById('ntceEnddeYYYMMDD').value;

			if(ntceBgndeYYYMMDD == '' || ntceEnddeYYYMMDD == '') {
                alert("게시기간은 필수 입력값입니다.");
                return;
            }

			var iChkBeginDe = Number( ntceBgndeYYYMMDD.replaceAll("-","") + fn_egov_SelectBoxValue('ntceBgndeHH') +  fn_egov_SelectBoxValue('ntceBgndeMM'));
			var iChkEndDe = Number( ntceEnddeYYYMMDD.replaceAll("-","") + fn_egov_SelectBoxValue('ntceEnddeHH') +  fn_egov_SelectBoxValue('ntceEnddeMM'));

			if(iChkBeginDe > iChkEndDe || iChkEndDe < iChkBeginDe ){
				alert("게시시작일시는 게시종료일시 보다 클수 없고,\n게시종료일시는 게시시작일시 보다 작을수 없습니다. ");
				return;
			}

			varFrom.ntceBgnde.value = ntceBgndeYYYMMDD.replaceAll('-','') + fn_egov_SelectBoxValue('ntceBgndeHH') +  fn_egov_SelectBoxValue('ntceBgndeMM');
			varFrom.ntceEndde.value = ntceEnddeYYYMMDD.replaceAll('-','') + fn_egov_SelectBoxValue('ntceEnddeHH') +  fn_egov_SelectBoxValue('ntceEnddeMM');
       	} else {
       		varFrom.ntceBgnde.value = "";
       		varFrom.ntceEndde.value = "";
       	}

       	if($('#linkUrl').val() != ''){
			if(is_url($('#linkUrl').val()) == false){
				alert("URL형식이 잘못되었습니다. ex)http:\/\/www.*****.com");
				$('#linkUrl').focus();
				return;
			}
		}

        if(varFrom.imageDc.value == '') {
            alert("이미지설명은 필수 입력값입니다.");
            return;
        }
        varFrom.submit();

    }
}
/* ********************************************************
* RADIO BOX VALUE FUNCTION
******************************************************** */
function fn_egov_RadioBoxValue(sbName)
{
	var FLength = document.getElementsByName(sbName).length;
	var FValue = "";
	for(var i=0; i < FLength; i++)
	{
		if(document.getElementsByName(sbName)[i].checked == true){
			FValue = document.getElementsByName(sbName)[i].value;
		}
	}
	return FValue;
}
/* ********************************************************
* SELECT BOX VALUE FUNCTION
******************************************************** */
function fn_egov_SelectBoxValue(sbName)
{
	var FValue = "";
	for(var i=0; i < document.getElementById(sbName).length; i++)
	{
		if(document.getElementById(sbName).options[i].selected == true){

			FValue=document.getElementById(sbName).options[i].value;
		}
	}

	return  FValue;
}
/* ********************************************************
* PROTOTYPE JS FUNCTION
******************************************************** */
String.prototype.trim = function(){
	return this.replace(/^\s+|\s+$/g, "");
}

String.prototype.replaceAll = function(src, repl){
	 var str = this;
	 if(src == repl){return str;}
	 while(str.indexOf(src) != -1) {
	 	str = str.replace(src, repl);
	 }
	 return str;
}
function fncMainImageDelete() {
    var varFrom = document.getElementById("mainImage");
    varFrom.action = "/boffice/ex/mainImage/mainImageRmv.do";
    if(confirm("삭제 하시겠습니까?")){
        varFrom.submit();
    }
}

$(window).load(function() {
	$("input[id=ntceBgndeYYYMMDD], input[id=ntceBgndeYYYMMDD]").datepicker();
	$("input[id=ntceBgndeYYYMMDD], input[id=ntceBgndeYYYMMDD]").mask("9999-99-99");
    $("input[id=ntceBgndeYYYMMDD], input[id=ntceBgndeYYYMMDD]").datepicker( "option", "dateFormat", "yy-mm-dd" );
    $("input[id=ntceEnddeYYYMMDD], input[id=ntceEnddeYYYMMDD]").datepicker();
	$("input[id=ntceEnddeYYYMMDD], input[id=ntceEnddeYYYMMDD]").mask("9999-99-99");
    $("input[id=ntceEnddeYYYMMDD], input[id=ntceEnddeYYYMMDD]").datepicker( "option", "dateFormat", "yy-mm-dd" );
});

</script>

</head>

<body>

	<!-- 컨텐츠 영역 -->
	<div id="contents">
	<form:form commandName="mainImage" method="post" action="${pageContext.request.contextPath}/uss/ion/msi/updtMainImage.do' />" enctype="multipart/form-data">
	<%-- <input type="hidden" name="type" value="<c:out value="${mainImageVO.type}"/>"> --%>
	<input type="hidden" name="posblAtchFileNumber" value="1" >

	<!-- 검색조건 유지 -->
	<input type="hidden" name="searchCondition" value="<c:out value='${mainImageVO.searchCondition}'/>">
	<input type="hidden" name="searchKeyword" value="<c:out value='${mainImageVO.searchKeyword}'/>">
	<input type="hidden" name="pageIndex" value="<c:out value='${mainImageVO.pageIndex}'/>">

	<input type="hidden" name="imageId" value="<c:out value='${mainImage.imageId}'/>">
	<input type="hidden" name="ntceBgnde" value="<c:out value='${mainImage.ntceBgnde}'/>">
	<input type="hidden" name="ntceEndde" value="<c:out value='${mainImage.ntceEndde}'/>">


		<table summary="" class="write">
			<caption></caption>
			<colgroup>
				<col width="20%" />
				<col width="*" />
			</colgroup>
			<tbody>
			<tr>
			  	<th>* <label for="imageNm">제목</label></th>
				<td><input name="imageNm" id="imageNm" title="이미지명" type="text" value="<c:out value='${mainImage.imageNm}'/>" size="80"  /></td>
			</tr>
			<tr>
			  	<th>* 팝업 위치 구분</th>
				<td>
					<table border="0">
					<tr>
					<c:forEach var="result" items="${rowDataList }" varStatus="status">
						<td>
							<input type="checkbox" name="type" id="<c:out value="${result.code }"/>" value="<c:out value="${result.code }"/>" <c:if test="${fn:indexOf(mainImage.type, result.code) >= 0 }">checked</c:if>/> <label for="<c:out value="${result.code }"/>"><c:out value="${result.codeName }"/></label>
						</td>
						<c:if test="${status.count % 4 == 0 }">
						</tr><tr>
						</c:if>
					</c:forEach>
					</tr>
					</table>
				</td>
			</tr>
			<tr>
				<th>* <label for="ntceBgndeYYYMMDD">게시기간</label></th>
				<td>
					<span class="cal_in" ><input type="text" name="ntceBgndeYYYMMDD" id="ntceBgndeYYYMMDD" size="10" maxlength="10"  value="<c:if test="${not empty mainImage.ntceBgnde}"><c:out value="${fn:substring(mainImage.ntceBgnde, 0, 4)}"/>-<c:out value="${fn:substring(mainImage.ntceBgnde, 4, 6)}"/>-<c:out value="${fn:substring(mainImage.ntceBgnde, 6, 8)}"/></c:if>" readonly/></span>
				    <form:select path="ntceBgndeHH">
				        <form:options items="${ntceBgndeHH}" itemValue="code" itemLabel="codeNm"/>
				    </form:select><label for="ntceBgndeHH">시</label>
				    <form:select path="ntceBgndeMM">
				        <form:options items="${ntceBgndeMM}" itemValue="code" itemLabel="codeNm"/>
				    </form:select><label for="ntceBgndeMM">분</label>
				    &nbsp;&nbsp;~&nbsp;&nbsp;
				    <label for="ntceEnddeYYYMMDD" class="hidden">마감기간</label>
				    <span class="cal_in" ><input type="text" name="ntceEnddeYYYMMDD" id="ntceEnddeYYYMMDD" size="10" maxlength="10"  value="<c:if test="${not empty mainImage.ntceEndde}"><c:out value="${fn:substring(mainImage.ntceEndde, 0, 4)}"/>-<c:out value="${fn:substring(mainImage.ntceEndde, 4, 6)}"/>-<c:out value="${fn:substring(mainImage.ntceEndde, 6, 8)}"/></c:if>" readonly/></span>
				    <form:select path="ntceEnddeHH">
				        <form:options items="${ntceEnddeHH}" itemValue="code" itemLabel="codeNm"/>
				    </form:select><label for="ntceEnddeHH">시</label>
				    <form:select path="ntceEnddeMM">
				        <form:options items="${ntceEnddeMM}" itemValue="code" itemLabel="codeNm"/>
				    </form:select><label for="ntceEnddeMM">분</label>
				    &nbsp;&nbsp;&nbsp;&nbsp;
				    <input type="checkbox" id="ordtmNtceYn" name="ordtmNtceYn" value="Y" <c:if test="${mainImage.ordtmNtceYn == 'Y'}">checked</c:if> /> <label for="ordtmNtceYn">상시 게시</label>
				</td>
			</tr>
			<tr>
				<th>* <label for="linkUrl">링크주소</label></th>
				<td>
					<input type="text" name="linkUrl" id="linkUrl" size="80"  title="링크주소" value="<c:out value='${mainImage.linkUrl}'/>"/>
				    &nbsp;&nbsp;&nbsp;&nbsp;
				    <input type="checkbox" id="newWindowYn" name="newWindowYn" value="Y" <c:if test="${mainImage.newWindowYn == 'Y'}">checked</c:if> /> <label for="newWindowYn">새창 띄우기</label>
				</td>
			</tr>
			<tr>
			  	<th>* <label for="egovComFileUploader">이미지 첨부</label></th>
			  	<td>
			    	<span><input type="file" name="file_1" id="egovComFileUploader" title="이미지" onchange="javascript:imgFileCheck(this);"/></span>
			  		<div style="padding-top:5px;">
						<img src="<c:url value='/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${mainImage.imageFile}"/>&amp;fileSn=0" width="225" height="200" alt="<c:out value="${mainImage.imageDc}"/>" />
						<c:import url="/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
							<c:param name="param_atchFileId" value="${mainImage.imageFile}" />
						</c:import>
					</div>
			  	</td>
			</tr>
			<tr>
				<th>* <label for="imageDc">이미지 설명</label></th>
				<td>
					<textarea id="imageDc" name="imageDc" cols="75" rows="3" style="width:100%;" title="이미지설명"><c:out value='${mainImage.imageDc}'/></textarea>
					<div style="padding-top:5px;">
						<span style="font-size:11px;">(이미지의 대체 텍스트를 입력해주세요!)</span>
					</div>
				</td>
			</tr>

			<tr>
				<th>* 반영여부</th>
				<td>
					<input type="radio" name="reflctYn" id="reflctY" value="Y" <c:if test="${empty mainImage.reflctYn ||mainImage.reflctYn == 'Y'}">checked</c:if>/><label for="reflctY">게시중</label>
					<input type="radio" name="reflctYn" id="reflctN" value="N" <c:if test="${mainImage.reflctYn == 'N'}">checked</c:if>/><label for="reflctN">게시중지</label>
					<div style="padding-top:5px;">
						<span style="font-size:11px;">(* 팝업 기시기간 중이라도 게시중지를 선택하시면 강제 게시 종료를 하실 수 있습니다.)</span>
					</div>
			 	</td>
			</tr>
			</tbody>
		</table>

		<!-- 버튼 -->
		<div class="btn_zone">
			<%-- 목록 --%>
			<a href="javascript:fncSelectMainImageList();" class="btn3">목록</a>
			<%-- 저장 --%>
			<a href="javascript:fncMainImageUpdate();" class="btn2">저장</a>
			<%-- 삭제 --%>
			<a href="javascript:fncMainImageDelete();" class="btn2">삭제</a>
		</div>
		<!--// 버튼 -->

</form:form>

	</div>
	<!--// 컨텐츠 영역 -->

</body>
</html>

