package dao;

import java.util.ArrayList;

import vo.BoardVO;
import vo.MessageVO;

public class BoardDAO extends DAO {
	// Constructor
	public BoardDAO() {
		System.out.println("3: BoardDAO load");
	}

	// Method
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

	// 글 목록 불러오기
	public ArrayList<BoardVO> getSelectResult() {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		try {
			String sql = "SELECT * FROM BOARD ORDER BY NO DESC";
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

	// 글 검색
	public ArrayList<BoardVO> getSearchResult(MessageVO msg) {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		try {
			String sql=null;
			if(msg.getStatus()== MessageVO.BOARD_SEARCH_TITLE) {
				sql = "SELECT * FROM BOARD WHERE TITLE LIKE '%'||?||'%'";				
			}else if(msg.getStatus()==MessageVO.BOARD_SEARCH_WRITER) {
				sql = "SELECT * FROM BOARD WHERE ID LIKE '%'||?||'%'";								
			}
			getPreparedStatement(sql);
			String target = msg.getArticle().getContent();
			pstmt.setString(1, target);
			
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

	// 글 읽기
	public BoardVO getReadResult(MessageVO msg) {
		BoardVO article = new BoardVO();
		try {
			// 조회수 증가
			String sql = "UPDATE BOARD SET VIEWCOUNT = VIEWCOUNT+1 WHERE NO=?";
			getPreparedStatement(sql);
			pstmt.setInt(1, msg.getNo());
			int val = pstmt.executeUpdate();
			if (val == 1) {
				System.out.println(msg.getNo() + "번 게시글 조회수 증가");
			}

			// 특정 게시글 조회
			sql = "SELECT * FROM BOARD WHERE NO=?";
			getPreparedStatement(sql);
			pstmt.setInt(1, msg.getNo());

			rs = pstmt.executeQuery();
			while (rs.next()) {
				article.setNo(rs.getInt(1));
				article.setTitle(rs.getString(2));
				article.setContent(rs.getString(3));
				article.setWriter(rs.getString(4));
				article.setViewcount(rs.getInt(5));
				article.setWdate(rs.getString(6));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return article;
	}
}
