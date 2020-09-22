<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:include page="/html/cms/layout/header.jsp"></jsp:include>

<!-- 페이지 전용 스타일/스크립트 -->
<!-- //페이지 전용 스타일/스크립트 -->

<!-- 컨텐츠영역 -->
<div class="content">
	<div class="sectionTitle">
		<h2>로그인 기록</h2>
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
					<col class="w70">
					<col>
					<col class="w70">
					<col>
				</colgroup>
				<tbody>
					<tr>
						<th><label for="">상태</label></th>
						<td>
							<select id="" name="">
								<option value="" selected="selected">전체</option>
								<option value="">성공</option>
								<option value="">실패</option>
							</select>
						</td>
						<th><label for="">검색어</label></th>
						<td>
							<select id="" name="">
								<option value="" selected="selected">전체</option>
								<option value="">아이디</option>
								<option value="">IP주소</option>
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
				<a href="#" class="btn_inline">선택삭제</a>
			</div>
			<div class="btnArea">
				<a href="#" class="btn_inline btn_down">엑셀다운로드</a>
			</div>
		</div>
		<div class="tableBox">
			<table class="list useBtn">
			<caption>로그인기록 목록</caption>
			<colgroup>
				<col class="w50">
				<col class="w50">
				<col class="w60">
				<col class="w90">
				<col>
				<col class="w140">
				<col class="w70">
			</colgroup>
			<thead>
				<tr>
					<th scope="col"><input type="checkbox" name="checkAll" value="1"></th>
					<th scope="col">순번</th>
					<th scope="col">상태</th>
					<th scope="col">아이디</th>
					<th scope="col">아이피</th>
					<th scope="col">일시</th>
					<th scope="col">관리</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><input type="checkbox" name="checkItem[]" value="1"></td>
					<td>1</td>
					<td>성공</td>
					<td>test</td>
					<td>000.000.000.000</td>
					<td>2016-08-05 00:00:00</td>
					<td>
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