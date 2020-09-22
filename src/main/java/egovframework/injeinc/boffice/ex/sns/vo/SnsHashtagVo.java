package egovframework.injeinc.boffice.ex.sns.vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import egovframework.cmm.ComDefaultVO;
import egovframework.injeinc.common.util.DateUtil;

@SuppressWarnings("serial")
public class SnsHashtagVo extends ComDefaultVO {

	private String hsIdx			= "";
	private String hashtag			= "";
	private int viewCnt			= 0;
	private int snsCnt			= 0;
	private String useYn			= "";
	private String regid			= "";
	private String regdt			= "";

	
	public String getHashtag() {
		return hashtag;
	}
	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}
	public int getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}
	public int getSnsCnt() {
		return snsCnt;
	}
	public void setSnsCnt(int snsCnt) {
		this.snsCnt = snsCnt;
	}
	public String getHsIdx() {
		return hsIdx;
	}
	public void setHsIdx(String hsIdx) {
		this.hsIdx = hsIdx;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getRegid() {
		return regid;
	}
	public void setRegid(String regid) {
		this.regid = regid;
	}
	public String getRegdt() {
		return regdt;
	}
	public void setRegdt(String regdt) {
		this.regdt = regdt;
	}
	
}