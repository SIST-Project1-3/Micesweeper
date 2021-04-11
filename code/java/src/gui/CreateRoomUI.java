package gui;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JTextField;

public class CreateRoomUI {
	Frame f = new Frame("号持失");
	Panel p_north = new Panel();
	Panel p_center = new Panel();
	Panel p_south = new Panel();
	Panel p_roomName = new Panel();
	Label l_roomCreate = new Label("号持失");
	Label l_roomName = new Label("号戚硯");
	JTextField tf_roomName = new JTextField(15);
	JButton create = new JButton("持失");
	JButton cancel = new JButton("昼社");
	
	public CreateRoomUI() {
		createRoom();
	}
	
	public void createRoom() {
		l_roomCreate.setFont(Commons.getFont());
		p_north.add(l_roomCreate);
		
		l_roomName.setFont(Commons.getFont());
		p_roomName.add(l_roomName);
		p_roomName.add(tf_roomName);
		p_center.add(p_roomName);
		
		create.setFont(Commons.getFont());
		cancel.setFont(Commons.getFont());
		p_south.add(create);
		p_south.add(cancel);
		
		f.add(BorderLayout.NORTH,p_north);
		f.add(BorderLayout.CENTER,p_center);
		f.add(BorderLayout.SOUTH,p_south);
		
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				f.dispose();
			}
		});
		
		f.setResizable(false);
		f.setSize(300, 150);
		f.setVisible(true);
		
	}

	public static void main(String[] args) {
		new CreateRoomUI();
	}
	
}
