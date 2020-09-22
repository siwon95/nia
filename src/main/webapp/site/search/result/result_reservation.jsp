<%@ page contentType="text/html; charset=UTF-8"%><%
/*
* subject: RESERVATION 페이지
* @original author: SearchTool
*/
	thisCollection = "RESERVATION";
	if (collection.equals("ALL") || collection.equals(thisCollection)) {
		int count = wnsearch.getResultCount(thisCollection);
		int thisTotalCount = wnsearch.getResultTotalCount(thisCollection);

		if ( thisTotalCount > 0 ) {
%>
				<!-- 통합예약 -->
			<div class="search-sub-wrap">
				<article class="content-row reservation">
					<h2>통합예약 <span class="search-result-amout">[총 <strong class="text-point"><%=numberFormat(thisTotalCount)%></strong>건]</span></h2>
					
					<div class="search-result-wrap">
						<div class="border-top">
							<p class="left-infomation">
								<span class="online">온라인접수</span>:
								<span class="site">현장접수</span>:
								<span class="tel">전화접수</span>:
								<span class="other">별도사이트</span>
							</p>
						</div>
						
						<div class="card-list-container">
							<ul class="card-list">
<%
			for(int idx = 0; idx < count; idx ++) {
                String DOCID                    = wnsearch.getField(thisCollection,"DOCID",idx,false);
                String DATE                     = wnsearch.getField(thisCollection,"DATE",idx,false);
                String TITLE                    = wnsearch.getField(thisCollection,"TITLE",idx,false);
                String CONTENT                  = wnsearch.getField(thisCollection,"CONTENT",idx,false);
                String WRITER                   = wnsearch.getField(thisCollection,"WRITER",idx,false);
                String LINK                     = wnsearch.getField(thisCollection,"LINK",idx,false);
                String RI_PLACE                 = wnsearch.getField(thisCollection,"RI_PLACE",idx,false);
                String RI_TYPE                  = wnsearch.getField(thisCollection,"RI_TYPE",idx,false);
                String RI_SUPERVISION_ORG       = wnsearch.getField(thisCollection,"RI_SUPERVISION_ORG",idx,false);
                String RI_SUPERVISION_ORG_NAME  = wnsearch.getField(thisCollection,"RI_SUPERVISION_ORG_NAME",idx,false);
                String RI_CONFIRM_YN            = wnsearch.getField(thisCollection,"RI_CONFIRM_YN",idx,false);
                String RI_MANAGE_DEPT           = wnsearch.getField(thisCollection,"RI_MANAGE_DEPT",idx,false);
                String RI_PROGRESS              = wnsearch.getField(thisCollection,"RI_PROGRESS",idx,false);
                String RI_SDATE                 = wnsearch.getField(thisCollection,"RI_SDATE",idx,false);
                String RI_EDATE                 = wnsearch.getField(thisCollection,"RI_EDATE",idx,false);
                String RI_RESERVATION_SDATE     = wnsearch.getField(thisCollection,"RI_RESERVATION_SDATE",idx,false);
                String RI_RESERVATION_EDATE     = wnsearch.getField(thisCollection,"RI_RESERVATION_EDATE",idx,false);
                String RI_RESERVATION_SDT       = wnsearch.getField(thisCollection,"RI_RESERVATION_SDT",idx,false);
                String RI_RESERVATION_EDT       = wnsearch.getField(thisCollection,"RI_RESERVATION_EDT",idx,false);
                String RI_IMAGE_FILE_ID         = wnsearch.getField(thisCollection,"RI_IMAGE_FILE_ID",idx,false);
                String RI_MAIN_FILE_NM          = wnsearch.getField(thisCollection,"RI_MAIN_FILE_NM",idx,false);
                String RI_READ_CNT              = wnsearch.getField(thisCollection,"RI_READ_CNT",idx,false);
                String RI_RECRUIT_YN            = wnsearch.getField(thisCollection,"RI_RECRUIT_YN",idx,false);
                String RI_RECRUIT_CNT           = wnsearch.getField(thisCollection,"RI_RECRUIT_CNT",idx,false);
                String RI_LOT_USE               = wnsearch.getField(thisCollection,"RI_LOT_USE",idx,false);
                String RI_OFF_LOT_USE           = wnsearch.getField(thisCollection,"RI_OFF_LOT_USE",idx,false);
                String RI_PO_YN                 = wnsearch.getField(thisCollection,"RI_PO_YN",idx,false);
                String USERCNT                  = wnsearch.getField(thisCollection,"USERCNT",idx,false);
                String LC_CATEGORY_UPR_NM       = wnsearch.getField(thisCollection,"LC_CATEGORY_UPR_NM",idx,false);
                String LC_TARGET_NAME           = wnsearch.getField(thisCollection,"LC_TARGET_NAME",idx,false);
                String ALIAS                    = wnsearch.getField(thisCollection,"ALIAS",idx,false);
                TITLE= wnsearch.getKeywordHl(TITLE,"<span class='txt-point'>","</span>");
				CONTENT= wnsearch.getKeywordHl(CONTENT,"<span class='txt-point'>","</span>");
				WRITER= wnsearch.getKeywordHl(WRITER,"<span class='txt-point'>","</span>");
            String URL = "URL 정책에 맞게 작성해야 합니다.";
            
%>
				<li class="<%=RI_PROGRESS.equals("접수마감")||RI_PROGRESS.equals("예약완료")||RI_PROGRESS.equals("행사마감") ? "end" : "" %>"> <!-- end 접수마감 -->
					<a href="#">
						<div class="info-container">
							<!-- 상단콘텐츠 -->
							<div class="class-content">
								<div class="info-title">
									<span class="dong-info"><%=RI_SUPERVISION_ORG_NAME %></span>
									<h3 class="box-title"><%=TITLE %></h3>
								</div>
								<span class="signal signal-<%=RI_PROGRESS.equals("접수마감")||RI_PROGRESS.equals("예약완료")||RI_PROGRESS.equals("행사마감") ? "end" : "receipt" %>"><%=RI_PROGRESS %></span>
								
								<div class="class-notice">
									<span class="receit-day">접수기간 : <%=RI_RESERVATION_SDATE %> ~ <%=RI_RESERVATION_EDATE %></span>
									<span class="edu-maximun">정원 : <%=RI_RECRUIT_CNT %>명 / 접수 : <%=USERCNT %>명</span>
									<span class="edu-target">대상 : <%=LC_TARGET_NAME %></span>
									<span class="edu-day">교육기간 : <%=RI_SDATE %> ~ <%=RI_EDATE %></span>
									<span class="edu-place">교육장소 : <%=RI_PLACE %></span>
								</div>								
							</div>						
							<!-- //상단콘텐츠 -->						
																	
							<!-- 하단기타 -->
							<div class="icon-info">
								<p class="receipt-meaning"> 
								<%if(!RI_OFF_LOT_USE.equals("")){
									String [] RI_OFF_LOT_USEs= RI_OFF_LOT_USE.split("\\|");
										if(RI_OFF_LOT_USEs.length > 0){
										for(int i=0; i < RI_OFF_LOT_USEs.length; i++){
											if(RI_OFF_LOT_USEs[i].equals("online")){%>
												<span class="online">온라인접수</span>
											<%}else if(RI_OFF_LOT_USEs[i].equals("field")){ %>
												<span class="site">현정접수</span>
											<%}else if(RI_OFF_LOT_USEs[i].equals("tel")){ %>
												<span class="tel">전화접수</span>
											<%}else if(RI_OFF_LOT_USEs[i].equals("another")){ %>
												<span class="other">별도사이트</span>
											<%}else{ %>
											
											<%} %>
										<%}
										}
									} %>
								</p>
								<p class="means-receipt">
									<span class="way"><%=RI_LOT_USE %></span>
								</p>
							</div>
							<div class="btn-nowgo">
								<button class="go-now" onclick="doPopup('<%=LINK%>')">바로보기</button>
							</div>
							<!-- //하단기타 -->
						</div><!-- //info-container -->
					</a>
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