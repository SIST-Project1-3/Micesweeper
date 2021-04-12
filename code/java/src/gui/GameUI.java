package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameUI {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameUI window = new GameUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GameUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100,100,1280,720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel westpanel = new JPanel();
		westpanel.setSize(300,500);
		frame.getContentPane().add(westpanel, BorderLayout.WEST);
		westpanel.setLayout(new GridLayout(4, 1, 0, 50));
		
		JPanel panel_6_1 = new JPanel();
		westpanel.add(panel_6_1);
		
		JLabel lblNewLabel_1 = new JLabel("\uBC29\uC7A5 \uD45C\uC2DC\uC6A9");
		panel_6_1.add(lblNewLabel_1);
		
		JPanel panel_3 = new JPanel();
		westpanel.add(panel_3);
		
		JPanel panel_4 = new JPanel();
		westpanel.add(panel_4);
		panel_4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("\uBC29\uC7A5 ID");
		lblNewLabel.setFont(new Font("±¼¸²", Font.PLAIN, 24));
		panel_4.add(lblNewLabel);
		
		JPanel panel_5 = new JPanel();
		westpanel.add(panel_5);
		panel_5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnNewButton_16 = new JButton("\uD504\uB85C\uD544 \uBCF4\uAE30");
		btnNewButton_16.setFont(new Font("±¼¸²", Font.PLAIN, 24));
		panel_5.add(btnNewButton_16);
		JPanel centerpanel = new JPanel();
		frame.getContentPane().add(centerpanel, BorderLayout.CENTER);
		centerpanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		centerpanel.add(panel);
		panel.setLayout(new GridLayout(9, 9, 5, 5));
		
		JButton btnNewButton1_1 = new JButton("  ");
		panel.add(btnNewButton1_1);
		JButton btnNewButton1_2 = new JButton("  ");
		panel.add(btnNewButton1_2);
		JButton btnNewButton1_3 = new JButton("  ");
		panel.add(btnNewButton1_3);
		JButton btnNewButton1_4 = new JButton("  ");
		panel.add(btnNewButton1_4);
		JButton btnNewButton1_5 = new JButton("  ");
		panel.add(btnNewButton1_5);
		JButton btnNewButton1_6 = new JButton("  ");
		panel.add(btnNewButton1_6);
		JButton btnNewButton1_7 = new JButton("  ");
		panel.add(btnNewButton1_7);
		JButton btnNewButton1_8 = new JButton("  ");
		panel.add(btnNewButton1_8);
		JButton btnNewButton1_9 = new JButton("  ");
		panel.add(btnNewButton1_9);
		JButton btnNewButton2_1 = new JButton("  ");
		panel.add(btnNewButton2_1);
		JButton btnNewButton2_2 = new JButton("  ");
		panel.add(btnNewButton2_2);
		JButton btnNewButton2_3 = new JButton("  ");
		panel.add(btnNewButton2_3);
		JButton btnNewButton2_4 = new JButton("  ");
		panel.add(btnNewButton2_4);
		JButton btnNewButton2_5 = new JButton("  ");
		panel.add(btnNewButton2_5);
		JButton btnNewButton2_6 = new JButton("  ");
		panel.add(btnNewButton2_6);
		JButton btnNewButton2_7 = new JButton("  ");
		panel.add(btnNewButton2_7);
		JButton btnNewButton2_8 = new JButton("  ");
		panel.add(btnNewButton2_8);
		JButton btnNewButton2_9 = new JButton("  ");
		panel.add(btnNewButton2_9);
		JButton btnNewButton3_1 = new JButton("  ");
		panel.add(btnNewButton3_1);
		JButton btnNewButton3_2 = new JButton("  ");
		panel.add(btnNewButton3_2);
		JButton btnNewButton3_3 = new JButton("  ");
		panel.add(btnNewButton3_3);
		JButton btnNewButton3_4 = new JButton("  ");
		panel.add(btnNewButton3_4);
		JButton btnNewButton3_5 = new JButton("  ");
		panel.add(btnNewButton3_5);
		JButton btnNewButton3_6 = new JButton("  ");
		panel.add(btnNewButton3_6);
		JButton btnNewButton3_7 = new JButton("  ");
		panel.add(btnNewButton3_7);
		JButton btnNewButton3_8 = new JButton("  ");
		panel.add(btnNewButton3_8);
		JButton btnNewButton3_9 = new JButton("  ");
		panel.add(btnNewButton3_9);
		JButton btnNewButton4_1 = new JButton("  ");
		panel.add(btnNewButton4_1);
		JButton btnNewButton4_2 = new JButton("  ");
		panel.add(btnNewButton4_2);
		JButton btnNewButton4_3 = new JButton("  ");
		panel.add(btnNewButton4_3);
		JButton btnNewButton4_4 = new JButton("  ");
		panel.add(btnNewButton4_4);
		JButton btnNewButton4_5 = new JButton("  ");
		panel.add(btnNewButton4_5);
		JButton btnNewButton4_6 = new JButton("  ");
		panel.add(btnNewButton4_6);
		JButton btnNewButton4_7 = new JButton("  ");
		panel.add(btnNewButton4_7);
		JButton btnNewButton4_8 = new JButton("  ");
		panel.add(btnNewButton4_8);
		JButton btnNewButton4_9 = new JButton("  ");
		panel.add(btnNewButton4_9);
		JButton btnNewButton5_1 = new JButton("  ");
		panel.add(btnNewButton5_1);
		JButton btnNewButton5_2 = new JButton("  ");
		panel.add(btnNewButton5_2);
		JButton btnNewButton5_3 = new JButton("  ");
		panel.add(btnNewButton5_3);
		JButton btnNewButton5_4 = new JButton("  ");
		panel.add(btnNewButton5_4);
		JButton btnNewButton5_5 = new JButton("  ");
		panel.add(btnNewButton5_5);
		JButton btnNewButton5_6 = new JButton("  ");
		panel.add(btnNewButton5_6);
		JButton btnNewButton5_7 = new JButton("  ");
		panel.add(btnNewButton5_7);
		JButton btnNewButton5_8 = new JButton("  ");
		panel.add(btnNewButton5_8);
		JButton btnNewButton5_9 = new JButton("  ");
		panel.add(btnNewButton5_9);
		JButton btnNewButton6_1 = new JButton("  ");
		panel.add(btnNewButton6_1);
		JButton btnNewButton6_2 = new JButton("  ");
		panel.add(btnNewButton6_2);
		JButton btnNewButton6_3 = new JButton("  ");
		panel.add(btnNewButton6_3);
		JButton btnNewButton6_4 = new JButton("  ");
		panel.add(btnNewButton6_4);
		JButton btnNewButton6_5 = new JButton("  ");
		panel.add(btnNewButton6_5);
		JButton btnNewButton6_6 = new JButton("  ");
		panel.add(btnNewButton6_6);
		JButton btnNewButton6_7 = new JButton("  ");
		panel.add(btnNewButton6_7);
		JButton btnNewButton6_8 = new JButton("  ");
		panel.add(btnNewButton6_8);
		JButton btnNewButton6_9 = new JButton("  ");
		panel.add(btnNewButton6_9);
		JButton btnNewButton7_1 = new JButton("  ");
		panel.add(btnNewButton7_1);
		JButton btnNewButton7_2 = new JButton("  ");
		panel.add(btnNewButton7_2);
		JButton btnNewButton7_3 = new JButton("  ");
		panel.add(btnNewButton7_3);
		JButton btnNewButton7_4 = new JButton("  ");
		panel.add(btnNewButton7_4);
		JButton btnNewButton7_5 = new JButton("  ");
		panel.add(btnNewButton7_5);
		JButton btnNewButton7_6 = new JButton("  ");
		panel.add(btnNewButton7_6);
		JButton btnNewButton7_7 = new JButton("  ");
		panel.add(btnNewButton7_7);
		JButton btnNewButton7_8 = new JButton("  ");
		panel.add(btnNewButton7_8);
		JButton btnNewButton7_9 = new JButton("  ");
		panel.add(btnNewButton7_9);
		JButton btnNewButton8_1 = new JButton("  ");
		panel.add(btnNewButton8_1);
		JButton btnNewButton8_2 = new JButton("  ");
		panel.add(btnNewButton8_2);
		JButton btnNewButton8_3 = new JButton("  ");
		panel.add(btnNewButton8_3);
		JButton btnNewButton8_4 = new JButton("  ");
		panel.add(btnNewButton8_4);
		JButton btnNewButton8_5 = new JButton("  ");
		panel.add(btnNewButton8_5);
		JButton btnNewButton8_6 = new JButton("  ");
		panel.add(btnNewButton8_6);
		JButton btnNewButton8_7 = new JButton("  ");
		panel.add(btnNewButton8_7);
		JButton btnNewButton8_8 = new JButton("  ");
		panel.add(btnNewButton8_8);
		JButton btnNewButton8_9 = new JButton("  ");
		panel.add(btnNewButton8_9);
		JButton btnNewButton9_1 = new JButton("  ");
		panel.add(btnNewButton9_1);
		JButton btnNewButton9_2 = new JButton("  ");
		panel.add(btnNewButton9_2);
		JButton btnNewButton9_3 = new JButton("  ");
		panel.add(btnNewButton9_3);
		JButton btnNewButton9_4 = new JButton("  ");
		panel.add(btnNewButton9_4);
		JButton btnNewButton9_5 = new JButton("  ");
		panel.add(btnNewButton9_5);
		JButton btnNewButton9_6 = new JButton("  ");
		panel.add(btnNewButton9_6);
		JButton btnNewButton9_7 = new JButton("  ");
		panel.add(btnNewButton9_7);
		JButton btnNewButton9_8 = new JButton("  ");
		panel.add(btnNewButton9_8);
		JButton btnNewButton9_9 = new JButton("  ");
		panel.add(btnNewButton9_9);
		
		
		
		
		
		JPanel southpanel = new JPanel();
		frame.getContentPane().add(southpanel, BorderLayout.SOUTH);
		southpanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		southpanel.add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(new GridLayout(2, 0, 0, 20));
		
		JButton btnNewButton_14 = new JButton("\uC900\uBE44");
		btnNewButton_14.setFont(new Font("±¼¸²", Font.PLAIN, 24));
		btnNewButton_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_1.add(btnNewButton_14);
		
		JButton btnNewButton_15 = new JButton("\uB098\uAC00\uAE30");
		btnNewButton_15.setFont(new Font("±¼¸²", Font.PLAIN, 24));
		btnNewButton_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_1.add(btnNewButton_15);
		
		JPanel panel_2 = new JPanel();
		southpanel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JTextArea textArea = new JTextArea();
		textArea.setRows(10);
		panel_2.add(textArea, BorderLayout.CENTER);
		
		textField = new JTextField();
		panel_2.add(textField, BorderLayout.SOUTH);
		textField.setColumns(1);
		
		JPanel eastpanel = new JPanel();
		frame.getContentPane().add(eastpanel, BorderLayout.EAST);
		eastpanel.setLayout(new GridLayout(4, 1, 0, 50));
		
		JPanel panel_6 = new JPanel();
		eastpanel.add(panel_6);
		
		JPanel panel_3_2 = new JPanel();
		eastpanel.add(panel_3_2);
		
		JPanel panel_4_1 = new JPanel();
		eastpanel.add(panel_4_1);
		panel_4_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("±¼¸²", Font.PLAIN, 24));
		panel_4_1.add(lblId);
		
		JPanel panel_5_1 = new JPanel();
		eastpanel.add(panel_5_1);
		panel_5_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnNewButton_16_2 = new JButton("\uD504\uB85C\uD544 \uBCF4\uAE30");
		btnNewButton_16_2.setFont(new Font("±¼¸²", Font.PLAIN, 24));
		panel_5_1.add(btnNewButton_16_2);
	}

}
