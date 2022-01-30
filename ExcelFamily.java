package week5.day2;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFamily {

	public static String[][] readExcel(String fileName) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook("./data/" + fileName + ".xlsx");

		XSSFSheet worksheet = workbook.getSheet("Sheet 1");

		int rowCount = worksheet.getLastRowNum();

		int colCount = worksheet.getRow(0).getLastCellNum();

		System.out.println("Row count is: " + rowCount);

		System.out.println("Column count is: " + colCount);

		String [][] data = new String[rowCount][colCount];
		
		for (int i = 1; i <= rowCount; i++) {

			XSSFRow row = worksheet.getRow(i);

			for (int j = 0; j < colCount; j++) {
				XSSFCell cell = row.getCell(j);

				String cellvalue = cell.getStringCellValue();
				System.out.println(cellvalue);
				
				data[i-1][j] = cellvalue;
			}
		}
		
		workbook.close();
		
		return data;
	}
}
