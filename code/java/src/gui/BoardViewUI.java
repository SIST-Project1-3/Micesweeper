package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import system.client.ClientSystem;
import vo.BoardVO;
import vo.CommentVO;

public class BoardViewUI implements ActionListener {
	// Field
	private JFrame frame;
	BoardVO article;
	JButton btn_write;
	JTextField tf_write;
	ClientSystem client;
	DefaultTableModel model_comments;
	JTable table_comments;
	String[] colNames = { "작성자", "내용" };
	Object[] row = new Object[colNames.length]; // 댓글 작성자, 댓글 내용을 담을 배열

	// Constructor
	public BoardViewUI(BoardVO article, ClientSystem client) {
		this.article = article;
		this.client = client;
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

	// 제목, 작성자, 작성일, 조회수를 담는 패널 생성
	public JPanel createNorthPanel() {
		JPanel panel = new JPanel(new GridLayout(2, 2));
		JPanel panel_title = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel panel_date = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel panel_author = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel panel_view = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		JLabel label_title = new JLabel("제목: " + article.getTitle());
		label_title.setFont(Commons.getFont());
		panel_title.add(label_title);

		JLabel label_date = new JLabel("작성일: " + article.getWdate());
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

	// 글 내용이 보이는 창
	public JPanel createCenterPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		JTextArea ta_content = new JTextArea(article.getContent());
		ta_content.setFont(Commons.getFont());
		ta_content.setEditable(false);
		panel.add(ta_content);
		return panel;
	}

	// 하단 패널 생성. 댓글 보기 및 댓글 작성 창
	public JPanel createSouthPanel() {
		JPanel panel = new JPanel(new BorderLayout());

		JPanel panel_viewComment = new JPanel(new BorderLayout());
		model_comments = new DefaultTableModel(colNames, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		createCommentTable(getCommentList(article.getComments())); // 테이블 내용을 채움
		table_comments = new JTable(model_comments);
		table_comments.getColumn("작성자").setPreferredWidth(100);
		table_comments.getColumn("내용").setPreferredWidth(600);
		JScrollPane sp_table = new JScrollPane(table_comments);
		sp_table.setPreferredSize(new Dimension(500, 100));
		panel_viewComment.add(sp_table);

		JPanel panel_writeComment = new JPanel(new BorderLayout());
		tf_write = new JTextField();
		btn_write = new JButton("댓글 작성");
		btn_write.setFont(Commons.getFont());
		tf_write.addActionListener(this);
		btn_write.addActionListener(this);
		panel_writeComment.add(tf_write);
		panel_writeComment.add(btn_write, "East");

		panel.add(panel_viewComment, "Center");
		panel.add(panel_writeComment, "South");
		return panel;
	}

	// VARCHAR2로 된 댓글 데이터를 받아서 리스트로 반환함
	public ArrayList<CommentVO> getCommentList(String comments) {
		System.out.println("댓글 리스트 생성 메소드 실행");
		System.out.println("댓글 String: " + comments);
		ArrayList<CommentVO> list = new ArrayList<CommentVO>();
		String[] commentsArr = comments.split("\\\\n"); // 댓글 하나 하나는 '\n'로 구분
		for (String commentIdContent : commentsArr) {
			System.out.println("분리된 댓글: " + commentIdContent);
			CommentVO comment = new CommentVO();
			String[] commentInfo = commentIdContent.split(":"); // id와 내용은 ';'로 구분
			comment.setId(commentInfo[0]); // id 추가
			comment.setComment(commentInfo[1]); // 작성 내용 추가
			System.out.println("댓글 추가 - 작성자: " + comment.getId() + ", 내용: " + comment.getComment());
			list.add(comment);
		}
		return list;
	}

	// 댓글 테이블 생성 메서드
	public void createCommentTable(ArrayList<CommentVO> commentList) {
		model_comments.setNumRows(0);
		for (CommentVO comment : commentList) {
			row[0] = comment.getId();
			row[1] = comment.getComment();
			model_comments.addRow(row);
		}
		model_comments.fireTableDataChanged();
	}

	public void exit() {
		frame.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == tf_write || obj == btn_write) { // 댓글 입력창에서 엔터를 누르거나, 댓글 작성 버튼을 누른 경우.
			if (client.writeComment(article.getNo(), client.id, tf_write.getText())) { // 댓글 작성 성공
				article = client.readArticle(article.getNo());
				createCommentTable(getCommentList(article.getComments()));
			} else {
				JOptionPane.showMessageDialog(null, Commons.getMsg("댓글 작성 실패"));
			}

		}
	}

}
