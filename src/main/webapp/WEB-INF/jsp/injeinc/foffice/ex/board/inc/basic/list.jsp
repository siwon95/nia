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
					<tr <c:if test="${result.notiYn eq 'Y'}">class="notice"</c:if>>
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
							<c:when test="${contentMapping eq 'SUB_CONT'}">
								<c:if test="${BoardVo.bbsTempletGbn eq '16050600'}">
								<c:set var="subLinkCont" value="${result.subLinkCont}" />
									<td>
										<c:if test="${!empty subLinkCont}">
										<a href="<c:out value="${subLinkCont}"/>" target="_target" title="새창열림">
										</c:if>
										<c:out value="${bbsContentValue}" />
										<c:if test="${!empty subLinkCont}">
										</a>
										</c:if>
									</td>
								</c:if>
								<c:if test="${BoardVo.bbsTempletGbn ne '16050600'}">
									<c:set var="subCont" value="${fn:replace(fn:replace(fn:replace(result.subCont,'&','&amp;'),'<','&lt;'),'>','&gt;')}" />
									<c:set var="subCont" value="${fn:replace(subCont,'&amp;#','&#')}" />
									<td class="title">
										<c:if test="${empty result.delRsnTxt}">
										<c:if test="${result.openYnCont eq '21000200' || (!empty result.dupcode && result.dupcode eq ss_dupkey)}">
										<a href="/site/<c:out value="${strDomain}"/>/ex/board/View.do?cbIdx=<c:out value="${result.cbIdx}"/>&bcidx=<c:out value="${result.bcIdx}"/>" onclick="doBbsContentFView(<c:out value="${result.bcIdx}"/>);return false;"title="<c:out value="${n - status.index}"/>번글">
										</c:if>
											<c:if test="${result.answerDepth > 0}">
												<c:forEach varStatus="status" begin="1" end="${result.answerDepth}" step="1">
													&nbsp;&nbsp;&nbsp;
												</c:forEach>
												<span class="reply">답변</span>
											</c:if><c:out value="${subCont}"/>
										<c:if test="${result.openYnCont eq '21000200' || (!empty result.dupcode && result.dupcode eq ss_dupkey)}">
										</a>
										</c:if>
										</c:if>
										<c:if test="${!empty result.delRsnTxt}">
											<span class="strike"><c:out value="${subCont}"/></span>
											<p class="del"><c:out value="${result.delRsnTxt }" /></p>
										</c:if>
									</td>
								</c:if>
							</c:when>
							<c:when test="${contentMapping eq 'NAME_CONT'}">
								<td>
									<c:if test="${BoardVo.anonymityYn eq 'Y'}">OOO</c:if>
									<c:if test="${BoardVo.anonymityYn eq 'N'}"><c:out value="${result.nameCont}" /></c:if>
								</td>
							</c:when>
							<c:when test="${contentMapping eq 'REG_DT'}">
								<td>
									<fmt:parseDate var="regDt" value="${result.regDt}" pattern="yyyyMMddHHmmss"/>
									<fmt:formatDate var="regDt" value="${regDt}" pattern="yyyy.MM.dd"/>
									<c:out value="${regDt}" />
								</td>
							</c:when>
							<c:when test="${contentMapping eq 'OPEN_YN_CONT'}">
								<td>
									<c:if test="${result.openYnCont eq '21000200'}"><span class="open">공개</span></c:if>
									<c:if test="${result.openYnCont eq '21000100'}"><span class="no-open">비공개</span></c:if>
								</td>
							</c:when>
							<c:when test="${contentMapping eq 'MW_STATUS_CONT'}">
								<td>
									<c:if test="${result.ansRYn eq 'Y'}"><span class="status-end">답변완료</span></c:if>
									<c:if test="${result.ansRYn eq 'N'}"><span class="status-ing">답변중</span></c:if>
								</td>
							</c:when>
							<c:when test="${contentMapping eq 'EXT1'}">
								<td>
									<c:if test="${BoardVo.cbIdx == 39}"><a href="/html/ebook/<c:out value="${result.ext1}"/>/index.html" target="_blank" title="새창열림"><img alt="e-book" src="/images/board/ebook.png"></a></c:if>
									<c:if test="${BoardVo.cbIdx != 39}"><c:out value="${result.ext1}" /></c:if>
								</td>
							</c:when>
							<c:when test="${contentMapping eq 'FILE_CONT'}">
								<td><c:if test="${result.fileCnt > 0}"><span class="file">첨부파일</span></c:if></td>
							</c:when>
							<c:when test="${labelPropGbn eq '16020700' || labelPropGbn eq '16020800' || labelPropGbn eq '16020900'}">
								<td><!--SELECTBOX && RADIOBOX && CHECKBOX-->
									<c:set var="itemList" value="${cmm:getCodeList(itemCode)}" />
									<c:forEach var="itemInfo" items="${itemList}">
									<c:if test="${itemInfo.code eq bbsContentValue}"><c:out value="${itemInfo.codeName}"/></c:if>
									</c:forEach>
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