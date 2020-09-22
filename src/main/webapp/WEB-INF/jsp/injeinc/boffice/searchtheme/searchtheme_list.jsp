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
<title>Basic Board List</title>
<script type="text/javascript">
//<![CDATA[

	//리스트 조회
	function doSearchthemeList(){
		document.SearchthemeVo.action = "<c:url value='/boffice/searchtheme/Searchtheme_List.do' />";
		document.SearchthemeVo.submit();
	}
	
	//삭제
	function doSearchthemeRmv(idx){
		if(confirm("정말 삭제 하시겠습니까?")){
			document.SearchthemeVo.action = "/boffice/searchtheme/Searchtheme_Rmv.do?stIdx="+idx;
			document.SearchthemeVo.submit();
		}
	}
	
	//수정페이지
	function doSearchthemeReceiveForm(idx){
		document.SearchthemeVo.action = "<c:url value='/boffice/searchtheme/Searchtheme_Form.do?stIdx=' />"+idx;
		document.SearchthemeVo.submit();
	}
	
	//페이징처리 
	function doSearchthemePag(pageIndex) {
		document.SearchthemeVo.pageIndex.value = pageIndex;
		document.SearchthemeVo.action = "<c:url value='/boffice/searchtheme/Searchtheme_List.do' />";
		document.SearchthemeVo.submit();
	}
	
	//등록페이지
	function doSearchthemeForm(){
		document.SearchthemeVo.action = "<c:url value='/boffice/searchtheme/Searchtheme_Form.do' />";
		document.SearchthemeVo.submit();
	}
	
	//사용여부 체크
	function doSearchthemeUseYnAx(idx){
		$.ajax({
			type: "GET",
			url: "<c:url value='/boffice/searchtheme/SearchthemeUseYn_Ax.do' />",
			data : "stIdx=" + idx,
			dataType : "json",
			success:function(obj){
				alert(obj.message);
			 },
	        error: function(xhr,status,error){
	        	alert(status);
	        }
		});	
	}
	
	$(document).ready(function(){
	});
//]]>
</script>
</head>
<body style="text-align: center; margin: 0 auto; display: inline; padding-top: 100px;">
	<form:form commandName="SearchthemeVo" name="SearchthemeVo" method="post">
		<form:hidden path="pageIndex" />
		<!-- 컨텐츠 영역 -->
		<div id="contents">
			<!-- 검색 -->
			<table summary="" class="write">
				<caption></caption>
				<colgroup>
					<col width="15%" />
					<col width="*" />
				</colgroup>
				<tbody>
					<tr>
						<th><label for="useYn">사용유무</label></th>
						<td>
							<select name="useYn" id="useYn">
								<option value="">전체</option>
								<option value="Y" <c:if test="${SearchthemeVo.useYn eq 'Y' }">selected="selected"</c:if>>사용</option>
								<option value="N" <c:if test="${SearchthemeVo.useYn eq 'N' }">selected="selected"</c:if>>미사용</option>
							</select>
						</td>
					</tr>
					<tr>
						<th><label for="schgroupcd">그룹</label></th>
						<td>
							<select name="schgroupcd" id="schgroupcd">
								<option value="">전체</option>
								<c:forEach items="${groupCdList }" var="group" varStatus="status">
									<option value="<c:out value="${group.groupCd}"/>"
										<c:if test="${SearchthemeVo.schgroupcd eq group.groupCd }">selected="selected"</c:if>><c:out value="${group.groupCd}" />
									</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th><label for="searchCondition">검색</label></th>
						<td>
							<select name="searchCondition" id="searchCondition">
								<option value="">전체</option>
								<option value="schtitle" <c:if test="${SearchthemeVo.searchCondition eq 'schtitle' }">selected="selected"</c:if>>제목</option>
								<option value="schintroduce" <c:if test="${SearchthemeVo.searchCondition eq 'schintroduce' }">selected="selected"</c:if>>소개</option>
								<option value="schtags" <c:if test="${SearchthemeVo.searchCondition eq 'schtags' }">selected="selected"</c:if>>태그</option>
								<option value="schdepartment" <c:if test="${SearchthemeVo.searchCondition eq 'schdepartment' }">selected="selected"</c:if>>담당부서</option>
							</select>
							<label for="searchKeyword">검색어</label>
							<form:input path="searchKeyword" size="30" title="검색어" />
							<a href="javascript:doSearchthemeList();" class="btn2 btn_input"><i class="fa fa-search"></i> 검색</a>
						</td>
					</tr>
				</tbody>
			</table>
			<!-- //검색 -->
			<br/>
			<div class="btn_zone">
				<a href="javascript:doSearchthemeForm()" class="btn2">등록</a>
			</div>
			<br/><br/>
			<table summary="" class="list1">
				<caption></caption>
				<colgroup>
					<col width="7%" />
					<col width="20%" />
					<col width="15%" />
					<col width="*" />
					<col width="5%" />
					<col width="7%" />
					<col width="7%" />
				</colgroup>
				<thead>
					<tr>
						<th>NO</th>
						<th>이미지</th>
						<th>그룹</th>
						<th>내용</th>
						<th>사용</th>
						<th>수정</th>
						<th>삭제</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${fn:length(resultList) == 0}">
							<tr>
								<td colspan="7" align="center">테마 내용이 없습니다.</td>
							</tr>
						</c:when>
						<c:when test="${fn:length(resultList) > 0}">
							<c:forEach items="${resultList}" var="result" varStatus="status">
								<tr>
									<td><c:out value="${(totCnt+1)-(status.count+(SearchthemeVo.pageIndex-1)*SearchthemeVo.recordCountPerPage)}" /></td>
									<td>
										<c:if test="${result.streFileNm ne '' && result.streFileNm != null}">
											<img src="/upload/search_theme/<c:out value="${result.streFileNm}"/>" width="300" height="170" />
										</c:if>
									</td>
									<td><c:out value="${result.groupCd}" /></td>
									<td style="text-align: left;">
										<b>제목 :</b> <c:out value="${result.title}" /><br/>
										<b>URL :</b> <c:out value="${result.url}" /><br/>
										<b>소개 :</b> <c:out value="${result.introduce}" /><br/>
										<b>태그 :</b> <c:out value="${result.tags}" /><br/>
										<b>담당부서 :</b> <c:out value="${result.department}" /><br/>
										<b>연락처 :</b> <c:out value="${result.telNum}" />
									</td>
									<td>
										<input type="checkbox" name="usecheck" <c:if test="${result.useYn eq 'Y'}" >checked="checked"</c:if> onclick="doSearchthemeUseYnAx('<c:out value="${result.stIdx}" />','<c:out value="${result.useYn}" />');" />
									</td>
									<td><a href="javascript:doSearchthemeReceiveForm('<c:out value="${result.stIdx}" />');" class="btn1">수정</a></td>
									<td><a href="javascript:doSearchthemeRmv('<c:out value="${result.stIdx}" />');" class="btn2">삭제</a></td>
								</tr>
							</c:forEach>
						</c:when>
					</c:choose>
				</tbody>
			</table>

			<!--paginate -->
			<div class="paginate">
				<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="doSearchthemePag" />
			</div>
			<!--//paginate -->

		</div>
		<!-- //컨텐츠 영역 -->
	</form:form>
</body>
</html>