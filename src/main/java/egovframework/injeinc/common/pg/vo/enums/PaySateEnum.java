package egovframework.injeinc.common.pg.vo.enums;

import java.util.LinkedHashMap;
import java.util.Map;

public enum PaySateEnum {
	
	ALL("전체"),
	W("결제대기"),
	S("결제완료"),
	//C("결제취소"),
	A("환불요청"),
	//B("환불진행"),
	R("환불완료"),
	F("결제실패"),
	N("결제없음")
	//E("환불불가") // 불가사유?
	;
	
	private String codeName;
	
	PaySateEnum(String codeName){
		this.codeName = codeName;
	}

	public String getCodeName() {
		return codeName;
	}
	
	/* 전체 */
	public static Map<PaySateEnum, String> all(){
		Map<PaySateEnum, String> map = new LinkedHashMap<PaySateEnum, String>();
		
		for (PaySateEnum e : PaySateEnum.values()) {
			map.put(e, e.getCodeName());
		}
		return map;
	}
}
