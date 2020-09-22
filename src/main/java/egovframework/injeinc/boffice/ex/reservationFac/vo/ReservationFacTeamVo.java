package egovframework.injeinc.boffice.ex.reservationFac.vo;

import egovframework.cmm.ComDefaultVO;

public class ReservationFacTeamVo extends ComDefaultVO {
   private String ttIdx = "";
   private String teamName = "";
   private String userNm = "";
   private String userId = "";
   private String userHp = "";
   private String userEtc = "";
   private String regDate = "";
   private String actionkey = "";
   
   
   
	public String getTtIdx() {
	return ttIdx;
}
public void setTtIdx(String ttIdx) {
	this.ttIdx = ttIdx;
}
	public String getActionkey() {
	return actionkey;
}
public void setActionkey(String actionkey) {
	this.actionkey = actionkey;
}
	
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserHp() {
		return userHp;
	}
	public void setUserHp(String userHp) {
		this.userHp = userHp;
	}
	public String getUserEtc() {
		return userEtc;
	}
	public void setUserEtc(String userEtc) {
		this.userEtc = userEtc;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	   
   
   
}
