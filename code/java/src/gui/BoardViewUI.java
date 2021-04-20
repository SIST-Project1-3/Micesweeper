package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import vo.BoardVO;

public class BoardViewUI {
	// Field
	private JFrame frame;
	BoardVO article;
	JButton btn_write;
	JTextField tf_write;

	// Constructor
	public BoardViewUI(BoardVO article) {
		this.article = article;
		initialize();
	}

	// Method
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("커뮤니티");

		frame.add(createNorthPanel(), BorderLayout.NORTH);
		frame.add(createCenterPanel(), BorderLayout.CENTER);
		frame.add(createSouthPanel(), BorderLayout.SOUTH);

		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				exit();
			}
		});
	}

	public JPanel createNorthPanel() {
		JPanel panel = new JPanel(new GridLayout(2, 2));
		JPanel panel_title = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel panel_date = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel panel_author = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel panel_view = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		JLabel label_title = new JLabel("제목: " + article.getTitle());
		label_title.setFont(Commons.getFont());
		panel_title.add(label_title);

		JLabel label_date = new JLabel("작성일: " + article.getDate());
		label_date.setFont(Commons.getFont());
		panel_date.add(label_date);

		JLabel label_author = new JLabel("글쓴이: " + article.getWriter());
		label_author.setFont(Commons.getFont());
		panel_author.add(label_author);

		JLabel label_view = new JLabel("조회수: " + String.valueOf(article.getViewcount()));
		label_view.setFont(Commons.getFont());
		panel_view.add(label_view);

		panel.add(panel_title);
		panel.add(panel_date);
		panel.add(panel_author);
		panel.add(panel_view);

		return panel;
	}

	public JPanel createCenterPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		JTextArea ta_content = new JTextArea(article.getContent());
		ta_content.setFont(Commons.getFont());
		ta_content.setEditable(false);
		panel.add(ta_content);
		return panel;
	}

	public JPanel createSouthPanel() {
		JPanel panel = new JPanel(new BorderLayout());

		JPanel panel_viewComment = new JPanel(new BorderLayout());
		String[] colNames = { "작성자", "내용" };
		DefaultTableModel model = new DefaultTableModel(colNames, 0);
		JTable table = new JTable(model);
		table.getColumn("작성자").setPreferredWidth(100);
		table.getColumn("내용").setPreferredWidth(600);
		JScrollPane sp_table = new JScrollPane(table);
		sp_table.setPreferredSize(new Dimension(500, 50));
		panel_viewComment.add(sp_table);

		JPanel panel_writeComment = new JPanel(new BorderLayout());
		tf_write = new JTextField();
		btn_write = new JButton("댓글 작성");
		btn_write.setFont(Commons.getFont());
		panel_writeComment.add(tf_write);
		panel_writeComment.add(btn_write, "East");

		panel.add(panel_viewComment, "Center");
		panel.add(panel_writeComment, "South");
		return panel;
	}

	public void exit() {
		frame.dispose();
	}

	public static void main(String[] args) {
		BoardVO article = new BoardVO();
		article.setTitle("제목");
		article.setDate("오늘");
		article.setWriter("이창민");
		article.setViewcount(303);
		article.setContent("내용입니다");
		new BoardViewUI(article);
	}
}
