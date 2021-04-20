package system.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import vo.MessageVO;

public class ServerSystem_Chat extends Thread {
	// Field
	ServerSocket server;
	Socket client;
	ArrayList<chatThread> ctList = new ArrayList<chatThread>();

	// Constructor
	public ServerSystem_Chat() {
		this.start();
	}

	// Method

	@Override
	public void run() {
		try {
			while (true) {
				server = new ServerSocket(9100);
				client = server.accept();
				System.out.println("클라이언트 채팅서버 연결");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 채팅 에코
	public void broadcastMsg(MessageVO msg) {
		try {
			for (chatThread ct : ctList) {
				ct.oos.writeObject(msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public class chatThread extends Thread {
		// Field
		Socket client;
		ObjectOutputStream oos;
		ObjectInputStream ois;

		// Constructor
		public chatThread(Socket client) {
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
		}
	}
}
