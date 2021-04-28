package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JButton;

import dao.GameDAO;
import gamesystem.GameSystemClient;
import gamesystem.GameSystemServer;
import vo.GameVO;
import vo.MemberVO;
import vo.MessageVO;

public class GameUIEvent implements ActionListener, WindowListener, MouseListener {

	ArrayList<Integer> clickmice;
	GameUI ui;
	GameDAO gdao;
	GameVO gvo;
	GameSystemClient gsc;

	public GameUIEvent(GameUI ui) {
		gdao = new GameDAO();
		gvo = new GameVO();
		this.ui = ui;
		gvo = ui.gvo;
		gsc = ui.gsc;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		clickmice = gvo.getClickmice();
		Object obj = e.getSource();

		if (obj == ui.watchprofilebtn) {// 방장 프로필
			MessageVO msg = new MessageVO();
			msg.setStatus(MessageVO.GAME_PROFILE);
			msg.setId(ui.bangjangidlabel.getText());
			MemberVO gameProfile = gdao.getGameProfileResult(msg);
			ProfileUI pui = new ProfileUI();
			pui.game_Profile(gameProfile);
		} else if (obj == ui.send || obj == ui.textField) { // 채팅
			ui.client.sendGameChat(ui.textField.getText());
			ui.textField.setText("");
			System.out.println("텍스트");
		} else if (obj == ui.readybutton) { // 레디
			win();
			// lose();
		} else if (obj == ui.exitbutton) { // 나가기
			exit();
		} else if (obj == ui.watchprofilebtn_2) { // 도전자 프로필
			MessageVO msg = new MessageVO();
			msg.setStatus(MessageVO.GAME_PROFILE);
			msg.setId(ui.idlabel.getText());
			MemberVO gameProfile = gdao.getGameProfileResult(msg);
			ProfileUI pui = new ProfileUI();
			pui.game_Profile(gameProfile);
		} else if(gsc.turnflag == true){ // 지뢰 버튼 클릭 처리

//			String btnName = ((JButton) obj).getName();
			int btnName = Integer.parseInt(((JButton) obj).getName());
			int btnNo = btnName / 10 * 9 + btnName % 10; // 버튼의 순서: 00 ~ 88 까지 매핑된 버튼의 이름을 0 ~ 81
			clickmice.add(btnNo); // 지뢰 클릭 시 클릭된 지뢰 리스트에 추가
			System.out.println(clickmice);
			gvo.setClickmice(clickmice); // 리스트 갱신
			ui.gsc.init();
			System.out.println(gvo.getClickmice());// 이따삭제
		}

	}

	public void exit() {
		new MainUI(ui.client);
		ui.exit();
	}

	public void win() {
		MessageVO msg = new MessageVO();
		msg.setStatus(MessageVO.WIN_OR_LOSE);
		msg.setId(ui.bangjangidlabel.getText());
		gdao.getWinResult(msg);
	}

	public void lose() {
		MessageVO msg = new MessageVO();
		msg.setStatus(MessageVO.WIN_OR_LOSE);
		msg.setId(ui.bangjangidlabel.getText());
		gdao.getLoseResult(msg);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		exit();
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
