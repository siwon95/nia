<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<script type="text/javascript">

function fn_no_scrap() {
	
	alert("스크랩은 회원전용 서비스 입니다.");
	document.myscrap.action = "<c:url value='/site/yangcheon/login/Login.do' />";
	document.myscrap.submit();
	return;
	
}

 function fn_scrap() {

	var cbIdx = document.getElementById("bbs_cd_n").value;	
	$('input[name="bbs_cd_n"]').attr("value",cbIdx);
	
  	var bcIdx = document.getElementById("bbs_seq_n").value;
	$('input[name="bbs_seq_n"]').attr("value",bcIdx); 
  
	var scrap_title_v = document.getElementById("scrap_title_v").value;
	
	var scrap_title_t = $("#scrap_title").val();
	
	if(scrap_title_t != null && scrap_title_t != "undefine" && scrap_title_t != ""){
		scrap_title_v = scrap_title_v+"||"+scrap_title_t
	}
	$('input[name="scrap_title_v"]').attr("value",scrap_title_v); 
	
	
	//var pageUrl = document.location; 
	//pageUrl = new String(pageUrl);			
	//pageUrl = pageUrl.replace('http://cms.yangcheon.go.kr','');
	//$('input[name="scrap_loc_v"]').attr("value",pageUrl);
	
	

	if (!confirm("해당 페이지를 스크랩하시겠습니까?")) {	
		return;
	}

	document.myscrap.action = "<c:url value='/site/yangcheon/ex/myscrap/myscrapUserInsert.do' />";
	document.myscrap.submit();
	
} 




/* function fn_scrap() {	
	
	var cbIdx = document.getElementById("bbs_cd_n").value;	
	$('input[name="bbs_cd_n"]').attr("value",cbIdx);
	
  	var bcIdx = document.getElementById("bbs_seq_n").value;
	$('input[name="bbs_seq_n"]').attr("value",bcIdx); 
  
	var scrap_title_v = document.getElementById("scrap_title_v").value;
	$('input[name="scrap_title_v"]').attr("value",scrap_title_v); 
	
alert(cbIdx);

	$.ajax({
		type: "POST",
		url: "<c:url value='/site/yangcheon/ex/myscrap/myscrapUserInsert.do'/>",
		data : {"cbIdx":cbIdx},
		dataType : "json",
		success:function(data) {
			alert(cbIdx);
			alert(data.message);
			confirm("마이페이지로 이동 하시겠습니까?");
			if(data.result) {
				document.myscrap.action = "<c:url value='/site/yangcheon/foffice/ex/myscrap/myscrapUserList.do'/>";
				document.myscrap.submit();
			}
		},
		error: function(xhr, status, error) {
			alert(status);
		}
	});
	
} */



</script>

<form:form commandName="MyscrapVO" name="myscrap" id="myscrap" method="post">
<form:hidden path="bbs_cd_n" />
<form:hidden path="bbs_seq_n" />
<form:hidden path="scrap_loc_v" />
<form:hidden path="scrap_title_v" />
</form:form>