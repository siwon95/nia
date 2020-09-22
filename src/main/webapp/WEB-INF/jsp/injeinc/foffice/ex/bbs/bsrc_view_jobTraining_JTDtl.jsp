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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>

$(function(){
	
	$(".btn_l").click(function(){
		document.bbsFVo.action = "<c:url value='/site/${strDomain}/ex/bbs/List.do?cbIdx=${bbsFVo.cbIdx}' />";
		document.bbsFVo.submit();
	});
	
	var video = document.getElementById("video");
	
	/**/
	video.onplay = function(){
		mediaInterval = setInterval(function(){
			$("#currentTime").val(video.currentTime); 
			$("#duration").val(video.duration); 
			var params = jQuery("#bbsFVo").serialize();
			 $.ajax({
				data:params,
				type:"POST",
				url:"<c:url value='/site/{strDomain}/ex/bbs/mediaPlay.do?cbIdx=${bbsFVo.cbIdx}'/>"
			});
		},10000)
			
		
	}
	
	
	video.onpause = function (){
		
		clearInterval(mediaInterval);
	}

	window.onbeforeunload = function (e) {
		e.preventDefault();
		
		var video = document.getElementById("video");
		
		$("#currentTime").val(video.currentTime); 
		$("#duration").val(video.duration); 
		var params = jQuery("#bbsFVo").serialize();
		 $.ajax({
			data:params,
			type:"POST",
			url:"<c:url value='/site/{strDomain}/ex/bbs/mediaPlay.do?cbIdx=${bbsFVo.cbIdx}'/>"
		});
		 
		
 	}; 
});		

</script>
<body>
<form:form commandName="bbsFVo" name="bbsFVo" method="post" id="bbsFVo" >
<form:hidden path="searchKey" />
<form:hidden path="searchKey2" />
<form:hidden path="cdDepstep" />
<form:hidden path="pageIndex" />
<form:hidden path="tgtTypeCd" />
<form:hidden path="searchDepth" />
<form:hidden path="mode" />
<form:hidden path="parentSeq" />
<form:hidden path="answerDepth" />
<form:hidden path="answerStep" />
<form:hidden path="deptCode" />
<form:hidden path="cateTypeCd" />
<form:hidden path="fileMaxSize" />
<form:hidden path="gubPassword" />
<form:hidden path="currentTime" />
<form:hidden path="bcIdx" />
<form:hidden path="duration" />

</form:form>

<input type="hidden" name="currentTime" id="currentTime" path="currentTime">
<c:if test="${bbsFVo.socialYn=='Y'}">
	<jsp:include page="/layout/module/sns_share.jsp" flush="true"/>
</c:if>

<h3><c:out value="${bbsFVo.cbName }"/></h3>
	
	<div class="board_view">
			
		<div class="tit">
			<strong>
				<c:if test="${not empty detailMap.subCont}"><c:out value="${fn:replace(fn:replace(fn:replace(detailMap.subCont,'&','&amp;'),'<','&lt;'),'>','&gt;')}" escapeXml="false"/></c:if>
			</strong>
			<span>
				<c:if test="${not empty detailMap.CENTER}"><span><c:out value="${detailMap.CENTER}" /></span></c:if> 
				<c:if test="${not empty detailMap.regDt}"><span><c:out value="${detailMap.regDt}" /></span></c:if> 
				<c:if test="${not empty detailMap.countCont}"><span>조회수<c:out value="${detailMap.countCont}" /></span></c:if>
			</span>
		</div>
		<div class="view_content">
			<div class="movie_play">
				<div class="movie_">
					<c:if test="${not empty detailMap.mLinkCont}">
						<video src="<c:out value="${detailMap.mLinkCont}" />#t=<c:out value="${mediaMap.playTime}" />" width="800" height="450" id="video" controls></video>
					</c:if>	
				</div>
				<div class="txt_">
					<c:if test="${not empty detailMap.captionCont}">
						<c:out value="${detailMap.captionCont}" />
					</c:if>
				</div>
			</div>
		</div>
		
		
		<c:forEach items="${detailList}" var="detailList">
			<c:choose>
				<c:when test="${detailList.labelPropGbn eq '16020200'}">
					<c:if test="${detailList.bbsTempletGbn eq '16050200' || detailList.bbsTempletGbn eq '16050300' || detailList.bbsTempletGbn eq '16050500' && fileImgList ne null }">
						<div class="bbsViewImage">
							<c:forEach items="${fileImgList}" var="fileImgList">
								<img src="<c:out value='${fileImgList.fileStreCours}' /><c:out value='${fileImgList.streFileNm}' />" alt=""><br /><br />
							</c:forEach>
						</div>
					</c:if>
				</c:when>
				<%-- 파일 다운로드 & 확장자 구분으로 이미지 뿌리기 시작 --%>
				<c:when test="${detailList.labelPropGbn eq '16020600' || detailList.labelPropGbn eq '16021300' }">
					<c:forEach items="${fileList}" var="fileList">		
						<c:if test="${fileList ne null }">
					 		<div class="thubfile">
					 			<strong>첨부파일</strong>
					 			<div>
						 			<a href="/common/board/Download.do?bcIdx=<c:out value="${fileList.bcIdx}"/>&amp;cbIdx=<c:out value="${fileList.cbIdx}"/>&amp;streFileNm=<c:out value="${fileList.streFileNm}"/>" target="_blank" title="파일다운로드">
										<i class="<c:out value="${fileList.fileExtsn}"/>">
											<span class="soundOnly">
												<c:out value="${fileList.fileExtsn}"/>파일
											</span>
											<span>
												<c:out value="${fileList.orignlFileNm }" />  <%-- [<c:out value="${bbs:byteCalculation(fileList.fileSize)}" />] --%>
											</span>		
										</i>
									</a>
								</div>
							</div>
						</c:if>
					</c:forEach>
				</c:when>
			</c:choose>
		</c:forEach>
	
		<c:if test="${prevNextList.PREV_IDX ne null || prevNextList.NEXT_IDX ne null}">
			<div class="divGroup cols2 pre_next_view">
				<c:if test="${prevNextList.NEXT_IDX ne null}">
					<div class="pre_view">
						<strong>이전글</strong>
						<a href="<c:url value='/site/${strDomain}/ex/bbs/View.do?cbIdx=${bbsFVo.cbIdx }&bcIdx=${prevNextList.NEXT_IDX }&tgtTypeCd=${bbsFVo.tgtTypeCd }&searchKey=${bbsFVo.searchKey}&pageIndex=${changePage.NEXT_PAGE_NUM }&searchDepth=${bbsFVo.searchDepth }'/>">
							<c:out value="${fn:replace(fn:replace(fn:replace(prevNextList.NEXT_SUB,'&','&amp;'),'<','&lt;'),'>','&gt;')}"/>
						</a>
					</div>
				</c:if>
				<c:if test="${prevNextList.NEXT_IDX eq null}">
					<div class="pre_view">
						<strong>이전글</strong>
						<a>이전글이 없습니다.</a>
					</div>
				</c:if>
				
				<c:if test="${prevNextList.PREV_IDX ne null}">
					<div class="next_view">
						<strong>다음글</strong>
						<a href="<c:url value='/site/${strDomain}/ex/bbs/View.do?cbIdx=${bbsFVo.cbIdx }&bcIdx=${prevNextList.PREV_IDX }&tgtTypeCd=${bbsFVo.tgtTypeCd }&searchKey=${bbsFVo.searchKey}&pageIndex=${changePage.PREV_PAGE_NUM}&searchDepth=${bbsFVo.searchDepth }'/>">
							<c:out value="${fn:replace(fn:replace(fn:replace(prevNextList.PREV_SUB,'&','&amp;'),'<','&lt;'),'>','&gt;')}"/>
						</a>
					</div>
				</c:if>
				<c:if test="${prevNextList.PREV_IDX eq null}">
					<div class="next_view">
						<strong>다음글</strong>
						<a>다음글이 없습니다.</a>
					</div>
				</c:if>
			</div>
			
		</c:if>
				
		<!-- 검색조건으로 게시글이1개일때 -->
		<c:if test="${prevNextList.PREV_IDX eq null and prevNextList.NEXT_IDX eq null}">
			<div class="divGroup cols2 pre_next_view">
					<div class="pre_view">
						<strong>이전글</strong>
						<a>이전글이 없습니다.</a>
					</div>
				
					<div class="next_view">
						<strong>다음글</strong>
						<a>다음글이 없습니다.</a>
					</div>
			</div>
		</c:if>
	
		<div class="btnArea right">
			<a href="#" class="btn_l">목록</a>
		</div>
		
	</div>
	
