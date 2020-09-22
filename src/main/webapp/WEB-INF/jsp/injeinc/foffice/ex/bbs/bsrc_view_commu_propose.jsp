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
function moveList(){
	var detailType = '${bbsFVo.detailType}';
	if (detailType=='A') {//제안하기로 이동
		document.bbsFVo.action = "<c:url value='/site/${strDomain}/ex/bbs/List.do?cbIdx=${bbsFVo.cbIdx}&searchKind=${bbsFVo.searchKind}' />";
		document.bbsFVo.submit();		
	}else if (detailType=='B') {//내가쓴글로 이동
		document.bbsFVo.action = "<c:url value='/site/${strDomain}/ex/bbs/List.do?cbIdx=1185&regId=${ss_id}' />";
		document.bbsFVo.submit();
	}
	
}

function deleteCheck(){
	if(!confirm("삭제하시겠습니까?")){
		return;
	} 
	location.href = "<c:url value='Delete.do?cbIdx=1141&bcIdx=${bbsFVo.bcIdx}&dupcode=${bbsFVo.dupcode}'/>";
}

function checkId(regId) {
	var sessionId = "${ss_id}";
	if (sessionId !== regId) {
		event.preventDefault();
		alert("비밀글은 본인만 확인 할 수 있습니다.");
	}
}

</script>
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
		<h3>제안하기</h3>
			<!-- 질문내용 -->
			<div class="board_view">
				<div class="tit">
					<strong><c:out value="${fn:replace(fn:replace(fn:replace(detailMap.subCont,'&','&amp;'),'<','&lt;'),'>','&gt;')}" escapeXml="false"/></strong>
					<span>
						<span><c:out value="${detailMap.kinds}" /></span>
						<span><c:out value="${detailMap.nameCont}" /></span>
						<span><c:out value="${detailMap.regDt}" /></span>
						<span><c:out value="${detailMap.countCont}" /></span>
					</span>
				</div>
				<div class="view_content">
					<c:out value="${cmm:outClobCont(detailMap.clobCont)}" escapeXml="false" />
				</div>
				<c:if test="${fileList[0].orignlFileNm ne null}">
					<div class="thubfile">
						<strong>첨부파일</strong>
						<div>
							<c:forEach items="${fileList}" var="fileList">		
								<c:if test="${fileList ne null }">
							 		<a href="/common/board/Download.do?bcIdx=<c:out value="${fileList.bcIdx}"/>&amp;cbIdx=<c:out value="${fileList.cbIdx}"/>&amp;streFileNm=<c:out value="${fileList.streFileNm}"/>" target="_blank" title="파일다운로드">
										<i class="<c:out value="${fileList.fileExtsn}"/>">
											<span class="soundOnly"><c:out value="${fileList.fileExtsn}"/>파일</span>
											<span><c:out value="${fileList.orignlFileNm }" /></span> 
											 <%-- [<c:out value="${bbs:byteCalculation(fileList.fileSize)}" />]</span> --%>									
										</i>
									</a>
								</c:if>
							</c:forEach>
						</div>
					</div>
				</c:if>
				
				<!-- 이전글, 다음글, 목록 버튼 -->
				<c:if test="${prevNextList.PREV_IDX ne null || prevNextList.NEXT_IDX ne null}">
					<div class="divGroup cols2 pre_next_view">
						<div class="pre_view">
							<c:choose>
							<c:when test="${prevNextList.NEXT_IDX ne null}">
								<strong>이전글</strong>
								<a href="<c:url value='/site/${strDomain}/ex/bbs/View.do?cbIdx=${bbsFVo.cbIdx}&bcIdx=${prevNextList.NEXT_IDX}&tgtTypeCd=${bbsFVo.tgtTypeCd}&searchKey=${bbsFVo.searchKey}&searchKind=${bbsFVo.searchKind}&detailType=A&pageIndex=${changePage.NEXT_PAGE_NUM}'/>"
												onclick="checkId('${changePage.NEXT_REG_ID}');">
					                <c:out value="${fn:replace(fn:replace(fn:replace(prevNextList.NEXT_SUB,'&','&amp;'),'<','&lt;'),'>','&gt;')}"/>
					            </a>
							</c:when>
							<c:otherwise>
								<strong>이전글</strong>
								<a href="">이전글이 없습니다.</a>
							</c:otherwise>
							</c:choose>
						</div>
						<div class="next_view">
							<c:choose>
								<c:when test="${prevNextList.PREV_IDX ne null}">
									<strong>다음글</strong>
									<a href="<c:url value='/site/${strDomain}/ex/bbs/View.do?cbIdx=${bbsFVo.cbIdx}&bcIdx=${prevNextList.PREV_IDX}&tgtTypeCd=${bbsFVo.tgtTypeCd}&searchKey=${bbsFVo.searchKey}&searchKind=${bbsFVo.searchKind}&detailType=A&pageIndex=${changePage.PREV_PAGE_NUM}'/>"
													onclick="checkId('${changePage.PREV_REG_ID}');">
					                   <c:out value="${fn:replace(fn:replace(fn:replace(prevNextList.PREV_SUB,'&','&amp;'),'<','&lt;'),'>','&gt;')}"/>
					                </a>	
								</c:when>
								<c:otherwise>
									<strong>다음글</strong>
									<a href="">다음글이 없습니다.</a>
								</c:otherwise>
							</c:choose>
						</div>
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
			</div>	
				<!-- 답변 -->
				<c:if test="${reDetailMap ne null}">
					<div class="reple_state">
						<ul>
							<li>
								<strong>답변자</strong>
								<div>${reDetailMap.nameCont}</div>
								<strong>소속</strong>
								<div>${reDetailMap.CENTER}</div>
							</li>
							<li>
								<strong>답변내용</strong>
								<div class="w86p">
									<c:out value="${cmm:outClobCont(reDetailMap.clobCont)}" escapeXml="false" />
								</div>
							</li>
							<c:if test="${refileList[0].orignlFileNm ne null}">
								<li class="thubfile">
									<strong>첨부파일</strong>
									<div>
										<c:forEach items="${refileList}" var="refileList">		
											<c:if test="${refileList ne null }">
										 		<a href="/common/board/Download.do?bcIdx=<c:out value="${refileList.bcIdx+1}"/>&amp;cbIdx=<c:out value="${refileList.cbIdx}"/>&amp;streFileNm=<c:out value="${refileList.streFileNm}"/>" target="_blank" title="파일다운로드">
													<i class="<c:out value="${refileList.fileExtsn}"/>">
														<span class="soundOnly"><c:out value="${fileList.fileExtsn}"/>파일</span>
														<span><c:out value="${refileList.orignlFileNm }" /></span>	
														<%-- [<c:out value="${bbs:byteCalculation(refileList.fileSize)}" />] --%>								
													</i>
												</a>
											</c:if>
										</c:forEach>
									</div>
								</li>
							</c:if>
						</ul>
					</div>
				</c:if>
			<div class="btnArea right">
				<a href="#" class="btn_l" onclick="moveList();">목록</a>
				<a href="Regist.do?cbIdx=1141&bcIdx=${bbsFVo.bcIdx}&mode=U" class="btn_l on">수정</a>
				<a href="#" class="btn_l off" onclick="deleteCheck();">삭제</a>
			</div>