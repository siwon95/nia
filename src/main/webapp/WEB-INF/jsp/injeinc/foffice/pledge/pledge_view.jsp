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
	$(".btn_list").click(function(e){
		e.preventDefault();
		document.PledgeFVo.action = "<c:url value='/site/${strDomain}/pledge/List.do' />";
		document.PledgeFVo.submit();
	});
	$(".btn_del").click(function(e){
		e.preventDefault();
		if (!confirm("삭제하시겠습니까?")) {
			return;
		}
		document.PledgeFVo.action = "<c:url value='/site/${strDomain}/pledge/Delete.do' />";
		document.PledgeFVo.submit();
	});
	$(".btn_mod").click(function(e){
		e.preventDefault();
		document.PledgeFVo.action = "<c:url value='/site/${strDomain}/pledge/Form.do' />";
		document.PledgeFVo.submit();
	});
	$(".btn_recommend").click(function(e){
		e.preventDefault();
		$.ajax({
			type: "GET",
			url: "<c:url value='RecommendAx.do'/>",
			data : {"plIdx":$("#plIdx").val()},
			dataType : "json",
			success:function(data){
				alert(data.msg);
				if(data.result){
					$(".recommendCont").text(data.recommendCnt);
				}
			 },
	        error: function(xhr,status,error){
	        	alert(status);
	        }
		 });
	});
});
</script>
<body>
<form:form commandName="PledgeFVo" id="PledgeFVo" name="PledgeFVo" method="post" enctype="multipart/form-data">
	<input type="hidden" id="actionkey" name="actionKey" value="<c:out value="${PledgeFVo.actionKey}"/>" />
	<input type="hidden" id="pageIndex" name="pageIndex" value="<c:out value="${PledgeFVo.pageIndex}"/>" />
	<input type="hidden" id="pageUnit" name="pageUnit" value="<c:out value="${PledgeFVo.pageUnit}"/>" />
	<input type="hidden" id="searchCondition" name="searchCondition" value="<c:out value="${PledgeFVo.searchCondition}"/>" />
	<input type="hidden" id="searchKeyword" name="searchKeyword" value="<c:out value="${PledgeFVo.searchKeyword}"/>" />
	<input type="hidden" id="searchStartDate" name="searchStartDate" value="<c:out value="${PledgeFVo.searchStartDate}"/>" />
	<input type="hidden" id="searchEndDate" name="searchEndDate" value="<c:out value="${PledgeFVo.searchEndDate}"/>" />
	<input type="hidden" id="searchPlWiwid1" name="searchPlWiwid1" value="<c:out value="${PledgeFVo.searchPlWiwid1}"/>" />
	<input type="hidden" id="searchPlWiwid2" name="searchPlWiwid2" value="<c:out value="${PledgeFVo.searchPlWiwid2}"/>" />
	<input type="hidden" id="searchCateCont" name="searchCateCont" value="<c:out value="${PledgeFVo.searchCateCont}"/>" />
	<input type="hidden" id="plIdx" name="plIdx" value="<c:out value="${PledgeFVo.plIdx}"/>" />
	<div class="bbsView">
		<div class="bbsViewTitle">
			<b>
				<c:out value="${fn:replace(fn:replace(fn:replace(PledgeFVo.subCont,'&','&amp;'),'<','&lt;'),'>','&gt;')}" escapeXml="false"/>
			</b>
			<ul>
				<li><c:out value="${PledgeFVo.regName}" /></li>
				<li><c:out value="${PledgeFVo.regDt}" /></li>
				<li>조회수 <c:out value="${PledgeFVo.countCont}" /></li>
				<li>추천수 <span class="recommendCont"><c:out value="${PledgeFVo.recommendCont}" /></span></li>
			</ul>
		</div>
		<div class="bbsViewContent">
		<div class="bbsViewInfo">
			<ul>
				<li><span class="title">공약구분</span><c:out value="${PledgeFVo.cateContTxt}"  /></li>
				<li><span class="title">지역</span><c:out value="${PledgeFVo.plWiwTxt1}"/> <c:out value="${PledgeFVo.plWiwTxt2}"/></li>
			</ul>
		</div>
		<c:if test="${fn:length(fileList) > 0}">
			<div class="bbsViewFile">
				<ul>
					<c:forEach items="${fileList}" var="file">		
						<c:if test="${file ne null}">
					 		<li>
					 			<a href="/common/pledge/Download.do?plIdx=<c:out value="${file.plIdx}"/>&amp;streFileNm=<c:out value="${file.streFileNm}"/>" target="_blank" title="파일다운로드">
									<span class="icon_bbsFile <c:out value="${file.fileExtsn}"/>"></span>
									<c:out value="${file.orignlFileNm }" />  [<c:out value="${bbs:byteCalculation(file.fileSize)}" />]
								</a>
							</li>
						</c:if>
					</c:forEach>
				</ul>
			</div>
		</c:if>
		<div class="bbsViewEditor">
			<c:out value="${cmm:outClobCont(PledgeFVo.clobCont)}" escapeXml="false" />
		</div>
	</div>
	<div class="btnArea">
		<a href="#" class="btn_m btn_recommend">추천하기</a>
	</div>
	<div class="btnArea right">
		<c:if test="${PledgeFVo.modifyAuthor eq 'Y'}"><a href="#" class="btn_m on btn_mod">수정</a></c:if>
		<c:if test="${PledgeFVo.deleteAuthor eq 'Y'}"><a href="#" class="btn_m on btn_del">삭제</a></c:if>
		<a href="#" class="btn_m btn_list">목록</a>
	</div>
</form:form>