<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script>
//가입하기 
function doGoJoin(youngYn){
	document.UserFVo.youngYn.value = youngYn;
	document.UserFVo.submit();
}
</script>
<!-- 여기부터 -->
<h3>회원가입</h3>

<form:form commandName="UserFVo" name="UserFVo" method="post" action="/site/${strDomain}/user/User_Step_02.do">
	<form:hidden path="youngYn" />
	
	<div class="member_info_bg leave_info_bg">
		<div>
			<span>개인정보 보호법 제22조에 따라 14세 미만 회원이 스마트쉼센터의 다양한 서비스를 이용하기 위해서는 국내 법률상 보호자(법정대리인)의 동의를 받아야 합니다.<br />
					연령에 따라 회원가입절차가 다르므로 해당되는 회원가입 유형을 선택하여 진행하여 주세요. </span>
		</div>
	</div>
	
	<div class="divGroup cols2 member_type">
		<div class="fourteen_up_agree_bg"><span><b>만 14세 이상</b>국내거주인 회원</span>
			<div class="btnArea">
				<a class="btn_l xlarge" href="javascript:doGoJoin('N');" >가입하기</a>
			</div>
		</div>
		
		<div class="fourteen_down_agree_bg"><span><b>만 14세 미만</b>보호자(법적대리인)의 동의가 필요한 회원</span>
			<div class="btnArea">
				<a class="btn_l xlarge on" href="javascript:doGoJoin('Y');">가입하기</a>
			</div>
		</div>
	</div>
</form:form>
<!-- 여기까지 -->
