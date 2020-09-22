<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:include page="/html/cms/layout/header.jsp"></jsp:include>

<!-- 페이지 전용 스타일/스크립트 -->
<link rel="stylesheet" type="text/css" href="/plugin/jstree//dist/themes/default/style.min.css" />
<script type="text/javascript" src="/plugin/jstree//dist/jstree.min.js"></script>
<script type="text/javascript">
$(function(){ 
	$('#menuTree').css("display","").jstree({
		"core" : {
			"check_callback" : true
		},
	    "plugins" : [ "dnd" ]
		 //  , "plugins" : [ "checkbox" ]
	}).bind("move_node.jstree",function(event,data){
		var instance = $('#menuTree').jstree(true);
		var pr = instance.get_node(data.node.parent);
		if(pr.id == "#" || (pr.id).replace("000000000000","") != (pr.id)){
			alert("최상단으로 이동 할수 없습니다!");
			document.location.reload();
			return;
		}
		if((data.node.id).replace("0000000000","") != (data.node.id)){
			alert("최상단으로 이동 할 수 없습니다!");
			document.location.reload();
			return;
		}
		
		var pr = instance.get_node(data.node.parent);
		document.myorder.preSortOrder.value = pr.id;
		document.myorder.moveOrderStep.value = data.position;
		if(data.position==0){
			document.myorder.moveSortOrder.value = "0";
		}else{
			document.myorder.moveSortOrder.value = pr.children[data.position-1];
		}
		document.myorder.nowSortOrder.value = data.node.id;
		
		var tempHtml = "<div id=\"html\" style=\"padding:5px;width:210px;border:1px solid #99BBE8\"><ul><li>재정렬 중입니다.<br /> 잠시만 기다려 주십시요!</li></ul></div>";
		document.getElementById("html").innerHTML=tempHtml;
		document.myorder.submit();
	});
	
	$("#menuTree").jstree("open_node","1000000000000");
	$("#menuTree").jstree("select_node","1000000000000");
});
</script>
<!-- //페이지 전용 스타일/스크립트 -->

<!-- 컨텐츠영역 -->
<div class="content">
	<div class="sectionTitle">
		<h2>부서관리</h2>
		<div class="titleNav">
			<ul>
				<li><a href="#">조직관리</a>
					<ul>
						<li><a href="#">콘텐츠관리</a></li>
						<li><a href="#">게시판관리</a></li>
						<li><a href="#">기타관리</a></li>
						<li><a href="#">일자리센터</a></li>
						<li><a href="#">운영관리</a></li>
					</ul>
				</li>
				<li><a href="#">부서 및 직원관리</a>
					<ul>
						<li><a href="#">개인정보변경</a></li>
						<li><a href="#">관리자관리</a></li>
					</ul>
				</li>
				<li class="last"><a href="#">부서관리</a>
					<ul>
						<li><a href="#">직원관리</a></li>
					</ul>
				</li>
			</ul>
		</div>
		<!-- <div class="listType">
			<a href="#" class="btn_listTypeThumb">섬네일형</a>
			<a href="#" class="btn_listTypeList">리스트형</a>
		</div> -->
	</div>
	<div class="section divGroup leftTree">
		<div class="left">
			<div class="treeTopBtn">
				<input type="button" value="하위부서추가" class="btn_inline btn_add">
			</div>

			<div id="menuTree">
				<ul>
					<li id="1000000000000"><span onclick="">HOME</span>
						<ul>
							<li id=""><span onclick="">총재</span>
								<ul>
									<li id=""><span onclick="">구청기획과</span>
										<ul>
											<li id=""><span onclick="">홍보기획팀</span></li>
										</ul>
									</li>
								</ul>
							</li>
							<li id=""><span onclick="">부총재</span></li>
							<li id=""><span onclick="">총재</span>
								<ul>
									<li id=""><span onclick="">지역협력실</span>
										<ul>
											<li id=""><span onclick="">지역협력조정팀</span></li>
											<li id=""><span onclick="">지역경제팀</span></li>
										</ul>
									</li>
									<li id=""><span onclick="">법규제실</span>
										<ul>
											<li id=""><span onclick="">동행정지원팀</span></li>
											<li id=""><span onclick="">주민자치팀</span></li>
											<li id=""><span onclick="">동복지지원팀</span></li>
											<li id=""><span onclick="">마을공동체팀</span></li>
										</ul>
									</li>
									<li id=""><span onclick="">금융통화위원회실</span>
										<ul>
											<li id=""><span onclick="">의사팀</span></li>
										</ul>
									</li>
									<li id=""><span onclick="">비서실</span>
										<ul>
											<li id=""><span onclick="">민원행정팀</span></li>
											<li id=""><span onclick="">가족관계등록팀</span></li>
											<li id=""><span onclick="">여권팀</span></li>
										</ul>
									</li>
								</ul>
							</li>
							<li id=""><span onclick="">부서1</span>
								<ul>
									<li id=""><span onclick="">부서1-1</span>
										<ul>
											<li id=""><span onclick="">부서1-1-1</span></li>
											<li id=""><span onclick="">부서1-1-2</span></li>
											<li id=""><span onclick="">부서1-1-3</span></li>
										</ul>
									</li>
									<li id=""><span onclick="">부서1-2</span>
										<ul>
											<li id=""><span onclick="">부서1-2-1</span></li>
											<li id=""><span onclick="">부서1-2-2</span></li>
											<li id=""><span onclick="">부서1-2-3</span></li>
										</ul>
									</li>
								</ul>
							</li>
							<li id=""><span onclick="">부서2</span>
								<ul>
									<li id=""><span onclick="">부서2-1</span>
										<ul>
											<li id=""><span onclick="">부서2-1-1</span></li>
											<li id=""><span onclick="">부서2-1-2</span></li>
											<li id=""><span onclick="">부서2-1-3</span></li>
										</ul>
									</li>
									<li id=""><span onclick="">부서2-2</span>
										<ul>
											<li id=""><span onclick="">부서2-2-1</span></li>
											<li id=""><span onclick="">부서2-2-2</span></li>
											<li id=""><span onclick="">부서2-2-3</span></li>
										</ul>
									</li>
								</ul>
							</li>
							<li id=""><span onclick="">부서3</span>
								<ul>
									<li id=""><span onclick="">부서3-1</span>
										<ul>
											<li id=""><span onclick="">부서3-1-1</span></li>
											<li id=""><span onclick="">부서3-1-2</span></li>
											<li id=""><span onclick="">부서3-1-3</span></li>
										</ul>
									</li>
									<li id=""><span onclick="">부서1-2</span>
										<ul>
											<li id=""><span onclick="">부서3-2-1</span></li>
											<li id=""><span onclick="">부서3-2-2</span></li>
											<li id=""><span onclick="">부서3-2-3</span></li>
										</ul>
									</li>
								</ul>
							</li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
		<div class="right">
			<div class="tableTitle">
				<div class="btnArea">
					<a href="./system_staff.jsp" class="btn_inline">직원보기</a>
					<a href="#" class="btn_inline">수정</a>
					<a href="#" class="btn_inline btn_caption useConfirm">삭제</a>
				</div>
			</div>
			<div class="tableBox">
				<table class="form">
				<caption></caption>
				<colgroup>
					<col class="w15p">
					<col>
					<col class="w15p">
					<col>
				</colgroup>
				<tbody>
					<tr>
						<th>부서경로</th>
						<td colspan="3">기획협력국 &gt; 지역협력실 &gt; 지역협력조정팀</td>
					</tr>
					<tr>
						<th><label for=""><span class="required">필수입력</span>부서명</label></th>
						<td colspan="3"><input type="text" id="" name="" value="" class="w100p"></td>
					</tr>
					<tr>
						<th><label for="">전화</label></th>
						<td><input type="text" id="" name="" value="" class="w100p"></td>
						<th><label for="">팩스</label></th>
						<td><input type="text" id="" name="" value="" class="w100p"></td>
					</tr>
					<tr>
						<th><label for="">영문이름</label></th>
						<td><input type="text" id="" name="" value="" class="w100p"></td>
						<th><label for="">중문이름</label></th>
						<td><input type="text" id="" name="" value="" class="w100p"></td>
					</tr>
					<tr>
						<th><label for="">위치정보</label></th>
						<td colspan="3"><input type="text" id="" name="" value="" class="w100p"></td>
					</tr>
					<tr>
						<th><label for="">주요업무</label></th>
						<td colspan="3"><textarea id="" name="" class="w100p h150"></textarea></td>
					</tr>
				</tbody>
				</table>
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