package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class BoardListUI implements WindowListener {

	// Field
	private JFrame frame;
	JButton btn_write, btn_search;
	JTextField tf_search;
	JComboBox<String> cb_search;

	// Constructor
	public BoardListUI() {
		initialize();

	}

	// Method
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("커뮤니티");

		frame.add(createNorthPanel(), "North");
		frame.add(createCenterPanel(), "Center");
		frame.add(createSouthPanel(), "South");

		frame.setSize(500, 500);
		frame.setVisible(true);

		frame.addWindowListener(this);
	}

	public JPanel createNorthPanel() {
		JPanel panel = new JPanel();
		String[] searchTarget = { "제목", "글쓴이" };
		cb_search = new JComboBox<String>(searchTarget);
		tf_search = new JTextField(10);
		btn_search = new JButton("검색");
		panel.add(cb_search);
		panel.add(tf_search);
		panel.add(btn_search);
		return panel;
	}

	public JPanel createCenterPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		String[] colNames = { "글번호", "제목", "글쓴이", "조회수" };
		DefaultTableModel model = new DefaultTableModel(colNames, 0);
		JTable table = new JTable(model);

		table.setRowHeight(25);
		JScrollPane sp_table = new JScrollPane(table);

		panel.add(sp_table);
		return panel;
	}

	public JPanel createSouthPanel() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		btn_write = new JButton("글쓰기");
		panel.add(btn_write);
		return panel;
	}

	public void exit() {
		System.exit(0);
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
		new BoardListUI();
	}

}
