package vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

// 서버와 클라이언트가 통신할 때 사용하는 VO
public class MessageVO implements Serializable {
	public static final int BOARD_WRITE = 1; // 게시글 작성
	public static final int BOARD_READ_LIST = 2; // 게시글 목록 불러오기
	public static final int BOARD_READ_ARTICLE = 3; // 특정 게시글 읽기
	public static final int BOARD_UPDATE_ARTICLE = 4; // 특정 게시글 수정
	public static final int BOARD_DELETE_ARTICLE = 5; // 특정 게시글 삭제
	public static final int BOARD_SEARCH_TITLE = 6; // 게시글 제목 검색
	public static final int BOARD_SEARCH_WRITER = 7; // 게시글 작성자 검색
	public static final int LOGIN = 8; // 로그인
	public static final int CONNECT = 9; // 게임종료 및 로그아웃
	public static final int EXIT = 10; // 게임종료 및 로그아웃
	public static final int JOIN = 11; // 회원가입
	public static final int IDCHECK = 12; // 아이디 중복확인
	public static final int SERVERCHAT = 13; // 멀티 채팅
	public static final int GAMECHAT = 14; // 게임 채팅
	public static final int MY_PROFILE = 15; // 내 프로필 정보 요청
	public static final int IMG_REQUEST = 16; // 프로필 이미지 요청
	public static final int IMG_UPDATE = 17; // 수정할 이미지 경로 가져오기
	public static final int GAME_PROFILE = 18; // 상대 프로필 정보 요청
	public static final int ANOTHER_PROFILE = 19; // 상대 프로필 정보 요청
	public static final int ROOM_CREATE = 20; // 방 생성
	public static final int ROOM_JOIN = 21; // 방 참가
	public static final int WIN_OR_LOSE = 22; // 승패기록
	public static final int GAME_CLICK = 23; // 게임 클릭
	public static final int GAME_QUIT = 24; // 게임 탈주
	public static final int GAME_IMG = 25;
	public static final int GAME_WIN = 26; // 게임 승리
	public static final int GAME_LOSE = 27; // 게임 패배
	public static final int GAME_LEAVE = 28; // 게임 탈주
	public static final int GAME_READY = 29; // 게임 탈주
	public static final int BOARD_WRITE_COMMENT = 29; // 댓글 작성

	int clickedBtn; // 누른 버튼
	int status;// MsgVO의 상태
	int no; // 게시글 No, 게임방 No
	String title, content, id, id2, pw, img; // 게시글 제목, (내용, 채팅메시지), 내ID or 방장ID, 참가자ID, 패스워드, 내프로필이미지
	boolean result; // DAO 결과값
	ArrayList<BoardVO> boardList; // 게시글 목록 불러오기
	BoardVO article; // 게시글 읽기
	MemberVO myProfile; // 내 프로필 정보 (메인화면)
	String[] imgList; // 프로필 이미지
	MemberVO gameImg; // 게임화면 이미지 (방장, 참가자)
	MemberVO gameProfile; // 게임화면 프로필 정보 (나 ,상대)
	Vector<String> userList; // 접속중인 유저 목록
	ArrayList<RoomVO> roomList; // 방 목록
	RoomVO room; // 방 정보
	boolean winflag, loseflag, readyflag2, gameflag;

	public boolean isGameflag() {
		return gameflag;
	}

	public void setGameflag(boolean gameflag) {
		this.gameflag = gameflag;
	}

	public boolean isReadyflag2() {
		return readyflag2;
	}

	public void setReadyflag2(boolean readyflag2) {
		this.readyflag2 = readyflag2;
	}

	public int getClickedBtn() {
		return clickedBtn;
	}

	public void setClickedBtn(int clickedBtn) {
		this.clickedBtn = clickedBtn;
	}

	public boolean isWinflag() {
		return winflag;
	}

	public void setWinflag(boolean winflag) {
		this.winflag = winflag;
	}

	public boolean isLoseflag() {
		return loseflag;
	}

	public void setLoseflag(boolean loseflag) {
		this.loseflag = loseflag;
	}

	public RoomVO getRoom() {
		return room;
	}

	public void setRoom(RoomVO room) {
		this.room = room;
	}

	public ArrayList<RoomVO> getRoomList() {
		return roomList;
	}

	public void setRoomList(ArrayList<RoomVO> roomList) {
		this.roomList = roomList;
	}

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

	public MemberVO getMyProfile() {
		return myProfile;
	}

	public void setMyProfile(MemberVO myProfile) {
		this.myProfile = myProfile;
	}

	public MemberVO getGameImg() {
		return gameImg;
	}

	public void setGameImg(MemberVO gameImg) {
		this.gameImg = gameImg;
	}

	public MemberVO getGameProfile() {
		return gameProfile;
	}

	public void setGameProfile(MemberVO gameProfile) {
		this.gameProfile = gameProfile;
	}

	public String[] getImgList() {
		return imgList;
	}

	public void setImgList(String[] imgList) {
		this.imgList = imgList;
	}

}
