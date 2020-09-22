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
	function doBbsContentFList() {
		$("#BbsContentFVo").attr("action", "<c:url value='/site/seocho/foffice/board/List.do' />?cbIdx=<c:out value="${BbsContentFVo.cbIdx }"/>").submit();
	}

	function doBbsContentFForm() {
		$("#BbsContentFVo").attr("action", "<c:url value='/site/seocho/foffice/board/Form.do' />?cbIdx=<c:out value="${BbsContentFVo.cbIdx }"/>&bcIdx=<c:out value="${BbsContentFVo.bcIdx }"/>").submit();
	}

	function doBbsContentFRmv() {
		if(!confirm("정말 삭제하시겠습니까?")) return;
		$("#BbsContentFVo").attr("action", "/site/seocho/foffice/board/RmvProcess.do?cbIdx=<c:out value="${BbsContentFVo.cbIdx }"/>&bcIdx=<c:out value="${BbsContentFVo.bcIdx }"/>").submit();
	}
	
	function goReload() {
		$("#BbsContentFVo").attr("action", "<c:url value='/site/seocho/foffice/board/View.do' />?cbIdx=<c:out value="${BbsContentFVo.cbIdx }"/>&bcIdx=<c:out value="${BbsContentFVo.bcIdx }"/>").submit();
	}
	
	function doMinwonSurveyRegAx() {
		
		if($("input:radio[name='mcSurvey']:checked").length < 0) {
			alert("만족도를 선택해주세요.");
			$("input:radio[name='mcSurvey']").eq(0).focus();
			return;
		}
		
		if($('#mcSurveyDesc').val() == "") {
			alert("의견을 작성해 주세요.");
			$("#mcSurveyDesc").focus;
			return;
		}
		
		$.ajax({
			type: "GET",
			url: "<c:url value='/site/seocho/ex/board/ContentMinwonResultFSurveyRegAx.do'/>",
			data : $("#BbsContentFVo").serialize(),
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
//]]>
</script>
</head>
<body>
<fmt:setLocale value="ko_kr"/>
<form:form commandName="BbsContentFVo" name="BbsContentFVo" method="post">
<input type="hidden" id="pageIndex" name="pageIndex" value="<c:out value="${BbsContentFVo.pageIndex }"/>" />
<input type="hidden" id="searchCondition" name="searchCondition" value="<c:out value="${BbsContentFVo.searchCondition }"/>" />
<input type="hidden" id="searchKeyword" name="searchKeyword" value="<c:out value="${BbsContentFVo.searchKeyword }"/>" />
<input type="hidden" id="searchCateCont" name="searchCateCont" value="<c:out value="${BbsContentFVo.searchCateCont }"/>" />

	<div class="board">
	
		<c:set var="replyType" value="" />
		
		<jsp:include page="./inc/${bbsTempletName}/view.jsp" />
	
		<!-- 버튼 -->
		<div class="btns aright">
			<c:if test="${isModifyGbn}">
			<c:if test="${(BoardVo.cbIdx != 230 && BbsContentFVo.ansRYn ne 'Y') || (BoardVo.cbIdx == 230 && empty replyType)}">
			<a href="#modify" onclick="doBbsContentFForm();return false;" class="type1">수정</a>
			</c:if>
			</c:if>
			<c:if test="${isDeleteGbn}">
			<a href="#delete" onclick="doBbsContentFRmv();return false;" class="type1">삭제</a>
			</c:if>
			<a href="#list" onclick="doBbsContentFList();return false;" class="type2">목록</a>
		</div>
		<!-- //버튼 -->
		
	</div><!-- //board -->
	
</form:form>
</body>
