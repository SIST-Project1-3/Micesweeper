package gamesystem;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import gui.Commons;
import gui.GameUI;
import gui.JoinUI;
import system.client.ClientSystem;
import vo.GameVO;
import vo.RoomVO;

public class GameSystemClient {
	// field
	public boolean turnflag, winflag = false, loseflag = false;
	int count = 0;
	public GameVO gvo;
	GameUI ui;

	// constructor
	public GameSystemClient(GameUI ui, boolean turnflag, RoomVO room) {
		this.ui = ui;
		this.turnflag = turnflag;
		gvo = room.game;
		// 턴 플래그 - 방 생성시 방장은 true, 도전자는 false

	}

	// method
	// 버튼 클릭 연산
	public void calcBtnClick(int btnnum) {

		gameplay(btnnum);//승패 기록 갱신 안됨
		if (turnflag == true) {
			if (winflag == true) {
				gvo.setWinflag(true);
				ui.event.win();
				JOptionPane.showMessageDialog(null, Commons.getMsg("승리!!"));
				gvo.setGameflag(false);

				// 승리 팝업창
				exit();
			} else if (loseflag == true) {
				gvo.setLoseflag(true);
				ui.event.lose();
				JOptionPane.showMessageDialog(null, Commons.getMsg("패배..."));
				gvo.setGameflag(false);
				// 패배 팝업창
				exit();
			} else {
				turnflag = false;
			}
		} else {
			if (winflag == true) {
				gvo.setLoseflag(true);
				ui.event.lose();
				JOptionPane.showMessageDialog(null, Commons.getMsg("패배..."));
				gvo.setGameflag(false);
				// 패배 팝업창
				// 종료
			} else if (loseflag == true) {
				gvo.setWinflag(true);
				ui.event.win();
				JOptionPane.showMessageDialog(null, Commons.getMsg("승리!!"));
				gvo.setGameflag(false);
				// 승리 팝업창
				// 종료
			} else {
				turnflag = true;
			}

		}
	}

	public void gameplay(int btnnum) {
		open(btnnum);
		if(turnflag == false) {
			ui.center_panel.setBorder(new LineBorder(Color.red,1));
		}else {
			ui.center_panel.setBorder(null);
		}
	}

	public void open(int btnnum) {

		int numcount = 0;
		JButton btn;
		btn = gvo.getMicebtn().get(btnnum);
		if (btn.isEnabled() == true) {
			if (btnnum == gvo.getMice().get(0)) {// 첫번째 지뢰(고양이) 누를시
				btn.setBackground(Color.BLUE); // 색 설정
				ImageIcon cat = new ImageIcon("images/고양이.png");
				Image img = cat.getImage();
				Image changeImg = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
				ImageIcon cat2 = new ImageIcon(changeImg);
				btn.setIcon(cat2);//고양이 아이콘 삽입
				btn.setEnabled(false); // 버튼 비활성화
				ArrayList<JButton> arjb;
				arjb = gvo.getMicebtn();
				arjb.set(btnnum, btn);
				gvo.setMicebtn(arjb);
				winflag = true;
				System.out.println("현재 턴 플레이어 승리(고양이)");
			} else {
				for (int k = 1; k < gvo.getMice().size(); k++) {// 나머지 지뢰(쥐) 누를시
					if (btnnum == gvo.getMice().get(k)) {

						btn.setBackground(Color.RED); // 색 설정
						ImageIcon mice = new ImageIcon("images/쥐.png");
						Image img = mice.getImage();
						Image changeImg = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
						ImageIcon mice2 = new ImageIcon(changeImg);
						btn.setIcon(mice2);//쥐 아이콘 삽입
						btn.setEnabled(false); // 버튼 비활성화
						ArrayList<JButton> arjb;
						arjb = gvo.getMicebtn();
						arjb.set(btnnum, btn);
						gvo.setMicebtn(arjb);
						loseflag = true;
						System.out.println("현재 턴 플레이어 패배(쥐)");
					}
				}
			} // 지뢰 체크 - 지뢰 있을 시 winflag나 loseflag가 변동
			if (winflag == false & loseflag == false) {// 지뢰를 안건드렸을때
				for (int m = 0; m < gvo.getNumber().size(); m++) {
					if (btnnum == gvo.getNumber().get(m)) {
						numcount++;// 근처 지뢰 갯수 추가
					}
				} // 누른 버튼이 지뢰가 아니므로 근처 지뢰가 몇개인지 검색 for

				if (numcount != 0) {// 지뢰 칸이 숫자일때(주변에 지뢰가 1개 이상)
					btn.setBackground(Color.LIGHT_GRAY); // 색 설정
					btn.setText(Integer.toString(numcount));// 주변 지뢰 숫자 표시
					btn.setEnabled(false); // 버튼 비활성화
					ArrayList<JButton> arjb;
					arjb = gvo.getMicebtn();
					arjb.set(btnnum, btn);
					gvo.setMicebtn(arjb);
				} else {// 지뢰 칸이 공백일때(주변에 지뢰 없음)
					btn.setBackground(Color.GRAY); // 색 설정
					btn.setEnabled(false); // 버튼 비활성화
					ArrayList<JButton> arjb;
					arjb = gvo.getMicebtn();
					arjb.set(btnnum, btn);
					gvo.setMicebtn(arjb);
					int a = btnnum / 9;
					int b = btnnum % 9;// 아래는 주변 8칸을 여는 작업
					if (a == 0) {
						if (b == 0) {
							open(((a) * 9 + (b + 1)));
							open(((a + 1) * 9 + (b)));
							open(((a + 1) * 9 + (b + 1)));
						} else if (b == 8) {
							open(((a) * 9 + (b - 1)));
							open(((a + 1) * 9 + (b - 1)));
							open(((a + 1) * 9 + (b)));
						} else {
							open(((a) * 9 + (b - 1)));
							open(((a) * 9 + (b + 1)));
							open(((a + 1) * 9 + (b - 1)));
							open(((a + 1) * 9 + (b)));
							open(((a + 1) * 9 + (b + 1)));
						}
					} else if (a == 8) {
						if (b == 0) {
							open(((a - 1) * 9 + (b)));
							open(((a - 1) * 9 + (b + 1)));
							open(((a) * 9 + (b + 1)));
						} else if (b == 8) {
							open(((a - 1) * 9 + (b - 1)));
							open(((a - 1) * 9 + (b)));
							open(((a) * 9 + (b - 1)));
						} else {
							open(((a - 1) * 9 + (b - 1)));
							open(((a - 1) * 9 + (b)));
							open(((a - 1) * 9 + (b + 1)));
							open(((a) * 9 + (b - 1)));
							open(((a) * 9 + (b + 1)));
						}
					} else {
						if (b == 0) {
							open(((a - 1) * 9 + (b)));
							open(((a - 1) * 9 + (b + 1)));
							open(((a) * 9 + (b + 1)));
							open(((a + 1) * 9 + (b)));
							open(((a + 1) * 9 + (b + 1)));
						} else if (b == 8) {
							open(((a - 1) * 9 + (b - 1)));
							open(((a - 1) * 9 + (b)));
							open(((a) * 9 + (b - 1)));
							open(((a + 1) * 9 + (b - 1)));
							open(((a + 1) * 9 + (b)));
						} else {
							open(((a - 1) * 9 + (b - 1)));
							open(((a - 1) * 9 + (b)));
							open(((a - 1) * 9 + (b + 1)));
							open(((a) * 9 + (b - 1)));
							open(((a) * 9 + (b + 1)));
							open(((a + 1) * 9 + (b - 1)));
							open(((a + 1) * 9 + (b)));
							open(((a + 1) * 9 + (b + 1)));
						}
					}
				} // 숫자 else

			} // 노지뢰 if
		}
	}

	public void exit() {

	}

}
