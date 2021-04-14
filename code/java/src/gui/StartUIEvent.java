package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartUIEvent implements ActionListener{
	StartUI sui;
	
	public StartUIEvent(StartUI sui) {
		this.sui = sui;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == sui.login_btn) {
			login_check();
		}else if(obj == sui.join_btn) {
			new JoinUI();
		}
	}
	
	//로그인 유효성 검사
	public void login_check() {
		//데이터베이스 연결해서 비교
		//아이디,패스워드 틀렸을 시 '아이디/패스워드가 틀렸습니다.'메세지 띄우기
		
		if(sui.id_tf.equals("")) {
			
		}else if(!sui.id_tf.equals("")) {
			
		}
		
		
	}
}
