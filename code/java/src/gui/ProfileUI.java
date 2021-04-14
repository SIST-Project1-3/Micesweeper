package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ProfileUI{
	JFrame f;
	JPanel center_panel, content_panel, south_in_panel, south_out_panel,
		  in_panel, file_panel, id_panel, score_panel, ns_panel, exit_panel;
	JButton file_change_btn, nick_update_btn, exit_btn;
	ProfileUIEvent profileEvent = new ProfileUIEvent(this);
	
	public ProfileUI() {
		myProfile();
		//otherProfile();
	}
	
	//나의 프로필 창, 메인화면의 내기록 버튼, 게임화면의 (나의)프로필보기 버튼과 연결해야함!
	public void myProfile() {
		f = new JFrame("내 프로필");
		center_panel = new JPanel(new BorderLayout());
		content_panel = new JPanel(new BorderLayout());
		south_in_panel = new JPanel(new GridLayout(2,1));
		south_out_panel = new JPanel();
		in_panel = new JPanel(new BorderLayout());
		file_panel = new JPanel();
		id_panel = new JPanel(new FlowLayout());
		score_panel = new JPanel();
		exit_panel = new JPanel();
		
		file_change_btn = new JButton("프로필 바꾸기");
		nick_update_btn = new JButton("수정"); 
		exit_btn = new JButton("나가기"); 
		file_change_btn.setFont(Commons.getFont());
		nick_update_btn.setFont(Commons.getFont());
		exit_btn.setFont(Commons.getFont());
		
		JLabel id_lable = new JLabel("ID");
		JLabel score_lable = new JLabel("전적");
		JTextArea nick_ta = new JTextArea(1,17);
		JTextArea score_ta = new JTextArea(1,17);
		id_lable.setFont(Commons.getFont());
		score_lable.setFont(Commons.getFont());
		id_panel.add(id_lable);
		id_panel.add(nick_ta);
		score_panel.add(score_lable);
		score_panel.add(score_ta);
		
		file_panel.add(file_change_btn);
		id_panel.add(nick_update_btn);
		exit_panel.add(exit_btn);
		
		file_change_btn.addActionListener(profileEvent);
		nick_update_btn.addActionListener(profileEvent);
		exit_btn.addActionListener(profileEvent);
		
		//프로필 사진 & 크기조절
		//사용자가 선택한 이미지가 삽입될 수 있게 만들어야 함
		ImageIcon icon = new ImageIcon("images/고양이.png"); 
		Image img = icon.getImage();
		Image changeImg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		ImageIcon changeIcon = new ImageIcon(changeImg);
		JLabel img_label = new JLabel(changeIcon);
		
		content_panel.add(img_label);
		content_panel.add(BorderLayout.SOUTH,file_panel);
		south_in_panel.add(id_panel);
		south_in_panel.add(score_panel);
		in_panel.add(BorderLayout.CENTER,content_panel);
		in_panel.add(BorderLayout.SOUTH,south_in_panel);
		
		center_panel.add(BorderLayout.CENTER,in_panel);
		south_out_panel.add(BorderLayout.SOUTH,exit_panel);
		
		f.add(BorderLayout.CENTER,center_panel);
		f.add(BorderLayout.SOUTH,south_out_panel);
		
		f.setResizable(false);	// 창크기 변경 막아놓음
		f.setSize(500, 500);
		f.setVisible(true);
	}
	
	//상대 프로필 창, 게임화면의 상대 프로필보기 버튼과 연결해야함!
//	public void otherProfile() {
//		f = new JFrame("상대 프로필");
//		center_panel = new JPanel(new BorderLayout());
//		content_panel = new JPanel(new BorderLayout());
//		south_in_panel = new JPanel(new FlowLayout(FlowLayout.CENTER,0,24));
//		south_out_panel = new JPanel();
//		in_panel = new JPanel(new BorderLayout());
//		id_panel = new JPanel();
//		score_panel = new JPanel();
//		ns_panel = new JPanel(new GridLayout(2,1));
//		exit_panel = new JPanel();
//		
//		JLabel nick_lable = new JLabel("닉네임");
//		JLabel score_lable = new JLabel("전적");
//		JTextArea nick_ta = new JTextArea(1,17);
//		JTextArea score_ta = new JTextArea(1,17);
//		nick_lable.setFont(Commons.getFont());
//		score_lable.setFont(Commons.getFont());
//		nick_panel.add(nick_lable);
//		nick_panel.add(nick_ta);
//		ns_panel.add(nick_panel);
//		score_panel.add(score_lable);
//		score_panel.add(score_ta);
//		ns_panel.add(score_panel);
//		
//		exit_btn = new JButton("나가기"); 
//		exit_btn.setFont(Commons.getFont());
//		exit_panel.add(exit_btn);
//		exit_btn.addActionListener(this);
//		
//		//프로필 사진 & 크기조절
//		//상대방 프로필 사진 위치를 가져와야 하나..?
//		ImageIcon icon = new ImageIcon("images/쥐.png"); 
//		Image img = icon.getImage();
//		Image changeImg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
//		ImageIcon changeIcon = new ImageIcon(changeImg);
//		JLabel img_label = new JLabel(changeIcon);
//		
//		content_panel.add(img_label);
//		content_panel.add(BorderLayout.SOUTH,ns_panel);
//		in_panel.add(BorderLayout.CENTER,content_panel);
//		in_panel.add(BorderLayout.SOUTH,south_in_panel);
//		
//		center_panel.add(BorderLayout.CENTER,in_panel);
//		south_out_panel.add(BorderLayout.SOUTH,exit_panel);
//		
//		f.add(BorderLayout.CENTER,center_panel);
//		f.add(BorderLayout.SOUTH,south_out_panel);
//		
//		f.setResizable(false);	// 창크기 변경 막아놓음
//		f.setSize(500, 500);
//		f.setVisible(true);
//	}

	public static void main(String[] args) {
		new ProfileUI();
	}
	
}
