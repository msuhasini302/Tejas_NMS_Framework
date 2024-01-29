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
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import com.google.common.collect.Table.Cell;

public class ExcelReader {

	private Workbook workbook;
	 public ExcelReader() {
	    }

	public ExcelReader(String filePath) {
		try {
			FileInputStream fileInputStream = new FileInputStream(new File(filePath));
			workbook = new XSSFWorkbook(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Map<String, String> getDataById(String sheetName, String uniqueId) {
		Map<String, String> testData = new HashMap<>();
		Sheet sheet = workbook.getSheet(sheetName);

		if (sheet != null) {
			int rowCount = sheet.getPhysicalNumberOfRows();
			int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

			for (int i = 1; i < rowCount; i++) {
				Row rowCells = sheet.getRow(i);
				String currentUniqueId = rowCells.getCell(0).getStringCellValue(); // Assuming unique ID is in the first
																					// column

				if (currentUniqueId.equals(uniqueId)) {
					for (int j = 1; j < colCount; j++) {
						String columnName = sheet.getRow(0).getCell(j).getStringCellValue();
						org.apache.poi.ss.usermodel.Cell cell = rowCells.getCell(j);
						String cellValue = "";
						if (cell != null) {
							cellValue = cell.getStringCellValue();
						}
						testData.put(columnName, cellValue);
					}
					break;
				}
			}
		}

		return testData;
	}
	  

	public void close() {
		try {
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}