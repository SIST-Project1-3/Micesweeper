package gui;

import javax.swing.JFrame;

public class BoardViewUI {
	// Field
	private JFrame frame;

	// Constructor
	public BoardViewUI() {
		initialize();
	}

	// Method
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new BoardViewUI();
	}
}
