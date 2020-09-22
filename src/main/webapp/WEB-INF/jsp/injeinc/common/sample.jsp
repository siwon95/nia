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
<title>Basic Board List</title>
<script type="text/javascript">
//<![CDATA[

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


function doUserPop(){
	
	var url = "<c:url value='/boffice/sy/user/User_CheckPop.do' />";
	var width="700";
	var height="500";
	var from = document.form;
	doCommonPop(from,"test",url,width,height);
	//common.js (doCommonPop : 공통팝업오픈(폼,"팝업명",url,width,heigth)넣음됨 )
}

function setCuIdValue(cuId) {
	$("#cuId").val(cuId);
	$("#cuId").focus();
}

function dateCheck() {
	//alert($('#scRegDtSt').val());
	if(!cm_check_dateformat($('#scRegDtSt').val())) {
		alert("날짜 형식이 잘못되었습니다.");
		$('input[name=scRegDtSt]').focus();
		return;
	}
	
	if(!cm_check_dateformat($('#scRegDtEd').val())) {
		alert("날짜 형식이 잘못되었습니다.");
		$('input[name=scRegDtEd]').focus();
		return;
	}	
}

function doFileUpload(){
	var from = document.form;
	//from.action = "<c:url value='/boffice/sy/file/File_List.do' />";
	from.action = "<c:url value='/boffice/common/files/CmmFilesList.do' />";
	from.submit();
}

function doCheckDept(){
	alert($("#test_combo").val());
}



function doSaveExcel(){
	

var thisForm = document.getElementById('form');
thisForm.action = "<c:url value='/boffice/sy/mgr/Mgr_Excel_Down.do' />";
thisForm.target = "_self";
thisForm.submit();
	
}

function mobileAuth() {
	
	var PAgree = open("/common/mobileAuth/mobile_auth_mgr.jsp", "CheckJN", "width=420, height=570, resizable=0, scrollbars=no, status=0, titlebar=0, toolbar=0, left=480, top=115");
	PAgree.focus();

}

function writeJN(name, di) {
	$("#mobileName").val(name);
	$("#mobileDi").val(di);
}

function userIdAuth(){
	
	var UserIdAuthPop = open("<c:url value='/user/UserSearchPop.do' />", "UserIdAuth", "width=300, height=130, resizable=0, scrollbars=no, status=0, titlebar=0, toolbar=0, left=480, top=115");
	UserIdAuthPop.focus();
}

function writeUserInfo(id, name, email, zip, addr1, addr2, tel, hp, di) {
	$("#mobileId").val(id);
	$("#mobileName").val(name);
	$("#mobileEmail").val(email);
	$("#mobileZip").val(zip);
	$("#mobileAddr1").val(addr1);
	$("#mobileAddr2").val(addr2);
	var telArr = tel.split("-");
	$("#mobileTel1").val(telArr[0]);
	$("#mobileTel2").val(telArr[1]);
	$("#mobileTel3").val(telArr[2]);
	var hpArr = hp.split("-");
	$("#mobileHp1").val(hpArr[0]);
	$("#mobileHp2").val(hpArr[1]);
	$("#mobileHp3").val(hpArr[2]);
	$("#mobileDi").val(di);
}
//]]>
</script>



<SCRIPT type="text/javascript">
/**
 * 페이지의 모든 요소(이미지)가 로딩시 구동
 */
$(window).load(function() {
	
// 	doJsTreeOpen();
	cm_combo_dept("#test_combo");
	
	cm_combo_cmm("#test_combo2","04000000"); //공통코드콤보(select:id ,공통부모코드);
	
	cm_combo("#test_combo3","AD000000"); //관리자,bbs 전용 콤보 (select:id ,공통부모코드);
    $("input[id=scRegDtSt], input[id=scRegDtSt]").datepicker();
	$("input[id=scRegDtSt], input[id=scRegDtSt]").mask("9999-99-99");
    $("input[id=scRegDtSt], input[id=scRegDtSt]").datepicker( "option", "dateFormat", "yy-mm-dd" );
    $("input[id=scRegDtEd], input[id=scRegDtEd]").datepicker();
	$("input[id=scRegDtEd], input[id=scRegDtEd]").mask("9999-99-99");
    $("input[id=scRegDtEd], input[id=scRegDtEd]").datepicker( "option", "dateFormat", "yy-mm-dd" );
});


</SCRIPT>


</head>
<body style="text-align:center; margin:0 auto; display:inline; padding-top:100px;" >
<!-- 주소팝업사용시 무조건 만드셔야됨. -->
<form id="form" name="form" method="post" style="visibility: hidden;">
		<input type="text" id="confmKey" name="confmKey" value="bnVsbDIwMTQxMDIzMTc0NTQ2"/>
		<input type="hidden" id="returnUrl" name="returnUrl" value=""/>
</form>
<form id="excelform">

<!-- 주소팝업사용시 무조건 만드셔야됨. -->
<div class="title">
				<h2>관리자메인 &gt;</h2>
				<h3>메인</h3>
			</div>
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
						<th>관리자아이디세션</th>
						<td><input type="text" value="<c:out value='${SesUserId}'/>" readOnly="true"/></td>
					</tr>
					<tr>
						<th>관리자이름세션</th>
						<td><input type="text" value="<c:out value='${SesUserName}'/>" readOnly="true"/></td>
					</tr>
					<tr>
						<th>관리자권한코드</th>
						<td><input type="text" value="<c:out value='${SesUserPermCd}'/>" readOnly="true"/></td>
					</tr>
					<tr>
						<th>관리자권한명</th>
						<td><input type="text" value="<c:out value='${SesUserPermNm}'/>" readOnly="true"/></td>
					</tr>
					<tr>
						<th>관리자부서코드세션</th>
						<td><input type="text" value="<c:out value='${SesUserDeptCd}'/>" readOnly="true"/></td>
					</tr>
					<tr>
						<th>관리자부서명</th>
						<td><input type="text" value="<c:out value='${SesUserDeptNm}'/>" readOnly="true"/></td>
					</tr>
					<tr>
						<th>주소팝업기능</th>
						<td colspan="3">
							<input type="text" id="zip" size="4" title="(주소)우편번호" readonly="true"/> &nbsp;&nbsp;<a href="javascript:openAddrSearch();" class="btn2">주소검색</a>
							</br>
							<input type="text" id="addr1" size="60" title="(주소)기본주소" readonly="true"/>
							</br>
							</br>
							상세주소 : <input type="text" id="addr2" size="45" maxlength="22" title="(주소)상세주소"/> *번지는 안적으셔도 됩니다.
						</td>				
					</tr>
					<tr>
					<th>아이디검색</th>
					<td><input type="text" id="cuId"/>
					<a href="javascript:doUserPop();" class="btn1">공통아이디검색</a></td>
					</tr>
					<tr>
						<th>공통부서콤보</th>
						<td>
						<select id="test_combo" name="test_combo">
								<option value="">선택하세요</option>
						</select>
						<select id="test_combo2" name="test_combo2">
								<option value="">선택하세요</option>
						</select>
						<select id="test_combo3" name="test_combo3">
								<option value="">선택하세요</option>
						</select>
						</td>
					</tr>
					<tr>
						<th>날짜입력</th>
						<td>
							<span class="cal_in" ><input id="scRegDtSt" name="scRegDtSt" size="7" title="시작일"/></span>
							~
							<span class="cal_in" ><input id="scRegDtEd" name="scRegDtEd" size="7" title="종료일"/></span>
							<a href="javascript:dateCheck();" class="btn1">날짜형식검증</a></td>
						</td>
					</tr>
					<tr>
						<th>파일 업로드</th>
						<td>
							<a href="javascript:doFileUpload();">파일업로드 바로가기</a>
						</td>
					</tr>
					<tr>
						<th>관리자 대리 접수용 회원인증</th>
						<td colspan="3">
							성명 : <input type="text" id="mobileName" name="mobileName" readonly="true" value="" /> &nbsp;&nbsp;<a href="javascript:mobileAuth();" class="btn2">관리자 모바일 인증</a> <a href="javascript:userIdAuth();" class="btn2">아이디인증</a>
							</br>
							모바일 인증은 실서버에서 테스트가능합니다.<br/>아이디 인증은 localhost에선 아이디==>shinil//비밀번호==>1 회원만 테스트 가능
							</br>
							본인확인코드(DI):<input type="text" id="mobileDi" name="mobileDi" size="64" readonly="true" value=""/><br/>
							ID:<input type="text" id="mobileId" name="mobileId" size="20" readonly="true" value=""/><br/>
							email:<input type="text" id="mobileEmail" name="mobileEmail" size="30" value=""/><br/>
							Zip:<input type="text" id="mobileZip" name="mobileZip" size="8" readonly="true" value=""/><br/>
							Addr1:<input type="text" id="mobileAddr1" name="mobileAddr1" size="50" readonly="true" value=""/><br/>
							Addr2:<input type="text" id="mobileAddr2" name="mobileAddr2" size="50" value=""/><br/>
							Tel:<input type="text" id="mobileTel1" name="mobileTel1" size="4" value=""/>-<input type="text" id="mobileTel2" name="mobileTel2" size="4" value=""/>-<input type="text" id="mobileTel3" name="mobileTel3" size="4" value=""/><br/>
							Hp:<input type="text" id="mobileHp1" name="mobileHp1" size="4" value=""/>-<input type="text" id="mobileHp2" name="mobileHp2" size="4" value=""/>-<input type="text" id="mobileHp3" name="mobileHp3" size="4" value=""/><br/>
						</td>
					</tr>
					<tr>
						<th rowspan="2">파일바로보기</th>
						<td>
							태안시설 설계서.pptx <a href="http://www.seocho.go.kr/common/docuzen/preImageFromDoc.do?filePath=/upload/test/RI_20150721123456789&filename=RI_20150721123456789.pptx" class="btn3" target="_blank">바로보기</a>
						</td>
					</tr>
					<tr>
						<td>
							&lt;a href="/common/docuzen/preImageFromDoc.do?filePath=<span style="color:red">/upload/test/RI_20150721123456789</span>&filename=<span style="color:red">RI_20150721123456789.pptx</span>" class="btn3" target="_blank"&gt;바로보기&lt;/a&gt;
							<br/>filePath 는 WEB서버에 존재하는 파일의 파일명 포함 주소
							<br/>filename 은 영문이어야 하고 확장자가 꼭 있어야 합니다. 실제 파일명과 달라도 상관없음
							<br/>파일변환서버에서 URL을 체크하기 때문에 *.seocho.go.kr 에서만 가능합니다.
						</td>
					</tr>
					<tr>
						<th>WAS에서 WEB으로 파일 옮기는 UTIL</th>
						<td>
							egovframework.injeinc.common.util.FTPClientOroinc.java 
							<br/><br/>
							사용예시 : <br/>
							<br/>FTPClientOroinc ftpClient = new FTPClientOroinc();
							<br/>ftpClient.put("<span style="color:red">/web/schome/upload/test/</span>", "<span style="color:red">RI_20150721123456789</span>", "<span style="color:blue">/was/schome/upload/test/</span>", "<span style="color:blue">RI_20150721123456789</span>"); //실서버
							<br/>ftpClient.put("<span style="color:red">/web/schome/upload/test/</span>", "<span style="color:red">test.jpg</span>", "<span style="color:blue">C:/eGovFrameDev-2.7.1-64bit/workspace/SCGHOME/src/main/webapp/upload/test/</span>", "<span style="color:blue">test.jpg</span>"); //웹서버FTP접속이 가능한 로컬에서 테스트 가능 
							<br/>(확인URL:http://www.seocho.go.kr/upload/test/test.jpg)
							<br/><br/>
							삭제 예시 : <br/>
							<br/>FTPClientOroinc ftpClient = new FTPClientOroinc();
							<br/>ftpClient.delfile("<span style="color:red">/web/schome/upload/test/</span>", "<span style="color:red">test.jpg</span>");
							
						</td>
					</tr>
					</tbody>
				</table>
					<!-- 버튼 -->
				<div class="btn_zone">
				<a href="javascript:doCheckDept()" class="btn2">부서테스트</a>
				<a href="javascript:doSaveExcel();" class="btn2">엑셀</a>
				<a href="#" class="btn1">버튼종류</a> <!-- 트랜젝션이 탈때 처리하는버튼 예 : 저장 -->
				<a href="#" class="btn2">버튼종류</a> <!-- 예 : 목록,수정,삭제,등록 -->
				<a href="#" class="btn3">버튼종류</a>
<!-- 					<a href="javascript:openAddrSearch();" class="btn1">공통아이디검색</a> -->
				</div>
				<!-- //버튼 -->
</form>
</body>
</html>

