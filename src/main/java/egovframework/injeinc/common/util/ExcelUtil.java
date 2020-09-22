package egovframework.injeinc.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
//import org.apache.poi.xssf.usermodel.XSSFCell;

public class ExcelUtil {

	private static Log log = LogFactory.getLog(ExcelUtil.class);
	
	public static String getValue(HSSFCell cell) {

		String result = "";

		if (null == cell || cell.equals(null)) {
			return "";
		}

		if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
			log.debug("### HSSFCell.CELL_TYPE_BOOLEAN : " + HSSFCell.CELL_TYPE_BOOLEAN);
			
			result = String.valueOf(cell.getBooleanCellValue());

		}else if (cell.getCellType() == HSSFCell.CELL_TYPE_ERROR) {
			log.debug("### HSSFCell.CELL_TYPE_ERROR : " + HSSFCell.CELL_TYPE_ERROR);

		}else if (cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA) {
			log.debug("### HSSFCell.CELL_TYPE_FORMULA : " + HSSFCell.CELL_TYPE_FORMULA);

			String stringValue = cell.getRichStringCellValue().getString();
			String longValue = doubleToString(cell.getNumericCellValue());

			result = EgovStringUtil.isNumeric(longValue) ? longValue : stringValue;

		}else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
			log.debug("### HSSFCell.CELL_TYPE_NUMERIC : " + HSSFCell.CELL_TYPE_NUMERIC);
			result = HSSFDateUtil.isCellDateFormatted(cell) ? DateUtil.toString(cell.getDateCellValue(), "yyyy-MM-dd", null) : doubleToString(cell.getNumericCellValue());

		}else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
			log.debug("### HSSFCell.CELL_TYPE_STRING : " + HSSFCell.CELL_TYPE_STRING);
			result = cell.getRichStringCellValue().getString();

		}else if (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
			log.debug("### HSSFCell.CELL_TYPE_BLANK : " + HSSFCell.CELL_TYPE_BLANK);
		}

		return result;
	}
	
	/*
	
	public static String getValue(XSSFCell cell) {
		
		String result = "";
		
		if (null == cell || cell.equals(null)) {
			return "";
		}
		
		if (cell.getCellType() == XSSFCell.CELL_TYPE_BOOLEAN) {
			log.debug("### XSSFCell.CELL_TYPE_BOOLEAN : " + XSSFCell.CELL_TYPE_BOOLEAN);
			result = String.valueOf(cell.getBooleanCellValue());
			
		}else if (cell.getCellType() == XSSFCell.CELL_TYPE_ERROR) {
			log.debug("### XSSFCell.CELL_TYPE_ERROR : " + XSSFCell.CELL_TYPE_ERROR);
			
		}else if (cell.getCellType() == XSSFCell.CELL_TYPE_FORMULA) {
			log.debug("### XSSFCell.CELL_TYPE_FORMULA : " + XSSFCell.CELL_TYPE_FORMULA);
			
			String stringValue = cell.getRichStringCellValue().getString();
			String longValue = doubleToString(cell.getNumericCellValue());
			
			result = EgovStringUtil.isNumeric(longValue) ? longValue : stringValue;
			
		}else if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
			log.debug("### XSSFCell.CELL_TYPE_NUMERIC : " + XSSFCell.CELL_TYPE_NUMERIC);
			
			result = HSSFDateUtil.isCellDateFormatted(cell) ? DateUtil.toString(cell.getDateCellValue(), "yyyy/MM/dd", null) : doubleToString(cell.getNumericCellValue());
					
		}else if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
			log.debug("### XSSFCell.CELL_TYPE_STRING : " + XSSFCell.CELL_TYPE_STRING);
			result = cell.getRichStringCellValue().getString();
			
		}else if (cell.getCellType() == XSSFCell.CELL_TYPE_BLANK) {
			log.debug("### XSSFCell.CELL_TYPE_BLANK : " + XSSFCell.CELL_TYPE_BLANK);
		}
		
		return result;
	}
	
	*/

	public static String doubleToString(double d) {
		long lValue = (long) d;
		return (lValue == d) ? Long.toString(lValue) : Double.toString(d);
	}

}
