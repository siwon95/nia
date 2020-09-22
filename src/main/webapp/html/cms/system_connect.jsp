<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:include page="/html/cms/layout/header.jsp"></jsp:include>

<!-- 페이지 전용 스타일/스크립트 -->
<script type="text/javascript">
$(function(){ 
	//버튼이벤트
	$(".btn_add").click(function(e){
		e.preventDefault();
		var modal_id = "modal_add";
		var modal_url = "./system_connect_form.jsp";
		var modal_param = {};;
		var modal_class = "small"; //wide, small
		ajaxModal(modal_id, modal_url, modal_param, modal_class);
	});
	
	//버튼이벤트
	$(".btn_mod").click(function(e){
		e.preventDefault();
		var modal_id = "modal_mod";
		var modal_url = "./system_connect_form.jsp";
		var modal_param = {};;
		var modal_class = "small"; //wide, small
		ajaxModal(modal_id, modal_url, modal_param, modal_class);
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
		<h2>접속허용설정</h2>
		<!-- <div class="listType">
			<a href="#" class="btn_listTypeThumb">섬네일형</a>
			<a href="#" class="btn_listTypeList">리스트형</a>
		</div> -->
	</div>
	<div class="section">
		<div class="tableTitle">
			<div class="btnArea left">
				<a href="#" class="btn_inline">선택삭제</a>
			</div>
			<div class="btnArea">
				<a href="#" class="btn_inline btn_add">신규등록</a>
			</div>
		</div>
		<div class="tableBox">
			<table class="list useBtn">
			<caption>접속허용 목록</caption>
			<colgroup>
				<col class="w50">
				<col class="w50">
				<col>
				<col class="w150">
				<col class="w120">
			</colgroup>
			<thead>
				<tr>
					<th scope="col"><input type="checkbox" name="checkAll" value="1"></th>
					<th scope="col">순번</th>
					<th scope="col">접속허용 대역</th>
					<th scope="col">등록일</th>
					<th scope="col">관리</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><input type="checkbox" name="checkItem[]" value="1"></td>
					<td>9</td>
					<td class="left">200.4.2.*</td>
					<td>2017-05-17 12:11:47</td>
					<td>
						<a href="javascript:doAccessIpFormAx('AI00000017');" class="btn_inline btn_mod">수정</a>
						<a href="javascript:doAccessIpRmvAx('AI00000017');" class="btn_inline btn_caption useConfirm">삭제</a>
					</td>
				</tr>
				<tr>
					<td><input type="checkbox" name="checkItem[]" value="1"></td>
					<td>8</td>
					<td class="left">200.4.2.*</td>
					<td>2016-11-03 15:48:41</td>
					<td>
						<a href="javascript:doAccessIpFormAx('AI00000016');" class="btn_inline btn_mod">수정</a>
						<a href="javascript:doAccessIpRmvAx('AI00000016');" class="btn_inline btn_caption useConfirm">삭제</a>
					</td>
				</tr>
				<tr>
					<td><input type="checkbox" name="checkItem[]" value="1"></td>
					<td>7</td>
					<td class="left">98.15.12.110 ~ 98.15.12.110</td>
					<td>2016-09-07 16:28:19</td>
					<td>
						<a href="javascript:doAccessIpFormAx('AI00000015');" class="btn_inline btn_mod">수정</a>
						<a href="javascript:doAccessIpRmvAx('AI00000015');" class="btn_inline btn_caption useConfirm">삭제</a>
					</td>
				</tr>
				<tr>
					<td><input type="checkbox" name="checkItem[]" value="1"></td>
					<td>6</td>
					<td class="left">200.4.10.*</td>
					<td>2016-07-12 09:24:55</td>
					<td>
						<a href="javascript:doAccessIpFormAx('AI00000012');" class="btn_inline btn_mod">수정</a>
						<a href="javascript:doAccessIpRmvAx('AI00000012');" class="btn_inline btn_caption useConfirm">삭제</a>
					</td>
				</tr>
				<tr>
					<td><input type="checkbox" name="checkItem[]" value="1"></td>
					<td>5</td>
					<td class="left">180.182.20.150 ~ 180.182.20.150</td>
					<td>2016-07-12 09:23:56</td>
					<td>
						<a href="javascript:doAccessIpFormAx('AI00000011');" class="btn_inline btn_mod">수정</a>
						<a href="javascript:doAccessIpRmvAx('AI00000011');" class="btn_inline btn_caption useConfirm">삭제</a>
					</td>
				</tr>
				<tr>
					<td><input type="checkbox" name="checkItem[]" value="1"></td>
					<td>4</td>
					<td class="left">98.15.14.172 ~ 98.15.14.176</td>
					<td>2016-06-24 18:03:46</td>
					<td>
						<a href="javascript:doAccessIpFormAx('AI00000007');" class="btn_inline btn_mod">수정</a>
						<a href="javascript:doAccessIpRmvAx('AI00000007');" class="btn_inline btn_caption useConfirm">삭제</a>
					</td>
				</tr>
				<tr>
					<td><input type="checkbox" name="checkItem[]" value="1"></td>
					<td>3</td>
					<td class="left">98.15.14.172 ~ 98.15.14.176</td>
					<td>2016-06-24 17:36:44</td>
					<td>
						<a href="javascript:doAccessIpFormAx('AI00000004');" class="btn_inline btn_mod">수정</a>
						<a href="javascript:doAccessIpRmvAx('AI00000004');" class="btn_inline btn_caption useConfirm">삭제</a>
					</td>
				</tr>
				<tr>
					<td><input type="checkbox" name="checkItem[]" value="1"></td>
					<td>2</td>
					<td class="left">127.0.0.*</td>
					<td>2016-06-03 15:20:47</td>
					<td>
						<a href="javascript:doAccessIpFormAx('SAMPLE-00245');" class="btn_inline btn_mod">수정</a>
						<a href="javascript:doAccessIpRmvAx('SAMPLE-00245');" class="btn_inline btn_caption useConfirm">삭제</a>
					</td>
				</tr>
				<tr>
					<td><input type="checkbox" name="checkItem[]" value="1"></td>
					<td>1</td>
					<td class="left">200.4.*.*</td>
					<td>2016-06-03 15:08:58</td>
					<td>
						<a href="javascript:doAccessIpFormAx('SAMPLE-00235');" class="btn_inline btn_mod">수정</a>
						<a href="javascript:doAccessIpRmvAx('SAMPLE-00235');" class="btn_inline btn_caption useConfirm">삭제</a>
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