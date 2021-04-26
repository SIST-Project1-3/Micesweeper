package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JList;
import javax.swing.JOptionPane;

import vo.BoardVO;
import vo.MemberVO;
import vo.MessageVO;

public class MainUIEvent implements ActionListener, WindowListener, MouseListener {
	MainUI ui;

	public MainUIEvent(MainUI ui) {
		this.ui = ui;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == ui.list_btn.get(0)) {// 방 생성
			new CreateRoomUI();
		} else if (obj == ui.list_btn.get(1)) { // 내 프로필
			MessageVO msg = new MessageVO();
			msg.setStatus(MessageVO.MY_PROFILE);
			msg.setId(ui.client.getId());
			MemberVO profile = ui.client.myProfile(msg);
			new ProfileUI(profile);
		} else if (obj == ui.list_btn.get(2)) { // 커뮤니티
			new BoardListUI(ui.client);
		} else if (obj == ui.list_btn.get(3)) { // 게임종료
			ui.exit();
		} else if (obj == ui.btn_send || obj == ui.tf_chat) { // 채팅
			if (!ui.tf_chat.getText().isEmpty()) { // 입력한 내용이 있는 경우 실행
				MessageVO msg = new MessageVO();
				msg.setStatus(MessageVO.SERVERCHAT);
				msg.setId(ui.client.getId());
				msg.setContent(ui.tf_chat.getText());
				ui.client.sendMultiChat(msg);
				ui.tf_chat.setText("");
				ui.ta_chat.setCaretPosition(ui.ta_chat.getDocument().getLength());
			}
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		ui.exit();
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		JList<String> list = (JList<String>) e.getSource();
		if (e.getClickCount() == 2) { // 더블클릭
			int index = list.locationToIndex(e.getPoint());
			int answer = JOptionPane.showConfirmDialog(null, Commons.getMsg(ui.rooms[index] + "에 입장하시겠습니까?"));
			
			MessageVO msg = new MessageVO();
			msg.setStatus(MessageVO.MY_PROFILE);
			msg.setId(ui.client.getId());
			msg.setId2(id2);
			MemberVO img = ui.client.myProfile(msg);
			
			if (answer == 0) {
				JOptionPane.showMessageDialog(null, Commons.getMsg("방 입장"));
				new GameUI(img);
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
