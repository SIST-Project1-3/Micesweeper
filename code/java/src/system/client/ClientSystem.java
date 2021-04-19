package system.client;

import dao.BoardDAO;
import vo.MessageVO;

public class ClientSystem {
	// Field
	String id = "test";
	BoardDAO bdao = new BoardDAO();

	// Constructor

	// Method
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean writeBoard(MessageVO msg) {
		return bdao.getInsertResult(msg);
	}
}
