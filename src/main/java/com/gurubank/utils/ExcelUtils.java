package com.gurubank.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

public class ExcelUtils {
    public List<Map<String, String>> readDataFromExcel(String excelFilePath, String sheetName) {
        List<Map<String, String>> dataList = new ArrayList<>();
        try {
            File excelFile = new File(excelFilePath);
            FileInputStream excelFis = new FileInputStream(excelFile);
            XSSFWorkbook excelWorkBook = new XSSFWorkbook(excelFis);
            XSSFSheet xssfSheet = excelWorkBook.getSheet(sheetName);
            List<String> columnHeaders = readColumnHeaders(xssfSheet);

            int usedRageCount = xssfSheet.getLastRowNum();

            for (int i = 1; i <= usedRageCount; i++) {
                Map<String, String> eachRowDataMap = new LinkedHashMap<>();
                Row eachRow = xssfSheet.getRow(i);
                int eachRowColumnCount = eachRow.getLastCellNum();
                for (int j = 0; j < eachRowColumnCount; j++) {
                    Cell eachCell = eachRow.getCell(j);
                    switch (eachCell.getCellType()) {
                        case STRING:
                            String stringData = eachCell.getStringCellValue();
                            eachRowDataMap.put(columnHeaders.get(j), stringData);
                            break;
                        case NUMERIC:
                            double numberData = eachCell.getNumericCellValue();
                            eachRowDataMap.put(columnHeaders.get(j), String.valueOf(numberData));

                            break;
                        default:
                            System.out.println("No match Found");
                    }
                }
                dataList.add(eachRowDataMap);
            }
            excelFis.close();
        } catch (Exception e) {
            System.out.println("Exception Occurred while reading the data from excel: " + e.getMessage());
        }

        return dataList;
    }

    public List<String> readColumnHeaders(XSSFSheet xssfSheet) {
        List<String> columnHeadersList = new ArrayList<>();

        Row columnHeadersRow = xssfSheet.getRow(0);
        Iterator<Cell> allCells = columnHeadersRow.cellIterator();

        while (allCells.hasNext()) {
            Cell eachCell = allCells.next();

            switch (eachCell.getCellType()) {
                case STRING:
                    String stringData = eachCell.getStringCellValue();
                    columnHeadersList.add(stringData);
                    break;
                case NUMERIC:
                    double numberData = eachCell.getNumericCellValue();
                    columnHeadersList.add(String.valueOf(numberData));
                    break;
                default:
                    System.out.println("No match Found");
            }
        }
        return columnHeadersList;
    }

    /**
     * Updates a specific cell in Excel sheet by column header name and row number
     * @param excelFilePath Path to the Excel file
     * @param sheetName Name of the sheet to update
     * @param columnHeaderName Name of the column to update (e.g., "CustomerId")
     * @param rowNumber Row number to update (1-based, where row 1 is first data row after headers)
     * @param newValue New value to write to the cell
     */
    public void updateCellInExcel(String excelFilePath, String sheetName, String columnHeaderName, int rowNumber, String newValue) {
        try {
            File excelFile = new File(excelFilePath);
            FileInputStream excelFis = new FileInputStream(excelFile);
            XSSFWorkbook excelWorkBook = new XSSFWorkbook(excelFis);
            XSSFSheet xssfSheet = excelWorkBook.getSheet(sheetName);

            // Get column headers
            List<String> columnHeaders = readColumnHeaders(xssfSheet);

            // Find the column index for the given header name
            int columnIndex = getColumnIndexByName(columnHeaders, columnHeaderName);

            if (columnIndex == -1) {
                System.out.println("Column '" + columnHeaderName + "' not found in the Excel sheet");
                excelFis.close();
                return;
            }

            // Get the row (rowNumber starts from 1 for first data row)
            Row targetRow = xssfSheet.getRow(rowNumber);

            if (targetRow == null) {
                System.out.println("Row " + rowNumber + " not found in the Excel sheet");
                excelFis.close();
                return;
            }

            // Get or create the cell
            Cell targetCell = targetRow.getCell(columnIndex);
            if (targetCell == null) {
                targetCell = targetRow.createCell(columnIndex);
            }

            // Set the new value
            targetCell.setCellValue(newValue);

            // Close input stream and write to file
            excelFis.close();
            FileOutputStream excelFos = new FileOutputStream(excelFile);
            excelWorkBook.write(excelFos);
            excelFos.close();
            excelWorkBook.close();

            System.out.println("Successfully updated cell at row " + rowNumber + ", column '" + columnHeaderName + "' with value: " + newValue);

        } catch (Exception e) {
            System.out.println("Exception Occurred while updating the data in excel: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Finds the column index by column header name
     * @param columnHeaders List of column headers
     * @param columnName Name of the column to find
     * @return Column index (0-based), or -1 if not found
     */
    private int getColumnIndexByName(List<String> columnHeaders, String columnName) {
        for (int i = 0; i < columnHeaders.size(); i++) {
            if (columnHeaders.get(i).equalsIgnoreCase(columnName)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Updates CustomerId field for a specific row in TestData sheet
     * Convenience method for updating customer IDs
     * @param excelFilePath Path to the Excel file
     * @param sheetName Name of the sheet (e.g., "TestData")
     * @param rowNumber Row number to update (1-based)
     * @param customerId New customer ID to write
     */
    public void updateCustomerId(String excelFilePath, String sheetName, int rowNumber, String customerId) {
        updateCellInExcel(excelFilePath, sheetName, "CustomerId", rowNumber, customerId);
    }

//    public static void main(String[] args) {
//        ExcelUtils excelUtils = new ExcelUtils();
//        List<Map<String, String>> dataList = excelUtils.readDataFromExcel(System.getProperty("user.dir") + ReadConfigProperties.getConfigPropertyValue("test.data.path"), ReadConfigProperties.getConfigPropertyValue("test.data.sheet.name"));
//        System.out.println(dataList);
//    }
}
