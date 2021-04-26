package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
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
	String[] colNames = { "글번호", "제목", "글쓴이", "조회수", "수정", "삭제" };
	Object[] row = new Object[colNames.length];
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

		frame.setSize(1000, 500);
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
		JPanel panel_inner = new JPanel(new BorderLayout());
		String[] searchTarget = { "제목", "글쓴이" };
		cb_search = new JComboBox<String>(searchTarget);
		cb_search.setFont(Commons.getFont());
		tf_search = new JTextField(20);
		tf_search.setFont(Commons.getFont());
		tf_search.addActionListener(this);
		btn_search = new JButton("검색");
		btn_search.setFont(Commons.getFont());
		btn_search.addActionListener(this);
		panel_inner.add(cb_search, "West");
		panel_inner.add(tf_search, "Center");
		panel_inner.add(btn_search, "East");
		panel.add(panel_inner);
		return panel;
	}

	public JPanel createCenterPanel() {
		JPanel panel = new JPanel(new BorderLayout());
//		model = new DefaultTableModel(colNames, 10);
		model = new DefaultTableModel(colNames, 10) {
			// 행 수정 여부 메소드
			@Override
			public boolean isCellEditable(int row, int column) {
				if (column == 4 || column == 5) {
					return true;
				}
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

		table.getColumn("수정").setCellRenderer(new TableUpdateCell("수정", this, client.bdao, 1));
		table.getColumn("수정").setCellEditor(new TableUpdateCell("수정", this, client.bdao, 1));
		table.getColumn("삭제").setCellRenderer(new TableUpdateCell("삭제", this, client.bdao, 1));
		table.getColumn("삭제").setCellEditor(new TableUpdateCell("삭제", this, client.bdao, 1));

//		TableColumnModel tcm = table.getColumnModel();
		table.getColumn("글번호").setResizable(false);
		table.getColumn("글번호").setPreferredWidth(100);
		table.getColumn("제목").setResizable(false);
		;
		table.getColumn("제목").setPreferredWidth(700);
		table.getColumn("글쓴이").setResizable(false);
		;
		table.getColumn("글쓴이").setPreferredWidth(200);
		table.getColumn("조회수").setResizable(false);
		;
		table.getColumn("조회수").setPreferredWidth(100);
		table.getColumn("수정").setResizable(false);
		;
		table.getColumn("수정").setPreferredWidth(100);
		table.getColumn("삭제").setResizable(false);
		;
		table.getColumn("삭제").setPreferredWidth(100);
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

	// 테이블에 데이터 입력 및 테이블 새로고침 기능
	public void createJtableData() {
		model.setNumRows(0);
		for (BoardVO post : client.readBoard()) {
			row[0] = post.getNo();
			row[1] = post.getTitle();
			row[2] = post.getWriter();
			row[3] = post.getViewcount();
			model.addRow(row);
		}
		model.fireTableDataChanged();
	}

	// 검색 결과 테이블에 출력
	public void createJtableData(ArrayList<BoardVO> list) {
		model.setNumRows(0);
		for (BoardVO post : list) {
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

		String name = e.getActionCommand(); // 수정, 삭제 버튼 분류에 사용
		int r = table.getSelectedRow(); // 선택된 행이 몇번째 행인지
		String userId = client.getId();
		String writer;

		if (obj == btn_search || obj == tf_search) { // 검색
			String category = cb_search.getSelectedItem().toString();
			if (category.equals("제목")) {
				createJtableData(client.searchBoard(MessageVO.BOARD_SEARCH_TITLE, tf_search.getText()));
			} else if (category.contentEquals("글쓴이")) {
				createJtableData(client.searchBoard(MessageVO.BOARD_SEARCH_WRITER, tf_search.getText()));
			}
			
		} else if (obj == btn_write) { // 글쓰기
			new BoardWriteUI(this);
			
		} else if (name.equals("수정")) {
			writer = (String) table.getValueAt(r, 2); // 해당 글 작성자
			System.out.printf("접속자: %s, %d번 게시글의 작성자: %s\n", client.getId(), table.getValueAt(r, 0), writer);
			if (userId.equals(writer)) {
				System.out.println(table.getValueAt(r, 0) + "번 게시물 수정");
				BoardVO article = client.readArticle((int) table.getValueAt(r, 0));
				new BoardWriteUI(this, article);
			} else {
				JOptionPane.showMessageDialog(null, Commons.getMsg("작성자만 수정이 가능합니다."));
			}
			
		} else if (name.equals("삭제")) {
			writer = (String) table.getValueAt(r, 2); // 해당 글 작성자
			if (userId.equals(writer)) {
				int result = JOptionPane.showConfirmDialog(null, Commons.getMsg("정말로 삭제하시겠습니까?"));
				if(result == 0) { // 확인버튼 클릭
					if(client.deleteBoard((int)table.getValueAt(r, 0))) {					
						createJtableData();
						JOptionPane.showMessageDialog(null, Commons.getMsg("삭제 완료"));
					}else {
						JOptionPane.showMessageDialog(null, Commons.getMsg("삭제 실패"));
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, Commons.getMsg("작성자만 삭제가 가능합니다."));
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int r = table.getSelectedRow();
//		System.out.println(r);
		if (e.getClickCount() == 2) {
			System.out.println("Selected Index: " + r + ", article no: " + table.getValueAt(r, 0));
			BoardVO article = client.readArticle((int) table.getValueAt(r, 0));
			new BoardViewUI(article);
			createJtableData();
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

	// JTable 수정, 삭제 버튼 생성 및 추가 클래스
	class TableUpdateCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {
		JButton jb;
		BoardDAO dao;

		public TableUpdateCell(String name, BoardListUI blist, BoardDAO dao, int option) {
			this.dao = dao;
			jb = new JButton(name);
			jb.setFont(Commons.getFont());

			jb.addActionListener(blist);
		}

		@Override
		public Object getCellEditorValue() {
			return null;
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			return jb;
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			return jb;
		}
	}// TableUpdateCell class
}
