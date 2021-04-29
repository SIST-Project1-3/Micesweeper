package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public abstract class DAO {
	String url;
	String user = "scott";
	String pass = "tiger";
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	// Constructor
	public DAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 운영체제 확인
			String osName = System.getProperty("os.name").toLowerCase();
			if (osName.indexOf("win") >= 0) { // 윈도우
				url = "jdbc:oracle:thin:@127.0.0.1:1521";
			} else if (osName.indexOf("mac") >= 0) { // 맥북
				url = "jdbc:oracle:thin:@localhost:1521/xe";
			}

			conn = DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** PreparedStatement 생성 **/
	public void getPreparedStatement(String sql) {
		try {
			pstmt = conn.prepareStatement(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 종료 **/
	public void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
			System.out.println("6: Terminate OracleDB connection ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
