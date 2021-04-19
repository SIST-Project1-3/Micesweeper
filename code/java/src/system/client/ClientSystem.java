package system.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import dao.BoardDAO;
import vo.BoardVO;
import vo.MessageVO;

public class ClientSystem {
	// Field
	Socket client;
	String id = "test";
	BoardDAO bdao = new BoardDAO();
	ObjectOutputStream oos;
	ObjectInputStream ois;

	// Constructor
	public ClientSystem() {
		initialize();
	}

	// Method
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void initialize() {
		try {
			client = new Socket("127.0.0.1", 9000);
			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 글 작성
	public boolean writeBoard(MessageVO msg) {
		boolean result = false;

		try {
			oos.writeObject(msg);
			MessageVO recieveMsg = (MessageVO) ois.readObject();
			result = recieveMsg.getResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 글 목록
	public ArrayList<BoardVO> readBoard() {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		MessageVO msg = new MessageVO();
		msg.setStatus(MessageVO.READ);
		try {
			oos.writeObject(msg);
			list = ((MessageVO) ois.readObject()).getBoardList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// 글 읽기
	public BoardVO readArticle() {
		BoardVO article = new BoardVO();
		return article;
	}
}
