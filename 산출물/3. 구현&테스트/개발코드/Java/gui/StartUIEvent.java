package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import system.client.ClientSystem;
import vo.MessageVO;

public class StartUIEvent implements ActionListener {
	StartUI sui;
	ClientSystem client;

	public StartUIEvent(StartUI sui, ClientSystem client) {
		this.sui = sui;
		this.client = client;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		// 로그인 버튼 & ID, PW 입력창에서 Enter키를 눌렀을 때 로그인 시도 가능하게 함
		if (obj == sui.login_btn || obj == sui.id_tf || obj == sui.pw_tf) {
			login_check();
		} else if (obj == sui.join_btn) {	// 회원가입 버튼
			new JoinUI(client);
		}
	}

	// 로그인 유효성 검사
	public void login_check() {
		// 로그인 빈칸 체크
		if (sui.id_tf.getText().equals("")) {
			JOptionPane.showMessageDialog(null, Commons.getMsg("아이디를 입력해주세요"));
			sui.id_tf.requestFocus();
		} else if (sui.pw_tf.getText().equals("")) {
			JOptionPane.showMessageDialog(null, Commons.getMsg("패스워드를 입력해주세요"));
			sui.pw_tf.requestFocus();
		} else {
			// 로그인 정보 체크
			MessageVO msg = new MessageVO();
			msg.setStatus(MessageVO.LOGIN);
			msg.setId(sui.id_tf.getText());
			msg.setPw(sui.pw_tf.getText());
			boolean result = client.login(msg);

			if (result) {
				// 로그인 성공
				JOptionPane.showMessageDialog(null, Commons.getMsg("로그인 되었습니다"));
				client.id = sui.id_tf.getText();
				new MainUI(client);
				sui.f.dispose();
			} else {
				// 로그인 실패시 입력했던 정보 리셋
				JOptionPane.showMessageDialog(null, Commons.getMsg("아이디 또는 패스워드가 틀렸습니다"));
				sui.id_tf.setText("");
				sui.pw_tf.setText("");
				sui.id_tf.requestFocus();
			}
		}
	}// login_check

}
