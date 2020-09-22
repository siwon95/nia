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

		<table class="view">
			<caption><c:out value="${BoardVo.cbName}" /> 상세정보 보기</caption>
			<colgroup>
				<col class="w15 m-w20"><col>
			</colgroup>
			<tbody>
			<c:forEach var="propertyInfo" items="${propertylist}">
			<c:set var="contentMapping" value="${propertyInfo.contentMapping}" />
			<c:set var="contentMappingL" value="${propertyInfo.contentMappingL}" />
			<c:set var="labelPropGbn" value="${propertyInfo.labelPropGbn}" />
			<c:set var="labelName" value="${propertyInfo.labelName}" />
			<c:set var="labelCompYn" value="${propertyInfo.labelCompYn}" />
			<c:set var="itemCode" value="${propertyInfo.itemCode}" />
			<c:set var="bbsContentValue" value="${bbs:getValue(BbsContentFVo, contentMappingL)}" />
				<c:choose>
				<c:when test="${contentMapping eq 'NO_CONT'}">
				</c:when>
				<c:when test="${contentMapping eq 'MW_STATUS_CONT'}">
				</c:when>
				<c:when test="${contentMapping eq 'CAPTION_CONT'}">
				</c:when>
				<c:when test="${contentMapping eq 'REG_DT'}">
				<tr><!-- REG_DT -->
					<th scope="row"><c:out value="${labelName}" /></th>
					<td>
						<fmt:parseDate var="regDt" value="${BbsContentFVo.regDt}" pattern="yyyyMMddHHmmss"/>
						<fmt:formatDate var="regDt" value="${regDt}" pattern="yyyy.MM.dd"/>
						<c:out value="${regDt}" />
					</td>
				</tr>
				</c:when>
				<c:when test="${contentMapping eq 'FILE_CONT'}">
				<c:if test="${fn:length(fileList) > 0}">
				<tr class="m-block"><!-- FILE_CONT  -->
					<th scope="row"><c:out value="${labelName}" /></th>
					<td class="attach-file">
						<c:forEach var="fileInfo" items="${fileList}">
						<p><a href="/common/board/Download.do?bcIdx=<c:out value="${fileInfo.bcIdx}"/>&amp;cbIdx=<c:out value="${fileInfo.cbIdx}"/>&amp;streFileNm=<c:out value="${fileInfo.streFileNm}"/>" class="file" title="파일 다운로드">
						<c:out value="${fileInfo.orignlFileNm }" /> [<c:out value="${bbs:byteCalculation(fileInfo.fileSize)}" />]
						</a>
							<a href="/common/board/DownloadView.do?cbIdx=<c:out value='${fileInfo.bcIdx }' />&amp;bcIdx=<c:out value='${fileInfo.cbIdx }' />&amp;streFileNm=<c:out value='${fileInfo.streFileNm }' />" title="새창" target="_blank" class="btn btn-quickView">바로보기</a>
						</p>
						</c:forEach>
					</td>
				</tr>
				</c:if>
				</c:when>
				<c:when test="${contentMapping eq 'M_LINK_CONT'}">
				<tr><!-- UCC LINK  -->
					<th scope="row"><c:out value="${labelName}" /></th>
					<td>
						<div class="view_contents">
							<div class="txt-area">
								<iframe width="640" height="390" src="https://www.youtube.com/embed/<c:out value="${bbs:getValue(BbsContentFVo, 'mLinkCont')}"/>" frameborder="0" allowfullscreen></iframe>
								<textarea class="movie-txt" readonly rows="7"><c:out value="${bbs:getValue(BbsContentFVo, 'captionCont')}"/></textarea>
							</div>
						</div>
					</td>
				</tr>
				</c:when>
				<c:when test="${contentMapping eq 'CLOB_CONT' && labelPropGbn eq '16020200' && BoardVo.editorYn eq 'Y'}">
				<tr class="m-block"><!-- CLOB_CONT 이고 TEXTAREA 이면서 EDITOR 사용 시  -->
					<th scope="row"><c:out value="${labelName}" /></th>
					<td>
						<div class="view_contents">
							<div class="txt-area"><c:out value="${bbsContentValue}" escapeXml="false" /></div>
						</div>
					</td>
				</tr>
				</c:when>
				<c:when test="${contentMapping eq 'OPEN_YN_CONT' }">
				<tr>
					<th scope="row"><c:out value="${labelName}" /></th>
					<td>
						<c:if test="${BbsContentFVo.mwAdOpenYn eq 'N' && BbsContentFVo.openYnCont eq '21000200'}">
						<span class="open">공개</span>
						</c:if>
						<c:if test="${BbsContentFVo.mwAdOpenYn eq 'Y' || BbsContentFVo.openYnCont eq '21000100'}">
						<span class="no-open">비공개</span>
						</c:if>
					</td>
				</tr>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${labelPropGbn eq '16020700' || labelPropGbn eq '16020800'}">
						<tr><!-- RADIOBOX 및 SELECTBOX-->
							<th scope="row"><c:out value="${labelName}" /></th>
							<td><c:out value="${cmm:getCodeName(bbsContentValue)}" /></td>
						</tr>
						</c:when>
						<c:when test="${labelPropGbn eq '16020900'}">
						<tr><!-- CHECKBOX-->
							<th scope="row"><c:out value="${labelName}" /></th>
							<td>
								<c:set var="itemList" value="${cmm:getCodeList(itemCode)}" />
								<c:forEach  var="itemInfo" items="${itemList}">
								<c:if test="${fn:indexOf(bbsContentValue, itemInfo.code) > -1 }">
								<p><c:out value="${itemInfo.codeName}" /></p>
								</c:if>
								</c:forEach>
							</td>
						</tr>
						</c:when>
						<c:when test="${labelPropGbn eq '16020200'}">
						<tr class="m-block"><!-- TEXTAREA -->
							<th scope="row"><c:out value="${labelName}" /></th>
							<td>
								<div class="view_contents">
									<div class="txt-area" style="word-break:break-all;"><c:out value="${cmm:outClobCont(bbsContentValue)}"/></div>
								</div>
							</td>
						</tr>
						</c:when>
						<c:when test="${labelPropGbn eq '16020300'}">
						<tr><!-- LINK -->
							<th scope="row"><c:out value="${labelName}" /></th>
							<td>
								<a href="<c:out value="${bbsContentValue}"/>" ><c:out value="${bbsContentValue}" /></a>
							</td>
						</tr>
						</c:when>
						<c:when test="${labelPropGbn eq '16020400'}">
						<tr><!-- ADRESS -->
							<th scope="row"><c:out value="${labelName}" /></th>
							<td><c:out value="${fn:replace(bbsContentValue, '_', ' ')}" /></td>
						</tr>
						</c:when>
						<c:otherwise>
						<tr>
							<th scope="row"><c:out value="${labelName}" /></th>
							<td><c:out value="${bbsContentValue}" /></td>
						</tr>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
				</c:choose>
			</c:forEach>
			</tbody>
		</table>