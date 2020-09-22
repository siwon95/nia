package egovframework.injeinc.boffice.cn.content.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.EgovResourceCloseHelper;
import egovframework.cmm.service.EgovProperties;
import egovframework.injeinc.boffice.cn.content.service.ContentSvc;
import egovframework.injeinc.boffice.cn.content.vo.ContentVo;
//파일 첨부를 위해 추가 import 구문

@Controller
public class ContentCtr extends CmmLogCtr {
	private static final Logger LOGGER = LoggerFactory.getLogger(ContentCtr.class);
	@Resource(name = "ContentSvc")
	private ContentSvc contentSvc;
	
	@RequestMapping(value= "/cn/content/Content_Reg.do")
	public void contentRegist(
			HttpServletRequest request,
			ModelMap model) throws Exception {
		
			FileReader fr = null;
			
			BufferedReader br = null;
			
			Date d = null;
			
			try{
				
				// "ReadFile.txt" 파일을 읽는 FileReader 객체 생성
				// BufferedReader 객체 생성
				String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
				List<ContentVo> contentList = contentSvc.selectListContent();
				
				//System.out.println("contentList : " + contentList.size());
				
					for(int i=0;i<contentList.size();i++){
						fr = new FileReader(rootPath+contentList.get(i).getUserMenuUrl());
						br = new BufferedReader(fr);
						
						// FileWriter로 파일 "CopyFile.txt"에 출력한다. 기존 파일에 덮어쓴다.
						// BufferedWriter 객체 생성
						
						String s = "";
						String c = "";
						
						// 파일 복사 시작 시간
						int a = 0;
						// ReadFile.txt 에서 한줄씩 읽어서 BufferedRaeder에 저장한다.
					      while((s=br.readLine()) != null){
					    	  if(a != 0){
					       //System.out.println(s);  //엔터키값이 있는 경우에도 읽어들이는데, 엔터키값은 2개의 코드로 이루어져 있어 두번더 반복된다.(화면에는 출력되지 않음)
					    	  c = c+s+"\n";
					    	  }
					    		a = a+1;	 
					      }
					      ContentVo contentVo = new ContentVo();
					      contentVo.setContentBody(c);
					      contentVo.setMenuCode(contentList.get(i).getMenuCode());
					      contentVo.setSiteCd(contentList.get(i).getSiteCd());
					      
					      contentSvc.registContent(contentVo);
					      
					      fr.close();
					      br.close();
					}
				
			    }catch(IOException ioe){
			    	LOGGER.debug("IGNORED: " + ioe.getMessage());
			    	EgovResourceCloseHelper.close(br);
					EgovResourceCloseHelper.close(fr);
				}finally{
					EgovResourceCloseHelper.close(br);
					EgovResourceCloseHelper.close(fr);
				}

	}
	
}
