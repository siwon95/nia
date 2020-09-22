
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/css/admin.css" rel="stylesheet" type="text/css" />
<link href="/css/board.css" rel="stylesheet" type="text/css" />
<link href="/css/jquery-ui.css" rel="stylesheet" type="text/css" />
<link href="/css/jquery.timepicker.css" rel="stylesheet" type="text/css" />
<link href="/css/style.min.css" rel="stylesheet" type="text/css" />
<link href="/js/vakata-jstree-5bece58/dist/themes/default/style.min.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="/js/jquery/jquery.1.10.2.js"></script>

<title>직원정보 수정</title>
<script type="text/javascript">
//<![CDATA[
	function doModifyEmpTemp(){
		document.EmpTempVo.action = "/boffice/emp/Modify_EmpTemp.do";
		document.EmpTempVo.submit();
	}
	
	$(document).ready(function() {
		<c:if test='${message != null && message eq "update"}'>
			window.opener.location.reload(true);
			window.close();
		</c:if>
	});
//]]>
</script>
</head>
<body style="text-align:center; margin:0 auto; display:inline; padding-top:100px;">
	<div id="contents">
		
		<form:form commandName="EmpTempVo" name="EmpTempVo" method="post">	
			<form:hidden path="userIdx"/>
			<table summary="" class="write">
				<caption>직원정보 테이블</caption>
				<colgroup>
					<col width="20%" />
					<col width="30%" />
					<col width="20%" />
					<col width="30%" />
					<col/>
	 			</colgroup>
	 			<tr>
	 				<th> 
	 					<label for="userName">이름</label>					
	 				</th>
	 				<td>
	 					<form:input path="userName"/>
	 				</td>
	 				<th> 					
	 					<label for="rankName">직책</label>
	 				</th>
	 				<td>
	 					<form:input path="rankName"/>
	 				</td>
	 			</tr>
	 			<tr>
	 				<th>
	 					<label for="sectionName">팀이름</label>					
	 				</th>
	 				<td>
	 					<form:input path="sectionName"/>
	 				</td>
	 				<th>
	 					<label for="deptName">과이름</label>
	 				</th>
	 				<td>
	 					<form:input path="deptName"/>
	 				</td>
	 			</tr>
	 			<tr>
	 				<th>
	 					<label for="tel">tel</label>
	 				</th>
	 				<td>
	 					<form:input path="tel"/>
	 				</td>
	 				<th>
	 					<label for="mobile">mobile</label>
	 				</th>
	 				<td>
	 					<form:input path="mobile"/>
	 				</td>
	 			</tr>
	 			<tr>
	 				<th>
	 					<label for="mail">email</label>
	 				</th>
	 				<td colspan="3">
	 					<form:input path="mail"/>
	 				</td>
	 			</tr>
	 			<tr>
	 				<th>
	 					<label for="workDivisionContents">업무내용</label>
	 				</th>
	 				<td colspan="3">
	 					<form:textarea path="workDivisionContents"/>
	 				</td>
	 			</tr>
	 			<tr>
	 				<th>
	 					<label for="useYn">use_yn</label>				
	 				</th>
	 				<td>
	 					<form:input path="useYn"/>
	 				</td>
	 				<th>
	 					<label for="markYn">mark_yn</label>
	 				</th>
	 				<td>
	 					<form:input path="markYn"/>
	 				</td>
	 			</tr>
	 			<tr>
	 				<th>
	 					<label for="userStatus">user_status</label>	
	 				</th>
	 				<td>
	 					<form:input path="userStatus"/>
	 				</td>
	 				<th>
	 					<label for="odr">odr</label>
	 				</th>
	 				<td>
	 					<form:input path="odr"/>
	 				</td>
	 			</tr>
			</table>
		</form:form>
		<!-- //조직도연동 리스트-->
		<div class="btn_zone">
			<div class="center">
				<a href="javascript:doModifyEmpTemp();" class="btn1">저장</a>
			</div>
		</div>
	</div>
	<!-- //컨텐츠 영역 -->
</body>
</html>
