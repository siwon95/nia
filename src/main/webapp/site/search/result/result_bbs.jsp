<%@ page contentType="text/html; charset=UTF-8"%><%
/*
* subject: BBS 페이지
* @original author: SearchTool
*/
	thisCollection = "BBS";
	if (collection.equals("ALL") || collection.equals(thisCollection)) {
		int count = wnsearch.getResultCount(thisCollection);
		int thisTotalCount = wnsearch.getResultTotalCount(thisCollection);

		if ( thisTotalCount > 0 ) {
%>
			<div class="search-sub-wrap">
				<article class="content-row bbs">
					<h2>게시판 <span class="search-result-amout">[총 <strong class="text-point"><%=numberFormat(thisTotalCount)%></strong>건]</span></h2>
					<div class="search-result-wrap">
						<ul class="search-result-list">
<%
			for(int idx = 0; idx < count; idx ++) {
                String DOCID                    = wnsearch.getField(thisCollection,"DOCID",idx,false);
                String DATE                     = wnsearch.getField(thisCollection,"DATE",idx,false);
                String TITLE                    = wnsearch.getField(thisCollection,"TITLE",idx,false);
                String CONTENT                  = wnsearch.getField(thisCollection,"CONTENT",idx,false);
                String WRITER                   = wnsearch.getField(thisCollection,"WRITER",idx,false);
                String LINK                     = wnsearch.getField(thisCollection,"LINK",idx,false);
                String CATEGORY                 = wnsearch.getField(thisCollection,"CATEGORY",idx,false);
                String ALIAS                    = wnsearch.getField(thisCollection,"ALIAS",idx,false);
                TITLE= wnsearch.getKeywordHl(TITLE,"<span class='txt-point'>","</span>");
				CONTENT= wnsearch.getKeywordHl(CONTENT,"<span class='txt-point'>","</span>");
				WRITER= wnsearch.getKeywordHl(WRITER,"<span class='txt-point'>","</span>");
            String URL = "URL 정책에 맞게 작성해야 합니다.";

%>
				<li>
					<h3><a href="<%=LINK%>"><%=TITLE %></a> <span class="date"><%=DATE %></span><a href="#none" class="new-win-view" onclick="doPopup('<%=LINK%>')">새창보기</a></h3>
					<p class="search-txt"><a href="#"><%=CONTENT %></a></p>
					<p class="search-loc"><%=CATEGORY %></p>
				</li>
 <%}%>
			</ul>
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