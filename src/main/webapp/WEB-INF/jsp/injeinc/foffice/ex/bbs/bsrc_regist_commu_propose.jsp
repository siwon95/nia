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

<script src="/plugin/ckeditor/ckeditor.js"></script>

<script>
	$(document).ready(function() {
		var reTgtTypeCd = '${detailMap.ext1}';
		console.log("reTgtTypeCd : "+reTgtTypeCd)
		$.ajax({
			type: "GET",
			url: "<c:url value='/site/{strDomain}/ex/bbs/CmmCodeAx.do'/>",
			data : {"cmmCode" : 'DRP_KINDS' },
			dataType : "json",
			success:function(data){
				console.log("data : ",data);
				$.each(data.cmmCode, function (index, item) {
					if (item.code == reTgtTypeCd) {
						$("#searchKind").append("<option value='"+item.code+"' selected>"+item.codeName+"</option>");
					}else{
						$("#searchKind").append("<option value='"+item.code+"'>"+item.codeName+"</option>");					
					}
				});
			 },
		       error: function(xhr,status,error){
		       	alert(status);
		       }
		 });
	});
	
	$(function(){
		CKEDITOR.replace('clobCont')
	});
	
	function doForm(){
		var mode = '${bbsFVo.mode}'
		var searchKind = $('#searchKind').val();
		var subCont	= $('#subCont').val();
		var clobCont = CKEDITOR.instances.clobCont.getData();
		var blank_pattern = /^\s+|\s+$/g;

		console.log(subCont);
		console.log(clobCont);

	 	if (subCont.replace(blank_pattern, "")== "") {
			alert("제목을 입력해주세요.");				
			return false;
		}
		if (clobCont.replace(blank_pattern, "")== "") {
			alert("내용을 입력해주세요.");				
			return false;
		} 
		
		if (mode=='C') {
			$('#fileupload').attr('id','bbsFVo');
			$("#bbsFVo").attr('name','bbsFVo');
			$("#bbsFVo").attr("action", "BoardRegProc.do?cbIdx=1141&mode=C");				
		}else if (mode=='U') {
			$('#fileupload').attr('id','bbsFVo');
			$("#bbsFVo").attr('name','bbsFVo');
			$("#bbsFVo").attr("action", "BoardRegProc.do?cbIdx=1141&bcIdx=${detailMap.bcIdx}&mode=U&ext1="+searchKind+"&subCont="+subCont+"&clobCont="+clobCont+"");	 
		}
	 	return true;
	} 

	$(function() {
		$(".btn_delFile").click(function(e){
			e.preventDefault();
			if(!confirm("파일이 완전히 삭제 됩니다. 삭제하시겠습니까?")){
				return;
			}
			var ajaxParam = {"bcIdx":$(this).attr("data-bcIdx"),"cbIdx":$(this).attr("data-cbIdx"),"fileNo":$(this).attr("data-fileNo")};
			$.ajax({
				type: "GET",
				url: "<c:url value='/site/{strDomain}/ex/bbs/File_RmvAx.do'/>",
				data : ajaxParam,
				dataType : "json",
				success:function(data){
					console.log("data : ",data);
					$("#bbsFVo").attr("action", "<c:url value='BoardRegProc.do?cbIdx=1141&bcIdx=${detailMap.bcIdx}&mode=U'/>"); 	
					location.reload(true);
				},
			       error: function(xhr,status,error){
			       	alert(status);
			     }
			 });
		});
	});
</script>
<body>
<form:form commandName="bbsFVo" id="fileupload" name="fileupload" method="post" enctype="multipart/form-data" action="File_Upload.do" onsubmit="return doForm(this);">
<form:hidden path="fileMaxSize" value="20" />
<c:if test="${bbsFVo.socialYn=='Y'}">
	<jsp:include page="/layout/module/sns_share.jsp" flush="true"/>
</c:if>
	<h3>제안하기</h3>
		<div class="board_write mTable">
			<table>
				<caption>제안하기 등록</caption>
				<colgroup>
					<col style="width:15%">
					<col style="width:85%">
				</colgroup>
				
				<tbody>
					<tr>
						<th scope="row"><label for="">제안분야</label></th>
						<td>
							<form:select path="ext1" id="searchKind"></form:select>  					
						</td>
					</tr>
					<tr>
						<th scope="row"><label for="">제목</label></th>
						<c:if test="${detailMap.subCont ne null}">
							<td>
								<input type="text" path="subCont" id="subCont" class="w100p" value="<c:out value="${detailMap.subCont}"/>">
							</td>	
						</c:if>
						<c:if test="${detailMap.subCont eq null}">
							<td>					
								<form:input path="subCont" id="subCont" class="w100p" ></form:input>
							</td>
						</c:if>
					</tr>
					<tr>
						<th scope="row"><label for="">내용</label></th>
						<c:if test="${detailMap.clobCont ne null}">
							<td>
								<textarea path="clobCont" id="clobCont" class="w100p h300">${detailMap.clobCont}</textarea>
							</td>
						</c:if>
						<c:if test="${detailMap.subCont eq null}">
							<td>
								<form:textarea path="clobCont" id="clobCont" class="w100p h300"></form:textarea>
							</td>						
						</c:if>
					</tr>
					<c:choose>
						<c:when test="${fileList[0].orignlFileNm ne null }">
							<c:set var="fileLength" value="${fn:length(fileList)}" />
								<c:forEach begin="0" end="2" varStatus="status" >	
									<tr>		
										<c:if test="${status.first}">
											<th scope="row" rowspan="4">첨부파일</th>
										</c:if>		
										<td>	
											<c:if test="${fileLength > status.index}">
										 		<a href="/common/board/Download.do?bcIdx=<c:out value="${fileList[status.index].bcIdx}"/>&amp;cbIdx=<c:out value="${fileList[status.index].cbIdx}"/>&amp;streFileNm=<c:out value="${fileList[status.index].streFileNm}"/>" target="_blank" title="파일다운로드">
													<i class="<c:out value="${fileList[status.index].fileExtsn}"/>">
														<span><c:out value="${fileList[status.index].orignlFileNm }" />  [<c:out value="${bbs:byteCalculation(fileList[status.index].fileSize)}" />]</span>									
													</i></a>
												<a href="#" data-bcIdx="<c:out value="${fileList[status.index].bcIdx}"/>" data-cbIdx="<c:out value="${fileList[status.index].cbIdx}"/>" data-fileNo="<c:out value="${fileList[status.index].fileNo}"/>" class="btn_delFile">[삭제]</a>	
											</c:if>	
											<c:if test="${fileLength <= status.index}">
												<input type="file" id="fileTagId" name="fileTagId" class="w200" title="첨부파일<c:out value="${status.index}"/>"/>													
											</c:if>
											<c:if test="${status.last}">
												<span>※첨부파일 등록 가능한 확장자(gif,jpeg,jpg,png,doc,docx,hwp,pdf,xls,xlsx,ppt,pptx,txt,zip), 파일 용량은 20MB까지 가능합니다.</span>								
											</c:if>
										</td> 
									</tr>
								</c:forEach>
						</c:when>
						<c:when test="${fileList[0].orignlFileNm eq null }">
							<c:forEach begin="0" varStatus="status" end="2">
								<tr>
									<c:if test="${1>status.index}">
										<th scope="row" rowspan="3">첨부파일</th>
									</c:if>							
									<c:if test="${2>status.index}">
										<td class="thumbfile">
											<input type="file" id="fileTagId" name="fileTagId" class="w200" title="첨부파일<c:out value="${status.index}"/>"/>			
										</td>
									</c:if>
									<c:if test="${status.last}">
										<td class="thumbfile">
											<input type="file" id="fileTagId" name="fileTagId" class="w200" title="첨부파일<c:out value="${status.index}"/>"/>														
											<span>※첨부파일 등록 가능한 확장자(gif,jpeg,jpg,png,doc,docx,hwp,pdf,xls,xlsx,ppt,pptx,txt,zip), 파일 용량은 20MB까지 가능합니다.</span>
										</td>
									</c:if> 
								</tr> 
							</c:forEach>
						</c:when>
					</c:choose>
				</tbody>
			</table>
		</div>
		<div class="btnArea right">
			<input type="submit" value="확인" class="btn_l">
			<a href="<c:url value='List.do?cbIdx=1141'/>" class="btn_l off">취소</a>
		</div>
		<!-- 여기까지 -->	
</form:form> 