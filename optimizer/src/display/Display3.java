package display;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class Display3{
	
	public static void createWindow() {
		//Create and set up the window.
        JFrame frame = new JFrame("Schedule Optimizer");
        
        //creating elements
        JButton button = new JButton("Enter");
        JLabel label = new JLabel();
        JTextField major1 = new JTextField("Enter First Major");
        JTextField major2 = new JTextField("Enter Second Major");
        JTextField major3 = new JTextField("Enter Minor");
        
        //positioning the elements
        button.setBounds(300, 100, 90, 20);
        major1.setBounds(50, 50, 150, 50);
        major2.setBounds(250, 50, 150, 50);
        major3.setBounds(450, 50, 150, 50);
        label.setBounds(300, 150, 100,200);
       
        //adding elements to the screen
        frame.add(label);
        frame.add(button);
        frame.add(major1);
        frame.add(major2);
        frame.add(major3);
        frame.setSize(1000,1000);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        button.addActionListener(new ActionListener() {
        	
        		public void actionPerformed(ActionEvent e) {
        			label.setText("MAJORS ENTERED");
        		}
        	
        });

	}

	public static void main(String argv[]) {
		/*javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createWindow();
            }
        });*/
		createWindow();
		
	}
	
}
	
	

