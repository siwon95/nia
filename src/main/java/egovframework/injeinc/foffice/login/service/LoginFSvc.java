package egovframework.injeinc.foffice.login.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

/**
 * 공통로그인 서비스
 * @author 공통서비스 개발팀 이동열
 */


@Service
public interface LoginFSvc {
	
	/** 아이디중복체크 */
	public HashMap<String, Object> retrieveListloginF(HashMap<String, String> param) throws Exception;
	
	/** 아이디중복체크 */
	public HashMap<String, Object> retrieveListOutloginF(HttpServletRequest request, HashMap<String, String> param) throws Exception;
	
	/** di 조회 후 정보가져오기 */
	public HashMap<String, Object> retrieveListloginListDiF(HashMap<String, String> param) throws Exception;
	
	/** 로그등록 */
	public void registUserLog(HashMap<String, String> param) throws Exception;
	
	/** 비밀번호 5회 오류자 30분 이상 로그인불가 확인 */
	public int retrieveLoginFfalseCnt(HashMap<String, String> param) throws Exception;
	
	/** 로그인 실패 횟수 */
	public int retrieveLoginFSucYnCnt(HashMap<String, String> param) throws Exception;
	
	/** 탈퇴회원 여부 조회 */
	public int retrieveLoginFOutUserCnt(HashMap<String, String> param) throws Exception;
	
	/** 오늘 포인트 획득 유무 조회 */
	public int retrieveTodayPointCnt(String cuId) throws Exception;
	
}