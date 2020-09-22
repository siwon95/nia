<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%-- ------------------------------------------------------------
- 제목 : 권한설정
- 파일명 : role_list.jsp
- 최종수정일 : 2019-04-29
- 상태 : 1차완료
-------------------------------------------------------------- --%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>통합관리시스템</title>

<!-- 페이지 전용 스타일/스크립트 -->
<script>
$(function(){
	<c:if test="${not empty message}">
	resultMessage("<spring:message code="${message}" />");
	</c:if>
	<c:if test="${not empty param.message}">
	resultMessage("<spring:message code="${param.message}" />");
	</c:if>
	
	//버튼 이벤트
	$(".btn_add").click(function(e){
    	e.preventDefault();
    	var ajaxParam = {"roleIdx":""};
    	var ajaxOption = {};
    	ajaxOption.success = function(data){
			if(data.result) {
				var resultVo= data.resultVo;
				$("#actionkey").val(resultVo.actionkey);
				$("#roleIdx").val(resultVo.roleIdx);
				$("#roleName").val(resultVo.roleName);
				$("#permCd").val(resultVo.permCd);
				$("input:radio[name='useYn'][value='"+resultVo.useYn+"']").prop('checked', true);
				$("input:radio[name='publishAuthYn'][value='"+resultVo.publishAuthYn+"']").prop('checked', true);
				modalOpen("#roleForm");
			}else{
				alert(data.message);
			}
    	};
    	ajaxAction("<c:url value='/boffice/sy/mgr/RoleListFormAx.do'/>", ajaxParam, ajaxOption);
	});
	$(".btn_mod").click(function(e){
    	e.preventDefault();
		var trIdx = $(this).parents("tr").eq(0).attr("data-idx");
    	var ajaxParam = {"roleIdx":trIdx};
    	var ajaxOption = {};
    	ajaxOption.success = function(data){
			if(data.result) {
				var resultVo= data.resultVo;
				$("#actionkey").val(resultVo.actionkey);
				$("#roleIdx").val(resultVo.roleIdx);
				$("#roleName").val(resultVo.roleName);
				$("#permCd").val(resultVo.permCd);
				$("input:radio[name='useYn'][value='"+resultVo.useYn+"']").prop('checked', true);
				$("input:radio[name='publishAuthYn'][value='"+resultVo.publishAuthYn+"']").prop('checked', true);
				modalOpen("#roleForm");
			}else{
				alert(data.message);
			}
    	};
    	ajaxAction("<c:url value='/boffice/sy/mgr/RoleListFormAx.do'/>", ajaxParam, ajaxOption);
	});
	$(".btn_del").click(function(e){
    	e.preventDefault();
		if(!confirm("<spring:message code="common.delete.msg" />")){
			return;
		}
		var trIdx = $(this).parents("tr").eq(0).attr("data-idx");
    	var ajaxParam = {"roleIdx":trIdx};
		ajaxActionMessage("<c:url value='/boffice/sy/mgr/MgrRoleRmvAx.do'/>", ajaxParam, '', true);
	});
	$(".btn_modalSave").click(function(e){
    	e.preventDefault();
		if(!confirm("저장 하시겠습니까?")){
			return;	
		}
    	if(formRequiredCheck($("#saveFrm"))){
	    	var ajaxParam = $("#saveFrm").serializeObject();
    		ajaxActionMessage("<c:url value='/boffice/sy/mgr/RoleListRegAx.do'/>", ajaxParam, '정상 처리 되었습니다.', true);	
    	}
	});
	$(".btn_roleConfig").click(function(e){
    	e.preventDefault();
		var trIdx = $(this).parents("tr").eq(0).attr("data-idx");
		$("#AuthorityFrame").prop("src", "/boffice/sy/mgr/MgrAuthorityPop.do?roleIdx="+trIdx+"&searchType=menu");
		modalOpen("#divMgrAuthority");
	});
	
	//전체선택
    $("input[name=checkAll]").click(function() {
		if($(this).is(":checked"))
			$("input[name^=checkItem]").prop("checked", true);
		else
			$("input[name^=checkItem]").prop("checked", false);
    });
	
	//목록의 발행권한 설정
	$("input[name='publishAuthYn'].useToggle").on("click", function(){
		var trIdx = $(this).parents("tr").eq(0).attr("data-idx");
		var roleIdx = $(this).val();
		var _value = "N";
		if($(this).is(":checked")) {
			_value = "Y";
		}
    	var ajaxParam = {"roleIdx":trIdx, "value":_value};
		ajaxActionMessage("<c:url value='/boffice/sy/mgr/MgrRolePublishAuthYnModAx.do'/>", ajaxParam);
	});
	
	//목록의 사용유무 설정
	$("input[name='useYn'].useToggle").on("click", function(){
		var trIdx = $(this).parents("tr").eq(0).attr("data-idx");
		var roleIdx = $(this).val();
		var _value = "N";
		if($(this).is(":checked")) {
			_value = "Y";
		}
    	var ajaxParam = {"roleIdx":trIdx, "value":_value};
		ajaxActionMessage("<c:url value='/boffice/sy/mgr/MgrRoleUseYnModAx.do'/>", ajaxParam);
	});
	
	//중복확인초기화
	$("#mgrId").click(function() {
		if($("#actionkey").val() == "regist" && $("#idCheckYn").val() == "Y") {
			$("#idCheckYn").val("N");
		}
	});
});

function doForm(f) {
	var requiredCheck = formRequiredCheck(f);
	if(requiredCheck == false){
		return false;
	}
	
	var ajaxParam = $(f).serialize();
	ajaxActionMessage("<c:url value='/boffice/sy/mgr/RoleListRegAx.do' />", ajaxParam, '저장되었습니다.', true);
	return false;
}
</script>
<!-- //페이지 전용 스타일/스크립트 -->

</head>
<body>

	<div class="section">
		<form:form commandName="RoleVo" name="RoleVo" method="post" class="searchListPage" onsubmit="return doSearch(this);">
		<form:hidden path="pageIndex" />
		<div class="search">
			<div class="right">
				<input type="hidden" name="searchCondition" value="2" />
				<form:input path="searchKeyword" size="30" class="w150" />
				<button class="btn_inline btn_search">검색</button>
			</div>
		</div>
		</form:form>
		
		<div class="tableTitle">
			<!-- <div class="btnArea left">
				<a href="#" class="btn_inline">선택수정</a>
				<a href="#" class="btn_inline">선택삭제</a>
			</div> -->
			<div class="btnArea">
				<a href="#" class="btn_inline btn_add">신규등록</a>
			</div>
		</div>
		
		<div class="tableBox">
			<table class="list useBtn">
				<caption>관리자 목록</caption>
				<colgroup>
					<!-- <col class="w50"> -->
					<col class="w50">
					<col>
					<col class="w70">
					<col class="w70">
					<col class="w120">
					<col class="w80">
				</colgroup>
				<thead>
					<tr>
						<!-- <th scope="col"><input type="checkbox" name="checkAll" value="1"></th> -->
						<th scope="col">순번</th>
						<th scope="col">권한명</th>
						<th scope="col">발행권한</th>
						<th scope="col">사용</th>
						<th scope="col">관리</th>
						<th scope="col">권한설정</th>
					</tr>
				</thead>
				<tbody>
					<c:set var="n" value="${resultCnt - (paginationInfo.currentPageNo-1)*paginationInfo.recordCountPerPage }"/>
					<c:forEach var="result" items="${resultList}" varStatus="status">
					<tr data-idx="<c:out value="${result.roleIdx}"/>">
						<!-- <td><input type="checkbox" name="checkItem[]" value="1"></td> -->
						<td><c:out value="${n - status.index}" /></td>
						<td class="left"><c:out value="${result.roleName}" /></td>
						<td><input type="checkbox" id="publishAuthYn<c:out value="${status.index}" />" name="publishAuthYn" value="Y" class="useToggle" <c:if test="${result.publishAuthYn eq 'Y'}">checked="checked"</c:if> /><label for="publishAuthYn<c:out value="${status.index}" />">사용</label></td>
						<td><input type="checkbox" id="useYn<c:out value="${status.index}" />" name="useYn" value="Y" class="useToggle" <c:if test="${result.useYn eq 'Y'}">checked="checked"</c:if> /><label for="useYn<c:out value="${status.index}" />">사용</label></td>
						<td>
							<a href="#" class="btn_inline btn_mod">수정</a>
							<a href="#" class="btn_inline btn_caption btn_del">삭제</a>
						</td>
						<td>
							<c:if test="${result.permCd eq '05010000'}">
							모든권한
							</c:if>
							<c:if test="${result.permCd eq '05020000'}">
							<a href="#" class="btn_inline on btn_roleConfig">권한설정</a>
							</c:if>
						</td>
					</tr>
					</c:forEach>
					<c:if test="${fn:length(resultList) == 0}">
					<tr>
						<td colspan="5" class="empty"><spring:message code="common.nodata.msg" /></td>
					</tr>
					</c:if>
				</tbody>
			</table>	
		</div>
			
		<div class="paging">
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="doPageMove" />
		</div>
	</div>


<!-- ============================== 모달영역 ============================== -->
<div id="roleForm" class="modalWrap small">
	<div class="modalTitle">
		<h3>권한 등록/수정</h3>
		<a href="#" class="btn_modalClose">모달팝업닫기</a>
	</div>
	<div class="modalContent">
		<form id="saveFrm" name="saveFrm" method="post" onsubmit="return doForm(this);">
		<input type="hidden" id="roleIdx" name="roleIdx" />
		<input type="hidden" id="actionkey" name="actionkey" />
		
		<div class="tableBox">
			<table class="form">
			<caption>권한 입력/수정</caption>
			<colgroup>
				<col class="w100">
				<col>
			</colgroup>
			<tbody>
				<tr>
					<th scope="row"><label for="roleName">권한명</label></th>
					<td><input type="text" id="roleName" name="roleName" size="20" value="" class="w100p required" /></td>
				</tr>
				<tr>
					<th scope="row"><label for="permCd">권한선택</label></th>
					<td>
						<select id="permCd" name="permCd" class="w100p required">
							<option value="">권한을 선택하세요</option>
							<c:forEach var="codeInfo" items="${codeList }">
								<c:if test="${codeInfo.code ne '05010000'}">
									<option value="${codeInfo.code}"><c:out value="${codeInfo.codeName}"/></option>
								</c:if>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="modalPublishAuthYn1">발행권한</label></th>
					<td>
						<input type="radio" id="modalPublishAuthYn1" name="publishAuthYn" value="Y" /> <label for="modalPublishAuthYn1">사용</label>
						<input type="radio" id="modalPublishAuthYn2" name="publishAuthYn" value="N" /> <label for="modalPublishAuthYn2">미사용</label>
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="modalUseYn1">사용 유무</label></th>
					<td>
						<input type="radio" id="modalUseYn1" name="useYn" value="Y" /> <label for="modalUseYn1">사용</label>
						<input type="radio" id="modalUseYn2" name="useYn" value="N" /> <label for="modalUseYn2">미사용</label>
					</td>
				</tr>
			</tbody>
			</table>
		</div>
		<div class="btnArea">
			<input type="submit" value="저장" class="btn_m on">
			<a href="#" class="btn_m btn_modalClose">취소</a>
		</div>
		</form>
	</div>
</div>
<div id="divMgrAuthority" class="modalWrap">
	<div class="modalTitle">
		<h3>권한설정</h3>
		<a href="#" class="btn_modalClose">모달팝업닫기</a>
	</div>
	<div class="modalContent">
		<iframe id="AuthorityFrame" name="AuthorityFrame" src="#" style="width:100%;height:600px;border:0px none;"></iframe>
	</div>
</div>

</body>
</html>
