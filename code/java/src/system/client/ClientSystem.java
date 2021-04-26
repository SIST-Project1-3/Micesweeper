package system.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

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
	public Vector<String> userList = new Vector<String>(); // 접속중인 유저 목록

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

	// 글 수정
	public boolean updateBoard(BoardVO article) {
		boolean result = false;
		MessageVO msg = new MessageVO();
		msg.setStatus(MessageVO.BOARD_UPDATE_ARTICLE);
		msg.setArticle(article);
		try {
			oos.writeObject(msg);
			MessageVO recieveMsg = (MessageVO) ois.readObject();
			result = recieveMsg.getResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 글 삭제
	public boolean deleteBoard(int no) {
		boolean result = false;
		MessageVO msg = new MessageVO();
		msg.setStatus(MessageVO.BOARD_DELETE_ARTICLE);
		msg.setNo(no);
		try {
			oos.writeObject(msg);
			MessageVO recieveMsg = (MessageVO) ois.readObject();
			result = recieveMsg.getResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
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
			MessageVO recieveMsg = (MessageVO) ois.readObject();
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
			MessageVO recieveMsg = (MessageVO) ois.readObject();
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
			MessageVO recieveMsg = (MessageVO) ois.readObject();
			result = recieveMsg.getResult();
			if (result) { // 로그인 성공 시 접속중인 유저 목록을 수신
				userList = recieveMsg.getUserList();
			}
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
			profile = ((MessageVO) ois.readObject()).getProfile();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return profile;
	}

	// 프로필 이미지 요청
	public String[] requestImg(MessageVO msg) {
		String[] img_list = new String[6];
		try {
			oos.writeObject(msg);
			img_list = ((MessageVO) ois.readObject()).getImgList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return img_list;
	}

	// 선택한 이미지 정보 저장
	public boolean updateImg(MessageVO msg) {
		boolean result = false;
		System.out.println("client - updateImg");
		try {
			oos.writeObject(msg);
			MessageVO recieveMsg = (MessageVO) ois.readObject();
			result = recieveMsg.getResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 종료
	public void exit() {
		try {
			MessageVO msg = new MessageVO();
			msg.setStatus(MessageVO.EXIT);
			msg.setId(id);
			oos.writeObject(msg);
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
					} else if (msg.getStatus() == MessageVO.LOGIN) { // 사용자 접속
						userList = msg.getUserList();
						if (mainui != null) { // 로그인하기 전에 코드를 실행하면 에러가 나므로 if 문으로 검증
							mainui.jlist_user.setListData(userList); // MainUI 접속자 명단 갱신
						}
					} else if (msg.getStatus() == MessageVO.EXIT) { // 사용자 퇴장
						userList = msg.getUserList();
						if (mainui != null) { // 로그인하기 전에 코드를 실행하면 에러가 나므로 if 문으로 검증
							mainui.jlist_user.setListData(userList); // MainUI 접속자 명단 갱신
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
