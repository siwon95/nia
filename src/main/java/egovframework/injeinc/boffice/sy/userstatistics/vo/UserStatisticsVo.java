package egovframework.injeinc.boffice.sy.userstatistics.vo;

import egovframework.cmm.ComDefaultVO;

public class UserStatisticsVo extends ComDefaultVO {
	private String memberCnt;				//회원가입자수
	private String scType;				//보여주는 형식
	private String scYear;				//검색조건(년)
	private String scMonth;				//검색조건(월)
	private String resultDate;
	
	
	public String getMemberCnt() {
		return memberCnt;
	}
	public void setMemberCnt(String memberCnt) {
		this.memberCnt = memberCnt;
	}
	public String getScType() {
		return scType;
	}
	public void setScType(String scType) {
		this.scType = scType;
	}
	public String getScYear() {
		return scYear;
	}
	public void setScYear(String scYear) {
		this.scYear = scYear;
	}
	public String getScMonth() {
		return scMonth;
	}
	public void setScMonth(String scMonth) {
		this.scMonth = scMonth;
	}
	public String getResultDate() {
		return resultDate;
	}
	public void setResultDate(String resultDate) {
		this.resultDate = resultDate;
	}
	
	@Override
	public String toString() {
		return "UserStatisticsVo [memberCnt=" + memberCnt + ", scType=" + scType + ", scYear=" + scYear + ", scMonth=" + scMonth + ", resultDate=" + resultDate + "]";
	}			
	
}
