package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import gamesystem.GameSystemClient;
import gamesystem.GameSystemServer;
import system.client.ClientSystem;
import vo.GameVO;

public class GameUI {

	// Field
	public JFrame frame;
	public JTextField textField;
	public JTextArea textArea;
	public ArrayList<JButton> micebtn;
	public JButton watchprofilebtn, readybutton, exitbutton, send, watchprofilebtn_2;
	GameUIEvent event;
	ImageIcon icon, icon2;
	JLabel bangjangidlabel, idlabel;
	public GameVO gvo;
	GameSystemClient gsc;
	GameSystemServer gss;
	ClientSystem client;

	// Constructor
	public GameUI() {
		initialize();
	}

	public GameUI(ClientSystem client) {
		this.client = client;
		initialize();
	}

	// Method

	private void initialize() {
		gss = new GameSystemServer();
		gvo = gss.gvo;
		gsc = new GameSystemClient(this);
		event = new GameUIEvent(this);

		frame = new JFrame("쥐뢰찾기");

		JPanel westpanel = new JPanel();
		westpanel.setSize(300, 500);
		westpanel.setLayout(new GridLayout(3, 1, 0, 50));

		JPanel bangjangpanel = new JPanel();
		westpanel.add(bangjangpanel);

		JLabel bangjangpyosilabel = new JLabel("방장 표시용");
		bangjangpanel.add(bangjangpyosilabel);

		JPanel bangjangimagepanel = new JPanel();
		icon = new ImageIcon("images/고양이.png");
		Image img = icon.getImage();
		Image changeImg = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		ImageIcon changeIcon = new ImageIcon(changeImg);
		JLabel img_label = new JLabel(changeIcon);
		bangjangimagepanel.add(img_label);
		westpanel.add(bangjangimagepanel);

		JPanel bangjangprofilepanel = new JPanel();
		bangjangprofilepanel.setVisible(true);
		westpanel.add(bangjangprofilepanel);
		bangjangprofilepanel.setLayout(new GridLayout(2, 1, 0, 20));

		bangjangidlabel = new JLabel("test");
		bangjangidlabel.setHorizontalAlignment(SwingConstants.CENTER);
		bangjangidlabel.setFont(Commons.getFont());
		bangjangprofilepanel.add(bangjangidlabel);

		JPanel panel_1 = new JPanel();
		bangjangprofilepanel.add(panel_1);

		watchprofilebtn = new JButton("프로필 보기");
		watchprofilebtn.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		watchprofilebtn.addActionListener(event);
		panel_1.add(watchprofilebtn);
		JPanel centerpanel = new JPanel();
		centerpanel.setLayout(new GridLayout(0, 1, 0, 0));

		// 지뢰 버튼 생성
		JPanel panel = new JPanel();
		centerpanel.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel gamepanel = new JPanel();
		panel.add(gamepanel);
		gamepanel.setLayout(new GridLayout(9, 9, 0, 0));

		for (int a = 0; a < 9; a++) {
			for (int b = 0; b < 9; b++) {
				micebtn = gvo.getMicebtn();
				JButton btn = new JButton(a + "_" + b);
				btn.setFont(Commons.getFont());
				btn.setPreferredSize(new Dimension(52, 52));
				btn.setName(Integer.toString(a) + Integer.toString(b));
				gamepanel.add(btn);
				btn.addActionListener(event);
				micebtn.add(btn);
				gvo.setMicebtn(micebtn);
			}
		}

		JPanel southpanel = new JPanel();
		southpanel.setLayout(new BorderLayout(0, 0));

		JPanel readypanel = new JPanel();
		southpanel.add(readypanel, BorderLayout.EAST);
		readypanel.setLayout(new GridLayout(2, 0, 0, 20));

		readybutton = new JButton("준비");
		readybutton.setFont(Commons.getFont());
		readybutton.addActionListener(event);
		readypanel.add(readybutton);

		exitbutton = new JButton("나가기");
		exitbutton.setFont(Commons.getFont());
		exitbutton.addActionListener(event);
		readypanel.add(exitbutton);

		JPanel chatpanel = new JPanel();
		southpanel.add(chatpanel, BorderLayout.CENTER);
		chatpanel.setLayout(new BorderLayout(0, 0));

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setRows(10);
		chatpanel.add(textArea, BorderLayout.CENTER);

		JPanel chatpanel_send = new JPanel();
		chatpanel.add(chatpanel_send, BorderLayout.SOUTH);
		chatpanel_send.setLayout(new BorderLayout(0, 0));

		// 채팅 입력창 왼쪽 유저 ID 표시
//		JLabel label_chatID = new JLabel(client.getId());
//		label_chatID.setFont(Commons.getFont());
//		chatpanel_send.add(label_chatID, BorderLayout.WEST);

		textField = new JTextField();
		textField.setColumns(1);
		textField.addActionListener(event);
		chatpanel_send.add(textField);

		send = new JButton("send");
		send.addActionListener(event);
		chatpanel_send.add(send, BorderLayout.EAST);

		JPanel eastpanel = new JPanel();
		frame.getContentPane().add(eastpanel, BorderLayout.EAST);
		eastpanel.setLayout(new GridLayout(3, 1, 0, 50));

		JPanel imsipanel = new JPanel();
		eastpanel.add(imsipanel);

		JPanel imagepanel = new JPanel();
		icon2 = new ImageIcon("images/쥐.png");
		Image img2 = icon2.getImage();
		Image changeImg2 = img2.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		ImageIcon changeIcon2 = new ImageIcon(changeImg2);
		JLabel img_label2 = new JLabel(changeIcon2);
		imagepanel.add(img_label2);
		eastpanel.add(imagepanel);

		JPanel profilepanel = new JPanel();
		eastpanel.add(profilepanel);
		profilepanel.setLayout(new GridLayout(2, 1, 0, 20));

		idlabel = new JLabel("aaaa");
		idlabel.setHorizontalAlignment(SwingConstants.CENTER);
		idlabel.setFont(Commons.getFont());
		profilepanel.add(idlabel);

		JPanel panel_1_1 = new JPanel();
		profilepanel.add(panel_1_1);

		watchprofilebtn_2 = new JButton("프로필 보기");
		watchprofilebtn_2.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		watchprofilebtn_2.addActionListener(event);
		panel_1_1.add(watchprofilebtn_2);

		frame.add(westpanel, BorderLayout.WEST);
		frame.add(centerpanel, BorderLayout.CENTER);
		frame.add(southpanel, BorderLayout.SOUTH);

		frame.setSize(810, 730);
		frame.setResizable(false);
		frame.setVisible(true);
	}

	public void exit() {
		frame.dispose();
	}

	public static void main(String[] args) {
		new GameUI();
	}
}
