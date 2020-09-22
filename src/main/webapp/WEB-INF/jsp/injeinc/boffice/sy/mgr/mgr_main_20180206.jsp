<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%-- ------------------------------------------------------------
- 제목 : 메인
- 파일명 : mgr_main.jsp
- 작성일 : 2018-01-23
- 작성자 : 김근 차장
-------------------------------------------------------------- --%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>통합관리시스템</title>
<script type="text/javascript">
$(window).ready(function(){
	// 달력
	$("input[id=scRegDtSt], input[id=scRegDtSt]").datepicker();
	$("input[id=scRegDtSt], input[id=scRegDtSt]").mask("9999-99-99");
    $("input[id=scRegDtSt], input[id=scRegDtSt]").datepicker( "option", "dateFormat", "yy-mm-dd" );
    $("input[id=scRegDtEd], input[id=scRegDtEd]").datepicker();
	$("input[id=scRegDtEd], input[id=scRegDtEd]").mask("9999-99-99");
    $("input[id=scRegDtEd], input[id=scRegDtEd]").datepicker( "option", "dateFormat", "yy-mm-dd" );
	
	//시설,강좌,행사 홍보 버튼
	$("#tabBtnContainer > li > a").each(function(o_index, o_element) {
        var objBtn = $(this);
        objBtn.click(function() {
            $("#tabBtnContainer > li > a").each(function(i_index, i_element) {
            	 var that = $(this);
                if(i_index == o_index) {
                    $("#"+ that.attr("layer")).show();
                    $(this).addClass("on");
                } else {
                    $("#"+ that.attr("layer")).hide();
                    $(this).removeClass("on");
                }
            });
        });
        
        if(objBtn.attr("layer") == "expand") {
            objBtn.trigger("click");
        }
        
        if(objBtn.attr("layer") == "expand"){
        	$("#goList").attr("href", "/boffice/ex/reservationFac/reservationFacList.do?cmm_code=RM020000");
        }else if(objBtn.attr("layer") == "lecture"){
        	$("#goList").attr("href", "/boffice/ex/lecture/Lecture_List.do?cmm_code=RM040000");
        }else if(objBtn.attr("layer") == "event"){
        	$("#goList").attr("href", "/boffice/ex/reservation/reservationEventList.do?cmm_code=RM030000");
        }
    });
});

/* 날짜 체크 return */
function doDateCheck(data){
	var str = "";
	if(data < 10){ 
		data = "0" + data;
	}
	str = data;
	return str;
}

/* 날짜 체크 */
function doDateSet(gbn){
	
	var today = new Date();
	var year = today.getFullYear();
	var month = today.getMonth() + 1;
	var day = today.getDate();
	var scRegDtSt = "";
	var scRegDtEd = "";
	
	if(gbn == '0'){ //오늘
		scRegDtSt = year + "-" + doDateCheck(month) + "-" + doDateCheck(day); 
		scRegDtEd = year + "-" + doDateCheck(month) + "-" + doDateCheck(day);
	}else if(gbn == '1'){ //1일
		scRegDtSt = year + "-" + doDateCheck(month) + "-" + doDateCheck((day - 1)); 
		scRegDtEd = year + "-" + doDateCheck(month) + "-" + doDateCheck(day);
	}else if(gbn == '2'){ //3일
		scRegDtSt = year + "-" + doDateCheck(month) + "-" + doDateCheck((day - 3)); 
		scRegDtEd = year + "-" + doDateCheck(month) + "-" + doDateCheck(day);
	}else if(gbn == '3'){ //1주일
		scRegDtSt = year + "-" + doDateCheck(month) + "-" + doDateCheck((day - 7)); 
		scRegDtEd = year + "-" + doDateCheck(month) + "-" + doDateCheck(day);
	}else if(gbn == '4'){ //한달
		scRegDtSt = year + "-" + doDateCheck((month - 1)) + "-" + doDateCheck(day); 
		scRegDtEd = year + "-" + doDateCheck(month) + "-" + doDateCheck(day);
	}else if(gbn == '5'){ //연초
		scRegDtSt = year + "-" + "01" + "-" + "01"; 
		scRegDtEd = year + "-" + doDateCheck(month) + "-" + doDateCheck(day);
	}else if(gbn == '6'){
		scRegDtSt = "2007" + "-" + "01" + "-" + "01"; 
		scRegDtEd = year + "-" + doDateCheck(month) + "-" + doDateCheck(day);
	}
	
	document.getElementById("scRegDtSt").value = scRegDtSt; 
	document.getElementById("scRegDtEd").value = scRegDtEd;
}

function doSearchListSurvey() {
	document.MgrMainVo.action = "<c:url value='/boffice/sy/mgr/MgrMain.do' />";
	document.MgrMainVo.submit();
}
</script>
</head>
<fmt:setLocale value="ko_kr"/>
<body>
	<form id="MgrMainVo" name="MgrMainVo" method="post">
	
	<div class="search">
		<label for="scRegDtSt" class="hidden">시작일</label><input id="scRegDtSt" type="text" name="scRegDtSt" value="<c:out value='${MgrMainVo.scRegDtEd }'/>"> ~
		<label for="scRegDtEd" class="hidden">종료일</label><input id="scRegDtEd" type="text" name="scRegDtEd" value="<c:out value='${MgrMainVo.scRegDtEd }'/>">
		<a href="javascript:doDateSet('0');" class="btn_inline">오늘</a>
		<a href="javascript:doDateSet('1');" class="btn_inline">1일</a>
		<a href="javascript:doDateSet('2');" class="btn_inline">3일</a>
		<a href="javascript:doDateSet('3');" class="btn_inline">1주일</a>
		<a href="javascript:doDateSet('4');" class="btn_inline">한달</a>
		<a href="javascript:doDateSet('5');" class="btn_inline">연초</a>
		<a href="javascript:doSearchListSurvey();" class="btn_inline btn_search">조회</a>
	</div>
	<br />
	
	<table class="list">
	<caption></caption>
	<colgroup>
		<col style="width:33%;">
		<col style="width:33%;">
		<col style="width:33%;">
	</colgroup>
	<thead>
		<tr>
			<th>민원건수</th>
			<th>신규게시물</th>
			<th>통합예약접수</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td><b><c:out value="${minwonCount}" /></b></td>
			<td><b><c:out value="${boardCount}" /></b></td>
			<td><b><c:out value="${commCount}" /></b></td>				
		</tr>
	</tbody>
	</table>
	
	<c:if test="${MgrMainConfigVo.noticeYn eq 'Y' }">
	<h4>관리자 공지게시판</h4>
	<table class="list">
	<caption></caption>
	<colgroup>
		<col style="width:10%;">
		<col>
		<col style="width:15%;">
		<col style="width:15%;">
		<col style="width:15%;">
	</colgroup>
	<thead>
		<tr>
			<th>NO</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>5</td>
			<td><a href="/boffice/ex/board/BbsContentView.do?cmm_code=BB020000&searchGroup=CB034000&searchCbIdx=280&cbIdx=280&bcIdx=916" >개인이 차를 사고,팔때 필요한 서류 안내입니다.</a></td>
			<td></td>
			<td>
				<c:out value='${MgrMainVo.scRegDtEd }'/>
			</td>
			<td>15</td>
		</tr>
		<tr>
			<td>4</td>
			<td><a href="/boffice/ex/board/BbsContentView.do?cmm_code=BB020000&searchGroup=CB034000&searchCbIdx=280&cbIdx=280&bcIdx=915" >법인이 차를 사고, 팔때 필요한 서류안내 입니다.</a></td>
			<td></td>
			<td>
				<c:out value='${MgrMainVo.scRegDtEd }'/>
			</td>
			<td>8</td>
		</tr>
		<tr>
			<td>3</td>
			<td><a href="/boffice/ex/board/BbsContentView.do?cmm_code=BB020000&searchGroup=CB034000&searchCbIdx=280&cbIdx=280&bcIdx=914" >자동차 신규등록(자가용, 영업용 등) 안내 입니다.</a></td>
			<td></td>
			<td>
				<c:out value='${MgrMainVo.scRegDtEd }'/>
			</td>
			<td>11</td>
		</tr>
		<tr>
			<td>2</td>
			<td><a href="/boffice/ex/board/BbsContentView.do?cmm_code=BB020000&searchGroup=CB034000&searchCbIdx=280&cbIdx=280&bcIdx=913" >소유권이전등록 안내 입니다.</a></td>
			<td></td>
			<td>
				<c:out value='${MgrMainVo.scRegDtEd }'/>
			</td>
			<td>10</td>
		</tr>
		<tr>
			<td>1</td>
			<td><a href="/boffice/ex/board/BbsContentView.do?cmm_code=BB020000&searchGroup=CB034000&searchCbIdx=280&cbIdx=280&bcIdx=912" >자동차 부활등록 안내 입니다.</a></td>
			<td></td>
			<td>
				<c:out value='${MgrMainVo.scRegDtEd }'/>
			</td>
			<td>7</td>
		</tr>
	</tbody>
	</table>
	
	<%-- <table class="list">
	<caption></caption>
	<colgroup>
		<col style="width:10%;">
		<col>
		<col style="width:15%;">
		<col style="width:15%;">
		<col style="width:15%;">
	</colgroup>
	<thead>
		<tr>
			<th>NO</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
	</thead>
	<tbody>
		<c:set var="n" value="${fn:length(noticeList)}" />
		<c:forEach items="${noticeList}" var="noticeInfo" varStatus="status">
			<tr>
				<td><c:out value="${n - status.index}" /></td>
				<td><a href="/boffice/ex/board/BbsContentView.do?cmm_code=BB020000&searchGroup=CB034000&searchCbIdx=<c:out value="${noticeInfo.cbIdx}"/>&cbIdx=<c:out value="${noticeInfo.cbIdx}"/>&bcIdx=<c:out value="${noticeInfo.bcIdx}"/>" ><c:out value="${noticeInfo.subCont}" /></a></td>
				<td><c:out value="${noticeInfo.nameCont}" /></td>
				<td>
					<fmt:parseDate var="regDt" value="${noticeInfo.regDt}" pattern="yyyyMMddHHmmss"/>
					<fmt:formatDate var="regDt" value="${regDt}" pattern="yyyy.MM.dd"/>
					<c:out value="${regDt}" />
				</td>
				<td><c:out value="${noticeInfo.countCont}" /></td>
			</tr>
		</c:forEach>
		<c:if test="${fn:length(noticeList) == 0}">
			<tr>
				<td colspan="5"><spring:message code="common.nodata.msg" /></td>
			</tr>
		</c:if>
	</tbody>
	</table> --%>
	</c:if>
	</form>
</body>
</html>
