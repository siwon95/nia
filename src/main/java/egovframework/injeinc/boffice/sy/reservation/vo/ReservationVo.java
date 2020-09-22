package egovframework.injeinc.boffice.sy.reservation.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import egovframework.cmm.ComDefaultVO;

public class ReservationVo{

	private String id;
	 
	private String nodeId;

	private String parentId;
	
	private String parent;
	 	
	private String text;
	 
	private String mgrUrl;
	
	private String usrUrl;
	 
	private String useYn;
	
	private String position;
	
	private String idChk;
	
	
	private  JSONObject nodeParam;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
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
	
	public String getMgrUrl() {
		return mgrUrl;
	}

	public void setMgrUrl(String mgrUrl) {
		this.mgrUrl = mgrUrl;
	}

	public String getUsrUrl() {
		return usrUrl;
	}

	public void setUsrUrl(String usrUrl) {
		this.usrUrl = usrUrl;
	}

	public String getUseYn() {
		return useYn;
	}
	
	public void setUseYn(String useYn) {
		this.useYn = useYn;
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

	public String getIdChk() {
		return idChk;
	}

	public void setIdChk(String idChk) {
		this.idChk = idChk;
	}
	
	
}