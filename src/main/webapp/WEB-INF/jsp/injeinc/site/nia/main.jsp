<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div id="container" class="main">
	<!-- //mainVisual -->
	<div id="mainVisual">
		<img src="/images/nia/main/img_main_visual.jpg" alt="국민 누구나,집 근처에서 편하게 교육 받을 수 있는 디지털 역량교육! 가까운 디지털배움터를 찾아주세요!">
	</div>
	<!-- //mainVisual -->
	<!-- //mainSection -->
	<div id="mainSection">
		<div class="inner">
			<div id="mainPopup">
				<h3>홍보관</h3>
				<ul>
					<c:if test="${not empty mainRelationList}">
						<c:forEach var="rlList" items="${mainRelationList}">
							<li>
								<a href="<c:url value='/site/${strDomain}/ex/bbs/View.do?cbIdx=${rlList.cbIdx }&bcIdx=${rlList.bcIdx }'/>">
									<img src="/common/board/Download.do?bcIdx=<c:out value="${rlList.bcIdx}"/>&amp;cbIdx=<c:out value="${rlList.cbIdx}"/>&amp;streFileNm=<c:out value="${rlList.streFileNm}"/>" alt="섬네일이미지(<c:out value="${rlList.streFileNm}"/>)">
								</a>
							</li>
						</c:forEach>
					</c:if>
				</ul>
				<a href="/site/nia/ex/bbs/List.do?cbIdx=1189" class="more">더보기</a>
			</div>
			<div id="mainQuick">
				<ul>
					<li class="type1">
						<div>
							<b>디지털배움터 찾아보기</b>
							<a href="/site/nia/01/10100000000002020092216.jsp">바로가기</a>
						</div>
					</li>
					<li class="type2">
						<div>
							<b>교육 신청하기</b>
							<a href="#">바로가기</a>
						</div>
					</li>
					<li class="type3">
						<div>
							<b>강사·서포터즈 신청하기</b>
							<a href="#">바로가기</a>
						</div>
					</li>
					<li class="type4">
						<div>
							<b>소통게시판</b>
							<a href="/site/nia/ex/bbs/List.do?cbIdx=1188">바로가기</a>
						</div>
					</li>
				</ul>
			</div>
		</div>
		<!-- //mainSection -->
	</div>
</div>