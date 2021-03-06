package egovframework.injeinc.common.pg.vo.enums;

public enum PayRefundEnum {
	
//	0000("정상","환불성공","정상 처리 및 재요청에 대한 정상 처리건 응답","환불완료"),
	RF00("정상","환불이 정상 처리되었습니다.","* 구 링크방식으로 환불요청한 경우만 적용 ","환불완료"),
	RF21("환불실패","환불 기간이 초과되었습니다.","10일(mid별 설정값 기준) 이후의 환불 요청건에대해서 실패처리","환불불가"),
	RF05("환불실패","환불 가능한 상점이 아닙니다.","환불 계약이 이루어지지 않은 상점 ","환불불가"),
	RF07("환불실패","해당 상점이 존재하지 않습니다.","상점 아이디가 존재하지 않을 때 재요청 요망","환불불가"),
	RF17("환불실패","환불이 비정상 처리되었습니다.","은행으로부터 환불 에러로 결과가 옴.  LG U+ 에 확인 요청","환불불가"),
	RF24("환불실패","요청하신 건에 대한 임금된 내역이 없습니다","가상계좌 입금이 안된 주문건을 환불요청할 경우 (가상계좌 적용)","환불불가"),
	RFFF("환불실패","알 수 없는 상태","기부분환불된 거래건의 남은 거래액을 환불시, 부분환불 요청을 아니하고 전액환불요청을 하였을 때 응답  ","환불불가"),
	RF02("환불실패(재요청 요망)","은행 서비스 시간이 아닙니다.","은행의 서비스 시간이 아닐 때 재요청 요망","재요청대상(환불가능)"),
	RF08("환불실패(재요청 요망)","상점 한달 환불 가능액을 초과했습니다.","영업사원(AM)이나 기술문의(1544-7772(3)) 요망","재요청대상(환불가능)"),
	RF13("환불실패(재요청 요망)","상점 환불 한도액이 0원 입니다","월한도액 0원 : 영업사원(AM)이나 기술문의(1544-7772(3)) 요망","재요청대상(환불가능)"),
	RF22("환불실패(재요청 요망)","은행 장애로 서비스 중지 상태이오니 잠시후 사용해주세요.","일시적 은행 장애로 재요청 요망","재요청대상(환불가능)"),
	RF28("환불실패(재요청 요망)","환불요청시 환불금액이 0원이거나 누락되었습니다","과오납허용 가상계좌 이용상점중 환불요청시, 환불금액을 보내주지 않은 경우에 대한 응답  (과오납허용 가상계좌에만 적용)","재요청대상(환불가능)"),
	RF26("환불실패(재요청 요망)","환불계좌 형식이 유효하지 않습니다","환불계좌정보(예금주, 은행코드, 계좌번호)  모두  일치하지 않는 경우","재요청대상(환불가능)"),
	RF27("환불실패(재요청 요망)","환불요청금액이 입금금액보다 큽니다","부분환불요청시, 환불요청금액이 (잔여)입금금액 보다 큰 경우   ","재요청대상(환불가능)"),
	RF30("환불실패(재요청 요망)","에스크로거래건은 부분환불이 불가합니다","매매보호 (에스크로) 건은 부분환불이 불가함","재요청대상(환불가능)"),
	RF31("환불실패(재요청 요망)","가상계좌번호는 환불계좌로 이용할 수 없습니다","환불요청시, 실제 예금계좌가 아닌 은행가상계좌번호를 넘겨줄 경우 (예: (단위)농협 가상계좌) ","재요청대상(환불가능)"),
	RF10("오프라인처리","환불 기간이 초과되었습니다.(오프라인처리)","은행별 실시간환불기간 (10일)이 지났지만 상점환불 사용가능일(계약) 이내일때 오프라인환불 처리됨 ","환불진행중"),
	RF09("백그라운드 처리","결제기관 미응답","LG유플러스 사정으로 환불 처리 안됨. 백그라운드로 처리중으로 재요청하면 결과를 보내줌","환불진행중"),
	RF15("백그라운드 처리","환불 요청이 기 수행되었음","환불 기요청건 (환불 성공되지 않은 상태)","환불진행중"),
	RF19("백그라운드 처리","환불 타임아웃","은행 사정으로 환불 처리안됨.  LG유플러스에서 백그라운드 처리중으로 재요청하면 결과를 보내줌","환불진행중"),
	RF23("상태응답","요청하신 건에 대한 환불이 진행중입니다","오프라인 처리중이거나 백그라운드 처리중인모든 LG유플러스 처리중인 건에 대해 재요청시에 대한 응답 (계좌이체 적용)","환불진행중"),
	RF25("상태응답","요청하신 건에 대한 환불이 진행중입니다","오프라인 처리중이거나 백그라운드 처리중인모든 LG유플러스 처리중인 건에 대해 재요청시에 대한 응답  (가상계좌 적용)","환불진행중"),
	C054("에러","부분취소를 할 수 없는 상점입니다.","부분취소를 할 수 없는 상점입니다.","에러"),
	XC01("에러","LGD_TID 필드가 누락되었습니다.","LGD_TID 필드가 누락되었습니다.","에러");


	private String gubun;
	private String msg;
	private String desc;
	private String isRefund;
	
	PayRefundEnum(String gubun,String msg,String desc,String isRefund){
		this.gubun = gubun;
		this.msg = msg;
		this.desc = desc;
		this.isRefund = isRefund;
	}

	public String getGubun() {
		return gubun;
	}

	public String getMsg() {
		return msg;
	}

	public String getDesc() {
		return desc;
	}

	public String getIsRefund() {
		return isRefund;
	}

}
