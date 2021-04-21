package system.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

import vo.MessageVO;

public class ServerSystem_Chat extends Thread {
	// Field
	ServerSocket server;
	Socket client;
	ArrayList<ChatThread> ctList = new ArrayList<ChatThread>();

	// Constructor
	public ServerSystem_Chat() {
	}
	// Method

	@Override
	public void run() {
		try {
			server = new ServerSocket(10000);
			System.out.println("Chat server start");
			while (true) {
				client = server.accept();
				System.out.println("클라이언트 채팅서버 연결");

				ChatThread ct = new ChatThread(client);
				ct.start();
				ctList.add(ct);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 채팅 에코
	public void broadcastMsg(MessageVO msg) {
		Iterator<ChatThread> iter = ctList.iterator();
		try {
			while (iter.hasNext()) {
				ChatThread ct = iter.next();
				try {
					ct.oos.writeObject(msg);
				} catch (Exception e) {
					System.err.println("소켓 연결 해제");
					iter.remove();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// try {
//			for (ChatThread ct : ctList) {
//				ct.oos.writeObject(msg);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.err.println("채팅서버 클라이언트 연결 끊김");
//		}
	}

	public class ChatThread extends Thread {
		// Field
		Socket client;
		ObjectOutputStream oos;
		ObjectInputStream ois;

		// Constructor
		public ChatThread(Socket client) {
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
					broadcastMsg(msg);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
