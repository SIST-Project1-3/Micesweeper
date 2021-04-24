package vo;

import java.io.Serializable;
import java.util.ArrayList;

// 서버와 클라이언트가 통신할 때 사용하는 VO
public class MessageVO implements Serializable {
	public static int BOARD_WRITE = 1; // 게시글 작성
	public static int BOARD_READ_LIST = 2; // 게시글 목록 불러오기
	public static int BOARD_READ_ARTICLE = 3; // 특정 게시글 읽기
	public static int BOARD_SEARCH_TITLE = 8; // 게시글 제목 검색 
	public static int BOARD_SEARCH_WRITER = 9; // 게시글 작성자 검색 
	public static int LOGIN = 4; // 로그인
	public static int LOGOUT = 5; // 게임종료 및 로그아웃
	public static int JOIN = 6; // 회원가입
	public static int IDCHECK = 10; // 아이디 중복확인
	public static int SERVERCHAT = 7;
	public static int REQUEST_PROFILE = 11; 	// 프로필 정보 요청
	public static int IMG_REQUEST = 12;	// 프로필 이미지 요청
	public static int IMG_UPDATE = 13;	// 수정할 이미지 경로 가져오기
	int status, no; // MsgVO의 상태, 게시글 No
	String title, content, id, pw, img; // 게시글 제목, (내용, 채팅메시지), 작성ID, 패스워드, 프로필이미지
	boolean result; // DAO 결과값
	ArrayList<BoardVO> boardList; // 게시글 목록 불러오기
	BoardVO article; // 게시글 읽기
	MemberVO profile; // 프로필 정보
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}
	
	public void setPw(String pw) {
		this.pw = pw;
	}
	
	public boolean getResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public ArrayList<BoardVO> getBoardList() {
		return boardList;
	}

	public void setBoardList(ArrayList<BoardVO> boardList) {
		this.boardList = boardList;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public BoardVO getArticle() {
		return article;
	}

	public void setArticle(BoardVO article) {
		this.article = article;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public MemberVO getProfile() {
		return profile;
	}

	public void setProfile(MemberVO profile) {
		this.profile = profile;
	}

	
}
