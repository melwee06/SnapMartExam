package snapmartexam.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class HandleTestData {
	private static String filePath, fileName, sheetName;
	private static Object[][] testData;
	
	public void readTestData() throws IOException {
		File file = new File(filePath+fileName);
		//read file
		FileInputStream inputStream = new FileInputStream(file);
		Workbook workbook = null;

		//check file type
	    String fileExtensionName = fileName.substring(fileName.indexOf("."));
	    
	    if(fileExtensionName.equals(".xlsx")){
	    	workbook = new XSSFWorkbook(inputStream);
	    }else if(fileExtensionName.equals(".xls")){
	    	workbook = new HSSFWorkbook(inputStream);
	    }

	    Sheet sheet = workbook.getSheet(sheetName);
	    
	    //get header values and index and put to map
	    LinkedHashMap<String, Integer> testDataMap = new LinkedHashMap<>();
	    Row headerRow = sheet.getRow(0);
        for (int i= 0; i<headerRow.getLastCellNum(); i++) {
        	testDataMap.put(headerRow.getCell(i).getStringCellValue(), i);
        }
       
        ArrayList<List<Object>> collection = new ArrayList<List<Object>>();
        
        System.out.println("row count: "+sheet.getLastRowNum());
        //parameterized method should return should be on 2 dimensional object array class
		testData = new Object[sheet.getLastRowNum()][]; //must specify 2d row on instantiation
        //loop through rows and set data referencing key value
        for(int i=0; i<sheet.getLastRowNum(); i++) {
        	
        	int x = i+1;//start on second row
        	Row row = sheet.getRow(x);
        	
        	//checking row with empty cell, sometimes poi is reading even the blank rows or cell!
    		if(row.getCell(row.getFirstCellNum()).getCellType()==CellType.BLANK) break;
        	
        	ArrayList<Object> dataList = new ArrayList<>();
        	
        	for(Map.Entry<String, Integer> data : testDataMap.entrySet()) {
        		
        		//add cell value to collection
        		switch(data.getKey()) {
    	        case "webBrowser":
    	        	dataList.add(row.getCell(data.getValue()).getStringCellValue());
    	        	break;
    	        case "webUrl":
    	        	dataList.add(row.getCell(data.getValue()).getStringCellValue());
    	        	break;
    	        case "username":
    	        	dataList.add(row.getCell(data.getValue()).getStringCellValue());
    	        	break;
    	        case "password":
    	        	dataList.add(row.getCell(data.getValue()).getStringCellValue());
    	        	break;
    	        case "expUrlLogin":
    	        	dataList.add(row.getCell(data.getValue()).getStringCellValue());
    	        	break;
    	        case "expUrlSearch":
    	        	dataList.add(row.getCell(data.getValue()).getStringCellValue());
    	        	break;
    	        case "incorrectUsername":
    	        	dataList.add(row.getCell(data.getValue()).getStringCellValue());
    	        	break;
    	        case "incorrectPassword":
    	        	dataList.add(row.getCell(data.getValue()).getStringCellValue());
    	        	break;
    	        case "expUrlBasket":
    	        	dataList.add(row.getCell(data.getValue()).getStringCellValue());
    	        	break;
    	        case "itemName":
    	        	dataList.add(row.getCell(data.getValue()).getStringCellValue());
    	        	break;
    	        case "quantity":
    	        	dataList.add(row.getCell(data.getValue()).getStringCellValue());
    	        	break;
    	        }
        	}
        	collection.add(dataList);
        	
			ArrayList<Object> arrRow = (ArrayList<Object>) collection.get(i); //get values from a row
		    testData[i] = arrRow.toArray(new Object[arrRow.size()]); //instantiate new object array of row
    		
        }
		System.out.println("collection: "+ collection);
	}
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		HandleTestData.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		HandleTestData.fileName = fileName;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		HandleTestData.sheetName = sheetName;
	}
	
	public Object[][] getTestData() {
		return testData;
	}
	
	
}
