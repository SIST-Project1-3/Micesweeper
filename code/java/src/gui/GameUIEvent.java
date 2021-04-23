package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import vo.GameVO;

import system.client.ClientSystem;

public class GameUIEvent implements ActionListener, WindowListener, MouseListener {

	ArrayList<Integer> clickmice;
	GameUI ui;
	ClientSystem client;
	GameVO gvo = new GameVO();
	
	public GameUIEvent(GameUI ui) {
		this.ui = ui;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//GameVO gvo = new GameVO();
		clickmice = gvo.getClickmice();
		Object obj = e.getSource();

		if (obj == ui.watchprofilebtn) {// 방장 프로필
			new ProfileUI();
		} else if(obj == ui.send || obj == ui.textField) {
			System.out.println("텍스트");//채팅
		} else if (obj == ui.readybutton) { // 레디
			new ProfileUI();
		} else if (obj == ui.exitbutton) { //나가기
			new MainUI(client);
			ui.exit();
		} else if(obj== ui.watchprofilebtn_2) {
			new ProfileUI();// 도전자 프로필
		} else{

			int a;
			a=(Integer.parseInt(((JButton)obj).getName()))/10*9+(Integer.parseInt(((JButton)obj).getName()))%10;
			clickmice.add(a);
			System.out.println(clickmice);
			gvo.setClickmice(clickmice);
			System.out.println(gvo.getClickmice());//이따삭제
		}
				

	}
			
		
		
		
	

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		
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
