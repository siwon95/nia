package egovframework.injeinc.common.util;

import jcifs.UniAddress;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbSession;
import jcifs.smb.SmbFile;
import java.util.List;
import java.util.ArrayList;

import java.io.File;

public class SmbShare {
	
	public void login(String address, String username, String password) throws Exception {
		SmbSession.logon(UniAddress.getByName(address), new NtlmPasswordAuthentication(address, username, password));
	}
	
	public SmbFile[] smbFileList(String address, String username, String password, String mode) throws Exception {
		String url = "smb://"+username+":"+password+"@"+address+"";
	    SmbFile file = new SmbFile(url);
	    SmbFile[] fArr = file.listFiles();
	    
	    /*
        for(int a = 0; a < fArr.length; a++) {
        	System.out.println("파일/폴더이름:"+fArr[a].getName());
        	System.out.println("속성:"+fArr[a].getAttributes());
        }
        */
	    return fArr;
	}
	
	public String getDir(String nasIp,String parentId, String jsonText, SmbFile[] sf, String inChk) throws Exception {
		int jsonCount = 0;
		if(parentId.equals("#")|| inChk.equals("sub")){
			jsonText+="[";
		}
		for(int i=0;i<sf.length;i++){
			if(sf[i].getName().indexOf("IPC$")==-1
					&& sf[i].getName().indexOf("home")==-1
					&& sf[i].getName().indexOf("USBDisk1")==-1
					&& sf[i].getName().indexOf("Public")==-1
					&& sf[i].getName().indexOf("Recycle")==-1
					&& sf[i].isDirectory()==true
			){
				
				int tempPathAt = (sf[i].getPath().toString()).indexOf("@");
				String tempPath = sf[i].getPath().toString().substring(tempPathAt+1);
				tempPath = tempPath.replaceAll(nasIp,"");
				String tempValue="";
				
				String strId = "";
				if(parentId.equals("#")){
					strId = jsonCount+"";
				}else{
					strId = parentId+"_"+jsonCount;
				}
				
				if(inChk.equals("yes") || inChk.equals("no")){
					if(jsonText.length()>1){
						jsonText += ",\n";
					}
					jsonText += "{";
					jsonText += "\"id\":\""+strId+"\"";
					jsonText += ",\"parent\":\""+parentId+"\"";
					jsonText += ",\"text\":\""+sf[i].getName().replaceAll("/","")+"\"";
					jsonText += ",\"a_attr\":{\"href\":\""+java.net.URLEncoder.encode(tempPath,"utf-8")+"\"}";
					jsonText += "}";
				}
				jsonCount++;
				
				if(inChk.equals("yes") || inChk.equals("sub")){
					jsonText = getDir(nasIp,strId,jsonText,sf[i].listFiles(),"no");
				}
			}
		}
		if(parentId.equals("#")|| inChk.equals("sub")){
			jsonText+="]";
		}
		return jsonText;
	}
	
	public File[] nasFileList(String address, String mode) throws Exception {
		String url = address;
		File file = new File(url);
		File[] fArr = file.listFiles();
	    
	    /*
        for(int a = 0; a < fArr.length; a++) {
        	System.out.println("파일/폴더이름:"+fArr[a].getName());
        	System.out.println("속성:"+fArr[a].getAttributes());
        }
        */
	    return fArr;
	}
	
	
	public String getNasDir(String parentId, String jsonText, File[] sf, String inChk) throws Exception {
		int jsonCount = 0;
		if(parentId.equals("#")|| inChk.equals("sub")){
			jsonText+="[";
		}
		for(int i=0;i<sf.length;i++){
			if(sf[i].getName().indexOf("IPC$")==-1
					&& sf[i].getName().indexOf("home")==-1
					&& sf[i].getName().indexOf("USBDisk1")==-1
					&& sf[i].getName().indexOf("Public")==-1
					&& sf[i].getName().indexOf("Recycle")==-1
					&& sf[i].isDirectory()==true
			){
				
				int tempPathAt = (sf[i].getPath().toString()).indexOf("@");
				String tempPath = sf[i].getPath().toString().substring(tempPathAt+1);
				String tempValue="";
				
				String strId = "";
				if(parentId.equals("#")){
					strId = jsonCount+"";
				}else{
					strId = parentId+"_"+jsonCount;
				}
				
				if(inChk.equals("yes") || inChk.equals("no")){
					if(jsonText.length()>1){
						jsonText += ",\n";
					}
					jsonText += "{";
					jsonText += "\"id\":\""+strId+"\"";
					jsonText += ",\"parent\":\""+parentId+"\"";
					jsonText += ",\"text\":\""+sf[i].getName().replaceAll("/","")+"\"";
					jsonText += ",\"a_attr\":{\"href\":\""+java.net.URLEncoder.encode(tempPath,"utf-8")+"\"}";
					jsonText += "}";
				}
				jsonCount++;
				
				if(inChk.equals("yes") || inChk.equals("sub")){
					jsonText = getNasDir(strId,jsonText,sf[i].listFiles(),"no");
				}
			}
		}
		if(parentId.equals("#")|| inChk.equals("sub")){
			jsonText+="]";
		}
		return jsonText;
	}
	
	

}