<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:include page="/html/cms/layout/header.jsp"></jsp:include>

<!-- 페이지 전용 스타일/스크립트 -->
<script type="text/javascript">
$(function(){ 
	//버튼이벤트
	$(".btn_add").click(function(e){
		e.preventDefault();
		var modal_id = "modal_add";
		var modal_url = "./system_auth_form.jsp";
		var modal_param = {};;
		var modal_class = "small"; //wide, small
		ajaxModal(modal_id, modal_url, modal_param, modal_class);
	});
	
	//버튼이벤트
	$(".btn_mod").click(function(e){
		e.preventDefault();
		var modal_id = "modal_mod";
		var modal_url = "./system_auth_config.jsp";
		var modal_param = {};;
		var modal_class = "wide"; //wide, small
		ajaxModal(modal_id, modal_url, modal_param, modal_class);
	});
	
	//전체선택
    $("input[name=checkAll]").click(function() {
		if($(this).is(":checked"))
			$("input[name^=checkItem]").prop("checked", true);
		else
			$("input[name^=checkItem]").prop("checked", false);
    });
});
</script>
<!-- //페이지 전용 스타일/스크립트 -->

<!-- 컨텐츠영역 -->
<div class="content">
	<div class="sectionTitle">
		<h2>권한관리</h2>
		<!-- <div class="listType">
			<a href="#" class="btn_listTypeThumb">섬네일형</a>
			<a href="#" class="btn_listTypeList">리스트형</a>
		</div> -->
	</div>
	<div class="section">
		<div class="search">
			<div class="right">
				<input type="text" name="" class="w150"> 
				<a href="#" class="btn_inline">검색</a> 
			</div>
		</div>
		
		<div class="tableTitle">
			<div class="btnArea left">
				<a href="#" class="btn_inline">선택삭제</a>
			</div>
			<div class="btnArea">
				<a href="#" class="btn_inline btn_add">신규등록</a>
			</div>
		</div>
		<div class="tableBox">
			<table class="list useBtn">
			<caption>권한 목록</caption>
			<colgroup>
				<col class="w50">
				<col class="w50">
				<col>
				<col class="w70">
				<col class="w90">
			</colgroup>
			<thead>
				<tr>
					<th scope="col"><input type="checkbox" name="checkAll" value="1"></th>
					<th scope="col">순번</th>
					<th scope="col">권한명</th>
					<th scope="col">사용</th>
					<th scope="col">관리</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><input type="checkbox" name="checkItem[]" value="1"></td>
					<td>3</td>
					<td><input type="text" id="" name="" value="평생학습관" class="w100p"></td>
					<td><input type="checkbox" id="checkbox1" name="" class="useToggle" checked><label for="checkbox1">사용</label></td>
					<td><a href="#" class="btn_inline on btn_mod">권한설정</a></td>
				</tr>
				<tr>
					<td><input type="checkbox" name="checkItem[]" value="1"></td>
					<td>2</td>
					<td><input type="text" id="" name="" value="교통지도과" class="w100p"></td>
					<td><input type="checkbox" id="checkbox2" name="" class="useToggle" checked><label for="checkbox2">사용</label></td>
					<td><a href="#" class="btn_inline on btn_mod">권한설정</a></td>
				</tr>
				<tr>
					<td><input type="checkbox" name="checkItem[]" value="1"></td>
					<td>1</td>
					<td><input type="text" id="" name="" value="홍보정책과" class="w100p"></td>
					<td><input type="checkbox" id="checkbox3" name="" class="useToggle"><label for="checkbox3">사용</label></td>
					<td><a href="#" class="btn_inline on btn_mod">권한설정</a></td>
				</tr>
			</tbody>
			</table>
		</div>
	</div>
</div>
<!-- //컨텐츠영역 -->

<!-- 메뉴얼영역 -->
<div class="aside">
	<div class="asideTitle">
		<h5>메뉴얼</h5>
	</div>
	<div class="asideContent">
		<p>
			권한을 관리하는 메뉴입니다.<br />
			권한 검색/등록/설정이 가능합니다
		</p>
		<h6>권한 검색</h6>
		<ul>
			<li>상단 검색 폼을 통해서 검색합니다.</li>
		</ul>
		<h6>권한 등록</h6>
		<ul>
			<li>우측 상단의 [신규등록]버튼을 클릭합니다.</li>
			<li>팝업 내의 폼을 통해서 정보를 입력합니다.</li>
		</ul>
		<h6>권한 설정</h6>
		<ul>
			<li>테이블 우측의 [권한설정]버튼을 클릭합니다.</li>
		</ul>
	</div>
	<a href="#" class="btn_asideToggle">메뉴얼 열고닫기</a>
</div>
<!-- //메뉴얼영역 -->

<jsp:include page="/html/cms/layout/footer.jsp"></jsp:include>