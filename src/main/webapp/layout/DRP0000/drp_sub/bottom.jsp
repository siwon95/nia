<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<!-- webapp/layout/DRP0000/drp_sub/bottom.jsp -->	
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		
		<script>
			function f_satisfaction(f){
				var comchk = -1;
				for(var c=0;c < f.point.length; c++){
					if(f.point[c].checked) {
					 	comchk = c;
					 	break;
					}
				}
				
				if(comchk == -1){
					alert("만족도 값을 선택하세요");
					document.getElementById("verygood").focus();
					return false;
				}
			}
		</script>
		<div class='star_rating'>					
				<strong>문의처</strong>
				<span>담당기관 : 000기관</span>
				<span>담당자명 : 홍길동</span>
				<span>전화 : 02-000-0000</span>
				<span>팩스 : 02-000-0000</span>
				<div>
					<b>* 현재 화면에서 제공하는 정보의 만족도를 평가해 주세요.</b>
					<form name="fSatisfaction" action="/site/<c:out value="${requestScope['ssSiteCode']}" />/user/satisfaction.do" method="post" onsubmit="return f_satisfaction(this);" >
					<input type="hidden" name="returnUrl" value="<c:out value="${request.getRequestURL()}"/>"/>
					<input type="hidden" name="menuCode" value="<c:out value="${requestScope['ssMenuCode']}"/>"/> 
						<fieldset>
							<legend>만족도평가</legend>
							<ul>
								<li>
									<label for='verygood' class='l_wrap'>
									<span class='s_star checked'></span>
									<span class='s_star checked'></span>
									<span class='s_star checked'></span>
									<span class='s_star checked'></span>
									<span class='s_star checked'></span>
									<span class='soundOnly'>별다섯개</span>(매우 만족)
									<input type='radio' id='verygood' name='point' value='5'></label>
								</li>
								<li>
									<label for='good' class='l_wrap'>
									<span class='s_star checked'></span>
									<span class='s_star checked'></span>
									<span class='s_star checked'></span>
									<span class='s_star checked'></span>
									<span class='s_star'></span>
									<span class='soundOnly'>별네개</span>(만족)
									<input type='radio' id='good' name='point' value='4'></label>
								</li>
								<li>
									<label for='usually' class='l_wrap'>
									<span class='s_star checked'></span>
									<span class='s_star checked'></span>
									<span class='s_star checked'></span>
									<span class='s_star'></span>
									<span class='s_star'></span>
									<span class='soundOnly'>별세개</span>(보통)
									<input type='radio' id='usually' name='point' value='3'></label>
								</li>
								<li>
									<label for='dissatisfaction' class='l_wrap'>
									<span class='s_star checked'></span>
									<span class='s_star checked'></span>
									<span class='s_star'></span>
									<span class='s_star'></span>
									<span class='s_star'></span>
									<span class='soundOnly'>별두개</span>(불만족)
									<input type='radio' id='dissatisfaction' name='point' value='2'></label>
								</li>
								<li>
									<label for='very_dissatisfaction' class='l_wrap'>

									<span class='s_star checked'></span>
									<span class='s_star'></span>
									<span class='s_star'></span>
									<span class='s_star'></span>
									<span class='s_star'></span>
									<span class='soundOnly'>별한개</span>(매우 불만족)
									<input type='radio' id='very_dissatisfaction' name='point' value='1'></label>
								</li>
							</ul>
							<input type='submit' value='평가하기'>
						</fieldset>
					</form>
				</div>
		</div> <!-- star_rating -->
	</div><!-- Container > inner -->
</div><!-- Container -->	

			<div id='protection'> 
				<div class='inner'>
					<a href='/site/DRP0000/10/11001000000002020082615.jsp'>이용약관</a>
					<a href=''>저작권 정책</a>
					<a href=''>이메일 무단 수집거부</a>
					<a href=''>개인정보보호 책임자 : 홍길동 / hong@nia.or.kr</a>
					<div>
						<select>
							<option>관련사이트 바로가기</option>
						</select>
					</div>
				</div>
			</div>
			<div id='footer'>
				<div class='inner'>
					<div class='divGroup cols4'>
						<div>
							<a href='' class='f_logo'>디지털 역기능 대응 서비스 통합안내 시스템</a>
							<span>Copyright ⓒ 2020 한국정보화진흥원.<br>All Rights Reserved.</span>
						</div>
						<div>
							<dl>
								<dt>대구본원</dt>
								<dd>
									<address>대구광역시 동구 첨단로 53 (41068)</address>
									<span>대표전화 053-230-1114</span>
								</dd>
							</dl>
						</div>
						<div>
							<dl>
								<dt>서울사무소</dt>
								<dd>
									<address>서울특별시 중구 청계천로 14 (04520)</address>
									<span>대표전화 02-6191-2114</span>
								</dd>
							</dl>
						</div>
						<div>
							<dl>
								<dt>제주 NIA 글로벌센터 </dt>
								<dd>
									<address>제주특별자치도 서귀포시 서호중앙로 68-11 (63568)</address>
									<span>대표전화 064-909-3114</span>
								</dd>
							</dl>
						</div>
					</div>
				</div>
		</div>
		<div id='overlay'></div>
		<jsp:include page="/common/siteStates.do" flush="true"/>
	</div>
</body>
		