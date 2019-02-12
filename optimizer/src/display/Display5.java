package display;
/** mock up* by Naissa Charles*/
/**search buttom*/

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

 import javax.swing.*;

import java.awt.FlowLayout;
import java.awt.GraphicsConfiguration;
import java.awt.TextField;
 //import javax.awt.*;
 

public class Display5 {
	
	/*static JTextField searchField;
	
	//public TextField (String intitalText);
	static GraphicsConfiguration gc;
	
	
	//print a statement when you press enter;

	public static void main (String [] args) {
	 
		//create the window
				JFrame frame = new JFrame (gc);
				frame.setTitle("Schedule Optimizer-Search Bar");
				frame.setSize(600, 400);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				
				frame.getContentPane().setLayout(new FlowLayout());
				searchField = new JTextField("Search", 20);
				
				
				frame.getContentPane().add(searchField);
				frame.setVisible(true);
				//you need text field and button
	}*/
	
	SearchBar(){
		
		JFrame frame = new JFrame("Schedule Optimizer-Search Bar");
		
		//enter button
		JButton button = new JButton("Enter");
		button.setBounds(100,100,140,40);
		
		
		JLabel label = new JLabel();
		label.setText("Search");
		label.setBounds(10,10,100,100);
		
		
		JLabel label1 = new JLabel();
		label1.setBounds(10,110,200,100);
		
		JTextField searchField = new JTextField();
		searchField.setBounds(110,50,130,30);
		
		frame.add(label1);
		frame.add(searchField);
		frame.add(label);
		frame.add(button);
		frame.setSize(400, 400);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		button.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent arg0) {
				label1.setText("YOU SEARCHED!");
			}
		});
		
		
		public static void main(String[] args) {
			new SearchBar();
		}
		
	}
	
	
	
	

}

