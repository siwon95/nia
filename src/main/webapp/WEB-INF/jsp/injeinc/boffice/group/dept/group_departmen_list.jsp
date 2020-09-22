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
- 최종수정일 : 2019-04-18
- 상태 : 수정중
-------------------------------------------------------------- --%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>통합관리시스템</title>

<!-- 페이지 전용 스타일/스크립트 -->
<link rel="stylesheet" type="text/css" href="/plugin/jstree//dist/themes/default/style.min.css" />
<script src="/plugin/jstree//dist/jstree.min.js"></script>
<script type="text/javascript">
$(function(){ 
	$('#menuTree').css("display","").jstree({
		"core":{"check_callback":true},
		"default":{draggable:false} //, "plugins":["checkbox"]
	}).bind("select_node.jstree", function(e,d){ //선택시
		var tree = $('#menuTree').jstree(true);
		var codeArr = d.node.id.split("\|");
		var cdIdx = codeArr[4];
		
		f = document.regFrom;
		f.deptNm.value = $(this).text();
		f.cdSubject.value = "추가부서";
		f.cdDepstep1.value = codeArr[0];
		f.cdDepstep2.value = codeArr[1];
		f.cdDepstep3.value = codeArr[2];
		f.subframe.value = parseInt(codeArr[3]) + 1;
		
		if(codeArr[0] != "00"){
			$.ajax({
				type : "POST",
				url : "<c:url value='/boffice/group/dept/deptSelectAx.do'/>",
				data : { "cdIdx" : cdIdx },
				dataType : "json",
				success : function(data) {
					$("#deptStep").text(data.groupDeptView.deptPath);
					$("#cdIdx").val(data.groupDeptView.cdIdx);
					$("#cdSubject").val(data.groupDeptView.cdSubject);
					$("#cdTel").val(data.groupDeptView.cdTel);
					$("#cdFax").val(data.groupDeptView.cdFax);
					$("#cdEngSubject").val(data.groupDeptView.cdEngSubject);
					$("#cdChiSubject").val(data.groupDeptView.cdChiSubject);
					$("#cdArea").val(data.groupDeptView.cdArea);
					$("#cdText").val(data.groupDeptView.cdText);
					$("#cdDepstep1").val(data.groupDeptView.cdDepstep1);
					$("#cdDepstep2").val(data.groupDeptView.cdDepstep2);
					$("#cdDepstep3").val(data.groupDeptView.cdDepstep3);
					$("#position").val(data.groupDeptView.position);
				},
				error : function(xhr, status, error) {
					alert(status);
				}
			});
		}else{
			$("#menuTree").jstree("open_node", d.node.id);
		}
	}).bind("move_node.jstree",function(e,d){ //드레그이동
	});
    <c:if test="${not empty param.selBox}">
    	<c:set var="selBox" value="${fn:replace(param.selBox, '/', '|')}" />
   		$("#menuTree").jstree("select_node", "<c:out value="${selBox}" />");
	</c:if>
	//버튼이벤트
	$(".btn_add").click(function(e){
		if(confirm("하위부서를 추가하시겠습니까?")){
			if(document.regFrom.subframe.value > 3){
				alert(document.regFrom.deptNm.value + " 밑에 하위부서를 추가할 수 없습니다.");
				return;
			}
			document.regFrom.submit();
		}
	});
	$(".btn_del").click(function(e){
		if(!confirm("<spring:message code="common.delete.msg" />")){
			return;
		}
		document.groupDeptVo.action = "/boffice/group/dept/GroupDept_Rmv.do";
		document.groupDeptVo.submit();
	});
	$(".btn_mod").click(function(e){
		if(confirm("부서 정보를 수정 하시겠습니까?")){
			document.groupDeptVo.action = "/boffice/group/dept/GroupDeptDetail_Mod.do";
			document.groupDeptVo.submit();
		}
	});
	$(".btn_emp_view").click(function(e){
		var cdIdx = $("#cdIdx").val();		
		$(location).attr("href","/boffice/group/emp/Emp_List.do?selBox=" + cdIdx);
	});
});
</script>
<!-- //페이지 전용 스타일/스크립트 -->
</head>
<body>
	<form name="regFrom" id="regFrom" action="<c:url value="/boffice/group/dept/GroupDept_Reg.do"/>" method="post">
		<input type="hidden" name="deptNm" value=""/>
		<input type="hidden" name="cdSubject" value=""/>
		<input type="hidden" name="cdDepstep1" value="00"/>
		<input type="hidden" name="cdDepstep2" value="00"/>
		<input type="hidden" name="cdDepstep3" value="00"/>
		<input type="hidden" name="subframe" value="1"/>
	</form>
	<div class="section divGroup leftTree">
		<div class="left">
			<div class="treeTopBtn">
				<input type="button" value="하위부서추가" class="btn_inline btn_add">
			</div>

			<div id="menuTree">
				<ul>
					<li id="00|00|00|0"><a href="#">HOME</a>
						<c:set var="level" value="0" />
						<c:forEach items="${selectList}" var="result" varStatus="status">
							<c:choose>
								<c:when test="${result.level > level || status.index==0}">
									<ul>
										<li id="<c:out value="${result.cdDepstep1}|${result.cdDepstep2}|${result.cdDepstep3}|${result.level}|${result.cdIdx}"/>"><a href="#"><c:out value="${result.cdSubject }" /></a>
								</c:when>
								<c:when test="${result.level < level}">
									<c:forEach var="i" begin="1" end="${level-result.level}">
											</li>								
										</ul>
									</c:forEach>
									</li>
									<li id="<c:out value="${result.cdDepstep1}|${result.cdDepstep2}|${result.cdDepstep3}|${result.level}|${result.cdIdx}"/>"><a href="#"><c:out value="${result.cdSubject }" /></a>									
								</c:when>
								<c:otherwise>
									</li>
									<li id="<c:out value="${result.cdDepstep1}|${result.cdDepstep2}|${result.cdDepstep3}|${result.level}|${result.cdIdx}"/>"><a href="#"><c:out value="${result.cdSubject }" /></a>															
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
				<div class="btnArea">
					<a href="#" class="btn_inline btn_emp_view">직원보기</a>
					<a href="#" class="btn_inline btn_mod">수정</a>
					<a href="#" class="btn_inline btn_caption btn_del">삭제</a>
				</div>
			</div>
			<form:form commandName="groupDeptVo" name="groupDeptVo">
				<form:hidden path="cdIdx" />
				<form:hidden path="cdCode" />
				<form:hidden path="cdDepstep1" />
				<form:hidden path="cdDepstep2" />
				<form:hidden path="cdDepstep3" />
				<form:hidden path="position" />
				<div class="tableBox">
					<table class="form">
					<caption></caption>
					<colgroup>
						<col class="w15p">
						<col>
						<col class="w15p">
						<col>
					</colgroup>
					<tbody>
						<tr>
							<th>부서경로</th>
							<td colspan="3" id="deptStep"><c:out value="${groupDeptVo.deptPath == null ? '부서를 선택해주세요.' :  groupDeptVo.deptPath}"/></td>
						</tr>
						<tr>
							<th><label for="cdSubject"><span class="required">필수입력</span>부서명</label></th>
							<td colspan="3"><form:input path="cdSubject" cssClass="w100p"/></td>
						</tr>
						<tr>
							<th><label for="cdTel">전화</label></th>
							<td><form:input path="cdTel" cssClass="w100p"/></td>
							<th><label for="cdFax">팩스</label></th>
							<td><form:input path="cdFax" cssClass="w100p"/></td>
						</tr>
						<tr>
							<th><label for="cdEngSubject">영문이름</label></th>
							<td><form:input path="cdEngSubject" cssClass="w100p"/></td>
							<th><label for="cdChiSubject">중문이름</label></th>
							<td><form:input path="cdChiSubject" cssClass="w100p"/></td>
						</tr>
						<tr>
							<th><label for="cdArea">위치정보</label></th>
							<td colspan="3"><form:input path="cdArea" cssClass="w100p"/></td>
						</tr>
						<tr>
							<th><label for="cdText">주요업무</label></th>
							<td colspan="3"><form:textarea path="cdText" cssClass="w100p h150" /></td>
						</tr>
					</tbody>
					</table>
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>