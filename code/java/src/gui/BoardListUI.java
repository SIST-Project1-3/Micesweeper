package gui;

import javax.swing.JFrame;

public class BoardListUI {

	// Field
	private JFrame frame;

	// Constructor
	public BoardListUI() {
		initialized();
		
	}

	// Method
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new BoardListUI();
	}
}
