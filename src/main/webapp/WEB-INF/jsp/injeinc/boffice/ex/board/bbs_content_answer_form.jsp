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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>게시판 답변하기</title>
<script type="text/javascript">
//<![CDATA[
	function doBbsContentAnswerMod() {
		
		/* if($("#chargeNameCont").val() == "") {
			alert("담당자 이름을 입력해 주십시요.");
			$("#chargeNameCont").focus();
			return;
		}
		
		if($("#chargeTelCont").val() == "") {
			alert("담당자 연락처를 입력해 주십시요.");
			$("#chargeTelCont").focus();
			return;
		}
		
		if($("#ansContent").val() == "") {
			alert("답변 내용을 입력해 주십시요.");
			$("#ansContent").focus();
			return;
		} */
		
		if($('input:radio[name="ansRYn"]:checked').val() == "N"){
			$("#BbsContentVo").attr("action", "<c:url value='BbsContentAnswerMod.do' />").submit();
		}
		
		if($('input:radio[name="ansRYn"]:checked').val() == "S"){
			var checkC = confirm("[접수완료] 상태로 변경하시겠습니까?");
				if(checkC == true){
					$("#BbsContentVo").attr("action", "<c:url value='BbsContentAnswerMod.do' />").submit();
				}else{
					return;
				}		
			
			}
		
		if($('input:radio[name="ansRYn"]:checked').val() == "C"){
			var checkC = confirm("[답변처리중] 상태로 변경하시겠습니까?");
				if(checkC == true){
					$("#BbsContentVo").attr("action", "<c:url value='BbsContentAnswerMod.do' />").submit();
				}else{
					return;
				}		
			
			}
		
		if($('input:radio[name="ansRYn"]:checked').val() == "Y"){
			var checkY = confirm("[답변완료] 상태로 변경하시겠습니까?");
				if(checkY == true){					
					$("#BbsContentVo").attr("action", "<c:url value='BbsContentAnswerMod.do' />").submit();
				}else{
					return;
				}		
			
			}

	}
	
	function doBbsContentAnswerView() {
		$("#BbsContentVo").attr("action", "<c:url value='BbsContentView.do' />").submit();
	}
//]]>
</script>
</head>
<fmt:setLocale value="ko_kr"/>
<body style="text-align:center; margin:0 auto; display:inline; padding-top:100px;">
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

	<!-- 컨텐츠 영역 -->
			<div id="contents">
			
				<table summary="게시물 상세보기" class="view board">
					<caption>게시물 상세보기</caption>
					<colgroup>
						<col width="15%"/>
						<col width="85%"/>
					</colgroup>
					<tbody>
						<tr>
							<th scope="row">게시판</th>
							<td><c:out value="${BoardVo.cbPath}" /></td>
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
						<c:when test="${detailList.contentMapping eq 'M_LINK_CONT'}">
						<tr><!-- UCC LINK  -->
							<th scope="row"><c:out value="${propertyInfo.labelName}" /></th>
							<td>
								<div class="view_contents">
									<div class="txt-area">
										<iframe width="640" height="390" src="https://www.youtube.com/embed/<c:out value="${bbs:getValue(BbsContentVo, 'mLinkCont')}"/>" frameborder="0" allowfullscreen></iframe>
										<textarea class="movie-txt" readonly rows="7"><c:out value="${bbs:getValue(BbsContentVo, 'captionCont')}"/></textarea>
									</div>
								</div>
							</td>
						</tr>
						</c:when>
						<c:when test="${detailList.contentMapping eq 'CAPTION_CONT'}">
						</c:when>
						<c:when test="${propertyInfo.contentMapping eq 'FILE_CONT'}">
						<c:if test="${fn:length(fileList) > 0}">
						<tr><!-- FILE_CONT  -->
							<th scope="row"><c:out value="${propertyInfo.labelName}" /></th>
							<td>
								<c:forEach var="fileInfo" items="${fileList}">
								<p><a href="/common/board/Download.do?bcIdx=<c:out value="${fileInfo.bcIdx}"/>&cbIdx=<c:out value="${fileInfo.cbIdx}&streFileNm=${fileInfo.streFileNm}"/>" class="file">
								<img src="/images/egovframework/com/cmm/fileicon/<c:out value="${fileInfo.fileExtsn}"/>.gif" alt="<c:out value="${fileInfo.fileExtsn}"/> 아이콘" /> <c:out value="${fileInfo.orignlFileNm }" />  [<fmt:formatNumber value="${fileInfo.fileSize}"></fmt:formatNumber> KB]
								</a><%-- <a href="/common/docuzen/preImageFromDoc.do?filePath=<c:out value='${fileInfo.fileStreCours }' /><c:out value='${fileInfo.streFileNm }' />&filename=<c:out value='${fileInfo.streFileNm }' />" target="_blank" title="새창" class="btn">바로보기</a> --%></p>
								</c:forEach>
							</td>
						</tr>
						</c:if>
						</c:when>
						<c:when test="${propertyInfo.contentMapping eq 'CLOB_CONT' && propertyInfo.labelPropGbn eq '16020200' && BoardVo.editorYn eq 'Y'}">
						<tr><!-- CLOB_CONT 이고 TEXTAREA 이면서 EDITOR 사용 시  -->
							<th scope="row"><c:out value="${propertyInfo.labelName}" /></th>
							<td style="vertical-align:top;">
								<c:out value="${cmm:outClobCont(bbs:getValue(BbsContentVo, propertyInfo.contentMappingL))}" escapeXml="false" />
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
						<c:otherwise>
							<c:choose>
								<c:when test="${propertyInfo.labelPropGbn eq '16020700' || propertyInfo.labelPropGbn eq '16020800'}">
								<tr><!-- RADIOBOX 및 SELECTBOX-->
									<th scope="row"><c:out value="${propertyInfo.labelName}" /></th>
									<td><c:out value="${cmm:getCodeName(bbs:getValue(BbsContentVo, propertyInfo.contentMappingL))}" /></td>
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
									<th scope="row" style="min-height:100px;"><c:out value="${propertyInfo.labelName}" /></th>
									<td style="vertical-align:top;"><c:out value="${cmm:outClobCont(bbs:getValue(BbsContentVo, propertyInfo.contentMappingL))}" escapeXml="false" /></td>
								</tr>
								</c:when>
								<c:when test="${propertyInfo.labelPropGbn eq '16020400'}">
								<tr><!-- ADRESS -->
									<th scope="row"><c:out value="${propertyInfo.labelName}" /></th>
									<td><c:out value="${fn:replace(bbs:getValue(BbsContentVo, propertyInfo.contentMappingL), '_', ' ')}" /></td>
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
					</tbody>
				</table>
				
				<table summary="게시물 답변하기" class="write">
					<caption>게시물답변하기</caption>
					<colgroup>
						<col width="15%"/>
						<col width="35%"/>
						<col width="15%"/>
						<col width="35%"/>
					</colgroup>
					<tbody>						
						<tr>
							<th scope="row"><label for="ansWriter"><c:out value="${(BoardVo.cbIdx == 12 || BoardVo.cbIdx == 15) ? '검토부서' : '처리상태'}"/></label></th>
							<td>
							<c:choose>
							<c:when test="${(BoardVo.cbIdx == 12 || BoardVo.cbIdx == 15)}">
								<input type="text" id="ansWriter" name="ansWriter" value="<c:out value="${!empty BbsContentVo.ansWriter ? BbsContentVo.ansWriter : SesUserDeptNm}" />" />
							</c:when>
							<c:otherwise>
								<input type="hidden" id="ansWriter" name="ansWriter" value="<c:out value="${!empty BbsContentVo.ansWriter ? BbsContentVo.ansWriter : SesUserDeptNm}" />" />
								<input type="radio" id="ansRYn1" name="ansRYn" value="N" <c:if test="${BbsContentVo.ansRYn eq 'N'}">checked="checked"</c:if> /><label for="ansRYn1">접수중</label>
								<input type="radio" id="ansRYn2" name="ansRYn" value="S" <c:if test="${BbsContentVo.ansRYn eq 'S'}">checked="checked"</c:if> /><label for="ansRYn1">접수완료</label>
								<input type="radio" id="ansRYn2" name="ansRYn" value="C" <c:if test="${BbsContentVo.ansRYn eq 'C'}">checked="checked"</c:if> /><label for="ansRYn1">답변처리중</label> 
								<input type="radio" id="ansRYn3" name="ansRYn" value="Y" <c:if test="${BbsContentVo.ansRYn eq 'Y'}">checked="checked"</c:if> /><label for="ansRYn2">답변완료</label>
							</c:otherwise>
							</c:choose>														
							</td>
							<th scope="row"><label for="ansRYn1">처리일자</label></th>
							<td><c:out value="${!empty BbsContentVo.ansDt ? BbsContentVo.ansDt : nowDate}" /></td>
						</tr>
						<tr>
							<th scope="row"><label for="chargeNameCont"><c:out value="${(BoardVo.cbIdx == 12 || BoardVo.cbIdx == 15) ? '검토담당자' : '담당자'}"/></label></th>
							<td><input type="text" id="chargeNameCont" name="chargeNameCont" value="<c:out value="${!empty BbsContentVo.chargeNameCont ? BbsContentVo.chargeNameCont : SesUserDeptNm}" />" /></td>
							<th scope="row"><label for="chargeTelCont"><c:out value="${(BoardVo.cbIdx == 12 || BoardVo.cbIdx == 15) ? '채택여부' : '담당자연락처'}"/></label></th>
							<td>
								<c:choose>
								<c:when test="${BoardVo.cbIdx == 12 || BoardVo.cbIdx == 15}">
								<select id="chargeTelCont" name="chargeTelCont">
									<option value="불채택" <c:if test="${BbsContentVo.chargeTelCont eq '불채택'}">selected="selected"</c:if>>불채택</option>
									<option value="채택" <c:if test="${BbsContentVo.chargeTelCont eq '채택'}">selected="selected"</c:if>>채택</option>
								</select>
								</c:when>
								<c:otherwise>
								<input type="text" id="chargeTelCont" name="chargeTelCont" value="<c:out value="${BbsContentVo.chargeTelCont}" />" /><%-- <c:out value="${BbsContentVo.chargeTelCont}" /> --%>
								</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr style="min-height:150px;">
							<th scope="row"><label for="ansContent"><c:out value="${(BoardVo.cbIdx == 12 || BoardVo.cbIdx == 15) ? '검토의견' : '답변내용'}"/></label></th>
							<td colspan="3">
								<textarea rows="10" cols="50" id="ansContent" name="ansContent" class="w90 board_input"><c:out value="${BbsContentVo.ansContent}"/></textarea>
							</td>
						</tr>
					</tbody>
				</table>
				
				<!-- 버튼 -->
				<div class="btn_zone">
					<a href="javascript:doBbsContentAnswerMod();" class="btn2">답변저장</a>
					<a href="javascript:doBbsContentAnswerView();" class="btn1">취소</a>
				</div>
				<!-- //버튼 -->
		
			</div>
			<!-- //컨텐츠 영역 -->
</form>
<!--  웹필터 수정 -->
<%@ include file="/webfilter/inc/initCheckWebfilter.jsp"%>
<!--  웹필터 수정 -->
</body>
</html>