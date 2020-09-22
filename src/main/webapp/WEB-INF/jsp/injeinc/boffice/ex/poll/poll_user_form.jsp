<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="cmm" uri="http://cms.injeinc.co.kr/cmm"%>
<%-- ------------------------------------------------------------
- 제목 : 설문관리
- 파일명 : poll_user_form.jsp
- 최종수정일 : 2019-04-29
- 상태 : 1차완료
-------------------------------------------------------------- --%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>통합관리시스템</title>

<!-- 페이지 전용 스타일/스크립트 -->
<script>
function doPollUserReg() {

	<c:if test="${PollListVo.plAuthType eq 'A' || PollListVo.plAuthType eq 'L'}">
	if($("#puName").val() == "") {
		alert("참여자 이름을 입력하여 주십시오.");
		$("#puName").focus();
		return;
	}
	</c:if>
	<c:if test="${PollListVo.plAddrYn eq 'Y'}">
	if($("#puZip").val() == "" || $("#puAddr1").val() == "") {
		alert("주소를 입력하여 주십시오.");
		$("#puZip").focus();
		return;
	}
	</c:if>
	<c:if test="${PollListVo.plTelYn eq 'Y'}">
	if($("#puTel1").val() == "" || $("#puTel2").val() == "" || $("#puTel3").val() == "") {
		alert("전화번호를 입력하여 주십시오.");
		$("#puTel1").focus();
		return;
	}
	</c:if>
	<c:if test="${PollListVo.plHpYn eq 'Y'}">
	if($("#puHp1").val() == "" || $("#puHp2").val() == "" || $("#puHp3").val() == "") {
		alert("휴대폰번호를 입력하여 주십시오.");
		$("#puHp1").focus();
		return;
	}
	</c:if>
	<c:if test="${PollListVo.plEmailYn eq 'Y'}">
	if($("#puEmail").val() == "") {
		alert("이메일을 입력하여 주십시오.");
		$("#puHp1").focus();
		return;
	}
	</c:if>

	if(!checkItemList()) {
		alert("필수항목을 입력하여 주십시오.");
		return;
	}
	
	if($("#actionkey").val() == "regist") {
		$("#PollUserVo").attr("action", "/boffice/ex/poll/PollUserReg.do").submit();
	}else if($("#actionkey").val() == "modify") {
		$("#PollUserVo").attr("action", "/boffice/ex/poll/PollUserMod.do").submit();
	}
}

function checkItemList() {
<c:forEach var="questionInfo" items="${questionList }">
<c:if test="${questionInfo.pqCheck eq 'Y'}">
	<c:if test="${questionInfo.pqType eq 'radio'}">
	if($("input:radio[name='<c:out value="${questionInfo.pqIdx}"/>']:checked").length < 1) {
		$("input:radio[name='<c:out value="${questionInfo.pqIdx}"/>']").eq(0).focus();
		return false;
	}
	<c:if test="${questionInfo.pqEtc eq 'Y'}">
	if($("input:radio[name='<c:out value="${questionInfo.pqIdx}"/>']").eq(<c:out value="${questionInfo.pqItemCnt-1}"/>).is(":checked") 
		&& $("#<c:out value="${questionInfo.pqIdx}"/>_etc").val() == "") {
		$("#<c:out value="${questionInfo.pqIdx}"/>_etc").focus();
		return false;
	}else if($("input:radio[name='<c:out value="${questionInfo.pqIdx}"/>']").eq(<c:out value="${questionInfo.pqItemCnt-1}"/>).is(":not(:checked)") 
		&& $("#<c:out value="${questionInfo.pqIdx}"/>_etc").val() != "") {
		$("#<c:out value="${questionInfo.pqIdx}"/>_etc").val("");
	}
	</c:if>
	</c:if>
	<c:if test="${questionInfo.pqType eq 'checkbox'}">
	if($("input:checkbox[name='<c:out value="${questionInfo.pqIdx}"/>']:checked").length < 1) {
		$("input:checkbox[name='<c:out value="${questionInfo.pqIdx}"/>']").eq(0).focus();
		return false;
	}
	<c:if test="${questionInfo.pqEtc eq 'Y'}">
	if($("input:checkbox[name='<c:out value="${questionInfo.pqIdx}"/>']").eq(<c:out value="${questionInfo.pqItemCnt-1}"/>).is(":checked")
		&& $("#<c:out value="${questionInfo.pqIdx}"/>_etc").val() == "") {
		$("#<c:out value="${questionInfo.pqIdx}"/>_etc").focus();
		return false;
	}else if($("input:checkbox[name='<c:out value="${questionInfo.pqIdx}"/>']").eq(<c:out value="${questionInfo.pqItemCnt-1}"/>).is(":not(:checked)") 
		&& $("#<c:out value="${questionInfo.pqIdx}"/>_etc").val() != "") {
		$("#<c:out value="${questionInfo.pqIdx}"/>_etc").val("");
	}
	</c:if>
	</c:if>
	<c:if test="${questionInfo.pqType eq 'selectbox' || questionInfo.pqType eq 'text' || questionInfo.pqType eq 'textarea'}">
	if($("#<c:out value="${questionInfo.pqIdx}"/>").val() == "") {
		$("#<c:out value="${questionInfo.pqIdx}"/>").focus();
		return false;
	}
	</c:if>
</c:if>
</c:forEach>
	return true;
}

function doPollUserView() {
	$("#PollUserVo").attr("action", "<c:url value='/boffice/ex/poll/PollUserView.do' />").submit();
}

function doPollUserList() {
	$("#PollUserVo").attr("action", "<c:url value='/boffice/ex/poll/PollUserList.do' />").submit();
}

function doPollUserRmv() {
	if (!confirm("정말 삭제하시겠습니까?")) return;
	$("#PollUserVo").attr("action", "/boffice/ex/poll/PollUserRmv.do").submit();
}

function openAddrSearch() {
	var url = "/common/addrSearch/jusoPopup.jsp"
	var addr_pop = window.open(url,"pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
	addr_pop.focus();
}

function setAddrValue(addr1, addr2, zip) {
	$("#puZip").val(zip);
	$("#puAddr1").val(addr1);
	$("#puAddr2").val(addr2);
}

function mobileAuth() {
	
	var PAgree = open("<c:url value='/common/mobileAuth/mobile_auth_mgr.jsp' />", "CheckJN", "width=420, height=570, resizable=0, scrollbars=no, status=0, titlebar=0, toolbar=0, left=480, top=115");
	PAgree.focus();

}

function writeJN(name, di) {
	$("#puId").val("실명인증");
	$("#puName").val(name);
	$("#regDi").val(di);
}

function userIdAuth() {
	
	var UserIdAuthPop = open("<c:url value='/user/UserSearchPop.do' />", "UserIdAuth", "width=300, height=250, resizable=0, scrollbars=no, status=0, titlebar=0, toolbar=0, left=480, top=115");
	UserIdAuthPop.focus();
}

function writeUserInfo(id, name, email, zip, addr1, addr2, tel, hp, di) {
	$("#puId").val(id);
	$("#puName").val(name);
	$("#regDi").val(di);
	<c:if test="${PollListVo.plAddrYn eq 'Y'}">
	$("#puZip").val(zip);
	$("#puAddr1").val(addr1);
	$("#puAddr2").val(addr2);
	</c:if>
	<c:if test="${PollListVo.plTelYn eq 'Y'}">
	var telArr = tel.split("-");
	$("#puTel1").val(telArr[0]);
	$("#puTel2").val(telArr[1]);
	$("#puTel3").val(telArr[2]);
	</c:if>
	<c:if test="${PollListVo.plHpYn eq 'Y'}">
	var hpArr = hp.split("-");
	$("#puHp1").val(hpArr[0]);
	$("#puHp2").val(hpArr[1]);
	$("#puHp3").val(hpArr[2]);
	</c:if>
	<c:if test="${PollListVo.plEmailYn eq 'Y'}">
	var emailArr = email.split("@");
	$("#puEmail1").val(emailArr[0]);
	$("#puEmail2").val(emailArr[1]);
	$("#puEmail3").val("");
	</c:if>
}

$(document).ready(function() {
	<c:if test="${PollListFVo.plEmailYn eq 'Y'}">
	
	cm_combo_email("#puEmail3", "EMAIL_CD");
	
	$("#puEmail3").change(function() {
		if($(this).val() == "") {
			$("#puEmail2").val("");
			$("#puEmail2").attr("readonly", false);
		}else {
			$("#puEmail2").val($(this).val());
			$("#puEmail2").attr("readonly", true);
		}
	});
	
	</c:if>
	
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
</script>
<!-- //페이지 전용 스타일/스크립트 -->

</head>
<body>
	
<form:form commandName="PollUserVo" name="PollUserVo" method="post">
<form:hidden path="pageIndex" />
<form:hidden path="searchCdIdx" />
<form:hidden path="searchUse" />
<form:hidden path="plIdx" />
<form:hidden path="pageSubIndex" />
<form:hidden path="searchSubCondition" />
<form:hidden path="searchSubKeyword" />
<form:hidden path="actionkey" />
<form:hidden path="puIdx" />

	<div class="section">
		<div class="tableBox">
			<table class="form">
				<caption>설문조사 정보 및 참여자 기본정보 입력/수정</caption>
				<colgroup>
					<col class="w15p">
					<col class="w35p">
					<col class="w15p">
					<col class="w35p">
				</colgroup>
				<tbody>
					<tr>
						<th scope="row">제목</th>
						<td colspan="3"><c:out value="${PollListVo.plTitle}" /></td>
					</tr>
					<tr>
						<th scope="row">담당부서</th>
						<td><c:out value="${PollListVo.cdSubject}" /></td>
						<th scope="row">사이트</th>
						<td><c:out value="${PollListVo.siteNm}" /></td>
					</tr>
					<tr>
						<th scope="row">담당자</th>
						<td><c:out value="${PollListVo.plManagerName}" /></td>
						<th scope="row">담당자 연락처</th>
						<td><c:out value="${PollListVo.plManagerTel}" /></td>
					</tr>
					<tr>
						<th scope="row">기간</th>
						<td colspan="3"><c:out value="${fn:substring(PollListVo.plStartDate, 0, 16)}" /> ~ <c:out value="${fn:substring(PollListVo.plEndDate, 0, 16)}" /></td>
					</tr>
					<tr>
						<th scope="row">안내문</th>
						<td colspan="3"><c:out value="${cmm:textAreaToHtml(PollListVo.plGuide)}"/></td>
					</tr>
					<c:if test="${PollListVo.plAuthType eq 'A' || PollListVo.plAuthType eq 'L'}">
					<tr>
						<th scope="row"><label for="puName">이름</label></th>
						<td>
							<form:input path="puName" size="10" readonly="true" class="w100"/>
							<c:if test="${PollUserVo.actionkey eq 'regist'}">
								<c:if test="${PollListVo.plAuthType eq 'A'}">
									<a href="javascript:mobileAuth();" class="btn_inline">모바일인증</a>
								</c:if>
								<a href="javascript:userIdAuth();" class="btn_inline">아이디인증</a>
								<form:hidden path="regDi" />
							</c:if>
						</td>
						<th scope="row"><label for="puId">아이디</label></th>
						<td><form:input path="puId" size="10" readonly="true" class="w100" /></td>
					</tr>
					</c:if>
					<c:if test="${PollListVo.plAddrYn eq 'Y'}">
					<tr>
						<th scope="row" rowspan="3"><label for="puZip">주소</label></th>
						<td colspan="3">
							<form:input path="puZip" size="8" maxlength="7" title="우편번호" readonly="true" /> 
							<a href="javascript:openAddrSearch();" class="btn_inline">우편번호검색</a>
						</td>
					</tr>
					<tr>
						<td colspan="3"><label for="puAddr1" class="hidden">기본주소</label><form:input path="puAddr1" size="50" title="우편번호 검색 후 자동입력 기본주소" readonly="true" /></td>
					</tr>
					<tr>
						<td colspan="3"><label for="puAddr2" class="hidden">상세주소</label><form:input path="puAddr2" size="50" title="상세주소" /></td>
					</tr>
					</c:if>
					<c:if test="${PollListVo.plTelYn eq 'Y'}">
					<tr>
						<th scope="row"><label for="puTel1">전화번호</label></th>
						<td colspan="3">
							<form:input path="puTel1" size="4" maxlength="4" class="onlynum" title="전화번호앞자리" /> - 
							<label for="puTel2" class="hidden">중간자리</label>
							<form:input path="puTel2" size="4" maxlength="4" class="onlynum" title="전화번호중간자리" /> -
							<label for="puTel3" class="hidden">끝자리</label> 
							<form:input path="puTel3" size="4" maxlength="4" class="onlynum" title="전화번호끝자리" />
						</td>
					</tr>
					</c:if>
					<c:if test="${PollListVo.plHpYn eq 'Y'}">
					<tr>
						<th scope="row"><label for="puHp1">휴대폰번호</label></th>
						<td colspan="3">
							<form:input path="puHp1" size="4" maxlength="4" class="onlynum" title="휴대폰번호앞자리" /> - 
							<label for="puHp2" class="hidden">중간자리</label>
							<form:input path="puHp2" size="4" maxlength="4" class="onlynum" title="휴대폰번호중간자리" /> -
							<label for="puHp3" class="hidden">끝자리</label> 
							<form:input path="puHp3" size="4" maxlength="4" class="onlynum" title="휴대폰번호끝자리" />
						</td>
					</tr>
					</c:if>
					<c:if test="${PollListVo.plEmailYn eq 'Y'}">
					<tr>
						<th scope="row"><label for="puEmail1">이메일</label></th>
						<td colspan="3">
							<form:input path="puEmail1" size="14" title="이메일" /> @ 
							<label for="puEmail3" class="hidden">이메일선택</label>
							<select id="puEmail3" title="puEmail3">
								<option value="" label="--직접입력--" />
							</select>
							<label for="puEmail2" class="hidden">도메인</label>
							<form:input path="puEmail2" size="14" title="도메인" />
						</td>
					</tr>
					</c:if>
					<tr>
						<th scope="row">설문내용</th>
						<td colspan="3">
							<table class="list useBtn">
								<caption>설문내용 목록</caption>
								<colgroup>
									<col class="w50">
									<col>
									<col>
								</colgroup>
								<thead>
									<tr>
										<th scope="col">순번</th>
										<th scope="col">질문내용</th>
										<th scope="col">답변목록</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach var="questionInfo" items="${questionList}" varStatus="status">
									<c:if test="${PollUserVo.actionkey eq 'modify'}">
										<c:set var="answerInfo" value="${answerList[status.index]}" />
										<c:set var="paAnswer" value="${answerInfo.paAnswer}" />
										<c:set var="paText" value="${answerInfo.paText}" />
									</c:if>
									<tr>
										<td><c:out value="${questionInfo.pqSort}" /></td>
										<td class="left">
											<c:if test="${questionInfo.pqCheck eq 'Y'}">
												<span class="required">*</span>
											</c:if>
											<b><c:out value="${questionInfo.pqTitle}" /></b><br>
											<c:if test="${!empty questionInfo.pqDetail}">
												<c:out value="${cmm:textAreaToHtml(questionInfo.pqDetail)}" />
											</c:if>
										</td>
										<td class="left">
											<c:if test="${questionInfo.pqType eq 'radio'}">
												<c:set var="pqItemList" value="${cmm:split(questionInfo.pqItemList, '｜', questionInfo.pqItemCnt)}" />
												<ul>
												<c:forEach begin="0" varStatus="status2"
													end="${questionInfo.pqItemCnt-1}">
													<li <c:if test="${status2.last && questionInfo.pqEtc eq 'Y'}">class="etc"</c:if>>
														<input type="radio" id="<c:out value="${questionInfo.pqIdx}"/>_<c:out value="${status2.count}"/>" name="<c:out value="${questionInfo.pqIdx}"/>" value="<c:out value="${status2.count}"/>" <c:if test="${paAnswer == status2.count}">checked="checked"</c:if> />
														<label for="<c:out value="${questionInfo.pqIdx}"/>_<c:out value="${status2.count}"/>"><c:out value="${fn:length(pqItemList) > status2.index ? pqItemList[status2.index]:''}" /></label>
														<c:if test="${status2.last && questionInfo.pqEtc eq 'Y'}">
															<input type="text" id="<c:out value="${questionInfo.pqIdx}"/>_etc" name="<c:out value="${questionInfo.pqIdx}"/>_etc" class="w30" title="기타의견 입력" value="<c:out value="${paText}"/>" />
														</c:if>
													</li>
												</c:forEach>
												</ul>
											</c:if>
											<c:if test="${questionInfo.pqType eq 'selectbox'}">
												<label for="<c:out value="${questionInfo.pqIdx}"/>" class="hidden">선택</label>
												<select id="<c:out value="${questionInfo.pqIdx}"/>" name="<c:out value="${questionInfo.pqIdx}"/>" class="select2">
													<option value="">선택하세요</option>
													<c:set var="pqItemList" value="${cmm:split(questionInfo.pqItemList, '｜', questionInfo.pqItemCnt)}" />
													<c:forEach begin="0" varStatus="status2" end="${questionInfo.pqItemCnt-1}">
														<option value="<c:out value="${status2.count}"/>" <c:if test="${paAnswer == status2.count}">selected="selected"</c:if>><c:out value="${fn:length(pqItemList) > 0 ? pqItemList[status2.index]:''}"/></option>
													</c:forEach>
												</select>
											</c:if>
											<c:if test="${questionInfo.pqType eq 'checkbox'}">
												<c:set var="pqItemList" value="${cmm:split(questionInfo.pqItemList, '｜', questionInfo.pqItemCnt)}" />
												<ul>
												<c:forEach begin="0" varStatus="status2" end="${questionInfo.pqItemCnt-1}">
													<li <c:if test="${status2.last && questionInfo.pqEtc eq 'Y'}">class="etc"</c:if>>
														<input type="checkbox" id="<c:out value="${questionInfo.pqIdx}"/>_<c:out value="${cmm:changeNumberAlphabat(status2.count)}"/>" name="<c:out value="${questionInfo.pqIdx}"/>" value="<c:out value="${cmm:changeNumberAlphabat(status2.count)}"/>" <c:if test="${fn:indexOf(paAnswer, cmm:changeNumberAlphabat(status2.count)) > -1}">checked="checked"</c:if> />
														<label for="<c:out value="${questionInfo.pqIdx}"/>_<c:out value="${cmm:changeNumberAlphabat(status2.count)}"/>"><c:out value="${fn:length(pqItemList) > 0 ? pqItemList[status2.index]:''}"/></label>
														<c:if test="${status2.last && questionInfo.pqEtc eq 'Y'}">
															<input type="text" id="<c:out value="${questionInfo.pqIdx}"/>_etc" name="<c:out value="${questionInfo.pqIdx}"/>_etc" value="<c:out value="${paText}"/>" class="w30" title="기타의견 입력" />
														</c:if>
													</li>
												</c:forEach>
												</ul>
											</c:if>
											<c:if test="${questionInfo.pqType eq 'text'}">
												<label for="<c:out value="${questionInfov.pqIdx}"/>" class="hidden"><c:out value="${paText}"/></label>
												<input type="text" id="<c:out value="${questionInfov.pqIdx}"/>" name="<c:out value="${questionInfo.pqIdx}"/>" value="<c:out value="${paText}"/>" class="w95" />
											</c:if>
											<c:if test="${questionInfo.pqType eq 'textarea'}">
												<label for="<c:out value="${questionInfov.pqIdx}"/>" class="hidden"><c:out value="${paText}"/></label>
												<textarea id="<c:out value="${questionInfo.pqIdx}"/>" name="<c:out value="${questionInfo.pqIdx}"/>" rows="5" class="w95"><c:out value="${paText}"/></textarea>
											</c:if>
										</td>
									</tr>
								</c:forEach>
								<c:if test="${fn:length(questionList) eq 0}">
								<tr>
									<td colspan="3">질문이 없습니다.</td>
								</tr>
								</c:if>
								</tbody>
							</table>
							<!-- <div class="poll-wrap">
								<c:forEach var="questionInfo" items="${questionList }" varStatus="status">
									<c:if test="${PollUserVo.actionkey eq 'modify'}">
										<c:set var="answerInfo" value="${answerList[status.index]}" />
										<c:set var="paAnswer" value="${answerInfo.paAnswer}" />
										<c:set var="paText" value="${answerInfo.paText}" />
									</c:if>
									<c:if test="${PollUserVo.actionkey eq 'regist'}">
										<c:set var="paAnswer" value="" />
										<c:set var="paText" value="" />
									</c:if>
									<c:if test="${questionInfo.pqType eq 'title'}">
										<h4><c:out value="${questionInfo.pqTitle}" /></h4>
										<c:if test="${!empty questionInfo.pqDetail}">
											<p><c:out value="${cmm:textAreaToHtml(questionInfo.pqDetail)}" /></p>
										</c:if>
									</c:if>
									<c:if test="${questionInfo.pqType ne 'title'}">
										<dl>
											<dt>
												<c:if test="${questionInfo.pqCheck eq 'Y'}">
													<span class="required">*</span>
												</c:if>
												<c:out value="${questionInfo.pqTitle}" />
											</dt>
											<c:if test="${!empty questionInfo.pqDetail}">
												<dd class="guide">
													<c:out value="${cmm:textAreaToHtml(questionInfo.pqDetail)}" />
												</dd>
											</c:if>
											<dd <c:if test="${questionInfo.pqItemDirection eq 'C'}">class="type-col"</c:if>>
												<c:if test="${questionInfo.pqType eq 'radio'}">
													<c:set var="pqItemList" value="${cmm:split(questionInfo.pqItemList, '｜', questionInfo.pqItemCnt)}" />
													<c:forEach begin="0" varStatus="status2"
														end="${questionInfo.pqItemCnt-1}">
														<p <c:if test="${status2.last && questionInfo.pqEtc eq 'Y'}">class="etc"</c:if>>
															<input type="radio" id="<c:out value="${questionInfo.pqIdx}"/>_<c:out value="${status2.count}"/>" name="<c:out value="${questionInfo.pqIdx}"/>" value="<c:out value="${status2.count}"/>" <c:if test="${paAnswer == status2.count}">checked="checked"</c:if> />
															<label for="<c:out value="${questionInfo.pqIdx}"/>_<c:out value="${status2.count}"/>"><c:out value="${fn:length(pqItemList) > status2.index ? pqItemList[status2.index]:''}" /></label>
															<c:if test="${status2.last && questionInfo.pqEtc eq 'Y'}">
																<input type="text" id="<c:out value="${questionInfo.pqIdx}"/>_etc" name="<c:out value="${questionInfo.pqIdx}"/>_etc" class="w30" title="기타의견 입력" value="<c:out value="${paText}"/>" />
															</c:if>
														</p>
													</c:forEach>
												</c:if>
												<c:if test="${questionInfo.pqType eq 'selectbox'}">
													<label for="<c:out value="${questionInfo.pqIdx}"/>" class="hidden">선택</label>
													<select id="<c:out value="${questionInfo.pqIdx}"/>" name="<c:out value="${questionInfo.pqIdx}"/>" class="select2">
														<option value="">선택하세요</option>
														<c:set var="pqItemList" value="${cmm:split(questionInfo.pqItemList, '｜', questionInfo.pqItemCnt)}" />
														<c:forEach begin="0" varStatus="status2" end="${questionInfo.pqItemCnt-1}">
															<option value="<c:out value="${status2.count}"/>" <c:if test="${paAnswer == status2.count}">selected="selected"</c:if>><c:out value="${fn:length(pqItemList) > 0 ? pqItemList[status2.index]:''}"/></option>
														</c:forEach>
													</select>
												</c:if>
												<c:if test="${questionInfo.pqType eq 'checkbox'}">
													<c:set var="pqItemList" value="${cmm:split(questionInfo.pqItemList, '｜', questionInfo.pqItemCnt)}" />
													<c:forEach begin="0" varStatus="status2" end="${questionInfo.pqItemCnt-1}">
														<p <c:if test="${status2.last && questionInfo.pqEtc eq 'Y'}">class="etc"</c:if>>
															<input type="checkbox" id="<c:out value="${questionInfo.pqIdx}"/>_<c:out value="${cmm:changeNumberAlphabat(status2.count)}"/>" name="<c:out value="${questionInfo.pqIdx}"/>" value="<c:out value="${cmm:changeNumberAlphabat(status2.count)}"/>" <c:if test="${fn:indexOf(paAnswer, cmm:changeNumberAlphabat(status2.count)) > -1}">checked="checked"</c:if> />
															<label for="<c:out value="${questionInfo.pqIdx}"/>_<c:out value="${cmm:changeNumberAlphabat(status2.count)}"/>"><c:out value="${fn:length(pqItemList) > 0 ? pqItemList[status2.index]:''}"/></label>
															<c:if test="${status2.last && questionInfo.pqEtc eq 'Y'}">
																<input type="text" id="<c:out value="${questionInfo.pqIdx}"/>_etc" name="<c:out value="${questionInfo.pqIdx}"/>_etc" value="<c:out value="${paText}"/>" class="w30" title="기타의견 입력" />
															</c:if>
														</p>
													</c:forEach>
												</c:if>
												<c:if test="${questionInfo.pqType eq 'text'}">
													<label for="<c:out value="${questionInfov.pqIdx}"/>" class="hidden"><c:out value="${paText}"/></label>
													<input type="text" id="<c:out value="${questionInfov.pqIdx}"/>" name="<c:out value="${questionInfo.pqIdx}"/>" value="<c:out value="${paText}"/>" class="w95" />
												</c:if>
												<c:if test="${questionInfo.pqType eq 'textarea'}">
													<label for="<c:out value="${questionInfov.pqIdx}"/>" class="hidden"><c:out value="${paText}"/></label>
													<textarea id="<c:out value="${questionInfo.pqIdx}"/>" name="<c:out value="${questionInfo.pqIdx}"/>" rows="5" class="w95"><c:out value="${paText}"/></textarea>
												</c:if>
											</dd>
										</dl>
									</c:if>
								</c:forEach>
							</div>  -->
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="btnArea">
			<a href="javascript:doPollUserReg();" class="btn_inline on"><c:out value="${PollUserVo.actionkey eq 'regist' ? '입력':'수정'}"/></a>
			<c:if test="${PollUserVo.actionkey eq 'modify'}">
				<a href="javascript:doPollUserRmv();" class="btn_inline btn_caption">삭제</a>
				<a href="javascript:doPollUserView();" class="btn_inline">보기</a>
			</c:if>
			<a href="javascript:doPollUserList();" class="btn_inline">참여자 목록</a>
		</div>
	</div>
	
</form:form>
</body>
</html>