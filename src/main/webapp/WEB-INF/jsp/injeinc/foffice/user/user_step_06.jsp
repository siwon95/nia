<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script src="/js/cms/lib.js"></script>
<script type="text/javascript">
//정규식
var confirmid = false;
var idchk = /^[a-z0-9_]{4,12}$/;
//비밀번호 9자 이상 영문, 숫자, 특수문자 조합
var pwchk = /^(?=.*[a-zA-Z])(?=.*[~!@#$%^&*()_+|\=-]).{9,}$/;
var emailchk = /^(?=.*[a-zA-Z])(?=.*[0-9])$/;
var domain = /[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;


//공동코드 ajax 
$(document).ready(function() {
	
	//공통코드
	$.ajax({
		type: "GET",
		url: "<c:url value='/site/${strDomain}/ex/bbs/CmmCodeAx.do'/>",
		data : { "cmmCode" : 'EMAIL_CD' , "cmmCode2th" : 'TEL_CD' , "cmmCode3th" : 'CELL_CD'},
		dataType : "json",
		success:function(data){
			//도메인 주소
			$.each(data.cmmCode, function (index, item) {
				 var EMAIL_CD = '<c:out value="${userlist.emaildomain }"/>';
				 if (EMAIL_CD != item.codeName) {
					$("#emaildomain").append("<option value='"+item.codeName+"'>"+item.codeName+"</option>");
						} else {
							$("#emaildomain").append("<option value='"+item.codeName+"'selected>"+item.codeName+"</option>");
						} 
    		});
			
			//지역전화번호
			$.each(data.cmmCode2th, function (index, item) {
				 var tel1 = '<c:out value="${userlist.telNum }"/>';
				 var tel1_SPLIT = tel1.split('-');
				 var telNum = tel1_SPLIT[0];
				 
				if(telNum !=item.codeName){
					$("#tel1").append("<option value='"+item.codeName+"'>"+item.codeName+"</option>");
				} else {
					$("#tel1").append("<option value='"+item.codeName+"'selected>"+item.codeName+"</option>");
				} 
    		});
			
			//핸드폰앞번호
			$.each(data.cmmCode3th, function (index, item) {
				 var hp1 = '<c:out value="${userlist.hpNum }"/>';
				 var hp1_SPLIT = hp1.split('-');
				 var hp1Num = hp1_SPLIT[0];
				
				if(hp1Num !=item.codeName){
					$("#hp1").append("<option value='"+item.codeName+"'>"+item.codeName+"</option>");
				} else {
					$("#hp1").append("<option value='"+item.codeName+"'selected>"+item.codeName+"</option>");
				} 
    		});
		 },
		 
        error: function(xhr,status,error){
        	alert(status);
        }
	 });
	
});

//우편번호 검색
function openAddrSearch() {
	var protocol=location.protocol;
	var slashes=protocol.concat("//");
	var host=slashes.concat(window.location.hostname);
	var port=location.port;
	
	if (port != '80') {
		host = host+":"+port;
	}
	
	var offset = host + "/cmm/Juso_Set.do";
	var winUrl = "http://www.juso.go.kr/addrlink/addrLinkUrl.do";
	$('#returnUrl').val(offset);
	doCommonPop(form, "JusoPop", winUrl, 570, 420);		
}

function setAddrValue(addr1, addr2, zip) {
	$("#zip").val(zip);
	$("#addr1").val(addr1);
	$("#addr2").val(addr2);
	$("#addr2").focus();
}

// 수정버튼 클릭시
function doUserFModify() {
	var test = "<c:out value='${UserFVo.userMenu}'/>";
	var  emaildomainVal = $("select[name=emaildomain] option:selected").val(); 
	
	if ($("#zip").val() == '') {
		alert("우편번호검색을 해주세요");
		$('#zipsrch').focus();
		return false;
	} else if ($("#tel1").val() == '') {
		alert("연락처 앞자리를 입력하여 주십시오.");
		$("#tel1").focus();
		return false;
	} else if ($("#tel2").val() == '') {
		alert("연락처 중간자리를 입력하여 주십시오.");
		$("#tel2").focus();
		return false;
	} else if ($("#tel3").val() == '') {
		alert("연락처 뒷자리를 입력하여 주십시오.");
		$("#tel3").focus();
		return false;
	} else if ($("#hp1").val() == '') {
		alert("핸드폰 앞자리를 입력하여 주십시오.");
		$("#hp1").focus();
		return false;
	} else if ($("#hp2").val() == '') {
		alert("핸드폰 중간자리를 입력하여 주십시오.");
		$("#hp2").focus();
		return false;
	} else if ($("#hp3").val() == '') {
		alert("핸드폰 뒷자리를 입력하여 주십시오.");
		$("#hp3").focus();
		return false;
	} else if ($("#emailid").val() == '') {
		alert("이메일 아이디를 입력해주세요");
		$("#emailid").focus();
		return false;
	} else if(emaildomainVal == 'X' && $("#emaildomainText").val() == ''){
		alert("도메인을 선택하거나 직접 입력해 주세요")
		return false;
	} else if(emaildomainVal != 'X' && $("#emaildomainText").val() != ''){
		alert("도메인 선택이나 직접입력 둘중하나만 입력해 주세요 ");
		return false;
	}else if (emaildomainVal == 'X' && !domain.test($("#emaildomainText").val())) {
		alert("이메일 주소를 바르게 입력해주세요");
		$("#emaildomainText").focus();
		return false;
	}
	if($("#emaildomainText").is(":empty")){
		$("#emaildomainTextYn").val("N");
	}
	if (confirm("수정 하시겠습니까?")) {
		
		document.UserFVo.action = "/site/DRP0000/user/memberModify_step01.do";
		document.UserFVo.submit();
	}
	
}
	//취소버튼 클릭시 이전페이지 이동
	function doBack() {
		history.back(-1);
	}
</script>

<!-- 여기부터 -->
<h3>회원정보관리</h3>

<h4>정보입력</h4>
<form id="form" name="form" method="post" class="hide">
	<input type="hidden" id="confmKey" name="confmKey" value="bnVsbDIwMTQxMDIzMTc0NTQ2" />
	<input type="hidden" id="returnUrl" name="returnUrl" value="" />
</form>
<form id="UserFVo" name="UserFVo" method="post">
	<input type="hidden" id="addrTarget" name="addrTarget" value="" />
	
	<fieldset>
		<legend>회원정보입력관리</legend>
		<div class="member_insert mTable">
			<span>필수 입력사항입니다.</span>
			<table>
				<caption>회원정보입력</caption>
				<colgroup>
					<col style="width:15%;">
					<col style="width:85%;">
				</colgroup>
				<tbody>
					<tr>
						<th scope="row"><em>필수</em>아이디</th>
						<td>${ss_id }</td>
					</tr>
					<tr>
						<th scope="row">이름</th>
						<td>${ss_name }</td>
					</tr>
					<tr>
						<th scope="row"><em>필수</em>주소</th>
						<td>
							<a href="javascript:openAddrSearch();" class="btn_duble">우편번호검색</a> 
							<label for="아이디값매칭1" class="soundOnly">우편번호</label>
							<input type="text" id="zip" name="zip" class="w100" value="${userlist.zip }">
							
							<label for="아이디값매칭2" class="soundOnly">기본주소</label>
							<input type="text" id="addr1" name="addr1" class="w100p" value="${userlist.addr1 }">
							
							<label for="아이디값매칭3" class="soundOnly">상세주소</label>
							<input type="text" id="addr2" name="addr2" class="w100p" value="${userlist.addr2 }">
						</td>
					</tr>
					<tr> 
						<th scope="row"><em>필수</em>연락처</th>
						<td>
							<c:set var="telNum" value="${userlist.telNum }"/>
							<c:set var="telNumLeng" value="${fn:split(telNum,'-')}"/>
							
							<label for="아이디값매칭4" class="soundOnly">첫번째자리</label>
							<select  id="tel1" name="tel1" class="w130" ></select> -
							
							<label for="아이디값매칭5" class="soundOnly">가운데자리</label>
							<input type="text" id="tel2" name="tel2" class="w130" value="${telNumLeng[1]}" /> -
							
							<label for="아이디값매칭6" class="soundOnly">마지막자리</label>
							<input type="text" id="tel3" name="tel3" class="w130" value="${telNumLeng[2]}" />
						</td>
					</tr>
					<tr>
						<th scope="row"><em>필수</em>휴대전화 번호</th>
						<td>
							<c:set var="hpNum" value="${userlist.hpNum }"/>
							<c:set var="hpNumLeng" value="${fn:split(hpNum,'-')}"/>
							<label for="아이디값매칭7" class="soundOnly">첫번째자리</label>
							<select  id="hp1" name="hp1" class="w130" ></select> -
							
							<label for="아이디값매칭8" class="soundOnly">가운데자리</label>
							<input type="text" id="hp2" name="hp2" class="w130" value="${hpNumLeng[1]}"> -
							
							<label for="아이디값매칭9" class="soundOnly">마지막자리</label>
							<input type="text" id="hp3" name="hp3" class="w130" value="${hpNumLeng[1]}">
						</td>
					</tr>
					<tr>
						<th scope="row"><em>필수</em>이메일 주소</th>
						<td>
							<label for="아이디값매칭10" class="soundOnly">도메인</label>
							<input type="text" id="emailid" name="emailid" class="w270" value="${userlist.emailid }"> @
							
							<label for="아이디값매칭11" class="soundOnly">웹주소</label>
							<select  id="emaildomain" name="emaildomain" class="w130" >
								<option value="X">직접입력</option>
							</select> 
							<label for="아이디값매칭12" class="soundOnly">웹주소직접입력</label>
							<input type="text" id="emaildomainText" name="emaildomainText" value="${userlist.emaildomain }" class="w200" >
						</td>
					</tr>
				</tbody>
			</table>
		</div>

		<div class="btnArea">
			<span class="f_r">
				<input type="submit" class="btn_l large" value="확인" onclick="doUserFModify();return false;">  
				<a class="btn_l large off" onclick="doBack()">취소</a>
			</span>
			<span class="f_l">
				<a href="/site/DRP0000/user/User_Pwd_Chg_Form.do" class="btn_l large on">비밀번호 변경</a>
			</span>
			<div class="c_b"></div>
		</div>
	</fieldset>
</form>

<!-- 여기까지 -->