package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JoinUI implements ActionListener{
	JFrame f;
	JPanel west_panel, center_panel, south_panel,
		  label_panel, tf_panel, btn_panel, id_check_panel;
	String[] joinlist = {"ID","PW","PWȮ��","�г���"};
	JButton join_btn, id_check_btn, cancel_btn;
	
	public JoinUI() {
		init();
	}

	public void init() {
		f = new JFrame("ȸ������");
		west_panel = new JPanel();
		center_panel = new JPanel();
		south_panel = new JPanel();
		label_panel = new JPanel(new GridLayout(4, 1));
		tf_panel = new JPanel(new GridLayout(4, 1));
		btn_panel = new JPanel(new FlowLayout());
		id_check_panel = new JPanel(new FlowLayout());
		
		//�ϴ��� ����, ��� ��ư ����
		join_btn = new JButton("����");
		cancel_btn = new JButton("���");
		join_btn.setFont(Commons.getFont());
		cancel_btn.setFont(Commons.getFont());
		btn_panel.add(join_btn);
		btn_panel.add(cancel_btn);
		
		//ȸ������ ���� �Է�â ������ ���� for��
		//�ؽ�Ʈ�ʵ忡 ��Ŀ�� �����..?
		for(String join : joinlist) {
			JLabel join_label = new JLabel(join);
			join_label.setFont(Commons.getFont());
			JPanel l_panel = new JPanel(new FlowLayout(FlowLayout.LEFT,0,7));
			JPanel t_panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	
			//�ߺ�Ȯ�� ��ư�� ID�ؽ�Ʈ�ʵ� ���� �����ϱ� ���� if��
			if(join.equals("ID")) {
				l_panel.add(join_label);
				label_panel.add(l_panel);
				
				JTextField join_tf = new JTextField(15);
				id_check_btn = new JButton("�ߺ�Ȯ��");
				id_check_btn.setFont(Commons.getFont());
				id_check_panel.add(join_tf);
				id_check_panel.add(id_check_btn);
				tf_panel.add(id_check_panel);
				
			}else {
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
		
		west_panel.add(label_panel);
		center_panel.add(tf_panel);
		south_panel.add(btn_panel);
		
		f.add(BorderLayout.WEST, west_panel);
		f.add(BorderLayout.CENTER, center_panel);
		f.add(BorderLayout.SOUTH, south_panel);
		
		//ȸ������â ����
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				f.dispose();
			}
		});
		
		f.setResizable(false);	//âũ�� ���� ���Ƴ���
		f.setSize(400, 300);
		f.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == id_check_btn) {
			id_check();
		}else if(obj == join_btn) {
			//ȸ������ �Է����� ����
			//���Լ����� 'ȸ�������� �Ϸ��߽��ϴ�' �޼��� ����
			
		}else if(obj == cancel_btn) {
			//ȸ������â ����
			f.dispose();
		}
	}
	
	//ID �ߺ� üũ
	public void id_check() {
		//�����ͺ��̽� �����ؼ� �� or  UNIQUE ���?
		//���̵� üũ �� '��밡���� ���̵� �Դϴ�' �Ǵ� '�ߺ��� ���̵� �Դϴ�' �޼��� ����
		//�ؽ�Ʈ�ʵ忡 ��Ŀ�� �����..?
		
		
	}
		
	public static void main(String[] args) {
		new JoinUI();
	}
	
}