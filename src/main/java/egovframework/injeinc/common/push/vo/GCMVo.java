package egovframework.injeinc.common.push.vo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
 
public class GCMVo {
    // android 로 보낼 정보
    private String title = "제목입니다.";
    private String msg = "내용입니다.";
    private String typeCode = "코드입니다.";
    private String link_url = "";
    // push 결과 정보
    private String regId; // regId
    private boolean pushSuccessOrFailure; // 성공 여부
    private String msgId = ""; // 메시지 ID
    private String errorMsg = ""; // 에러메시지
 
    
    public String getLink_url() {
		return link_url;
	}

	public void setLink_url(String link_url) {
		this.link_url = link_url;
	}

	public String getTitle() {
        return title;
    }
 
    public void setTitle(String title) throws UnsupportedEncodingException {
        this.title = title;
    }
 
    public String getMsg() {
        return msg;
    }
 
    public void setMsg(String msg) throws UnsupportedEncodingException {
        this.msg = msg;
    }
 
    public String getTypeCode() {
        return typeCode;
    }
 
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }
 
    public boolean getPushSuccessOrFailure() {
        return pushSuccessOrFailure;
    }
 
    public void setPushSuccessOrFailure(boolean pushSuccessOrFailure) {
        this.pushSuccessOrFailure = pushSuccessOrFailure;
    }
 
    public String getErrorMsg() {
        return errorMsg;
    }
 
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
 
    public void setRegId(String regId) {
        this.regId = regId;
    }
 
    public String getMsgId() {
        return msgId;
    }
 
    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
 
    public String getRegId() {
        return regId;
    }
    
    
    
}
