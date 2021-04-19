package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import system.client.ClientSystem;

public class StartUI {
	// Field
	JFrame f = new JFrame();
	JPanel center_panel, south_panel, login_panel, btn_panel;
	JButton login_btn, join_btn;
	JTextField id_tf;
	JPasswordField pw_tf;
	ClientSystem client= new ClientSystem();
	StartUIEvent loginEvent = new StartUIEvent(this, client);

	// Constructor
	public StartUI() {
		init();
	}

	// Method
	public void init() {
		f = new JFrame("쥐뢰찾기");
		center_panel = new JPanel(new BorderLayout());
		south_panel = new JPanel();
		login_panel = new JPanel();
		btn_panel = new JPanel();
		JPanel label_panel = new JPanel(new GridLayout(2, 1));
		JPanel tf_panel = new JPanel(new GridLayout(2, 1));

		// ID, PW 입력창 생성
		JLabel id_label = new JLabel("ID");
		JLabel pw_label = new JLabel("PW");
		id_tf = new JTextField(15);
		id_tf.addActionListener(loginEvent);
		pw_tf = new JPasswordField(15);
		pw_tf.addActionListener(loginEvent);
		id_label.setFont(Commons.getFont());
		pw_label.setFont(Commons.getFont());
		label_panel.add(id_label);
		label_panel.add(pw_label);
		tf_panel.add(id_tf);
		tf_panel.add(pw_tf);
		login_panel.add(label_panel);
		login_panel.add(tf_panel);

		// 하단의 로그인 회원가입 버튼 생성
		login_btn = new JButton("로그인");
		join_btn = new JButton("회원가입");
		login_btn.setFont(Commons.getFont());
		join_btn.setFont(Commons.getFont());
		btn_panel.add(login_btn);
		btn_panel.add(join_btn);

		// 이미지 삽입 & 크기조절
		ImageIcon icon = new ImageIcon("images/쥐뢰찾기(선없음).png");
		Image img = icon.getImage();
		Image changeImg = img.getScaledInstance(700, 650, Image.SCALE_SMOOTH);
		ImageIcon changeIcon = new ImageIcon(changeImg);
		JLabel img_label = new JLabel(changeIcon);
		center_panel.add(img_label);

		center_panel.add(BorderLayout.SOUTH, login_panel);
		south_panel.add(btn_panel);

		// StartUIEvent 연결
		login_btn.addActionListener(loginEvent);
		join_btn.addActionListener(loginEvent);

		f.add(BorderLayout.CENTER, center_panel);
		f.add(BorderLayout.SOUTH, south_panel);

		f.setResizable(false);	// 창크기 변경 막아놓음
		f.setSize(700, 700);
		f.setVisible(true);

		// 윈도우 종료 이벤트
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

	}

	public static void main(String[] args) {
		new StartUI();
	}

}
