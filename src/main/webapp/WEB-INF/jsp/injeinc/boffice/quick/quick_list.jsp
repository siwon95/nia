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
<title>퀵링크관리</title>
<script type="text/javascript">
//<![CDATA[
	function doQuickFormAx(cqlIdx) {
		
		$.ajax({
			type: "POST",
			url: "<c:url value='/boffice/quick/QuickFormAx.do'/>",
			data : {"cqlIdx":cqlIdx},
			dataType : "json",
			success:function(data) {
				
				if(data.result) {
					var resultVo = data.resultVo;
					$("#actionkey").val(resultVo.actionkey);
					$("#cqlCode").val(resultVo.cqlCode);
					$("#cqlIdx").val(resultVo.cqlIdx);
					$("#cqlName").val(resultVo.cqlName);
					$("#cqlLink").val(resultVo.cqlLink);
					$("#cqlTarget").val(resultVo.cqlTarget);
					$("input[name='cqlHotYn']:radio[value='"+resultVo.cqlHotYn+"']").prop('checked', true);
					$("input[name='cqlNewYn']:radio[value='"+resultVo.cqlNewYn+"']").prop('checked', true);
					$("input[name='cqlUse']:radio[value='"+resultVo.cqlUse+"']").prop('checked', true);
					$("#divQuickForm").dialog("open");
				}else{
					alert(data.message);
				}
			},
			error: function(xhr, status, error) {
				alert(status);
			}
		});
	}
	
	function doQuickRegAx() {
		
		if($("#cqlCode").val() == "") {
			alert("분류를 선택하여 주십시오.");
			$("#cqlCode").focus();
			return;
		}
		if($("#cqlName").val() == "") {
			alert("이름을 입력하여 주십시오.");
			$("#cqlName").focus();
			return;
		}
		if($("#cqlLink").val() == "") {
			alert("링크를 입력하여 주십시오.");
			$("#cqlLink").focus();
			return;
		}
		
		var url = "";
		if($("#actionkey").val() == "regist") {
			url = "<c:url value='/boffice/quick/QuickRegAx.do' />";
		}else if($("#actionkey").val() == "modify") {
			url = "<c:url value='/boffice/quick/QuickModAx.do' />";
		}
		
		$.ajax({
			type: "POST",
			url: url,
			data : $("#saveFrm").serialize(),
			dataType : "json",
			success:function(data) {
				alert(data.message);
				if(data.result) {
					goReload();
				}
			},
			error: function(xhr, status, error) {
				alert(status);
			}
		});
		
	}

	function doQuickRmvAx(cqlIdx) {
		if (!confirm("정말 삭제하시겠습니까?")) return;

		$.ajax({
			type: "POST",
			url: "<c:url value='/boffice/quick/QuickRmvAx.do'/>",
			data : {"cqlIdx":cqlIdx},
			dataType : "json",
			success:function(data) {
				alert(data.message);
				if(data.result) {
					goReload();
				}
			},
			error: function(xhr, status, error) {
				alert(status);
			}
		});
		
	}
	
	function doQuickPag(pageIndex) {
		$("#pageIndex").val(pageIndex);
		$("#QuickVo").attr("action", "<c:url value='/boffice/quick/QuickList.do' />").submit();
	}
	
	function doQuickList() {
		$("#pageIndex").val("1");
		$("#QuickVo").attr("action", "<c:url value='/boffice/quick/QuickList.do' />").submit();
	}
	
	function goReload() {
		$("#QuickVo").attr("action", "<c:url value='/boffice/quick/QuickList.do' />").submit();
	}
	
	function doSortUp(cqlIdx) {
		
		$.ajax({
			type: "POST",
			url: "<c:url value='/boffice/quick/QuickSortUpAx.do'/>",
			data : {"cqlIdx":cqlIdx},
			dataType : "json",
			success:function(data) {
				if(data.result) {
					goReload();
				}else{
					alert(data.message);
				}
			},
			error: function(xhr, status, error) {
				alert(status);
			}
		});
	}
	
	function doSortDown(cqlIdx) {
		
		$.ajax({
			type: "POST",
			url: "<c:url value='/boffice/quick/QuickSortDownAx.do'/>",
			data : {"cqlIdx":cqlIdx},
			dataType : "json",
			success:function(data) {
				if(data.result) {
					goReload();
				}else{
					alert(data.message);
				}
			},
			error: function(xhr, status, error) {
				alert(status);
			}
		});
	}
	
	$(document).ready(function() {
		$("input:checkbox").on("click", function() {
			var type = $(this).attr("name");
			var cqlIdx = $(this).val();
			var value = ""
			if($(this).is(":checked")) {
				value = "Y";
			}else{
				value = "N";
			}
			
			$.ajax({
				type: "POST",
				url: "<c:url value='/boffice/quick/QuickModEtcInfoAx.do'/>",
				data : {"cqlIdx":cqlIdx, "type":type, "value":value},
				dataType : "json",
				success:function(data) {
					alert(data.message);
					if(data.result) {
						if(type == "use") {
							goReload();
						}
					}
				},
				error: function(xhr, status, error) {
					alert(status);
				}
			});
			
		});

		$("#divQuickForm").dialog({
			autoOpen: false,
			height: 400,
			width: 600,
			modal: true,
			buttons: {
				"저장": function() {
					doQuickRegAx();
				},
				"닫기": function() {
					$(this).dialog("close");
				}
			}
		});

	});
//]]>
</script>
</head>
<body style="text-align: center; margin: 0 auto; display: inline; padding-top: 100px;">
	<form:form commandName="QuickVo" name="QuickVo" method="post">
		<form:hidden path="pageIndex" />

		<!-- 컨텐츠 영역 -->
		<div id="contents">

			<table summary="검색테이블" class="view">
				<caption>검색테이블</caption>
				<colgroup>
					<col width="13%" />
					<col width="20%" />
					<col width="13%" />
					<col width="20%" />
					<col width="13%" />
					<col width="21%" />
				</colgroup>
				<tbody>
					<tr>
						<th scope="row"><label for="pageUnit">페이지수</label></th>
						<td>
							<form:select path="pageUnit" onchange="doQuickList();">
								<form:option value="10" label="10" />
								<form:option value="20" label="20" />
								<form:option value="30" label="30" />
								<form:option value="50" label="50" />
								<form:option value="100" label="100" />
							</form:select>
						</td>
						<th scope="row"><label for="searchOrder">정렬순서</label></th>
						<td>
							<form:select path="searchOrder" onchange="doQuickList();">
								<form:option value="1" label="sort순" />
								<form:option value="2" label="이름순" />
							</form:select>
						</td>
						<th scope="row"><label for="searchCqlUse">사용유무</label></th>
						<td>
							<form:select path="searchCqlUse" onchange="doQuickList();">
								<form:option value="Y" label="사용" />
								<form:option value="N" label="미사용" />
							</form:select>
						</td>
					</tr>
					<tr>
						<th scope="row"><label for="searchCqlCode">분류선택</label></th>
						<td>
							<form:select path="searchCqlCode" onchange="doQuickList();">
								<form:option value="" label="분류전체보기" />
								<c:forEach var="codeInfo" items="${codeList }">
									<option value="<c:out value="${codeInfo.code}"/>"
										<c:if test="${QuickVo.searchCqlCode eq codeInfo.code}">selected="selected"</c:if>><c:out value="${codeInfo.codeName}" />
									</option>
								</c:forEach>
							</form:select>
						</td>
						<th scope="row"><label for="searchCondition">검색</label></th>
						<td colspan="3">
							<form:select path="searchCondition">
								<form:option value="1" label="이름" />
								<form:option value="2" label="주소" />
							</form:select>
							<span class="sfont"><label for="searchKeyword" class="hidden">검색어</label>
								<form:input path="searchKeyword" size="30" />
								<button class="btn2 btn_input" onclick="doQuickList(); return false;">
									<i class="fa fa-search"></i> 검색
								</button>
							</span>
						</td>
					</tr>
				</tbody>
			</table>

			<table summary="퀵링크관리" class="list1" style="table-layout: fixed;">
				<caption>퀵링크관리</caption>
				<colgroup>
					<col width="5%" />
					<col width="10%" />
					<col width="12%" />
					<col />
					<col width="5%" />
					<col width="5%" />
					<col width="5%" />
					<col width="5%" />
					<col width="5%" />
					<col width="8%" />
					<col width="10%" />
				</colgroup>
				<thead>
					<tr>
						<th scope="col">순서</th>
						<th scope="col">분류</th>
						<th scope="col">이름</th>
						<th scope="col">주소</th>
						<th scope="col">타겟</th>
						<th scope="col">Hot</th>
						<th scope="col">New</th>
						<th scope="col">Use</th>
						<th scope="col">Sort</th>
						<th scope="col">순서</th>
						<th scope="col">수정</th>
					</tr>
				</thead>
				<tbody>
					<c:set var="n" value="${1 + (paginationInfo.currentPageNo-1)*paginationInfo.recordCountPerPage }" />
					<c:forEach var="result" items="${resultList}" varStatus="status">
						<tr>
							<td><c:out value="${n + status.index}" /></td>
							<td><c:out value="${result.cqlCodeName}" /></td>
							<td><c:out value="${result.cqlName}" /></td>
							<td class="sbj" style="word-break: break-all;"><c:out value="${result.cqlLink}" /></td>
							<td><c:out value="${result.cqlTarget}" /></td>
							<td><input type="checkbox" name="hot" value="<c:out value="${result.cqlIdx}"/>"
								<c:if test="${result.cqlHotYn eq 'Y'}">checked="checked"</c:if> /></td>
							<td><input type="checkbox" name="new" value="<c:out value="${result.cqlIdx}"/>"
								<c:if test="${result.cqlNewYn eq 'Y'}">checked="checked"</c:if> /></td>
							<td><input type="checkbox" name="use" value="<c:out value="${result.cqlIdx}"/>"
								<c:if test="${result.cqlUse eq 'Y'}">checked="checked"</c:if> /></td>
							<td><c:out value="${result.cqlSort}" /></td>
							<td>
								<c:if test="${QuickVo.searchOrder == 1 && QuickVo.searchCqlUse eq 'Y'  && empty QuickVo.searchKeyword}">
									<a href="javascript:doSortUp('<c:out value="${result.cqlIdx}"/>');" class="btn">▲</a>
									<a href="javascript:doSortDown('<c:out value="${result.cqlIdx}"/>');" class="btn">▼</a>
								</c:if>
								<c:if test="${QuickVo.searchOrder != 1 || QuickVo.searchCqlUse ne 'Y' || !empty QuickVo.searchKeyword}">-</c:if>
							</td>
							<td>
								<a href="javascript:doQuickFormAx('<c:out value="${result.cqlIdx}" />');" class="btn">수정</a>
								<a href="javascript:doQuickRmvAx('<c:out value="${result.cqlIdx}" />');" class="btn">삭제</a>
							</td>
						</tr>
					</c:forEach>
					<c:if test="${fn:length(resultList) == 0}">
						<tr>
							<td colspan="11"><spring:message code="common.nodata.msg" /></td>
						</tr>
					</c:if>
				</tbody>
			</table>

			<!--paginate -->
			<div class="paginate">
				<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="doQuickPag" />
			</div>
			<!--//paginate -->

			<!-- 버튼 -->
			<div class="btn_zone">
				<a href="javascript:doQuickFormAx('');" class="btn2">퀵링크 등록</a>
			</div>
			<!-- //버튼 -->

		</div>
		<!-- //컨텐츠 영역 -->
	</form:form>
	<div id="divQuickForm" title="퀵링크 입력/수정">
		<form id="saveFrm" name="saveFrm" method="post">
			<input type="hidden" id="cqlIdx" name="cqlIdx" />
			<input type="hidden" id="actionkey" name="actionkey" />
			<table summary="퀵링크 입력/수정" class="write" style="margin-bottom: 0px;">
				<caption>퀵링크 입력/수정</caption>
				<colgroup>
					<col width="20%" />
					<col width="80%" />
				</colgroup>
				<tbody>
					<tr>
						<th scope="row"><label for="cqlCode">분류선택</label></th>
						<td colspan="3">
							<select id="cqlCode" name="cqlCode">
								<option value="">분류를 선택하세요</option>
								<c:forEach var="codeInfo" items="${codeList }">
									<option value="<c:out value="${codeInfo.code}"/>"
										<c:if test="${QuickVo.searchCqlCode eq codeInfo.code}">selected="selected"</c:if>><c:out value="${codeInfo.codeName}" />
									</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th scope="row"><label for="cqlName">이름</label></th>
						<td><input type="text" id="cqlName" name="cqlName" size="20" value="" /></td>
					</tr>
					<tr>
						<th scope="row"><label for="cqlLink">링크</label></th>
						<td><input type="text" id="cqlLink" name="cqlLink" value="" class="w90" /></td>
					</tr>
					<tr>
						<th scope="row"><label for="cqlTarget">타겟</label></th>
						<td>
							<select id="cqlTarget" name="cqlTarget">
								<option value="_blank">_blank</option>
								<option value="_self">_self</option>
							</select>
						</td>
					</tr>
					<tr>
						<th scope="row"><label for="cqlHotYn1">Hot 유무</label></th>
						<td>
							<input type="radio" id="cqlHotYn1" name="cqlHotYn" value="Y" /><label for="cqlHotYn1">사용</label>
							<input type="radio" id="cqlHotYn2" name="cqlHotYn" value="N" /><label for="cqlHotYn2">미사용</label>
						</td>
					</tr>
					<tr>
						<th scope="row"><label for="cqlNewYn1">New 유무</label></th>
						<td>
							<input type="radio" id="cqlNewYn1" name="cqlNewYn" value="Y" /><label for="cqlNewYn1">사용</label>
							<input type="radio" id="cqlNewYn2" name="cqlNewYn" value="N" /><label for="cqlNewYn2">미사용</label>
						</td>
					</tr>
					<tr>
						<th scope="row"><label for="cqlUse1">사용 유무</label></th>
						<td>
							<input type="radio" id="cqlUse1" name="cqlUse" value="Y" /><label for="cqlUse1">사용</label>
							<input type="radio" id="cqlUse2" name="cqlUse" value="N" /><label for="cqlUse2">미사용</label>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>