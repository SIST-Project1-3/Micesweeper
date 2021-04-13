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
			System.out.println("1�ܰ�: ����̹� �ε�");

			conn = DriverManager.getConnection(url, user, pass);
			System.out.println("2�ܰ�: DB�� Ư�� �������� ����");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** PreparedStatement ���� **/
	public void getPreparedStatement(String sql) {
		try {
			pstmt = conn.prepareStatement(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** ���� **/
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
			System.out.println("6�ܰ�: ������ ���̽� ���� ����");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
