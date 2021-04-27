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
	boolean turnflag, winflag = false, loseflag = false; // 턴, 승리, 종료 플래그
	int count = 0; // 몇번째 턴인지 표시

	public void setMicebtn(ArrayList<JButton> micebtn) {
		this.micebtn = micebtn;
	}

	public boolean isTurnflag() {
		return turnflag;
	}

	public void setTurnflag(boolean turnflag) {
		this.turnflag = turnflag;
	}

	public ArrayList<JButton> getMicebtn() {
		return micebtn;
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

}
