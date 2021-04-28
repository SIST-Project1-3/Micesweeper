package gamesystem;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

import system.server.ServerSystem;
import vo.GameVO;
import vo.MessageVO;
import vo.RoomVO;

public class GameSystemServer {
	// Field
	boolean gameflag, readyflag;
	int miceCount; // 지뢰 개수
	public ArrayList<Integer> mice; // 지뢰칸 위치
	public ArrayList<Integer> number; // 지뢰 주변 숫자칸 위치
	public GameVO gvo;
	public int loc; // 누른 버튼의 좌표
	public RoomVO room;

	// Constructor
	public GameSystemServer() {
		gvo = new GameVO();
		room = new RoomVO();
		createMice();
	}

	// Method

	// 메소드 명을 알아보기 쉽게 수정해주세요.
	public void init() {
		gameflag = false; // 나중에 false로 바꾸고, 시작하면 true 되는 로직 생성해야함
		// 준비 + 시작하는 과정
		if (gameflag = true) {
			gvo.setGameflag(gameflag);
			createMice();// 지뢰, 숫자칸 생성+각 클라이언트에 지뢰 숫자칸 정보 주기
			simpan();
			// 게임이 끝났다는 신호를 받으면 전적 갱신후 메인화면으로(?)
		} else {
			init();
		}
	}

	public void createMice() {
		mice = gvo.getMice();
		number = gvo.getNumber();// 지뢰, 숫자 어레이리스트 생성
		boolean mflag = true; // 중복값 판단하는 flag
		miceCount = 10 + (int) ((Math.random()) * 10000) % 6; // 지뢰갯수 설정
		System.out.println("k=" + miceCount); // 지뢰갯수 출력
		for (int i = 0; i < miceCount; i++) {
			System.out.println(i);
			int l = (int) (Math.random() * 10000) % 81;
			System.out.println("지뢰=" + l);
			System.out.println(mice.size());
			for (int m = 0; m < mice.size(); m++) {
				int value = mice.get(m);
				if (value == l) {
					mflag = false;
				}
			}
			System.out.println(mflag);
			if (mflag == false) {
				i--;
				System.out.println("중복!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				mflag = true;
			} else if (mflag == true) {
				mice.add(l);
				gvo.setMice(mice);
				System.out.println(gvo.getMice());
				int a = l / 9;
				int b = l % 9;
				if (a == 0) {
					if (b == 0) {
						number.add((a) * 9 + (b + 1));
						number.add((a + 1) * 9 + (b));
						number.add((a + 1) * 9 + (b + 1));
					} else if (b == 8) {
						number.add((a) * 9 + (b - 1));
						number.add((a + 1) * 9 + (b - 1));
						number.add((a + 1) * 9 + (b));
					} else {
						number.add((a) * 9 + (b - 1));
						number.add((a) * 9 + (b + 1));
						number.add((a + 1) * 9 + (b - 1));
						number.add((a + 1) * 9 + (b));
						number.add((a + 1) * 9 + (b + 1));
					}
				} else if (a == 8) {
					if (b == 0) {
						number.add((a - 1) * 9 + (b));
						number.add((a - 1) * 9 + (b + 1));
						number.add((a) * 9 + (b + 1));
					} else if (b == 8) {
						number.add((a - 1) * 9 + (b - 1));
						number.add((a - 1) * 9 + (b));
						number.add((a) * 9 + (b - 1));
					} else {
						number.add((a - 1) * 9 + (b - 1));
						number.add((a - 1) * 9 + (b));
						number.add((a - 1) * 9 + (b + 1));
						number.add((a) * 9 + (b - 1));
						number.add((a) * 9 + (b + 1));
					}
				} else {
					if (b == 0) {
						number.add((a - 1) * 9 + (b));
						number.add((a - 1) * 9 + (b + 1));
						number.add((a) * 9 + (b + 1));
						number.add((a + 1) * 9 + (b));
						number.add((a + 1) * 9 + (b + 1));
					} else if (b == 8) {
						number.add((a - 1) * 9 + (b - 1));
						number.add((a - 1) * 9 + (b));
						number.add((a) * 9 + (b - 1));
						number.add((a + 1) * 9 + (b - 1));
						number.add((a + 1) * 9 + (b));
					} else {
						number.add((a - 1) * 9 + (b - 1));
						number.add((a - 1) * 9 + (b));
						number.add((a - 1) * 9 + (b + 1));
						number.add((a) * 9 + (b - 1));
						number.add((a) * 9 + (b + 1));
						number.add((a + 1) * 9 + (b - 1));
						number.add((a + 1) * 9 + (b));
						number.add((a + 1) * 9 + (b + 1));
					}
				}
				gvo.setNumber(number);
				System.out.println(gvo.getNumber());
			} // else
		} // for

	}

	public void simpan() {
		if (gvo.isLoseflag() == true || gvo.isWinflag() == true) {
			int winner = gvo.getCount() % 2;
			if (winner == 0) {
				// 방장승리
			} else {
				// 도전자승리
			}
			// 승리자 패배자 db입력
			gameflag = false;// 종료
			gvo.setGameflag(gameflag);
		} else {
			simpan();
		}
	}

}