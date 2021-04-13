package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MainUI {

	// Field
	JFrame frame;
	ArrayList<JButton> list_btn = new ArrayList<JButton>();
	DefaultListModel<String> listmodel_room;
	JList<String> jlist_room;
	MainUIEvent event = new MainUIEvent(this);

	String[] rooms = { "ù��° ���Դϴ�.", "�ι�° ���̰ŵ��", "����° ���̴��󱸿�", "�׹�° ���� �� ���ƿ�", "�ټ���°�ΰ�����", "����°", "ĥ", "���̹�", "������",
			"ĭ��", "�ѱ���", "��", "��", "��", "��", "��", "��" };
	String[] users = { "��â��", "���ؼ�", "���ٺ�", "�ڰ���", "��â��", "���ؼ�", "���ٺ�", "�ڰ���", "��â��", "���ؼ�", "���ٺ�", "�ڰ���", "��â��", "���ؼ�",
			"���ٺ�", "�ڰ���", "��â��", "���ؼ�", "���ٺ�", "�ڰ���", };

	// Constructor
	public MainUI() {
		initialize();
	}

	// Method
	private void initialize() {
		frame = new JFrame();
		frame.setLayout(new BorderLayout(10, 10));
		frame.setTitle("���ã��");

		frame.getContentPane().add(createNorthPanel(), "North");
		frame.getContentPane().add(createCenterPanel(), "Center");
		frame.getContentPane().add(createSouthPanel(), "South");

		frame.setSize(500, 500);
		frame.setVisible(true);

		frame.addWindowListener(event);
	}

	public JPanel createNorthPanel() {
		JPanel panel_north = new JPanel(new GridLayout(2, 1));

		JPanel panel_label1 = new JPanel();
		JPanel panel_label2 = new JPanel();

		JLabel label1 = new JLabel("���ã��");
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
		JLabel label_name = new JLabel("�� ����", SwingConstants.LEFT);
		label_name.setFont(Commons.getFont());
		JLabel label_capacity = new JLabel("�ο� ��", SwingConstants.RIGHT);
		label_capacity.setFont(Commons.getFont());
		panel_label.add(label_name);
		panel_label.add(label_capacity);
		listmodel_room = new DefaultListModel<String>();
		// �׽�Ʈ�� �� ��� ����
		for (String str : rooms) {
			String str_room = str + " - 1/2";
			listmodel_room.addElement(str_room);
		}
		//
		jlist_room = new JList<String>(listmodel_room);
		jlist_room.setFont(Commons.getFont());
		jlist_room.addMouseListener(event);
		JScrollPane sp_room = new JScrollPane(jlist_room);
		panel_center.add(sp_room, "Center");
		panel_center.add(panel_label, "North");

		JPanel panel_east = new JPanel(new BorderLayout());
		JLabel label_users = new JLabel("�������� ���");
		label_users.setFont(Commons.getFont());
		DefaultListModel<String> listmodel_user = new DefaultListModel<String>();
		// 1. test�� ���� �߰�
		for (String str : users) {
			listmodel_user.addElement(str);
		}
		JList<String> jlist_user = new JList<String>(listmodel_user);
		jlist_user.setFont(Commons.getFont());
		JScrollPane sp_user = new JScrollPane(jlist_user);
		panel_east.add(label_users, "North");
		panel_east.add(sp_user, "Center");

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
		String[] str_btn = { "�� ����", "�� ���", "Ŀ�´�Ƽ", "��������" };
		for (String str : str_btn) {
			JButton btn = new JButton(str);
			btn.setFont(Commons.getFont());
			btn.addActionListener(event);
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

	public static void main(String[] args) {
		new MainUI();
	}

}
