<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:include page="/html/cms/layout/header.jsp"></jsp:include>

<!-- 페이지 전용 스타일/스크립트 -->
<script type="text/javascript">
$(function(){ 
	//버튼이벤트
	$(".btn_add").click(function(e){
		e.preventDefault();
		var modal_id = "modal_add";
		var modal_url = "./manage_poll_form.jsp";
		var modal_param = {};;
		var modal_class = "wide"; //wide, small
		ajaxModal(modal_id, modal_url, modal_param, modal_class);
	});
	
	//버튼이벤트
	$(".btn_mod").click(function(e){
		e.preventDefault();
		var modal_id = "modal_mod";
		var modal_url = "./manage_poll_form.jsp";
		var modal_param = {};;
		var modal_class = "wide"; //wide, small
		ajaxModal(modal_id, modal_url, modal_param, modal_class);
	});
	/*
	//버튼이벤트
	$(".btn_pollMember").click(function(e){
		e.preventDefault();
		var modal_id = "modal_member";
		var modal_url = "./manage_poll_member.jsp";
		var modal_param = {};;
		var modal_class = "wide"; //wide, small
		ajaxModal(modal_id, modal_url, modal_param, modal_class);
	});
	
	//버튼이벤트
	$(".btn_pollResult").click(function(e){
		e.preventDefault();
		var modal_id = "modal_result";
		var modal_url = "./system_staff_result.jsp";
		var modal_param = {};;
		var modal_class = ""; //wide, small
		ajaxModal(modal_id, modal_url, modal_param, modal_class);
	});
	
	//버튼이벤트
	$(".btn_pollQuestion").click(function(e){
		e.preventDefault();
		var modal_id = "modal_question";
		var modal_url = "./system_staff_question.jsp";
		var modal_param = {};;
		var modal_class = ""; //wide, small
		ajaxModal(modal_id, modal_url, modal_param, modal_class);
	});
	*/
	//전체선택
    $("input[name=checkAll]").click(function() {
		if($(this).is(":checked"))
			$("input[name^=checkItem]").prop("checked", true);
		else
			$("input[name^=checkItem]").prop("checked", false);
    });
});
</script>
<!-- //페이지 전용 스타일/스크립트 -->

<!-- 컨텐츠영역 -->
<div class="content">
	<div class="sectionTitle">
		<h2>설문조사</h2>
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
						<th scope="row"><label for="">관리부서</label></th>
						<td>
							<select id="" name="" class="w150">
								<option value="">사이트선택</option>
								<option value="" selected="selected">부서1</option>
								<option value="">부서2</option>
							</select>
						</td>
						<th scope="row"><label for="">상태선택</label></th>
						<td>
							<select id="" name="">
								<option value="" selected="selected">전체보기</option>
								<option value="Y">진행중</option>
								<option value="N">마감</option>
							</select>
						</td>
					</tr>
				</tbody>
			</table>
			<button class="btn_inline btn_search">검색</button>
		</div>
		
		<div class="tableTitle">
			<div class="btnArea left">
				<a href="#" class="btn_inline">선택수정</a>
				<a href="#" class="btn_inline">선택삭제</a>
			</div>
			<div class="btnArea">
				<a href="#" class="btn_inline btn_add">신규등록</a>
			</div>
		</div>
		<div class="tableBox">
			<table class="list useBtn">
			<caption>설문조사 목록</caption>
			<colgroup>
				<col class="w50">
				<col class="w50">
				<col>
				<col class="w100">
				<col class="w180">
				<col class="w100">
				<col class="w60">
				<col class="w170">
				<col class="w140">
			</colgroup>
			<thead>
				<tr>
					<th scope="col"><input type="checkbox" name="checkAll" value="1"></th>
					<th scope="col">순번</th>
					<th scope="col">설문제목</th>
					<th scope="col">담당부서</th>
					<th scope="col">설문기간</th>
					<th scope="col">상태</th>
					<th scope="col">참여수</th>
					<th scope="col">설문결과</th>
					<th scope="col">설문관리</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><input type="checkbox" name="checkItem[]" value="1"></td>
					<td>5</td>
					<td class="left">설문제목1</td>
					<td>조사팀</td>
					<td>2016-08-05 ~ 2016-08-31</td>
					<td>
						<select id="" name="" class="w100p">
							<option value="Y" selected="selected">진행중</option>
							<option value="N">마감</option>
						</select>
					</td>
					<td>5</td>
					<td>
						<a href="./manage_poll_member.jsp" class="btn_inline btn_pollMember">참여자목록</a>
						<a href="./manage_poll_result.jsp" class="btn_inline btn_pollResult">설문결과</a>
					</td>
					<td>
						<a href="./manage_poll_question.jsp" class="btn_inline btn_pollQuestion">질문관리</a>
						<a href="#" class="btn_inline btn_mod">수정</a>
					</td>
				</tr>
				<tr>
					<td><input type="checkbox" name="checkItem[]" value="1"></td>
					<td>4</td>
					<td class="left">설문제목1</td>
					<td>조사팀</td>
					<td>2016-08-05 ~ 2016-08-31</td>
					<td>
						<select id="" name="" class="w100p">
							<option value="Y" selected="selected">진행중</option>
							<option value="N">마감</option>
						</select>
					</td>
					<td>5</td>
					<td>
						<a href="./manage_poll_member.jsp" class="btn_inline btn_pollMember">참여자목록</a>
						<a href="./manage_poll_result.jsp" class="btn_inline btn_pollResult">설문결과</a>
					</td>
					<td>
						<a href="./manage_poll_question.jsp" class="btn_inline btn_pollQuestion">질문관리</a>
						<a href="#" class="btn_inline btn_mod">수정</a>
					</td>
				</tr>
				<tr>
					<td><input type="checkbox" name="checkItem[]" value="1"></td>
					<td>3</td>
					<td class="left">설문제목1</td>
					<td>조사팀</td>
					<td>2016-08-05 ~ 2016-08-31</td>
					<td>
						<select id="" name="" class="w100p">
							<option value="Y" selected="selected">진행중</option>
							<option value="N">마감</option>
						</select>
					</td>
					<td>5</td>
					<td>
						<a href="./manage_poll_member.jsp" class="btn_inline btn_pollMember">참여자목록</a>
						<a href="./manage_poll_result.jsp" class="btn_inline btn_pollResult">설문결과</a>
					</td>
					<td>
						<a href="./manage_poll_question.jsp" class="btn_inline btn_pollQuestion">질문관리</a>
						<a href="#" class="btn_inline btn_mod">수정</a>
					</td>
				</tr>
				<tr>
					<td><input type="checkbox" name="checkItem[]" value="1"></td>
					<td>2</td>
					<td class="left">설문제목1</td>
					<td>조사팀</td>
					<td>2016-08-05 ~ 2016-08-31</td>
					<td>
						<select id="" name="" class="w100p">
							<option value="Y" selected="selected">진행중</option>
							<option value="N">마감</option>
						</select>
					</td>
					<td>5</td>
					<td>
						<a href="./manage_poll_member.jsp" class="btn_inline btn_pollMember">참여자목록</a>
						<a href="./manage_poll_result.jsp" class="btn_inline btn_pollResult">설문결과</a>
					</td>
					<td>
						<a href="./manage_poll_question.jsp" class="btn_inline btn_pollQuestion">질문관리</a>
						<a href="#" class="btn_inline btn_mod">수정</a>
					</td>
				</tr>
				<tr>
					<td><input type="checkbox" name="checkItem[]" value="1"></td>
					<td>1</td>
					<td class="left">설문제목1</td>
					<td>조사팀</td>
					<td>2016-08-05 ~ 2016-08-31</td>
					<td>
						<select id="" name="" class="w100p">
							<option value="Y" selected="selected">진행중</option>
							<option value="N">마감</option>
						</select>
					</td>
					<td>5</td>
					<td>
						<a href="./manage_poll_member.jsp" class="btn_inline btn_pollMember">참여자목록</a>
						<a href="./manage_poll_result.jsp" class="btn_inline btn_pollResult">설문결과</a>
					</td>
					<td>
						<a href="./manage_poll_question.jsp" class="btn_inline btn_pollQuestion">질문관리</a>
						<a href="#" class="btn_inline btn_mod">수정</a>
					</td>
				</tr>
				<tr>
					<td colspan="9" class="empty">해당 자료가 없습니다.</td>
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
			설문조사를 관리하는 메뉴입니다.<br />
			설문조사 검색/등록/수정/삭제가 가능합니다
		</p>
		<h6>설문조사 검색</h6>
		<ul>
			<li>상단 검색폼을 통해서 검색할 수 있습니다.</li>
		</ul>
		<h6>설문조사 등록</h6>
		<ul>
			<li>우측 상단의 [신규등록]버튼을 클릭합니다.</li>
			<li>팝업 내의 폼을 통해서 정보를 입력합니다.</li>
		</ul>
		<h6>설문조사 수정</h6>
		<ul>
			<li>테이블 우측의 [수정]버튼을 클릭합니다.</li>
			<li>팝업 내의 폼을 통해서 정보를 수정합니다.</li>
		</ul>
		<h6>설문조사 삭제</h6>
		<ul>
			<li>테이블에서 삭제할 설문을 선택합니다.</li>
			<li>좌측 상단의 [선택삭제]버튼을 클릭합니다.</li>
		</ul>
	</div>
	<a href="#" class="btn_asideToggle">메뉴얼 열고닫기</a>
</div>
<!-- //메뉴얼영역 -->

<jsp:include page="/html/cms/layout/footer.jsp"></jsp:include>