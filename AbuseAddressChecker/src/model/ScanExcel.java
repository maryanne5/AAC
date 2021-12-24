package model;

import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ScanExcel implements IScan {

	@Override
	public void SaveScan(ArrayList<ScanItem> scan) {
	   DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH.mm.ss");  
	   LocalDateTime currentTime = LocalDateTime.now();  
	   String file = "Excel file - " +dateTimeFormatter.format(currentTime) + ".xls";  
	  

		HSSFWorkbook workbook = new HSSFWorkbook();  
		HSSFSheet sheet = workbook.createSheet("Results");   
		int numOfRows = 0;
		HSSFRow rowhead = sheet.createRow(numOfRows++);  
		rowhead.createCell(0).setCellValue("Address");  
		rowhead.createCell(1).setCellValue("# Of Abuses");  
		rowhead.createCell(2).setCellValue("Adress Link");  
		
		for (ScanItem item : scan) {
			rowhead = sheet.createRow(numOfRows++);  
			rowhead.createCell(0).setCellValue(item.getAddress());  
			rowhead.createCell(1).setCellValue(item.getNumOfAbuses());  
			rowhead.createCell(2).setCellValue(item.getLink());  
		}
		
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(file);
			workbook.write(fileOut);  
			
			fileOut.close();  
		} catch (Exception e) {
			Logger.getLogger().log("[SaveScan] - Could not save the report to excel. Error msg: " + e.getMessage());
		}  
 

	}

}
