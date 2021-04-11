package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class ProfileUI implements ActionListener {
	Frame f;
	Panel center_panel, content_panel, south_in_panel, south_out_panel, in_panel, file_panel, nick_panel, score_panel,
			ns_panel, exit_panel;
	JButton file_change_btn, nick_update_btn, exit_btn;

	public ProfileUI() {
		myProfile();
		// otherProfile();
	}

	// 나의 프로필 창, 메인화면의 내기록 버튼, 게임화면의 (나의)프로필보기 버튼과 연결해야함!
	public void myProfile() {
		f = new Frame("내 프로필");
		center_panel = new Panel(new BorderLayout());
		content_panel = new Panel(new BorderLayout());
		south_in_panel = new Panel(new GridLayout(2, 1));
		south_out_panel = new Panel();
		in_panel = new Panel(new BorderLayout());
		file_panel = new Panel();
		nick_panel = new Panel(new FlowLayout());
		score_panel = new Panel();
		exit_panel = new Panel();

		file_change_btn = new JButton("프로필 바꾸기");
		nick_update_btn = new JButton("수정");
		exit_btn = new JButton("나가기");
		file_change_btn.setFont(Commons.getFont());
		nick_update_btn.setFont(Commons.getFont());
		exit_btn.setFont(Commons.getFont());

		JTextArea nick_ta = new JTextArea(1, 13);
		nick_panel.add(nick_ta);
		JTextArea score_ta = new JTextArea(1, 20);
		score_panel.add(score_ta);

		file_panel.add(file_change_btn);
		nick_panel.add(nick_update_btn);
		exit_panel.add(exit_btn);

		file_change_btn.addActionListener(this);
		nick_update_btn.addActionListener(this);
		exit_btn.addActionListener(this);

		// 프로필 사진 & 크기조절
		// 사용자가 선택한 이미지가 삽입될 수 있게 만들어야 함
		ImageIcon icon = new ImageIcon("images/고양이.png");
		Image img = icon.getImage();
		Image changeImg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		ImageIcon changeIcon = new ImageIcon(changeImg);
		JLabel img_label = new JLabel(changeIcon);

		content_panel.add(img_label);
		content_panel.add(BorderLayout.SOUTH, file_panel);
		south_in_panel.add(nick_panel);
		south_in_panel.add(score_panel);
		in_panel.add(BorderLayout.CENTER, content_panel);
		in_panel.add(BorderLayout.SOUTH, south_in_panel);

		center_panel.add(BorderLayout.CENTER, in_panel);
		south_out_panel.add(BorderLayout.SOUTH, exit_panel);

		f.add(BorderLayout.CENTER, center_panel);
		f.add(BorderLayout.SOUTH, south_out_panel);

		f.setResizable(false);	// 창크기 변경 막아놓음
		f.setSize(500, 500);
		f.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == file_change_btn) {
			file_form();
		} else if (obj == nick_update_btn) {
			nick_form();
		} else if (obj == exit_btn) {
			// 프로필 창 종료
			f.dispose();
		}
	}

	public void file_form() {
		// 프로필 이미지 파일 수정 창 생성
		// Toolkit.getDefaultToolkit().getImage("/Users/ohdavi/Downloads/쥐뢰찾기
		// 이미지/고양이.png");
		// FileChooser
	}

	public void nick_form() {
		// 닉네임 수정 창 생성

	}

	// 상대 프로필 창, 게임화면의 상대 프로필보기 버튼과 연결해야함!
	public void otherProfile() {
		f = new Frame("상대 프로필");
		center_panel = new Panel(new BorderLayout());
		content_panel = new Panel(new BorderLayout());
		south_in_panel = new Panel(new FlowLayout(FlowLayout.CENTER, 0, 24));
		south_out_panel = new Panel();
		in_panel = new Panel(new BorderLayout());
		nick_panel = new Panel();
		score_panel = new Panel();
		ns_panel = new Panel(new GridLayout(2, 1));
		exit_panel = new Panel();

		JTextArea nick_ta = new JTextArea(1, 17);
		nick_panel.add(nick_ta);
		ns_panel.add(nick_panel);

		JTextArea score_ta = new JTextArea(1, 17);
		score_panel.add(score_ta);
		ns_panel.add(score_panel);

		exit_btn = new JButton("나가기");
		exit_btn.setFont(Commons.getFont());
		exit_panel.add(exit_btn);
		exit_btn.addActionListener(this);

		// 프로필 사진 & 크기조절
		// 상대방 프로필 사진 위치를 가져와야 하나..?
		ImageIcon icon = new ImageIcon("images/쥐.png");
		Image img = icon.getImage();
		Image changeImg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		ImageIcon changeIcon = new ImageIcon(changeImg);
		JLabel img_label = new JLabel(changeIcon);

		content_panel.add(img_label);
		content_panel.add(BorderLayout.SOUTH, ns_panel);
		in_panel.add(BorderLayout.CENTER, content_panel);
		in_panel.add(BorderLayout.SOUTH, south_in_panel);

		center_panel.add(BorderLayout.CENTER, in_panel);
		south_out_panel.add(BorderLayout.SOUTH, exit_panel);

		f.add(BorderLayout.CENTER, center_panel);
		f.add(BorderLayout.SOUTH, south_out_panel);

		f.setResizable(false);	// 창크기 변경 막아놓음
		f.setSize(500, 500);
		f.setVisible(true);
	}

	public static void main(String[] args) {
		new ProfileUI();
	}
}
