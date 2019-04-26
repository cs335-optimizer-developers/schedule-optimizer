package display;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import core.Optimizer;
import core.ReadPopulateCSV;
import info.ClassType;
import info.Course;
import info.Semester;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.color.*;


/**
 * Class to handle primary display window.
 * 
 * Follows singleton. TODO Should maybe be broken into a few classes for
 * the sake of editing and clarity, especially insofar as there are
 * different components to handle information.
 */
public class FinalDisplay extends JFrame {

	private static final long serialVersionUID = 1L;
	private static FinalDisplay one_display;
	private JPanel contentPane;
	private JTextField majorOne;
	private JTextField minor;
	private JButton btnAdvancedOptions;
	private JTextField majorTwo;
	private JLabel lblEnterMajorsAnd;
	private JLabel lblSearchForClasses;
	private JTextField searchBar;
	private JButton btnEnter;
	private JButton submitButton;
	
	
	
	
	private CSVPanel scheduleDisplay = new CSVPanel();
	private Map<String, Course> cMap;
	
	JTextArea semOneText;
	JTextArea semTwoText;
	JTextArea semThreeText;
	JTextArea semFourText;
	JTextArea semFiveText;
	JTextArea semSixText;
	JTextArea semSevenText;
	JTextArea semEightText;
	
	/**
	 * Individual main class.
	 */
	public static void main(String[] args) {initDisplay();}
	
	/**
	 * Display the frame.
	 */
	public static void initDisplay() {
		EventQueue.invokeLater(() -> {
			try {
				FinalDisplay frame = new FinalDisplay();
				frame.setVisible(true);
				frame.connect();
			} catch (Exception e) {e.printStackTrace();}
		});

	}
	
	/**
	 * Connection of ActionListeners to the buttons.
	 */
	private void connect() {
		//Allows submit to write instances created by Optimizer.
		submitButton.addActionListener(
				e -> displaySchedule(Optimizer.getInstance().write()));
		btnAdvancedOptions.addActionListener(
				e -> scheduleDisplay.update((Optimizer.getInstance().generate())));
		btnEnter.addActionListener(
				e -> {
					try {
						displaySearch();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				});
	}
	
	/**
	 * Display the schedules, each semester has many courses- display each semester's courses in each
	 * 	semester text area
	 * @param s
	 */
	public void displaySchedule(Semester[] s) {
		List<JTextArea> semContainers = new ArrayList<>();
		semContainers.add(semOneText);
		semContainers.add(semTwoText);
		semContainers.add(semThreeText);
		semContainers.add(semFourText);
		semContainers.add(semFiveText);
		semContainers.add(semSixText);
		semContainers.add(semSevenText);
		semContainers.add(semEightText);
		
		for(int i = 0; i < semContainers.size(); i++) {
			JTextArea a = semContainers.get(i);
			a.setText(parseSemester(s[i]));
			final int j = i;
			a.addMouseListener(new MouseListener() {
				@Override
				public void mouseReleased(MouseEvent e) {
					if(s[j] == null) {
						return;
					}
					
			        SectionPanel panel = new SectionPanel(new ArrayList<Course>(s[j].getCourses().values()));
				}

				@Override
				public void mouseEntered(MouseEvent e) {}
				@Override
				public void mouseExited(MouseEvent e) {}
				@Override
				public void mouseClicked(MouseEvent e) {}
				@Override
				public void mousePressed(MouseEvent e) {}
			});
		}
	}
	
    private void displaySemesterPages() {
    		SectionPanel[] semesters = null;
    }
	
	
	
	/**
	 * initiates a new window when the enter button is pressed, and 
	 * displays the information about the course
	 * that was searched for
	 * @throws IOException 
	 */
	public void displaySearch() throws IOException {
		
		String key = searchBar.getText();
		key = key.toUpperCase();
		
		if(!cMap.containsKey(key)) {
			JFrame f = new JFrame("Error");
			f.setBounds(300, 300, 300,100);
			f.setVisible(true);
			JLabel errorLabel = new JLabel();
			errorLabel.setText("Invalid Course. Enter a New Course");
			f.getContentPane().add(errorLabel);
		}else{
			JFrame frame = new JFrame(key);
			JPanel panel = new JPanel();
			frame.setBounds(300, 300, 500, 400);
			frame.setVisible(true);
			List<ClassType> sections = cMap.get(key).getSections();
			String[] columnNames = {"TIME","PROFESSOR","QUAD","SECTION"};
			Object[][] sectionTable = new Object[sections.size()][4];
			for(int i = 0; i < sections.size(); i++) {
				ClassType s = sections.get(i);
				sectionTable[i][0] = s.getTime().replaceAll("\\\\",",");
				sectionTable[i][1] = s.getProf().replaceAll("\\\\",",");
				sectionTable[i][2] = s.getQuad();
				sectionTable[i][3] = (Integer) (((info.Section) s).getSection());
			}
			JTable table = new JTable(sectionTable, columnNames);	
			table.setAutoCreateRowSorter(true);
			table.add(new JLabel("Test"));
			table.setFillsViewportHeight(true);
			JScrollPane ScrollPane = new JScrollPane(table);
			ScrollPane.setPreferredSize(new Dimension(400, 200));   
			ScrollPane.setMinimumSize(new Dimension(30, 30));
			panel.add(ScrollPane);
			frame.getContentPane().add(panel, BorderLayout.CENTER);
			JButton addButton = new JButton("Add Class to Semester");
			frame.getContentPane().add(addButton, BorderLayout.SOUTH);
		}
	}
        
        
        
	
	
	/**
	 * Parse a semester's courses into a single string.
	 * @param s, a semester- if null then return "Empty"
	 * @return, single string derived from parsing a semester's courses.
	 */
	private String parseSemester(Semester s) {
		if(s == null)
			return "Empty";
		List<Course> courses = new ArrayList<Course>(s.getCourses().values());
		String data = "";
		for(int i = 0; i < courses.size(); i++) {
			data += courses.get(i).getName() + "\n";
		}
		return data;
	}
	
	/**
	 * Gate in case display has not been initialized, but parameters
	 * are requested. Mostly for testing purposes.
	 * @return
	 */
	public static DParam requestParameters() {
		if (one_display == null) {
			DParam toReturn = new DParam();
			
			toReturn.addProgram("test-gen-ed");
			toReturn.addProgram("csci-major");
			
			return toReturn;
		}
		return one_display.getParameters();
	}
	
	/**
	 * Contains all parameters to be received from display.
	 * @return parameter object for generation.
	 */
	public DParam getParameters() {
		DParam toReturn = new DParam();
		
		//TODO Check box for if to include gen-eds. Client may choose not to for experimentation.
		boolean wantGenEd = true;
		if (wantGenEd) {
//			System.err.println("test-gen-ed" + " requested");
			toReturn.addProgram("test-gen-ed");
		}
		
		//TODO a slider, higher number referring to higher preference to fill gen-eds early.
		toReturn.setPreferGenEd(1);
		
//		System.err.println(majorOne.getText() + " requested");
		toReturn.addProgram(majorOne.getText());
		
//		System.err.println(majorTwo.getText() + " requested");
		toReturn.addProgram(majorTwo.getText());
		
//		System.err.println(minor.getText() + " requested");
		toReturn.addProgram(minor.getText());
		
		return toReturn;
	}

	/**
	 * Create the frame. Made externally, tf indecipherable.
	 */
	private FinalDisplay() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		lblEnterMajorsAnd = new JLabel("Enter Major(s) and Minor");
		
		majorOne = new JTextField();
		majorOne.setText("math-major");
		majorOne.setColumns(10);
		
		majorTwo = new JTextField();
		majorTwo.setText("csci-major");
		majorTwo.setColumns(10);
		
		minor = new JTextField();
		minor.setText("econ-minor");
		minor.setColumns(10);
		
		lblSearchForClasses = new JLabel("Search For Classes");
		
		searchBar = new JTextField();
		searchBar.setText("COMM 101");
		searchBar.setColumns(10);
		
		
		/*
		 * Event handler for when search button is pressed
		 * TODO do not forget to implement
		 * 
		 */
		btnEnter = new JButton("Enter");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		cMap = ReadPopulateCSV.getMap();
		
		submitButton = new JButton("Write");
		
		btnAdvancedOptions = new JButton("Generate CSV");
		
		semOneText = new JTextArea();
		semOneText.setEditable(false);
		semTwoText = new JTextArea();
		semTwoText.setEditable(false);
		semThreeText = new JTextArea();
		semThreeText.setEditable(false);
		semFourText = new JTextArea();
		semFourText.setEditable(false);
		semFiveText = new JTextArea();
		semFiveText.setEditable(false);
		semSixText = new JTextArea();
		semSixText.setEditable(false);
		semSevenText = new JTextArea();
		semSevenText.setEditable(false);
		semEightText = new JTextArea();
		semEightText.setEditable(false);
		
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
							.addComponent(majorTwo, GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
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
											.addComponent(semThreeText, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
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
						.addComponent(majorTwo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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
						.addComponent(semThreeText, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
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
		
		
		//this.getContentPane().setLayout(new FlowLayout());
		
		JLabel label = new JLabel();
		
		label.setOpaque(true);
		
		label.setBackground(Color.GREEN);
		
		add(label);
		
	}
	
	public static FinalDisplay getInstance() {
		if (one_display == null)
			one_display = new FinalDisplay();
		return one_display;
	}
	
	
	/*public SetBackgroundColor() {
		
		this.getContentPane().setLayout(new FlowLayout());
		
		JLabel label = new JLabel();
		
		label.setOpaque(true);
		
		label.setBackground(Color.BLUE);
		
	}*/
}
