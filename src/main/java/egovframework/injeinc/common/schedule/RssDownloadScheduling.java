package egovframework.injeinc.common.schedule;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.EgovWebUtil;
import egovframework.cmm.service.EgovProperties;
import egovframework.injeinc.common.util.DateUtil;

@Service("rssDownloadScheduling")
public class RssDownloadScheduling extends CmmLogCtr {
	
	public void RssDownload() throws Exception {
		
		try {
			String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
			
			DocumentBuilderFactory docbuildfactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docbuilder = docbuildfactory.newDocumentBuilder();
			Document doc = docbuilder.parse("http://blog.rss.naver.com/inje.xml");
			
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();

			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(EgovWebUtil.filePathBlackList(rootPath)+"/upload/xml/inje.xml"));

			transformer.transform(source, result);
			
			debugLog("["+DateUtil.getCurrentDatetime()+"] Blog rss download success ");
			
		}catch (SAXParseException err) {
			debugLog("xml parsing Error , 줄" + err.getLineNumber() + ", uri" + err.getSystemId());
			debugLog("xml parsing ErrorMsg" + err.getMessage());
		}catch (SAXException e) {
			Exception x = e.getException();
			(x == null ? e : x).printStackTrace();
		}catch (Throwable s) {
			s.printStackTrace();
		}
	}

}
