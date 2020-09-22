<%@ page contentType="text/html; charset=UTF-8"%><%
/*
* subject: MINWON 페이지
* @original author: SearchTool
*/
	thisCollection = "MINWON";
	if (collection.equals("ALL") || collection.equals(thisCollection)) {
		int count = wnsearch.getResultCount(thisCollection);
		int thisTotalCount = wnsearch.getResultTotalCount(thisCollection);

		if ( thisTotalCount > 0 ) {
%>
			<div class="search-sub-wrap">
				<article class="content-row minwon">
					<h2>민원사무편람 <span class="search-result-amout">[총 <strong class="text-point"><%=numberFormat(thisTotalCount)%></strong>건]</span></h2>
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
                String FILE_PATH                = wnsearch.getField(thisCollection,"FILE_PATH",idx,false);
                String ALIAS                    = wnsearch.getField(thisCollection,"ALIAS",idx,false);
                TITLE= wnsearch.getKeywordHl(TITLE,"<span class='txt-point'>","</span>");
				CONTENT= wnsearch.getKeywordHl(CONTENT,"<span class='txt-point'>","</span>");
				WRITER= wnsearch.getKeywordHl(WRITER,"<span class='txt-point'>","</span>");
            String URL = "URL 정책에 맞게 작성해야 합니다.";
            
            int index = FILE_PATH.lastIndexOf(".");
            String fileExtension = FILE_PATH.substring(index + 1);
            if(fileExtension.equals("hwp")){
            	fileExtension = "hangul";
            }else if(fileExtension.equals(".docx") || fileExtension.equals(".doc") ){
            	fileExtension = "ms-word";
            }else if(fileExtension.equals(".pptx") || fileExtension.equals(".ppt") ){
            	fileExtension = "ms-ppt";
            }else if(fileExtension.equals(".xlsx") || fileExtension.equals(".xls") ){
            	fileExtension = "ms-excel";
            }else if(fileExtension.equals(".pdf") || fileExtension.equals(".pdf") ){
            	fileExtension = "pdf";
            }else if(fileExtension.equals(".zip") || fileExtension.equals(".zip") ){
            	fileExtension = "zip";
            }else if(fileExtension.equals(".img") || fileExtension.equals(".img") ){
            	fileExtension = "img";
            }else{
            	fileExtension = "doc";
            }

%>
<!-- 적용 후 반드시 삭제해주세요 !

* link file type class
	hangul
	ms-word
	ms-excel
	ms-ppt
	pdf
	zip
	img
	doc : ms-word를 제외한 모든 문서 또는 텍스트 파일타입

-->
				<li>
					<a href="<%=FILE_PATH %>" class="minwon-subject"><%=TITLE %></a>
					<a href="<%=FILE_PATH %>" class="file <%=fileExtension%>"><!-- 첨부되는 파일 형태에 따라 앞에 class명을 위 주석 참조하여 변경해주세요. --><span class="hidden"><%=FILE_PATH %> 다운로드</span></a>
					<p class="minwon-depart"><%=WRITER %></p>
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