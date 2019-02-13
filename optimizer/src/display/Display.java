package display;

import java.util.*;
import javax.swing.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class Display {

	public static void main(String[] args) {
	Frame f = new Frame();
	Label label = new Label("Display");
	final TextField text = new TextField(20);
	Button b = new Button("Start");
	Button b1 = new Button("Stop");
	Button b2 = new Button("Reset");
    	b.addActionListener(e -> {
			try {
				String strFile = "./output/generated-schedule.csv";
				BufferedReader br = new BufferedReader(new FileReader(strFile));
				String strLine = "";
                StringTokenizer st = null;
                int lineNumber = 0, tokenNumber = 0;
 
                ArrayList<String> arrayList = new ArrayList<String>();
 
                while ((strLine = br.readLine()) != null) {
                    lineNumber++;
 
	                st = new StringTokenizer(strLine, ",");
	                while (st.hasMoreTokens()) {
 
                        tokenNumber++;
                        arrayList.add(st.nextToken());
 
                        tokenNumber = 0;
                    }
	                Object[] elements = arrayList.toArray();
 
                } }catch (Exception e1) {
                        System.out.println("Exception while reading csv file: " + e1);
                }
 
            	//JTable table = new JTable(data, columnNames);
                //JScrollPane scrollPane = new JScrollPane(table);
                JFrame frame = new JFrame();
               // frame.add(scrollPane);
                frame.setVisible(true);
                frame.pack();
                frame.repaint();
            });
            Panel p = new Panel(new GridLayout(6, 6));
            p.add(label);
            p.add(text);
    p.add(b);
    p.add(b1);
    p.add(b2);
    f.add(p);
    f.setVisible(true);
    f.pack();
	}
}