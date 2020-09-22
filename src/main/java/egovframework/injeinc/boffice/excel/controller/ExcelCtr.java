package egovframework.injeinc.boffice.excel.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFOptimiser;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;

import egovframework.cmm.EgovResourceCloseHelper;
import egovframework.cmm.Message;
import egovframework.cmm.service.EgovProperties;
import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

/**
 * 공통 엑셀 다운로드 관련 Controller 클래스
 * @author 박우현
 * @since 2015.07.06
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   수정일      수정자           수정내용
 *  -------      --------    ---------------------------
 *   2015.07.06  박우현          최초 생성
 * </pre>
 */

@Controller
public class ExcelCtr {
	private static final Logger LOGGER = Logger.getLogger(ExcelCtr.class.getName());
    private ExcelCtr() {
    }

	public static void writeToExcel(File filePath , String data[][] , String sheetName, String fileName , short[] halign, short[] valign,HttpServletRequest request,HttpServletResponse response) throws IOException {

    	if(data == null) return;

     	FileOutputStream fileOut = null;

     	try {

    		HSSFWorkbook wb = new HSSFWorkbook(); // 워크북을 생성합니다. 엑셀파일을 만든다고 생각하시면 됩니다.
    		HSSFSheet sheet = wb.createSheet(sheetName);// 생성된 엑셀에 시트를 생성합니다.

	    	int rowsTotal = data.length;
	    	for(int rowInx = 0 ; rowInx < rowsTotal ; rowInx++) {

	    		HSSFOptimiser.optimiseCellStyles(wb); // 중복된 셀스타일 제거 (The maximum number of cell styles was exceeded. You can define up to 4000 styles in a .xls workbook 에러방지..)
	    		HSSFRow row = sheet.getRow(rowInx); //로우를 가져 옵니다. 로우는 그냥 엑셀의 한줄이라고 생각하시면 됩니다.
	    		if(row == null) row = sheet.createRow(rowInx); // 가져온 로우가 널일때는 로우를 생성합니다.

	    		int colsTotal = data[rowInx].length;
	    		for(int colInx = 0 ; colInx < colsTotal ; colInx++) {

	    			HSSFCell cell = row.getCell(colInx); //셀을 가져옵니다.
	    			if(cell == null) cell = row.createCell(colInx); // 가져온 셀이 널일 경우에 셀을 생성합니다.

	    			HSSFCellStyle style = createCellStyle(wb,rowInx); // 셀의 스타일을 가져옵니다.

    				if (rowInx == 0) {
    				style.setAlignment(HSSFCellStyle.ALIGN_CENTER); //1행 셀의 정렬
    				style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //1행 셀의 정렬
    				} else {
    					style.setAlignment(halign[colInx]); //셀의 정렬
	    				style.setVerticalAlignment(valign[colInx]); //셀의 정렬
    				}

	    			cell.setCellStyle(style);
	    			cell.setCellValue(data[rowInx][colInx]);	// 셀에 값을 설정합니다.

	    		}

	    	}

	    	// 셀크기 자동조정
	    	for(int rowInx = 0 ; rowInx < rowsTotal ; rowInx++) {
    			//sheet.autoSizeColumn(rowInx);
    			sheet.setColumnWidth(rowInx, (sheet.getColumnWidth(rowInx)) + 1000);
	    	}

	    	fileOut = new FileOutputStream(filePath);	//파일 출력하기 위한 아웃풋 파일 스크립을 엽니다.
	    	wb.write(fileOut);		// 워크북(엑셀)에 작성된 문서를 일괄적으로 파일로 작성합니다.
	    	fileDown(filePath, fileName, request,response);
	     } catch (FileNotFoundException e) {
	      LOGGER.debug("IGNORED: " + e.getMessage());
	      throw e;
	     } catch (IOException e) {
	      LOGGER.debug("IGNORED: " + e.getMessage());
	      throw e;
	     } finally {
	      if(fileOut != null) fileOut.close();
	     }


    }
	
	@SuppressWarnings("rawtypes")
	public static void writeToExcel(File filePath , List list , String sheetName, String fileName, HttpServletRequest request, HttpServletResponse response) throws IOException {
    	if(list == null) return;
     	FileOutputStream fileOut = null;
     	
     	String ArraySheetName[] = null;
     	if(sheetName != null){
     		ArraySheetName = sheetName.split("\\|");
     	}
     	try {
    		HSSFWorkbook wb = new HSSFWorkbook(); // 워크북을 생성합니다. 엑셀파일을 만든다고 생각하시면 됩니다.
    		HSSFSheet sheet = null; 
    		for(int i = 0 ; i < list.size(); i ++){
    			String data[][] = (String[][])list.get(i);
    			sheet = wb.createSheet(ArraySheetName[i]);// 생성된 엑셀에 시트를 생성합니다.
    			int rowsTotal = data.length;
    	    	for(int rowInx = 0 ; rowInx < rowsTotal ; rowInx++) {
    	    		
    	    		HSSFOptimiser.optimiseCellStyles(wb); // 중복된 셀스타일 제거 (The maximum number of cell styles was exceeded. You can define up to 4000 styles in a .xls workbook 에러방지..)
    	    		HSSFRow row = sheet.getRow(rowInx); //로우를 가져 옵니다. 로우는 그냥 엑셀의 한줄이라고 생각하시면 됩니다.
    	    		if(row == null) row = sheet.createRow(rowInx); // 가져온 로우가 널일때는 로우를 생성합니다.
    	      
    	    		int colsTotal = data[rowInx].length;
    	    		for(int colInx = 0 ; colInx < colsTotal ; colInx++) {
    	       
    	    			HSSFCell cell = row.getCell(colInx); //셀을 가져옵니다.
    	    			if(cell == null) cell = row.createCell(colInx); // 가져온 셀이 널일 경우에 셀을 생성합니다.
    	       
    	    			HSSFCellStyle style = createCellStyle(wb,rowInx); // 셀의 스타일을 가져옵니다.
    	    			
	    				if (rowInx == 0) {
	    				style.setAlignment(HSSFCellStyle.ALIGN_CENTER); //1행 셀의 정렬
	    				style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //1행 셀의 정렬
	    				} else {
	    					style.setAlignment(HSSFCellStyle.ALIGN_CENTER); //셀의 정렬
		    				style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //셀의 정렬
	    				}
    	       
    	    			cell.setCellStyle(style);
    	    			cell.setCellValue(data[rowInx][colInx]);	// 셀에 값을 설정합니다.
    	       
    	    		}
    	      
    	    	}
    	    	// 셀크기 자동조정
    	    	for(int rowInx = 0 ; rowInx < rowsTotal ; rowInx++) {        			
        			sheet.setColumnWidth(rowInx, (sheet.getColumnWidth(rowInx)) + 1000); 
    	    	}
    			
    		}
	    	fileOut = new FileOutputStream(filePath);	//파일 출력하기 위한 아웃풋 파일 스크립을 엽니다.
	    	wb.write(fileOut);		// 워크북(엑셀)에 작성된 문서를 일괄적으로 파일로 작성합니다.
	    	fileDown(filePath, fileName, request,response);
	     } catch (FileNotFoundException e) {
	      LOGGER.debug("IGNORED: " + e.getMessage());
	      throw e;
	     } catch (IOException e) {
	      LOGGER.debug("IGNORED: " + e.getMessage());
	      throw e;
	     } finally {
	      if(fileOut != null) fileOut.close();
	     }
        

    }
	
	public static void writeToExcel(String templateFile, String filename , Map<String, Object> beans, HttpServletRequest request,HttpServletResponse response) throws IOException, InvalidFormatException {
		String mimetype = "application/x-msdownload";
		String rootPath = EgovProperties.getProperty("WasServer.ROOT_PATH");
        String storePathString = Message.getMessage("excel.file.upload.path");
        
        OutputStream os = null;
        InputStream is = null;

        try {
        	response.setContentType(mimetype);
			setDisposition(filename+".xls", "", request, response);
			
            // 엑셀 템플릿 파일이 존재하는 위치
            is = new FileInputStream(new File(rootPath + storePathString + templateFile));

            os = response.getOutputStream();

            XLSTransformer transformer = new XLSTransformer();

            Workbook excel = transformer.transformXLS(is, beans);
            excel.write(os);
            os.flush();
            
        } catch (ParsePropertyException e) {
        	EgovResourceCloseHelper.close(is);
			EgovResourceCloseHelper.close(os);
		} catch (InvalidFormatException e) {
			EgovResourceCloseHelper.close(is);
			EgovResourceCloseHelper.close(os);
        } catch (IOException e) {
        	EgovResourceCloseHelper.close(is);
			EgovResourceCloseHelper.close(os);
        } catch (Exception e) {
        	EgovResourceCloseHelper.close(is);
			EgovResourceCloseHelper.close(os);
        } finally {
        	EgovResourceCloseHelper.close(is);
			EgovResourceCloseHelper.close(os);            
        }

    }

    public static void fileDown(File filePath, String fileName, HttpServletRequest request, HttpServletResponse response){
    	int fSize = 0;
    	if(filePath != null){
    		fSize = (int)filePath.length();
    	}

	    String mimetype = "application/x-msdownload";
	    BufferedInputStream in = null;
		BufferedOutputStream out = null;
	    try {
			response.setContentType(mimetype);
			setDisposition(fileName+".xls", "", request, response);
			response.setContentLength(fSize);

			in = new BufferedInputStream(new FileInputStream(filePath));
		    out = new BufferedOutputStream(response.getOutputStream());

		    FileCopyUtils.copy(in, out);
		    out.flush();
		} catch (FileNotFoundException e) {
			LOGGER.debug("IGNORED: " + e.getMessage());
			EgovResourceCloseHelper.close(in);
			EgovResourceCloseHelper.close(out);
		} catch (IOException e) {
			LOGGER.debug("IGNORED: " + e.getMessage());
			EgovResourceCloseHelper.close(in);
			EgovResourceCloseHelper.close(out);
		} catch (Exception e) {
			LOGGER.debug("IGNORED: " + e.getMessage());
			EgovResourceCloseHelper.close(in);
			EgovResourceCloseHelper.close(out);
		} finally {
			EgovResourceCloseHelper.close(in);
			EgovResourceCloseHelper.close(out);			
		}
    }

    /** Disposition 지정하기 */
    private static void setDisposition(String filename, String dispositionPrefix, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String browser = getBrowser(request);

		dispositionPrefix = StringUtils.isBlank(dispositionPrefix) ? "attachment; filename=" : dispositionPrefix + "; filename=";
		String encodedFilename = null;

		if (browser.equals("MSIE")) {
		    encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		} else if (browser.equals("Firefox")) {
			if(filename != null){
				encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
			}
		} else if (browser.equals("Opera")) {
			if(filename != null){
				encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
			}
		} else if (browser.equals("Chrome")) {
		    StringBuffer sb = new StringBuffer();
		    if(filename != null){
			    for (int i = 0; i < filename.length(); i++) {
					char c = filename.charAt(i);
					if (c > '~') {
					    sb.append(URLEncoder.encode("" + c, "UTF-8"));
					} else {
					    sb.append(c);
					}
			    }
		    }
		    encodedFilename = sb.toString();
		} else {
		    //throw new RuntimeException("Not supported browser");
		    throw new IOException("Not supported browser");
		}

		response.setHeader("Content-Disposition", dispositionPrefix + encodedFilename);

		if ("Opera".equals(browser)){
		    response.setContentType("application/octet-stream;charset=UTF-8");
		}
    }

    /** 브라우저 구분 얻기 */
    private static String getBrowser(HttpServletRequest request) {
        String header = request.getHeader("User-Agent");
        if (header.indexOf("MSIE") > -1) {
            return "MSIE";
        } else if (header.indexOf("Trident") > -1) {
        	// InternetExplorer11 이상
            return "MSIE";
        } else if (header.indexOf("Chrome") > -1) {
            return "Chrome";
        } else if (header.indexOf("Opera") > -1) {
            return "Opera";
        }
        return "Firefox";
    }

    public static HSSFCellStyle createCellStyle(HSSFWorkbook wb,int chkColnum) {
    	HSSFCellStyle style = null;
    	if(wb != null){
    		style = wb.createCellStyle();
    	}

    	//셀의 스타일을 설정합니다.
        //셀의 테두리 설정과 색상을 설정합니다.
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        //style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        //style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        //style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        //style.setTopBorderColor(IndexedColors.BLACK.getIndex());

        if(chkColnum == 0){
        	HSSFFont font = null;
        	if(wb != null){
        		font = wb.createFont();
        	}
        	font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        	style.setFont(font);
    	}

        return style;

    }

}
