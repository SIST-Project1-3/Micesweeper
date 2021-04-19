package system.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import dao.BoardDAO;
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
//	public boolean writeBoard(MessageVO msg) {
//		return bdao.getInsertResult(msg);
//	}

	public boolean writeBoard(MessageVO msg) {
		boolean result = false;
		;
		try {
			oos.writeObject(msg);
			MessageVO recieveMsg = (MessageVO) ois.readObject();
			result = recieveMsg.getResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
