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
var mutifileyn = false;


var tempfilecnt = 0;
function doBbsFReg() {	

	var value = "";
	var value2 = "";
	var value3 = "";

<c:forEach var="labelPropGbnList1" items="${labelPropGbnList}">
		<c:choose>
			<c:when test="${labelPropGbnList1.LABEL_PROP_GBN eq '16020100' }"><%//text Box %>
				value = $('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>1').val();
				//alert(document.bbsFVo.<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>.value );
			</c:when>
			
			<c:when test="${labelPropGbnList1.LABEL_PROP_GBN eq '16020110' && ss_id != null }"><%//PASSWORD %>
			value = $('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>1').val();
			//alert(document.bbsFVo.<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>.value );
			</c:when>
			
			<c:when test="${labelPropGbnList1.LABEL_PROP_GBN eq '16020200' }"><%//text Area %>
				value = $('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>1').val();
			</c:when>
			
			<c:when test="${labelPropGbnList1.LABEL_PROP_GBN eq '16020700' || labelPropGbnList1.LABEL_PROP_GBN eq '16020800' || labelPropGbnList1.LABEL_PROP_GBN eq '16020900'}">
				<c:if test="${labelPropGbnList1.LABEL_PROP_GBN eq '16020700'}"><%//select %>
					value = $('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>1').val();
				</c:if>
				
				<c:if test="${labelPropGbnList1.LABEL_PROP_GBN eq '16020800'}"><%//radio %>
					value = "";
					<c:forEach var="itemList" items="${itemList[selNum]}" varStatus="sstatus">
						if($('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/><c:out value="${sstatus.index+1}"/>').is(':checked')){
							value += $('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/><c:out value="${sstatus.index+1}"/>').val()+"";
						}

					</c:forEach>
				</c:if>
				
				<c:if test="${labelPropGbnList1.LABEL_PROP_GBN eq '16020900'}"><%//checkbox %>
					value = "";
					<c:forEach var="itemList" items="${itemList[selNum]}" varStatus="sstatus">
						if($('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/><c:out value="${sstatus.index+1}"/>').is(':checked')){
							value += $('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/><c:out value="${sstatus.index+1}"/>').val()+"";
						}
					</c:forEach>
				</c:if>				
					<c:set var="selNum" value="${selNum+1}"/>
			</c:when>
			
				<c:when test="${labelPropGbnList1.LABEL_PROP_GBN eq '16021000'}"><%//Writer %>
					value = $('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>1').val();
					//alert(document.bbsFVo.<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>.value );
				</c:when>
				
				<c:when test="${labelPropGbnList1.LABEL_PROP_GBN eq '16020400'}"><%//Address %>
					value = $('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>1').val();
					value2 = $('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>2').val();
					value3 = $('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>3').val();
					//document.bbsFVo.<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>.value = value +"_"+ value2 +"_"+ value3;
					$('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>').val(value +"_"+ value2 +"_"+ value3);
				//alert(document.bbsFVo.<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>.value );
				</c:when>
				
				<c:when test="${labelPropGbnList1.LABEL_PROP_GBN eq '16021100'}"><%//Tel %>
					value = $('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>1').val();
					value2 = $('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>2').val();
					value3 = $('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>3').val();
					//document.bbsFVo.<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>.value = value +"-"+ value2 +"-"+ value3;
					$('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>').val(value +"-"+ value2 +"-"+ value3);
					
				//alert(document.bbsFVo.<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>.value );
				</c:when>
				
				<c:when test="${labelPropGbnList1.LABEL_PROP_GBN eq '16020500'}"><%//Phone %>
					value = $('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>1').val();
					value2 = $('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>2').val();
					value3 = $('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>3').val();
					//document.bbsFVo.<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>.value = value +"-"+ value2 +"-"+ value3;
					$('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>').val(value +"-"+ value2 +"-"+ value3);
				</c:when>
				
				<c:when test="${labelPropGbnList1.LABEL_PROP_GBN eq '16021200'}"><%//Email %>
					value = $('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>1').val();
					value2 = $('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>2').val();
					value3 = $('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>3').val();
					
					$('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>').val(value +"@"+ value2+value3);
					//document.bbsFVo.<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>.value = value +"@"+ value2+value3;	
					//alert(document.bbsFVo.<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>.value);
					
				</c:when>
		
				<c:when test="${labelPropGbnList1.LABEL_PROP_GBN eq '16020600'}"><%//File %>
					value = "file";
				</c:when>
			</c:choose>
			//alert('aaaaaaaaaa');

	</c:if>
			
	//인재에게 바란다. 제외한 (벨리데이터)				
	<c:if test="${bbsFVo.cbIdx ne '414'}">		
		<c:if test="${labelPropGbnList1.LABEL_COMP_YN eq 'Y'}">
			<c:if test="${labelPropGbnList1.LABEL_PROP_GBN ne '16020600' && labelPropGbnList1.LABEL_PROP_GBN ne '16020800'}">
				
		 	if(cm_is_empty(value)){
					alert("<c:out value="${labelPropGbnList1.LABEL_NAME}"/>을(를) 입력해주세요.");
					$('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>1').focus();
					return;
				} 
			</c:if>
			
			
			
			//2016_11_15 벨리데이터 (라디오버튼) 시작
			<c:if test="${labelPropGbnList1.LABEL_PROP_GBN eq '16020800'}">
			
			 if(cm_is_empty($("input:radio[name='<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>']:checked").val()) ){
					alert("<c:out value="${labelPropGbnList1.LABEL_NAME}"/>을(를) 입력해주세요.");
					$("input:radio[name='<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>']:checked").focus;
					return;
				}
			 
			</c:if>
			 
			//2016_11_15 벨리데이터 (라디오버튼)	끝		
	
			<c:if test="${labelPropGbnList1.LABEL_PROP_GBN eq '16020400' || labelPropGbnList1.LABEL_PROP_GBN eq '16021100' || labelPropGbnList1.LABEL_PROP_GBN eq '16020500'}">
				if(cm_is_empty(value2)){
					alert("<c:out value="${labelPropGbnList1.LABEL_NAME}"/>을(를) 입력해주세요.");
					$('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>2').focus();
					return;
				}
				if(cm_is_empty(value3)){
					alert("<c:out value="${labelPropGbnList1.LABEL_NAME}"/>을(를) 입력해주세요.");
					$('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>3').focus();
					return;
				}		
				
			</c:if>
			
			<c:if test="${labelPropGbnList1.LABEL_PROP_GBN ne '16020600'}">
			if ($('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>1').val() == "") {
				alert("<c:out value="${labelPropGbnList1.LABEL_NAME}"/>를 입력하여 주십시오.");
				$('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>1').focus();
				return;
			}
	</c:if>

	<c:if test="${labelPropGbnList1.LABEL_PROP_GBN eq '16020400' || labelPropGbnList1.LABEL_PROP_GBN eq '16021100' || labelPropGbnList1.LABEL_PROP_GBN eq '16020500'}">
		if($('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>2').val() == ""){
			alert("<c:out value="${labelPropGbnList1.LABEL_NAME}"/>을(를) 입력해주세요.");
			$('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>2').focus();
			return;
		}
		if($('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>3').val() == ""){
			alert("<c:out value="${labelPropGbnList1.LABEL_NAME}"/>을(를) 입력해주세요.");
			$('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>3').focus();
			return;
		}		
		
</c:if>
		</c:if>
	</c:if>
	
</c:forEach>

<c:if test="${bbsFVo.mode eq 'U'}">
	document.bbsFVo.submit();    
</c:if>
<c:if test="${bbsFVo.mode eq 'C'}">
	document.bbsFVo.submit();    
</c:if>
<c:if test="${bbsFVo.mode eq 'R'}">
	document.bbsFVo.submit();    
</c:if>   

}

function openAddrSearch() {

	var from = document.form;
	doJusoPop(from);
}

function setAddrValue(addr1, addr2, zip) {

	$("#addrCont1").val(zip);
	$("#addrCont2").val(addr1);
	$("#addrCont3").val(addr2);
	$("#addrCont3").focus();
}



$(window).load(function() {
$(".datepicker").datepicker({
	dateFormat: 'yy-mm-dd',
	buttonImage: '/images/<c:out value='${strDomain}' />/common/icon-calendar.gif',
	

});		
	
function cm_is_empty(val){
	if(val==""){
		return true;
	}
	return false;
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
	<form id="form" name="form" method="post" class="hide">
		<input type="hidden" id="confmKey" name="confmKey" value="bnVsbDIwMTQxMDIzMTc0NTQ2" />
		<input type="hidden" id="returnUrl" name="returnUrl" value="" />
	</form>
	<!-- //주소팝업사용시 무조건 만드셔야됨. -->	
	<form:form commandName="bbsFVo" id="fileupload" name="fileupload" method="post" enctype="multipart/form-data" action="/site/${strDomain}/ex/bbs/File_Upload.do">
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
		<input type="hidden" name="oldGubPassword" id="oldGubPassword" value="${gubPassword}"/>
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
								<option>선택</option>
								<c:forEach var="itemInfo" items="${codeList}" varStatus="status">
									<option value="<c:out value="${itemInfo.code}"/>" <c:if test="${itemInfo.codeName eq detailMap[ext1]}">selected="selected"</c:if> ><c:out value="${itemInfo.codeName}"/></option>
								</c:forEach>
							</select>
						</c:if>
					</td>
					<th scope="row">작성자</th>
					<td>
						<input type="text" name="nameCont" id="nameCont1" title="작성자입력" class="w160" value="<c:out value='${detailMap[nameCont]}'/>"/>
					</td>
				</tr>
				<c:if test="${empty detailMap}">
					<tr>
						<th scope="row">비밀번호</th>
						<td>
							<input type="password" name="gubPassword" id="gubPassword1" class="w200"/>
						</td>
						<th scope="row">비밀번호 확인</th>
						<td><input type="Password" name="gubPassword2" id="gubPassword2" value="" class="w200"></td>
					</tr>
				</c:if>
				<tr>
				  <th scope="row">지역</th>
				  <td class="left" colspan="3">
					<select name="sido1" id="sido1"></select>
					<select name="gugun1" id="gugun1" class="w120"></select>
					<select name="dong1" id="dong1" class="w120"></select>
				  </td>
				</tr>
				<tr>
					<th scope="row">이메일</th>
					<td>
						<c:set var="email" value="${fn:split(detailMap[emailCont],'@')}"/>
						<input type="hidden" name="emailCont" id="emailCont" value=""/>
						<input type="text" name="emailCont1" id="emailCont1" class="input_04" value="<c:out value="${email[0]}"/>"/>@
							<select name="emailCont2" id="emailCont2">
								<option value="" >직접입력</option>
									<c:forEach items="${emailList}" var="emailList" varStatus="status">
										<option value="<c:out value="${emailList.codeName}"/>" <c:if test="${emailList.codeName eq email[1]}">selected="selected"</c:if> label="${emailList.codeName}"></option>
									</c:forEach>
							</select>
						<input type="text" name="emailCont3" id="emailCont3" class="input_04" value="<c:out value="${email[1]}"/>"/> 
					</td>
					<th scope="row">연락처</th>
					<td>
						<c:set var="tel" value="${fn:split(detailMap[telCont],'-')}"/>
						<input type="hidden" name="telCont" id="telCont" value=""/>
						<select name="telCont1" id="telCont1" class="w70">
							<c:forEach items="${phoneList}" var="phoneList" varStatus="status">
								<option value="<c:out value="${phoneList.codeName}"/>" <c:if test="${phoneList.codeName eq tel[0]}">selected="selected"</c:if> label="${phoneList.codeName}"></option>
							</c:forEach>
							<c:forEach items="${telList}" var="telList" varStatus="status">
								<option value="<c:out value="${telList.codeName}"/>" <c:if test="${telList.codeName eq tel[0]}">selected="selected"</c:if> label="${telList.codeName}"></option>
							</c:forEach>
						  </select>
						-
						<input type="text" name="telCont2" id="telCont2" value="<c:out value="${tel[1]}"/>" title="전화번호 중간자리" class="w80" maxlength="4" >
						-
						<input type="text" name="telCont3" id="telCont3" value="<c:out value="${tel[2]}"/>" title="전화번호 뒷자리" class="w80" maxlength="4" >
					</td>
				</tr>	
				<tr>
					<th scope="row">제목</th>
					<td colspan="3"><input type="text" name="subCont" id="subCont1" value="<c:out value="${detailMap[subCont]}"/>" title="제목입력" class="w70p"></td>
				</tr>	
				<tr>
					<th scope="row">문의내용</th>
					<td colspan="3">
						<textarea class="w100p h150" name="clobCont" id="clobCont1" value="<c:out value="${detailMap[clobCont]}"/>"></textarea>
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
	<a href="javascript:history.back();" class="btn_l blue">취소</a>
</div>
<!-- // Board foot -->
							
<script type="text/javascript">
	new sojaeji('sido1', 'gugun1', 'dong1');
</script>