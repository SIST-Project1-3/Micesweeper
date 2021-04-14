package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BoardWriteUI {
	// Field
	JFrame frame;
	JButton btn_write;

	// Constructor
	public BoardWriteUI() {
		initialize();
	}

	// Method
	private void initialize() {
		frame = new JFrame();

		JPanel panel_north = new JPanel();
		JLabel label_north = new JLabel("커뮤니티");
		label_north.setFont(Commons.getFont());
panel_north.add(label_north);
		
		JPanel panel_center = new JPanel(new BorderLayout());
		JPanel panel_title = new JPanel(new BorderLayout());
		JLabel label_title = new JLabel("제목");
		label_title.setFont(Commons.getFont());
		JTextField tf_title = new JTextField();
		panel_title.add(label_title, "West");
		panel_title.add(tf_title, "Center");

		JPanel panel_content = new JPanel(new BorderLayout());
		JLabel label_content = new JLabel("내용");
		label_content.setFont(Commons.getFont());
		JTextArea tf_content = new JTextArea();
		panel_content.add(label_content, "West");
		panel_content.add(tf_content, "Center");
		
		panel_center.add(panel_title, "North");
		panel_center.add(panel_content, "Center");

		JPanel panel_btn = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		btn_write = new JButton("작성");
		btn_write.setFont(Commons.getFont());
		panel_btn.add(btn_write, "East");
		
		frame.add(panel_north, "North");
		frame.add(panel_center, "Center");
		frame.add(panel_btn, "South");
		frame.setSize(500, 500);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new BoardWriteUI();
	}
}
