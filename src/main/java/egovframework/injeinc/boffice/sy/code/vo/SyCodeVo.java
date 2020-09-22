package egovframework.injeinc.boffice.sy.code.vo;

import org.json.simple.JSONObject;

public class SyCodeVo{
	
	private String id;
	private String parent;
	private String text;
	private String position;
	//추가 
	private String nodeId;
	private String parentId;
	private String useYn;
	private String idChk;
	private String codeValue;
	private String oldCode;
	
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
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getIdChk() {
		return idChk;
	}
	public void setIdChk(String idChk) {
		this.idChk = idChk;
	}
	public String getCodeValue() {
		return codeValue;
	}
	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}
	public String getOldCode() {
		return oldCode;
	}
	public void setOldCode(String oldCode) {
		this.oldCode = oldCode;
	}
	
	
}