<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<title>Basic Board List</title>
<script type="text/javascript">

/* 목록 */
function doList(cbIdx, bcIdx, searchBbsGroup, searchBbs){
	document.BoardAdminVo.cbIdx.value = cbIdx;
	document.BoardAdminVo.bcIdx.value = bcIdx;
	document.getElementById("searchBbsGroup").value = searchBbsGroup;
	document.getElementById("searchBbs").value = searchBbs;
	
	document.BoardAdminVo.action = "<c:url value='/boffice/ex/bbs/boardAdmin/boardAdmin_list.do'/>";
	document.BoardAdminVo.submit();
}

/* 파일 다운로드 */
function doFileDownLoad(fileNo){
	document.BoardAdminVo.fileNo.value = fileNo;
	document.BoardAdminVo.action = "<c:url value='/boffice/ex/bbs/boardAdmin/boardAdmin_fileDownload.do'/>";
	document.BoardAdminVo.submit();
}

/* 수정 */
function doUpdate(){
	document.BoardAdminVo.mode.value ="U";
	document.BoardAdminVo.action = "<c:url value='/boffice/ex/bbs/boardAdmin/boardAdmin_RegForm.do'/>";
	document.BoardAdminVo.submit();
}

/* 답변 */
function doAnswer(cbIdx, answerStep, parentSeq, answerDepth){
	document.BoardAdminVo.cbIdx.value = cbIdx;
	document.BoardAdminVo.answerStep.value = answerStep;
	document.BoardAdminVo.parentSeq.value = parentSeq;
	document.BoardAdminVo.answerDepth.value = answerDepth;
	
	document.BoardAdminVo.mode.value ="R";
	document.BoardAdminVo.action = "<c:url value='/boffice/ex/bbs/boardAdmin/boardAdmin_RegForm.do'/>";
	document.BoardAdminVo.submit();
}

/* 이관 */
function doTrans(cbIdx,bcIdx){
	$.ajax({
		type: "GET",
		url: "<c:url value='/boffice/ex/bbs/boardAdmin/TransAx.do'/>",
		data : {"cbIdx" : cbIdx
			   , "bcIdx" : bcIdx
			   , "transCbIdx" : "230"
			   },
		dataType : "json",
		success:function(object){
			alert("이관 되었습니다.");
			location.reload();
		 },
	    error: function(xhr,status,error){
	    	alert(status);
	    }
	});
}

/* 복사 */
function doCopy(){
	
}

/* 공지설정 및 해제 */
function doOpenYn(gbn){
	
	var msg = "";
	if(gbn == "Y"){
		msg = "해당 게시물을 공지사항으로 등록하겠습니까?";
	}else if(gbn == "N"){
		msg = "해당 게시물을 공지사항에서 취소하겠습니까?";
	}
	if (!confirm(msg)) {
		return;
	}
	document.BoardAdminVo.gbnVal.value = "notiYn";
	document.BoardAdminVo.notiYn.value = gbn;
	document.BoardAdminVo.action = "<c:url value='/boffice/ex/bbs/boardAdmin/boardAdmin_BbsMod.do'/>";
	document.BoardAdminVo.submit();
	
	
}

/* 인쇄 */
function doPrint(){
	window.print();
}

/* 승인 */
function doConf(gbn){
	document.BoardAdminVo.gbnVal.value = "apprYn";
	document.BoardAdminVo.apprYn.value = gbn;
	document.BoardAdminVo.action = "<c:url value='/boffice/ex/bbs/boardAdmin/boardAdmin_BbsMod.do'/>";
	document.BoardAdminVo.submit();
}

/* 민원글 삭제 및 복구 */
/* function doMwYn(gbn){
// 	document.BoardAdminVo.gbnVal.value = "mwDelYn";
// 	document.BoardAdminVo.bcDelYn.value = gbn;
	document.BoardAdminVo.action = "<c:url value='/boffice/ex/bbs/boardAdmin/boardAdmin_BbsMod.do'/>";
	document.BoardAdminVo.submit();
} */

//민원 삭제
function doMwRmv(){
		var url = "<c:url value='/boffice_nodeco/ex/bbs/boardAdmin/MwRmvPop.do' />";
		var width="450";
		var height="350";
		var form = document.BoardAdminVo;
		doCommonPop(form,'popup',url,width,height);
}

//민원 복구
function doMwRcv(cbIdx,bcIdx){
	if(!confirm("복구하시겠습니까?")){
		return;
	}
	$.ajax({
		type: "GET",
		url: "<c:url value='/boffice/ex/bbs/boardAdmin/Mw_RcvAx.do'/>",
		data : {"cbIdx" : cbIdx
			   , "bcIdx" : bcIdx
			   },
		dataType : "json",
		success:function(object){
			alert("복구되었습니다.");
			location.reload();
		 },
	    error: function(xhr,status,error){
	    	alert(status);
	    }
	});
}



$(window).load(function() {
	
});
</script>
<style type="text/css">
	.view1{ width:100%; margin-bottom:10px;border-top:2px solid #5a5a5a; }
	.view1 tr td:first-child{text-align:center;background-color:#f4f4f4;}	
	.view1 td{padding:10px; border-bottom:1px solid #d1cdc9;}
</style>
</head>
<body style="text-align:center; margin:0 auto; display:inline; padding-top:100px;">
<form:form commandName="BoardAdminVo" name="BoardAdminVo" method="post" >
<%-- <input type="hidden" name="cbIdx" id="cbIdx" value="<c:out value="${cbIdx}"/>"/>
<input type="hidden" name="bcIdx" id="bcIdx" value="<c:out value="${bcIdx}"/>"/> --%>
<%-- <input type="hidden" name="answerDepth" id="answerDepth" value="<c:out value="${boardView[0].ANSWER_DEPTH}"/>"/> --%>
<!-- <input type="hidden" name="answerDepthGbn" id="answerDepthGbn" /> -->
<input type="hidden" name="searchBbsGroup" id="searchBbsGroup" value="<c:out value="${searchBbsGroup}"/>" />
<input type="hidden" name="searchBbs" id="searchBbs" value="<c:out value="${searchBbs}"/>" />
<input type="hidden" name="searchBbsGroupText" id="searchBbsGroupText" value="<c:out value="${searchBbsGroupText}"/>" />
<input type="hidden" name="searchBbsText" id="searchBbsText" value="<c:out value="${searchBbsText}"/>" />
<input type="hidden" name="bbsFileCnt" id="bbsFileCnt" value="<c:out value="${bbsFileCnt}"/>" />

<form:hidden path="gbnVal" />
<form:hidden path="fileNo" />
<form:hidden path="notiYn" />
<form:hidden path="apprYn" />
<form:hidden path="cbIdx" />
<form:hidden path="bcIdx" />
<form:hidden path="mode" />
<form:hidden path="parentSeq" />
<form:hidden path="answerDepth" />
<form:hidden path="answerStep" />

<div class="board board-list">
	<div class="title">
			<h2>통합게시물관리 &gt;</h2>
			<h3>통합게시물관리 상세보기</h3>
		</div>
	<table summary="" class="write">
		<caption></caption>
		<colgroup>
			<col width="15%" />
			<col width="*" />
		</colgroup>
		
		<tbody>
			<tr>
				<th>위치</th>
				<td><b>[그룹] </b><c:out value="${searchBbsGroupText}"/> -> <b>[게시판] </b><c:out value="${searchBbsText}"/></td>
			</tr>
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
										<c:out value="${detailList.clobCont }" escapeXml="false"/>
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
										<iframe width="640" height="390" src="https://www.youtube.com/embed/<c:out value='${detailList.content }' />" frameborder="0" allowfullscreen></iframe>
										<c:if test="${detailList.captionCont ne null}">
											<textarea class="movie-txt" readonly rows="7"><c:out value="${detailList.captionCont}"/></textarea>
										</c:if>
									</div>
								</div>
							</td>
						</tr>
					</c:when>
					
					
					
					<c:otherwise>
						<c:if test="${detailList.labelPropGbn ne '16020600' && detailList.labelPropGbn ne '16021000' }">
							<tr>
								<th scope="row"><c:out value="${detailList.labelName }"></c:out></th>
								<c:choose>
									<c:when test="${detailList.contentMapping eq 'MW_OPENYN_CONT'  }">
										<td class="none">
											<c:if test="${detailList.content eq 'AD_Y'}">
												<span class="adno-open">비공개</span>
											</c:if>
											<c:if test="${detailList.content eq '21000200'}">
												<span class="open">공개</span>
											</c:if>
											<c:if test="${detailList.content eq '21000100'}">
												<span class="no-open">비공개</span>
											</c:if>
										</td>
									</c:when>
									
									<c:when test="${detailList.contentMapping eq 'ANS_YN_CONT'  }">
										<td class="none">
											<c:if test="${detailList.content eq '22000100'}">
												답변요청
											</c:if>
											<c:if test="${detailList.content eq '22000200'}">
												답변 필요 없음
											</c:if>
										</td>
									</c:when>
									<c:when test="${detailList.contentMapping eq 'ANS_COMP_CONT'  }">
										<td class="none">
											<c:set var="ansCompCont" value="${fn:split(detailList.content,',')}" />
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
											<c:if test="${detailList.content eq '20000100'}">
												<span class="status-move">삭제</span>
											</c:if>
											<c:if test="${detailList.content eq '20000200'}">
												<span class="status-move">비공개처리</span>
											</c:if>
											<c:if test="${detailList.content eq '20000300'}">
												<span class="status-move">이관</span>
											</c:if>
											<c:if test="${detailList.content eq '20000400'}">
												<span class="status-move">답변없음</span>
											</c:if>
											<c:if test="${detailList.content eq '20000500'}">
												<span class="status-end">답변완료</span>
											</c:if>
											<c:if test="${detailList.content eq '20000600'}">
												<span class="status-move">부서답변완료</span>
											</c:if>
											<c:if test="${detailList.content eq '20000700'}">
												<span class="adno-open">중간답변</span>
											</c:if>
											<c:if test="${detailList.content eq '20000800'}">
												<span class="status-ing">처리중</span>
											</c:if>
											<c:if test="${detailList.content eq '20000900'}">
												<span class="status-re">접수중</span>
											</c:if>
										</td>
									</c:when>
									<c:otherwise>
										<td><c:out value='${fn:replace(detailList.content, "|", " ")}'/></td>
									</c:otherwise>
								</c:choose>
							</tr>
						</c:if>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</tbody>
		
	</table>
	
	<!-- 첨부파일 -->
	<c:if test="${boardFileList[0].bcIdx ne null }">
	<table class="write">
		<caption></caption>
		<colgroup>
			<col width="15%" />
			<col width="*" />
		</colgroup>
		
		<tbody>
		<tr>
			<th>첨부파일</th>
			<td>
				<c:forEach items="${boardFileList}" var="fileList">
					<p><a href="/boffice/ex/bbs/boardAdmin/boardAdmin_fileDownload.do?bcIdx=<c:out value="${fileList.bcIdx }" />&cbIdx=<c:out value="${fileList.cbIdx }" />&fileNo=<c:out value="${fileList.fileNo }" />" class="file">
					<c:out value="${fileList.orignlFileNm }" />  [<fmt:formatNumber value="${fileList.fileSize}"></fmt:formatNumber> KB]
					</a><%-- <a href="/common/docuzen/preImageFromDoc.do?filePath=<c:out value='${fileList.fileStreCours }' />&filename=<c:out value='${fileList.streFileNm }' />" title="새창" class="view-direct">바로보기</a> --%></p>
				</c:forEach>
			</td>
		</tr>
		</tbody>
	</table>
	</c:if>
	<!-- //첨부파일 -->
	
	<!-- 버튼 -->
	<div class="btn_zone">
		<c:choose>
			<c:when test="${ detailMap.notiYn eq null || detailMap.notiYn eq 'N' }">
			<a href="javascript:doOpenYn('Y');" class="btn2">공지설정</a>
			</c:when>
			<c:when test="${detailMap.notiYn eq 'Y'}">
			<a href="javascript:doOpenYn('N');" class="btn2">공지해제</a>
			</c:when>
		</c:choose>
		<a href="javascript:doPrint();" class="btn2">인쇄</a>
		<c:if test="${detailMap.apprYn eq 'N' || detailMap.apprYn eq null}">
		<a href="javascript:doConf('Y');" class="btn2">승인</a>
		</c:if>
		<c:choose>
			<c:when test="${detailMap.mwStatusCont eq null || detailMap.mwStatusCont eq '' || detailMap.mwStatusCont eq '20000900'}">
			<a href="javascript:doMwRmv();" class="btn2">민원글 삭제</a>
			</c:when>
			<c:when test="${detailMap.mwStatusCont eq '20000100'}">
			<a href="javascript:doMwRcv('<c:out value="${cbIdx}"/>','<c:out value="${bcIdx}"/>');" class="btn2">민원글 복구</a>	
			</c:when>
		</c:choose>
		<!-- <a href="javascript:doCopy();" class="btn2">복사</a> -->
		<c:if test="${detailMap.mwStatusCont eq null || detailMap.mwStatusCont eq '' || detailMap.mwStatusCont eq '20000900'}">
			<a href="javascript:doTrans('<c:out value="${cbIdx}"/>','<c:out value="${bcIdx}"/>');" class="btn2">구청장에게바란다 이관</a>
		</c:if>
		<a href="javascript:doAnswer('<c:out value="${boardAdminVo.cbIdx }"/>', '<c:out value="${detailList[0].answerDepth}"/>','<c:out value="${detailList[0].parentSeq}"/>', '<c:out value="${detailList[0].answerDepth}"/>');" class="btn2">답변</a>
		<a href="javascript:doUpdate();" class="btn2">수정</a>
		<a href="javascript:doList('<c:out value="${boardAdminVo.cbIdx}"/>', '<c:out value="${boardAdminVo.bcIdx}"/>','<c:out value="${searchBbsGroup}"/>','<c:out value="${searchBbs}"/>');" class="btn2">목록</a>

	</div>
	<!-- //버튼 -->
	
</div>
</form:form>
</body>
</html>
