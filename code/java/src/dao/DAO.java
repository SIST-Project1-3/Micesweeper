package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public abstract class DAO {
	String url = "jdbc:oracle:thin:@127.0.0.1:1521";
	String user = "scott";
	String pass = "tiger";
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	// Constructor
	public DAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1단계: 드라이버 로드");

			conn = DriverManager.getConnection(url, user, pass);
			System.out.println("2단계: DB에 특정 계정으로 접속");
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
			System.out.println("6단계: 데이터 베이스 연결 종료");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
