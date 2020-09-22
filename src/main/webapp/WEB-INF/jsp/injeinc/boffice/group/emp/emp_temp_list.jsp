<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Basic Board List</title>
<script type="text/javascript">
//<![CDATA[
//리스트 조회
function doEmpTempList(){
	document.EmpTempVo.action = "<c:url value='/boffice/emp/Emp_Temp_List.do' />";
	document.EmpTempVo.submit();
}

//삭제
function doEmpTempRmv(idx){
	if(confirm("정말 삭제 하시겠습니까?")){
		document.EmpTempVo.action = "/boffice/emp/Emp_Temp_Rmv.do?userIdx="+idx;
		document.EmpTempVo.submit();
	}
}

//복원
function doEmpTempRes(idx){
	if(confirm("정말 복원 하시겠습니까?")){
		document.EmpTempVo.action = "/boffice/emp/Emp_Temp_Res.do?userIdx="+idx;
		document.EmpTempVo.submit();
	}
}

//페이징처리 
function doEmpPag(pageIndex) {
	document.EmpTempVo.pageIndex.value = pageIndex;
	document.EmpTempVo.pageIndex.value = pageIndex;
	document.EmpTempVo.pageIndex.value = pageIndex;
	document.EmpTempVo.action = "<c:url value='/boffice/emp/Emp_Temp_List.do' />";
	document.EmpTempVo.submit();
}

//등록
function doEmpTempReg(idx){
	/* document.EmpTempVo.dvDepth1.value= dvDepth1; */
	document.EmpTempVo.action = "/boffice/emp/Emp_Temp_Reg.do?userIdx="+idx;
	document.EmpTempVo.submit();
}

//부서관리
function doOpenDept(){
	window.open("/boffice_noDeco/group/dept/GroupDept_List.do?layout=n","_target","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable,width=1200"); 
}

//일괄 등록
function doSavAll(type){
	var f = document.listForm;
	var idxValue = "";
	var flag = false;
	var chkLength = $("input:checkbox[id=checkId]:checked").length;
	var cnt = 0;
	for(i=1;i<=10; i++){
		if(eval("document.listForm.chknum_"+i) != undefined){
			if(eval("document.listForm.chknum_"+i).checked == true){
				idxValue += eval("document.listForm.chknum_"+i).value;
				cnt = cnt + 1;
				if(cnt<chkLength){
					idxValue += ",";
				}
				flag = true;
			}
		}
	}
	
	if(flag){
		if(type == 'regist'){
			f.action="/boffice/emp/Emp_Temp_Regist_All.do";
			f.idxValues.value=idxValue;
			f.submit();
		}else if(type == 'remove'){
			f.action="/boffice/emp/Emp_Temp_Remove_All.do";
			f.idxValues.value=idxValue;
			f.submit();
		}else if(type == 'restore'){
			f.action="/boffice/emp/Emp_Temp_Restore_All.do";
			f.idxValues.value=idxValue;
			f.submit();
		}else if(type == 'mark'){
			f.action="/boffice/emp/Emp_Temp_Mark_All.do";
			f.idxValues.value=idxValue;
			f.submit();
		}
	}
}

//전체 체크 
function chkAll(){
	if($("#chkAllId").prop("checked")) {
		$("input[type=checkbox]").prop("checked",true);
	} else {
		$("input[type=checkbox]").prop("checked",false);
	}
}

/* //직원정보 수정 팝업
function doEmpTempPop(idx,page,insertyn,listcnt,condition,keyword,year,month,day){
	window.open('/boffice/emp/EmpTemp_Pop.do?useIdx='+idx+'&pageIndex='+page+'&insertYn='+insetyn+'&listcnt='+listcnt+'&searchCondition='+condition+'&searchKeyword='+keyword+'&year='+year+'&month='+month+'&day='+day,'직원정보 수정');
} */

function doEmpTempPop(idx){
	window.open('/boffice/emp/EmpTemp_Pop.do?userIdx='+idx,'','width=600,height=350');
}

//]]>
</script>
</head>
<body style="text-align:center; margin:0 auto; display:inline; padding-top:100px;">
	<!-- 컨텐츠 영역 -->
	<div id="contents">
		<form:form commandName="EmpTempVo" name="EmpTempVo" method="post" >
		<form:hidden path="pageIndex" />
		<form:hidden path="dvDepth1" />
			<!-- 검색 -->
			<fieldset class="date_search">
				<div class="left">
					<select name="insertYn" onchange="doEmpTempList();">
						<option value="N" <c:if test="${EmpTempVo.insertYn eq 'N' }">selected="selected"</c:if>>미등록</option>
						<option value="Y" <c:if test="${EmpTempVo.insertYn eq 'Y' }">selected="selected"</c:if>>등록</option>
						<option value="" <c:if test="${EmpTempVo.insertYn eq 'all' }">selected="selected"</c:if>>전체</option>
					</select>
					<select name="listcnt" onchange="doEmpTempList();">
						<option value="10" <c:if test="${EmpTempVo.listcnt eq '10' }">selected="selected"</c:if>>10</option>
						<option value="20" <c:if test="${EmpTempVo.listcnt eq '20' }">selected="selected"</c:if>>20</option>
						<option value="30" <c:if test="${EmpTempVo.listcnt eq '30' }">selected="selected"</c:if>>30</option>
						<option value="50" <c:if test="${EmpTempVo.listcnt eq '50' }">selected="selected"</c:if>>50</option>
						<option value="100" <c:if test="${EmpTempVo.listcnt eq '100' }">selected="selected"</c:if>>100</option>
					</select>줄씩 보기
				</div>
				<div class="center">
				    <select name="searchCondition">
						<option value="name" <c:if test="${EmpTempVo.searchCondition eq 'name' }">selected="selected"</c:if>>이름</option>
						<option value="deptName" <c:if test="${EmpTempVo.searchCondition eq 'deptName' }">selected="selected"</c:if>>과</option>
					</select>
					<span class="sfont">
						<form:input path="searchKeyword" size="30"/>
						<a href="javascript:doEmpTempList();" class="btn2">검색</a>
					</span>
				</div>
				<div class="right">
					<c:set var="yearArray" value="2010,2011,2012,2013,2014,2015,2016" />
					<select name="year" onchange="doEmpTempList();">
						<c:forEach items="${yearArray }" var="yearList" varStatus="status">
							<option value="<c:out value="${yearList}"/>" <c:if test="${EmpTempVo.year eq yearList}">selected="selected"</c:if>><c:out value="${yearList}"/></option>
						</c:forEach>
					</select>년
					<c:set var="monthArray" value="01,02,03,04,05,06,07,08,09,10,11,12" />
					<select name="month" onchange="doEmpTempList();">
						<option value="">전체</option>
						<c:forEach items="${monthArray }" var="monthList" varStatus="status">
							<option value="<c:out value="${monthList}"/>" <c:if test="${EmpTempVo.month eq monthList}">selected="selected"</c:if>><c:out value="${monthList}"/></option>
						</c:forEach>
					</select>월
					<c:set var="dayArray" value="01,02,03,04,05,06,07,08,09,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31" />
					<select name="day" onchange="doEmpTempList();">
						<option value="">전체</option>
						<c:forEach items="${dayArray }" var="dayList" varStatus="status">
							<option value="<c:out value="${dayList}"/>" <c:if test="${EmpTempVo.day eq dayList}">selected="selected"</c:if>><c:out value="${dayList}"/></option>
						</c:forEach>
					</select>일
				</div>
			</fieldset>
			<!-- //검색 -->
		</form:form>
		
		<form name="listForm" method="post">	
		
		<input type="hidden" name="user_idx" value="" />
		<input type="hidden" name="idxValues" value="" />
		<input type="hidden" name="ce_depstep1" value="" />
		<input type="hidden" name="ce_cdidx" value="" />
		<table summary="" class="write">
			<caption></caption>
			<colgroup>
				<col width="2%" />
				<col width="5%" />
				<col width="10%" />
				<col width="5%" />
				<col width="5%" />
				<col width="10%" />
				<col width="10%" />
				<col width="10%" />
				<col width="7%" />
				<col width="3%" />
				<col width="3%" />
				<col width="4%" />
				<col width="4%" />
				<col width="7%" />
				<col width="4%" />
				<col/>
 			</colgroup>
 			<thead>
			<tr>
					<th style="text-align:center;"><input type="checkbox" id="chkAllId" onclick="chkAll()" /></th>
					<th style="text-align:center;">No</th>
					<th style="text-align:center;">직원ID</th>
					<th style="text-align:center;">이름</th>
					<th style="text-align:center;">직위</th>
					<th style="text-align:center;">팀이름</th>
					<th style="text-align:center;">과이름</th>
					<th style="text-align:center;">국이름</th>
					<th style="text-align:center;">연결코드</th>
					<th style="text-align:center;">순번</th>
					<th style="text-align:center;">사용</th>
					<th style="text-align:center;">표기</th>
					<th style="text-align:center;">상태<br/>코드</th>
					<th style="text-align:center;">상태명</th>
					<th style="text-align:center;">최종<br/>입력</th>
					<th style="text-align:center;">입력/삭제</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${resultList }" var="result" varStatus="status">
					<tr>
						<td><input type="checkbox" id="checkId" name="chknum_<c:out value="${status.count}"/>" value="<c:out value="${result.userIdx}"/>"/></td>
						<td><c:out value="${result.userIdx }"/></td>
						<td><c:out value="${result.userId }"/></td>
						<td>
							<a href="javascript:doEmpTempPop('<c:out value="${result.userIdx }"/>');">
								<c:out value="${result.userName }"/>
							</a>
						</td>
						<td><c:out value="${result.rankName }"/></td>
						<td>	<c:out value="${result.sectionName }"/>	</td>
						<td align="center">
							<c:choose>
								<c:when test="${result.deptName eq '오-케이민원센터' }">
									OK민원센터
								</c:when>
								<c:otherwise>
									<c:out value="${result.deptName }"/>
								</c:otherwise>
							</c:choose>
						</td>
						<td align="center">
							<c:if test="${result.dvDepth1 eq '' }">
								조회안됨
							</c:if>
							<c:if test="${result.dvDepth1 ne '' }">
								<c:out value="${result.dvDepth1 }" />
							</c:if>
						</td>
						<td align="center">
							<c:if test="${result.dvIdxdepth3 eq '' }">
								조회안됨
							</c:if>
							<c:if test="${result.dvIdxdepth3 ne '' }">
								<c:out value="${result.dvIdxdepth3 }" />
							</c:if>
						</td>
						<td><c:out value="${result.odr }"/></td>
						<td><c:out value="${result.useYn }"/></td>
						<td><c:out value="${result.markYn }"/></td>
						<td><c:out value="${result.userStatus }"/></td>
						<td><c:out value="${result.userStatusName }"/></td>
						<td><c:out value="${result.insertYn }"/></td>
						<td>
							<input type="button" value="등록" onclick="doEmpTempReg('<c:out value="${result.userIdx }"/>');"/>
							<c:if test="${result.insertYn eq 'Y' }">
								<input type="button" value="복원" onclick="doEmpTempRes('<c:out value="${result.userIdx }"/>');"/>
							</c:if>
							<c:if test="${result.insertYn eq 'N' }">
								<input type="button" value="삭제" onclick="doEmpTempRmv('<c:out value="${result.userIdx }"/>');"/>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</form>
		<!-- //조직도연동 리스트-->
		<!--paginate -->
		<div class="paginate">
				<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="doEmpPag" />
		</div>
		<!--//paginate -->
		<div class="btn_zone">
			<div class="left">
				<a href="javascript:doSavAll('regist');" class="btn1">일괄등록</a>
				<a href="javascript:doSavAll('remove');" class="btn1">일괄삭제</a>
				<a href="javascript:doSavAll('restore');" class="btn1">일괄복원</a>
				<!-- <a href="javascript:doSavAll('mark');" class="btn1">일괄mark변경</a> -->
			</div>
			<div class="right">
				<a href="javascript:doOpenDept();" class="btn1">부서관리</a>
			</div>
		</div>
	</div>
	<!-- //컨텐츠 영역 -->
</body>
</html>
