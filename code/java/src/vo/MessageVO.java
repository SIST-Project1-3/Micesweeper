package vo;

import java.io.Serializable;
import java.util.ArrayList;

// 서버와 클라이언트가 통신할 때 사용하는 VO
public class MessageVO implements Serializable {
	public static int BOARD_WRITE = 1; // 게시글 작성
	public static int BOARD_READ_LIST = 2; // 게시글 목록 불러오기 
	public static int BOARD_READ_ARTICLE = 3; // 특정 게시글 읽기 
	int status, no; // MsgVO의 상태, 게시글 No
	String title, content, id; // 게시글 제목, 내용, 작성자
	boolean result; // DAO 결과값
	ArrayList<BoardVO> boardList;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean getResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public ArrayList<BoardVO> getBoardList() {
		return boardList;
	}

	public void setBoardList(ArrayList<BoardVO> boardList) {
		this.boardList = boardList;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

}
