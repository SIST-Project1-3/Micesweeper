package system.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

import dao.BoardDAO;
import dao.GameDAO;
import dao.MemberDAO;
import gamesystem.GameSystemServer;
import vo.BoardVO;
import vo.GameVO;
import vo.MemberVO;
import vo.MessageVO;
import vo.RoomVO;

public class ServerSystem {
	// Field
	ServerSocket server;
	Socket client;
	ServerSystem_Chat chatServer;
	ArrayList<ServerThread> stList = new ArrayList<ServerThread>(); // 접속한 클라이언트와 연결된 스레드 리스트
	Vector<String> userList = new Vector<String>(); // 접속중인 유저 목록
	ArrayList<GameSystemServer> gssList = new ArrayList<GameSystemServer>(); // 생성된 게임 방 목록
	int roomNo = 1; // 방 번호
	BoardDAO bdao = new BoardDAO();
	MemberDAO mdao = new MemberDAO();
	GameDAO gdao = new GameDAO();

	// Constructor
	public ServerSystem() {
		initialize();
	}

	// Method
	public void initialize() {
		try {

			server = new ServerSocket(9000);
			System.out.println("Server start");
			chatServer = new ServerSystem_Chat();
			chatServer.start();

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

	// 게임방의 정보 목록을 반환
	public ArrayList<RoomVO> getRoomList() {
		ArrayList<RoomVO> list = new ArrayList<RoomVO>();
		for (GameSystemServer gss : gssList) {
			list.add((RoomVO) gss.room.clone());
		}
		return list;
	}

	// 게임 방 생성 메소드
	public RoomVO createRoom(MessageVO msg, ServerThread client) {
		System.out.println("createRoom 메소드 실행");
		RoomVO result = null;
		GameSystemServer gss = new GameSystemServer();
		gss.room.userList.add(msg.getId()); // 해당 유저의 아이디 추가
		gss.room.title = msg.getTitle(); // 해당 방의 이름 설정
		gss.room.userCount = 1; // 방의 인원수 설정
		gss.room.no = roomNo++; // 방 번호 입력 후 해당 시퀀스 1 추가
		if (gssList.add(gss)) { // 생성 성공 시 true 반환
			System.out.println("방 생성 성공");
			MessageVO returnMsg = new MessageVO(); // 모든 클라이언트에게 방이 생성되었음을 알림
			returnMsg.setStatus(MessageVO.ROOM_CREATE);
			returnMsg.setRoomList(getRoomList()); // 방 목록을 메시지에 추가
			chatServer.broadcastMsg(returnMsg);
			result = gss.room;
			result.game = gss.gvo;
		} else {
			System.out.println("방 생성 실패");
		}
		return result;
	}

	// 방 참가 메소드
	public RoomVO joinRoom(MessageVO msg, ServerThread client) {
		RoomVO result = null;
		for (GameSystemServer gss : gssList) {
			if (gss.room.no == msg.getNo()) { // 참가하려는 방의 번호를 찾아내면 실행
				gss.room.userList.add(msg.getId());
				gss.room.userCount++;
				System.out.println(
						msg.getNo() + "번 방 참가자: " + gss.room.userList.get(0) + ", " + gss.room.userList.get(1));
				MessageVO returnMsg = new MessageVO();
				returnMsg.setStatus(MessageVO.ROOM_JOIN);
				returnMsg.setRoomList(getRoomList());
				chatServer.broadcastMsg(returnMsg);
				result = gss.room;
				break;
			}
		}
		return result;
	}

	public class ServerThread extends Thread {
		// Field
		Socket client;
		public ObjectOutputStream oos;
		public ObjectInputStream ois;

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
					System.out.println("메시지 수신");
					MessageVO returnMsg = new MessageVO();
					returnMsg.setStatus(msg.getStatus()); // 요청한 상태와 같은 상태로 답신
					if (msg.getStatus() == MessageVO.BOARD_WRITE) { // 게시글 작성
						returnMsg.setResult(bdao.getInsertResult(msg));
						oos.writeObject(returnMsg);
					} else if (msg.getStatus() == MessageVO.BOARD_READ_LIST) { // 게시글 목록 불러오기
						ArrayList<BoardVO> boardlist = bdao.getSelectResult();
						returnMsg.setBoardList(boardlist);
						oos.writeObject(returnMsg);
					} else if (msg.getStatus() == MessageVO.BOARD_READ_ARTICLE) { // 특정 게시글 읽기
						System.out.println("게시글 읽기 메시지 수신");
						BoardVO article = bdao.getReadResult(msg);
						returnMsg.setArticle(article);
						oos.writeObject(returnMsg);
						System.out.println("게시글 읽기 메시지 답신");
					} else if (msg.getStatus() == MessageVO.BOARD_UPDATE_ARTICLE) { // 특정 게시글 수정
						returnMsg.setResult(bdao.getUpdateResult(msg));
						oos.writeObject(returnMsg);
					} else if (msg.getStatus() == MessageVO.BOARD_DELETE_ARTICLE) { // 특정 게시글 삭제
						returnMsg.setResult(bdao.getDeleteResult(msg));
						oos.writeObject(returnMsg);
					} else if (msg.getStatus() == MessageVO.BOARD_WRITE_COMMENT) { // 댓글 작성
						returnMsg.setResult(bdao.getWriteCommentResult(msg));
						oos.writeObject(returnMsg);
					} else if (msg.getStatus() == MessageVO.JOIN) { // 회원가입
						returnMsg.setResult(mdao.getJoinResult(msg));
						oos.writeObject(returnMsg);
					} else if (msg.getStatus() == MessageVO.IDCHECK) { // 아이디 중복체크
						returnMsg.setResult(mdao.getIdCheckResult(msg));
						oos.writeObject(returnMsg);
					} else if (msg.getStatus() == MessageVO.LOGIN) { // 로그인
						boolean result = mdao.getLoginResult(msg);
						returnMsg.setResult(result);
						if (result) { // 로그인 성공 시 접속중인 유저 목록 전송
							System.out.println(msg.getId() + ": 로그인");
							userList.add(msg.getId());
							// oos의 특징때문에 clone()으로 전송. flush(), reset()을 이용하는 방법도 있을 것으로 생각됨
							returnMsg.setUserList((Vector<String>) userList.clone());
							returnMsg.setRoomList(getRoomList()); // 방 목록을 메시지에 추가
							chatServer.broadcastMsg(returnMsg);// 접속중인 클라이언트 모두에게 리스트 갱신 전송
						}
						oos.writeObject(returnMsg); // 로그인 대상에게 응답 전송
					} else if (msg.getStatus() == MessageVO.EXIT) { // 게임종료
						userList.remove(msg.getId());
						// oos의 특징때문에 clone()으로 전송. flush(), reset()을 이용하는 방법도 있을 것으로 생각됨
						returnMsg.setUserList((Vector<String>) userList.clone());
						chatServer.broadcastMsg(returnMsg);
					} else if (msg.getStatus() == MessageVO.BOARD_SEARCH_TITLE
							|| msg.getStatus() == MessageVO.BOARD_SEARCH_WRITER) { // 검색
						returnMsg.setBoardList(bdao.getSearchResult(msg));
						oos.writeObject(returnMsg);
					} else if (msg.getStatus() == MessageVO.MY_PROFILE) { // 내 프로필 정보 요청
						MemberVO myProfile = mdao.getMyProfileResult(msg);
						returnMsg.setMyProfile(myProfile);
						oos.writeObject(returnMsg);
					} else if (msg.getStatus() == MessageVO.IMG_REQUEST) { // 프로필 이미지 요청
						String[] img_list = mdao.getImgResult(msg);
						returnMsg.setImgList(img_list);
						oos.writeObject(returnMsg);
					} else if (msg.getStatus() == MessageVO.IMG_UPDATE) { // 프로필 이미지 수정
						returnMsg.setResult(mdao.getUpdateResult(msg));
						oos.writeObject(returnMsg);
//					} else if (msg.getStatus() == MessageVO.GAME_IMG) { // 게임화면 이미지 요청
//						MemberVO gameImg = gdao.getGameImgResult(msg);
//						returnMsg.setGameImg(gameImg);
//						oos.writeObject(returnMsg);
					} else if (msg.getStatus() == MessageVO.GAME_PROFILE) { // 게임 프로필 정보 요청
						MemberVO gameProfile = gdao.getGameProfileResult(msg);
						returnMsg.setGameProfile(gameProfile);
						oos.writeObject(returnMsg);
					} else if (msg.getStatus() == MessageVO.ROOM_CREATE) { // 방 생성 요청, 성공 여부 반환
						returnMsg.setRoom(createRoom(msg, this)); // 자신의 소켓 정보까지 넘김
						oos.writeObject(returnMsg);
					} else if (msg.getStatus() == MessageVO.ROOM_JOIN) { // 방 참가 요청, 성공 여부 반환
						returnMsg.setRoom(joinRoom(msg, this));
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
