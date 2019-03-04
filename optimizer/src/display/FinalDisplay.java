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
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class FinalDisplay extends JFrame {

	private JPanel contentPane;
	private JTextField majorOne;
	private JTextField minor;
	private JButton btnAdvancedOptions;
	private JTextField txtEnterMajor;
	private JLabel lblEnterMajorsAnd;
	private JLabel lblSearchForClasses;
	private JTextField textField;
	private JButton btnEnter;

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
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		lblEnterMajorsAnd = new JLabel("Enter Major(s) and Minor");
		
		majorOne = new JTextField();
		majorOne.setText("Enter Major 1");
		majorOne.setColumns(10);
		
		txtEnterMajor = new JTextField();
		txtEnterMajor.setText("Enter Major 2");
		txtEnterMajor.setColumns(10);
		
		minor = new JTextField();
		minor.setText("Enter Minor");
		minor.setColumns(10);
		
		JButton submitButton = new JButton("Submit");
		
		lblSearchForClasses = new JLabel("Search For Classes");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		btnEnter = new JButton("Enter");
		
		btnAdvancedOptions = new JButton("Advanced Options");
		
		JTextArea textArea = new JTextArea();
		
		JTextArea textArea_1 = new JTextArea();
		
		JTextArea textArea_2 = new JTextArea();
		
		JTextArea textArea_3 = new JTextArea();
		
		JTextArea textArea_4 = new JTextArea();
		
		JTextArea textArea_5 = new JTextArea();
		
		JTextArea textArea_6 = new JTextArea();
		
		JTextArea textArea_7 = new JTextArea();
		
		JLabel lblSemester = new JLabel("Semester 1");
		
		JLabel lblSemester_1 = new JLabel("Semester 2");
		
		JLabel lblSemester_2 = new JLabel("Semester 3");
		
		JLabel lblSemester_3 = new JLabel("Semester 4");
		
		JLabel lblSemester_4 = new JLabel("Semester 5");
		
		JLabel lblSemester_5 = new JLabel("Semester 6");
		
		JLabel lblSemester_6 = new JLabel("Semester 7");
		
		JLabel lblSemester_7 = new JLabel("Semester 8");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(214)
							.addComponent(lblEnterMajorsAnd))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(majorOne, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addComponent(txtEnterMajor, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addComponent(minor, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(246)
							.addComponent(submitButton))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(184)
							.addComponent(btnAdvancedOptions, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(textArea, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSearchForClasses, Alignment.TRAILING)
								.addComponent(textArea_4, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(6)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(textArea_5, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(textArea_6, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(textArea_7, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(textArea_1, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(textArea_2, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(textArea_3, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE))))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(36)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblSemester_1, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(lblSemester_2, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
										.addComponent(textField, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE))
									.addGap(60)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(btnEnter)
										.addComponent(lblSemester_3, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(48)
							.addComponent(lblSemester)))
					.addContainerGap(1, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(47)
					.addComponent(lblSemester_4, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
					.addGap(73)
					.addComponent(lblSemester_5, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
					.addGap(74)
					.addComponent(lblSemester_6, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
					.addComponent(lblSemester_7, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
					.addGap(53))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(3)
					.addComponent(lblEnterMajorsAnd)
					.addGap(12)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(majorOne, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtEnterMajor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(minor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
					.addComponent(submitButton)
					.addGap(5)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(6)
							.addComponent(lblSearchForClasses))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(1)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnEnter))
					.addGap(38)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSemester)
						.addComponent(lblSemester_1)
						.addComponent(lblSemester_2)
						.addComponent(lblSemester_3))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
						.addComponent(textArea_1, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
						.addComponent(textArea_2, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
						.addComponent(textArea_3, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSemester_4)
						.addComponent(lblSemester_5)
						.addComponent(lblSemester_6)
						.addComponent(lblSemester_7))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textArea_4, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
						.addComponent(textArea_5, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
						.addComponent(textArea_6, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
						.addComponent(textArea_7, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
					.addGap(135)
					.addComponent(btnAdvancedOptions))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
