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
		<div class="photo-list">
		
			<ul>
				<c:set var="n" value="${resultCnt - (paginationInfo.currentPageNo-1)*paginationInfo.recordCountPerPage }"/>
				<c:forEach var="result" items="${resultList}" varStatus="status">
				<li>
					<a href="/site/<c:out value="${strDomain}"/>/ex/board/View.do?cbIdx=<c:out value="${result.cbIdx}"/>&bcidx=<c:out value="${result.bcIdx}"/>" onclick="doBbsContentFView('<c:out value="${result.bcIdx}"/>');return false;"title="<c:out value="${n - status.index}"/>번글">
						<c:set var="ImageInfo" value="${bbs:getBbsFileOne(result.bcIdx, result.cbIdx)}" />
						<c:if test="${!empty ImageInfo}">
							<c:if test="${result.cbIdx == 62}">
								<span class="photo photoA4"><img src="/common/board/Thumbnail.do?bcIdx=<c:out value="${ImageInfo.bcIdx}"/>&amp;cbIdx=<c:out value="${ImageInfo.cbIdx}"/>&amp;streFileNm=<c:out value="${ImageInfo.streFileNm}"/>&amp;width=174&amp;height=244" alt="<c:out value='${result.subCont}' />"></span>
							</c:if>
							<c:if test="${result.cbIdx != 62}">
								<span class="photo"><img src="/common/board/Thumbnail.do?bcIdx=<c:out value="${ImageInfo.bcIdx}"/>&amp;cbIdx=<c:out value="${ImageInfo.cbIdx}"/>&amp;streFileNm=<c:out value="${ImageInfo.streFileNm}"/>" alt="<c:out value='${result.subCont}' />"></span>
							</c:if>
						</c:if>
						<c:if test="${empty ImageInfo}">
							<span class="photo <c:out value="${result.cbIdx == 62 ? 'photoA4':''}"/>"><img src="/images/seocho/board/photo_default.jpg" alt="<c:out value='${result.subCont}' />"></span>
						</c:if>
						
						<c:forEach var="propertyInfo" items="${propertylist}">
							<c:set var="contentMapping" value="${propertyInfo.contentMapping}" />
							<c:set var="contentMappingL" value="${propertyInfo.contentMappingL}" />
							<c:set var="labelPropGbn" value="${propertyInfo.labelPropGbn}" />
							<c:set var="itemCode" value="${propertyInfo.itemCode}" />
							<c:set var="bbsContentValue" value="${bbs:getValue(result, contentMappingL)}" />
								
							<c:choose>
								<c:when test="${contentMapping eq 'FILE_CONT'}"></c:when>
								<c:when test="${contentMapping eq 'NO_CONT'}"><span class="no"><span class="hide">번호:</span><c:out value="${n - status.index}" /></span></c:when>
								<c:when test="${contentMapping eq 'CATE_CONT'}">
									<span class="category"><span class="hide">분류:</span>
										<c:set var="itemList" value="${cmm:getCodeList(itemCode)}" />
										<c:forEach var="itemInfo" items="${itemList}">
										<c:if test="${itemInfo.code eq bbsContentValue}"><c:out value="${itemInfo.codeName}"/></c:if>
										</c:forEach>
									</span>
								</c:when>
								<c:when test="${contentMapping eq 'SUB_CONT'}"><span class="hide">제목:</span><span class="title"><c:out value="${result.subCont}" /></span></c:when>
								<c:when test="${contentMapping eq 'NAME_CONT'}"><span class="writer"><span class="hide">작성자:</span><c:out value="${result.nameCont}" /></span></c:when>
								<c:when test="${contentMapping eq 'TEL_CONT'}"><span class="tel"><c:out value="${result.telCont}" /></span></c:when>
								<c:when test="${contentMapping eq 'PHONE_CONT'}"><span class="phone"><c:out value="${result.phoneCont}" /></span></c:when>
								<c:when test="${contentMapping eq 'COUNT_CONT'}"><span class="hit"><c:out value="${result.countCont}" /></span></c:when>
								<c:when test="${contentMapping eq 'CLOB_CONT'}"><span class="con"><c:out value="${result.clobContSearch}" /></span></c:when>
								<c:when test="${contentMapping eq 'REG_DT'}">
									<span class="date"><span class="hide">작성일:</span>
										<fmt:parseDate var="regDt" value="${result.regDt}" pattern="yyyyMMddHHmmss"/>
										<fmt:formatDate var="regDt" value="${regDt}" pattern="yyyy.MM.dd"/>
										<c:out value="${regDt}" />
									</span>
								</c:when>
								<c:when test="${labelPropGbn eq '16020700' || labelPropGbn eq '16020800' || labelPropGbn eq '16020900'}">
									<span><!--SELECTBOX && RADIOBOX && CHECKBOX-->
										<c:set var="itemList" value="${cmm:getCodeList(itemCode)}" />
										<c:forEach var="itemInfo" items="${itemList}">
										<c:if test="${itemInfo.code eq bbsContentValue}"><c:out value="${itemInfo.codeName}"/></c:if>
										</c:forEach>
									</span>
								</c:when>
								<c:otherwise>
									<span><c:out value="${bbsContentValue}" /></span>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						
					</a>
				</li>
				</c:forEach>
				<c:if test="${resultCnt == 0}">
				<li class="no-data"><spring:message code="common.nodata.msg" /></li>
				</c:if>
			</ul>
			
		</div>