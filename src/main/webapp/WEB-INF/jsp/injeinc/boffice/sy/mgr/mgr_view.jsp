<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Basic Board List</title>
<script type="text/javascript">
//<![CDATA[
function doMgrList() {
	document.MgrVo.action = "<c:url value='/boffice/sy/mgr/Mgr_List.do' />";
	document.MgrVo.mgrDept = "";
	document.MgrVo.submit();
	
}

function doMgrRmv(txt){
	if (!confirm("삭제하시겠습니까?")) {
		return;
	}	
	
	document.MgrVo.mgrIdchk.value = txt;
	document.MgrVo.action = "/boffice/sy/mgr/Mgr_Rmv.do";
	document.MgrVo.submit();
	
}

function doMgrForm(txt){
	document.MgrVo.mgrMenu.value = "update";
	document.MgrVo.mgrIdchk.value = txt;
	document.MgrVo.action = "/boffice/sy/mgr/Mgr_Form.do";	
	document.MgrVo.submit();
}


function doMgrAuthority(txt){
	document.MgrVo.mgrIdchk.value = txt;
	document.MgrVo.action = "/boffice/sy/mgr/Mgr_Authority.do";
	document.MgrVo.submit();
	
}

//]]>
</script>
</head>
<body style="text-align:center; margin:0 auto; display:inline; padding-top:100px;">
<form:form commandName="MgrVo" name="MgrVo" method="post" >
<form:hidden path="sampleId" />
<form:hidden path="pageIndex" />
<form:hidden path="mgrIdchk" />
<form:hidden path="mgrMenu" />
			<!-- 컨텐츠 영역 -->
			<div id="contents">
			
				<table summary="" class="write">
					<caption></caption>
					<colgroup>
						<col width="15%" />
						<col width="*" />
					</colgroup>
					
					<tbody>
					<tr>
						<th>관리자 아이디</th>
						<td><c:out value="${mgrVO.mgrId}" /></td>
					</tr>
					<tr>
						<th>관리자 비밀번호</th>
						<td><c:out value="${mgrVO.mgrPw}" /></td>
					</tr>
					<tr>
						<th>관리자 이름</th>
						<td><c:out value="${mgrVO.mgrName}" /></td>
					</tr>
					<tr>
						<th>관리자등급코드</th>
						<td>
							<c:out value="${mgrVO.permCd}" />
						</td>
					</tr>
					<tr>
						<th>관리자 부서</th>
						<td><c:out value="${mgrVO.mgrDept}" /></td>
					</tr>
					<tr>
						<th>관리자 상태</th>
						<td><c:out value="${mgrVO.statusCd}" /></td>
					</tr>
					<tr>
						<th>대표 여부</th>
						<td>
						<c:out value="${mgrVO.bsYn}" />
						</td>
					</tr>
					</tbody>
				</table>

				
				<!-- 버튼 -->
				<div class="btn_zone">
				    <a href="javascript:doMgrAuthority('<c:out value="${mgrVO.mgrId}" />');" class="btn2">권한변경</a>
					<a href="javascript:doMgrForm('<c:out value="${mgrVO.mgrId}" />');" class="btn2">수정</a>
					<a href="javascript:doMgrRmv('<c:out value="${mgrVO.mgrId}" />');" class="btn1">삭제</a>
					<a href="javascript:doMgrList();" class="btn1">목록</a>
				</div>
				<!-- //버튼 -->
		
			</div>
			<!-- //컨텐츠 영역 -->
</form:form>
</body>
</html>
