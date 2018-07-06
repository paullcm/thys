package com.cinfotech.db;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.cinfotech.domain.Comments;
import com.cinfotech.domain.Items;
import com.cinfotech.domain.Pictures;

public class SimpleExcel {
	public static final String C_TIME_PATTON_DEFAULT = "yyyy-MM-dd HH:mm:ss";

	public static final String C_TIME_PATTON_DEFAULT1 = "yyyy-MM-dd";
    public void read() throws Exception{ 
    	   XSSFWorkbook book = new XSSFWorkbook("E:/works/神箭手/crawler-data-1982230-1530845146393.xlsx");
           SqlUtils sql=new SqlUtils();
           sql.init("jdbc:mysql://192.168.10.6:3306/crawler?useUnicode=true&amp;characterEncoding=UTF-8", "root", "ChangeMe");
           XSSFSheet sheet = book.getSheetAt(0);
           int row = 1;
           
           while (row < sheet.getLastRowNum()){// sheet.getLastRowNum()) {
        	  
        	   List<Items> itemlist= new ArrayList<Items>();
        	   List<Pictures> pictureslist= new ArrayList<Pictures>();
        	   List<Comments> commentslist= new ArrayList<Comments>();
        	   
        	   for (int counter = 0; counter <10 && row < sheet.getLastRowNum(); counter++) {				
        		       int col = 0;
		        	   XSSFRow xlsrow = sheet.getRow(row); 
		        	   Items item= new Items();
		        	   {
			        	   String tmp =xlsrow.getCell(col++).getStringCellValue();
			        	   item.setItemTime( new SimpleDateFormat(C_TIME_PATTON_DEFAULT).parse(tmp));
		        	   }
		        	   {
			        	   String tmp =xlsrow.getCell(col++).getStringCellValue();
			        	   item.setItemUrl(tmp);
			        	   item.setItemId(getId(tmp));
		        	   } 
		        	   {
			        	   String tmp =xlsrow.getCell(col++).getStringCellValue();
			        	   item.setItemName(tmp); 
		        	   } 
		        	   {
			        	   String tmp =xlsrow.getCell(col++).getStringCellValue();
			        	   item.setItemSaleNum(getInt(tmp));
		        	   } 
		        	   {
			        	   String tmp =xlsrow.getCell(col++).getStringCellValue();
			        	   item.setItemPrice(getDouble(tmp));
		        	   } 
		        	   {
			        	   String tmp =xlsrow.getCell(col++).getStringCellValue();
			        	   item.setItemStock(getInt(tmp));
		        	   } 
		        	   {
			        	   String tmp =xlsrow.getCell(col++).getStringCellValue();
			        	   item.setItemShop((tmp));
		        	   } 
		        	   {
			        	   String tmp =xlsrow.getCell(col++).getStringCellValue();
			        	   item.setItemArea((tmp));
		        	   } 
		        	   {
			        	   String tmp =xlsrow.getCell(col++).getStringCellValue();
			        	   item.setItemCommentNum(getInt(tmp));
		        	   } 
		        	   {
			        	   String tmp =xlsrow.getCell(col++).getStringCellValue();
			        	   List<Comments> comments=getComments(tmp);
			        	   for (int i = 0; i < comments.size(); i++) {
							 comments.get(i).setItemId(item.getItemId());					
			        	   }
			        	   commentslist.addAll(comments);
		        	   } 
		        	   {
			        	   String tmp =xlsrow.getCell(col++).getStringCellValue();
			        	   List<Pictures> pictures=getPictures(tmp);
			        	   for (int i = 0; i < pictures.size(); i++) {
			        		   pictures.get(i).setItemId(item.getItemId());					
			        	   }
			        	   pictureslist.addAll(pictures);
		        	   }
		        	   itemlist.add(item);

		               row++;
        	   }
//               for (int col = 0; col <  xlsrow.getLastCellNum(); col++) { 
//                  System.out.println( xlsrow.getCell(col).getStringCellValue());    
//                 
//               }
        	   int res =  sql.saveItems(itemlist);
        	   if(res>0){
	        	   sql.saveComments(commentslist);
	        	   sql.savePictures(pictureslist);
        	   }
           }
           sql.close();
           book.close();

    }
    private List<Pictures> getPictures(String tmp) {
    	List<Pictures>  picturesList=new ArrayList<Pictures>();
    	Pattern p = Pattern.compile("((http://)(([\\w-]+\\.)+)([\\w-:]+)(/[\\w- ./?%&=]*)?\\.(png|jpg))");
		Matcher m = p.matcher(tmp);
		while (m.find()) {
			if (m != null && m.groupCount() > 0) { 
				Pictures pic= new Pictures(); 
				pic.setPicUrl(m.group(0));
				picturesList.add(pic) ;		 
			}
		}
		return picturesList;
	}
	/*
["W***TS8\n    \n    \n    <br>\n    <span class=\"gray\">（2017-10-08）</span>","<p>好评！</p>\n     \n     <p class=\"gray\">评价等级：好评</p>","","W***eJE\n    \n    \n    <br>\n    <span class=\"gray\">（2017-10-04）</span>","<p>好评！</p>\n     \n     <p class=\"gray\">评价等级：好评</p>","","s***455\n    \n    \n    <br>\n    <span class=\"gray\">（2017-07-14）</span>","<p>好评！</p>\n     \n     <p class=\"gray\">评价等级：好评</p>","","H***230\n    \n    \n    <br>\n    <span class=\"gray\">（2017-07-14）</span>","<p>好评！</p>\n     \n     <p class=\"gray\">评价等级：好评</p>",""]

     */
    private List<Comments> getComments(String commentstr) {
    	List<Comments>  commentsList=new ArrayList<Comments>();
		if(commentstr.length()>3){
			int eidx=0;
			do{
				Comments comment = new Comments();
				{
					String startStr="\"";
					int idx=commentstr.indexOf(startStr,eidx);
					if(idx>eidx){
			    		eidx=commentstr.indexOf("\\n",idx);
			    		if(eidx>idx){
			    			comment.setCmAuthor(commentstr.substring(idx+startStr.length(),eidx));
			    		}else{
			    			break;
			    		}
			    	}else{
			    		break;
			    	}
			    		
				}
				
				{
					String startStr="（";
					int idx=commentstr.indexOf(startStr,eidx);
			    	if(idx>=0){
			    		eidx=commentstr.indexOf("）",idx);
			    		if(eidx>idx){
			    			try {
								comment.setCmTime( new SimpleDateFormat(C_TIME_PATTON_DEFAULT1).parse(commentstr.substring(idx+startStr.length(),eidx)) );
							} catch (ParseException e) { 
								e.printStackTrace();
							}
			    		}
			    	}
				}
				
				
				{
					String startStr="<p>";
					int idx=commentstr.indexOf(startStr,eidx);
			    	if(idx>=0){
			    		eidx=commentstr.indexOf("</p>",idx);
			    		if(eidx>idx){ 
							comment.setCmDetail(commentstr.substring(idx+startStr.length(),eidx));
			    		}
			    	}
				}
				{
					
					String startStr="评价等级：";
					int idx=commentstr.indexOf(startStr,eidx);
			    	if(idx>=0){
			    		eidx=commentstr.indexOf("</p>",idx);
			    		if(eidx>idx){
								comment.setCmType( getCmType(commentstr.substring(idx+startStr.length(),eidx)) );
							 
			    		}
			    	}
			    	eidx=commentstr.indexOf("\"\"",eidx);
			    	if(eidx>0)
			    		eidx=eidx+2;
				}
				
				 
				commentsList.add(comment);
			}while(eidx>0);
	    	
		}
		return commentsList;
	}
	private int getCmType(String cm) {
		if(cm.equals("好评")){
			return 1;
		}else if(cm.equals("中评")){
			return 2;
		}else if(cm.equals("差评")){
			return 3;
		}else{
			return 1;
		}
	}
	private Double getDouble(String tmp) {
    	try{
    		return Double.parseDouble(tmp);
    	} catch (Exception e) {
		}
		return 0.0;
	}
	private int getInt(String tmp) {
    	try{
    		return Integer.parseInt( tmp);
    	} catch (Exception e) {
		}
		return 0;
	}
	/*
     * url format:	http://item.tyfo.com/458311.html
     */
    private String getId(String url) {
    	int idx=url.lastIndexOf("/");
    	if(idx>=0){
    		int eidx=url.indexOf(".",idx);
    		if(idx>=0){
    			return url.substring(idx+1,eidx);
    		}
    	}
		return "0";
	}

	public static void main(String[] args) throws Exception {    
    	new SimpleExcel().read();
    }
}
