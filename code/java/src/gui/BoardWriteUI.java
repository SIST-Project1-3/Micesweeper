package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import system.client.ClientSystem;
import vo.MessageVO;

public class BoardWriteUI implements ActionListener {
	// Field
	JFrame frame;
	JButton btn_write;
	JTextField tf_title;
	JTextArea tf_content;
	ClientSystem client;

	// Constructor
	public BoardWriteUI(ClientSystem client) {
		this.client = client;
		initialize();
	}

	// Method
	private void initialize() {
		frame = new JFrame("커뮤니티");

		JPanel panel_center = new JPanel(new BorderLayout());
		JPanel panel_title = new JPanel(new BorderLayout());
		JLabel label_title = new JLabel("제목");
		label_title.setFont(Commons.getFont());
		tf_title = new JTextField();
		panel_title.add(label_title, "West");
		panel_title.add(tf_title, "Center");

		JPanel panel_content = new JPanel(new BorderLayout());
		JLabel label_content = new JLabel("내용");
		label_content.setFont(Commons.getFont());
		tf_content = new JTextArea();
		panel_content.add(label_content, "West");
		panel_content.add(tf_content, "Center");

		panel_center.add(panel_title, "North");
		panel_center.add(panel_content, "Center");

		JPanel panel_btn = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		btn_write = new JButton("작성");
		btn_write.setFont(Commons.getFont());
		btn_write.addActionListener(this);
		panel_btn.add(btn_write, "East");

		frame.add(panel_center, "Center");
		frame.add(panel_btn, "South");
		frame.setSize(500, 500);
		frame.setVisible(true);

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				exit();
			}
		});
	}

	public void exit() {
		frame.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == btn_write) { // 작성버튼 클릭
			if (tf_title.getText().isEmpty()) { // 제목 입력 확인
				JOptionPane.showMessageDialog(null, Commons.getMsg("제목을 입력해주세요"));
			} else if (tf_content.getText().isEmpty()) { // 내용 입력 확인
				JOptionPane.showMessageDialog(null, Commons.getMsg("내용을 입력해주세요"));
			} else { // 글 작성
				MessageVO msg = new MessageVO();
				msg.setStatus(MessageVO.WRITE);
				msg.setId(client.getId());
				msg.setTitle(tf_title.getText());
				msg.setContent(tf_content.getText());
				if (client.writeBoard(msg)) {
					JOptionPane.showMessageDialog(null, Commons.getMsg("글 작성 성공"));
					frame.dispose();
				} else {
					JOptionPane.showMessageDialog(null, Commons.getMsg("글 작성 실패"));
				}
			}
		}
	}

	public static void main(String[] args) {
		new BoardWriteUI(new ClientSystem());
	}
}
