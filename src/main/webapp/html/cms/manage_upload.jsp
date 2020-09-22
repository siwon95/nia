<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:include page="/html/cms/layout/header.jsp"></jsp:include>

<!-- 페이지 전용 스타일/스크립트 -->
<script type="text/javascript">
$(function(){ 
	//버튼이벤트
	$(".btn_add").click(function(e){
		e.preventDefault();
		var modal_id = "modal_add";
		var modal_url = "./manage_upload_form.jsp";
		var modal_param = {};;
		var modal_class = "small"; //wide, small
		ajaxModal(modal_id, modal_url, modal_param, modal_class);
	});
	
	//버튼이벤트
	$(".btn_urlCopy").click(function(e){
		e.preventDefault();
		//URL복사하기
	});
	
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
		<h2>파일업로드관리</h2>
		<!-- <div class="listType">
			<a href="#" class="btn_listTypeThumb">섬네일형</a>
			<a href="#" class="btn_listTypeList">리스트형</a>
		</div> -->
	</div>
	<div class="section">
		<div class="search">
			<div class="right">
				<select id="" name="">
					<option value="1">파일명</option>
					<option value="2">변환된 파일명</option>
					<option value="3">파일ID</option>
					<option value="4">제목</option>
				</select>
				<input type="text" name="" class="w150"> 
				<a href="#" class="btn_inline">검색</a> 
			</div>
		</div>
			
		<div class="tableTitle">
			<div class="btnArea left">
				<a href="#" class="btn_inline">선택삭제</a>
			</div>
			<div class="btnArea">
				<a href="#" class="btn_inline btn_add">신규등록</a>
			</div>
		</div>
		<div class="tableBox">
			<table class="list">
			<caption>게시물 리스트</caption>
			<colgroup>
				<col class="w50">
				<col class="w50">
				<col>
				<col class="w80">
				<col class="w80">
				<col class="w140">
				<col class="w220">
			</colgroup>
			<thead>
				<tr>
					<th scope="col"><input type="checkbox" name="checkAll" value="1"></th>
					<th scope="col">순번</th>
					<th scope="col">이름</th>
					<th scope="col">크기</th>
					<th scope="col">파일형식</th>
					<th scope="col">수정된 날짜</th>
					<th scope="col">관리</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><input type="checkbox" name="checkItem[]" value="1"></td>
					<td>10</td>
					<td class="left"><img src="/images/cms/file_icon_excel.png" alt="excel"> 첨부파일 엑셀.xls</td>
					<td class="right">100.23MB</td>
					<td>xls 파일</td>
					<td>2019-05-05 15:30:22</td>
					<td>
						<a href="#" class="btn_inline" target="_blank" title="파일다운로드(새창열림)">다운로드</a>
						<a href="#" class="btn_inline btn_urlCopy">URL복사</a>
						<a href="#" class="btn_inline btn_caption useConfirm">삭제</a>
					</td>
				</tr>
				<tr>
					<td><input type="checkbox" name="checkItem[]" value="1"></td>
					<td>9</td>
					<td class="left"><img src="/images/cms/file_icon_media.png" alt="media"> 첨부파일 media.media</td>
					<td class="right">100.23MB</td>
					<td>media 파일</td>
					<td>2019-05-05 15:30:22</td>
					<td>
						<a href="#" class="btn_inline" target="_blank" title="파일다운로드(새창열림)">다운로드</a>
						<a href="#" class="btn_inline btn_urlCopy">URL복사</a>
						<a href="#" class="btn_inline btn_caption useConfirm">삭제</a>
					</td>
				</tr>
				<tr>
					<td><input type="checkbox" name="checkItem[]" value="1"></td>
					<td>8</td>
					<td class="left"><img src="/images/cms/file_icon_zip.png" alt="zip"> 첨부파일 zip.zip</td>
					<td class="right">100.23MB</td>
					<td>zip 파일</td>
					<td>2019-05-05 15:30:22</td>
					<td>
						<a href="#" class="btn_inline" target="_blank" title="파일다운로드(새창열림)">다운로드</a>
						<a href="#" class="btn_inline btn_urlCopy">URL복사</a>
						<a href="#" class="btn_inline btn_caption useConfirm">삭제</a>
					</td>
				</tr>
				<tr>
					<td><input type="checkbox" name="checkItem[]" value="1"></td>
					<td>7</td>
					<td class="left"><img src="/images/cms/file_icon_ppt.png" alt="ppt"> 첨부파일 파워포인트.ppt</td>
					<td class="right">321.23MB</td>
					<td>ppt 파일</td>
					<td>2019-05-05 15:30:22</td>
					<td>
						<a href="#" class="btn_inline" target="_blank" title="파일다운로드(새창열림)">다운로드</a>
						<a href="#" class="btn_inline btn_urlCopy">URL복사</a>
						<a href="#" class="btn_inline btn_caption useConfirm">삭제</a>
					</td>
				</tr>
				<tr>
					<td><input type="checkbox" name="checkItem[]" value="1"></td>
					<td>6</td>
					<td class="left"><img src="/images/cms/file_icon_pdf.png" alt="pdf"> 첨부파일 PDF.pdf</td>
					<td class="right">321.23MB</td>
					<td>pdf 파일</td>
					<td>2019-05-05 15:30:22</td>
					<td>
						<a href="#" class="btn_inline" target="_blank" title="파일다운로드(새창열림)">다운로드</a>
						<a href="#" class="btn_inline btn_urlCopy">URL복사</a>
						<a href="#" class="btn_inline btn_caption useConfirm">삭제</a>
					</td>
				</tr>
				<tr>
					<td><input type="checkbox" name="checkItem[]" value="1"></td>
					<td>5</td>
					<td class="left"><img src="/images/cms/file_icon_text.png" alt="text"> 첨부파일 text.text</td>
					<td class="right">100.23MB</td>
					<td>text 파일</td>
					<td>2019-05-05 15:30:22</td>
					<td>
						<a href="#" class="btn_inline" target="_blank" title="파일다운로드(새창열림)">다운로드</a>
						<a href="#" class="btn_inline btn_urlCopy">URL복사</a>
						<a href="#" class="btn_inline btn_caption useConfirm">삭제</a>
					</td>
				</tr>
				<tr>
					<td><input type="checkbox" name="checkItem[]" value="1"></td>
					<td>4</td>
					<td class="left"><img src="/images/cms/file_icon_img.png" alt="img"> 첨부파일 img.jpg</td>
					<td class="right">321.23MB</td>
					<td>jpg 파일</td>
					<td>2019-05-05 15:30:22</td>
					<td>
						<a href="#" class="btn_inline" target="_blank" title="파일다운로드(새창열림)">다운로드</a>
						<a href="#" class="btn_inline btn_urlCopy">URL복사</a>
						<a href="#" class="btn_inline btn_caption useConfirm">삭제</a>
					</td>
				</tr>
				<tr>
					<td><input type="checkbox" name="checkItem[]" value="1"></td>
					<td>3</td>
					<td class="left"><img src="/images/cms/file_icon_word.png" alt="word"> 첨부파일 word.word</td>
					<td class="right">321.23MB</td>
					<td>word 파일</td>
					<td>2019-05-05 15:30:22</td>
					<td>
						<a href="#" class="btn_inline" target="_blank" title="파일다운로드(새창열림)">다운로드</a>
						<a href="#" class="btn_inline btn_urlCopy">URL복사</a>
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
			파일업로드를 관리하는 메뉴입니다.<br />
			파일 검색/등록/다운로드/URL복사/삭제가 가능합니다
		</p>
		<h6>파일 검색</h6>
		<ul>
			<li>우측 상단 검색 폼을 통해서 파일을 검색합니다.</li>
		</ul>
		<h6>파일 등록</h6>
		<ul>
			<li>우측 상단의 [신규등록]버튼을 클릭합니다.</li>
			<li>팝업 내의 폼을 통해서 파일을 등록합니다.</li>
		</ul>
		<h6>파일 삭제</h6>
		<ul>
			<li>테이블 우측의 [삭제]버튼을 통해서 삭제합니다.</li>
		</ul>
		<h6>파일 다운로드</h6>
		<ul>
			<li>테이블 우측의 [다운로드]버튼을 클릭합니다.</li>
		</ul>
		<h6>파일 URL복사</h6>
		<ul>
			<li>테이블 우측의 [URL복사]버튼을 클릭합니다.</li>
		</ul>
	</div>
	<a href="#" class="btn_asideToggle">메뉴얼 열고닫기</a>
</div>
<!-- //메뉴얼영역 -->

<jsp:include page="/html/cms/layout/footer.jsp"></jsp:include>