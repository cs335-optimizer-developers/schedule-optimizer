package display;
import java.awt.*;
import javax.swing.*;

public class Display2 {
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Schedule Optimizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add the ubiquitous "Hello World" label.
        JLabel label = new JLabel("Hello World");
        frame.getContentPane().add(label);

        //Display the window.
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.getWidth()/1.3);
        int height = (int) (screenSize.getHeight()/1.3);
        frame.setMinimumSize(new Dimension(width,height));
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
