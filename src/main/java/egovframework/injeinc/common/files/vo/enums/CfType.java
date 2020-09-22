package egovframework.injeinc.common.files.vo.enums;

public enum CfType {
	
	BASIC("기본"), ATTACH("첨부파일"), TITLEIMG("대표이미지");

	private String codeName;
    
	CfType(String codeName){
        this.codeName = codeName;
    }
	
	public String getCodeName() {
		return codeName;
	}
}
