package system.client;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

import dao.BoardDAO;
import dao.MemberDAO;
import gui.GameUI;
import gui.MainUI;
import vo.BoardVO;
import vo.MemberVO;
import vo.MessageVO;
import vo.RoomVO;

public class ClientSystem {
	// Field
	Socket client;
	Socket client_chat;
	public String id; // 접속한 유저의 ID정보를 담고 있는 속성
	public BoardDAO bdao = new BoardDAO();
	MemberDAO mdao = new MemberDAO();
	ObjectOutputStream oos;
	ObjectInputStream ois;
	ObjectOutputStream oos_chat;
	ObjectInputStream ois_chat;
	public MainUI mainui;
	public GameUI gameui;
	public Vector<String> userList = new Vector<String>(); // 접속중인 유저 목록
	public ArrayList<RoomVO> roomList = new ArrayList<RoomVO>(); // 생성된 방 목록
	MemberVO gameProfile;

	// Constructor
	public ClientSystem() {
		System.out.println("client created");
		initialize();
	}

	// Method

	// 초기화
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

	// 게시글 읽기
	public BoardVO readArticle(int no) {
		BoardVO article = null;
		MessageVO msg = new MessageVO();
		msg.setStatus(MessageVO.BOARD_READ_ARTICLE);
		msg.setNo(no);
		try {
			oos.writeObject(msg);
			System.out.println("게시글 읽기 요청 전송");
			article = ((MessageVO) ois.readObject()).getArticle();
			System.out.println("게시글을 받아옴");
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

	// 댓글 작성
	public boolean writeComment(int no, String id, String content) {
		boolean result = false;
		try {
			MessageVO msg = new MessageVO();
			msg.setStatus(MessageVO.BOARD_WRITE_COMMENT);
			msg.setNo(no); // 게시글 번호
			msg.setId(id); // 작성자
			msg.setContent(content); // 작성 내용
			oos.writeObject(msg);
			MessageVO recieveMsg = (MessageVO) ois.readObject();
			result = recieveMsg.getResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
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
			if (result) { // 로그인 성공 시 접속중인 유저 목록과 방 목록을 수신
				userList = recieveMsg.getUserList();
				roomList = recieveMsg.getRoomList();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 내 프로필 요청 & 정보 받기
	public MemberVO myProfile(MessageVO msg) {
		MemberVO myProfile = null;
		try {
			oos.writeObject(msg);
			myProfile = ((MessageVO) ois.readObject()).getMyProfile();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return myProfile;
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
		try {
			oos.writeObject(msg);
			MessageVO recieveMsg = (MessageVO) ois.readObject();
			result = recieveMsg.getResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

//	// 게임화면 프로필 정보 요청
//		public MemberVO gameImg(MessageVO msg) {
//			MemberVO gameImg = null;
//			try {
//				oos.writeObject(msg);
//				gameImg = ((MessageVO) ois.readObject()).getGameImg();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			return gameImg;
//		}
	
	// 게임화면 프로필보기 정보 요청
	public MemberVO gameProfile(MessageVO msg) {
		MemberVO gameProfile = null;
		try {
			oos.writeObject(msg);
			gameProfile = ((MessageVO) ois.readObject()).getGameProfile();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gameProfile;
	}

	// 방 생성 요청
	public RoomVO createRoom(String title) {
		RoomVO result = null;
		try {
			MessageVO msg = new MessageVO();
			msg.setStatus(MessageVO.ROOM_CREATE);
			msg.setId(id);
			msg.setTitle(title);
			oos.writeObject(msg);
			System.out.println("방 생성 요청 성공");
			MessageVO recieveMsg = (MessageVO) ois.readObject();
			result = recieveMsg.getRoom();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 방 참가 요청
	public RoomVO joinRoom(int roomNo) {
		RoomVO result = null;
		try {
			MessageVO msg = new MessageVO();
			msg.setStatus(MessageVO.ROOM_JOIN);
			msg.setId(id);
			msg.setNo(roomNo);
			oos.writeObject(msg);
			MessageVO recieveMsg = (MessageVO) ois.readObject();
			result = recieveMsg.getRoom();
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

	// 방 정보 목록을 Vector형태로 반환
	public Vector<String> getRoomInfoList() {
		Vector<String> list = new Vector<String>();
		for (RoomVO room : roomList) {
			list.add(room.getInfo());
		}
		return list;
	}

	// 멀티채팅
	public void sendMultiChat(MessageVO msg) {
		try {
			oos_chat.writeObject(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 1:1 게임 채팅 전송
	public void sendGameChat(String txt) {
		try {
			MessageVO msg = new MessageVO();
			msg.setStatus(MessageVO.GAMECHAT);
			msg.setNo(gameui.room.no);
			msg.setId(id);
			msg.setContent(txt);
			oos_chat.writeObject(msg); // 채팅 서버를 이용함
			System.out.println("게임 채팅 메시지 전송 성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 게임 클릭 전송
	public void sendGameClick(int btnNO) {
		try {
			MessageVO msg = new MessageVO();
			msg.setStatus(MessageVO.GAME_CLICK);
			msg.setRoom(new RoomVO());
			msg.getRoom().no = gameui.room.no; // 방 번호
			msg.setNo(btnNO); // 누른 버튼
			oos_chat.writeObject(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//게임 준비
	public void sendReady(Boolean readyflag2) {
		try {
			MessageVO msg = new MessageVO();
			msg.setStatus(MessageVO.GAME_READY);
			msg.setRoom(new RoomVO());
			msg.getRoom().no = gameui.room.no; // 방 번호
			msg.setReadyflag2(true); // 누른 버튼
			oos_chat.writeObject(msg);
		} catch (Exception e) {
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
						if (mainui != null) { // 게임에 들어가면 mainui가 사라짐
							mainui.ta_chat.append(msg.getId() + ": " + msg.getContent() + "\n");
						}
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
					} else if (msg.getStatus() == MessageVO.ROOM_CREATE) { // 방 생성
						roomList = msg.getRoomList();
						if (mainui != null) {
							mainui.jlist_room.setListData(getRoomInfoList());
						}
					} else if (msg.getStatus() == MessageVO.ROOM_JOIN) { // 방 참가
						roomList = msg.getRoomList();
						if (mainui != null) {
							mainui.jlist_room.setListData(getRoomInfoList());
						}
					} else if (msg.getStatus() == MessageVO.GAMECHAT) { // 게임 채팅
						if (gameui != null && (msg.getNo() == gameui.room.no)) { // 게임 중이고, 받은 메시지의 방 번호가 나의 방 번호일 때 실행
							gameui.chat_ta.append(msg.getId() + ": " + msg.getContent() + "\n");
							System.out.println(msg.getNo() + "번 방 채팅: " + msg.getId() + ": " + msg.getContent() + "\n");
						}
					} else if (msg.getStatus() == MessageVO.GAME_CLICK) { // 게임 클릭
						if (gameui != null) { // 게임 중이면 실행
							if (msg.getRoom().no == gameui.room.no) { // 방 번호가 맞으면 실행
								gameui.gsc.calcBtnClick(msg.getNo());
							}
						}
					} else if (msg.getStatus() == MessageVO.GAME_READY) { // 게임 레디
						if (gameui != null) { // 게임 중이면 실행
							if (msg.getRoom().no == gameui.room.no) { // 방 번호가 맞으면 실행
								gameui.gvo.setReadyflag2(msg.isReadyflag2());
								gameui.ready_btn.setBackground(Color.ORANGE);
							}
						}
					} else if (msg.getStatus() == MessageVO.GAME_LEAVE) { // 게임 탈주
						if (gameui != null) { // 게임 중이면 실행
							if (msg.getRoom().no == gameui.room.no) { // 방 번호가 맞으면 실행
								gameui.gsc.calcBtnClick(msg.getNo());
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
