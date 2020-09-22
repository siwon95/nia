<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:include page="/html/cms/layout/header.jsp"></jsp:include>

<!-- 페이지 전용 스타일/스크립트 -->
<script type="text/javascript">
$(function(){ 
	//버튼이벤트
	$(".btn_add").click(function(e){
		e.preventDefault();
		var modal_id = "modal_add";
		var modal_url = "./system_manager_form.jsp";
		var modal_param = {};;
		var modal_class = ""; //wide, small
		ajaxModal(modal_id, modal_url, modal_param, modal_class);
	});
	
	//버튼이벤트
	$(".btn_mod").click(function(e){
		e.preventDefault();
		var modal_id = "modal_mod";
		var modal_url = "./system_manager_form.jsp";
		var modal_param = {};;
		var modal_class = ""; //wide, small
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
		<h2>운영자관리</h2>
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
					<col class="w80">
					<col>
					<col class="w80">
					<col>
					<col class="w80">
					<col>
				</colgroup>
				<tbody>
					<tr>
						<th scope="row"><label for="">권한선택</label></th>
						<td>
							<select id="" name="" class="w150">
								<option value="" selected="selected">선택해주세요</option>
								<option value="05010000">최고관리자</option>
								<option value="05020000">일반관리자</option>
							</select>
						</td>
						<th scope="row"><label for="">부서선택</label></th>
						<td>
							<select id="" name="" class="w150">
								<option value="" selected="selected">부서전체보기</option>
								<option value="">금융안정국</option>
							</select>
						</td>
						<th scope="row"><label for="">대표유무</label></th>
						<td>
							<select id="" name="" class="w100">
								<option value="" selected="selected">전체</option>
								<option value="Y">대표아이디</option>
								<option value="N">일반</option>
							</select>
						</td>
					</tr>
					<tr>
						<th scope="row"><label for="">사용유무</label></th>
						<td>
							<select id="" name="" class="w100">
								<option value="Y" selected="selected">사용</option>
								<option value="N">미사용</option>
							</select>
						</td>
						<th scope="row"><label for="">검색</label></th>
						<td colspan="3">
							<select id="" name="" class="w100">
								<option value="1">아이디</option>
								<option value="2">이름</option>
								<option value="3">이메일</option>
							</select> 
							<label for="" class="soundOnly">검색어</label>
							<input id="" name="" type="text" value="" class="w200">
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
				<a href="#" class="btn_inline btn_add">신규등록</a>
			</div>
		</div>
		<div class="tableBox">
			<table class="list useBtn">
			<caption>직원 목록</caption>
			<colgroup>
				<col class="w50">
				<col class="w50">
				<col class="w80">
				<col class="w80">
				<col class="w80">
				<col class="w80">
				<col class="w150">
				<col>
				<col class="w70">
				<col class="w70">
				<col class="w120">
			</colgroup>
			<thead>
				<tr>
					<th scope="col"><input type="checkbox" name="checkAll" value="1"></th>
					<th scope="col">순번</th>
					<th scope="col">등급</th>
					<th scope="col">권한</th>
					<th scope="col">아이디</th>
					<th scope="col">이름</th>
					<th scope="col">이메일</th>
					<th scope="col">부서정보</th>
					<th scope="col">대표</th>
					<th scope="col">사용</th>
					<th scope="col">관리</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><input type="checkbox" name="checkItem[]" value="1"></td>
					<td>1</td>
					<td>최고관리자</td>
					<td>모든권한</td>
					<td>admin</td>
					<td>관리자</td>
					<td class="left">webmaster@test.com</td>
					<td class="left">기획협력국 &gt; 지역협력실 &gt; 지역협력조정팀</td>
					<td><input type="checkbox" id="checkbox1_1" name="" class="useToggle" checked><label for="checkbox1_1">사용</label></td>
					<td><input type="checkbox" id="checkbox1_2" name="" class="useToggle" checked><label for="checkbox1_2">사용</label></td>
					<td>
						<a href="#" class="btn_inline btn_mod">수정</a>
						<a href="#" class="btn_inline btn_caption useConfirm">삭제</a>
					</td>
				</tr>
				<tr>
					<td><input type="checkbox" name="checkItem[]" value="1"></td>
					<td>2</td>
					<td>일반관리자</td>
					<td>모든권한</td>
					<td>manager</td>
					<td>운영자</td>
					<td class="left"></td>
					<td class="left">기획협력국 &gt; 홍보팀</td>
					<td><input type="checkbox" id="checkbox2_1" name="" class="useToggle" checked><label for="checkbox2_1">사용</label></td>
					<td><input type="checkbox" id="checkbox2_2" name="" class="useToggle" checked><label for="checkbox2_2">사용</label></td>
					<td>
						<a href="#" class="btn_inline btn_mod">수정</a>
						<a href="#" class="btn_inline btn_caption useConfirm">삭제</a>
					</td>
				</tr>
				<tr>
					<td><input type="checkbox" name="checkItem[]" value="1"></td>
					<td>3</td>
					<td>일반관리자</td>
					<td>모든권한</td>
					<td>user</td>
					<td>일반관리자</td>
					<td class="left"></td>
					<td class="left">기획협력국 &gt; 미디어소통실</td>
					<td><input type="checkbox" id="checkbox3_1" name="" class="useToggle"><label for="checkbox3_1">사용</label></td>
					<td><input type="checkbox" id="checkbox3_2" name="" class="useToggle"><label for="checkbox3_2">사용</label></td>
					<td>
						<a href="#" class="btn_inline btn_mod">수정</a>
						<a href="#" class="btn_inline btn_caption useConfirm">삭제</a>
					</td>
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
			운영자를 관리하는 메뉴입니다.<br />
			운영자 검색/등록/수정/삭제가 가능합니다
		</p>
		<h6>운영자 검색</h6>
		<ul>
			<li>상단 검색 폼을 통해서 검색합니다.</li>
		</ul>
		<h6>운영자 등록</h6>
		<ul>
			<li>우측 상단의 [신규등록]버튼을 클릭합니다.</li>
			<li>팝업 내의 폼을 통해서 정보를 입력합니다.</li>
			<li>하단의 [확인]버튼을 통해 저장합니다.</li>
		</ul>
		<h6>운영자 수정</h6>
		<ul>
			<li>테이블 우측에 [수정]버튼을 클릭합니다.</li>
			<li>팝업 내의 폼을 통해서 정보를 수정합니다.</li>
			<li>하단의 [수정]버튼을 통해 저장합니다.</li>
		</ul>
		<h6>운영자 삭제</h6>
		<ul>
			<li>테이블 우측에 [삭제]버튼을 통해서 삭제합니다.</li>
		</ul>
	</div>
	<a href="#" class="btn_asideToggle">메뉴얼 열고닫기</a>
</div>
<!-- //메뉴얼영역 -->

<jsp:include page="/html/cms/layout/footer.jsp"></jsp:include>