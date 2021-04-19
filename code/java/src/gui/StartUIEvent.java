package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import system.client.ClientSystem;

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
		if (obj == sui.login_btn || obj == sui.id_tf || obj == sui.pw_tf) {
			login_check();
		} else if (obj == sui.join_btn) {
			new JoinUI(client);
		}
	}// 액션 이벤트

	// 로그인 유효성 검사
	public void login_check() {
		if (sui.id_tf.getText().equals("")) {
			JOptionPane.showMessageDialog(null, Commons.getMsg("아이디를 입력해주세요"));
			sui.id_tf.requestFocus();
		} else if (sui.pw_tf.getText().equals("")) {
			JOptionPane.showMessageDialog(null, Commons.getMsg("패스워드를 입력해주세요"));
			sui.pw_tf.requestFocus();
		} else {
			// 로그인 체크
			boolean result = client.loginCheck(sui.id_tf.getText(), sui.pw_tf.getText());
			if (result) {
				JOptionPane.showMessageDialog(null, Commons.getMsg("로그인 되었습니다."));
				client.setId(sui.id_tf.getText());
				new MainUI(client);
				sui.f.setVisible(false);
			} else {
				JOptionPane.showMessageDialog(null, Commons.getMsg("아이디 또는 패스워드가 틀렸습니다."));
				sui.id_tf.setText("");
				sui.pw_tf.setText("");
				sui.id_tf.requestFocus();
			}
		}
	}// login_check

}
