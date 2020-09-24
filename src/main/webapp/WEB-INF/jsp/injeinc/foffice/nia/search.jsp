<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<script>
	$(document).ready(function() {
		
	});

	//페이징처리
	/* function doBbsFPag(pageIndex) {
	 document.bbsFVo.pageIndex.value = pageIndex;
	 document.bbsFVo.submit(); 
	 }  */

	//select box선택시 전달파라미터 설정
	/* function drpArea() {
	 $('#searchArea2').val($("#searchArea option:checked").val());  
	 } */
</script>

<form:form commandName="SearchVO" name="SearchVO" method="post"  onsubmit="return doSearch(this);">
	<%--<form:hidden path="pageIndex" />
 <form:hidden path="bcIdx" />
<form:hidden path="mode" /> --%>
	<!-- 기관별 버튼 생성  -->
	<!-- 	<ul class="tabBar" id="centerButton"></ul> -->

	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tbody>
			<tr>
				<td height="316" valign="top" style="background: url(/images/nia/bg_search_sub.jpg) no-repeat top;">
					<table width="870" border="0" align="center" cellpadding="0" cellspacing="0">
						<tbody>
							<tr>
								<td height="95">&nbsp;</td>
							</tr>
							<tr>
								<td height="220"><img src="/images/nia/title_search.png" alt="디지털 배움터 찾아보기" /></td>
							</tr>
							<tr>
								<td height="30">&nbsp;</td>
							</tr>
							<tr>
								<td><p>
										<span class="text06">디지털 역량 교육 사업을 통해 원하는 국민 누구나 쉽게 찾아가
											배울 수 있도록 행정 복지센터, 평생학습관, 도서관 같은 생활SOC의 공간을 활용하여 연간 1,000개소의
											‘디지털 배움터를 운영합니다.
									</p>
									<p>
										<span class="text06">‘디지털 배움터’는 디지털 역량이 부족한 지역 주민들을 위한
											디지털 역량 교육을 제공할 뿐만 아니라 일상적인 디지털 활용에 어려움을 느낄 때 언제든 찾아와 도움을 받을 수
											있도록 지원합니다.<br> <br> 각 지역별 ‘디지털배움터’는 강사, 서포터즈 채용과정을
											거쳐 세부 교육과정이 준비되는 대로 순차적으로 개소할 예정이며, 세부 교육일정은 업데이트되는 대로 공지하도록
											하겠습니다. (교육 문의 : 1800-0096)
										</span>
									</p>
									<p>
										<span class="text06">*코로나 2단계 격상으로 미운영 교육장이 많아지는 상황입니다.
											교육장 운영과 관련된 상세한 문의는 광역시도별 교육기관에 문의해 주시기 바랍니다. 아울러 교육장이 운영되지
											않는 경우, 온라인 화상교육 과정을 별도로 운영할 예정이오니 많은 참여 부탁드립니다.</span>
									</p></td>
							</tr>

							<tr>
								<td height="40"><p>&nbsp;</p>
									<table width="870" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td valign="top">
												<table width="455" border=0 cellspacing=0 cellpadding=0>
													<tbody>
														<tr>
															<td width="455"
																style="background-image: url(/images/nia/map_gangwondo.gif)">
																<div class="map" id="map_sel">
																	<img src="/images/nia/map_gangwondo.gif" name="overMap" width="455" height="560">
																	<div class="map_img"><img src="/images/nia/map.png" width="455" height="560" usemap="#Map"></div>
																	<div class="map_gangwondo">강원도<br> <span class="nbcolor">50개</span> </div>
																	<div class="map_gyeonggido">경기도<br><span class="nbcolor">143개</span></div>
																	<div class="map_gyeongsangnamdo">경상남도<br><span class="nbcolor">73개</span></div>
																	<div class="map_gyeongsangbukdo">경상북도<br><span class="nbcolor">59개</span></div>
																	<div class="map_gwangju">광주광역시<br><span class="nbcolor">48개</span></div>
																	<div class="map_daegu">대구광역시<br><span class="nbcolor">17개</span></div>
																	<div class="map_daejeon">대전광역시<br><span class="nbcolor">33개</span></div>
																	<div class="map_busan">부산광역시<br><span class="nbcolor">182개</span></div>
																	<div class="map_seoul">서울특별시<br><span class="nbcolor">123개</span></div>
																	<div class="map_saison">세종특별자치시<br><span class="nbcolor">12개</span></div>
																	<div class="map_ulsan">울산광역시<br><span class="nbcolor">20개</span></div>
																	<div class="map_Incheon">인천광역시<br><span class="nbcolor">56개</span></div>
																	<div class="map_jeollanamdo">전라남도<br><span class="nbcolor">141개</span></div>
																	<div class="map_jeollabukdo">전라북도<br><span class="nbcolor">101개</span></div>
																	<div class="map_cheju">제주특별자치도<br><span class="nbcolor">22개</span></div>
																	<div class="map_chungcheongnamdo">충청남도<br><span class="nbcolor">52개</span></div>
																	<div class="map_chungcheongbukdo">충청북도<br><span class="nbcolor">37개</span></div>
																</div>
															</td>
														</tr>
													</tbody>
												</table>
											</td>
											<td width="15">&nbsp;</td>
											<td valign="top">
												<table width="400" border="0" cellspacing="0" cellpadding="0">
													<tbody>
														<%-- <form name="mapform" method="post" action="search.jsp"> --%>
														<tr>
															<td height="33" valign="top"><span class="left">
																	<select name="sido1" id="sido1"></select> 
																	<select name="gugun1" id="gugun1"></select> 
																	<input type=image src="/images/nia/btn_search.gif" alt="검색하기" />
															</span></td>
														</tr>
														<%-- </form> --%>
														<tr>
															<td><table width="400" border="0" cellpadding="0" cellspacing="0" class="type05">
																	<tbody>
																		<tr>
																			<th>기관명<br>(주소)</th>
																			<th width="35">위치</th>
																		</tr>
																		<tr>
																			<c:forEach items="${resultList}" var="resultList" varStatus="status">
																				<td><strong><c:out value="${resultList.COM_NM}" /></strong><br>
																				<c:out value="${resultList.COM_ADDR}" /></td>
																				<td class="center"><a href="javascript:map_search('');"><img src="/images/nia/icon_map.gif" width="24" height="24" alt="위치확인"></a></td>
																			</c:forEach>
																		</tr>
																	</tbody>
																</table></td>
														</tr>
														<tr>
															<td height="40" align="center"></td>
														</tr>
													</tbody>
												</table>
												<div class="paging">
													<ui:pagination paginationInfo="${paginationInfo}" type="drpImage" jsFunction="doBbsFPag" />
												</div>
											</td>
										</tr>
									</table></td>
							</tr>
							<tr><td>&nbsp;</td></tr>
							<tr><td><iframe height="400px" width="870px" id="map_search" src="map.jsp"></iframe></td></tr>
							<tr><td align="center">&nbsp;</td></tr>
							<tr><td align="center"><a href="./index.jsp"><img src="/images/nia/btn_main.gif" alt="메인화면으로 이" /></a></td></tr>
							<tr><td height="40">&nbsp;</td></tr>
							<tr>
								<td><table width="870" border="0" cellpadding="0" cellspacing="0" class="type01">
										<tbody>
											<!--#include file='./area_call.html'-->
										</tbody>
									</table></td>
							</tr>
						</tbody>
					</table></td>
			</tr>
			<tr><td height="60">&nbsp;</td></tr>
		</tbody>
	</table>
</form:form>

<map name="Map">
  <area shape="poly" coords="168,47,272,34,324,107,362,176,305,170,261,152,227,160,230,122,209,110,216,79" href="./search.jsp?area=gangwondo" alt="강원도" onmouseover="if(document.images) overMap.src='images/m_gangwondo.png'" onmouseout="if(document.images) overMap.src='/images/nia/map_gangwondo.gif'" onfocus="this.blur();">
  <area shape="poly" coords="140,140,126,154,144,175,170,179,180,190,223,155,229,120,205,111,214,79,210,77,167,48,130,82,121,114,140,130,161,110,174,125,170,145,152,146,141,141" href="./search.jsp?area=gyeonggido" alt="경기도" onmouseover="if(document.images) overMap.src='/images/nia/m_gyeonggido.png'" onmouseout="if(document.images) overMap.src='images/map_gangwondo.gif'" onfocus="this.blur();">
  <area shape="poly" coords="329,323,348,349,316,372,299,406,264,391,227,400,210,356,211,329,221,304,236,293,264,314,297,322" href="./search.do?area=gyeongsangnamdo" alt="경상남도" onmouseover="if(document.images) overMap.src='/images/nia/m_gyeongsangnamdo.png'" onmouseout="if(document.images) overMap.src='images/map_gangwondo.gif'" onfocus="this.blur();">
  <area shape="poly" coords="381,318,368,261,362,178,306,170,282,196,254,195,234,214,237,248,249,259,236,290,273,317,280,287,309,279,314,306,296,319,329,321,352,312" href="./search.jsp?area=gyeongsangbukdo" alt="경상북도" onmouseover="if(document.images) overMap.src='/images/nia/m_gyeongsangbukdo.png'" onmouseout="if(document.images) overMap.src='images/map_gangwondo.gif'" onfocus="this.blur();">
  <area shape="poly" coords="276,314,282,289,309,281,313,304,296,322" href="./search.jsp?area=daegu" alt="대구" onmouseover="if(document.images) overMap.src='/images/nia/m_daegu.png'" onmouseout="if(document.images) overMap.src='images/map_gangwondo.gif'" onfocus="this.blur();">	
  <area shape="poly" coords="128,364,142,360,155,364,156,379,135,381,127,375" href="./search.jsp?area=gwangju" alt="광주광역시" onmouseover="if(document.images) overMap.src='/images/nia/m_gwangju.png'" onmouseout="if(document.images) overMap.src='/images/nia/map_gangwondo.gif'" onfocus="this.blur();">
  <area shape="poly" coords="199,261,202,246,196,239,188,232,179,244,179,262" href="./search.jsp?area=daejeon" alt="대전광역시" onmouseover="if(document.images) overMap.src='/images/nia/m_daejeon.png'" onmouseout="if(document.images) overMap.src='/images/nia/map_gangwondo.gif'" onfocus="this.blur();">
  <area shape="poly" coords="348,349,313,372,345,387,368,372" href="./search.jsp?area=busan" alt="부산광역시" onmouseover="if(document.images) overMap.src='/images/nia/m_busan.png'" onmouseout="if(document.images) overMap.src='/images/nia/map_gangwondo.gif'" onfocus="this.blur();">
  <area shape="poly" coords="139,136,147,122,161,113,173,129,169,142,154,145" href="./search.jsp?area=seoul" alt="서울" onmouseover="if(document.images) overMap.src='/images/nia/m_seoul.png'" onmouseout="if(document.images) overMap.src='/images/nia/map_gangwondo.gif'" onfocus="this.blur();">
  <area shape="poly" coords="188,234,174,214,154,207,157,224,171,243,179,245" href="./search.jsp?area=saison" alt="세종특별자치시" onmouseover="if(document.images) overMap.src='/images/nia/m_saison.png'" onmouseout="if(document.images) overMap.src='/images/nia/map_gangwondo.gif'" onfocus="this.blur();">
  <area shape="poly" coords="330,323,351,312,378,320,384,337,369,370" href="./search.jsp?area=ulsan" alt="울산광역시" onmouseover="if(document.images) overMap.src='/images/nia/m_ulsan.png'" onmouseout="if(document.images) overMap.src='/images/nia/map_gangwondo.gif'" onfocus="this.blur();">
  <area shape="poly" coords="138,137,128,124,117,122,117,133,132,145" href="./search.jsp?area=Incheon" alt="인천광역시" onmouseover="if(document.images) overMap.src='/images/nia/m_Incheon.png'" onmouseout="if(document.images) overMap.src='/images/nia/map_gangwondo.gif'" onfocus="this.blur();">
  <area shape="poly" coords="205,355,225,398,190,441,125,460,100,420,116,382,157,380,155,362,142,359,126,362,124,372,132,378,114,380,99,361,122,351,140,337,166,347,194,352" href="./search.jsp?area=jeollanamdo" alt="전라남도" onmouseover="if(document.images) overMap.src='/images/nia/m_jeollanamdo.png'" onmouseout="if(document.images) overMap.src='images/map_gangwondo.gif'" onfocus="this.blur();">
  <area shape="poly" coords="109,358,124,320,142,284,186,271,191,285,218,279,238,281,215,307,207,355,140,336" href="./search.jsp?area=jeollabukdo" alt="전라북도" onmouseover="if(document.images) overMap.src='/images/nia/m_jeollabukdo.png'" onmouseout="if(document.images) overMap.src='images/map_gangwondo.gif'" onfocus="this.blur();">
  <area shape="poly" coords="167,501,146,493,109,501,86,518,97,529,141,526,165,502" href="./search.jsp?area=cheju" alt="제주도" onmouseover="if(document.images) overMap.src='/images/nia/m_cheju.png'" onmouseout="if(document.images) overMap.src='/images/nia/map_gangwondo.gif'" onfocus="this.blur();">
  <area shape="poly" coords="201,262,181,261,181,248,167,247,157,224,154,204,178,211,188,231,183,205,183,192,167,181,138,177,112,171,89,199,116,216,127,264,140,281,188,269,193,285,217,278" href="./search.jsp?area=chungcheongnamdo" alt="충청남도" onmouseover="if(document.images) overMap.src='/images/nia/m_chungcheongnamdo.png'" onmouseout="if(document.images) overMap.src='images/map_gangwondo.gif'" onfocus="this.blur();">
  <area shape="poly" coords="238,279,245,262,237,249,231,213,258,193,281,196,306,172,254,153,219,159,185,184,182,205,193,233,204,244,198,257,211,271,226,281" href="./search.jsp?area=chungcheongbukdo" alt="충청북도" onmouseover="if(document.images) overMap.src='/images/nia/m_chungcheongbukdo.png'" onmouseout="if(document.images) overMap.src='images/map_gangwondo.gif'" onfocus="this.blur();">
</map>

<script type="text/javascript">
	sojaeji = new sojaeji('sido1', 'gugun1');
</script>