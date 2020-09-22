<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="/js/cms/lib.js"></script>
<script type="text/javascript">
//정규식
var confirmid = false;
// 아이디 
var idchk = /^[a-z0-9_]{4,12}$/;
//비밀번호 9자 이상 영문, 숫자, 특수문자 조합
var pwchk = /^(?=.*[a-zA-Z])(?=.*[~!@#$%^&*()_+|\=-]).{9,}$/;
var emailchk = /^(?=.*[a-zA-Z])(?=.*[0-9])$/;
var domain = /[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;


function resetId() {
	confirmid = false;
}

//공동코드 ajax 
$(document).ready(function() {

	//공통코드
	$.ajax({
		type: "GET",
		url: "<c:url value='/site/${strDomain}/ex/bbs/CmmCodeAx.do'/>",
		data : { "cmmCode" : 'EMAIL_CD' , "cmmCode2th" : 'TEL_CD' , "cmmCode3th" : 'CELL_CD' , "cmmCode4th" : 'DRP_AREA'},
		dataType : "json",
		success:function(data){
			//도메인 주소
			$.each(data.cmmCode, function (index, item) {
				 var EMAIL_CD = '${UserFVo.emaildomain}';
				 if (EMAIL_CD != item.code) {
					$("#emaildomain").append("<option value='"+item.codeName+"'>"+item.codeName+"</option>");
						} else {
							$("#emaildomain").append("<option value='"+item.codeName+"'selected>"+item.codeName+"</option>");
						} 
    		});
			
			//지역전화번호
			$.each(data.cmmCode2th, function (index, item) {
				var tel1 = '${UserFVo.tel1}';
				
				if(tel1 !=item.code){
					$("#tel1").append("<option value='"+item.codeName+"'>"+item.codeName+"</option>");
				} else {
					$("#tel1").append("<option value='"+item.codeName+"'selected>"+item.codeName+"</option>");
				} 
    		});
			
			//핸드폰앞번호
			$.each(data.cmmCode3th, function (index, item) {
				var hp1 = '${UserFVo.hp1}';
				
				if(hp1 !=item.code){
					$("#hp1").append("<option value='"+item.codeName+"'>"+item.codeName+"</option>");
				} else {
					$("#hp1").append("<option value='"+item.codeName+"'selected>"+item.codeName+"</option>");
				} 
    		});
			
			//지역
			$.each(data.cmmCode4th, function (index, item) {
				var area = '${UserFVo.area}';
				if(area !=item.code){
					$("#area").append("<option value='"+item.code+"'>"+item.codeName+"</option>");
				} else {
					$("#area").append("<option value='"+item.code+"'selected>"+item.codeName+"</option>");
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

//아이디 연속문자 확인
// 비밀번호에 3자리 이상 연속된 동일한 문자(예:aaa)는 사용할 수 없습니다.
function idSame(){   //동일 문자, 숫자 체크(3자리)
	 var tmp = "";
	 var cnt = 0;
	 validpw = $("#cuId").val()
	 for(i=0; i<validpw.length; i++){
		  tmp = validpw.charAt(i);
		  if(tmp == validpw.charAt(i+1) && tmp == validpw.charAt(i+2)){
		   cnt = cnt + 1;
		  }
	 }
	 if(cnt > 0){
	  	return true;
	 }else{
	  	return false;
	 }
}

//아이디 중복확인
function doIdchkAx() {
	var userid = $("#cuId").val()
	
	if(!userid){
		alert("아이디를 입력해주세요");
	}else if(idSame()){
		alert("3자리 이상 연속된 동일한 문자(예:aaa)는 사용할 수 없습니다.");
		$('#cuId').focus();
	}else if(!idchk.test($('#cuId').val())) {
		alert("아이디는 4-12자의 영문 소문자, 숫자 조합만 가능하며 첫글자는 영문이어야 합니다.");
		$('#cuId').focus();
		return;
	} else {
		var id = $("#cuId").val();
		$.ajax({
			type : "GET",
			url : "<c:url value='/site/${strDomain}/user/UserIdCheck_Ax.do' />",
			data : "cuId=" + id,
			dataType : "json",
			success : function(obj) {
				if (obj.message == 'Y') {
					alert("사용가능한 아이디입니다.");
					confirmid = true;
				} else {
					alert("이미사용중인 아이디입니다.\n(탈퇴한 아이디 불가능)");
					confirmid = false;
				}
			},
			error : function(xhr, status, error) {
				alert(status);
				confirmid = 'N';
			}
		});
	}
}

//가입버튼 클릭시
function doUserFReg() {
	var test = "<c:out value='${UserFVo.userMenu}'/>";
	var emaildomainVal = $("select[name=emaildomain] option:selected").val(); 
	
	if(!$("#cuId").val()){
		alert("아이디를 입력해주세요");
	}else if (!confirmid && test != "myform") {
		alert("아이디 중복체크를 해주세요.");
		return false;
	}else if($('input:radio[name=sex]').is(':checked') == false){
		alert("성별을 체크해주세요");
	}else if (!pwchk.test($("#cuPwd").val())) {
  		alert("비밀번호는 영문, 숫자, 특수문자 모두포함 9자 이상 으로 설정 바랍니다");
  		$("#cuPwd").focus();
		return false;
	} else if ($("#cuPwd").val() != $("#cupwdchk").val()) {
		alert("비밀번호, 비밀번호확인이 서로 맞지않습니다. 다시 입력해주세요");
		$('#cupwdchk').focus();
		return false;
	} else if ($("#zip").val() == '') {
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
		alert("휴대전화 앞자리를 입력하여 주십시오.");
		$("#hp1").focus();
		return false;
	} else if ($("#hp2").val() == '') {
		alert("휴대전화 중간자리를 입력하여 주십시오.");
		$("#hp2").focus();
		return false;
	} else if ($("#hp3").val() == '') {
		alert("휴대전화 뒷자리를 입력하여 주십시오.");
		$("#hp3").focus();
		return false;
	}else if ($("#emailid").val() == '') {
		alert("이메일 아이디를 입력해주세요");
		$("#emailid").focus();
		return false;
	} else if(emaildomainVal == 'X' && $("#emaildomainText").val() == ''){
		alert("도메인을 선택하거나 직접 입력해 주세요")
		return false;
	} else if(emaildomainVal != 'X' && $("#emaildomainText").val() != ''){
		alert("도메인 선택이나 직접입력 둘중하나만 입력해 주세요 ");
		return false;
	} else if (emaildomainVal == 'X' && !domain.test($("#emaildomainText").val())) {
		alert("이메일 주소를 바르게 입력해주세요");
		$("#emaildomainText").focus();
		return false;
	}
	
	if (confirm("가입 하시겠습니까?")) {	
		document.UserFVo.action = "/site/${strDomain}/user/User_Reg.do";
		document.UserFVo.submit();
	}
	
}
</script>

<!-- 여기부터 -->
	<h3>회원가입</h3>
	<div class="member_info_bg leave_info_bg">
		<div>
			<span>
				디지털 역기능 대응서비스 통합안내 시스템에 방문하신 것을 환영합니다.<br>
				회원가입을 위해 아래 약관에 동의하여 주십시오.
			</span>
		</div>
	</div>

	<ul class="agree_step">
		<li class="step01">
			<span></span>
			<b>Step01<em>약관동의</em></b>
		</li>
		<li class="step02">
			<span></span>
			<b>Step02<em>본인확인</em></b>
		</li>
		<li class="step03 on">
			<span></span>
			<b>Step03<em>정보입력</em></b>
		</li>
		<li class="step04">
			<span></span>
			<b>Step04<em>가입신청완료</em></b>
		</li>
	</ul>
	<h4>정보입력</h4>
	<form id="form" name="form" method="post" class="hide">
		<input type="hidden" id="confmKey" name="confmKey" value="bnVsbDIwMTQxMDIzMTc0NTQ2" />
		<input type="hidden" id="returnUrl" name="returnUrl" value="" />
	</form>
	<form:form commandName="UserFVo" name="UserFVo" method="post" >
		<input type="hidden" id="addrTarget" name="addrTarget" value="" />
		<form:hidden path="youngAgreeName" />
		<input type="hidden" name="youngYn" value="${userFVo.youngYn }">
		<input type="hidden" name="cuName" value="홍길동">
		
		<fieldset>
			<legend>개인 - 회원정보입력관리</legend>
			<div class="member_insert mTable">
				<span>필수 입력사항입니다.</span>
				<table>
					<caption>개인 - 회원정보입력</caption>
					<colgroup>
						<col style="width:15%;">
						<col style="width:85%;">
					</colgroup>
					<tbody>
						<tr>
							<th scope="row"><label for="아이디값0"><em>필수</em>아이디</label></th>
							<td>
								<input type="text" id="cuId" name="cuId" value="" />
								<a href="javascript:doIdchkAx();" class="btn_duble" id="idchkbutton">아이디 중복확인</a>
							</td>
						</tr>
						<tr>
							<th scope="row">이름</th>
							<td id="cuName" name="">홍길동</td>
						</tr>
						<tr>
							<th scope="row"><em>필수</em>성별</th>
							<td>
								<form:radiobutton path="sex" value="W" />여자
								<form:radiobutton path="sex" value="M" />남자
								
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="아이디값03"><em>필수</em>지역</label></th>
							<td>
								<form:select path="area" class="w130" name="area" >
								</form:select> 
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="아이디값0_1"><em>필수</em>비밀번호</label></th>
							<td>
								<form:password path="cuPwd" id="cuPwd" onblur="pwdValidate();"/>
								<font id="pwdCheck1" style="display: none; color: blue; font-size: 15px;">사용 가능합니다.</font>
								<font id="pwdCheck2" style="display: none; color: red; font-size: 15px;">비밀번호는 영문, 숫자, 특수문자 모두포함 9자 이상 으로 설정 바랍니다</font>
								<span class="txt">※ 비밀번호는 영문, 숫자, 특수문자 모두포함 9자 이상 으로 설정 바랍니다</span>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="아이디값0_2"><em>필수</em>비밀번호 확인</label></th>
							<td>
								<input type="password" name="cupwdchk" id="cupwdchk" onkeyup="chkpwd();">
								<span class="sub-txt" id="pwdchk" style="display: none;">비밀번호가 일치하지 않습니다.</span>
							</td>
						</tr>
						<tr>
							<th scope="row"><em>필수</em>주소</th>
							<td>
								<a href="javascript:openAddrSearch();" class="btn_duble" title="새창열림" id="zipsrch">우편번호검색</a> 
								<label for="아이디값매칭1" class="soundOnly">우편번호</label>
								<form:input path="zip" id="zip" class="w100" />
								
								<label for="아이디값매칭2" class="soundOnly">기본주소</label>
								<form:input path="addr1" id="addr1" readonly="readonly" class="w100p" />
								
								<label for="아이디값매칭3" class="soundOnly">상세주소</label>
								<form:input path="addr2" id="addr2" class="w100p" />
							</td>
						</tr>
						<tr>
							<th scope="row"><em>필수</em>연락처</th>
							<td>
								<label for="아이디값매칭4" class="soundOnly">첫번째자리</label>
								<form:select path="tel1" id="tel1" class="w130" >
								</form:select> -
								
								<label for="아이디값매칭5" class="soundOnly">가운데자리</label>
								<form:input path="tel2" id="tel2" class="w130" /> -
								
								<label for="아이디값매칭6" class="soundOnly">마지막자리</label>
								<form:input path="tel3" id="tel3" class="w130" />
							</td>
						</tr>
						<tr>
							<th scope="row"><em>필수</em>휴대전화 번호</th>
							<td>
								<label for="아이디값매칭7" class="soundOnly">첫번째자리</label>
								<form:select path="hp1" id="hp1" class="w130" >
								</form:select> -
								
								<label for="아이디값매칭8" class="soundOnly">가운데자리</label>
								<form:input path="hp2" id="hp2" class="w130" /> -
								
								<label for="아이디값매칭9" class="soundOnly">마지막자리</label>
								<form:input path="hp3" id="hp3" class="w130" />
							</td>
						</tr>
						<tr>
							<th scope="row"><em>필수</em>이메일 주소</th>
							<td>
								<label for="도메인" class="soundOnly">도메인</label>
								<input type="text" id="emailid" class="w270" name="emailid"> @
								
								<label for="웹주소" class="soundOnly">웹주소</label>
								<form:select title="조건선택" path="emaildomain" id="emaildomain" class="w200" >
									<form:option value="X">직접입력</form:option>
								</form:select>
								
								<label for="웹주소직접입력" class="soundOnly">웹주소직접입력</label>
								<input type="text" id="emaildomainText" name="emaildomain" class="w200">
							</td>
						</tr>
					</tbody>
				</table>
			</div>

			<div class="btnArea">
				<input type="submit" class="btn_l large" value="입력완료" onclick="doUserFReg();return false;"> 
				<a href="/site/DRP0000/main.do" class="btn_l large off" >가입신청취소</a>
			</div>
		</fieldset>
	</form:form>
	<!-- 여기까지 -->