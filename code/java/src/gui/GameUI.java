package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import vo.GameVO;

public class GameUI {

	public JFrame mainframe;
	public JTextField textField;
	public JTextArea textArea;
	public ArrayList<JButton> micebtn;
	public JButton watchprofilebtn, readybutton, exitbutton, send, watchprofilebtn_2;
	GameUIEvent event = new GameUIEvent(this);
	
	/**
	 * Launch the application
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new GameUI();
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
		GameVO gvo = new GameVO();
		
		
		mainframe = new JFrame();
		mainframe.setBounds(100,100,1280,720);
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainframe.getContentPane().setLayout(new BorderLayout(0, 0));
		mainframe.setTitle("쥐뢰찾기");
		mainframe.setVisible(true);
		
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
		bangjangprofilepanel.setVisible(true);
		westpanel.add(bangjangprofilepanel);
		bangjangprofilepanel.setLayout(new GridLayout(2, 1, 0, 20));
		
		JLabel bangjangidlabel = new JLabel("방장 ID");
		bangjangidlabel.setHorizontalAlignment(SwingConstants.CENTER);
		bangjangidlabel.setFont(Commons.getFont());
		bangjangprofilepanel.add(bangjangidlabel);
		
		JPanel panel_1 = new JPanel();
		bangjangprofilepanel.add(panel_1);
		
		watchprofilebtn = new JButton("프로필 보기");
		watchprofilebtn.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		watchprofilebtn.addActionListener(event);
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
		
		
		
		
		for (int a =0; a<9; a++) {
			for (int b=0; b<9; b++) {
				micebtn = gvo.getMicebtn();
				JButton btn = new JButton(a + "_" + b);
				btn.setFont(Commons.getFont());
				btn.setPreferredSize(new Dimension(52, 52));
				btn.setName(Integer.toString(a)+Integer.toString(b));
				gamepanel.add(btn);
				btn.addActionListener(event);
				micebtn.add(btn);
				gvo.setMicebtn(micebtn);
			}
		}
		
		
		
		JPanel southpanel = new JPanel();
		mainframe.getContentPane().add(southpanel, BorderLayout.SOUTH);
		southpanel.setLayout(new BorderLayout(0, 0));
		
		JPanel readypanel = new JPanel();
		southpanel.add(readypanel, BorderLayout.EAST);
		readypanel.setLayout(new GridLayout(2, 0, 0, 20));
		
		readybutton = new JButton("준비");
		readybutton.setFont(Commons.getFont());
		readybutton.addActionListener(event);
		readypanel.add(readybutton);
		
		exitbutton = new JButton("나가기");
		exitbutton.setFont(Commons.getFont());
		exitbutton.addActionListener(event);
		readypanel.add(exitbutton);
		
		JPanel chatpanel = new JPanel();
		southpanel.add(chatpanel, BorderLayout.CENTER);
		chatpanel.setLayout(new BorderLayout(0, 0));
		
		textArea = new JTextArea();
		textArea.setRows(10);
		chatpanel.add(textArea, BorderLayout.CENTER);
		
		JPanel chatpanel_send = new JPanel();
		chatpanel.add(chatpanel_send, BorderLayout.SOUTH);
		chatpanel_send.setLayout(new BorderLayout(0, 0));
		
		textField = new JTextField();
		textField.setColumns(1);
		textField.addActionListener(event);
		chatpanel_send.add(textField);
		
		send = new JButton("send");
		send.addActionListener(event);
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
		
		watchprofilebtn_2 = new JButton("프로필 보기");
		watchprofilebtn_2.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		watchprofilebtn_2.addActionListener(event);
		panel_1_1.add(watchprofilebtn_2);
	}
	public void exit() {
		mainframe.dispose();
	}
}
