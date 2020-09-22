<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="cmm" uri="http://cms.injeinc.co.kr/cmm"%>
<%@ taglib prefix="poll" uri="http://cms.injeinc.co.kr/poll"%>

<script type="text/javascript">
//<![CDATA[
	
	function doPollListFTextReport(pqIdx, obj) {
		var tdObj = $(obj).parent();
		var strDomain="<c:out value='${strDomain}'/>";
		$.ajax({
			type: "POST",
			url: "<c:url value='/site/${strDomain}/ex/poll/PollListFTextReportAx.do' />",
			data : {"pqIdx":pqIdx},
			dataType : "json",
			success:function(data) {
				if(data.result) {
					
					var pqType = data.questionInfo.pqType;

					tdObj.empty();
					
					var count = 0;
					
					$(data.answerList).each(function() {
						count++;
						if(pqType == "text" || pqType == "textarea") {
							tdObj.append("<p><span>"+count+"</span> "+this.paText+"</p>");
						}else{
							tdObj.append("<p>"+count+". "+this.paText+"</p>");
						}
					});
					
				}else{
					alert(data.message);
				}
			
			},
			error: function(xhr, status, error) {
				alert(status);
			}
		});
		
	}
	
	$(document).ready(function(){
		
		$(".progress-bar").each(function(){
			var pro_txt = $(this).children().text();
			var slice_txt = pro_txt.slice(0,-1);
			//var int_value = parseInt(slice_txt);
			$(this).css("width",slice_txt + "%");
		})
		
	});
	
//]]>
</script>
<article class="content-row">
	<!-- progress Bar Type -->
	<div class="result-bar-container">
		<h1>설문결과</h1>
		<c:forEach var="result" items="${resultList}" varStatus="status">
		<c:if test="${result.pqType ne 'title'}">
		<div class="result-container">
			<p class="question-main"><c:if test="${result.pqCheck eq 'Y'}"><span class="required">*</span></c:if><c:out value="${result.pqTitle}" /></p>
			<div class="tableBox over_vi">
				<c:if test="${result.pqType eq 'radio' || result.pqType eq 'selectbox'}">
				<table class="view">
					<caption>순번, 보기, 응답수, 총응답, 비율의 정보가 포함된 설문조사 결과 안내표입니다</caption>
					<colgroup>
						<col style="width:10%;" />
						<col style="width:40%;" />
						<col style="width:10%;" />
						<col style="width:10%;" />
						<col style="width:30%;" />
					</colgroup>
					<thead>
						<tr>
							<th scope="col">순번</th>
							<th scope="col">보기</th>
							<th scope="col">응답수</th>
							<th scope="col">총응답</th>
							<th scope="col">비율</th>
						</tr>
					</thead>
					<tbody>						
						<c:set var="pqItemList" value="${cmm:split(result.pqItemList, '｜', result.pqItemCnt)}" />
						<c:forEach begin="0" varStatus="status2" end="${result.pqItemCnt-1}">
						<c:set var="count" value="${poll:getCount(result.pqIdx, result.pqType, status2.count)}" />
						<c:if test="${resultTotal > 0}">
						<c:set var="percent" value="${100*count/resultTotal}" />
						<c:set var="percent" value="${percent+((percent%1>0.5)?(1-(percent%1))%1:-(percent%1))}" />
						</c:if>
						<c:if test="${resultTotal == 0}">
						<c:set var="percent" value="0" />
						</c:if>
						<fmt:parseNumber var="percent" integerOnly="true" value="${percent}" />
						<fmt:parseNumber var="percent2" integerOnly="true" value="${percent*0.81}" />
						<tr>
							<td class="txt_center"><c:out value='${status2.count}' /></td>
							<td class="txt-left"><c:out value="${fn:length(pqItemList) > status2.index ? pqItemList[status2.index]:''}" /><c:if test="${result.pqEtc eq 'Y' && status2.count eq result.pqItemCnt}"><a href="#none" title="기타답변이 팝업으로 열립니다" onclick="doPollListFTextReport('<c:out value="${result.pqIdx}"/>', this);return false;" class="btn btn-card-more">기타보기</a></c:if></td>
							<td class="txt_center"><c:out value='${count}' /></td>
							<td class="txt_center"><c:out value='${resultTotal}' /></td>
							<td class="testColor">
								<div class="progress-bar-box">
									<span class="progress-bar" style="width:${percent2}%;"><!-- width:81%일때 100%임 -->
										<span class="progress-txt"><c:out value='${percent}' />%</span>
									</span>
								</div>
							</td>
						</tr>
						</c:forEach>																						
					</tbody>
				</table>
				</c:if>
				<c:if test="${result.pqType eq 'checkbox'}">
				<table class="view">
					<caption>순번, 보기, 응답수, 총응답, 비율의 정보가 포함된 설문조사 결과 안내표입니다</caption>
					<colgroup>
						<col style="width:10%;" />
						<col style="width:40%;" />
						<col style="width:10%;" />
						<col style="width:10%;" />
						<col style="width:30%;" />
					</colgroup>
					<thead>
						<tr>
							<th scope="col">순번</th>
							<th scope="col">보기</th>
							<th scope="col">응답수</th>
							<th scope="col">총응답</th>
							<th scope="col">비율</th>
						</tr>
					</thead>
					<tbody>						
						<c:set var="pqItemList" value="${cmm:split(result.pqItemList, '｜', result.pqItemCnt)}" />
						<c:forEach begin="0" varStatus="status2" end="${result.pqItemCnt-1}">
							<c:set var="count" value="${poll:getCount(result.pqIdx, result.pqType, cmm:changeNumberAlphabat(status2.count))}" />
							<c:if test="${resultTotal > 0}">
							<c:set var="percent" value="${100*count/resultTotal}" />
							<c:set var="percent" value="${percent+((percent%1>0.5)?(1-(percent%1))%1:-(percent%1))}" />
							</c:if>
							<c:if test="${resultTotal == 0}">
							<c:set var="percent" value="0" />
							</c:if>
							<fmt:parseNumber var="percent" integerOnly="true" value="${percent}" />
							<fmt:parseNumber var="percent2" integerOnly="true" value="${percent*0.81}" />							
						<tr>
							<td class="txt_center"><c:out value='${status2.count}' /></td>
							<td class="txt-left"><c:out value="${fn:length(pqItemList) > status2.index ? pqItemList[status2.index]:''}" /><c:if test="${result.pqEtc eq 'Y' && status2.count eq result.pqItemCnt}"><a href="#none" title="기타답변이 팝업으로 열립니다" onclick="doPollListFTextReport('<c:out value="${result.pqIdx}"/>', this);return false;" class="btn btn-card-more">기타보기</a></c:if></td>
							<td class="txt_center"><c:out value='${count}' /></td>
							<td class="txt_center"><c:out value='${resultTotal}' /></td>
							<td class="testColor">
								<div class="progress-bar-box">
									<span class="progress-bar" style="width:${percent2}%;"><!-- width:81%일때 100%임 -->
										<span class="progress-txt"><c:out value='${percent}' />%</span>
									</span>
								</div>
							</td>
						</tr>
						</c:forEach>																						
					</tbody>
				</table>
				</c:if>
				<c:if test="${result.pqType eq 'text' || result.pqType eq 'textarea'}">
				<table class="view">
					<caption>순번, 보기, 응답수, 총응답, 비율의 정보가 포함된 설문조사 결과 안내표입니다</caption>
					<colgroup>
						<col style="width:10%;" />
						<col style="width:40%;" />
						<col style="width:10%;" />
						<col style="width:10%;" />
						<col style="width:30%;" />
					</colgroup>
					<thead>
						<tr>
							<th scope="col">순번</th>
							<th scope="col">보기</th>
							<th scope="col">응답수</th>
							<th scope="col">총응답</th>
							<th scope="col">비율</th>
						</tr>
					</thead>
					<tbody>						
						<c:set var="count" value="${poll:getCount(result.pqIdx, result.pqType,'aa')}" />
						<c:if test="${resultTotal > 0}">
						<c:set var="percent" value="${100*count/resultTotal}" />
						<c:set var="percent" value="${percent+((percent%1>0.5)?(1-(percent%1))%1:-(percent%1))}" />
						</c:if>
						<c:if test="${resultTotal == 0}">
						<c:set var="percent" value="0" />
						</c:if>
						<fmt:parseNumber var="percent" integerOnly="true" value="${percent}" />
						<fmt:parseNumber var="percent2" integerOnly="true" value="${percent*0.81}" />
						<tr>
							<td class="txt_center">1</td>
							<td>
							<!-- 주관식결과 보기 팝업창 디자인없음 추후에 스타일 추가해야됨 -->
							주관식<a href="#none" title="주관식 결과가 팝업으로 열립니다" onClick="javascript:resultPop();"class="btn btn-card-more">결과보기</a></td>
							<td class="txt_center"><c:out value='${count}' /></td>
							<td class="txt_center"><c:out value='${resultTotal}' /></td>
							<td class="testColor">
								<div class="progress-bar-box">
									<span class="progress-bar" style="width:${percent2}%;"><!-- width:81%일때 100%임 -->
										<span class="progress-txt"><c:out value='${percent}' />%</span>
									</span>
								</div>
							</td>
						</tr>						
					</tbody>
				</table>
				</c:if>
			</div>
		</div>
		</c:if>
		</c:forEach>
	</div>	
	<div class="result-contents-container">
		<h1>설문내용</h1>
		<div class="bbsView">
			<div class="bbsViewTitle">
				<b class="result-title"><c:out value="${PollListFVo.plTitle}" /></b>
				<ul>
					<li><c:out value="${fn:substring(PollListFVo.plStartDate, 0, 10)}" /> ~ <c:out value="${fn:substring(PollListFVo.plEndDate, 0, 10)}" /></li>
				</ul>
			</div>
			<div class="bbsViewContent">
				<div class="bbsViewEditor">
			    ${cmm:textAreaToHtml(PollListFVo.plGuide)}	
			    </div>			
			</div>
		</div>
	</div>
	
	<!-- 버튼 -->
	<div class="board-foot-container">
		<div class="btn-area">
			<div class="btnArea">
				<a href="/site/<c:out value='${strDomain}'/>/ex/poll/PollListI.do" class="btn_l on w100 btn_bbsAnswer">목록</a>
			</div>
		</div>
	</div>
	<!-- //버튼 -->
</article>
