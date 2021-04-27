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
import vo.BoardVO;
import vo.MemberVO;
import vo.MessageVO;

public class ServerSystem {
	// Field
	ServerSocket server;
	Socket client;
	ServerSystem_Chat chatServer;
	ArrayList<ServerThread> stList = new ArrayList<ServerThread>(); // 접속한 클라이언트와 연결된 스레드 리스트
	ArrayList<String> roomList = new ArrayList<String>(); // 생성된 게임 방 목록
	Vector<String> userList = new Vector<String>(); // 접속중인 유저 목록
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
					returnMsg.setStatus(msg.getStatus());
					if (msg.getStatus() == MessageVO.BOARD_WRITE) { // 게시글 작성
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
					} else if (msg.getStatus() == MessageVO.BOARD_UPDATE_ARTICLE) { // 특정 게시글 수정
						returnMsg.setResult(bdao.getUpdateResult(msg));
						oos.writeObject(returnMsg);
					} else if (msg.getStatus() == MessageVO.BOARD_DELETE_ARTICLE) { // 특정 게시글 삭제
						returnMsg.setResult(bdao.getDeleteResult(msg));
						oos.writeObject(returnMsg);
					} else if (msg.getStatus() == MessageVO.JOIN) { // 회원가입
						returnMsg.setResult(mdao.getJoinResult(msg));
						oos.writeObject(returnMsg);
					} else if (msg.getStatus() == MessageVO.IDCHECK) { // 아이디 중복체크
						returnMsg.setResult(mdao.getIdCheckResult(msg));
						oos.writeObject(returnMsg);
					} else if (msg.getStatus() == MessageVO.LOGIN) { // 로그인
						boolean result = mdao.getLoginResult(msg);
						if (result) { // 로그인 성공 시 접속중인 유저 목록 전송
							System.out.println(msg.getId() + ": 로그인");
							userList.add(msg.getId());
							// oos의 특징때문에 clone()으로 전송. flush(), reset()을 이용하는 방법도 있을 것으로 생각됨
							returnMsg.setUserList((Vector<String>) userList.clone());
						}
						returnMsg.setResult(result);
						oos.writeObject(returnMsg); // 로그인 대상에게 응답 전송
						chatServer.broadcastMsg(returnMsg);// 접속중인 클라이언트 모두에게 리스트 갱신 전송
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
						System.out.println("server - IMG_UPDATE");
						returnMsg.setResult(mdao.getUpdateResult(msg));
						oos.writeObject(returnMsg);
//					} else if (msg.getStatus() == MessageVO.GAME_PROFILE) { // 상대 프로필 정보 요청
//						System.out.println("server");
//						MemberVO gameProfile = gdao.getGameProfileResult(msg);
//						returnMsg.setMyProfile(gameProfile);
//						oos.writeObject(returnMsg);
					} else if (msg.getStatus() == MessageVO.ROOM_CREATE) { // 방 생성 요청, 성공 여부 반환
//						createRoom();
					} else if (msg.getStatus() == MessageVO.ROOM_JOIN) { // 방 참가 요청, 성공 여부 반환
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
