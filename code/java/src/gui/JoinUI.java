package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JoinUI implements ActionListener{
	JFrame f;
	JPanel west_panel, center_panel, south_panel,
		  label_panel, tf_panel, btn_panel, id_check_panel;
	String[] joinlist = {"ID","PW","PW확인","닉네임"};
	JButton join_btn, id_check_btn, cancel_btn;
	
	public JoinUI() {
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
		
		//하단의 가입, 취소 버튼 생성
		join_btn = new JButton("가입");
		cancel_btn = new JButton("취소");
		join_btn.setFont(Commons.getFont());
		cancel_btn.setFont(Commons.getFont());
		btn_panel.add(join_btn);
		btn_panel.add(cancel_btn);
		
		//회원가입 정보 입력창 생성을 위한 for문
		//텍스트필드에 포커스 맞출까..?
		for(String join : joinlist) {
			JLabel join_label = new JLabel(join);
			join_label.setFont(Commons.getFont());
			JPanel l_panel = new JPanel(new FlowLayout(FlowLayout.LEFT,0,7));
			JPanel t_panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	
			//중복확인 버튼을 ID텍스트필드 옆에 생성하기 위한 if문
			if(join.equals("ID")) {
				l_panel.add(join_label);
				label_panel.add(l_panel);
				
				JTextField join_tf = new JTextField(15);
				id_check_btn = new JButton("중복확인");
				id_check_btn.setFont(Commons.getFont());
				id_check_panel.add(join_tf);
				id_check_panel.add(id_check_btn);
				tf_panel.add(id_check_panel);
				
			}else {
				JTextField join_tf = new JTextField(23);
				l_panel.add(join_label);
				t_panel.add(join_tf);
				label_panel.add(l_panel);
				tf_panel.add(t_panel);
			}
		}
		
		join_btn.addActionListener(this);
		id_check_btn.addActionListener(this);
		cancel_btn.addActionListener(this);
		
		west_panel.add(label_panel);
		center_panel.add(tf_panel);
		south_panel.add(btn_panel);
		
		f.add(BorderLayout.WEST, west_panel);
		f.add(BorderLayout.CENTER, center_panel);
		f.add(BorderLayout.SOUTH, south_panel);
		
		//회원가입창 종료
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				f.dispose();
			}
		});
		
		f.setResizable(false);	//창크기 변경 막아놓음
		f.setSize(400, 300);
		f.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == id_check_btn) {
			id_check();
		}else if(obj == join_btn) {
			//회원가입 입력정보 저장
			//가입성공시 '회원가입을 완료했습니다' 메세지 생성
			
		}else if(obj == cancel_btn) {
			//회원가입창 종료
			f.dispose();
		}
	}
	
	//ID 중복 체크
	public void id_check() {
		//데이터베이스 연결해서 비교 or  UNIQUE 사용?
		//아이디 체크 후 '사용가능한 아이디 입니다' 또는 '중복된 아이디 입니다' 메세지 생성
		//텍스트필드에 포커스 맞출까..?
		
		
	}
		
	public static void main(String[] args) {
		new JoinUI();
	}
	
}