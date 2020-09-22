<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javaScript" language="javascript" defer="defer">
//<![CDATA[

function fncCheckAll() {
    var checkField = document.listForm.delYn;
    if(document.listForm.checkAll.checked) {
        if(checkField) {
            if(checkField.length > 1) {
                for(var i=0; i < checkField.length; i++) {
                    checkField[i].checked = true;
                }
            } else {
                checkField.checked = true;
            }
        }
    } else {
        if(checkField) {
            if(checkField.length > 1) {
                for(var j=0; j < checkField.length; j++) {
                    checkField[j].checked = false;
                }
            } else {
                checkField.checked = false;
            }
        }
    }
}

function fncManageChecked() {

    var checkField = document.listForm.delYn;
    var checkId = document.listForm.checkId;
    var returnValue = "";
    var returnBoolean = false;
    var checkCount = 0;

    if(checkField) {
        if(checkField.length > 1) {
            for(var i=0; i<checkField.length; i++) {
                if(checkField[i].checked) {
                	checkCount++;
                    checkField[i].value = checkId[i].value;
                    if(returnValue == "")
                        returnValue = checkField[i].value;
                    else
                        returnValue = returnValue + ";" + checkField[i].value;
                }
            }
            if(checkCount > 0)
                returnBoolean = true;
            else {
                alert("선택된  메인화면 이미지가 없습니다.");
                returnBoolean = false;
            }
        } else {
            if(document.listForm.delYn.checked == false) {
                alert("선택된  메인화면 이미지가 없습니다.");
                returnBoolean = false;
            }
            else {
                returnValue = checkId.value;
                returnBoolean = true;
            }
        }
    } else {
    	alert("조회된 결과가 없습니다.");
    }

    document.listForm.imageIds.value = returnValue;
    return returnBoolean;
}

function fncSelectMainImageList(pageNo){
    document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/boffice/ex/mainImage/mainImageList.do'/>";
    document.listForm.submit();
}

function fncSelectMainImage(imageId) {
    document.listForm.imageId.value = imageId;
    document.listForm.action = "<c:url value='/boffice/ex/mainImage/mainImage.do'/>";
    document.listForm.submit();
}

function fncAddMainImageInsert() {

    if(document.listForm.pageIndex.value == "") {
        document.listForm.pageIndex.value = 1;
    }
    document.listForm.action = "<c:url value='/boffice/ex/mainImage/mainImageForm.do'/>";
    document.listForm.submit();
}

function fncLoginMainImageListDelete() {
	if(fncManageChecked()) {
        if(confirm("삭제하시겠습니까?")) {
            document.listForm.action = "/boffice/ex/mainImage/mainImageListRmv.do";
            document.listForm.submit();
        }
    }
}

function linkPage(pageNo){
    document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/boffice/ex/mainImage/mainImageList.do'/>";
    document.listForm.submit();
}

function press() {

    if (event.keyCode==13) {
    	fncSelectMainImageList('1');
    }
}

function doStepdown(id,sort){
	document.mainSort.imageId.value = id;
	document.mainSort.sort.value = sort;
	document.mainSort.action="/boffice/ex/mainImage/Step_Mod.do?temp=down";
	document.mainSort.submit();
}

function doStepup(id,sort){
	document.mainSort.imageId.value = id;
	document.mainSort.sort.value = sort;
	document.mainSort.action="/boffice/ex/mainImage/Step_Mod.do?temp=up";
	document.mainSort.submit();
}

//]]>
</script>

</head>

<body>


	<!-- 컨텐츠 영역 -->
	<div id="contents">
		
		<form name="mainSort" method="post">
			<input type="hidden" name="imageId" />
			<input type="hidden" name="sort" />
			<input type="hidden" name="pageIndex" value="<c:out value='${mainImageVO.pageIndex}'/>">
		</form>
		
		<form name="item" method="post" action="<c:url value='/uss/ion/msi/getMainImage.do'/>">
			<input type="hidden" name="type" value="<c:out value="${mainImageVO.type}"/>">
		    <input type="hidden" name="imageId" value="<c:out value="${mainImageVO.imageId}"/>">
		    <input type="hidden" name="pageIndex" value="<c:out value='${mainImageVO.pageIndex}'/>">
		    <input type="hidden" name="searchCondition" value="<c:out value='${mainImageVO.searchCondition}'/>">
		    <input type="hidden" name="searchKeyword" value="<c:out value="${mainImageVO.searchKeyword}"/>">
		    <input type="hidden" name="sort" value="<c:out value="${mainImageVO.sort}"/>">
		</form>

		<form name="listForm" action="<c:url value='/uss/ion/msi/selectMainImageList.do'/>" method="post">
		<input type="hidden" name="type" value="<c:out value="${mainImageVO.type}"/>">
		<input type="hidden" name="imageId">
		<input type="hidden" name="pageIndex" value="<c:if test="${empty mainImageVO.pageIndex }">1</c:if><c:if test="${!empty mainImageVO.pageIndex }"><c:out value='${mainImageVO.pageIndex}'/></c:if>" >
		<input type="hidden" name="searchCondition" value="1" >

		<table summary="검색테이블" class="view">
			<caption>검색테이블</caption>
			<colgroup>
				<col width="20%"/>
				<col width="80%"/>
			</colgroup>
			<tbody>
				<tr>
					<th scope="row"><label for="searchKeywordTo">검색</label></th>
					<td>
						<span class="sfont">
							<select name="searchKeywordTo" id="searchKeywordTo" >
								<option value="all" <c:if test="${mainImageVO.searchKeywordTo eq 'all' }">selected="selected"</c:if>>전체</option>
								<c:forEach var="result" items="${rowDataList }" varStatus="status">
								<option value="<c:out value="${result.code }"/>" <c:if test="${result.code eq mainImageVO.searchKeywordTo }">selected="selected"</c:if>><c:out value="${result.codeName }"/></option>
								</c:forEach>
							</select>
							<label for="searchKeyword" class="hidden">검색어</label>
							<input type="text" name="searchKeyword" id="searchKeyword" value="<c:out value="${mainImageVO.searchKeyword}"/>" size="30"/>
							<a href="javascript:fncSelectMainImageList('1');" class="btn1">검색</a>
						</span>
					</td>
				</tr>
			</tbody>
		</table>
		</form>

		<table summary="" class="list1">
			<caption></caption>
			<colgroup>
				<col width="7%" />
				<col width="16%" />
				<col width="*" />
				<col width="20%" />
				<col width="10%" />
				<col width="10%" />
				<col width="10%" />
			</colgroup>
			<thead>
			<tr>
				<th>번호</th>
				<th>팝업존</th>
				<th>제목</th>
				<th>게시기간</th>
				<th>순위</th>
				<th>작성일</th>
				<th>게시상태</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach var="mainImage" items="${mainImageList}" varStatus="status">
			<tr>
				<td>
					<c:out value="${status.count+(mainImageVO.pageIndex-1)*mainImageVO.recordCountPerPage}" />
				</td>
				<td>
					<c:out value="${mainImage.type }"/>
				</td>
				<td class="sbj">
					<a href="javascript:fncSelectMainImage('<c:out value="${mainImage.imageId}"/>');"><c:out value="${mainImage.imageNm}"/></a>
				</td>
				<td>
					<c:choose>
						<c:when test="${'Y' eq mainImage.ordtmNtceAt}">
							상시 게시
						</c:when>
						<c:otherwise>
							<c:out value="${fn:substring(mainImage.ntceBgnde, 0, 4)}"/>-<c:out value="${fn:substring(mainImage.ntceBgnde, 4, 6)}"/>-<c:out value="${fn:substring(mainImage.ntceBgnde, 6, 8)}"/>
						 	~
						 	<c:out value="${fn:substring(mainImage.ntceEndde, 0, 4)}"/>-<c:out value="${fn:substring(mainImage.ntceEndde, 4, 6)}"/>-<c:out value="${fn:substring(mainImage.ntceEndde, 6, 8)}"/>
						</c:otherwise>
					</c:choose>
				</td>
				<td>
					<c:if test="${mainImage.ntceSttus eq '1' || mainImage.ntceSttus eq '2'}">
						<input type="button" value="▼" onclick="doStepdown('<c:out value="${mainImage.imageId }" />','<c:out value="${mainImage.sort }" />');">&nbsp;<input type="button" value="▲" onclick="doStepup('<c:out value="${mainImage.imageId }" />','<c:out value="${mainImage.sort }" />');">
					</c:if>
				</td>
				<td>
					<c:out value="${fn:substring(mainImage.regDt,0,10)}" />
				</td>
				<td>
					<c:choose><c:when test="${mainImage.ntceSttus == '1'}">
						<span class="cate2">게시중</span>
					</c:when>
					<c:when test="${mainImage.ntceSttus == '2'}">
						<span class="cate2">게시예정</span>
					</c:when>
					<c:when test="${mainImage.ntceSttus == '3'}">
						<span class="cate1">게시중지</span>
					</c:when><c:otherwise>
						<span class="cate1">게시완료</span>
					</c:otherwise></c:choose>
				</td>
			</tr>
			</c:forEach>
			<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
			<c:if test="${fn:length(mainImageList) == 0}">
			<tr>
				<td colspan="7">
			 		해당 자료가 없습니다.
				</td>
			</tr>
			</c:if>
			</tbody>
		</table>

		<!-- paginate -->
		<div class="paginate">
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="linkPage" />
		</div>
		<!--// paginate -->

		<!-- 버튼 -->
		<div class="btn_zone">
			<%-- 등록 --%>
			<a href="javascript:fncAddMainImageInsert();" class="btn2">등록</a>
		</div>
		<!--// 버튼 -->

	</div>
	<!--// 컨텐츠 영역 -->

</body>
</html>

