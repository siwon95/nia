<%@ page contentType="text/html; charset=UTF-8"%><%
/*
* subject: EMPLOYEE 페이지
* @original author: SearchTool
*/
	thisCollection = "EMPLOYEE";
	if (collection.equals("ALL") || collection.equals(thisCollection)) {
		int count = wnsearch.getResultCount(thisCollection);
		int thisTotalCount = wnsearch.getResultTotalCount(thisCollection);

		if ( thisTotalCount > 0 ) {
%>
		<div class="search-sub-wrap">
				<article class="content-row member">
					<h2>직원/업무 <span class="search-result-amout">[총 <strong class="text-point"><%=numberFormat(thisTotalCount)%></strong>건]</span></h2>
					<div class="search-result-wrap">
						<table class="search-employee">
						<caption>직원/업무 검색결과</caption>
						<thead>
						<tr>
							<th class="th-cell1">직책</th>
							<th class="th-cell2">직원명</th>
							<th class="th-cell3">부서명</th>
							<th class="th-cell4">연락처</th>
							<th class="th-cell5">업무내용</th>
						</tr>
						</thead>
						<tbody>

						<!-- <ul class="search-result-list"> -->
<%
			for(int idx = 0; idx < count; idx ++) {
                String DOCID                    = wnsearch.getField(thisCollection,"DOCID",idx,false);
                String DATE                     = wnsearch.getField(thisCollection,"DATE",idx,false);
                String TITLE                    = wnsearch.getField(thisCollection,"TITLE",idx,false);
                String CONTENT                  = wnsearch.getField(thisCollection,"CONTENT",idx,false);
                String WRITER                   = wnsearch.getField(thisCollection,"WRITER",idx,false);
                String LINK                     = wnsearch.getField(thisCollection,"LINK",idx,false);
                String DEPARTMENT               = wnsearch.getField(thisCollection,"DEPARTMENT",idx,false);
                String TEL                      = wnsearch.getField(thisCollection,"TEL",idx,false);
                String JOBLEVEL                 = wnsearch.getField(thisCollection,"JOBLEVEL",idx,false);
                String ALIAS                    = wnsearch.getField(thisCollection,"ALIAS",idx,false);
                TITLE= wnsearch.getKeywordHl(TITLE,"<span class='txt-point'>","</span>");
				CONTENT= wnsearch.getKeywordHl(CONTENT,"<span class='txt-point'>","</span>");
				WRITER= wnsearch.getKeywordHl(WRITER,"<span class='txt-point'>","</span>");
				DEPARTMENT= wnsearch.getKeywordHl(DEPARTMENT,"<span class='txt-point'>","</span>");
				JOBLEVEL= wnsearch.getKeywordHl(JOBLEVEL,"<span class='txt-point'>","</span>");
            String URL = "URL 정책에 맞게 작성해야 합니다.";
            if(JOBLEVEL.equals("")){
            	JOBLEVEL = "주무관";
            }

%>
						<tr>
							<td class="td-cell1"><%=JOBLEVEL %></td>
							<td class="td-cell2"><%=TITLE %></td>
							<td class="td-cell3"><%=DEPARTMENT %></td>
							<td class="td-cell4"><%=TEL %></td>
							<td class="td-cell5"><%=CONTENT %></td>
						</tr>
<%-- 
				<li>
					<h3><%=JOBLEVEL %> <%=TITLE %></h3>
					<ul class="member-wrap">
						<li class="team"><%=DEPARTMENT %></li>
						<%if(!TEL.equals("")) {%>
						<li class="phone">☎ <%=TEL %></li>
						<%} %>
						<li class="duty">업무내용  :  <%=CONTENT %></li>
					</ul>
				</li>
 --%>
	<%}%>
						</tbody>
						</table>
<!-- 			</ul> -->
		</div>
		<%if ( collection.equals("ALL") && thisTotalCount > TOTALVIEWCOUNT ) {%>
			<a href="#more-view" class="more-view" onClick="javascript:doCollection('<%=thisCollection%>');">검색결과 더보기</a>
		<%}%>
				</article>
			</div>
			<%
		}
	}
%>