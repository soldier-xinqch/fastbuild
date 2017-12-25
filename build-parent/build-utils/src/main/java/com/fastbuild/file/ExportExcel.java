package com.fastbuild.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFPictureData;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExportExcel {

	private static Logger logger = LoggerFactory.getLogger(ExportExcel.class);
	private static int sheetIndex = 0;

	/**
	 * 读取Excel 文件
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	private static Workbook readExcel(String filePath) throws IOException {
		Workbook wb = null;
		if (filePath == null) {
			return null;
		}
		String extString = filePath.substring(filePath.lastIndexOf("."));
		InputStream is = null;
		try {
			is = new FileInputStream(filePath);
			//wb = WorkbookFactory.create(is);  
			if (".xls".equals(extString)) {
				return wb = new HSSFWorkbook(is);
			} else if (".xlsx".equals(extString)) {
				return wb = new XSSFWorkbook(is);
			} else {
				return wb = null;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch(OfficeXmlFileException e){
			//文件转换
			is = new FileInputStream(filePath);
			if (".xls".equals(extString)) {
				return wb = new XSSFWorkbook(is);
			} else if (".xlsx".equals(extString)) {
				return wb = new HSSFWorkbook(is);
			} else {
				return wb = null;
			}
		}finally {
			if (is != null) {
				is.close();
			}
		}
		return wb;
	}

	/**
	 * 获取工作表
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 * @throws  
	 */
	private static Sheet getExcelSheets(String filePath) throws IOException {
		Workbook wb = readExcel(filePath);
		return wb.getSheetAt(sheetIndex);
	}
	
	public static Map<Integer,Map<Integer,Object>> getData(String filePath,Integer[] ignoreRows) throws IOException{
		return getData(getExcelSheets(filePath), ignoreRows,filePath);
	}
	
	public static Map<Integer,Map<Integer,Object>> getData(String filePath) throws IOException{
		return getData(getExcelSheets(filePath), null,filePath);
	}

	private static Map<Integer,Map<Integer,Object>> getData(Sheet sheet,Integer[] ignoreRows, String filePath) {
		Map<Integer,Map<Integer,Object>> resultMap = new HashMap<Integer, Map<Integer, Object>>();
		//获取最大行数
		int rowTotalNum = sheet.getPhysicalNumberOfRows();
		for (int rowIndex = 0; rowIndex < rowTotalNum; rowIndex++) {
			logger.debug("excel get data ing,curent filePath:{},rowIndex:{}",filePath,rowIndex);
			// 忽略行 不处理
			if(isContant(ignoreRows, rowIndex)){
				continue;
			}
			Row row = sheet.getRow(rowIndex);
			if(null == row){
				continue;
			}
			//获取最大列数
			int colTotalNum = row.getPhysicalNumberOfCells();
			
			Map<Integer,Object> cellMap = new LinkedHashMap<Integer, Object>();
			for (int cellIndex = 0; cellIndex < colTotalNum; cellIndex++) {
				//CellReference cellRef = new CellReference(rowIndex, cellIndex);
				// 单元格名称 System.out.print(cellRef.formatAsString());
				// 通过获取单元格值并应用任何数据格式（Date，0.00，1.23e9，$ 1.23等），获取单元格中显示的文本
				//DataFormatter formatter = new DataFormatter();
				//String text = formatter.formatCellValue(cell);
				Cell cell = row.getCell(cellIndex);
				if(null == cell){
					continue;
				}
				// 获取值并自己格式化
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:// 字符串型
					cellMap.put(cellIndex, cell.getRichStringCellValue().getString());
					break;
				case Cell.CELL_TYPE_NUMERIC:// 数值型
					if (DateUtil.isCellDateFormatted(cell)) { // 如果是date类型则，获取该cell的date值
						cellMap.put(cellIndex, cell.getDateCellValue());
					} else {// 纯数字
						DecimalFormat df = new DecimalFormat("0");    
						String cellText = df.format(cell.getNumericCellValue());
						cellMap.put(cellIndex, cellText);
					}
					break;
				case Cell.CELL_TYPE_BOOLEAN:// 布尔
					cellMap.put(cellIndex, cell.getBooleanCellValue());
					break;
				case Cell.CELL_TYPE_FORMULA:// 公式型
					cellMap.put(cellIndex, cell.getCellFormula());
					break;
				case Cell.CELL_TYPE_BLANK:// 空值
					break;
				case Cell.CELL_TYPE_ERROR: // 故障
					break;
				default:
					cellMap.put(cellIndex, null);
				}
			}
			if(MapUtils.isNotEmpty(cellMap)&& cellMap.size()>0)resultMap.put(rowIndex, cellMap);
		}
		return resultMap;
	}
	
	/**
	 *  获取表格中的图片
	 * @param workbook
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static List<HSSFPictureData> getExcelImages(Workbook workbook){
        List<HSSFPictureData> pictures = (List<HSSFPictureData>) workbook.getAllPictures();//读取图片
        HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(sheetIndex);
        for (HSSFShape shape : sheet.getDrawingPatriarch().getChildren()) {  
            if (shape instanceof HSSFPicture) {
                HSSFPicture pic = (HSSFPicture) shape;  
                int pictureIndex = pic.getPictureIndex()-1;  
                HSSFPictureData picData = pictures.get(pictureIndex);
                System.out.println("image-size:" + picData.getData().length);
            }  
        }  
        return pictures;
	}
	
	/**
	 * 校验 忽略行数
	 * @param objs
	 * @param obj
	 * @return
	 */
	private static boolean isContant(Object[] objs,Object obj){
		if(null != objs&&objs.length>0){ 
			for (Object object : objs) {
				if(obj.equals(object)){
					return true;
				}
			}
		}else{
			return true;
		}
		return false;
	}

}
