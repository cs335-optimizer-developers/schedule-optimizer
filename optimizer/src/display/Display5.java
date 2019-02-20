package display;
/** mock up by Naissa Charles*/
/**search button*/

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

 import javax.swing.*;
 

public class Display5 {
	
	Display5(){
		
		JFrame frame = new JFrame("Schedule Optimizer-Search Bar");
		
		//enter button
		JButton button = new JButton("Enter");
		button.setBounds(250,50,90,20);
		
		//search label
		JLabel label = new JLabel();
		label.setText("Search");
		label.setBounds(50,10,100,100);
		
		
		JLabel label1 = new JLabel();
		label1.setBounds(10,110,200,100);
		
		//the actual search bar
		JTextField searchField = new JTextField();
		searchField.setBounds(110,50,130,20);
		
		//adding everything to the frame
		frame.add(label1);
		frame.add(searchField);
		frame.add(label);
		frame.add(button);
		frame.setSize(400, 400);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//button action listener 
		button.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent arg0) {
				label1.setText("YOU SEARCHED!");
			}
		});
		
	}
	public static void main(String[] args) {
		new Display5();
	}
	
	
}

