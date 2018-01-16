package com.fastbuild.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 *  导出工作表格
 */
public class ExportExcel {

    /**
     * 根据ExcelEntity等参数生成Workbook
     *
     * @param entity
     * @return
     * @throws Exception
     */
    public static <T> Workbook export2Excel(ExcelEntity<T> entity) throws Exception {
        Workbook workbook = export2Excel(entity.getHeader(), entity.getFooter(), entity.getSheetName(), entity.getColumnNames(), entity.getMethodNames(),
                entity.getEntities());
        return workbook;
    }


    /**
     * 根据给定参数导出Excel文档
     *
     * @param headerTitle 题头
     * @param footerTitle 脚注
     * @param sheetName
     * @param columnNames 表头名称
     * @param methodNames
     * @param entities
     * @return
     * @throws Exception
     */
    public static <T> Workbook export2Excel(String headerTitle, String footerTitle, String sheetName, String[] columnNames,
                                            String[] methodNames, List<T> entities) throws Exception {
        if (methodNames.length != columnNames.length)
            throw new IllegalArgumentException("methodNames.length should be equal to columnNames.length:"
                    + columnNames.length + " " + methodNames.length);
        Workbook newWorkBook2007 = new XSSFWorkbook();
        Sheet sheet = newWorkBook2007.createSheet(sheetName);

        //设置题头
        Header header = sheet.getHeader();
        header.setCenter(headerTitle);
        //设置脚注
        Footer footer = sheet.getFooter();
        footer.setCenter(footerTitle);

        int[] columnWidths = new int[columnNames.length];
        // 创建表头
        createTableHeader(sheet, 0, headerTitle, columnNames, columnWidths);
        // 填充表内容
        createTableContent(sheet, 1, methodNames, columnWidths, entities);

        return newWorkBook2007;

    }

    /**
     * 创建表头
     *
     * @param sheet
     * @param index        表头开始的行数
     * @param headerTitle  题头
     * @param columnNames
     * @param columnWidths
     */
    private static void createTableHeader(Sheet sheet, int index, String headerTitle, String[] columnNames,
                                          int[] columnWidths) {


        Row headerRow = sheet.createRow(index);

        /* 格式设置 */
        // 设置字体
        Font font = sheet.getWorkbook().createFont();
        font.setBold(true);// 粗体显示
        // 设置背景色
        CellStyle style = sheet.getWorkbook().createCellStyle();
        style.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFont(font);

        for (int i = 0; i < columnNames.length; i++) {
            Cell headerCell = headerRow.createCell(i);
            headerCell.setCellStyle(style);
            headerCell.setCellValue(columnNames[i]);
        }

        for (int i = 0; i < columnNames.length; i++) {
            columnWidths[i] = (columnNames[i].getBytes().length + 2) * 256;
            sheet.setColumnWidth(i, columnWidths[i]);
        }

    }

    /**
     * 创建表格内容
     *
     * @param sheet
     * @param rowIndexBegin 表内容开始的行数
     * @param methodNames   T对象的方法名
     * @param columnWidths
     * @param entities
     * @throws Exception
     */
    private static <T> void createTableContent(Sheet sheet, int rowIndexBegin, String[] methodNames, int[] columnWidths,
                                               List<T> entities) throws Exception {
        Class<? extends Object> clazz = null;
        if (entities.size() > 0)
            clazz = entities.get(0).getClass();

        String content = null;
        for (T t : entities) {
            Row row = sheet.createRow(rowIndexBegin++);
            for (int i = 0; i < methodNames.length; i++) {
                Cell cell = row.createCell(i);
                Method method = clazz.getMethod(methodNames[i], null);
                Object object = method.invoke(t, null);
                object = object == null ? "" : object;
                if (object.getClass().equals(Date.class)) {// 对日期格式进行特殊处理
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    content = sdf.format((Date) object);
                    cell.setCellValue(content);
                } else {
                    content = object.toString();
                    cell.setCellValue(content);
                }
                int columnWidth = (content.getBytes().length + 2) * 256;
                if (columnWidth > columnWidths[i]) {// 如果实际内容宽度大于对应的表头宽度，则设置为实际内容宽度
                    columnWidths[i] = columnWidth;
                    sheet.setColumnWidth(i, columnWidths[i]);
                }

            }
        }
    }

    public static <T> void testPOI(String[] columnNames, String[] methodNames, List<T> entities) throws Exception {
        String sheetName = "Test";
        String title = "标题栏";
        String dstFile = "d:/temp/test.xlsx";
        Workbook newWorkBook2007 = new XSSFWorkbook();
        Sheet sheet = newWorkBook2007.createSheet(sheetName);
        int[] columnWidths = new int[columnNames.length];
        // 创建表头
        createTableHeader(sheet, 0, title, columnNames, columnWidths);
        // 填充表内容
        createTableContent(sheet, 1, methodNames, columnWidths, entities);
        // 保存为文件
        saveWorkBook2007(newWorkBook2007, dstFile);
        System.out.println("end");

    }

    /**
     * @param workbook2007
     * @param dstFile
     */
    public static void saveWorkBook2007(Workbook workbook2007, String dstFile) {
        File file = new File(dstFile);
        OutputStream os = null;
        try {
            os = new FileOutputStream(file);
            workbook2007.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        }
    }

    /**
     * @param workbook2007
     * @param dstFile
     */
    public static void saveWorkBook2007(Workbook workbook2007, String dstFile, OutputStream os) {
        try {
            workbook2007.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        }
    }
}

