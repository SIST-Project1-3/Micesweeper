package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

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
		if(sui.id_tf.getText().equals("")) {
			JOptionPane.showMessageDialog(null, Commons.getMsg("아이디를 입력해주세요"));
			sui.id_tf.requestFocus();
		}else if(sui.pw_tf.getText().equals("")) {
			JOptionPane.showMessageDialog(null, Commons.getMsg("패스워드를 입력해주세요"));
			sui.pw_tf.requestFocus();
//		}else if(아이디 비교 && 패스워드 비교) {
//			//데이터베이스 연결해서 비교
//			JOptionPane.showMessageDialog(null, Commons.getMsg("로그인 성공"));
//			new MainUI();  //->로그인 성공하면 메인화면으로 넘어가야함
//		}else if(!아이디 비교 || !패스워드 비교){
//			JOptionPane.showMessageDialog(null, Commons.getMsg("아이디 또는 패스워드가 틀렸습니다"));
//			sui.id_tf.requestFocus();
		}
	}
	
	public static void main(String[] args) {
		new StartUI();
	}
	
	
}
