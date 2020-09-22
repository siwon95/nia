package egovframework.cmm;

import org.apache.log4j.Logger;

import egovframework.rte.fdl.cmmn.exception.handler.ExceptionHandler;

/**
 * @Class Name : EgovComExcepHndlr.java
 * @Description : 공통서비스의 exception 처리 클래스
 * @Modification Information
 *
 *    수정일       수정자         수정내용
 *    -------        -------     -------------------
 *    2009. 3. 13.     이삼섭
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 13.
 * @version
 * @see
 *
 */
public class EgovComExcepHndlr implements ExceptionHandler {

	private Logger logger = Logger.getLogger(EgovComExcepHndlr.class);

	/*
	 * @Resource(name = "otherSSLMailSender") private SimpleSSLMail mailSender;
	 */
	/**
	 * 발생된 Exception을 처리한다.
	 */
	public void occur(Exception ex, String packageName) {

		logger.error(packageName, ex);
	}
	
}