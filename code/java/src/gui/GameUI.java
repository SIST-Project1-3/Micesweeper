package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
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
import java.util.ArrayList;

public class GameUI {

	private JFrame mainframe;
	private JTextField textField;

	/**
	 * Launch the application
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameUI window = new GameUI();
					window.mainframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application
	 */
	public GameUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame
	 */
	private void initialize() {
		
		ArrayList<JButton> mice = new ArrayList<JButton>();
		mainframe = new JFrame();
		mainframe.setBounds(100,100,1280,720);
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainframe.getContentPane().setLayout(new BorderLayout(0, 0));
		mainframe.setTitle("쥐뢰찾기");
		
		JPanel westpanel = new JPanel();
		westpanel.setSize(300,500);
		mainframe.getContentPane().add(westpanel, BorderLayout.WEST);
		westpanel.setLayout(new GridLayout(3, 1, 0, 50));
		
		JPanel bangjangpanel = new JPanel();
		westpanel.add(bangjangpanel);
		
		JLabel bangjangpyosilabel = new JLabel("방장 표시용");
		bangjangpanel.add(bangjangpyosilabel);
		
		JPanel bangjangimagepanel = new JPanel();
		ImageIcon icon = new ImageIcon("images/쥐.png");
		Image img = icon.getImage();
		Image changeImg = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		ImageIcon changeIcon = new ImageIcon(changeImg);
		JLabel img_label = new JLabel(changeIcon);
	    bangjangimagepanel.add(img_label);
		westpanel.add(bangjangimagepanel);
		
		JPanel bangjangprofilepanel = new JPanel();
		westpanel.add(bangjangprofilepanel);
		bangjangprofilepanel.setLayout(new GridLayout(2, 1, 0, 20));
		
		JLabel bangjangidlabel = new JLabel("방장 ID");
		bangjangidlabel.setHorizontalAlignment(SwingConstants.CENTER);
		bangjangidlabel.setFont(Commons.getFont());
		bangjangprofilepanel.add(bangjangidlabel);
		
		JPanel panel_1 = new JPanel();
		bangjangprofilepanel.add(panel_1);
		
		JButton watchprofilebtn = new JButton("프로필 보기");
		watchprofilebtn.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		panel_1.add(watchprofilebtn);
		JPanel centerpanel = new JPanel();
		mainframe.getContentPane().add(centerpanel, BorderLayout.CENTER);
		centerpanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		centerpanel.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel gamepanel = new JPanel();
		panel.add(gamepanel);
		gamepanel.setLayout(new GridLayout(9, 9, 0, 0));
		
		
		
		
		for (int a =1; a<10; a++) {
			for (int b=1; b<10; b++) {
				JButton btn = new JButton(a + "_" + b);
				btn.setFont(Commons.getFont());
				btn.setPreferredSize(new Dimension(52, 52));
				gamepanel.add(btn);
				mice.add(btn);
			}
		}
		
		
		
		JPanel southpanel = new JPanel();
		mainframe.getContentPane().add(southpanel, BorderLayout.SOUTH);
		southpanel.setLayout(new BorderLayout(0, 0));
		
		JPanel readypanel = new JPanel();
		southpanel.add(readypanel, BorderLayout.EAST);
		readypanel.setLayout(new GridLayout(2, 0, 0, 20));
		
		JButton readybutton = new JButton("준비");
		readybutton.setFont(Commons.getFont());
		readybutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		readypanel.add(readybutton);
		
		JButton exitbutton = new JButton("나가기");
		exitbutton.setFont(Commons.getFont());
		exitbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		readypanel.add(exitbutton);
		
		JPanel chatpanel = new JPanel();
		southpanel.add(chatpanel, BorderLayout.CENTER);
		chatpanel.setLayout(new BorderLayout(0, 0));
		
		JTextArea textArea = new JTextArea();
		textArea.setRows(10);
		chatpanel.add(textArea, BorderLayout.CENTER);
		
		JPanel chatpanel_send = new JPanel();
		chatpanel.add(chatpanel_send, BorderLayout.SOUTH);
		chatpanel_send.setLayout(new BorderLayout(0, 0));
		
		textField = new JTextField();
		textField.setColumns(1);
		chatpanel_send.add(textField);
		
		JButton send = new JButton("send");
		chatpanel_send.add(send, BorderLayout.EAST);
		
		JPanel eastpanel = new JPanel();
		mainframe.getContentPane().add(eastpanel, BorderLayout.EAST);
		eastpanel.setLayout(new GridLayout(3, 1, 0, 50));
		
		JPanel imsipanel = new JPanel();
		eastpanel.add(imsipanel);
		
		JPanel imagepanel = new JPanel();
		ImageIcon icon2 = new ImageIcon("images/쥐.png");
		Image img2 = icon2.getImage();
		Image changeImg2 = img2.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		ImageIcon changeIcon2 = new ImageIcon(changeImg2);
		JLabel img_label2 = new JLabel(changeIcon2);
	    imagepanel.add(img_label2);
		eastpanel.add(imagepanel);
		
		JPanel profilepanel = new JPanel();
		eastpanel.add(profilepanel);
		profilepanel.setLayout(new GridLayout(2, 1, 0, 20));
		
		JLabel idlabel = new JLabel("ID");
		idlabel.setHorizontalAlignment(SwingConstants.CENTER);
		idlabel.setFont(Commons.getFont());
		profilepanel.add(idlabel);
		
		JPanel panel_1_1 = new JPanel();
		profilepanel.add(panel_1_1);
		
		JButton watchprofilebtn_2 = new JButton("프로필 보기");
		watchprofilebtn_2.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		panel_1_1.add(watchprofilebtn_2);
	}

}
