<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:include page="/html/cms/layout/header.jsp"></jsp:include>

<!-- 페이지 전용 스타일/스크립트 -->
<!-- //페이지 전용 스타일/스크립트 -->

<!-- 컨텐츠영역 -->
<div class="content">
	<div class="sectionTitle">
		<h2>설문조사 &gt; 설문결과</h2>
		<!-- <div class="listType">
			<a href="#" class="btn_listTypeThumb">섬네일형</a>
			<a href="#" class="btn_listTypeList">리스트형</a>
		</div> -->
	</div>
	<div class="section">
		<div class="tableBox">
			<table class="form">
			<caption></caption>
			<colgroup>
				<col class="w120">
				<col>
				<col class="w120">
				<col>
			</colgroup>
			<tbody>
				<tr>
					<th scope="row">제목</th>
					<td colspan="3">설문조사 테스트</td>
				</tr>
				<tr>
					<th scope="row">안내문</th>
					<td colspan="3">&lt;p&gt;ㅇㄹㅇㄹㅇㄹㅇㄹ&lt;/p&gt;</td>
				</tr>
				<tr>
					<th scope="row">기간</th>
					<td>2016-05-23 09:00 ~ 2016-05-31 18:00</td>
					<th scope="row">담당부서</th>
					<td>감사팀</td>
				</tr>
				<tr>
					<th scope="row">담당자</th>
					<td>담당자</td>
					<th scope="row">담당자 연락처</th>
					<td>02-1234-1234</td>
				</tr>
				<tr>
					<th scope="row">참여자</th>
					<td>20명</td>
					<th scope="row">모집인원</th>
					<td>25명</td>
				</tr>
			</tbody>
			</table>
		</div>
		
		<div class="tableBox">
		<table class="list useBtn">
		<caption></caption>
		<colgroup>
			<col class="w50">
			<col class="w200">
			<col class="w200">
			<col>
		</colgroup>
		<thead>
			<tr>
				<th scope="col">순번</th>
				<th scope="col">질문내용</th>
				<th scope="col">답변목록</th>
				<th scope="col">선택결과</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>3</td>
				<td class="left">
					<b>설문조사 3번 문항</b><br />
					설문조사 3번 문항 설명
				</td>
				<td class="left">
					<ul>
						<li>1번 답변</li>
						<li>2번 답변</li>
						<li>3번 답변</li>
						<li>4번 답변</li>
					</ul>
				</td>
				<td class="left barUI">
					<ul>
						<li>
							<label>①</label>
							<span class="bar">
								<span class="curr" style="width:25%;"><em>25<span>%</span></em></span>
								<span class="left">5</span>
								<span class="right">20</span>
							</span>
						</li>
						<li>
							<label>②</label>
							<span class="bar">
								<span class="curr" style="width:50%;"><em>50<span>%</span></em></span>
								<span class="left">10</span>
								<span class="right">20</span>
							</span>
						</li>
						<li>
							<label>③</label>
							<span class="bar">
								<span class="curr" style="width:25%;"><em>25<span>%</span></em></span>
								<span class="left">5</span>
								<span class="right">20</span>
							</span>
						</li>
						<li>
							<label>④</label>
							<span class="bar">
								<span class="curr" style="width:0%;"><em>0<span>%</span></em></span>
								<span class="left">0</span>
								<span class="right">20</span>
							</span>
						</li>
						<li>
							<label>기타</label>
							<span class="bar">
								<span class="curr" style="width:0%;"><em>0<span>%</span></em></span>
								<span class="left">0</span>
								<span class="right">20</span>
							</span>
						</li>
					</ul>
				</td>
			</tr>
			<tr>
				<td>2</td>
				<td class="left">
					<b>설문조사 2번 문항</b><br />
					설문조사 2번 문항 설명
				</td>
				<td class="left">
					<ul>
						<li>1번 답변</li>
						<li class="active"><b>2번 답변</b></li>
						<li>3번 답변</li>
						<li>4번 답변</li>
					</ul>
				</td>
				<td class="left barUI">
					<ul>
						<li>
							<label>①</label>
							<span class="bar">
								<span class="curr" style="width:20%;"><em>20<span>%</span></em></span>
								<span class="left">4</span>
								<span class="right">20</span>
							</span>
						</li>
						<li>
							<label>②</label>
							<span class="bar">
								<span class="curr" style="width:50%;"><em>50<span>%</span></em></span>
								<span class="left">10</span>
								<span class="right">20</span>
							</span>
						</li>
						<li>
							<label>③</label>
							<span class="bar">
								<span class="curr" style="width:20%;"><em>20<span>%</span></em></span>
								<span class="left">4</span>
								<span class="right">20</span>
							</span>
						</li>
						<li>
							<label>④</label>
							<span class="bar">
								<span class="curr" style="width:0%;"><em>0<span>%</span></em></span>
								<span class="left">0</span>
								<span class="right">20</span>
							</span>
						</li>
						<li>
							<label>기타</label>
							<span class="bar">
								<span class="curr" style="width:0%;"><em>10<span>%</span></em></span>
								<span class="left">2</span>
								<span class="right">18</span>
							</span>
							<br />
							<div>
								- 기타답변2<br />
								- 기타답변1
							</div>
						</li>
					</ul>
				</td>
			</tr>
			<tr>
				<td>1</td>
				<td class="left">
					<b>설문조사 1번 문항</b><br />
					설문조사 1번 문항 설명
				</td>
				<td class="left">
					<ul>
						<li>1번 답변</li>
						<li class="active"><b>2번 답변</b></li>
						<li>3번 답변</li>
						<li>4번 답변</li>
					</ul>
				</td>
				<td class="left barUI">
					<ul>
						<li>
							<label>①</label>
							<span class="bar">
								<span class="curr" style="width:25%;"><em>25<span>%</span></em></span>
								<span class="left">5</span>
								<span class="right">20</span>
							</span>
						</li>
						<li>
							<label>②</label>
							<span class="bar">
								<span class="curr" style="width:50%;"><em>50<span>%</span></em></span>
								<span class="left">10</span>
								<span class="right">20</span>
							</span>
						</li>
						<li>
							<label>③</label>
							<span class="bar">
								<span class="curr" style="width:25%;"><em>25<span>%</span></em></span>
								<span class="left">5</span>
								<span class="right">20</span>
							</span>
						</li>
						<li>
							<label>④</label>
							<span class="bar">
								<span class="curr" style="width:0%;"><em>0<span>%</span></em></span>
								<span class="left">0</span>
								<span class="right">20</span>
							</span>
						</li>
						<li>
							<label>기타</label>
							<span class="bar">
								<span class="curr" style="width:0%;"><em>0<span>%</span></em></span>
								<span class="left">0</span>
								<span class="right">20</span>
							</span>
						</li>
					</ul>
				</td>
			</tr>
			</tbody>
			</table>
		</div>
		<div class="btnArea right">
			<a href="./manage_poll.jsp" class="btn_m">설문목록</a>
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