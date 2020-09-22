<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!-- 페이지 전용 스타일/스크립트 -->
<script type="text/javascript">
$(function(){
	//버튼이벤트
	$(".btn_view").click(function(e){
		e.preventDefault();
		$("#modal_history").remove();
		$("#overlay").hide();
		
		var modal_id = "modal_view";
		var modal_url = "./content_menu_form.jsp";
		var modal_param = {};;
		var modal_class = "wide"; //wide, small
		ajaxModal(modal_id, modal_url, modal_param, modal_class);
	});
});
</script>
<!-- //페이지 전용 스타일/스크립트 -->

<!-- ============================== 모달영역 ============================== -->
<div class="modalTitle">
	<h3>컨텐츠 등록</h3>
	<a href="#" class="btn_modalClose">모달팝업닫기</a>
</div>
<div class="modalContent">
	<div class="search">
		<table>
			<caption>검색테이블</caption>
			<colgroup>
				<col class="w80">
				<col>
				<col class="w80">
				<col>
			</colgroup>
			<tbody>
				<tr>
					<th scope="row"><label for="">검색기간</label></th>
					<td>
						<input type="text" id="" name="" class="useDatepicker" value="" size="10"> ~ 
						<input type="text" id="" name="" class="useDatepicker" value="" size="10">
					</td>
					<th scope="row"><label for="">수정내용</label></th>
					<td><input type="text" name="" class="w150"> </td>
				</tr>
			</tbody>
		</table>
		<button class="btn_inline btn_search">검색</button>
	</div>
	
	<div class="tableBox">
		<table class="list useBtn">
		<caption>컨텐츠 수정이력</caption>
		<colgroup>
			<col class="w50">
			<col>
			<col class="w80">
			<col class="w80">
			<col class="w80">
			<col class="w80">
		</colgroup>
		<thead>
			<tr>
				<th scope="col">순번</th>
				<th scope="col">수정내용</th>
				<th scope="col">수정일</th>
				<th scope="col">발행자</th>
				<th scope="col">보기</th>
				<th scope="col">복구</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>3</td>
				<td class="left">연락처 변경</td>
				<td>2019-02-09</td>
				<td>관리자</td>
				<td>최종수정</td>
				<td><a href="#" class="btn_inline useConfirm">이전복구</a></td>
			</tr>
			<tr>
				<td>2</td>
				<td class="left">좌측 상담실 아이콘 변경</td>
				<td>2019-01-02</td>
				<td>관리자</td>
				<td><a href="#" class="btn_inline btn_view">화면보기</a></td>
				<td><a href="#" class="btn_inline useConfirm">이전복구</a></td>
			</tr>
			<tr>
				<td>1</td>
				<td class="left">최초등록</td>
				<td>2018-12-12</td>
				<td>관리자</td>
				<td><a href="#" class="btn_inline btn_view">화면보기</a></td>
				<td>최초등록</td>
			</tr>
		</tbody>
		</table>
	</div>
	<div class="btnArea">
		<a href="#" class="btn_m btn_modalClose">취소</a>
	</div>
</div>
<!-- ============================== //모달영역 ============================== -->
