package egovframework.injeinc.common.pg.vo.enums;

public enum PayTypeEnum {
	/**신용카드*/
	SC0010("신용카드"),
	/**계좌이체*/
	SC0030("계좌이체"),
	/**무통장*/
	SC0040("무통장"),
	/**휴대폰*/
	SC0060("휴대폰"),
	/**유선전화결제*/
	SC0070("유선전화결제"),
	/**OK캐쉬백*/
	SC0090("OK캐쉬백"),
	/**문화상품권*/
	SC0111("문화상품권"),
	/**게임문화상품권*/
	SC0112("게임문화상품권"),
	/**도서문화상품권*/
	SC0113("도서문화상품권"),
	/**모바일T머니*/
	SC0220("모바일T머니"),
	/**선택안함*/
	UNSELECTED("선택안함"),
	MONEY("현금")
	;
	
	private String codeName;
	
	PayTypeEnum(String codeName){
		this.codeName = codeName;
	}

	public String getCodeName() {
		return codeName;
	}
}
