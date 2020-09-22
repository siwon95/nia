<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!-- 페이지 전용 스타일/스크립트 -->
<script src="/js/tui.editor/lib/jquery/dist/jquery.js"></script>
<script src="/js/tui.editor/lib/markdown-it/dist/markdown-it.js"></script>
<script src="/js/tui.editor/lib/to-mark/dist/to-mark.js"></script>
<script src="/js/tui.editor/lib/tui-code-snippet/dist/tui-code-snippet.js"></script>
<script src="/js/tui.editor/lib/codemirror/lib/codemirror.js"></script>
<script src="/js/tui.editor/lib/highlightjs/highlight.pack.js"></script>
<script src="/js/tui.editor/lib/squire-rte/build/squire-raw.js"></script>
<link rel="stylesheet" href="/js/tui.editor/lib/codemirror/lib/codemirror.css">
<link rel="stylesheet" href="/js/tui.editor/lib/highlightjs/styles/github.css">

<script src="/js/tui.editor/dist/tui-editor-Editor.js"></script>
<link rel="stylesheet" href="/js/tui.editor/dist/tui-editor.css">
<link rel="stylesheet" href="/js/tui.editor/dist/tui-editor-contents.css">
<script class="code-js">
$(function(){
	var editor = new tui.Editor({
		el: document.querySelector('#editSection'),
		initialEditType:'markdown',
		previewStyle:'vertical',
		height:'500px'
	});
	
	//버튼이벤트
	$(".btn_apply").click(function(e){
		e.preventDefault();
		$("#modal_editor").remove();
		$("#overlay").hide();
		
		var modal_id = "modal_apply";
		var modal_url = "./content_menu_apply.jsp";
		var modal_param = {};;
		var modal_class = "small"; //wide, small
		ajaxModal(modal_id, modal_url, modal_param, modal_class);
	});
});
</script>
<!-- //페이지 전용 스타일/스크립트 -->

<!-- ============================== 모달영역 ============================== -->
<div class="modalTitle">
	<h3>컨텐츠 등록</h3>
	<a href="#" class="btn_modalClose">모달팝업닫기</a>
</div>
<div class="modalContent">
	<div id="editSection"></div>
	<br />
	<div class="btnArea">
		<a href="#" class="btn_m on btn_apply">발행</a>
		<a href="#" class="btn_m btn_modalClose">취소</a>
	</div>
</div>
<!-- ============================== //모달영역 ============================== -->
