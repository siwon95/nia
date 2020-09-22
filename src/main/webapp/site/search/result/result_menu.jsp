<%@ page contentType="text/html; charset=UTF-8"%><%
/*
* subject: MENU 페이지
* @original author: SearchTool
*/
	thisCollection = "MENU";
	if (collection.equals("ALL") || collection.equals(thisCollection)) {
		int count = wnsearch.getResultCount(thisCollection);
		int thisTotalCount = wnsearch.getResultTotalCount(thisCollection);

		if ( thisTotalCount > 0 ) {
%>
			<div class="search-sub-wrap">
				<article class="content-row menu">
					<h2>메뉴검색 <span class="search-result-amout">[총 <strong class="text-point"><%=numberFormat(thisTotalCount)%></strong>건]</span></h2>
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
                String SITE_CD                  = wnsearch.getField(thisCollection,"SITE_CD",idx,false);
                String SITE_NM                  = wnsearch.getField(thisCollection,"SITE_NM",idx,false);
                String ALIAS                    = wnsearch.getField(thisCollection,"ALIAS",idx,false);
				TITLE= wnsearch.getKeywordHl(TITLE,"<span class='txt-point'>","</span>");
				CONTENT= wnsearch.getKeywordHl(CONTENT,"<span class='txt-point'>","</span>");
				WRITER= wnsearch.getKeywordHl(WRITER,"<span class='txt-point'>","</span>");
            String URL = "URL 정책에 맞게 작성해야 합니다.";

%>
			<li><a href="<%=LINK%>"><%=TITLE %></a></li>
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