<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%-- ------------------------------------------------------------
- 제목 : 운영자관리
- 파일명 : mgr_list.jsp
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
	
	//버튼이벤트
    $(".btn_add").click(function(e){
    	e.preventDefault();
		doMgrListFormAx('');
    });
    $(".btn_mod").click(function(e){
    	e.preventDefault();
		var trIdx = $(this).parents("tr").eq(0).attr("data-idx");
		doMgrListFormAx(trIdx);
    });
    $(".btn_del").click(function(e){
    	e.preventDefault();
		if(!confirm("<spring:message code="common.delete.msg" />")){
			return;
		}
		var trIdx = $(this).parents("tr").eq(0).attr("data-idx");
		var ajaxParam = {"mgrIdx":trIdx};
		ajaxActionMessage("<c:url value='/boffice/sy/mgr/MgrListRmvAx.do'/>", ajaxParam, '', true);
    });
    $(".btn_idCheck").click(function(e){
    	e.preventDefault();
    	if($("#mgrId").val() == "") {
    		alert("아이디를 입력하여 주십시오.");
    		$("#mgrId").focus();
    		return;
    	}
    	
		var ajaxParam = {"mgrId":$("#mgrId").val()};
    	var ajaxOption = {};
    	ajaxOption.success = function(data){
			alert(data.message);
			if(data.result){
				$("#idCheckYn").val("Y");
			}
    	};
    	ajaxAction("<c:url value='/boffice/sy/mgr/MgrListCheckIdAx.do'/>", ajaxParam, ajaxOption);
    });
	
	//전체선택
    $("input[name=checkAll]").click(function() {
		if($(this).is(":checked"))
			$("input[name^=checkItem]").prop("checked", true);
		else
			$("input[name^=checkItem]").prop("checked", false);
    });
	
	//권한선택
	$("select[name='roleIdx']").change(function(){
		var trIdx = $(this).parents("tr").eq(0).attr("data-idx");
		var ajaxParam = {"mgrIdx":trIdx, "type":"roleChange" ,"value":$(this).val()};
		ajaxActionMessage("<c:url value='/boffice/sy/mgr/MgrListModEtcInfoAx.do'/>", ajaxParam, '', false);
	});
	
	//대표, 사용여부
	$("input[name=basic],input[name=use]").on("click", function() {
		var type = $(this).attr("name");
		var mgrIdx = $(this).val();
		var value = "N";
		if($(this).is(":checked")) {
			value = "Y";
		}
		var ajaxParam = {"mgrIdx":mgrIdx, "type":type, "value":value};
		ajaxActionMessage("<c:url value='/boffice/sy/mgr/MgrListModEtcInfoAx.do'/>", ajaxParam, '', true);
	});
	
	//중복확인초기화
	$("#mgrId").click(function() {
		if($("#actionkey").val() == "regist" && $("#idCheckYn").val() == "Y") {
			$("#idCheckYn").val("N");
		}
	});
});

function doMgrListFormAx(mgrIdx) {
	$.ajax({
		type: "POST",
		url: "<c:url value='/boffice/sy/mgr/MgrListFormAx.do'/>",
		data : {"mgrIdx":mgrIdx},
		dataType : "json",
		success:function(data) {
			if(data.result) {
				var resultVo= data.resultVo;
				$("#actionkey").val(resultVo.actionkey);
				if(resultVo.actionkey == "regist") {
					$("#idCheckYn").val("N");
					$("#mgrId").prop("readonly", false);
				}else{
					$("#idCheckYn").val("Y");
					$("#mgrId").prop("readonly", true);
				}
				$("#mgrIdx").val(resultVo.mgrIdx);
				$("#mgrId").val(resultVo.mgrId);
				$("#mgrPw").val(resultVo.mgrPw);
				$("#mgrPw2").val(resultVo.mgrPw);
				$("#mgrName").val(resultVo.mgrName);
				$("#mgrEmail").val(resultVo.mgrEmail);
				$("#permCd").val(resultVo.permCd);
				$("#deptCd").val(resultVo.deptCd);
				$("input[name='mgrSiteCd']").prop('checked',false);
				var arrSiteCd = (resultVo.mgrSiteCd+",empty").split(",");
				for(i=0;i<arrSiteCd.length-1;i++){
					$("input[name='mgrSiteCd']:checkbox[value='"+arrSiteCd[i]+"']").prop('checked',true);
				}
				$("input[name='basicYn']:radio[value='"+resultVo.basicYn+"']").prop('checked', true);
				$("input[name='mgrUse']:radio[value='"+resultVo.mgrUse+"']").prop('checked', true);
				modalOpen("#divMgrListForm");
			}else{
				alert(data.message);
			}
		},
        error: function(response){
            //ajaxError(response);
        }
	});
}

function doForm(f){
	var requiredCheck = formRequiredCheck(f);
	if(requiredCheck == false){
		return false;
	}
	
	if($("#idCheckYn").val() == "N") {
		alert("아이디 중복검사를 해 주십시오");
		return false;
	}
	if($("#mgrPw").val() != $("#mgrPw2").val()) {
		alert("비밀번호를 확인하여 주십시오.");
		$("#mgrPw2").focus();
		return false;
	}
	
	var url = "";
	if($("#actionkey").val() == "regist") {
		url = "<c:url value='/boffice/sy/mgr/MgrListRegAx.do' />";
	}else if($("#actionkey").val() == "modify") {
		url = "<c:url value='/boffice/sy/mgr/MgrListModAx.do' />";
	}
	var ajaxParam = $(f).serialize();
	ajaxActionMessage(url, ajaxParam, "", true);
	return false;
}
</script>
<!-- //페이지 전용 스타일/스크립트 -->

</head>
<body>
	<div class="section">
		<form:form commandName="MgrListVo" name="MgrListVo" method="post" class="searchListPage" onsubmit="return doSearch(this);">
		<form:hidden path="pageIndex" />
		<div class="search">
			<table>
				<caption>검색테이블</caption>
				<colgroup>
					<col class="w80">
					<col>
					<col class="w80">
					<col>
					<col class="w80">
					<col>
				</colgroup>
				<tbody>
					<tr>
						<th scope="row"><label for="searchPermCd">권한선택</label></th>
						<td>
							<form:select path="searchPermCd" class="w150">
								<form:option value="" label="선택해주세요" />
								<c:forEach var="codeInfo" items="${codeList }">
								<option value="<c:out value="${codeInfo.code}"/>" <c:if test="${MgrListVo.searchPermCd eq codeInfo.code}">selected="selected"</c:if>><c:out value="${codeInfo.codeName}"/></option>
								</c:forEach>
							</form:select>
						</td>
						<th scope="row"><label for="searchDeptCd">부서선택</label></th>
						<td>
							<form:select path="searchDeptCd" class="w150">
								<form:option value="" label="선택해주세요" />
								<c:forEach var="departInfo" items="${departList }" >
									<option value="<c:out value="${departInfo.cdCode}"/>" <c:if test="${MgrListVo.searchDeptCd eq departInfo.cdCode}">selected="selected"</c:if>>
										<c:forEach begin="2" end="${departInfo.level }" step="1">
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</c:forEach>
										<c:out value="${departInfo.cdSubject}"/>
									</option>
								</c:forEach>
							</form:select>
						</td>
						<th scope="row"><label for="searchBasic">대표유무</label></th>
						<td>
							<form:select path="searchBasic">
								<form:option value="" label="전체" />
								<form:option value="Y" label="대표아이디" />
								<form:option value="N" label="일반" />
							</form:select>
						</td>
					</tr>
					<tr>
						<th scope="row"><label for="searchUse">사용유무</label></th>
						<td>
							<form:select path="searchUse">
								<form:option value="" label="전체" />
								<form:option value="Y" label="사용" />
								<form:option value="N" label="미사용" />
							</form:select>
						</td>
						<th scope="row"><label for="searchCondition">검색</label></th>
						<td colspan="3">
							<form:select path="searchCondition">
								<form:option value="1" label="아이디" />
								<form:option value="2" label="이름" />
								<form:option value="3" label="이메일" />
							</form:select>
							<label for="searchKeyword" class="hidden">검색어</label>
							<form:input path="searchKeyword" class="w200" />
						</td>
					</tr>
				</tbody>
			</table>
			<input type="submit" value="검색" class="btn_inline btn_search">
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
				<caption>운영자 목록</caption>
				<colgroup>
					<%-- <col class="w50"> --%>
					<col class="w50">
					<col class="w80">
					<col class="w80">
					<col class="w80">
					<col class="w80">
					<col class="w150">
					<col>
					<col class="w70">
					<col class="w70">
					<col class="w120">
				</colgroup>
				<thead>
					<tr>
						<!-- <th scope="col"><input type="checkbox" name="checkAll" value="1"></th> -->
						<th scope="col">순번</th>
						<th scope="col">등급</th>
						<th scope="col">권한</th>
						<th scope="col">아이디</th>
						<th scope="col">이름</th>
						<th scope="col">부서정보</th>
						<th scope="col">이메일</th>
						<th scope="col">대표</th>
						<th scope="col">사용</th>
						<th scope="col">관리</th>
					</tr>
				</thead>
				<tbody>
					<c:set var="n" value="${resultCnt - (paginationInfo.currentPageNo-1)*paginationInfo.recordCountPerPage }"/>
					<c:forEach var="result" items="${resultList}" varStatus="status">
					<tr data-idx="<c:out value="${result.mgrIdx}"/>">
						<!-- <td><input type="checkbox" name="checkItem[]" value="1"></td> -->
						<td><c:out value="${n - status.index}" /></td>
						<td><c:out value="${result.permNm}" /></td>
						<td>
							<c:if test="${result.permCd eq '05010000'}">
								모든권한
							</c:if>
							<c:if test="${result.permCd eq '05020000'}">
								<select name="roleIdx">
									<option value="">권한선택</option>
									<c:forEach var="role" items="${roleList}" varStatus="status">
										<option value="<c:out value="${role.roleIdx}"/>" <c:if test="${result.roleIdx eq role.roleIdx}">selected</c:if>><c:out value="${role.roleName}"/></option>
									</c:forEach>
								</select>
							</c:if>
						</td>
						<td><c:out value="${result.mgrId}" /></td>
						<%-- <td><c:out value="${result.mgrPw}" /></td> --%>
						<td><c:out value="${result.mgrName}" /></td>
						<td><c:out value="${result.deptNm}" /></td>
						<td class="left"><c:out value="${result.mgrEmail}" /></td>
						<td><input type="checkbox" id="check<c:out value="${result.mgrIdx}"/>_basic" name="basic" value="<c:out value="${result.mgrIdx}"/>" class="useToggle" <c:if test="${result.basicYn eq 'Y'}">checked="checked"</c:if> /><label for="check<c:out value="${result.mgrIdx}"/>_basic">사용</label></td>
						<td><input type="checkbox" id="check<c:out value="${result.mgrIdx}"/>_use" name="use" value="<c:out value="${result.mgrIdx}"/>" class="useToggle" <c:if test="${result.mgrUse eq 'Y'}">checked="checked"</c:if> /><label for="check<c:out value="${result.mgrIdx}"/>_use">사용</label></td>
						<td>
							<a href="#" class="btn_inline btn_mod">수정</a>
							<a href="#" class="btn_inline btn_caption btn_del">삭제</a>
						</td>
					</tr>
					</c:forEach>
					<c:if test="${fn:length(resultList) == 0}">
					<tr>
						<td colspan="10" class="empty"><spring:message code="common.nodata.msg" /></td>
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
<div id="divMgrListForm" class="modalWrap">
	<div class="modalTitle">
		<h3>운영자 등록/수정</h3>
		<a href="#" class="btn_modalClose">모달팝업닫기</a>
	</div>
	<div class="modalContent">
		<form id="saveFrm" name="saveFrm" method="post" onsubmit="return doForm(this);">
		<input type="hidden" id="mgrIdx" name="mgrIdx" />
		<input type="hidden" id="actionkey" name="actionkey" />
		<input type="hidden" id="idCheckYn" name="idCheckYn" />
		<div class="tableBox">
			<table class="form">
			<caption>관리자 입력/수정</caption>
			<colgroup>
				<col class="w100">
				<col>
			</colgroup>
			<tbody>
				<tr>
					<th scope="row"><label for="mgrId">아이디</label></th>
					<td>
						<input type="text" id="mgrId" name="mgrId" class="required w150">
						<a href="#" class="btn_inline btn_idCheck">중복검사</a>
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="mgrPw">비밀번호</label></th>
					<td><input type="password" id="mgrPw" name="mgrPw" class="required w150"></td>
				</tr>
				<tr>
					<th scope="row"><label for="mgrPw2">비밀번호확인</label></th>
					<td><input type="password" id="mgrPw2" name="mgrPw2" class="required w150"></td>
				</tr>
				<tr>
					<th scope="row"><label for="mgrName">이름</label></th>
					<td><input type="text" id="mgrName" name="mgrName" class="required w150"></td>
				</tr>
				<tr>
					<th scope="row"><label for="mgrEmail">이메일</label></th>
					<td><input type="text" id="mgrEmail" name="mgrEmail" class="w100p"></td>
				</tr>
				<tr>
					<th scope="row"><label for="permCd">권한선택</label></th>
					<td>
						<select id="permCd" name="permCd" class="w100p">
							<option value="">권한을 선택하세요</option>
							<c:forEach var="codeInfo" items="${codeList }">
							<option value="<c:out value="${codeInfo.code}"/>"><c:out value="${codeInfo.codeName}"/></option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="deptCd">부서선택</label></th>
					<td>
						<select id="deptCd" name="deptCd" class="required w100p">
							<option value="">부서전체보기</option>
							<c:forEach var="departInfo" items="${departList }">
								<option value="<c:out value="${departInfo.cdIdx}"/>" >
									<c:forEach begin="2" end="${departInfo.level }" step="1">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</c:forEach>
									<c:out value="${departInfo.cdSubject}"/>
								</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr style="display:none">
					<th scope="row"><label>관리사이트</label></th>
					<td>
						<c:forEach var="siteList" items="${siteList}" varStatus="status">
							<span>
							<input type="checkbox" name="mgrSiteCd" value="<c:out value="${siteList.siteCd}"/>"><c:out value="${siteList.siteNm}"/>
							</span>
							<c:if test="${status.index!=0 && (status.index+1)%4==0}"><br/></c:if>
						</c:forEach>
						<br/>
						<c:forEach var="subSiteList" items="${subSiteList}" varStatus="status">
							<input type="checkbox" name="mgrSiteCd" value="<c:out value="${subSiteList.code}"/>"><c:out value="${subSiteList.codeName}"/>
							<c:if test="${status.index!=0 && (status.index+1)%4==0}"><br/></c:if>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="basicYn1">대표 유무</label></th>
					<td>
						<input type="radio" id="basicYn1" name="basicYn" value="Y" /><label for="basicYn1">사용</label>&nbsp;&nbsp;
						<input type="radio" id="basicYn2" name="basicYn" value="N" /><label for="basicYn2">미사용</label>
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="mgrUse1">사용 유무</label></th>
					<td>
						<input type="radio" id="mgrUse1" name="mgrUse" value="Y" /><label for="mgrUse1">사용</label>&nbsp;&nbsp;
						<input type="radio" id="mgrUse2" name="mgrUse" value="N" /><label for="mgrUse2">미사용</label>
					</td>
				</tr>
			</tbody>
			</table>
		</div>
		<div class="btnArea">
			<input type="submit" value="확인" class="btn_m on">
			<a href="#" class="btn_m btn_modalClose">취소</a>
		</div>
		</form>
	</div>
</div>
</body>
</html>
