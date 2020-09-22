package egovframework.cmm;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.util.WebUtils;

import egovframework.cmm.service.AuthoritySvc;
import egovframework.cmm.vo.AuthorityVo;
import egovframework.injeinc.common.util.EgovStringUtil;

@Controller
public class AuthorityCtr extends CmmLogCtr {

	@Resource(name = "AuthoritySvc")
	private AuthoritySvc authoritySvc;

	@Autowired(required = true)
	private MappingJackson2JsonView jsonView;

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/cmm/AuthorityList.do")
	public void AuthorityListAx(HttpServletRequest request,HttpServletResponse response,@ModelAttribute("AuthorityVo") AuthorityVo authorityVo,ModelMap model) throws Exception {

		try {

			List retrieveListAuthority = null;

			String SesMgrIdx = EgovStringUtil.isNullToString(WebUtils.getSessionAttribute(request, "SesUserRoleIdx"));
			String SesUserDeptNm = EgovStringUtil.isNullToString(WebUtils.getSessionAttribute(request, "SesUserDeptNm"));
			String SesUserPermCd = EgovStringUtil.isNullToString(WebUtils.getSessionAttribute(request, "SesUserPermCd"));
			String authorityCode = EgovStringUtil.isNullToString(WebUtils.getSessionAttribute(request, "AuthorityCode"));

			String searchCode = request.getParameter("code");

			if (EgovStringUtil.isEmpty(searchCode)) {

				if (!authorityCode.equals("")) {
					authorityVo.setMmCd(authorityCode);
					retrieveListAuthority = authoritySvc.retrieveListAuthorityMenu(authorityVo);
				}

			} else {
				while (true) {
					String UprCd = authoritySvc.retrieveUprCd(searchCode);

					if (UprCd == null)
						break;

					searchCode = UprCd;
				}
				authorityVo.setMmCd(searchCode);

				if (SesUserPermCd.equals("05010000")) {
					retrieveListAuthority = authoritySvc.retrieveListAuthorityMenuMaster(authorityVo);
				} else {
					authorityVo.setMgrIdx(SesMgrIdx);
					retrieveListAuthority = authoritySvc.retrieveListAuthorityMenu(authorityVo);
				}

			}

			HashMap<String, Object> jsonMap = new HashMap<String, Object>();

			jsonMap.put("AuthorityList", retrieveListAuthority);
			jsonMap.put("deptNm", SesUserDeptNm);
			jsonMap.put("AuthorityCode", searchCode);

			jsonView.render(jsonMap, request, response);

		} catch (Exception e) {
			errorLog("TreeFormAjax ERROR!", e);
		}
	}
	
}