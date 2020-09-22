<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!-- ============================== 모달영역 ============================== -->
<div class="modalTitle">
	<h3>설문등록</h3>
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
					<th scope="row"><label for="">제목</label></th>
					<td colspan="3"><input id="" name="" type="text" value="" class="w100p"></td>
				</tr>
				<tr>
					<th scope="row"><label for="">안내문</label></th>
					<td colspan="3"><textarea id="" name="" class="w100p h50"></textarea></td>
				</tr>
				<tr>
					<th scope="row"><label for="">담당부서</label></th>
					<td>
						<select id="" name="">
							<option value="" selected="selected">담당부서 선택</option>
							<option value="">금융안전국</option>
						</select>
					</td>
					<th scope="row"><label for="">담당자연락처</label></th>
					<td><input id="" name="" type="text" value="" class="w100p"></td>
				</tr>
				<tr>
					<th scope="row"><label for="">설문조사기간</label></th>
					<td colspan="3">
						<input type="text" id="" name="" class="useDatepicker" value="" size="10">
						<select id="" name="">
							<option value="00" selected="selected">00</option>
							<option value="01">01</option>
							<option value="02">02</option>
							<option value="03">03</option>
							<option value="04">04</option>
							<option value="05">05</option>
							<option value="06">06</option>
							<option value="07">07</option>
							<option value="08">08</option>
							<option value="09">09</option>
							<option value="10">10</option>
							<option value="11">11</option>
							<option value="12">12</option>
							<option value="13">13</option>
							<option value="14">14</option>
							<option value="15">15</option>
							<option value="16">16</option>
							<option value="17">17</option>
							<option value="18">18</option>
							<option value="19">19</option>
							<option value="20">20</option>
							<option value="21">21</option>
							<option value="22">22</option>
							<option value="23">23</option>				
						</select>
						<label for="">시</label>
						<select id="" name="">
							<option value="00" selected="selected">00</option>
							<option value="10">10</option>
							<option value="20">20</option>
							<option value="30">30</option>
							<option value="40">40</option>
							<option value="50">50</option>
						</select>
						<label for="">분</label>
						까지 ~ 
						<input type="text" id="" name="" class="useDatepicker" value="" size="10">
						<select id="" name="">
							<option value="00" selected="selected">00</option>
							<option value="01">01</option>
							<option value="02">02</option>
							<option value="03">03</option>
							<option value="04">04</option>
							<option value="05">05</option>
							<option value="06">06</option>
							<option value="07">07</option>
							<option value="08">08</option>
							<option value="09">09</option>
							<option value="10">10</option>
							<option value="11">11</option>
							<option value="12">12</option>
							<option value="13">13</option>
							<option value="14">14</option>
							<option value="15">15</option>
							<option value="16">16</option>
							<option value="17">17</option>
							<option value="18">18</option>
							<option value="19">19</option>
							<option value="20">20</option>
							<option value="21">21</option>
							<option value="22">22</option>
							<option value="23">23</option>				
						</select>
						<label for="">시</label>
						<select id="" name="">
							<option value="00" selected="selected">00</option>
							<option value="10">10</option>
							<option value="20">20</option>
							<option value="30">30</option>
							<option value="40">40</option>
							<option value="50">50</option>
						</select>
						<label for="">분</label>
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="">참여인원</label></th>
					<td colspan="3"><input id="" name="" type="text" value="" class="w50">명 (*숫자만 입력 가능합니다.)</td>
				</tr>
				<tr>
					<th scope="row">인증방법</th>
					<td>
						<input id="" name="" type="radio" value="" checked><label for="">없음</label>
						<input id="" name="" type="radio" value=""><label for="">IP</label>
						<input id="" name="" type="radio" value=""><label for="">본인확인</label>
						<input id="" name="" type="radio" value=""><label for="">로그인</label>
					</td>
					<th scope="row"><label for="">설문결과 공개여부</label></th>
					<td>
						<input id="" name="" type="radio" value="" checked><label for="">사용</label>
						<input id="" name="" type="radio" value=""><label for="">미사용</label>
					</td>
				</tr>
				<tr>
					<th scope="row">주소</th>
					<td>
						<input id="" name="" type="radio" value="" checked><label for="">사용</label>
						<input id="" name="" type="radio" value=""><label for="">미사용</label>
					</td>
					<th scope="row">이메일</th>
					<td>
						<input id="" name="" type="radio" value="" checked><label for="">사용</label>
						<input id="" name="" type="radio" value=""><label for="">미사용</label>
					</td>
				</tr>
				<tr>
					<th scope="row">전화번호</th>
					<td>
						<input id="" name="" type="radio" value="" checked><label for="">사용</label>
						<input id="" name="" type="radio" value=""><label for="">미사용</label>
					</td>
					<th scope="row">휴대폰번호</th>
					<td>
						<input id="" name="" type="radio" value="" checked><label for="">사용</label>
						<input id="" name="" type="radio" value=""><label for="">미사용</label>
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
