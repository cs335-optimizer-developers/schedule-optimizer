package display;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;

public class FinalDisplay extends JFrame {

	private JPanel contentPane;
	private JTextField majorOne;
	private JTextField majorTwo;
	private JTextField minor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FinalDisplay frame = new FinalDisplay();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FinalDisplay() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		majorOne = new JTextField();
		majorOne.setText("Enter Major 1");
		GridBagConstraints gbc_majorOne = new GridBagConstraints();
		gbc_majorOne.insets = new Insets(0, 0, 5, 5);
		gbc_majorOne.gridx = 0;
		gbc_majorOne.gridy = 1;
		contentPane.add(majorOne, gbc_majorOne);
		majorOne.setColumns(10);
		
		majorTwo = new JTextField();
		majorTwo.setText("Enter Major 2");
		GridBagConstraints gbc_majorTwo = new GridBagConstraints();
		gbc_majorTwo.insets = new Insets(0, 0, 5, 5);
		gbc_majorTwo.gridx = 1;
		gbc_majorTwo.gridy = 1;
		contentPane.add(majorTwo, gbc_majorTwo);
		majorTwo.setColumns(10);
		
		minor = new JTextField();
		minor.setText("Enter Minor");
		GridBagConstraints gbc_minor = new GridBagConstraints();
		gbc_minor.insets = new Insets(0, 0, 5, 0);
		gbc_minor.fill = GridBagConstraints.HORIZONTAL;
		gbc_minor.gridx = 2;
		gbc_minor.gridy = 1;
		contentPane.add(minor, gbc_minor);
		minor.setColumns(10);
		
		JButton submitButton = new JButton("Submit");
		GridBagConstraints gbc_submitButton = new GridBagConstraints();
		gbc_submitButton.insets = new Insets(0, 0, 0, 5);
		gbc_submitButton.gridx = 1;
		gbc_submitButton.gridy = 2;
		contentPane.add(submitButton, gbc_submitButton);
	}

}
