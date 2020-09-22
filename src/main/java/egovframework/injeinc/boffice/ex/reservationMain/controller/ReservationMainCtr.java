package egovframework.injeinc.boffice.ex.reservationMain.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.EgovWebUtil;
import egovframework.cmm.Message;
import egovframework.cmm.service.EgovProperties;
import egovframework.injeinc.boffice.ex.reservationMain.service.ReservationMainSvc;
import egovframework.injeinc.boffice.ex.reservationMain.vo.ReservationMainVo;
import egovframework.injeinc.common.util.EgovFormBasedUUID;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class ReservationMainCtr extends CmmLogCtr {
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationMainCtr.class);
	
	@Resource(name="ReservationMainSvc")
	private ReservationMainSvc reservationMainSvc;
	
	/** 리스트 */
	@RequestMapping(value="/boffice/ex/reservationMain/PesMain_List.do")
	public String pesMainList(HttpServletRequest request,
								@ModelAttribute("ReservationMainVo") ReservationMainVo reservationMainVo,
								ModelMap model) throws Exception {
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(reservationMainVo.getPageIndex());
		paginationInfo.setRecordCountPerPage(reservationMainVo.getPageUnit());
		paginationInfo.setPageSize(reservationMainVo.getPageSize());
		
		reservationMainVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		reservationMainVo.setLastIndex(paginationInfo.getLastRecordIndex());
		reservationMainVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		
		int totCnt = reservationMainSvc.retrievePagReservationMainImg(reservationMainVo);
		paginationInfo.setTotalRecordCount(totCnt);
		
		@SuppressWarnings("rawtypes")
		List resultList = reservationMainSvc.retrieveListReservationMainImg(reservationMainVo);
		if(model != null){
			model.addAttribute("totCnt",totCnt);
			model.addAttribute("resultList", resultList);
			model.addAttribute("paginationInfo", paginationInfo);
		}
		
		return "injeinc/boffice/ex/reservationMain/res_main_list";
		
	}
	
	/** 등록/수정 폼 */
	@RequestMapping(value= "/boffice/ex/reservationMain/PesMain_Form.do")
	public String pesMainForm(HttpServletRequest request, 
								@ModelAttribute("ReservationMainVo") ReservationMainVo reservationMainVo
								, ModelMap model ) throws Exception {
							
		if(!reservationMainVo.getRmiIdx().equals("")){//수정폼 호출시
			ReservationMainVo result= reservationMainSvc.retrieveReservationMainImg(reservationMainVo);
			result.setPageIndex(reservationMainVo.getPageIndex());
			result.setSearchKeyword(reservationMainVo.getSearchKeyword());
			
			String startDay = result.getRmiPostSdt().substring(0, 8);
			String startHour = result.getRmiPostSdt().substring(8, 10);
			String startMinute = result.getRmiPostSdt().substring(10, 12);
			result.setStartday(startDay.substring(0, 4)+"-"+startDay.substring(4, 6)+"-"+startDay.substring(6, 8));
			result.setStarthour(startHour);
			result.setStartminute(startMinute);
			
			String endDay = result.getRmiPostEdt().substring(0, 8);
			String endHour = result.getRmiPostEdt().substring(8, 10);
			String endMinute = result.getRmiPostEdt().substring(10, 12);
			result.setEndday(endDay.substring(0, 4)+"-"+endDay.substring(4, 6)+"-"+endDay.substring(6, 8));
			result.setEndhour(endHour);
			result.setEndminute(endMinute);
			if(model != null){
				model.addAttribute("ReservationMainVo", result);
			}
		}else{
			if(model != null){
				model.addAttribute("ReservationMainVo", reservationMainVo);
			}
		}
		
		return "injeinc/boffice/ex/reservationMain/res_main_form";
	}
	
	/** 등록 */
	@RequestMapping(value= "/boffice/ex/reservationMain/PesMain_Reg.do")
	public String pesMainReg(HttpServletRequest request, 
								@ModelAttribute("ReservationMainVo") ReservationMainVo reservationMainVo
								, ModelMap model ) throws Exception {
		
		HttpSession ses = request.getSession();
		
		reservationMainVo.setRegId((String)ses.getAttribute("SesUserId"));
		
		String SVC_MSGE = "";
		
		String uploadPath = Message.getMessage("reservationMain.file.upload.path"); //첨부파일경로 fileUploadProperties.properties에서 경로 설정
		
		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
		
		
		File saveFolder = new File(EgovWebUtil.filePathBlackList(rootPath + uploadPath));
		  if (!saveFolder.exists() || saveFolder.isFile()) saveFolder.mkdirs();

		MultipartHttpServletRequest multipartRequest =  (MultipartHttpServletRequest)request;
		
		//단일 파일 업로드
		MultipartFile file1 = multipartRequest.getFile("fileUpload"); 
		MultipartFile file2 = multipartRequest.getFile("fileUpload2"); 
		
		//다중파일 업로드
		//List<MultipartFile> files = multipartRequest.getFiles("fileUpload"); 
		String fileSystemName = "";
		File ufile = null;
		try{
			
			if(!file1.isEmpty()){            
				  
				String fileName = file1.getOriginalFilename();
				reservationMainVo.setRmiMainOrgname(fileName);
				
				String fileExt = fileName.substring(fileName.indexOf("."));		//확장자 추출
				fileSystemName = EgovFormBasedUUID.randomUUID().toString().toLowerCase()+fileExt;	// 전자정부 UTIL 방법
				reservationMainVo.setRmiMainRename(fileSystemName);	// 파일명 랜덤으로 set
				
				reservationMainVo.setRmiMainPath(uploadPath);
				
				ufile = new File(EgovWebUtil.filePathBlackList(rootPath + uploadPath + fileSystemName));  
				
				//파일 저장    
				file1.transferTo((ufile));
			}
			if(!file2.isEmpty()){            
				
				String fileName = file2.getOriginalFilename();
				reservationMainVo.setRmiBannerOrgname(fileName);
				
				String fileExt = fileName.substring(fileName.indexOf("."));		//확장자 추출
				fileSystemName = EgovFormBasedUUID.randomUUID().toString().toLowerCase()+fileExt;	// 전자정부 UTIL 방법
				reservationMainVo.setRmiBannerRename(fileSystemName);	// 파일명 랜덤으로 set
				
				reservationMainVo.setRmiBannerPath(uploadPath);
				
				ufile = new File(EgovWebUtil.filePathBlackList(rootPath + uploadPath + fileSystemName));  
				
				//파일 저장    
				file2.transferTo((ufile));
			}
				
			reservationMainSvc.registReservationMainImg(reservationMainVo);		//등록
			SVC_MSGE = Message.getMessage("201.message"); //인서트성공
		}catch(IllegalStateException e){
			ufile.delete();
			LOGGER.debug("IGNORED: "+e.getMessage());
			SVC_MSGE = Message.getMessage("901.message"); //인서트실패
		}catch(IOException e){
			ufile.delete();
			LOGGER.debug("IGNORED: "+e.getMessage());
			SVC_MSGE = Message.getMessage("901.message"); //인서트실패
		}catch(Exception e){
			ufile.delete();
			LOGGER.debug("IGNORED: "+e.getMessage());
			SVC_MSGE = Message.getMessage("901.message"); //인서트실패
		}
							
		return alert("/boffice/ex/reservationMain/PesMain_List.do", SVC_MSGE, model);
	}
	
	/** 수정 */
	@RequestMapping(value= "/boffice/ex/reservationMain/PesMain_Mod.do")
	public String pesMainMod(HttpServletRequest request, 
								@ModelAttribute("ReservationMainVo") ReservationMainVo reservationMainVo
								, ModelMap model ) throws Exception {
		
		HttpSession ses = request.getSession();
		
		reservationMainVo.setModId((String)ses.getAttribute("SesUserId"));
		
		String SVC_MSGE = "";
		
		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
		
		String uploadPath = Message.getMessage("reservationMain.file.upload.path"); //첨부파일경로 fileUploadProperties.properties에서 경로 설정
		
		File saveFolder = new File(EgovWebUtil.filePathBlackList(rootPath + uploadPath));
		  if (!saveFolder.exists() || saveFolder.isFile()) saveFolder.mkdirs();
		  
		MultipartHttpServletRequest multipartRequest =  (MultipartHttpServletRequest)request;
		
		//단일 파일 업로드
		MultipartFile file1 = multipartRequest.getFile("fileUpload"); 
		MultipartFile file2 = multipartRequest.getFile("fileUpload2"); 
		
		//다중파일 업로드
		//List<MultipartFile> files = multipartRequest.getFiles("fileUpload"); 
		
		
		String fileSystemName = "";
		File ufile = null;
		try{
			
				if(!file1.isEmpty()){            
					if(reservationMainVo.getRmiMainRename() != null && !reservationMainVo.getRmiMainRename().equals("")){
						//기존 파일 삭제 시작
						File beforeFile = new File(EgovWebUtil.filePathBlackList(rootPath + uploadPath+reservationMainVo.getRmiMainRename()));
						
						beforeFile.delete();		//WAS파일 삭제
						
						//기존 파일 삭제 끝
					}
					String fileName = file1.getOriginalFilename();
					reservationMainVo.setRmiMainOrgname(fileName);
					
					String fileExt = fileName.substring(fileName.indexOf("."));		//확장자 추출
					fileSystemName = EgovFormBasedUUID.randomUUID().toString().toLowerCase()+fileExt;	// 전자정부 UTIL 방법
					reservationMainVo.setRmiMainRename(fileSystemName); // 파일명 랜덤으로 set
					
					reservationMainVo.setRmiMainPath(uploadPath);
					
					ufile = new File(EgovWebUtil.filePathBlackList(rootPath + uploadPath + fileSystemName));  
					
					//파일 저장    
					file1.transferTo((ufile));
				}
				if(!file2.isEmpty()){            
					if(reservationMainVo.getRmiBannerRename() != null && !reservationMainVo.getRmiBannerRename().equals("")){
						//기존 파일 삭제 시작
						File beforeFile = new File(EgovWebUtil.filePathBlackList(rootPath + uploadPath+reservationMainVo.getRmiBannerRename()));
						
						beforeFile.delete();		//WAS파일 삭제
						
						//기존 파일 삭제 끝
					}
					String fileName = file2.getOriginalFilename();
					reservationMainVo.setRmiBannerOrgname(fileName);
					
					String fileExt = fileName.substring(fileName.indexOf("."));		//확장자 추출
					fileSystemName = EgovFormBasedUUID.randomUUID().toString().toLowerCase()+fileExt;	// 전자정부 UTIL 방법
					reservationMainVo.setRmiBannerRename(fileSystemName); // 파일명 랜덤으로 set
					
					reservationMainVo.setRmiBannerPath(uploadPath);
					
					ufile = new File(EgovWebUtil.filePathBlackList(rootPath + uploadPath + fileSystemName));  
					
					//파일 저장    
					
					file2.transferTo((ufile));
					
				}
			reservationMainSvc.modifyReservationMainImg(reservationMainVo);		//수정
			SVC_MSGE = Message.getMessage("401.message"); //수정성공
		}catch(IllegalStateException e){
			ufile.delete();
			LOGGER.debug("IGNORED: "+e.getMessage());
			SVC_MSGE = Message.getMessage("901.message"); //수정실패
		}catch(IOException e){
			ufile.delete();
			LOGGER.debug("IGNORED: "+e.getMessage());
			SVC_MSGE = Message.getMessage("901.message"); //수정실패
		}catch(Exception e){
			ufile.delete();
			LOGGER.debug("IGNORED: "+e.getMessage());
			SVC_MSGE = Message.getMessage("901.message"); //수정실패
		}
							
		return alert("/boffice/ex/reservationMain/PesMain_Form.do?rmiIdx="+reservationMainVo.getRmiIdx() , SVC_MSGE, model);
	}
	
	/** 삭제 */
	@RequestMapping(value= "/boffice/ex/reservationMain/PesMain_Rmv.do")
	public String pesMainRmv(HttpServletRequest request, 
								@ModelAttribute("ReservationMainVo") ReservationMainVo reservationMainVo
								, ModelMap model ) throws Exception {
		
		HttpSession ses = request.getSession();
		reservationMainVo.setModId((String)ses.getAttribute("SesUserId"));
		
		reservationMainSvc.deleteReservationMainImg(reservationMainVo);
		
		String SVC_MSGE = Message.getMessage("501.message"); //삭제성공
		
		return alert("/boffice/ex/reservationMain/PesMain_List.do", SVC_MSGE, model);
	}

}
