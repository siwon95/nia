/**
 * 개요
 * - 메인화면이미지에 대한 DAO 클래스를 정의한다.
 *
 * 상세내용
 * - 메인화면이미지에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 메인화면이미지의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 이문준
 * @version 1.0
 * @created 03-8-2009 오후 2:08:58
 */

package egovframework.injeinc.boffice.ex.mainImage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.cmm.service.FileVO;
import egovframework.injeinc.boffice.ex.mainImage.vo.MainImage;
import egovframework.injeinc.boffice.ex.mainImage.vo.MainImageVO;
import egovframework.injeinc.foffice.ex.bbs.vo.BbsFVo;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("mainImageDAO")
public class MainImageDao extends EgovAbstractMapper {

	/**
	 * 메인화면이미지정보를 관리하기 위해 등록된 메인화면이미지 목록을 조회한다.
	 * @param mainImageVO - 메인이미지 VO
	 * @return List - 메인이미지 목록
	 */
	@SuppressWarnings("unchecked")
	public List<MainImageVO> selectMainImageList(MainImageVO mainImageVO) throws Exception {
		return selectList("mainImageDAO.selectMainImageList", mainImageVO);
	}

	/**
	 * 사용자 메인배너이미지정보를 관리하기 위해 등록된 메인화면이미지 목록을 조회한다.
	 * @param mainImageVO - 메인이미지 VO
	 * @return List - 메인이미지 목록
	 */
	@SuppressWarnings("unchecked")
	public List<MainImageVO> selectBnImageList(MainImageVO mainImageVO) throws Exception {
		return selectList("mainImageDAO.selectBnImageList", mainImageVO);
	}

    /**
	 * 메인화면이미지목록 총 갯수를 조회한다.
	 * @param mainImageVO - 메인화면이미지 VO
	 * @return int
	 * @exception Exception
	 */
    public int selectMainImageListTotCnt(MainImageVO mainImageVO) throws Exception {
        return (Integer)selectOne("mainImageDAO.selectMainImageListTotCnt", mainImageVO);
    }

	/**
	 * 등록된 메인화면이미지의 상세정보를 조회한다.
	 * @param mainImageVO - 메인이미지 VO
	 * @return MainImageVO - 메인이미지 VO
	 */
	public MainImageVO selectMainImage(MainImageVO mainImageVO) throws Exception {
		return (MainImageVO) selectOne("mainImageDAO.selectMainImage", mainImageVO);
	}

	/**
	 * 메인화면이미지정보를 신규로 등록한다.
	 * @param mainImage - 메인이미지 model
	 */
	public void insertMainImage(MainImage mainImage) throws Exception {
		insert("mainImageDAO.insertMainImage", mainImage);
	}

	/**
	 * 기 등록된 메인화면이미지정보를 수정한다.
	 * @param mainImage - 메인이미지 model
	 */
	public void updateMainImage(MainImage mainImage) throws Exception {
		update("mainImageDAO.updateMainImage", mainImage);
	}

	/**
	 * 기 등록된 메인화면이미지정보를 삭제한다.
	 * @param mainImage - 메인이미지 model
	 */
	public void deleteMainImage(MainImage mainImage) throws Exception {
		delete("mainImageDAO.deleteMainImage", mainImage);
	}

	/**
	 * 기 등록된 메인화면이미지정보의 이미지파일을 삭제하기 위해 파일정보를 조회한다.
	 * @param mainImage - 메인이미지 model
	 */
	public FileVO selectMainImageFile(MainImage mainImage) throws Exception {
		return (FileVO) selectOne("mainImageDAO.selectMainImageFile", mainImage);
	}

	/**
	 * 메인화면이미지가 특정화면에 반영된 결과를 조회한다.
	 * @param mainImageVO - 메인이미지 VO
	 * @return MainImageVO - 메인이미지 VO
	 */
	@SuppressWarnings("unchecked")
	public List<MainImageVO> selectMainImageResult(MainImageVO mainImageVO) throws Exception {
		return selectList("mainImageDAO.selectMainImageResult", mainImageVO);
	}
	
	/**
	 * 사이트 메인화면 팝업존 리스트 조회
	 * @param mainImageVO - 메인이미지 VO
	 * @return MainImageVO - 메인이미지 VO
	 */
	@SuppressWarnings("unchecked")
	public List<MainImageVO> selectListMainImage() throws Exception {
		return selectList("mainImageDAO.selectListMainImage", null);
	}
	
	/**
	 * 사이트 메인화면 팝업존 상위 조회
	 * @param mainImageVO - 메인이미지 VO
	 * @return MainImageVO - 메인이미지 VO
	 */
	@SuppressWarnings("unchecked")
	public MainImage selectMainImageStepUp(MainImage mainImage) throws Exception {
		return (MainImage)selectOne("mainImageDAO.selectMainImageStepUp", mainImage);
	}
	
	/**
	 * 사이트 메인화면 팝업존 순서 바꿈
	 * @param mainImageVO - 메인이미지 VO
	 * @return MainImageVO - 메인이미지 VO
	 */
	@SuppressWarnings("unchecked")
	public void updateMainImageSort(MainImage mainImage) throws Exception {
		update("mainImageDAO.updateMainImageSort", mainImage);
	}
	
	/**
	 * 사이트 메인화면 팝업존 하위 조회
	 * @param mainImageVO - 메인이미지 VO
	 * @return MainImageVO - 메인이미지 VO
	 */
	@SuppressWarnings("unchecked")
	public MainImage selectMainImageStepDown(MainImage mainImage) throws Exception {
		return (MainImage)selectOne("mainImageDAO.selectMainImageStepDown", mainImage);
	}
	
	/**
	 * 사이트 팝업존 조회
	 * @param mainImageVO - 메인이미지 VO
	 * @return MainImageVO - 메인이미지 VO
	 */
	@SuppressWarnings("unchecked")
	public List<MainImageVO> selectListMainImageAll() throws Exception {
		return selectList("mainImageDAO.selectListMainImageAll", null);
	}
	
	/**
	 * 디지털 배움터 메인 홍보관 이미지 조회
	 * @param cbIdx - 홍보관 게시판 번호
	 * @return BbsFVo - 게시물 VO
	 */
	public List<BbsFVo> selectListNiaMainRelation(String cbIdx) throws Exception{
		return selectList("mainImageDAO.selectListNiaMainRelation", cbIdx);
	}
}
