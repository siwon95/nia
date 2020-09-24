package egovframework.injeinc.site.main;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import egovframework.cmm.CmmLogCtr;
import egovframework.injeinc.boffice.cn.menu.vo.MenuVo;
import egovframework.injeinc.boffice.cn.site.service.SiteSvc;
import egovframework.injeinc.boffice.cn.site.vo.SiteVo;
import egovframework.injeinc.boffice.ex.mainImage.service.MainImageSvc;
import egovframework.injeinc.boffice.ex.reservation.service.ReservationEventSvc;
import egovframework.injeinc.boffice.ex.reservationEtc.service.ReservationEtcSvc;
import egovframework.injeinc.boffice.ex.reservationMain.service.ReservationMainSvc;
import egovframework.injeinc.boffice.hot.manage.service.HotManageSvc;
import egovframework.injeinc.boffice.hot.manage.vo.HotManageVo;
import egovframework.injeinc.boffice.hot.stat.service.HotStatSvc;
import egovframework.injeinc.boffice.main.layerPopup.service.MainLayerPopupSvc;
import egovframework.injeinc.boffice.main.layerPopup.vo.MainLayerPopupVo;
import egovframework.injeinc.boffice.main.popupZone.service.MainPopupZoneSvc;
import egovframework.injeinc.boffice.main.popupZone.vo.MainPopupZoneVo;
import egovframework.injeinc.boffice.main.top.service.TopImagesSvc;
import egovframework.injeinc.boffice.main.top.vo.TopImagesVo;
import egovframework.injeinc.boffice.quick.service.QuickSvc;
import egovframework.injeinc.boffice.quick.vo.QuickVo;
import egovframework.injeinc.boffice.sy.code.service.CmmCodeSvc;
import egovframework.injeinc.common.util.EgovStringUtil;
import egovframework.injeinc.foffice.ex.bbs.service.BbsFSvc;
import egovframework.injeinc.foffice.ex.bbs.vo.BbsFVo;
import egovframework.injeinc.foffice.ex.sns.service.SnsFSvc;
import egovframework.injeinc.foffice.user.service.UserFSvc;
import egovframework.injeinc.foffice.user.vo.UserFVo;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Controller
public class MainCtr extends CmmLogCtr {
	
	@Resource(name = "QuickSvc")
	private QuickSvc quickSvc;
	
	@Resource(name = "SiteSvc")
	private SiteSvc siteSvc;
	
	@Resource(name = "SnsFSvc")
	private SnsFSvc snsFSvc;
	
	@Resource(name = "MainLayerPopupSvc")
	private MainLayerPopupSvc mainLayerPopupSvc;
	
	@Resource(name = "MainPopupZoneSvc")
	private MainPopupZoneSvc mainPopupZoneSvc;
	
	@Resource(name = "TopImagesSvc")
	private TopImagesSvc topImagesSvc;
	
	@Resource(name = "HotManageSvc")
	private HotManageSvc hotManageSvc;
	
	@Resource(name = "HotStatSvc")
	private HotStatSvc hotStatSvc;
	
	@Resource(name = "ReservationEtcSvc")
	private ReservationEtcSvc reservationEtcSvc;
	
	@Autowired
	private BbsFSvc bbsFSvc;
	
	@Resource(name="ReservationMainSvc")
	private ReservationMainSvc reservationMainSvc;
	
	@Resource(name = "mainImageService")
	private MainImageSvc mainImageService;
	
	 @Resource(name = "ReservationEventSvc")
	 private ReservationEventSvc reservationEventSvc;
	
	@Autowired(required=true)
	private MappingJackson2JsonView jsonView;
	
	@Resource(name = "UserFSvc")
	private UserFSvc userSvc;
	
	@Resource(name = "CmmCodeSvc")
	private CmmCodeSvc cmmCodeSvc;
	
	//리스트
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value= "/{strSitePath}/{strDomain}/main.do")
	public String menuList(@PathVariable("strSitePath") String strSitePath,@PathVariable("strDomain") String strDomain, HttpServletRequest request, @ModelAttribute("MenuVo") MenuVo menuVo, ModelMap model) throws Exception {

		
		//상단이미지
		//List<TopImagesVo> topImages = topImagesSvc.retrieveListTopImagesMain();
		
		//model.addAttribute("topImages", topImages);
		
		//인재 포토겔러리 
		String cbIdx = "";
		cbIdx = "416";
		List boardCommonList = bbsFSvc.boardNewRecordNoCache(cbIdx);
		
		
		model.addAttribute("galleryBoard", boardCommonList);
		
		///////////////////팝업존 가져오기 시작/////////////////////
		MainPopupZoneVo mainPopupZoneVo = new MainPopupZoneVo();
		mainPopupZoneVo.setSearchSiteCd(strDomain);
		mainPopupZoneVo.setCode("35001000");
		model.addAttribute("mainPopList1", mainPopupZoneSvc.retrieveListMainPopupZone(mainPopupZoneVo));
		///////////////////팝업존 가져오기 끝/////////////////////
		
		List boardNewContentList = bbsFSvc.boardNewContentList();
		List boardNewNoticeList = bbsFSvc.boardNewNoticeList(); //20200902 전진형 공지사항 최신글 추가
		
		cbIdx = "750";
		boardCommonList = bbsFSvc.boardNewRecordNoCache(cbIdx);
		
		//디지털배움터 메인 홍보관 이미지목록 조회
		if(strDomain.equals("nia")) {
			cbIdx = "1189";
			List mainRelationList = mainImageService.selectListNiaMainRelation(cbIdx);
			model.addAttribute("mainRelationList", mainRelationList);
		}
		
		model.addAttribute("noticeBoard", boardCommonList);
		
		model.addAttribute("sitePath", strSitePath);
		model.addAttribute("siteCd", strDomain);
		
		model.addAttribute("newList", boardNewContentList);
		model.addAttribute("newNoticeList", boardNewNoticeList); //20200902 전진형 공지사항 최신글 추가
		
		
		return "injeinc/site/"+strDomain+"/main";
	}
	

	/** 조회수 증가 */
	@RequestMapping(value = "/site/{strDomain}/View_Cnt_Ax.do")
	public void viewCntAx(@PathVariable("strDomain") String strDomain, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String tiIdx = EgovStringUtil.nullConvert(request.getParameter("tiIdx"));
		topImagesSvc.modifyTopImagesViewCnt(tiIdx);	// 조회수 증가
	}
	
	/** 빠른서비스관리 조회수 증가 */
	@RequestMapping(value = "/site/{strDomain}/Hot_Cnt_Ax.do")
	public void hotCntAx(@PathVariable("strDomain") String strDomain, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String hlIdx = EgovStringUtil.nullConvert(request.getParameter("hlIdx"));
		hotStatSvc.modifyHotViewCnt(hlIdx);	// 조회수 증가
	}
	
	/** 통합예약_리스트 가져오기 */
	@RequestMapping(value="/{strSitePath}/{strDomain}/reservationListAx.do")
	public void reservationListAx(HttpServletRequest request,
									HttpServletResponse response,
									ModelMap model) throws Exception {
		
		/* 통합예약 구분  (A : 전체 / L : 강좌 / F : 시설/대관 / E : 문화/행사 / T : 기타예약)  */
		String rType = request.getParameter("rType"); 
		String listCnt = request.getParameter("listCnt");
		String rOrd = request.getParameter("rOrd");
		/* 통합예약 메인 타입별 검색 ( ING : 접수중 / LOCAL : 지역별 / ORG : 기관별 / TARGET : 대상별 )*/
		String rMainType = request.getParameter("rMainType"); 
		String rMainValue = request.getParameter("rMainValue"); 
		
		if(listCnt == null || "".equals(listCnt)){
			listCnt = "10";
		}
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("rType", rType);
		paramMap.put("listCnt", listCnt);
		paramMap.put("rOrd", rOrd);
		paramMap.put("rMainType", rMainType);
		paramMap.put("rMainValue", rMainValue);
		List<EgovMap> reservationList = reservationMainSvc.selectReservationList(paramMap);
		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", reservationList);
		
		jsonView.render(jsonMap, request, response);
	}
	
	private Document parseXML(InputStream stream) throws Exception{
		DocumentBuilderFactory objDocumentBuilderFactory = null;
		DocumentBuilder objDocumentBuilder = null;
		Document doc = null;
		try{
			objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
			objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();
			doc = objDocumentBuilder.parse(stream);
		}catch(Exception ex){
			throw ex;
		}
		return doc;
	}
	
	public static String escape(String src) {   
        int i;   
        char j;   
        StringBuffer tmp = new StringBuffer();   
        tmp.ensureCapacity(src.length() * 6);   
        for (i = 0; i < src.length(); i++) {   
                j = src.charAt(i);   
                if (Character.isDigit(j) || Character.isLowerCase(j) || Character.isUpperCase(j))   
                        tmp.append(j);   
                else if (j < 256) {   
                        tmp.append("%");   
                        if (j < 16)   
                                tmp.append("0");   
                        tmp.append(Integer.toString(j, 16));   
                } else {   
                        tmp.append("%u");   
                        tmp.append(Integer.toString(j, 16));   
                }   
        }   
        return tmp.toString();   
	}   

	public static String unescape(String src) {   
	        StringBuffer tmp = new StringBuffer();   
	        tmp.ensureCapacity(src.length());   
	        int lastPos = 0, pos = 0;   
	        char ch;   
	        while (lastPos < src.length()) {   
	                pos = src.indexOf("%", lastPos);   
	                if (pos == lastPos) {   
	                        if (src.charAt(pos + 1) == 'u') {   
	                                ch = (char) Integer.parseInt(src   
	                                                .substring(pos + 2, pos + 6), 16);   
	                                tmp.append(ch);   
	                                lastPos = pos + 6;   
	                        } else {   
	                                ch = (char) Integer.parseInt(src   
	                                                .substring(pos + 1, pos + 3), 16);   
	                                tmp.append(ch);   
	                                lastPos = pos + 3;   
	                        }   
	                } else {   
	                        if (pos == -1) {   
	                                tmp.append(src.substring(lastPos));   
	                                lastPos = src.length();   
	                        } else {   
	                                tmp.append(src.substring(lastPos, pos));   
	                                lastPos = pos;   
	                        }   
	                }   
	        }   
	        return tmp.toString();   
	}  
}