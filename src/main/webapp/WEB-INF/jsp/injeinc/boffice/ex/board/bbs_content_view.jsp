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
<%-- ------------------------------------------------------------
- 제목 : 게시물관리
- 파일명 : bbs_content_view.jsp
- 최종수정일 : 2019-04-29
- 상태 : 1차완료
-------------------------------------------------------------- --%>

<!-- 페이지 전용 스타일/스크립트 -->
<script>
$(function(){
	//버튼이벤트
	$(".btn_view_mod").click(function(e){
		e.preventDefault();
		var modal_id = "modal_mod";
		var modal_url = "<c:url value='/boffice_noDeco/ex/board/BbsContentForm.do' />";
		var modal_param = $(".modalContent #BbsContentVo").serializeArray();
		var modal_class = "wide"; //wide, small
		ajaxModal(modal_id, modal_url, modal_param, modal_class);
	});
	$(".btn_view_del").click(function(e){
		e.preventDefault();
		if(!confirm("<spring:message code="common.delete.msg" />")){
			return;
		}
		$(".modalContent #BbsContentVo").attr("action", "BbsContentRmv.do").submit();
	});
	$(".btn_view_reply").click(function(e){
		e.preventDefault();
		$(".modalContent #BbsContentVo").attr("action", "BbsContentReply.do").submit();
	});
	$(".btn_view_answer").click(function(e){
		e.preventDefault();
		$("#BbsContentVo").attr("action", "<c:url value='BbsContentAnswerForm.do' />").submit();
	});
});
<c:if test="${BoardVo.commentYn eq 'Y'}">
$(function(){
	var param={"pageIndex":1,"bcIdx":$("#bcIdx").val(),"cbIdx":$("#cbIdx").val()};
	commentList(param);
});
function commentList(param){
	var ajaxParam=param;
	var ajaxOption = {};
	ajaxOption.success = function(data){
		$("#comment_box").html(data);
	}
	ajaxAction("<c:url value='/boffice_noDeco/ex/board/BbsCommentList.do'/>", ajaxParam, ajaxOption);
}
</c:if>

</script>
<!-- //페이지 전용 스타일/스크립트 -->

<!-- ============================== 모달영역 ============================== -->
<div class="modalTitle">
	<h3>게시물 등록/수정</h3>
	<a href="#" class="btn_modalClose">모달팝업닫기</a>
</div>
<div class="modalContent">
	<fmt:setLocale value="ko_kr"/>
	<form id="BbsContentVo" name="BbsContentVo" method="post">
	<input type="hidden" id="pageIndex" name="pageIndex" value="<c:out value="${BbsContentVo.pageIndex}"/>" />
	<input type="hidden" id="pageUnit" name="pageUnit" value="<c:out value="${BbsContentVo.pageUnit}"/>" />
	<input type="hidden" id="searchCondition" name="searchCondition" value="<c:out value="${BbsContentVo.searchCondition}"/>" />
	<input type="hidden" id="searchKeyword" name="searchKeyword" value="<c:out value="${BbsContentVo.searchKeyword}"/>" />
	<input type="hidden" id="searchStartDate" name="searchStartDate" value="<c:out value="${BbsContentVo.searchStartDate}"/>" />
	<input type="hidden" id="searchEndDate" name="searchEndDate" value="<c:out value="${BbsContentVo.searchEndDate}"/>" />
	<input type="hidden" id="searchGroup" name="searchGroup" value="<c:out value="${BbsContentVo.searchGroup}"/>" />
	<input type="hidden" id="searchCbIdx" name="searchCbIdx" value="<c:out value="${BbsContentVo.searchCbIdx}"/>" />
	<input type="hidden" id="searchDelYn" name="searchDelYn" value="<c:out value="${BbsContentVo.searchDelYn}"/>" />
	<input type="hidden" id="bcIdx" name="bcIdx" value="<c:out value="${BbsContentVo.bcIdx}"/>" />
	<input type="hidden" id="cbIdx" name="cbIdx" value="<c:out value="${BbsContentVo.cbIdx}"/>" />
	<div class="tableBox">
		<table class="view">
			<caption>게시물 상세보기</caption>
			<colgroup>
				<col style="width:15%;">
				<col>
			</colgroup>
			<tbody>
				<tr>
					<th scope="row">게시판</th>
					<td><c:out value="${fn:replace(fn:substring(BoardVo.cbPath,1,100), '>', ' > ')}" /></td>
				</tr>
				<c:forEach var="propertyInfo" items="${propertylist}">
				<c:choose>
				<c:when test="${propertyInfo.contentMapping eq 'NO_CONT'}">
				</c:when>
				<c:when test="${propertyInfo.contentMapping eq 'MW_STATUS_CONT'}">
				</c:when>
				<c:when test="${propertyInfo.contentMapping eq 'REG_DT'}">
				<tr><!-- REG_DT -->
					<th scope="row"><c:out value="${propertyInfo.labelName}" /></th>
					<td>
						<c:out value="${BbsContentVo.regDt}" />
					</td>
				</tr>
				</c:when>
				<c:when test="${propertyInfo.contentMapping eq 'M_LINK_CONT'}">
				<tr><!-- UCC LINK  -->
					<th scope="row"><c:out value="${propertyInfo.labelName}" /></th>
					<td class="textContent">
						<iframe width="640" height="390" src="https://www.youtube.com/embed/<c:out value="${bbs:getValue(BbsContentVo, 'mLinkCont')}"/>" frameborder="0" allowfullscreen></iframe>
						<textarea class="movie-txt" readonly rows="7"><c:out value="${bbs:getValue(BbsContentVo, 'captionCont')}"/></textarea>
					</td>
				</tr>
				</c:when>
				<c:when test="${propertyInfo.contentMapping eq 'CAPTION_CONT'}">
				</c:when>
				<c:when test="${propertyInfo.contentMapping eq 'FILE_CONT'}">
				<c:if test="${fn:length(fileList) > 0}">
					<c:choose>
						<c:when test="${BbsContentVo.cbIdx eq '1189'}">
							<tr><!-- THUMBNAIL  -->
								<th scope="row">썸네일</th>
								<td>
									<c:if test="${fn:trim(fileList[0].thumbnail) ne ''}">
										<p><img src="/common/board/DownloadThumbnail.do?bcIdx=<c:out value="${fileList[0].bcIdx}"/>&cbIdx=<c:out value="${fileList[0].cbIdx}"/>&streFileNm=<c:out value="${fileList[0].streFileNm}"/>"/></p>
									</c:if>
									<p>
										<a href="/common/board/Download.do?bcIdx=<c:out value="${fileList[0].bcIdx}"/>&cbIdx=<c:out value="${fileList[0].cbIdx}"/>&streFileNm=<c:out value="${fileList[0].streFileNm}"/>" class="file">
											<img src="/images/egovframework/com/cmm/fileicon/<c:out value="${fileList[0].fileExtsn}"/>.gif" alt="<c:out value="${fileList[0].fileExtsn}"/> 아이콘" /> <c:out value="${fileList[0].orignlFileNm }" />  [<c:out value="${bbs:byteCalculation(fileList[0].fileSize)}" />]
										</a>
									</p>
								</td>
							</tr>
							<tr><!-- FILE_CONT  -->
								<th scope="row"><c:out value="${propertyInfo.labelName}" /></th>
								<td>
									<c:forEach var="fileInfo" items="${fileList}" begin="1">
									<c:if test="${fn:trim(fileInfo.thumbnail) ne ''}">
										<p><img src="/common/board/DownloadThumbnail.do?bcIdx=<c:out value="${fileInfo.bcIdx}"/>&cbIdx=<c:out value="${fileInfo.cbIdx}"/>&streFileNm=<c:out value="${fileInfo.streFileNm}"/>"/></p>
									</c:if>
									<p><a href="/common/board/Download.do?bcIdx=<c:out value="${fileInfo.bcIdx}"/>&cbIdx=<c:out value="${fileInfo.cbIdx}"/>&streFileNm=<c:out value="${fileInfo.streFileNm}"/>" class="file">
									<img src="/images/egovframework/com/cmm/fileicon/<c:out value="${fileInfo.fileExtsn}"/>.gif" alt="<c:out value="${fileInfo.fileExtsn}"/> 아이콘" /> <c:out value="${fileInfo.orignlFileNm }" />  [<c:out value="${bbs:byteCalculation(fileInfo.fileSize)}" />]
									</a><%-- <a href="/common/docuzen/preImageFromDoc.do?filePath=<c:out value='${fileInfo.fileStreCours }' /><c:out value='${fileInfo.streFileNm }' />&filename=<c:out value='${fileInfo.streFileNm }' />" target="_blank" title="새창" class="btn">바로보기</a> --%></p>
									</c:forEach>
								</td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr><!-- FILE_CONT  -->
								<th scope="row"><c:out value="${propertyInfo.labelName}" /></th>
								<td>
									<c:forEach var="fileInfo" items="${fileList}">
									<c:if test="${fn:trim(fileInfo.thumbnail) ne ''}">
										<p><img src="/common/board/DownloadThumbnail.do?bcIdx=<c:out value="${fileInfo.bcIdx}"/>&cbIdx=<c:out value="${fileInfo.cbIdx}"/>&streFileNm=<c:out value="${fileInfo.streFileNm}"/>"/></p>
									</c:if>
									<p><a href="/common/board/Download.do?bcIdx=<c:out value="${fileInfo.bcIdx}"/>&cbIdx=<c:out value="${fileInfo.cbIdx}"/>&streFileNm=<c:out value="${fileInfo.streFileNm}"/>" class="file">
									<img src="/images/egovframework/com/cmm/fileicon/<c:out value="${fileInfo.fileExtsn}"/>.gif" alt="<c:out value="${fileInfo.fileExtsn}"/> 아이콘" /> <c:out value="${fileInfo.orignlFileNm }" />  [<c:out value="${bbs:byteCalculation(fileInfo.fileSize)}" />]
									</a><%-- <a href="/common/docuzen/preImageFromDoc.do?filePath=<c:out value='${fileInfo.fileStreCours }' /><c:out value='${fileInfo.streFileNm }' />&filename=<c:out value='${fileInfo.streFileNm }' />" target="_blank" title="새창" class="btn">바로보기</a> --%></p>
									</c:forEach>
								</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</c:if>
				</c:when>
				<c:when test="${propertyInfo.contentMapping eq 'MULTI_FILE_CONT'}">				
				<tr><!-- FILE_CONT  -->
					<th scope="row"><c:out value="${propertyInfo.labelName}" /></th>
					<td>
						<c:forEach var="fileInfo" items="${fileList}">
						<p><a href="/common/board/Download.do?bcIdx=<c:out value="${fileInfo.bcIdx}"/>&cbIdx=<c:out value="${fileInfo.cbIdx}"/>&streFileNm=<c:out value="${fileInfo.streFileNm}"/>" class="file">
						<img src="/images/egovframework/com/cmm/fileicon/<c:out value="${fileInfo.fileExtsn}"/>.gif" alt="<c:out value="${fileInfo.fileExtsn}"/> 아이콘" /> <c:out value="${fileInfo.orignlFileNm }" />  [<c:out value="${bbs:byteCalculation(fileInfo.fileSize)}" />]
						</a><%-- <a href="/common/docuzen/preImageFromDoc.do?filePath=<c:out value='${fileInfo.fileStreCours }' /><c:out value='${fileInfo.streFileNm }' />&filename=<c:out value='${fileInfo.streFileNm }' />" target="_blank" title="새창" class="btn">바로보기</a> --%></p>
						</c:forEach>
					</td>
				</tr>		 			
				</c:when>
				<c:when test="${propertyInfo.contentMapping eq 'CLOB_CONT' && propertyInfo.labelPropGbn eq '16020200' && BoardVo.editorYn eq 'Y'}">
				<tr>
					<th scope="row"><c:out value="${propertyInfo.labelName}" /></th>
					<td class="textContent">
						<c:out value="${cmm:outClobCont(bbs:getValue(BbsContentVo, propertyInfo.contentMappingL))}" escapeXml="false" />
					</td>
				</tr> 
				</c:when>
				<c:when test="${propertyInfo.contentMapping eq 'CLOB_CONT' && propertyInfo.labelPropGbn eq '16020200' && BoardVo.editorYn eq 'N'}">
				<tr>
					<th scope="row"><c:out value="${propertyInfo.labelName}" /></th>
					<td class="textContent">
						<c:out value="${cmm:outClobCont(bbs:getValue(BbsContentVo, propertyInfo.contentMappingL))}" escapeXml="false" />
					</td>
				</tr>
				</c:when>
				<c:when test="${propertyInfo.contentMapping eq 'CLOB_CONT2' && propertyInfo.labelPropGbn eq '16020200' && BoardVo.editorYn eq 'Y'}">
				<tr>
					<th scope="row"><c:out value="${propertyInfo.labelName}" /></th>
					<td class="textContent">
						<c:out value="${cmm:outClobCont(bbs:getValue(BbsContentVo, propertyInfo.contentMappingL))}" escapeXml="false" />
					</td>
				</tr> 
				</c:when>
				<c:when test="${propertyInfo.contentMapping eq 'TYPE' }">
					<tr>
						<th scope="row"><c:out value="${propertyInfo.labelName}" /></th>
						<td>
							<c:out value="${BbsContentVo.type}"/>
						</td>
					</tr>
				</c:when>
				<c:when test="${propertyInfo.contentMapping eq 'TARGET' }">
					<tr>
						<th scope="row"><c:out value="${propertyInfo.labelName}" /></th>
						<td>
							<c:out value="${BbsContentVo.target}"/>
						</td>
					</tr>
				</c:when>
				<c:when test="${propertyInfo.contentMapping eq 'AREA' }">
					<tr>
						<th scope="row"><c:out value="${propertyInfo.labelName}" /></th>
						<td>
							<c:out value="${BbsContentVo.area}"/>
						</td>
					</tr>
				</c:when>
				<c:when test="${propertyInfo.contentMapping eq 'DEPTH_1' }">
					<tr>
						<th scope="row"><c:out value="${propertyInfo.labelName}" /></th>
						<td>
							<c:out value="${BbsContentVo.depth1}"/>
						</td>
					</tr>
				</c:when>
				<c:when test="${propertyInfo.contentMapping eq 'CENTER' }">
					<tr>
						<th scope="row"><c:out value="${propertyInfo.labelName}" /></th>
						<td>
							<c:out value="${BbsContentVo.center}"/>
						</td>
					</tr>
				</c:when>
				<c:when test="${propertyInfo.contentMapping eq 'OPEN_YN_CONT' }">
				<tr>
					<th scope="row"><c:out value="${propertyInfo.labelName}" /></th>
					<td>
						<c:if test="${bbs:getValue(BbsContentVo, 'openYnCont') eq '21000200'}">
						<span class="open">공개</span>
						</c:if>
						<c:if test="${bbs:getValue(BbsContentVo, 'openYnCont') eq '21000100'}">
						<span class="no-open">비공개</span>
						</c:if>
					</td>
				</tr>
				</c:when>
				<c:when test="${propertyInfo.contentMapping eq 'ANS_YN_CONT' }">
				
				</c:when>
				
				<c:otherwise>
					<c:choose>
						
						<c:when test="${propertyInfo.labelPropGbn eq '16020700' || propertyInfo.labelPropGbn eq '16020800'}">
						<tr><!-- RADIOBOX 및 SELECTBOX-->
							<th scope="row"><c:out value="${propertyInfo.labelName}" /></th>
								<td><c:out value="${cmm:getCodeName(bbs:getValue(BbsContentVo, propertyInfo.contentMappingL))}" />
							</td>
						</tr>
						</c:when>
						<c:when test="${propertyInfo.labelPropGbn eq '16020900'}">
						<tr><!-- CHECKBOX-->
							<th scope="row"><c:out value="${propertyInfo.labelName}" /></th>
							<td>
								<c:set var="checkValue" value="${bbs:getValue(BbsContentVo, propertyInfo.contentMappingL)}" />
								<c:set var="itemList" value="${cmm:getCodeList(propertyInfo.itemCode)}" />
								<c:forEach  var="itemInfo" items="${itemList}">
								<c:if test="${fn:indexOf(checkValue, itemList.code) > -1 }">
								<p><c:out value="${itemInfo.codeName}" /></p>
								</c:if>
								</c:forEach>
							</td>
						</tr>
						</c:when>
						<c:when test="${propertyInfo.labelPropGbn eq '16020200'}">
						<tr><!-- TEXTAREA -->
							<th scope="row"><c:out value="${propertyInfo.labelName}" /></th>
							<td class="textContent"><c:out value="${cmm:outClobCont(bbs:getValue(BbsContentVo, propertyInfo.contentMappingL))}"/></td>
						</tr>
						</c:when>
						<c:when test="${propertyInfo.labelPropGbn eq '16020400'}">
						<tr><!-- ADRESS -->
							<th scope="row"><c:out value="${propertyInfo.labelName}" /></th>
							<td><c:out value="${fn:replace(bbs:getValue(BbsContentVo, propertyInfo.contentMappingL), '_', ' ')}" /></td>
						</tr>
						</c:when>
							<c:when test="${propertyInfo.labelPropGbn eq '16021600'}">
						<tr><!-- ADRESS -->
							<th scope="row"><c:out value="${propertyInfo.labelName}" /></th>
							<td><c:out value="${BbsContentVo.cdSubject}" /></td>
						</tr>
						</c:when>
						<c:otherwise>
						<tr>
							<th scope="row"><c:out value="${propertyInfo.labelName}" /></th>
							<td><c:out value="${bbs:getValue(BbsContentVo, propertyInfo.contentMappingL)}" /></td>
						</tr>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
				</c:choose>
				</c:forEach>
				<c:if test="${BoardVo.cbIdx == 57 || BoardVo.cbIdx == 58 || BoardVo.cbIdx == 59}">
				<c:set var="departName" value="${bbs:getValue(BbsContentVo, 'nameCont')}" />
				<tr><!-- 공지사항, 행사소식, 고시공고 -->
					<th scope="row"><label for="placeType">게시 홈페이지</label></th>
					<td>
						<c:if test="${BbsContentVo.placeType eq 'Y'}">[구청홈페이지, <c:out value="${departName}"/>]</c:if>
						<c:if test="${BbsContentVo.placeType eq 'A'}">[구청홈페이지]</c:if>
						<c:if test="${BbsContentVo.placeType eq 'B'}">[<c:out value="${departName}"/>]</c:if>
					</td>
				</tr>
				</c:if>
			</tbody>
		</table>
	</div>
	<div class="btnArea">
		<c:if test="${BbsContentVo.searchCbIdx != 280 || (BbsContentVo.searchCbIdx == 280 && SesUserPermCd eq '05010000')}">
			<c:if test="${BoardVo.replyYn eq 'Y'}">
				<a href="#" class="btn_m on btn_view_reply">답변</a>	
			</c:if>
			<c:if test="${SesUserPermCd eq '05010000' || SesUserId eq BbsContentVo.regId}">
		 		<a href="#" class="btn_m on btn_view_mod">수정</a>
				<a href="#" class="btn_m btn_caption btn_view_del">삭제</a>
		 	</c:if>
		</c:if>
		<a href="#" class="btn_m btn_modalClose">취소</a>
	</div>
	
	<c:if test="${bbsFVo.cbIdx != 414}">
		<c:if test="${BbsContentVo.ANS_R_YN eq 'Y'}"><span class="status-end">[답변완료]</span></c:if>
		<c:if test="${BbsContentVo.ANS_R_YN eq 'N'}"><span class="status-ing">[답변중]</span></c:if>
	</c:if>
	<c:if test="${BoardVo.bbsTempletGbn eq 'bbs_minwon'}">
	<div class="tableBox">
		<table class="view">
			<caption>게시물 상세보기</caption>
			<colgroup>
				<col style="width:15%;">
				<col style="width:35%;">
				<col style="width:15%;">
				<col style="width:35%;">
			</colgroup>
			<tbody>						
				<tr>
					<th scope="row"><c:out value="${(BoardVo.cbIdx == 12 || BoardVo.cbIdx == 15) ? '검토부서' : '처리상태'}"/></th>
					<td>
						<c:choose>
						<c:when test="${(BoardVo.cbIdx == 12 || BoardVo.cbIdx == 15)}">
							<c:out value="${BbsContentVo.ansWriter}" />
						</c:when>
						<c:otherwise>
							<c:if test="${BbsContentVo.ansRYn eq 'N'}"><strong class="red">[접수중]</strong></c:if>
							<c:if test="${BbsContentVo.ansRYn eq 'S'}"><strong class="red">[접수완료]</strong></c:if>
							<c:if test="${BbsContentVo.ansRYn eq 'C'}"><strong class="red">[답변 처리중]</strong></c:if>
							<c:if test="${BbsContentVo.ansRYn eq 'Y'}"><strong class="blue">[답변완료]</strong></c:if>
						</c:otherwise>
						</c:choose>							
					</td>
					<th scope="row">처리일자</th>
					<td><c:out value="${BbsContentVo.ansDt}" /></td>
				</tr>
				<tr>
					<th scope="row"><c:out value="${(BoardVo.cbIdx == 12 || BoardVo.cbIdx == 15) ? '검토담당자' : '담당자'}"/></th>
					<td><c:out value="${BbsContentVo.chargeNameCont}" /></td>
					<th scope="row"><c:out value="${(BoardVo.cbIdx == 12 || BoardVo.cbIdx == 15) ? '채택여부' : '담당자연락처'}"/></th>
					<td><c:out value="${BbsContentVo.chargeTelCont}" /></td>
				</tr>
				<tr>
					<th scope="row"><c:out value="${(BoardVo.cbIdx == 12 || BoardVo.cbIdx == 15) ? '검토의견' : '처리내용'}"/></th>
					<td colspan="3"><c:out value="${cmm:outClobCont(BbsContentVo.ansContent)}" escapeXml="false"/></td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="btnArea">
		<a href="#" class="btn_m btn_view_answer">민원답변<c:out value="${BbsContentVo.ansRYn eq 'Y' ? '수정':''}"/></a>
	</div>
	</c:if>
	</form>
	<c:if test="${BoardVo.commentYn eq 'Y'}">
	<div id="comment_box"></div>
	</c:if>
</div>
<!-- ============================== //모달영역 ============================== -->
