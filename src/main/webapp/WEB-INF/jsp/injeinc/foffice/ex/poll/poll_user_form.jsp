<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="cmm" uri="http://cms.injeinc.co.kr/cmm"%>
<head>
<script type="text/javascript">
//<![CDATA[
	function doPollListF() {
		$("#PollUserFVo").attr("action", "<c:url value='/site/${strDomain}/ex/poll/PollListF.do' />").submit();
	}
	
	function doPollUserFReg() {
		
		if(!$("#agreeChk").is(":checked")){
			alert("\'개인정보수집 및 이용에\' 동의해 주세요.\n동의하지 않으실 경우 접수하실 수 없습니다.");
			$("#agreeChk").focus();
			return;
		}
		
		<c:if test="${PollListFVo.plAddrYn eq 'Y'}">
		if($("#puZip").val() == "" || $("#puAddr1").val() == "") {
			alert("주소를 입력하여 주십시오.");
			$("#puZip").focus();
			return;
		}
		</c:if>
		<c:if test="${PollListFVo.plTelYn eq 'Y'}">
			<c:if test="${ss_numgubun eq 'H' || ss_numgubun eq 'C'}">
			if($("#puTel1").val() == "") {
				alert("첫자리 전화번호를 입력하여 주십시오.");
				$("#puTel1").focus();
				return;
			}
			if($("#puTel2").val() == "") {
				alert("중간자리 전화번호를 입력하여 주십시오.");
				$("#puTel2").focus();
				return;
			}
			if($("#puTel3").val() == "") {
				alert("끝자리 전화번호를 입력하여 주십시오.");
				$("#puTel3").focus();
				return;
			}
			</c:if>
		</c:if>
		<c:if test="${PollListFVo.plHpYn eq 'Y'}">
			<c:if test="${ss_numgubun eq 'M'}">
			if($("#puHp1").val() == "") {
				alert("첫자리 휴대폰번호를 입력하여 주십시오.");
				$("#puHp1").focus();
				return;
			}
			if($("#puHp2").val() == "") {
				alert("중간자리 휴대폰번호를 입력하여 주십시오.");
				$("#puHp2").focus();
				return;
			}
			if($("#puHp3").val() == "") {
				alert("끝자리 휴대폰번호를 입력하여 주십시오.");
				$("#puHp3").focus();
				return;
			}
			</c:if>
		</c:if>
		
		<c:if test="${PollListFVo.plEmailYn eq 'Y'}">
		if($("#puEmail1").val() == "") {
			alert("이메일을 입력하여 주십시오.");
			$("#puEmail1").focus();
			return;
		}
		
		if($("#puEmail2").val() == "") {
			alert("이메일을 입력하여 주십시오.");
			$("#puEmail2").focus();
			return;
		}
		
		</c:if>

		if(!checkItemList()) {
			alert("필수항목을 입력하여 주십시오.");
			return;
		}
		
		$("#PollUserFVo").attr("action", "/site/${strDomain}/ex/poll/PollUserFReg.do").submit();
	}

	function checkItemList() {
	<c:forEach var="questionInfo" items="${questionList }">
	<c:if test="${questionInfo.pqCheck eq 'Y'}">
		<c:if test="${questionInfo.pqType eq 'radio'}">
		if($("input:radio[name='<c:out value="${questionInfo.pqIdx}"/>']:checked").length < 1) {
			$("input:radio[name='<c:out value="${questionInfo.pqIdx}"/>']").eq(0).focus();
			return false;
		}
		<c:if test="${questionInfo.pqEtc eq 'Y'}">
		if($("input:radio[name='<c:out value="${questionInfo.pqIdx}"/>']").eq(<c:out value="${questionInfo.pqItemCnt-1}"/>).is(":checked") 
			&& $("#<c:out value="${questionInfo.pqIdx}"/>_etc").val() == "") {
			$("#<c:out value="${questionInfo.pqIdx}"/>_etc").focus();
			return false;
		}else if($("input:radio[name='<c:out value="${questionInfo.pqIdx}"/>']").eq(<c:out value="${questionInfo.pqItemCnt-1}"/>).is(":not(:checked)") 
			&& $("#<c:out value="${questionInfo.pqIdx}"/>_etc").val() != "") {
			$("#<c:out value="${questionInfo.pqIdx}"/>_etc").val("");
		}
		</c:if>
		</c:if>
		<c:if test="${questionInfo.pqType eq 'checkbox'}">
		if($("input:checkbox[name='<c:out value="${questionInfo.pqIdx}"/>']:checked").length < 1) {
			$("input:checkbox[name='<c:out value="${questionInfo.pqIdx}"/>']").eq(0).focus();
			return false;
		}
		<c:if test="${questionInfo.pqEtc eq 'Y'}">
		if($("input:checkbox[name='<c:out value="${questionInfo.pqIdx}"/>']").eq(<c:out value="${questionInfo.pqItemCnt-1}"/>).is(":checked")
			&& $("#<c:out value="${questionInfo.pqIdx}"/>_etc").val() == "") {
			$("#<c:out value="${questionInfo.pqIdx}"/>_etc").focus();
			return false;
		}else if($("input:checkbox[name='<c:out value="${questionInfo.pqIdx}"/>']").eq(<c:out value="${questionInfo.pqItemCnt-1}"/>).is(":not(:checked)") 
			&& $("#<c:out value="${questionInfo.pqIdx}"/>_etc").val() != "") {
			$("#<c:out value="${questionInfo.pqIdx}"/>_etc").val("");
		}
		</c:if>
		</c:if>
		<c:if test="${questionInfo.pqType eq 'selectbox' || questionInfo.pqType eq 'text' || questionInfo.pqType eq 'textarea'}">
		if($("#<c:out value="${questionInfo.pqIdx}"/>").val() == "") {
			$("#<c:out value="${questionInfo.pqIdx}"/>").focus();
			return false;
		}
		</c:if>
	</c:if>
	</c:forEach>
		return true;
	}
	
	function openAddrSearch() {
		var url = "/common/addrSearch/jusoPopup.jsp"
		var addr_pop = window.open(url,"pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
		addr_pop.focus();
	}
	
	function setAddrValue(addr1, addr2, zip) {
		$("#puZip").val(zip);
		$("#puAddr1").val(addr1);
		$("#puAddr2").val(addr2);
	}

	$(document).ready(function() {
		
		<c:if test="${PollListFVo.plEmailYn eq 'Y'}">
		
		cm_combo_email("#puEmail3", "EMAIL_CD");
		
		$("#puEmail3").change(function() {
			if($(this).val() == "") {
				$("#puEmail2").val("");
				$("#puEmail2").attr("readonly", false);
			}else {
				$("#puEmail2").val($(this).val());
				$("#puEmail2").attr("readonly", true);
			}
		});
		
		</c:if>
		
		$(".onlynum").keyup(
			function() {
				var regexp = /[^0-9]/g;
				var v = $(this).val();
				if(regexp.test(v)) {
					alert("숫자만 입력가능 합니다.");
					$(this).val(v.replace(regexp, ""));
				}
			}
		);
	});
//]]>
</script>
</head>
<body>
<form:form commandName="PollUserFVo" name="PollUserFVo" method="post">
	<form:hidden path="searchCdIdx" />
	<form:hidden path="plIdx" />
	<form:hidden path="searchKeyword" />
	<article class="board-row">
		<!--  -->
		<div class="enquete-container">
		<!-- 설문조사 부서 -->
			<div class="bbsView">
			<div class="bbsViewTitle">
				<h1 class="result-title"><c:out value="${PollListFVo.plTitle}" /></h1>
					<ul >
						<li>						
							<div class="align-left">
								<span class="surveyTim-title">관리부서</span><span class="surveyTim-contents"><c:out value="${PollListFVo.cdSubject}" /></span>
							</div><div class="align-right">
								<span class="surveyTim-title">담당자 연락처</span><span class="surveyTim-contents"><c:out value="${PollListFVo.plManagerTel}" /></span>
							</div>
						</li>
						<li>
							<span class="surveyTim-title">담당자 이름</span><span class="surveyTim-contents"><c:out value="${PollListFVo.plManagerName}" /></span>
						</li>
						<li>
							<span class="surveyTim-title">설문조사 기간</span><span class="surveyTim-contents"><c:out value="${fn:substring(PollListFVo.plStartDate, 0, 10)}" /> ~ <c:out value="${fn:substring(PollListFVo.plEndDate, 0, 10)}" /></span>
						</li>
						<%-- <li>
							<span class="surveyTim-title">안내문</span><span class="surveyTim-contents">
								${cmm:textAreaToHtml(PollListFVo.plGuide)}
							</span>
						</li> --%>
					</ul>
			</div>
			<div class="bbsViewContent">
				<div class="bbsViewEditor">
			    ${cmm:textAreaToHtml(PollListFVo.plGuide)}				
				</div>
			</div>
			</div>
			<!-- //설문조사 부서 -->
			
		<!-- 개인정보 입력 -->
		<c:if test="${PollListFVo.plAddrYn eq 'Y' || PollListFVo.plTelYn eq 'Y' || PollListFVo.plHpYn eq 'Y' || PollListFVo.plEmailYn eq 'Y'}">
		<div class="enquete-data2">
			<h1>개인정보 입력</h1>
			<div class="survey-data-container">
				<form id="" action="" method="post">
					<fieldset>
						<legend>설문조사에 참여하는 사용자의 개인정보 입력 폼</legend>
							<ul class="survey-list">
								<c:if test="${PollListFVo.plAddrYn eq 'Y'}">
								<li>
									<span class="personalData-title"><label for="">주소 <span class="required-mark" title="필수입력 항목">&#9733;</span></label></span><span class="personalData-contents">
									<form:input path="puZip" size="8" maxlength="7" title="우편번호" readonly="true" />
									<a href="#zip" onclick="openAddrSearch();return false;" class="btn_zipcode" target="_blank">우편번호검색</a>									
									<form:input path="puAddr1" title="우편번호 검색 후 자동입력 기본주소" readonly="true" class="add-coltype-w298" />
									<form:input path="puAddr2" title="상세주소를 입력해주세요" class="add-coltype-w526"  />									
									</span>
								</li>
								</c:if>
								<c:if test="${PollListFVo.plTelYn eq 'Y'}">
								<li>
									<span class="personalData-title"><label for="">전화번호 <c:if test="${ss_numgubun eq 'H' || ss_numgubun eq 'C'}"><span class="required-mark" title="필수입력 항목">&#9733;</span></c:if></label></span><span class="personalData-contents">
									<!-- select class="select-box select-box-no w91">
										<option>선택</option>
									</select -->
									 <form:input path="puTel1" maxlength="4" class="txt-box w91" title="전화번호앞자리" /> - <form:input path="puTel2" maxlength="4" class="txt-box w91" title="전화번호중간자리" /> - <form:input path="puTel3" maxlength="4" class="txt-box w91" title="전화번호끝자리" />
									</span> 
								</li>
								</c:if>
								<c:if test="${PollListFVo.plHpYn eq 'Y'}">
								<li>
									<span class="personalData-title"><label for="">휴대폰번호 <c:if test="${ss_numgubun eq 'M'}"><span class="required-mark" title="필수입력 항목">&#9733;</span></c:if></label></span><span class="personalData-contents">
									<!-- select class="select-box select-box-no w91">
										<option>선택</option>
									</select -->
									<form:input path="puHp1" maxlength="4" class="txt-box w91" title="휴대폰번호앞자리" /> - <form:input path="puHp2" maxlength="4" class="txt-box w91" title="휴대폰번호중간자리" /> - <form:input path="puHp3" maxlength="4" class="txt-box w91" title="휴대폰번호끝자리" />
									</span>
								</li>
								</c:if>
								<c:if test="${PollListFVo.plEmailYn eq 'Y'}">
								<li>
									<span class="personalData-title"><label for="">이메일 <span class="required-mark" title="필수입력 항목">&#9733;</span></label></span><span class="personalData-contents">
									<form:input path="puEmail1" title="이메일" class="email-box-w204" /> @ 								
									<select id="puEmail3" name="puEmail3" title="도메인 직접선택" class="select-box select-box-txt w158">
										<option>직접입력</option>
									</select>
									<form:input path="puEmail2" title="도메인 직접입력" class="email-box-w198" />									
									</span>
								</li>
								</c:if>
							</ul>					
					</fieldset>
				</form>
			</div>
		</div>
		</c:if>
		
		
		<!-- 설문조사지 -->
		<div class="enquete-list-container">
			<div class="enquete-template">
				<!-- <h2 class="enquete-template-title">◇ 설문을 시작합니다. ◇</h2> -->
				<fieldset>
					<legend>◇ 설문을 시작합니다. ◇</legend>
				<div class="enquete-list">
					<ol>
						<c:forEach var="questionInfo" items="${questionList }" varStatus="status">
						<c:if test="${questionInfo.pqType ne 'title'}">
						<li>
							<div class="question-contents">
								<p class="question-main">
									<c:if test="${questionInfo.pqCheck eq 'Y'}"><span class="required">*</span></c:if> 
									<c:if test="${questionInfo.pqType eq 'text' || questionInfo.pqType eq 'textarea' || questionInfo.pqType eq 'selectbox'}">
										<label for="<c:out value="${questionInfo.pqIdx}"/>">
									</c:if>
									<c:out value="${questionInfo.pqTitle}" />
									<c:if test="${questionInfo.pqType eq 'text' || questionInfo.pqType eq 'textarea' || questionInfo.pqType eq 'selectbox'}">
										</label>
									</c:if>
								</p>
								<c:if test="${!empty questionInfo.pqDetail}">
								<p class="question-sub">${cmm:textAreaToHtml(questionInfo.pqDetail)}	</p>
								</c:if>
								<!-- 가로형 -->
								<div class="question-answer">
									<ul 
										<c:choose>
											<c:when test="${questionInfo.pqItemDirection eq 'R'}">class="answer-coltype"</c:when>
											<c:otherwise>class="answer-rowtype"</c:otherwise>
										</c:choose>
									>										
										<c:if test="${questionInfo.pqType eq 'radio'}">
										<c:set var="pqItemList" value="${cmm:split(questionInfo.pqItemList, '｜', questionInfo.pqItemCnt)}" />
										<c:forEach begin="0" varStatus="status2" end="${questionInfo.pqItemCnt-1}">
											<li>
												<input type="radio" id="<c:out value="${questionInfo.pqIdx}"/>_<c:out value="${status2.count}"/>" name="<c:out value="${questionInfo.pqIdx}"/>" value="<c:out value="${status2.count}"/>" class="answer-radio" />
												<label for="<c:out value="${questionInfo.pqIdx}"/>_<c:out value="${status2.count}"/>"><c:out value="${fn:length(pqItemList) > status2.index ? pqItemList[status2.index]:''}"/></label>
												<c:if test="${status2.last && questionInfo.pqEtc eq 'Y'}">
												<div class="question-answer-box">
													<input type="text" id="<c:out value="${questionInfo.pqIdx}"/>_etc" name="<c:out value="${questionInfo.pqIdx}"/>_etc" class="answer-txt-box etc-box" title="기타의견 입력" value="" />
												</div>
												</c:if>											
											</li>
										</c:forEach>
										</c:if>		
										<c:if test="${questionInfo.pqType eq 'selectbox'}">
											
											<div class="question-answer-box">
												<select id="<c:out value="${questionInfo.pqIdx}"/>" name="<c:out value="${questionInfo.pqIdx}"/>" class="select-box select-box-txt">
													<option value="">선택하세요</option>
													<c:set var="pqItemList" value="${cmm:split(questionInfo.pqItemList, '｜', questionInfo.pqItemCnt)}" />
													<c:forEach begin="0" varStatus="status2" end="${questionInfo.pqItemCnt-1}">
													<option value="<c:out value="${status2.count}"/>"><c:out value="${fn:length(pqItemList) > 0 ? pqItemList[status2.index]:''}" /></option>
													</c:forEach>
												</select>
											</div>
											
										</c:if>
										<c:if test="${questionInfo.pqType eq 'checkbox'}">
											
											<c:set var="pqItemList" value="${cmm:split(questionInfo.pqItemList, '｜', questionInfo.pqItemCnt)}" />
											<c:forEach begin="0" varStatus="status2" end="${questionInfo.pqItemCnt-1}">					
												<li>							
												<input type="checkbox" id="<c:out value="${questionInfo.pqIdx}"/>_<c:out value="${cmm:changeNumberAlphabat(status2.count)}"/>" name="<c:out value="${questionInfo.pqIdx}"/>" value="<c:out value="${cmm:changeNumberAlphabat(status2.count)}"/>" class="answer-radio"/> 
												<label for="<c:out value="${questionInfo.pqIdx}"/>_<c:out value="${cmm:changeNumberAlphabat(status2.count)}"/>"><c:out value="${fn:length(pqItemList) > 0 ? pqItemList[status2.index]:''}"/></label>
												<c:if test="${status2.last && questionInfo.pqEtc eq 'Y'}">
													<div class="question-answer-box">
														<input type="text" id="<c:out value="${questionInfo.pqIdx}"/>_etc" name="<c:out value="${questionInfo.pqIdx}"/>_etc" value="" class="answer-txt-box etc-box" title="기타의견 입력" />
													</div>
												</c:if>			
												</li>																		
											</c:forEach>
											
										</c:if>		
										<c:if test="${questionInfo.pqType eq 'text'}">
											<div class="question-answer-box">
											<input type="text" id="<c:out value="${questionInfo.pqIdx}"/>" name="<c:out value="${questionInfo.pqIdx}"/>" value="" class="answer-txt-box" />
											</div>
										</c:if>
										<c:if test="${questionInfo.pqType eq 'textarea'}">
											<div class="question-answer-box">
											<textarea id="<c:out value="${questionInfo.pqIdx}"/>" name="<c:out value="${questionInfo.pqIdx}"/>" class="txtAtea"></textarea>
											</div>
										</c:if>																	
									</ul>
								</div>								
							</div>
						</li>
						</c:if>						
						</c:forEach>
					</ol>					
					
					<!-- 개인정보동의 -->
					<div class="personaldata_container">
						<div class="personaldata">
							<!-- <h3 class="personaldata-title">개인정보 제공동의</h3> -->
							<fieldset>
							<legend>개인정보 제공동의</legend>
							<ul>
								<li>귀하께서 신고하신 ‘불합리 규제 신고’ 내용은 신고하신 기관 이외의 업무에 해당할 경우 다른 행정기관에 개인정보가 제공될 수 있습니다.</li>
								<li>제공되는 개인정보는 이름, 연락처, 이메일이며 귀하께서 기재하신 정보입니다.귀하께서는 개인정보제공에 동의하지 않을 수 있으며, 동의하지 않더라도 신고는 가능합니다. 다만, 신고하신 내용이 신고기관의 업무가 아닌 경우에 소관 행정기관으로 이송/이첩되지 아니하고 종결처리 될 수 있습니다.
								</li>
							</ul>
							<div class="personaldata-ok">개인정보 제공 동의하십니까?  <input type="checkbox" id="agreeChk" name="agreeChk" value="yes" class="checkbox-ok" /><label for="">동의합니다.</label></div>
							</fieldset>
						</div>
					</div>				
					<!-- //개인정보동의 -->
					
					
					
				</div><!-- //enquete-list -->
				</fieldset>
				<!-- 설문조사완료버튼? -->
					<div class="btnArea">
						<a href="#none" onclick="doPollUserFReg();return false;" class="btn_l on w100 btn_bbsAnswer">설문완료</a>
					</div>
					<!-- //설문조사완료버튼? -->
			</div>
			
		</div>
	<!-- //설문조사지 -->
	
	
	
	</div><!-- //설문조사_____끗 -->	
	</article>
</form:form>
</body>