package gui;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import system.client.ClientSystem;
import vo.RoomVO;

public class CreateRoomUI implements ActionListener {
	// Field
	Frame f = new Frame("방 생성");
	Panel panel_center = new Panel();
	Panel panel_south = new Panel();
	Panel panel_roomName = new Panel();
	Label label_roomName = new Label("방 이름");
	JTextField tf_roomName = new JTextField(15);
	JButton btn_create = new JButton("생성");
	JButton btn_cancel = new JButton("취소");
	ClientSystem client;
	MainUI mainui;

	// Constructor
	public CreateRoomUI(ClientSystem client, MainUI mainui) {
		this.client = client;
		this.mainui = mainui;
		createRoom();
	}

	// Method
	public void createRoom() {
		label_roomName.setFont(Commons.getFont());
		panel_roomName.add(label_roomName);
		panel_roomName.add(tf_roomName);
		panel_center.add(panel_roomName);

		btn_create.setFont(Commons.getFont());
		btn_cancel.setFont(Commons.getFont());
		tf_roomName.addActionListener(this);
		btn_create.addActionListener(this);
		btn_cancel.addActionListener(this);
		panel_south.add(btn_create);
		panel_south.add(btn_cancel);

		f.add(BorderLayout.CENTER, panel_center);
		f.add(BorderLayout.SOUTH, panel_south);

		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				exit();
			}
		});

		f.setResizable(false);
		f.setSize(300, 120);
		f.setVisible(true);

	}

	public void exit() {
		f.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == btn_cancel) { // 취소 버튼
			exit();
		} else if (obj == btn_create || obj == tf_roomName) { // 생성 버튼
			if (!tf_roomName.getText().isEmpty()) { // 빈칸이 아니면 생성
				RoomVO room = client.createRoom(tf_roomName.getText()); // 방을 생성하고 생성된 방의 정보를 가져옴
				if (room != null) {
					JOptionPane.showMessageDialog(null, Commons.getMsg("방 생성 성공"));
					mainui.frame.dispose();
					new GameUI(client, room);
					exit();
				} else {
					JOptionPane.showMessageDialog(null, Commons.getMsg("방 생성 실패"));
				}
			} else { // 빈칸이면 에러 출력
				JOptionPane.showMessageDialog(null, Commons.getMsg("방 이름을 입력해주세요"));
			}
		}
	}

}
