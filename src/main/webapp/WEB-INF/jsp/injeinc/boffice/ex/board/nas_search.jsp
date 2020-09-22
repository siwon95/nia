<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="egovframework.injeinc.common.util.SmbShare"%>
<%@page import="java.io.File"%>
<%@page import="egovframework.cmm.service.EgovProperties"%>
<%
String rMode = request.getParameter("mode")+"";
String rType = request.getParameter("type")+"";
String nasDir = EgovProperties.getProperty("Globals.Nas");

if(rMode.equals("json")){
// json 요청이 들어올경우 ################################################################################
	String path = request.getParameter("path")+"";
	String parentId = request.getParameter("id")+"";
	SmbShare sm = new SmbShare();
	File[] sf = sm.nasFileList(path, "folder");
	String jsonText = "";
	//jsonText += sm.getNasDir(parentId,jsonText,sf,"sub");
	out.println(jsonText);

}else if(rMode.equals("jsonFile")){
	// json 요청이 들어올경우 ################################################################################
		String path = request.getParameter("path")+"";
		//path = "D:\\nas\\테스트";
		String parentId = request.getParameter("id")+"";
		//System.out.println("path:"+path);
		//out.println("path:"+path);
		SmbShare sm = new SmbShare();
		File[] sf = sm.nasFileList(path, "file");
		String jsonFile = "";
		jsonFile += "[";
		int fileCount = 0;
		for(int i=0;i<sf.length;i++){
			if((sf[i].isDirectory()) == false){
				if(fileCount!=0){
					jsonFile += ",\n";
				}
				int tempPathAt = (sf[i].getPath().toString()).indexOf("@");
				String tempPath = sf[i].getPath().toString().substring(tempPathAt+1);
				//tempPath = tempPath.replaceAll(nasDir,"");
				String tempValue="";
				

				jsonFile +="{";
				jsonFile += "\"id\":\""+parentId+"_"+fileCount+"\"";
				jsonFile += ",\"parent\":\""+parentId+"\"";
				jsonFile += ",\"text\":\""+sf[i].getName().replaceAll("/","")+"\"";
				jsonFile += ",\"hPath\":\""+tempPath+"\"";
				jsonFile += ",\"oPath\":\""+java.net.URLEncoder.encode(tempPath,"utf-8")+"\"";
				jsonFile += "}";
				fileCount++;
			}
		}
		jsonFile += "]";
		out.println(jsonFile);
}else{
// 사용자페이지 ################################################################################
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>양천구청NAS</title>
<link rel="stylesheet" type="text/css" href="/css/common/common.css" />
<link rel="stylesheet" type="text/css" href="/css/new_mnt.css" />
<link href="/js/vakata-jstree-5bece58/dist/themes/default/style.min.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/jquery/jquery.1.10.2.js"></script>
<script type="text/javascript" src="/js/vakata-jstree-5bece58/dist/jstree.min.js"></script>
<script>

	var arrayCollection = <% 
	String path = "/";
	String parentId = request.getParameter("id")+"";
	SmbShare sm = new SmbShare();
	File[] sf = sm.nasFileList(nasDir+path, "folder");
//	System.out.println(nasDir);
	String jsonText="";
	jsonText += sm.getNasDir("#",jsonText,sf,"yes");

	out.println(jsonText);
	%>;


	var oldId = "#";
	$(function() {
		$('#jstree').jstree({
			'core': {
			   'data': arrayCollection
			}
		}).bind("select_node.jstree", function (event, data) {
				fJsonFile(data.node.id,data.node.a_attr.href);
		}).bind("after_open.jstree", function (event, data) {
			var tree = $('#jstree').jstree(true);
			if(oldId.indexOf("#"+data.node.id+"#")==-1){
				oldId += data.node.id+"#";
				fJson(data.node.id,data.node.a_attr.href,true);
			}
		});
	});
         
	function fJson(inId,inPath,inChk){
		var request = new XMLHttpRequest();
		var rUrl = "/boffice_noDeco/ex/board/nasSearch.do?mode=json&id="+inId+"&path="+inPath;
		request.open("get",rUrl);
		request.send();
		request.onreadystatechange = function(e){
			if(request.readyState==4){
				if(request.status==200){
					var data = request.responseText.trim();
					var arrJson = eval('('+data+')');
					for(s=0;s<arrJson.length;s++){
						arrayCollection.push(arrJson[s]);
					}
					$('#jstree').jstree(true).settings.core.data = arrayCollection;
					$('#jstree').jstree(true).refresh();
				
				}else{
					return;
				}
			}            		
		}
		return true;
	}
	function fJsonFile(inId,inPath){
		var request = new XMLHttpRequest();
		$('#jslist').html("목록을 불러오는 중입니다. 잠시만 기다려 주십시요!");
		
		var rUrl = "/boffice_noDeco/ex/board/nasSearch.do?mode=jsonFile&id="+inId+"&path="+inPath;
		$("#idurl").val("http://2016.yangcheon.go.kr"+rUrl);
		request.open("get",rUrl);
		request.send();
		request.onreadystatechange = function(e){
			if(request.readyState==4){
				if(request.status==200){
					var data2 = request.responseText.trim();
					var arrJson = eval('('+data2+')');
					var fileList = "";
					for(s=0;s<arrJson.length;s++){
						//alert(arrJson[s].oPath);
						var fileInfo = arrJson[s].oPath.split("%2F"); //리눅스
						var fileInfo2 = arrJson[s].hPath.split("/"); //리눅스
						//var fileInfo = arrJson[s].oPath.split("%5C"); //윈도우
						//var fileInfo = arrJson[s].oPath;
						var fileInfoPath="";
						var fileInfoName="";
						
						for(k=0;k<fileInfo.length;k++){
							if(k<fileInfo.length-1){
								fileInfoPath += fileInfo[k] +"%2F";
								//fileInfoPath += fileInfo[k] +"%2F";
							}else{
								fileInfoName = fileInfo[k];
							}
						}
						var fileInfoPath2="";
						var fileInfoName2="";
						
						for(k=0;k<fileInfo2.length;k++){
							if(k<fileInfo2.length-1){
								fileInfoPath2 += fileInfo2[k] +"/";
								//fileInfoPath += fileInfo[k] +"%2F";
							}else{
								fileInfoName2 = fileInfo2[k];
							}
						}
						var orgfileInfoName = arrJson[s].text;
						//alert(fileInfoPath+"/"+fileInfoName);

					
						if(arrJson[s].text.toLowerCase().indexOf(".jpg")==-1){
							fileList += "<span>";
							fileList += "<a href=\"/common/files/ThumbnailDownload.do?";
							fileList += "mode=nas";
							fileList += "&amp;thumbnailyn=y";
							fileList += "&amp;filePath="+ fileInfoPath;
							fileList += "&amp;fileName="+ fileInfoName;
							fileList += "\">";
							fileList += arrJson[s].text;
							fileList += "</a>";
							fileList += "</span>";
						}else{
							fileList += "<span style=\"float:left\"><table width=\"170\"><tr><td>";
							fileList += "<a href=\"/common/files/ThumbnailDownload.do?";
							fileList += "mode=nas";
							fileList += "&amp;thumbnailyn=n";
							fileList += "&amp;filePath="+ fileInfoPath;
							fileList += "&amp;fileName="+ fileInfoName;
							fileList += "\">";
							fileList += "<img src=\"/common/files/ThumbnailDownload.do?";
							fileList += "mode=nas";
							fileList += "&amp;filePath="+ fileInfoPath;
							fileList += "&amp;fileName="+ fileInfoName;
							fileList += "\" width=\"100\">";
							fileList += "</a>";
							fileList += "</td></tr><tr><td>";
							fileList += arrJson[s].text;
							fileList += "</td></tr><tr><td>";
							fileList += "<input type=\"checkbox\" name=\"filePath_"+s+"\" id=\"filePath_"+s+"\" value=\""+fileInfoPath2+fileInfoName2+"\">선택";
							fileList += "<input type=\"hidden\" name=\"fileName_"+s+"\" id=\"fileName_"+s+"\" value=\""+arrJson[s].text+"\">";
							fileList += "</td></tr><tr><td height=\"20\">";
							
							fileList += "</td></tr></table>";
							fileList += "</span>";
						}
					}
					$('#jslist').html(fileList);
					if(fileList==""){
						$('#jslist').html("선택하신 폴더엔 파일이 없습니다.");
					}
					
					$('#pathCount').val(arrJson.length);
				
				}else{
					return;
				}
			}            		
		}
		return true;
	}
	
	function fileSubmit(){
		var fileText = "";
		var nasFileCount = opener.$('#nasFileCount').val();
		for(i=0;i<$('#pathCount').val();i++){
			//alert($('#filePath_'+i).prop('checked'));
			if($('#filePath_'+i).prop('checked') == true){
				nasFileCount++;
				fileText += "<span id=\"idFileName"+nasFileCount+"\"><div style=\"height:30px\">";
				fileText += $('#fileName_'+i).val();
				fileText += "<input type=\"hidden\" id=\"nasFilePath"+nasFileCount+"\" name=\"nasFilePath\" value=\""+$('#filePath_'+i).val()+"\">";
				fileText += " <input type=\"button\" value=\"삭제\" class=\"btn\" onclick=\"nasFileDelete("+nasFileCount+")\">"
				fileText += "</div></span>"
			}
		}
		
		if(fileText==""){
			alert("파일을 선택해 주십시요!");
		}else{
			opener.$('#nasFileTitle').html(opener.$('#nasFileTitle').html()+fileText);
			opener.$('#nasFileCount').val(nasFileCount);
		}
		window.close();
	}
</script>
        
        <style>
        	html, body {
        		height: 100%; overflow:hidden;
        	}
        </style>
	
</head> 
<body style="height:90%">
	<input type="hidden" name="pathCount" id="pathCount" value="0"/>
<br/>
<div style="overflow:auto;float:left;width:20%;font-size:16px;font-weight:bold">양천구청NAS</div>
<div style="float:left;width:80%;text-align:right;">
	<span><input type="button" value="선택파일적용" class="btn" onclick="fileSubmit()">&nbsp;</span>
</div>
<br/><br/>
<div id="jstree" style="overflow:auto;float:left;width:35%;height:90%"></div>
<div id="jslist" style="float:right;width:65%;height:90%">
	폴더를 선택해 주십시요.
</div>
<input type="hidden" id="idurl" size="100"/><br/>
<!-- <a href="http://localhost:8080/common/files/ThumbnailDownload.do?mode=smb&amp;thumbnailyn=n&amp;filePath=%2F%ED%96%89%EC%82%AC+%EC%99%B8+%EC%9E%90%EB%A3%8C%EC%82%AC%EC%A7%84%2F2012%E1%84%82%E1%85%A7%E1%86%AB+%E1%84%92%E1%85%A9%E1%86%BC%E1%84%87%E1%85%A9%E1%84%8B%E1%85%AD%E1%86%BC+%E1%84%8C%E1%85%A1%E1%84%85%E1%85%AD%E1%84%89%E1%85%A1%E1%84%8C%E1%85%B5%E1%86%AB%2F12%E1%84%8B%E1%85%AF%E1%86%AF%2F18%E1%84%8B%E1%85%B5%E1%86%AF+%E1%84%91%E1%85%A1%E1%84%85%E1%85%B5%E1%84%80%E1%85%A9%E1%86%BC%E1%84%8B%E1%85%AF%E1%86%AB+%E1%84%86%E1%85%B5%E1%84%83%E1%85%B5%E1%84%8B%E1%85%A5%E1%84%91%E1%85%A1%E1%84%8F%E1%85%B3%2F&fileName=_DSC7998.JPG"> -->
<!-- <img src="http://localhost:8080/common/files/ThumbnailDownload.do?mode=smb&amp;filePath=%2F%ED%96%89%EC%82%AC+%EC%99%B8+%EC%9E%90%EB%A3%8C%EC%82%AC%EC%A7%84%2F2012%E1%84%82%E1%85%A7%E1%86%AB+%E1%84%92%E1%85%A9%E1%86%BC%E1%84%87%E1%85%A9%E1%84%8B%E1%85%AD%E1%86%BC+%E1%84%8C%E1%85%A1%E1%84%85%E1%85%AD%E1%84%89%E1%85%A1%E1%84%8C%E1%85%B5%E1%86%AB%2F12%E1%84%8B%E1%85%AF%E1%86%AF%2F18%E1%84%8B%E1%85%B5%E1%86%AF+%E1%84%91%E1%85%A1%E1%84%85%E1%85%B5%E1%84%80%E1%85%A9%E1%86%BC%E1%84%8B%E1%85%AF%E1%86%AB+%E1%84%86%E1%85%B5%E1%84%83%E1%85%B5%E1%84%8B%E1%85%A5%E1%84%91%E1%85%A1%E1%84%8F%E1%85%B3%2F&fileName=_DSC7998.JPG"/> -->
</a>
<br/>
</body>
</html>
<%}%>