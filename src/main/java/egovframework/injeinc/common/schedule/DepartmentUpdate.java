package egovframework.injeinc.common.schedule;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import javax.annotation.Resource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
 


import org.xml.sax.InputSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import egovframework.cmm.CmmLogCtr;
import egovframework.injeinc.boffice.group.dept.service.GroupDeptSvc;
import egovframework.injeinc.boffice.group.dept.vo.GroupDeptVo;

import egovframework.injeinc.boffice.group.emp.service.EmpSvc;
import egovframework.injeinc.boffice.group.emp.vo.EmpVo;

@Service("DepartmentUpdate")
public class DepartmentUpdate extends CmmLogCtr {
	
	@Autowired
	private GroupDeptSvc groupDeptSvc;
	
	@Autowired
	private EmpSvc empSvc;
	
	@SuppressWarnings("unchecked")
	public void departmentUpdate() throws Exception {
		String xmlUrl = "http://localhost:8080/homepage/dept.xml";
		URL url = null;
		InputStream is = null;
		
		StringBuffer urlBuffer = null;
		urlBuffer = new StringBuffer();
		urlBuffer.append(xmlUrl);
		url = new URL(urlBuffer.toString());
		is = url.openStream();

		GroupDeptVo deptVo = new GroupDeptVo();

		 try {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(is);
				
				XPath  xpath = XPathFactory.newInstance().newXPath();
				doc.getDocumentElement().normalize();
				
				NodeList nList1 = (NodeList)xpath.evaluate("//root/item",doc, XPathConstants.NODESET);
				System.out.println("부서정보갱신 시작");
				groupDeptSvc.deleteEzDepartmentTemp(deptVo);
				for (int i = 0; i < nList1.getLength(); i++) { 
					Node nNode = nList1.item(i);
					Element eElement = (Element) nNode;
					deptVo.setTeamId(eElement.getElementsByTagName("team_id").item(0).getTextContent());
					deptVo.setGroupId(eElement.getElementsByTagName("group_id").item(0).getTextContent());
					deptVo.setTeamName(eElement.getElementsByTagName("team_name").item(0).getTextContent());
					deptVo.setRegDt(eElement.getElementsByTagName("reg_date").item(0).getTextContent());
					deptVo.setSortNo(eElement.getElementsByTagName("sort_no").item(0).getTextContent());
					
					groupDeptSvc.insertEzDepartmentTemp(deptVo);
				}
				
				groupDeptSvc.updateCdUseEzDepartment(deptVo);
				groupDeptSvc.insertEzDepartment(deptVo);
				groupDeptSvc.updateEzDepartment(deptVo);
				
				System.out.println("부서정보갱신 끝");
			
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
		 
		 	xmlUrl = "http://localhost:8080/homepage/employee.xml";
			url = null;
			is = null;
			
			urlBuffer = null;
			urlBuffer = new StringBuffer();
			urlBuffer.append(xmlUrl);
			url = new URL(urlBuffer.toString());
			is = url.openStream();

			EmpVo empVo = new EmpVo();

			 try {
					DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
					Document doc = dBuilder.parse(is);
					
					XPath  xpath = XPathFactory.newInstance().newXPath();
					doc.getDocumentElement().normalize();
					
					NodeList nList1 = (NodeList)xpath.evaluate("//root/item",doc, XPathConstants.NODESET);
					empSvc.deleteGkEmployee(empVo);
					for (int i = 0; i < nList1.getLength(); i++) { 
						Node nNode = nList1.item(i);
						Element eElement = (Element) nNode;
						
						empVo.setMemSsn(eElement.getElementsByTagName("mem_ssn").item(0).getTextContent());
						empVo.setMemName(eElement.getElementsByTagName("mem_name").item(0).getTextContent());
						empVo.setMemTask(eElement.getElementsByTagName("mem_task").item(0).getTextContent());
						empVo.setMemHp(eElement.getElementsByTagName("mem_hp").item(0).getTextContent());
						empVo.setMemTel(eElement.getElementsByTagName("mem_tel").item(0).getTextContent());
						empVo.setMemEmail(eElement.getElementsByTagName("mem_email").item(0).getTextContent());
						empVo.setId(eElement.getElementsByTagName("id").item(0).getTextContent());
						empVo.setPosition(eElement.getElementsByTagName("position").item(0).getTextContent());
						empVo.setTeamId(eElement.getElementsByTagName("team_id").item(0).getTextContent());
						empVo.setTeamName(eElement.getElementsByTagName("team_name").item(0).getTextContent());
						empVo.setTeamOrder(eElement.getElementsByTagName("team_order").item(0).getTextContent());
						empVo.setSortNum(eElement.getElementsByTagName("sort_num").item(0).getTextContent());
						empVo.setPhotoPath(eElement.getElementsByTagName("photo_path").item(0).getTextContent());
						empVo.setMemExtNum(eElement.getElementsByTagName("mem_ext_num").item(0).getTextContent());
						
						empSvc.insertGkEmployee(empVo);
					}
					
					empSvc.insertRenewEmp(empVo);
					empSvc.updateRenewEmp(empVo);
				
			    } catch (Exception e) {
			    	e.printStackTrace();
			    }
	}
}
