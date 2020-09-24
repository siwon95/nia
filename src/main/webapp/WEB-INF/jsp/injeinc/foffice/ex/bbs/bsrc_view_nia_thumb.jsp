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
<script>
$(function(){
	$(".btn_bbsAnswer").click(function(){
		document.bbsFVo.answerStep.value = "<c:out value="${detailList[0].answerStep}"/>";
		document.bbsFVo.parentSeq.value = "<c:out value="${detailList[0].parentSeq}"/>";
		document.bbsFVo.answerDepth.value = "<c:out value="${detailList[0].answerDepth}"/>";
		//document.bbsFVo.answerGbn.value = "<c:out value="${bbsFVo.answerGbn}"/>";
		document.bbsFVo.action = "<c:url value='/site/${strDomain}/ex/bbs/Regist.do?cbIdx=${bbsFVo.cbIdx}' />";
		document.bbsFVo.mode.value = "R";
		document.bbsFVo.submit();
	});
	<c:if test="${bbsFVo.dupcode ne 'guest'}">
	$(".btn_bbsDelete").click(function(){
		if (!confirm("삭제하시겠습니까?")) {
			return;
		}
		document.bbsFVo.action = "<c:url value='/site/${strDomain}/ex/bbs/Delete.do?cbIdx=${bbsFVo.cbIdx}&bcIdx=${bbsFVo.bcIdx}' />";
		document.bbsFVo.submit();
	});
	$(".btn_bbsModify").click(function(){
		document.bbsFVo.action = "<c:url value='/site/${strDomain}/ex/bbs/Regist.do?cbIdx=${bbsFVo.cbIdx}&bcIdx=${bbsFVo.bcIdx}' />";
		document.bbsFVo.mode.value = "U";
		document.bbsFVo.submit();
	});
	</c:if>
	
	<c:if test="${bbsFVo.dupcode eq 'guest' && detailList[0].gubPassword ne ''}">
	$(".btn_bbsDelete").click(function(){
		injeinc.modalOpen("#modal_password");
		document.bbsFVo.mode.value = "D";
	});
	$(".btn_bbsModify").click(function(){
		injeinc.modalOpen("#modal_password");
		document.bbsFVo.mode.value = "U";
	});
	</c:if>
	$(".btn_bbsList").click(function(){
		document.bbsFVo.action = "<c:url value='/site/${strDomain}/ex/bbs/List.do?cbIdx=${bbsFVo.cbIdx}' />";
		document.bbsFVo.submit();
	});
});

function bbsPasswordForm(f){
	if(document.bbsFVo.mode.value=="D"){
		if (!confirm("삭제하시겠습니까?"))
			return;
		document.bbsFVo.action = "<c:url value='/site/${strDomain}/ex/bbs/Delete.do?cbIdx=${bbsFVo.cbIdx}&bcIdx=${bbsFVo.bcIdx}' />";
	}else{
		document.bbsFVo.action = "<c:url value='/site/${strDomain}/ex/bbs/Regist.do?cbIdx=${bbsFVo.cbIdx}&bcIdx=${bbsFVo.bcIdx}' />";
	}
	document.bbsFVo.gubPassword.value = f.gubPassword.value;
	document.bbsFVo.submit();
	return false;
}
</script>
<style>
#overlay{z-index:90;}
</style>
</head>
<body>
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
<c:if test="${bbsFVo.socialYn=='Y'}">
	<jsp:include page="/layout/module/sns_share.jsp" flush="true"/>
</c:if>
<div id="container">
	<div class="containerTop type2">
		<div class="inner">
			<h3><c:out value="${bbsFVo.cbName}"/></h3>
		</div>
	</div>
	<div id="contents">
		<div class="inner">
			<h4><c:out value="${bbsFVo.cbName}"/></h4>
			<div class="tableBox">
				<table class="view mgb20">
					<colgroup>
						<col>
					</colgroup>
					<thead>
						<tr>
							<th scope="col">
								<c:out value="${fn:replace(fn:replace(fn:replace(detailMap.subCont,'&','&amp;'),'<','&lt;'),'>','&gt;')}" escapeXml="false"/>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td class="content">
								<div class="text">
									<c:out value="${cmm:outClobCont(detailMap.clobCont)}" escapeXml="false" />
								</div>
							</td>
						<tr>
							<td>
								<c:forEach items="${fileList}" var="fileList">		
									<c:if test="${fileList ne null}">
										<c:if test="${fileList.fileNo ne '1'}">
											<img src="/images/nia/sub/icon_file.png" alt="첨부파일">&nbsp;&nbsp;&nbsp;<c:out value="${fileList.orignlFileNm}"/> &nbsp;
											<a href="/common/board/Download.do?bcIdx=<c:out value="${fileList.bcIdx}"/>&amp;cbIdx=<c:out value="${fileList.cbIdx}"/>&amp;streFileNm=<c:out value="${fileList.streFileNm}"/>" target="_blank" class="filedown btn_m">
												다운받기
											</a>
											<br>
										</c:if>
									</c:if>
								</c:forEach>
							</td>
						</tr>
					</tbody>
				</table>
				<table class="view list_go">
					<colgroup>
						<col style="width:10%;">			
						<col>
						<col style="width:10%;">
						<col>
					</colgroup>
					<tbody>	
						<tr>
							<th scope="row">다음글</th>
							<td colspan="3">
								<c:if test="${prevNextList.PREV_IDX ne null}">
									<a href="<c:url value='/site/${strDomain}/ex/bbs/View.do?cbIdx=${bbsFVo.cbIdx }&bcIdx=${prevNextList.PREV_IDX }'/>">
								<c:out value="${fn:replace(fn:replace(fn:replace(prevNextList.PREV_SUB,'&','&amp;'),'<','&lt;'),'>','&gt;')}"/>
							</a>
								</c:if>
							</td>
						</tr>	
						<tr>
							<th scope="row" class="b_top">이전글</th>
							<td colspan="3" class="b_top">
								<c:if test="${prevNextList.NEXT_IDX ne null}">
									<a href="<c:url value='/site/${strDomain}/ex/bbs/View.do?cbIdx=${bbsFVo.cbIdx }&bcIdx=${prevNextList.NEXT_IDX }'/>">
										<c:out value="${fn:replace(fn:replace(fn:replace(prevNextList.NEXT_SUB,'&','&amp;'),'<','&lt;'),'>','&gt;')}"/>
									</a>
								</c:if>
							</td>
						</tr>	
					</tbody>
				</table>
			</div>
			<div class="btnArea">
				<a href="#" class="btn_l focus btn_bbsList">목록</a>					
			</div>
			
		</div>
	</div>
</div>