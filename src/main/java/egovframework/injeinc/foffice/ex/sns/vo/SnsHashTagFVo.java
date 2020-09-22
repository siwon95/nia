package egovframework.injeinc.foffice.ex.sns.vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import egovframework.cmm.ComDefaultVO;
import egovframework.injeinc.common.util.DateUtil;

@SuppressWarnings("serial")
public class SnsHashTagFVo extends ComDefaultVO {

	private String hsIdx			= "";
	private String hashtag			= "";
	
	
	public String getHashtag() {
		return hashtag;
	}
	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}
	public String getHsIdx() {
		return hsIdx;
	}
	public void setHsIdx(String hsIdx) {
		this.hsIdx = hsIdx;
	}
}