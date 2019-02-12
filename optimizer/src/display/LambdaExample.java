package display;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LambdaExample {
	
	private static Button b1;
	private static Button b2;
	
	/*
	 * Demonstration of using lambda function to shorten ActionListener
	 * writing, as well as some basic Swing display functionality. Let me
	 * know if any of it doesn't make sense.
	 */
	public static void main(String[] args) {
		Frame f = new Frame();
		Label label = new Label("Display");
		final TextField text = new TextField(20);
		exampleA();
		exampleB();
		Panel p = new Panel(new GridLayout(6, 6));
        p.add(label);
        p.add(text);
        p.add(label);
        p.add(text);
		p.add(b1);
		p.add(b2);
		f.add(p);
		f.setVisible(true);
		f.pack();
	}
	
	private static void exampleA() {
		b1 = new Button("Test");
		b1.addActionListener(e -> {
			System.out.println("Button 1 pressed");
		});
	}
	
	private static void exampleB() {
		b2 = new Button("Test2");
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Button 2 pressed");				
			}
		});
	}
}
