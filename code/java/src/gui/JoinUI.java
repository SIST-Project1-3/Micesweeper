package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import system.client.ClientSystem;

public class JoinUI {
	JFrame f;
	JPanel west_panel, center_panel, south_panel, label_panel, tf_panel, btn_panel, id_check_panel;
	JButton join_btn, id_check_btn, cancel_btn;
	JTextField id_tf;
	JPasswordField pw_tf, pw_check_tf;
	String[] joinlist = { "ID", "PW", "PW확인" };
	ArrayList<Object> list = new ArrayList<Object>();
	ClientSystem client;
	JoinUIEvent joinEvent;

	public JoinUI(ClientSystem client) {
		this.client = client;
		joinEvent = new JoinUIEvent(this, client);
		init();
	}

	public void init() {
		f = new JFrame("회원가입");
		west_panel = new JPanel();
		center_panel = new JPanel();
		south_panel = new JPanel();
		label_panel = new JPanel(new GridLayout(4, 1));
		tf_panel = new JPanel(new GridLayout(4, 1));
		btn_panel = new JPanel(new FlowLayout());
		id_check_panel = new JPanel(new FlowLayout());

		// 가입, 취소 버튼 생성
		join_btn = new JButton("가입");
		cancel_btn = new JButton("취소");
		join_btn.setFont(Commons.getFont());
		cancel_btn.setFont(Commons.getFont());
		btn_panel.add(join_btn);
		btn_panel.add(cancel_btn);

		// 회원가입 정보 입력창 생성을 위한 for문
		for (String join : joinlist) {
			JLabel join_label = new JLabel(join);
			join_label.setFont(Commons.getFont());
			JPanel l_panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 7));
			JPanel t_panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

			// 중복확인 버튼을 ID텍스트필드 옆에 생성하기 위한 if문
			if (join.equals("ID")) {
				l_panel.add(join_label);
				label_panel.add(l_panel);

				id_tf = new JTextField(15);
				id_check_btn = new JButton("중복확인");
				id_check_btn.setFont(Commons.getFont());
				id_check_panel.add(id_tf);
				id_check_panel.add(id_check_btn);
				tf_panel.add(id_check_panel);
				list.add(id_tf);
			} else if (join.equals("PW")) {
				pw_tf = new JPasswordField(23);
				l_panel.add(join_label);
				t_panel.add(pw_tf);
				label_panel.add(l_panel);
				tf_panel.add(t_panel);
				list.add(pw_tf);
			} else if (join.equals("PW확인")) {
				pw_check_tf = new JPasswordField(23);
				l_panel.add(join_label);
				t_panel.add(pw_check_tf);
				label_panel.add(l_panel);
				tf_panel.add(t_panel);
				list.add(pw_check_tf);
			}
		}

		// 이벤트 연결
		join_btn.addActionListener(joinEvent);
		id_check_btn.addActionListener(joinEvent);
		cancel_btn.addActionListener(joinEvent);
		id_tf.addActionListener(joinEvent);
		pw_tf.addActionListener(joinEvent);
		pw_check_tf.addActionListener(joinEvent);

		west_panel.add(label_panel);
		center_panel.add(tf_panel);
		south_panel.add(btn_panel);

		f.add(BorderLayout.WEST, west_panel);
		f.add(BorderLayout.CENTER, center_panel);
		f.add(BorderLayout.SOUTH, south_panel);

		// 윈도우 이벤트 종료 (회원가입 창)
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				f.dispose();
			}
		});

		f.setResizable(false); // 창크기 변경 막아놓음
		f.setSize(400, 200);
		f.setVisible(true);
	}

}