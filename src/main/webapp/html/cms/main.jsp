<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:include page="/html/cms/layout/header.jsp"></jsp:include>
<!-- 컨텐츠영역 -->
<div class="content">
	<div class="sectionTitle">
		<h2>대쉬보드</h2>
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
				<col class="w15p">
				<col>
			</colgroup>
			<tbody>
				<tr>
					<th scope="row">토글버튼</th>
					<td><input type="checkbox" id="checkbox1" name="" class="useToggle"><label for="checkbox1">사용</label></td>
				</tr>
				<tr>
					<th scope="row">컨펌필수버튼</th>
					<td><a href="#" class="btn_inline btn_caption useConfirm">삭제</a></td>
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
			부서를 관리하는 메뉴입니다.<br />
			부서 추가/수정/삭제가 가능합니다
		</p>
		<h6>부서 등록</h6>
		<ul>
			<li>[하위부서추가] 버튼을 클릭하면 선택하신 부서 하위에 부서가 추가됩니다.</li>
		</ul>
		<h6>부서 수정</h6>
		<ul>
			<li>트리에서 수정할 부서를 클릭합니다.</li>
			<li>우측 폼을 통해서 정보를 수정한 후 우측상단의 [수정]버튼을 통해서 저장합니다.</li>
		</ul>
		<h6>부서 삭제</h6>
		<ul>
			<li>트리에서 삭제할 부서를 클릭합니다.</li>
			<li>우측 상단의 [삭제]버튼을 통해서 삭제합니다.</li>
		</ul>
	</div>
	<a href="#" class="btn_asideToggle">메뉴얼 열고닫기</a>
</div>
<!-- //메뉴얼영역 -->
<jsp:include page="/html/cms/layout/footer.jsp"></jsp:include>