package gui;

import java.awt.BorderLayout;
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

public class ProfileUIEvent implements ActionListener{
	ProfileUI pui;
	JFrame f;
	
	public ProfileUIEvent(ProfileUI pui) {
		this.pui = pui;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == pui.file_change_btn) {
			file_form();
//		}else if() {
//			
		}else if(obj == pui.exit_btn) {
			//프로필 창 종료
			pui.f.dispose();
		}
	}
	
	public void file_form() {
		//프로필 이미지 파일 수정 창 생성
		//Toolkit.getDefaultToolkit().getImage("/Users/ohdavi/Downloads/쥐뢰찾기이미지/고양이.png");
		//FileChooser??
		f = new JFrame("프로필 수정");
		JPanel find_panel= new JPanel();
		JPanel south_panel = new JPanel();
		JButton btn_find = new JButton("찾기");
		JButton btn_update = new JButton("수정");
		JButton btn_cancel = new JButton("취소");
		btn_update.setFont(Commons.getFont());
		btn_update.setFont(Commons.getFont());
		btn_cancel.setFont(Commons.getFont());
		find_panel.add(btn_find);
		south_panel.add(btn_update);
		south_panel.add(btn_cancel);
		
		f.add(BorderLayout.CENTER,find_panel);
		f.add(BorderLayout.SOUTH,south_panel);
		
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				f.dispose();
			}
		});
		
		f.setResizable(false);
		f.setSize(300, 100);
		f.setVisible(true);
	}
	
	public void nick_form() {
		//닉네임 수정 창 생성
		f = new JFrame("닉네임 수정");
		JPanel nick_panel= new JPanel();
		JPanel label_panel = new JPanel(new GridLayout(2,1));
		JPanel tf_panel = new JPanel(new GridLayout(2,1));
		JPanel btn_panel = new JPanel();
		JLabel original_label = new JLabel("기존 닉네임");
		JLabel change_label = new JLabel("변경 닉네임");
		JTextField tf_original= new JTextField(15);
		JTextField tf_change= new JTextField(15);
		JButton btn_update = new JButton("수정");
		JButton btn_cancel = new JButton("취소");
		
		original_label.setFont(Commons.getFont());
		change_label.setFont(Commons.getFont());
		label_panel.add(original_label);
		label_panel.add(change_label);
		
		tf_panel.add(tf_original);
		tf_panel.add(tf_change);
		nick_panel.add(label_panel);
		nick_panel.add(tf_panel);
		
		btn_update.setFont(Commons.getFont());
		btn_cancel.setFont(Commons.getFont());
		btn_panel.add(btn_update);
		btn_panel.add(btn_cancel);
		
		f.add(BorderLayout.CENTER,nick_panel);
		f.add(BorderLayout.SOUTH,btn_panel);
		
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				f.dispose();
			}
		});
		
		f.setResizable(false);
		f.setSize(300, 130);
		f.setVisible(true);
	}
	
	public static void main(String[] args) {
		new ProfileUI();

	}

}
