package dao;

import java.util.ArrayList;

import vo.BoardVO;
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
	public ArrayList<BoardVO> getSelectResult() {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		try {
			String sql = "select * from board";
			getPreparedStatement(sql);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardVO board = new BoardVO();
				board.setNo(rs.getInt(1));
				board.setTitle(rs.getString(2));
				board.setContent(rs.getString(3));
				board.setWriter(rs.getString(4));
				board.setViewcount(rs.getInt(5));
				board.setWdate(rs.getString(6));
				list.add(board);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	// 글목록
}
