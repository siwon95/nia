<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<%-- ------------------------------------------------------------
- 제목 : 게시판관리
- 파일명 : board_set.jsp
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
	alert("<spring:message code="${message}" />");
	</c:if>
	<c:if test="${not empty param.message}">
	alert("<spring:message code="${param.message}" />");
	</c:if>
	
	//버튼이벤트
	$(".btn_mod").click(function(e){
		e.preventDefault();
		var param = $(this).parents("li").eq(0).attr("data-param").split("|");
		$('#codeName').text(param[0]);
		$('#bbsTempletGbn').val(param[1]);
		$('#listCode').val(param[2]);
		$('#viewCode').val(param[3]);
		$('#registCode').val(param[4]);
		modalOpen("#modal_mod");
	});
	$(".btn_del").click(function(e){
		e.preventDefault();
		if(!confirm("<spring:message code="common.delete.msg" />")){
			return;
		}
		//삭제 기능추가
	});
});

function doForm(f){
	return true;
}
</script>
<!-- //페이지 전용 스타일/스크립트 -->

</head>
<body>

	<div class="section">
		<div class="guideMessage">
			<ul>
				<li>게시판템플릿은 공통코드메뉴를 통해서 추가, 삭제가 가능합니다.</li>
				<li>(공통코드 > 게시판/민원업무 > 게시판템플릿종류) <a href="/boffice/sy/code/Code_List.do?treeLocationId=16050000" class="btn_ss">공통코드 바로가기</a></li>
			</ul>
		</div>
		<ul class="thumbList template">
			<c:forEach items="${resultList}" var="result" varStatus="status">
			<li data-param="<c:out value="${result.codeName}" />|<c:out value="${result.bbsTempletGbn}"/>|<c:out value="${result.listCode}"/>|<c:out value="${result.viewCode}"/>|<c:out value="${result.registCode}"/>">
				<div class="thumbImg">
					<a href="#modal_siteMod" class="btn_modalOpen"><img src="#" alt="No Image" onerror="this.style.display='none';"></a>
				</div>
				<div>
					<a href="#" class="btn_mod"><span class="subject"><c:out value="${result.codeName}" /></span></a>
				</div>
			</li>
			</c:forEach>
		</ul>
	</div>

<!-- ============================== 모달영역 ============================== -->
<!-- 템플릿 등록 -->
<div id="modal_mod" class="modalWrap small">
	<div class="modalTitle">
		<h3>템플릿 수정</h3>
		<a href="#" class="btn_modalClose">모달팝업닫기</a>
	</div>
	<div class="modalContent">
		<form name="EzBbsTempletVo" method="post" action="<c:url value='/boffice/sy/board/ezBbsTemplet_action.do' />" onsubmit="return doForm(this);">
		<input type="hidden" id="mode" name="mode" value="ezBbsTemplet_insert">
		<input type="hidden" id="bbsTempletGbn" name="bbsTempletGbn" value="">
		<div class="tableBox">
			<table class="form">
				<caption></caption>
				<colgroup>
					<col class="w20p">
					<col>
				</colgroup>
				<tbody>
					<tr>
						<th>템플릿명</th>
						<td id="codeName"></td>
					</tr>
					<tr>
						<th><label for="listCode">목록타입</label></th>
						<td>
							<select id="listCode" name="listCode" class="w100p">
								<option value="">선택</option>
								<c:forEach var="bsrcList" items="${bsrcList}">
									<option value="<c:out value="${bsrcList.code}"/>"><c:out value="${bsrcList.codeName}"/></option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th><label for="registCode">상세타입</label></th>
						<td>
							<select id="viewCode" name="viewCode" class="w100p">
								<option value="">선택</option>
								<c:forEach var="bsrcView" items="${bsrcView}">
									<option value="<c:out value="${bsrcView.code}"/>"><c:out value="${bsrcView.codeName}"/></option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th><label for="registCode">등록타입</label></th>
						<td>
							<select id="registCode" name="registCode" class="w100p">
								<option value="">선택</option>
								<c:forEach var="bsrcRegist" items="${bsrcRegist}">
									<option value="<c:out value="${bsrcRegist.code}"/>"><c:out value="${bsrcRegist.codeName}"/></option>
								</c:forEach>
							</select>
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
