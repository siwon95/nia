package egovframework.injeinc.boffice.sy.mgr.vo;

import egovframework.cmm.ComDefaultVO;

public class MgrMainConfigVo extends ComDefaultVO {
	/** 관리자 pk */	
	private String mgrIdx;
	/** 관리자 아이디 */	
	private String mgrId;
	/** 메인 관리자공지 노출여부 */
	private String noticeYn;
	/** 메인 민원목록 노출여부 */
	private String minwonYn;
	/** 메인 묻고답하기 노출여부 */
	private String qnaYn;
	/** 메인 게시판 노출여부 */
	private String boardYn;
	/** 메인 통합예약(시설) 노출여부 */
	private String expandYn;
	/** 메인 통합예약(강좌) 노출여부 */
	private String lectureYn;
	/** 메인 통합예약(행사) 노출여부 */
	private String eventYn;
	/** 등록아이디 */
	private String regId;
	/** 등록일 */
	private String regDt;
	/** 수정아이디 */
	private String modId;
	/** 수정일 */
	private String modDt;
	
	public String getMgrIdx() {
		return mgrIdx;
	}
	public void setMgrIdx(String mgrIdx) {
		this.mgrIdx = mgrIdx;
	}
	public String getMgrId() {
		return mgrId;
	}
	public void setMgrId(String mgrId) {
		this.mgrId = mgrId;
	}
	public String getNoticeYn() {
		return noticeYn == null ? "" : noticeYn;
	}
	public void setNoticeYn(String noticeYn) {
		this.noticeYn = noticeYn;
	}
	public String getMinwonYn() {
		return minwonYn == null ? "" : minwonYn;
	}
	public void setMinwonYn(String minwonYn) {
		this.minwonYn = minwonYn;
	}
	public String getQnaYn() {
		return qnaYn == null ? "" : qnaYn;
	}
	public void setQnaYn(String qnaYn) {
		this.qnaYn = qnaYn;
	}
	public String getBoardYn() {
		return boardYn == null ? "" : boardYn;
	}
	public void setBoardYn(String boardYn) {
		this.boardYn = boardYn;
	}
	public String getExpandYn() {
		return expandYn == null ? "" : expandYn;
	}
	public void setExpandYn(String expandYn) {
		this.expandYn = expandYn;
	}
	public String getLectureYn() {
		return lectureYn == null ? "" : lectureYn;
	}
	public void setLectureYn(String lectureYn) {
		this.lectureYn = lectureYn;
	}
	public String getEventYn() {
		return eventYn == null ? "" : eventYn;
	}
	public void setEventYn(String eventYn) {
		this.eventYn = eventYn;
	}
	public String getRegId() {
		return regId == null ? "" : regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getRegDt() {
		return regDt == null ? "" : regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getModId() {
		return modId == null ? "" : modId;
	}
	public void setModId(String modId) {
		this.modId = modId;
	}
	public String getModDt() {
		return modDt == null ? "" : modDt;
	}
	public void setModDt(String modDt) {
		this.modDt = modDt;
	}
	
	
}