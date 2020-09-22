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

// 이전화면
function doHistoryBack(){
	history.back(-1);
}

// selectBox 중복체크
function doSelectBoxValCheck(value){
	//alert(value);
	$("#selectBoxRowValue").val(value);
}


//템플릿 파일 저장
function doTempletSave(){
	
	var formData = new FormData();
    formData.append("cbIdx", document.getElementById("cbIdx").value);
    formData.append("bbsTempletGbn", document.getElementById("bbsTempletGbn").value);
    formData.append("uploadFile",$("#uploadFile")[0].files[0]);
    formData.append("templetName",document.getElementById('templetName').value);
	
	$.ajax({
		url : "<c:url value='/boffice/sy/board/Board_TempletRegAx.do'/>"
		, data : formData
		, type : "POST"
		, processData: false
     	, contentType: false
		, success : function(data) {
			var cbIdx = document.getElementById("cbIdx").value;
			var bbsTempletGbn = document.getElementById("bbsTempletGbn").value;
			doBbsTempletSelect(cbIdx, bbsTempletGbn);
			alert("저장완료");
 		},error : function(code, msg, error) {
 				var str = code.status + " : " + msg + " : " + error;
 				console.log(str);
 		}
			
	});
	
}

// 템플릿파일 조회
function doBbsTempletSelect(cbIdx, bbsTempletGbn){
	var poaram = "";
    param = "cbIdx=" + cbIdx + "&" + "bbsTempletGbn=" + bbsTempletGbn;

	$.ajax({
		url : "<c:url value='/boffice/sy/board/BbsTempletFile_ListAx.do'/>"
		, data : param
		, type : "POST"
		, dataType : "json"
		, async : false
		, success : function(data) {
			var btIdx = '<c:out value="${boardVO.btIdx}"/>';
			var output = "";
				output += "<option>::: 선택 :::</option>";
			$(data.BbsTempletList).each(function(index, element){
				output += "<option value=" + element.btIdx + ">" + element.fileName + "</option>";
			});
			
	        $('#bbsTemplet').html(output);
	        document.getElementById("templetName").value = "";
			document.getElementById("uploadFile").value = "";
			$('#bbsTemplet').val(btIdx);
			},error : function(code, msg, error) {
					var str = code.status + " : " + msg + " : " + error;
					console.log(str);
			}
			
	});
}

// 저장
function doMgrForm(){
	
	var t_len = $('#dynatable tr[id=tr_added]').length;
	
	for (var i=0; i < t_len; i++) {
		var cud			 = $("#dynatable tr[id=tr_added]:eq("+ i +")").find("input[name=cud]");
		var row_no		 = $("#dynatable tr[id=tr_added]:eq("+ i +")").find("input[name=row_no]:text");
		var labelCode	 = $("#dynatable tr[id=tr_added]:eq("+ i +")").find("select[name=labelCode]");
		var webUseYn = $("#dynatable tr[id=tr_added]:eq("+ i +")").find("input[name=webUseYn]:checkbox");
		var webUseYnValue = $("#dynatable tr[id=tr_added]:eq("+ i +")").find("input[name=webUseYnValue]:text");
		var webUseYnChecked = webUseYn.is(":checked");
		var mobileUseYn = $("#dynatable tr[id=tr_added]:eq("+ i +")").find("input[name=mobileUseYn]:checkbox");
		var mobileUseYnValue = $("#dynatable tr[id=tr_added]:eq("+ i +")").find("input[name=mobileUseYnValue]:text");
		var mobileUseYnChecked = mobileUseYn.is(":checked");
		var searchLabelUseYn = $("#dynatable tr[id=tr_added]:eq("+ i +")").find("input[name=searchLabelUseYn]:checkbox");
		var searchLabelUseYnValue = $("#dynatable tr[id=tr_added]:eq("+ i +")").find("input[name=searchLabelUseYnValue]:text");
		var searchLabelUseYnChecked = searchLabelUseYn.is(":checked");
		
		if(webUseYnChecked){
			webUseYnValue.val("Y");
		}else{
			webUseYnValue.val("N");
		}
		
		if(mobileUseYnChecked){
			mobileUseYnValue.val("Y");
		}else{
			mobileUseYnValue.val("N");
		}
		
		if(searchLabelUseYnChecked){
			searchLabelUseYnValue.val("Y");
		}else{
			searchLabelUseYnValue.val("N");
		}
		
	}//for
	var clobYn = $("input[name=clobYn]:checkbox");
	var clobYnValue = $("input[name=clobYnValue]:text");
	var clobYnChecked = clobYn.is(":checked");
	if(clobYnChecked){
		clobYnValue.val("Y");
	}else{
		clobYnValue.val("N");
	}

	document.BoardVo.action = "/boffice/sy/board/BoardConfigProperty_Reg.do";
	document.BoardVo.submit();
}

// 라벨코드 리스트
function doLabelCodeList(id){
	var param = "cbIdx=" + id;
	$.ajax({
		//url : "<c:url value='/boffice/sy/board/LabelCode_ListAx.do'/>"
		url : "<c:url value='/boffice/sy/board/LabelCode_ListAx.do'/>"
		, data : param
		, type : "POST"
		, dataType : "json"
		, async : false
		, success : function(data) {
			var output = "";
				output += "<option>::: 선택하세요 :::</option>";
			$(data.LabelCodeList).each(function(index, element){
				output += "<option value=" + element.labelCode + ">" + element.labelName + "</option>";
				$("#selectLength").val(index + 1);
			});
			
            $('#labelCode').html(output);
            //$('#labelCodeSelectBox').html(output);
 		},error : function(code, msg, error) {
 				var str = code.status + " : " + msg + " : " + error;
 				console.log(str);
 		}
			
	});
}

// 게시판 디자인설정 조회
function selectView(cbIdx){
	
	var param = "";
	param = "cbIdx=" + cbIdx;
	
	//tr 초기화
	$("table[id=dynatable]").find("tr[id=tr_added]").remove();
	
	$.ajax({
		url : "<c:url value='/boffice/sy/board/Board_ViewListAx.do'/>"
		, data : param
		, type : "POST"
		, dataType : "json"
		, async : false
		, success : function(data) {
			
			var rows = data.rowDataList;
			
			for (var i=0; i < rows.length; i++) {
				var row = data.rowDataList[i];
				
				var labelCode	 = row.labelCode;
				var labelPropGbn = row.labelPropGbn;
				var labelCompYn  = row.labelCompYn;
				var labelOrdNo   = row.labelOrdNo;
				var labelProvSize = row.labelProvSize;
				var webUseYn      = row.webUseYn;
				var mobileUseYn   = row.mobileUseYn;
				var searchLabelUseYn = row.searchLabelUseYn;
				var searchClobYn = row.searchClobYn;
					
				var output = "";
				output += "<option>::: 선택하세요 :::</option>";
				$(data.rowDataList).each(function(index, element){
					output += "<option value=" + element.labelCode + ">" + element.labelName + "</option>";
					$("#selectLength").val(index + 1);
				});
				
	            $('#labelCode').html(output);
					
				var master = $("#dynatable");
				var prot = master.find("tr[id=prototype]").clone();
				var rowNo = $("#dynatable tr:last").find("input[name=row_no]:text").attr("value");
				rowNo++;
				
				prot.attr("id", "tr_added");
				prot.attr("style", ""); //프로토타입 css속성을 제거하여 hidden 속성을 제거한다.
				
				//prot.find("input[name=xxx_check]:checkbox").attr("value", rowIdx);
				prot.find("input[name=row_no]").attr("value", labelOrdNo);
				prot.find("input[name=cud]").attr("value", "C");
				prot.find("select[name=labelCode]").val(labelCode);
				prot.find("input[name=labelProvSize]").val(labelProvSize);
				
				if(webUseYn == "Y"){
					prot.find("input[name=webUseYn]:checkbox").attr("checked", true);
				}else{
					prot.find("input[name=webUseYn]:checkbox").attr("checked", false);
				}
				
				if(mobileUseYn == "Y"){
					prot.find("input[name=mobileUseYn]:checkbox").attr("checked", true);
				}else{
					prot.find("input[name=mobileUseYn]:checkbox").attr("checked", false);
				}
				
				if(searchLabelUseYn == "Y"){
					prot.find("input[name=searchLabelUseYn]:checkbox").attr("checked", true);
				}else{
					prot.find("input[name=searchLabelUseYn]:checkbox").attr("checked", false);
				}
				
				master.find("tbody").append(prot);	
			}
			if(searchClobYn == "Y"){
				alert(searchClobYn)
				//prot.find("input[name=ClobYn]:checkbox").attr("checked", true);
				$("input[name=ClobYn]:checkbox").attr("checked", true);
			}else{
				//prot.find("input[name=ClobYn]:checkbox").attr("checked", false);
				$("input[name=ClobYn]:checkbox").attr("checked", false);
			}
 		},error : function(code, msg, error) {
 				var str = code.status + " : " + msg + " : " + error;
 				console.log(str);
 		}
			
	});
}

</script>
<script type="text/javascript">
/**
 * 페이지의 요소가 준비되면 함수구동
 */
$(document).ready(function() {
	//동적테이블 row 추가
	$("#dynatable").on("click", "input[name=add_btn]", function(e) {
		
		//테이블의 총 row 개수
		var id = $('#dynatable tr').length;
		
		
	
		var master = $(this).parents("#dynatable");
		var prot = master.find("tr[id=prototype]").clone();
		var rowNo = $("#dynatable tr:last").find("input[name=row_no]:text").attr("value");
		var selectLength = $("#selectLength").val();
		rowNo++;
		
		if(rowNo > selectLength){
			alert("더이상 항목을 추가할 수 없습니다.");
			return;
		}else{
			prot.attr("id", "tr_added");
			prot.attr("style", ""); //프로토타입 css속성을 제거하여 hidden 속성을 제거한다.
			
			prot.find("input[name=cud]").attr("value", "C");
			prot.find("input[name=row_no]:text").attr("value", rowNo);
			
			
			master.find("tbody").append(prot);
		}
	});//add

	//동적테이블 row 삭제 
	$("#dynatable").on("click", "input[name=remove_btn]", function(e) {		
	
		var cud = $(this).parents("tr").find("input[name=cud]").attr("value");
		
		if (cud == "C") {
			//추가된 row 이면 row를 삭제한다.
			$(this).parents("tr").remove();
			
		} else if (cud == "D") {
			//조회된 row 가 삭제 'D' 상태이면  cud를 'U' 로 변경하고 버튼명을 '삭제'로 변경한다..
			$(this).parents("tr").css("background-color","");
			$(this).parents("tr").find("input[name=cud]").attr("value","U");
			$(this).parents("tr").find("input[name=remove_btn]").attr("value","삭제");
			
		} else {
			//조회된 row 가 삭제 'U' 상태이면  cud를 'D' 로 변경하고 버튼명을 '삭제'로 변경한다..
			$(this).parents("tr").css("background-color","#D5D5D5");
			$(this).parents("tr").find("input[name=cud]").attr("value","D");
			$(this).parents("tr").find("input[name=remove_btn]").attr("value","취소");
		}
	});
	
	//동적테이블 cud변경
	$("#dynatable").on("click", "input[name=xxx_check]", function(e) {
		if ( $(this).parents("tr").find("input[name=xxx_check]:checkbox").is(":checked") == true ) {
		} else {
		}
	});	
	
});

/**
 * 페이지의 모든 요소(이미지)가 로딩시 구동
 */
 $(window).load(function() {
	//cm_combo_cmm("#labelCode", '16030000');
	var cbIdx = '<c:out value="${boardVO.cbIdx}"/>';
	var bbsTempletGbn = '<c:out value="${boardVO.bbsTempletGbn}"/>';
	
	//doLabelCodeList(cbIdx);
	selectView(cbIdx);
	doBbsTempletSelect(cbIdx, bbsTempletGbn);
});
</SCRIPT>
</head>
<body style="text-align:center; margin:0 auto; display:inline; padding-top:100px;">
<form:form commandName="BoardVo" name="BoardVo" method="post" enctype="multipart/form-data">
<form:hidden path="cbIdx" />
<form:hidden path="cbCd" />
<form:hidden path="cbUprCd" />
<form:hidden path="ordNo" />
<form:hidden path="pageIndex" />
<form:hidden path="parent" />

<input type="hidden" id="cbIdx" name="cbIdx" value="<c:out value="${boardVO.cbIdx}"/>" />
<input type="hidden" id="cbCd" name="cbCd" value="<c:out value="${boardVO.cbCd}"/>" />
<input type="hidden" id="bbsTempletGbn" name="bbsTempletGbn" value="<c:out value="${boardVO.bbsTempletGbn}"/>" />
<input type="hidden" id="btIdx" name="btIdx" value="<c:out value="${boardVO.btIdx}"/>" />
<input type="hidden" id="selectLength" name="selectLength" />
<!-- 
cbIdx : <input type="text" id="cbIdx" name="cbIdx" value="<c:out value="${boardVO.cbIdx}"/>" />
cbCd : <input type="text" id="cbCd" name="cbCd" value="<c:out value="${boardVO.cbCd}"/>" />
bbsTempletGbn : <input type="text" id="bbsTempletGbn" name="bbsTempletGbn" value="<c:out value="${boardVO.bbsTempletGbn}"/>" />
btIdx : <input type="text" id="btIdx" name="btIdx" value="<c:out value="${boardVO.btIdx}"/>" />
selectLength : <input type="text" id="selectLength" name="selectLength" />
-->
	<!-- 컨텐츠 영역 -->
	<div id="contents" style="float:right; width:100%;">
		<table summary="" class="write" id="dynatable">
			<caption></caption>
			<colgroup>
				<col width="10%" />
				<col width="30%" />
				<col width="15%" />
				<col width="15%" />
				<col width="15%" />
				<col width="15%" />
			</colgroup>
			<tr>
				<th colspan="6">::: 포토(리스트) 게시판 디자인 설정</th>
			</tr>
			<tr>
				<th style="text-align: center;">번호</td>
				<th style="text-align: center;">목록 항목</td>
				<th style="text-align: center;">목록 가로크기</td>
				<th style="text-align: center;">웹 사용여부</td>
				<th style="text-align: center;">모바일 사용여부</td>
				<th style="text-align: center;">검색항목</td>
			</tr>
			<tr>
				<th colspan="5">
					<input type="button" value="추가" name="add_btn" id="add_btn" />
				</th>
				<td>
					<label for="clobYn">내용사용</label> <input type="checkbox" id="clobYn" name="clobYn" />
					<input type="hidden" id="clobYnValue" name="clobYnValue" />
				</td>
			</tr>
			<tr id="prototype" style="display:none;">
				<td>
					<input type="button" value="취소" name="remove_btn" id="remove_btn" size="30"/>
					<label for="row_no" class="hidden">번호</label><input type="text" name="row_no" id="row_no" value="0" size="1" readonly="readonly"/>
					<input type="hidden" name="cud" id="cud" size="1" value="P"/>
				</td>
				<td>
					<label for="labelCode" class="hidden">코드</label>
					<select id="labelCode" name="labelCode" onchange="javascript:doSelectBoxValCheck(this.value);">
						<option>선택하세요.</option>
					</select>
					<input type="hidden" id="labelCodeRowValue" name="labelCodeRowValue" />
				</td>
				<td>
					<label for="labelProvSize" class="hidden">사이즈</label>
					<input type="text" id="labelProvSize" name="labelProvSize" />
				</td>
				<td>
					<label for="webUseYn" class="hidden">웹사용여부</label>
					<input type="checkbox" id="webUseYn" name="webUseYn" />
					<input type="hidden" id="webUseYnValue" name="webUseYnValue" />
				</td>
				<td>
					<label for="mobileUseYn" class="hidden">모바일사용여부</label>
					<input type="checkbox" id="mobileUseYn" name="mobileUseYn" />
					<input type="hidden" id="mobileUseYnValue" name="mobileUseYnValue" />
				</td>
				<td>
					<label for="searchLabelUseYn" class="hidden">검색어추가여부</label>
					<input type="checkbox" id="searchLabelUseYn" name="searchLabelUseYn" />
					<input type="hidden" id="searchLabelUseYnValue" name="searchLabelUseYnValue" />
				</td>
			</tr>
		</table>
		<table summary="" class="write">
			<tr>
				<th colspan="5">::: 상세페이지 템플릿 선택</th>
			</tr>
			<tr>
				<th>템플릿 선택</th>
				<td colspan="4">
					<select id="bbsTemplet" name="bbsTemplet" >
						<option>::: 선택 :::</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>템플릿명</th>
				<td colspan="4">
					<input type="text" name="templetName" id="templetName" />
					<input type="file" name="uploadFile" id="uploadFile" />
					<a href="javascript:doTempletSave();" class="btn2">템플릿 저장</a>
				</td>
			</tr>
		</table>
		
		<!-- 버튼 -->
		<div id="menu" class="btn_zone">
			<!-- <a href="javascript:doMgrForm();" class="btn2">저장</a> -->
			<a href="javascript:doHistoryBack();" class="btn2">이전화면</a>
			<a href="javascript:doMgrForm();" class="btn2">저장</a>
		</div>
		<!-- //버튼 -->

	</div>
	<!-- //컨텐츠 영역 -->
</form:form>
</body>
</html>
