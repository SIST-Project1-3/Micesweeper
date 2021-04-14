package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class JoinUIEvent implements ActionListener{
	JoinUI jui;
	
	public JoinUIEvent(JoinUI jui) {
		this.jui = jui;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == jui.id_check_btn) {
			if(jui.join_tf.getText().equals("")) {
				JOptionPane.showMessageDialog(null, Commons.getMsg("아이디를 입력하세요"));
			}else if(!jui.join_tf.getText().equals("")) {
				id_check();
			}
			
		}else if(obj == jui.join_btn) {
			//회원가입 입력정보 체크 후 저장
			join_check();
		}else if(obj == jui.cancel_btn) {
			//회원가입창 종료
			jui.f.dispose();
		}
	}
	

	//ID 중복 체크
	public void id_check() {
		//데이터베이스 연결해서 비교 or  UNIQUE 사용?
//		if(DB에 없을 때) {
//			JOptionPane.showMessageDialog(null, Commons.getMsg("사용가능한 아이디입니다"));
//			jui.join_tf.requestFocus();
//		}else if(이미 있는 아이디일 때) {
//			JOptionPane.showMessageDialog(null, Commons.getMsg("중복된 아이디입니다"));
//			jui.join_tf.requestFocus();
//		}
	}//id_check
		
	//회원가입 정보 입력 체크
	public void  join_check() {
//		//빈칸 검사 for문
//		for() {
//			if(){
//			
//			
//				//아이디 중복확인 안한 경우 가입 못하게 하기 + 포커스 아이디쪽으로 맞출수있나..?
//				
//				
//			}else if() {
//				//가입 성공시 '회원가입을 완료했습니다' 메세지 생성 + 데이터 저장로직
//				
//				
//			}
//		}
	}//join_check
	
	public static void main(String[] args) {
		new JoinUI();
	}
	
	
	
}
