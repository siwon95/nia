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
<script type="text/javascript">
function checkIt() {		
	if($("#gubPassword").val() == ""){
		alert("비밀번호를 입력해주세요.");
		$("#gubPassword").focus();
		return;
	}
	
	$("#bbsFVo").submit();
}
</script>
<body>	
<!-- //container -->
		<div id="container">
			<div class="containerTop type1">
				<div class="inner">
					<h3>소통 게시판</h3>
				</div>
			</div>
			<div id="contents">
				<div class="inner">
					<div id="password_box" class="type2">
						<form:form commandName="bbsFVo" id="bbsFVo" name="bbsFVo" method="post" enctype="multipart/form-data" action="/site/${strDomain}/ex/bbs/View.do?cbIdx=${cbIdx}&bcIdx=${bcIdx}">
							<p>비밀번호를 입력하세요.</p>
							<input type="password" name="gubPassword" id="gubPassword" class="w100p"> 
							<div class="btnArea">
								<a href="#" onclick="checkIt();" class="btn_l" id="re_write">확인</a>
								<a href="/site/nia/ex/bbs/List.do?cbIdx=1188" class="btn_l gray">목록</a>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
		<!-- //container -->