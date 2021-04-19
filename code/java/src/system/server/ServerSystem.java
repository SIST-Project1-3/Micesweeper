package system.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import dao.BoardDAO;
import vo.BoardVO;
import vo.MessageVO;

public class ServerSystem {
	// Field
	ServerSocket server;
	Socket client;
	ArrayList<ServerThread> stList = new ArrayList<ServerThread>();
	BoardDAO bdao = new BoardDAO();

	// Constructor
	public ServerSystem() {
		initialize();
	}

	// Method
	public void initialize() {
		try {
			server = new ServerSocket(9000);
			System.out.println("Server start");

			while (true) {
				// 클라이언트 접속 대기
				client = server.accept();

				// 클라이언트 접속시 해당 클라이언트와 연결된 서버 스레드 실행
				ServerThread st = new ServerThread(client);
				st.start();

				stList.add(st);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 메세지 전체 에코
	public void broadcastMsg(MessageVO msg) {
		try {
			for (ServerThread st : stList) {
				st.oos.writeObject(msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public class ServerThread extends Thread {
		// Field
		Socket client;
		ObjectOutputStream oos;
		ObjectInputStream ois;

		// Constructor
		public ServerThread(Socket client) {
			try {
				this.client = client;
				oos = new ObjectOutputStream(client.getOutputStream());
				ois = new ObjectInputStream(client.getInputStream());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// Method
		@Override
		public void run() {
			try {
				while (true) {
					MessageVO msg = (MessageVO) ois.readObject();
					MessageVO returnMsg = new MessageVO();
					if (msg.getStatus() == MessageVO.WRITE) {
						// 서버에서 처리 후 모두에게 전송하지 않고, 해당 클라이언트에게만 전송해야 하는 경우 if문으로 처리
						returnMsg.setStatus(MessageVO.WRITE);
						returnMsg.setResult(bdao.getInsertResult(msg));
						oos.writeObject(returnMsg);
					} else if (msg.getStatus() == MessageVO.READ) {
						ArrayList<BoardVO> boardlist = bdao.getSelectResult();
						returnMsg.setBoardList(boardlist);
						oos.writeObject(returnMsg);
					} else {
						// 전체 에코 메세지
						broadcastMsg(msg);
					}
				}
			} catch (

			Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		new ServerSystem();
	}
}
