package egovframework.injeinc.foffice.ex.myscrap.vo;

import egovframework.cmm.ComDefaultVO;


@SuppressWarnings("serial")
public class MyscrapVO extends ComDefaultVO{
	
	private String scrap_seq_n;
	private int bbs_cd_n	= 0;
	private int bbs_seq_n	= 0;
	private String user_id_v;
	private String scrap_title_v;
	private String scrap_loc_v;
	private String scrap_dt_d;
	
	public String getScrap_seq_n() {
		return scrap_seq_n;
	}
	public void setScrap_seq_n(String scrap_seq_n) {
		this.scrap_seq_n = scrap_seq_n;
	}
	public int getBbs_cd_n() {
		return bbs_cd_n;
	}
	public void setBbs_cd_n(int bbs_cd_n) {
		this.bbs_cd_n = bbs_cd_n;
	}
	public int getBbs_seq_n() {
		return bbs_seq_n;
	}
	public void setBbs_seq_n(int bbs_seq_n) {
		this.bbs_seq_n = bbs_seq_n;
	}
	public String getUser_id_v() {
		return user_id_v;
	}
	public void setUser_id_v(String user_id_v) {
		this.user_id_v = user_id_v;
	}
	public String getScrap_title_v() {
		return scrap_title_v;
	}
	public void setScrap_title_v(String scrap_title_v) {
		this.scrap_title_v = scrap_title_v;
	}
	public String getScrap_loc_v() {
		return scrap_loc_v;
	}
	public void setScrap_loc_v(String scrap_loc_v) {
		this.scrap_loc_v = scrap_loc_v;
	}
	public String getScrap_dt_d() {
		return scrap_dt_d;
	}
	public void setScrap_dt_d(String scrap_dt_d) {
		this.scrap_dt_d = scrap_dt_d;
	}
	
	

}
