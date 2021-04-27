package vo;

import java.util.ArrayList;

import javax.swing.JButton;

// 게임 내 정보들을 담기위한 VO
public class GameVO {
	public ArrayList<JButton> micebtn = new ArrayList<JButton>();
	public ArrayList<Integer> clickmice = new ArrayList<Integer>();
	public ArrayList<Integer> mice = new ArrayList<Integer>();
	public ArrayList<Integer> number = new ArrayList<Integer>();
	boolean winflag=false, loseflag=false;
	int count=0;

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
}
