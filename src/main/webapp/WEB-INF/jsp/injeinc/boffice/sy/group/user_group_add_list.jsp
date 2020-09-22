
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%-- ------------------------------------------------------------
- 제목 : 회원그룹 > 회원추가
- 파일명 : user_group_add_list.jsp
- 최종수정일 : 2019-04-29
- 상태 : 1차완료
-------------------------------------------------------------- --%>
<!DOCTYPE html>
<html lang="ko" class="iframe">
<head>
<title>통합관리시스템</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta charset="utf-8" />
<c:import url="/layout/cms/head.jsp" ></c:import>
<script>
//회원추가
function doUserRegListAx(gcIdx) {
	var cuIdx = new Array();
	var count = 0;
	
	$('input:checkbox[id="checkbox"]:checked').each(function(idx){
		cuIdx[idx] = this.value;
		count++;
	});
	
	if(count <= 0) {
		alert('선택된 회원이 없습니다.');
		return;
	}
	
	$.ajax({  
        type: "POST",
        url: "<c:url value='/boffice/sy/group/UserGroupAddRegAx.do'/>",
        data: "cuIdx=" + cuIdx +"&gcIdx=" + gcIdx,
        dataType: "json",
        success: function(data) {
        	$('.wrap-loading').addClass('display-none'); 
        	alert("추가되었습니다.");
        	doUserGroupAddPag(document.UserVo.pageIndex.value);
        	//opener.doGroupConfigListPag(1);//부모창 새로고침
        	opener.$(".searchListPage").attr("action","<c:url value='/boffice/sy/group/GroupConfig_List.do'/>").submit();
        	window.close();
        }
        ,beforeSend:function(){ 
            //(이미지 보여주기 처리) 
            $('.wrap-loading').removeClass('display-none'); 
        } 
        ,complete:function(){ 
            //(이미지 감추기 처리) 
            //$('.wrap-loading').addClass('display-none'); 
        }
        ,error: function(req, status, error) {
        	$('.wrap-loading').addClass('display-none'); 
        	var str = req.status + " : " + status + " : " + error;
        	alert(str);
        }
    });
}

//페이징처리 
function doUserGroupAddPag(pageIndex) {
	document.UserVo.pageIndex.value = pageIndex;
	document.UserVo.action = "<c:url value='/boffice/sy/group/UserGroupAddList.do' />";
	document.UserVo.submit();
}

//리스트
function doUserGroupAddList() {
	document.UserVo.pageIndex.value = 1;	
	document.UserVo.searchKeyword.value = document.UserVo.searchkeyword.value;	
	document.UserVo.action = "<c:url value='/boffice/sy/group/UserGroupAddList.do' />";
	document.UserVo.submit();
}

$(window).load(function() {
    $("input[id=scRegDtSt]").val("<c:out value='${UserVo.scRegDtSt}'/>");
    $("input[id=scRegDtEd]").val("<c:out value='${UserVo.scRegDtEd}'/>");
    $("input[id=scLastLogDtSt]").val("<c:out value='${UserVo.scLastLogDtSt}'/>");
    $("input[id=scLastLogDtEd]").val("<c:out value='${UserVo.scLastLogDtEd}'/>");
    $("#searchkeyword").val("<c:out value='${UserVo.searchKeyword}'/>");
    
});
</script>
<style type="text/css">
	.view1{ width:100%; margin-bottom:10px;border-top:2px solid #5a5a5a; }
	.view1 tr td:first-child{text-align:center;background-color:#f4f4f4;}	
	.view1 td{padding:10px; border-bottom:1px solid #d1cdc9;}
	
.wrap-loading{ /*화면 전체를 어둡게 합니다.*/ 
    position: fixed; 
    left:0; 
    right:0; 
    top:0; 
    bottom:0; 
    background: rgba(0,0,0,0.2); /*not in ie */ 
    filter: progid:DXImageTransform.Microsoft.Gradient(startColorstr='#20000000', endColorstr='#20000000');    /* ie */ 
} 
.wrap-loading div{ /*로딩 이미지*/ 
        position: fixed; 
        top:50%; 
        left:50%; 
        margin-left: -21px; 
        margin-top: -21px; 
}
.display-none{ /*감추기*/ 
	display:none;
}

</style>
</head>
<body>

<!-- ============================== 모달영역 ============================== -->
<div class="modalWrap popup">
	<div class="modalTitle">
		<h3>회원추가</h3>
		<a href="#" class="btn_modalClose">닫기</a>
	</div>
	<div class="modalContent">
		<form:form commandName="UserVo" name="UserVo" method="post" >
		<form:hidden path="pageIndex" />
		<form:hidden path="cuIdx" />
		<form:hidden path="guIdx" />
		<form:hidden path="searchKeyword" />
		<input type="hidden" name="gcIdx" id="gcIdx" value="<c:out value='${gcIdx}' />" />
		<div class="tableBox">
			<table class="form">
				<caption></caption>
				<colgroup>
					<col class="w100">
					<col>
					<col class="w100">
					<col>
				</colgroup>
				<tr>
					<th>
						<label for="scRegDtSt">가입일</label>
					</th>
					<td>
						<form:input path="scRegDtSt" class="useDatepicker dateFrom required" title="가입일(부터)"/> ~
						<form:input path="scRegDtEd" class="useDatepicker dateFrom required" title="가입일(까지)"/>
					</td>
					<th>
						<label for="scLastLogDtSt">최종방문일</label>
					</th>
					<td>
						<form:input path="scLastLogDtSt" class="useDatepicker dateFrom required" title="최종방문일(부터)"/> ~
						<form:input path="scLastLogDtEd" class="useDatepicker dateFrom required" title="최종방문일(까지)"/>
					</td>
				</tr>
				<tr>
					<th>
						<label for="searchCondition">검색</label>
					</th>
					<td colspan="3">
						<form:select path="searchCondition">
							<form:option value="" label="전체"/>
							<form:option value="name" label="이름"/>
							<form:option value="id" label="아이디"/>
						</form:select>
						<span class="sfont">
						<input type="text" id="searchkeyword" name="searchkeyword" size="30"/>
						<a href="javascript:doUserGroupAddList();" class="btn_inline btn_search">검색</a>
						</span>
					</td>
				</tr>			
			</table>
		</div>
			
		<div class="tableBox">	
			<table class="list">
				<caption></caption>
				<colgroup>
					<col style="width:5%;">
					<col>
					<col>
					<col>
					<col>
					<col style="width:8%;">
				</colgroup>
				<thead>
				<tr>
					<th>번호</th>
					<th>아이디</th>
					<th>이름</th>
					<th>가입일</th>
					<th>최종방문일</th>
					<th>확인</th>
				</tr>
				</thead>
				<tbody>
				<c:if test="${fn:length(resultList) eq 0}">
				<tr>
					<td colspan="6"  class="empty">검색결과가 없습니다.</td>						
				</tr>
				</c:if>
				<c:forEach items="${resultList}" var="result" varStatus="status">
				<tr>
					<td><c:out value="${(totCnt+1)-(status.count+(UserVo.pageIndex-1)*UserVo.recordCountPerPage)}" /></td>						
					<td><c:out value="${result.cuId}" /></td>
					<td><c:out value="${result.cuName}" /></td>
					<td><c:out value="${result.regDt}" /></td>
					<td><c:out value="${result.lastLogDt}" /></td>
					<td><input type="checkbox" name="checkbox" id="checkbox" value="<c:out value="${result.cuIdx}" />" /></td>
				</tr>
				</c:forEach>
			
				</tbody>
			</table>
		</div>
		<div class="paging">
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="doUserGroupAddPag" />
		</div>
		</form:form>
		
		<div class="btnArea">
			<a href="javascript:doUserRegListAx('<c:out value='${gcIdx}' />');" class="btn_m on btn_add">회원추가</a>
			<a href="#" class="btn_m btn_modalClose">취소</a>
		</div>
	</div>
</div>
<!-- ============================== //모달영역 ============================== -->
</body>
</html>