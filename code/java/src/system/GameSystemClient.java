package system;

import java.awt.Color;
import java.util.HashMap;
import java.util.Iterator;

import gui.GameUI;
import gui.JoinUI;
import system.client.ClientSystem;

public class GameSystemClient {
//field
	boolean turnflag, winflag=false, loseflag=false;
	int count = 0;
//constructor
	public GameSystemClient() {
		init();
	}
//method
	public void init() {
		//서버로부터 GameVO 받아옴
		//턴 플래그 - 방 생성시 방장은 true, 도전자는 false
		
		if(turnflag == true) {
			gameplay();
			if(winflag == true) {
			//승리 - 서버 전송
				count = 0;
				//종료
			}else if(loseflag == true){
				//패배 - 서버 전송
				count = 0;
				//종료
			}else {
				turnflag = false;
				count++;
				init();
				
			}
		}else {
			gamewait();
			if(winflag == true) {
				//패배 - 서버 전송
				count = 0;
				//종료
				}else if(loseflag == true){
					//승리 - 서버 전송
					count = 0;
					//종료
				}else {
					turnflag = true;
					count++;
					init();
					
				}
		}
	}
	public void gameplay() {
		//UI에서 누른 버튼의 정보를 받아옴

		int btnnum = GameUI.clickmice.get(count);
		open(btnnum);
	}
	public void gamewait() {
		//Server에서 눌려진 버튼의 정보를 받아옴

		int btnnum = GameUI.clickmice.get(count);
		open(btnnum);
	}
	public void open(int btnnum) {
		int numcount = 0;
		if(btnnum == GameSystemServer.mice.get(0)) {//첫번째 지뢰(고양이) 누를시
			GameUI.micebtn.get(btnnum).setBackground(Color.BLUE); // 색 설정
			GameUI.micebtn.get(btnnum).setIcon();//고양이 아이콘 삽입
			GameUI.micebtn.get(btnnum).setEnabled(false); // 버튼 비활성화
			winflag = true;
			System.out.println("현재 턴 플레이어 승리(고양이)");
		}else {
			for(int k=1; k<GameSystemServer.mice.size(); k++) {//나머지 지뢰(쥐) 누를시
				if(btnnum == GameSystemServer.mice.get(k)) {
					GameUI.micebtn.get(btnnum).setBackground(Color.RED);
					GameUI.micebtn.get(btnnum).setIcon();//쥐 아이콘 삽입
					GameUI.micebtn.get(btnnum).setEnabled(false); // 버튼 비활성화
					loseflag = true;
					System.out.println("현재 턴 플레이어 패배(쥐)");
				}
			}
		}//지뢰 체크 - 지뢰 있을 시 winflag나 loseflag가 변동
			if(winflag == false & loseflag == false) {//지뢰를 안건드렸을때
					for(int m=0; m<GameSystemServer.number.size();m++) {
						if(btnnum == GameSystemServer.number.get(m)) {
							numcount++;//근처 지뢰 갯수 추가
						}
					}//누른 버튼이 지뢰가 아니므로 근처 지뢰가 몇개인지 검색 for
						if (numcount != 0) {//지뢰 칸이 숫자일때(주변에 지뢰가 1개 이상)
							GameUI.micebtn.get(btnnum).setText(Integer.toString(numcount)); // 주변 지뢰 숫자 표시
							GameUI.micebtn.get(btnnum).setBackground(Color.GRAY);
							GameUI.micebtn.get(btnnum).setEnabled(false); // 버튼 비활성화
						}else {//지뢰 칸이 공백일때(주변에 지뢰 없음)
							GameUI.micebtn.get(btnnum).setBackground(Color.LIGHT_GRAY);
							GameUI.micebtn.get(btnnum).setEnabled(false); // 버튼 비활성화
							int a=btnnum/9;
							int b=btnnum%9;//아래는 주변 8칸을 여는 작업
							if(a==0) {
								if(b==0) {
									open(((a)*9+(b+1)));
									open(((a+1)*9+(b)));
									open(((a+1)*9+(b+1)));
								}else if(b==8) {
									open(((a)*9+(b-1)));
									open(((a+1)*9+(b-1)));
									open(((a+1)*9+(b)));
								}else {
									open(((a)*9+(b-1)));
									open(((a)*9+(b+1)));
									open(((a+1)*9+(b-1)));
									open(((a+1)*9+(b)));
									open(((a+1)*9+(b+1)));
								}
							}else if(a==8) {
								if(b==0) {
									open(((a-1)*9+(b)));
									open(((a-1)*9+(b+1)));
									open(((a)*9+(b+1)));
								}else if(b==8) {
									open(((a-1)*9+(b-1)));
									open(((a-1)*9+(b)));
									open(((a)*9+(b-1)));
								}else {
									open(((a-1)*9+(b-1)));
									open(((a-1)*9+(b)));
									open(((a-1)*9+(b+1)));
									open(((a)*9+(b-1)));
									open(((a)*9+(b+1)));
								}
							}else {
								if(b==0) {
									open(((a-1)*9+(b)));
									open(((a-1)*9+(b+1)));
									open(((a)*9+(b+1)));
									open(((a+1)*9+(b)));
									open(((a+1)*9+(b+1)));
								}else if(b==8) {
									open(((a-1)*9+(b-1)));
									open(((a-1)*9+(b)));
									open(((a)*9+(b-1)));
									open(((a+1)*9+(b-1)));
									open(((a+1)*9+(b)));
								}else {
									open(((a-1)*9+(b-1)));
									open(((a-1)*9+(b)));
									open(((a-1)*9+(b+1)));
									open(((a)*9+(b-1)));
									open(((a)*9+(b+1)));
									open(((a+1)*9+(b-1)));
									open(((a+1)*9+(b)));
									open(((a+1)*9+(b+1)));
								}
							}
						}//숫자 else
					
				}//노지뢰 if	
		}
	public static void main(String[] args) {
		new GameSystemClient();
	}
		
	}

