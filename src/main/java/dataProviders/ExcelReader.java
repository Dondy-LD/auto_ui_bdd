package dataProviders;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;

//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jxl.Sheet;
import jxl.Workbook;

public class ExcelReader {
	public ExcelReader() {
	}

	public List<Map<String, String>> readExcel2(String FilePath, String SheetName) throws Exception {
		File file = new File(FilePath);
		FileInputStream fis = new FileInputStream(file);
		Workbook wb = Workbook.getWorkbook(fis);
		Sheet sheet = wb.getSheet(SheetName);
		int rows = sheet.getRows();
		int columns = sheet.getColumns();
		List<Map<String, String>> datalist = new ArrayList<Map<String, String>>();
		for (int i = 0; i < rows; i++) {
			Map<String, String> map = new HashMap<String, String>();
			for (int j = 0; j < columns; j++) {
				if (!String.valueOf(i).equals(String.valueOf(rows - 1))) {
					Cell key = sheet.getCell(j, 0);
					Cell value = sheet.getCell(j, i + 1);
					map.put(key.getContents(), value.getContents());
				}
			}
			if (map.size() > 0) {
				datalist.add(map);
			}
		}
		return datalist;
	}

//	public List<List<String>> readExcel(String FilePath, String SheetName) {
//		List<List<String>> listAll = new ArrayList<List<String>>();
//		try {
//			File file = new File(FilePath); // creating a new file instance
//			FileInputStream fis = new FileInputStream(file); // obtaining bytes from the file
//			XSSFWorkbook wb = new XSSFWorkbook(fis);
////			XSSFSheet sheet = wb.getSheetAt(0); // creating a Sheet object to retrieve object
//			XSSFSheet sheet = wb.getSheet(SheetName);
//			Iterator<Row> itr = sheet.iterator(); // iterating over excel file
//
//			while (itr.hasNext()) {
//				List<String> rowList = new ArrayList<String>();
//				Row row = itr.next();
//				Iterator<Cell> cellIterator = row.cellIterator(); // iterating over each column
//				while (cellIterator.hasNext()) {
//					Cell cell = cellIterator.next();
//					switch (cell.getCellType()) {
//					case Cell.CELL_TYPE_STRING: // field that represents string cell type
////						System.out.print(cell.getStringCellValue() + "\t\t\t");
//						rowList.add(cell.getStringCellValue());
//						break;
//					case Cell.CELL_TYPE_NUMERIC: // field that represents number cell type
////						System.out.print(cell.getNumericCellValue() + "\t\t\t");
//						rowList.add(String.format("%.0f", cell.getNumericCellValue()));
//						break;
//					default:
//					}
//				}
//				listAll.add(rowList);
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return listAll;
//	}

}
