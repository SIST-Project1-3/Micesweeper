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
	// 게시글 작성
	public boolean getInsertResult(MessageVO msg) {
		boolean result = false;

		try {
			String sql = "INSERT INTO BOARD(NO, TITLE, CONTENT, ID) VALUES(BOARD_NO_SEQ.NEXTVAL, ?, ?, ?)";
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

	// 게시글 목록
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
//				board.setContent(rs.getString(3)); // 게시글 목록을 보여줄 때 내용은 필요 없으므로 생략
				board.setWriter(rs.getString(4));
				board.setViewcount(rs.getInt(5));
				board.setWdate(rs.getString(6));
//				board.setComments(rs.getString(7)); // 댓글도 필요 없으므로 생략
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
			String sql = null;
			if (msg.getStatus() == MessageVO.BOARD_SEARCH_TITLE) {
				sql = "SELECT * FROM BOARD WHERE TITLE LIKE '%'||?||'%' ORDER BY NO DESC";
			} else if (msg.getStatus() == MessageVO.BOARD_SEARCH_WRITER) {
				sql = "SELECT * FROM BOARD WHERE ID LIKE '%'||?||'%' ORDER BY NO DESC";
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

	// 게시글 읽기
	public BoardVO getReadResult(MessageVO msg) {
		BoardVO article = new BoardVO();
		try {
			// 조회수 증가
			String sql = "UPDATE BOARD SET VIEWCOUNT = VIEWCOUNT+1 WHERE NO=?";
			getPreparedStatement(sql);
			pstmt.setInt(1, msg.getNo());
			int val = pstmt.executeUpdate();
			System.out.println("조회수 증가 적용. " + msg.getNo() + "번 게시글 조회수 증가 결과: " + val);

			// 조회수 증가 코드가 제대로 적용되면 실행
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
				article.setComments(rs.getString(7));
				System.out.println("댓글: " + article.getComments());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return article;
	}

	// 글 수정
	public boolean getUpdateResult(MessageVO msg) {
		boolean result = false;

		try {
			String sql = "UPDATE BOARD SET TITLE = ?, CONTENT = ? WHERE NO = ?";
			getPreparedStatement(sql);

			pstmt.setString(1, msg.getArticle().getTitle());
			pstmt.setString(2, msg.getArticle().getContent());
			pstmt.setInt(3, msg.getArticle().getNo());

			int val = pstmt.executeUpdate();

			if (val != 0) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 글 삭제
	public boolean getDeleteResult(MessageVO msg) {
		boolean result = false;

		try {
			String sql = "DELETE FROM BOARD WHERE NO = ?";
			getPreparedStatement(sql);

			pstmt.setInt(1, msg.getNo());

			int val = pstmt.executeUpdate();

			if (val != 0) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 댓글 작성
	public boolean getWriteCommentResult(MessageVO msg) {
		boolean result = false;
		try {
			String sql = "UPDATE BOARD SET COMMENTS = COMMENTS || ? || ':' || ? || '\\n' WHERE NO = ?";
			getPreparedStatement(sql);

			pstmt.setString(1, msg.getId()); // 댓글 작성자 ID
			pstmt.setString(2, msg.getContent()); // 댓글 작성 내용
			pstmt.setInt(3, msg.getNo()); // 댓글을 추가할 게시글 번호

			int val = pstmt.executeUpdate();

			if (val != 0) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
