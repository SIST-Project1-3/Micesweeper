package vo;

import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JButton;

// 게임 내 정보들을 담기위한 VO
public class GameVO implements Serializable {
	public ArrayList<JButton> micebtn = new ArrayList<JButton>(); // 게임상의 버튼들
	public ArrayList<Integer> mice = new ArrayList<Integer>(); // 지뢰 위치
	public ArrayList<Integer> clickmice = new ArrayList<Integer>(); // 클릭된 버튼
	public ArrayList<Integer> number = new ArrayList<Integer>(); // 숫자 위치
	public ArrayList<String> userID = new ArrayList<String>(2); // 연결된 유저의 이름
	public ArrayList<Socket> socketList = new ArrayList<Socket>(2); // 연결된 소켓 리스트
	boolean turnflag, winflag = false, loseflag = false; // 턴, 승리, 종료 플래그
	int count = 0, no, userCount; // 누른 버튼의 좌표, 방 번호, 인원 수
	String title; // 방 이름

	public String getInfo() {
		return title + " - " + userCount;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getUserCount() {
		return userCount;
	}

	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}

	public ArrayList<JButton> getMicebtn() {
		return micebtn;
	}

	public void setMicebtn(ArrayList<JButton> micebtn) {
		this.micebtn = micebtn;
	}

	public ArrayList<Integer> getClickmice() {
		return clickmice;
	}

	public void setClickmice(ArrayList<Integer> clickmice) {
		this.clickmice = clickmice;
	}

	public ArrayList<Integer> getMice() {
		return mice;
	}

	public void setMice(ArrayList<Integer> mice) {
		this.mice = mice;
	}

	public ArrayList<Integer> getNumber() {
		return number;
	}

	public void setNumber(ArrayList<Integer> number) {
		this.number = number;
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public ArrayList<String> getUserID() {
		return userID;
	}

	public void setUserID(ArrayList<String> userID) {
		this.userID = userID;
	}

}
