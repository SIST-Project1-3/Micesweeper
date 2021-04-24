package system.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import dao.BoardDAO;
import dao.MemberDAO;
import gui.MainUI;
import vo.BoardVO;
import vo.MemberVO;
import vo.MessageVO;

public class ClientSystem {
	// Field
	Socket client;
	Socket client_chat;
	String id; // 접속한 유저의 ID정보를 담고 있는 속성
	public BoardDAO bdao = new BoardDAO();
	MemberDAO mdao = new MemberDAO();
	ObjectOutputStream oos;
	ObjectInputStream ois;
	ObjectOutputStream oos_chat;
	ObjectInputStream ois_chat;
	public MainUI mainui;

	// Constructor
	public ClientSystem() {
		System.out.println("client created");
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
			System.out.println("커뮤니티 통신 소켓 연결");

			new ClientThread().start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 글 작성 - MessageVO를 인자로 받아서 메소드 실행
	public boolean writeBoard(MessageVO msg) {
		boolean result = false;

		try {
			// 소켓통신을 이용해 msg에 담아서 서버에 전송
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
		msg.setStatus(MessageVO.BOARD_READ_LIST);
		try {
			oos.writeObject(msg);
			list = ((MessageVO) ois.readObject()).getBoardList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// 글 검색
	public ArrayList<BoardVO> searchBoard(int type, String target) {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		MessageVO msg = new MessageVO();
		msg.setStatus(type);
		msg.setArticle(new BoardVO());
		msg.getArticle().setContent(target);
		try {
			oos.writeObject(msg);
			list = ((MessageVO) ois.readObject()).getBoardList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// 글 읽기
	public BoardVO readArticle(int no) {
		BoardVO article = null;
		MessageVO msg = new MessageVO();
		msg.setStatus(MessageVO.BOARD_READ_ARTICLE);
		msg.setNo(no);
		try {
			oos.writeObject(msg);
			article = ((MessageVO) ois.readObject()).getArticle();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return article;
	}

	// 멀티채팅
	public void sendMultiChat(MessageVO msg) {
		try {
			oos_chat.writeObject(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 회원가입
	public boolean join(MessageVO msg) {
		boolean result = false;
		
		try {
			oos.writeObject(msg);
			MessageVO recieveMsg = (MessageVO)ois.readObject();
			result = recieveMsg.getResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// 아이디 중복체크
	public boolean idCheck(MessageVO msg) {
		boolean result = false;
		
		try {
			oos.writeObject(msg);
			MessageVO recieveMsg = (MessageVO)ois.readObject();
			result = recieveMsg.getResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 로그인
	public boolean login(MessageVO msg) {
		boolean result = false;
		
		try {
			oos.writeObject(msg);
			MessageVO recieveMsg = (MessageVO)ois.readObject();
			result = recieveMsg.getResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// 내 프로필 요청 & 정보 받기
	public MemberVO requestProfile(MessageVO msg) {
		MemberVO profile = null;
		try {
			oos.writeObject(msg);
			profile = ((MessageVO)ois.readObject()).getProfile();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return profile;
	}
	
	// 종료
	public void exit() {
		try {
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class ClientThread extends Thread {
		// Field

		// Constructor

		// Method
		@Override
		public void run() {
			try {

				client_chat = new Socket("127.0.0.1", 10000);
				System.out.println("채팅 통신 소켓 연결");
				ois_chat = new ObjectInputStream(client_chat.getInputStream());
				System.out.println("채팅 소켓 ois 생성");
				oos_chat = new ObjectOutputStream(client_chat.getOutputStream());
				System.out.println("채팅 소켓 oos 생성");
				while (true) {
					MessageVO msg = (MessageVO) ois_chat.readObject();
					if (msg.getStatus() == MessageVO.SERVERCHAT) { // 멀티채팅
						mainui.ta_chat.append(msg.getId() + ": " + msg.getContent() + "\n");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
