package vo;

import java.util.ArrayList;

import javax.swing.JButton;

// 게임 내 정보들을 담기위한 VO
public class GameVO {
	public ArrayList<JButton> micebtn;
	public ArrayList<Integer> clickmice;
	static ArrayList<Integer> mice;
	static ArrayList<Integer> number;
	boolean turnflag, winflag, loseflag;
	int count;
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
	public static ArrayList<Integer> getMice() {
		return mice;
	}
	public static void setMice(ArrayList<Integer> mice) {
		GameVO.mice = mice;
	}
	public static ArrayList<Integer> getNumber() {
		return number;
	}
	public static void setNumber(ArrayList<Integer> number) {
		GameVO.number = number;
	}
	public boolean isTurnflag() {
		return turnflag;
	}
	public  void setTurnflag(boolean turnflag) {
		this.turnflag = turnflag;
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
