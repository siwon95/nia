<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<script type="text/javascript">
if("<c:out value='${result_cd}' />" == "ok"){
	location.href = "/synap/skin/doc.html?fn=<c:out value='${fn}' />&rs=<c:out value='${rs}' />&rfn=<c:out value='${rfn}' />";
}else{
	alert('에러가 발생하였습니다.');
	self.close();
}
</script>
</head>
</html>