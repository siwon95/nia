package egovframework.injeinc.boffice.sy.board.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import egovframework.cmm.CmmLogCtr;
import egovframework.cmm.Message;
import egovframework.injeinc.boffice.sy.board.service.BoardSvc;
import egovframework.injeinc.boffice.sy.board.service.CmsBbsSvc;
import egovframework.injeinc.boffice.sy.board.vo.BoardVo;
import egovframework.injeinc.boffice.sy.board.vo.ConfigPropertyVo;
import egovframework.injeinc.boffice.sy.code.service.CmmCodeSvc;
import egovframework.injeinc.boffice.sy.group.service.GroupConfigSvc;
import egovframework.injeinc.common.util.EgovStringUtil;
import egovframework.injeinc.boffice.sy.board.vo.CmsBbsVo;

@Controller
public class BoardCtr extends CmmLogCtr {

	@Resource(name = "CmmCodeSvc")
	private CmmCodeSvc cmmCodeSvc;

	@Resource(name = "BoardSvc")
	private BoardSvc boardSvc;
	
	@Resource(name="GroupConfigSvc")
	private GroupConfigSvc groupConfigSvc;
	
	@Resource(name = "CmsBbsSvc")
	private CmsBbsSvc cmsBbsSvc;
	
	@RequestMapping("/boffice{strDomain}/sy/board/BoardList.do")
	public String BoardList(HttpServletRequest request,@PathVariable("strDomain") String strDomain, @ModelAttribute("BoardVo") BoardVo boardVo, ModelMap model) throws Exception {
		if(strDomain.equals("_noDeco")){
			return "injeinc/boffice/sy/board/board_list_pop";
		}else {
			return "injeinc/boffice/sy/board/board_list";
		}
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/boffice/sy/board/BoardSetPop.do")
	public String BoardSetPop(HttpServletRequest request, @ModelAttribute("BoardVo") BoardVo boardVo, ModelMap model) throws Exception {
		//다른 게시판 설정을 불러오기 위해 cbidx를 copycbidx 값으로 바꿔준다.
		BoardVo resultTmp=null;
		if(boardVo != null && boardVo.getCopyCbIdx() != 0) {
			resultTmp = boardSvc.retrieveBoard(boardVo);
			boardVo.setCbIdx(boardVo.getCopyCbIdx());
		}
		BoardVo result = boardSvc.retrieveBoard(boardVo);
		
		CmsBbsVo cmsBbsVo = new CmsBbsVo();
		
		String bbsTempletGbn = boardVo.getBbsTempletGbn();
		
		if(result != null && result.getCbIdx() != 0) {
			
			ConfigPropertyVo configPropertyVo = new ConfigPropertyVo();
			
			
			
			if(bbsTempletGbn.equals("")){
				System.out.println("bbsTempletGbn1");
				configPropertyVo.setCbIdx(result.getCbIdx());
				
				List propertyList = boardSvc.retrieveListConfigProperty(configPropertyVo);
				model.addAttribute("configPropertyList", propertyList);
			}else{
				System.out.println("bbsTempletGbn2");
				configPropertyVo.setBbsTempletGbn(bbsTempletGbn);
				
				List propertyList = boardSvc.retrieveListConfigPropertyTemplet(configPropertyVo);
				model.addAttribute("configPropertyList", propertyList);
				
				result.setBbsTempletGbn(bbsTempletGbn);
			}
			if(resultTmp!=null && boardVo.getCopyCbIdx() != 0) {
				result.setCbIdx(resultTmp.getCbIdx());
				result.setMgrSiteCd(resultTmp.getMgrSiteCd());
			}
			model.addAttribute("BoardVo", result);
			
			cmsBbsVo.setCbIdx(result.getCbIdx()+"");			
			cmsBbsVo = cmsBbsSvc.selectCmsBbs(cmsBbsVo);
			
			model.addAttribute("CmsBbsVo", cmsBbsVo);
		}

		
		model.addAttribute("gbnList", cmmCodeSvc.retrieveListCmmCode("16010000"));
		model.addAttribute("gbnList2", groupConfigSvc.retrieveListGroupConfigAll());
		model.addAttribute("propertyList", cmmCodeSvc.retrieveListCmmCode("16020000"));
		model.addAttribute("templetList", cmmCodeSvc.retrieveListCmmCode("16050000"));
		model.addAttribute("itemList", cmmCodeSvc.retrieveListCmmCode("25000000"));
		model.addAttribute("bsrcList", cmmCodeSvc.retrieveListCmmCode("bsrc_list"));
		model.addAttribute("bsrcView", cmmCodeSvc.retrieveListCmmCode("bsrc_view"));
		model.addAttribute("bsrcRegist", cmmCodeSvc.retrieveListCmmCode("bsrc_regist"));
		model.addAttribute("fileMaxSize", cmmCodeSvc.retrieveListCmmCode("16070000"));
		
		return "injeinc/boffice/sy/board/board_set";
	}
	
	@RequestMapping("/boffice/sy/board/BoardSetReg.do")
	public String BoardSetReg(HttpServletRequest request, @ModelAttribute("BoardVo") BoardVo boardVo, ModelMap model) throws Exception {
		
		int cbIdx = boardVo.getCbIdx();
		
		if(cbIdx == 0) {
			String SVC_MSGE = "잘못된 접근입니다.";
			return alert("/boffice/sy/board/BoardSetPop.do", SVC_MSGE, model);
		}
		
		String userid = (String) WebUtils.getSessionAttribute(request, "SesUserId");
		boardVo.setRegId(userid);
		boardVo.setModId(userid);
		
		List<ConfigPropertyVo> propertyList = new ArrayList<ConfigPropertyVo>();
		
		String[] labelOrdNoArr = request.getParameterValues("labelOrdNoArr");
		String[] labelNameArr = request.getParameterValues("labelNameArr");
		String[] contentMappingArr = request.getParameterValues("contentMappingArr");
		String[] labelPropGbnArr = request.getParameterValues("labelPropGbnArr");
		String[] itemCodeArr = request.getParameterValues("itemCodeArr");
		String[] labelProvSizeArr = request.getParameterValues("labelProvSizeArr");
		String[] webUseYnArr = request.getParameterValues("webUseYnArr");
		String[] mobileUseYnArr = request.getParameterValues("mobileUseYnArr");
		String[] searchLabelUseYnArr = request.getParameterValues("searchLabelUseYnArr");
		String[] labelCompYnArr = request.getParameterValues("labelCompYnArr");
		String[] viewUseYnArr = request.getParameterValues("viewUseYnArr");
		String[] regUseYnArr = request.getParameterValues("regUseYnArr");
		System.out.println("regUseYnArr:"+regUseYnArr[0]);
			
		for(int i = 0; i < labelOrdNoArr.length; i++) {
			ConfigPropertyVo configPropertyVo = new ConfigPropertyVo();
			configPropertyVo.setCbIdx(cbIdx);
			configPropertyVo.setLabelOrdNo(labelOrdNoArr[i]);
			configPropertyVo.setLabelName(labelNameArr[i]);
			configPropertyVo.setContentMapping(contentMappingArr[i]);
			configPropertyVo.setLabelPropGbn(labelPropGbnArr[i]);
			configPropertyVo.setItemCode(itemCodeArr[i]);
			configPropertyVo.setLabelProvSize(labelProvSizeArr[i]);
			configPropertyVo.setWebUseYn(webUseYnArr[i]);
			configPropertyVo.setMobileUseYn(mobileUseYnArr[i]);
			configPropertyVo.setSearchLabelUseYn(searchLabelUseYnArr[i]);
			configPropertyVo.setLabelCompYn(labelCompYnArr[i]);
			configPropertyVo.setViewUseYn(viewUseYnArr[i]);
			configPropertyVo.setContentMappingL(EgovStringUtil.convertToCamelCase(contentMappingArr[i]));
			configPropertyVo.setRegId(userid);
			configPropertyVo.setRegUseYn(regUseYnArr[i]);
			
			propertyList.add(configPropertyVo);
		}
		
		boardSvc.registBoard(boardVo, propertyList);
		
		CmsBbsVo cmsBbsVo = new CmsBbsVo();
		cmsBbsVo.setCbIdx(cbIdx+"");			
		cmsBbsVo = cmsBbsSvc.selectCmsBbs(cmsBbsVo);
		
		String mode = boardVo.getMode();
		
		if(mode == null){
			mode = "";
		}
		
		cmsBbsVo.setUseYn(request.getParameter("useYn"));
		cmsBbsVo.setPublicYn(request.getParameter("publicYn"));
		cmsBbsVo.setCbName(request.getParameter("cbName"));
		cmsBbsVo.setMgrSiteCd(boardVo.getMgrSiteCd());
		cmsBbsVo.setMode(mode);
		cmsBbsVo.setModId(userid);
		
		if(mode.equals("delete")){
			cmsBbsSvc.deleteCmsBbsList(cmsBbsVo);
		}else{
			cmsBbsSvc.updateCmsBbs(cmsBbsVo);
		}		

		String SVC_MSGE = Message.getMessage("100.message");
		if(mode.equals("delete")){
			return alertParentUrl("/boffice/sy/board/BoardList.do?mgrSiteCd=" + boardVo.getMgrSiteCd(), SVC_MSGE, model);
		}else{
			return alert("/boffice/sy/board/BoardSetPop.do?mgrSiteCd=" + boardVo.getMgrSiteCd() + "&cbIdx=" + cbIdx, SVC_MSGE, model);
		}
	}
}