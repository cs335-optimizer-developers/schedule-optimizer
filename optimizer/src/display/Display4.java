package display;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.TableColumn;

/*
 * Mockup by Joel Armstrong
 * 
 */

public class Display4 {
	
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Schedule Optimizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //create and set up the JLabel
        JLabel label = new JLabel("Your	Parameters");
        Dimension d = new Dimension(100,50);
        label.setPreferredSize(d);
        label.setHorizontalAlignment(SwingConstants.CENTER);

        //Create and set up the JPanel
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.add(label);
        
        //Creating a table and its data
        String[] columns = {"Requirement","Fulfilled?"};
        String[][] data = {{"Gen EDs","no"},{"CATC Tags","yes"},{"Computer Science Major","no"}};
        JTable jt;
        jt = new JTable(data, columns);
        jt.setFillsViewportHeight(true);
        TableColumn tc = jt.getColumnModel().getColumn(0);
        tc.setPreferredWidth(400);
        tc = jt.getColumnModel().getColumn(1);
        tc.setPreferredWidth(100);
        JScrollPane jsp = new JScrollPane(jt);
        main.add(jsp);
        
        //making the generate button
        JButton jb = new JButton("Generate Schedule Based on These Constrains");
        main.add(jb);
        frame.getContentPane().add(main);        

        //Display the window.
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.getWidth()/4);
        int height = (int) (screenSize.getHeight()/1.4);
        frame.setMinimumSize(new Dimension(width,height));
        frame.pack();
        frame.setVisible(true);
    }	
	
	public static void main(String args[]){
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
	}
}
