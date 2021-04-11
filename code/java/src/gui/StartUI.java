package gui;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class StartUI implements ActionListener{
	Frame f = new Frame();
	Panel center_panel, south_panel, login_panel, btn_panel;
	JButton login_btn, join_btn;
	
	public StartUI() {
		init();
	}
	
	public void init() {
		f = new Frame("쥐뢰찾기");
		center_panel = new Panel(new BorderLayout());
		south_panel = new Panel();
		login_panel = new Panel();
		btn_panel = new Panel();
		Panel label_panel = new Panel(new GridLayout(2,1));
		Panel tf_panel = new Panel(new GridLayout(2,1));
		
		//ID, PW 입력창 생성
		Label id_label = new Label("ID");
		Label pw_label = new Label("PW");
		JTextField id_tf = new JTextField(15);
		JTextField pw_tf = new JTextField(15);
		id_label.setFont(Commons.getFont());
		pw_label.setFont(Commons.getFont());
		label_panel.add(id_label);
		label_panel.add(pw_label);
		tf_panel.add(id_tf);
		tf_panel.add(pw_tf);
		login_panel.add(label_panel);
		login_panel.add(tf_panel);
		
		//하단의 로그인 회원가입 버튼 생성
		login_btn = new JButton("로그인");
		join_btn = new JButton("회원가입");
		login_btn.setFont(Commons.getFont());
		join_btn.setFont(Commons.getFont());
		btn_panel.add(login_btn);
		btn_panel.add(join_btn);
		
		//이미지 삽입 & 크기조절
		//ImageIcon icon = new ImageIcon("images/쥐뢰찾기(선있음).png");
		ImageIcon icon = new ImageIcon("images/쥐뢰찾기(선없음).png");
		Image img = icon.getImage();
		Image changeImg = img.getScaledInstance(700, 650, Image.SCALE_SMOOTH);
		ImageIcon changeIcon = new ImageIcon(changeImg);
		JLabel img_label = new JLabel(changeIcon);
	    center_panel.add(img_label);
		
		center_panel.add(BorderLayout.SOUTH,login_panel);
		south_panel.add(btn_panel);
		
		//로그인, 회원가입 버튼 액션이벤트
		login_btn.addActionListener(this);
		join_btn.addActionListener(this);
		
		f.add(BorderLayout.CENTER,center_panel);
		f.add(BorderLayout.SOUTH,south_panel);
		
		f.setResizable(false);	//창크기 변경 막아놓음
		f.setSize(700, 700);
		f.setVisible(true);
		
		//윈도우 종료 이벤트	
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
			System.exit(0);
			}
		});
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == login_btn) {
			login_check();
		}else if(obj == join_btn) {
			new JoinUI();
		}
	}
	
	//로그인 유효성 검사
	public void login_check() {
		//데이터베이스 연결해서 비교
		//아이디,패스워드 틀렸을 시 '아이디/패스워드가 틀렸습니다.'메세지 띄우기
		
		
		
	}
	
	public static void main(String[] args) {
		new StartUI();
	}
}

