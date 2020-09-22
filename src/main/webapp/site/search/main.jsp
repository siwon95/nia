<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="./common/api/WNSearch.jsp" %><% request.setCharacterEncoding("UTF-8");%><%
/*
 * subject: 검색 메인 페이지
 * @original author: SearchTool
 */

//실시간 검색어 화면 출력 여부 체크
boolean isRealTimeKeyword = false;
//오타 후 추천 검색어 화면 출력 여부 체크
boolean useSuggestedQuery = true;
String suggestQuery = "";

//디버깅 보기 설정
boolean isDebug = false;

int TOTALVIEWCOUNT = 3;    //통합검색시 출력건수
int COLLECTIONVIEWCOUNT = 10;    //더보기시 출력건수

String START_DATE = "2011.01.01";	// 기본 시작일

// 결과 시작 넘버
int startCount = parseInt(getCheckReqXSS(request, "startCount", "0"), 0);	//시작 번호
String query = getCheckReqXSS(request, "query", "");						//검색어
String collection = getCheckReqXSS(request, "collection", "ALL");			//컬렉션이름
String rt = getCheckReqXSS(request, "rt", "");								//결과내 재검색 체크필드
String rt2 = getCheckReqXSS(request, "rt2", "");							//결과내 재검색 체크필드
   String reQuery = getCheckReqXSS(request, "reQuery", "");					//결과내 재검색 체크필드
String realQuery = getCheckReqXSS(request, "realQuery", "");				//결과내 검색어
String sort = getCheckReqXSS(request, "sort", "RANK");						//정렬필드
String range = getCheckReqXSS(request, "range", "A");						//기간관련필드
String startDate = getCheckReqXSS(request, "startDate", START_DATE);		//시작날짜
String endDate = getCheckReqXSS(request, "endDate", getCurrentDate());		//끝날짜
   String writer = getCheckReqXSS(request, "writer", "");						//작성자
   String searchField = getCheckReqXSS(request, "searchField", "");			//검색필드
   String strOperation  = "" ;												//operation 조건 필드
String exquery = "" ;													//exquery 조건 필드
int totalCount = 0;

String[] searchFields = null;
// 상세검색 검색 필드 설정이 되었을때
if (!searchField.equals("")) {
	// 작성자
// 	if (!writer.equals("")) {
// 		exquery = "<WRITER:" + writer + ">";
// 	}
} else {
	searchField = "ALL";
}

String[] collections = null;
if(collection.equals("ALL")) { //통합검색인 경우
    collections = COLLECTIONS;
} else {                        //개별검색인 경우
    collections = new String[] { collection };
}

if (reQuery.equals("1")) {
	realQuery = query + " " + realQuery;
} else if (!reQuery.equals("2")) {
	realQuery = query;
}

WNSearch wnsearch = new WNSearch(isDebug,false, collections, searchFields);

int viewResultCount = COLLECTIONVIEWCOUNT;
if ( collection.equals("ALL") ||  collection.equals("") )
    viewResultCount = TOTALVIEWCOUNT;

for (int i = 0; i < collections.length; i++) {

    //출력건수
    wnsearch.setCollectionInfoValue(collections[i], PAGE_INFO, startCount+","+viewResultCount);

    //검색어가 없으면 DATE_RANGE 로 전체 데이터 출력
    if (!query.equals("") ) {
          wnsearch.setCollectionInfoValue(collections[i], SORT_FIELD, sort + "/DESC");
    } else {
          wnsearch.setCollectionInfoValue(collections[i], DATE_RANGE, START_DATE.replaceAll("[.]","/") + ",2030/12/31,-");
          wnsearch.setCollectionInfoValue(collections[i], SORT_FIELD, "RANK/DESC,DATE/DESC");
    }

    //searchField 값이 있으면 설정, 없으면 기본검색필드
    if (!searchField.equals("") && searchField.indexOf("ALL") == -1 ) {
		wnsearch.setCollectionInfoValue(collections[i], SEARCH_FIELD, searchField);
	}

    //operation 설정
    if (!strOperation.equals("")) {
		wnsearch.setCollectionInfoValue(collections[i], FILTER_OPERATION, strOperation);
	}

    //exquery 설정
    if (!exquery.equals("")) {
		wnsearch.setCollectionInfoValue(collections[i], EXQUERY_FIELD, exquery);
	}

    //기간 설정 , 날짜가 모두 있을때
    if (!startDate.equals("")  && !endDate.equals("") ) {
         wnsearch.setCollectionInfoValue(collections[i], DATE_RANGE, startDate.replaceAll("[.]","/") + "," + endDate.replaceAll("[.]","/") + ",-");
    }
    if(query.equals("")){
    	  wnsearch.setCollectionInfoValue(collections[i], DATE_RANGE, "2030/12/30" + ",2030/12/31,-");
    }
};

wnsearch.search(realQuery, isRealTimeKeyword, CONNECTION_CLOSE, useSuggestedQuery);

 // 디버그 메시지 출력
String debugMsg = wnsearch.printDebug() != null ? wnsearch.printDebug().trim() : "";
if ( !debugMsg.trim().equals("")) {
     out.println(debugMsg);
}
int themeCount =0;
 // 전체건수 구하기
if ( collection.equals("ALL") ) {
    for (int i = 0; i < collections.length; i++) {
    	if(!collections[i].equals("THEME")){
       		totalCount += wnsearch.getResultTotalCount(collections[i]);
    	}else{
    		themeCount =wnsearch.getResultTotalCount(collections[i]);
    	}
    }
} else {
  //개별건수 구하기
    totalCount = wnsearch.getResultTotalCount(collection);
}

String thisCollection = "";
if(useSuggestedQuery) {
   suggestQuery = wnsearch.suggestedQuery;
}
%>
<script type="text/javascript">
<!--
$(document).ready(function() {

    getPopkeyword('D');  //인기검색어
    getRecommend(); //추천검색어
	// 내가 찾은 검색어
	getMyKeyword("<%=query%>", <%=totalCount%>);
});
function getPopkeyword(reange) {

	var target		= "popword";
	var range		= reange;
	var collection  = "all";
    var datatype   = "text";
	$.ajax({
	  type: "POST",
	  url: "./trans/popword.jsp",
	  dataType: datatype,
	  data: { "target" : target, "range" : range, "collection" : collection , "datatype" : datatype },
	  error : function(a, b, c){
// 		alert(a);  
// 		alert(b);  
// 		alert(c);  
	  },
	  success: function(text) {
     text = trim(text);
     var xml = $.parseXML(text);
		var str = "<div class=\"popular-search-header\">";
		str += "	<h2>인기 검색어</h2>";
		str += "	<ul class=\"sort-tab\">";
		if(reange == "D"){
			str += "		<li><a href=\"#none\" class=\"on\" onclick=\"getPopkeyword('D')\" >일간</a></li>";
			str += "		<li><a href=\"#none\" onclick=\"getPopkeyword('W')\">주간</a></li>"; 
		}else{
			str += "		<li><a href=\"#none\"  onclick=\"getPopkeyword('D')\" >일간</a></li>";
			str += "		<li><a href=\"#none\" class=\"on\" onclick=\"getPopkeyword('W')\">주간</a></li>"; 
		}
		str += "	</ul>";
		str += "</div>";
		str += "<ol class=\"word-list\">";
		
		$(xml).find("Query").each(function(){
 			str += "<li>";
 				str += "	<span class=\"num\">"+ $(this).attr("id")+"</span>";
 			str += "	<a href=\"#none\" onclick=\"javascript:doKeyword('" + $(this).text() + "');\">" + $(this).text() + "</a>";
			if ($(this).attr("updown") == "U") {
				str += "<span class=\"state up\">";
			} else if ($(this).attr("updown") == "D") {
				str += "<span class=\"state down\">";
			} else if ($(this).attr("updown") == "N") {
				str += "<span class=\"state new\">new";
			} else if ($(this).attr("updown") == "C") {
				str += "<span class=\"state stay\">";
			}
			if($(this).attr("count") == '-1' || $(this).attr("updown") == "N"){
 				str +=  "</span></li>"; 
 			}else{
				str +=  $(this).attr("count") + "</span></li>";
 			}
			
		});
			str += "</ol>";
		$("#popword").html(str);
	  }
	});

}
/**
 * 
 <h2>추천검색어 : </h2>
	<ul>
		<!-- 추천검색어 시작 -->
		<li><a href="#">통합예약</a></li>
		<li><a href="#">통합예약</a></li>
		<li><a href="#">정보화교육</a></li>
		<li><a href="#">동주민센터</a></li>
		<li><a href="#">예방접종</a></li>
		<li><a href="#">여권</a></li>
		<!-- 추천검색어 시작 -->
	</ul>
 */
function getRecommend() {
	var query		= '<%=query%>';
	var target		= "recommend";
// 	var range		= reange;
	var label  = "_ALL_";
    var datatype   = "text";
	$.ajax({
	  type: "POST", 
	  url: "./trans/popword.jsp",
	  dataType: datatype,
	  data: { "target" : target, "query" : query, "label" : label , "datatype" : datatype },
	  error : function(a, b, c){
		alert(a);  
		alert(b);  
		alert(c);  
	  },
	  success: function(text) {
     text = trim(text);
     var xml = $.parseXML(text);
     if($(xml).find("Return").text() == '1'){
			var str = "<h2>추천검색어 : </h2>";
			str += "<ul>";
			
			$(xml).find("Word").each(function(){
				str += "<li><a href=\"#none\" onclick=\"javascript:doKeyword('" + $(this).text() + "');\" >"+$(this).text()+"</a></li>";
			});
				str += "</ul>";
			$("#recommend").html(str);
		  }
	  }
	});

}
 function f_datepicker(obj){
		var str = "#"+obj;
		// 기간 달력 설정
		
		  try {
		$(str).datepicker({dateFormat: "yy.mm.dd",
			changeMonth: true,
			changeYear: true,
			yearRange: 'c-50:c+50',
			dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'],
			monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월']
		}).datepicker("show");
		  } catch(e) {
              alert(e.Message);     //에러발생시 에러메세지 출력
           }
// 			$("#startDate").datepicker({dateFormat: "yy.mm.dd"});
// 		 	$("#endDate").datepicker({dateFormat: "yy.mm.dd"});
	}
//-->

</script>
<div id="mid-top-container">
<!-- ******************************************************************************
	Middle Top Section
****************************************************************************** -->
	<div id="top-section">
	<form name="search" id="search" action="<%=request.getRequestURI()%>" method="POST">
		<input type="hidden" name="startCount" value="0">
		<input type="hidden" name="sort" value="<%=sort%>">
		<input type="hidden" name="collection" value="<%=collection%>">
		<input type="hidden" name="range" id="range" value="<%=range%>">
		<input type="hidden" name="startDate" value="<%=startDate%>">
		<input type="hidden" name="endDate"value="<%=endDate%>">
		<input type="hidden" name="searchField"value="<%=searchField%>">
		<input type="hidden" name="reQuery" />
		<input type="hidden" name="realQuery" value="<%=realQuery%>" />
		
		<div class="wide-fix">

			<h1 class="top-logo"><a href="/site/yangcheon/main.do"><img src="/images/lib/layout/top-h1-logo.png" alt="양천구 로고" /></a><a href="/site/search/main.jsp" class="totalSearch">통합검색</a></h1>

			<fieldset class="total-search-fieldset">
				<legend>통합검색</legend>
				<label for="total-search-select hidden"><span class="hidden">통합검색</span></label>
				<select id="total-search-select" class="total-search-select" onChange="doCollection(this.value)" >
					<option value="ALL" <%=collection.equals("ALL") ? "selected" : "" %>>통합검색</option>
					<option value="MENU" <%=collection.equals("MENU") ? "selected" : "" %>>메뉴검색</option>
					<option value="EMPLOYEE" <%=collection.equals("EMPLOYEE") ? "selected" : "" %>>직원/업무</option>
					<option value="MINWON" <%=collection.equals("MINWON") ? "selected" : "" %>>민원사무편람</option>
					<option value="CONTENTS" <%=collection.equals("CONTENTS") ? "selected" : "" %>>웹페이지</option>
					<option value="BBS" <%=collection.equals("BBS") ? "selected" : "" %>>게시판</option>
					<option value="PHOTO" <%=collection.equals("PHOTO") ? "selected" : "" %>>이미지</option>
					<option value="RESERVATION" <%=collection.equals("RESERVATION") ? "selected" : "" %>>통합예약</option>
				</select>
				<input type="text" placeholder="검색어를 입력하세요"  name="query"  id="query"   value="<%=query%>" onKeypress="javascript:pressCheck((event),this);" autocomplete='off'  title="검색어를 입력하세요."  class="total-search-input" />
<!-- 자동완성 시작 -->
				<div id="ark"></div>
<!-- // 자동완성 끝 -->
				<input type="image" src="/images/search/layout/icon-search.png" title="검색하기" onClick="doSearch()" class="total-search-submit" />
				<span class="re-search-check"><input type="checkbox" name="reChk" id="reChk" onClick="checkReSearch()"><label for="">결과 내 재검색</label></span>
			</fieldset>
			<div class="search-recomm-word" id="recommend">
				
			</div>
</form>
			<div class="mob-top-right">
<form name="search_mobile" id="search_mobile" action="<%=request.getRequestURI()%>" method="POST">
		<input type="hidden" name="startCount" value="0">
		<input type="hidden" name="sort" value="<%=sort%>">
		<input type="hidden" name="collection" value="<%=collection%>">
		<input type="hidden" name="range"  value="<%=range%>">
		<input type="hidden" name="startDate" value="<%=startDate%>">
		<input type="hidden" name="endDate"value="<%=endDate%>">
		<input type="hidden" name="searchField"value="<%=searchField%>">
		<input type="hidden" name="reQuery" />
		<input type="hidden" name="realQuery" value="<%=realQuery%>" />
				<a href="#mob-total-search" class="mob-total-search-trigger"><img src="/images/lib/layout/btn-mob-total-search.gif" alt="통합검색 열기" /></a>
<!-- Mobile Total Search -->
				<div class="mob-total-search-container"></div>
<!-- // Mobile Total Search -->
				<a href="#mob-total-menu" class="mob-total-menu-trigger"><img src="/images/lib/layout/btn-mob-menu-trigger.gif" alt="전체메뉴 열기" /></a>
<!--  Mobile Navigation Menu All View -->
				<nav class="total-menu-container">
					<h1 class="total-menu-title"><a href="#total-menu-close" title="전체메뉴 닫기">전체메뉴보기</a></h1>
					<ul class="total-menu-list"></ul>
				</nav>
<!-- // Mobile Navigation Menu All View -->
			</div>

		</div>

</form>
	</div>

	<div id="global-navigation-section">

		<div class="wide-fix">

			<nav class="global-navigation">

				<h1 class="hidden">Global Navigation Menu</h1>
				<!-- Global Navigation -->
				<ul class="gnb">
					<li class="gnb-dep1 gnb1"><a href="#none"  onClick="javascript:doCollection('ALL');" class="<%=collection.equals("ALL") ? "on" : "" %>">통합검색</a></li>
					<li class="gnb-dep1 gnb2"><a href="#none"  onClick="javascript:doCollection('MENU');" class="<%=collection.equals("MENU") ? "on" : "" %>">메뉴검색</a></li>
					<li class="gnb-dep1 gnb3"><a href="#none"  onClick="javascript:doCollection('EMPLOYEE');" class="<%=collection.equals("EMPLOYEE") ? "on" : "" %>">직원/업무</a></li>
					<li class="gnb-dep1 gnb4"><a href="#none"  onClick="javascript:doCollection('MINWON');" class="<%=collection.equals("MINWON") ? "on" : "" %>">민원사무편람</a></li>
					<li class="gnb-dep1 gnb5"><a href="#none"  onClick="javascript:doCollection('CONTENTS');" class="<%=collection.equals("CONTENTS") ? "on" : "" %>">웹페이지</a></li>
					<li class="gnb-dep1 gnb6"><a href="#none"  onClick="javascript:doCollection('BBS');" class="<%=collection.equals("BBS") ? "on" : "" %>">게시판</a></li>
					<li class="gnb-dep1 gnb7"><a href="#none"  onClick="javascript:doCollection('PHOTO');" class="<%=collection.equals("PHOTO") ? "on" : "" %>">이미지</a></li>
					<li class="gnb-dep1 gnb8"><a href="#none"  onClick="javascript:doCollection('RESERVATION');" class="<%=collection.equals("RESERVATION") ? "on" : "" %>">통합예약</a></li>
				</ul>
				<!-- // Global Navigation -->
			</nav>

		</div>

	</div>
</div>

<div id="mid-container">
	<div class="wide-fix">
		<section class="content-wrap-container">

			<h1 class="page-title"><%if(collection.equals("ALL")){ %>통합검색<%}else{ out.println(wnsearch.getCollectionKorName(collection)); }%></h1><!-- 메뉴마다 달라져야함  -->
			

			<div id="search-information-container">
				<div class="search-information">
					<span class="search-word"><strong>‘ <%=realQuery %> ’</strong>에 대한 검색 결과</span>
					<span class="search-num">[ 총 <strong><%=numberFormat(totalCount)%></strong>건 ]</span>
				</div>
				<div class="search-option-container">
					<a href="#search-option" class="btn-option">검색옵션</a>
				</div>
			</div>

			<div class="search-optional-container" style="display: none;">
				<div class="search-optional-1">
					<h2>검색정렬</h2>
					<a href="#none" onclick="doSorting('RANK');" class="<%=sort.equals("RANK") ? "on" : ""%>">정확도</a>
					<a href="#none" onclick="doSorting('DATE');"  class="<%=sort.equals("DATE") ? "on" : ""%>">최신순</a>
				</div>
					
				<div class="search-optional-2">
					<h2>영역</h2>
					<a href="#none" onClick="doSearchField('ALL');" class="<%=searchField.equals("ALL") ? "on" : ""%>">전체</a>
					<a href="#none" onClick="doSearchField('TITLE');" class="<%=searchField.equals("TITLE") ? "on" : ""%>"> 제목</a>
					<a href="#none" onClick="doSearchField('CONTENT');" class="<%=searchField.equals("CONTENT") ? "on" : ""%>">내용</a>
					<a href="#none" onClick="doSearchField('WRITER');" class="<%=searchField.equals("WRITER") ? "on" : ""%>"> 작성자</a>
				</div>
					
				<div class="search-optional-3">
					<h2>검색기간</h2>
					<a href="#none" onClick="javascript:setDate('A');" id="range_A" class="<%=range.equals("A") ? "on" : ""%>" >전체</a>
					<a href="#none" onClick="javascript:setDate('W');" id="range_W" class="<%=range.equals("W") ? "on" : ""%>" >1주</a>
					<a href="#none" onClick="javascript:setDate('M');" id="range_M" class="<%=range.equals("M") ? "on" : ""%>" >1개월</a>
					<a href="#none" onClick="javascript:setDate('Y');" id="range_Y" class="<%=range.equals("Y") ? "on" : ""%>" >1년</a>
					<span class="start-date-wrap">
<%-- 						<input type="text" id="" maxlength="10" class="search-start-date" name="startDate"  id="startDate"  value="<%=startDate%>" title="검색시작 년월일을 입력해주세요" /> --%>
<%-- 						<input name="startDate" id="startDate" type="text" value="<%=startDate %>" readonly="true"/> --%>
						<input type="text" maxlength="10" class="search-start-date" name="stDate"  id="startDate"  value="<%=startDate%>" title="검색시작 년월일을 입력해주세요" />
						<a href="#calendar" class="calendar-start"  title="달력에서 검색 시작일 선택" onclick="f_datepicker('startDate')"><img src="/images/search/layout/icon-calendar.gif" alt="달력 아이콘" /></a>
					</span>
					~
					<span class="end-date-wrap">
						<input type="text"  maxlength="10" class="search-end-date" name="enDate"  id="endDate"  value="<%=endDate%>" title="검색종료 년월일을 입력해주세요" />
						<a href="#calendar" class="calendar-end"   title="달력에서 검색 종료일 선택" onclick="f_datepicker('endDate')" ><img src="/images/search/layout/icon-calendar.gif" alt="달력 아이콘" /></a>
					</span>
					<input type="submit" id="" class="btn-search-date" value="검색" title="검색하기" onclick="javascript:doDeSearch()" />
				</div>
			</div>
<!-- 검색 메인에서만 노출 시작 -->
		<%if(collection.equals("ALL")){ %>
			<div class="search-option-result">
				<ul class="search-option-list">
					<li><a href="#none" onClick="javascript:doCollection('MENU');" >메뉴검색 [<%=numberFormat(wnsearch.getResultTotalCount("MENU")) %>]</a></li>
					<li><a href="#none" onClick="javascript:doCollection('EMPLOYEE');" >직원/업무 [<%=numberFormat(wnsearch.getResultTotalCount("EMPLOYEE")) %>]</a></li>
					<li><a href="#none" onClick="javascript:doCollection('MINWON');" >민원사무편람 [<%=numberFormat(wnsearch.getResultTotalCount("MINWON")) %>]</a></li>
					<li><a href="#none" onClick="javascript:doCollection('CONTENTS');" >웹페이지 [<%=numberFormat(wnsearch.getResultTotalCount("CONTENTS")) %>]</a></li>
					<li><a href="#none" onClick="javascript:doCollection('BBS');" >게시판 [<%=numberFormat(wnsearch.getResultTotalCount("BBS")) %>]</a></li>
					<li><a href="#none" onClick="javascript:doCollection('PHOTO');" >이미지 [<%=numberFormat(wnsearch.getResultTotalCount("PHOTO")) %>]</a></li>
					<li><a href="#none" onClick="javascript:doCollection('RESERVATION');" >통합예약 [<%=numberFormat(wnsearch.getResultTotalCount("RESERVATION")) %>]</a></li>
				</ul>
			</div>
			<%} %>
<!-- 검색 메인에서만 노출 끝 --> 
			<article id="content-container">
			<%if(themeCount > 0){ 
                String DOCID                    = wnsearch.getField("THEME","DOCID",0,false);
                String DATE                     = wnsearch.getField("THEME","DATE",0,false);
                String TITLE                    = wnsearch.getField("THEME","TITLE",0,false);
                String CONTENT                  = wnsearch.getField("THEME","CONTENT",0,false);
                String TM_KEYWORD                    = wnsearch.getField("THEME","TM_KEYWORD",0,false);
                String TM_URL                    = wnsearch.getField("THEME","TM_URL",0,false);
                TITLE= wnsearch.getKeywordHl(TITLE,"<span class='txt-point'>","</span>");
                %>
			<div class="search-sub-wrap">
			
				테마검색
				<article class="content-row theme-box">
					<h2 class="hidden">민원편람(민원서식)</h2>
					<div class="h2-box"> 
<!-- 						<div class="left-cont"> -->
<!-- 							<img src="/images/search/temp/temp-site1.jpg" alt="" /> -->
<!-- 						</div> -->
						<div class="right-cont">
							<h3>[ 테마검색 ] <%if(!TM_URL.equals("")){ %><a href="<%=TM_URL%>" target="_blank"><%}else{%><a href="#none" ><%}%><%=TITLE %></a></h3>
							<ul class="theme-infor-list">
							<%=CONTENT %>
<!-- 								<li><span class="title">소개</span><span class="txt-cont">대한민국 대표 복지포털 복지로 홈페이지입니다. 복지로는 다양한 복지제도 정보와 맞춤형 복지서비스를 간편히 검색하고 생활이 힘겨운 분들과 이웃 모두가 도움을 신청하거나 상담하실 수 있습니다.</span></li>  -->
<!-- 								<li><span class="title">홈페이지</span><span class="txt-cont anchor"><a href="http://www.bokjiro.go.kr" target="_blank">http://www.bokjiro.go.kr</a></span></li> -->
<!-- 								<li><span class="title">담당부서</span><span class="txt-cont">보건복지부</span></li> -->
<!-- 								<li><span class="title">문의전화</span><span class="txt-cont">☎ 129</span></li> -->
							</ul>
						</div>
					</div>
				</article>
				
			</div>
			<%} %>

<%if(totalCount > 0 ){ %>
	<%@ include file="./result/result_menu.jsp" %><!-- -->
	<%@ include file="./result/result_employee.jsp" %><!-- -->
	<%@ include file="./result/result_minwon.jsp" %><!-- -->
	<%@ include file="./result/result_contents.jsp" %><!-- -->
	<%@ include file="./result/result_bbs.jsp" %><!-- -->
	<%@ include file="./result/result_photo.jsp" %><!-- -->
	<%@ include file="./result/result_reservation.jsp" %><!-- -->
	<% if (!collection.equals("ALL") && totalCount > TOTALVIEWCOUNT) { %>
		<%=wnsearch.getPageLinks(startCount , totalCount, 10, 10)%>
	<% } %>
<%}else{ %>
	<!-- 검색 결과가 없을때 -->
<!-- 	<div>검색 결과가 없습니다. </div>  -->
		<div class="no-reault">
			검색결과가 없습니다.
		</div>
<%} %>
				
<%
	if ( wnsearch != null ) {
		wnsearch.closeServer();
	}
%>