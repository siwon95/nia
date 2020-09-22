/*
 * Created on 2005. 2. 11.
 *
 */
package egovframework.injeinc.common.util;
 
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.cmm.service.EgovProperties;


public class WebUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WebUtil.class);
	
	public static String checkSecure(String str) {

    	str = str.replaceAll("=", "");
    	str = str.replaceAll("'", "");
    	str = str.replaceAll("|", "");
    	str = str.replaceAll("<", "");
    	str = str.replaceAll(">", "");
    	
        return str;
    }  
	public static String checkReq(String req) {
		if ( req != null ){
		req = req.replaceAll("<","&lt;");
		req = req.replaceAll(">","&gt;");
		req = req.replaceAll("&","&amp;");
		req = req.replaceAll("\"","&quot;");
		}else{
			req = "";
		}
		return req;
	}  
	
    public static Object nvl(Object val) {
        if (val == null) {
            return "";
        }

        return val;
    }

    public static String nvl(String val) {
        if (val == null) {
            return "";
        }

        return val;
    }

    public static int strtoint(String val) {
        if (val == null) {
            return 0;
        }
        if (val == "") {
            return 0;
        }

        return Integer.valueOf(val).intValue();
    }

    public static float strtofloat(String val) {
        if (val == null) {
            return 0;
        }
        if (val == "") {
            return 0;
        }

        return Float.valueOf(val).floatValue();
    }

    public static Object nvl(Object val, Object altVal) {
        if (val == null) {
            return altVal;
        }

        return val;
    }

    public static String checked(String val, String val2) {
        return optionSelection(val, val2, "checked=\"checked\"");
    }

    public static String checked(String val) {
        return (val == null) ? "" : "checked=\"checked\"";
    }
    
    public static String checked2(String val, String val2) {
    	String ret = "";
    	if(val != null && val2 != null){
    		ret = val.indexOf(val2)>-1? "checked=\"checked\"" : "";
    	}
        return ret;
    }

    public static String selected(String val) {
        return (val == null) ? "" : "selected=\"selected\"";
    }

    public static String selected(String val, String val2) {
        return optionSelection(val, val2, "selected=\"selected\"");
    }

    public static String selected(int val, int val2) {
        return optionSelection(val + "", val2 + "", "selected=\"selected\"");
    }

    /**
     *  true disabled 
     * 
     * @param condition
     * @return
     */
    public static String disabled(boolean condition) {
        return condition ? "disabled" : "";
    }

    /**
     * ������ true�� disabled �� �����ϴ� �޼ҵ�
     * 
     * @param condition
     * @return
     */
    public static String readOnly(boolean condition) {
        return condition ? "readOnly" : "";
    }

    /**
     * �� ���� ���Ͽ� ������ optString�� return�ϰ� �ٸ��� ""�� �����ϴ� �޼ҵ�.
     * 
     * @param val1
     * @param val2
     * @param optString
     * @return
     */
    private static String optionSelection(String val1, String val2, String optString) {
        if (val1 == null) {
            return "";
        }

        if (val1.equals(val2)) {
            return optString;
        } else {
            return "";
        }
    }

    /**
     * ���ǿ� ��� ��ũ�� ������ִ� �޼ҵ�
     * 
     * @param condition
     * @param url
     * @param content
     * @return
     */
    public static String makeLink(boolean condition, String trueUrl, String falseUrl, String content) {
        return condition ? "<a href=\"" + trueUrl + "\">" + content + "</a>" : "<a href=\"" + falseUrl + "\">" + content + "</a>";
    }

    /**
     * ���ǿ� ��� ������ ������ִ� �޼ҵ�
     * 
     * @param condition
     * @param content
     * @return
     */
    public static String makeContent(boolean condition, String content) {
        return condition ? content : "";
    }

    /**
     * ���ڿ�(str)�� maxLength ��ŭ���� �߶� "..." ������ �ִ� �޼ҵ�
     * 
     * @param str
     * @param maxLength
     * @return
     */
    public static String wjawjawja(String str, int maxLength) {
    	if(str != null){
	        if (str.length() > maxLength) {
	            return str.substring(0, maxLength) + "..."; // ����� �̻��ϸ� "��"�� ��ü...
	        } else {
	            return str;
	        }
    	}else{
    		return str;	
    	}
    }
    public static String wjawjawja2(String str, int maxLength) {

    	int int_byte = 0;
    	String returnStr = "";
    	if(str != null){
	    	if(str.getBytes().length > maxLength){
		    	for(int i=0;i<maxLength; i++){
		    		String temp = "";
		    		if(str != null){
		    			temp = ""+str.charAt(i);
		    		}
		    		int_byte = int_byte+temp.getBytes().length;
		    		
		    		if(int_byte <= maxLength){
		    			returnStr = returnStr + temp;
		    		} else {
		    			break;
		    		}
		    	}
	 
	    		return returnStr+"...";
	    	
	    	} else {
	    		return str;
	    	}
    	}else{
    		return str;
    	}
    }
    /**
     * ���ڿ�(str)�� maxLength ��ŭ���� �߶� "..." ������ �ְ� �� ���ڿ��� label�� title�� �����ؼ� �������ִ�  �޼ҵ�
     * 
     * @param str
     * @param maxLength
     * @return
     */
    public static String wjawjawjaWithLabel(String str, int maxLength) {
        if(str != null){
	    	if (str.length() > maxLength) {
	            return "<label title=\"" + str + "\">" + str.substring(0, maxLength) + "...</label>"; // ����� �̻��ϸ� "��"�� ��ü...
	        } else {
	            return str;
	        }
        }else{
        	return str;
        }
    }
    
    public static String toAstar(String str) {
        String retStr = null;
        if(str != null){
        	retStr = str.replaceAll(".", "*");
        }
        return retStr;
    }
    
    /**
     * ���ڿ�(str)�� �ѱ� Encoding�� �Ͽ� ������ �ִ� �޼ҵ�
     * 
     * @param String str
     * @return String
     */
    public static String toEucKr(String str) {
    	try{
    		if(str != null)
    			str = new String(str.getBytes("8859_1"), "EUC-KR");
    		else str = "";
    	} catch(UnsupportedEncodingException e) {
    		LOGGER.debug("IGNORED: "+e.getMessage());
    	}
    	return str;
    }
    
    
    /**
     * ���ڿ��� ���ϴ� ���̸�ŭ ���� �߶��ִ� �޼ҵ�
     * @param str
     * @param maxLength
     * @return
     */
    public static String[] cutStringByLength(StringBuffer str, int maxLength) {
        String[] strArr = null;
        int divideCnt = 0;
        if(str != null){
        	divideCnt = str.length() / maxLength;
        }
        if(str != null){
	        if( (str.length() % maxLength) > 0 ){
	            divideCnt++;
	        }
        }
        if(str != null){
	        if (str.length() > maxLength) {
	            strArr = new String[divideCnt];
	            for(int loofCnt = 0 ; loofCnt < divideCnt ; loofCnt++){
	                if( loofCnt == 0 ){
	                	if(str != null){
	                		strArr[loofCnt] = new String(str.substring(0 , maxLength));
	                	}
	                }else if( (loofCnt+1) == divideCnt ){
	                	if(str != null){
	                		strArr[loofCnt] = new String(str.substring(loofCnt * maxLength));
	                	}
	                }else{
	                	if(str != null){
	                		strArr[loofCnt] = new String(str.substring(loofCnt * maxLength , (loofCnt+1)*maxLength));
	                	}
	                }
	                
	            }
	            return strArr;
	        } else {
	            strArr = new String[1];
	            strArr[0] = new String(str);
	            return strArr;
	        }
        }else{
        	return strArr;
        }
    }
    
    /**
     * ���ڿ��� ����Ʈũ��� �߶� �迭�� ����
     * @param str
     * @param byteLength
     * @return
     */
    public static String[] cutStringByByte(StringBuffer str, int byteLength){
        String[] strArr = null;
        byte[] strByte = null;
        if(str != null){
        	strByte = str.toString().getBytes();
        }
        
        //System.out.println("Byte Length of Content String = " + strByte.length);
        
        int divideCnt = strByte.length / byteLength;
        if(str != null){
	        if( (str.length() % byteLength) > 0 ){
	            divideCnt++;
	        }
        }
        if(str != null){
	        if (str.length() > byteLength) {
	            strArr = new String[divideCnt];
	            for(int loofCnt = 0 ; loofCnt < divideCnt ; loofCnt++){
	                if( loofCnt == 0 ){										
	                    strArr[loofCnt] = new String(strByte, 0, byteLength);
	                }else if( (loofCnt+1) == divideCnt ){
	                    strArr[loofCnt] = new String(strByte, loofCnt * byteLength, strByte.length - loofCnt * byteLength);
	                }else{
	                    strArr[loofCnt] = new String(strByte, loofCnt * byteLength , byteLength);
	                }
	                
	            }
	            return strArr;
	        } else {
	            strArr = new String[1];
	            strArr[0] = new String(str);
	            return strArr;
	        }
        }else{
        	return strArr;
        }
   }
    
    /**
     * �����ȣ�� ���ڸ��� �߶� �迭�� ��ȯ�Ѵ�.
     * @param zipcode
     * @return
     */
    public static String[] separateZipcode(String zipcode){
        
        String[] zipcodeArr = new String[2];
        if(zipcode != null){
        	zipcodeArr = zipcode.split("-");
        }
//        zipcode = zipcode.replaceAll("-","");
////        zipcode = zipcode.replaceAll("","");
//        
//        zipcodeArr[0] = zipcode.substring(0,3);
//        zipcodeArr[1] = zipcode.substring(3);
        
        return zipcodeArr;
    }
    
    /**
     * ��ȭ��ȣ�и��ڸ� �����ϰ� ������ȣ/����/��ȣ
     * @param tel
     * @return
     */
    public static String[] separateTelephone(String tel){
        String[] telArr = new String[3];
        if(tel != null){
        	telArr = tel.split("-");
        }
        
//        tel = tel.replaceAll("-","");
//        tel = tel.replaceAll("/","");
//  
//        telArr[0] = tel.substring(0,3);
//        telArr[1] = tel.substring(3,7);
//        telArr[2] = tel.substring(7);

		return telArr;
    }
    
    /**
     * HTML Tag ���� 1500�� ���� --;
     * ����ǥ������ ��ü 
     * @param str
     * @return
     */
    public static String removeHtml(String str) {
		StringBuffer strBufContent = new StringBuffer(str);
		return removeHtml(strBufContent);
	}
    
    public static String removeHtml(StringBuffer targetContents) {
        boolean flag=false;
        StringBuffer str = new StringBuffer();
        String tmp="";
        String conts = "";
        if(targetContents != null){
	        for (int i=0; i < targetContents.length(); i++) {
	            tmp = targetContents.substring(i, i+1);
	            flag = flag||"<".equals(tmp);
	            if (!flag) str.append(tmp);
	            if (">".equals(tmp)) flag=false;
	        }
        }
        

        
        int tmpInt = str.toString().length();
        if ( tmpInt > 1500 ) {
        	conts = str.toString().substring(0,1485)+"..";
        	//conts = str.toString().substring(15,1500)+"..";
        }else {
        	conts = str.toString().replaceAll("No title", "");
        }

        return conts;
    }
    
    /**
     * �������� ��Ÿ�� �±� ���� 
     * @param targetContents
     * @return
     */
    public static String removeEditorTag(StringBuffer targetContents) {
        boolean flag=false;
        StringBuffer str = new StringBuffer();
        String tmp1="";
        String tmp2="";
        String conts = "";
        if(targetContents != null){
	        for (int i=0; i < targetContents.length()-4; i++) {
	            tmp1 = targetContents.substring(i, i+4);
	            tmp2 = targetContents.substring(i, i+1);
	            flag = flag||".VBN".equalsIgnoreCase(tmp1);
	            if (!flag) str.append(tmp1);
	            if ("}".equalsIgnoreCase(tmp2)) flag=false;
	        }
        }
        int tmpInt = str.toString().length();
        if ( tmpInt > 1500 )
        	conts = str.toString().substring(15,1500)+"..";
        else
        	conts = str.toString().replaceAll("No title", "");
                
        return conts;
    }
       
    /**
     * replace ���ڿ� ���÷��̽�
     * @param str, pattern, replace
     * @return result
     */
    public static String replace(String str, String pattern, String replace) 
    { 
            int s = 0;  
            int e = 0; 
            StringBuffer result = new StringBuffer(); 
            if(pattern != null){
            	if(str != null){
		            while ((e = str.indexOf(pattern, s)) >= 0)
		            { 
		            		if(str != null){
		            			result.append(str.substring(s, e));
		            		}
		                    result.append(replace); 
		                    if(pattern != null){
		                    	s = e+pattern.length();
		                    }
		            } 
            	}
            }
            if(str != null){
            	result.append(str.substring(s)); 
            }
            return result.toString(); 
    } 
    
    /**
     * encString ���ڿ� ��ȣȭ
     * @param 
     * @return result
     */
    public static String encString(String str) 
    { 
        return str; 
    } 
 
    /**
     * encString ���ڿ� ��ȣȭ
     * @param 
     * @return result
     */
    public static String decString(String str) 
    { 
        return str; 
    } 

	
	public static boolean checkRefer(HttpServletRequest request, String url) {

		String refer = request.getHeader("referer");
		if(refer == null) {
			return false;
		}
		
		if(refer.startsWith(url)) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * ����URL�� üũ
	 * @param request
	 * @param url
	 * @return
	 */
	public static boolean checkRefer(HttpServletRequest request, String[] url) {
		
		String refer =  request.getHeader("referer");
		
		if(refer == null) return false;
		
		int first = refer.lastIndexOf("/") + 1;
		int last =  refer.lastIndexOf("?");
		
		String referUrl = (last == -1) ? refer.substring(first) : refer.substring(first,last);
		
		int k = 0;
		if(url != null){
			for(int i = 0; i < url.length; i++){
				if(referUrl.equals(url[i])) k++;
			}
		}
		
		if(k == 0) return false;
		return true;
	}
	
	
	/**
	 * 2011.08.04 ������ ���Ǽ� ��� ����
	 * @param request
	 * @param url
	 * @return
	 */
    public static String makeInput(String type, String name, String value){    	
        return makeInput(type, name, value,"",""); 
    } 
    
    public static String makeInput(String type, String name, String value,String size,String maxsize){
    
    	String str = "";
    	
        return str; 
    }     

	public static Boolean Upload_Filter(String fileName,String avaext[]){
		String file_ext = fileName == null ? "" : fileName.substring(fileName.indexOf('.') + 1).toLowerCase();//���� Ȯ���ڸ� �����ϱ� ����
		//String file_ext = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();//Ȯ���ڸ� �˻�

		Boolean result = false;

		// ���ε� ���� Ȯ���� ����Ʈ
		String bad_ext[] = {"php3","php4","asp","jsp","php","html","htm","inc","js","pl", "cgi","java","exe"}; 
		// ����� Ȯ���� üũ
		if(avaext != null){
			for(int i=0; i<avaext.length;i++) {
				if( avaext[i].equals(file_ext)){
					 result = true;
					 break;
				}
			}
		}
		// ���ε� ���� Ȯ���� üũ
		for(int i=0; i<bad_ext.length;i++){
			if( bad_ext[i].equals(file_ext)){
				return false;
			}
		}
		// IIS �Ľ� ���� üũ
		if(fileName != null){
			if(fileName.contains(";") ){
				return false;
			}
		}
		// Ȯ���� ���� üũ (�� test.jsp. )
		if(file_ext.length() == 0 ){
			return false;
		}
		//         ���� ��� ���� üũ
		if(fileName != null){
			if(fileName.contains("..") ){
				return false;
			}
		}
		return result;
	}
	
	public static boolean isMobile(HttpServletRequest request) {

		boolean isMobile = false;
		String agent = request.getHeader("USER-AGENT");
		String[] mobileos = {"iPhone","iPod","Android","BlackBerry","Windows CE","Nokia","Webos","Opera Mini","SonyEricsson","Opera Mobi","IEMobile"};
		
		for(int i=0 ; i<mobileos.length ; i++) {
			if(agent.indexOf(mobileos[i]) > -1 ) {
				// 모바일로 접근했을 때
				isMobile = true;
				break;
			}
		}
		
		return isMobile;
	}

	public static boolean isAndroid(HttpServletRequest request) {
		
		boolean isMobile = false;
		String agent = request.getHeader("USER-AGENT");
		String[] mobileos = {"Android"};
		
		for(int i=0 ; i<mobileos.length ; i++) {
			if(agent.indexOf(mobileos[i]) > -1 ) {
				// 모바일로 접근했을 때
				isMobile = true;
				break;
			}
		}
		
		return isMobile;
	}

	public static boolean isIOS(HttpServletRequest request) {
		
		boolean isMobile = false;
		String agent = request.getHeader("USER-AGENT");
		String[] mobileos = {"iPhone","iPod"};
		
		for(int i=0 ; i<mobileos.length ; i++) {
			if(agent.indexOf(mobileos[i]) > -1 ) {
				// 모바일로 접근했을 때
				isMobile = true;
				break;
			}
		}
		
		return isMobile;
	}
	
	
	public String readFile(String filePath, int startNum) {
        BufferedReader br = null;
        StringBuffer sb = new StringBuffer(); // 테스트용 변수
        
        String webRoot = EgovProperties.getProperty("WasServer.ROOT_PATH");
        
        String strContent = "";
        
         try {
            br = new BufferedReader(new FileReader(webRoot+filePath));
            String line = null;
            int lineCount = 0;
            while ((line = br.readLine()) != null) {
                //System.out.println(line);
                //sb.append(line);
            	if(lineCount>=startNum){
            		strContent+=line+"\n";
            	}
                lineCount++;
            }
 
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        } finally {
            try { 
                if (br!=null) 
                    br.close(); 
            } catch (Exception e) {}
        }
         
         return strContent;
    }

}