package gamesystem;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JButton;

import gui.GameUI;
import gui.JoinUI;
import system.client.ClientSystem;
import vo.GameVO;

public class GameSystemClient {
	// field
	public boolean turnflag = true, winflag = false, loseflag = false;
	int count = 0;
	GameVO gvo;
	GameUI ui;

	// constructor
	public GameSystemClient(GameUI ui) {
		this.ui = ui;
		gvo = ui.gvo;
		// 턴 플래그 - 방 생성시 방장은 true, 도전자는 false

	}

	// method
	// 버튼 클릭 연산
	public void calcBtnClick() {

		if (turnflag == true) {
			gameplay();
			if (winflag == true) {
				gvo.setWinflag(true);
				gvo.setCount(count);
				// 승리 팝업창
				count = 0;
				exit();
			} else if (loseflag == true) {
				gvo.setLoseflag(true);
				gvo.setCount(count);
				// 패배 팝업창
				count = 0;
				exit();
			} else {
				turnflag = false;
				count++;
				calcBtnClick();

			}
		} else {
			while(gvo.isClickflag()==true) {
			gamewait();
			if (winflag == true) {
				// 패배 팝업창
				count = 0;
				// 종료
			} else if (loseflag == true) {
				// 승리 팝업창
				count = 0;
				// 종료
			} else {
				turnflag = true;
				count++;

			}
			}
		}
	}

	public void gameplay() {
		// UI에서 누른 버튼의 정보를 받아옴
		System.out.println("내 턴");
		System.out.println(count);
		int btnnum = gvo.getClickmice().get(count); //
		open(btnnum);
		gvo.setClickflag(false);
	}

	public void gamewait() {
		// Server에서 눌려진 버튼의 정보를 받아옴
		System.out.println("상대 턴");
		System.out.println(count);
		System.out.println(gvo.getClickmice());
		int btnnum = gvo.getClickmice().get(count);
		open(btnnum);
		gvo.setClickflag(false);
	}

	public void open(int btnnum) {

		int numcount = 0;
		JButton btn;
		btn = gvo.getMicebtn().get(btnnum);
		if (btn.isEnabled() == true) {
			if (btnnum == gvo.getMice().get(0)) {// 첫번째 지뢰(고양이) 누를시
				btn.setBackground(Color.BLUE); // 색 설정
				// btn.setIcon();//고양이 아이콘 삽입
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
						// btn.setIcon();//고양이 아이콘 삽입
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
					btn.setText(Integer.toString(numcount));// 주변 지뢰 숫자 표시
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
