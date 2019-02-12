package display;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;

/**
 * A collapsible panel advanced options menu
 * 
 * @author Christian Cameron
 *
 */
public class CollapsiblePanel extends JPanel {

	String title = "Advanced Options";
	TitledBorder border;
	boolean visible = true;

	public CollapsiblePanel() {
		border = BorderFactory.createTitledBorder(title);
		setBorder(border);
		BorderLayout borderLayout = new BorderLayout();
		setLayout(borderLayout);
		add(new JToggleButton("Foreign Language"));
	}

	/**
	 * Toggle the visibility of the panel
	 */
	protected void toggleVisibility() {
		visible = !visible;
		for (Component c : getComponents()) {
			c.setVisible(visible);
		}
		if(!visible)
			setBorder(null);
		else
			setBorder(border);
	}
}
