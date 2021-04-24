package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import vo.BoardVO;

public class ProfileUIEvent implements ActionListener, MouseListener {
	ProfileUI pui;
	JFrame f;
	JButton btn_update, btn_cancel, btn_img1, btn_img2, btn_img3, btn_img4;
	ImageIcon changeIcon1, changeIcon2, changeIcon3, changeIcon4;
//	String[] img_list = { "images/프로필고양이1.jpg", "images/프로필고양이2.jpg", "images/프로필쥐1.jpg", "images/프로필쥐2.png",
//			"images/고양이.png", "images/쥐.png" };
//	ArrayList<JButton> btn_list = new ArrayList<JButton>();

	public ProfileUIEvent(ProfileUI pui) {
		this.pui = pui;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj == pui.file_change_btn) { // 프로필 수정
			file_form();
		} else if (obj == btn_update) { // 수정
//			update_from();
			if (btn_img1.getMouseListeners() != null) {
				pui.img_label.setIcon(changeIcon1);
			}
//			} else if (btn_img2.getMouseListeners() != null) {
//				pui.img_label.setIcon(changeIcon2);
//			} else if (btn_img3.getMouseListeners() != null) {
//				pui.img_label.setIcon(changeIcon3);
//			} else if (btn_img4.getMouseListeners() != null) {
//				pui.img_label.setIcon(changeIcon4);
			System.out.println("수정");
		} else if (obj == btn_cancel) { // 취소
			// 이미지 다시 선택할 수 있게 만듦
			btn_img1.setEnabled(true);
			btn_img2.setEnabled(true);
			btn_img3.setEnabled(true);
			btn_img4.setEnabled(true);
			btn_img1.setBorder(null);
			btn_img2.setBorder(null);
			btn_img3.setBorder(null);
			btn_img4.setBorder(null);
			System.out.println("취소");
		} else if (obj == pui.exit_btn) { // 프로필 창 종료
			pui.f.dispose();
		}
	}

	public void file_form() {
		// 프로필 수정 창 생성
		f = new JFrame("프로필 수정");
		JPanel center_panel = new JPanel(new GridLayout(3, 2));
		JPanel south_panel = new JPanel();
		JPanel img1_panel = new JPanel();
		JPanel img2_panel = new JPanel();
		JPanel img3_panel = new JPanel();
		JPanel img4_panel = new JPanel();
		JPanel img5_panel = new JPanel();
		JPanel img6_panel = new JPanel();

//		for (String str : img_list) {
//			ImageIcon icon = new ImageIcon("" + str + "");
//			Image img = icon.getImage();
//			Image changeImg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
//			changeIcon = new ImageIcon(changeImg);
//			btn_img = new JButton(changeIcon);
//			img_panel.add(btn_img);
//			center_panel.add(img_panel);
//			btn_list.add(btn_img);
//		}
		
		// 프로필 이미지 4개
		ImageIcon icon1 = new ImageIcon("images/프로필고양이1.jpg");
		Image img1 = icon1.getImage();
		Image changeImg1 = img1.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		changeIcon1 = new ImageIcon(changeImg1);
		btn_img1 = new JButton(changeIcon1);
		ImageIcon icon2 = new ImageIcon("images/프로필고양이2.jpg");
		Image img2 = icon2.getImage();
		Image changeImg2 = img2.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		changeIcon2 = new ImageIcon(changeImg2);
		btn_img2 = new JButton(changeIcon2);
		ImageIcon icon3 = new ImageIcon("images/프로필쥐1.jpg");
		Image img3 = icon3.getImage();
		Image changeImg3 = img3.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		changeIcon3 = new ImageIcon(changeImg3);
		btn_img3 = new JButton(changeIcon3);
		ImageIcon icon4 = new ImageIcon("images/프로필쥐2.png");
		Image img4 = icon4.getImage();
		Image changeImg4 = img4.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		changeIcon4 = new ImageIcon(changeImg4);
		btn_img4 = new JButton(changeIcon4);
		
		img1_panel.add(btn_img1);
		img2_panel.add(btn_img2);
		img3_panel.add(btn_img3);
		img4_panel.add(btn_img4);
		center_panel.add(img1_panel);
		center_panel.add(img2_panel);
		center_panel.add(img3_panel);
		center_panel.add(img4_panel);
		
		btn_update = new JButton("수정");
		btn_cancel = new JButton("취소");
		btn_update.setFont(Commons.getFont());
		btn_update.setFont(Commons.getFont());
		btn_cancel.setFont(Commons.getFont());
		south_panel.add(btn_update);
		south_panel.add(btn_cancel);

		btn_img1.addMouseListener(this);
		btn_img2.addMouseListener(this);
		btn_img3.addMouseListener(this);
		btn_img4.addMouseListener(this);
		btn_update.addActionListener(this);
		btn_cancel.addActionListener(this);

		f.add(BorderLayout.CENTER, center_panel);
		f.add(BorderLayout.SOUTH, south_panel);

		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				f.dispose();
			}
		});

		//f.setResizable(false);
		f.setSize(500, 750);
		f.setVisible(true);
	}

	public void update_from() {
//		if (btn_img1.getMouseListeners() != null) {
//			btn_img1.setVisible(true);
//			pui.img_label.setIcon(changeIcon1);
//		} else if (btn_img2.getMouseListeners() != null) {
//			pui.img_label.setIcon(changeIcon2);
//		} else if (btn_img3.getMouseListeners() != null) {
//			pui.img_label.setIcon(changeIcon3);
//		} else if (btn_img4.getMouseListeners() != null) {
//			pui.img_label.setIcon(changeIcon4);
//		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object obj = e.getSource();

		// 프로필 수정의 이미지중 하나를 눌렀을 때 선택된 이미지 버튼의 바깥쪽에 빨간색 선이 생기게 하고
		// 선택되지 않은 나머지 이미지 버튼은 비활성화
		if (e.getClickCount() == 2) {
			if (obj == btn_img1) {
				btn_img1.setBorder(BorderFactory.createLineBorder(Color.RED));
				btn_img2.setEnabled(false);
				btn_img3.setEnabled(false);
				btn_img4.setEnabled(false);
			} else if (obj == btn_img2) {
				btn_img2.setBorder(BorderFactory.createLineBorder(Color.RED));
				btn_img1.setEnabled(false);
				btn_img3.setEnabled(false);
				btn_img4.setEnabled(false);
			} else if (obj == btn_img3) {
				btn_img3.setBorder(BorderFactory.createLineBorder(Color.RED));
				btn_img1.setEnabled(false);
				btn_img2.setEnabled(false);
				btn_img4.setEnabled(false);
			} else if (obj == btn_img4) {
				btn_img4.setBorder(BorderFactory.createLineBorder(Color.RED));
				btn_img1.setEnabled(false);
				btn_img2.setEnabled(false);
				btn_img3.setEnabled(false);
			}
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
	
}
