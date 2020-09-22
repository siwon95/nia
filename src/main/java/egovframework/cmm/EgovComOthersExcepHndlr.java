package egovframework.cmm;

import org.apache.log4j.Logger;

import egovframework.rte.fdl.cmmn.exception.handler.ExceptionHandler;

public class EgovComOthersExcepHndlr implements ExceptionHandler {

	private static final Logger logger = Logger.getLogger(EgovComOthersExcepHndlr.class);

	public void occur(Exception exception, String packageName) {
		// log.debug(" EgovServiceExceptionHandler run...............");
		logger.error(packageName, exception);
	}
	
}