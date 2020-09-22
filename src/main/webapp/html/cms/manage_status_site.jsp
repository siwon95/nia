<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:include page="/html/cms/layout/header.jsp"></jsp:include>

<!-- 페이지 전용 스타일/스크립트 -->
<link rel="stylesheet" type="text/css" href="/js/c3chart/c3.css" />
<script src="/js/c3chart/d3-3.5.6.min.js"></script>
<script src="/js/c3chart/c3.min.js"></script>
<script type="text/javascript">
$(function(){ 
	//차트
	var chart = c3.generate({
		bindto: '#chart',
		data: {
			columns: [
				['data1', 30, 200, 100, 400, 150, 250]
			]
		}
	});
});
</script>
<!-- //페이지 전용 스타일/스크립트 -->

<!-- 컨텐츠영역 -->
<div class="content">
	<div class="sectionTitle">
		<h2>사이트별 접속통계</h2>
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
				</colgroup>
				<tbody>
					<tr>
						<th scope="row"><label for="">사이트</label></th>
						<td>
							<select id="" name="" class="w150">
								<option value="">사이트선택</option>
								<option value="yangcheon" selected="selected">대표홈페이지</option>
								<option value="demo">데모사이트</option>
							</select>
						</td>
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
				</tbody>
			</table>
			<button class="btn_inline btn_search">검색</button>
		</div>
		
		<div id="chart" class="chartWrap">
		</div>
		
		<div class="btnArea right">
			<a href="#" class="btn_inline btn_down">엑셀다운로드</a>
		</div>
		<div class="divGroup cols3">
			<div>
				<div class="tableTitle">
					<h5>접속 IP수</h5>
				</div>
				<div class="tableBox">
					<table class="list">
					<caption>접속 IP수 목록</caption>
					<thead>
						<tr>
							<th scope="col">일</th>
							<th scope="col">IP수</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>2019-04-04</td>
							<td>1</td>
						</tr>
						<tr>
							<td>2019-04-03</td>
							<td>1</td>
						</tr>
						<tr>
							<td>2019-04-02</td>
							<td>1</td>
						</tr>
						<tr>
							<td>2019-04-01</td>
							<td>1</td>
						</tr>
						<tr>
							<td colspan="2" class="empty">해당 자료가 없습니다.</td>
						</tr>
					</tbody>
					</table>
				</div>
			</div>
			<div>
				<div class="tableTitle">
					<h5>방문자 수(UV)</h5>
				</div>
				<div class="tableBox">
					<table class="list">
					<caption>방문자 수 목록</caption>
					<thead>
						<tr>
							<th scope="col">일</th>
							<th scope="col">방문자 수</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>2019-04-04</td>
							<td>1</td>
						</tr>
						<tr>
							<td>2019-04-03</td>
							<td>1</td>
						</tr>
						<tr>
							<td>2019-04-02</td>
							<td>1</td>
						</tr>
						<tr>
							<td>2019-04-01</td>
							<td>1</td>
						</tr>
						<tr>
							<td colspan="2" class="empty">해당 자료가 없습니다.</td>
						</tr>
					</tbody>
					</table>
				</div>
			</div>
			<div>
				<div class="tableTitle">
					<h5>페이지 뷰(PV)</h5>
				</div>
				<div class="tableBox">
					<table class="list">
					<caption>페이지 뷰(PV) 목록</caption>
					<thead>
						<tr>
							<th scope="col">일</th>
							<th scope="col">페이지 뷰 수</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>2019-04-04</td>
							<td>1</td>
						</tr>
						<tr>
							<td>2019-04-03</td>
							<td>1</td>
						</tr>
						<tr>
							<td>2019-04-02</td>
							<td>1</td>
						</tr>
						<tr>
							<td>2019-04-01</td>
							<td>1</td>
						</tr>
						<tr>
							<td colspan="2" class="empty">해당 자료가 없습니다.</td>
						</tr>
					</tbody>
					</table>
				</div>
			</div>
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
			사이트별 접속통계를 보여주는 메뉴입니다.<br />
			접속통계 검색이 가능합니다
		</p>
		<h6>접속통계 검색</h6>
		<ul>
			<li>상단 검색폼을 통해서 검색할 수 있습니다.</li>
		</ul>
	</div>
	<a href="#" class="btn_asideToggle">메뉴얼 열고닫기</a>
</div>
<!-- //메뉴얼영역 -->

<jsp:include page="/html/cms/layout/footer.jsp"></jsp:include>