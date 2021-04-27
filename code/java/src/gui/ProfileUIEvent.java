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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import vo.MessageVO;

public class ProfileUIEvent implements ActionListener, MouseListener {
	ProfileUI pui;
	JFrame f;
	JButton btn_update, btn_cancel, btn_img1, btn_img2, btn_img3, btn_img4, btn_img5, btn_img6;
	ImageIcon changeIcon1, changeIcon2, changeIcon3, changeIcon4, changeIcon5, changeIcon6;
	String[] img_list;

	public ProfileUIEvent(ProfileUI pui) {
		this.pui = pui;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj == pui.file_change_btn) { // 프로필 바꾸기
			MessageVO msg = new MessageVO();
			msg.setStatus(MessageVO.IMG_REQUEST);
			img_list = pui.client.requestImg(msg);
			file_form();
		} else if (obj == btn_update) { // 확인 (선택한 이미지 저장)
			imgUpdate_from();
		} else if (obj == btn_cancel) { // 취소
			// 이미지 다시 선택할 수 있게 만들기
			btn_img1.setEnabled(true);
			btn_img2.setEnabled(true);
			btn_img3.setEnabled(true);
			btn_img4.setEnabled(true);
			btn_img6.setEnabled(true);
			btn_img6.setEnabled(true);
			btn_img1.setBorder(null);
			btn_img2.setBorder(null);
			btn_img3.setBorder(null);
			btn_img4.setBorder(null);
			btn_img5.setBorder(null);
			btn_img6.setBorder(null);
		} else if (obj == pui.exit_btn) { // 나가기 (프로필 창 종료) 
			pui.f.dispose();
		}
	}

	public void file_form() {
		// 프로필 바꾸기 창 생성
		f = new JFrame("프로필 수정");
		JPanel center_panel = new JPanel(new GridLayout(3, 2));
		JPanel south_panel = new JPanel();
		JPanel img1_panel = new JPanel();
		JPanel img2_panel = new JPanel();
		JPanel img3_panel = new JPanel();
		JPanel img4_panel = new JPanel();
		JPanel img5_panel = new JPanel();
		JPanel img6_panel = new JPanel();

		// 프로필 이미지 6개 생성 & 크기조절
		ImageIcon icon1 = new ImageIcon("" + img_list[0] + "");
		Image img1 = icon1.getImage();
		Image changeImg1 = img1.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		changeIcon1 = new ImageIcon(changeImg1);
		btn_img1 = new JButton(changeIcon1);
		ImageIcon icon2 = new ImageIcon("" + img_list[1] + "");
		Image img2 = icon2.getImage();
		Image changeImg2 = img2.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		changeIcon2 = new ImageIcon(changeImg2);
		btn_img2 = new JButton(changeIcon2);
		ImageIcon icon3 = new ImageIcon("" + img_list[2] + "");
		Image img3 = icon3.getImage();
		Image changeImg3 = img3.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		changeIcon3 = new ImageIcon(changeImg3);
		btn_img3 = new JButton(changeIcon3);
		ImageIcon icon4 = new ImageIcon("" + img_list[3] + "");
		Image img4 = icon4.getImage();
		Image changeImg4 = img4.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		changeIcon4 = new ImageIcon(changeImg4);
		btn_img4 = new JButton(changeIcon4);
		ImageIcon icon5 = new ImageIcon("" + img_list[4] + "");
		Image img5 = icon5.getImage();
		Image changeImg5 = img5.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		changeIcon5 = new ImageIcon(changeImg5);
		btn_img5 = new JButton(changeIcon5);
		ImageIcon icon6 = new ImageIcon("" + img_list[5] + "");
		Image img6 = icon6.getImage();
		Image changeImg6 = img6.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		changeIcon6 = new ImageIcon(changeImg6);
		btn_img6 = new JButton(changeIcon6);

		img1_panel.add(btn_img1);
		img2_panel.add(btn_img2);
		img3_panel.add(btn_img3);
		img4_panel.add(btn_img4);
		img5_panel.add(btn_img5);
		img6_panel.add(btn_img6);
		center_panel.add(img1_panel);
		center_panel.add(img2_panel);
		center_panel.add(img3_panel);
		center_panel.add(img4_panel);
		center_panel.add(img5_panel);
		center_panel.add(img6_panel);

		// 확인, 취소 버튼 생성
		btn_update = new JButton("확인");
		btn_cancel = new JButton("취소");
		btn_update.setFont(Commons.getFont());
		btn_update.setFont(Commons.getFont());
		btn_cancel.setFont(Commons.getFont());
		south_panel.add(btn_update);
		south_panel.add(btn_cancel);

		// 버튼 이벤트 연결
		btn_img1.addMouseListener(this);
		btn_img2.addMouseListener(this);
		btn_img3.addMouseListener(this);
		btn_img4.addMouseListener(this);
		btn_img5.addMouseListener(this);
		btn_img6.addMouseListener(this);
		btn_update.addActionListener(this);
		btn_cancel.addActionListener(this);

		f.add(BorderLayout.CENTER, center_panel);
		f.add(BorderLayout.SOUTH, south_panel);

		// 윈도우 종료 이벤트 (프로필 창)
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				f.dispose();
			}
		});

		f.setResizable(false); // 창크기 변경 막아놓음
		f.setSize(500, 750);
		f.setVisible(true);
	}

	public void imgUpdate_from() {
		// 선택한 이미지 msgVO 이용해서 client에 전달
		MessageVO msg = new MessageVO();
		msg.setStatus(MessageVO.IMG_UPDATE);
		msg.setId(pui.myProfile.getId());

		// 선택된 이미지 msgVO에 담기 위한 if문
		if (pui.img_label.getIcon().equals(changeIcon1)) {
			msg.setImg(img_list[0]);
		} else if (pui.img_label.getIcon().equals(changeIcon2)) {
			msg.setImg(img_list[1]);
		} else if (pui.img_label.getIcon().equals(changeIcon3)) {
			msg.setImg(img_list[2]);
		} else if (pui.img_label.getIcon().equals(changeIcon4)) {
			msg.setImg(img_list[3]);
		} else if (pui.img_label.getIcon().equals(changeIcon5)) {
			msg.setImg(img_list[4]);
		} else if (pui.img_label.getIcon().equals(changeIcon6)) {
			msg.setImg(img_list[5]);
		}

		boolean result = pui.client.updateImg(msg);

		if (result == false) {
			// 프로필 변경 성공 + 프로필 바꾸기 창 종료
			JOptionPane.showMessageDialog(null, Commons.getMsg("프로필 이미지가 변경되었습니다"));
			f.dispose();
		} else {
			// 프로필 변경 실패 + 프로필 바꾸기 창, 프로필 창 종료
			JOptionPane.showMessageDialog(null, Commons.getMsg("변경 실패"));
			f.dispose();
			pui.f.dispose();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object obj = e.getSource();

		// 프로필 바꾸기의 이미지 중 하나를 눌렀을 때 선택된 이미지 버튼의 바깥쪽에 빨간색 선이 생기게 하고
		// 선택되지 않은 나머지 이미지 버튼은 비활성화
		// 선택함과 동시에 내 프로필 창의 이미지 변경 (저장되지는 않음, 미리보기 같은 느낌)
		if (e.getClickCount() == 2) { // 더블클릭해야 버튼이 눌러짐
			if (obj == btn_img1) {
				btn_img1.setBorder(BorderFactory.createLineBorder(Color.RED));
				btn_img2.setEnabled(false);
				btn_img3.setEnabled(false);
				btn_img4.setEnabled(false);
				btn_img5.setEnabled(false);
				btn_img6.setEnabled(false);
				pui.img_label.setIcon(changeIcon1);
			} else if (obj == btn_img2) {
				btn_img2.setBorder(BorderFactory.createLineBorder(Color.RED));
				btn_img1.setEnabled(false);
				btn_img3.setEnabled(false);
				btn_img4.setEnabled(false);
				btn_img5.setEnabled(false);
				btn_img6.setEnabled(false);
				pui.img_label.setIcon(changeIcon2);
			} else if (obj == btn_img3) {
				btn_img3.setBorder(BorderFactory.createLineBorder(Color.RED));
				btn_img1.setEnabled(false);
				btn_img2.setEnabled(false);
				btn_img4.setEnabled(false);
				btn_img5.setEnabled(false);
				btn_img6.setEnabled(false);
				pui.img_label.setIcon(changeIcon3);
			} else if (obj == btn_img4) {
				btn_img4.setBorder(BorderFactory.createLineBorder(Color.RED));
				btn_img1.setEnabled(false);
				btn_img2.setEnabled(false);
				btn_img3.setEnabled(false);
				btn_img5.setEnabled(false);
				btn_img6.setEnabled(false);
				pui.img_label.setIcon(changeIcon4);
			} else if (obj == btn_img5) {
				btn_img5.setBorder(BorderFactory.createLineBorder(Color.RED));
				btn_img1.setEnabled(false);
				btn_img2.setEnabled(false);
				btn_img3.setEnabled(false);
				btn_img4.setEnabled(false);
				btn_img6.setEnabled(false);
				pui.img_label.setIcon(changeIcon5);
			} else if (obj == btn_img6) {
				btn_img6.setBorder(BorderFactory.createLineBorder(Color.RED));
				btn_img1.setEnabled(false);
				btn_img2.setEnabled(false);
				btn_img3.setEnabled(false);
				btn_img4.setEnabled(false);
				btn_img5.setEnabled(false);
				pui.img_label.setIcon(changeIcon6);
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
