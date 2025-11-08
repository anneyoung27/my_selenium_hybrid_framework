package helper;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import utils.LogUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Objects;

public class ExcelHelper {

    private FileInputStream fis;
    private FileOutputStream fileOut;
    private Workbook wb;
    private Sheet sh;
    private Cell cell;
    private Row row;
    private CellStyle cellStyle;
    private Color myColor;
    private String excelFilePath;
    private Map<String, Integer> cols = new HashMap<>();

    public void setExcelFile(String excelPath, String sheetName) {
        try {
            File f = new File(excelPath);
            if (!f.exists()) {
                throw new Exception("File doesn't exist at " + excelPath);
            }

            fis = new FileInputStream(excelPath);
            wb = WorkbookFactory.create(fis);
            sh = wb.getSheet(sheetName);

            if (sh == null) {
                throw new Exception("Sheet " + sheetName + " doesn't exist in " + excelPath);
            }

            this.excelFilePath = excelPath;

            // adding all the column header names to the map 'cols'
            sh.getRow(0).forEach(cell -> {
                cols.put(cell.getStringCellValue(), cell.getColumnIndex());
            });
        } catch (Exception e) {
            LogUtils.error("Error in setExcelFile: " + e.getMessage());
        }
    }

    public String getCellData(int columnIndex, int rowIndex) {
        try {
            cell = sh.getRow(rowIndex).getCell(columnIndex);
            String cellData = null;
            switch (cell.getCellType()) {
                case STRING:
                    cellData = cell.getStringCellValue();
                    break;
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                            cellData = String.valueOf(cell.getDateCellValue());
                        } else{
                            cellData = String.valueOf((long) cell.getNumericCellValue());
                        }
                    break;
                case BOOLEAN:
                    cellData = Boolean.toString(cell.getBooleanCellValue());
                    break;
                case BLANK:
                    cellData = "";
                    break;
            }
            return cellData;
        } catch (Exception e) {
            LogUtils.error("Error in getCellData: " + e.getMessage());
            return "";
        }
    }

    public String getCellData(String columnName, int rowIndex){
        return getCellData(cols.get(columnName), rowIndex);
    }

    public void setCellData(String text, int columnIndex, int rowIndex){
        try {
            row = sh.getRow(rowIndex);
            if (row == null ){
                row = sh.createRow(rowIndex);
            }
            cell = row.getCell(columnIndex);

            if (cell == null){
                cell = row.createCell(columnIndex);
            }
            cell.setCellValue(text);

            XSSFCellStyle style = (XSSFCellStyle) wb.createCellStyle();
            style.setFillPattern(FillPatternType.NO_FILL);
            style.setAlignment(HorizontalAlignment.CENTER);
            style.setVerticalAlignment(VerticalAlignment.CENTER);

            cell.setCellStyle(style);

            fileOut = new FileOutputStream(excelFilePath);
            wb.write(fileOut);
            fileOut.flush();
            fileOut.close();
            LogUtils.info("Set data completed!");
        } catch (Exception e){
            LogUtils.error("Error in setCellData: " + e.getMessage());
        }
    }

    // set by column name
    public void setCellData(String text, String columnName, int rowIndex){
        try {
            row = sh.getRow(rowIndex);
            if (row == null){
                row = sh.createRow(rowIndex);
            }
            cell = row.getCell(cols.get(columnName));

            if (cell == null){
                cell = row.createCell(cols.get(columnName));
            }
            cell.setCellValue(text);

            XSSFCellStyle style = (XSSFCellStyle) wb.createCellStyle();
            style.setFillPattern(FillPatternType.NO_FILL);
            style.setAlignment(HorizontalAlignment.CENTER);
            style.setVerticalAlignment(VerticalAlignment.CENTER);

            cell.setCellStyle(style);

            fileOut = new FileOutputStream(excelFilePath);
            wb.write(fileOut);
            fileOut.flush();
            fileOut.close();
            LogUtils.info("Set data completed!");
        } catch (Exception e){
            LogUtils.error("Error in setCellData: " + e.getMessage());
        }
    }

    // get all data from a sheet
    public Object[][] getExcelData(String filePath, String sheetName){
        Object[][] data = null;
        Workbook workbook = null;

        try {
            // load the file
            FileInputStream file = new FileInputStream(filePath);

            // load the workbook
            workbook = new XSSFWorkbook(fis);

            // load the sheet
            Sheet sh = workbook.getSheet(sheetName);

            // load the row
            Row row = sh.getRow(0);

            int noOfRows = sh.getPhysicalNumberOfRows();
            int noOfCols = row.getLastCellNum();

            LogUtils.info("No of Rows: " + noOfRows);
            LogUtils.info("No of Columns: " + noOfCols);

            Cell cell;
            data = new Object[noOfRows - 1][noOfCols];

            for (int i = 1; i < noOfRows; i++){
                for (int j = 0; j < noOfCols; j++){
                    row = sh.getRow(i);
                    cell = row.getCell(j);

                    if (Objects.requireNonNull(cell.getCellType()) == CellType.NUMERIC) {
                        data[i - 1][j] = String.valueOf(cell.getNumericCellValue());
                    } else {
                        data[i - 1][j] = cell.getStringCellValue();
                    }
                }
            }
        }   catch (Exception e){
            LogUtils.error("Error in getExcelData: " + e.getMessage());
        } finally {
            try {
                if (workbook != null) {
                    workbook.close();
                }
            } catch (Exception e) {
                LogUtils.error("Error closing workbook: " + e.getMessage());
            }
        }
        return data;
    }

    public int getColumns(){
        try {
            row = sh.getRow(0);
            return row.getLastCellNum();
        } catch (Exception e){
            LogUtils.error("Error in getColumns: " + e.getMessage());
            return 0;
        }
    }

    public int getLastRowNum(){
        return sh.getLastRowNum();
    }

    public int getPhysicalNumberOfRows(){
        return sh.getPhysicalNumberOfRows();
    }

    public Object[][] getDataHashtable(String excelPath, String sheetName, int startRow, int endRow){
        System.out.println("Excel path: " + excelPath);
        Object[][] data = null;

        try {
            File f = new File(excelPath);
            if (!f.exists()) {
                try {
                    LogUtils.error("File doesn't exist at " + excelPath);
                } catch (Exception e) {
                    LogUtils.error("Error in getDataHashtable: " + e.getMessage());
                }
            }

            fis = new FileInputStream(excelPath);
            wb = new XSSFWorkbook(fis);
            sh = wb.getSheet(sheetName);

            int rows = getLastRowNum();
            int columns = getColumns();

            LogUtils.info("Row: " + rows + " - Column: " + columns);
            LogUtils.info("Start Row: " + startRow + " - End Row: " + endRow);

            data = new Object[endRow - startRow + 1][1];
            Hashtable<String, String> table = null;
            for (int rowNums = startRow; rowNums <= endRow; rowNums++){
                table = new Hashtable<>();
                for (int colNums = 0; colNums < columns; colNums++){
                    table.put(getCellData(colNums, 0), getCellData(colNums, rowNums));
                }
                data[rowNums - startRow][0] = table;
            }
        } catch (IOException e){
            LogUtils.error("Error in getDataHashtable: " + e.getMessage());
        }
        return data;
    }

}