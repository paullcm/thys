package com.cfinotech.db;


import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.cfinotech.domain.Items;

public class SimpleExcel {
    public void read() throws Exception{ 
    	   XSSFWorkbook book = new XSSFWorkbook("E:/works/神箭手/crawler-data-1982230-1530673370479.xlsx");
           
           XSSFSheet sheet = book.getSheetAt(0);
           int row = 0;
           
           while (row < 2){// sheet.getLastRowNum()) {
        	   XSSFRow xlsrow = sheet.getRow(row); 
        	   Items item= new Items();
        	   item.set
        	   item.setItemId(itemId);
               for (int col = 0; col <  xlsrow.getLastCellNum(); col++) { 
                  System.out.println( xlsrow.getCell(col).getStringCellValue());    
                 
               }
               row++;
           }
           book.close();

    }
    
    public static void main(String[] args) throws Exception {    
    	new SimpleExcel().read();
    }
}
