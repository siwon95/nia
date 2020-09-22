package egovframework.injeinc.foffice.ex.mainImage.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.injeinc.boffice.ex.mainImage.service.MainImageSvc;
import egovframework.injeinc.boffice.ex.mainImage.vo.MainImage;
import egovframework.injeinc.boffice.ex.mainImage.vo.MainImageVO;


@Controller
public class MainImageFCtr {

    @Resource(name = "mainImageService")
    private MainImageSvc mainImageService;

    
    /**
	 * 순서를 바꾼다
	 * @param mainImage - 메인이미지 model
	 */
	@RequestMapping(value="/site/{strDomain}/ex/mainImage/MainImage_All.do")
	public String imageSortMod(HttpServletRequest request,
										@ModelAttribute("MainImage") MainImage mainImage,
										@PathVariable("strDomain") String strDomain,
                                       ModelMap model) throws Exception {
		List<MainImageVO> result = mainImageService.retrieveListMainImageAll();
		if(model != null){
			model.addAttribute("mainImage", result);
		}
		
		return "injeinc/foffice/ex/mainImage/main_Image_All";
	}
}
