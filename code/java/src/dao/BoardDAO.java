package dao;

import vo.MessageVO;

public class BoardDAO extends DAO {

	// 글쓰기
	public boolean getInsertResult(MessageVO msg) {
		boolean result = false;

		try {
			String sql = "INSERT INTO BOARD VALUES(BOARD_NO_SEQ.NEXTVAL, ?, ?, ?, 0, SYSDATE)";
			getPreparedStatement(sql);

			pstmt.setString(1, msg.getTitle());
			pstmt.setString(2, msg.getContent());
			pstmt.setString(3, msg.getId());

			int val = pstmt.executeUpdate();

			if (val != 0) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	// 글읽기

	// 글목록
}
