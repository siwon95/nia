<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="cmm" uri="http://cms.injeinc.co.kr/cmm"%>
<%@ taglib prefix="bbs" uri="http://cms.injeinc.co.kr/bbs"%>
		<div class="board-list">
		
			<p id="b_summary" class="hide">
				<c:out value="${BoardVo.cbName}" />의
				<c:forEach var="propertyInfo" items="${propertylist}" varStatus="status">
					<c:if test="${!status.first}">, </c:if><c:out value="${propertyInfo.labelName}" />
				</c:forEach>
				안내
			</p>
		
			<table class="list <c:if test="${BoardVo.mListYn eq 'Y' && isMobile}">mobile_list</c:if>" aria-describedby="b_summary">
				<caption><c:out value="${BoardVo.cbName}" /> 목록</caption>
				<colgroup>
					<c:forEach var="propertyInfo" items="${propertylist}">
						<col class="w<c:out value="${propertyInfo.labelProvSize}"/>">
					</c:forEach>
				</colgroup>
				<thead>
					<tr>
					<c:forEach var="propertyInfo" items="${propertylist}">
						<th scope="col"><c:out value="${propertyInfo.labelName}" /></th>
					</c:forEach>
					</tr>
				</thead>
				<tbody>
					<c:set var="n" value="${resultCnt - (paginationInfo.currentPageNo-1)*paginationInfo.recordCountPerPage }"/>
					<c:forEach var="result" items="${resultList}" varStatus="status">
					<c:set var="processYn" value="N" />
					<tr>
						<c:forEach var="propertyInfo" items="${propertylist}">
							<c:set var="contentMapping" value="${propertyInfo.contentMapping}" />
							<c:set var="contentMappingL" value="${propertyInfo.contentMappingL}" />
							<c:set var="labelPropGbn" value="${propertyInfo.labelPropGbn}" />
							<c:set var="itemCode" value="${propertyInfo.itemCode}" />
							<c:set var="bbsContentValue" value="${bbs:getValue(result, contentMappingL)}" />
							
							<c:choose>
								<c:when test="${contentMapping eq 'NO_CONT'}">
									<td><c:out value="${n - status.index}" /></td>
								</c:when>
								<c:when test="${contentMapping eq 'MW_STATUS_CONT'}">
									<td>
										<c:choose>
										<c:when test="${result.ansYnCont eq '22000200'}"><span class="status-move">답변요청없음</span></c:when>
										<c:when test="${result.mwNoReplyYn eq 'Y'}"><span class="status-move">답변비대상</span></c:when>
										<c:otherwise>
											<c:set var="MinwonResultList" value="${bbs:getMinwonResultList(result.bcIdx, result.cbIdx)}" />
											<c:set var="MinwonResultTotal" value="${bbs:getMinwonResultTotal(result.bcIdx, result.cbIdx)}" />
											<c:choose>
												<c:when test="${!empty MinwonResultTotal && MinwonResultTotal.auditYn ne '27000200'}"><span class="status-end">답변완료</span></c:when>
												<c:when test="${(fn:length(MinwonResultList) == 1) && (MinwonResultList[0].tempYn eq 'Y')}"><span class="status-end">답변완료</span></c:when>
												<c:otherwise>
													<c:set var="processYn" value="Y" />
													<span class="status-ing">처리중</span>
												</c:otherwise>
											</c:choose>
										</c:otherwise>
										</c:choose>
									</td>
								</c:when>
								<c:when test="${contentMapping eq 'SUB_CONT'}">
									<c:set var="subCont" value="${fn:replace(fn:replace(fn:replace(result.subCont,'&','&amp;'),'<','&lt;'),'>','&gt;')}" />
									<c:set var="subCont" value="${fn:replace(subCont,'&amp;#','&#')}" />
									<td class="title">
										<c:if test="${result.openYnCont eq '21000200' || (!empty result.dupcode && result.dupcode eq ss_dupkey)}">
										<a href="/site/<c:out value="${strDomain}"/>/ex/board/View.do?cbIdx=<c:out value="${result.cbIdx}"/>&bcidx=<c:out value="${result.bcIdx}"/>" onclick="doBbsContentFView('<c:out value="${result.bcIdx}"/>');return false;"title="<c:out value="${n - status.index}"/>번글">
										</c:if>
										<c:out value="${subCont}" />
										<c:if test="${result.openYnCont eq '21000200' || (!empty result.dupcode && result.dupcode eq ss_dupkey)}">
										</a>
										</c:if>
										<c:if test="${processYn eq 'Y'}">
											<c:set var="deadLine" value="${bbs:getMinwonResultDeadLine(result.bcIdx, result.cbIdx)}" />
											<c:set var="delayInfo" value="${bbs:getMinwonResultDelay(result.bcIdx, result.cbIdx)}" />
											<c:if test="${!empty delayInfo}">
												<fmt:parseDate var="mcDelayDay" value="${delayInfo.mcDelayDay}" pattern="yyyyMMdd"/>
												<fmt:formatDate var="mcDelayDay" value="${mcDelayDay}" pattern="yyyy.MM.dd"/>
												<p class="del" style='color:red;'><c:out value="${delayInfo.mcDelayRsn}" /> 등으로 <c:out value="${mcDelayDay}" />까지 답변이 연기되었습니다.</p>
											</c:if>
											<c:if test="${empty delayInfo && !empty deadLine}">
												<p class="del" <c:if test="${fn:replace(deadLine, '.', '') <= nowDate}">style='color:red;'</c:if>  ><c:out value="${deadLine}" />까지 답변 드리겠습니다.</p>
											</c:if>
										</c:if>
									</td>
								</c:when>
								<c:when test="${contentMapping eq 'NAME_CONT'}">
									<td>OOO</td>
								</c:when>
								<c:when test="${contentMapping eq 'OPEN_YN_CONT'}">
									<td>
										<c:if test="${result.openYnCont eq '21000200'}"><span class="open">공개</span></c:if>
										<c:if test="${result.openYnCont eq '21000100'}"><span class="no-open">비공개</span></c:if>
									</td>
								</c:when>
								<c:when test="${contentMapping eq 'REG_DT'}">
									<td>
										<fmt:parseDate var="regDt" value="${result.regDt}" pattern="yyyyMMddHHmmss"/>
										<fmt:formatDate var="regDt" value="${regDt}" pattern="yyyy.MM.dd"/>
										<c:out value="${regDt}" />
									</td>
								</c:when>
								<c:otherwise>
									<td><c:out value="${bbsContentValue}" /></td>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:forEach>
					<c:if test="${resultCnt == 0}">
					<tr>
						<td colspan="<c:out value="${fn:length(propertylist)}"/>"><spring:message code="common.nodata.msg" /></td>
					</tr>
					</c:if>
				</tbody>
			</table>
		</div>