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
<script type="text/javascript" src="/js/nia/sojaeji.js"></script>
<script type="text/javascript">

 var JSAdd;

function AddJS(path){
	head = document.getElementsByTagName('HEAD')[0];
	if (JSAdd){ head.removeChild(JSAdd); }
	JSAdd = document.createElement('SCRIPT');
	JSAdd.innerHTML = path;
	head.appendChild(JSAdd);
}

$(function(){
	//이메일 직접 입력 시 Input박스 보이게 처리
	emailBox();
	
	$("#emailCont2").change(function(e){
		emailBox();
	});
});

function doBbsFReg() {	
	if($("#ext11").val() == ""){
		alert("분류를 선택해주세요.");
		$("#ext11").focus();
		return;
	}
	
	if($("#nameCont1").val() == ""){
		alert("작성자를 입력해주세요.");
		$("#nameCont1").focus();
		return;
	}
	
	if($("#gubPassword1").val() != ""){
		alert("비밀번호를 입력해주세요.");
		$("#gubPassword1").focus();
		return;
	}
	
	if($("#gubPassword2").val() == ""){
		alert("비밀번호 확인을 입력해주세요.");
		$("#gubPassword2").focus();
		return;
	}else if($("#gubPassword1").val() != $("#gubPassword2").val()){
		alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
		$("#gubPassword2").focus();
		return;
	}
	
	if($("#emailCont1").val() == ""){
		alert("이메일을 입력해주세요.");
		$("#emailCont1").focus();
		return;
	}
	
	if($("#emailCont2").val() == ""){
		if($("#emailCont3").val() == ""){
			alert("이메일을 입력해주세요.");
			$("#emailCont3").focus();
			return;
		}
	}
	
	if($("#telCont2").val() == ""){
		alert("전화번호 중간자리를 입력해주세요.");
		$("#telCont2").focus();
		return;
	}
	
	if($("#telCont3").val() == ""){
		alert("전화번호 뒷자리를 입력해주세요.");
		$("#telCont3").focus();
		return;
	}
	
	if($("#subCont1").val() == ""){
		alert("제목을 입력해주세요.");
		$("#subCont1").focus();
		return;
	}
	
	if($("#clobCont1").text() == ""){
		alert("문의내용 입력해주세요.");
		$("#clobCont1").focus();
		return;
	}
	
	//저장 시 데이터 처리
	$("#addrCont").val($("#sido1").val()+"_"+$("#gugun1").val()+"_"+$("#dong1").val());
	if($("#emailCont2").val() == ""){
		$("#emailCont").val($("#emailCont1").val()+"@"+$("#emailCont3").val());
	}else{
		$("#emailCont").val($("#emailCont1").val()+"@"+$("#emailCont2").val());
	}		
	$("#telCont").val($("#telCont1").val()+"-"+$("#telCont2").val()+"-"+$("#telCont3").val());

	$("#bbsFVo").submit();
}

function openAddrSearch() {

	var from = document.form;
	doJusoPop(from);
}

function emailBox(){
	if($("#emailCont2").val() != ""){
		$("#emailCont3").hide();
	}else{
		$("#emailCont3").show();
	}
}
</script>
<body>	
	<div id="container">
		<div class="containerTop type1">
			<div class="inner">
				<h3><c:out value="${bbsFVo.cbName}"/></h3>
			</div>
		</div>
		<div id="contents">
			<div class="inner">
				<p class="top_info">디지털배움터에 관한 문의사항과 칭찬하기, 민원사항을 작성하실 수 있습니다.</p>
	<!-- Board Top -->
	<!-- // Board center -->
	<!-- 주소팝업사용시 무조건 만드셔야됨. -->
	<%-- <form id="form" name="form" method="post" class="hide">
		<input type="hidden" id="confmKey" name="confmKey" value="bnVsbDIwMTQxMDIzMTc0NTQ2" />
		<input type="hidden" id="returnUrl" name="returnUrl" value="" />
	</form> --%>
	<!-- //주소팝업사용시 무조건 만드셔야됨. -->	
	<form:form commandName="bbsFVo" id="bbsFVo" name="bbsFVo" method="post" enctype="multipart/form-data" action="/site/${strDomain}/ex/bbs/BoardRegProc.do">
		<form:hidden path="cbIdx" />
		<form:hidden path="bcIdx" />
		<form:hidden path="mode" />
		<form:hidden path="ssId" />
		<form:hidden path="dupcode" />
		<form:hidden path="parentSeq" />
		<form:hidden path="answerStep" />
		<form:hidden path="answerDepth" />
		<form:hidden path="searchKey" />
		<form:hidden path="pageIndex" />
		<form:hidden path="tgtTypeCd" />
		<form:hidden path="clobContTemp" />
		<form:hidden path="subContTemp" />
		<form:hidden path="fileMaxSize" />
		<input type="hidden" name="tempname"/>
		<input type="hidden" name="tempsname"/>
		<input type="hidden" name="tempsize"/>
		<input type="hidden" name="strDomain" id="strDomain" value="${strDomain}"/>
	<div class="tableBox">
		<table class="write">
			<colgroup>
				<col style="width:10%;">			
				<col>
				<col style="width:10%;">
				<col>
			</colgroup>
			<tbody>
				<tr>
					<th scope="row">분류</th>
					<td>
						<c:set var="itemCode" value="1"/>
						<c:forEach var="labelList" items="${labelPropGbnList}">
							<c:if test="${labelList.LABEL_PROP_GBN  eq '16020700'}">
								<c:set var="itemCode" value="${labelList.ITEM_CODE}"/>
							</c:if>
						</c:forEach>
						<c:if test="${itemCode ne '1'}">
							<c:set var="codeList" value="${cmm:getCodeList(itemCode)}" />
							<select name="ext1" id="ext11" class="w160" >
								<option value="">선택</option>
								<c:forEach var="itemInfo" items="${codeList}" varStatus="status">
									<option value="<c:out value="${itemInfo.code}"/>" <c:if test="${itemInfo.code eq detailMap.ext1}">selected="selected"</c:if> ><c:out value="${itemInfo.codeName}"/></option>
								</c:forEach>
							</select>
						</c:if>
					</td>
					<th scope="row">작성자</th>
					<td>
						<input type="text" name="nameCont" id="nameCont1" title="작성자입력" class="w160" value="<c:out value='${detailMap.nameCont}'/>"/>
					</td>
				</tr>
					<input type="hidden" name="ansYnCont" id="ansYnCont" value="22000100"/>
					<tr>
						<th scope="row">비밀번호</th>
						<td>
							<input type="password" name="gubPassword" id="gubPassword1" class="w200"/>
						</td>
						<th scope="row">비밀번호 확인</th>
						<td><input type="Password" id="gubPassword2" value="" class="w200"></td>
					</tr>
				<tr>
				  <th scope="row">지역</th>
				  <td class="left" colspan="3">
				  	<input type="hidden" name="addrCont" id="addrCont" value=""/>
					<select name="sido1" id="sido1"></select>
					<select name="gugun1" id="gugun1" class="w120"></select>
					<select name="dong1" id="dong1" class="w120"></select>
				  </td>
				</tr>
				<tr>
					<th scope="row">이메일</th>
					<td>
						<c:set var="email" value="${fn:split(detailMap.emailCont,'@')}"/>
						<input type="hidden" name="emailCont" id="emailCont" value=""/>
						<input type="text" id="emailCont1" class="input_04" value="<c:out value="${email[0]}"/>"/>@
							<select id="emailCont2">
								<option value="" >직접입력</option>
									<c:forEach items="${emailList}" var="emailList" varStatus="status">
										<option value="<c:out value="${emailList.codeName}"/>" <c:if test="${emailList.codeName eq email[1]}">selected="selected"</c:if> label="${emailList.codeName}"></option>
									</c:forEach>
							</select>
						<input type="text" id="emailCont3" class="input_04" value="<c:out value="${email[1]}"/>"/> 
					</td>
					<th scope="row">연락처</th>
					<td>
						<c:set var="tel" value="${fn:split(detailMap.telCont,'-')}"/>
						<input type="hidden" name="telCont" id="telCont" value=""/>
						<select id="telCont1" class="w70">
							<c:forEach items="${phoneList}" var="phoneList" varStatus="status">
								<option value="<c:out value="${phoneList.codeName}"/>" <c:if test="${phoneList.codeName eq tel[0]}">selected="selected"</c:if> label="${phoneList.codeName}"></option>
							</c:forEach>
							<c:forEach items="${telList}" var="telList" varStatus="status">
								<option value="<c:out value="${telList.codeName}"/>" <c:if test="${telList.codeName eq tel[0]}">selected="selected"</c:if> label="${telList.codeName}"></option>
							</c:forEach>
						  </select>
						-
						<input type="text" id="telCont2" value="<c:out value="${tel[1]}"/>" title="전화번호 중간자리" class="w80" maxlength="4" >
						-
						<input type="text" id="telCont3" value="<c:out value="${tel[2]}"/>" title="전화번호 뒷자리" class="w80" maxlength="4" >
					</td>
				</tr>	
				<tr>
					<th scope="row">제목</th>
					<td colspan="3"><input type="text" name="subCont" id="subCont1" value="<c:out value="${detailMap.subCont}"/>" title="제목입력" class="w70p"></td>
				</tr>	
				<tr>
					<th scope="row">문의내용</th>
					<td colspan="3">
						<textarea class="w100p h150" name="clobCont" id="clobCont1"><c:out value="${detailMap.clobCont}"/></textarea>
					</td>
				</tr>	
			</tbody>
		</table>
	</div>
</form:form>	
<!-- // Board center -->	
<!-- // Board foot -->		
<div class="btnArea">
	<a href="javascript:doBbsFReg();" class="btn_l">등록</a>
	<a href="/site/nia/ex/bbs/List.do?cbIdx=1188" class="btn_l blue">취소</a>
</div>
<!-- // Board foot -->
							
<script type="text/javascript">
	new sojaeji('sido1', 'gugun1', 'dong1');
	
	<c:if test="${not empty detailMap}">
		var addrCont = '<c:out value="${detailMap.addrCont}"/>';
		var addr = addrCont.split("_");
	
		$("#sido1").val(addr[0]).trigger('change');;
		$("#gugun1").val(addr[1]).trigger('change');;
		$("#dong1").val(addr[2]); 
	</c:if>
</script>