package vo;

import java.io.Serializable;
import java.util.ArrayList;

// DAO를 통해 DB에 접근하기위한 VO
public class MemberVO implements Serializable{
	public static int REQUEST_PROFILE = 1; 	// 프로필 정보 요청
	public static int IMG_REQUEST = 2;	// 프로필 이미지 요청
	public static int IMG_UPDATE = 3;	// 수정할 이미지 경로 가져오기
	String id, pw, name, rdate, img; // ID, PW, 이름, 등록일자, 이미지 경로
	int status, win, lose; // memberVO의 상태, 승리횟수, 패배횟수
	MemberVO profile;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRdate() {
		return rdate;
	}
	public void setRdate(String rdate) {
		this.rdate = rdate;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getWin() {
		return win;
	}
	public void setWin(int win) {
		this.win = win;
	}
	public int getLose() {
		return lose;
	}
	public void setLose(int lose) {
		this.lose = lose;
	}
	public MemberVO getProfile() {
		return profile;
	}
	public void setProfile(MemberVO profile) {
		this.profile = profile;
	}
	
}
