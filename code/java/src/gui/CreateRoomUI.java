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
import javax.swing.JTextField;

public class CreateRoomUI implements ActionListener {
	Frame f = new Frame("방 생성");
	Panel p_center = new Panel();
	Panel p_south = new Panel();
	Panel p_roomName = new Panel();
	Label l_roomName = new Label("방 이름");
	JTextField tf_roomName = new JTextField(15);
	JButton create = new JButton("생성");
	JButton cancel = new JButton("취소");

	public CreateRoomUI() {
		createRoom();
	}

	public void createRoom() {
		l_roomName.setFont(Commons.getFont());
		p_roomName.add(l_roomName);
		p_roomName.add(tf_roomName);
		p_center.add(p_roomName);

		create.setFont(Commons.getFont());
		cancel.setFont(Commons.getFont());
		create.addActionListener(this);
		cancel.addActionListener(this);
		p_south.add(create);
		p_south.add(cancel);

		f.add(BorderLayout.CENTER, p_center);
		f.add(BorderLayout.SOUTH, p_south);

		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				f.dispose();
			}
		});

		f.setResizable(false);
		f.setSize(300, 120);
		f.setVisible(true);

	}

	public static void main(String[] args) {
		new CreateRoomUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == cancel) {
			f.dispose();
		}
	}

}
