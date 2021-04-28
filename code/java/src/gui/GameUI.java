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
import vo.MemberVO;
import vo.MessageVO;
import vo.RoomVO;

public class GameUI {

	// Field
	public JFrame frame;
	public JTextField chat_tf;
	public JTextArea chat_ta;
	public ArrayList<JButton> mice_btn;
	public JButton watch_profile_btn, watch_profile_btn2, ready_btn, exit_btn, send_btn;
	GameUIEvent event;
	ImageIcon icon, icon2;
	JLabel master_id_label, user_id_label;
	public GameVO gvo;
	GameSystemClient gsc;
	GameSystemServer gss;
	ClientSystem client;
	public RoomVO room; // 방 정보

	// Constructor
	public GameUI() {
		client = new ClientSystem();
		client.id = "Test";
		room = new RoomVO();
		room.title = "test";
		room.no = 0;
		initialize();
	}

	public GameUI(ClientSystem client, RoomVO room) {
		this.client = client;
		this.room = room;
		client.gameui = this; // 클아이언트에 ui 등록
		initialize();
	}

	// Method

	private void initialize() {
		gss = new GameSystemServer();
		gvo = gss.gvo;
		gsc = new GameSystemClient(this);
		event = new GameUIEvent(this);

		frame = new JFrame("쥐뢰찾기 - " + room.title);

		JPanel center_panel = new JPanel(); // 쥐뢰게임
		JPanel west_panel = new JPanel(new GridLayout(3, 1, 0, 50)); // 방장 프로필
		JPanel east_panel = new JPanel(new GridLayout(3, 1, 0, 50)); // 참가자 프로필
		JPanel south_panel = new JPanel(new BorderLayout()); // 채팅
		// center_panel.setLayout(new GridLayout(0, 1, 0, 0));
		// west_panel.setSize(300, 500);

		// 방장(왼쪽)
		JPanel master_icon_panel = new JPanel();
		JPanel master_image_panel = new JPanel();
		JPanel master_id_panel = new JPanel();
		JPanel master_btn_panel = new JPanel();
		JPanel master_panel = new JPanel(new GridLayout(2, 1, 0, 20)); // id, btn 패널 넣음

		// 방장표시 아이콘
		ImageIcon master_icon = new ImageIcon("방장 표시용");
		Image master_img = master_icon.getImage();
		Image master_changeImg = master_img.getScaledInstance(10, 10, Image.SCALE_SMOOTH);
		ImageIcon master_changeIcon = new ImageIcon(master_changeImg);
		JLabel icon_label = new JLabel(master_changeIcon);
		master_icon_panel.add(icon_label);

		// 방장 이미지 가져오기 위한 msgVO
//		MessageVO msg = new MessageVO();
//		msg.setStatus(MessageVO.GAME_IMG);
//		msg.setId(room.userList.get(0));
//		MemberVO gameImg = client.gameImg(msg);

		// 방장 이미지
//		icon = new ImageIcon("" + gameImg.getImg());
		icon = new ImageIcon("images/고양이.png");
		Image img = icon.getImage();
		Image changeImg = img.getScaledInstance(160, 160, Image.SCALE_SMOOTH);
		ImageIcon changeIcon = new ImageIcon(changeImg);
		JLabel img_label = new JLabel(changeIcon);
		master_image_panel.add(img_label);

		// 방장 아이디
		master_id_label = new JLabel("" + room.userList.get(0));
//		bangjangidlabel.setHorizontalAlignment(SwingConstants.CENTER);
		master_id_label.setFont(Commons.getFont());
		master_id_panel.add(master_id_label);
		master_panel.add(master_id_panel);

		// 방장 프로필 보기 버튼
		watch_profile_btn = new JButton("프로필 보기");
		watch_profile_btn.setFont(Commons.getFont());
		master_btn_panel.add(watch_profile_btn);
		master_panel.add(master_btn_panel);

		west_panel.add(master_icon_panel);
		west_panel.add(master_image_panel);
		west_panel.add(master_panel);

		// 참가자 (오른쪽)
		JPanel user_icon_panel = new JPanel();
		JPanel user_image_panel = new JPanel();
		JPanel user_id_panel = new JPanel();
		JPanel user_btn_panel = new JPanel();
		JPanel user_panel = new JPanel(new GridLayout(2, 1, 0, 20)); // id, btn 패널 넣음

		// 참가자표시 아이콘 (대칭 맞추기 위해 빈칸 삽입)
		JLabel user_icon = new JLabel("");
		user_icon_panel.add(user_icon);

		// 참가자 이미지 가져오기 위한 msgVO
//		MessageVO msg2 = new MessageVO();
//		msg2.setStatus(MessageVO.GAME_IMG);
//		msg2.setId(room.userList.get(1));
//		MemberVO gameImg2 = client.gameImg(msg2);

		// 참가자 이미지
//		icon2 = new ImageIcon("" + gameImg.getImg());
		icon2 = new ImageIcon("images/쥐.png");
		Image img2 = icon2.getImage();
		Image changeImg2 = img2.getScaledInstance(160, 160, Image.SCALE_SMOOTH);
		ImageIcon changeIcon2 = new ImageIcon(changeImg2);
		JLabel img_label2 = new JLabel(changeIcon2);
		user_image_panel.add(img_label2);

		// 참가자 아이디
//		user_id_label = new JLabel("" + room.userList.get(0));
		user_id_label = new JLabel("aaaa");
//		bangjangidlabel.setHorizontalAlignment(SwingConstants.CENTER);
		user_id_label.setFont(Commons.getFont());
		user_id_panel.add(user_id_label);
		user_panel.add(user_id_panel);

		// 참가자 프로필 보기 버튼
		watch_profile_btn2 = new JButton("프로필 보기");
		watch_profile_btn2.setFont(Commons.getFont());
		user_btn_panel.add(watch_profile_btn2);
		user_panel.add(user_btn_panel);

		east_panel.add(user_icon_panel);
		east_panel.add(user_image_panel);
		east_panel.add(user_panel);

		// 지뢰 버튼 생성
		JPanel game_panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JPanel game_in_panel = new JPanel(new GridLayout(9, 9, 0, 0));

		for (int a = 0; a < 9; a++) {
			for (int b = 0; b < 9; b++) {
				mice_btn = gvo.getMicebtn();
				JButton btn = new JButton(a + "_" + b);
				btn.setFont(Commons.getFont());
				btn.setPreferredSize(new Dimension(52, 52));
				btn.setName(Integer.toString(a) + Integer.toString(b));
				game_in_panel.add(btn);
				btn.addActionListener(event);
				mice_btn.add(btn);
				gvo.setMicebtn(mice_btn);
			}
		}
		game_panel.add(game_in_panel);
		center_panel.add(game_panel);

		// 채팅
		JPanel chat_panel = new JPanel(new BorderLayout());
		JPanel chat_send_panel = new JPanel(new BorderLayout());
		JPanel menu_panel = new JPanel(new GridLayout(2, 1, 0, 20));
		
		// 준비, 나가기 버튼
		ready_btn = new JButton("준비");
		exit_btn = new JButton("나가기");
		ready_btn.setFont(Commons.getFont());
		exit_btn.setFont(Commons.getFont());
		menu_panel.add(ready_btn);
		menu_panel.add(exit_btn);

		// 채팅창
		chat_ta = new JTextArea(10, 50);
		chat_ta.setEditable(false);

		// 채팅 입력창 왼쪽 유저 ID 표시
//		JLabel label_chatID = new JLabel(client.getId());
//		label_chatID.setFont(Commons.getFont());
//		chat_send_panel.add(label_chatID, BorderLayout.WEST);
		
		// 채팅 입력창
		chat_tf = new JTextField(40);
		chat_tf.requestFocus();
		
		// 보내기 버튼
		send_btn = new JButton("send");
		send_btn.setFont(Commons.getFont());
		
		chat_send_panel.add(chat_tf, BorderLayout.CENTER);
		chat_send_panel.add(send_btn, BorderLayout.EAST);
		chat_panel.add(chat_ta, BorderLayout.CENTER);
		chat_panel.add(chat_send_panel, BorderLayout.SOUTH);

		south_panel.add(menu_panel, BorderLayout.EAST);
		south_panel.add(chat_panel, BorderLayout.CENTER);

		// 버튼 & 텍스트 필드 이벤트
		watch_profile_btn.addActionListener(event);
		watch_profile_btn2.addActionListener(event);
		ready_btn.addActionListener(event);
		exit_btn.addActionListener(event);
		chat_tf.addActionListener(event);
		send_btn.addActionListener(event);
		
		frame.add(west_panel, BorderLayout.WEST);
		frame.add(east_panel, BorderLayout.EAST);
		frame.add(center_panel, BorderLayout.CENTER);
		frame.add(south_panel, BorderLayout.SOUTH);

		frame.setSize(810, 730);
		frame.setResizable(false);	// 창 크기 변경X
		frame.setVisible(true);

		frame.addWindowListener(event);
	}

	public void exit() {
		client.gameui = null; // 클라이언트와 ui 연결 해제
		frame.dispose();
	}

	public static void main(String[] args) {
		new GameUI();
	}
}
