package egovframework.injeinc.common.util;

import java.util.Comparator;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/*
 * Map의 값을 정렬하기 위한 클래스
 * */
public class ComparatorEGovMapRegDtDesc implements Comparator<EgovMap> {
	 
	/**
	 * 내림차순(DESC)
	 */
	
	public int compare(EgovMap arg0, EgovMap arg1) {
		Long argInt0 = Long.parseLong(String.valueOf(arg0.get("regDt")));
		Long argInt1 = Long.parseLong(String.valueOf(arg1.get("regDt")));
		return argInt0 > argInt1 ? -1 : argInt0 < argInt1 ? 1:0;
	}

}
