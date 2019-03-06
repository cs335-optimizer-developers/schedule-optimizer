package display;

import java.awt.EventQueue;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import core.Optimizer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FinalDisplay extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField majorOne;
	private JTextField minor;
	private JButton btnAdvancedOptions;
	private JTextField txtEnterMajor;
	private JLabel lblEnterMajorsAnd;
	private JLabel lblSearchForClasses;
	private JTextField searchBar;
	private JButton btnEnter;
	JButton submitButton;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {initDisplay();}
	
	/**
	 * Display the frame.
	 */
	public static void initDisplay() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FinalDisplay frame = new FinalDisplay();
					frame.setVisible(true);
					frame.connect();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Connection of ActionListeners to the buttons.
	 */
	private void connect() {
		//Allows submit to write instances created by Optimizer.
		submitButton.addActionListener(
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						Optimizer.getInstance().write();
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
		
		lblSearchForClasses = new JLabel("Search For Classes");
		
		searchBar = new JTextField();
		searchBar.setColumns(10);
		
		
		/*
		 * Event handler for when search button is pressed
		 * do not forget to implement
		 * 
		 */
		btnEnter = new JButton("Enter");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		submitButton = new JButton("Write");
		
		btnAdvancedOptions = new JButton("Advanced Options");
		
		JTextArea semOneText = new JTextArea();
		
		JTextArea semTwoText = new JTextArea();
		
		JTextArea SemThreeText = new JTextArea();
		
		JTextArea semFourText = new JTextArea();
		
		JTextArea semFiveText = new JTextArea();
		
		JTextArea semSixText = new JTextArea();
		
		JTextArea semSevenText = new JTextArea();
		
		JTextArea semEightText = new JTextArea();
		
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
							.addComponent(lblEnterMajorsAnd, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(222))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(majorOne, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
							.addGap(5)
							.addComponent(txtEnterMajor, GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
							.addGap(5)
							.addComponent(minor, GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(246)
							.addComponent(submitButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(255))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(184)
							.addComponent(btnAdvancedOptions, GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
							.addGap(192))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(semOneText, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
									.addGap(21)
									.addComponent(lblSearchForClasses, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addComponent(semFiveText, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(6)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(semSixText, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(semSevenText, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(semEightText, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(semTwoText, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(SemThreeText, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(semFourText, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE))))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(36)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblSemester_1, GroupLayout.PREFERRED_SIZE, 35, Short.MAX_VALUE)
											.addGap(75)
											.addComponent(lblSemester_2, GroupLayout.PREFERRED_SIZE, 35, Short.MAX_VALUE))
										.addComponent(searchBar, GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE))
									.addGap(60)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(btnEnter, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(7)
											.addComponent(lblSemester_3, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)))
									.addGap(47)))
							.addGap(9))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(48)
							.addComponent(lblSemester, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(472)))
					.addGap(1))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(47)
					.addComponent(lblSemester_4, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
					.addGap(73)
					.addComponent(lblSemester_5, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
					.addGap(74)
					.addComponent(lblSemester_6, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
					.addGap(67)
					.addComponent(lblSemester_7, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
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
							.addComponent(searchBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnEnter))
					.addGap(38)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSemester)
						.addComponent(lblSemester_1)
						.addComponent(lblSemester_2)
						.addComponent(lblSemester_3))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(semOneText, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
						.addComponent(semTwoText, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
						.addComponent(SemThreeText, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
						.addComponent(semFourText, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSemester_4)
						.addComponent(lblSemester_5)
						.addComponent(lblSemester_6)
						.addComponent(lblSemester_7))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(semFiveText, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
						.addComponent(semSixText, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
						.addComponent(semSevenText, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
						.addComponent(semEightText, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
					.addGap(135)
					.addComponent(btnAdvancedOptions))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
