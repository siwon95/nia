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
<input type="hidden" id="gubPassword" name="gubPassword" value="<c:out value='${detailMap.gubPassword}'/>"/>
</form:form>
<c:if test="${bbsFVo.socialYn=='Y'}">
	<jsp:include page="/layout/module/sns_share.jsp" flush="true"/>
</c:if>
<div id="container">
	<div class="containerTop type1">
		<div class="inner">
			<h3><c:out value="${bbsFVo.cbName}"/></h3>
		</div>
	</div>
	<div id="contents">
		<div class="inner">
			<div class="tableBox">
				<table class="view mgb20">
					<colgroup>
						<col style="width:10%;">			
						<col>
						<col style="width:10%;">
						<col>
					</colgroup>
					<tbody>
						<c:if test="${detailMap.notiYn eq 'N'}">
							<tr>
								<th scope="row">분류</th>
								<td><c:out value="${cmm:getCodeName(detailMap.ext1)}" /></li></td>
								<th scope="row">작성자</th>
								<td><c:out value="${detailMap.nameCont}" /></li></td>
							</tr>	
							<tr>
								<th scope="row">이메일</th>
								<td><c:out value="${detailMap.emailCont}" /></li></td>
								<th scope="row">연락처</th>
								<td><c:out value="${detailMap.telCont}" /></li></td>
							</tr>	
							<tr>
								<th scope="row">지역</th>
								<td colspan="3"><c:out value="${fn:replace(detailMap.addrCont,'_',' ')}" /></li></td>
							</tr>
						</c:if>
						<tr>
							<th scope="row">제목</th>
							<td colspan="3"><c:out value="${fn:replace(fn:replace(fn:replace(detailMap.subCont,'&','&amp;'),'<','&lt;'),'>','&gt;')}" escapeXml="false"/></td>
						</tr>

						<tr>
							<th scope="row">내용</th>
							<td colspan="3"><c:out value="${cmm:outClobCont(detailMap.clobCont)}" escapeXml="false" /></td>
						</tr>							
					</tbody>
				</table>
			</div>
			<c:if test="${detailMap.ansYnCont eq '22000200'}">
				<div class="tableBox">
					<table class="view mgb20">
						<colgroup>
							<col style="width:10%;">			
							<col>
							<col style="width:10%;">
							<col>
						</colgroup>
						<tbody>
							<tr>
								<th scope="row">답변자</th>
								<td></td>
								<th scope="row">답변일자</th>
								<td></td>
							</tr>
							<tr>
								<th scope="row">첨부파일</th>
								<td colspan="3">
								<img src="/images/sub/icon_file.png" alt="첨부파일">&nbsp;&nbsp;&nbsp;&nbsp;<a href="/download.asp?table_name=tb_consult&sn=&folder=tb_consult&field=file1" class="filedown btn_m">다운받기</a><br>
								</td>
							</tr>

							<tr>
								<th scope="row">답변내용</th>
								<td colspan="3"></td>
							</tr>

						</tbody>
					</table>
				</div>
			</c:if>
			<div class="btnArea ">
					<c:if test="${detailMap.notiYn eq 'N'}">
						<a href="#" class="btn_l btn_bbsModify">수정</a>
						<a href="#" class="btn_l red btn_bbsDelete">삭제</a>
					</c:if>
					<a href="#" class="btn_l gray btn_bbsList">목록</a>		
			</div>
		</div>
	</div>
</div>