package egovframework.injeinc.foffice.login.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import egovframework.cmm.EgovMessageSource;
import egovframework.cmm.Message;
import egovframework.injeinc.common.util.XecureUtil;
import egovframework.injeinc.foffice.login.dao.LoginFDao;
import egovframework.injeinc.foffice.login.service.LoginFSvc;
import egovframework.injeinc.foffice.login.vo.LoginFVo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 공통로그인 서비스 
 * @author 공통서비스 개발팀 이동열
 */


@Service("LoginFSvc")
public class LoginFImpl extends EgovAbstractServiceImpl implements
 LoginFSvc {

    @Resource(name="LoginFDao")
    private LoginFDao longinFDao;
    
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
	/** 로그인 액션 */
	  public HashMap<String, Object> retrieveListloginF(HashMap<String, String> param) throws Exception {
			HashMap<String, Object> output = new HashMap<String, Object>();
			
			//목록
			LoginFVo result =  longinFDao.selectLogin(param);
			
		
			if (result == null) {
				output.put("SVC_CODE", Message.getMessage("610.code"));
				output.put("SVC_MSGE", Message.getMessage("610.message"));
				return output;	
			
			}else {
				longinFDao.updateUserLog(result.getCuIdx());		//최종로그인 시간 update
//					List resultGroup =  longinFDao.selectLoginGroup(result.getCuIdx());
				
				output.put("SVC_CODE", Message.getMessage("100.code"));
				output.put("cuIdx", result.getCuIdx());
				output.put("cuId", result.getCuId());
				output.put("cuName", result.getCuName());
				output.put("ownAuth", result.getOwnAuth());
				output.put("email", result.getEmail());
				output.put("zip", result.getZip());
				output.put("addr1", result.getAddr1());
				output.put("addr2", result.getAddr2());
				output.put("telNum", result.getTelNum());
				output.put("hpNum", result.getHpNum());
				output.put("regDt", result.getRegDt());
				output.put("pwdChgDt", result.getPwdChgDt());
				System.out.println(" birth ::  "+result.getBirth());
				output.put("birth", result.getBirth());
				output.put("coNum", result.getCoNum());	
				output.put("sex", result.getSex());
				output.put("slibno", result.getSlibno());
				output.put("teamName", result.getTeamName());
				output.put("numGubun", result.getNumGubun());
				
//					output.put("userGroup", resultGroup);
				
				return output;		
			}
			
		}

	public void registUserLog(HashMap<String, String> param)
			throws Exception {
		longinFDao.insertUserLog(param);
	}
	
	public HashMap<String, Object> retrieveListloginListDiF(HashMap<String, String> param) throws Exception {
		HashMap<String, Object> output = new HashMap<String, Object>();
		
		try {
			//목록
			ArrayList<Object> listDao = (ArrayList<Object>) longinFDao.selectLoginListAx(param);
			output.put("rowDataList", listDao);
			
			if(listDao.size() == 0) {
				output.put("SVC_CODE", egovMessageSource.getMessage("301.code"));
				output.put("SVC_MSGE", egovMessageSource.getMessage("301.message"));
				return output;				
			}
		
			
		} catch (RuntimeException e) {
			output.put("SVC_CODE", egovMessageSource.getMessage("901.code"));
			output.put("SVC_MSGE", egovMessageSource.getMessage("901.message"));
		} catch (Exception e) {
			output.put("SVC_CODE", egovMessageSource.getMessage("901.code"));
			output.put("SVC_MSGE", egovMessageSource.getMessage("901.message"));
		}
		
		return output;
	}

	public int retrieveLoginFfalseCnt(HashMap<String, String> param)
			throws Exception {
		return longinFDao.selectLoginFfalseCnt(param);
	}

	public int retrieveLoginFSucYnCnt(HashMap<String, String> param)
			throws Exception {
		return longinFDao.selectLoginFSucYnCnt(param);
	}

	public HashMap<String, Object> retrieveListOutloginF(
			HttpServletRequest request, HashMap<String, String> param)
			throws Exception {
		
		HashMap<String, Object> output = new HashMap<String, Object>();
		String loginSucYn = "";
		String flag = "";
		String logKdCd = "L";
		HttpSession ses = request.getSession();
		
		int loginFalseCnt = longinFDao.selectLoginFfalseCnt(param);
		
		if(loginFalseCnt > 0){
			output.put("loginFalseCnt", loginFalseCnt);
		}else{
			try {
				
				String cuId = param == null ? "" : param.get("cuId");
				String cuPwd = param == null ? "" :  XecureUtil.DigestX64(param.get("cuPwd"));	//비밀번호 암호화
				
				HashMap<String, String> param2 = new HashMap<String, String>();
				param2.put("cuId",cuId);
				param2.put("cuPwd",cuPwd);
				
				//목록
				LoginFVo result =  longinFDao.selectLogin(param2);
			
				if (result == null) {
					output.put("SVC_CODE", Message.getMessage("610.code"));
					output.put("SVC_MSGE", Message.getMessage("610.message"));
					
					output.put("flag","false");
					//로그인 실패 횟수
					int loginFCnt = longinFDao.selectLoginFSucYnCnt(param);
					
					if(loginFCnt == 4){
						logKdCd = "F";
					}
					output.put("loginFCnt", loginFCnt);
					loginSucYn = "N";
				}else {
					List resultGroup =  longinFDao.selectLoginGroup(result.getCuIdx());
					
					ses.setAttribute("ss_idx",   result.getCuIdx());
					ses.setAttribute("ss_id",   result.getCuId());
					ses.setAttribute("ss_name", result.getCuName());
					ses.setAttribute("ss_level","7");
					ses.setAttribute("ss_dupkey", result.getOwnAuth());
					ses.setAttribute("ss_email", result.getEmail());
					ses.setAttribute("ss_zip", result.getZip());
					ses.setAttribute("ss_addr1", result.getAddr1());
					ses.setAttribute("ss_addr2", result.getAddr2());
					ses.setAttribute("ss_tel", result.getTelNum());
					ses.setAttribute("ss_hp", result.getHpNum());
					ses.setAttribute("ss_birth", result.getBirth());
					
					
					output.put("SVC_CODE", Message.getMessage("100.code"));
					output.put("cuIdx", result.getCuIdx());
					output.put("cuId", result.getCuId());
					output.put("cuName", result.getCuName());
					output.put("ownAuth", result.getOwnAuth());
					output.put("email", result.getEmail());
					output.put("zip", result.getZip());
					output.put("addr1", result.getAddr1());
					output.put("addr2", result.getAddr2());
					output.put("telNum", result.getTelNum());
					output.put("hpNum", result.getHpNum());
					output.put("regDt", result.getRegDt());
					output.put("pwdChgDt", result.getPwdChgDt());
					output.put("userGroup", resultGroup);
					output.put("flag","true");
					output.put("loginFCnt", 0);
					loginSucYn = "Y";
					
				}
				
			} catch (RuntimeException e) {
				throw e;
			} catch (Exception e) {
				throw e;
			}
		}
		
		HashMap<String, String> logParam = new HashMap<String, String>();
		
		logParam.put("cuId", param.get("cuId"));
		logParam.put("logKdCd", logKdCd);
		
		String ip = request.getHeader("X-FORWARDED-FOR");
	       if (ip == null){
	    	   
	           ip = request.getRemoteAddr();
	           logParam.put("ip", ip);
	       }
	       
		logParam.put("loginSucYn", loginSucYn);
		
		longinFDao.insertUserLog(logParam);
		return output;
	}

	public int retrieveLoginFOutUserCnt(HashMap<String, String> param) throws Exception {
		return longinFDao.selectLoginFOutUserCnt(param);
	}

	public int retrieveTodayPointCnt(String cuId) throws Exception {
		return longinFDao.selectTodayPointCnt(cuId);
	}

}