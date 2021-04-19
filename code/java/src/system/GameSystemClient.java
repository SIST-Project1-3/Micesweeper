package system;

import gui.GameUI;

public class GameSystemClient {
//field
	boolean turnflag, winflag, loseflag;
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
			}else if(loseflag == true){
				//패배 - 서버 전송
			}else {
				turnflag = false;
				init();
			}
		}else {
			gamewait();
			if(winflag == true) {
				//패배 - 서버 전송
				}else if(loseflag == true){
					//승리 - 서버 전송
				}else {
					turnflag = true;
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
		int btnnum = GameUI.micebtn;
		if(btnnum == GameSystemServer.mice.get(0)) {
			winflag = true;
		}else {
		while(GameSystemServer.mcit.hasNext()) {
			int value = GameSystemServer.mcit.next();
			if (value == btnnum) {
				loseflag = true;
			}
		}
		}
	}
}
