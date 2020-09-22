<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="egovframework.injeinc.boffice.cn.common.Util" %>
<%@ page import="java.util.List" %>
<%@ page import="org.jdom2.Element" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="egovframework.injeinc.boffice.cn.common.UtilSvc" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
  
<c:set var="depth1" value="${fn:substring(ssSortOrder,1,3)}"/>
<c:set var="depth2" value="${fn:substring(ssSortOrder,3,5)}"/>
<c:set var="depth3" value="${fn:substring(ssSortOrder,5,7)}"/>
<c:set var="arrMenuPath" value="${fn:split(sessionScope['ssMenuPath'],'>')}"/>

<% 
String strSortOrder = "", siteCd = "", sortOrder = "", showYn = "", linkType = "", activeClass = "";
//strSortOrder = request.getAttribute("ssSortOrder").toString();	
strSortOrder =  request.getAttribute("ssSortOrder") != null ? request.getAttribute("ssSortOrder").toString() : "";	
siteCd = Util.getSiteCode(request).toString();
ServletContext conext = session.getServletContext();
WebApplicationContext wContext = WebApplicationContextUtils.getWebApplicationContext(conext);
UtilSvc utilSvc = (UtilSvc)wContext.getBean("UtilSvc");
List<Element> menuList = utilSvc.makeMenuXml(siteCd);

String headContent = "";
String arrMenuCode[] = new String[3];

for ( Element element:menuList ) {				
	List<Element> menuList1 = element.getChildren("child-menu").get(0).getChildren();
	
	for ( Element element1:menuList1 ) {
		List<Element> menuList2 = element1.getChildren("child-menu").get(0).getChildren();
		
		for ( Element element2:menuList2 ) {
			List<Element> menuList3 = element2.getChildren("child-menu").get(0).getChildren();
			
			if ( element2.getChild("menu-code").getText().equals( request.getAttribute("ssMenuCode") ) ) {
				arrMenuCode[0] = element1.getChild("menu-code").getText();
				arrMenuCode[1] = element2.getChild("menu-code").getText();
				arrMenuCode[2] = "";
			}			
			
			if ( menuList3.size() > 0 ) {
				for ( Element element3:menuList3 ) {
					if ( element3.getChild("menu-code").getText().equals( request.getAttribute("ssMenuCode") ) ) { 
						
						arrMenuCode[0] = element1.getChild("menu-code").getText();
						arrMenuCode[1] = element2.getChild("menu-code").getText();
						arrMenuCode[2] = element3.getChild("menu-code").getText();
						
						headContent = ( element1.getChild("head-content") != null ) ? element1.getChild("head-content").getText() : "&nbsp;";
						request.setAttribute("headContent",headContent);
						break;
					}
				}//for
			}//if
			
		}// for element2
		
	}//for element1
	
}//for element
%>
<!-- webapp/layout/DRP0000/drp_sub/top.jsp -->
<body> 
<div id='wrap' class="main">
	<div id='header'>
			<div class='inner'>
				<h1><a href='/site/DRP0000/main.do'>디지털&nbsp;&nbsp;<em>역기능&nbsp;대응&nbsp;</em>&nbsp;통합안내&nbsp;시스템</a></h1>
					<div class='topRight'>
					<c:choose>
						<c:when test="${ss_id eq null or ss_id eq '' }">
							<a href='/site/DRP0000/login/Login.do' class='login'>로그인</a>
							<a href='/site/DRP0000/user/User_Step_01.do' class='member_join'>회원가입</a>
						</c:when>
						<c:otherwise>
							<a href='#n' class='login'>${ss_id}님</a>
							<a href='/site/DRP0000/login/Logout.do' class='btn_logout'>로그아웃</a>  
							<a href='/site/DRP0000/ex/bbs/List.do?cbIdx=1185&regId=${ss_id}' class=''>마이페이지</a>
						</c:otherwise>
					</c:choose>
				
					<!-- <a href='/site/DRP0000/10/11001000000002020082615.jsp' class='info_use'>이용안내</a> -->
					<button type='button' class='search_open'>검색창열기</button>
					<div class='top_search'>
						<div class='inner'>
							<form name="searchVo" method="get" action="/site/DRP0000/ex/bbs/List.do">
								<fieldset>
									<legend>검색어</legend>
									<div class='seach_area'>
										<div>
											<input type="text" name="searchKey" class="w400" title="검색어입력" placeholder="검색어를 입력하세요">
											<input type="hidden" name="cbIdx" value="<c:out value='1178'/>">
											<input type='submit' value='검색'>
										</div>
										<em>추천검색어</em>
										<a href=''>#생애주기별 서비스</a>
										<a href=''>#상담자료실</a>
										<a href=''>#강사찾기</a>
									</div>
								</fieldset>
							</form>
							<button type='button' class='btn_search_close'>검색창 닫기</button>
						</div>
					</div>
					<a href='/site/DRP0000/ex/bbs/List.do?cbIdx=1172' class='btn_professional'>전문인력양성</a>
					<a href='/site/DRP0000/ex/bbs/List.do?cbIdx=1166' class='btn_training'>직무연수</a>
				</div>
			</div>
	</div>

	<nav id='gnb'>
		<div class='inner'>
			<ul>
				  
			<%
				for ( Element element:menuList ) {				
					List<Element> menuList1 = element.getChildren("child-menu").get(0).getChildren();
					
					for ( Element element1:menuList1 ) {
						List<Element> menuList2 = element1.getChildren("child-menu").get(0).getChildren();	
						
						if("Y".equals(element1.getChild("show-yn").getText() )){
			%>
					<li class="menu_<%= menuList1.indexOf(element1) %>">
						<a href="<%= element1.getChild("user-menu-url").getText() %>"><%= element1.getChild("menu-nm").getText() %></a>
						<div>
							<div>
								<div class='hack'>
									<%
										String nm = element1.getChild("menu-nm").getText();
										int emIdx = nm.lastIndexOf(' ');
										
										if ( emIdx > 0 ) {
											nm = nm.substring( 0, emIdx ) + "<em>" + nm.substring( emIdx ) + "</em>";	
										}
									%>								
									<strong><%= nm %></strong>
									<span><%= ( element1.getChild("head-content") != null ) ? element1.getChild("head-content").getText(): "&nbsp;" %></span>
								</div>
								<ul>
									<%
										for ( Element element2:menuList2 ) {
											List<Element> menuList3 = element2.getChildren("child-menu").get(0).getChildren();	
									%>		
											<li><a href="<%= element2.getChild("user-menu-url").getText() %>"><%= element2.getChild("menu-nm").getText() %></a>
									<%		
											if ( menuList3.size() > 0 ) {
									%>
												<ul>
									<%
												for ( Element element3:menuList3 ) {
									%>		
													<li><a href="<%= element3.getChild("user-menu-url").getText() %>"><%= element3.getChild("menu-nm").getText() %></a></li>												
									<%
												}
									%>		
												</ul>
									<%			
											}
									%>
											</li>
									<%
										}
									%>
								</ul>						
							</div>
						</div>
					</li>			
			<%
						}  
					}  
				}
			%>	
			</ul>			
			<button type='button' class='all_mune_view'>전체보기열기</button>
		</div>
	</nav>
		
		<div class='inner'>
			<div id='allMenu'>
					<strong><span>전체메뉴</span>
						<c:choose>
						<c:when test="${ss_id eq null or ss_id eq '' }">
							<a href='/site/DRP0000/login/Login.do' class='login'>로그인</a>
							<a href='/site/DRP0000/user/User_Step_01.do' class='member_join'>회원가입</a>
						</c:when>
						<c:otherwise>
							<a href='/site/DRP0000/login/Logout.do' class='btn_logout'>로그아웃</a>  
							<a href='/site/DRP0000/ex/bbs/List.do?cbIdx=1185&regId=${ss_id}' class=''>마이페이지</a>
						</c:otherwise>
					</c:choose>
					</strong>
					<ul>
					<%
						for ( Element element:menuList ) {				
							List<Element> menuList1 = element.getChildren("child-menu").get(0).getChildren();
							
							for ( Element element1:menuList1 ) {
								List<Element> menuList2 = element1.getChildren("child-menu").get(0).getChildren();	
								
								if("Y".equals(element1.getChild("show-yn").getText() )){
					%>					
								<li>
									<div>
										<a href="<%= element1.getChild("user-menu-url").getText() %>"><%= element1.getChild("menu-nm").getText() %></a>
										<ul>
											<%
												for ( Element element2:menuList2 ) {
													List<Element> menuList3 = element2.getChildren("child-menu").get(0).getChildren();	
											%>		
													<li><a href="<%= element2.getChild("user-menu-url").getText() %>"><%= element2.getChild("menu-nm").getText() %></a>
											<%		
													if ( menuList3.size() > 0 ) {
											%>
														<ul>
											<%
														for ( Element element3:menuList3 ) {
											%>		
															<li><a href="<%= element3.getChild("user-menu-url").getText() %>"><%= element3.getChild("menu-nm").getText() %></a></li>												
											<%
														}
											%>		
														</ul>
											<%			
													}
											%>
													</li>	
											<%
												}
											%>
										</ul>
									</div>
								</li>
					<%
								}
							}
						}
					%>	
					<li>
							<div>
								<a href="/site/DRP0000/ex/bbs/List.do?cbIdx=1172">전문인력 양성</a>
								<ul>
									<li>
										<a href="/site/DRP0000/ex/bbs/List.do?cbIdx=1172">전문인력 양성</a>
									</li>
									<li>
										<a href="/site/DRP0000/ex/bbs/List.do?cbIdx=1173">양성과정 자료</a>
									</li>
								</ul>
							</div>
						</li>
						<li>
							<div>
								<a href="/site/DRP0000/ex/bbs/List.do?cbIdx=1166">직무연수</a>
								<ul>
									<li>
										<a href="/site/DRP0000/ex/bbs/List.do?cbIdx=1166">직무연수</a>
									</li>
									<li>
										<a href="/site/DRP0000/ex/bbs/List.do?cbIdx=1167">보로자연수</a>
									</li>
								</ul>
							</div>
						</li>
						<c:if test="${ss_id ne null}" >
							<li>
								<div>
									<a href="/site/DRP0000/ex/bbs/List.do?cbIdx=1185">마이페이지</a>
									<ul>
										<li>
											<a href="/site/DRP0000/ex/bbs/List.do?cbIdx=1185">내가쓴글</a>
										</li>
										<li>
											<a href="/site/DRP0000/ex/bbs/List.do?cbIdx=1186">수강내역확인</a>
										</li>
										<li>
											<a href="/site/DRP0000/user/User_Step_06.do">회원정보관리</a>
										</li>
										<li>
											<a href="/site/DRP0000/user/User_Pwd_Chg_Form.do">비밀번호변경</a>
										</li>
									</ul>
								</div>
							</li>
						</c:if>
					</ul>
					<button type='button' class='all_mune_close'>전체메뉴보기닫기</button>
			</div>
		</div>
		
		<% if(!"".equals(strSortOrder)) {%>
			<!-- 
			<div class="bg_area_title sub_h2_area_<c:out value="${depth1}" />">
				<div class="inner">
					<h2>${arrMenuPath[1]}</h2>
					<div class="location">
						<ol>
							<li class="l_home">홈</li>
							<li class="step1">&nbsp; ${arrMenuPath[1]}</li>
							<li class="step2">${arrMenuPath[2]}</li>
							<li class="step2">${arrMenuPath[3]}</li>
						</ol>
						<div>
							<button type="button" class="sharing">공유</button>
							<button type="button" class="print" onclick="doContentPrint()">프린트</button>
						</div>
					</div>
				</div>
			</div>
			-->
		<%} %>
		
         <div class="bg_area_title sub_h2_area_${depth1}">
            <div class="inner">
                <h2>${arrMenuPath[1]}</h2> 
                <div class="location">
                    <ol>
                        <li class="l_home">홈</li>
                        <li class="step1"><a href="#">${arrMenuPath[1]}</a>
							<ol>
							<%
								for ( Element element:menuList ) {				
									List<Element> menuList1 = element.getChildren("child-menu").get(0).getChildren();			
									
									for ( Element element1:menuList1 ) {
										List<Element> menuList2 = element1.getChildren("child-menu").get(0).getChildren();		
										
										if("Y".equals(element1.getChild("show-yn").getText() )){
							%>		
									<li><a href="<%= element1.getChild("user-menu-url").getText() %>"><%= element1.getChild("menu-nm").getText() %></a></li>
							<%
										}
									}
								}
							%>									
							</ol>
						</li>
                        <li class="step2"><a href="#">${arrMenuPath[2]}</a>
							<ol>
							<%
							for ( Element element:menuList ) {				
								List<Element> menuList1 = element.getChildren("child-menu").get(0).getChildren();
								
								for ( Element element1:menuList1 ) {
									List<Element> menuList2 = element1.getChildren("child-menu").get(0).getChildren();								
									
									if ( element1.getChild("menu-code").getText().equals( arrMenuCode[0] ) ) {
										for ( Element element2:menuList2 ) {
							%>	
									<li><a href="<%= element2.getChild("user-menu-url").getText() %>"><%= element2.getChild("menu-nm").getText() %></a></li>
							<%
										}
									}
								}
							}
							%>	
							</ol>
						</li>
						<c:if test="${depth1 ne 13}" >
                        <li class="step3"><a href="#">${arrMenuPath[3]}</a>
							<ol>
							<%
							for ( Element element:menuList ) {				
								List<Element> menuList1 = element.getChildren("child-menu").get(0).getChildren();
								
								for ( Element element1:menuList1 ) {
									List<Element> menuList2 = element1.getChildren("child-menu").get(0).getChildren();								
									
									for ( Element element2:menuList2 ) {
										List<Element> menuList3 = element2.getChildren("child-menu").get(0).getChildren();	
										
										if ( element2.getChild("menu-code").getText().equals( arrMenuCode[1] ) ) {
											for ( Element element3:menuList3 ) {
											
										
							%>	
									<li><a href="<%= element3.getChild("user-menu-url").getText() %>"><%= element3.getChild("menu-nm").getText() %></a></li>
							<%
											}
										}
									}
								}
							}
							%>	
							</ol>
						</li>
						</c:if>
                    </ol>
	                    <div> 
	                    	<button type="button" class="sharing">공유</button> 
		                    <div class="sharing_wrap">
			                     <a href="#" class="item1" title="새창열림" onclick="return false;" id="iconFb">페이스북</a>
			                     <a href="#" class="item2" title="새창열림" onclick="return false;" id="iconTw">트위터</a>
			                     <a href="#" class="item3" title="새창열림" onclick="return false;" id="iconKs">카카오스토리</a>
		                	</div>
	                    	<button type="button" class="print" onclick="doContentPrint()">프린트</button> 
                    	</div>
                </div>
            </div>
        </div>
		  
		<div id="container">
			<div class="inner">