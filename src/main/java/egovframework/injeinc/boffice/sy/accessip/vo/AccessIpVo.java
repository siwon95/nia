package egovframework.injeinc.boffice.sy.accessip.vo;

import egovframework.cmm.ComDefaultVO;

public class AccessIpVo extends ComDefaultVO {
	
	private String ai_idx;
	private String s_ip;
	private String e_ip;
	private String note;
	private String reg_id;
	private String reg_dt;
	private String mod_id;
	private String mod_dt;
	
	private String actionkey		= "";
	
	public String getAi_idx() {
		return ai_idx;
	}
	public void setAi_idx(String ai_idx) {
		this.ai_idx = ai_idx;
	}
	public String getS_ip() {
		return s_ip;
	}
	public void setS_ip(String s_ip) {
		this.s_ip = s_ip;
	}
	public String getE_ip() {
		return e_ip;
	}
	public void setE_ip(String e_ip) {
		this.e_ip = e_ip;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getReg_id() {
		return reg_id;
	}
	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}
	public String getReg_dt() {
		return reg_dt;
	}
	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt;
	}
	public String getMod_id() {
		return mod_id;
	}
	public void setMod_id(String mod_id) {
		this.mod_id = mod_id;
	}
	public String getMod_dt() {
		return mod_dt;
	}
	public void setMod_dt(String mod_dt) {
		this.mod_dt = mod_dt;
	}
	public String getActionkey() {
		return actionkey;
	}
	public void setActionkey(String actionkey) {
		this.actionkey = actionkey;
	}
	
}