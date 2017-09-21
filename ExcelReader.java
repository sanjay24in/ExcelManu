/**
 * 
 */
package org.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author Abhishek
 *
 */
public class ExcelReader {

	private static final String FILE_NAME = "C:\\Users\\Abhishek\\Desktop\\JobSearch\\Aviva\\TestCase.xlsx";
	private static final String START_KEYWORD = "Start";
	private static final String END_KEYWORD = "End";

	public void readData() {
		FileInputStream excelFile=null ;
		try {

			excelFile = new FileInputStream(new File(FILE_NAME));
			XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
			Sheet sheet0=workbook.getSheetAt(0);
			Sheet sheet1=workbook.getSheetAt(1);
			XSSFSheet sheet2=workbook.getSheetAt(2);
			

			HashMap<String, Map<String, ExcelData>> mainDataMap = readFirstSheet(workbook);

			System.out.println("mainDataMap --->" + mainDataMap);
			HashMap<String, Map<String, ExcelData>> mainDataMap2 = readSecondSheet(workbook, mainDataMap);
			
					
			
			writeXLSXFile(workbook,mainDataMap2,sheet0,sheet1,sheet2);
			

		} catch (FileNotFoundException e) {
			System.out.println("File is not found in the location");
			e.printStackTrace();
		} catch (IOException ex) {
			System.out.println("Error in opening or closing file");
			ex.printStackTrace();

		}finally {

					excelFile=null;
				
			
		}

	}
	
public void writeXLSXFile( Workbook workbook, HashMap<String, Map<String, ExcelData>> mainDataMap2, Sheet sheet0, Sheet sheet1, XSSFSheet sheet2) throws IOException {
		

//		String sheetName = "WriteDataHereExample";//name of sheet
//		XSSFWorkbook wb = new XSSFWorkbook();
//		XSSFSheet wsheet0 = wb.createSheet();
		//wsheet0.set
		
//		XSSFSheet sheet = wb.getSheet(sheetName);

		 Iterator<Map<String, ExcelData>> valueMapIterator = mainDataMap2.values().iterator();
		 ArrayList<ExcelData> finalDataList= new ArrayList<ExcelData>();
		 
		 while (valueMapIterator.hasNext()) {
			 finalDataList.addAll(valueMapIterator.next().values());
		 }
		
		//iterating r number of rows
		for (int r=0;r < finalDataList.size(); r++ )
		{
			
			XSSFRow row = sheet2.createRow(r+1);

			ExcelData exceldata = finalDataList.get(r);
			
				XSSFCell cell = row.createCell(0);
				XSSFCell cell1 = row.createCell(1);
				XSSFCell cell2 = row.createCell(2);
	
				cell.setCellValue(exceldata.getTcDetail());
				
				cell1.setCellValue(exceldata.getTestCaseNumber());
				
				cell2.setCellValue(exceldata.getDescription());
				
				
			//}
		}

		
		FileOutputStream fileOut = new FileOutputStream(new File(FILE_NAME));
		//write this workbook to an Outputstream.
		workbook.write(fileOut);
		fileOut.flush();
		fileOut.close();
	}
	

	private HashMap<String, Map<String, ExcelData>> readSecondSheet(Workbook workbook,
			HashMap<String, Map<String, ExcelData>> mainDataMap) {
		Sheet datatypeSheet = workbook.getSheetAt(1);
		Iterator<Row> iterator = datatypeSheet.iterator();

		iterator.next();// Ignoring column header row

		String currentSection = "";

		while (iterator.hasNext()) {

			Row currentRow = iterator.next();

			Cell firstCell = currentRow.getCell(0);

			if (firstCell != null && firstCell.getCellTypeEnum() == CellType.STRING) {
				String firstCellData = firstCell.getStringCellValue();
				System.out.println("firstCellData 2 --" + firstCellData);
				if (firstCellData.contains(START_KEYWORD)) {
					// HashMap<String, ExcelData> newMap = new HashMap<String, ExcelData>();
					currentSection = firstCellData.substring(0, firstCellData.indexOf(" "));
					// newMap.put(useFulData, null);
					System.out.println("currentSection 2--" + currentSection);

				} else if (firstCellData.contains(END_KEYWORD)) {
					continue;
				}

			} else {
				System.out.println("if 2");
				if (currentSection.length() > 0) {
					Cell testCaseCell = currentRow.getCell(1);
					String testCaseCellData = testCaseCell.getStringCellValue();

					ExcelData data = new ExcelData();
					
					//data.setTcDetail(currentSection);
					
					data.setTestCaseNumber(testCaseCellData);
					System.out.println("testCaseCellData 2:" + testCaseCellData);

					Cell descCell = currentRow.getCell(2);
					String descCellData = descCell.getStringCellValue();

					System.out.println("desc 2:" + descCellData);
					System.out.println("currentSection:" + currentSection);

					ExcelData rowData = mainDataMap.get(currentSection).get(testCaseCellData);
					rowData.setDescription(descCellData);

					mainDataMap.get(currentSection).put(testCaseCellData, rowData);

				}
			}

		}
		System.out.println("main data map updated ->" + mainDataMap);
		return mainDataMap;
	}

	/**
	 * @param workbook
	 * @return
	 */
	private HashMap<String, Map<String, ExcelData>> readFirstSheet(Workbook workbook) {
		Sheet datatypeSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = datatypeSheet.iterator();

		HashMap<String, Map<String, ExcelData>> mainDataMap = new HashMap<String, Map<String, ExcelData>>();
		String currentSection = "";

		iterator.next();// Ignoring column header row

		while (iterator.hasNext()) {

			Row currentRow = iterator.next();
			// Iterator<Cell> cellIterator = currentRow.iterator();

			Cell firstCell = currentRow.getCell(0);

			if (firstCell != null && firstCell.getCellTypeEnum() == CellType.STRING) {
				String firstCellData = firstCell.getStringCellValue();
				// String useFulData="";

				if (firstCellData != null && firstCellData.trim().length() > 0) {
					System.out.println("firstCellData --" + firstCellData);
					if (firstCellData.contains(START_KEYWORD)) {
						// HashMap<String, ExcelData> newMap = new HashMap<String, ExcelData>();
						currentSection = firstCellData.substring(0, firstCellData.indexOf(" "));
						// newMap.put(useFulData, null);
						System.out.println("currentSection --" + currentSection);
						mainDataMap.put(currentSection, new HashMap<String, ExcelData>());
					} else if (firstCellData.contains(END_KEYWORD)) {
						continue;
					}
				}
			} else {
				if (currentSection.length() > 0) {
					Cell testCaseCell = currentRow.getCell(1);
					String testCaseCellData = testCaseCell.getStringCellValue();

					ExcelData data = new ExcelData();
					data.setTestCaseNumber(testCaseCellData);
					data.setTcDetail(currentSection);
					System.out.println("testCaseCellData :" + testCaseCellData);

					mainDataMap.get(currentSection).put(testCaseCellData, data);

				}
			}

		}
		return mainDataMap;
	}

}
