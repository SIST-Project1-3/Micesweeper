package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JTextField;

public class JoinUI implements ActionListener {
	Frame f;
	Panel north_panel, west_panel, center_panel, south_panel, label_panel, tf_panel, btn_panel, id_check_panel;
	String[] joinlist = { "ID", "PW", "PWȮ��", "�г���" };
	JButton join_btn, id_check_btn, cancel_btn;

	public JoinUI() {
		init();
	}

	public void init() {
		f = new Frame("ȸ������");
		north_panel = new Panel();
		west_panel = new Panel();
		center_panel = new Panel();
		south_panel = new Panel();
		label_panel = new Panel(new GridLayout(4, 1));
		tf_panel = new Panel(new GridLayout(4, 1));
		btn_panel = new Panel(new FlowLayout());
		id_check_panel = new Panel(new FlowLayout());

		// ����� ȸ������ ǥ��
		Label north_label = new Label("ȸ������");
		north_label.setFont(Commons.getFont());

		// �ϴ��� ����, ��� ��ư ����
		join_btn = new JButton("����");
		cancel_btn = new JButton("���");
		join_btn.setFont(Commons.getFont());
		cancel_btn.setFont(Commons.getFont());
		btn_panel.add(join_btn);
		btn_panel.add(cancel_btn);

		// ȸ������ ���� �Է�â ������ ���� for��
		// �ؽ�Ʈ�ʵ忡 ��Ŀ�� �����..?
		for (String join : joinlist) {
			Label join_label = new Label(join);
			join_label.setFont(Commons.getFont());
			Panel l_panel = new Panel(new FlowLayout(FlowLayout.LEFT, 0, 7));
			Panel t_panel = new Panel(new FlowLayout(FlowLayout.LEFT));

			// �ߺ�Ȯ�� ��ư�� ID�ؽ�Ʈ�ʵ� ���� �����ϱ� ���� if��
			if (join.equals("ID")) {
				l_panel.add(join_label);
				label_panel.add(l_panel);

				JTextField join_tf = new JTextField(15);
				id_check_btn = new JButton("�ߺ�Ȯ��");
				id_check_btn.setFont(Commons.getFont());
				id_check_panel.add(join_tf);
				id_check_panel.add(id_check_btn);
				tf_panel.add(id_check_panel);

			} else {
				JTextField join_tf = new JTextField(23);
				l_panel.add(join_label);
				t_panel.add(join_tf);
				label_panel.add(l_panel);
				tf_panel.add(t_panel);
			}
		}

		join_btn.addActionListener(this);
		id_check_btn.addActionListener(this);
		cancel_btn.addActionListener(this);

		north_panel.add(north_label);
		west_panel.add(label_panel);
		center_panel.add(tf_panel);
		south_panel.add(btn_panel);

		f.add(BorderLayout.NORTH, north_panel);
		f.add(BorderLayout.WEST, west_panel);
		f.add(BorderLayout.CENTER, center_panel);
		f.add(BorderLayout.SOUTH, south_panel);

		// ȸ������â ����
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				f.dispose();
			}
		});

		f.setResizable(false);	// âũ�� ���� ���Ƴ���
		f.setSize(400, 300);
		f.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == id_check_btn) {
			id_check();
		} else if (obj == join_btn) {
			// ȸ������ �Է����� ����
			// ���Լ����� 'ȸ�������� �Ϸ��߽��ϴ�' �޼��� ����

		} else if (obj == cancel_btn) {
			// ȸ������â ����
			f.dispose();
		}
	}

	// ID �ߺ� üũ
	public void id_check() {
		// �����ͺ��̽� �����ؼ� ��
		// ���̵� üũ �� '��밡���� ���̵� �Դϴ�' �Ǵ� '�ߺ��� ���̵� �Դϴ�' �޼��� ����
		// �ؽ�Ʈ�ʵ忡 ��Ŀ�� �����..?

	}

	public static void main(String[] args) {
		new JoinUI();
	}

}