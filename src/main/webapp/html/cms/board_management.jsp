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
			alert("TOP 메뉴로 이동 할수 없습니다!");
			document.location.reload();
			return;
		}
		if((data.node.id).replace("0000000000","") != (data.node.id)){
			alert("TOP 메뉴는 이동 할 수 없습니다!");
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
		
		var tempHtml = "";
		tempHtml += "<div id=\"html\" style=\"padding:5px;width:210px;border:1px solid #99BBE8\">";
		tempHtml += "<ul>";
		tempHtml += "	<li>";
		tempHtml += "	메뉴 재정렬 중입니다.<br/> 잠시만 기다려 주십시요!";
		tempHtml += "	</li>";
		tempHtml += "</ul>";				
		tempHtml += "</div>";

		document.getElementById("html").innerHTML=tempHtml;
		document.myorder.submit();
		
	});
	$("#menuTree").jstree("open_node","1000000000000");
	$("#menuTree").jstree("select_node","1000000000000");
	
});
function doConfigPropertyAdd(obj) {
	var clone = $("input[name='labelOrdNoArr']").eq(0).parent().parent().clone();
	$(obj).parent().parent().before(clone);
	clone.find("input[type='text']").val("");
	clone.find("select").val("");
	clone.find("input[type='checkbox']").prop("checked", false);
}
function doConfigPropertyRmv(obj) {
	$(obj).parent().parent().remove();
}
</script>
<!-- //페이지 전용 스타일/스크립트 -->

<!-- 컨텐츠영역 -->
<div class="content">
	<div class="sectionTitle">
		<h2>게시판 관리</h2>
		<!-- <div class="listType">
			<a href="#" class="btn_listTypeThumb">섬네일형</a>
			<a href="#" class="btn_listTypeList">리스트형</a>
		</div> -->
	</div>
	<div class="section divGroup leftTree">
		<div class="left">
			<div class="siteSelect">
				<form id="MenuVoSearch" name="" method="post">
				<input id="menuCode" name="" type="hidden" value="">
				<select id="siteCd" name="" title="사이트선택" style="width:100%">
					<option value="">사이트선택</option>
					<option value="" selected="selected">대표홈페이지</option><option value="">데모사이트</option>
				</select>
				</form>
			</div>
			<div id="menuTree">
<ul> <li id="1000000000000" ><span onclick="siteGo('yangcheon','10000000000000000000000','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10000000000000000000000');">HOME</span> <ul> <li id="1010000000000" ><span onclick="siteGo('yangcheon','10100000000002016081013','');">통합민원</span> <ul> <li id="1010100000000" ><span onclick="siteGo('yangcheon','10101000000002016081013','');">종합민원안내</span><ul> <li id="1010101000000" ><span onclick="siteGo('yangcheon','10101010000002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=268');">민원업무안내</span></li> <li id="1010103000000" ><span onclick="siteGo('yangcheon','10101030000002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10101030000002016081013');">민원수수료</span></li> <li id="1010104000000" ><span onclick="siteGo('yangcheon','10101040000002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10101040000002016081013');">무인민원발급기안내</span></li> <li id="1010105000000" ><span onclick="siteGo('yangcheon','10101050000002016081013','');">행정서비스헌장</span>												<ul> <li id="1010105010000" ><span onclick="siteGo('yangcheon','10101050100002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10101050100002016081013');">행정서비스헌장</span></li> <li id="1010105020000" ><span onclick="siteGo('yangcheon','10101050200002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10101050200002016081013');">헌장내용</span></li> <li id="1010105030000" ><span onclick="siteGo('yangcheon','10101050300002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10101050300002016081013');">관련사이트</span></li> <li id="1010105040000" ><span onclick="siteGo('yangcheon','10101050400002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10101050400002016081013');">구민고객권리</span></li> </ul> </li> <li id="1010106000000" ><span onclick="siteGo('yangcheon','10101060000002016081013','');">민원행정추진계획</span> <ul> <li id="1010106010000" ><span onclick="siteGo('yangcheon','10101060100002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10101060100002016081013');">중장기민원행정추진종합계획</span></li> <li id="1010106020000" ><span onclick="siteGo('yangcheon','10101060200002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10101060200002016081013');">민원행정 및 제도개선 추진계획</span></li>								</ul> </li> <li id="1010107000000" ><span onclick="siteGo('yangcheon','10101070000002016081013','');">민원서비스제도</span> <ul> <li id="1010107010000" ><span onclick="siteGo('yangcheon','10101070100002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10101070100002016081013');">구술,전화민원사무 처리</span></li> <li id="1010107020000" ><span onclick="siteGo('yangcheon','10101070200002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10101070200002016081013');">민원후견인인제</span></li> <li id="1010107030000" ><span onclick="siteGo('yangcheon','10101070300002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10101070300002016081013');">행정정보공동이용</span></li> </ul> </li>								</ul> </li> <li id="1010200000000" ><span onclick="siteGo('yangcheon','10102000000002016081013','');">분야별민원안내</span>						<ul> <li id="1010201000000" ><span onclick="siteGo('yangcheon','10102010000002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=279');">청소</span></li> <li id="1010202000000" ><span onclick="siteGo('yangcheon','10102020000002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=280');">자동차</span></li> <li id="1010203000000" ><span onclick="siteGo('yangcheon','10102030000002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=281');">환경</span></li> <li id="1010204000000" ><span onclick="siteGo('yangcheon','10102040000002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=282');">주택</span></li> <li id="1010205000000" ><span onclick="siteGo('yangcheon','10102050000002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=283');">재건축</span></li> <li id="1010206000000" ><span onclick="siteGo('yangcheon','10102060000002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=284');">민방위</span></li> <li id="1010207000000" ><span onclick="siteGo('yangcheon','10102070000002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=285');">여권</span></li> <li id="1010208000000" ><span onclick="siteGo('yangcheon','10102080000002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=286');">가족관계등록</span></li> <li id="1010209000000" ><span onclick="siteGo('yangcheon','10102090000002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10102090000002016081013');">지방세인터넷납부</span></li>								</ul></li> <li id="1010300000000" ><span onclick="siteGo('yangcheon','10103000000002016081013','');">민원신청</span> <ul> <li id="1010301000000" ><span onclick="siteGo('yangcheon','10103010000002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10103010000002016081013');">민원24</span> <ul> <li id="1010301010000" ><span onclick="siteGo('yangcheon','10103010100002018060510','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10103010100002018060510');">추가메뉴</span></li>								</ul></li> <li id="1010302000000" ><span onclick="siteGo('yangcheon','10103020000002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10103020000002016081013');">인터넷발급/신청</span>						</li>								</ul></li> <li id="1010400000000" ><span onclick="siteGo('yangcheon','10101020000002016081013','');">민원사무편람(민원서식)</span>						<ul> <li id="1010401000000" ><span onclick="siteGo('yangcheon','10101020100002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=269');">국문</span></li> <li id="1010402000000" ><span onclick="siteGo('yangcheon','10101020200002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=270');">영어</span></li> <li id="1010403000000" ><span onclick="siteGo('yangcheon','10101020300002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=271');">일본어</span></li> <li id="1010404000000" ><span onclick="siteGo('yangcheon','10101020400002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=272');">중국어</span></li> <li id="1010405000000" ><span onclick="siteGo('yangcheon','10101020500002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=273');">베트남어</span></li>								</ul></li> <li id="1010500000000" ><span onclick="siteGo('yangcheon','10104000000002016081013','');">민원상담</span> <ul> <li id="1010501000000" ><span onclick="siteGo('yangcheon','10104010000002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10104010000002016081013');">온라인민원상담</span></li> <li id="1010502000000" ><span onclick="siteGo('yangcheon','10104020000002016081013','');">법률무료상담</span>												<ul> <li id="1010502010000" ><span onclick="siteGo('yangcheon','10104020100002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10104020100002016081013');">무료법률상담안내</span></li> <li id="1010502020000" ><span onclick="siteGo('yangcheon','10104020200002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=276');">인터넷상담신청</span>												</li>								</ul></li> <li id="1010503000000" ><span onclick="siteGo('yangcheon','10104030000002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=277');">민원FAQ</span>						</li>								</ul></li> <li id="1010700000000" ><span onclick="siteGo('yangcheon','10105000000002016081013','');">주민신고센터</span> <ul> <li id="1010701000000" ><span onclick="siteGo('yangcheon','10105010000002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10105010000002016081013');">주민신고센터안내</span></li> <li id="1010702000000" ><span onclick="siteGo('yangcheon','10105020000002016081013','');">신고하기</span>												<ul> <li id="1010702010000" ><span onclick="siteGo('yangcheon','10105020100002016081013','');">재난위험신고</span></li> <li id="1010702020000" ><span onclick="siteGo('yangcheon','10105020200002016081013','');">환경신문고</span></li> <li id="1010702030000" ><span onclick="siteGo('yangcheon','10105020300002016081013','');">불합리규제신고</span></li> <li id="1010702040000" ><span onclick="siteGo('yangcheon','10105020400002016081013','');">공직자부조리신고</span></li> <li id="1010702050000" ><span onclick="siteGo('yangcheon','10105020500002016081013','');">하도급부조리신고</span></li>								</ul></li> <li id="1010703000000" ><span onclick="siteGo('yangcheon','10105030000002016081013','');">나의신고내역조회</span>						</li>								</ul></li>								</ul></li> <li id="1020000000000" ><span onclick="siteGo('yangcheon','10200000000002016081013','');">소통과참여</span> <ul> <li id="1020100000000" ><span onclick="siteGo('yangcheon','10201000000002016081013','');">양천소통광장</span> <ul> <li id="1020101000000" ><span onclick="siteGo('yangcheon','10201010000002016081013','');">양천소식</span><ul> <li id="1020101010000" ><span onclick="siteGo('yangcheon','10201010100002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=254');">공지사항</span></li> <li id="1020101020000" ><span onclick="siteGo('yangcheon','10201010200002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=256');">행사안내</span></li> <li id="1020101030000" ><span onclick="siteGo('yangcheon','10201010300002016081013','');">고시/공고</span></li> <li id="1020101040000" ><span onclick="siteGo('yangcheon','10201010400002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=258');">양천뉴스</span></li>								</ul></li> <li id="1020102000000" ><span onclick="siteGo('yangcheon','10201020000002016081013','');">언론보도</span>						<ul> <li id="1020102010000" ><span onclick="siteGo('yangcheon','10201020100002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=289');">언론보도</span></li> <li id="1020102020000" ><span onclick="siteGo('yangcheon','10201020200002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=290');">보도자료</span></li> <li id="1020102030000" ><span onclick="siteGo('yangcheon','10201020300002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=291');">그건 이렇습니다</span></li> <li id="1020102040000" ><span onclick="siteGo('yangcheon','10201020400002017050205','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10201020400002017050205');">저건저렇습니다</span></li>								</ul></li> <li id="1020103000000" ><span onclick="siteGo('yangcheon','10201030000002016081013','');">양천 SNS</span>						</li> <li id="1020104000000" ><span onclick="siteGo('yangcheon','10201040000002016081013','');">양천홍보관</span>												<ul> <li id="1020104010000" ><span onclick="siteGo('yangcheon','10201040100002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=266');">포토갤러리</span></li> <li id="1020104020000" ><span onclick="siteGo('yangcheon','10201040200002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10201040200002016081013');">인터넷방송국</span></li> <li id="1020104030000" ><span onclick="siteGo('yangcheon','10201040300002016081013','');">양천구소식지</span></li> <li id="1020104040000" ><span onclick="siteGo('yangcheon','10201040400002016081013','');">꿈나무소식지</span>												</li>								</ul></li> <li id="1020105000000" ><span onclick="siteGo('yangcheon','10201050000002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=278');">자유게시판</span>						</li> <li id="1020106000000" ><span onclick="siteGo('yangcheon','10201060000002016081013','');">설문조사</span>												<ul> <li id="1020106010000" ><span onclick="siteGo('yangcheon','10201060100002016081709','');">진행중인 설문</span></li> <li id="1020106020000" ><span onclick="siteGo('yangcheon','10201060200002016081709','');">완료된 설문</span></li>								</ul></li> <li id="1020107000000" ><span onclick="siteGo('yangcheon','10201070000002016081013','');">아이디어하우스</span> <ul> <li id="1020107010000" ><span onclick="siteGo('yangcheon','10201070100002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10201070100002016081013');">소개</span></li> <li id="1020107020000" ><span onclick="siteGo('yangcheon','10201070200002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10201070200002016081013');">이용안내</span></li> <li id="1020107030000" ><span onclick="siteGo('yangcheon','10201070300002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=295');">공지사항</span></li> <li id="1020107040000" ><span onclick="siteGo('yangcheon','10201070400002016081013','');">제안하기</span></li> <li id="1020107050000" ><span onclick="siteGo('yangcheon','10201070500002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=297');">제안심사결과</span></li> <li id="1020107060000" ><span onclick="siteGo('yangcheon','10201070600002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=298');">명예의전당</span></li> <li id="1020107070000" ><span onclick="siteGo('yangcheon','10201070700002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=299');">정책반영</span></li>								</ul></li> <li id="1020108000000" ><span onclick="siteGo('yangcheon','10201080000002016081013','');">칭찬PLAZA</span> <ul> <li id="1020108010000" ><span onclick="siteGo('yangcheon','10201080100002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10201080100002016081013');">목적 및 취지</span></li> <li id="1020108020000" ><span onclick="siteGo('yangcheon','10201080200002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=301');">이웃칭찬편지</span></li> <li id="1020108030000" ><span onclick="siteGo('yangcheon','10201080300002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=302');">직원칭찬편지</span></li>								</ul></li> <li id="1020109000000" ><span onclick="siteGo('yangcheon','10201090000002016100410','');">청소년구정평가단</span>						<ul> <li id="1020109010000" ><span onclick="siteGo('yangcheon','10201090100002016100410','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=561');">알림방</span></li> <li id="1020109020000" ><span onclick="siteGo('yangcheon','10201090200002016100410','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=562');">자료실</span></li> <li id="1020109030000" ><span onclick="siteGo('yangcheon','10201090300002016100410','');">건의사항</span>												<ul> <li id="1020109030100" ><span onclick="siteGo('yangcheon','10201090301002016100410','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=565');">불편사항</span></li> <li id="1020109030200" ><span onclick="siteGo('yangcheon','10201090302002016100410','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=566');">아이디어</span></li> <li id="1020109030300" ><span onclick="siteGo('yangcheon','10201090303002016100410','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=567');">평가의견</span>												</li>								</ul></li> <li id="1020109040000" ><span onclick="siteGo('yangcheon','10201090400002016100410','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=564');">자유게시판</span>						</li>								</ul></li>								</ul></li> <li id="1020200000000" ><span onclick="siteGo('yangcheon','10202000000002016081013','');">통합예약</span> <ul> <li id="1020201000000" ><span onclick="siteGo('yangcheon','10202010000002016102904','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10202010000002016102904');">통합예약안내</span></li>								</ul></li> <li id="1020300000000" ><span onclick="siteGo('yangcheon','10203000000002016081013','');">마을공동체</span> <ul> <li id="1020301000000" ><span onclick="siteGo('yangcheon','10203010000002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10203010000002016081013');">마을공동체란?</span></li> <li id="1020302000000" ><span onclick="siteGo('yangcheon','10203020000002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=304');">공지사항</span></li> <li id="1020303000000" ><span onclick="siteGo('yangcheon','10203030000002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=305');">우리마을 이야기</span></li>								</ul></li> <li id="1020400000000" ><span onclick="siteGo('yangcheon','10204000000002016081013','');">생활속공유</span> <ul> <li id="1020401000000" ><span onclick="siteGo('yangcheon','10204010000002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10204010000002016081013');">공유란 무엇인가?</span></li> <li id="1020402000000" ><span onclick="siteGo('yangcheon','10204020000002016081013','');">공유에 참여하는 방법</span>												<ul> <li id="1020402010000" ><span onclick="siteGo('yangcheon','10204020100002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10204020100002016081013');">장소공유</span></li> <li id="1020402020000" ><span onclick="siteGo('yangcheon','10204020200002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10204020200002016081013');">물건공유</span></li> <li id="1020402030000" ><span onclick="siteGo('yangcheon','10204020300002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10204020300002016081013');">재능공유</span></li> <li id="1020402040000" ><span onclick="siteGo('yangcheon','10204020400002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10204020400002016081013');">복합공유</span></li> <li id="1020402050000" ><span onclick="siteGo('yangcheon','10204020500002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10204020500002016081013');">기타공유</span></li>								</ul></li> <li id="1020403000000" ><span onclick="siteGo('yangcheon','10204030000002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10204030000002016081013');">공유단체와 기업</span>									</li> <li id="1020404000000" ><span onclick="siteGo('yangcheon','10204040000002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=307');">공유소식</span></li>								</ul></li>								</ul></li> <li id="1030000000000" ><span onclick="siteGo('yangcheon','10300000000002016081013','');">행정공개</span> <ul> <li id="1030100000000" ><span onclick="siteGo('yangcheon','10301000000002016081013','');">구정정보</span><ul> <li id="1030101000000" ><span onclick="siteGo('yangcheon','10301010000002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=310');">월중주요업무</span></li> <li id="1030102000000" ><span onclick="siteGo('yangcheon','10301020000002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=311');">주요업무계획</span></li> <li id="1030103000000" ><span onclick="siteGo('yangcheon','10301030000002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=312');">구정기본현황</span></li> <li id="1030104000000" ><span onclick="siteGo('yangcheon','10301040000002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=313');">구정백서</span></li> <li id="1030105000000" ><span onclick="siteGo('yangcheon','10301050000002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10301050000002016081013');">행정수요조사</span></li> <li id="1030106000000" ><span onclick="siteGo('yangcheon','10301060000002016081013','');">국공유재산관리 현황공개</span>												<ul> <li id="1030106010000" ><span onclick="siteGo('yangcheon','10301060100002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10301060100002016081013');">국공유재산안내</span></li> <li id="1030106020000" ><span onclick="siteGo('yangcheon','10301060200002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=316');">국유지</span></li> <li id="1030106030000" ><span onclick="siteGo('yangcheon','10301060300002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=317');">사유지</span></li> <li id="1030106040000" ><span onclick="siteGo('yangcheon','10301060400002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=318');">구유지</span></li>								</ul></li>								</ul></li> <li id="1030200000000" ><span onclick="siteGo('yangcheon','10302000000002016081013','');">법령/자치법규</span> <ul> <li id="1030201000000" ><span onclick="siteGo('yangcheon','10302010000002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10302010000002016081013');">자치법규집</span></li> <li id="1030202000000" ><span onclick="siteGo('yangcheon','10302020000002016081013','');">행정처분기준편람</span>												<ul> <li id="1030202010000" ><span onclick="siteGo('yangcheon','10302020100002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10302020100002016081013');">행정처분기준편람안내</span></li> <li id="1030202020000" ><span onclick="siteGo('yangcheon','10302020200002016081013','');">행정처분기준편람</span></li>								</ul></li> <li id="1030203000000" ><span onclick="siteGo('yangcheon','10302030000002016081013','');">행정절차제도</span> <ul> <li id="1030203010000" ><span onclick="siteGo('yangcheon','10302030100002016083110','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10302030100002016083110');">사전통지의 엄격한 준수</span></li> <li id="1030203020000" ><span onclick="siteGo('yangcheon','10302030200002016083110','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10302030200002016083110');">의견청취 운영절차의 개선</span></li> <li id="1030203030000" ><span onclick="siteGo('yangcheon','10302030300002016083110','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10302030300002016083110');">행정상의 입법예고의 운영 철저</span></li> <li id="1030203040000" ><span onclick="siteGo('yangcheon','10302030400002016083110','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10302030400002016083110');">행정지도의 적법한 운영</span></li> <li id="1030203050000" ><span onclick="siteGo('yangcheon','10302030500002016083110','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10302030500002016083110');">행정절차법/시행령/시행규칙</span></li>								</ul></li>								</ul></li> <li id="1030300000000" ><span onclick="siteGo('yangcheon','10303000000002016081013','');">구보 및 입법예고</span>						<ul> <li id="1030301000000" ><span onclick="siteGo('yangcheon','10303010000002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=322');">양천구보</span></li> <li id="1030302000000" ><span onclick="siteGo('yangcheon','10303020000002016081013','');">입법예고</span></li>								</ul></li> <li id="1030400000000" ><span onclick="siteGo('yangcheon','10304000000002016081013','');">정보공개</span> <ul> <li id="1030401000000" ><span onclick="siteGo('yangcheon','10304010000002016081013','');">행정정보보공개제도</span><ul> <li id="1030401010000" ><span onclick="siteGo('yangcheon','10304010100002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=325');">정보공개이용안내</span></li> <li id="1030401020000" ><span onclick="siteGo('yangcheon','10304010200002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10304010200002016081013');">행정정보소재안내</span></li> <li id="1030401030000" ><span onclick="siteGo('yangcheon','10304010300002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=326');">비공개대상정보범위세부기준</span></li> <li id="1030401040000" ><span onclick="siteGo('yangcheon','10304010400002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=327');">정보공개 연차보고서</span></li>								</ul></li> <li id="1030402000000" ><span onclick="siteGo('yangcheon','10304020000002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10304020000002016081013');">정보공개청구</span></li> <li id="1030403000000" ><span onclick="siteGo('yangcheon','10304030000002016081013','');">사전정보공표목록</span></li> <li id="1030404000000" ><span onclick="siteGo('yangcheon','10304040000002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=329');">정보공개BEST</span></li> <li id="1030405000000" ><span onclick="siteGo('yangcheon','10304050000002016081013','');">회의공개</span>												<ul> <li id="1030405010000" ><span onclick="siteGo('yangcheon','10304050100002016081013','');">위원회회의</span></li> <li id="1030405020000" ><span onclick="siteGo('yangcheon','10304050200002016081013','');">회의록공개</span>												</li>								</ul></li> <li id="1030406000000" ><span onclick="siteGo('yangcheon','10304100000002016112201','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=332');">회의자료공개</span>									</li> <li id="1030407000000" ><span onclick="siteGo('yangcheon','10304060000002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=334');">정책실명제</span></li> <li id="1030408000000" ><span onclick="siteGo('yangcheon','10304070000002016081013','');">업무추진비공개</span>												<ul> <li id="1030408010000" ><span onclick="siteGo('yangcheon','10304070100002016090709','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=397');">법인카드 및 업무추진비</span></li> <li id="1030408020000" ><span onclick="siteGo('yangcheon','10304070200002016090709','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=398');">기관 업무추진비</span></li> <li id="1030408030000" ><span onclick="siteGo('yangcheon','10304070300002016090709','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=399');">시책 업무추진비</span></li>								</ul></li> <li id="1030409000000" ><span onclick="siteGo('yangcheon','10304080000002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10304080000002016081013');">공공데이터개방</span>						</li> <li id="1030410000000" ><span onclick="siteGo('yangcheon','10304090000002016081013','');">생애주기별 맞춤형정보</span>												<ul> <li id="1030410010000" ><span onclick="siteGo('yangcheon','10304090100002016090709','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=401');">임신전/임신중</span></li> <li id="1030410020000" ><span onclick="siteGo('yangcheon','10304090200002016090709','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=402');">영유아</span></li> <li id="1030410030000" ><span onclick="siteGo('yangcheon','10304090300002016090709','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=403');">취학전</span></li> <li id="1030410040000" ><span onclick="siteGo('yangcheon','10304090400002016090709','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=404');">학생</span></li> <li id="1030410050000" ><span onclick="siteGo('yangcheon','10304090500002016090709','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=405');">성인</span></li> <li id="1030410060000" ><span onclick="siteGo('yangcheon','10304090600002016090709','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=406');">어르신</span></li> <li id="1030410070000" ><span onclick="siteGo('yangcheon','10304090700002016090709','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=407');">사망</span></li>								</ul></li>								</ul></li> <li id="1030500000000" ><span onclick="siteGo('yangcheon','10305000000002016081013','');">열린재정</span>						<ul> <li id="1030501000000" ><span onclick="siteGo('yangcheon','10305010000002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=421');">예산운영현황</span></li> <li id="1030502000000" ><span onclick="siteGo('yangcheon','10305020000002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=336');">주민참여예산방</span></li> <li id="1030503000000" ><span onclick="siteGo('yangcheon','10305030000002016081013','');">예산의견접수</span>												<ul> <li id="1030503010000" ><span onclick="siteGo('yangcheon','10305030100002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10305030100002016081013');">안내</span></li> <li id="1030503020000" ><span onclick="siteGo('yangcheon','10305030200002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=338');">의견접수하기</span></li>								</ul></li> <li id="1030504000000" ><span onclick="siteGo('yangcheon','10305040000002016081013','');">재정운영공시</span>						<ul> <li id="1030504010000" ><span onclick="siteGo('yangcheon','10305040100002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=340');">예산</span></li> <li id="1030504020000" ><span onclick="siteGo('yangcheon','10305040200002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=341');">결산</span></li> <li id="1030504030000" ><span onclick="siteGo('yangcheon','10305040300002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=342');">세입세출결산서</span></li> <li id="1030504040000" ><span onclick="siteGo('yangcheon','10305040400002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=343');">의견제시방</span></li>								</ul></li> <li id="1030505000000" ><span onclick="siteGo('yangcheon','10305050000002016081013','');">지방공기업</span> <ul> <li id="1030505010000" ><span onclick="siteGo('yangcheon','10305050100002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10305050100002016081013');">지방공기업이란</span></li> <li id="1030505020000" ><span onclick="siteGo('yangcheon','10305050200002016081013','');">지방공기업현황</span></li> <li id="1030505030000" ><span onclick="siteGo('yangcheon','10305050300002016081013','');">지방공기업 경영정보</span></li> <li id="1030505040000" ><span onclick="siteGo('yangcheon','10305050400002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10305050400002016081013');">산하 지방공기업 결산정보</span></li>								</ul></li> <li id="1030506000000" ><span onclick="siteGo('yangcheon','10305060000002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10305060000002016081013');">세입세출 운용현황</span>						</li>								</ul></li> <li id="1030600000000" ><span onclick="siteGo('yangcheon','10306000000002016081013','');">계약정보공개시스템</span>						<ul> <li id="1030601000000" ><span onclick="siteGo('yangcheon','10306010000002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=345');">연간발주계획</span></li> <li id="1030602000000" ><span onclick="siteGo('yangcheon','10306020000002016081013','');">발주계획</span></li> <li id="1030603000000" ><span onclick="siteGo('yangcheon','10306030000002016081013','');">입찰공고현황</span>												<ul> <li id="1030603010000" ><span onclick="siteGo('yangcheon','10306030100002016102801','');">전체입찰</span></li> <li id="1030603020000" ><span onclick="siteGo('yangcheon','10306030200002016102801','');">물품입찰</span></li> <li id="1030603030000" ><span onclick="siteGo('yangcheon','10306030300002016102801','');">공사입찰</span></li> <li id="1030603040000" ><span onclick="siteGo('yangcheon','10306030400002016102801','');">용역입찰</span></li>								</ul></li> <li id="1030604000000" ><span onclick="siteGo('yangcheon','10306040000002016081013','');">개찰결과</span> <ul> <li id="1030604010000" ><span onclick="siteGo('yangcheon','10306040100002016102911','');">전체개찰</span></li> <li id="1030604020000" ><span onclick="siteGo('yangcheon','10306040200002016102911','');">물품개찰</span></li> <li id="1030604030000" ><span onclick="siteGo('yangcheon','10306040300002016102911','');">공사개찰</span></li> <li id="1030604040000" ><span onclick="siteGo('yangcheon','10306040400002016102911','');">용역개찰</span></li>								</ul></li> <li id="1030605000000" ><span onclick="siteGo('yangcheon','10306050000002016081013','');">전체계약현황</span> <ul> <li id="1030605010000" ><span onclick="siteGo('yangcheon','10306050100002016101311','');">전체계약</span></li> <li id="1030605020000" ><span onclick="siteGo('yangcheon','10306050200002016101311','');">물품계약</span></li> <li id="1030605030000" ><span onclick="siteGo('yangcheon','10306050300002016101311','');">공사계약</span></li> <li id="1030605040000" ><span onclick="siteGo('yangcheon','10306050400002016101311','');">용역계약</span></li>								</ul></li> <li id="1030606000000" ><span onclick="siteGo('yangcheon','10306060000002016081013','');">하도급계약현황</span> <ul> <li id="1030606010000" ><span onclick="siteGo('yangcheon','10306060100002016101311','');">전체하도급계약</span></li> <li id="1030606020000" ><span onclick="siteGo('yangcheon','10306060200002016101311','');">물품하도급계약</span></li> <li id="1030606030000" ><span onclick="siteGo('yangcheon','10306060300002016101311','');">공사하도급계약</span></li> <li id="1030606040000" ><span onclick="siteGo('yangcheon','10306060400002016101311','');">용역하도급계약</span></li>								</ul></li> <li id="1030607000000" ><span onclick="siteGo('yangcheon','10306070000002016081013','');">대가지급현황</span> <ul> <li id="1030607010000" ><span onclick="siteGo('yangcheon','10306070100002016101311','');">전체대가지급</span></li> <li id="1030607020000" ><span onclick="siteGo('yangcheon','10306070200002016101311','');">물품대가지급</span></li> <li id="1030607030000" ><span onclick="siteGo('yangcheon','10306070300002016101311','');">공사대가지급</span></li> <li id="1030607040000" ><span onclick="siteGo('yangcheon','10306070400002016101311','');">용역대가지급</span>												</li>								</ul></li> <li id="1030608000000" ><span onclick="siteGo('yangcheon','10306080000002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=346');">하자보증기간</span>									</li> <li id="1030609000000" ><span onclick="siteGo('yangcheon','10306090000002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=347');">회계관련서식</span></li>								</ul></li>								</ul></li> <li id="1040000000000" ><span onclick="siteGo('yangcheon','10400000000002016081013','');">열린양천</span> <ul> <li id="1040100000000" ><span onclick="siteGo('yangcheon','10401000000002016081013','');">열린구청장실</span> <ul> <li id="1040101000000" ><span onclick="siteGo('yangcheon','10401010000002016090608','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10401010000002016090608');">김수영입니다.</span></li>								</ul></li> <li id="1040200000000" ><span onclick="siteGo('yangcheon','10402000000002016081013','');">우리구소개</span> <ul> <li id="1040201000000" ><span onclick="siteGo('yangcheon','10402010000002016081013','');">연혁 및 역사</span> <ul> <li id="1040201010000" ><span onclick="siteGo('yangcheon','10402010100002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10402010100002016081013');">양천구 연혁</span></li> <li id="1040201020000" ><span onclick="siteGo('yangcheon','10402010200002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=350');">시대별 변천사</span></li> <li id="1040201030000" ><span onclick="siteGo('yangcheon','10402010300002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=351');">지명유래</span></li>								</ul></li> <li id="1040202000000" ><span onclick="siteGo('yangcheon','10402020000002016081013','');">문화 및 명소</span>						<ul> <li id="1040202010000" ><span onclick="siteGo('yangcheon','10402020100002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=354');">속담과 전설</span></li> <li id="1040202020000" ><span onclick="siteGo('yangcheon','10402020200002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10402020200002016081013');">문화재/유물</span></li> <li id="1040202030000" ><span onclick="siteGo('yangcheon','10402020300002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=353');">양천명소</span></li> <li id="1040202040000" ><span onclick="siteGo('yangcheon','10402020400002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10402020400002016081013');">서울미래유산</span></li> <li id="1040202050000" ><span onclick="siteGo('yangcheon','10402020500002016112209','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10402020500002016112209');">양천관광코스</span></li>								</ul></li> <li id="1040203000000" ><span onclick="siteGo('yangcheon','10402030000002016081013','');">일반현황</span> <ul> <li id="1040203010000" ><span onclick="siteGo('yangcheon','10402030100002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10402030100002016081013');">위치와 면적</span></li> <li id="1040203020000" ><span onclick="siteGo('yangcheon','10402030200002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10402030200002016081013');">지세와 기후</span></li> <li id="1040203030000" ><span onclick="siteGo('yangcheon','10402030300002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10402030300002016081013');">주거 및 인구</span></li> <li id="1040203040000" ><span onclick="siteGo('yangcheon','10402030400002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10402030400002016081013');">행정구역</span></li> <li id="1040203050000" ><span onclick="siteGo('yangcheon','10402030500002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10402030500002016081013');">주요시설현황</span></li>								</ul></li> <li id="1040204000000" ><span onclick="siteGo('yangcheon','10402040000002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10402040000002016081013');">상징물</span></li> <li id="1040205000000" ><span onclick="siteGo('yangcheon','10402050000002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10402050000002016081013');">구민헌장</span></li>								</ul></li> <li id="1040300000000" ><span onclick="siteGo('yangcheon','10403000000002016081013','');">행정조직</span> <ul> <li id="1040301000000" ><span onclick="siteGo('yangcheon','10403010000002016081013','');">조직도보기</span></li> <li id="1040302000000" ><span onclick="siteGo('yangcheon','10403020000002016081013','');">부서안내</span></li> <li id="1040303000000" ><span onclick="siteGo('yangcheon','10403030000002016081013','');">직원검색</span></li>								</ul></li> <li id="1040400000000" ><span onclick="siteGo('yangcheon','10404000000002016081013','');">청사안내</span> <ul> <li id="1040401000000" ><span onclick="siteGo('yangcheon','10404010000002016081013','');">구청</span> <ul> <li id="1040401010000" ><span onclick="siteGo('yangcheon','10404010100002016091211','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10404010100002016091211');">구청 층별 안내</span></li> <li id="1040401020000" ><span onclick="siteGo('yangcheon','10404010200002016091211','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10404010200002016091211');">해누리타운 층별안내</span></li> <li id="1040401030000" ><span onclick="siteGo('yangcheon','10404010300002016091211','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10404010300002016091211');">구청 및 해누리타운 오시는길</span></li>								</ul></li> <li id="1040402000000" ><span onclick="siteGo('yangcheon','10404020000002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10404020000002016081013');">보건소</span></li> <li id="1040403000000" ><span onclick="siteGo('yangcheon','10404030000002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10404030000002016081013');">동주민센터</span></li>								</ul></li> <li id="1040500000000" ><span onclick="siteGo('yangcheon','10405000000002016081013','');">양천통계정보</span> <ul> <li id="1040501000000" ><span onclick="siteGo('yangcheon','10405010000002016081013','');">통계로 보는 양천</span> <ul> <li id="1040501010000" ><span onclick="siteGo('yangcheon','10405010100002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10405010100002016081013');">양천구의 한해</span></li> <li id="1040501020000" ><span onclick="siteGo('yangcheon','10405010200002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=358');">도표로 보는 양천</span>												</li>								</ul></li> <li id="1040502000000" ><span onclick="siteGo('yangcheon','10405020000002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=360');">인구통계</span></li> <li id="1040503000000" ><span onclick="siteGo('yangcheon','10405030000002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10405030000002016081013');">통계연보</span></li> <li id="1040504000000" ><span onclick="siteGo('yangcheon','10405040000002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10405040000002016081013');">사업체통계</span></li> <li id="1040505000000" ><span onclick="siteGo('yangcheon','10405050000002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10405050000002016081013');">광제조업통계</span></li> <li id="1040506000000" ><span onclick="siteGo('yangcheon','10405060000002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10405060000002016081013');">통계정보조회</span></li>								</ul></li> <li id="1040600000000" ><span onclick="siteGo('yangcheon','10406000000002016081013','');">관내시설</span>						<ul> <li id="1040601000000" ><span onclick="siteGo('yangcheon','10406010000002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=362');">사회복지시설</span></li> <li id="1040602000000" ><span onclick="siteGo('yangcheon','10406020000002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=363');">문화시설</span></li> <li id="1040603000000" ><span onclick="siteGo('yangcheon','10406030000002016081013','');">체육시설</span>												<ul> <li id="1040603010000" ><span onclick="siteGo('yangcheon','10406030100002016090901','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10406030100002016090901');">시설현황</span></li> <li id="1040603020000" ><span onclick="siteGo('yangcheon','10406030200002016090901','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10406030200002016090901');">이용안내</span></li> <li id="1040603030000" ><span onclick="siteGo('yangcheon','10406030300002016090901','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10406030300002016090901');">오시는길</span></li> <li id="1040603040000" ><span onclick="siteGo('yangcheon','10406030400002016090901','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=364');">공지사항</span></li>								</ul></li> <li id="1040604000000" ><span onclick="siteGo('yangcheon','10406040000002016081013','');">교육시설</span> <ul> <li id="1040604010000" ><span onclick="siteGo('yangcheon','10406040100002016101105','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10406040100002016101105');">e-유치원</span></li> <li id="1040604020000" ><span onclick="siteGo('yangcheon','10406040200002016101105','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10406040200002016101105');">초등학교</span></li> <li id="1040604030000" ><span onclick="siteGo('yangcheon','10406040300002016101105','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10406040300002016101105');">중학교</span></li> <li id="1040604040000" ><span onclick="siteGo('yangcheon','10406040400002016101105','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10406040400002016101105');">고등학교</span></li>								</ul></li> <li id="1040605000000" ><span onclick="siteGo('yangcheon','10406050000002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10406050000002016081013');">시설관리공단</span>						</li>								</ul></li> <li id="1040700000000" ><span onclick="siteGo('yangcheon','10407000000002016081013','');">자매결연도시</span> <ul> <li id="1040701000000" ><span onclick="siteGo('yangcheon','10407010000002016081013','');">국내</span> <ul> <li id="1040701010000" ><span onclick="siteGo('yangcheon','10407010100002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10407010100002016081013');">부여군</span></li> <li id="1040701020000" ><span onclick="siteGo('yangcheon','10407010200002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10407010200002016081013');">순천시</span></li> <li id="1040701030000" ><span onclick="siteGo('yangcheon','10407010300002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10407010300002016081013');">울진군</span></li> <li id="1040701040000" ><span onclick="siteGo('yangcheon','10407010400002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10407010400002016081013');">화순군</span></li> <li id="1040701050000" ><span onclick="siteGo('yangcheon','10407010500002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10407010500002016081013');">강화군</span></li>								</ul></li> <li id="1040702000000" ><span onclick="siteGo('yangcheon','10407020000002016081013','');">국외</span> <ul> <li id="1040702010000" ><span onclick="siteGo('yangcheon','10407020100002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10407020100002016081013');">호주 뱅크스타운시</span></li> <li id="1040702020000" ><span onclick="siteGo('yangcheon','10407020200002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10407020200002016081013');">중국 장춘시 조양구</span></li> <li id="1040702030000" ><span onclick="siteGo('yangcheon','10407020300002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10407020300002016081013');">일본 도쿄도 나카노구</span></li>								</ul></li>								</ul></li>								</ul></li> <li id="1050000000000" ><span onclick="siteGo('yangcheon','10500000000002016081013','');">분야별 정보</span> <ul> <li id="1050100000000" ><span onclick="siteGo('yangcheon','10501000000002016081013','');">복지</span> <ul> <li id="1050101000000" ><span onclick="siteGo('yangcheon','10501010000002016081013','');">사회복지</span> <ul> <li id="1050101010000" ><span onclick="siteGo('yangcheon','10501010100002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10501010100002016081013');">급여의 종류</span></li> <li id="1050101020000" ><span onclick="siteGo('yangcheon','10501010200002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10501010200002016081013');">기초생활보장 수급자 선정</span></li> <li id="1050101030000" ><span onclick="siteGo('yangcheon','10501010300002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10501010300002016081013');">급여의 신청</span></li> <li id="1050101040000" ><span onclick="siteGo('yangcheon','10501010400002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10501010400002016081013');">감면제도</span></li>								</ul></li> <li id="1050102000000" ><span onclick="siteGo('yangcheon','10501020000002016081013','');">영유아복지</span> <ul> <li id="1050102010000" ><span onclick="siteGo('yangcheon','10501020100002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10501020100002016081013');">신생아출산지원금</span></li> <li id="1050102020000" ><span onclick="siteGo('yangcheon','10501020200002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10501020200002016081013');">시설미이용아동양육수당</span></li> <li id="1050102030000" ><span onclick="siteGo('yangcheon','10501020300002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10501020300002016081013');">다둥이행복카드</span></li> <li id="1050102040000" ><span onclick="siteGo('yangcheon','10501020400002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10501020400002016081013');">아이돌보미사업</span></li> <li id="1050102050000" ><span onclick="siteGo('yangcheon','10501020500002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10501020500002016081013');">보육정책</span></li> <li id="1050102060000" ><span onclick="siteGo('yangcheon','10501020600002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10501020600002016081013');">보육시설이용안내</span></li> <li id="1050102070000" ><span onclick="siteGo('yangcheon','10501020700002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10501020700002016081013');">관련법령</span></li>								</ul></li> <li id="1050103000000" ><span onclick="siteGo('yangcheon','10501030000002016081013','');">아동복지</span> <ul> <li id="1050103010000" ><span onclick="siteGo('yangcheon','10501030100002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10501030100002016081013');">아동복지시설현황</span></li> <li id="1050103020000" ><span onclick="siteGo('yangcheon','10501030200002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10501030200002016081013');">소년소녀가정 지원</span></li> <li id="1050103030000" ><span onclick="siteGo('yangcheon','10501030300002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10501030300002016081013');">아동급식지원</span></li> <li id="1050103040000" ><span onclick="siteGo('yangcheon','10501030400002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10501030400002016081013');">지역아동센터</span></li> <li id="1050103050000" ><span onclick="siteGo('yangcheon','10501030500002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10501030500002016081013');">드림스타트</span></li> <li id="1050103060000" ><span onclick="siteGo('yangcheon','10501030600002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10501030600002016081013');">관련법령</span></li>								</ul></li> <li id="1050104000000" ><span onclick="siteGo('yangcheon','10501040000002016081013','');">청소년복지</span> <ul> <li id="1050104010000" ><span onclick="siteGo('yangcheon','10501040100002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10501040100002016081013');">청소년참여행사및교류</span></li> <li id="1050104020000" ><span onclick="siteGo('yangcheon','10501040200002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10501040200002016081013');">청소년프로그램운영</span></li> <li id="1050104030000" ><span onclick="siteGo('yangcheon','10501040300002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10501040300002016081013');">청소년보호활동</span></li> <li id="1050104040000" ><span onclick="siteGo('yangcheon','10501040400002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10501040400002016081013');">청소년시설현황</span></li> <li id="1050104050000" ><span onclick="siteGo('yangcheon','10501040500002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10501040500002016081013');">청소년지킴이방</span></li> <li id="1050104060000" ><span onclick="siteGo('yangcheon','10501040700002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10501040700002016081013');">관련법령</span>												</li>								</ul></li> <li id="1050105000000" ><span onclick="siteGo('yangcheon','10501050000002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=367');">여성복지</span>									</li> <li id="1050106000000" ><span onclick="siteGo('yangcheon','10501060000002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=368');">어르신복지</span></li> <li id="1050107000000" ><span onclick="siteGo('yangcheon','10501070000002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=369');">장애인복지</span></li> <li id="1050108000000" ><span onclick="siteGo('yangcheon','10501080000002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=370');">저소득주민</span></li> <li id="1050109000000" ><span onclick="siteGo('yangcheon','10501090000002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10501090000002016081013');">법률 및 금융복지상담</span></li> <li id="1050110000000" ><span onclick="siteGo('yangcheon','10501100000002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10501100000002016081013');">양천사랑복지재단</span></li> <li id="1050111000000" ><span onclick="siteGo('yangcheon','10501110000002016081013','');">복지관련사이트</span>												<ul> <li id="1050111010000" ><span onclick="siteGo('yangcheon','10501110100002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10501110100002016081013');">장애인관련사이트</span></li> <li id="1050111020000" ><span onclick="siteGo('yangcheon','10501110200002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10501110200002016081013');">노인관련사이트</span></li>								</ul></li>								</ul></li> <li id="1050200000000" ><span onclick="siteGo('yangcheon','10502000000002016081013','');">교육/문화</span> <ul> <li id="1050201000000" ><span onclick="siteGo('yangcheon','10502010000002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10502010000002016081013');">교육시설안내</span></li> <li id="1050202000000" ><span onclick="siteGo('yangcheon','10502020000002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10502020000002016081013');">사이버평생학습포털</span></li> <li id="1050203000000" ><span onclick="siteGo('yangcheon','10502030000002016081013','');">정보화교육</span>												<ul> <li id="1050203010000" ><span onclick="siteGo('yangcheon','10502030100002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10502030100002016081013');">안내</span></li> <li id="1050203020000" ><span onclick="siteGo('yangcheon','10502030600002016101704','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10502030600002016101704');">수강료 및 납부방법</span></li> <li id="1050203030000" ><span onclick="siteGo('yangcheon','10502030700002016101704','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10502030700002016101704');">강좌안내</span></li> <li id="1050203040000" ><span onclick="siteGo('yangcheon','10502030200002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10502030200002016081013');">교육일정</span></li> <li id="1050203050000" ><span onclick="siteGo('yangcheon','10502030300002016081013','');">교육접수</span></li> <li id="1050203060000" ><span onclick="siteGo('yangcheon','10502030400002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10502030400002016081013');">교육장안내</span></li> <li id="1050203070000" ><span onclick="siteGo('yangcheon','10502030500002016081013','');">나의교육현황</span></li>								</ul></li> <li id="1050204000000" ><span onclick="siteGo('yangcheon','10502040000002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10502040000002016081013');">양천구도서관</span></li> <li id="1050205000000" ><span onclick="siteGo('yangcheon','10502050000002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10502050000002016081013');">원어민영어화상학습</span></li> <li id="1050206000000" ><span onclick="siteGo('yangcheon','10502060000002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10502060000002016081013');">평생학습관</span></li> <li id="1050207000000" ><span onclick="siteGo('yangcheon','10502070000002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10502070000002016081013');">진로직업체험지원센터</span></li> <li id="1050208000000" ><span onclick="siteGo('yangcheon','10502080000002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10502080000002016081013');">여성교실</span></li> <li id="1050209000000" ><span onclick="siteGo('yangcheon','10503110000002016110105','');">교육혁신지구</span>												<ul> <li id="1050209010000" ><span onclick="siteGo('yangcheon','10503090100002016110105','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10503090100002016110105');">서울형 혁신교육</span></li> <li id="1050209020000" ><span onclick="siteGo('yangcheon','10503090200002016110105','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10503090200002016110105');">마을교육사업</span></li> <li id="1050209030000" ><span onclick="siteGo('yangcheon','10503090300002016110105','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10503090300002016110105');">청소년사업</span></li> <li id="1050209040000" ><span onclick="siteGo('yangcheon','10503090400002016110105','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10503090400002016110105');">학부모사업</span></li> <li id="1050209050000" ><span onclick="siteGo('yangcheon','10503090500002016110105','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10503090500002016110105');">교육나눔사업</span></li> <li id="1050209060000" ><span onclick="siteGo('yangcheon','10503090600002016110105','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10503090600002016110105');">마을청소년축제한마당</span></li> <li id="1050209070000" ><span onclick="siteGo('yangcheon','10503090700002016110105','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10503090700002016110105');">양천민관학 거버넌스</span></li>								</ul></li> <li id="1050210000000" ><span onclick="siteGo('yangcheon','10502090000002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10502090000002016081013');">양천문화회관</span></li> <li id="1050211000000" ><span onclick="siteGo('yangcheon','10502100000002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10502100000002016081013');">양천문화원</span></li>								</ul></li> <li id="1050300000000" ><span onclick="siteGo('yangcheon','10503000000002016081013','');">교통/주차</span> <ul> <li id="1050301000000" ><span onclick="siteGo('yangcheon','10503010000002016081013','');">도로 및 교량</span> <ul> <li id="1050301010000" ><span onclick="siteGo('yangcheon','10503010100002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10503010100002016081013');">도로</span></li> <li id="1050301020000" ><span onclick="siteGo('yangcheon','10503010200002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10503010200002016081013');">교량</span></li>								</ul></li> <li id="1050302000000" ><span onclick="siteGo('yangcheon','10503020000002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10503020000002016081013');">대중교통안내</span>						</li> <li id="1050303000000" ><span onclick="siteGo('yangcheon','10503030000002016081013','');">자전거</span>												<ul> <li id="1050303010000" ><span onclick="siteGo('yangcheon','10503030100002016092803','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=372');">이용안내</span></li> <li id="1050303020000" ><span onclick="siteGo('yangcheon','10503030200002016092803','');">등록자전거 조회</span></li> <li id="1050303030000" ><span onclick="siteGo('yangcheon','10503030300002016092803','');">분실,도난 자전거 현황</span></li>								</ul></li> <li id="1050304000000" ><span onclick="siteGo('yangcheon','10503040000002016081013','');">주차</span> <ul> <li id="1050304010000" ><span onclick="siteGo('yangcheon','10503040100002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10503040100002016081013');">거주자우선주차제</span></li> <li id="1050304020000" ><span onclick="siteGo('yangcheon','10503040200002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=373');">그린파킹</span></li> <li id="1050304030000" ><span onclick="siteGo('yangcheon','10503040400002016100609','');">그린파크 신청하기</span></li> <li id="1050304040000" ><span onclick="siteGo('yangcheon','10900000000002016100609','');">그린파크 신청확인</span></li> <li id="1050304050000" ><span onclick="siteGo('yangcheon','10503040300002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10503040300002016081013');">공영주차장안내</span></li>								</ul></li> <li id="1050305000000" ><span onclick="siteGo('yangcheon','10503050000002016081013','');">주정차위반</span> <ul> <li id="1050305010000" ><span onclick="siteGo('yangcheon','10503050100002016083101','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10503050100002016083101');">주정차위반조회</span></li> <li id="1050305020000" ><span onclick="siteGo('yangcheon','10503050200002016083101','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10503050200002016083101');">주정차단속 SMS</span></li>								</ul></li> <li id="1050306000000" ><span onclick="siteGo('yangcheon','10503060000002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10503060000002016081013');">견인차량보관소운영</span>						</li>								</ul></li> <li id="1050400000000" ><span onclick="siteGo('yangcheon','10504000000002016081013','');">청소/위생</span> <ul> <li id="1050401000000" ><span onclick="siteGo('yangcheon','10504010000002016081013','');">대형생활폐기물</span> <ul> <li id="1050401010000" ><span onclick="siteGo('yangcheon','10504010100002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10504010100002016081013');">배출신고안내</span></li> <li id="1050401020000" ><span onclick="siteGo('yangcheon','10504010200002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10504010200002016081013');">폐기물규격 및 금액</span></li> <li id="1050401030000" ><span onclick="siteGo('yangcheon','10504010300002016081013','');">배출신고</span></li> <li id="1050401040000" ><span onclick="siteGo('yangcheon','10504010400002016081013','');">신고내역조회</span></li> <li id="1050401050000" ><span onclick="siteGo('yangcheon','10504010500002016081013','');">관리목록</span></li>								</ul></li> <li id="1050402000000" ><span onclick="siteGo('yangcheon','10504020000002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10504020000002016081013');">정화조 청소신청</span></li> <li id="1050403000000" ><span onclick="siteGo('yangcheon','10504030000002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10504030000002016081013');">재활용센터</span></li>								</ul></li> <li id="1050500000000" ><span onclick="siteGo('yangcheon','10505000000002016081013','');">지역경제</span> <ul> <li id="1050501000000" ><span onclick="siteGo('yangcheon','10505010000002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10505010000002016081013');">중소상공인지원센터</span></li> <li id="1050502000000" ><span onclick="siteGo('yangcheon','10505020000002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10505020000002016081013');">중소기업정보</span></li> <li id="1050503000000" ><span onclick="siteGo('yangcheon','10505030000002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10505030000002016081013');">양천구공동브랜드</span></li> <li id="1050504000000" ><span onclick="siteGo('yangcheon','10505040000002016081013','');">물가정보</span>												<ul> <li id="1050504010000" ><span onclick="siteGo('yangcheon','10505040100002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10505040100002016081013');">착한가격업소정보제공</span></li> <li id="1050504020000" ><span onclick="siteGo('yangcheon','10505040200002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=379');">장바구니물가정보</span></li> <li id="1050504030000" ><span onclick="siteGo('yangcheon','10505040300002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10505040300002016081013');">좋은가격실속정보</span></li>								</ul></li> <li id="1050505000000" ><span onclick="siteGo('yangcheon','10505050000002016081013','');">소비자보호센터</span> <ul> <li id="1050505010000" ><span onclick="siteGo('yangcheon','10505050100002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10505050100002016081013');">양천구</span></li> <li id="1050505020000" ><span onclick="siteGo('yangcheon','10505050200002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10505050200002016081013');">서울시</span></li> <li id="1050505030000" ><span onclick="siteGo('yangcheon','10505050300002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10505050300002016081013');">방문판매소비자상담</span></li> <li id="1050505040000" ><span onclick="siteGo('yangcheon','10505050400002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10505050400002016081013');">기타상담기관</span></li> <li id="1050505050000" ><span onclick="siteGo('yangcheon','10505050500002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=380');">소비자정보</span></li>								</ul></li> <li id="1050506000000" ><span onclick="siteGo('yangcheon','10505060000002016081013','');">유통·상가정보</span> <ul> <li id="1050506010000" ><span onclick="siteGo('yangcheon','10505060100002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10505060100002016081013');">목동로데오거리</span></li> <li id="1050506020000" ><span onclick="siteGo('yangcheon','10505060200002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=877');">전통시장현황</span></li> <li id="1050506030000" ><span onclick="siteGo('yangcheon','10505060300002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10505060300002016081013');">대규모점포현황</span></li>								</ul></li> <li id="1050507000000" ><span onclick="siteGo('yangcheon','10505070000002016081013','');">대부업</span> <ul> <li id="1050507010000" ><span onclick="siteGo('yangcheon','10505070100002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10505070100002016081013');">대부업·대부중개업 등록안내</span></li> <li id="1050507020000" ><span onclick="siteGo('yangcheon','10505070200002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10505070200002016081013');">등록현황</span></li> <li id="1050507030000" ><span onclick="siteGo('yangcheon','10505070300002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10505070300002016081013');">피해신고</span></li>								</ul></li> <li id="1050508000000" ><span onclick="siteGo('yangcheon','10505080000002016081013','');">에너지</span> <ul> <li id="1050508010000" ><span onclick="siteGo('yangcheon','10505080100002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10505080100002016081013');">석유판매업</span></li> <li id="1050508020000" ><span onclick="siteGo('yangcheon','10505080200002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10505080200002016081013');">LP가스</span></li> <li id="1050508030000" ><span onclick="siteGo('yangcheon','10505080300002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10505080300002016081013');">고압가스</span></li> <li id="1050508040000" ><span onclick="siteGo('yangcheon','10505080400002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10505080400002016081013');">LP가스 안전공급계약제</span></li> <li id="1050508050000" ><span onclick="siteGo('yangcheon','10505080500002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10505080500002016081013');">LP가스사용차량운전자교육</span></li> <li id="1050508060000" ><span onclick="siteGo('yangcheon','10505080600002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10505080600002016081013');">가스안전관리</span></li>								</ul></li> <li id="1050509000000" ><span onclick="siteGo('yangcheon','10505090000002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10505090000002016081013');">관련사이트</span>						</li>								</ul></li> <li id="1050600000000" ><span onclick="siteGo('yangcheon','10506000000002016081013','');">일자리플러스센터</span> <ul> <li id="1050601000000" ><span onclick="siteGo('yangcheon','10506010000002016081013','');">소개</span> <ul> <li id="1050601010000" ><span onclick="siteGo('yangcheon','10506010100002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10506010100002016081013');">일자리플러스센터 소개</span></li> <li id="1050601020000" ><span onclick="siteGo('yangcheon','10506010200002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10506010200002016081013');">일자리플러스센터 활동</span></li> <li id="1050601030000" ><span onclick="siteGo('yangcheon','10506010300002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10506010300002016081013');">미소금융지원안내</span></li>								</ul></li> <li id="1050602000000" ><span onclick="siteGo('yangcheon','10506020000002016081013','');">사회적경제기업</span> <ul> <li id="1050602010000" ><span onclick="siteGo('yangcheon','10506020100002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10506020100002016081013');">소개</span></li> <li id="1050602020000" ><span onclick="siteGo('yangcheon','10506020200002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10506020200002016081013');">관내사회적경제기업현황</span></li> <li id="1050602030000" ><span onclick="siteGo('yangcheon','10506020300002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10506020300002016081013');">사업적경제기업쇼핑몰</span></li> <li id="1050602040000" ><span onclick="siteGo('yangcheon','10506020400002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10506020400002016081013');">유관기관</span></li> <li id="1050602050000" ><span onclick="siteGo('yangcheon','10506020500002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10506020500002016081013');">사회적경제홍보물</span></li> <li id="1050602060000" ><span onclick="siteGo('yangcheon','10506020600002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=382');">공지사항</span></li>								</ul></li> <li id="1050603000000" ><span onclick="siteGo('yangcheon','10506030000002016081013','');">취업박람회</span> <ul> <li id="1050603010000" ><span onclick="siteGo('yangcheon','10506030100002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10506030100002016081013');">취업박람회</span></li> <li id="1050603020000" ><span onclick="siteGo('yangcheon','10506030200002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10506030200002016081013');">소규모취업박람회</span></li>								</ul></li> <li id="1050604000000" ><span onclick="siteGo('yangcheon','10506040000002016081013','');">청년인턴제</span> <ul> <li id="1050604010000" ><span onclick="siteGo('yangcheon','10506040100002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10506040100002016081013');">안내</span></li> <li id="1050604020000" ><span onclick="siteGo('yangcheon','10506040200002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=383');">신청안내</span></li> <li id="1050604030000" ><span onclick="siteGo('yangcheon','10506040300002016081013','');">청년인턴신청</span></li> <li id="1050604040000" ><span onclick="siteGo('yangcheon','10506040400002016081013','');">인턴기업신청</span></li> <li id="1050604050000" ><span onclick="siteGo('yangcheon','10506040500002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10506040500002016081013');">기업청년인턴 기업현황</span></li>								</ul></li> <li id="1050605000000" ><span onclick="siteGo('yangcheon','10506050000002016081013','');">공공일자리</span> <ul> <li id="1050605010000" ><span onclick="siteGo('yangcheon','10506050100002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10506050100002016081013');">공공근로사업</span></li> <li id="1050605020000" ><span onclick="siteGo('yangcheon','10506050200002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10506050200002016081013');">지역일자리공동체사업</span></li> <li id="1050605030000" ><span onclick="siteGo('yangcheon','10506050300002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=384');">서식자료실</span></li>								</ul></li> <li id="1050606000000" ><span onclick="siteGo('yangcheon','10506060000002016081013','');">구인구직등록</span> <ul> <li id="1050606010000" ><span onclick="siteGo('yangcheon','10506060100002016081013','');">안내</span></li> <li id="1050606020000" ><span onclick="siteGo('yangcheon','10506060200002016081013','');">구직등록</span></li> <li id="1050606030000" ><span onclick="siteGo('yangcheon','10506060300002016081013','');">구인등록</span></li>								</ul></li> <li id="1050607000000" ><span onclick="siteGo('yangcheon','10506070000002016081013','');">일자리정보</span>						<ul> <li id="1050607010000" ><span onclick="siteGo('yangcheon','10506070100002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=385');">공지사항</span></li> <li id="1050607020000" ><span onclick="siteGo('yangcheon','10506070200002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=386');">일자리정보</span></li> <li id="1050607030000" ><span onclick="siteGo('yangcheon','10506070300002016081013','');">고용노동부워크넷</span></li> <li id="1050607040000" ><span onclick="siteGo('yangcheon','10506070400002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10506070400002016081013');">서울시일자리플러스센터</span></li> <li id="1050607050000" ><span onclick="siteGo('yangcheon','10506070500002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10506070500002016081013');">기업취업관련사이트</span></li>								</ul></li> <li id="1050608000000" ><span onclick="siteGo('yangcheon','10506080000002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10506080000002016081013');">교육정보</span>						</li>								</ul></li> <li id="1050700000000" ><span onclick="siteGo('yangcheon','10507000000002016081013','');">건축/지적</span> <ul> <li id="1050701000000" ><span onclick="siteGo('yangcheon','10507010000002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10507010000002016081013');">지적기준점  웹GIS 시스템</span></li> <li id="1050702000000" ><span onclick="siteGo('yangcheon','10507020000002016081013','');">건축 원-스톱 콜센터</span>												<ul> <li id="1050702010000" ><span onclick="siteGo('yangcheon','10507020100002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10507020100002016081013');">신청안내</span></li> <li id="1050702020000" ><span onclick="siteGo('yangcheon','10507020200002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=839&amp;mode');">신청하기</span></li>								</ul></li> <li id="1050703000000" ><span onclick="siteGo('yangcheon','10507030000002016081013','');">보도블럭재활용센터</span>						<ul> <li id="1050703010000" ><span onclick="siteGo('yangcheon','10507030100002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=387');">공사중 발생품 지원 신청개요</span></li> <li id="1050703020000" ><span onclick="siteGo('yangcheon','10507030200002016081013','');">공사중 발생품 지원 신청하기</span></li> <li id="1050703030000" ><span onclick="siteGo('yangcheon','10507030300002016081013','');">조회하기</span></li>								</ul></li> <li id="1050704000000" ><span onclick="siteGo('yangcheon','10507040000002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10507040000002016081013');">도로굴착복구시스템</span></li> <li id="1050705000000" ><span onclick="siteGo('yangcheon','10507050000002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10507050000002016081013');">안양천 도시시설물</span></li>								</ul></li> <li id="1050800000000" ><span onclick="siteGo('yangcheon','10508000000002016081013','');">부동산</span> <ul> <li id="1050801000000" ><span onclick="siteGo('yangcheon','10508010000002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10508010000002016081013');">부동산종합정보</span></li> <li id="1050802000000" ><span onclick="siteGo('yangcheon','10508020000002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10508020000002016081013');">부동산거래관리시스템</span></li> <li id="1050803000000" ><span onclick="siteGo('yangcheon','10508030000002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=390');">뉴타운</span></li> <li id="1050804000000" ><span onclick="siteGo('yangcheon','10508040000002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10508040000002016081013');">원클릭 공동주택 통합정보</span></li> <li id="1050805000000" ><span onclick="siteGo('yangcheon','10508050000002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10508050000002016081013');">부동산중개업소 자율점검</span></li> <li id="1050806000000" ><span onclick="siteGo('yangcheon','10508070000002016081013','');">부동산중개사무소 개설등록</span>												<ul> <li id="1050806010000" ><span onclick="siteGo('yangcheon','10508070100002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10508070100002016081013');">중개사무소 개설등록 사전예약</span></li> <li id="1050806020000" ><span onclick="siteGo('yangcheon','10508070200002016081013','');">중개사무소 개설등록 사전예약신청</span></li> <li id="1050806030000" ><span onclick="siteGo('yangcheon','10508070300002016081013','');">신고내용조회</span></li>								</ul></li> <li id="1050807000000" ><span onclick="siteGo('yangcheon','10508080000002016081013','');">중개보조원 신고</span> <ul> <li id="1050807010000" ><span onclick="siteGo('yangcheon','10508080100002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10508080100002016081013');">신고안내</span></li> <li id="1050807020000" ><span onclick="siteGo('yangcheon','10508080200002016081013','');">부동산중개보조원신고</span></li> <li id="1050807030000" ><span onclick="siteGo('yangcheon','10508080300002016081013','');">신고내용조회</span></li>								</ul></li> <li id="1050808000000" ><span onclick="siteGo('yangcheon','10508090000002016081013','');">개별공시지가 이의신청</span> <ul> <li id="1050808010000" ><span onclick="siteGo('yangcheon','10508090100002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10508090100002016081013');">안내</span></li> <li id="1050808020000" ><span onclick="siteGo('yangcheon','10508090200002016081013','');">의견신청</span></li> <li id="1050808030000" ><span onclick="siteGo('yangcheon','10508090300002016081013','');">이의신청</span></li> <li id="1050808040000" ><span onclick="siteGo('yangcheon','10508090400002016081013','');">신청결과</span></li> <li id="1050808050000" ><span onclick="siteGo('yangcheon','10508090500002016081013','');">개별공시지가 및 토지특성자료조회</span></li>								</ul></li> <li id="1050809000000" ><span onclick="siteGo('yangcheon','10508100000002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10508100000002016081013');">토지등급열람</span>						</li> <li id="1050810000000" ><span onclick="siteGo('yangcheon','10508110000002016081013','');">도로명주소</span>												<ul> <li id="1050810010000" ><span onclick="siteGo('yangcheon','10508110100002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10508110100002016081013');">안내지도</span></li> <li id="1050810020000" ><span onclick="siteGo('yangcheon','10508110200002016081013','');">변동연혁검색</span></li> <li id="1050810030000" ><span onclick="siteGo('yangcheon','10508110300002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10508110300002016081013');">양천구도로명주소 목록</span></li> <li id="1050810040000" ><span onclick="siteGo('yangcheon','10508110400002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10508110400002016081013');">도로명주소 외국어변환검색</span></li>								</ul></li>								</ul></li> <li id="1050900000000" ><span onclick="siteGo('yangcheon','10510000000002016110110','');">공공생활정보</span> <ul> <li id="1050901000000" ><span onclick="siteGo('yangcheon','10511010000002016110111','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10511010000002016110111');">공공생활지도서비스</span></li>								</ul></li> <li id="1051000000000" ><span onclick="siteGo('yangcheon','10509000000002016081013','');">생활안전</span>						<ul> <li id="1051001000000" ><span onclick="siteGo('yangcheon','10509010000002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=392');">재난안전정보</span></li> <li id="1051002000000" ><span onclick="siteGo('yangcheon','10509020000002016081013','');">안전문화운동</span>												<ul> <li id="1051002010000" ><span onclick="siteGo('yangcheon','10509020100002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10509020100002016081013');">안전문화운동의정의</span></li> <li id="1051002020000" ><span onclick="siteGo('yangcheon','10509020200002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10509020200002016081013');">안전문화운동의 필요성</span></li> <li id="1051002030000" ><span onclick="siteGo('yangcheon','10509020300002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10509020300002016081013');">안전문화운동의 방향</span></li> <li id="1051002040000" ><span onclick="siteGo('yangcheon','10509020400002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10509020400002016081013');">주요사고사례</span></li>								</ul></li> <li id="1051003000000" ><span onclick="siteGo('yangcheon','10509030000002016081013','');">안전문화광장</span>						<ul> <li id="1051003010000" ><span onclick="siteGo('yangcheon','10509030100002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=393');">주민안전사고체험담</span></li> <li id="1051003020000" ><span onclick="siteGo('yangcheon','10509030200002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=394');">안전칼럼</span>												</li>								</ul></li> <li id="1051004000000" ><span onclick="siteGo('yangcheon','10509040000002016081013','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=395');">홍보채널</span></li> <li id="1051005000000" ><span onclick="siteGo('yangcheon','10509050000002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10509050000002016081013');">안전관련법령</span></li> <li id="1051006000000" ><span onclick="siteGo('yangcheon','10509060000002016081013','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10509060000002016081013');">양천 생활안전 체험교육관</span></li>								</ul></li>								</ul></li> <li id="1060000000000" ><span onclick="siteGo('yangcheon','10600000000002016081013','');">회원</span> <ul> <li id="1060200000000" ><span onclick="siteGo('yangcheon','10602000000002016081013','');">회원가입</span></li> <li id="1060300000000" ><span onclick="siteGo('yangcheon','10601000000002016081013','');">로그인</span></li> <li id="1060400000000" ><span onclick="siteGo('yangcheon','10602020100002016101109','');">아이디찾기</span></li> <li id="1060500000000" ><span onclick="siteGo('yangcheon','10603010000002016101109','');">비밀번호찾기</span></li> <li id="1060600000000" ><span onclick="siteGo('yangcheon','10606000000002016101109','');">비회원로그인</span></li>								</ul></li> <li id="1070000000000" ><span onclick="siteGo('yangcheon','10700000000002016090207','');">마이양천</span> <ul> <li id="1070100000000" ><span onclick="siteGo('yangcheon','10710000000002016111010','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10710000000002016111010');">마이페이지</span> <ul> <li id="1070101000000" ><span onclick="siteGo('yangcheon','10708000000002016102411','');">마이페이지메인</span></li> <li id="1070102000000" ><span onclick="siteGo('yangcheon','10707000000002016102409','');">나의 정보관리</span>												<ul> <li id="1070102010000" ><span onclick="siteGo('yangcheon','10708010000002016102411','');">회원정보수정</span></li> <li id="1070102020000" ><span onclick="siteGo('yangcheon','10708020000002016102411','');">통합아이디 선택</span></li> <li id="1070102030000" ><span onclick="siteGo('yangcheon','10708040000002016102411','');">비밀번호 변경</span></li> <li id="1070102040000" ><span onclick="siteGo('yangcheon','10708050000002016102411','');">회원유효기간연장</span></li> <li id="1070102050000" ><span onclick="siteGo('yangcheon','10708030000002016102411','');">회원탈퇴</span></li>								</ul></li> <li id="1070103000000" ><span onclick="siteGo('yangcheon','10701000000002016102409','');">나의 스크랩</span></li> <li id="1070104000000" ><span onclick="siteGo('yangcheon','10702000000002016102409','');">내가 쓴 글 보기</span></li> <li id="1070105000000" ><span onclick="siteGo('yangcheon','10704000000002016102409','');">나의 신고 내역 조회</span>												<ul> <li id="1070105010000" ><span onclick="siteGo('yangcheon','10701050600002016112205','');">전체보기</span></li> <li id="1070105020000" ><span onclick="siteGo('yangcheon','10705010000002016102411','');">재난위험신고</span></li> <li id="1070105030000" ><span onclick="siteGo('yangcheon','10705020000002016102411','');">환경신문고</span></li> <li id="1070105040000" ><span onclick="siteGo('yangcheon','10705030000002016102411','');">불합리규제신고</span></li> <li id="1070105050000" ><span onclick="siteGo('yangcheon','10705040000002016102411','');">공직자부조리신고</span></li> <li id="1070105060000" ><span onclick="siteGo('yangcheon','10705050000002016102411','');">하도급부조리</span></li>								</ul></li> <li id="1070106000000" ><span onclick="siteGo('yangcheon','10705000000002016102409','');">나의 통합 예약내역</span> <ul> <li id="1070106010000" ><span onclick="siteGo('yangcheon','10707040000002016103109','');">전체</span></li> <li id="1070106020000" ><span onclick="siteGo('yangcheon','10706010000002016102411','');">교육/강좌</span></li> <li id="1070106030000" ><span onclick="siteGo('yangcheon','10706020000002016102411','');">문화/행사</span></li> <li id="1070106040000" ><span onclick="siteGo('yangcheon','10707050000002016103101','');">시설/대관</span></li> <li id="1070106050000" ><span onclick="siteGo('yangcheon','10706030000002016102411','');">기타예약</span></li>								</ul></li> <li id="1070107000000" ><span onclick="siteGo('yangcheon','10703000000002016102409','');">내가 참여한 설문조사 보기</span> <ul> <li id="1070107010000" ><span onclick="siteGo('yangcheon','10704030000002016102402','');">전체</span></li> <li id="1070107020000" ><span onclick="siteGo('yangcheon','10704010000002016102411','');">진행중설문</span></li> <li id="1070107030000" ><span onclick="siteGo('yangcheon','10704020000002016102411','');">완료된설문</span></li>								</ul></li>								</ul></li>								</ul></li> <li id="1080000000000" ><span onclick="siteGo('yangcheon','10800000000002016111110','');">이용안내</span> <ul> <li id="1080100000000" ><span onclick="siteGo('yangcheon','10807000000002016111508','');">홈페이지운영정책</span> <ul> <li id="1080102000000" ><span onclick="siteGo('yangcheon','10801000000002016111110','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10801000000002016111110');">이용약관</span></li> <li id="1080103000000" ><span onclick="siteGo('yangcheon','10802000000002016111110','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10802000000002016111110');">개인정보처리방침</span></li> <li id="1080104000000" ><span onclick="siteGo('yangcheon','10804000000002016111110','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10804000000002016111110');">이메일집단수집거부</span></li> <li id="1080105000000" ><span onclick="siteGo('yangcheon','10806000000002016111105','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10806000000002016111105');">저작권정책</span></li> <li id="1080106000000" ><span onclick="siteGo('yangcheon','10804010000002016111110','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=887');">홈페이지개선방안제안</span></li>								</ul></li> <li id="1080400000000" ><span onclick="siteGo('yangcheon','10808000000002016111508','');">영상정보처리기기</span> <ul> <li id="1080401000000" ><span onclick="siteGo('yangcheon','10803000000002016111110','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10803000000002016111110');">CCTV설치및운영지침</span></li> <li id="1080402000000" ><span onclick="siteGo('yangcheon','10804020000002018052402','/boffice_noDeco/ex/board/BbsContentLink.do?cbIdx=930');">서울문화재단 테스트 게시판</span>												<ul> <li id="1080402010000" ><span onclick="siteGo('yangcheon','10804020100002018052402','/boffice_noDeco/cn/menu/User_Menu_Contents_Form.do?siteCd=yangcheon&amp;menuCode=10804020100002018052402');">SFAC 2018</span></li> </ul></li> </ul></li> </ul></li> </ul>
			</div>
		</div>
		
		<div class="right">
			<div class="tableTitle">
				<div class="btnArea">
					<a href="#" class="btn_inline">저장</a>
					<a href="#" class="btn_inline">삭제</a>
					<a href="#" class="btn_inline">게시물 관리</a>
					<a href="#" class="btn_inline">게시판바로가기</a>
					<a href="#" class="btn_inline">모바일화면보기</a>
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
						<th>게시판 고유코드</th>
						<td><span class="required"><b>251</b></span> (코드: <b>s01</b>)</td>
						<th>게시판 이름</th>
						<td><input type="text" name="" value="대표홈페이지"></td>
					</tr>
					<tr>
						<th>공통권한</th>
						<td><input type="checkbox" name="" id="publicYn"> 사용</td>
						<th>사용유무</th>
						<td>
							<input type="radio" name="" id="useYn1" checked="">사용
							<input type="radio" name="" id="useYn2">미사용
						</td>
					</tr>
					<tr>
						<th><label for="bbsTempletGbn">게시판 템플릿</label></th>
						<td>
							<select id="bbsTempletGbn" name="">
								<option value="" selected="selected">일반게시판</option>
								<option value="">포토(리스트)</option>
								<option value="">포토(앨범)</option>
								<option value="">동영상</option>
								<option value="">묻고답하기</option>
								<option value="">목록형게시판</option>
								<option value="">민원게시판</option>
								<option value="">공지형게시판</option>
								<option value="">카드형복합형</option>
								<option value="">카드형겔러리</option>
								<option value="">카드형단순형</option>
								<option value="">카드형갤러리2</option>
								<option value="">사전정보공표</option>
								<option value="">예산운영현황</option>
								<option value="">장소형게시판</option>
								<option value="">이달의영화</option>
								<option value="">달력형</option>
								<option value="">콜센터형</option>
								<option value="">포토목록형</option>
								<option value="">브리핑형</option>
								<option value="">갤러리게시판</option>
							</select>
							<input type="button" value="초기화" class="btn_inline btn_reset">
						</td>
						<th><label for="bbsFileCnt">첨부파일</label></th>
						<td>
							<select id="bbsFileCnt" name="">
								<option value="0" selected="selected">0</option>
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
								<option value="7">7</option>
								<option value="8">8</option>
								<option value="9">9</option>
								<option value="10">10</option>
							</select> 개
							<span id="sizeSelect">
								/ <label for="fileMaxSize">파일최대용량 :</label> 
								<select id="fileMaxSize" name="">
									<option value="">10MB</option>
									<option value="">30MB</option>
									<option value="">50MB</option>
									<option value="">70MB</option>
									<option value="">100MB</option>
									<option value="">40MB</option>
								</select>
							</span>
						</td>
					</tr>
					<tr>
						<th>게시글 승인 설정</th>
						<td>
							<input id="bbsApprYn1" name="" type="radio"><label for="bbsApprYn1">사용</label>
							<input id="bbsApprYn2" name="" type="radio"><label for="bbsApprYn2">미사용</label>
						</td>
						<th>에디터 설정</th>
						<td>
							<input id="editorYn1" name="" type="radio"><label for="editorYn1">사용</label>
							<input id="editorYn2" name="" type="radio"><label for="editorYn2">미사용</label>
						</td>
					</tr>
					<tr>
						<th>익명 설정</th>
						<td>
							<input id="anonymityYn1" name="" type="radio"><label for="anonymityYn1">사용</label>
							<input id="anonymityYn2" name="" type="radio"><label for="anonymityYn2">미사용</label>
						</td>
						<th>관리자 답글기능</th>
						<td>
							<input id="replyYn1" name="" type="radio"><label for="replyYn1">사용</label>
							<input id="replyYn2" name="" type="radio"><label for="replyYn2">미사용</label>
						</td>
					</tr>
					<tr>
						<th>카테고리</th>
						<td>
							<input id="categoryUseYn1" name="" type="radio"><label for="categoryUseYn1">사용</label>
							<input id="categoryUseYn2" name="" type="radio"><label for="categoryUseYn2">미사용</label>
						</td>
						<th>RSS<!-- <span class="required">(URL 수정불가)</span> --></th>
						<td>
							<input id="rssUseYn1" name="" type="radio"><label for="rssUseYn1">사용</label>
							<input id="rssUseYn2" name="" type="radio"><label for="rssUseYn2">미사용</label>														
					 			
						</td>
					</tr>
					<tr>
						<th>썸네일사이즈</th>
						<td>
							가로 <input id="thumbnailWidth" name="" label="썸네일가호" type="text" value="" size="4">px ×
							세로 <input id="thumbnailHeight" name="" label="썸네일세로" type="text" value="" size="4">px
						</td>
						<th>NAS</th>
						<td>
							<input id="nasYn1" name="" type="radio"><label for="nasYn1">사용</label>
							<input id="nasYn2" name="" type="radio"><label for="nasYn2">미사용</label>
						</td>
					</tr>
					<tr>
						<th>페이지당개수</th>
						<td>
							<input id="pageCount" name="" type="text" value="10" size="4">줄
						</td>
						<th>모바일 PUSH</th>
						<td>
							<input id="pushYn1" name="" type="radio"><label for="pushYn1">사용</label>
							<input id="pushYn2" name="" type="radio" checked="checked"><label for="pushYn2">미사용</label>
						</td>
					</tr>
				</tbody>
				</table>
			</div>
			
			<div class="tableTitle">
				<h4>권한 설정</h4>
			</div>
			<div class="tableBox">
				<table class="list">
				<caption></caption>
				<thead>
					<tr>
						<th>목록</th>
						<th>읽기</th>
						<th>쓰기</th>
						<th width="*">답변(사용자)</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							<label for="listGbn" class="soundOnly">목록</label>
							<select id="listGbn" name="" class="w90">
								<option value="" selected="selected">모두</option>
								<option value="">회원</option>
								<option value="">회원(본인게시물)</option>
								<option value="">관리자</option>
								<option value="">회원(청소년구정평가단)</option>
							</select>
						</td>
						<td>
							<label for="readGbn" class="soundOnly">읽기</label>
							<select id="readGbn" name="" class="w90">
								<option value="" selected="selected">모두</option>
								<option value="">회원</option>
								<option value="">회원(본인게시물)</option>
								<option value="">관리자</option>
								<option value="">회원(청소년구정평가단)</option>
							</select>
						</td>
						<td>
							<label for="writeGbn" class="soundOnly">쓰기</label>
							<select id="writeGbn" name="" class="w90">
								<option value="" selected="selected">모두</option>
								<option value="">회원</option>
								<option value="">관리자</option>
								<option value="">회원(청소년구정평가단)</option>
							</select>
						</td>
						<td>
							<label for="answerGbn" class="soundOnly">답변(사용자)</label>
							<select id="answerGbn" name="" class="w90">
								<option value="" selected="selected">모두</option>
								<option value="">회원</option>
								<option value="">회원(본인게시물)</option>
								<option value="">관리자</option>
								<option value="">회원(청소년구정평가단)</option>
							</select>
						</td>
					</tr>
				</tbody>
				</table>
			</div>
			<div class="tableTitle">
				<h4>항목 및 속성 설정</h4>
				<div class="btnArea">
					<a href="#" class="btn_inline">저장</a>
					<a href="#" class="btn_inline">삭제</a>
				</div>
			</div>
			<div class="tableBox scrolled h380">
				<table class="list">
				<colgroup>
					<col style="width:60px;">
					<col>
					<col>
					<col>
					<col>
					<col style="width:50px;">
					<col>
					<col style="width:50px;">
					<col style="width:50px;">
					<col style="width:50px;">
					<col style="width:50px;">
					<col style="width:50px;">
					<col>
				</colgroup>
				<thead>
					<tr>
						<th rowspan="2">번호</th>
						<th rowspan="2">필드명</th>
						<th rowspan="2">컬럼선택</th>
						<th colspan="2">설정</th>
						<th colspan="4">목록</th>
						<th colspan="2">등록</th>
						<th rowspan="2">보기</th>
						<th rowspan="2">삭제</th>
					</tr>
					<tr>
						<th>형식</th>
						<th>아이템</th>
						<th>사용</th>
						<th>넓이</th>
						<th>모바일</th>
						<th>검색</th>
						<th>사용</th>
						<th>필수</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							<label for="labelOrdNoArr0" class="soundOnly">번호</label>
							<input type="text" name="" id="labelOrdNoArr0" value="" class="w100p">
						</td>
						<td>
							<label for="labelNameArr0" class="soundOnly">필드명</label>
							<input type="text" name="" id="labelNameArr0" value="" class="w100p">
						</td>
						<td>
							<label for="contentMappingArr0" class="soundOnly">컬럼선택</label>
							<select name="" id="contentMappingArr0" class="w100p" >
								<option value="">선택</option>
								<option value="">글번호</option>
								<option value="">제목</option>
								<option value="">내용</option>
								<option value="">내용2</option>
								<option value="">성명</option>
								<option value="">주소</option>
								<option value="">이메일</option>
								<option value="">전화번호</option>
								<option value="">이동전화</option>
								<option value="">등록일</option>
								<option value="">조회수</option>
								<option value="">동영상링크</option>
								<option value="">썸네일</option>
								<option value="">첨부파일</option>
								<option value="">멀티파일</option>
								<option value="">자막</option>
								
								<option value="">공개여부</option>
								<option value="">답변여부</option>
								<option value="">답변완료통보</option>
								<option value="">진행상태</option>
								<option value="">담당자이름</option>
								<option value="">담당자연락처</option>
								<option value=>카테고리</option>
								
								<option value="">부서명</option>
								<option value="">EXT1</option>
								<option value="">EXT2</option>
								<option value="">EXT3</option>
								<option value="">EXT4</option>
								<option value="">EXT5</option>
								<option value="">EXT6</option>
								<option value="">EXT7</option>
								<option value="">EXT8</option>
								<option value="">EXT9</option>
								<option value="">EXT10</option>
								<option value="">바로가기</option>
								<option value="">비밀번호</option>
							</select>
						</td>
						<td>
							<label for="labelPropGbnArr0" class="soundOnly">그룹</label>
							<select name="" id="labelPropGbnArr0" class="w100p">
								<option value="">선택</option>
								<option value="">Text Box</option>
								<option value="">Text Area</option>
								<option value="">Select Box</option>
								<option value="">Radio Box</option>
								<option value="">Check Box</option>
								<option value="">Writer</option>
								<option value="">Address</option>
								<option value="">Tel</option>
								<option value="">Phone</option>
								<option value="">Email</option>
								<option value="">Link</option>
								<option value="">File</option>
								<option value="">MutiFile</option>
								<option value="">Calendar</option>
								<option value="">Keyword</option>
								<option value="">Department</option>
								<option value="">Password</option>
							</select>
						</td>
						<td>
							<label for="itemCodeArr0" class="soundOnly">아이템코드</label>
							<select name="" id="itemCodeArr0" class="w100p">
								<option value="">선택</option>
								<option value="">민원통보방법</option>
								<option value="">답변여부</option>
								<option value="">공개여부</option>
								<option value="">행정정보사전공표분류</option>
								<option value="">행사대상</option>
								<option value="">포토갤러리</option>
								<option value="">민원별분류</option>
								<option value="">학습동아리</option>
								<option value="">보건소업소현황</option>
								<option value="">재산현황</option>
								<option value="">지목</option>
								<option value="">양천뉴스구분</option>
								<option value="">국공유재산관리분류(장소)</option>
								<option value="">사전정보공표</option>
								<option value="">진행상태</option>
								<option value="">청소년-건의사항</option>
								<option value="">청소년-자유게시판</option>
							</select>
						</td>
						<td>
							<label for="webUseYn0" class="soundOnly">웹</label>
							<input type="checkbox" name="" id="webUseYn0">
							<input type="hidden" name="">
						</td>
						<td>
							<label for="labelProvSizeArr0" class="soundOnly">넓이</label>
							<input type="text" name="" id="labelProvSizeArr0" value="" class="w100p">
						</td>
						<td>
							<label for="mobileUseYn0" class="soundOnly">모바일</label>
							<input type="checkbox" name="" id="mobileUseYn0">
							<input type="hidden" name="">
						</td>
						<td>
							<label for="searchLabelUseYn0" class="soundOnly">검색</label>
							<input type="checkbox" name="" id="searchLabelUseYn0">
							<input type="hidden" name="">
						</td>
						<td>
							<label for="regUseYn0" class="soundOnly">등록</label>
							<input type="checkbox" name="" id="regUseYn0">
							<input type="hidden" name="">
						</td>
						<td>
							<label for="labelCompYn0" class="soundOnly">필수</label>
							<input type="checkbox" name="" id="labelCompYn0">
							<input type="hidden" name="">
						</td>
						<td>
							<label for="viewUseYn0" class="soundOnly">사용</label>
							<input type="checkbox" name="" id="viewUseYn0">
							<input type="hidden" name="">
						</td>
						<td><a href="#" onclick="doConfigPropertyRmv(this);return false;" class="btn_ss btn_delete">삭제</a></td>
					</tr>
					<tr>
						<td colspan="13"><a href="#" onclick="doConfigPropertyAdd(this);return false;" class="btn_inline btn_add">항목추가</a></td>
					</tr>
				</tbody>
				</table>
			</div>
			<div class="tableTitle">
				<h4>게시판 추가</h4>
				<div class="btnArea">
					<a href="#" class="btn_inline">추가</a>
				</div>
			</div>
			<div class="tableBox">
				<table class="form">
				<caption>게시판 추가</caption>
				<colgroup>
					<col style="width:15%;">
					<col style="width:35%;">
					<col style="width:15%;">
					<col style="width:35%;">
				</colgroup>
				<tbody>
					<tr>
						<th><span class="required">*</span> <label for="text">추가옵션</label></th>
						<td colspan="3">
							<label class="linked"><input name="" title="동일 위치에 추가" id="sameLevel1" type="radio"> 동일 위치에 추가</label>
							<label class="linked"><input name="" title="하위에 추가" id="sameLevel2" type="radio"> 하위에 추가</label>
						</td>
					</tr>	
					<tr>
						<th><span class="required">*</span> <label for="nodeId">코드</label></th>
						<td>
							<input type="text" id="cbCd" name="" maxlength="20"> 
							<a href="#" class="btn_inline">중복체크</a>
						</td>
						<th><span class="required">*</span> <label for="text">게시판 이름</label></th>
						<td><input type="text" id="cbName" name="" size="50" maxlength="51"></td>
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
			게시판을 관리하는 메뉴입니다.<br />
			게시물 관리/수정/삭제가 가능합니다.
		</p>
		<h6>게시물 관리</h6>
		<ul>
			<li>우측 상단의 [게시물 관리] 버튼을 클릭합니다.</li>
		</ul>
		<h6>게시판 수정</h6>
		<ul>
			<li>트리에서 수정할 게시판을 클릭합니다.</li>
			<li>우측 폼을 통해서 정보를 수정합니다.</li>
		</ul>
		<h6>게시판 삭제</h6>
		<ul>
			<li>트리에서 삭제할 게시판를 클릭합니다.</li>
			<li>우측 상단의 [삭제]버튼을 통해서 삭제합니다.</li>
		</ul>
	</div>
	<a href="#" class="btn_asideToggle">메뉴얼 열고닫기</a>
</div>
<!-- //메뉴얼영역 -->

<jsp:include page="/html/cms/layout/footer.jsp"></jsp:include>