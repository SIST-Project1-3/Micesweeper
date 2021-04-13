package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StartUI implements ActionListener{
	JFrame f = new JFrame();
	JPanel center_panel, south_panel, login_panel, btn_panel;
	JButton login_btn, join_btn;
	
	public StartUI() {
		init();
	}
	
	public void init() {
		f = new JFrame("���ã��");
		center_panel = new JPanel(new BorderLayout());
		south_panel = new JPanel();
		login_panel = new JPanel();
		btn_panel = new JPanel();
		JPanel label_panel = new JPanel(new GridLayout(2,1));
		JPanel tf_panel = new JPanel(new GridLayout(2,1));
		
		//ID, PW �Է�â ����
		JLabel id_label = new JLabel("ID");
		JLabel pw_label = new JLabel("PW");
		JTextField id_tf = new JTextField(15);
		JTextField pw_tf = new JTextField(15);
		id_label.setFont(Commons.getFont());
		pw_label.setFont(Commons.getFont());
		label_panel.add(id_label);
		label_panel.add(pw_label);
		tf_panel.add(id_tf);
		tf_panel.add(pw_tf);
		login_panel.add(label_panel);
		login_panel.add(tf_panel);
		
		//�ϴ��� �α��� ȸ������ ��ư ����
		login_btn = new JButton("�α���");
		join_btn = new JButton("ȸ������");
		login_btn.setFont(Commons.getFont());
		join_btn.setFont(Commons.getFont());
		btn_panel.add(login_btn);
		btn_panel.add(join_btn);
		
		//�̹��� ���� & ũ������
		//ImageIcon icon = new ImageIcon("images/���ã��(������).png");
		ImageIcon icon = new ImageIcon("images/���ã��(������).png");
		Image img = icon.getImage();
		Image changeImg = img.getScaledInstance(700, 650, Image.SCALE_SMOOTH);
		ImageIcon changeIcon = new ImageIcon(changeImg);
		JLabel img_label = new JLabel(changeIcon);
	    center_panel.add(img_label);
		
		center_panel.add(BorderLayout.SOUTH,login_panel);
		south_panel.add(btn_panel);
		
		//�α���, ȸ������ ��ư �׼��̺�Ʈ
		login_btn.addActionListener(this);
		join_btn.addActionListener(this);
		
		f.add(BorderLayout.CENTER,center_panel);
		f.add(BorderLayout.SOUTH,south_panel);
		
		f.setResizable(false);	//âũ�� ���� ���Ƴ���
		f.setSize(700, 700);
		f.setVisible(true);
		
		//������ ���� �̺�Ʈ	
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
			System.exit(0);
			}
		});
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == login_btn) {
			login_check();
		}else if(obj == join_btn) {
			new JoinUI();
		}
	}
	
	//�α��� ��ȿ�� �˻�
	public void login_check() {
		//�����ͺ��̽� �����ؼ� ��
		//���̵�,�н����� Ʋ���� �� '���̵�/�н����尡 Ʋ�Ƚ��ϴ�.'�޼��� ����
		
		
		
	}
	
	public static void main(String[] args) {
		new StartUI();
	}
	
}

