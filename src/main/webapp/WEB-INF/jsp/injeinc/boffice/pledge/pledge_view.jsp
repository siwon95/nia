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
<script>
$(function(){
	//버튼이벤트
	$(".btn_mod").click(function(e){
		e.preventDefault();
		$("#PledgeVo #actionKey").val("modify");
		var modal_id = "modal_mod";
		var modal_url = "<c:url value='/boffice_nodeco/pledge/PledgeForm.do' />";
		var modal_param = $("#PledgeVo").serializeArray();
		var modal_class = "wide"; //wide, small
		ajaxModal(modal_id, modal_url, modal_param, modal_class);
	});
	$(".btn_del").click(function(e){
		e.preventDefault();
		if(!confirm("<spring:message code="common.delete.msg" />")){
			return;
		}
		$(".modalContent #PledgeVo").attr("action", "PledgeRmv.do").submit();
	});
});
</script>
<!-- //페이지 전용 스타일/스크립트 -->
</head>
<body>
	<!-- ============================== 모달영역 ============================== -->
	<div class="modalTitle">
		<h3>공약은행 상세페이지</h3>
		<a href="#" class="btn_modalClose">모달팝업닫기</a>
	</div>
	<div class="modalContent">
		<fmt:setLocale value="ko_kr"/>
		<form:form commandName="PledgeVo" id="PledgeVo" name="PledgeVo" method="post" enctype="multipart/form-data">
		<input type="hidden" id="actionkey" name="actionKey" value="<c:out value="${PledgeVo.actionKey}"/>" />
		<input type="hidden" id="pageIndex" name="pageIndex" value="<c:out value="${PledgeVo.pageIndex}"/>" />
		<input type="hidden" id="pageUnit" name="pageUnit" value="<c:out value="${PledgeVo.pageUnit}"/>" />
		<input type="hidden" id="searchCondition" name="searchCondition" value="<c:out value="${PledgeVo.searchCondition}"/>" />
		<input type="hidden" id="searchKeyword" name="searchKeyword" value="<c:out value="${PledgeVo.searchKeyword}"/>" />
		<input type="hidden" id="searchStartDate" name="searchStartDate" value="<c:out value="${PledgeVo.searchStartDate}"/>" />
		<input type="hidden" id="searchEndDate" name="searchEndDate" value="<c:out value="${PledgeVo.searchEndDate}"/>" />
		<input type="hidden" id="plIdx" name="plIdx" value="<c:out value="${!empty PledgeVo.plIdx ? PledgeVo.plIdx : 0}" />"/>
		<input type="hidden" id="searchPlWiwid1" name="searchPlWiwid1" value="<c:out value="${PledgeVo.searchPlWiwid1}"/>" />
		<input type="hidden" id="searchPlWiwid2" name="searchPlWiwid2" value="<c:out value="${PledgeVo.searchPlWiwid2}"/>" />
		<input type="hidden" id="searchCateCont" name="searchCateCont" value="<c:out value="${PledgeVo.searchCateCont}"/>" />
		<div class="tableBox">
		<table class="form">
			<caption>공약은행 상세페이지</caption>
			<colgroup>
				<col style="width:15%;">
				<col>
			</colgroup>
			<tbody>
				<tr><!-- 제목 -->
					<th scope="row">제목</th>
					<td>
						<c:out value="${PledgeVo.subCont}"/>
					</td>
				</tr>
				<tr><!-- 공약구분 -->
					<th scope="row">공약구분</th>
					<td>
						<c:out value="${PledgeVo.cateContTxt}"/>
					</td>
				</tr>
				<tr><!-- 지역 -->
					<th scope="row">지역</th>
					<td>
						<c:out value="${PledgeVo.plWiwTxt1}"/> <c:out value="${PledgeVo.plWiwTxt2}"/>
					</td>
				</tr>
				<tr><!-- 작성자 -->
					<th scope="row">작성자</th>
					<td>
						<c:out value="${PledgeVo.regName}"/>
					</td>
				</tr>
				<tr><!-- 아이디 -->
					<th scope="row">아이디</th>
					<td>
						<c:out value="${PledgeVo.regId}"/>
					</td>
				</tr>
				<tr><!-- 작성 방식 -->
					<th scope="row">작성 방식</th>
					<td>
						<c:out value="${PledgeVo.regType}"/>
					</td>
				</tr>
				<tr><!-- 연락처 -->
					<th scope="row">연락처</th>
					<td>
						<c:out value="${PledgeVo.telCont}"/>
					</td>
				</tr>
				<tr><!-- 이메일 주소 -->
					<th scope="row">이메일 주소</th>
					<td>
						<c:out value="${PledgeVo.emailCont}"/>
					</td>
				</tr>
				<c:if test="${fn:length(fileList) > 0}">
				<tr>
					<th scope="row">첨부파일</th>
					<td>
						<c:forEach var="fileInfo" items="${fileList}">
						<p>
							<a href="/common/pledge/Download.do?plIdx=<c:out value="${fileInfo.plIdx}"/>&streFileNm=<c:out value="${fileInfo.streFileNm}"/>" class="file">
								<img src="/images/egovframework/com/cmm/fileicon/<c:out value="${fileInfo.fileExtsn}"/>.gif" alt="<c:out value="${fileInfo.fileExtsn}"/> 아이콘" /> <c:out value="${fileInfo.orignlFileNm}" />  [<c:out value="${bbs:byteCalculation(fileInfo.fileSize)}" />]
							</a>
						</p>
						</c:forEach>
					</td>
				</tr>
				</c:if>
				<tr><!-- 내용 -->
					<th scope="row">내용</th>
					<td>${PledgeVo.clobCont}</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="btnArea">
		<a href="#" class="btn_m on btn_mod">수정</a>
		<a href="#" class="btn_m btn_caption btn_del">삭제</a>
		<a href="#" class="btn_m btn_modalClose">닫기</a>
	</div>
	</form:form>
</div>
</body>
</html>