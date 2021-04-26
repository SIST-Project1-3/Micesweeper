package vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

// 서버와 클라이언트가 통신할 때 사용하는 VO
public class MessageVO implements Serializable {
	public static final int BOARD_WRITE = 1; // 게시글 작성
	public static final int BOARD_READ_LIST = 2; // 게시글 목록 불러오기
	public static final int BOARD_READ_ARTICLE = 3; // 특정 게시글 읽기
	public static final int BOARD_UPDATE_ARTICLE = 14; // 특정 게시글 수정
	public static final int BOARD_DELETE_ARTICLE = 15; // 특정 게시글 삭제
	public static final int BOARD_SEARCH_TITLE = 8; // 게시글 제목 검색
	public static final int BOARD_SEARCH_WRITER = 9; // 게시글 작성자 검색
	public static final int LOGIN = 4; // 로그인
	public static final int CONNECT= 16; // 게임종료 및 로그아웃
	public static final int EXIT = 5; // 게임종료 및 로그아웃
	public static final int JOIN = 6; // 회원가입
	public static final int IDCHECK = 10; // 아이디 중복확인
	public static final int SERVERCHAT = 7; // 멀티 채팅
	public static final int MY_PROFILE = 11; // 내 프로필 정보 요청
	public static final int IMG_REQUEST = 12; // 프로필 이미지 요청
	public static final int IMG_UPDATE = 13; // 수정할 이미지 경로 가져오기
	public static final int ANOTHER_PROFILE = 14; // 상대 프로필 정보 요청
	
	int status, no; // MsgVO의 상태, 게시글 No
	String title, content, id, id2, pw, img, img2; // 게시글 제목, (내용, 채팅메시지), 내ID, 상대ID, 패스워드, 내프로필이미지, 상대프로필이미지
	boolean result; // DAO 결과값
	ArrayList<BoardVO> boardList; // 게시글 목록 불러오기
	BoardVO article; // 게시글 읽기
	MemberVO myProfile; // 내 프로필 정보 (메인화면, 게임화면)
	String[] imgList; // 프로필 이미지
	MemberVO anotherProfile; // 상대 프로필 정보 (게임화면)
	Vector<String> userList;

	public Vector<String> getUserList() {
		return userList;
	}

	public void setUserList(Vector<String> userList) {
		this.userList = userList;
	}

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

	public String getId2() {
		return id2;
	}

	public void setId2(String id2) {
		this.id2 = id2;
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

	public String getImg2() {
		return img2;
	}

	public void setImg2(String img2) {
		this.img2 = img2;
	}

	public MemberVO getMyProfile() {
		return myProfile;
	}

	public void setMyProfile(MemberVO myProfile) {
		this.myProfile = myProfile;
	}

	public MemberVO getAnotherProfile() {
		return anotherProfile;
	}

	public void setAnotherProfile(MemberVO anotherProfile) {
		this.anotherProfile = anotherProfile;
	}

	public String[] getImgList() {
		return imgList;
	}

	public void setImgList(String[] imgList) {
		this.imgList = imgList;
	}


}
