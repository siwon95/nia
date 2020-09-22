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
	
	//버튼이벤트
	$(".btn_add").click(function(e){
		e.preventDefault();
		var modal_id = "modal_add";
		var modal_url = "./system_staff_form.jsp";
		var modal_param = {};;
		var modal_class = ""; //wide, small
		ajaxModal(modal_id, modal_url, modal_param, modal_class);
	});
	
	//버튼이벤트
	$(".btn_mod").click(function(e){
		e.preventDefault();
		var modal_id = "modal_mod";
		var modal_url = "./system_staff_form.jsp";
		var modal_param = {};;
		var modal_class = ""; //wide, small
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
		<h2>직원관리</h2>
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
		<div class="left noOption">
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
				<h5>기획협력국 &gt; 지역협력실 &gt; 지역협력조정팀</h5>
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
				<caption>직원 목록</caption>
				<colgroup>
					<col class="w50">
					<col class="w50">
					<col class="w60">
					<col class="w60">
					<col class="w100">
					<col class="w100">
					<col class="w100">
					<col>
					<col class="w40">
					<col class="w40">
					<col class="w70">
					<col class="w70">
				</colgroup>
				<thead>
					<tr>
						<th scope="col"><input type="checkbox" name="checkAll" value="1"></th>
						<th scope="col">순번</th>
						<th scope="col">이름</th>
						<th scope="col">직위</th>
						<th scope="col">메일</th>
						<th scope="col">전화번호</th>
						<th scope="col">휴대폰</th>
						<th scope="col">부서정보</th>
						<th scope="col">연동</th>
						<th scope="col">사용</th>
						<th scope="col">정렬</th>
						<th scope="col">관리</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><input type="checkbox" name="checkItem[]" value="1"></td>
						<td>1</td>
						<td>김영무</td>
						<td>팀장</td>
						<td>ymk6105</td>
						<td>02-2620-3057</td>
						<td>010-4703-3690</td>
						<td class="left">기획협력국 &gt; 지역협력실 &gt; 지역협력조정팀</td>
						<td><input type="checkbox" id="" name="" value="" checked></td>
						<td><input type="checkbox" id="" name="" value="" checked></td>
						<td>
							<a href="#" class="btn_ss btn_sortUp">▲</a>
							<a href="#" class="btn_ss btn_sortDown">▼</a>
						</td>
						<td><a href="#" class="btn_inline btn_mod">수정</a></td>
					</tr>
					<tr>
						<td><input type="checkbox" name="checkItem[]" value="1"></td>
						<td>10</td>
						<td>오학상</td>
						<td></td>
						<td>ohs0707</td>
						<td>02-2620-3796</td>
						<td>010-4311-1010</td>
						<td class="left">기획협력국 &gt; 지역협력실 &gt; 지역협력조정팀</td>
						<td><input type="checkbox" id="" name="" value="" checked></td>
						<td><input type="checkbox" id="" name="" value="" checked></td>
						<td>
							<a href="#" class="btn_ss btn_sortUp">▲</a>
							<a href="#" class="btn_ss btn_sortDown">▼</a>
						</td>
						<td><a href="#" class="btn_inline btn_mod">수정</a></td>
					</tr>
					<tr>
						<td><input type="checkbox" name="checkItem[]" value="1"></td>
						<td>11</td>
						<td>최수석</td>
						<td></td>
						<td>16479140</td>
						<td>02-2620-4699</td>
						<td>010-7345-6006</td>
						<td class="left">기획협력국 &gt; 지역협력실 &gt; 지역협력조정팀</td>
						<td><input type="checkbox" id="" name="" value="" checked></td>
						<td><input type="checkbox" id="" name="" value="" checked></td>
						<td>
							<a href="#" class="btn_ss btn_sortUp">▲</a>
							<a href="#" class="btn_ss btn_sortDown">▼</a>
						</td>
						<td><a href="#" class="btn_inline btn_mod">수정</a></td>
					</tr>
					<tr>
						<td><input type="checkbox" name="checkItem[]" value="1"></td>
						<td>12</td>
						<td>김영기</td>
						<td></td>
						<td>sodrkf444</td>
						<td>02-2620-3797</td>
						<td>010-2779-2916</td>
						<td class="left">기획협력국 &gt; 지역협력실 &gt; 지역협력조정팀</td>
						<td><input type="checkbox" id="" name="" value="" checked></td>
						<td><input type="checkbox" id="" name="" value="" checked></td>
						<td>
							<a href="#" class="btn_ss btn_sortUp">▲</a>
							<a href="#" class="btn_ss btn_sortDown">▼</a>
						</td>
						<td><a href="#" class="btn_inline btn_mod">수정</a></td>
					</tr>
					<tr>
						<td><input type="checkbox" name="checkItem[]" value="1"></td>
						<td>13</td>
						<td>박생만</td>
						<td></td>
						<td>park6959</td>
						<td>02-2620-3792</td>
						<td>010-4907-6959</td>
						<td class="left">기획협력국 &gt; 지역협력실 &gt; 지역협력조정팀</td>
						<td><input type="checkbox" id="" name="" value="" checked></td>
						<td><input type="checkbox" id="" name="" value="" checked></td>
						<td>
							<a href="#" class="btn_ss btn_sortUp">▲</a>
							<a href="#" class="btn_ss btn_sortDown">▼</a>
						</td>
						<td><a href="#" class="btn_inline btn_mod">수정</a></td>
					</tr>
					<tr>
						<td><input type="checkbox" name="checkItem[]" value="1"></td>
						<td>14</td>
						<td>김남진</td>
						<td></td>
						<td>knj38127</td>
						<td>02-2620-3800</td>
						<td>010-7648-1188</td>
						<td class="left">기획협력국 &gt; 지역협력실 &gt; 지역협력조정팀</td>
						<td><input type="checkbox" id="" name="" value="" checked></td>
						<td><input type="checkbox" id="" name="" value="" checked></td>
						<td>
							<a href="#" class="btn_ss btn_sortUp">▲</a>
							<a href="#" class="btn_ss btn_sortDown">▼</a>
						</td>
						<td><a href="#" class="btn_inline btn_mod">수정</a></td>
					</tr>
					<tr>
						<td><input type="checkbox" name="checkItem[]" value="1"></td>
						<td>15</td>
						<td>유창수</td>
						<td></td>
						<td>15335100</td>
						<td>02-2620-3785</td>
						<td>010-7144-8291</td>
						<td class="left">기획협력국 &gt; 지역협력실 &gt; 지역협력조정팀</td>
						<td><input type="checkbox" id="" name="" value="" checked></td>
						<td><input type="checkbox" id="" name="" value="" checked></td>
						<td>
							<a href="#" class="btn_ss btn_sortUp">▲</a>
							<a href="#" class="btn_ss btn_sortDown">▼</a>
						</td>
						<td><a href="#" class="btn_inline btn_mod">수정</a></td>
					</tr>
					<tr>
						<td><input type="checkbox" name="checkItem[]" value="1"></td>
						<td>16</td>
						<td>박창수</td>
						<td></td>
						<td>pcs2233</td>
						<td>02-2620-3789</td>
						<td>010-9030-2233</td>
						<td class="left">기획협력국 &gt; 지역협력실 &gt; 지역협력조정팀</td>
						<td><input type="checkbox" id="" name="" value="" checked></td>
						<td><input type="checkbox" id="" name="" value="" checked></td>
						<td>
							<a href="#" class="btn_ss btn_sortUp">▲</a>
							<a href="#" class="btn_ss btn_sortDown">▼</a>
						</td>
						<td><a href="#" class="btn_inline btn_mod">수정</a></td>
					</tr>
					<tr>
						<td><input type="checkbox" name="checkItem[]" value="1"></td>
						<td>17</td>
						<td>김종두</td>
						<td></td>
						<td>kimdou55</td>
						<td>02-2620-3798</td>
						<td>010-4310-6666</td>
						<td class="left">기획협력국 &gt; 지역협력실 &gt; 지역협력조정팀</td>
						<td><input type="checkbox" id="" name="" value="" checked></td>
						<td><input type="checkbox" id="" name="" value="" checked></td>
						<td>
							<a href="#" class="btn_ss btn_sortUp">▲</a>
							<a href="#" class="btn_ss btn_sortDown">▼</a>
						</td>
						<td><a href="#" class="btn_inline btn_mod">수정</a></td>
					</tr>
					<tr>
						<td><input type="checkbox" name="checkItem[]" value="1"></td>
						<td>18</td>
						<td>손계석</td>
						<td></td>
						<td>dptmgud</td>
						<td>02-2620-3786</td>
						<td>010-7139-0119</td>
						<td class="left">기획협력국 &gt; 지역협력실 &gt; 지역협력조정팀</td>
						<td><input type="checkbox" id="" name="" value="" checked></td>
						<td><input type="checkbox" id="" name="" value="" checked></td>
						<td>
							<a href="#" class="btn_ss btn_sortUp">▲</a>
							<a href="#" class="btn_ss btn_sortDown">▼</a>
						</td>
						<td><a href="#" class="btn_inline btn_mod">수정</a></td>
					</tr>
					<tr>
						<td><input type="checkbox" name="checkItem[]" value="1"></td>
						<td>19</td>
						<td>김연숙</td>
						<td></td>
						<td>sook600</td>
						<td>02-2620-3806</td>
						<td>010-9092-3800</td>
						<td class="left">기획협력국 &gt; 지역협력실 &gt; 지역협력조정팀</td>
						<td><input type="checkbox" id="" name="" value="" checked></td>
						<td><input type="checkbox" id="" name="" value="" checked></td>
						<td>
							<a href="#" class="btn_ss btn_sortUp">▲</a>
							<a href="#" class="btn_ss btn_sortDown">▼</a>
						</td>
						<td><a href="#" class="btn_inline btn_mod">수정</a></td>
					</tr>
					<tr>
						<td><input type="checkbox" name="checkItem[]" value="1"></td>
						<td>2</td>
						<td>김봉주</td>
						<td></td>
						<td>16553170</td>
						<td>02-2620-3058</td>
						<td>010-9914-7667</td>
						<td class="left">기획협력국 &gt; 지역협력실 &gt; 지역협력조정팀</td>
						<td><input type="checkbox" id="" name="" value="" checked></td>
						<td><input type="checkbox" id="" name="" value="" checked></td>
						<td>
							<a href="#" class="btn_ss btn_sortUp">▲</a>
							<a href="#" class="btn_ss btn_sortDown">▼</a>
						</td>
						<td><a href="#" class="btn_inline btn_mod">수정</a></td>
					</tr>
					<tr>
						<td><input type="checkbox" name="checkItem[]" value="1"></td>
						<td>20</td>
						<td>성지창</td>
						<td></td>
						<td>sjcgogo</td>
						<td>02-2620-3799</td>
						<td>010-6251-8658</td>
						<td class="left">기획협력국 &gt; 지역협력실 &gt; 지역협력조정팀</td>
						<td><input type="checkbox" id="" name="" value="" checked></td>
						<td><input type="checkbox" id="" name="" value="" checked></td>
						<td>
							<a href="#" class="btn_ss btn_sortUp">▲</a>
							<a href="#" class="btn_ss btn_sortDown">▼</a>
						</td>
						<td><a href="#" class="btn_inline btn_mod">수정</a></td>
					</tr>
					<tr>
						<td><input type="checkbox" name="checkItem[]" value="1"></td>
						<td>21</td>
						<td>김재선</td>
						<td></td>
						<td>kj8416</td>
						<td>02-2620-3781</td>
						<td>010-7323-5879</td>
						<td class="left">기획협력국 &gt; 지역협력실 &gt; 지역협력조정팀</td>
						<td><input type="checkbox" id="" name="" value="" checked></td>
						<td><input type="checkbox" id="" name="" value="" checked></td>
						<td>
							<a href="#" class="btn_ss btn_sortUp">▲</a>
							<a href="#" class="btn_ss btn_sortDown">▼</a>
						</td>
						<td><a href="#" class="btn_inline btn_mod">수정</a></td>
					</tr>
					<tr>
						<td><input type="checkbox" name="checkItem[]" value="1"></td>
						<td>22</td>
						<td>김강준</td>
						<td></td>
						<td>kj3136</td>
						<td>02-2620-3000</td>
						<td>010-5493-8935</td>
						<td class="left">기획협력국 &gt; 지역협력실 &gt; 지역협력조정팀</td>
						<td><input type="checkbox" id="" name="" value="" checked></td>
						<td><input type="checkbox" id="" name="" value="" checked></td>
						<td>
							<a href="#" class="btn_ss btn_sortUp">▲</a>
							<a href="#" class="btn_ss btn_sortDown">▼</a>
						</td>
						<td><a href="#" class="btn_inline btn_mod">수정</a></td>
					</tr>
					<tr>
						<td colspan="12" class="empty">해당 자료가 없습니다.</td>
					</tr>
				</tbody>
				</table>
			</div>
			
			<div class="paging">
				<a href="#" class="first">처음페이지</a>
				<a href="#" class="prev">이전페이지</a>
				<a href="#" class="active">1</a>
				<a href="#">2</a>
				<a href="#">3</a>
				<a href="#">4</a>
				<a href="#">5</a>
				<a href="#" class="next">다음페이지</a>
				<a href="#" class="last">마지막페이지</a>
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
			직원정보를 관리하는 메뉴입니다.<br />
			직원정보 등록/수정/삭제/정렬이 가능합니다
		</p>
		<h6>직원 등록</h6>
		<ul>
			<li>우측 상단의 [신규등록] 버튼을 클릭합니다.</li>
			<li>팝업 내의 폼을 통해서 정보를 입력합니다.</li>
			<li>하단의 [확인]버튼을 통해 저장합니다.</li>
		</ul>
		<h6>직원 수정</h6>
		<ul>
			<li>테이블 우측에 [수정]버튼을 클릭합니다.</li>
			<li>팝업 내의 폼을 통해서 정보를 수정합니다.</li>
			<li>하단의 [수정]버튼을 통해 저장합니다.</li>
		</ul>
		<h6>직원 삭제</h6>
		<ul>
			<li>테이블에서 삭제할 직원을 선택합니다.</li>
			<li>좌측 상단의 [선택삭제]버튼을 통해서 삭제합니다.</li>
		</ul>
		<h6>직원 정렬</h6>
		<ul>
			<li>테이블 우측 화살표 버튼을 통해 위치를 이동합니다.</li>
		</ul>
		<h6>직원 연동</h6>
		<ul>
			<li>테이블 우측 체크박스 버튼을 통해 연동설정을 합니다.</li>
		</ul>
		<h6>직원 사용/미사용</h6>
		<ul>
			<li>테이블 우측 체크박스 버튼을 통해 사용설정을 합니다.</li>
		</ul>
	</div>
	<a href="#" class="btn_asideToggle">메뉴얼 열고닫기</a>
</div>
<!-- //메뉴얼영역 -->

<jsp:include page="/html/cms/layout/footer.jsp"></jsp:include>