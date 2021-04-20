package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.MemberDAO;
import system.client.ClientSystem;
import system.server.ServerSystem;
import vo.MemberVO;

public class JoinUIEvent implements ActionListener {
	JoinUI jui;
	ClientSystem client;

	public JoinUIEvent(JoinUI jui, ClientSystem client) {
		this.jui = jui;
		this.client = client;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		// 아이디 중복확인 버튼
		if (obj == jui.id_check_btn) {
			// 아이디 입력창 빈칸 검사
			if (jui.id_tf.getText().equals("")) {
				JOptionPane.showMessageDialog(null, Commons.getMsg("아이디를 입력해주세요."));
				jui.id_tf.requestFocus();
			} else if (!jui.list.get(0).equals("")) {
				// 아이디 입력창 빈칸 아닐 때 중복검사
				id_check();
			}
			// 가입 버튼
		} else if (obj == jui.join_btn) {
			// 아이디 중복확인 안한 경우(result=false) 가입 안되게 하기
//			boolean result = client.idCheck(jui.id_tf.getText());
			boolean result = id_check();

			if (!result) {
				// PW == PW확인 이 맞는지 체크
				if (jui.pw_tf.getText().equals(jui.pw_check_tf.getText())) {
					if (join_check()) {
						// 회원가입 정보 저장 및 회원가입 성공여부 확인
						ArrayList<JTextField> jlist = new ArrayList<JTextField>();
						for (Object tf : jui.list) {
							JTextField jtf = (JTextField) tf;
							jlist.add(jtf);
						}
						MemberVO member = new MemberVO();
						member.setId(jlist.get(0).getText());
						member.setPw(jlist.get(1).getText());

						boolean result2 = client.joinCheck(member);
						if (result2) {
							JOptionPane.showMessageDialog(null, Commons.getMsg("회원가입을 완료했습니다."));
							for (Object obj2 : jui.list) {
								JTextField tf = (JTextField) obj2;
								tf.setText("");
							}
							jui.f.setVisible(false);
						} else {
							JOptionPane.showMessageDialog(null, Commons.getMsg("회원가입에 실패했습니다."));
						}
					} else {
						for (Object obj2 : jui.list) {
							JTextField tf = (JTextField) obj2;
						}
					} // if문(join_check)
					// PW확인이 비어있을 때
				} else if (jui.pw_check_tf.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, Commons.getMsg("PW확인을 입력해주세요."));
					jui.pw_check_tf.requestFocus();
					// PW != PW확인
				} else {
					JOptionPane.showMessageDialog(null, Commons.getMsg("비밀번호가 다릅니다."));
					jui.pw_tf.requestFocus();
				}
			} else {
				JOptionPane.showMessageDialog(null, Commons.getMsg("아이디 중복여부를 확인해주세요."));
				jui.id_tf.requestFocus();
			}
			// 취소 버튼
		} else if (obj == jui.cancel_btn) {
			// 회원가입창 종료
			jui.f.dispose();
		}
	}// 액션이벤트

	// ID 중복 체크
	public boolean id_check() {
		boolean result = true;
		// result가 true면 중복된 아이디가 있다는 의미

		if (result == client.idCheck(jui.id_tf.getText())) {
			JOptionPane.showMessageDialog(null, Commons.getMsg("이미 사용중인 아이디입니다"));
			jui.id_tf.requestFocus();
		} else {
			JOptionPane.showMessageDialog(null, Commons.getMsg("사용가능한 아이디입니다"));
			jui.pw_tf.requestFocus();
			result = false;
		}
		return result;
	}// id_check

	// 회원가입 빈칸 체크
	public boolean join_check() {
		boolean result = false;

		for (int i = 0; i < jui.list.size(); i++) {
			JTextField tf = (JTextField) jui.list.get(i);

			if (tf.getText().equals("")) {
				JOptionPane.showMessageDialog(null, Commons.getMsg(jui.joinlist[i] + "를 입력해주세요"));
				tf.requestFocus();
				i = jui.list.size();

			} else if (i == jui.list.size() - 1) {
				result = true;
			}
		}
		return result;
	}// join_check

}
