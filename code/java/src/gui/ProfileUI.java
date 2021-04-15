package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ProfileUI {
	JFrame f;
	JPanel center_panel, content_panel, south_in_panel, south_out_panel,
		  in_panel, file_panel, name_panel, data_panel, nd_panel, exit_panel;
	JButton file_change_btn, exit_btn;
	ProfileUIEvent profileEvent = new ProfileUIEvent(this);
	
	public ProfileUI() {
		main_myProfile();
		//game_Profile();
		
	}
	
	//메인화면의 내 프로필 (프로필 수정 가능)
	public void main_myProfile() {
		f = new JFrame("내 프로필");
		center_panel = new JPanel(new BorderLayout());
		content_panel = new JPanel(new BorderLayout());
		south_in_panel = new JPanel(new FlowLayout(FlowLayout.CENTER,0,24));
		south_out_panel = new JPanel();
		in_panel = new JPanel(new BorderLayout());
		name_panel = new JPanel(new GridLayout(2,1));
		data_panel = new JPanel(new GridLayout(2,1));
		nd_panel = new JPanel();
		file_panel = new JPanel();
		exit_panel = new JPanel();
		
		JLabel id_lable = new JLabel("ID");
		JLabel score_lable = new JLabel("전적");
		JLabel idData_lable = new JLabel("아이디 경로");
		JLabel scoreData_lable = new JLabel("전 승 패");
		id_lable.setFont(Commons.getFont());
		score_lable.setFont(Commons.getFont());
		name_panel.add(id_lable);
		name_panel.add(score_lable);
		data_panel.add(idData_lable);
		data_panel.add(scoreData_lable);
		nd_panel.add(name_panel);
		nd_panel.add(data_panel);
		
		file_change_btn = new JButton("프로필 바꾸기");
		exit_btn = new JButton("나가기"); 
		file_change_btn.setFont(Commons.getFont());
		exit_btn.setFont(Commons.getFont());
		file_panel.add(file_change_btn);
		exit_panel.add(exit_btn);
		
		file_change_btn.addActionListener(profileEvent);
		exit_btn.addActionListener(profileEvent);
		
		//프로필 이미지 & 크기조절
		//사용자가 프로필 수정 창에서 선택한 이미지가 삽입될 수 있게 만들어야 함
		ImageIcon icon = new ImageIcon("images/고양이.png"); 
		Image img = icon.getImage();
		Image changeImg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		ImageIcon changeIcon = new ImageIcon(changeImg);
		JLabel img_label = new JLabel(changeIcon);
		
		content_panel.add(img_label);
		content_panel.add(BorderLayout.SOUTH,file_panel);
		south_in_panel.add(nd_panel);
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

	public void game_Profile() {
		//게임화면의 프로필 보기 (프로필 수정 불가능)
		f = new JFrame("내 프로필");
//		f = new JFrame("상대 프로필");
		center_panel = new JPanel(new BorderLayout());
		content_panel = new JPanel(new BorderLayout());
		south_in_panel = new JPanel(new FlowLayout(FlowLayout.CENTER,0,24));
		south_out_panel = new JPanel();
		in_panel = new JPanel(new BorderLayout());
		name_panel = new JPanel(new GridLayout(2,1));
		data_panel = new JPanel(new GridLayout(2,1));
		nd_panel = new JPanel();
		exit_panel = new JPanel();
		
		JLabel id_lable = new JLabel("ID");
		JLabel score_lable = new JLabel("전적");
		JLabel idData_lable = new JLabel("아이디 경로");
		JLabel scoreData_lable = new JLabel("전 승 패");
		id_lable.setFont(Commons.getFont());
		score_lable.setFont(Commons.getFont());
		name_panel.add(id_lable);
		name_panel.add(score_lable);
		data_panel.add(idData_lable);
		data_panel.add(scoreData_lable);
		nd_panel.add(name_panel);
		nd_panel.add(data_panel);
		
		
		exit_btn = new JButton("나가기"); 
		exit_btn.setFont(Commons.getFont());
		exit_panel.add(exit_btn);
		exit_btn.addActionListener(profileEvent);
		
		//프로필 이미지
		//나와 상대방이 각자 설정한 이미지 불러오기
		ImageIcon icon = new ImageIcon("images/쥐.png"); 
		Image img = icon.getImage();
		Image changeImg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		ImageIcon changeIcon = new ImageIcon(changeImg);
		JLabel img_label = new JLabel(changeIcon);
		
		content_panel.add(img_label);
		content_panel.add(BorderLayout.SOUTH,nd_panel);
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
	
	public static void main(String[] args) {
		new ProfileUI();
	}
	
}
