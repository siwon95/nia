package egovframework.injeinc.boffice.title.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import egovframework.cmm.ComDefaultVO;

public class TitleVo{
	
	
	
	private String id;
	private String parent;
	private String text;
	private String position;
	private String a_attr;
	//추가 
	private String nodeId;
	private String parentId;
	
	private String mgrUrl;
	
	private String adminUrl;
	
	private String useCnt;
	
	private String useYn;
	
	private  JSONObject nodeParam;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public JSONObject getNodeParam() {
		return nodeParam;
	}
	public void setNodeParam(JSONObject nodeParam) {
		this.nodeParam = nodeParam;
	}
	public String getMgrUrl() {
		return mgrUrl;
	}
	public void setMgrUrl(String mgrUrl) {
		this.mgrUrl = mgrUrl;
	}
	public String getAdminUrl() {
		return adminUrl;
	}
	public void setAdminUrl(String adminUrl) {
		this.adminUrl = adminUrl;
	}
	public String getUseCnt() {
		return useCnt;
	}
	public void setUseCnt(String useCnt) {
		this.useCnt = useCnt;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getA_attr() {
		return a_attr;
	}
	public void setA_attr(String a_attr) {
		this.a_attr = a_attr;
	}

	

	
	
}