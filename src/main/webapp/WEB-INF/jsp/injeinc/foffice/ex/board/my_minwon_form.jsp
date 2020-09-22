<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="cmm" uri="http://cms.injeinc.co.kr/cmm"%>
<%@ taglib prefix="bbs" uri="http://cms.injeinc.co.kr/bbs"%>
<head>
<script type="text/javascript">
//<![CDATA[
	function doBbsContentFMod() {

		<c:forEach var="propertyInfo" items="${propertylist}">
		<c:set var="contentMapping" value="${propertyInfo.contentMapping}" />
		<c:set var="contentMappingL" value="${propertyInfo.contentMappingL}" />
		<c:set var="labelPropGbn" value="${propertyInfo.labelPropGbn}" />
		<c:set var="labelName" value="${propertyInfo.labelName}" />
		<c:set var="labelCompYn" value="${propertyInfo.labelCompYn}" />
		<c:if test="${labelCompYn eq 'Y'}">
			<c:choose>
			<c:when test="${labelPropGbn eq '16020400'}">
			if($("#<c:out value="${contentMappingL}"/>1").val() == "" || $("#<c:out value="${contentMappingL}"/>2").val() == "") {
				alert("<c:out value="${labelName}"/>을(를) 선택해 주세요");$("#<c:out value="${contentMappingL}"/>1").focus();return;
			}
			</c:when>
			<c:when test="${labelPropGbn eq '16020500'}">
			if($("#<c:out value="${contentMappingL}"/>1").val() == "" || $("#<c:out value="${contentMappingL}"/>2").val() == "" || $("#<c:out value="${contentMappingL}"/>3").val() == "") {
				alert("<c:out value="${labelName}"/>을(를) 입력해 주세요");$("#<c:out value="${contentMappingL}"/>1").focus();return;
			}
			</c:when>
			<c:when test="${labelPropGbn eq '16020900'}">
			if($("input:checkbox[name='<c:out value="${contentMappingL}"/>Arr']:checked").length < 1) {
				$("input:checkbox[name='<c:out value="${contentMappingL}"/>Arr']").eq(0).focus();alert("<c:out value="${labelName}"/>을(를) 입력해 주세요");return;
			}
			</c:when>
			<c:otherwise>
			if($("#<c:out value="${contentMappingL}"/>").val() == "") {
				alert("<c:out value="${labelName}"/>을(를) 입력해 주세요");$("#<c:out value="${contentMappingL}"/>").focus();return;
			}
			</c:otherwise>
			</c:choose>
		</c:if>
		<c:if test="${labelPropGbn eq '16020400'}">
		$("#<c:out value="${contentMappingL}"/>").val($("#<c:out value="${contentMappingL}"/>1").val()+"_"+$("#<c:out value="${contentMappingL}"/>2").val()+"_"+$("#<c:out value="${contentMappingL}"/>3").val());
		</c:if>
		<c:if test="${labelPropGbn eq '16020500'}">
		$("#<c:out value="${contentMappingL}"/>").val($("#<c:out value="${contentMappingL}"/>1").val()+"-"+$("#<c:out value="${contentMappingL}"/>2").val()+"-"+$("#<c:out value="${contentMappingL}"/>3").val());
		</c:if>
		<c:if test="${labelPropGbn eq '16020900'}">
		$("#<c:out value="${contentMappingL}"/>").val($("input[name='<c:out value="${contentMappingL}"/>Arr']:checkbox:checked").map(function () {return this.value;}).get());
		</c:if>
		</c:forEach>
		
		$("#BbsContentFVo").attr("action", "/site/seocho/foffice/board/MyMinwonMod.do").submit();
	}
	
	function goReload() {
		$("#BbsContentFVo").attr("action", "<c:url value='/site/seocho/foffice/board/MyMinwonForm.do' />").submit();
	}

	function doContentFileRmvAx(bcIdx, cbIdx, streFileNm) {
		
		if(!confirm("파일이 완전히 삭제 됩니다. 삭제하시겠습니까?")) return;
		
		$.ajax({
			type: "POST",
			url: "<c:url value='/common/board/ContentFileRmvAx.do'/>",
			data : {"bcIdx":bcIdx,"cbIdx":cbIdx,"streFileNm":streFileNm},
			dataType : "json",
			success:function(data) {
				alert(data.message);
				if(data.result) {
					goReload();
				}
			 },
			error: function(xhr,status,error){
				alert(status);
			}
		});
	}
	
	function openAddrSearch(addrTarget) {
		$("#addrTarget").val(addrTarget);
		var url = "/common/addrSearch/jusoPopup.jsp";
		var addr_pop = window.open(url,"pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
		addr_pop.focus();
	}
	
	function setAddrValue(addr1, addr2, zip) {
		var addrTarget = $("#addrTarget").val();
		$("#"+addrTarget+"1").val(zip);
		$("#"+addrTarget+"2").val(addr1);
		$("#"+addrTarget+"3").val(addr2);
	}

	$(document).ready(function() {
		$(".onlynum").keyup(
			function() {
				var regexp = /[^0-9]/g;
				var v = $(this).val();
				if(regexp.test(v)) {
					alert("숫자만 입력가능 합니다.");
					$(this).val(v.replace(regexp, ""));
				}
			}
		);
	});
//]]>
</script>
</head>
<body>
<fmt:setLocale value="ko_kr"/>
<form:form commandName="BbsContentFVo" name="BbsContentFVo" method="post" enctype="multipart/form-data">
<input type="hidden" id="pageIndex" name="pageIndex" value="<c:out value="${BbsContentFVo.pageIndex }"/>" />
<input type="hidden" id="searchCondition" name="searchCondition" value="<c:out value="${BbsContentFVo.searchCondition }"/>" />
<input type="hidden" id="searchKeyword" name="searchKeyword" value="<c:out value="${BbsContentFVo.searchKeyword }"/>" />
<input type="hidden" id="bcIdx" name="bcIdx" value="<c:out value="${BbsContentFVo.bcIdx }"/>" />
<input type="hidden" id="cbIdx" name="cbIdx" value="<c:out value="${BbsContentFVo.cbIdx }"/>" />
<input type="hidden" id="parentSeq" name="parentSeq" value="<c:out value="${BbsContentFVo.parentSeq }"/>" />
<input type="hidden" id="answerStep" name="answerStep" value="<c:out value="${BbsContentFVo.answerStep }"/>" />
<input type="hidden" id="answerDepth" name="answerDepth" value="<c:out value="${BbsContentFVo.answerDepth }"/>" />
<input type="hidden" id="addrTarget" name="addrTarget" value="" />

	<div class="board-regist-top mgb10">
		<ul class="icon-dot">
			<li>관악구 발전을 위하여 구정에 대한 건의, 개선, 불편사항은 "구청장에게 바란다"를 이용하여 주시기 바랍니다.</li>
			<li>구민 누구나가 자유로이 의견을 올리는 공간입니다.</li>
			<li>건전한 사이버공간이 되도록 협조바라며, <em class="bold blue underline">여기에 게시된 의견은 회신하여 드리지 않습니다.</em></li>
			<li>타인의 명예를 훼손하는 글을 게재할 경우 「정보통신망이용촉진 및 정보보호 등에 관한 법률」에 따라 형사처벌될 수 있음을 알려드립니다.</li>
			<li>저속한 표현, 비방, 반사회적인 글 및 선전ㆍ광고 등 상업적인 내용, 근거없는 유언비어, 선동적인 내용을 게시할 경우 <em class="bold blue underline">통보없이 삭제됨을 알려드립니다.</em></li>
			<li><em class="brown bold">공직선거법제93조1항에 의거하여 선거일전 180일부터 선거일까지 선거에 영향을 미치는 정당 또는 후보자를 지지ㆍ추천하거나 반대하는 내용이 포함되어 있거나
			정당의 명칭 또는 후보자의 성명을 나타내는 내용을 배부ㆍ첩부ㆍ살포ㆍ상영 또는 게시할 수 없습니다. 이를 위반할 경우 처벌을 받게 되오니 유념하여주시기 바랍니다.</em></li>
			<li><em class="blue bold">본인이 작성한 내용 중 개인정보에 관한 내용이 있다면 지금 즉시 삭제 후 재작성 해주시기 바랍니다. (주민번호, 주소 등)</em></li>
		</ul>
	</div>

	<div class="board">
	
		<p class="required-guide">
			<span class="required">*</span> 표시된 항목은 <em>필수입력</em> 항목입니다.
		</p>

		<table class="regist">
			<caption><c:out value="${boardVo.cbName }" /> 등록폼</caption>
			<colgroup>
				<col class="w15" />
				<col class="w85" />
			</colgroup>
			<tbody>
				<c:forEach var="propertyInfo" items="${propertylist}">
				<c:set var="contentMapping" value="${propertyInfo.contentMapping}" />
				<c:set var="contentMappingL" value="${propertyInfo.contentMappingL}" />
				<c:set var="labelPropGbn" value="${propertyInfo.labelPropGbn}" />
				<c:set var="labelName" value="${propertyInfo.labelName}" />
				<c:set var="labelCompYn" value="${propertyInfo.labelCompYn}" />
				<c:set var="itemCode" value="${propertyInfo.itemCode}" />
				<c:set var="bbsContentValue" value="${bbs:getValue(BbsContentFVo, contentMappingL)}" />
				
				<c:choose>
				<c:when test="${contentMapping eq 'NO_CONT'}">
				</c:when>
				<c:when test="${contentMapping eq 'MW_STATUS_CONT'}">
				</c:when>
				<c:when test="${contentMapping eq 'REG_DT'}">
				</c:when>
				<c:when test="${contentMapping eq 'COUNT_CONT'}">
				</c:when>
				<c:when test="${contentMapping eq 'FILE_CONT'}">
				<tr>
					<th scope="row"><label for="fileTagId0">첨부파일</label></th>
					<td>
						<c:forEach begin="0" varStatus="status" end="${BoardVo.bbsFileCnt-1}">
						<c:if test="${fn:length(fileList) > status.index}">
						<p><a href="/common/board/Download.do?bcIdx=<c:out value="${fileList[status.index].bcIdx}"/>&cbIdx=<c:out value="${fileList[status.index].cbIdx}"/>&streFileNm=<c:out value="${fileList[status.index].streFileNm}"/>" class="file" title="파일 다운로드" /><c:out value="${fileList[status.index].orignlFileNm}" /> [<c:out value="${bbs:byteCalculation(fileList[status.index].fileSize)}" />]</a> <a href="javascript:doContentFileRmvAx('<c:out value="${fileList[status.index].bcIdx}"/>', '<c:out value="${fileList[status.index].cbIdx}"/>, '<c:out value="${fileList[status.index].streFileNm}"/>');" class="btn-inner">삭제</a></p>
						</c:if>
						<c:if test="${fn:length(fileList) <= status.index}">
						<p><input type="file" id="dataFile<c:out value="${status.index}"/>" name="dataFile<c:out value="${status.index}"/>" class="w90" title="첨부파일<c:out value="${status.index}"/>" /></p>
						</c:if>
						</c:forEach>
					</td>
				</tr>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${labelPropGbn eq '16020700'}">
						<tr><!--SELECTBOX -->
							<th scope="row"><label for="<c:out value="${contentMappingL}"/>"><c:if test="${labelCompYn eq 'Y'}"><span class='required'>*</span></c:if><c:out value="${labelName}" /></label></th>
							<td>
								<c:set var="itemList" value="${cmm:getCodeList(itemCode)}" />
								<select id="<c:out value="${contentMappingL}"/>" name="<c:out value="${contentMappingL}"/>">
								<c:forEach var="itemInfo" items="${itemList}" varStatus="status">
								<option value="<c:out value="${itemInfo.code}"/>" <c:if test="${itemInfo.code eq bbsContentValue}">selected="selected"</c:if> ><c:out value="${itemInfo.codeName}"/></option>
								</c:forEach>
								</select>
							</td>
						</tr>
						</c:when>
						<c:when test="${labelPropGbn eq '16020800'}">
						<tr><!-- RADIOBOX -->
							<th scope="row"><label for="<c:out value="${contentMappingL}"/>1"><c:if test="${labelCompYn eq 'Y'}"><span class='required'>*</span></c:if><c:out value="${labelName}" /></label></th>
							<td>
								<c:set var="itemList" value="${cmm:getCodeList(itemCode)}" />
								<c:forEach var="itemInfo" items="${itemList}" varStatus="status">
								<input type="radio" id="<c:out value="${contentMappingL}"/><c:out value="${status.count}"/>" name="<c:out value="${contentMappingL}"/>" value="<c:out value="${itemInfo.code}"/>" <c:if test="${itemInfo.code eq bbsContentValue}">checked="checked"</c:if> />
								<label for="<c:out value="${contentMappingL}"/><c:out value="${status.count}"/>"><c:out value="${itemInfo.codeName}"/></label> 
								</c:forEach>
							</td>
						</tr>
						</c:when>
						<c:when test="${labelPropGbn eq '16020900'}">
						<tr><!-- CHECKBOX-->
							<th scope="row"><label for="<c:out value="${contentMappingL}"/>1"><c:if test="${labelCompYn eq 'Y'}"><span class='required'>*</span></c:if><c:out value="${propertyInfo.labelName}" /></label></th>
							<td>
								<c:set var="itemList" value="${cmm:getCodeList(itemCode)}" />
								<c:forEach var="itemInfo" items="${itemList}" varStatus="status">
								<input type="checkbox" id="<c:out value="${contentMappingL}"/><c:out value="${status.count}"/>" name="<c:out value="${contentMappingL}"/>Arr" value="<c:out value="${itemInfo.code}"/>" <c:if test="${fn:indexOf(bbsContentValue, itemInfo.code) > -1}">checked="checked"</c:if> />
								<label for="<c:out value="${contentMappingL}"/><c:out value="${status.count}"/>"><c:out value="${itemInfo.codeName}"/></label> 
								</c:forEach>
								<input type="hidden" id="<c:out value="${contentMappingL}"/>" name="<c:out value="${contentMappingL}"/>" value="<c:out value="${bbsContentValue}"/>" />
							</td>
						</tr>
						</c:when>
						<c:when test="${labelPropGbn eq '16020200'}">
						<tr><!-- TEXTAREA -->
							<th scope="row"><label for="<c:out value="${contentMappingL}"/>"><c:if test="${labelCompYn eq 'Y'}"><span class='required'>*</span></c:if><c:out value="${labelName}" /></label></th>
							<td><textarea rows="10" cols="50" id="<c:out value="${contentMappingL}"/>" name="<c:out value="${contentMappingL}"/>" class="w90 board_input"><c:out value="${bbsContentValue}"/></textarea></td>
						</tr>
						</c:when>
						<c:when test="${labelPropGbn eq '16020500'}">
						<tr><!-- PHONE -->
							<th scope="row"><label for="<c:out value="${contentMappingL}"/>1"><c:if test="${labelCompYn eq 'Y'}"><span class='required'>*</span></c:if><c:out value="${labelName}" /></label></th>
							<td>
								<c:set var="telArr" value="${cmm:split(bbsContentValue, '-', 3)}" />
								<input type="text" id="<c:out value="${contentMappingL}"/>1" name="<c:out value="${contentMappingL}"/>1" value="<c:out value="${fn:length(telArr) > 0 ? telArr[0] : ''}"/>" class="onlynum" maxlength="4" size="5" title="전화 앞번호" /> - 
								<input type='text' id="<c:out value="${contentMappingL}"/>2" name="<c:out value="${contentMappingL}"/>2" value="<c:out value="${fn:length(telArr) > 1 ? telArr[1] : ''}"/>" class="onlynum" maxlength="4" size="5" title="전화 중간번호" /> - 
								<input type='text' id="<c:out value="${contentMappingL}"/>3" name="<c:out value="${contentMappingL}"/>3" value="<c:out value="${fn:length(telArr) > 2 ? telArr[2] : ''}"/>" class="onlynum" maxlength="4" size="5" title="전화 뒷번호" />
								<input type="hidden" id="<c:out value="${contentMappingL}"/>" name="<c:out value="${contentMappingL}"/>" value="<c:out value="${bbsContentValue}"/>" />
							</td>
						</tr>
						</c:when>
						<c:when test="${labelPropGbn eq '16020400'}">
						<c:set var="addrArr" value="${cmm:split(bbsContentValue, '_', 3)}" />
						<tr><!-- ADDRESS -->
							<th scope="row" rowspan="3"><label for="<c:out value="${contentMappingL}"/>1"><c:if test="${labelCompYn eq 'Y'}"><span class='required'>*</span></c:if><c:out value="${labelName}" /></label></th>
							<td>
								<input type="text" id="<c:out value="${contentMappingL}"/>1" name="<c:out value="${contentMappingL}"/>1" value="<c:out value="${fn:length(addrArr) > 0 ? addrArr[0] : ''}"/>" size="10" readonly="true" /> 
								<a href="#searchAddr" onclick="openAddrSearch('<c:out value="${contentMappingL}"/>');return false;" class="btn-inner" title="새창">우편번호검색</a>
								<input type="hidden" id="<c:out value="${contentMappingL}"/>" name="<c:out value="${contentMappingL}"/>" value="<c:out value="${bbsContentValue}"/>" />
							</td>
						</tr>
						<tr>
							<td><input type="text" id="<c:out value="${contentMappingL}"/>2" name="<c:out value="${contentMappingL}"/>2" value="<c:out value="${fn:length(addrArr) > 1 ? addrArr[1] : ''}"/>" class="w80" title="기본주소" readonly="true" /></td>
						</tr>
						<tr>
							<td><input type="text" id="<c:out value="${contentMappingL}"/>3" name="<c:out value="${contentMappingL}"/>3" value="<c:out value="${fn:length(addrArr) > 2 ? addrArr[2] : ''}"/>" class="w80" title="상세주소" /></td>
						</tr>
						</c:when>
						<c:when test="${labelPropGbn eq '16021000'}">
						<tr><!-- 이름 -->
							<th scope="row"><label for="<c:out value="${contentMappingL}"/>"><c:if test="${labelCompYn eq 'Y'}"><span class='required'>*</span></c:if><c:out value="${labelName}" /></label></th>
							<td><input type="text" id="<c:out value="${contentMappingL}"/>" name="<c:out value="${contentMappingL}"/>" value="<c:out value="${bbsContentValue}"/>" class="w15" readonly="true" /></td></td>
						</tr>
						</c:when>
						<c:otherwise>
						<tr>
							<th scope="row"><label for="<c:out value="${contentMappingL}"/>"><c:if test="${labelCompYn eq 'Y'}"><span class='required'>*</span></c:if><c:out value="${labelName}" /></label></th>
							<td><input type="text" id="<c:out value="${contentMappingL}"/>" name="<c:out value="${contentMappingL}"/>" value="<c:out value="${bbsContentValue}"/>" class="w90" /></td>
						</tr>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
				</c:choose>
				</c:forEach>
			
			</tbody>
		</table>
	
		<!-- 버튼 -->
		<div class="btns aright">
			<a href="#modify" onclick="doBbsContentFMod();return false;" class="type1">수정</a>
			<a href="/site/seocho/foffice/board/MyMinwonView.do<c:out value='${addParam}' />" class="type2">취소</a>
		</div>
		<!-- //버튼 -->
		
	</div><!-- //board -->
	
</form:form>
<!--  웹필터 수정 -->
<%@ include file="/webfilter/inc/initCheckWebfilter.jsp"%>
<!--  웹필터 수정 -->
</body>
