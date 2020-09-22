<%@ page contentType="text/html; charset=UTF-8"%><%
/*
* subject: PHOTO 페이지
* @original author: SearchTool
*/
	thisCollection = "PHOTO";
	if (collection.equals("ALL") || collection.equals(thisCollection)) {
		int count = wnsearch.getResultCount(thisCollection);
		int thisTotalCount = wnsearch.getResultTotalCount(thisCollection);

		if ( thisTotalCount > 0 ) {
%>
			<!-- 이미지 -->
			<div class="search-sub-wrap">
				<article class="content-row images">
					<h2>이미지 <span class="search-result-amout">[총 <strong class="text-point"><%=numberFormat(thisTotalCount)%></strong>건]</span></h2>

					<div class="search-result-wrap">
						<ul class="search-result-list">
<%
			for(int idx = 0; idx < count; idx ++) {
                String DOCID                    = wnsearch.getField(thisCollection,"DOCID",idx,false);
                String DATE                     = wnsearch.getField(thisCollection,"DATE",idx,false);
                String TITLE                    = wnsearch.getField(thisCollection,"TITLE",idx,false);
                String TITLE_NO                    = wnsearch.getField(thisCollection,"TITLE",idx,false);
                String CONTENT                  = wnsearch.getField(thisCollection,"CONTENT",idx,false);
                String WRITER                   = wnsearch.getField(thisCollection,"WRITER",idx,false);
                String LINK                     = wnsearch.getField(thisCollection,"LINK",idx,false);
                String FILE_PATH                = wnsearch.getField(thisCollection,"FILE_PATH",idx,false);
                String ALIAS                    = wnsearch.getField(thisCollection,"ALIAS",idx,false);
				TITLE_NO= wnsearch.getKeywordHl(TITLE_NO,"","");
				TITLE= wnsearch.getKeywordHl(TITLE,"<span class='txt-point'>","</span>");
				CONTENT= wnsearch.getKeywordHl(CONTENT,"<span class='txt-point'>","</span>");
				WRITER= wnsearch.getKeywordHl(WRITER,"<span class='txt-point'>","</span>");
            String URL = "URL 정책에 맞게 작성해야 합니다.";

%>
				<li>
					<div class="search-img-box">
<%-- 						<a href="#" class="search-img"><img src="<%=FILE_PATH %>" alt="<%=TITLE_NO%>" /></a> --%>
						<a href="<%=LINK %>" class="search-img"><img src="<%=FILE_PATH %>" alt="<%=TITLE_NO%>" /></a>
						<div class="img-caption">
							<h3><a href="<%=LINK %>"><%=TITLE %></a></h3>
							<p class="reg-date"><a href="#">등록일 : <%=DATE %></a></p>
						</div>
					</div>
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