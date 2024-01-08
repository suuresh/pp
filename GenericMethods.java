package com.ppi.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Properties;

import org.apache.commons.io.comparator.LastModifiedFileComparator;

import com.ppi.api.endpoints.Routes;
import com.ppi.libraries.Xls_Reader;

public class GenericMethods {

	public static Xls_Reader excel;

	// Reading data from excel file using dataProviders
	public Object[][] getData(String XlFileName, String sheetname, String typeoftest) {

		excel = new Xls_Reader(Routes.xlPath + XlFileName);
		int rowc = excel.getRowCount(sheetname);
		// System.out.println("Excell row count is intially :" + rowc);
		int colc = excel.getColumnCount(sheetname);
		int rcount = 0;
		// Get the rowcount
		for (int rowNum1 = 2; rowNum1 <= rowc; rowNum1++) {
			if(typeoftest.contains(excel.getCellData(sheetname, 2, rowNum1))) {
				rcount++;
			}
		}
		
		//Object[][] data = new Object[rowc - 1][colc];
		Object[][] data = new Object[rcount][colc];
		System.out.println("Row Count : " + rowc);
		System.out.println("Column count:" + colc);
		int rc =0;
		for (int rowNum = 2; rowNum <= rowc; rowNum++) { // 2
			
			// System.out.println("RowNum inside loop :"+rowNum);
			if(typeoftest.contains(excel.getCellData(sheetname, 2, rowNum))) {
				
				for (int colNum = 0; colNum < colc; colNum++) {
					// System.out.println("ColumnNum inside loop:"+colNum);
					
					data[rc][colNum] = excel.getCellData(sheetname, colNum, rowNum); // -2
					// System.out.println(excel.getCellData(sheetname, colNum, rowNum));
					
				}
				rc++;
			}
		}
		System.out.println("Selected rows::"+ data.length);
		return data;
	}
	
	public static void writingToExcel(String XlFileName, String sheetname, String colName, int rowNum,
			String keyValue) {
		excel = new Xls_Reader(Routes.xlPath + XlFileName);
		excel.setCellData(sheetname, colName, rowNum, keyValue);
	}
	
	public static int getRowCount(String XlFileName, String sheetname) {
		excel = new Xls_Reader(Routes.xlPath + XlFileName);
		return excel.getRowCount(sheetname);
	}
	
	public void explicitWait(int durationInSec) {
		try {
			Thread.sleep(1000 * durationInSec);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void waitExplicitely(int durationInSec) {
		try {
			Thread.sleep(1000 * durationInSec);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getCurrentDateTime(String format) {		
		LocalDateTime myDateObj = LocalDateTime.now();		    
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern(format);
	    String dateTime = myDateObj.format(myFormatObj);		
		return dateTime;
	}
	
	public Properties readPropertiesFile(String fileName) throws IOException {
		FileInputStream fis = null;
		Properties prop = null;
		try {
			fis = new FileInputStream(Routes.propertiesPath+fileName);
			prop = new Properties();
			prop.load(fis);
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			fis.close();
		}
		return prop;
	}
	
	public static String getExtentReportLatestFileName() {
		String fileName="";		
		try {
			File directory = new File(Routes.extentReportsPath);
			File[] files = directory.listFiles();
			Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
			File file;
			if(files.length>0) {
				file = files[0];
				fileName = file.getName();
				System.out.println(fileName);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("No files found");
		}
		return fileName;
	}
}
