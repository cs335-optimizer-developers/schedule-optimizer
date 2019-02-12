package display;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Display2 {
	private static int width;
	private static int height;
	private static final int ROWS = 10;
	private static final int COLS = 7;
	
	
	private static void createAndShowGUI() {
		//Create and set up the window.
		JFrame frame = new JFrame("Schedule Optimizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Relative screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = (int) (screenSize.getWidth()/1.3);
        height = (int) (screenSize.getHeight()/1.3);
        frame.setMinimumSize(new Dimension(width,height));
        
        // Put a new panel in each position of the grid.
        // Allows us to specify the position of elements.
		int i = ROWS;
		int j = COLS;
		JPanel[][] panelHolder = new JPanel[i][j];    
		frame.setLayout(new GridLayout(i,j));
		for(int m = 0; m < i; m++) {
		   for(int n = 0; n < j; n++) {
		      panelHolder[m][n] = new JPanel();
		      frame.add(panelHolder[m][n]);
		   }
		}
		
		// Add and display the button.
        addOptionsButton(panelHolder[9][3]);
        
        // Display the window.
        frame.pack();
        frame.setVisible(true);
    }
	
	/** 
	 * Add the options button to the specified panel.
	 * TODO: Create a pop-up showing more advanced options the user can input into the generator.
	 * @param p, a JPanel where the button will be placed.
	 */
	private static void addOptionsButton(JPanel p) {
		JButton b = new JButton("Advanced Options");
		p.add(b);
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Advanced Options Pressed");
				// TODO: add popup
				
			}
		});
	}

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
