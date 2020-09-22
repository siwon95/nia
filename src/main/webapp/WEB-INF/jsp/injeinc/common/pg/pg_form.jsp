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
<title>Pg 등록/수정폼</title>
<script type="text/javascript">
var idchk = false;

//등록
function doPgReg() {
	//필수값 체크
	if (cm_check_empty($('input[id=cstMid]').attr("title"), $('input[id=cstMid]')) == false) {
		return;
	}

	if ($('input[id=cstMid]').val().trim().substring(0, 1) == 't') {
		alert("상점 ID는 t로 시작할수 없습니다.");
		return;
	}

	if ($("select[name=mertCode]").val() == "") {
		alert("상점메뉴는 필수입니다.");
		$("#mertCode").focus();
		return;
	}

	if (cm_check_empty($('input[id=pgAls]').attr("title"), $('input[id=pgAls]')) == false) {
		return;
	}

	if (cm_check_empty($('textarea[id=pgMemo]').attr("title"), $('textarea[id=pgMemo]')) == false) {
		return;
	}

	if (cm_check_empty($('input[id=pgDeptNm]').attr("title"), $('input[id=pgDeptNm]')) == false) {
		return;
	}

	if (cm_check_empty($('input[id=tel1]').attr("title"), $('input[id=tel1]')) == false) {
		return;
	}

	if (cm_check_empty($('input[id=tel2]').attr("title"), $('input[id=tel2]')) == false) {
		return;
	}

	if (cm_check_empty($('input[id=lgdMertkey]').attr("title"), $('input[id=lgdMertkey]')) == false) {
		return;
	}

	if (idchk == false) {
		alert("아이디 중복체크를 해주십시오");
		return;
	}

	var pgDeptTelNum = $("#PgVo input[name=tel1]").val() + "-" + $("#PgVo input[name=tel2]").val() + "-" + $("#PgVo input[name=tel3]").val();
	$("#pgDeptTelNum").val(pgDeptTelNum);

	if (confirm("등록하시겠습니까?")) {
		$("#PgVo").attr("action","<c:url value='/boffice/common/pg/Pg_Reg.do' />").submit();
	}

}

//수정
function doPgMod() {
	var id = $("input[name=cstMid]").val();
	var beforeid = $("input[name=beforecstmid]").val();

	if (cm_check_empty($('input[id=cstMid]').attr("title"), $('input[id=cstMid]')) == false) {
		return;
	}

	if ($('input[id=cstMid]').val().trim().substring(0, 1) == 't') {
		alert("상점 ID는 t로 시작할수 없습니다.");
		return;
	}

	if ($("select[name=mertCode]").val() == "") {
		alert("상점메뉴는 필수입니다.");
		$("#mertCode").focus();
		return;
	}

	if (cm_check_empty($('input[id=pgAls]').attr("title"), $('input[id=pgAls]')) == false) {
		return;
	}

	if (cm_check_empty($('textarea[id=pgMemo]').attr("title"), $('textarea[id=pgMemo]')) == false) {
		return;
	}

	if (cm_check_empty($('input[id=pgDeptNm]').attr("title"), $('input[id=pgDeptNm]')) == false) {
		return;
	}

	if (cm_check_empty($('input[id=tel1]').attr("title"), $('input[id=tel1]')) == false) {
		return;
	}

	if (cm_check_empty($('input[id=tel2]').attr("title"), $('input[id=tel2]')) == false) {
		return;
	}

	if (cm_check_empty($('input[id=lgdMertkey]').attr("title"), $('input[id=lgdMertkey]')) == false) {
		return;
	}

	if (id == beforeid) {
		idchk = true;
	}

	if (idchk == false) {
		alert("아이디 중복체크를 해주십시오");
		return;
	}

	var pgDeptTelNum = $("#PgVo input[name=tel1]").val() + "-" + $("#PgVo input[name=tel2]").val() + "-" + $("#PgVo input[name=tel3]").val();
	$("#pgDeptTelNum").val(pgDeptTelNum);

	if (confirm("수정하시겠습니까?")) {
		$("#PgVo").attr("action","<c:url value='/boffice/common/pg/Pg_Mod.do' />").submit();
	}

}

//목록(취소)
function doPgList() {
	$("#PgVo").attr("action","<c:url value='/boffice/common/pg/Pg_List.do' />").submit();
}

//중복확인
function doPgAx() {
	var id = $("input[name=cstMid]").val();

	if (!idchk) {

		$.ajax({
			type : "GET",
			url : "<c:url value='/boffice/common/pg/PgId_Ax.do' />",
			data : "cstMid=" + id,
			dataType : "json",
			success : function(obj) {
				if (obj.message == 'Y') {
					alert("사용가능한 아이디입니다.");
					idchk = true;
				} else {
					if ($("input[name=beforecstmid]").val() == id) {
						alert("사용가능한 아이디입니다.");
						idchk = true;
					} else {
						alert("이미사용중인 아이디입니다.");
						idchk = false;
					}
				}
			},
			error : function(xhr, status, error) {
				alert(status);
				idchk = false;
			}
		});
	}
}

//아이디 중복체크 리셋
function resetId() {
	idchk = false;
}

// 삭제
function doPgRmv(cstMid, pg_als) {
	if (confirm("삭제하시면 해당conf 파일도 삭제됩니다. 정말 삭제하시겠습니까?")) {
		$("#cstMid").val(cstMid);
		$("#pg_als").val(pg_als);
		$("#PgVo").attr("action","<c:url value='/boffice/common/pg/Pg_Rmv.do' />").submit();
	}
}
</script>
</head>
<body style="text-align: center; margin: 0 auto; display: inline; padding-top: 100px;">
<form:form commandName="PgVo" name="PgVo" method="post">
	<form:hidden path="pageIndex" />
	<form:hidden path="searchCondition" />
	<form:hidden path="searchKeyword" />
	<form:hidden path="type" />
	<form:hidden path="lgdAmount" />
	<form:hidden path="lgdOid" />
	<form:hidden path="lgdBuyer" />
	<form:hidden path="lgdProductInfo" />
	<form:hidden path="lgdbuyerEmail" />
	<form:hidden path="lgdCustomUsablePay" />
	<input type="hidden" name="beforecstmid" value="<c:out value="${PgVo.cstMid }"/>" />

	<!-- 컨텐츠 영역 -->
	<div id="contents">
		<table summary="Pg 등록/수정폼" class="write">
			<caption>Pg 등록/수정폼</caption>
			<colgroup>
				<col width="15%" />
				<col width="*" />
			</colgroup>
			<tbody>
				<tr>
					<th>*상점 ID</th>
					<td>
						<form:input path="cstMid" id="cstMid" onkeyup="resetId();" title="상점 ID" />&nbsp;
						<input type="button" value="중복확인" onclick="doPgAx();" />
						<c:if test="${fn:substring(PgVo.cstMid,0,1) ne 'T' && fn:substring(PgVo.cstMid,0,1) ne 't' && PgVo.type eq 'modify'}">
							<br /><br />
							<span style="color: #C61400;">* 해당 상점 ID는 현재 실제운영중인 ID입니다.</span>
						</c:if>
					</td>
				</tr>
				<tr>
					<th>*PG 상점</th>
					<td>
						<form:select path="mertCode" title="PG 상점">
							<form:option value="" label="선택하세요"></form:option>
							<form:option value="information" label="정보화교육"></form:option>
							<form:option value="womenclass" label="여성교실"></form:option>
							<form:options items="${resultList}" itemValue="siteCd" itemLabel="siteNm" />
						</form:select>
					</td>
				</tr>
				<tr>
					<th>*PG 별칭</th>
					<td><form:input path="pgAls" id="pgAls" size="150" title="PG 별칭" /></td>
				</tr>
				<tr>
					<th>*PG 설명</th>
					<td><form:textarea path="pgMemo" id="pgMemo" title="PG 설명" /></td>
				</tr>
				<tr>
					<th>*담당자 이름</th>
					<td><form:input path="pgDeptNm" id="pgDeptNm" title="담당자 이름" /></td>
				</tr>
				<tr>
					<th>*담당자 연락처</th>
					<td>
						<form:input path="tel1" maxlength="4" onkeypress="return showKeyCode(event);" title="담당자 연락처(앞자리)" /> -
						<form:input path="tel2" maxlength="4" onkeypress="return showKeyCode(event);" title="담당자 연락처(중간자리)" /> -
						<form:input path="tel3" maxlength="4" onkeypress="return showKeyCode(event);" title="담당자 연락처(끝자리)" />
						<form:hidden path="pgDeptTelNum" />
					</td>
				</tr>
				<tr>
					<th>*상점 MERT_KEY</th>
					<td><form:input path="lgdMertkey" id="lgdMertkey" size="150" title="상점 MERT_KEY" /></td>
				</tr>
			</tbody>
		</table>

		<!-- 버튼 -->
		<div class="btn_zone">
			<c:choose>
				<c:when test="${PgVo.type eq 'modify' }">
					<div class="left">
						<a href="javascript:doPgRmv('<c:out value="${PgVo.cstMid}"/>','<c:out value="${PgVo.pgAls}"/>');" class="btn1" title="삭제">삭제</a>
					</div>
					<a href="javascript:doPgMod();" class="btn1" title="수정">수정</a>
				</c:when>
				<c:otherwise>
					<a href="javascript:doPgReg();" class="btn1" title="등록">등록</a>
				</c:otherwise>
			</c:choose>
			<a href="javascript:doPgList();" class="btn2" title="취소">취소</a>
		</div>
		<!-- //버튼 -->

	</div>
	<!-- //컨텐츠 영역 -->
</form:form>
</body>
</html>