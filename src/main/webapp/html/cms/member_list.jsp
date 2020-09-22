<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:include page="/html/cms/layout/header.jsp"></jsp:include>

<!-- 페이지 전용 스타일/스크립트 -->
<script type="text/javascript">
$(function(){ 
	//버튼이벤트
	$(".btn_add").click(function(e){
		e.preventDefault();
		var modal_id = "modal_add";
		var modal_url = "./member_form.jsp";
		var modal_param = {};;
		var modal_class = "wide"; //wide, small
		ajaxModal(modal_id, modal_url, modal_param, modal_class);
	});
	
	//버튼이벤트
	$(".btn_mod").click(function(e){
		e.preventDefault();
		var modal_id = "modal_mod";
		var modal_url = "./member_form.jsp";
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
		<h2>회원목록</h2>
		<!-- <div class="listType">
			<a href="#" class="btn_listTypeThumb">섬네일형</a>
			<a href="#" class="btn_listTypeList">리스트형</a>
		</div> -->
	</div>
	<div class="section">
		<div class="search">
			<table>
				<caption>검색테이블</caption>
				<colgroup>
					<col class="w90">
					<col>
					<col class="w90">
					<col>
					<col class="w90">
					<col>
				</colgroup>
				<tbody>
					<tr>
						<th><label for="">가입일</label></th>
						<td>
							<input type="text" id="" name="" value="" class="useDatepicker"> ~
							<input type="text" id="" name="" value="" class="useDatepicker">		
						</td>
						<th><label for="">최종방문일</label></th>
						<td colspan="3">
							<input type="text" id="" name="" value="" class="useDatepicker"> ~
							<input type="text" id="" name="" value="" class="useDatepicker">		
						</td>
					</tr>
					<tr>
						<th><label for="">이메일수신</label></th>
						<td>
							<input id="" name="" type="radio" value="" checked="checked"><label for="scEmailRcvChk1">전체</label> 
							<input id="" name="" type="radio" value="Y"><label for="scEmailRcvChk2">동의</label> 
							<input id="" name="" type="radio" value="N"><label for="scEmailRcvChk3">거부</label> 
						</td>
						<th><label for="">SMS수신</label></th>
						<td>
							<input id="" name="" type="radio" value="" checked="checked"><label for="scSmsRcvYn1">전체</label> 
							<input id="" name="" type="radio" value="Y"><label for="scSmsRcvYn2">동의</label> 
							<input id="" name="" type="radio" value="N"><label for="scSmsRcvYn3">거부</label> 
						</td>
						<th><label for="">검색어</label></th>
						<td>
							<select id="" name="">
								<option value="" selected="selected">전체</option>
								<option value="name">이름</option>
								<option value="id">아이디</option>
								<option value="hp">핸드폰번호</option>
							</select>
							<input id="" name="" type="text" value="" size="30">
						</td>
					</tr>	
				</tbody>
			</table>
			<button class="btn_inline btn_search">검색</button>
		</div>
		
		<div class="tableTitle">
			<div class="btnArea left">
				<a href="#" class="btn_inline">선택수정</a>
				<a href="#" class="btn_inline">선택삭제</a>
			</div>
			<div class="btnArea">
				<a href="#" class="btn_inline btn_down">엑셀다운로드</a>
				<a href="#" class="btn_inline btn_add">신규등록</a>
			</div>
		</div>
		<div class="tableBox">
			<table class="list useBtn">
			<caption>회원그룹 목록</caption>
			<colgroup>
				<col class="w50">
				<col class="w50">
				<col class="w90">
				<col class="w90">
				<col>
				<col>
				<col class="w90">
				<col class="w90">
				<col class="w120">
			</colgroup>
			<thead>
				<tr>
					<th scope="col"><input type="checkbox" name="checkAll" value="1"></th>
					<th scope="col">순번</th>
					<th scope="col">아이디</th>
					<th scope="col">이름</th>
					<th scope="col">전화번호</th>
					<th scope="col">휴대폰번호</th>
					<th scope="col">가입일</th>
					<th scope="col">최종방문일</th>
					<th scope="col">관리</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><input type="checkbox" name="checkItem[]" value="1"></td>
					<td>1</td>
					<td>test</td>
					<td>테스트</td>
					<td>000-0000-0000</td>
					<td>000-0000-0000</td>
					<td>2016-08-05</td>
					<td>2016-08-05</td>
					<td>
						<a href="#" class="btn_inline btn_mod">수정</a>
						<a href="#" class="btn_inline btn_caption useConfirm">삭제</a>
					</td>
				</tr>
				<tr>
					<td colspan="9" class="empty">해당 자료가 없습니다.</td>
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
			여러개의 사이트에 대한 관리가 가능합니다.<br />
			각 사이트별 메뉴와 게시판이 구분되어 관리 됩니다.
		</p>
		<h6>사이트 등록/수정</h6>
		<ul>
			<li>[+] 버튼을 통해 사이트 등록을 할 수 있습니다.</li>
			<li>사이트 섬네일을 클릭하시면 등록정보를 변경 할 수 있습니다.</li>
		</ul>
	</div>
	<a href="#" class="btn_asideToggle">메뉴얼 열고닫기</a>
</div>
<!-- //메뉴얼영역 -->

<jsp:include page="/html/cms/layout/footer.jsp"></jsp:include>