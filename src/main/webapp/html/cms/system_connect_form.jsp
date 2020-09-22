<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!-- ============================== 모달영역 ============================== -->
<div class="modalTitle">
	<h3>접속허용IP등록</h3>
	<a href="#" class="btn_modalClose">모달팝업닫기</a>
</div>
<div class="modalContent">
	<div class="tableBox">
		<table class="form">
			<caption></caption>
			<colgroup>
				<col class="w20p">
				<col>
			</colgroup>
			<tbody>
			<tbody>
			<tr>
				<th scope="row"><label for="">IP대역</label></th>
				<td>
					<input type="text" class="w35" id="ip1" value="" maxlength="3" onkeyup="" numberonly="true"> .
					<input type="text" class="w35" id="ip2" value="" maxlength="3" onkeyup="" numberonly="true"> .
					<input type="text" class="w35" id="ip3" value="" maxlength="3" onkeyup="" numberonly="true"> .
					<input type="text" class="w35" id="ip4" value="" maxlength="3" onkeyup="" numberonly="true"> ~
					<input type="text" class="w35" id="ip5" value="" maxlength="3" readonly="readonly"> .
					<input type="text" class="w35" id="ip6" value="" maxlength="3" readonly="readonly"> .
					<input type="text" class="w35" id="ip7" value="" maxlength="3" readonly="readonly"> .
					<input type="text" class="w35" id="ip8" value="" maxlength="3" numberonly="true">
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="">비고</label></th>
				<td>
					<textarea name="" id="" class="w100p h100"></textarea>
				</td>
			</tr>
			</tbody>
		</table>
	</div>
	<div class="btnArea">
		<a href="#" class="btn_m on">확인</a>
		<a href="#" class="btn_m btn_modalClose">취소</a>
	</div>
</div>
<!-- ============================== //모달영역 ============================== -->
