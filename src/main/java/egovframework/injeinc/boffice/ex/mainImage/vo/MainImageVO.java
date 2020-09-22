package egovframework.injeinc.boffice.ex.mainImage.vo;

import java.util.List;

public class MainImageVO extends MainImage {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 메인화면이미지 목록
	 */
	List<MainImageVO> mainImageList;
	/**
	 * 삭제대상 목록
	 */
    String[] delYn;

	/**
	 * @return the mainImageList
	 */
	public List<MainImageVO> getMainImageList() {
		return mainImageList;
	}
	/**
	 * @param mainImageList the mainImageList to set
	 */
	public void setMainImageList(List<MainImageVO> mainImageList) {
		this.mainImageList = mainImageList;
	}
	/**
	 * @return the delYn
	 */
	public String[] getDelYn() {
		return delYn;
	}
	/**
	 * @param delYn the delYn to set
	 */
	public void setDelYn(String[] delYn) {
		this.delYn = delYn;
	}



}
