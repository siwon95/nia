package egovframework.injeinc.common.docuzen;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import egovframework.cmm.CmmLogCtr;
import egovframework.injeinc.common.util.EgovStringUtil;

@Controller
public class DocuzenCtr extends CmmLogCtr {

	//리스트
	@RequestMapping(value= "/common/docuzen/preImageFromDoc.do")
	public String preImageFromDoc (HttpServletRequest request, ModelMap model) throws Exception {
		
		/*String filePath = EgovStringUtil.isNullToString(request.getParameter("filePath"));
		String filename = EgovStringUtil.isNullToString(request.getParameter("filename"));
		
		DocumentBuilderFactory docBuilderFactory = null;
		DocumentBuilder docBuilder = null;
		Document doc = null;
		
		docBuilderFactory = DocumentBuilderFactory.newInstance();
		docBuilder = docBuilderFactory.newDocumentBuilder();
		
		String rssFileName = "http://211.241.36.51:8080/ctrl/convert.aspx?filePath=http://"+request.getServerName()+filePath+"&filename="+filename+"&type=jpg&dpi=96&rtntype=w";
	
		//System.out.println("rssFileName===============>"+rssFileName);
		
		doc = docBuilder.parse(rssFileName);
	
		Element elem = doc.getDocumentElement() ;
		NodeList tagStatus = elem.getElementsByTagName("status");
		NodeList tagResource = elem.getElementsByTagName("resource");
		
		Element status = (Element)tagStatus.item(0);
		String strStatus = status.getFirstChild().getNodeValue();
		
		String strStatusMsg = "";
		
		if(status.getElementsByTagName("msg").item(0) != null) {
			strStatusMsg = status.getElementsByTagName("msg").item(0).getFirstChild().getNodeValue();
		}
		if(model != null){
			model.put("strStatus", strStatus);
			model.put("strStatusMsg", strStatusMsg);
		}
		List<String> fileNameList = new ArrayList<String>();
		
		if(strStatus.equals("0")) {
			for(int i = 0; i < tagResource.getLength(); i++) {
				fileNameList.add(tagResource.item(i).getFirstChild().getNodeValue());
			}
			if(model != null){
				model.put("fileNameList", fileNameList);
			}
		}
		
		return "injeinc/common/docuzen/preImageFromDoc";
	}*/
		return "";
	}
}


