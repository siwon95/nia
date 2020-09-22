<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%-- ------------------------------------------------------------
- 제목 : 직원정보관리
- 파일명 : emp_list.jsp
- 최종수정일 : 2019-04-29
- 상태 : 1차완료
-------------------------------------------------------------- --%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>통합관리시스템</title>

<!-- 페이지 전용 스타일/스크립트 -->
<script>
$(function(){
	<c:if test="${not empty message}">
	resultMessage("<spring:message code="${message}" />");
	</c:if>
	<c:if test="${not empty param.message}">
	resultMessage("<spring:message code="${param.message}" />");
	</c:if>
	
	//트리
	$('#menuTree').css("display","").jstree({
		"core":{"check_callback":true},
		"default":{draggable:false} //, "plugins":["checkbox"]
	}).bind("select_node.jstree", function(e,d){ //선택시
		console.log(d.node.id);
		var selBoxValue = d.node.id;
		var f = document.searchForm;
		console.log(f.selBox.value);
		if(f.selBox.value != selBoxValue){
			f.selBox.value = selBoxValue;
			f.pageIndex.value = "1";
			f.ceName.value = "";
			f.submit();	
		}
	});
  	<c:if test="${not empty param.selBox}">
   		$("#menuTree").jstree("select_node", "<c:out value="${param.selBox}" />");
   	</c:if>
	
	//버튼이벤트
	$(".btn_add").click(function(e){
		e.preventDefault();
		var modal_id = "modal_add";
		var modal_url = "/boffice_noDeco/group/emp/Emp_Form.do";
		var modal_param = {"ceIdx":"new", "selBox":"<c:out value="${param.selBox}" />"};
		var modal_class = ""; //wide, small
		ajaxModal(modal_id, modal_url, modal_param, modal_class);
	});
	$(".btn_mod").click(function(e){
		e.preventDefault();
		var formIdx = $(this).parents("tr").eq(0).attr("data-idx");
		var modal_id = "modal_mod";
		var modal_url = "/boffice_noDeco/group/emp/Emp_Form.do";
		var modal_param = {"ceIdx":formIdx, "selBox":"<c:out value="${param.selBox}" />"};
		var modal_class = ""; //wide, small
		ajaxModal(modal_id, modal_url, modal_param, modal_class);
	});
	$(".btn_siteViewYn").click(function(e){
		e.preventDefault();
		var formIdx = $(this).parents("tr").eq(0).attr("data-idx");
		var f = document.getElementById("empListForm"+formIdx);
		f.actionKey.value = "jUpdate";
		if (f.siteViewYn.value =="Y") {
			f.siteViewYn.value = "N";
		}else {
			f.siteViewYn.value = "Y";
		}
		f.submit();
	});
	$(".btn_ceUse").click(function(e){
		e.preventDefault();
		var formIdx = $(this).parents("tr").eq(0).attr("data-idx");
		var f = document.getElementById("empListForm"+formIdx);
		if (f.ceUse.value =="Y") {
			f.ceUse.value = "N";
		}else {
			f.ceUse.value = "Y";
		}
		f.submit();
	});
	$(".btn_sortUp").click(function(e){
		e.preventDefault();
		var formIdx = $(this).parents("tr").eq(0).attr("data-idx");
		var f = document.getElementById("empListForm"+formIdx);
		var step = "up";
		f.action = "/emp/EmpStep_Mod.do?step="+step;
		f.submit();
	});
	$(".btn_sortDown").click(function(e){
		e.preventDefault();
		var formIdx = $(this).parents("tr").eq(0).attr("data-idx");
		var f = document.getElementById("empListForm"+formIdx);
		var step = "down";
		f.action = "/emp/EmpStep_Mod.do?step="+step;
		f.submit();
	});
	
	//전체선택
    $("input[name=checkAll]").click(function() {
		if($(this).is(":checked"))
			$("input[name^=checkItem]").prop("checked", true);
		else
			$("input[name^=checkItem]").prop("checked", false);
    });
});

var searchFunc = function(f){
	if(!f.ceName.value && (!f.selBox.value || f.selBox.value == 'dd')){
		alert("검색어를 입력해주세요.");
		f.ceName.focus();
		return false;
	}
	if(!f.selBox.value){
		f.selBox.value = 'dd';
	}
};
</script>
<!-- //페이지 전용 스타일/스크립트 -->

</head>
<body>

	<div class="section divGroup leftTree">
		<div class="left noOption">
			<div id="menuTree">
				<ul>
					<li id="D000000" <c:if test="${empVo.selBox eq 'D000000'}"> data-jstree='{"selected":true}'</c:if>>><a href="#">전체</a>
						<c:set var="level" value="0" />
						<c:forEach items="${selectList}" var="result" varStatus="status">
							<c:choose>
								<c:when test="${result.level > level || status.index==0}">
									<ul>
										<li id="<c:out value="${result.cdIdx}"/>"<c:if test="${empVo.selBox eq result.cdIdx}"> data-jstree='{"selected":true}'</c:if>><a href="#"><c:out value="${result.cdSubject }" /><c:if test="${result.empCnt ne '0'}">(<c:out value="${result.empCnt }" />)</c:if></a>
								</c:when>
								<c:when test="${result.level < level}">
									<c:forEach var="i" begin="1" end="${level-result.level}">
											</li>								
										</ul>
									</c:forEach>
									</li>
									<li id="<c:out value="${result.cdIdx}"/>"<c:if test="${empVo.selBox eq result.cdIdx}"> data-jstree='{"selected":true}'</c:if>><a href="#"><c:out value="${result.cdSubject }" /><c:if test="${result.empCnt ne '0'}">(<c:out value="${result.empCnt }" />)</c:if></a>									
								</c:when>
								<c:otherwise>
									</li>
									<li id="<c:out value="${result.cdIdx}"/>"<c:if test="${empVo.selBox eq result.cdIdx}"> data-jstree='{"selected":true}'</c:if>><a href="#"><c:out value="${result.cdSubject }" /><c:if test="${result.empCnt ne '0'}">(<c:out value="${result.empCnt }" />)</c:if></a>															
								</c:otherwise>
							</c:choose>				
							<c:set var="level" value="${result.level}"/>
						</c:forEach>
						<c:forEach var="i" begin="1" end="${level}">
								</li>
							</ul>
						</c:forEach>
					</li>
				</ul>
			</div>
		</div>
		<div class="right">
			<div class="tableTitle">
				<h5><c:out value="${step }"/></h5>
			</div>
			
			<div class="search">
				<div class="right">
					<form name="searchForm" method="post" action="<c:url value="/boffice/group/emp/Emp_List.do"/>" class="searchListPage" onsubmit="return doSearch(this);">
						<input type="hidden" name="pageIndex" value="<c:out value='${empVo.pageIndex}' />">	
						<input type="hidden" id="selBox" name="selBox" value="<c:out value='${param.selBox}' />">	
						<input type="text" name="ceName" value="<c:out value='${param.ceName}' />" >
						<input type="submit" value="검색" class="btn_inline btn_search">
					</form> 
				</div>
			</div>
			
			<div class="tableTitle">
				<!-- <div class="btnArea left">
					<a href="#" class="btn_inline">선택수정</a>
					<a href="#" class="btn_inline">선택삭제</a>
				</div> -->
				<c:if test="${not empty param.selBox && param.selBox ne 'D000000' }">
				<div class="btnArea">
					<a href="#" class="btn_inline btn_add">신규등록</a>
				</div>
				</c:if>
			</div>
			<div class="tableBox">
				<table class="list useBtn">
					<caption>직원 목록</caption>
					<colgroup>
						<!-- <col class="w50"> -->
						<col class="w50">
						<col class="w60">
						<col class="w60">
						<col class="w100">
						<col class="w100">
						<col class="w100">
						<col>
						<col class="w70">
						<col class="w70">
						<c:if test="${not empty param.selBox && param.selBox ne 'D000000' }">
						<col class="w70">
						</c:if>
						<col class="w70">
					</colgroup>
					<thead>
						<tr>
							<!-- <th scope="col"><input type="checkbox" name="checkAll" value="1"></th> -->
							<th scope="col">순번</th>
							<th scope="col">이름</th>
							<th scope="col">직위</th>
							<th scope="col">메일</th>
							<th scope="col">전화번호</th>
							<th scope="col">휴대폰</th>
							<th scope="col">부서정보</th>
							<th scope="col">연동</th>
							<th scope="col">사용</th>
							<c:if test="${not empty param.selBox && param.selBox ne 'D000000' }">
								<th scope="col">정렬</th>
							</c:if>
							<th scope="col">관리</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${resultList }" var="result" varStatus="status">
						<tr data-idx="<c:out value='${result.ceIdx}' />">
							<!-- <td><input type="checkbox" name="checkItem[]" value="1"></td> -->
							<td>
								<%-- <c:out value="${(paginationInfo.totalRecordCount+1)-(status.count+(paginationInfo.currentPageNo-1)*paginationInfo.recordCountPerPage)}" /> --%>
								<c:out value="${paginationInfo.totalRecordCount - (paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo-1) * paginationInfo.recordCountPerPage + status.index) -1)}" />
								<form id="empListForm<c:out value="${result.ceIdx}" />" method="post">
									<input type="hidden" name="ceIdx" value="<c:out value='${result.ceIdx}' />" />
									<input type="hidden" name="ceSort" value="<c:out value='${result.ceSort}' />" />
									<input type="hidden" name="ceCdIdx" value="<c:out value='${result.ceCdIdx}' />" />
									<input type="hidden" name="ceDepstep1" value="<c:out value='${result.ceDepstep1}' />" />
									<input type="hidden" name="ceDepstep2" value="<c:out value='${result.ceDepstep2}' />" />
									<input type="hidden" name="ceDepstep3" value="<c:out value='${result.ceDepstep3}' />" />
									<input type="hidden" name="ceUse" value="<c:out value='${result.ceUse}' />" />
									<input type="hidden" name="ceName" value="<c:out value='${param.ceName}' />" />
									<input type="hidden" name="srchAll" value="<c:out value='${param.srchAll}' />" />
									<input type="hidden" name="selBox" value="<c:out value='${param.selBox}' />" />
									<input type="hidden" name="siteViewYn" value="<c:out value='${result.siteViewYn}' />" />
									<input type="hidden" name="actionKey" value="update" />
								</form>
							</td>
							<td><c:out value="${result.ceName}" /></td>
							<td><c:out value="${result.ceDepstep4}" /></td>
							<td><c:out value="${result.ceMailid}" /></td>
							<td><c:out value="${result.ceTel}" /></td>
							<td><c:out value="${result.ceHp}" /></td>
							<td class="left">
								<c:out value="${result.ceDepstep1}" />
								<c:if test="${not empty result.ceDepstep2}"> &gt; <c:out value="${result.ceDepstep2}" /></c:if>
								<c:if test="${not empty result.ceDepstep3}"> &gt; <c:out value="${result.ceDepstep3}" /></c:if>
							</td>
							<td><input type="checkbox" id="check<c:out value="${result.ceIdx}"/>_siteViewYn" name="siteViewYn[]" class="useToggle btn_siteViewYn" value=""<c:if test="${result.siteViewYn eq 'Y'}"> checked</c:if>><label for="check<c:out value="${result.ceIdx}"/>_siteViewYn">사용</label></td>
							<td><input type="checkbox" id="check<c:out value="${result.ceIdx}"/>_ceUse" name="ceUse[]" class="useToggle btn_ceUse" value=""<c:if test="${result.ceUse eq 'Y'}"> checked</c:if>><label for="check<c:out value="${result.ceIdx}"/>_ceUse">사용</label></td>
							<c:if test="${not empty param.selBox && param.selBox ne 'D000000' }">
								<td>
									<a href="#" class="btn_ss btn_sortUp">▲</a>
									<a href="#" class="btn_ss btn_sortDown">▼</a>
								</td>
							</c:if>
							<td>
								<a href="#" class="btn_inline btn_mod">수정</a>
							</td>
						</tr>
						</c:forEach>
						<c:if test="${fn:length(resultList) == 0 }">
						<tr>
							<c:choose>
								<c:when test="${not empty param.selBox && param.selBox ne 'D000000' }">
									<td colspan="11" class="empty"><spring:message code="common.nodata.msg" /></td>
								</c:when>
								<c:otherwise>
									<td colspan="10" class="empty"><spring:message code="common.nodata.msg" /></td>
								</c:otherwise>
							</c:choose>
						</tr>
						</c:if>
					</tbody>
				</table>
			</div>
			
			<div class="paging">
				<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="doPageMove" />
			</div>
		</div>
	</div>
</body>
</html>
