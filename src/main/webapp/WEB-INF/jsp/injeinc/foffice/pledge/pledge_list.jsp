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
	//시도 선택시 하위 시군구 나오도록
	$("#searchPlWiwid1").change(function(){
		$("#searchPlWiwid2").find("option:not(:first)").remove();
		$.ajax({
			type: "GET",
			url: "<c:url value='/site/${strDomain}/pledge/WiwIdListAx.do'/>",
			data : {"searchPlWiwid1":this.value},
			dataType : "json",
			success:function(data){
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
			 },
	        error: function(xhr,status,error){
	        	alert(status);
	        }
		 });
	});
	$(".btn_orderBy").click(function(e){
		e.preventDefault();
		var orderBy=$(this).attr("data-orderBy");
		$("#orderBy").val(orderBy);
		$("#PledgeFVo").attr("action", "<c:url value='List.do' />").submit();
	});
	$(".btn_add").click(function(e){
		e.preventDefault();
		var orderBy=$(this).attr("data-orderBy");
		$("#plIdx").val(0);
		$("#PledgeFVo").attr("action", "<c:url value='Form.do' />").submit();
	});
	$(".btn_add").click(function(e){
		e.preventDefault();
		var orderBy=$(this).attr("data-orderBy");
		$("#plIdx").val(0);
		$("#PledgeFVo").attr("action", "<c:url value='Form.do' />").submit();
	});
});
function doPledgeListPag(pageIndex) {
	$("#pageIndex").val(pageIndex);
	$("#PledgeFVo").attr("action", "<c:url value='/site/${strDomain}/pledge/List.do' />").submit();
}
</script>
<!-- //페이지 전용 스타일/스크립트 -->
<body>
	<c:set var="addParam" value="pageIndex=${PledgeFVo.pageIndex}&orderBy=${PledgeFVo.orderBy}&searchPlWiwid1=${PledgeFVo.searchPlWiwid1}&searchPlWiwid2=${PledgeFVo.searchPlWiwid2}&searchCateCont=${PledgeFVo.searchCateCont}&searchCondition=${PledgeFVo.searchCondition}&searchKeyword=${PledgeFVo.searchKeyword}&orderBy=${PledgeFVo.orderBy}"/>
	<form:form commandName="PledgeFVo" name="PledgeFVo" method="post" action="/site/${strDomain}/pledge/List.do">
		<form:hidden path="pageIndex" />
		<input type="hidden" id="plIdx" name="plIdx" value="0" />
		<input type="hidden" id="mode" name="mode" value=""/>
		<input type="hidden" id="orderBy" name="orderBy" value="<c:out value="${PledgeFVo.orderBy}"/>"/>
		<div class="bbsSearch">
			<span class="total">
				전체 <b class="txtBlue"><fmt:formatNumber value="${resultCnt}"></fmt:formatNumber></b>건,  
				<b><c:out value="${paginationInfo.currentPageNo}"/></b> / <c:out value="${paginationInfo.totalPageCount}"/>페이지
			</span>
			<div>
				<form:input path="searchStartDate" class="useDatepicker dateFrom" readonly="true" />
				<span>~</span>
				<form:input path="searchEndDate" class="useDatepicker dateTo" readonly="true" />		
				
				<form:select path="searchPlWiwid1" class="w20p">
					<form:option value="0" label="시도선택"/>
					<c:forEach items="${plWiwidList1}" var="result" >
						<form:option value="${result.wiwId}" label="${result.wiwName}"/>
					</c:forEach>
				</form:select>
				<form:select path="searchPlWiwid2" class="w20p">
					<form:option value="0" label="구시군선택"/>
					<c:forEach items="${plWiwidList2}" var="result" >
						<form:option value="${result.wiwId}" label="${result.wiwName}"/>
					</c:forEach>
				</form:select>
					
				<form:select path="searchCateCont" class="w20p">
					<form:option value="" label="공약전체"/>
					<c:forEach items="${plCateList}" var="result" >
						<form:option value="${result.code}" label="${result.codeName}"/>
					</c:forEach>
				</form:select>
			</div>
			<div>
				<form:select path="searchCondition" class="w15p">
					<form:option value="1" label="제목"/>
					<form:option value="2" label="내용"/>
					<form:option value="3" label="제목+내용"/>
					<form:option value="4" label="작성자"/>
				</form:select>
				
				<form:input path="searchKeyword" class="w50p" title="검색어입력" placeholder="검색어를 입력해주세요"/>
				
				<input type="submit" value="검색" title="검색">
				<a href="List.do" class="btn_inline">전체보기</a>
			</div>
		</div>
	</form:form>
	
	<div class="btnArea right">
		<a href="#" data-orderby="1" class="btn_inline btn_orderBy<c:out value="${PledgeFVo.orderBy=='1'?' on':''}"/>">등록일순</a>
		<a href="#" data-orderby="2" class="btn_inline btn_orderBy<c:out value="${PledgeFVo.orderBy=='2'?' on':''}"/>">추천순</a>
		<a href="#" data-orderby="3" class="btn_inline btn_orderBy<c:out value="${PledgeFVo.orderBy=='3'?' on':''}"/>">조회순</a>
	</div>
	<div class="tableBox">
		<table class="list">
			<caption>공약은행 리스트</caption>
			<colgroup>
				<col class="w50">
				<col class="w130">
				<col class="w130">
				<col class="w130">
				<col>
				<col class="w100">
				<col class="w120">
				<col class="w100">
				<col class="w100">
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
					<td class="title">
						<a href="View.do?plIdx=${result.plIdx}&${addParam}">
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
						<td colspan="9">등록된 내용이 없습니다.</td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</div>
	<div class="paging">
		<ui:pagination paginationInfo="${paginationInfo}" type="imagenew" jsFunction="doPledgeListPag" />
	</div>
	<div class="btnArea right">
		<a href="#" class="btn_inline btn_add">제안하기</a>
	</div>