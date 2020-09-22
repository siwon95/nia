<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script>

	$(document).ready(function(){
		$(".d_point>div").each(function(i){
			$(".d_point>div:eq("+i+")").on("mouseover click focusin focusout",function(e){
				e.preventDefault();
				$(this).addClass("on");
				$(".d_point>div:not(:eq("+i+"))").removeClass("on");
			});
		});
	});

</script>

		<div class="m_visuall">
			<div class="inner">
				<div class="m_v_area"></div>
			</div>
		</div>
		<div id="m_container">
			<div class="inner">
				<div class="divGroup gongi">
					<div class="w70p">
						<span>
							<button type="button" class="gongi_start active" onclick="start()">시작</button>
							<button type="button" class="gongi_stop" onclick="sstop()">멈춤</button>
							<button type="button" class="gongi_prev" onclick="pre();">이전으로이동</button>
							<button type="button" class="gongi_next" onclick="next();">다음으로 이동</button>
						</span>
						<ul>
							<c:forEach items="${newNoticeList}" var="noticeList" varStatus="status">
								<li><a href="/site/DRP0000/ex/bbs/View.do?cbIdx=${noticeList.CB_IDX}&bcIdx=${noticeList.BC_IDX}">${noticeList.SUB_CONT}</a>
								<span><fmt:formatDate value="${noticeList.REG_DT}" pattern="yyyy-MM-dd" type="date"/></span></li>
							</c:forEach>
						</ul>
					</div>
					<div class="w30p">
						<a href="/site/DRP0000/ex/bbs/List.do?cbIdx=1140" class="faq">자주묻는질문</a>
					</div>
				</div>
			</div>
			<div class="digital_point">
				<div class="inner">
					<div class="d_point">
						<div class="smartphone on">
							<strong>스마트폰<span>과의존</span></strong>
							<div>
								<h3>스마트폰<em>과의존</em></h3>
								<span>
									과도한 스마트폰 이용으로<br>
									문제적 결과를 경험하는 상태
									<a href="/site/DRP0000/01/10105000000002020082515.jsp">자가점검</a>
								</span>
								<div>
									<ul>
										<li><span><a href="/site/DRP0000/ex/bbs/List.do?cbIdx=1094">교육안내·신청</a></span></li>
										<li><span><a href="/site/DRP0000/ex/bbs/List.do?cbIdx=1099">상담안내·신청</a></span></li>
										<li><span><a href="/site/DRP0000/ex/bbs/List.do?cbIdx=1101">상담기관 찾기</a></span></li>
										<li><span><a href="/site/DRP0000/ex/bbs/List.do?cbIdx=1105">치유센터 찾기</a></span></li>
									</ul>
									<dl>
										<dt>관계기관</dt>
										<dd>
											<a href="https://www.iapc.or.kr/" target="_blank"><img src="/images/drp/smart_related1.png" alt=""></a>
											<a href="https://ss.moiba.or.kr/index.do" target="_blank"><img src="/images/drp/smart_related2.png" alt=""></a>
										</dd>
									</dl>
								</div>
							</div>
						</div>
						<div class="game_absorbe">
							<strong>게임<span>과몰입</span></strong>
							<div>
								<h3>게임<em>과몰입</em></h3>
								<span>
									게임 사용시간 증가와 함께<br>신체적·정신적 불안감, 일상생활에<br>부정적 영향을 유발하는 상태
									<a href="/site/DRP0000/02/10205000000002020082516.jsp"><span>자가점검</span></a>
								</span>
								<div>
									<ul>
										<li><span><a href="/site/DRP0000/02/10203030000002020072909.jsp">게임리터러시교육</a></span></li>
										<li><span><a href="">검사 프로그램</a></span></li>
										<li><span><a href="/site/DRP0000/ex/bbs/List.do?cbIdx=1107">상담기관 찾기</a></span></li>
										<li><span><a href="/site/DRP0000/02/10204030000002020080610.jsp">치료프로그램</a></span></li>
									</ul>
									<dl>
										<dt>관계기관</dt>
										<dd>
											<a href="http://www.gameculture.or.kr/" target="_blank"><img src="/images/drp/smart_related3.png" alt=""></a>
											<a href="http://www.gucc.or.kr/main/index.asp" target="_blank"><img src="/images/drp/smart_related4.png" alt=""></a>
											<a href="http://www.kocca.kr/cop/main.do" target="_blank"><img src="/images/drp/smart_related5.png" alt=""></a>
											<a href="https://edu.kocca.kr/edu/main/main.do" target="_blank"><img src="/images/drp/smart_related6.png" alt=""></a>

										</dd>
									</dl>
								</div>
							</div>
						</div>
						<div class="cyber_gamble">
							<strong>사이버<span>도박</span></strong>
							<div>
								<h3>사이버<em>도박</em></h3>
								<span>
									사이버 도박으로 인해 물질적·<br>정신적 피해를 겪고 있는 상태
									<a href="/site/DRP0000/03/10305000000002020082516.jsp">자가점검</a>
								</span>
								<div>
									<ul>
										<li><span><a href="/site/DRP0000/ex/bbs/List.do?cbIdx=1128">교육안내·신청</a></span></li>
										<li><span><a href="/site/DRP0000/03/10303020000002020080613.jsp">온라인 상담</a></span></li>
										<li><span><a href="/site/DRP0000/ex/bbs/List.do?cbIdx=1118">상담기관 찾기</a></span></li> 
										<li><span><a href="/site/DRP0000/ex/bbs/List.do?cbIdx=1116">상담안내·신청</a></span></li>
										<li><span><a href="/site/DRP0000/03/10303030000002020080613.jsp">채팅 상담</a></span></li>
										<li><span><a href="/site/DRP0000/03/10304030000002020080613.jsp">치유센터 찾기</a></span></li>
									</ul>
									<dl>
										<dt>관계기관</dt>
										<dd>
											<a href="https://www.kcgp.or.kr/pcMain.do" target="_blank"><img src="/images/drp/smart_related7.png" alt=""></a>
											<a href="https://www.ngcc.go.kr/" target="_blank"><img src="/images/drp/smart_related8.png" alt=""></a>
										</dd>
									</dl>
								</div>
							</div>
						</div>
						<div class="cyber_violence">
							<strong>사이버<span>폭력</span></strong>
							<div>
								<h3>사이버<em>폭력</em></h3>
								<span>
									사이버 공간에서 다양한 형태로<br>피해를 주거나 받아 고통을<br>야기하는 상태
								</span>
								<div>
									<ul>
										<li><span><a href="/site/DRP0000/ex/bbs/List.do?cbIdx=1144">교육안내·신청</a></span></li>
										<li><span><a href="/site/DRP0000/ex/bbs/List.do?cbIdx=1148">상담기관 찾기</a></span></li>
										<li><span><a href="/site/DRP0000/ex/bbs/List.do?cbIdx=1146">상담안내·신청</a></span></li>
										<li><span><a href="/site/DRP0000/ex/bbs/List.do?cbIdx=1134">치유센터 찾기</a></span></li>
									</ul>
									<dl>
										<dt>관계기관</dt>
										<dd>
											<a href="https://www.wee.go.kr/" target="_blank"><img src="/images/drp/smart_related9.png" alt=""></a>
											<a href="http://www.moj.go.kr/moj/index.do" target="_blank"><img src="/images/drp/smart_related10.png" alt=""></a>
											<a href="http://digitalcitizen.kr/main.do" target="_blank"><img src="/images/drp/smart_related11.png" alt=""></a>
											<a href="http://www.moiba.or.kr/" target="_blank"><img src="/images/drp/smart_related12.png" alt=""></a>
										</dd>
									</dl>
								</div>
							</div>
						</div>
						<div class="internet_ethics">
							<strong>인터넷<span>윤리</span></strong>
							<div>
								<h3>인터넷<em>윤리</em></h3>
								<span>
									건강한 인터넷 이용을 위한<br>규범과윤리
								</span>
								<div>
									<ul>
										<li><span><a href="/site/DRP0000/ex/bbs/List.do?cbIdx=1124">인터넷윤리 동향</a></span></li>
										<li><span><a href="/site/DRP0000/05/10503010000002020080613.jsp">행사·캠페인</a></span></li>
										<li><span><a href="/site/DRP0000/ex/bbs/List.do?cbIdx=1125">교육안내·신청</a></span></li>
									</ul>
									<dl>
										<dt>관계기관</dt>
										<dd>											
											<a href="http://digitalcitizen.kr/main.do" target="_blank"><img src="/images/drp/smart_related11.png" alt=""></a>
											<a href="https://www.nia.or.kr/site/nia_kor/main.do" target="_blank"><img src="/images/drp/smart_related13.png" alt=""></a>
										</dd>
									</dl>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<div class="inner">
				<h3 class="main_title">생애주기별 서비스</h3>
				<div class="divGroup cols4 bylifecycle">
					<div class="infants">
						<h4><a href="/site/DRP0000/ex/bbs/List.do?cbIdx=1159&searchContent=DRP_700001">영유아</a></h4>
						<span>디지털서비스 이용시 보호자의도움 및 교육이 필요한 세대</span>
						<ul>
							<li>
								<strong>인터넷 이용률</strong>
								<em>91.2%</em>
							</li>
							<li>
								<strong>주 사용 디지털기기</strong>
								<em>스마트폰</em>
							</li>
							<li>
								<strong>최초 이용시기(평균)</strong>
								<em>2.27세</em>
							</li>
							<li>
								<strong>주 이용 콘텐츠</strong>
								<em>동영상(유튜브)</em>
							</li>
						</ul>
					</div>
					<div class="division">
						<h4><a href="/site/DRP0000/ex/bbs/List.do?cbIdx=1159&searchContent=DRP_700002">아동청소년</a></h4>
						<span>건강한 인터넷윤리를 위한 디지털정보의선별적 수용과 활용이 필요한 세대</span>
						<ul>
							<li>
								<strong>인터넷 이용률</strong>
								<em>99.9%</em>
							</li>
							<li>
								<strong>주 이용 어플리케이션</strong>
								<em>채팅, SNS</em>
							</li>
							<li>
								<strong>스마트폰 보유율</strong>
								<em>96.6%</em>
							</li>
							<li>
								<strong>주 이용 SNS</strong>
								<em>페이스북&amp;인스타그램</em>
							</li>
						</ul>
					</div>
					<div class="youth">
						<h4><a href="/site/DRP0000/ex/bbs/List.do?cbIdx=1159&searchContent=DRP_700003">청년중장년</a></h4>
						<span>건전한 디지털 정보 활용을 위한 디지털 리터러시 역량을 키우는 세대</span>
						<ul>
							<li>
								<strong>인터넷 이용률</strong>
								<em>100%</em>
							</li>
							<li>
								<strong>주 사용 디지털기기</strong>
								<em>스마트폰&amp;태블릿</em>
							</li>
							<li>
								<strong>인터넷쇼핑 이용률</strong>
								<em>92.4%</em>
							</li>
							<li>
								<strong>주 이용 서비스</strong>
								<em>뱅킹&amp;쇼핑</em>
							</li>
						</ul>
					</div>
					<div class="mzee">
						<h4><a href="/site/DRP0000/ex/bbs/List.do?cbIdx=1159&searchContent=DRP_700004">어르신</a></h4>
						<span>디지털 정보활용능력 향상과 사이버범죄 예방교육이 필요한 세대</span>
						<ul>
							<li>
								<strong>인터넷 이용률</strong>
								<em>38.9%</em>
							</li>
							<li>
								<strong>주 사용 디지털기기</strong>
								<em>스마트폰</em>
							</li>
							<li>
								<strong>디지털정보 활용수준</strong>
								<em>25.1%</em>
							</li>
							<li>
								<strong>주 이용 콘텐츠</strong>
								<em>의사소통(메신저)</em>
							</li>
						</ul>
					</div>
					
				</div>
				
			<div class="divGroup cols3 m_field">
					<div>
						<div class="professional">
							<h3><a href="/site/DRP0000/ex/bbs/List.do?cbIdx=1172">전문인력 양성안내</a></h3>
							<span>
								교육 및 자격검정을 통해 신규인력양성과
								기존 전문인력의 역량을 강화합니다.
							</span>
						</div>
						<div class="job_training">
							<h3><a href="/site/DRP0000/ex/bbs/List.do?cbIdx=1166">직무연수 안내</a></h3>
							<span>
								아동·청소년을 대상으로 한 디지털역기능  
								예방 및 진로교육과정을 안내합니다.
							</span>
						</div>
					</div>
					<div>
						<div class="new_data">
							<h3>새로 등록된 자료</h3>
							<ul>
								<c:forEach items="${newList}" var="newList" varStatus="status">
									<li>
										<c:choose>
											<c:when test="${newList.CB_IDX eq '1095' || newList.CB_IDX eq '1104' || newList.CB_IDX eq '1129' || newList.CB_IDX eq '1144' || newList.CB_IDX eq '1126'}">
												<em class="edu">교육자료</em><a href="<c:url value='/site/${strDomain}/ex/bbs/View.do?cbIdx=${newList.CB_IDX }&bcIdx=${newList.BC_IDX }' />">
																				<c:out value="${fn:replace(fn:replace(fn:replace(newList.SUB_CONT,'&','&amp;'),'<','&lt;'),'>','&gt;')}" escapeXml="false"/>
																		   </a>
											</c:when>
											
											<c:otherwise>
												<em class="advice">상담자료</em><a href="<c:url value='/site/${strDomain}/ex/bbs/View.do?cbIdx=${newList.CB_IDX }&bcIdx=${newList.BC_IDX }' />">
																				<c:out value="${fn:replace(fn:replace(fn:replace(newList.SUB_CONT,'&','&amp;'),'<','&lt;'),'>','&gt;')}" escapeXml="false"/>
																	  		 </a>
											</c:otherwise>
										</c:choose>
									</li>
								</c:forEach>
							</ul>
						</div>
					</div>
					<div>
						<div class="banner_slider">
							<h3>유관기관</h3>
							<button type="button" class="useSlick_start active">배너시작</button>
							<button type="button" class="useSlick_stop">배너멈춤</button>
							<div class="slider useSlick">
								<ul>
									<li><a href="https://www.iapc.or.kr/" title="새창" target="blank"><img src="/images/drp/banner_related1.png" alt="스마트쉼센터"></a></li>
									<li><a href="https://ss.moiba.or.kr/" title="새창" target="blank"><img src="/images/drp/banner_related2.png" alt="사이버안심존"></a></li>
									<li><a href="https://www.wee.go.kr/" title="새창" target="blank"><img src="/images/drp/banner_related3.png" alt="we 우리가 희망이다"></a></li>
									<li><a href="http://www.moj.go.kr/" title="새창" target="blank"><img src="/images/drp/banner_related4.png" alt="법무부"></a></li>
									<li><a href="http://www.gameculture.or.kr/" title="새창" target="blank"><img src="/images/drp/banner_related5.png" alt="게임문화재단"></a></li>
									<li><a href="http://www.gucc.or.kr/" title="새창" target="blank"><img src="/images/drp/banner_related6.png" alt="gucc게임이용차보호센터"></a></li>
									<li><a href="https://www.iapc.or.kr/" title="새창" target="blank"><img src="/images/drp/banner_related1.png" alt="스마트쉼센터"></a></li>
									<li><a href="https://ss.moiba.or.kr/" title="새창" target="blank"><img src="/images/drp/banner_related2.png" alt="사이버안심존"></a></li>
									<li><a href="https://www.wee.go.kr/" title="새창" target="blank"><img src="/images/drp/banner_related3.png" alt="we 우리가 희망이다"></a></li>
									<li><a href="http://www.moj.go.kr/" title="새창" target="blank"><img src="/images/drp/banner_related4.png" alt="법무부"></a></li>
									<li><a href="http://www.gameculture.or.kr/" title="새창" target="blank"><img src="/images/drp/banner_related5.png" alt="게임문화재단"></a></li>
									<li><a href="http://www.gucc.or.kr/" title="새창" target="blank"><img src="/images/drp/banner_related6.png" alt="gucc게임이용차보호센터"></a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>