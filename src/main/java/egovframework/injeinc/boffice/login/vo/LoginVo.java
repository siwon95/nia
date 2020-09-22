package egovframework.injeinc.boffice.login.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

public class LoginVo implements Serializable {
	
	private String mgrIdx		= "";
	private String userId		= "";
	private String passWd		= "";
	private String userName		= "";
	private String permCd		= ""; 
	private String permNm		= "";
	
	private String pDeptCd		= ""; // 과/동 부서명
	private String deptCd		= ""; // 부서
	private String deptNm		= ""; // 부서명
	
	private String roleIdx		= "";
	private boolean isAdmin		= false;
	private List<String> mgrSiteCdList = null; /* 전체 권한 */
	private List<String> lcList = null; /* 강좌 권한*/
	
	private String mgrSiteCd	= "";
	private String publishAuthYn	= "";
	private String ip			= "";
	
	public String getMgrIdx() {
		return mgrIdx;
	}
	public void setMgrIdx(String mgrIdx) {
		this.mgrIdx = mgrIdx;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassWd() {
		return passWd;
	}
	public void setPassWd(String passWd) {
		this.passWd = passWd;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPermCd() {
		return permCd;
	}
	public void setPermCd(String permCd) {
		this.permCd = permCd;
		
		if("05010000".equals(permCd)){
			this.isAdmin = true;
		}
	}
	public String getPermNm() {
		return permNm;
	}
	public void setPermNm(String permNm) {
		this.permNm = permNm;
	}
	public String getDeptCd() {
		return deptCd;
	}
	public void setDeptCd(String deptCd) {
		this.deptCd = deptCd;
	}
	public String getDeptNm() {
		return deptNm;
	}
	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getRoleIdx() {
		return roleIdx;
	}
	public void setRoleIdx(String roleIdx) {
		this.roleIdx = roleIdx;
	}
	
	/* 최고관리자 여부 */
	public boolean getIsAdmin() {
		if("05010000".equals(permCd)){
			return true;
		}
		return false;
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
    }
	public String getpDeptCd() {
		return pDeptCd;
	}
	public void setpDeptCd(String pDeptCd) {
		this.pDeptCd = pDeptCd;
	}
	public String getMgrSiteCd() {
		return mgrSiteCd;
	}
	public void setMgrSiteCd(String mgrSiteCd) {
		this.mgrSiteCd = mgrSiteCd;
		
		mgrSiteCdList = new ArrayList<String>();
		lcList = new ArrayList<String>();
		String[] mgrSiteCds = (mgrSiteCd+",empty").split(",");
		for (String msc : mgrSiteCds) {
			mgrSiteCdList.add(msc);
			if(msc.indexOf("lc_") != -1){
				lcList.add(msc);
			}
		}

	}
	public List<String> getMgrSiteCdList() {
		return mgrSiteCdList;
	}
	public String getPublishAuthYn() {
		return publishAuthYn;
	}
	public void setPublishAuthYn(String publishAuthYn) {
		this.publishAuthYn = publishAuthYn;
	}
	public List<String> getLcList() {
		return lcList;
	}
	
}
