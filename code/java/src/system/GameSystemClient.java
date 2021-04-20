package system;

import java.util.HashMap;

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
			}else if(loseflag == true){
				//패배 - 서버 전송
				count = 0;
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
				}else if(loseflag == true){
					//승리 - 서버 전송
					count = 0;
				}else {
					turnflag = true;
					count++;
					init();
					
				}
		}
	}
	public void gameplay() {
		//UI에서 누른 버튼의 정보를 받아옴
		open();
	}
	public void gamewait() {
		//Server에서 눌려진 버튼의 정보를 받아옴
		open();
	}
	public void open() {
		int btnnum = GameUI.clickmice.get(count);
		if(btnnum == GameSystemServer.mice.get(0)) {
			winflag = true;
			System.out.println("현재 턴 플레이어 승리(고양이)");
		}else {
		while(GameSystemServer.mcit.hasNext()) {
			int value = GameSystemServer.mcit.next();
			if (value == btnnum) {
				loseflag = true;
				System.out.println("현재 턴 플레이어 패배(쥐)");
			}else {
				while(GameSystemServer.nuit.hasNext()) {
					int value2 = GameSystemServer.nuit.next();
					if(value2 == btnnum) {
						int count3 = 1;
						
						}
						}
					}
				}
			}
		}
	public static void main(String[] args) {
		new GameSystemClient();
	}
		
	}

