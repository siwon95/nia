<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=uft-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<title>Test</title>

<!-- 드래그 멀티 파일 업로드 부분 시작 -->
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.js"></script>
	
<!-- 드래그 멀티 파일 업로드 CSS 시작-->
<style>
	.dragAndDropDiv {
		border: 2px dashed #92AAB0;
		width: 650px;
		height: 200px;
		color: #92AAB0;
		text-align: center;
		vertical-align: middle;
		padding: 10px 0px 10px 10px;
		font-size: 200%;
		display: table-cell;
	}
	
	.progressBar {
		width: 200px;
		height: 22px;
		border: 1px solid #ddd;
		border-radius: 5px;
		overflow: hidden;
		display: inline-block;
		margin: 0px 10px 5px 5px;
		vertical-align: top;
	}
	
	.progressBar div {
		height: 100%;
		color: #fff;
		text-align: right;
		line-height: 22px;
		/* same as #progressBar height if we want text middle aligned */
		width: 0;
		background-color: #0ba1b5;
		border-radius: 3px;
	}
	
	.statusbar {
		border-top: 1px solid #A9CCD1;
		min-height: 25px;
		width: 99%;
		padding: 10px 10px 0px 10px;
		vertical-align: top;
	}
	
	.statusbar:nth-child(odd) {
		background: #EBEFF0;
	}
	
	.filename {
		display: inline-block;
		vertical-align: top;
		width: 250px;
	}
	
	.filesize {
		display: inline-block;
		vertical-align: top;
		color: #30693D;
		width: 100px;
		margin-left: 10px;
		margin-right: 5px;
	}
	
	.abort {
		background-color: #A8352F;
		-moz-border-radius: 4px;
		-webkit-border-radius: 4px;
		border-radius: 4px;
		display: inline-block;
		color: #fff;
		font-family: arial;
		font-size: 13px;
		font-weight: normal;
		padding: 4px 15px;
		cursor: pointer;
		vertical-align: top
	}
</style>
<!-- 드래그 멀티 파일 업로드 CSS 끝-->
		
<script type="text/javascript">
	$(document).ready(function() {
		var objDragAndDrop = $(".dragAndDropDiv");

		$(document).on("dragenter", ".dragAndDropDiv", function(e) {
			e.stopPropagation();
			e.preventDefault();
			$(this).css('border', '2px solid #0B85A1');
		});
		
		$(document).on("dragover", ".dragAndDropDiv", function(e) {
			e.stopPropagation();
			e.preventDefault();
		});
		
		// 파일 드래그 영역( 드래그시 파일 저장 )
		$(document).on("drop", ".dragAndDropDiv", function(e) {
			$(this).css('border', '2px dotted #0B85A1');
			e.preventDefault();
			var files = e.originalEvent.dataTransfer.files;
			handleFileUpload(files, objDragAndDrop);
		});		
		
		$(document).on('dragenter', function(e) {
			e.stopPropagation();
			e.preventDefault();
		});
		
		$(document).on('dragover', function(e) {
			e.stopPropagation();
			e.preventDefault();
			objDragAndDrop.css('border', '2px dotted #0B85A1');
		});
		
		
		// 드래그 시 파일 개수와 파일명 체크 (드래그 한 파일 목록을 보여준다.)
		$(document).on('drop', function(e) {
			$(this).css('border', '2px dotted #0B85A1');
			var files = e.originalEvent.dataTransfer.files;
					
			if(files.length == 1){
				$('#filescheck').empty();	
				var filescheck = "파일개수 : "+files.length+"<br>파일명 : "+files[0].name;
				$('#filescheck').append(filescheck);
			}
			
			if(files.length == 2){
				$('#filescheck').empty();	
				var filescheck = "파일개수 : "+files.length+"<br>파일명 : "+files[0].name+"/"+files[1].name;	
				$('#filescheck').append(filescheck);	
			}
			
			if(files.length == 3){
				$('#filescheck').empty();	
				var filescheck = "파일개수 : "+files.length+"<br>파일명 : "+files[0].name+"/"+files[1].name+"/"+files[2].name;	
				$('#filescheck').append(filescheck);	
			}
			
			if(files.length == 4){
				$('#filescheck').empty();	
				var filescheck = "파일개수 : "+files.length+"<br>파일명 : "+files[0].name+"/"+files[1].name+"/"+files[2].name+"/"+files[3].name;	
				$('#filescheck').append(filescheck);	
			}
			
			if(files.length == 5){
				$('#filescheck').empty();	
				var filescheck = "파일개수 : "+files.length+"<br>파일명 : "+files[0].name+"/"+files[1].name+"/"+files[2].name+"/"+files[3].name+"/"+files[4].name;	
				$('#filescheck').append(filescheck);	
			}	
	
			e.stopPropagation();
			e.preventDefault();
	
		});
		
		
		// 첨부파일 클릭 영역( 첨부파일 클릭 시 파일 저장 )				
		$("#files").change(function(e) {
			e.preventDefault();
			var files = e.target.files; 
			handleFileUpload(files, objDragAndDrop);
		});		
		
					
		// 드래그 시 & 첨부파일 시 = 파일 개수가 제한된 개수보다 넘었을때 경고문
	 	function handleFileUpload(files, obj) {
			if (files.length > 5) {
				alert('파일 갯수 5개 제한이 넘었습니다.');
			} else {						
				
				var filesave = document.getElementById('filesave');
				filesave.onclick =  function (){
					alert('저장하시겠습니까?');
				
			for (var i = 0; i < files.length; i++) {
				var fd = new FormData();
				fd.append('file', files[i]);
						
			// 드래그 시 & 첨부파일 시 파일 용량 체크
			if(files[i].size < 524288000){      									// 500MB -> 524288000B
					var status = new createStatusbar(obj); 					// Using this we can set progress.
					status.setFileNameSize(files[i].name, files[i].size);		
					//alert(files[i].name);
						 sendFileToServer(fd, status);	
				}else{				
					alert('제한된 파일 용량에 벗어납니다.');
				}	
					
				} //for문 끝
			}
		}
	}		 	
		
		
		// 드래그 시 & 첨부파일 시 = 파일 개수가 뿌려질때의 제한된 개수보다 넘었을때 경고문
		var rowCount = 0;
		function createStatusbar(obj) {

			rowCount++;
			if (rowCount > 5) {
				alert('업로드 갯수 제한에 걸렸습니다.(업로드 제한갯수 :5)');
			} else {
				var row = "odd";
				if (rowCount % 2 == 0)
					row = "even";
					this.statusbar = $("<div class='statusbar "+row+"'></div>");
					this.filename = $("<div id='fileName'></div><input type='hidden' id='fileName' class='fileName'>").appendTo(this.statusbar);
					this.size = $("<div class='filesize'></div>").appendTo(this.statusbar);
					this.progressBar = $("<div class='progressBar'><div></div></div>").appendTo(this.statusbar);
					this.abort = $("<div class='abort'>중지</div>").appendTo(this.statusbar);
			}
			obj.after(this.statusbar);

			this.setFileNameSize = function(name, size) {
				var sizeStr = "";
				var sizeKB = size / 1024;
				if (parseInt(sizeKB) > 1024) {
					var sizeMB = sizeKB / 1024;
					sizeStr = sizeMB.toFixed(2) + " MB";
				} else {
					sizeStr = sizeKB.toFixed(2) + " KB";
				}
				this.filename.html(name);
				this.size.html(sizeStr);
			}

				this.setProgress = function(progress) {
				var progressBarWidth = progress * this.progressBar.width() / 100;
				this.progressBar.find('div').animate({width : progressBarWidth}, 10).html(progress + "% ");
				if (parseInt(progress) >= 100) {
					this.abort.hide();
				}
			}	
		
			// 드래그 시 & 첨부파일 업로드 시 "중지" 버튼 누르면 리스트의 값을 다시 0으로 초기화 시키는 부분
			this.setAbort = function(jqxhr) {
				var sb = this.statusbar;
				this.abort.click(function() {
					jqxhr.abort();					
					sb.hide(function(){
						rowCount = 0;						
					});			
				});
			}
		}	
											
		//드래그 시 & 첨부파일 시 파일 각 데이터 업로드 부분
		function sendFileToServer(formData, status) {
			var uploadURL = "/boffice/sy/fileupload/FileUploadReg.do"; //Upload URL
			var extraData = {}; //Extra Data.
			var jqXHR = 
				$.ajax({
						xhr : function () {
							var xhrobj = $.ajaxSettings.xhr();
							if (xhrobj.upload) {
								xhrobj.upload.addEventListener('progress',function(event) {
								var percent = 0;
								var position = event.loaded || event.position;
								var total = event.total;
								if (event.lengthComputable) {
									percent = Math.ceil(position / total* 100);
								}
								//Set progress
								status.setProgress(percent);
								}, false);
							};							
				
							return xhrobj;					
							
						},
						url : uploadURL,
						type : "POST",
						contentType : false,
						processData : false,
						cache : false,
						data : formData,						
						success : function(data) {			
						
							status.setProgress(100);
							//$("#status1").append("File upload Done<br>");           
						}
					});
			status.setAbort(jqXHR);
		}	
	});	
</script>
<!-- 드래그 멀티 파일 업로드 부분 끝 -->

<script type="text/javascript">

	//등록
	function doCmmFilesReg() {
		$("#FileUploadVo").attr("action", "/boffice/sy/fileupload/FileUploadReg.do").submit();
	}
	
	// 삭제
	function doCmmFilesRmv(idx, group, rename) {
		if(!confirm("정말 삭제하시겠습니까?")) return;
		$("#fuIdx").val(idx);
		$("#fuGroup").val(group);
		$("#fuRename").val(rename);
		$("#FileUploadVo").attr("action", "/boffice/sy/fileupload/FileUploadRmv.do").submit();
	} 
	
	// 페이징
 	function doCmmFilesPag(pageIndex) {
		$("#pageIndex").val(pageIndex);
		$("#FileUploadVo").attr("action", "<c:url value='/boffice/sy/fileupload/Fileupload_List.do' />").submit();
	}
</script>
</head>

<body>
<form:form commandName="FileUploadVo" name="FileUploadVo" method="post" enctype="multipart/form-data">
	<form:hidden path="pageIndex" />
	<form:hidden path="fuIdx" />
	<form:hidden path="fuGroup" />
	<form:hidden path="fuRename" />


	<div id="contents">
		<div id="fileUpload" class="dragAndDropDiv">이곳에 파일을 드래그 <br>(총 파일: 5개 / 파일 용량 제한: 500MB )</div>
			<div id="fileUpload1">
					<ul>
						<li id="filescheck"></li>
					</ul>				
			</div>
				<input type="file" id="files" multiple="multiple"> 
					<a href="javascript:doCmmFilesReg();" id="filesave" class="btn2 btn_input">저장</a>
				
		
	
		<!-- //컨텐츠 영역 -->
	
		<table summary="파일업로드 목록" class="list1">
			<caption>파일업로드 목록</caption>
			<colgroup>
				<col width="20%" />
				<col width="70%" />
				<col width="10%" />
			</colgroup>
			<thead>
				<tr>
					<th scope="col">파일명</th>
					<th scope="col">URL</th>
					<th scope="col">삭제</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="n" value="${resultCnt - (paginationInfo.currentPageNo-1)*paginationInfo.recordCountPerPage }"/>
				<c:forEach var="result" items="${resultList}" varStatus="status">
					<tr>
						<td>
							<a href="/boffice/sy/fileupload/Download.do?fuIdx=<c:out value="${result.fuIdx}" />&amp;fuGroup=<c:out value="${result.fuGroup}" />&amp;fuRename=<c:out value="${result.fuRename}" />">
								<img src="/images/egovframework/com/cmm/fileicon/<c:out value="${result.fuFormat}"/>.gif" alt="<c:out value="${result.fuFormat}"/> 아이콘" />
								<c:out value="${result.fuName}" />
							</a>
						</td>
						<td style="text-align:left;">
							<p>절대주소 : <c:out value="${result.fuPath}" /><c:out value="${result.fuRename}" /></p>
							<p>다운주소 : /boffice/sy/fileupload/Download.do?fuIdx=<c:out value="${result.fuIdx}" />&amp;fuGroup=<c:out value="${result.fuGroup}" />&amp;fuRename=<c:out value="${result.fuRename}" /></p>
							<p>바로보기 : /common/docuzen/preImageFromDoc.do?filePath=<c:out value="${result.fuPath}" /><c:out value="${result.fuRename}" />&amp;filename=<c:out value="${result.fuRename}" /></p>
						</td>
						<td><a href="javascript:doCmmFilesRmv('<c:out value="${result.fuIdx}"/>','<c:out value="${result.fuGroup}"/>','<c:out value="${result.fuRename}"/>');" class="btn1 btn_input">삭제</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<!--paginate -->
		<%-- <div class="paginate">
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="doCmmFilesPag" />
		</div>  --%>
		<!--//paginate -->

	</div>
</form:form>
</body>
</html>