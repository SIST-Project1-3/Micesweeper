package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
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

import dao.GameDAO;
import gamesystem.GameSystemClient;
import system.client.ClientSystem;
import vo.GameVO;
import vo.RoomVO;

public class GameUI {

	// Field
	public JFrame frame;
	JPanel center_panel, west_panel, east_panel, south_panel;
	public JTextField chat_tf;
	public JTextArea chat_ta;
	public ArrayList<JButton> mice_btn;
	public JButton watch_profile_btn, watch_profile_btn2, ready_btn, exit_btn, send_btn;
	public GameUIEvent event;
	ImageIcon icon, icon2;
	JLabel master_id_label;
	public JLabel user_id_label;
	public GameVO gvo;
	public GameSystemClient gsc;
	public ClientSystem client;
	public RoomVO room; // 방 정보
	String[] iconList = new String[2]; // 프사 리스트
	GameDAO gdao = new GameDAO();
	JLabel img_label2;
	JPanel user_image_panel;

	// Constructor
	public GameUI() {
		client = new ClientSystem();
		client.id = "Test";
		room = new RoomVO();
		room.title = "test";
		room.no = 0;
		initialize();
	}

	// 클라이언트 시스템, 서버에서 받아온 방 정보, 선공/후공
	public GameUI(ClientSystem client, RoomVO room, boolean turnflag) {
		this.client = client;
		this.room = room;
		client.gameui = this; // 클아이언트에 ui 등록
		gsc = new GameSystemClient(this, turnflag, room);
		gvo = gsc.gvo;
		initialize();
		System.out.println("유저수: " + room.userList.size());
	}

	// Method
	private void initialize() {
		event = new GameUIEvent(this);

		frame = new JFrame("쥐뢰찾기 - " + room.title);

		center_panel = new JPanel(); // 쥐뢰게임
		west_panel = new JPanel(new GridLayout(3, 1, 0, 10)); // 방장 프로필
		east_panel = new JPanel(new GridLayout(3, 1, 0, 10)); // 참가자 프로필
		south_panel = new JPanel(new BorderLayout()); // 채팅

		for (int i = 0; i < room.userList.size(); i++) {
			String id = room.userList.get(i);
			GameDAO gdao = new GameDAO();
			iconList[i] = gdao.getGameImgResult(id);
		}

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

		// 방장 이미지
		if (!iconList[0].equals("images/쥐.png")) {
			icon = new ImageIcon(iconList[0]);
		} else {
			icon = new ImageIcon("images/쥐.png");
		}
		Image img = icon.getImage();
		Image changeImg = img.getScaledInstance(160, 160, Image.SCALE_SMOOTH);
		ImageIcon changeIcon = new ImageIcon(changeImg);
		JLabel img_label = new JLabel(changeIcon);
		master_image_panel.add(img_label);

		// 방장 아이디
		if (!room.userList.get(0).equals("대기중")) {
			master_id_label = new JLabel(room.userList.get(0));
		} else {
			master_id_label = new JLabel("대기중");
		}
		master_id_label.setFont(Commons.getFont());
		master_id_panel.add(master_id_label);
		master_panel.add(master_id_panel);

		// 방장 프로필 보기 버튼
		watch_profile_btn = new JButton("프로필 보기");
		watch_profile_btn.setFont(Commons.getFont());
		master_btn_panel.add(watch_profile_btn);
		master_panel.add(master_btn_panel);
		if (master_id_label.getText().equals("대기중")) {
			watch_profile_btn.setEnabled(false);
		} else {
			watch_profile_btn.setEnabled(true);
		}
		watch_profile_btn.addActionListener(event);

		west_panel.add(master_icon_panel);
		west_panel.add(master_image_panel);
		west_panel.add(master_panel);

		// 참가자 (오른쪽)
		JPanel user_icon_panel = new JPanel();
		user_image_panel = new JPanel();
		JPanel user_id_panel = new JPanel();
		JPanel user_btn_panel = new JPanel();
		JPanel user_panel = new JPanel(new GridLayout(2, 1, 0, 20)); // id, btn 패널 넣음

		// 참가자표시 아이콘 (대칭 맞추기 위해 빈칸 삽입)
		JLabel user_icon = new JLabel("");
		user_icon_panel.add(user_icon);

		// 참가자 이미지
		if (iconList[1] != null) {
			icon2 = new ImageIcon(iconList[1]);
			System.out.println(111 + iconList[1]);
		} else {
			icon2 = new ImageIcon("images/쥐.png");
		}
		Image img2 = icon2.getImage();
		Image changeImg2 = img2.getScaledInstance(160, 160, Image.SCALE_SMOOTH);
		ImageIcon changeIcon2 = new ImageIcon(changeImg2);
		img_label2 = new JLabel(changeIcon2);
		user_image_panel.add(img_label2);

		// 참가자 아이디
		System.out.println(room.userList.size());
		if (room.userList.size() == 2) {
			user_id_label = new JLabel(room.userList.get(1));
			System.out.println(222222 + room.userList.get(1));
		} else {
			user_id_label = new JLabel("대기중");
		}
		user_id_label.setFont(Commons.getFont());
		user_id_panel.add(user_id_label);
		user_panel.add(user_id_panel);

		// 참가자 프로필 보기 버튼
		watch_profile_btn2 = new JButton("프로필 보기");
		watch_profile_btn2.setFont(Commons.getFont());
		user_btn_panel.add(watch_profile_btn2);
		user_panel.add(user_btn_panel);
		if (user_id_label.getText().equals("대기중"))
			watch_profile_btn2.setEnabled(false);
		watch_profile_btn2.addActionListener(event);

		east_panel.add(user_icon_panel);
		east_panel.add(user_image_panel);
		east_panel.add(user_panel);

		// 지뢰 버튼 생성
		JPanel game_panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JPanel game_in_panel = new JPanel(new GridLayout(9, 9, 0, 0));

		for (int a = 0; a < 9; a++) {
			for (int b = 0; b < 9; b++) {
				mice_btn = gvo.getMicebtn();
				JButton btn = new JButton();
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
		JLabel label_chatID = new JLabel(client.id);
		label_chatID.setFont(Commons.getFont());
		chat_send_panel.add(label_chatID, BorderLayout.WEST);

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
		ready_btn.addActionListener(event);
		exit_btn.addActionListener(event);
		chat_tf.addActionListener(event);
		send_btn.addActionListener(event);

		frame.add(west_panel, BorderLayout.WEST);
		frame.add(east_panel, BorderLayout.EAST);
		frame.add(center_panel, BorderLayout.CENTER);
		frame.add(south_panel, BorderLayout.SOUTH);

		frame.setSize(900, 730);
		frame.setResizable(false); // 창 크기 변경X
		frame.setVisible(true);

		frame.addWindowListener(event);
	}

	// 참가자 패널 갱신 메소드
	public void fillUserPanel() {
		// 참가자가 있으면 참가자 패널 갱신
		if (room.userList.size() >= 2) {
			String id1 = room.userList.get(1);
			user_id_label.setText(id1);
			watch_profile_btn2.setEnabled(true);
			icon2 = new ImageIcon(gdao.getGameImgResult(id1));
			Image img2 = icon2.getImage();
			Image changeImg2 = img2.getScaledInstance(160, 160, Image.SCALE_SMOOTH);
			ImageIcon changeIcon2 = new ImageIcon(changeImg2);
			img_label2 = new JLabel(changeIcon2);
			user_image_panel.remove(0);
			user_image_panel.add(img_label2);
			user_image_panel.setVisible(false); // 패널 새로고침
			user_image_panel.setVisible(true);
			frame.repaint(); // 프레임 새로고침
		}
	}

	public void exit() {
		client.gameui = null; // 클라이언트와 ui 연결 해제
		frame.dispose();
	}

}
