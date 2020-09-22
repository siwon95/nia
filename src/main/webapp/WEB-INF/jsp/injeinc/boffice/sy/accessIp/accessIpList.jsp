<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%-- ------------------------------------------------------------
- 제목 : 접속허용설정
- 파일명 : accessIpList.jsp
- 최종수정일 : 2019-04-29
- 상태 : 1차완료
-------------------------------------------------------------- --%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>통합관리시스템</title>

<!-- 페이지 전용 스타일/스크립트 -->
<script>
$(function() {
	<c:if test="${not empty message}">
	resultMessage("<spring:message code="${message}" />");
	</c:if>
	<c:if test="${not empty param.message}">
	resultMessage("<spring:message code="${param.message}" />");
	</c:if>
	
	//버튼이벤트
	$(".btn_add").click(function(e){
    	e.preventDefault();
    	var ajaxParam = {"ai_idx":""};
    	var ajaxOption = {};
    	ajaxOption.success = function(data){
			var result= data.result;
			$("#actionkey").val(result.actionkey);
			$("#ai_idx").val(result.ai_idx);
			$('input[name=s_ip]').val(result.s_ip);
			$('input[name=e_ip]').val(result.e_ip);
			$('#note').val(result.note);
			if(result.s_ip){
				var s_ip_array = result.s_ip.split(".");
				$('#ip1').val(s_ip_array[0]);
				$('#ip2').val(s_ip_array[1]);
				$('#ip3').val(s_ip_array[2]);
				$('#ip4').val(s_ip_array[3]);
				$('#ip5').val(s_ip_array[0]);
				$('#ip6').val(s_ip_array[1]);
				$('#ip7').val(s_ip_array[2]);
			}
			if(result.e_ip){
				var e_ip_array = result.e_ip.split(".");
				$('#ip8').val(e_ip_array[3]);
			}
			modalOpen("#divAccessIpForm");
    	};
    	ajaxAction("<c:url value='/boffice/sy/accessIp/accessIpFormAx.do'/>", ajaxParam, ajaxOption);
	});
	$(".btn_mod").click(function(e){
    	e.preventDefault();
		var trIdx = $(this).parents("tr").eq(0).attr("data-idx");
    	var ajaxParam = {"ai_idx":trIdx};
    	var ajaxOption = {};
    	ajaxOption.success = function(data){
			var result= data.result;
			$("#actionkey").val(result.actionkey);
			$("#ai_idx").val(result.ai_idx);
			$('input[name=s_ip]').val(result.s_ip);
			$('input[name=e_ip]').val(result.e_ip);
			$('#note').val(result.note);
			if(result.s_ip){
				var s_ip_array = result.s_ip.split(".");
				$('#ip1').val(s_ip_array[0]);
				$('#ip2').val(s_ip_array[1]);
				$('#ip3').val(s_ip_array[2]);
				$('#ip4').val(s_ip_array[3]);
				$('#ip5').val(s_ip_array[0]);
				$('#ip6').val(s_ip_array[1]);
				$('#ip7').val(s_ip_array[2]);
			}
			if(result.e_ip){
				var e_ip_array = result.e_ip.split(".");
				$('#ip8').val(e_ip_array[3]);
			}
			modalOpen("#divAccessIpForm");
    	};
    	ajaxAction("<c:url value='/boffice/sy/accessIp/accessIpFormAx.do'/>", ajaxParam, ajaxOption);
	});
	$(".btn_del").click(function(e){
    	e.preventDefault();
		if(!confirm("<spring:message code="common.delete.msg" />")){
			return;
		}
		var trIdx = $(this).parents("tr").eq(0).attr("data-idx");
    	var ajaxParam = {"ai_idx":trIdx};
		ajaxActionMessage("<c:url value='/boffice/sy/accessIp/accessIpRmvAx.do'/>", ajaxParam, '', true);
	});
	
	/* 숫자만 입력하도록 */
	$(document).on("keyup", "input:text[numberOnly]", function() {$(this).val( $(this).val().replace(/[^0-9]/gi,"") );});

});

function doForm(f){
	var ip1 = $('#ip1').val();
	var ip2 = $('#ip2').val();
	var ip3 = $('#ip3').val();
	var ip4 = $('#ip4').val();
	var ip5 = $('#ip5').val();
	var ip6 = $('#ip6').val();
	var ip7 = $('#ip7').val();
	var ip8 = $('#ip8').val();
	var requiredCheck = formRequiredCheck(f);
	if(requiredCheck == false){
		return false;
	}
	if(Number(ip1) > 255 || Number(ip2) > 255 || Number(ip3) > 255 || Number(ip4) > 255 || Number(ip5) > 255 || Number(ip6) > 255 || Number(ip7) > 255 || Number(ip8) > 255){
		alert("최대 255까지 입력할 수 있습니다.");
		return;
	}
	if(ip8 != '' && Number(ip4) > Number(ip8)){
		alert("시작아이피보다 큰 숫자를 입력하셔야 합니다.");
		$('#ip8').focus();
		return;
	}
	var s_ip = ip1 + "." + ip2 + "." + ip3 + "." ;
	var e_ip = '';
	if(!ip4){
		s_ip += "*";
	}else if(ip4 && !ip8){
		s_ip += ip4;
	}else{
		s_ip += ip4;
		e_ip += ip5 + "." + ip6 + "." + ip7 + "." + ip8;
	}
	$("#s_ip").val(s_ip);
	$("#e_ip").val(e_ip);
	var url = "";
	if($("#actionkey").val() == "regist") {
		url = "<c:url value='/boffice/sy/accessIp/accessIpRegAx.do' />";
	}else if($("#actionkey").val() == "modify") {
		url = "<c:url value='/boffice/sy/accessIp/accessIpModAx.do' />";
	}
	var ajaxParam = $("#saveFrm").serializeArray();
	ajaxActionMessage(url, ajaxParam, '', true);
	return false;
}

function copyInputVal(obj, input_name){
	$('#'+input_name).val(obj.value);
}
</script>
<!-- //페이지 전용 스타일/스크립트 -->

</head>
<body>

	<div class="section">
		<form:form commandName="AccessIpVo" name="AccessIpVo" method="post" class="searchListPage" onsubmit="return doSearch(this);">
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
				<caption>접속허용 IP대역목록</caption>
				<colgroup>
					<!-- <col class="w50"> -->
					<col class="w50">
					<col>
					<col class="w150">
					<!-- <col class="w100"> -->
					<!-- <col class="w100"> -->
					<!-- <col class="w100"> -->
					<col class="w120">
				</colgroup>
				<thead>
					<tr>
						<!-- <th scope="col"><input type="checkbox" name="checkAll" value="1"></th> -->
						<th scope="col">순번</th>
						<th scope="col">접속허용대역</th>
						<th scope="col">등록일</th>
						<!-- <th scope="col">등록아이디</th> -->
						<!-- <th scope="col">수정일</th> -->
						<!-- <th scope="col">수정아이디</th> -->
						<th scope="col">관리</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="result" items="${resultList}" varStatus="status">
					<tr data-idx="<c:out value="${result.ai_idx}"/>">
						<!-- <td><input type="checkbox" name="checkItem[]" value="1"></td> -->
						<td><c:out value="${(totCnt+1)-(status.count+(AccessIpVo.pageIndex-1)*AccessIpVo.recordCountPerPage)}" /></td>
						<td class="left">
							<c:out value="${result.s_ip}" />
							<c:if test="${not empty result.e_ip}">
								~ <c:out value="${result.e_ip}" />
							</c:if>
						</td>
						<td><c:out value="${result.reg_dt}" /></td>
						<%-- <td><c:out value="${result.reg_id}" /></td> --%>
						<%-- <td><c:out value="${result.mod_dt}" /></td> --%>
						<%-- <td><c:out value="${result.mod_id}" /></td> --%>
						<td>
							<a href="#" class="btn_inline btn_mod">수정</a>
							<a href="#" class="btn_inline btn_caption btn_del">삭제</a>
						</td>
					</tr>
					</c:forEach>
					<c:if test="${fn:length(resultList) == 0}">
					<tr>
						<td colspan="4" class="empty"><spring:message code="common.nodata.msg" /></td>
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
<div id="divAccessIpForm" class="modalWrap">
	<div class="modalTitle">
		<h3>접속허용대역 등록/수정</h3>
		<a href="#" class="btn_modalClose">모달팝업닫기</a>
	</div>
	<div class="modalContent">
		<form id="saveFrm" name="saveFrm" method="post" onsubmit="return doForm(this);">
		<input type="hidden" id="ai_idx" name="ai_idx" />
		<input type="hidden" id="actionkey" name="actionkey" />
		<input type="hidden" id="s_ip" name="s_ip" />
		<input type="hidden" id="e_ip" name="e_ip" />
		<div class="tableDesc">
			특정 IP 대역을 모두 허용할 경우 네번째 입력란에 공백으로 등록<br/>
		</div>
		<div class="tableBox">
			<table class="form">
				<caption>접속허용대역 입력폼</caption>
				<colgroup>
					<col class="w100">
					<col>
				</colgroup>
				<tbody>
					<tr>
						<th scope="row">접속허용대역</th>
						<td>
							<input type="text" id="ip1" value="" title="IP첫번째" maxlength="3" onkeyup="copyInputVal(this, 'ip5');" class="required w35 onlyNum"/> .
							<input type="text" id="ip2" value="" title="IP두번째" maxlength="3" onkeyup="copyInputVal(this, 'ip6');" class="required w35 onlyNum"/> .
							<input type="text" id="ip3" value="" title="IP세번째" maxlength="3" onkeyup="copyInputVal(this, 'ip7');" class="required w35 onlyNum"/> .
							<input type="text" id="ip4" value="" title="IP네번째" maxlength="3" class="w35 onlyNum"/> ~
							<input type="text" id="ip5" value="" title="IP다섯번째" maxlength="3" readonly="readonly" class="w35 onlyNum"/> .
							<input type="text" id="ip6" value="" title="IP여섯번째" maxlength="3" readonly="readonly" class="w35 onlyNum"/> .
							<input type="text" id="ip7" value="" title="IP일곱번째" maxlength="3" readonly="readonly" class="w35 onlyNum"/> .
							<input type="text" id="ip8" value="" title="IP여덟번째" maxlength="3" class="w35 onlyNum"/>
						</td>
					</tr>
					<tr>
						<th scope="row"><label for="note">비고</label></th>
						<td><textarea name="note" id="note" class="required w100p h150"></textarea></td>
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

</body>
</html>
