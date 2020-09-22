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
	<c:forEach items="${detailList}" var="detailList">
		<c:if test="${detailList.contentMapping eq 'SUB_CONT'}">
			<meta id="mtTitle" property="og:title" content="<c:out value="${detailList.dataContent}"/>">
			<meta property="og:description" content="<c:out value="${detailList.dataContent}"/> 에 대한 상세내용입니다.">
		</c:if>
	</c:forEach>
<script type="text/javascript">
//이전글 다음글
function doPrevNext(cbIdx, bcIdx){
	document.bbsFVo.action = "<c:url value='/site/${strDomain}/ex/bbs/View.do?cbIdx=' />"+cbIdx+"&bcIdx="+bcIdx;
	document.bbsFVo.action = "<c:url value='/site/${strDomain}/ex/bbs/View.do?cbIdx=' />"+cbIdx+"&bcIdx="+bcIdx;
	document.bbsFVo.submit();
}

//수정페이지
function doBbsFMod(cbIdx, bcIdx, gbn){
	document.bbsFVo.action = "<c:url value='/site/${strDomain}/ex/bbs/Regist.do?cbIdx=' />"+cbIdx+"&bcIdx="+bcIdx;
	document.bbsFVo.mode.value = "U";
	document.bbsFVo.submit();
}

//답변
function doBbsFAnSwer(cbIdx, answerStep, parentSeq, answerDepth, gbn){
	document.bbsFVo.answerStep.value = answerStep;
	document.bbsFVo.parentSeq.value = parentSeq;
	document.bbsFVo.answerDepth.value = answerDepth;
	document.bbsFVo.action = "<c:url value='/site/${strDomain}/ex/bbs/Regist.do?cbIdx=' />"+cbIdx;
	document.bbsFVo.mode.value = "R";
	document.bbsFVo.submit();
}

//목록
function doBbsFList(cbIdx){
	document.bbsFVo.action = "<c:url value='/site/${strDomain}/ex/bbs/List.do?cbIdx=' />"+cbIdx;
	document.bbsFVo.submit();
}

 //삭제
function doBbsFRmv(cbIdx, bcIdx){
	if (!confirm("삭제하시겠습니까?")) {
		return;
	}
	document.bbsFVo.action = "<c:url value='/site/${strDomain}/ex/bbs/Delete.do?cbIdx=' />"+cbIdx+"&bcIdx="+bcIdx;
	document.bbsFVo.submit();
}

function doBbsFRmvGUB(cbIdx, bcIdx) {
	if (!confirm("삭제하시겠습니까?")) {
		return;
	}
	document.bbsFVo.action = "<c:url value='/site/mayor/ex/bbs/gubPassDeleteWord.do?cbIdx=' />"+cbIdx+"&bcIdx="+bcIdx;
	document.bbsFVo.submit();
}
</script>
</head>
<body>
	<c:if test="${bbsFVo.socialYn=='Y'}">
		<jsp:include page="/layout/module/sns_share.jsp" flush="true"/>
	</c:if>
	<div class="portfolioDetail">
		<c:set var="colspan" value=""/>
		<c:set var="oldcolspan" value=""/>
		<c:set var="colspanText" value=""/>
		<c:set var="colspanList" value=",CATE_CONT,NAME_CONT,EMAIL_CONT,TEL_CONT,PHONE_CONT,REG_DT,COUNT_CONT,M_LINK_CONT,THUMNAIL_CONT,CAPTION_CONT,SUB_LINK_CONT,OPEN_YN_CONT,ANS_YN_CONT,ANS_COMP_CONT,MW_STATUS_CONT,CD_SUBJECT,CHARGE_NAME_CONT,CHARGE_TEL_CONT,EXT1,EXT2,EXT3,EXT4,EXT5,EXT6,EXT7,EXT8,EXT9,EXT10,"/>
		<c:set var="rowCount" value="0"/>
		<c:set var="arrAddr" value="${fn:split('__','_')}"/>
		<c:set var="fileYn" value="N"/>
		<c:forEach items="${detailList}" var="detailList">
			<c:choose>
				<c:when test="${detailList.contentMapping eq 'CAPTION_CONT'}"></c:when>
				<c:when test="${detailList.contentMapping eq 'SUB_CONT'}"><!-- 제목 -->
					<input type="hidden" id="scrap_title" value="${detailList.dataContent }"/>
					<%-- <c:out value="${detailList.labelName }" escapeXml="false"/> --%>
		<h4><c:out value="${cmm:outClobCont(detailList.dataContent)}" escapeXml="false"/></h4>
		<a href="#" class="btn_list" onclick="doBbsFList('<c:out value="${bbsFVo.cbIdx }"/>', '<c:out value="${bbsFVo.bcIdx }"/>');return false;"><img src="/images/sub/btn_listArrow.png" alt=""> 목록</a>
		<div class="siteInfo">
			<ul>
				</c:when>
				<c:when test="${detailList.contentMapping eq 'EXT1'}">
				<li><span class="title">Client.</span> <c:out value="${detailList.dataContent }"/></li>
				</c:when>
				<c:when test="${detailList.contentMapping eq 'EXT2'}">
				<li><span class="title">Date.</span> <c:out value="${detailList.dataContent }"/></li>
				</c:when>
				<c:when test="${detailList.contentMapping eq 'CLOB_CONT'}">
		<div class="projectContent">
			<div>
					<c:choose>
						<c:when test="${bbsFVo.editorYn eq 'Y'}">
							<c:out value="${cmm:outClobCont(detailList.clobCont)}" escapeXml="false" />
						</c:when>
						<c:otherwise>
							<c:out value="${cmm:outClobCont(detailList.clobCont)}" escapeXml="false"/>
						</c:otherwise>
					</c:choose>
			</div>
		</div>
				</c:when>
			</c:choose>
		</c:forEach>
	</div>
	<c:if test="${bbsFVo.commentYn eq 'Y'}">
		<div id="comment_box">
			<jsp:include page="comment_list.jsp" flush="true"/>
		</div>
	</c:if>
	<div class="btnArea">
		<c:if test="${bbsFVo.answerGbn ne '16010500' && bbsFVo.author eq 'Y'}">
			<a href="#" onclick="doBbsFAnSwer('<c:out value="${bbsFVo.cbIdx }"/>', '<c:out value="${bbsFVo.bcIdx }"/>','<c:out value="${detailList[0].parentSeq}"/>', '<c:out value="${detailList[0].answerDepth}"/>','<c:out value="${bbsFVo.answerGbn}"/>');return false;" class="btn_m">답변</a>
		</c:if>
		<c:if test="${bbsFVo.dupcode ne null && detailList[0].dupcode eq bbsFVo.dupcode}" >
			<c:if test="${bbsFVo.cbIdx ne 414 && MinwonMap.ansRYn eq 'N'}">
				<a href="#" onclick="doBbsFRmv('<c:out value="${bbsFVo.cbIdx }"/>', '<c:out value="${bbsFVo.bcIdx }"/>');return false;" class="btn_m">삭제</a>			
				<a href="#" onclick="doBbsFMod('<c:out value="${bbsFVo.cbIdx }"/>', '<c:out value="${bbsFVo.bcIdx }"/>','<c:out value="${bbsFVo.modGbn }"/>');return false;" class="btn_m">수정</a>
			</c:if>
		</c:if>
		<a href="#" onclick="doBbsFList('<c:out value="${bbsFVo.cbIdx }"/>', '<c:out value="${bbsFVo.bcIdx }"/>');return false;" class="btn_m">목록</a>
	</div>

	<form:form commandName="bbsFVo" name="bbsFVo" method="post" >
		<form:hidden path="searchKey" />
		<form:hidden path="searchKey2" />
		<form:hidden path="cdDepstep" />
		<form:hidden path="pageIndex" />
		<form:hidden path="tgtTypeCd" />
		<form:hidden path="mode" />
		<form:hidden path="parentSeq" />
		<form:hidden path="answerDepth" />
		<form:hidden path="answerStep" />
		<form:hidden path="deptCode" />
		<form:hidden path="cateTypeCd" />
		<form:hidden path="fileMaxSize" />
		<form:hidden path="gubPassword" />
	</form:form>

</body>