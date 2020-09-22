<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:include page="/html/cms/layout/header.jsp"></jsp:include>

<!-- 페이지 전용 스타일/스크립트 -->
<!-- //페이지 전용 스타일/스크립트 -->

<!-- 컨텐츠영역 -->
<div class="content">
	<div class="sectionTitle">
		<h2>문자발송관리</h2>
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
				</colgroup>
				<tbody>
					<tr>
						<th scope="row"><label for="">검색기간</label></th>
						<td>
							<a href="#" class="btn_inline">최근 한달</a>
							<a href="#" class="btn_inline">최근 일주일</a>
							<a href="#" class="btn_inline">어제</a>
							<a href="#" class="btn_inline">오늘</a>
							<input type="text" id="" name="" class="useDatepicker" value="" size="10"> ~ 
							<input type="text" id="" name="" class="useDatepicker" value="" size="10">
						</td>
					</tr>
					<tr>
						<th scope="row"><label for="">검색어</label></th>
						<td>
							<select id="" name="">
								<option value="" selected="selected">제목</option>
								<option value="">수신자</option>
								<option value="">수신번호</option>
								<option value="">회신번호</option>
							</select>
							<input type="text" name="" class="w150">
						</td>
					</tr>
				</tbody>
			</table>
			<button class="btn_inline btn_search">검색</button>
		</div>
		
		<div class="tableTitle">
			<div class="btnArea">
				<a href="#" class="btn_inline btn_add">신규발송</a>
			</div>
		</div>
		<div class="tableBox">
			<table class="list useBtn">
			<caption>문자발송 목록</caption>
			<colgroup>
				<col class="w50">
				<col>
				<col class="w100">
				<col class="w150">
				<col class="w150">
				<col class="w80">
				<col class="w80">
				<col class="w90">
			</colgroup>
			<thead>
				<tr>
					<th scope="col">순번</th>
					<th scope="col">제목</th>
					<th scope="col">수신자</th>
					<th scope="col">수신번호</th>
					<th scope="col">회신번호</th>
					<th scope="col">전송일</th>
					<th scope="col">전송결과</th>
					<th scope="col">관리</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>1</td>
					<td class="left">문자제목</td>
					<td>테스트</td>
					<td class="left">010-1234-5678</td>
					<td class="left">070-4609-6333</td>
					<td>2019-04-04</td>
					<td>전송완료</td>
					<td><a href="#" class="btn_inline btn_mod">상세보기</a></td>
				</tr>
				<tr>
					<td>2</td>
					<td class="left">문자제목</td>
					<td>사용자</td>
					<td class="left">010-1234-5678</td>
					<td class="left">070-4609-6333</td>
					<td>2019-04-04</td>
					<td>전송완료</td>
					<td><a href="#" class="btn_inline btn_mod">상세보기</a></td>
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
	</div>
	<a href="#" class="btn_asideToggle">메뉴얼 열고닫기</a>
</div>
<!-- //메뉴얼영역 -->

<jsp:include page="/html/cms/layout/footer.jsp"></jsp:include>