package egovframework.injeinc.boffice.ex.mainImage.service.impl;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import egovframework.cmm.service.FileVO;
import egovframework.injeinc.boffice.ex.mainImage.dao.MainImageDao;
import egovframework.injeinc.boffice.ex.mainImage.service.MainImageSvc;
import egovframework.injeinc.boffice.ex.mainImage.vo.MainImage;
import egovframework.injeinc.boffice.ex.mainImage.vo.MainImageVO;
import egovframework.injeinc.foffice.ex.bbs.vo.BbsFVo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("mainImageService")
public class MainImageImpl extends EgovAbstractServiceImpl implements MainImageSvc {

	@Resource(name="mainImageDAO")
    private MainImageDao mainImageDAO;

	@Autowired
	private DataSourceTransactionManager transactionManager;
	
	/**
	 * 메인화면이미지정보를 관리하기 위해 등록된 메인화면이미지 목록을 조회한다.
	 * @param mainImageVO - 메인이미지 VO
	 * @return List - 메인이미지 목록
	 */
	public List<MainImageVO> selectMainImageList(MainImageVO mainImageVO) throws Exception {
		return mainImageDAO.selectMainImageList(mainImageVO);
	}

	/**
	 * 사용자메인 배너이미지정보를 관리하기 위해 등록된 메인화면이미지 목록을 조회한다.
	 * @param mainImageVO - 메인이미지 VO
	 * @return List - 메인이미지 목록
	 */
	public List<MainImageVO> selectBnImageList(MainImageVO mainImageVO) throws Exception {
		return mainImageDAO.selectBnImageList(mainImageVO);
	}

	/**
	 * 메인화면이미지목록 총 갯수를 조회한다.
	 * @param mainImageVO - 메인이미지 VO
	 * @return int - 메인이미지 카운트 수
	 */
	public int selectLoginScrinImageListTotCnt(MainImageVO mainImageVO) throws Exception {
		return mainImageDAO.selectMainImageListTotCnt(mainImageVO);
	}

	/**
	 * 등록된 메인화면이미지의 상세정보를 조회한다.
	 * @param mainImageVO - 메인이미지 VO
	 * @return MainImageVO - 메인이미지 VO
	 */
	public MainImageVO retrieveMainImage(MainImageVO mainImageVO) throws Exception {
		return mainImageDAO.selectMainImage(mainImageVO);
	}

	/**
	 * 메인화면이미지정보를 신규로 등록한다.
	 * @param mainImage - 메인이미지 model
	 */
	public MainImageVO registMainImage(MainImage mainImage,MainImageVO mainImageVO) throws Exception {
		mainImageDAO.insertMainImage(mainImage);
		if(mainImageVO != null){
			if(mainImage != null){
				mainImageVO.setImageId(mainImage.getImageId());
			}
		}
		return retrieveMainImage(mainImageVO);
	}

	/**
	 * 기 등록된 메인화면이미지정보를 수정한다.
	 * @param mainImage - 메인이미지 model
	 */
	public void modifyMainImage(MainImage mainImage) throws Exception {
		mainImageDAO.updateMainImage(mainImage);
	}

	/**
	 * 기 등록된 메인화면이미지정보를 삭제한다.
	 * @param mainImage - 메인이미지 model
	 */
	public void removeMainImage(MainImage mainImage) throws Exception {

		removeMainImageFile(mainImage);
		mainImageDAO.deleteMainImage(mainImage);
	}

	/**
	 * 기 등록된 메인화면이미지정보의 이미지파일을 삭제한다.
	 * @param mainImage - 메인이미지 model
	 */
	public void removeMainImageFile(MainImage mainImage) throws Exception {
		FileVO fileVO = (FileVO)mainImageDAO.selectMainImageFile(mainImage);
		File file = new File(fileVO.getFileStreCours()+fileVO.getStreFileNm());
		file.delete();
	}

	/**
	 * 메인화면이미지가 특정화면에 반영된 결과를 조회한다.
	 * @param mainImageVO - 메인이미지 VO
	 * @return MainImageVO - 메인이미지 VO
	 */
	public List<MainImageVO> selectMainImageResult(MainImageVO mainImageVO) throws Exception {
		return mainImageDAO.selectMainImageResult(mainImageVO);
	}

	public List<MainImageVO> retrieveListMainImage() throws Exception {
		return mainImageDAO.selectListMainImage();
	}

	public void modifyMainImageStepUp(MainImage mainImage) throws Exception {
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try{
			MainImage tempImage = mainImageDAO.selectMainImageStepUp(mainImage);		//상위 조회
			if(mainImage != null){
				mainImage.setTempImageId(tempImage.getTempImageId());
				mainImage.setTempSort(tempImage.getTempSort());
			}
			mainImageDAO.updateMainImageSort(mainImage);		//순서 바꿈
		}catch(RuntimeException e){
			transactionManager.rollback(status);
			throw e;
		}catch(Exception e){
			transactionManager.rollback(status);
			throw e;
		}
		transactionManager.commit(status);
	}

	public void modifyMainImageStepDown(MainImage mainImage) throws Exception {
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try{
			MainImage tempImage = mainImageDAO.selectMainImageStepDown(mainImage);		//상위 조회
			if(mainImage != null){
				mainImage.setTempImageId(tempImage.getTempImageId());
				mainImage.setTempSort(tempImage.getTempSort());
			}
			mainImageDAO.updateMainImageSort(mainImage);		//순서 바꿈
		}catch(RuntimeException e){
			transactionManager.rollback(status);
			throw e;
		}catch(Exception e){
			transactionManager.rollback(status);
			throw e;
		}
		transactionManager.commit(status);
		
	}

	public List<MainImageVO> retrieveListMainImageAll() throws Exception {
		return mainImageDAO.selectListMainImageAll();		//팝업존 전체 조회
	}
	
	/**
	 * 디지털 배움터 메인 홍보관 이미지 조회
	 * @param cbIdx - 홍보관 게시판 번호
	 * @return BbsFVo - 게시물 VO
	 */
	public List<BbsFVo> selectListNiaMainRelation(String cbIdx) throws Exception{
		return mainImageDAO.selectListNiaMainRelation(cbIdx);
	}
}
