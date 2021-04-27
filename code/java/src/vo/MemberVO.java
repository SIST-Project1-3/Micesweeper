package vo;

import java.io.Serializable;

// DAO를 통해 DB에 접근하기위한 VO
public class MemberVO implements Serializable {
	String id, pw, name, rdate, img; // ID, PW, 이름, 등록일자, 이미지 경로
	int win, lose; // 승리횟수, 패배횟수

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

	public String setImg(String img) {
		return this.img = img;
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

}
