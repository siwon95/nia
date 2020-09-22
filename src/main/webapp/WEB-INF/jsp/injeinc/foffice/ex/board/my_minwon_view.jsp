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
<head>
<script type="text/javascript">
//<![CDATA[
	function doMyMinwonList() {
		$("#BbsContentFVo").attr("action", "<c:url value='/site/seocho/foffice/board/MyMinwonList.do' />").submit();
	}

	function doMyMinwonForm() {
		$("#BbsContentFVo").attr("action", "<c:url value='/site/seocho/foffice/board/MyMinwonForm.do' />").submit();
	}

	function doMyMinwonRmv() {
		if(!confirm("정말 삭제하시겠습니까?")) return;
		$("#BbsContentFVo").attr("action", "/site/seocho/foffice/board/MyMinwonRmv.do").submit();
	}
	
	function goReload() {
		$("#BbsContentFVo").attr("action", "<c:url value='/site/seocho/foffice/board/MyMinwonView.do' />").submit();
	}
	
	function doMinwonSurveyRegAx() {
		
		if($("input:radio[name='mcSurvey']:checked").length < 0) {
			alert("만족도를 선택해주세요.");
			$("input:radio[name='mcSurvey']").eq(0).focus();
			return;
		}
		
		if($('#mcSurveyDesc').val() == "") {
			alert("의견을 작성해 주세요.");
			$("#mcSurveyDesc").focus;
			return;
		}
		
		$.ajax({
			type: "GET",
			url: "<c:url value='/site/seocho/ex/board/ContentMinwonResultFSurveyRegAx.do'/>",
			data : $("#BbsContentFVo").serialize(),
			dataType : "json",
			success:function(data) {
				alert(data.message);
				if(data.result) {
					goReload();
				}
			 },
			error: function(xhr,status,error){
				alert(status);
			}
		});
	}
//]]>
</script>
</head>
<body>
<fmt:setLocale value="ko_kr"/>
<form:form commandName="BbsContentFVo" name="BbsContentFVo" method="post">
<input type="hidden" id="pageIndex" name="pageIndex" value="<c:out value="${BbsContentFVo.pageIndex }"/>" />
<input type="hidden" id="searchCondition" name="searchCondition" value="<c:out value="${BbsContentFVo.searchCondition }"/>" />
<input type="hidden" id="searchKeyword" name="searchKeyword" value="<c:out value="${BbsContentFVo.searchKeyword }"/>" />
<input type="hidden" id="bcIdx" name="bcIdx" value="<c:out value="${BbsContentFVo.bcIdx }"/>" />
<input type="hidden" id="cbIdx" name="cbIdx" value="<c:out value="${BbsContentFVo.cbIdx }"/>" />

	<div class="board">

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
									<div class="txt-area"><c:out value="${cmm:outClobCont(bbsContentValue)}"/></div>
								</div>
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
		
		<c:if test="${BoardVo.bbsTempletGbn eq '16050700' && BoardVo.cbIdx != 230}">
	
		<br /><br />
		<c:if test="${BbsContentFVo.ansRYn eq 'Y'}">
		<p id="b_summary2" class="hide">답변글의 <c:out value="${BoardVo.cbIdx == 12 || BoardVo.cbIdx == 15 ? '검토부서':'답변부서'}"/>, <c:out value="${BoardVo.cbIdx == 12 || BoardVo.cbIdx == 15 ? '검토담당자':'담당자이름'}"/>, 답변일자, <c:out value="${BoardVo.cbIdx == 12 || BoardVo.cbIdx == 15 ? '채택여부':'담당자연락처'}"/>, <c:out value="${BoardVo.cbIdx == 12 || BoardVo.cbIdx == 15 ? '검토의견':'답변내용'}"/> 정보</p>
		<table class="view view-gray" aria-describedby="b_summary2">
			<caption>답변글정보 상세보기</caption>
			<colgroup>
				<col class="w15"><col><col class="w15"><col class="w35">
			</colgroup>
			<tbody>
				<tr>
					<th scope="row"><c:out value="${BoardVo.cbIdx == 12 || BoardVo.cbIdx == 15 ? '검토부서':'답변부서'}"/></th>
					<td><c:out value="${BbsContentFVo.ansWriter}" /></td>
					<th scope="row"><c:out value="${BoardVo.cbIdx == 12 || BoardVo.cbIdx == 15 ? '검토담당자':'담당자이름'}"/></th>
					<td><c:out value="${BbsContentFVo.chargeNameCont}" /></td>
				</tr>
				<tr>
					<th scope="row">답변일자</th>
					<td><c:out value="${fn:substring(BbsContentFVo.ansDt, 0, 10)}" /></td>
					<th scope="row"><c:out value="${BoardVo.cbIdx == 12 || BoardVo.cbIdx == 15 ? '채택여부':'담당자연락처'}"/></th>
					<td><c:out value="${BbsContentFVo.chargeTelCont}" /></td>
				</tr>
				<tr>
					<th scope="row"><c:out value="${BoardVo.cbIdx == 12 || BoardVo.cbIdx == 15 ? '검토의견':'답변내용'}"/></th>
					<td colspan="3"><c:out value="${cmm:outClobCont(BbsContentFVo.ansContent)}"/></td>
				</tr>
			</tbody>
		</table>
		</c:if>
		<c:if test="${BbsContentFVo.ansRYn eq 'N'}">
		<div class="view_contents">
			<div class="txt-area bbs_process">답변을 처리중 입니다.</div>
		</div>
		</c:if>
		
		</c:if>
		
		<c:if test="${BoardVo.cbIdx == 230}">
		
			<c:set var="replyType" value="" />
			<c:set var="MinwonResultList" value="${bbs:getMinwonResultList(BbsContentFVo.bcIdx, BbsContentFVo.cbIdx)}" />
			<c:set var="MinwonResultTotal" value="${bbs:getMinwonResultTotal(BbsContentFVo.bcIdx, BbsContentFVo.cbIdx)}" />
		
			<c:choose>
			<c:when test="${BbsContentFVo.ansYnCont eq '22000200' || BbsContentFVo.mwNoReplyYn eq 'Y'}">
				<div class="view_contents">
					<div class="txt-area bbs_process">답변이 등록되지 않는 게시물입니다.</div>
				</div>
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="${!empty MinwonResultTotal && MinwonResultTotal.auditYn eq '27000100'}">
						<c:set var="replyType" value="A" />
						<c:set var="MinwonResultInfo" value="${MinwonResultTotal}" />
					</c:when>
					<c:when test="${!empty MinwonResultTotal && MinwonResultTotal.auditYn eq '27000300'}">
						<c:set var="replyType" value="B" />
					</c:when>
					<c:when test="${(fn:length(MinwonResultList) == 1) && (MinwonResultList[0].tempYn eq 'Y')}">
						<c:set var="replyType" value="A" />
						<c:set var="MinwonResultInfo" value="${MinwonResultList[0]}" />
					</c:when>
					<c:otherwise>
						<div class="view_contents">
							<div class="txt-area bbs_process">처리중입니다.</div>
						</div>
					</c:otherwise>
				</c:choose>
			</c:otherwise>
			</c:choose>
			
			<c:if test="${replyType eq 'A'}">
				<div class="om-reply-date">답변일자 : 
					<fmt:parseDate var="modDt" value="${MinwonResultInfo.modDt}" pattern="yyyyMMddHHmmss"/>
					<fmt:formatDate var="modDt" value="${modDt}" pattern="yyyy.MM.dd"/>
					<c:out value="${modDt}" />
				</div>
				<div class="om-reply">
					<p>안녕하십니까? 관악구청장 <c:out value="${cmm:getChiefName(fn:replace(modDt, '.', '-'))}"/>입니다.</p>
					<p><c:out value="${MinwonResultInfo.mcPointTxt }" /></p>
					<div class="detail"><c:out value="${cmm:outClobCont(MinwonResultInfo.contentClob)}"/></div>
					<div>
						<p>관악구정에 애정과 관심을 가져주심에 다시한번 감사드리며, 귀하의 가정에 건강과 행복이 가득하시기를 기원합니다.</p>
						<p class="aright"><c:out value='${fn:substring(modDt, 0, 4)}' />년 <c:out value='${fn:substring(modDt, 5, 7)}' />월 <c:out value='${fn:substring(modDt, 8, 10)}' />일<br>관악구청장 <c:out value="${cmm:getChiefName(fn:replace(modDt, '.', '-'))}"/> 드림</p>
					</div>
				</div>
				<div class="om-reply-tel">
					<div>
						<p>자세한 사항은 아래로 연락주시면 정성을 다해 답변드리겠습니다.</p>
						<c:forEach var="MinwonResult" items="${MinwonResultList}">
						<p><c:out value="${MinwonResult.mcTel2 }" /></p>
						</c:forEach>
					</div>
				</div>
				<c:set var="MinwonFileList" value="${bbs:getMinwonFileList(BbsContentFVo.bcIdx, BbsContentFVo.cbIdx, MinwonResultInfo.mcIdx)}" />
				<c:if test="${fn:length(MinwonFileList) > 0}">
				<div class="om-reply-file">
					<p>
						<c:forEach var="MinwonFileInfo" items="${MinwonFileList}" >
						<p><a href="/common/board/Download.do?bcIdx=<c:out value="${MinwonFileInfo.bcIdx}"/>&amp;cbIdx=<c:out value="${MinwonFileInfo.cbIdx}"/>&amp;streFileNm=<c:out value="${MinwonFileInfo.streFileNm}"/>" class="file" title="파일 다운로드">
						<c:out value="${MinwonFileInfo.orignlFileNm }" /> [<c:out value="${bbs:byteCalculation(MinwonFileInfo.fileSize)}" />]
						</a>
						<a href="/common/board/DownloadView.do?cbIdx=<c:out value='${MinwonFileInfo.bcIdx }' />&amp;bcIdx=<c:out value='${MinwonFileInfo.cbIdx }' />&amp;streFileNm=<c:out value='${MinwonFileInfo.streFileNm }' />" title="새창" target="_blank" class="btn btn-quickView">바로보기</a>
						</p>
						</c:forEach>
					</p>
				</div>
				</c:if>
		
				<!-- 만족도 조사  -->
				<c:if test="${empty MinwonResultInfo.mcSurvey && !empty BbsContentFVo.dupcode && BbsContentFVo.dupcode eq ss_dupkey}">
					<br>
					<h4 class="con-title1 mgb5">구청장에 바란다 만족도 조사</h4>
					<table class="view regist-gu-satis">
						<caption>구청장에 바란다 만족도 조사</caption>
						<colgroup>
							<col class="w15"><col>
						</colgroup>
						<tbody>
							<tr>
								<th scope="row">만족도</th>
								<td class="option">
									<span><input type="radio" name="mcSurvey" value="5" id="mcSurvey1"> <label for="mcSurvey1">매우만족</label></span>
									<span class="mgl10"><input type="radio" name="mcSurvey" value="4" id="mcSurvey2"> <label for="mcSurvey2">만족</label></span>
									<span class="mgl10"><input type="radio" name="mcSurvey" value="3" id="mcSurvey3"> <label for="mcSurvey3">보통</label></span>
									<span class="mgl10"><input type="radio" name="mcSurvey" value="2" id="mcSurvey4"> <label for="mcSurvey4">불만족</label></span>
									<span class="mgl10"><input type="radio" name="mcSurvey" value="1" id="mcSurvey5"> <label for="mcSurvey5">매우불만족</label></span>
								</td>
							</tr>
							<tr>
								<th scope="row">의견</th>
								<td>
									<textarea cols="50" rows="2" name="mcSurveyDesc" id="mcSurveyDesc" class="w80 m-w70" ></textarea>
									<input type="hidden" id="mcIdx" name="mcIdx" value="<c:out value="${MinwonResultInfo.mcIdx }"/>" />
									<a href="#save" onclick="doMinwonSurveyRegAx();return false;" class="btn-inner2 w15" style="vertical-align:top;">저장</a>
								</td>
							</tr>
						</tbody>
					</table>
				</c:if>
				<!-- //만족도 조사 끝 -->
			
			</c:if>
			
			<c:if test="${replyType eq 'B'}">
			
				<div class="om-reply-date">답변일자 : 
				<fmt:parseDate var="modDt" value="${MinwonResultList[0].modDt}" pattern="yyyyMMddHHmmss"/>
				<fmt:formatDate var="modDt" value="${modDt}" pattern="yyyy.MM.dd"/>
				<c:out value="${modDt}" />
				</div>
			
				<div class="om-reply">
					<p>안녕하십니까? 관악구청장 <c:out value="${cmm:getChiefName(fn:replace(modDt, '.', '-'))}"/>입니다.</p>
					<c:forEach var="MinwonResultInfo" items="${MinwonResultList}">
						<dl>
							<dt>[<c:out value="${MinwonResultInfo.mcDeptCd }" /> 답변]</dt>
							<dt class="hide">[답변]</dt>
							<dd><c:out value="${MinwonResultInfo.mcPointTxt}" /></dd>
							<dd class="detail"><c:out value="${cmm:outClobCont(MinwonResultTotal.contentClob)}"/></dd>
							<c:set var="MinwonFileList" value="${bbs:getMinwonFileList(BbsContentFVo.bcIdx, BbsContentFVo.cbIdx, MinwonResultInfo.mcIdx)}" />
							<c:if test="${fn:length(MinwonFileList) > 0}">
							<dd>
								<c:forEach var="MinwonFileInfo" items="${MinwonFileList}" >
								<p><a href="/common/board/Download.do?bcIdx=<c:out value="${MinwonFileInfo.bcIdx}"/>&amp;cbIdx=<c:out value="${MinwonFileInfo.cbIdx}"/>&amp;streFileNm=<c:out value="${MinwonFileInfo.streFileNm}"/>" class="file" title="파일 다운로드">
								<c:out value="${MinwonFileInfo.orignlFileNm }" /> [<c:out value="${bbs:byteCalculation(MinwonFileInfo.fileSize)}" />]
								</a>
								<a href="/common/board/DownloadView.do?cbIdx=<c:out value='${MinwonFileInfo.bcIdx }' />&amp;bcIdx=<c:out value='${MinwonFileInfo.cbIdx }' />&amp;streFileNm=<c:out value='${MinwonFileInfo.streFileNm }' />" title="새창" target="_blank" class="btn btn-quickView">바로보기</a>
								</p>
								</c:forEach>
							</dd>
							</c:if>
						</dl>
					</c:forEach>
					<div>
						<p>관악구정에 애정과 관심을 가져주심에 다시한번 감사드리며, 귀하의 가정에 건강과 행복이 가득하시기를 기원합니다.</p>
						<p class="aright"><c:out value='${fn:substring(modDt, 0, 4)}' />년 <c:out value='${fn:substring(modDt, 5, 7)}' />월 <c:out value='${fn:substring(modDt, 8, 10)}' />일<br>관악구청장 <c:out value="${cmm:getChiefName(fn:replace(modDt, '.', '-'))}"/> 드림</p>
					</div>
					<div>
						<p>자세한 사항은 아래로 연락주시면 정성을 다해 답변드리겠습니다.</p>
						<p><c:out value="${MinwonResultList[0].mcTel2 }" /></p>
					</div>
				</div>
			
			</c:if>
		
		</c:if>
	
		<!-- 버튼 -->
		<div class="btns aright">
			<c:if test="${(BoardVo.cbIdx != 230 && BbsContentFVo.ansRYn ne 'Y') || (BoardVo.cbIdx == 230 && empty replyType)}">
				<a href="#modify" onclick="doMyMinwonForm();return false;" class="type1">수정</a>
			</c:if>
			<a href="#delete" onclick="doMyMinwonRmv();return false;" class="type1">삭제</a>
			<a href="#list" onclick="doMyMinwonList();return false;" class="type2">목록</a>
		</div>
		<!-- //버튼 -->
		
	</div><!-- //board -->
	
</form:form>
</body>
