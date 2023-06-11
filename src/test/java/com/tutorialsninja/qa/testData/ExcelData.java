package com.tutorialsninja.qa.testData;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ExcelData {
	
	public static FileInputStream ip;	
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	
	
	@DataProvider(name = "TN Login")
	public static Object[][] getTutorialsNinjaLoginData() throws Exception {
		Object[][] data = ExcelData.readDataFromExcelForLoginTutorialsNinja("LoginTN");
		return data;
	}
	
	@DataProvider(name = "TN Register")
	public static Object[][] getTutorialsNinjaRegisterData() throws Exception {
		Object[][] data = ExcelData.readDataFromExcelForRegisterTutorialsNinja("RegisterTN");
		return data;
	}
	
	public static Object[][] readDataFromExcelForRegisterTutorialsNinja(String sheetName) throws Exception{
		ip = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\com\\tutorialsninja\\qa\\testData\\ExcelDataCode.xlsx");
		
		workbook = new XSSFWorkbook(ip);
		 
		sheet = workbook.getSheet(sheetName);
		
		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();
		
		Object[][] data = new Object[rows][cols];
		
		for(int i=0 ; i<rows ; i++) {
			XSSFRow row = sheet.getRow(i+1);
			
			for(int j=0 ; j<cols ; j++) {
				XSSFCell cell = row.getCell(j);
				
			CellType cellType = cell.getCellType();
				
				switch(cellType) {
				
				case STRING: 
					data[i][j] = cell.getStringCellValue();
					break;
					
				case NUMERIC:
					data[i][j] = Integer.toString((int)cell.getNumericCellValue());
				    break;
				    
				case BOOLEAN:
					data[i][j] = cell.getBooleanCellValue();
					break;
				}
			}
		}
		
		return data;

	}
	
	public static Object[][] readDataFromExcelForLoginTutorialsNinja(String sheetName) throws Exception {
	
			ip = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\com\\tutorialsninja\\qa\\testData\\ExcelDataCode.xlsx");
		
			workbook = new XSSFWorkbook(ip);
			 
			sheet = workbook.getSheet(sheetName);
			
			int rows = sheet.getLastRowNum();
			int cols = sheet.getRow(0).getLastCellNum();
			
			Object[][] data = new Object[rows][cols];
			
			for(int i=0 ; i<rows ; i++) {
				XSSFRow row = sheet.getRow(i+1);
				
				for(int j=0 ; j<cols ; j++) {
					XSSFCell cell = row.getCell(j);
					
				CellType cellType = cell.getCellType();
					
					switch(cellType) {
					
					case STRING: 
						data[i][j] = cell.getStringCellValue();
						break;
						
					case NUMERIC:
						data[i][j] = Integer.toString((int)cell.getNumericCellValue());
					    break;
					    
					case BOOLEAN:
						data[i][j] = cell.getBooleanCellValue();
						break;
					}
				}
			}
			
			return data;
		
	}

}
