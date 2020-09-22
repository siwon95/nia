<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator"
	uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="cmm" uri="http://cms.injeinc.co.kr/cmm"%>
<%@ taglib prefix="bbs" uri="http://cms.injeinc.co.kr/bbs"%>

<script>
	$(function() {
		var searchForm = document.bbsFVo;
		$(".btn_bbsWrite")
				.click(
						function(e) {
							e.preventDefault();
							searchForm.action = "<c:url value='/site/${strDomain}/ex/bbs/Regist.do' />";
							searchForm.mode.value = "C";
							$(searchForm).submit();
						});

		<c:out value="${bbsFVo.readGbn }" />
		$(".btn_bbsDetail").click(function(e) {
			var authResult = doBoardAuthorChk(gbn);
			if (!authResult) {
				e.preventDefault();
				alert("권한이 없습니다.");
			}
		});
	});

	//권한 체크
	function doBoardAuthorChk() {
		return true;
	}
	//페이징처리
	function doBbsFPag(pageIndex) {
		/* var cbIdx = "<c:out value='${bbsFVo.cbIdx}' />";
		location.href = "<c:url value='/site/${strDomain}/ex/bbs/List.do?cbIdx=' />"+cbIdx+"&pageIndex="+pageIndex; */
		document.bbsFVo.pageIndex.value = pageIndex;
		document.bbsFVo.submit();
	}
</script>
<body>

	<form:form commandName="bbsFVo" name="bbsFVo" method="post"
		onsubmit="return doSearch(this);">
		<form:hidden path="pageIndex" />
		<%-- <form:hidden path="cbIdx" /> --%>
		<form:hidden path="bcIdx" />
		<form:hidden path="mode" />

		<h3>
			<c:out value="${bbsFVo.cbName }" />
		</h3>
		<div class="outline_info_bg gambl_icon_bg">
			<div>
				<span>
					도박중독 추방의 날(매년 9월 17일)과 도박문제 인식주간을 지정하여 도박중독의 폐해 및 심각성을 홍보하여대국민 인식 제고를 도모합니다.<br>
					도박문제 인식주간에는 도박중독 추방의 날 기념식과 국내외 도박문제 유관기관, 단체, 학계, 사행사업자, 대학생 등을 대상으로<br>
					도박문제 예방치유를 위한 다양한 주제로 국제 심포지엄 개최 및 도박문제 예방분위기 확산을 위해국민들이 참여할 수 있는 다양한 문화행사가 개최됩니다.
				</span>
			</div>
		</div>
		<div class="board_list">

			<div class="div_table b_type5">
				<div class="div_caption"></div>
				<div class="div_table-column-group">
					<div class="div_col" style="width: 8%"></div>
					<div class="div_col" style="width: 69%"></div>
					<div class="div_col" style="width: 13%"></div>
					<div class="div_col" style="width: 10%"></div>
				</div>

				<div class="div_thead">
					<div class="div_tr">
						<c:forEach items="${labelList}" var="labelList">
							<div class="div_th">
								<c:out value="${labelList.labelName}" />
							</div>
						</c:forEach>
					</div>
				</div>

				<div class="div_tbody">
					<!-- 게시글이 없는경우 -->
					<c:if test="${contentCnt <= 0 }">
						<div class="data_none">등록된 내용이 없습니다.</div>
					</c:if>

					<!-- 
					조회목록 화면 
					NO_CONT			--번호
					SUB_CONT		--제목
					REG_DT			--등록일	
					COUNT_CONT		--조회수
					-->
					<c:forEach items="${contentList}" var="contentList"
						varStatus="status">
						<div class="div_tr">

							<div class="div_td">
								<c:out value="${(contentCnt+1)-(status.count+(bbsFVo.pageIndex-1)*bbsFVo.recordCountPerPage)}" />
							</div>
							<div class="div_td left">
								<a
									href="<c:url value='/site/${strDomain}/ex/bbs/View.do?cbIdx=${contentList.CB_IDX }
																						&bcIdx=${contentList.BC_IDX }
																						&pageIndex=${paginationInfo.currentPageNo}
																						&tgtTypeCd=${bbsFVo.tgtTypeCd }
																						&searchKey=${bbsFVo.searchKey}' />"
									class="btn_bbsDetail"> <c:out
										value="${fn:replace(fn:replace(fn:replace(contentList.SUB_CONT,'&','&amp;'),'<','&lt;'),'>','&gt;')}"
										escapeXml="false" />
								</a>
							</div>
							<div class="div_td">
								<c:out value="${contentList.REG_DT}" />
							</div>
							<div class="div_td">
								<c:out value="${contentList.COUNT_CONT}" />
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>

		<div class="paging">
			<ui:pagination paginationInfo="${paginationInfo}" type="drpImage" jsFunction="doBbsFPag" />
		</div>

	</form:form>