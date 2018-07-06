package com.cinfotech.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Random;

import com.cinfotech.domain.Comments;
import com.cinfotech.domain.Items;
import com.cinfotech.domain.Pictures;

public class SqlUtils {
	private String JDBC_URL = null;
	private String JDBC_USER = null;
	private String JDBC_PASS = null;
	Connection conn = null;
	public SqlUtils(){
	}
	public void init(String url,String usr,String pwd) throws Exception{

		JDBC_URL=url;
		JDBC_USER=usr;
		JDBC_PASS=pwd;
		conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
	}
    public void close(){
    	try {
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	public int saveItems(List<Items> items) {
		int row = 0;
		PreparedStatement pstmt = null;
		try {
			conn.setAutoCommit(false);
			String sql = "INSERT INTO items(item_id, item_time, item_url, item_name, item_shop, item_price, item_sale_num, item_comment_num, item_stock, item_detail, item_area)"+
                      " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			 
			for (int i = 0; i < items.size(); i++) {
				Items	item = items.get(i);
//				System.out.println(i+":"+item.getItemPrice());
				pstmt.setString(1,item.getItemId());
				pstmt.setDate(2, new Date(item.getItemTime().getTime()));
				pstmt.setString(3, item.getItemUrl());
				pstmt.setString(4, item.getItemName());
				pstmt.setString(5, item.getItemShop());
				pstmt.setDouble(6, item.getItemPrice());
				pstmt.setInt(7, item.getItemSaleNum());
				pstmt.setInt(8, item.getItemCommentNum());
				pstmt.setInt(9, item.getItemStock());
				pstmt.setString(10, item.getItemDetail());
				pstmt.setString(11, item.getItemArea());
				pstmt.addBatch();
			}
			int[] rows = pstmt.executeBatch();
			conn.commit();
			row = rows.length;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}
		return row;
	}
	public int saveComments(List<Comments> commentslist) {
		int row = 0;
		PreparedStatement pstmt = null;
		try {
			conn.setAutoCommit(false);
			String sql = "INSERT INTO comments( item_id, cm_author, cm_time, cm_detail, cm_type, cm_ai_type)"+
							"VALUES( ?, ?, ?, ?, ?, ?)";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			 
			for (int i = 0; i < commentslist.size(); i++) {
				Comments	item = commentslist.get(i);
				pstmt.setString(1,item.getItemId());
				pstmt.setString(2, item.getCmAuthor());
				pstmt.setDate(3, new Date(item.getCmTime().getTime()));
				pstmt.setString(4, item.getCmDetail());			 
				pstmt.setInt(5, item.getCmType());
				pstmt.setInt(6, -1);
				pstmt.addBatch();
			}
			int[] rows = pstmt.executeBatch();
			conn.commit();
			row = rows.length;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}
		return row;
		
	}
	public int savePictures(List<Pictures> pictureslist) {
		int row = 0;
		PreparedStatement pstmt = null;
		try {
			conn.setAutoCommit(false);
			String sql = "INSERT INTO pictures( item_id, pic_url, pic_dir, pic_ai_type) VALUES( ?,?, ?, ?)";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			 
			for (int i = 0; i < pictureslist.size(); i++) {
				Pictures	item = pictureslist.get(i);
				pstmt.setString(1,item.getItemId());
				pstmt.setString(2, item.getPicUrl()); 
				pstmt.setString(3, null);			  
				pstmt.setInt(4, -1);
				pstmt.addBatch();
			}
			int[] rows = pstmt.executeBatch();
			conn.commit();
			row = rows.length;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}
		return row;
	}
}
