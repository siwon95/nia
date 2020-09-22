package egovframework.injeinc.boffice.sy.board.vo;

public class BoardVo {
	
	/** COMMON **/
	private int cbIdx				= 0;
	private int copyCbIdx			= 0;
	private String regDt			= "";
	private String regId			= "";
	private String modDt			= "";
	private String modId			= "";
	
	/** CMS_BBS **/
	private String cbName			= "";
	private String cbPath			= "";
	private String mgrSiteCd		= "";
	
	/** BBS_CONFIG **/
	private String bbsTempletGbn	= "";
	private String bbsApprYn		= "";
	private String categoryUseYn	= "";
	private int bbsFileCnt			= 0;
	private String fileMaxSize			= "";
	private String mwRKd			= "";
	private int btIdx				= 0;
	private String mListYn			= "";
	private String editorYn			= "";
	private String anonymityYn		= "";
	private String replyYn			= "";
	private String rssUseYn		="";
	private String rssUrl		="";
	private String thumbnailWidth = "";
	private String thumbnailHeight = "";
	private String nasYn		="";
	private int pageCount	= 10;
	private String pushYn = "";
	
	/** BBS_USR_CONFIG **/
	private String listGbn			= "";
	private String readGbn			= "";
	private String writeGbn			= "";
	private String modGbn			= "";
	private String delGbn			= "";
	private String answerGbn		= "";
	private String mode =  "";
	private String commentYn =  "";
	private String socialYn =  "";
	
	
	
	
	public String getPushYn() {
		return pushYn;
	}
	public void setPushYn(String pushYn) {
		this.pushYn = pushYn;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public int getCopyCbIdx() {
		return copyCbIdx;
	}
	public void setCopyCbIdx(int copyCbIdx) {
		this.copyCbIdx = copyCbIdx;
	}
	public int getCbIdx() {
		return cbIdx;
	}
	public void setCbIdx(int cbIdx) {
		this.cbIdx = cbIdx;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getModDt() {
		return modDt;
	}
	public void setModDt(String modDt) {
		this.modDt = modDt;
	}
	public String getModId() {
		return modId;
	}
	public void setModId(String modId) {
		this.modId = modId;
	}
	public String getCbName() {
		return cbName;
	}
	public void setCbName(String cbName) {
		this.cbName = cbName;
	}
	public String getCbPath() {
		return cbPath;
	}
	public void setCbPath(String cbPath) {
		this.cbPath = cbPath;
	}
	public String getBbsTempletGbn() {
		return bbsTempletGbn;
	}
	public void setBbsTempletGbn(String bbsTempletGbn) {
		this.bbsTempletGbn = bbsTempletGbn;
	}
	public String getBbsApprYn() {
		return bbsApprYn;
	}
	public void setBbsApprYn(String bbsApprYn) {
		this.bbsApprYn = bbsApprYn;
	}
	public String getCategoryUseYn() {
		return categoryUseYn;
	}
	public void setCategoryUseYn(String categoryUseYn) {
		this.categoryUseYn = categoryUseYn;
	}
	public int getBbsFileCnt() {
		return bbsFileCnt;
	}
	public void setBbsFileCnt(int bbsFileCnt) {
		this.bbsFileCnt = bbsFileCnt;
	}
	public String getFileMaxSize() {
		return fileMaxSize;
	}
	public void setFileMaxSize(String fileMaxSize) {
		this.fileMaxSize = fileMaxSize;
	}
	public String getMwRKd() {
		return mwRKd;
	}
	public void setMwRKd(String mwRKd) {
		this.mwRKd = mwRKd;
	}
	public int getBtIdx() {
		return btIdx;
	}
	public void setBtIdx(int btIdx) {
		this.btIdx = btIdx;
	}
	public String getmListYn() {
		return mListYn;
	}
	public void setmListYn(String mListYn) {
		this.mListYn = mListYn;
	}
	public String getEditorYn() {
		return editorYn;
	}
	public void setEditorYn(String editorYn) {
		this.editorYn = editorYn;
	}
	public String getAnonymityYn() {
		return anonymityYn;
	}
	public void setAnonymityYn(String anonymityYn) {
		this.anonymityYn = anonymityYn;
	}
	public String getReplyYn() {
		return replyYn;
	}
	public void setReplyYn(String replyYn) {
		this.replyYn = replyYn;
	}
	public String getListGbn() {
		return listGbn;
	}
	public void setListGbn(String listGbn) {
		this.listGbn = listGbn;
	}
	public String getReadGbn() {
		return readGbn;
	}
	public void setReadGbn(String readGbn) {
		this.readGbn = readGbn;
	}
	public String getWriteGbn() {
		return writeGbn;
	}
	public void setWriteGbn(String writeGbn) {
		this.writeGbn = writeGbn;
	}
	public String getModGbn() {
		return modGbn;
	}
	public void setModGbn(String modGbn) {
		this.modGbn = modGbn;
	}
	public String getDelGbn() {
		return delGbn;
	}
	public void setDelGbn(String delGbn) {
		this.delGbn = delGbn;
	}
	public String getAnswerGbn() {
		return answerGbn;
	}
	public void setAnswerGbn(String answerGbn) {
		this.answerGbn = answerGbn;
	}
	public String getRssUseYn() {
		return rssUseYn;
	}
	public void setRssUseYn(String rssUseYn) {
		this.rssUseYn = rssUseYn;
	}
	public String getRssUrl() {
		return rssUrl;
	}
	public void setRssUrl(String rssUrl) {
		this.rssUrl = rssUrl;
	}
	public String getThumbnailWidth() {
		return thumbnailWidth;
	}
	public void setThumbnailWidth(String thumbnailWidth) {
		this.thumbnailWidth = thumbnailWidth;
	}
	public String getThumbnailHeight() {
		return thumbnailHeight;
	}
	public void setThumbnailHeight(String thumbnailHeight) {
		this.thumbnailHeight = thumbnailHeight;
	}
	public String getNasYn() {
		return nasYn;
	}
	public void setNasYn(String nasYn) {
		this.nasYn = nasYn;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public String getMgrSiteCd() {
		return mgrSiteCd;
	}
	public void setMgrSiteCd(String mgrSiteCd) {
		this.mgrSiteCd = mgrSiteCd;
	}
	public String getCommentYn() {
		return commentYn;
	}
	public void setCommentYn(String commentYn) {
		this.commentYn = commentYn;
	}
	public String getSocialYn() {
		return socialYn;
	}
	public void setSocialYn(String socialYn) {
		this.socialYn = socialYn;
	}
	
	
	
	
	
}