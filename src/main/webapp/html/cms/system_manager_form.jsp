<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!-- ============================== 모달영역 ============================== -->
<div class="modalTitle">
	<h3>운영자등록</h3>
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
			<tr>
				<th><label for="">아이디</label></th>
				<td>
					<input type="text" id="" name="" value="" class="w100">
					<a href="#" class="btn_inline">중복검사</a>
				</td>
			</tr>
			<tr>
				<th><label for="">이름</label></th>
				<td><input type="text" id="" name="" value="" class="w100p"></td>
			</tr>
			<tr>
				<th><label for="">비밀번호</label></th>
				<td><input type="password" id="" name="" value="" class="w100p"></td>
			</tr>
			<tr>
				<th><label for="">비밀번호확인</label></th>
				<td><input type="password" id="" name="" value="" class="w100p"></td>
			</tr>
			<tr>
				<th><label for="">권한선택</label></th>
				<td>
					<select id="" name="" class="w150">
						<option value="" selected="selected">선택해주세요</option>
						<option value="05010000">최고관리자</option>
						<option value="RL00000027">test123</option>
						<option value="RL00000026">평생학습관</option>
						<option value="RL00000025">테스트</option>
						<option value="RL00000024">교통지도과</option>
						<option value="RL00000023">홍보정책과</option>
						<option value="RL00000022">구청장에게바란다</option>
						<option value="RL00000021">공직자부조리신고</option>
						<option value="RL00000020">현장구청장실</option>
					</select>
				</td>
			</tr>
			<tr>
				<th><label for="">부서선택</label></th>
				<td>
					<select id="" name="" class="w150">
						<option value="" selected="selected">부서전체보기</option>
						<option value="">금융안정국</option>
					</select>
				</td>
			</tr>
			<tr>
				<th><label for="">대표유무</label></th>
				<td>
					<input type="checkbox" id="checkbox1" name="" class="useToggle" checked><label for="checkbox1">대표</label>
				</td>
			</tr>
			<tr>
				<th><label for="">사용유무</label></th>
				<td>
					<input type="checkbox" id="checkbox2" name="" class="useToggle"><label for="checkbox2">사용</label>
				</td>
			</tr>
			</tbody>
		</table>
	</div>
	<div class="btnArea">
		<a href="#" class="btn_m on">확인</a>
		<a href="#" class="btn_m on">수정</a>
		<a href="#" class="btn_m btn_caption useConfirm">삭제</a>
		<a href="#" class="btn_m btn_modalClose">취소</a>
	</div>
</div>
<!-- ============================== //모달영역 ============================== -->
