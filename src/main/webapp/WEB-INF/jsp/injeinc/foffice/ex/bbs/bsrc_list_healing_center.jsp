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
$(document).ready(function() {
	var reTgtTypeCd = '${bbsFVo.searchArea}';
	$.ajax({
		type: "GET",
		url: "<c:url value='/site/{strDomain}/ex/bbs/CmmCodeAx.do'/>",
		data : {"cmmCode" : 'DRP_AREA' },
		dataType : "json",
		success:function(data){
		 	console.dir(data); 
		 	$("#searchArea").append("<option value='ALL'>전체</option>");
			$.each(data.cmmCode, function (index, item) {
				if (item.code == reTgtTypeCd) {
					$("#searchArea").append("<option value='"+item.code+"' selected>"+item.codeName+"</option>");
				}else{
					$("#searchArea").append("<option value='"+item.code+"'>"+item.codeName+"</option>");					
				}
			});
		 },
	       error: function(xhr,status,error){
	       	alert(status);
	       }
	 });
	
	$.ajax({
		type: "GET",
		url: "<c:url value='/site/{strDomain}/ex/bbs/CmmCodeAx.do'/>",
		data : {"cmmCode" : 'DRP_CENTER' },
		dataType : "json",
		success:function(data){
			$("#centerButton").append("<li class='active'><a href='/site/${strDomain}/ex/bbs/List.do?cbIdx=1105'>전체</a></li>");
			$.each(data.cmmCode, function (index, item) {
				var searchCenter = '${bbsFVo.searchCenter}';
				if(searchCenter !=item.code){
					$("#centerButton").append("<li><a href='/site/${strDomain}/ex/bbs/List.do?cbIdx=1105&searchCenter="+item.code+"'>"+item.codeName+"</a></li>");
				}else{
					$("#centerButton").append("<li class='active'><a href='/site/${strDomain}/ex/bbs/List.do?cbIdx=1105&searchCenter="+item.code+"'>"+item.codeName+"</a></li>");
				}  
	    		}); 
				if($("#centerButton").children('.active').length>1){
					$("#centerButton").children('.active').first().removeClass("active");
	  			}
		 }, 
	       error: function(xhr,status,error){
	       	alert(status);
	       }
	 });
});

//페이징처리
function doBbsFPag(pageIndex) {
	document.bbsFVo.pageIndex.value = pageIndex;
    document.bbsFVo.submit(); 
} 

//select box선택시 전달파라미터 설정
function drpArea() {
	$('#searchArea2').val($("#searchArea option:checked").val());  
}
</script>

<form:form commandName="bbsFVo" name="bbsFVo" method="post" onsubmit="return doSearch(this);">
<form:hidden path="pageIndex" />
<form:hidden path="bcIdx" />
<form:hidden path="mode" />


	<h3>관계기관/센터</h3>
	<!-- 기관별 버튼 생성  -->
	<!-- <ul class="tabBar" id="centerButton"></ul> -->
	
	<div class="board_list">
		<div class="board_search">
			<fieldset>
				<legend>관계기관/센터  게시물 검색</legend>
				<ul>
					<li>
						<label for="아이디값1">지역</label>
				 		<form:select title="조건선택" path="searchArea" id="searchArea" class="mr0" onchange="drpArea(this)"></form:select> 		 		
					</li>
				</ul>	
				<div>
					<label for="txt1">주소</label>
				 	<input type="text" name="searchKey" id="txt1" value="<c:out value='${bbsFVo.searchKey}' />" title="검색어입력" placeholder="검색어를 입력해주세요">
					<input type="submit" value="검색" title="검색">
				</div>
				<input type="hidden" name="searchArea2" id="searchArea2" value="ALL" >
			</fieldset>
		</div>
		<div style="clear:both"></div>
		
		<!-- 페이지번호 및 전체 건수 조회 -->
		<span class="count_total">
			총 <em><fmt:formatNumber value="${contentCnt}" /></em>건,  
			<em><c:out value="${paginationInfo.currentPageNo}"/></em> / <c:out value="${paginationInfo.totalPageCount}"/>페이지
		</span>
		
		<div class="div_table b_type4">
			<div class="div_caption">관계기관/센터 게시물 목록</div>
			<div class="div_table-column-group">
				<div class="div_col" style="width:8%"></div>
				<div class="div_col" style="width:20%"></div>
				<div class="div_col" style="width:15%"></div>
				<div class="div_col" style="width:7%"></div>
				<div class="div_col" style="width:30%"></div>
				<div class="div_col" style="width:10%"></div>
				<div class="div_col" style="width:10%"></div>
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
				<!-- 조회내용이 없는경우 표시 -->
				<c:if test="${contentCnt <= 0 }">
					<div class="data_none">등록된 내용이 없습니다.</div>
				</c:if>
				<c:forEach items="${contentList}" var="contentList" varStatus="status">
					<div class="div_tr">
						<div class="div_td"><c:out value="${(contentCnt+1)-(status.count+(bbsFVo.pageIndex-1)*bbsFVo.recordCountPerPage)}" /></div>
						<div class="div_td"><c:out value="${contentList.CENTER}" /></div>
						<div class="div_td"><c:out value="${contentList.DEPTH_1}" /></div>
						<div class="div_td"><c:out value="${contentList.AREA}" /></div>
						<div class="div_td"><c:out value="${contentList.ADDR_CONT}" /></div>
						<div class="div_td"><c:out value="${contentList.TEL_CONT}" /></div>
						<div class="div_td">
							<a class="baro" title="새창" target="blank" href=<c:out value="${contentList.EXT4}"/>>바로가기</a>
						</div>
					</div>
				</c:forEach>
			</div>	
		</div>
		<div class="paging">
			<ui:pagination paginationInfo="${paginationInfo}" type="drpImage" jsFunction="doBbsFPag" />
		</div>
	</div>
</form:form>