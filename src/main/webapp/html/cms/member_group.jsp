<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:include page="/html/cms/layout/header.jsp"></jsp:include>

<!-- 페이지 전용 스타일/스크립트 -->
<script type="text/javascript">
$(function(){ 
	//버튼이벤트
	$(".btn_add").click(function(e){
		e.preventDefault();
		var modal_id = "modal_add";
		var modal_url = "./member_group_form.jsp";
		var modal_param = {};;
		var modal_class = "small"; //wide, small
		ajaxModal(modal_id, modal_url, modal_param, modal_class);
	});
	
	//버튼이벤트
	$(".btn_mod").click(function(e){
		e.preventDefault();
		var modal_id = "modal_mod";
		var modal_url = "./member_group_form.jsp";
		var modal_param = {};;
		var modal_class = "small"; //wide, small
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
		<h2>회원그룹관리</h2>
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
				<a href="#" class="btn_inline">선택수정</a>
				<a href="#" class="btn_inline">선택삭제</a>
			</div>
			<div class="btnArea">
				<a href="#" class="btn_inline btn_add">신규등록</a>
			</div>
		</div>
		<div class="tableBox">
			<table class="list useBtn">
			<caption>회원그룹 목록</caption>
			<colgroup>
				<col class="w50">
				<col class="w50">
				<col class="w120">
				<col class="w120">
				<col>
				<col class="w140">
				<col class="w120">
			</colgroup>
			<thead>
				<tr>
					<th scope="col"><input type="checkbox" name="checkAll" value="1"></th>
					<th scope="col">순번</th>
					<th scope="col">그룹명</th>
					<th scope="col">회원수</th>
					<th scope="col">관련게시판</th>
					<th scope="col">등록일</th>
					<th scope="col">관리</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><input type="checkbox" name="checkItem[]" value="1"></td>
					<td>1</td>
					<td class="left"><input type="text" name="" value="그룹1" class="w100p"></td>
					<td>0</td>
					<td></td>
					<td>2016-08-05 00:00:00</td>
					<td>
						<a href="#" class="btn_inline btn_mod">수정</a>
						<a href="#" class="btn_inline btn_caption useConfirm">삭제</a>
					</td>
				</tr>
				<tr>
					<td colspan="7" class="empty">해당 자료가 없습니다.</td>
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
			회원그룹을 관리하는 메뉴입니다.<br />
			회원그룹 등록/수정/삭제가 가능합니다
		</p>
		<h6>회원그룹 등록</h6>
		<ul>
			<li>우측 상단의 [신규등록] 버튼을 클릭합니다.</li>
			<li>팝업 내의 폼을 통해서 정보를 입력합니다.</li>
		</ul>
		<h6>회원그룹 수정</h6>
		<ul>
			<li>테이블 우측의 [수정]버튼을 클릭합니다.</li>
			<li>팝업 내의 폼을 통해서 정보를 수정합니다.</li>
		</ul>
		<h6>회원그룹 삭제</h6>
		<ul>
			<li>테이블 우측의 [삭제]버튼을 클릭합니다.</li>
		</ul>
	</div>
	<a href="#" class="btn_asideToggle">메뉴얼 열고닫기</a>
</div>
<!-- //메뉴얼영역 -->

<jsp:include page="/html/cms/layout/footer.jsp"></jsp:include>