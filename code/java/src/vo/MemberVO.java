package vo;

import java.io.Serializable;

import javax.swing.ImageIcon;

// DAO를 통해 DB에 접근하기위한 VO
public class MemberVO implements Serializable {
	String id, pw, name, rdate, img2; // ID, PW, 이름, 등록일자, 참가자이미지
	ImageIcon img; // 방장이미지
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

	public ImageIcon getImg() {
		return img;
	}

	public void setImg(ImageIcon img) {
		this.img = img;
	}

	public String getImg2() {
		return img2;
	}

	public void setImg2(String img2) {
		this.img2 = img2;
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
