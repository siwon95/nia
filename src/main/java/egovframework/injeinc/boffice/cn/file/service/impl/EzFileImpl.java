package egovframework.injeinc.boffice.cn.file.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.cmm.service.EgovProperties;
import egovframework.injeinc.boffice.cn.common.Util;
import egovframework.injeinc.boffice.cn.file.dao.EzFileDao;
import egovframework.injeinc.boffice.cn.file.service.EzFileSvc;
import egovframework.injeinc.boffice.cn.file.vo.EzFileVo;
import egovframework.injeinc.common.util.EgovDateUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
	
	@Service("EzFileSvc")
	public class EzFileImpl extends EgovAbstractServiceImpl implements EzFileSvc  {
		
		private static final Logger LOGGER = LoggerFactory.getLogger(Util.class);
	
		@Resource(name="EzFileDao")
		EzFileDao ezFileDao;

		/**  ez_file 등록 */
		public void registEzFile(EzFileVo ezFileVo,HttpServletRequest request, String formName) throws Exception {
			EgovDateUtil egovDateUtil = new EgovDateUtil();
			String date = egovDateUtil.getToday();
			
			String uploadEzfileFolder = EgovProperties.getProperty("Globals.fileStorePath")+"EzFile/"+date+"/";
			String saveFileName = egovDateUtil.yearMonthDayTime();

			File fileFolder = new File(uploadEzfileFolder);
			if(!fileFolder.exists()){
				fileFolder.mkdir();
			}
			MultipartHttpServletRequest multipartRequest =  (MultipartHttpServletRequest)request;
			List<MultipartFile> files = multipartRequest.getFiles(formName); 

			try{
				int fileCount = 0;
				for(MultipartFile file : files){
					if(!file.isEmpty()){
						String fileName = file.getOriginalFilename();
						String fileSize = Integer.toString((int)file.getSize());
						String imgExt = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
						
						String saveFileName2 = saveFileName +fileCount+ "." + imgExt;
						File ufile = new File(uploadEzfileFolder + "/" + saveFileName2);
						
						//ezFileVo.setFileSn((maxSn+fileCount)+"");
						ezFileVo.setFileStreCours(uploadEzfileFolder+"/");
						ezFileVo.setStreFileNm(saveFileName2);
						ezFileVo.setOrignlFileNm(fileName);
						ezFileVo.setFileCn("");
						ezFileVo.setFileExtsn(imgExt);
						ezFileVo.setFileSize(fileSize);
						
						//파일 저장    
						try {

							file.transferTo((ufile));
						} catch (IllegalStateException e) {                    
							LOGGER.debug("IGNORED: " + e.getMessage());
						} catch (IOException e) {
							LOGGER.debug("IGNORED: " + e.getMessage());
						}
						fileCount++;
					}
				} 
				
			}catch(NumberFormatException e){
				LOGGER.debug("IGNORED: " + e.getMessage());
			}catch(IllegalStateException e){
				LOGGER.debug("IGNORED: " + e.getMessage());
			}catch(Exception e){
				LOGGER.debug("IGNORED: " + e.getMessage());
			}
			
			System.out.println("date:"+date);
			System.out.println("첨부파일 테스트"+uploadEzfileFolder);
			//ezFileDao.insertEzFile(ezFileVo);
		}
		
		/**  ez_file 등록 */
		public void registEzFileDetail(EzFileVo ezFileVo) throws Exception {
			ezFileDao.insertEzFileDetail(ezFileVo);
		}
		
		/**  ez_file 상세조회 */
		public EzFileVo retrieveEzFile(EzFileVo ezFileVo) throws Exception {
			return ezFileDao.selectEzFile(ezFileVo);
		}
		
		/**  ez_file Max */
		public String retrieveEzFileKey(EzFileVo ezFileVo) throws Exception {
			return ezFileDao.selectEzFileKey(ezFileVo);
		}
		
		/**  ez_filedetail sn Max */
		public String retrieveEzFileDetailSn(EzFileVo ezFileVo) throws Exception {
			return ezFileDao.selectEzFileDetailSn(ezFileVo);
		}
		
		/**  ez_file 목록 */
		public List retrieveListEzFile(EzFileVo ezFileVo) throws Exception {
			return ezFileDao.selectListEzFile(ezFileVo);
		}
		
		/** ez_file 수정 */
		public void modifyEzFile(EzFileVo ezFileVo) throws Exception {
			ezFileDao.updateEzFile(ezFileVo);
		}
		
		/** ez_file 삭제*/
		public void removeEzFile(EzFileVo ezFileVo) throws Exception {
			ezFileDao.deleteEzFile(ezFileVo);
		}
		
		/** ez_file 삭제재정렬*/
		public void removeEzFileSort(EzFileVo ezFileVo) throws Exception {
			ezFileDao.deleteEzFileSort(ezFileVo);
		}
	}
