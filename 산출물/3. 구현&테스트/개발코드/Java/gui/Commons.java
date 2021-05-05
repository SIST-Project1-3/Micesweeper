package gui;

import java.awt.Font;
import java.awt.Label;

import javax.swing.JButton;

public class Commons {

	public static Font getFont() {
		Font font = new Font("맑은 고딕", Font.BOLD,12);
		return font;
	}
	
	
	public static JButton getJButton(String name) {
		Font font = new Font("맑은 고딕", Font.BOLD,14);
		JButton button = new JButton(name);
		button.setFont(font);
		return button;
	}
	
	
	/** 메세지 출력 **/
	public static Label getMsg(String msg) {
		Font font = new Font("맑은 고딕", Font.BOLD,12);
		Label label = new Label(msg);
		label.setFont(font);


		return label;
	}
	


}
