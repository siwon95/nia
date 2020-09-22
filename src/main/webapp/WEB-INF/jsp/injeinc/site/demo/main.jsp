<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="cmm" uri="http://cms.injeinc.co.kr/cmm"%>
<%@ taglib prefix="bbs" uri="http://cms.injeinc.co.kr/bbs"%>

		<div class="inner">
			<div class="sectionOption">
				<ul>
					<!--
					<li><a href="#mainSection1">COMPANY</a></li>
					<li><a href="#mainSection2">BUSINESS</a></li>
					<li><a href="#mainSection3">PROJECT</a></li>
					<li><a href="#mainSection4">CONTACT</a></li>
					-->
					<li><a href="#mainSection1">01</a></li>
					<li><a href="#mainSection2">02</a></li>
					<li><a href="#mainSection3">03</a></li>
					<li><a href="#mainSection4">04</a></li>
				</ul>
			</div>
			<article class="mainContent">
				<h2 class="soundOnly">Main Content</h2>
				<section id="mainSection1">
					<h3 class="soundOnly">COMPANY/PRODUCTS</h3>
					<div class="tabWrap">
						<ul class="tabList">
							<li><a href="#">COMPANY</a></li>
							<li><a href="#">PRODUCTS</a></li>
						</ul>
						<div class="tabContent company">
							<h4 class="soundOnly">COMPANY</h4>
							<div class="visualCompany">
								<b>Total ICT <br />Service <br />Company</b>
								<span class="bordered">Different Thinking & Ideation</span>
								<span>
									창조적인 생각과 최신 IT 기술을 융합하여<br />
									고객의 새로운 가치를 창출합니다.
								</span>
							</div>
						</div>
						<div class="tabContent products">
							<h4 class="soundOnly">PRODUCTS</h4>
							<div class="itemGroup">
								<div class="item1"><a href="/content/products/cloud.asp">
									<em>Cloud 운영관리 솔루션</em>
									<b>CONE-PaaS + CONE</b>
									<span>
										G-클라우드 및 다수 Cloud 환경에서 효율적인 운영관리가 <br />
										가능합니다.<br />
										CONE-PaaS : Platform as a Service<br />
										CONE : Infrastructure as a Service
									</span>
								</a></div>
								<div class="item2"><a href="/content/products/cms.asp">
									<em>CMS</em>
									<b>EasyFramework <br />v3.5</b>
									<span>전자정부 표준프레임워크와 완벽 호환되는 <br />콘텐츠 통합관리 솔루션</span>
								</a></div>
								<div class="item3"><a href="/content/products/bim.asp">
									<em>BIM</em>
									<b>PETRA</b>
									<span>3D모델링의 기술적 한계를 <br />극복한 4D/5D BIM Solution</span>
								</a></div>
								<!--<div class="item4"><a href="/content/products/secure.asp">
									<em>Secure Coding 진단</em>
									<b>Code-Ray</b>
									<span>CC인증, GS인증, 조달 <br />등록 제품</span>
								</a></div>-->
								<div class="item5"><a href="/content/products/bigdata.asp">
									<em>빅데이터 분석</em>
									<b><!--Splunk <br />-->NDAP</b>
									<span>데이터에 기반한 <br />비즈니스 의사 결정을 <br />도와주는 솔루션</span>
									
								</a></div>
							</div>
						</div>
					</div>
					<a href="#mainSection2" class="btn_nextSection">다음(BUSINESS AREA) 스크롤 이동</a>
				</section>
				<section id="mainSection2">
					<h3>BUSINESS AREA</h3>
					<p class="sectionDesc">각 분야의 다양한 경험을 보유한 전문가 그룹이 창조적인 생각과 최신 IT 기술을 융합하여<br /> 고객의 새로운 가치를 창출합니다.</p>
					<div class="sectionInner">
						<ul>
							<li class="item1"><a href="/content/business/system.asp">
								<b>System Integration</b>
								<span>공공 민간 시스템 구축 <br />웹 시스템 통합(UI, HW, SW, NW) <br />Business Platform 구축 <br />(전자정부 프레임워크 적용) <br />반응형웹 구축 웹 접근성, 표준 코딩</span>
							</a></li>
							<li class="item2"><a href="/content/business/cloud.asp">
								<b>Cloud Service</b>
								<span>G-클라우드 이관 <br />클라우드 플랫폼 구축 <br />클라우드 운영관리 <br />클라우드 도입 컨설팅 <br />Openshift, Jboss 기술지원</span>
							</a></li>
							<li class="item3"><a href="/content/business/consulting.asp">
								<b>Strategy Consulting</b>
								<span>UI/UX Consulting <br />ISP/BPR <br />Market Research & Analysis <br />On/Offline Promotion <br />웹 보안 컨설팅</span>
							</a></li>
							<li class="item4"><a href="/content/business/bim.asp">
								<b>BIM</b>
								<span>빌딩 정보 모델링 <br />건설관리 시스템 구축 <br />건설 관련 3D모델링 <br />4D & 5D BIM 운영·관리</span>
							</a></li>
						</ul>
					</div>
					<a href="#mainSection3" class="btn_nextSection">다음(PROJECT) 스크롤 이동</a>
				</section>
				
				<section id="mainSection3">
					<h3>PROJECT</h3>
					<div class="sectionInner">
						<ul class="projectList">
							
							<li><a href="/content/project/project_list.asp?no=74">
								<em>Latest Project</em>
								<span class="title">
									<b>이크레더블</b>
									이크레더블 차세대 시스템 구축
								</span>
								<span class="desc">
									프로젝트 기간:<br />2019-04 ~ 2020-02
								</span>
							</a></li>
							
							<li><a href="/content/project/project_list.asp?no=73">
								<em>Latest Project</em>
								<span class="title">
									<b>공무원연금공단</b>
									종합재해보상시스템 구축
								</span>
								<span class="desc">
									프로젝트 기간:<br />2019-03 ~ 2019-12
								</span>
							</a></li>
							
							<li><a href="/content/project/portfolio_view.asp?ranking=1" style="background-image:url(/board_upload/tb_openproject/2019_03_20_bFPrjrMruJ.jpg);">
								<em>Portfolio</em>
							</a></li>
							
							<li><a href="/content/project/portfolio_view.asp?ranking=2" style="background-image:url(/board_upload/tb_openproject/2019_03_20_PFQLwcMfkb.jpg);">
								<em>Portfolio</em>
							</a></li>
							
							<li><a href="/content/project/portfolio_view.asp?ranking=3" style="background-image:url(/board_upload/tb_openproject/2019_03_20_nSafrNaaij.jpg);">
								<em>Portfolio</em>
							</a></li>
							
							<li><a href="/content/project/portfolio_view.asp?ranking=4" style="background-image:url(/board_upload/tb_openproject/2019_03_20_deDfdrkZTx.jpg);">
								<em>Portfolio</em>
							</a></li>
							
							<li><a href="/content/project/portfolio_view.asp?ranking=6" style="background-image:url(/board_upload/tb_openproject/2019_03_20_UcLpVXJfby.jpg);">
								<em>Portfolio</em>
							</a></li>
							
							<li><a href="/content/project/portfolio_view.asp?ranking=7" style="background-image:url(/board_upload/tb_openproject/2019_03_20_dThcKtYzbJ.jpg);">
								<em>Portfolio</em>
							</a></li>
							
							<li><a href="/content/project/project_list.asp?no=64">
								<em>Latest Project</em>
								<span class="title">
									<b>해양환경공단</b>
									해양환경공단 홈페이지 개편 및 유지관리
								</span>
								<span class="desc">
									프로젝트 기간:<br />2019-03 ~ 2019-12
								</span>
							</a></li>
							
							<li><a href="/content/project/project_list.asp?no=66">
								<em>Latest Project</em>
								<span class="title">
									<b>중앙선거관리위원회</b>
									온라인투표시스템 유지관리 및 위탁운영 사업
								</span>
								<span class="desc">
									프로젝트 기간:<br />2019-02 ~ 2019-12
								</span>
							</a></li>
							
						</ul>
							

							<!-- <li><a href="/content/project/project_list.asp?no=1">
								<em>Latest Project</em>
								<span class="title">
									<b>이크레더블</b>
									Global 평가시스템 재구축
								</span>
								<span class="desc">
									프로젝트 기간:<br />2017.04.24 ~ 2018.01.30
								</span>
							</a></li>
							<li><a href="/content/project/portfolio_view.asp" style="background-image:url(/images/main/img_section3_2.jpg);">
								<em>Portfolio</em>
							</a></li>
							<li><a href="/content/project/portfolio_view.asp" style="background-image:url(/images/main/img_section3_3.jpg);">
								<em>Portfolio</em>
							</a></li>
							<li><a href="/content/project/project_list.asp?no=1">
								<em>Latest Project</em>
								<span class="title">
									<b>서초구청</b>
									2018년 홈페이지 개선 및 유지관리 용역
								</span>
								<span class="desc">
									프로젝트 기간:<br />2017.04.24 ~ 2018.01.30
								</span>
							</a></li>
							<li><a href="/content/project/project_list.asp?no=1">
								<em>Latest Project</em>
								<span class="title">
									<b>행정안전부</b>
									2018년 행정안전부 대표 홈페이지 유지관리 및 운영
								</span>
								<span class="desc">
									프로젝트 기간:<br />2017.04.24 ~ 2018.01.30
								</span>
							</a></li>
							<li><a href="/content/project/portfolio_view.asp" style="background-image:url(/images/main/img_section3_4.jpg);">
								<em>Portfolio</em>
							</a></li>
							<li><a href="/content/project/portfolio_view.asp" style="background-image:url(/images/main/img_section3_5.jpg);">
								<em>Portfolio</em>
							</a></li>
							<li><a href="/content/project/project_list.asp?no=1">
								<em>Latest Project</em>
								<span class="title">
									<b>국회입법조사처</b>
									2017년도 국회입법 조사처 Open API 구축사업
								</span>
								<span class="desc">
									프로젝트 기간:<br />2017.04.24 ~ 2018.01.30
								</span>
							</a></li> -->
					</div>
					<a href="#mainSection4" class="btn_nextSection">다음(Contact US) 스크롤 이동</a>
				</section>
				<section id="mainSection4">
					<h3>Contact US</h3>
					<div class="sectionInner">
						<div class="contact">
							<ul>
								<li class="item1">	
									<b><b>공공, SI 부문</b> 기술/사업/제휴 문의</b>
									<span>
										<b>장재열 이사</b> 
										<a href="tel:010-4623-1502" target="_blank" title="전화걸기(새창열림)" class="btn_tel">010-4623-1502</a> / 
										<a href="mailto:pocas4356@injeinc.co.kr" target="_blank" title="메일발송(새창열림)" class="btn_mail">beastj@injeinc.co.kr</a>
									</span>
								</li>
								<li class="item2">
									<b><b>민간, 지자체 부문</b> 기술/사업/제휴 문의</b>
									<span>
										<b>백희선 이사</b> 
										<a href="tel:010-6252-8107" target="_blank" title="전화걸기(새창열림)" class="btn_tel">010-6252-8107</a> / 
										<a href="mailto:pocas4356@injeinc.co.kr" target="_blank" title="메일발송(새창열림)" class="btn_mail">pocas4356@injeinc.co.kr</a>
									</span>
								</li>
							</ul>
						</div>
						<div class="location">
							<div class="tabWrap">
								<ul class="tabList">	
									<li class="active"><a href="#">본사</a></li>
									<li><a href="#">서울사무소</a></li>
									<li><a href="#">대전지사</a></li>
								</ul>
								<div class="tabContent active">
									<div class="address">
										<h4 class="soundOnly">본사</h4>
										<b>대표전화 : <a href="tel:070-4609-6333" target="_blank" title="전화걸기(새창열림)" class="btn_tel">070-4609-6333</a></b>
										경기도 안양시 동안구 흥안대로 112-1(호계동, 웰빙타워 4층)
										<a href="http://naver.me/xMPuS3GD" target="_blank" title="새창열림" class="btn_mapOpen">상세지도보기</a>
									</div>
									<div class="mapping">
										<div id="map1">
											<!-- * 카카오맵 - 지도퍼가기 -->
											<!-- 1. 지도 노드 -->
											<div id="daumRoughmapContainer1555995965297" class="root_daum_roughmap root_daum_roughmap_landing"></div>

											<!--
												2. 설치 스크립트
												* 지도 퍼가기 서비스를 2개 이상 넣을 경우, 설치 스크립트는 하나만 삽입합니다.
											-->
											<script charset="UTF-8" class="daum_roughmap_loader_script" src="https://ssl.daumcdn.net/dmaps/map_js_init/roughmapLoader.js"></script>

											<!-- 3. 실행 스크립트 -->
											<script charset="UTF-8">
												new daum.roughmap.Lander({
													"timestamp" : "1555995965297",
													"key" : "t8qh",
													"mapWidth" : "588",
													"mapHeight" : "300"
												}).render();
											</script>
										</div>
									</div>
								</div>
								<div class="tabContent">
									<div class="address">
										<h4 class="soundOnly">서울사무소</h4>
										<b>대표전화 : <a href="tel:070-4609-6333" target="_blank" title="전화걸기(새창열림)" class="btn_tel">070-4609-6333</a></b>
										서울시 구로구 디지털로 33길 12, 1301호(구로동, 우림이비즈센터2차)
										<a href="http://naver.me/FdNJc7uT" target="_blank" title="새창열림" class="btn_mapOpen">상세지도보기</a>
									</div>
									<div class="mapping">
										<div id="map2">
											<!-- * 카카오맵 - 지도퍼가기 -->
											<!-- 1. 지도 노드 -->
											<div id="daumRoughmapContainer1555997836746" class="root_daum_roughmap root_daum_roughmap_landing"></div>

											<!-- 3. 실행 스크립트 -->
											<script charset="UTF-8">
												new daum.roughmap.Lander({
													"timestamp" : "1555997836746",
													"key" : "t8rb",
													"mapWidth" : "588",
													"mapHeight" : "300"
												}).render();
											</script>
										</div>
									</div>
								</div>
								<div class="tabContent">
									<div class="address">
										<h4 class="soundOnly">대전지사</h4>
										<b>대표전화 : <a href="tel:070-4609-6333" target="_blank" title="전화걸기(새창열림)" class="btn_tel">070-4609-6333</a></b>
										대전시 서구 둔산대로117번길 66, 909호 (만년동, 골드벤처타워)
										<a href="http://naver.me/xczImL4Y" target="_blank" title="새창열림" class="btn_mapOpen">상세지도보기</a>
									</div>
									<div class="mapping">
										<div id="map3">
											<!-- * 카카오맵 - 지도퍼가기 -->
											<!-- 1. 지도 노드 -->
											<div id="daumRoughmapContainer1555997243890" class="root_daum_roughmap root_daum_roughmap_landing"></div>


											<!-- 3. 실행 스크립트 -->
											<script charset="UTF-8">
												new daum.roughmap.Lander({
													"timestamp" : "1555997243890",
													"key" : "t8qw",
													"mapWidth" : "588",
													"mapHeight" : "300"
												}).render();
											</script>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</section>
			</article>
		</div>