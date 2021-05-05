package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import system.client.ClientSystem;
import vo.MemberVO;
import vo.MessageVO;

public class JoinUIEvent implements ActionListener {
	JoinUI jui;
	ClientSystem client;
	boolean idChkResult = true;

	public JoinUIEvent(JoinUI jui, ClientSystem client) {
		this.jui = jui;
		this.client = client;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj == jui.id_check_btn) { // 아이디 중복확인
			if (jui.id_tf.getText().equals("")) {
				// 아이디 입력창 빈칸 검사
				JOptionPane.showMessageDialog(null, Commons.getMsg("아이디를 입력해주세요"));
				jui.id_tf.requestFocus();

			} else if (!jui.list.get(0).equals("")) {
				// 아이디 입력창 빈칸 아닐 때 아이디 중복체크
				idChkResult = id_check();
			}
			// 가입 버튼 & ID, PW, PW확인 창에서 Enter키를 눌렀을 때 가입 시도 가능하게 함
		} else if (obj == jui.join_btn || obj == jui.id_tf || obj == jui.pw_tf || obj == jui.pw_check_tf) {
			if (!idChkResult) { // 아이디 중복확인 한 경우(idChkResult=false) 가입 가능
				if (jui.pw_tf.getText().equals(jui.pw_check_tf.getText())) { // PW == PW확인 체크
					if (join_check()) {
						// 회원가입 정보 저장 및 회원가입 성공여부 확인
						ArrayList<JTextField> jlist = new ArrayList<JTextField>();
						for (Object tf : jui.list) {
							JTextField jtf = (JTextField) tf;
							jlist.add(jtf);
						}
						MessageVO msg = new MessageVO();
						msg.setStatus(MessageVO.JOIN);
						msg.setId(jlist.get(0).getText());
						msg.setPw(jlist.get(1).getText());

						if (client.join(msg)) {
							// 회원가입 성공
							JOptionPane.showMessageDialog(null, Commons.getMsg("회원가입을 완료했습니다"));
							for (Object obj2 : jui.list) {
								JTextField tf = (JTextField) obj2;
								tf.setText("");
							}
							jui.f.setVisible(false);
						} else {
							// 회원가입 실패
							JOptionPane.showMessageDialog(null, Commons.getMsg("회원가입에 실패했습니다"));
						}
					} else { //
						for (Object obj2 : jui.list) {
							JTextField tf = (JTextField) obj2;
						}
					} // if문(join_check)
				} else if (jui.pw_check_tf.getText().isEmpty()) {
					// PW확인이 비어있을 때
					JOptionPane.showMessageDialog(null, Commons.getMsg("PW확인을 입력해주세요"));
					jui.pw_check_tf.requestFocus();
				} else {
					// PW != PW확인
					JOptionPane.showMessageDialog(null, Commons.getMsg("비밀번호가 다릅니다"));
					jui.pw_tf.requestFocus();
				}
			} else { // 아이디 중복체크 안한 경우
				// 아이디 입력하지 않았는데 가입 시도할 수 있으니 빈칸 검사
				if (jui.id_tf.getText().equals("")) {
					JOptionPane.showMessageDialog(null, Commons.getMsg("아이디를 입력해주세요"));
					jui.id_tf.requestFocus();
				} else {
					// 아이디 중복확인 안한 경우(idChkResult=false) 가입 불가능
					JOptionPane.showMessageDialog(null, Commons.getMsg("아이디 중복여부를 확인해주세요"));
					jui.id_tf.requestFocus();
				}
			}
		} else if (obj == jui.cancel_btn) { // 취소 (회원가입 창 종료)
			jui.f.dispose();
		}
	}

	// ID 중복 체크
	public boolean id_check() {
		boolean result = true;
		MessageVO msg = new MessageVO();
		msg.setStatus(MessageVO.IDCHECK);
		msg.setId(jui.id_tf.getText());

		if (result == client.idCheck(msg)) {
			// result = true 이면 DB에 정보가 있으니 사용 불가능
			JOptionPane.showMessageDialog(null, Commons.getMsg("이미 사용중인 아이디입니다"));
			jui.id_tf.requestFocus();
		} else {
			// result = false 이면 DB에 정보가 없으니 사용 가능
			JOptionPane.showMessageDialog(null, Commons.getMsg("사용가능한 아이디입니다"));
			jui.pw_tf.requestFocus();
			result = false;
		}
		return result;
	}// id_check

	// 회원가입 빈칸 체크
	public boolean join_check() {
		boolean result = false;

		// 빈칸 체크를 위한 for문
		for (int i = 0; i < jui.list.size(); i++) {
			JTextField tf = (JTextField) jui.list.get(i);

			if (tf.getText().equals("")) {
				// 빈칸이 있을 경우
				JOptionPane.showMessageDialog(null, Commons.getMsg(jui.joinlist[i] + "를 입력해주세요"));
				tf.requestFocus();
				i = jui.list.size();

			} else if (i == jui.list.size() - 1) {
				// 빈칸이 없을 경우
				result = true;
			}
		}
		return result;
	}// join_check

}
