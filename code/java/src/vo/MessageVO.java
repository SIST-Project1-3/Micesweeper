package vo;

import java.io.Serializable;
import java.util.ArrayList;

// 서버와 클라이언트가 통신할 때 사용하는 VO
public class MessageVO implements Serializable {
	public static int WRITE = 1;
	public static int READ = 1;
	int status;
	String title, content, id;
	boolean result;
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

}
