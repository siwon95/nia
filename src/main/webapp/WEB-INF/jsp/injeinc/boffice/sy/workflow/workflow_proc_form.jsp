<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="xss" uri="http://cms.injeinc.co.kr/xss" %>

<script>
var type = "<c:out value="${type}" />";
var overlapChk = "N";	// 신청/승인 프로세스ID 중복체크
var workflowId = "<c:out value="${workflowVo.workflowId}" />";	// 신청/승인 프로세스ID 값
var selectTr;
$(function(){
	$(".btn_workflowId_chk").click(function(e) {
		if($("#workflowId").val() == ""){
			alert("신청/승인 프로세스ID가 입력되지 않았습니다.");
			$("#workflowId").focus();
			return;
		}

		$.ajax({
			type : "POST",
			url : "<c:url value='/boffice/sy/workflow/workflowIdChkAx.do'/>",
			data : { "workflowId" : $("#workflowId").val() },
			dataType : "json",
			success : function(data) {
				overlapChk = data.chkYN;
				if (data.chkYN == "Y") {
					alert("사용가능한 신청/승인 프로세스ID 입니다.");
				} else {
					alert("이미 사용중인 신청/승인 프로세스ID 입니다.");
				}
			},
			error : function(xhr, status, error) {
				alert(status);
			}
		});
	});
	
	$(".btn_del").click(function(e) {
		e.preventDefault();
		if(!confirm("<spring:message code="common.delete.msg" />")){
			return;
		}
		
		$.ajax({
			type : "POST",
			url : "<c:url value='/boffice/sy/workflow/workflowDeleteProc.do' />",
			data : { "workflowId" : workflowId },
			dataType : "json",
			success : function(data) {
				
				if (data.chkYN == "Y") {
					if(data.deleteYn == "Y"){
						alert("<spring:message code='success.common.delete' />");
						window.location.reload();
					}else{
						alert("이미 사용중인 신청/승인 프로세스 입니다. 삭제할 수 없습니다.");
					}
				} else {
					alert("<spring:message code='fail.common.delete' />");
				}
			},
			error : function(xhr, status, error) {
				alert(status);
			}
			
		});
	});
	
	$("#workflowId").change(function() {
		overlapChk = "N";
	});
	
	//버튼이벤트
    $(".btn_row_add").click(function(e){
    	e.preventDefault();
    	var clone = $("#wf_step_list tr:eq(1)").clone();
    	$(this).parent().parent().before(clone);
    	clone.find("input[type='text']").val("");
    	clone.find("select").val("");
    	clone.find(".roleTxt").text("");
    	rowDelEvent();
    });
	
    rowDelEvent();
    
    $(document).on("click",".btn_charge",function(e){  
    	selectTr = $(this).parent().parent();
    	window.open("<c:url value='/boffice_noDeco/sy/mgr/RoleListPop.do'/>",'','scrollbars=yes,width=600,height=550');
    });
});

function rowDelEvent(){
	$(".btn_row_del").unbind().click(function(e){  
    	e.preventDefault();
    	 var rowcnt = $('#wf_step_list tr').size();
    	 if(rowcnt > 3){
    		$(this).parent().parent().remove();
    	 }else{
    		 alert("항목을 삭제하실수 없습니다.");
    	 }
    });
}

function fn_roleAdd(trIdx){
	var idxArr = trIdx.split("|");
	
	selectTr.find(".roleTxt").text(idxArr[1]);
	selectTr.find("input[name='workflowRoleArr']").val(idxArr[0]);
}

function fn_save(form){
	if (type == "I" && overlapChk != "Y") {
		alert("신청/승인 프로세스ID의 중복체크를 해야합니다.");
		$("#workflowId").focus();
		return false;
	}
	
	if(formRequiredCheck(form)){
		return true;
	}else{
		return false;
	}

	return true;
}
</script>

<!-- ============================== 모달영역 ============================== -->
<div class="modalTitle">
	<h3>워크플로우 유형 등록</h3>
	<a href="#" class="btn_modalClose">모달팝업닫기</a>
</div>
<div class="modalContent">
	<form name="frm" id="frm" method="post" action="<c:url value="/boffice/sy/workflow/workflowRegist.do"/>" onsubmit="return fn_save(this);">
		<div class="tableBox">
			<table class="form">
				<caption>신청/승인 프로세스</caption>
				<colgroup>
					<col class="w20p">
					<col>
				</colgroup>
				<tbody>
					<tr>
						<c:if test="${workflowVo.workflowId == null}">
						<th><label for="workflowId"><span class="required">*</span>신청/승인 프로세스ID</label></th>
						</c:if>
						<c:if test="${workflowVo.workflowId != null}">
						<th><span class="required">*</span>신청/승인 프로세스ID</th>
						</c:if>
						<td>
							<c:if test="${workflowVo.workflowId == null}">
								<input type="text" id="workflowId" name="workflowId" class="w70p onlyEngNum required" maxlength="35">
								<a href="#" class="btn_inline btn_workflowId_chk">중복체크</a>
							</c:if>
							<c:if test="${workflowVo.workflowId != null}">
								<input type="hidden" id="workflowId" name="workflowId" value="<c:out value="${workflowVo.workflowId}" />">
								<c:out value="${workflowVo.workflowId}" />
							</c:if>
						</td>
					</tr>
					<tr>
						<th><label for="workflowNm"><span class="required">*</span>신청/승인 프로세스명</label></th>
						<td>
							<input type="text" id="workflowNm" name="workflowNm" class="w70p required" maxlength="30" value="<c:out value="${workflowVo.workflowNm}" />">
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="tableBox">
			<table class="list" id="wf_step_list">
				<caption>워크플로우 관리(신청/승인 프로세스) 스텝 목록</caption>
				<colgroup>
					<col class="w30p">
					<col class="w20p">
					<col class="w70">
					<col class="w70">
					<col>
					<col class="w70">
				</colgroup>
				<thead>
					<tr>
						<th>단계명</th>
						<th colspan="2">담당자역할</th>
						<th>순서</th>
						<th>다음단계</th>
						<th>삭제</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${resultStepList }" var="resultStepList" varStatus="stepStatus">
						<tr>
							<td>
								<select name="workflowStepArr" class="required" title="단계명">
									<option value="">선택</option>
									<c:forEach items="${commCodeStatusCd }" var="result" varStatus="status">
										<option value="<c:out value="${result.code }"/>" <c:if test="${resultStepList.workflowStep eq result.code }">selected="selected"</c:if>><c:out value="${result.codeName }"/></option>
									</c:forEach>
								</select>
							</td>
							<td class="roleTxt"></td>
							<td><a href="#" class="btn_inline btn_charge">선택</a><input type="hidden" name="workflowRoleArr" value="<c:out value="${resultStepList.chargerRoleId }"/>"></td>
							<td><input type="text" name="workflowSortArr" class="required" title="순서" value="<c:out value="${resultStepList.workflowStepOrder }"/>"></td>
							<td>
								<select name="workflowNextStepArr" title="다음단계">
									<option value="">선택</option>
									<c:forEach items="${commCodeStatusCd }" var="result" varStatus="status">
										<option value="<c:out value="${result.code }"/>" <c:if test="${resultStepList.nextWorkflowStep eq result.code }">selected="selected"</c:if>><c:out value="${result.codeName }"/></option>
									</c:forEach>
								</select>
							</td>
							<td><a href="#" class="btn_inline btn_row_del">삭제</a></td>
						</tr>
					</c:forEach>
					<c:if test="${fn:length(resultStepList) == 0}">
						<tr>
							<td>
								<select name="workflowStepArr" class="required" title="단계명">
									<option value="">선택</option>
									<c:forEach items="${commCodeStatusCd }" var="result" varStatus="status">
										<option value="<c:out value="${result.code }"/>"><c:out value="${result.codeName }"/></option>
									</c:forEach>
								</select>
							</td>
							<td class="roleTxt"></td>
							<td><a href="#" class="btn_inline btn_charge">선택</a><input type="hidden" name="workflowRoleArr"></td>
							<td><input type="text" name="workflowSortArr" class="required" title="순서"></td>
							<td>
								<select name="workflowNextStepArr" title="다음단계">
									<option value="">선택</option>
									<c:forEach items="${commCodeStatusCd }" var="result" varStatus="status">
										<option value="<c:out value="${result.code }"/>"><c:out value="${result.codeName }"/></option>
									</c:forEach>
								</select>
							</td>
							<td><a href="#" class="btn_inline btn_row_del">삭제</a></td>
						</tr>
					</c:if>
					<tr>
						<td colspan="6"><a href="#" class="btn_inline btn_row_add">항목추가</a></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="btnArea">
			<input type="submit" value="저장" class="btn_m on">
			<c:if test="${workflowVo.workflowId != null}">
				<a href="#" class="btn_m btn_del">삭제</a>
			</c:if>
			<a href="#" class="btn_m btn_modalClose">취소</a>
		</div>
	</form>
</div>
<!-- ============================== //모달영역 ============================== -->
