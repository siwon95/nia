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
<div class="bbsView">
	<div class="bbsViewTitle">
		<b>
			<c:set var="answerDepth" value=""/>
			<c:if test="${detailMap.ANSWER_DEPTH > 0}">
				<c:forEach begin="1" end="${detailMap.ANSWER_DEPTH}" step="1">
					<c:set var="answerDepth" value="${answerDepth }"/>
				</c:forEach>
				<span class="depthStep<c:out value="${answerDepth}" escapeXml="false"/>"></span>
			</c:if>
			<c:if test="${not empty detailMap.cateName}"><span class="category">[<c:out value="${detailMap.cateName}" />]</span></c:if>
			<c:if test="${not empty detailMap.subCont}"><c:out value="${fn:replace(fn:replace(fn:replace(detailMap.subCont,'&','&amp;'),'<','&lt;'),'>','&gt;')}" escapeXml="false"/></c:if>
		</b>
		<ul>
			<c:if test="${not empty detailMap.nameCont}"><li><c:out value="${detailMap.nameCont}" /></li></c:if>
			<c:if test="${not empty detailMap.regDt}"><li><c:out value="${detailMap.regDt}" /></li></c:if>
			<c:if test="${not empty detailMap.countCont}"><li>조회수 <c:out value="${detailMap.countCont}" /></li></c:if>
		</ul>
	</div>
	<div class="bbsViewContent">
		<div class="bbsViewInfo">
			<ul>
				<c:if test="${not empty detailMap.emailCont}"><li><span class="title">E-mail</span><c:out value="${detailMap.emailCont}"  /></li></c:if>
				<c:if test="${not empty detailMap.telCont}"><li><span class="title">연락처</span><c:out value="${detailMap.telCont}"  /></li></c:if>
				<c:if test="${not empty detailMap.phoneCont}"><li><span class="title">휴대폰번호</span><c:out value="${detailMap.phoneCont}" /></li></c:if>
				<c:if test="${not empty detailMap.addrCont}">
					<c:set var="arrAddr" value="${fn:split(detailMap.addrCont,'_')}"/>
					<li><span class="title">주소</span><c:out value="${arrAddr[1]}" /> <c:out value="${arrAddr[2]}" /></li>
				</c:if>
				<%-- <c:if test="${not empty detailMap.openYnCont}">
					<li>
						<span class="title">공개여부</span>
						<c:if test="${detailMap.openYnCont eq 'AD_Y'}">비공개</c:if>
						<c:if test="${detailMap.openYnCont eq '21000200'}">공개</c:if>
						<c:if test="${detailMap.openYnCont eq '21000100'}">비공개</c:if>
					</li>
				</c:if> --%>
				<c:if test="${not empty detailMap.ansYnCont}">
					<li>
						<span class="title">답변요청여부</span>
						<c:if test="${detailMap.ansYnCont eq '22000100' }">답변요청</c:if>
						<c:if test="${detailMap.ansYnCont eq '22000200' }">답변 요청 없음</c:if>
					</li>
				</c:if>
				<c:if test="${not empty detailMap.mwStatusCont}">
					<td>
						<span class="title">답변상태</span>
						<c:if test="${detailMap.mwStatusCont eq '22000200'}">답변없음</c:if>
						<c:if test="${detailMap.mwStatusCont eq '20000500'}">답변완료</c:if>
						<c:if test="${detailMap.mwStatusCont eq '20000800'}">처리중</c:if>
					</td>
				</c:if>
				<c:if test="${not empty detailMap.cdSubject}"><li><span class="title"></span><c:out value="${detailMap.cdSubject}" /></li></c:if>
				<c:if test="${not empty detailMap.changeNameCont}"><li><span class="title"></span><c:out value="${detailMap.changeNameCont}" /></li></c:if>
				<c:if test="${not empty detailMap.changeTelCont}"><li><span class="title"></span><c:out value="${detailMap.changeTelCont}" /></li></c:if>
				<c:if test="${not empty detailMap.mLinkCont}"><li><span class="title"></span><c:out value="${detailMap.mLinkCont}" /></li></c:if>
				<c:if test="${not empty detailMap.thumnailCont}"><li><span class="title"></span><c:out value="${detailMap.thumnailCont}" /></li></c:if>
				<c:if test="${not empty detailMap.subLinkCont}"><li><span class="title"></span><c:out value="${detailMap.subLinkCont}" /></li></c:if>
				<c:if test="${not empty detailMap.ansCompCont}"><li><span class="title"></span><c:out value="${detailMap.ansCompCont}" /></li></c:if>
				<c:if test="${not empty detailMap.ext1}"><li><span class="title"></span><c:out value="${detailMap.ext1}" /></li></c:if>
				<c:if test="${not empty detailMap.ext2}"><li><span class="title"></span><c:out value="${detailMap.ext2}" /></li></c:if>
				<c:if test="${not empty detailMap.ext3}"><li><span class="title"></span><c:out value="${detailMap.ext3}" /></li></c:if>
				<c:if test="${not empty detailMap.ext4}"><li><span class="title"></span><c:out value="${detailMap.ext4}" /></li></c:if>
				<c:if test="${not empty detailMap.ext5}"><li><span class="title"></span><c:out value="${detailMap.ext5}" /></li></c:if>
				<c:if test="${not empty detailMap.ext6}"><li><span class="title"></span><c:out value="${detailMap.ext6}" /></li></c:if>
				<c:if test="${not empty detailMap.ext7}"><li><span class="title"></span><c:out value="${detailMap.ext7}" /></li></c:if>
				<c:if test="${not empty detailMap.ext8}"><li><span class="title"></span><c:out value="${detailMap.ext8}" /></li></c:if>
				<c:if test="${not empty detailMap.ext9}"><li><span class="title"></span><c:out value="${detailMap.ext9}" /></li></c:if>
				<c:if test="${not empty detailMap.ext10}"><li><span class="title"></span><c:out value="${detailMap.ext10}" /></li></c:if>
			</ul>
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
				<c:when test="${detailList.labelPropGbn eq '16020600' || detailList.labelPropGbn eq '16021300'}">
					<div class="bbsViewFile">
						<ul>
							<c:forEach items="${fileList}" var="fileList">		
								<c:if test="${fileList ne null }">
							 		<li>
							 			<a href="/common/board/Download.do?bcIdx=<c:out value="${fileList.bcIdx}"/>&amp;cbIdx=<c:out value="${fileList.cbIdx}"/>&amp;streFileNm=<c:out value="${fileList.streFileNm}"/>" target="_blank" title="파일다운로드">
											<span class="icon_bbsFile <c:out value="${fileList.fileExtsn}"/>"></span>
											<c:out value="${fileList.orignlFileNm }" />  [<c:out value="${bbs:byteCalculation(fileList.fileSize)}" />]
										</a>
										<a href="/common/board/DownloadView.do?bcIdx=<c:out value="${fileList.bcIdx}"/>&amp;cbIdx=<c:out value="${fileList.cbIdx}"/>&amp;streFileNm=<c:out value="${fileList.streFileNm}"/>" target="_blank" title="새창" class="btn_s">바로보기</a>
									</li>
								</c:if>
							</c:forEach>
						</ul>
					</div>
				</c:when>
			</c:choose>
		</c:forEach>
		<c:if test="${not empty detailMap.clobCont || not empty detailMap.clobCont2}">
			<c:if test="${not empty detailMap.clobCont}">
				<div class="bbsViewEditor">
					<c:out value="${cmm:outClobCont(detailMap.clobCont)}" escapeXml="false" />
				</div>
			</c:if>
			<c:if test="${not empty detailMap.clobCont2}">
				<div class="bbsViewEditor">
					<c:out value="${cmm:outClobCont(detailMap.clobCont2)}" escapeXml="false" />
				</div>
			</c:if>
		</c:if>
		<c:if test="${prevNextList.PREV_IDX ne null || prevNextList.NEXT_IDX ne null}">
			<div class="bbsViewLink">
				<ul>
					<c:if test="${prevNextList.PREV_IDX ne null}">
						<li>
							<span class="title">다음글</span>
							<a href="<c:url value='/site/${strDomain}/ex/bbs/View.do?cbIdx=${bbsFVo.cbIdx }&bcIdx=${prevNextList.PREV_IDX }'/>">
								<c:out value="${fn:replace(fn:replace(fn:replace(prevNextList.PREV_SUB,'&','&amp;'),'<','&lt;'),'>','&gt;')}"/>
							</a>
						</li>
					</c:if>
					<c:if test="${prevNextList.NEXT_IDX ne null}">
						<li>
							<span class="title">이전글</span>
							<a href="<c:url value='/site/${strDomain}/ex/bbs/View.do?cbIdx=${bbsFVo.cbIdx }&bcIdx=${prevNextList.NEXT_IDX }'/>">
								<c:out value="${fn:replace(fn:replace(fn:replace(prevNextList.NEXT_SUB,'&','&amp;'),'<','&lt;'),'>','&gt;')}"/>
							</a>
						</li>
					</c:if>
				</ul>
			</div>
		</c:if>
	</div>
	<c:if test="${bbsFVo.commentYn eq 'Y'}">
		<div id="comment_box">
			<jsp:include page="comment_list.jsp" flush="true"/>
		</div>
	</c:if>
	<div class="btnArea">
		<c:if test="${bbsFVo.answerGbn ne '16010500' && bbsFVo.author eq 'Y'}">
			<a href="#" class="btn_l on w100 btn_bbsAnswer">답변</a>&nbsp;
		</c:if>
		<c:if test="${bbsFVo.dupcode ne null && detailList[0].dupcode eq bbsFVo.dupcode}" >
			<c:if test="${bbsFVo.cbIdx ne 414 && MinwonMap.ansRYn eq 'N'}">
				<a href="#" class="btn_l w100 btn_bbsDelete">삭제</a>&nbsp;
				<a href="#" class="btn_l on w100 btn_bbsModify">수정</a>&nbsp;
			</c:if>
		</c:if>
		<a href="#" class="btn_l w100 btn_bbsList">목록</a>
	</div>
</div>
<div id="modal_password" class="modalWrap small">
	<div class="modalTitle">
		<h2>비밀번호 확인</h2>
		<a href="#" class="btn_modalClose">모달팝업닫기</a>
	</div>
	<div class="modalContent">
	<form id="bbsPassword" name="bbsPassword" method="post" onsubmit="return bbsPasswordForm(this);">
		<input type="password" name="gubPassword" id="gubPassword" class="w200"/>
		<input type="submit" value="확인" class="btn_inline" />
	</form>
	</div>
</div>