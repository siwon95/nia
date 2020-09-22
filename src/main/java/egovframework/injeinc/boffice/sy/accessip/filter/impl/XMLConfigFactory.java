package egovframework.injeinc.boffice.sy.accessip.filter.impl;


import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import egovframework.cmm.EgovWebUtil;
import egovframework.cmm.service.EgovProperties;
import egovframework.injeinc.boffice.sy.accessip.filter.core.Config;

public class XMLConfigFactory extends ConfigFactory {
    @Override
    public Config create(String value){
    	
    	String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
    	
    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    	DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document doc = null;
		try {
			doc = builder.parse(EgovWebUtil.filePathBlackList(rootPath)+ value);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		NodeList nodeList = doc.getElementsByTagName("allow-ip");
		
		
		Config config = new Config();
		config.setAllowFirst(true);
		config.setDefaultAllow(false);
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			Node textNode = node.getChildNodes().item(0);
			
			config.allow(textNode.getNodeValue());
		}
    	
//		config.allow("127.0.0.1");
//		config.deny("1.2.3.5");
		return config;
		
    }

    @Override
    public boolean isReloadSupported() {
        return true;
    }
}
