package com.cinfotech.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Random;

public class SqlUtils {
	private String JDBC_URL = null;
	private String JDBC_USER = null;
	private String JDBC_PASS = null;

	public int saveEmploeeBatch() {
		int row = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
			conn.setAutoCommit(false);
			String sql = "insert into tb_employee(name,age,sex,duty)values(?,?,?,?)";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			Random random = new Random();
			for (int i = 0; i < 10; i++) {
				pstmt.setString(1, "22" + i);
				pstmt.setInt(2, 1 + i);
				pstmt.setString(3, i % 2 == 0 ? "ÄÐ" : "Å®");
				pstmt.setInt(4, random.nextInt(5) + 10);
				pstmt.addBatch();
			}
			int[] rows = pstmt.executeBatch();
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
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return row;
	}
}
