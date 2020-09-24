<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="bbs" uri="http://cms.injeinc.co.kr/bbs"%>
<script type="text/javascript">

 var JSAdd;

function AddJS(path){
	head = document.getElementsByTagName('HEAD')[0];
	if (JSAdd){ head.removeChild(JSAdd); }
	JSAdd = document.createElement('SCRIPT');
	JSAdd.innerHTML = path;
	head.appendChild(JSAdd);
}
var mutifileyn = false;
$(window).load(function() {	

	<c:forEach var="labelPropGbnList2" items="${labelPropGbnList}">		
		<c:choose>
			<c:when test="${labelPropGbnList2.LABEL_PROP_GBN eq '16021300'}">
				//$('#fileupload').attr('id','bbsFVo');
				
			    mutifileyn = true;
			</c:when>
			<c:otherwise>
				
			</c:otherwise>
		</c:choose>
	</c:forEach>
    
    if(mutifileyn){
 
    	$('form[name=bbsFVo]').attr('id','fileupload');
		$('#fileupload').attr('name','fileupload');
		$('#fileupload').attr('commandName','bbsFVo');
		$('#fileupload').attr('method','POST');
		$('#fileupload').attr('enctype','multipart/form-data');
		$('#fileupload').attr('action','/site/${strDomain}/ex/bbs/File_Upload.do'); 
		
		var data = "<c:out value='${detailMap.multijson}'/>";
		if(data != null && data != ""){			
			data = data.replace(/&#039;/g, "'").replace(/&#034;/g, '"').replace(/quot;/g, '"').replace(/&lt;/g, "<").replace(/&gt;/g, ">").replace(/&amp;/g, "&");			
			var arr = JSON.parse(data);
			list = data;		
		    //라인 추가
		    for(var i=0; i < arr.length;i++){    	
		    	$('.files').append(tmpl("template-download",arr[i]));	
		    	if(i == 0){
		    		$('#drag').attr("style","display:none");
		    	}
		    }
		}
    }else{    	
    	$('#fileupload').attr('id','bbsFVo');
		$('#bbsFVo').attr('name','bbsFVo');
		$('#bbsFVo').attr('commandName','bbsFVo');
		$('#bbsFVo').attr('method','POST');
		$('#bbsFVo').attr('enctype','multipart/form-data');
		$('#bbsFVo').attr('action','/site/${strDomain}/ex/bbs/BoardRegProc.do'); 
    }
});

var tempfilecnt = 0;
function doBbsFReg() {	

	var value = "";
	var value2 = "";
	var value3 = "";

<c:forEach var="labelPropGbnList1" items="${labelPropGbnList}">
	
		<c:choose>
		
			<c:when test="${labelPropGbnList1.LABEL_PROP_GBN eq '16020100' }"><%//text Box %>
				value = $('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>1').val();
				//alert(document.bbsFVo.<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>.value );
			</c:when>
			
			<c:when test="${labelPropGbnList1.LABEL_PROP_GBN eq '16020110' && ss_id != null }"><%//PASSWORD %>
			value = $('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>1').val();
			//alert(document.bbsFVo.<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>.value );
			</c:when>
			
			<c:when test="${labelPropGbnList1.LABEL_PROP_GBN eq '16020200' }"><%//text Area %>
				value = $('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>1').val();
			</c:when>
			
			<c:when test="${labelPropGbnList1.LABEL_PROP_GBN eq '16020700' || labelPropGbnList1.LABEL_PROP_GBN eq '16020800' || labelPropGbnList1.LABEL_PROP_GBN eq '16020900'}">
				<c:if test="${labelPropGbnList1.LABEL_PROP_GBN eq '16020700'}"><%//select %>
					value = $('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>1').val();
				</c:if>
				
				<c:if test="${labelPropGbnList1.LABEL_PROP_GBN eq '16020800'}"><%//radio %>
					value = "";
					<c:forEach var="itemList" items="${itemList[selNum]}" varStatus="sstatus">
						if($('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/><c:out value="${sstatus.index+1}"/>').is(':checked')){
							value += $('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/><c:out value="${sstatus.index+1}"/>').val()+"";
						}

					</c:forEach>
				</c:if>
				
				<c:if test="${labelPropGbnList1.LABEL_PROP_GBN eq '16020900'}"><%//checkbox %>
					value = "";
					<c:forEach var="itemList" items="${itemList[selNum]}" varStatus="sstatus">
						if($('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/><c:out value="${sstatus.index+1}"/>').is(':checked')){
							value += $('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/><c:out value="${sstatus.index+1}"/>').val()+"";
						}
					</c:forEach>
				</c:if>				
					<c:set var="selNum" value="${selNum+1}"/>
			</c:when>
			
				<c:when test="${labelPropGbnList1.LABEL_PROP_GBN eq '16021000'}"><%//Writer %>
					value = $('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>1').val();
					//alert(document.bbsFVo.<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>.value );
				</c:when>
				
				<c:when test="${labelPropGbnList1.LABEL_PROP_GBN eq '16020400'}"><%//Address %>
					value = $('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>1').val();
					value2 = $('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>2').val();
					value3 = $('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>3').val();
					//document.bbsFVo.<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>.value = value +"_"+ value2 +"_"+ value3;
					$('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>').val(value +"_"+ value2 +"_"+ value3);
				//alert(document.bbsFVo.<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>.value );
				</c:when>
				
				<c:when test="${labelPropGbnList1.LABEL_PROP_GBN eq '16021100'}"><%//Tel %>
					value = $('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>1').val();
					value2 = $('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>2').val();
					value3 = $('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>3').val();
					//document.bbsFVo.<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>.value = value +"-"+ value2 +"-"+ value3;
					$('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>').val(value +"-"+ value2 +"-"+ value3);
					
				//alert(document.bbsFVo.<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>.value );
				</c:when>
				
				<c:when test="${labelPropGbnList1.LABEL_PROP_GBN eq '16020500'}"><%//Phone %>
					value = $('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>1').val();
					value2 = $('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>2').val();
					value3 = $('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>3').val();
					//document.bbsFVo.<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>.value = value +"-"+ value2 +"-"+ value3;
					$('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>').val(value +"-"+ value2 +"-"+ value3);
				</c:when>
				
				<c:when test="${labelPropGbnList1.LABEL_PROP_GBN eq '16021200'}"><%//Email %>
					value = $('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>1').val();
					value2 = $('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>2').val();
					value3 = $('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>3').val();
					
					$('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>').val(value +"@"+ value2+value3);
					//document.bbsFVo.<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>.value = value +"@"+ value2+value3;	
					//alert(document.bbsFVo.<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>.value);
					
				</c:when>
		
				<c:when test="${labelPropGbnList1.LABEL_PROP_GBN eq '16020600'}"><%//File %>
					value = "file";
				</c:when>
			</c:choose>
			//alert('aaaaaaaaaa');
			
			
		//구청장에게 바란다. (벨리데이터)
			
			<c:if test="${bbsFVo.cbIdx eq '414'}">
				<c:if test="${labelPropGbnList1.LABEL_COMP_YN eq 'Y'}">
				
				<c:if test="${labelPropGbnList1.LABEL_PROP_GBN eq '16020800'}">
				 if($("input:radio[id='<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>1']").prop("checked")==false && $("input:radio[id='<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>2']").prop("checked")==false){
						alert("<c:out value="${labelPropGbnList1.LABEL_NAME}"/>을(를) 선택해주세요.");
						$("#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>1").focus();
						return;
					}
				 </c:if>
				
				<c:if test="${labelPropGbnList1.LABEL_PROP_GBN ne '16020600'}">
						if ($('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>1').val() == "") {
							alert("<c:out value="${labelPropGbnList1.LABEL_NAME}"/>을(를) 선택해주세요.");
							$('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>1').focus();
							return;
						}
				</c:if>
			
				<c:if test="${labelPropGbnList1.LABEL_PROP_GBN eq '16020400' || labelPropGbnList1.LABEL_PROP_GBN eq '16021100' || labelPropGbnList1.LABEL_PROP_GBN eq '16020500'}">
					if($('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>2').val() == ""){
						alert("<c:out value="${labelPropGbnList1.LABEL_NAME}"/>을(를) 입력해주세요.");
						$('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>2').focus();
						return;
					}
					if($('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>3').val() == ""){
						alert("<c:out value="${labelPropGbnList1.LABEL_NAME}"/>을(를) 입력해주세요.");
						$('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>3').focus();
						return;
					}		
					
			</c:if>
		</c:if>
	</c:if>
			
	//인재에게 바란다. 제외한 (벨리데이터)				
	<c:if test="${bbsFVo.cbIdx ne '414'}">		
		<c:if test="${labelPropGbnList1.LABEL_COMP_YN eq 'Y'}">
			<c:if test="${labelPropGbnList1.LABEL_PROP_GBN ne '16020600' && labelPropGbnList1.LABEL_PROP_GBN ne '16020800'}">
				
		 	if(cm_is_empty(value)){
					alert("<c:out value="${labelPropGbnList1.LABEL_NAME}"/>을(를) 입력해주세요.");
					$('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>1').focus();
					return;
				} 
			</c:if>
			
			
			
			//2016_11_15 벨리데이터 (라디오버튼) 시작
			<c:if test="${labelPropGbnList1.LABEL_PROP_GBN eq '16020800'}">
			
			 if(cm_is_empty($("input:radio[name='<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>']:checked").val()) ){
					alert("<c:out value="${labelPropGbnList1.LABEL_NAME}"/>을(를) 입력해주세요.");
					$("input:radio[name='<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>']:checked").focus;
					return;
				}
			 
			</c:if>
			 
			//2016_11_15 벨리데이터 (라디오버튼)	끝		
	
			<c:if test="${labelPropGbnList1.LABEL_PROP_GBN eq '16020400' || labelPropGbnList1.LABEL_PROP_GBN eq '16021100' || labelPropGbnList1.LABEL_PROP_GBN eq '16020500'}">
				if(cm_is_empty(value2)){
					alert("<c:out value="${labelPropGbnList1.LABEL_NAME}"/>을(를) 입력해주세요.");
					$('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>2').focus();
					return;
				}
				if(cm_is_empty(value3)){
					alert("<c:out value="${labelPropGbnList1.LABEL_NAME}"/>을(를) 입력해주세요.");
					$('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>3').focus();
					return;
				}		
				
			</c:if>
			
			<c:if test="${labelPropGbnList1.LABEL_PROP_GBN ne '16020600'}">
			if ($('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>1').val() == "") {
				alert("<c:out value="${labelPropGbnList1.LABEL_NAME}"/>를 입력하여 주십시오.");
				$('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>1').focus();
				return;
			}
	</c:if>

	<c:if test="${labelPropGbnList1.LABEL_PROP_GBN eq '16020400' || labelPropGbnList1.LABEL_PROP_GBN eq '16021100' || labelPropGbnList1.LABEL_PROP_GBN eq '16020500'}">
		if($('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>2').val() == ""){
			alert("<c:out value="${labelPropGbnList1.LABEL_NAME}"/>을(를) 입력해주세요.");
			$('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>2').focus();
			return;
		}
		if($('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>3').val() == ""){
			alert("<c:out value="${labelPropGbnList1.LABEL_NAME}"/>을(를) 입력해주세요.");
			$('#<c:out value="${labelPropGbnList1.CONTENT_MAPPING_L}"/>3').focus();
			return;
		}		
		
</c:if>
		</c:if>
	</c:if>
	
</c:forEach>		

/* <c:if test="${bbsFVo.cbIdx ne '301' && bbsFVo.cbIdx ne '302' && bbsFVo.cbIdx ne '414'}">    
    if( !$('#userChk').is(':checked') ){
        alert('개인정보수집 및 이용 확인하시고 동의하여 주세요.');
        $('#userChk').focus();
        return;
    }
 </c:if>  */
 
 
<c:if test="${bbsFVo.cbIdx eq '301' || bbsFVo.cbIdx eq '302'}">    
    if( !$('#userChk1').is(':checked') ){
        alert('사전 양해 없이 삭제될 수 있으니 동의하여 주세요.');
        $('#userChk1').focus();
        return;
    }
</c:if>    



    if(mutifileyn){    	
    	if($(".template-upload").length > 1){
    		$("#uploadbtn").click();
    	}else{
    		$('#fileupload').attr('id','bbsFVo');
    		$('#bbsFVo').attr('name','bbsFVo');
    		$('#bbsFVo').attr('commandName','bbsFVo');
    		$('#bbsFVo').attr('method','POST');
    		$('#bbsFVo').attr('enctype','multipart/form-data');
    		$('#bbsFVo').attr('action','/site/${strDomain}/ex/bbs/BoardRegProc.do');
    		
    		<c:choose>
						<c:when test="${bbsFVo.cbIdx eq '414'}">
							<c:if test="${bbsFVo.mode eq 'U'}">
							var cbIdx = document.bbsFVo.cbIdx.value;
					    	var bcIdx = document.bbsFVo.bcIdx.value;
							//alert('aaaaaaaaaaaaa');
							//alert(cbIdx);
					    	var gubPassword = document.bbsFVo.gubPassword1.value;
							//alert(gubPassword);
								var objJson = {
										
										'cbIdx' : cbIdx
										,'bcIdx' : bcIdx
										,'gubPassword' : gubPassword
										
								}
								//console.log("::::", objJson);
								
								$.ajax({
									type: "POST",
									url: "<c:url value='/site/${strDomain}/ex/bbs/GUBBoardUpdateAx.do'/>",
									data : (objJson),
									dataType : "json",
									success:function(data) {
										//alert(data.result);
									
										if( data.result ){
				
											 //alert(data.message);
											 document.bbsFVo.submit(); 
											
										}else{
											
											alert(data.message);								
											return false;
											//$("#bbsFVo").attr("action", "/site/mayor/ex/bbs/List.do?cbIdx="+cbIdx+"").submit();
										}
										
										//console.log("::::", data);
										
									 },
									error: function(xhr,status,error){
										alert(status);
									}
								});
							
							</c:if>
							<c:if test="${bbsFVo.mode eq 'C'}">
							document.bbsFVo.submit();
							</c:if>
						</c:when>
						<c:otherwise>
						document.bbsFVo.submit();
						</c:otherwise>
					</c:choose>
    	}    	
    }else{
				    	<c:choose>
						<c:when test="${bbsFVo.cbIdx eq '414'}">
							<c:if test="${bbsFVo.mode eq 'U'}">
							var cbIdx = document.bbsFVo.cbIdx.value;
					    	var bcIdx = document.bbsFVo.bcIdx.value;
							//alert('aaaaaaaaaaaaa');
							//alert(cbIdx);
					    	var gubPassword = document.bbsFVo.gubPassword1.value;
							//alert(gubPassword);
								var objJson = {
										
										'cbIdx' : cbIdx
										,'bcIdx' : bcIdx
										,'gubPassword' : gubPassword
										
								}
								//console.log("::::", objJson);
								
								$.ajax({
									type: "POST",
									url: "<c:url value='/site/${strDomain}/ex/bbs/GUBBoardUpdateAx.do'/>",
									data : (objJson),
									dataType : "json",
									success:function(data) {
										//alert(data.result);
									
										if( data.result ){
				
											 //alert(data.message);
											 document.bbsFVo.submit(); 
											
										}else{
											
											alert(data.message);								
											$("#bbsFVo").attr("action", "/site/mayor/ex/bbs/List.do?cbIdx="+cbIdx+"").submit();
										}
										
										//console.log("::::", data);
										
									 },
									error: function(xhr,status,error){
										alert(status);
									}
								});
							
							</c:if>
						</c:when>
						<c:otherwise>
						<c:if test="${bbsFVo.mode eq 'U'}">
							document.bbsFVo.submit();    
						</c:if>
						</c:otherwise>
					</c:choose>
				
				<c:if test="${bbsFVo.mode eq 'C'}">
					document.bbsFVo.submit();    
				</c:if>
				<c:if test="${bbsFVo.mode eq 'R'}">
					document.bbsFVo.submit();    
				</c:if>
		//	document.bbsFVo.submit();  
	}     
}


//인바라 답변여부 알림 체크
function doASnsCompChk(){
	//문자안내
	if( $("#23000100").is(":checked") ){
		if( cm_is_empty($('#phoneCont1').val()) || cm_is_empty($('#phoneCont2').val()) || cm_is_empty($('#phoneCont3').val()) ){
			alert('이동전화을(를) 입력해주세요.');
			$("#23000100").attr("checked",false);
			return false;
		}
	}
	//음성안내
	if( $("#23000200").is(":checked") ){
		if( cm_is_empty($('#phoneCont1').val()) || cm_is_empty($('#phoneCont2').val()) || cm_is_empty($('#phoneCont3').val()) ){
			alert('이동전화을(를) 입력해주세요.');
			$("#23000200").attr("checked",false);
			return false;
		}
	}
	//이메일안내
	if( $("#23000300").is(":checked") ){
		if( cm_is_empty($('#emailCont').val()) ){
			alert('이메일을(를) 입력해주세요.');
			$("#23000300").attr("checked",false);
			return false;
		}
	}
	return true;
}

//파일 삭제
function doBbsFileRmvAx(cbIdx ,bcIdx ,streFileNm){
		
		if(!confirm("파일이 완전히 삭제 됩니다. 삭제하시겠습니까?")) return;
		
		$.ajax({
			type: "GET",
			url: "<c:url value='/site/${strDomain}/ex/bbs/File_RmvAx.do'/>",
			data : {"cbIdx":cbIdx,"bcIdx":bcIdx,"streFileNm":streFileNm},
			dataType : "json",
			success:function(object) {
				alert('삭제되었습니다.');
				$(".attached-files").hide();
			 },
			error: function(xhr,status,error){
				alert(status);
			}
		});
	}	


function openAddrSearch() {

	var from = document.form;
	doJusoPop(from);
}

function setAddrValue(addr1, addr2, zip) {

	$("#addrCont1").val(zip);
	$("#addrCont2").val(addr1);
	$("#addrCont3").val(addr2);
	$("#addrCont3").focus();
}



$(window).load(function() {
	$(".datepicker").datepicker({
		dateFormat: 'yy-mm-dd',
		buttonImage: '/images/<c:out value='${strDomain}' />/common/icon-calendar.gif',
		
	
	});		
	
	$("[id^='fileTagId']").change(function () {		
	    var iSize = 0;
	    if($(this).val() != ""){
	    	iSize = ($(this)[0].files[0].size / 1024);
		   	if(iSize == 0){
		   		alert("빈파일입니다 파일사이즈를 확인하세요");		   				   	
		   		$(this).replaceWith($(this).clone(true));
		   		$(this).val("");
		   		$("#file_route"+$(this).attr("cnt")).val("");		   		
		   		$(this).focus();
		   	}
	    }	    
	});
	
});

/* $(document).load(function() {
	$(".datepicker").datepicker({
        buttonImage: '/images/<c:out value="${strDomain}" />/common/icon-calendar.gif',
        dateFormat: 'yy-mm-dd'
 
     });
}); */
function cm_is_empty(val){
	if(val==""){
		return true;
	}
	return false;
}
</script>
<body>	
	<div id="container">
		<div class="containerTop type1">
			<div class="inner">
				<h3><c:out value="${bbsFVo.cbName}"/></h3>
			</div>
		</div>
		<div id="contents">
			<div class="inner">
				<p class="top_info">디지털배움터에 관한 문의사항과 칭찬하기, 민원사항을 작성하실 수 있습니다.</p>
	<!-- Board Top -->
	<!-- // Board center -->
	<!-- 주소팝업사용시 무조건 만드셔야됨. -->
	<form id="form" name="form" method="post" class="hide">
		<input type="hidden" id="confmKey" name="confmKey" value="bnVsbDIwMTQxMDIzMTc0NTQ2" />
		<input type="hidden" id="returnUrl" name="returnUrl" value="" />
	</form>
	<!-- //주소팝업사용시 무조건 만드셔야됨. -->	
		<form:form commandName="bbsFVo" id="fileupload" name="fileupload" method="post" enctype="multipart/form-data" action="/site/${strDomain}/ex/bbs/File_Upload.do">
		<form:hidden path="cbIdx" />
		<form:hidden path="bcIdx" />
		<form:hidden path="mode" />
		<form:hidden path="ssId" />
		<form:hidden path="dupcode" />
		<form:hidden path="parentSeq" />
		<form:hidden path="answerStep" />
		<form:hidden path="answerDepth" />
		<form:hidden path="searchKey" />
		<form:hidden path="pageIndex" />
		<form:hidden path="tgtTypeCd" />
		<form:hidden path="clobContTemp" />
		<form:hidden path="subContTemp" />
		<form:hidden path="fileMaxSize" />
		<input type="hidden" name="tempname"/>
		<input type="hidden" name="tempsname"/>
		<input type="hidden" name="tempsize"/>
		<input type="hidden" name="strDomain" id="strDomain" value="${strDomain}"/>
		<input type="hidden" name="oldGubPassword" id="oldGubPassword" value="${gubPassword}"/>
	<div class="tableBox">
		<table class="write">
			<colgroup>
				<col style="width:10%;">			
				<col>
				<col style="width:10%;">
				<col>
			</colgroup>
			<tbody>
				<tr>
					<th scope="row">분류</th>
					<td>
					</td>
					<th scope="row">작성자</th>
					<td><input type="text" name="WriteName" value="" title="작성자입력" class="w160"></td>
				</tr>
				<tr>
					<th scope="row">비밀번호</th>
					<td><input type="Password" name="Password1" id="Password1" value="" class="w200"></td>
					<th scope="row">비밀번호 확인</th>
					<td><input type="Password" name="Password2" id="Password2" value="" class="w200"></td>
				</tr>
				
				<tr>
				  <th scope="row">지역</th>
				  <td class="left" colspan="3">
					<select name="sido1" id="sido1"></select>
					<select name="gugun1" id="gugun1" class="w120"></select>
					<select name="dong1" id="dong1" class="w120"></select>
				  </td>
				</tr>
				<tr>
					<th scope="row">이메일</th>
					<td>
						<input name="email1" type="text" class="input_04" size="10" />
						@
						  <input name="email2" type="text" class="input_04" size="13" noHangul />
						  <select name="email3" onchange="Page_Email()">
							
							<option value="1">직접입력</option>
							<option value="naver.com">naver.com</option>
							<option value="hanmail.net">hanmail.net</option>
							<option value="dreamwiz.com">dreamwiz.com</option>
							<option value="empal.com">empal.com</option>
							<option value="paran.com">paran.com</option>
							<option value="korea.com">korea.com</option>
							<option value="nate.com">nate.com</option>
							<option value="netian.com">netian.com</option>
							<option value="yahoo.co.kr">yahoo.co.kr</option>
							<option value="hotmail.com">hotmail.com</option>
							<option value="gmail.com">gmail.com</option>
						  </select>
					</td>
					<th scope="row">연락처</th>
					<td>
						<select name="hp1" class="w70">
							<option value="010">010</option>
							<option value="019">019</option>
							<option value="02">02</option>
							<option value="031">031</option>
							<option value="032">032</option>
							<option value="033">033</option>
							<option value="041">041</option>
							<option value="042">042</option>
							<option value="043">043</option>
							<option value="044">044</option>
							<option value="051">051</option>
							<option value="052">052</option>
							<option value="053">053</option>
							<option value="054">054</option>
							<option value="055">055</option>
							<option value="061">061</option>
							<option value="062">062</option>
							<option value="063">063</option>
							<option value="064">064</option>

						  </select>
						-
						<input type="text" name="hp2" value="" title="전화번호 중간자리" class="w80">
						-
						<input type="text" name="hp3" value="" title="전화번호 뒷자리" class="w80">
					</td>
				</tr>	
				<tr>
					<th scope="row">제목</th>
					<td colspan="3"><input type="text" name="title" value="" title="제목입력" class="w70p"></td>
				</tr>	
				<tr>
					<th scope="row">문의내용</th>
					<td colspan="3">
						<textarea class="w100p h150" name="content"></textarea>
					</td>
				</tr>	
			</tbody>
		</table>
	</div>
	
	
			<caption><c:out value="${bbsFVo.cbName }" /> 등록/수정 폼</caption>
			<tbody>
			<c:set var="selNum" value="0"/>
				<c:forEach var="labelPropGbnList" items="${labelPropGbnList}">
					 <c:choose>
						<c:when test="${labelPropGbnList.LABEL_PROP_GBN  eq '16021600'}"><%//사용자에서는 부서입력 없음 %>
 							<!-- continue -->
						</c:when>
						<c:when test="${bbsFVo.cbIdx eq '414' && labelPropGbnList.LABEL_NAME eq '답변여부' }">
							<!-- continue -->
						</c:when>
						<c:when test="${(bbsFVo.cbIdx eq '276' || bbsFVo.cbIdx eq 420 || bbsFVo.cbIdx eq 429 || bbsFVo.cbIdx eq 436 || bbsFVo.cbIdx eq 443 || bbsFVo.cbIdx eq 450 || bbsFVo.cbIdx eq 506 || bbsFVo.cbIdx eq 513 || bbsFVo.cbIdx eq 520 || bbsFVo.cbIdx eq 527 || bbsFVo.cbIdx eq 534 || bbsFVo.cbIdx eq 541 || bbsFVo.cbIdx eq 548 || bbsFVo.cbIdx eq 457 || bbsFVo.cbIdx eq 464 || bbsFVo.cbIdx eq 471 || bbsFVo.cbIdx eq 478 || bbsFVo.cbIdx eq 492 || bbsFVo.cbIdx eq 499 ) && labelPropGbnList.LABEL_NAME eq '답변여부' }">
							 <!-- continue -->
						</c:when>
						<c:when test="${labelPropGbnList.LABEL_PROP_GBN eq '16020110' && ss_id!=null}">
							 <!-- continue -->
						</c:when>
						<c:otherwise>
						<tr>						
							<th class="item-title">
								<label for="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>1" >
									<c:if test="${labelPropGbnList.LABEL_COMP_YN eq 'Y'}">
										<b class="required-mark">&#9733;</b>
									</c:if>
										<c:out value="${labelPropGbnList.LABEL_NAME}"/>
									</span>
								</label>
							</th>						
						<c:choose>
							<c:when test="${labelPropGbnList.LABEL_PROP_GBN eq '16020100' }"><%//text Box %>
								<td class="item-data"><input type="text" class="write-address-detail" name="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>" id="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>1" value="<c:out value="${detailMap[labelPropGbnList.CONTENT_MAPPING_L]}"/>"/></td>
							</c:when>
							<c:when test="${labelPropGbnList.LABEL_PROP_GBN eq '16020110'}"><%//password %>
								<td class="item-data"><input type="password"  size="6" name="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>" id="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>1" />
									&nbsp;&nbsp;※삭제/수정시 필요하니 반드시 입력하여 주십시오(영문+숫자조합,숫자,영문조합). 
								</td>
							</c:when>
							<c:when test="${labelPropGbnList.LABEL_PROP_GBN eq '16020200' }"><%//text Area %>
								<td class="item-data">
									<textarea rows="10" cols="50" name="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>" id="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>1" class="txtAtea"><c:out value="${detailMap[labelPropGbnList.CONTENT_MAPPING_L]}"/></textarea>		
								</td>
							</c:when>
							<c:when test="${labelPropGbnList.LABEL_PROP_GBN eq '16020700' || labelPropGbnList.LABEL_PROP_GBN eq '16020800' || labelPropGbnList.LABEL_PROP_GBN eq '16020900'}">
								<c:if test="${labelPropGbnList.LABEL_PROP_GBN eq '16020700'}"><%//select %>
									<td class="item-data">
										<select name="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>" id="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>1" class="write-email-domain-select">
										<c:forEach var="itemList" items="${itemList[selNum]}" varStatus="sstatus">
											<option value="<c:out value="${itemList.CODE}"/>" <c:if test="${ detailMap[labelPropGbnList.CONTENT_MAPPING_L] eq itemList.CODE}">selected</c:if>><c:out value="${itemList.CODE_NAME}"/></option>
										</c:forEach>
										</select>
									</td>
								</c:if>
								<c:if test="${labelPropGbnList.LABEL_PROP_GBN eq '16020800'}"><%//radio %>				
									<td class="item-data">
									<c:choose>
										<c:when test="${bbsFVo.cbIdx eq '414' }">
											<c:choose>
												<c:when test="${labelPropGbnList.CONTENT_MAPPING_L eq 'ansYnCont'}">
													<span>				
													<c:forEach var="itemList" items="${itemList[selNum]}" varStatus="sstatus">
															<input type="radio" class="rdo1" name="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>" id="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/><c:out value="${sstatus.index+1}"/>" value="<c:out value="${itemList.CODE}"/>" 
															<c:if test="${empty detailMap[labelPropGbnList.CONTENT_MAPPING_L]}"><c:if test="${itemList.CODE_NAME eq '답변요청'}">checked</c:if></c:if> 
															<c:if test="${detailMap[labelPropGbnList.CONTENT_MAPPING_L]  ne '' }"><c:if test="${detailMap[labelPropGbnList.CONTENT_MAPPING_L] eq itemList.CODE}">checked</c:if></c:if>/>
															<label for=""><c:out value="${itemList.CODE_NAME}"/></label>
													</c:forEach>
													</span>
												</c:when>
												<c:when test="${labelPropGbnList.CONTENT_MAPPING_L eq 'ext1'}">
													<span>				
													<c:forEach var="itemList" items="${itemList[selNum]}" varStatus="sstatus">
															<input type="radio" class="rdo1" name="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>" id="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/><c:out value="${sstatus.index+1}"/>" value="<c:out value="${itemList.CODE}"/>" 
															<c:if test="${empty detailMap[labelPropGbnList.CONTENT_MAPPING_L]}"><c:if test="${itemList.CODE_NAME eq '홈페이지에서 직접확인'}">checked</c:if></c:if> 
															<c:if test="${detailMap[labelPropGbnList.CONTENT_MAPPING_L]  ne '' }"><c:if test="${detailMap[labelPropGbnList.CONTENT_MAPPING_L] eq itemList.CODE}">checked</c:if></c:if>/>
															<label for=""><c:out value="${itemList.CODE_NAME}"/></label>
													</c:forEach>
													</span>
												</c:when>
												<c:when test="${labelPropGbnList.CONTENT_MAPPING_L eq 'openYnCont'}">
													<span>				
													<c:forEach var="itemList" items="${itemList[selNum]}" varStatus="sstatus">
														<input type="radio" class="rdo1" name="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>" id="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/><c:out value="${sstatus.index+1}"/>" value="<c:out value="${itemList.CODE}"/>" 
														<c:if test="${empty detailMap[labelPropGbnList.CONTENT_MAPPING_L]}"><c:if test="${itemList.CODE_NAME eq ''}">checked</c:if></c:if>
														<c:if test="${detailMap[labelPropGbnList.CONTENT_MAPPING_L]  ne '' }"><c:if test="${detailMap[labelPropGbnList.CONTENT_MAPPING_L] eq itemList.CODE}">checked</c:if></c:if>/>
														<label for=""><c:out value="${itemList.CODE_NAME}"/></label>
													</c:forEach>
													</span>
												</c:when>
												<c:otherwise>
												<span>
													<c:forEach var="itemList" items="${itemList[selNum]}" varStatus="sstatus">
														<c:if test="${labelPropGbnList.CONTENT_MAPPING_L eq 'ansYnCont'}">
															<input type="radio" class="rdo1" name="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>" id="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/><c:out value="${sstatus.index+1}"/>" value="<c:out value="${itemList.CODE}"/>" 
															<c:if test="${empty detailMap[labelPropGbnList.CONTENT_MAPPING_L]}"><c:if test="${itemList.CODE_NAME eq '답변요청'}">checked</c:if></c:if>
															<c:if test="${detailMap[labelPropGbnList.CONTENT_MAPPING_L]  ne '' }"><c:if test="${detailMap[labelPropGbnList.CONTENT_MAPPING_L] eq itemList.CODE}">checked</c:if></c:if>/>
															<label for=""><c:out value="${itemList.CODE_NAME}"/></label>
														</c:if>	
													</c:forEach>
												</span>
												</c:otherwise>
											</c:choose>
										</c:when>
										<c:otherwise>
											<span>
												<c:forEach var="itemList" items="${itemList[selNum]}" varStatus="sstatus">
													<c:if test="${labelPropGbnList.CONTENT_MAPPING_L eq 'ansYnCont'}">
														<input type="radio" class="rdo1" name="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>" id="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/><c:out value="${sstatus.index+1}"/>" value="<c:out value="${itemList.CODE}"/>" 
														<c:if test="${empty detailMap[labelPropGbnList.CONTENT_MAPPING_L]}"><c:if test="${itemList.CODE_NAME eq '답변요청'}">checked</c:if></c:if>
														<c:if test="${detailMap[labelPropGbnList.CONTENT_MAPPING_L]  ne '' }"><c:if test="${detailMap[labelPropGbnList.CONTENT_MAPPING_L] eq itemList.CODE}">checked</c:if></c:if>/>
														<label for=""><c:out value="${itemList.CODE_NAME}"/></label>
													</c:if>	
												</c:forEach>
												<c:forEach var="itemList" items="${itemList[selNum]}" varStatus="sstatus">
													<c:if test="${labelPropGbnList.CONTENT_MAPPING_L eq 'openYnCont'}">
														<input type="radio" class="rdo1" name="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>" id="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/><c:out value="${sstatus.index+1}"/>" value="<c:out value="${itemList.CODE}"/>" 
														<c:if test="${empty detailMap[labelPropGbnList.CONTENT_MAPPING_L]}"><c:if test="${itemList.CODE_NAME eq '공개'}">checked</c:if></c:if> 
														<c:if test="${detailMap[labelPropGbnList.CONTENT_MAPPING_L]  ne '' }"><c:if test="${detailMap[labelPropGbnList.CONTENT_MAPPING_L] eq itemList.CODE}">checked</c:if></c:if>/>
														<label for=""><c:out value="${itemList.CODE_NAME}"/></label>
													</c:if>
												</c:forEach>
											</span>
										</c:otherwise>
									</c:choose>		
								</td>				
							</c:if>
							<c:if test="${labelPropGbnList.LABEL_PROP_GBN eq '16020900'}"><%//checkbox %>
								<td class="item-data">										
									<span>
										<c:forEach var="itemList" items="${itemList[selNum]}" varStatus="sstatus">
											<input type="checkbox" class="chb1" name="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>" id="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/><c:out value="${sstatus.index+1}"/>" value="<c:out value="${itemList.CODE}"/>" <c:if test="${detailMap[labelPropGbnList.CONTENT_MAPPING_L] eq itemList.CODE}">checked</c:if>/><label for=""><c:out value="${itemList.CODE_NAME}"/></label>
										</c:forEach>
									</span>
								</td>
							</c:if>				
							<c:set var="selNum" value="${selNum+1}"/>
						</c:when>
						<c:when test="${labelPropGbnList.LABEL_PROP_GBN eq '16021000'}"><%//Writer %>	
						<td class="item-data">
								<c:choose>
									<c:when test="${labelPropGbnList.CONTENT_MAPPING eq 'NAME_CONT' && empty detailMap[labelPropGbnList.CONTENT_MAPPING_L]}">
										<input type="text" name="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>" id="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>1" class="write-name" value="<c:out value="${detailMap[labelPropGbnList.CONTENT_MAPPING_L]}"/>"/>
									</c:when>
									<c:when test="${bbsFVo.cbIdx eq 261 || bbsFVo.cbIdx eq 262 || bbsFVo.cbIdx eq 263 || bbsFVo.cbIdx eq 264 || bbsFVo.cbIdx eq 265}">
										<input type="text" name="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>" id="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>1" class="write-name" value="<c:out value="${detailMap[labelPropGbnList.CONTENT_MAPPING_L]}"/>" readonly="true"/>
									</c:when>
									<c:otherwise>
										<c:if test="${labelPropGbnList.CONTENT_MAPPING eq 'NAME_CONT'}">
											<input type="hidden" name="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>" id="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>1" class="write-name" value="<c:out value="${detailMap[labelPropGbnList.CONTENT_MAPPING_L]}"/>"/>
											<label><c:out value="${detailMap[labelPropGbnList.CONTENT_MAPPING_L]}"/></label>
										</c:if>					
										
									</c:otherwise>
								</c:choose>		
							</td>				
						</c:when>
						<c:when test="${labelPropGbnList.LABEL_PROP_GBN eq '16020400'}"><%//Address %>
							<td class="item-data">
								<input type="hidden" name="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>" id="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>" value=""/>
								<c:set var="addr" value="${fn:split(detailMap[labelPropGbnList.CONTENT_MAPPING_L],'_')}"/>
								<input type="hidden" name="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>1" id="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>1" size="6" readonly="true" value="<c:out value="${addr[0]}"/>"/>
								<a href='javascript:openAddrSearch();' class="btn_zipcode" title='새창'>우편번호검색</a>
								<input type="text" title='기본주소' name='<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>2' id='<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>2' class="write-address-detail" readonly="true"  value="<c:out value="${addr[1]}"/>"/>
								<input type="text" title='상세주소' name='<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>3' id='<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>3' class="write-address-detail"  value="<c:out value="${addr[2]}"/>"/>
							</td>		
						</c:when>
						<c:when test="${labelPropGbnList.LABEL_PROP_GBN eq '16021100'}"><%//Tel %>
							<td class="item-data">
								<c:set var="tel" value="${fn:split(detailMap[labelPropGbnList.CONTENT_MAPPING_L],'-')}"/>
								<input type="hidden" name="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>" id="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>" value=""/>
								<c:if test="${bbsFVo.mode eq 'C'}">	
									<input name="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>1" title="<c:out value="${labelPropGbnList.LABEL_NAME}"/> 앞번호" id="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>1" onkeypress="return showKeyCode(event)" type="text" size="5" maxlength="4" value="${tel[0]}" class="write-phone2"/>					
								</c:if>		
								<c:if test="${bbsFVo.mode eq 'U'}">	
							  		<input name="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>1" title="<c:out value="${labelPropGbnList.LABEL_NAME}"/> 앞번호" id="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>1" onkeypress="return showKeyCode(event)" type="text" size="5" maxlength="4" value="${tel[0]}" class="write-phone2"/> 
								</c:if>
								<c:if test="${bbsFVo.mode eq 'R'}">	
									<input name="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>1" title="<c:out value="${labelPropGbnList.LABEL_NAME}"/> 앞번호" id="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>1" onkeypress="return showKeyCode(event)" type="text" size="5" maxlength="4" value="${tel[0]}" class="write-phone2"/> 
							 	</c:if>
								- <input name="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>2" title="<c:out value="${labelPropGbnList.LABEL_NAME}"/> 중간번호" id="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>2" onkeypress="return showKeyCode(event)" type="text" size="5" maxlength="4" value="${tel[1]}" class="write-phone2"> 
								- <input name="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>3" title="<c:out value="${labelPropGbnList.LABEL_NAME}"/> 뒷번호" id="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>3" onkeypress="return showKeyCode(event)" type="text" size="5" maxlength="4" value="${tel[2]}" class="write-phone3"></td>
							</td>
						</c:when>
						<c:when test="${labelPropGbnList.LABEL_PROP_GBN eq '16020500'}"><%//Phone %>
							<td class="item-data">
								<c:set var="phone" value="${fn:split(detailMap[labelPropGbnList.CONTENT_MAPPING_L],'-')}"/>
								<input type="hidden" name="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>" id="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>" value=""/>
								<c:if test="${bbsFVo.mode eq 'C'}">	
									<input name="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>1" title="<c:out value="${labelPropGbnList.LABEL_NAME}"/> 앞번호" id="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>1" onkeypress="return showKeyCode(event)" type="text" size="5" maxlength="4" value="${phone[0]}" class="write-phone2"/>
								</c:if>
								<c:if test="${bbsFVo.mode eq 'U'}">		
									<input name="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>1" title="<c:out value="${labelPropGbnList.LABEL_NAME}"/> 앞번호" id="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>1" onkeypress="return showKeyCode(event)" type="text" size="5" maxlength="4" value="${phone[0]}" class="write-phone2"/>
								</c:if>
								<c:if test="${bbsFVo.mode eq 'R'}">	
									<input name="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>1" title="<c:out value="${labelPropGbnList.LABEL_NAME}"/> 앞번호" id="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>1" onkeypress="return showKeyCode(event)" type="text" size="5" maxlength="4" value="${phone[0]}" class="write-phone2"/>
								</c:if>	
								- <input name="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>2" title="<c:out value="${labelPropGbnList.LABEL_NAME}"/> 중간번호" id="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>2" onkeypress="return showKeyCode(event)" type="text" size="5" maxlength="4" value="${phone[1]}" class="write-phone2"/> 
								- <input name="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>3" title="<c:out value="${labelPropGbnList.LABEL_NAME}"/> 뒷번호" id="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>3" onkeypress="return showKeyCode(event)" type="text" size="5" maxlength="4" value="${phone[2]}" class="write-phone3"/></td>
							</td>
						</c:when>
						<c:when test="${labelPropGbnList.LABEL_PROP_GBN eq '16021200'}"><%//Email %>
							<td class="item-data">
								<c:set var="email" value="${fn:split(detailMap[labelPropGbnList.CONTENT_MAPPING_L],'@')}"/>
								<input type="hidden" name="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>" id="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>" value=""/>
								<input type="text" name="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>1" id="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>1" class="write-email-domain" value="<c:out value="${email[0]}"/>"/>@
									<select class="write-email-domain-select" name="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>2" id="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>2">
										<option value="" >직접입력</option>
											<c:forEach items="${emailList }" var="emailList" varStatus="status">
												<option value="<c:out value="${emailList.codeName }"/>" label="${emailList.codeName}" id="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>2"></option>
											</c:forEach>
									</select>
									
								<%-- <select class="select-box select-box-txt" name="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>1">
										<option value="@">직접입력</option>
										<option value="@naver.com">naver.com</option>
										<option value="@hanmail.net">hanmail.net</option>
										<option value="@gmail.net">gmail.com</option>					
									</select> --%>					
									
								<input type="text" name="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>3" id="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>3" class="write-email-domain" value="<c:out value="${email[1]}"/>"/> 
							</td>
						</c:when>		
			
						<c:when test="${labelPropGbnList.LABEL_PROP_GBN eq '16021400' }"><%//달력 %>
							<td class="item-data">	
								<input type="text" id="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>1" name="<c:out value="${labelPropGbnList.CONTENT_MAPPING_L}"/>" title="달력" class="datepicker" style="width:30%;" /><!-- <a href="#none" title="" class="btn-pp-cal"><img src="/images/<c:out value='${strDomain}' />/common/icon-calendar.gif" alt="달력" /></a> -->
							</td>
						</c:when>
						<c:when test="${labelPropGbnList.LABEL_PROP_GBN eq '16020600'}"><%//File %>	
							<td class="item-data">
							<c:forEach var="fileNum" begin="1" end="${bbsFVo.bbsFileCnt}" step="1">					 			
								<span class="file-box">
									<input type="text" id="file_route${fileNum-1}" name="" title="fileRoute" readonly="readonly" class="write-file-attach" />
									<label for="fileTagId${fileNum-1}"><font>찾아보기</font>
										<input name="fileTagId" id="fileTagId${fileNum-1}" cnt="${fileNum-1}" type="file" onchange="javascript:document.getElementById('file_route${fileNum-1}').value=this.value" >
									</label>								
								</span>
							</c:forEach>
							<c:out value="${detailMap[labelPropGbnList.CONTENT_MAPPING_L]}"/>
							<p class="btm-text" style="color: rgb(255, 0, 0);">※파일1개당 최대<c:out value="${bbsFVo.fileMaxSize}"/>MB까지만 가능합니다</p>
							<%-- 파일 다운로드 & 확장자 구분으로 이미지 뿌리기 시작--%>						
							<c:forEach items="${fileList}" var="fileList">		
								<c:choose>
									<c:when test="${fn:substring(fileList.orignlFileNm ,fn:indexOf(fileList.orignlFileNm,'.')+1,fn:length(fileList.orignlFileNm )) eq 'hwp'}">
										<ul class="attached-files">
											<li>
												<span class="file-img">
													<a href="/common/board/Download.do?bcIdx=<c:out value="${fileList.bcIdx}"/>&amp;cbIdx=<c:out value="${fileList.cbIdx}"/>&amp;streFileNm=<c:out value="${fileList.streFileNm}"/>" class="file" title="파일 다운로드">
														<c:out value="${fileList.orignlFileNm }" /> [<c:out value="${bbs:byteCalculation(fileList.fileSize)}" />]
													</a>
												</span>
												<input type="button" onclick="doBbsFileRmvAx('<c:out value="${fileList.cbIdx}"/>','<c:out value="${fileList.bcIdx}"/>','<c:out value="${fileList.streFileNm}"/>');" class="btn btn-quickView" value="삭제"></button>
											</li>
										</ul>							
									</c:when>
									<c:when test="${fn:substring(fileList.orignlFileNm ,fn:indexOf(fileList.orignlFileNm,'.')+1,fn:length(fileList.orignlFileNm )) eq 'docx'}">
										<ul class="attached-files">
											<li>
												<span class="file-img">
													<a href="/common/board/Download.do?bcIdx=<c:out value="${fileList.bcIdx}"/>&amp;cbIdx=<c:out value="${fileList.cbIdx}"/>&amp;streFileNm=<c:out value="${fileList.streFileNm}"/>" class="file" title="파일 다운로드">
														<c:out value="${fileList.orignlFileNm }" /> [<c:out value="${bbs:byteCalculation(fileList.fileSize)}" />]
													</a>
												</span>
												<input type="button" onclick="doBbsFileRmvAx('<c:out value="${fileList.cbIdx}"/>','<c:out value="${fileList.bcIdx}"/>','<c:out value="${fileList.streFileNm}"/>');" class="btn btn-quickView" value="삭제"></button>
											</li>
										</ul>							
									</c:when>
									<c:when test="${fn:substring(fileList.orignlFileNm ,fn:indexOf(fileList.orignlFileNm,'.')+1,fn:length(fileList.orignlFileNm )) eq 'xlsx'}">
										<ul class="attached-files">
											<li>
												<span class="file-img">
													<a href="/common/board/Download.do?bcIdx=<c:out value="${fileList.bcIdx}"/>&amp;cbIdx=<c:out value="${fileList.cbIdx}"/>&amp;streFileNm=<c:out value="${fileList.streFileNm}"/>" class="file" title="파일 다운로드">
														<c:out value="${fileList.orignlFileNm }" /> [<c:out value="${bbs:byteCalculation(fileList.fileSize)}" />]
													</a>
												</span>
												<input type="button" onclick="doBbsFileRmvAx('<c:out value="${fileList.cbIdx}"/>','<c:out value="${fileList.bcIdx}"/>','<c:out value="${fileList.streFileNm}"/>');" class="btn btn-quickView" value="삭제"></button>
											</li>
										</ul>							
									</c:when>
									<c:when test="${fn:substring(fileList.orignlFileNm ,fn:indexOf(fileList.orignlFileNm,'.')+1,fn:length(fileList.orignlFileNm )) eq 'pptx'}">
									<ul class="attached-files">
										<li>
											<span class="file-img">
												<a href="/common/board/Download.do?bcIdx=<c:out value="${fileList.bcIdx}"/>&amp;cbIdx=<c:out value="${fileList.cbIdx}"/>&amp;streFileNm=<c:out value="${fileList.streFileNm}"/>" class="file" title="파일 다운로드">
													<c:out value="${fileList.orignlFileNm }" /> [<c:out value="${bbs:byteCalculation(fileList.fileSize)}" />]
												</a>
											</span>
											<input type="button" onclick="doBbsFileRmvAx('<c:out value="${fileList.cbIdx}"/>','<c:out value="${fileList.bcIdx}"/>','<c:out value="${fileList.streFileNm}"/>');" class="btn btn-quickView" value="삭제"></button>
										</li>
									</ul>							
									</c:when>
									<c:when test="${fn:substring(fileList.orignlFileNm ,fn:indexOf(fileList.orignlFileNm,'.')+1,fn:length(fileList.orignlFileNm )) eq 'txt'}">
									<ul class="attached-files">
										<li>
											<span class="file-img">
												<a href="/common/board/Download.do?bcIdx=<c:out value="${fileList.bcIdx}"/>&amp;cbIdx=<c:out value="${fileList.cbIdx}"/>&amp;streFileNm=<c:out value="${fileList.streFileNm}"/>" class="file" title="파일 다운로드">
													<c:out value="${fileList.orignlFileNm }" /> [<c:out value="${bbs:byteCalculation(fileList.fileSize)}" />]
												</a>
											</span>
											<input type="button" onclick="doBbsFileRmvAx('<c:out value="${fileList.cbIdx}"/>','<c:out value="${fileList.bcIdx}"/>','<c:out value="${fileList.streFileNm}"/>');" class="btn btn-quickView" value="삭제"></button>
										</li>
									</ul>							
									</c:when>
									<c:when test="${fn:substring(fileList.orignlFileNm ,fn:indexOf(fileList.orignlFileNm,'.')+1,fn:length(fileList.orignlFileNm )) eq 'jpg'}">
									<ul class="attached-files">
										<li>
											<span class="file-img">
												<a href="/common/board/Download.do?bcIdx=<c:out value="${fileList.bcIdx}"/>&amp;cbIdx=<c:out value="${fileList.cbIdx}"/>&amp;streFileNm=<c:out value="${fileList.streFileNm}"/>" class="file" title="파일 다운로드">
													<c:out value="${fileList.orignlFileNm }" /> [<c:out value="${bbs:byteCalculation(fileList.fileSize)}" />]
												</a>
											</span>
											<input type="button" onclick="doBbsFileRmvAx('<c:out value="${fileList.cbIdx}"/>','<c:out value="${fileList.bcIdx}"/>','<c:out value="${fileList.streFileNm}"/>');" class="btn btn-quickView" value="삭제"></button>
										</li>
									</ul>							
									</c:when>
									<c:when test="${fn:substring(fileList.orignlFileNm ,fn:indexOf(fileList.orignlFileNm,'.')+1,fn:length(fileList.orignlFileNm )) eq 'zip'}">
									<ul class="attached-files">
										<li>
											<span class="file-img">
												<a href="/common/board/Download.do?bcIdx=<c:out value="${fileList.bcIdx}"/>&amp;cbIdx=<c:out value="${fileList.cbIdx}"/>&amp;streFileNm=<c:out value="${fileList.streFileNm}"/>" class="file" title="파일 다운로드">
													<c:out value="${fileList.orignlFileNm }" /> [<c:out value="${bbs:byteCalculation(fileList.fileSize)}" />]
												</a>
											</span>
											<input type="button" onclick="doBbsFileRmvAx('<c:out value="${fileList.cbIdx}"/>','<c:out value="${fileList.bcIdx}"/>','<c:out value="${fileList.streFileNm}"/>');" class="btn btn-quickView" value="삭제"></button>
										</li>
									</ul>							
									</c:when>
								</c:choose>		
							</c:forEach>
							<%-- 파일 다운로드 & 확장자 구분으로 이미지 뿌리기 끝 --%>	
							</td>
						</c:when>								
	
						<c:when test="${labelPropGbnList.LABEL_PROP_GBN eq '16021300'}"><%//File %>
						<td class="fileupload-buttonbar">
					        <div class="fileupload-buttons">
					            <!-- The fileinput-button span is used to style the file input field as button -->							                          
					            <input type="file" name="files[]" multiple>				               
					            <button type="submit" class="start" id="uploadbtn" style="display:none">저장</button>            
					            <button type="button" class="delete" style="display:none">삭제</button>
					            <input type="checkbox" class="toggle" style="display:none">
					            <!-- The global file processing state -->
					            <span class="fileupload-process"></span>
					        </div>
					        <!-- The global progress state -->
					        <div class="fileupload-progress fade" style="display:none">
					            <!-- The global progress bar -->
					            <div class="progress" role="progressbar" aria-valuemin="0" aria-valuemax="100"></div>
					            <!-- The extended global progress state -->
					            <div class="progress-extended">&nbsp;</div>
					        </div>
				    		<br>
					    <!-- The table listing the files available for upload/download -->
					    <table role="presentation" id="filelist">
					    <th>파일명</th><th>용량</th><th>삭제</th>
					    <tbody class="files">
					    <tr class="template-upload fade" align="middle" id="drag" style="height:100px;">
					        <td colspan="3">
					            	<strong>파일을 업로드하려면 여기에 드롭해 주세요.</strong>
					        </td>        
					    </tr>
					    </tbody></table>										
					<script id="template-upload" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
	
    <tr class="template-upload fade" valign="top">        
        <td>
            <p class="name">{%=file.name%}</p>
            <strong class="error"></strong>
        </td>
        <td>
            <p class="size">Processing...</p>
            
        </td>
        <td style="text-align:center;" >
            {% if (!i && !o.options.autoUpload) { %}
                <button class="start" disabled style="display:none">Start</button>
            {% } %}
            {% if (!i) { %}
                <button class="btn-card-more cancel">취소</button>
            {% } %}
        </td>
    </tr>
	
{% } %}
	</script>
											<!-- The template to display files available for download -->
											<script id="template-download" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
    <tr class="template-download fade" valign="top">        
        <td>
            <p class="name">
                <a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" {%=file.thumbnailUrl?'data-gallery':''%}>{%=file.name%}</a>
            </p>
            
        </td>
        <td>
            <span class="size">{%=file.size%}</span>
        </td>
        <td>
            <button class="btn-card-more delete" data-type="text" data-url="/site/<c:out value='${strDomain}' />/ex/bbs/File_RmvAx.do?cbIdx=<c:out value='${bbsFVo.cbIdx}'/>&bcIdx=<c:out value='${bbsFVo.bcIdx}'/>&fileNo={%=file.fileno%}"{% if (file.deleteWithCredentials) { %} data-xhr-fields='{"withCredentials":true}'{% } %}>삭제</button>
            <input type="hidden" name="delete" value="1" class="toggle" align="absmiddle">
        </td>
    </tr>
{% } %}
	</script><span class="fileupload-notice">※파일1개당 최대<c:out value="${bbsFVo.fileMaxSize}"/>MB까지만 가능합니다</span>
										</td>
									</c:when>
							</c:choose>
							</tr>	
							</c:otherwise>
						</c:choose>	
					</c:forEach>
					</tbody>
				</table>
			</form:form>	
		</div>
	</article>
<!-- // Board center -->	

<!-- // Board foot -->		
	<div class="btnArea">
		<a href="javascript:doBbsFReg();" class="btn_l">등록</a>
		<a href="javascript:history.back();" class="btn_l blue">취소</a>
	</div>
<!-- // Board foot -->
							