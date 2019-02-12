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
	
	static JTextField searchField;
	
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
	}
	
	
	
	
	
	

}

