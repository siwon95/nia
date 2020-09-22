package egovframework.cmm;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
/**
 * 교차접속 스크립트 공격 취약성 방지(파라미터 문자열 교체)
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    	--------    ---------------------------
 *   2011.10.10  한성곤          최초 생성
 *
 * </pre>
 */

public class EgovWebUtil {
	public static String clearXSSMinimum(String value) {
		if (value == null || value.trim().equals("")) {
			return "";
		}

		String returnValue = value;

		returnValue = returnValue.replaceAll("&", "&amp;");
		returnValue = returnValue.replaceAll("<", "&lt;");
		returnValue = returnValue.replaceAll(">", "&gt;");
		returnValue = returnValue.replaceAll("\"", "&#34;");
		returnValue = returnValue.replaceAll("\'", "&#39;");
		return returnValue;
	}

	public static String clearXSSMaximum(String value) {
		String returnValue = value;
		returnValue = clearXSSMinimum(returnValue);

		returnValue = returnValue.replaceAll("%00", null);

		returnValue = returnValue.replaceAll("%", "&#37;");

		// \\. => .

		returnValue = returnValue.replaceAll("\\.\\./", ""); // ../
		returnValue = returnValue.replaceAll("\\.\\.\\\\", ""); // ..\
		returnValue = returnValue.replaceAll("\\./", ""); // ./
		returnValue = returnValue.replaceAll("%2F", "");

		return returnValue;
	}

	public static String filePathBlackList(String value) {
		String returnValue = value;
		if (returnValue == null || returnValue.trim().equals("")) {
			return "";
		}

		returnValue = returnValue.replaceAll("\\.\\./", ""); // ../
		returnValue = returnValue.replaceAll("\\.\\.\\\\", ""); // ..\

		return returnValue;
	}

	/**
	 * 행안부 보안취약점 점검 조치 방안.
	 *
	 * @param value
	 * @return
	 */
	public static String filePathReplaceAll(String value) {
		String returnValue = value;
		if (returnValue == null || returnValue.trim().equals("")) {
			return "";
		}

		returnValue = returnValue.replaceAll("/", "");
		returnValue = returnValue.replaceAll("\\", "");
		returnValue = returnValue.replaceAll("\\.\\.", ""); // ..
		returnValue = returnValue.replaceAll("&", "");

		return returnValue;
	}

	public static String filePathWhiteList(String value) {
		return value; // TODO
	}

	public static boolean isIPAddress(String str) {
		Pattern ipPattern = Pattern
				.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");

		return ipPattern.matcher(str).matches();
	}

	public static String removeCRLF(String parameter) {
		String retval = "";
		if (parameter != null) {
			retval = parameter.replaceAll("\r", "").replaceAll("\n", "");
		}
		return retval;
	}

	public static String removeSQLInjectionRisk(String parameter) {
		String retval = "";
		if (parameter != null) {
			retval = parameter.replaceAll("\\p{Space}", "").replaceAll("\\*", "").replaceAll("%", "").replaceAll(";", "").replaceAll("-", "").replaceAll("\\+", "").replaceAll(",", "");
		}
		return retval;
	}

	public static String removeOSCmdRisk(String parameter) {
		String retval = "";
		if (parameter != null) {
			retval = parameter.replaceAll("\\p{Space}", "").replaceAll("\\*", "").replaceAll("|", "").replaceAll(";", "");
		}
		return retval;
	}

	/*
	 * public static void main(String[] args) { String test = null;
	 * 
	 * test = "<script language='javascript' encoding=\"utf-8\">q&a</script>";
	 * System.out.println("clearXSSMinimum() Test"); System.out.println(test);
	 * System.out.println("=>"); System.out.println(clearXSSMinimum(test));
	 * System.out.println();
	 * 
	 * test = "/a/b/c../..\\"; System.out.println("clearXSSMaximum() Test");
	 * System.out.println(test); System.out.println(" =>");
	 * System.out.println(clearXSSMaximum(test)); System.out.println();
	 * 
	 * test = "/a/b/c/../../../..\\..\\";
	 * System.out.println("filePathBlackList() Test"); System.out.println(test);
	 * System.out.println("=>"); System.out.println(filePathBlackList(test));
	 * System.out.println();
	 * 
	 * test = "192.168.0.1"; System.out.println("isIPAddress() test");
	 * System.out.println("IP : " + test + " => " + isIPAddress(test));
	 * 
	 * test = "abc def*%;-+,ghi";
	 * System.out.println("removeSQLInjectionRisk() test");
	 * System.out.println(test + " => " + removeSQLInjectionRisk(test)); } //
	 */

	/**
	 * 썸네일 이미지 만들기(2013.12.14)
	 * 
	 * <pre>
	 * Eclipse error 처리법: 
	 * Window-Preferences -
	 *  Java-Compiler-Errors/Warnings -
	 *   Deprecated and restricted API - 
	 *    <b>Forbidden reference (access rules): Ignore</b>
	 * </pre>
	 * 
	 * @see <a href="https://github.com/thebuzzmedia/imgscalr">Simple Java
	 *      image-scaling library implementing Chris Campbell's incremental
	 *      scaling algorithm as well as Java2D's "best-practices" image-scaling
	 *      techniques.</a>
	 * @throws IOException
	 */

	public static void makeThumbNail(String orgFilePath,String thumbNailFilePath, int width, int height ) throws IOException {
			//20200811 확장자명 동적으로 가져오게함 ajhwan
			String fileExtsn= "";
			
			String [] fileExtsnLength = orgFilePath.split("[.]");
			fileExtsn = fileExtsnLength[fileExtsnLength.length-1];
		
			int orgWidth = 0;
			int orgHeight = 0;
			BufferedImage orgImage = ImageIO.read(new File(orgFilePath));
			orgWidth = orgImage.getWidth();
			orgHeight = orgImage.getHeight();
			int changeWidth = 0;
			int changeHeight = 0;
			int cropX = 0; //자르기 X좌표
			int cropY = 0; //자르기 Y좌표
			if(orgWidth<=width && orgHeight<=height){ //가로 세로 사이즈가 썸네일 사이즈보다 작은경우
				changeWidth = orgWidth;
				changeHeight = orgHeight;
				width = orgWidth;
				height = orgHeight;
				
			}else if(orgWidth<=width && orgHeight>height){ // 가로사이즈는 원본사이즈보다 작고 세로 사이즈는 원보사이즈보다 클경우
				changeWidth = orgWidth;
				changeHeight = height;
				width = orgWidth;
				
				cropY = (orgHeight - changeHeight)/2;
				
			}else if(orgHeight<=height && orgWidth>width){ // 세로사이즈는 원본사이즈보다 작고 가로 사이즈는 원보사이즈보다 클경우
				changeWidth = width;
				changeHeight = orgHeight;
				height = orgHeight;
				
				cropX = (orgWidth - changeWidth)/2;
				
			}else if((float)orgWidth/(float)orgHeight >  (float)width/(float)height){ //가로 비율이 클경우
				changeWidth = (int)((float)width * ((float)orgHeight/(float)height));
				changeHeight = orgHeight;
				
				cropX = (orgWidth - changeWidth)/2;
			}else{ //세로 비율이 클경우
				changeWidth = orgWidth;
				changeHeight = (int)((float)height * ((float)orgWidth/(float)width));
				cropY = (orgHeight - changeHeight)/2;
			}
			
			System.out.println("changeWidth:"+changeWidth+",changeHeight:"+changeHeight);
			
			BufferedImage cropImage = Scalr.crop(orgImage,cropX,cropY, changeWidth, changeHeight);
			BufferedImage thumbImage = Scalr.resize(cropImage, width, height);
			
			ByteArrayOutputStream bas = new ByteArrayOutputStream();
			//20200811 확장자명 동적으로 가져오게함 ajhwan
			ImageIO.write(thumbImage, fileExtsn, bas);
			byte[] writeData = bas.toByteArray();
			
			DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(thumbNailFilePath)));
	        dos.write(writeData);
	        dos.close();

			// JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);

			// JPEGEncodeParam jep =
			// JPEGCodec.getDefaultJPEGEncodeParam(thumbImage);
			// jep.setQuality(1f, true);

			// encoder.encode(thumbImage, jep);

	}

	public static void makeThumbNailFile(File orgFilePath,String thumbNailFilePath, int width, int height) throws IOException {
		int orgWidth = 0;
		int orgHeight = 0;
		BufferedImage orgImage = ImageIO.read(orgFilePath);
		orgWidth = orgImage.getWidth();
		orgHeight = orgImage.getHeight();
		
		int changeWidth = 0;
		int changeHeight = 0;
		int cropX = 0; //자르기 X좌표
		int cropY = 0; //자르기 Y좌표
		
		if((float)orgWidth/(float)orgHeight >  (float)width/(float)height){ //가로 비율이 클경우
			changeWidth = (int)((float)width * ((float)orgHeight/(float)height));
			changeHeight = orgHeight;
			
			cropX = (orgWidth - changeWidth)/2;
		}else{ //세로 비율이 클경우
			changeWidth = orgWidth;
			changeHeight = (int)((float)height * ((float)orgWidth/(float)width));
			cropY = (orgHeight - changeHeight)/2;
		}
		
		System.out.println("changeWidth:"+changeWidth+",changeHeight:"+changeHeight);
		
		BufferedImage cropImage = Scalr.crop(orgImage,cropX,cropY, changeWidth, changeHeight);
		BufferedImage thumbImage = Scalr.resize(cropImage, width, height);
		
		ByteArrayOutputStream bas = new ByteArrayOutputStream();
		ImageIO.write(thumbImage, "jpeg", bas);
		byte[] writeData = bas.toByteArray();
		
		DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(thumbNailFilePath)));
        dos.write(writeData);
        dos.close();
	}

}