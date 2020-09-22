<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>

<script type="text/javascript">
//수정페이지
function doBbsFMod(cbIdx, bcIdx){
	document.bbsFVo.cbIdx.value = cbIdx;
	document.bbsFVo.bcIdx.value = bcIdx;
	document.bbsFVo.action = "<c:url value='/site/${strDomain}/foffice/user/MyIntegMinwonBoardRegist.do' />";
	document.bbsFVo.mode.value = "U";
	document.bbsFVo.submit();
}

//목록
function doBbsFList(){
	document.bbsFVo.action = "<c:url value='/site/${strDomain}/foffice/user/MyIntegMinwonBoardList.do' />";
	document.bbsFVo.submit();
}

 //삭제
 function doBbsFRmv(cbIdx, bcIdx){
	if (!confirm("삭제하시겠습니까?")) {
		return;
	}	
	document.bbsFVo.cbIdx.value = cbIdx;
	document.bbsFVo.bcIdx.value = bcIdx;
	document.bbsFVo.action = "<c:url value='/site/${strDomain}/foffice/user/MyIntegMinwonBoardDelete.do' />";
	document.bbsFVo.submit();
	
}



</script>
<div class="board">
					
	<!-- 상세보기 상세정보 -->
	<table class="view">
		<caption><c:out value="${bbsFVo.cbName }" /> 상세정보 보기</caption>
		<colgroup>
			<col class="w15"><col class="w85">
		</colgroup>
		<tbody>
			<c:forEach items="${detailList}" var="detailList">
				<c:choose>
					
					<c:when test="${detailList.labelPropGbn eq '16020200'}">
						<tr>
							<th scope="row"><c:out value="${detailList.labelName }"></c:out></th>
							<td>
								<div class="view_contents">	
										<c:if test="${detailList.bbsTempletGbn eq '16050200' || detailList.bbsTempletGbn eq '16050300' || detailList.bbsTempletGbn eq '16050500' && fileImgList[0].bcIdx ne null }">
											<div class="photo-area">
											<c:forEach items="${fileImgList}" var="fileImgList">
												<img src="<c:out value='${fileImgList.fileStreCours}' /><c:out value='${fileImgList.streFileNm}' />" alt="">
												</br></br>
											</c:forEach>
											</div>
										</c:if>
									<div class="txt-area">
										<c:out value="${detailList.clobCont }" escapeXml="false"></c:out>
									</div>
								</div>
							</td>
						</tr>
					</c:when>
					
					
					
					<c:when test="${detailList.contentMapping eq 'M_LINK_CONT'  && detailList.bbsTempletGbn eq '16050400'  }">
						<tr>
							<th scope="row"><c:out value="${detailList.labelName }"></c:out></th>
							<td>
								<div class="view_contents">
									<div class="txt-area">
										<iframe width="640" height="390" src="https://www.youtube.com/embed/<c:out value='${detailList.dataContent }' ></c:out>" frameborder="0" allowfullscreen></iframe>
									</div>
								</div>
							</td>
						</tr>
					</c:when>
					
					
					
					<c:otherwise>
						<c:if test="${detailList.labelPropGbn ne '16020600' }">
							<tr>
								<th scope="row"><c:out value="${detailList.labelName }"></c:out></th>
								<c:choose>
									<c:when test="${detailList.contentMapping eq 'MW_OPENYN_CONT'  }">
										<td class="none">
											<c:if test="${detailList.dataContent eq 'AD_Y'}">
												<span class="adno-open">비공개</span>
											</c:if>
											<c:if test="${detailList.dataContent eq '21000200'}">
												<span class="open">공개</span>
											</c:if>
											<c:if test="${detailList.dataContent eq '21000100'}">
												<span class="no-open">비공개</span>
											</c:if>
										</td>
									</c:when>
									
									<c:when test="${detailList.contentMapping eq 'ANS_YN_CONT'  }">
										<td class="none">
											<c:if test="${detailList.dataContent eq '22000100'}">
												답변요청
											</c:if>
											<c:if test="${detailList.dataContent eq '22000200'}">
												답변 필요 없음
											</c:if>
										</td>
									</c:when>
									<c:when test="${detailList.contentMapping eq 'ANS_COMP_CONT'  }">
										<td class="none">
											<c:set var="ansCompCont" value="${fn:split(detailList.dataContent,',')}" />
											<c:forEach items="${ansCompCont}" var="ansCompCont">
												<c:if test="${ansCompCont eq '23000200'}">
													음성안내
												</c:if>
												<c:if test="${ansCompCont eq '23000100'}">
													문자안내
												</c:if>
												<c:if test="${ansCompCont eq '23000300'}">
													이메일안내
												</c:if>
											</c:forEach>
										</td>
									</c:when>
									<c:when test="${detailList.contentMapping eq 'MW_STATUS_CONT'  }">
										<td class="none">
											<c:if test="${detailList.dataContent eq '20000100'}">
												<span class="status-move">삭제</span>
											</c:if>
											<c:if test="${detailList.dataContent eq '20000200'}">
												<span class="status-move">비공개처리</span>
											</c:if>
											<c:if test="${detailList.dataContent eq '20000300'}">
												<span class="status-move">이관</span>
											</c:if>
											<c:if test="${detailList.dataContent eq '22000200'}">
												<span class="status-move">답변없음</span>
											</c:if>
											<c:if test="${detailList.dataContent eq '20000500'}">
												<span class="status-end">답변완료</span>
											</c:if>
											<c:if test="${detailList.dataContent eq '20000600'}">
												<span class="status-move">부서답변완료</span>
											</c:if>
											<c:if test="${detailList.dataContent eq '20000700'}">
												<span class="adno-open">중간답변</span>
											</c:if>
											<c:if test="${detailList.dataContent eq '20000800'}">
												<span class="status-ing">처리중</span>
											</c:if>
											<c:if test="${detailList.dataContent eq '20000900'}">
												<span class="status-re">접수중</span>
											</c:if>
										</td>
									</c:when>
									<c:otherwise>
										<td><c:out value="${fn:replace(detailList.dataContent, '|', ' ')}"/></td>
									</c:otherwise>
								</c:choose>
							</tr>
						</c:if>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			
			
			<!-- 첨부파일 -->
			<c:if test="${fileList[0].bcIdx ne null }">
				<tr class="m-block">
					<th scope="row">첨부파일</th>
					<td class="attach-file">
						<c:forEach items="${fileList}" var="fileList">
							<p><a href="/site/seocho/ex/bbs/fileDownload.do?bcIdx=<c:out value="${fileList.bcIdx }" />&cbIdx=<c:out value="${fileList.cbIdx }" />&fileNo=<c:out value="${fileList.fileNo }" />" class="file">
							<c:out value="${fileList.orignlFileNm }" />  [<fmt:formatNumber value="${fileList.fileSize}"></fmt:formatNumber> KB]
							</a>
							<a href="/common/board/DownloadView.do?cbIdx=<c:out value='${fileList.bcIdx }' />&amp;bcIdx=<c:out value='${fileList.cbIdx }' />&amp;streFileNm=<c:out value='${fileList.streFileNm }' />" title="새창" target="_blank" class="btn btn-quickView">바로보기</a>
							</p>
						</c:forEach>
					</td>
				</tr>
			</c:if>
			<!-- //첨부파일 -->
		</tbody>
	</table>
	

	
	<!--민원 상세보기 상세정보:답변 -->
	<c:forEach items="${minwonReList}" var="minwonReList">
		<br><br>
		<p id="b_summary2" class="hide">답변글의 제목,작성자,조회수,작성일 정보</p>
		<table class="view" aria-describedby="b_summary2">
			<caption>답변글정보 상세보기</caption>
			<colgroup>
				<col class="w15"><col><col class="w15"><col class="w35">
			</colgroup>
			<tbody>
				<tr>
					<th scope="row">답변제목</th>
					<td colspan="3"><c:out value="${minwonReList.mcTitle }" /></td>
				</tr>
				<tr>
					<th scope="row">담당자</th>
					<td><c:out value="${minwonReList.mcReplyer }" /></td>
					<th scope="row">담당자연락처</th>
					<td><c:out value="${minwonReList.mcTel1 }" /></td>
				</tr>
				<tr>
					<th scope="row">작성일</th>
					<td><c:out value="${minwonReList.regDt }" /></td>
					<th scope="row">상태</th>
					<td>답변완료</td>
				</tr>
				<tr>
					<th scope="row">답변내용</th>
					<td colspan="3">
						<!-- 상세내용 -->
						<div class="view_contents">			
							<div class="txt-area"><!-- 내용이 들어가는 자리 -->
								<c:forEach items="${fileMinwonImgList}" var="fileMinwonImgList">
										<c:if test="${minwonReList.mcIdx eq fileMinwonImgList.mcIdx}">
										<img src="<c:out value='${fileMinwonImgList.fileStreCours}' /><c:out value='${fileMinwonImgList.streFileNm}' />" alt="">
										</br></br>
									</c:if>
								</c:forEach>
								<c:out value="${minwonReList.contentClob }" escapeXml="false" />
								
							</div>
						</div>
						<!-- //상세내용 -->
					</td>
				</tr>
				<!-- 첨부파일 -->
				<c:forEach items="${minwonFileList}" var="minwonFileList">
					<c:if test="${minwonReList.mcIdx eq minwonFileList.mcIdx}">
						<tr class="m-block">
							<th scope="row">첨부파일 :</th>
							<td class="attach-file">
								<p><a href="/site/seocho/ex/bbs/fileDownload.do?bcIdx=<c:out value="${minwonFileList.bcIdx }" />&cbIdx=<c:out value="${minwonFileList.cbIdx }" />&fileNo=<c:out value="${minwonFileList.fileNo }" />&mcIdx=<c:out value="${minwonFileList.mcIdx }" />" class="file">
								<c:out value="${minwonFileList.orignlFileNm }" />  [<fmt:formatNumber value="${minwonFileList.fileSize}"></fmt:formatNumber> KB]
								</a>
								<a href="/common/board/DownloadView.do?cbIdx=<c:out value='${minwonFileList.bcIdx }' />&amp;bcIdx=<c:out value='${minwonFileList.cbIdx }' />&amp;streFileNm=<c:out value='${minwonFileList.streFileNm }' />" title="새창" target="_blank" class="btn btn-quickView">바로보기</a>
								</p>
							</td>
						</tr>
					</c:if>
				</c:forEach>
				<!-- //첨부파일 -->
			</tbody>
		</table>
	<!-- //상세보기 상세정보:답변 -->					
	
		
	</c:forEach>
<!-- 첨부파일 -->
<%-- 	<c:if test="${fileList[0].bcIdx ne null }">
		<div class="view-table-file">
			<dl>
				<dt>첨부파일 :</dt>
				<dd>
					<c:forEach items="${fileList}" var="fileList">
						<p><a href="/site/seocho/ex/bbs/fileDownload.do?bcIdx=<c:out value="${fileList.bcIdx }" />&cbIdx=<c:out value="${fileList.cbIdx }" />&fileNo=<c:out value="${fileList.fileNo }" />" class="file">
						<c:out value="${fileList.orignlFileNm }" />  [<fmt:formatNumber value="${fileList.fileSize}"></fmt:formatNumber> KB]
						</a>
						<a href="/common/board/DownloadView.do?cbIdx=<c:out value='${fileList.bcIdx }' />&amp;bcIdx=<c:out value='${fileList.cbIdx }' />&amp;streFileNm=<c:out value='${fileList.streFileNm }' />" title="새창" target="_blank" class="btn btn-quickView">바로보기</a>
						</p>
					</c:forEach>
				</dd>
			</dl>
		</div>
	</c:if> --%>
	<!-- //첨부파일 -->
	
	
	<c:if test="${fn:length(minwonReList[0].mcTitle) == 0 && bbsFVo.bbsTempletGbn eq '16050700'}">
		<br><br>
		<p id="b_summary2" class="hide">답변글의 제목,작성자,조회수,작성일 정보</p>
		<table class="view" aria-describedby="b_summary2">
			<caption>답변글정보 상세보기</caption>
			<colgroup>
				<col class="w15"><col><col class="w15"><col class="w35">
			</colgroup>
			<tbody>
				<tr>
					<th scope="row">답변제목</th>
					<td colspan="3"></td>
				</tr>
				<tr>
					<th scope="row">담당자</th>
					<td></td>
					<th scope="row">담당자연락처</th>
					<td></td>
				</tr>
				<tr>
					<th scope="row">작성일</th>
					<td></td>
					<th scope="row">상태</th>
					<td>답변완료</td>
				</tr>
				<tr>
					<th scope="row">답변내용</th>
					<td colspan="3">
						<div class="view_contents">			
							<div class="txt-area">
								답변을 처리중 입니다.
							</div>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	<!-- //상세보기 상세정보:답변 -->					
	</c:if>
	
	<!-- 버튼 -->
	<div class="btns aright">
		<%-- <c:if test="${bbsFVo.author eq 'Y'}">
			<a href="javascript:doBbsFAnSwer('${bbsFVo.cbIdx }', '${bbsFVo.bcIdx }','${detailList[0].parentSeq}', '${detailList[0].answerDepth}');" class="type1">답변</a>
		</c:if> --%>
			<c:if test="${detailList[0].regId eq bbsFVo.regId}" >
			<a href="javascript:doBbsFRmv('<c:out value="${bbsFVo.cbIdx }"/>', '<c:out value="${bbsFVo.bcIdx }"/>');" class="type1">삭제</a>
			<a href="javascript:doBbsFMod('<c:out value="${bbsFVo.cbIdx }"/>', '<c:out value="${bbsFVo.bcIdx }"/>');" class="type1">수정</a>
		</c:if>
		<a href="javascript:doBbsFList();" class="type2">목록</a>
	</div>
	<!-- //버튼 -->
		
		
	<form:form commandName="bbsFVo" name="bbsFVo" method="post" >
		<form:hidden path="cbIdx" />
		<form:hidden path="bcIdx" />
		<form:hidden path="mode" />
		<form:hidden path="parentSeq" />
		<form:hidden path="answerDepth" />
		<form:hidden path="answerStep" />
		<form:hidden path="pageIndex" />
		<form:hidden path="tgtTypeCd" />
		<form:hidden path="searchKey" />
	</form:form>
	<!-- 이전다음글 -->
	<%-- <div class="inner-list-table">
		<ul>
			<c:if test="${prevNextList.NEXT_IDX ne null}">
				<li class="prev">
					<span class="th">다음글</span>
					<span class="td-title"><a href="javascript:doPrevNext('${bbsFVo.cbIdx }', '${prevNextList.NEXT_IDX }');">${fn:replace(prevNextList.NEXT_SUB, "|", "<span class='reply'>답변</span>")}</a></span>
					<span class="td-date"><c:out value="${prevNextList.NEXT_REGDT }"></c:out></span>
				</li>
			</c:if>
			<c:if test="${prevNextList.PREV_IDX ne null}">
				<li class="next">
					<span class="th">이전글</span>
					<span class="td-title"><a href="javascript:doPrevNext('${bbsFVo.cbIdx }', '${prevNextList.PREV_IDX }');">${fn:replace(prevNextList.PREV_SUB, "|", "<span class='reply'>답변</span>")}</a></span>
					<span class="td-date">${prevNextList.PREV_REGDT }</span>
				</li>
			</c:if>
		</ul>
	</div> --%>
	<!-- //이전다음글 -->
	
</div><!-- //board -->
				