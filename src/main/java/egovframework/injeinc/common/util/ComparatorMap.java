package egovframework.injeinc.common.util;

import java.util.Comparator;
import java.util.Map;

/*
 * Map의 값을 정렬하기 위한 클래스
 * */
public class ComparatorMap implements Comparator<String> {

	Map<String, Integer> base;

	public ComparatorMap(Map<String, Integer> base) {
		this.base = base;
	}

	public int compare(String a, String b) {
		if (base.get(a) >= base.get(b)) {
			return -1;
		} else {
			return 1;
		}
	}

}
