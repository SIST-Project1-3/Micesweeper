package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class js {

	// Field
	JFrame f;
	Panel all_panel, p_right, p_left, top_panel, center_panel, footer_panel, order_panel;

	Label l_order, l_cus_call;
	JTextArea ta_date, ta_cal;

	JTable cus_order;
	JButton b_cus_call;

	// Constructor
	public js() {
		init();
	
	}	

	// Method
	
	public void init() {
			
			f = new JFrame();
			
			Panel p_cus_order = new Panel();
			Panel p_ta_date = new Panel(); 
			Panel p_ta_cal = new Panel(); 
			Panel p_l_order = new Panel(); 
			Panel p_l_cus_call = new Panel();
			Panel p_b_cus_call = new Panel();
			
			
			
			all_panel = new Panel(new GridLayout(1,2));
			p_right = new Panel();
			p_left = new Panel(new BorderLayout());
			
			top_panel  = new Panel();
			center_panel = new Panel(new BorderLayout());
			footer_panel  = new Panel(new GridLayout(2,1));
			
			order_panel = new Panel(new GridLayout(2,1));
			
			l_order = new Label("绊按林巩"); 
			l_cus_call = new Label("绊按龋免");
			
			ta_date = new JTextArea(2,10); 
			ta_cal = new JTextArea(2,10); 
		
			cus_order = new JTable(100,50);
			
			b_cus_call = new JButton("bell");
			
			/**持扁**/
			p_cus_order.add(cus_order);
			p_ta_date.add(ta_date);
			p_ta_cal.add(ta_cal);
			p_l_order.add(l_order);
			p_l_cus_call.add(l_cus_call);
			p_b_cus_call.add(b_cus_call);
			
			order_panel.add(p_l_order);
			order_panel.add(p_ta_cal);
			
			top_panel.add(p_ta_date);
			
			center_panel.add(BorderLayout.NORTH,order_panel);
//			center_panel.add(p_ta_cal);
			center_panel.add(BorderLayout.CENTER,p_cus_order);
			
			footer_panel.add(p_l_cus_call);
			footer_panel.add(p_b_cus_call);
		
			p_left.add(BorderLayout.NORTH,top_panel);
			p_left.add(BorderLayout.CENTER,center_panel);
			p_left.add(BorderLayout.SOUTH,footer_panel);
			
			all_panel.add(p_left);
			all_panel.add(p_right);
			
			f.add(all_panel);
			f.setSize(1439,2000);
			f.setVisible(true);
			
	}
	
	public static void main(String[] args) {
		new js();
	}
}