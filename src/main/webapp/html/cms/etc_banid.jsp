<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:include page="/html/cms/layout/header.jsp"></jsp:include>

<!-- 페이지 전용 스타일/스크립트 -->
<script type="text/javascript">
$(function(){
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
		<h2>금지아이디관리</h2>
		<!-- <div class="listType">
			<a href="#" class="btn_listTypeThumb">섬네일형</a>
			<a href="#" class="btn_listTypeList">리스트형</a>
		</div> -->
	</div>
	<div class="section">
		<div class="tableTitle">
			<div class="btnArea left">
				<a href="#" class="btn_inline">선택삭제</a>
				<a href="#" class="btn_inline">선택수정</a>
			</div>
		</div>
		<div class="tableBox">
			<table class="list useBtn">
			<caption>금지아이디 목록</caption>
			<colgroup>
				<col class="w50">
				<col>
				<col class="w90">
				<col class="w70">
			</colgroup>
			<thead>
				<tr>
					<th scope="col"><input type="checkbox" name="checkAll" value="1"></th>
					<th scope="col">아이디</th>
					<th scope="col">등록일</th>
					<th scope="col">관리</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>신규</td>
					<td><input type="text" id="" name="" value="" class="w100p"></td>
					<td>현재날짜</td>
					<td>
						<a href="#" class="btn_inline on">추가</a>
					</td>
				</tr>
				<tr>
					<td><input type="checkbox" name="checkItem[]" value="1"></td>
					<td><input type="text" id="" name="" value="injeinc" class="w100p"></td>
					<td>2019-05-12</td>
					<td>
						<a href="#" class="btn_inline btn_caption useConfirm">삭제</a>
					</td>
				</tr>
				<tr>
					<td><input type="checkbox" name="checkItem[]" value="1"></td>
					<td><input type="text" id="" name="" value="inje" class="w100p"></td>
					<td>2019-06-12</td>
					<td>
						<a href="#" class="btn_inline btn_caption useConfirm">삭제</a>
					</td>
				</tr>
				<tr>
					<td><input type="checkbox" name="checkItem[]" value="1"></td>
					<td><input type="text" id="" name="" value="admin" class="w100p"></td>
					<td>2019-07-12</td>
					<td>
						<a href="#" class="btn_inline btn_caption useConfirm">삭제</a>
					</td>
				</tr>
				<tr>
					<td><input type="checkbox" name="checkItem[]" value="1"></td>
					<td><input type="text" id="" name="" value="administrator" class="w100p"></td>
					<td>2019-08-12</td>
					<td>
						<a href="#" class="btn_inline btn_caption useConfirm">삭제</a>
					</td>
				</tr>
				<tr>
					<td><input type="checkbox" name="checkItem[]" value="1"></td>
					<td><input type="text" id="" name="" value="webmaster" class="w100p"></td>
					<td>2019-09-12</td>
					<td>
						<a href="#" class="btn_inline btn_caption useConfirm">삭제</a>
					</td>
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
			접속허용을 설정하는 메뉴입니다.<br />
			접속허용 등록/수정/삭제가 가능합니다
		</p>
		<h6>접속허용 등록</h6>
		<ul>
			<li>우측 상단의 [신규등록] 버튼을 클릭합니다.</li>
			<li>팝업 내의 폼을 통해서 정보를 입력합니다.</li>
		</ul>
		<h6>접속허용 수정</h6>
		<ul>
			<li>테이블 우측의 [수정]버튼을 클릭합니다.</li>
			<li>팝업 내의 폼을 통해서 정보를 수정합니다.</li>
		</ul>
		<h6>접속허용 삭제</h6>
		<ul>
			<li>테이블 우측의 [삭제]버튼을 통해서 삭제합니다.</li>
		</ul>
	</div>
	<a href="#" class="btn_asideToggle">메뉴얼 열고닫기</a>
</div>
<!-- //메뉴얼영역 -->

<jsp:include page="/html/cms/layout/footer.jsp"></jsp:include>