<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:if test="${
	requestScope['ssChargerShowYn'] eq 'Y'
	|| requestScope['ssSatisfyShowYn'] eq 'Y'
	|| requestScope['ssSocialShowYn'] eq 'Y'
}">
			<!-- Contact & Satisfaction -->
			<div id="survey-container" class="noprint">
				<div class="survey-container-inner">
			
<c:if test="${requestScope['ssChargerShowYn'] eq 'Y'}">
	<c:set var="deptInfo" value="${requestScope['ssDeptInfo']}||||"/>
	<c:set var="arrDeptInfo" value="${fn:split(deptInfo,'|')}"/>
	
					<!-- contact  information -->
						<div id="contact">
							<p>홈페이지 내 게시된 정보 문의는 해당 부서로 문의주시기 바랍니다.</p>
							<span class="depart">담당부서 &nbsp;:&nbsp;
								<c:out value="${arrDeptInfo[0]}"/>
							</span>
							<c:choose>
								<c:when test="${arrDeptInfo[4] eq 'Y'}">
									<span class="contactor">담당자 &nbsp;:&nbsp; 미지정</span>
								</c:when>
								<c:otherwise>
									<span class="contactor">담당자 &nbsp;:&nbsp; <c:out value="${arrDeptInfo[1]}"/></span>
									<span class="phone">연락처  &nbsp;:&nbsp; <c:out value="${arrDeptInfo[2]}"/></span>
									<span class="phone">팩스  &nbsp;:&nbsp; <c:out value="${arrDeptInfo[3]}"/></span>
								</c:otherwise>
							</c:choose>
						</div>
					<!-- // contact  information -->
</c:if>
					
<c:if test="${requestScope['ssSatisfyShowYn'] eq 'Y'}">
				<script type="text/javascript">
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
							document.getElementById("point1").focus();
							return false;
						}
					}
				</script>
					<!-- satisfaction survey JCheol-->
					<form name="fSatisfaction" action="/site/<c:out value="${requestScope['ssSiteCode']}" />/user/satisfaction.do" method="post" onsubmit="return f_satisfaction(this);">
					<input type="hidden" name="returnUrl" value="<c:out value="${request.getRequestURL()}"/>"/>
					<input type="hidden" name="menuCode" value="<c:out value="${requestScope['ssMenuCode']}"/>"/>
					<div id="satisfaction">
						<fieldset class="satisfaction-fieldset">
							<legend>만족도 조사</legend>
							<ul class="satisfaction-level">
								<li><input type="radio" name="point" id="point1" value="5" /><label for="point1">매우만족</label></li>
								<li><input type="radio" name="point" id="point2" value="4" /><label for="point2">만족</label></li>
								<li><input type="radio" name="point" id="point3" value="3" /><label for="point3">보통</label></li>
								<li><input type="radio" name="point" id="point4" value="2" /><label for="point4">불만족</label></li>
								<li><input type="radio" name="point" id="point5" value="1" /><label for="point5">매우불만족</label></li>
							</ul>
							<input type="text" name="contents" id="sContents" title="의견입력" class="satisfaction-discursion" placeholder="의견입력" />
							<input type="submit"  value="입력" class="satisfaction-submit"/>
						</fieldset>
					</div>
					</form>					
					<!-- // satisfaction survey -->
</c:if>
				</div>		
			</div>
			<!-- // Contact & Satisfaction JCheol-->
</c:if>