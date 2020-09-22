<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!-- ============================== 모달영역 ============================== -->
<div class="modalTitle">
	<h3>회원등록</h3>
	<a href="#" class="btn_modalClose">모달팝업닫기</a>
</div>
<div class="modalContent">
	<div class="tableBox">
		<table class="form">
			<caption></caption>
			<colgroup>
				<col class="w15p">
				<col>
				<col class="w15p">
				<col>
			</colgroup>
			<tbody>
				<tr>
					<th><span class="required">*</span><label for="">아이디</label></th>
					<td><input type="text" id="" name="" value="" class="w100"></td>
					<th><span class="required">*</span><label for="">이름</label></th>
					<td><input type="text" id="" name="" value="" class="w100"></td>
				</tr>
				<tr>
					<th><span class="required">*</span><label for="">비밀번호</label></th>
					<td>
						<input type="password" id="" name="" value="" maxlength="15" class="w150">
						※ 영문 대소문자, 숫자, 숫자키에 매치되는 특수문자(10~15자리)
					</td>
					<th><span class="required">*</span><label for="">비밀번호확인</label></th>
					<td><input type="password" id="" name="" maxlength="15" class="w150"></td>						
				</tr>
				<tr>
					<th><span class="required">*</span><label for="">주소</label></th>
					<td colspan="3">
						<input id="" name="" readonly="readonly" type="text" value="" class="w60"> 
						<a href="#" class="btn_inline">주소검색</a><br />
						<input type="text" id="" name="" title="기본주소" readonly value="" class="w200">
						<input type="text" id="" name="" title="상세주소" value="" class="w200">
					</td>				
				</tr>
				<tr>
					<th><label for="">전화번호</label></th>
					<td>
						<input type="text" id="" name="" title="전화번호앞자리" value="" class="w50"> -
						<input type="text" id="" name="" title="전화번호중간자리" value="" class="w60"> -
						<input type="text" id="" name="" title="전화번호끝자리" value="" class="w60">
					</td>
					<th><span class="required">*</span><label for="">휴대폰번호</label></th>						
					<td>
						<input type="text" id="" name="" title="휴대폰번호앞자리" value="" class="w50"> -
						<input type="text" id="" name="" title="휴대폰번호중간자리" value="" class="w60"> -
						<input type="text" id="" name="" title="휴대폰번호끝자리" value="" class="w60">
					</td>						
				</tr>
				<tr>
					<th><span class="required">*</span><label for="">이메일</label></th>
					<td colspan="3">
						<input type="text" id="" name="" title="이메일" value="" maxlength="15" class="w100"> @ 
						<input type="text" id="" name="" title="" maxlength="15" value="메일주소선택" disabled="disabled">
						<select id="" title="">
							<option value="">메일주소선택</option>
							<option value="hanmail.net">hanmail.net</option>
							<option value="naver.com">naver.com</option>
							<option value="yahoo.co.kr">yahoo.co.kr</option>
							<option value="paran.com">paran.com</option>
							<option value="empal.com">empal.com</option>
							<option value="nate.com">nate.com</option>
							<option value="gmail.com">gmail.com</option>
							<option value="dreamwiz.com">dreamwiz.com</option>
							<option value="freechal.com">freechal.com</option>
							<option value="chollian.net">chollian.net</option>
							<option value="lycos.co.kr">lycos.co.kr</option>
							<option value="hanafos.com">hanafos.com</option>
							<option value="">직접입력</option>
						</select>							
					</td>
				</tr>					
				<tr>
					<th><span class="required">*</span>이메일 수신 여부</th>
					<td colspan="3">
					    <input type="radio" id="" name="" title="이메일 수신 여부 - 예" value="Y"><label for="">예</label>
						<input type="radio" id="" name="" title="이메일 수신 여부 - 아니오" value="N" checked><label for="">아니오</label>
					</td>
				</tr>
				<tr>
					<th><span class="required">*</span>SMS 수신 여부</th>
					<td colspan="3">
						<input type="radio" id="" name="" title="SMS 수신 여부" value="Y"><label for="">예</label>
						<input type="radio" id="" name="" title="SMS 수신 여부" value="N" checked><label for="">아니오</label>						
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
