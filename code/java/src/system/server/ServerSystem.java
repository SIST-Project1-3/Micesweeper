package system.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import dao.BoardDAO;
import dao.MemberDAO;
import vo.BoardVO;
import vo.MemberVO;
import vo.MessageVO;

public class ServerSystem {
	// Field
	ServerSocket server;
	Socket client;
	ServerSystem_Chat chatServer;
	ArrayList<ServerThread> stList = new ArrayList<ServerThread>();
	BoardDAO bdao = new BoardDAO();
	MemberDAO mdao = new MemberDAO();

	// Constructor
	public ServerSystem() {
		initialize();
	}

	// Method
	public void initialize() {
		try {

			server = new ServerSocket(9000);
			System.out.println("Server start");
			new ServerSystem_Chat().start();

			while (true) {
				// 클라이언트 접속 대기
				client = server.accept();
				System.out.println("커뮤니티 소켓 연결");

				// 클라이언트 접속시 해당 클라이언트와 연결된 서버 스레드 실행
				ServerThread st = new ServerThread(client);
				st.start();
				stList.add(st);

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
					if (msg.getStatus() == MessageVO.BOARD_WRITE) { // 게시글 작성
						returnMsg.setStatus(MessageVO.BOARD_WRITE);
						returnMsg.setResult(bdao.getInsertResult(msg));
						oos.writeObject(returnMsg);
					} else if (msg.getStatus() == MessageVO.BOARD_READ_LIST) { // 게시글 목록 불러오기
						ArrayList<BoardVO> boardlist = bdao.getSelectResult();
						returnMsg.setBoardList(boardlist);
						oos.writeObject(returnMsg);
					} else if (msg.getStatus() == MessageVO.BOARD_READ_ARTICLE) { // 특정 게시글 읽기
						BoardVO article = bdao.getReadResult(msg);
						returnMsg.setArticle(article);
						oos.writeObject(returnMsg);
					} else if (msg.getStatus() == MessageVO.JOIN) { // 회원가입
						returnMsg.setStatus(MessageVO.JOIN);
						returnMsg.setResult(mdao.getJoinResult(msg));
						oos.writeObject(returnMsg);
					} else if (msg.getStatus() == MessageVO.IDCHECK) { // 아이디 중복체크
						returnMsg.setResult(mdao.getIdCheckResult(msg));
						oos.writeObject(returnMsg);
					} else if (msg.getStatus() == MessageVO.LOGIN) { // 로그인
						returnMsg.setResult(mdao.getLoginResult(msg));
						oos.writeObject(returnMsg);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new ServerSystem();
	}
}
