package egovframework.injeinc.boffice.ex.mainImage.service;

import java.util.List;

import egovframework.injeinc.boffice.ex.mainImage.vo.MainImage;
import egovframework.injeinc.boffice.ex.mainImage.vo.MainImageVO;
import egovframework.injeinc.foffice.ex.bbs.vo.BbsFVo;

public interface MainImageSvc {

	/**
	 * 메인화면이미지정보를 관리하기 위해 등록된 메인화면이미지 목록을 조회한다.
	 * @param mainImageVO - 메인이미지 VO
	 * @return List - 메인이미지 목록
	 */
	public List<MainImageVO> selectMainImageList(MainImageVO mainImageVO) throws Exception;

	/**
	 * 사용자 메인 배너이미지정보를 관리하기 위해 등록된 메인화면이미지 목록을 조회한다.
	 * @param mainImageVO - 메인이미지 VO
	 * @return List - 메인이미지 목록
	 */
	public List<MainImageVO> selectBnImageList(MainImageVO mainImageVO) throws Exception;

	/**
	 * 메인화면이미지목록 총 갯수를 조회한다.
	 * @param mainImageVO - 메인이미지 VO
	 * @return int - 메인이미지 카운트 수
	 */
	public int selectLoginScrinImageListTotCnt(MainImageVO mainImageVO) throws Exception;

	/**
	 * 등록된 메인화면이미지의 상세정보를 조회한다.
	 * @param mainImageVO - 메인이미지 VO
	 * @return MainImageVO - 메인이미지 VO
	 */
	public MainImageVO retrieveMainImage(MainImageVO mainImageVO) throws Exception;

	/**
	 * 메인화면이미지정보를 신규로 등록한다.
	 * @param mainImage - 메인이미지 model
	 * @param mainImageVO - 메인이미지 VO
	 */
	public MainImageVO registMainImage(MainImage mainImage,MainImageVO mainImageVO) throws Exception;

	/**
	 * 기 등록된 메인화면이미지정보를 수정한다.
	 * @param mainImage - 메인이미지 model
	 */
	public void modifyMainImage(MainImage mainImage) throws Exception;

	/**
	 * 기 등록된 메인화면이미지정보를 삭제한다.
	 * @param mainImage - 메인이미지 model
	 */
	public void removeMainImage(MainImage mainImage) throws Exception;

	/**
	 * 기 등록된 메인화면이미지정보의 이미지파일을 삭제한다.
	 * @param mainImage - 메인이미지 model
	 */
	public void removeMainImageFile(MainImage mainImage) throws Exception;

	/**
	 * 메인화면이미지가 특정화면에 반영된 결과를 조회한다.
	 * @param mainImageVO - 메인이미지 VO
	 * @return List - 메인이미지 목록
	 */
	public List<MainImageVO> selectMainImageResult(MainImageVO mainImageVO) throws Exception;
	
	/**
	 * 사이트 메인화면 팝업존 리스트 조회
	 * @param mainImageVO - 메인이미지 VO
	 * @return List - 메인이미지 목록
	 */
	public List<MainImageVO> retrieveListMainImage() throws Exception;
	
	/**
	 * 사이트 메인화면 팝업존 상위 조회
	 * @param mainImage - 메인이미지 model
	 * @return List - 메인이미지 목록
	 */
	public void modifyMainImageStepUp(MainImage mainImage) throws Exception;
	
	/**
	 * 사이트 메인화면 팝업존 하위 조회
	 * @param mainImage - 메인이미지 model
	 * @return List - 메인이미지 목록
	 */
	public void modifyMainImageStepDown(MainImage mainImage) throws Exception;
	
	/**
	 * 사이트 메인화면 팝업존 하위 조회
	 * @param mainImage - 메인이미지 model
	 * @return List - 메인이미지 목록
	 */
	public List<MainImageVO> retrieveListMainImageAll() throws Exception;
	
	
	/**
	 * 디지털 배움터 메인 홍보관 이미지 조회
	 * @param cbIdx - 홍보관 게시판 번호
	 * @return BbsFVo - 게시물 VO
	 */
	public List<BbsFVo> selectListNiaMainRelation(String cbIdx) throws Exception;
}