package system.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import dao.BoardDAO;
import dao.MemberDAO;
import vo.BoardVO;
import vo.MemberVO;
import vo.MessageVO;

public class ClientSystem {
	// Field
	Socket client;
	String id; // 접속한 유저의 ID정보를 담고 있는 속성
	BoardDAO bdao = new BoardDAO();
	MemberDAO mdao = new MemberDAO();
	ObjectOutputStream oos;
	ObjectInputStream ois;

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
	

	// 회원가입
	public boolean joinCheck(MemberVO member) {
		return mdao.getJoinResult(member);
	}

	// 아이디 중복체크
	public boolean idCheck(String id) {
		return mdao.getIdCheckResult(id);
	}

	// 로그인
	public boolean loginCheck(String id, String pw) {
		return mdao.getLoginResult(id, pw);
	}

	public void exit() {
		try {
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
