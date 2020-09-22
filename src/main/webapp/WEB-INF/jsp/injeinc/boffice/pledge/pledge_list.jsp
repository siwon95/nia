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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>통합관리시스템</title>
<!-- 페이지 전용 스타일/스크립트 -->
<script>
$(function(){
	//시도 선택시 하위 시군구 나오도록
	$("#searchPlWiwid1").change(function(){
		$("#searchPlWiwid2").find("option:not(:first)").remove();
    	var ajaxParam = {"searchPlWiwid1":this.value};
    	var ajaxOption = {};
    	ajaxOption.success = function(data){
    		if(data.result){
    			$("#searchPlWiwid2").show();
    			if(data.plWiwidList2.length==0){
    				$("#searchPlWiwid2").hide();
    				return;
    			}
    			$.each(data.plWiwidList2, function (index, item) {
    				$("#searchPlWiwid2").append("<option value='"+item.wiwId+"'>"+item.wiwName+"</option>");
        		});
    		}
    	};
    	ajaxAction("<c:url value='/boffice/pledge/WiwIdListAx.do'/>", ajaxParam, ajaxOption);
	});
	$(".btn_orderBy").click(function(){
		var orderBy=$(this).attr("data-orderBy");
		$("#orderBy").val(orderBy);
		$("#PledgeVo").attr("action", "<c:url value='PledgeList.do' />").submit();
	});
	//버튼이벤트
	$(".btn_add").click(function(e){
		e.preventDefault();
		$("#PledgeVo #actionKey").val("regist");
		$("#PledgeVo #plIdx").val(0);
		var modal_id = "modal_add";
		var modal_url = "<c:url value='/boffice_nodeco/pledge/PledgeForm.do' />";
		var modal_param = $("#PledgeVo").serializeArray();
		var modal_class = "wide"; //wide, small
		ajaxModal(modal_id, modal_url, modal_param, modal_class);
	});
	//제목 클릭시 뷰페이지
	$(".btn_excel").click(function(e){
		e.preventDefault();
		$("#PledgeVo").attr("action", "<c:url value='PledgeExcel.do' />").submit();
		$("#PledgeVo").attr("action", "<c:url value='PledgeList.do' />");
	});
	//제목 클릭시 뷰페이지
	$(".link_view").click(function(e){
		e.preventDefault();
		var plIdx = $(this).closest("tr").attr("data-plIdx");
		$("#PledgeVo #plIdx").val(plIdx);
		var modal_id = "modal_view";
		var modal_url = "<c:url value='/boffice_nodeco/pledge/PledgeView.do' />";
		var modal_param = $("#PledgeVo").serializeArray();
		var modal_class = "wide"; //wide, small
		ajaxModal(modal_id, modal_url, modal_param, modal_class);
	});
});
</script>
<!-- //페이지 전용 스타일/스크립트 -->
</head>
<body>
	<div class="section">
		<form:form commandName="PledgeVo" id="PledgeVo" name="PledgeVo" action="PledgeList.do" method="post" class="searchListPage" onsubmit="return doSearch(this);">
			<form:hidden path="pageIndex" />
			<input type="hidden" id="plIdx" name="plIdx" value="0" />
			<input type="hidden" id="actionKey" name="actionKey" value=""/>
			<input type="hidden" id="orderBy" name="orderBy" value="<c:out value="${PledgeVo.orderBy}"/>"/>
			<input type="hidden" name="returnurl" value="/boffice/pledge/PledgeList.do?&pageIndex=<c:out value="${PledgeVo.pageIndex}"/>"/>
			<div class="search">
				<table>
					<caption>검색테이블</caption>
					<tbody>
						<tr>
							<th scope="col"><label for="searchPlWiwid1">지역</label></th>
							<td>
								<form:select path="searchPlWiwid1" class="w100">
									<form:option value="0" label="선택"/>
									<c:forEach items="${plWiwidList1}" var="result" >
										<form:option value="${result.wiwId}" label="${result.wiwName}"/>
									</c:forEach>
								</form:select>
								<form:select path="searchPlWiwid2" class="w100">
									<form:option value="0" label="선택"/>
									<c:forEach items="${plWiwidList2}" var="result" >
										<form:option value="${result.wiwId}" label="${result.wiwName}"/>
									</c:forEach>
								</form:select>
							</td>
							<th scope="col"><label for="searchStartDate">기간</label></th>
							<td>
								<label for="searchStartDate" class="hidden">기간(부터)</label>
								<form:input path="searchStartDate" size="10" class="useDatepicker dateFrom" /> ~ 
								<label for="searchEndDate" class="hidden">기간(까지)</label>
								<form:input path="searchEndDate" size="10" class="useDatepicker dateTo" />		
							</td>
						</tr>
						<tr>
							<th scope="col"><label for="searchCateCont">공약</label></th>
							<td>
								<form:select path="searchCateCont" >
									<form:option value="" label="선택"/>
									<c:forEach items="${plCateList}" var="result" >
										<form:option value="${result.code}" label="${result.codeName}"/>
									</c:forEach>
								</form:select>
							</td>
							<th scope="col"><label for="searchKeyword">검색</label></th>
							<td>
								<form:select path="searchCondition" >
									<form:option value="1" label="제목"/>
									<form:option value="2" label="내용"/>
									<form:option value="3" label="제목+내용"/>
									<form:option value="4" label="작성자"/>
								</form:select>
								 <label for="searchKeyword" class="hidden">검색어</label>
								<form:input path="searchKeyword" size="30"/>
							</td>
						</tr>	
					</tbody>		
				</table>
				<input type="submit" value="검색" class="btn_inline btn_search">
			</div>
		</form:form>
		<div class="tableTitle">
			<div class="btnArea right">
				<a href="#" data-orderby="1" class="btn_inline btn_orderBy<c:out value="${PledgeVo.orderBy=='1'?' on':''}"/>">등록일순</a>
				<a href="#" data-orderby="2" class="btn_inline btn_orderBy<c:out value="${PledgeVo.orderBy=='2'?' on':''}"/>">추천순</a>
				<a href="#" data-orderby="3" class="btn_inline btn_orderBy<c:out value="${PledgeVo.orderBy=='3'?' on':''}"/>">조회순</a>
			</div>
		</div>
		<div class="tableBox">
			<table class="list">
				<caption>공약은행 리스트</caption>
				<colgroup>
					<col class="w50">
					<col class="w100">
					<col class="w100">
					<col class="w100">
					<col>
					<col class="w90">
					<col class="w120">
					<col class="w50">
					<col class="w50">
				</colgroup>
				<thead>
					<tr>
						<th scope="col">번호</th>
						<th scope="col">시도</th>
						<th scope="col">구시군</th>
						<th scope="col">공약구분</th>
						<th scope="col">제목</th>
						<th scope="col">작성자</th>
						<th scope="col">등록일</th>
						<th scope="col">추천수</th>
						<th scope="col">조회수</th>
					</tr>
				</thead>
				<tbody>
					<c:set var="n" value="${resultCnt - (paginationInfo.currentPageNo-1)*paginationInfo.recordCountPerPage }"/>
					<c:forEach var="result" items="${resultList}" varStatus="status">
					<tr data-plIdx="<c:out value="${result.plIdx}"/>">
						<td><c:out value="${n - status.index}" /></td>
						<td><c:out value="${result.plWiwTxt1}" /></td>
						<td><c:out value="${result.plWiwTxt2}" /></td>
						<td><c:out value="${result.cateContTxt}" /></td>
						<td class="left">
							<a href="#" class="link_view">
								<c:out value="${result.subCont}" />
								<c:if test="${result.fileCnt > 0}"><img src="/images/cms/icon_file.gif" alt="첨부파일"/></c:if>
							</a>
						</td>
						<td><c:out value="${result.regName}" /></td>
						<td><c:out value="${result.regDt}" /></td>
						<td><c:out value="${result.recommendCont}" /></td>
						<td><c:out value="${result.countCont}" /></td>
					</tr>
					</c:forEach>
					<c:if test="${fn:length(resultList) == 0}">
						<tr>
							<td colspan="9" class="empty"><spring:message code="common.nodata.msg" /></td>
						</tr>
					</c:if>
				</tbody>
			</table>
		</div>
		<div class="paging">
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="doPageMove" />
		</div>
		<div class="tableTitle">
			<div class="btnArea right">
				<a href="#" class="btn_inline btn_excel">엑셀파일 다운로드</a>
				<a href="#" class="btn_inline btn_add">신규등록</a>
			</div>
		</div>
	</div>
</body>
</html>