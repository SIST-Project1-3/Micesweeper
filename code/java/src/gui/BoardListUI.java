package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
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
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import dao.BoardDAO;
import system.client.ClientSystem;
import vo.BoardVO;
import vo.MessageVO;

public class BoardListUI implements ActionListener, MouseListener {

	// Field
	private JFrame frame;
	JButton btn_write, btn_search;
	JTextField tf_search;
	JComboBox<String> cb_search;
	ClientSystem client;
	DefaultTableModel model;
	Object[] row = new Object[4];
	JTable table;

	// Constructor
	public BoardListUI(ClientSystem client) {
		this.client = client;
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

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				exit();
			}
		});
	}

	public JPanel createNorthPanel() {
		JPanel panel = new JPanel();
		String[] searchTarget = { "제목", "글쓴이" };
		cb_search = new JComboBox<String>(searchTarget);
		cb_search.setFont(Commons.getFont());
		tf_search = new JTextField(10);
		tf_search.setFont(Commons.getFont());
		btn_search = new JButton("검색");
		btn_search.setFont(Commons.getFont());
		btn_search.addActionListener(this);
		panel.add(cb_search);
		panel.add(tf_search);
		panel.add(btn_search);
		return panel;
	}

	public JPanel createCenterPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		String[] colNames = { "글번호", "제목", "글쓴이", "조회수" };
//		model = new DefaultTableModel(colNames, 10);
		model = new DefaultTableModel(colNames, 10) {
			// 행 수정 여부 메소드
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		// table에 전체 데이터 추가
		createJtableData();
		model.setColumnIdentifiers(colNames);

		table = new JTable(model);
		table.setFillsViewportHeight(true);
//		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setColumnSelectionAllowed(false);
		table.setRowSelectionAllowed(true);
//		table.setAutoCreateRowSorter(false);

//		TableColumnModel tcm = table.getColumnModel();
		table.getColumn("글번호").setPreferredWidth(100);
		table.getColumn("제목").setPreferredWidth(700);
		table.getColumn("글쓴이").setPreferredWidth(200);
		table.getColumn("조회수").setPreferredWidth(100);
		table.setFont(Commons.getFont());
		table.addMouseListener(this);
		JScrollPane sp_table = new JScrollPane(table);

		panel.add(sp_table);
		return panel;
	}

	public JPanel createSouthPanel() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		btn_write = new JButton("글쓰기");
		btn_write.setFont(Commons.getFont());
		btn_write.addActionListener(this);
		panel.add(btn_write);
		return panel;
	}

	public void createJtableData() {
		model.setNumRows(0);
		for (BoardVO post : new BoardDAO().getSelectResult()) {
			System.out.println(1);
			row[0] = post.getNo();
			row[1] = post.getTitle();
			row[2] = post.getWriter();
			row[3] = post.getViewcount();
			model.addRow(row);
		}
		model.fireTableDataChanged();
	}

	public void exit() {
		frame.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == btn_search) { // 검색
		} else if (obj == btn_write) { // 글쓰기
			new BoardWriteUI(client);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int r = table.getSelectedRow();
//		System.out.println(r);
		if (e.getClickCount() == 2) {
			MessageVO msg = new MessageVO();
			BoardVO article = client.readArticle();
			new BoardViewUI(article);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		new BoardListUI(new ClientSystem());
	}
}
