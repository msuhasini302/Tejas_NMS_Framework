package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

import com.google.common.collect.Table.Cell;

public class ExcelReader {
	
	// File excelFile = new File(System.getProperty("user.dir") +
	// "\\src\\test\\resources\\testData\\CRM_TestData.xlsx");
	
	@DataProvider(name = "testData1")
	public Map<String, String>[] getData1() throws IOException {
		File excelFile = new File(
				System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\CRM_TestData.xlsx");

		FileInputStream inputStream = new FileInputStream(excelFile);

		Workbook workbook = WorkbookFactory.create(inputStream);
		Sheet sheet = workbook.getSheet("Sheet1");

		int rowCount = sheet.getPhysicalNumberOfRows();
		int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

		List<Map<String, String>> dataMap = new ArrayList<>();

		for (int i = 1; i < rowCount; i++) {
			Row rowCells = sheet.getRow(i);
			Map<String, String> testData = new HashMap<>();
			for (int j = 0; j < colCount; j++) {
				String columnName = sheet.getRow(0).getCell(j).getStringCellValue();
				
				// String cellValue = rowCells.getCell(j).getStringCellValue();
				
				org.apache.poi.ss.usermodel.Cell cell = rowCells.getCell(j);
				String cellValue = "";
				if (cell != null) {
					cellValue = cell.getStringCellValue();
				}
				testData.put(columnName, cellValue);
			}
			
			dataMap.add(testData);
		}
		workbook.close();
		inputStream.close();

		Map<String, String>[] dataArray = dataMap.toArray(new Map[dataMap.size()]);

		return dataArray;

	}

	@DataProvider(name = "testData2")
	public Map<String, String>[] getData2() throws IOException {
		File excelFile = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\CRM_TestData.xlsx");
		FileInputStream inputStream = new FileInputStream(excelFile);

		Workbook workbook = WorkbookFactory.create(inputStream);
		Sheet sheet = workbook.getSheet("Sheet2");

		int rowCount = sheet.getPhysicalNumberOfRows();
		int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

		List<Map<String, String>> dataMap = new ArrayList<>();

		for (int i = 1; i < rowCount; i++) {
			Row rowCells = sheet.getRow(i);
			Map<String, String> testData = new HashMap<>();
			for (int j = 0; j < colCount; j++) {
				String columnName = sheet.getRow(0).getCell(j).getStringCellValue();
				
				// String cellValue = rowCells.getCell(j).getStringCellValue();
				
				org.apache.poi.ss.usermodel.Cell cell = rowCells.getCell(j);
				String cellValue = "";
				if (cell != null) {
					cellValue = cell.getStringCellValue();
				}
				testData.put(columnName, cellValue);
			}
			dataMap.add(testData);
		}
		workbook.close();
		inputStream.close();

		Map<String, String>[] dataArray = dataMap.toArray(new Map[dataMap.size()]);

		return dataArray;

	}

}
