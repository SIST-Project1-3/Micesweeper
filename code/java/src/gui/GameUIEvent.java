package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import dao.GameDAO;
import gamesystem.GameSystemClient;
import gamesystem.GameSystemServer;
import vo.GameVO;
import vo.MemberVO;
import vo.MessageVO;

public class GameUIEvent implements ActionListener, WindowListener, MouseListener {

	ArrayList<Integer> clickmice;
	GameUI ui;
	GameVO gvo;
	GameSystemClient gsc;

	public GameUIEvent(GameUI ui) {
		gvo = new GameVO();
		this.ui = ui;
		gvo = ui.gvo;
		gsc = ui.gsc;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		clickmice = gvo.getClickmice();
		Object obj = e.getSource();

		if (obj == ui.watch_profile_btn) { // 방장 프로필 보기
//			MessageVO msg = new MessageVO();
//			msg.setStatus(MessageVO.GAME_PROFILE);
//			msg.setId(ui.master_id_label.getText());
			MemberVO gameProfile = ui.client.gameProfile(ui.master_id_label.getText());
//			MemberVO gameProfile = gdao.getGameProfileResult(msg);
			ProfileUI pui = new ProfileUI();
			pui.game_Profile(gameProfile);
		} else if (obj == ui.send_btn || obj == ui.chat_tf) { // 채팅
			ui.client.sendGameChat(ui.chat_tf.getText());
			ui.chat_tf.setText("");
			System.out.println("텍스트");
		} else if (obj == ui.ready_btn) { // 준비
			ui.ready_btn.setEnabled(false);
			ui.client.sendReady();
//			if(gvo.isReadyflag()==true && gvo.isReadyflag2()==true) {
//				gvo.setGameflag(true);
//			}
			// gvo.gameflag = true; // 방장 준비 처음에 비활성화 - 다음에 활성화시킬 것
		} else if (obj == ui.exit_btn) { // 나가기
			if (gvo.isGameflag() == true) {
				int answer = JOptionPane.showConfirmDialog(null, Commons.getMsg("게임 중 종료시 패배처리됩니다. 정말로 종료하시겠습니까?"));
				if (answer == 0) {
					ui.client.sendLeave(ui.client.id);
					exit();
					// 게임종료시 패배처리됩니다 종료하시겠습니까
				}
			} else {
				exit();
			}
		} else if (obj == ui.watch_profile_btn2) { // 참가자 프로필 보기
//			MessageVO msg = new MessageVO();
//			msg.setStatus(MessageVO.GAME_PROFILE);
//			msg.setId(ui.user_id_label.getText());
//			MemberVO gameProfile = gdao.getGameProfileResult(msg);
			MemberVO gameProfile = ui.client.gameProfile(ui.user_id_label.getText());
			ProfileUI pui = new ProfileUI();
			pui.game_Profile(gameProfile);
		} else if (gsc.turnflag == true) { // 지뢰 버튼 클릭 처리
			if (gvo.gameflag == true) {
				int btnName = Integer.parseInt(((JButton) obj).getName());
				int btnNo = btnName / 10 * 9 + btnName % 10; // 버튼의 순서: 00 ~ 88 까지 매핑된 버튼의 이름을 0 ~ 81
				ui.client.sendGameClick(btnNo);
//			gvo.setClickflag(true);// 클릭했다는 정보 갱신
//			ui.gsc.calcBtnClick();
//			System.out.println(gvo.getClickmice());// 이따삭제
			}
		}
	}

	public void exit() {
		new MainUI(ui.client);
		ui.exit();
	}

	// 승리하면 데이터베이스에 승리 횟수 +1 증가
	public boolean win() {
		return ui.client.sendWinResult();
	}

	// 패배하면 데이터베이스에 패배 횟수 +1 증가
	public boolean lose() {
		return ui.client.sendLoseResult();
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) { // 나가기
		if (gvo.isGameflag() == true) {
			int answer = JOptionPane.showConfirmDialog(null, Commons.getMsg("게임 중 종료시 패배처리됩니다. 정말로 종료하시겠습니까?"));
			if (answer == 0) {
				ui.client.sendLeave(ui.client.id);
				exit();
				// 게임종료시 패배처리됩니다 종료하시겠습니까
			}
		} else {
			exit();
		}
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
