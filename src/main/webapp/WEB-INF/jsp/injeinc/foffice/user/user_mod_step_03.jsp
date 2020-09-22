<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>

<script type="text/javascript" src="<c:url value='/js/common.js' />"></script>
<script type="text/javascript">
//<![CDATA[
    var emailchk = /^(?=.*[a-zA-Z])(?=.*[0-9])$/;
    
  //주소팝업
	function openAddrSearch() {
		var from = document.form;
		doJusoPop(from);
	}

	function setAddrValue(addr1, addr2, zip) {
		$("#zip").val(zip);
		$("#addr1").val(addr1);
		$("#addr2").val(addr2);
		$("#addr2").focus();
	}
	
	 //이메일 선택시
    function chgdomain(){
    	$("input[name=emaildomain]").val($("select[name=stdomain]").val());
    }
	 
  //가입버튼 클릭시
    function doUserFMod(){
    	if($("input[name=zip]").val() == ''){
    		alert("우편번호검색을 해주세요");
    		$('#zipsrch').focus();
    		return;
    	}else if($("input[name=ph1]").val() == ''){
    		alert("휴대폰번호 앞자리를 입력해주세요");
    		$("input[name=ph1]").focus();
    		return;
    	}else if($("input[name=ph2]").val() == ''){
    		alert("휴대폰번호 중간자리를 입력해주세요");
    		$("input[name=ph2]").focus();
    		return;
    	}else if($("input[name=ph3]").val() == ''){
    		alert("휴대폰번호 뒷자리를 입력해주세요");
    		$("input[name=ph3]").focus();
    		return;
    	}else if($("input[name=emailid]").val() == ''){
    		alert("이메일 아이디를 입력해주세요");
    		$("input[name=emailid]").focus();
    		return;
    	}else if($("input[name=emaildomain]").val() == ''){
    		alert("이메일 주소를 입력해주세요");
    		$("input[name=emaildomain]").focus();
    		return;
    	}else if(emailchk.test($("input[name=emailid]").val())){
    		alert("이메일 주소를 바르게 입력해주세요");
    		$("input[name=emailid]").focus();
    		return;
    	}else if(emailchk.test($("input[name=emaildomain]").val())){
    		alert("이메일 주소를 바르게 입력해주세요");
    		$("input[name=emaildomain]").focus();
    		return;
    	}else{
    		if(confirm("수정 하시겠습니까?")){
	    		$("input[name=telNum]").val($("input[name=tel1]").val()+"-"+$("input[name=tel2]").val()+"-"+$("input[name=tel3]").val());
	    		$("input[name=hpNum]").val($("input[name=hp1]").val()+"-"+$("input[name=hp2]").val()+"-"+$("input[name=hp3]").val());
	    		$("input[name=email]").val($("input[name=emailid]").val()+"@"+$("input[name=emaildomain]").val());
	    		$("input[name=emailRcvChk]").val($(":radio[name=emailyn]:checked").val());
	    		$("input[name=smsRcvYn]").val($(":radio[name=smsyn]:checked").val());
	    		document.UserFVo.action ="/site/seocho/foffice/user/User_Mod.do";
	    		document.UserFVo.submit();
    		}
    	}
    }
  
  //탈퇴
  function doUserFOut(){
	  if(confirm("정말 탈퇴하시겠습니까?")){
		  document.UserFVo.action ="/site/seocho/foffice/user/User_Out.do";
		  document.UserFVo.submit();
	  }
  }
  function cm_combo_email(pCombo_id,pData_use) {
		$.ajax({
			url : "/common/combo_commonAx.do"
			, type : "POST"
			, dataType : "json"
			, async : false
			, data : "DataUse="+pData_use
			, success : function(data) {
				
				var svcCode = data.SVC_CODE;
				var svcMsge = data.SVC_MSGE;
				
				if (svcCode == 400) {
					alert(svcMsge);
					return;
				}
				
				var rows = data.rowDataList;
				
				$(pCombo_id + " option:eq(0)").nextAll().remove();
				
				for (var i=0; i < rows.length; i++) {
					var row = data.rowDataList[i];
				
					$(pCombo_id + " option:eq("+i+")").after("<option value='" + row.codeName.trim() + "'>" + row.codeName.trim() +"</option>");
				}
				
			}
			,error : function(code, msg, error) {
				var str = code.status + " : " + msg + " : " + error;
				console.log("cm_combo >>>" + str);
			}
		});
	}
  
	//취소
	function doCancel(){
		window.location.href="<c:url value='/site/seocho/foffice/user/User_Cancel.do'/>";
	}
	
  $(window).load(function() {
  	cm_combo_email('#stdomain','EMAIL_CD');
	});
  
//]]>
</script>
<!-- 주소팝업사용시 무조건 만드셔야됨. -->
<form id="form" name="form" method="post" class="hide">
	<input type="hidden" id="confmKey" name="confmKey" value="bnVsbDIwMTQxMDIzMTc0NTQ2"/>
	<input type="hidden" id="returnUrl" name="returnUrl" value=""/>
</form>
<!-- 주소팝업사용시 무조건 만드셔야됨. -->

<div class="board">
<form:form commandName="UserFVo" name="UserFVo" method="post">
<form:hidden path="cuId" />
<form:hidden path="telNum" />
<form:hidden path="hpNum" />
<form:hidden path="email" />
<form:hidden path="emailRcvChk" />
<form:hidden path="smsRcvYn" />
	<p class="required-guide"><span class="required">*</span> 표시된 항목은 <em>필수입력</em> 항목입니다.</p>
	<table class="regist">
		<caption>회원정보 수정폼</caption>
		<colgroup>
			<col class="w15"><col>
		</colgroup>
		<tbody>
			<tr>
				<th scope="row">아이디</th>
				<td><c:out value="${UserFVo.cuId }" /></td>
			</tr>
			<tr>
				<th scope="row">이름</th>
				<td><c:out value="${UserFVo.cuName }" /></td>
			</tr>
			<tr>
				<th scope="row"><label for="zip"><span class="required">*</span>주소</label></th>
				<td class="addr">
					<div class="mgb5">
						<form:input path="zip" id="zip" size="6" />
						<a href="javascript:openAddrSearch();" class="btn-inner" title="새창">우편번호찾기</a>
					</div>
					<form:input path="addr1" id="addr1" class="w90 mgb5" />
					<form:input path="addr2" id="addr2" class="w90" />
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="tel1">전화번호</label></th>
				<td>
					<form:input path="tel1" id="tel1" size="5" title="전화번호 앞자리" /> -
					<form:input path="tel2" id="tel2" size="5" title="전화번호 중간자리" /> -
					<form:input path="tel3" id="tel3" size="5" title="전화번호 뒷자리" />
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="hp1"><span class="required">*</span>핸드폰번호</label></th>
				<td>
					<form:input path="hp1" id="hp1" size="5" title="핸드폰번호 앞자리" /> -
					<form:input path="hp2" id="hp2" size="5" title="핸드폰번호 중간자리" /> -
					<form:input path="hp3" id="hp3" size="5" title="핸드폰번호 뒷자리" />
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="emailid"><span class="required">*</span>이메일</label></th>
				<td>
					<form:input path="emailid" id="emailid" size="20" title="이메일 아이디" /> @
					<select name="stdomain" id="stdomain" title="도메인 선택" onchange="chgdomain();">
						<option value="">직접입력</option>
					</select>
					<form:input path="emaildomain" id="emaildomain" size="20" title="도메인주소" />
				</td>
			</tr>
			<tr>
				<th scope="row"><label for=""><span class="required">*</span>이메일수신여부</label></th>
				<td>
					<p>관악구에서 제공하는 행정정보(행사, 소식 등) 안내를 이메일로 수신하시겠습니까?</p>
					<label class="mgr20"><input type="radio" name="emailyn" value="Y" <c:if test="${UserFVo.emailRcvChk eq 'Y'}">checked="true"</c:if>> 예</label>
					<label><input type="radio" name="emailyn" value="N" <c:if test="${UserFVo.emailRcvChk eq 'N'}">checked="true"</c:if>> 아니오</label>
				</td>
			</tr>
			<tr>
				<th scope="row"><label for=""><span class="required">*</span>SMS 수신 여부</label></th>
				<td>
					<p>관악구에서 제공하는 행정정보(행사, 소식 등) 안내를 핸드폰 문자서비스로 수신하시겠습니까?</p>
					<label class="mgr20"><input type="radio" name="smsyn" value="Y" <c:if test="${UserFVo.smsRcvYn eq 'Y'}">checked="true"</c:if>> 예</label>
					<label><input type="radio" name="smsyn" value="N" <c:if test="${UserFVo.smsRcvYn eq 'N'}">checked="true"</c:if>> 아니오</label>
				</td>
			</tr>
		</tbody>
	</table>
	<!-- buttons -->
	<div class="btns">
		<div class="left">
			<input type="button" value="탈퇴" class="type1" onclick="doUserFOut();">
		</div>
		<div class="right">
			<input type="button" value="저장" class="type2" onclick="doUserFMod();">
			<input type="button" value="취소" class="type1" onclick="doCancel();">
		</div>						
	</div>
	<!-- //buttons -->
</form:form>
</div><!--//board-->	