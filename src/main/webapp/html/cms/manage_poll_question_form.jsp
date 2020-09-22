<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!-- ============================== 모달영역 ============================== -->
<div class="modalTitle">
	<h3>질문등록</h3>
	<a href="#" class="btn_modalClose">모달팝업닫기</a>
</div>
<div class="modalContent scrolled h500">
	<div class="tableBox">
		<table class="form">
			<caption></caption>
			<colgroup>
				<col class="w15p">
				<col>
			</colgroup>
			<tbody>
				<tr>
					<th scope="row"><label for="">제목</label></th>
					<td><input type="text" id="" name="" value="" class="w100p"></td>
				</tr>
				<tr id="detailTr">
					<th scope="row"><label for="">상세설명</label></th>
					<td><textarea id="" name="" class="w100p h80"></textarea></td>
				</tr>
				<tr>
					<th scope="row"><label for="">입력형식</label></th>
					<td>
						
						<select id="" name="">
							<option value="radio">객관식(단수선택)-라디오버튼형식</option>
							<option value="selectbox">객관식(단수선택)-셀렉트박스형식</option>
							<option value="checkbox">객관식(복수선택)-체크박스형식</option>
							<option value="text">주관식(한줄입력)</option>
							<option value="textarea">주관식(여러줄입력)</option>
							<option value="title">제목(그룹용)</option>
						</select>
					</td>
				</tr>
				<tr id="checkTr">
					<th scope="row">필수입력</th>
					<td>
						<input type="radio" id="" name="" value="Y" checked><label for="">필수</label>
						<input type="radio" id="" name="" value="N"><label for="">선택</label>
					</td>
				</tr>
				<tr id="etcTr">
					<th scope="row">기타입력</th>
					<td>
						<input type="radio" id="" name="" value="Y"><label for="">사용</label>
						<input type="radio" id="" name="" value="N" checked><label for="">미사용</label>
						<span class="captionText">(사용하실 경우는 마지막항목을 기타 등으로 입력하시면 됩니다.)</span>
					</td>
				</tr>
				<tr id="itemCntTr">
					<th scope="row"><label for="">객관식 항목수</label></th>
					<td>
						<select name="" id="">
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4" selected="selected">4</option>
							<option value="5">5</option>
							<option value="6">6</option>
							<option value="7">7</option>
							<option value="8">8</option>
							<option value="9">9</option>
							<option value="10">10</option>
						
						</select> 개
					</td>
				</tr>
				<tr id="itemListTr">
					<th scope="row">객관식 입력사항</th>
					<td>
						<table id="itemTable" class="list">
							<colgroup>
								<col style="width:60px;">
								<col>
								<col style="width:100px;">
							</colgroup>
							<thead>
								<tr>
									<th scope="col">번호</th>
									<th scope="col">항목</th>
									<th scope="col">순위변경</th>
								</tr>
							</thead>
							<tbody>
								<tr>	
									<td scope="row">1</td>
									<td>
										<input type="text" name="" value="" class="w95p">
									</td>
									<td>
										<span onclick="goSortUp(this);"><a href="#;" class="btn_s">▲</a></span>
										<span onclick="goSortDown(this);"><a href="#;" class="btn_s">▼</a></span>
									</td>
								</tr>
								<tr>	
									<td scope="row">2</td>
									<td>
										<input type="text" name="" value="" class="w95p">
									</td>
									<td>
										<span onclick="goSortUp(this);"><a href="#;" class="btn_s">▲</a></span>
										<span onclick="goSortDown(this);"><a href="#;" class="btn_s">▼</a></span>
									</td>
								</tr>
								<tr>	
									<td scope="row">3</td>
									<td>
										<input type="text" name="" value="" class="w95p">
									</td>
									<td>
										<span onclick="goSortUp(this);"><a href="#;" class="btn_s">▲</a></span>
										<span onclick="goSortDown(this);"><a href="#;" class="btn_s">▼</a></span>
									</td>
								</tr>
							
								<tr>	
									<td scope="row">4</td>
									<td>
										<input type="text" name="" value="" class="w95p">
									</td>
									<td>
										<span onclick="goSortUp(this);"><a href="#;" class="btn_s">▲</a></span>
										<span onclick="goSortDown(this);"><a href="#;" class="btn_s">▼</a></span>
									</td>
								</tr>
							
							</tbody>
						</table>
					</td>
				</tr>
				<tr id="itemDirectionTr">
					<th scope="row"><label for="">항목방향</label></th>
					<td>
						<input type="radio" id="" name="" value="R" checked><label for="">가로</label>
						<input type="radio" id="" name="" value="C"><label for="">세로</label>
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
