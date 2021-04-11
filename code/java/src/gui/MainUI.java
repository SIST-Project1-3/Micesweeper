package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MainUI implements ActionListener, WindowListener {

	// Field
	JFrame frame;
	ArrayList<JButton> list_btn = new ArrayList<JButton>();

	// Constructor
	public MainUI() {
		initialize();
	}

	// Method
	private void initialize() {
		frame = new JFrame();
		frame.setLayout(new BorderLayout(10, 10));

		frame.getContentPane().add(createNorthPanel(), "North");
		frame.getContentPane().add(createCenterPanel(), "Center");
		frame.getContentPane().add(createSouthPanel(), "South");

		frame.setSize(500, 500);
		frame.setVisible(true);

		frame.addWindowListener(this);
	}

	public JPanel createNorthPanel() {
		JPanel panel_north = new JPanel(new GridLayout(2, 1));

		JPanel panel_label1 = new JPanel();
		JPanel panel_label2 = new JPanel();

		JLabel label1 = new JLabel("쥐뢰찾기");
		label1.setFont(Commons.getFont());
		JLabel label2 = new JLabel("Game");
		label2.setFont(Commons.getFont());

		panel_label1.add(label1);
		panel_label2.add(label2);

		panel_north.add(panel_label1);
		panel_north.add(panel_label2);
		return panel_north;
	}

	public JPanel createCenterPanel() {
		JPanel panel = new JPanel(new BorderLayout(10, 10));

		JPanel panel_center = new JPanel(new BorderLayout());

		JPanel panel_label = new JPanel(new GridLayout(1, 2));
		JLabel label_name = new JLabel("방 제목", SwingConstants.LEFT);
		label_name.setFont(Commons.getFont());
		JLabel label_capacity = new JLabel("인원 수", SwingConstants.RIGHT);
		label_capacity.setFont(Commons.getFont());
		panel_label.add(label_name);
		panel_label.add(label_capacity);
		DefaultListModel<String> listmodel_room = new DefaultListModel<String>();
		JList<String> jlist_room = new JList<String>(listmodel_room);
		jlist_room.setFont(Commons.getFont());
		panel_center.add(jlist_room, "Center");
		panel_center.add(panel_label, "North");

		JPanel panel_east = new JPanel(new BorderLayout());
		JLabel label_users = new JLabel("접속중인 사람");
		label_users.setFont(Commons.getFont());
		DefaultListModel<String> listmodel_user = new DefaultListModel<String>();
		JList<String> jlist_user = new JList<String>(listmodel_user);
		jlist_user.setFont(Commons.getFont());
		panel_east.add(jlist_user, "Center");
		panel_east.add(label_users, "North");

		panel.add(panel_center, "Center");
		panel.add(panel_east, "East");

		return panel;
	}

	public JPanel createSouthPanel() {
		JPanel panel = new JPanel(new BorderLayout());

		JPanel panel_center = new JPanel(new BorderLayout());
		JTextArea ta = new JTextArea();
		JTextField tf_chat = new JTextField();
		panel_center.add(ta, "Center");
		panel_center.add(tf_chat, "South");

		JPanel panel_btn = new JPanel(new GridLayout(4, 1));
		String[] str_btn = { "방 생성", "내 기록", "커뮤니티", "게임종료" };
		for (String str : str_btn) {
			JButton btn = new JButton(str);
			btn.setFont(Commons.getFont());
			btn.addActionListener(this);
			list_btn.add(btn);
			panel_btn.add(btn);
		}

		panel.add(panel_center, "Center");
		panel.add(panel_btn, "East");

		return panel;

	}

	public void exit() {
		System.exit(0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		exit();
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		new MainUI();
	}

}
