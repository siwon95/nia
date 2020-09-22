<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%-- ------------------------------------------------------------
- 제목 : 템플릿관리
- 파일명 : layout_form.jsp
- 최종수정일 : 2019-04-29
- 상태 : 1차완료
-------------------------------------------------------------- --%>

<!-- 페이지 전용 스타일/스크립트 -->
<script>
var modalActionType = "";
$(function(){
	//버튼이벤트
	$(".btn_del").click(function(e){
		e.preventDefault();
		if(!confirm("<spring:message code="common.delete.msg" />")){
			return;
		}
		modalActionType = "delete";
		$(".modalContent #LayoutVo").submit();
	});
	
	
	// 템플릿 등록시 초기값 설정
	if('${pageMode}' == 'form') {
		var siteCd = '';
		var layoutCd = '';
		var layoutTp = '';
		$("select[name=siteCd]").change(function(){
			siteCd = $(this).val();
			layoutContentMake(siteCd, layoutCd, layoutTp);
		});
		$("input[name=layoutCode]").keyup(function(){
			layoutCd = $(this).val();
			layoutContentMake(siteCd, layoutCd, layoutTp);
		});
		$("input[name=layoutType]").change(function(){
			if($(this).val() == '3') {
				$("[name=layoutContent]").html('');
			} else {
				layoutTp = $(this).val();
				layoutContentMake(siteCd, layoutCd, layoutTp);
			}
		});
	}
	
	function layoutContentMake(siteCd, layoutCd, layoutTp){
		if(siteCd == '') {siteCd = '사이트코드';}
		if(layoutCd == '') {layoutCd = '템플릿코드';}
		var innerHtml = '&lt;%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%&gt;\n';
		innerHtml += '&lt;%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %&gt;\n';
		innerHtml += '&lt;%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %&gt;\n';			
		innerHtml += '&lt;%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %&gt;\n';
		innerHtml += '&lt;%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page" %&gt;\n';
		innerHtml += '&lt;jsp:include page="/layout/' + siteCd + '/' + layoutCd + '/head.jsp" flush="true"/&gt;\n';
		innerHtml += '&lt;decorator:head /&gt;\n';
		innerHtml += '&lt;jsp:include page="/layout/' + siteCd + '/' + layoutCd + '/top.jsp" flush="true"/&gt;\n';
		if(layoutCd.indexOf("sub") > -1) {
			innerHtml += '&lt;%-- sub template로 사용시 left메뉴 사용가능 --%&gt;\n';
			innerHtml += '&lt;%-- &lt;jsp:include page="/layout/' + siteCd + '/' + layoutCd + '/sub_left.jsp" flush="true"/&gt; --%&gt;\n';
		}
		innerHtml += '&lt;decorator:body /&gt;\n';
		if(layoutCd.indexOf("sub") > -1) {
			innerHtml += '&lt;%-- sub template로 사용시 right메뉴 사용가능 --%&gt;\n';
			innerHtml += '&lt;%-- &lt;jsp:include page="/layout/' + siteCd + '/' + layoutCd + '/sub_right.jsp" flush="true"/&gt; --%&gt;\n';
		}
		innerHtml += '&lt;jsp:include page="/layout/' + siteCd + '/' + layoutCd + '/bottom.jsp" flush="true"/&gt;\n';
		$("[name=layoutContent]").html(innerHtml);
	}
	
	
});

function doForm(f){
	var requiredCheck = formRequiredCheck(f);
	if(requiredCheck == false){
		return false;
	}

	var test = "<c:out value='${pageMode}'/>";
	if(modalActionType == "delete"){
		f.action = "<c:url value='/boffice/cn/layout/layout_rmv.do'/>";
	}else if(test == "form"){
		f.action = "<c:url value='/boffice/cn/layout/layout_reg.do'/>";
	}else if(test == "update"){
		f.action = "<c:url value='/boffice/cn/layout/layout_mod.do'/>";
	}
	return true;
}
</script>
<!-- //페이지 전용 스타일/스크립트 -->

<!-- ============================== 모달영역 ============================== -->
<div class="modalTitle">
	<h3>
		템플릿
		<c:choose>
			<c:when test="${pageMode eq 'form'}">등록</c:when>
			<c:when test="${pageMode eq 'update'}">수정</c:when>
		</c:choose>
	</h3>
	<a href="#" class="btn_modalClose">모달팝업닫기</a>
</div>
<div class="modalContent">
	<form:form commandName="LayoutVo" name="LayoutVo" method="post" onsubmit="return doForm(this);">
	<div class="tableBox">
		<table class="form">
			<caption></caption>
			<colgroup>
				<col class="w20p">
				<col>
			</colgroup>
			<tbody>
				<tr>
					<th><label for="siteCd">사이트 명</label></th>
					<td>
						<c:choose>
							<c:when test="${pageMode eq 'update'}">
								<c:out value='${LayoutVo.siteNm}' /> (사이트코드 : <c:out value='${LayoutVo.siteCd}' />)
								<form:hidden path="siteCd" />
							</c:when>
							<c:otherwise>
								<form:select path="siteCd" class="required w100p">
									<form:option value="" label="--선택하세요--" />
									<form:options items="${listSiteAll}" itemValue="siteCd" itemLabel="siteNm" />
								</form:select>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<th><label for="layoutCode">템플릿 코드</label></th>
					<td>
						<c:choose>
							<c:when test="${pageMode eq 'update'}">
								<c:out value='${LayoutVo.layoutCode}' />
								<form:hidden path="layoutCode" />
							</c:when>
							<c:otherwise>
								<form:input path="layoutCode" class="required w100p" />
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<th><label for="layoutDesc">템플릿 명</label></th>
					<td><form:input path="layoutDesc" class="required w100p" /></td>
				</tr>
				<c:choose>
					<c:when test="${pageMode eq 'form'}">
				<tr>
					<th>템플릿타입</th>
					<td>
						<ul class="layoutTypeList">
							<li>
								<span class="thumbImg type1"></span>
								<input type="radio" id="layoutType1" name="layoutType" value="1" checked>
								<label for="layoutType1">3단</label>
							</li>
							<li>
								<span class="thumbImg type2"></span>
								<input type="radio" id="layoutType2" name="layoutType" value="2">
								<label for="layoutType2">3단+좌측메뉴</label>
							</li>
							<li>
								<span class="thumbImg type3"></span>
								<input type="radio" id="layoutType3" name="layoutType" value="3">
								<label for="layoutType3">커스텀</label>
							</li>
						</ul>
						<div>
							선택하신 템플릿 형태로 Include 파일이 생성됩니다.<br />
							/layout/{사이트명}/ 경로에 생성됩니다.<br />
							*커스텀을 선택하시면 동일한 경로에 파일을 직접 생성해야 합니다.
						</div>
					</td>
				</tr>
					</c:when>
				</c:choose>
				<tr>
					<th><label for="layoutContent">템플릿 내용</label></th>
					<td>
						<form:textarea path="layoutContent" rows="10" class="required w100p" />
<pre class="soundOnly">&lt;%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%&gt;
&lt;%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %&gt;
&lt;%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %&gt;
&lt;%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %&gt;
&lt;%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page" %&gt;
&lt;jsp:include page="/layout/사이트코드/템플릿코드/head.jsp" flush="true"/&gt;
&lt;decorator:head /&gt;
&lt;jsp:include page="/layout/사이트코드/템플릿코드/top.jsp" flush="true"/&gt;
&lt;decorator:body /&gt;
&lt;jsp:include page="/layout/사이트코드/템플릿코드/bottom.do" flush="true"/&gt;</pre>
					</td>
				</tr>
				<tr>
					<th><label for=>템플릿 이미지</label></th>
					<td><input type="file" name="" class="w200"> (준비중)</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="btnArea">
		<c:choose>
			<c:when test="${pageMode eq 'form'}">
				<input type="submit" value="확인" class="btn_m on">
				<a href="#" class="btn_m btn_modalClose">취소</a>
			</c:when>
			<c:when test="${pageMode eq 'update'}">
				<input type="submit" value="수정" class="btn_m on">
				<a href="#" class="btn_m btn_caption btn_del">삭제</a>
				<a href="#" class="btn_m btn_modalClose">취소</a>
			</c:when>
		</c:choose>
	</div>
	</form:form>
</div>
<!-- ============================== //모달영역 ============================== -->
