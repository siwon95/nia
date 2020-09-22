<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>









<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd" >
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>통합관리시스템</title>
<link href="/css/admin.css" rel="stylesheet" type="text/css" />
<link href="/css/site/mnt/board.css" rel="stylesheet" type="text/css" />
<link href="/css/site/mnt/layout.css" rel="stylesheet" type="text/css" />
<link href="/css/jquery-ui.css" rel="stylesheet" type="text/css" />
<link href="/css/jquery.timepicker.css" rel="stylesheet" type="text/css" />
<link href="/css/style.min.css" rel="stylesheet" type="text/css" />
<link href="/js/vakata-jstree-5bece58/dist/themes/default/style.min.css"	rel="stylesheet" type="text/css" />

<script type="text/javascript" src="/js/vakata-jstree-5bece58/dist/jquery.js"></script>
<script type="text/javascript" src="/js/vakata-jstree-5bece58/dist/jstree.min.js"></script>

<script type="text/javascript" src="/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="/js/jquery/jquery.plugin.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript" src="/crosseditor3_5/js/namo_scripteditor.js"></script>
<!-- page head start -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript">
//<![CDATA[

           function doMenuUpdate() {
				var frm = document.MgrMenuVo;
				$("#mmHelp").val(CrossEditor.GetBodyValue());
				frm.action = "/boffice_noDeco/sy/menu/MgrMenuHelpMod.do";
				frm.submit();
			}
			
	
			
//]]>
</script>
</head>
<body>
		<div style="width: 100%">
			<!-- 컨텐츠 영역 -->
			<div id="contents">
				
<form:form commandName="MgrMenuVo" id="MgrMenuVo" name="MgrMenuVo" method="post">
	<form:hidden path="mmCd" />

					<table summary="" class="write" width="100%">
					<caption></caption>
					<colgroup>
						<col width="150px" />
						<col width="*" />
					</colgroup>
					<tbody>
						<tr>
							<th>메뉴명</th>
							<td><c:out value="${MgrMenuVo.mmName}"/></td>
						</tr>
						
						<tr>
							<td colspan="2">
								<c:out value="${MgrMenuVo.mmHelp}" escapeXml="false"/>
							 
							</td>
						</tr>
					</tbody>
				</table>
				<!-- 버튼 -->
				
				<div class="btn_zone">
						<a href="javascript:window.close();" class="btn2">닫기</a>
						&nbsp;
				</div>
</form:form>

	</div>

</body>
</html>



