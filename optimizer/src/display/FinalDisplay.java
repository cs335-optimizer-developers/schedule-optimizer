package display;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import info.ClassType;
import info.Course;
import info.Semester;


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
	private JLabel errMessage;
	
	private Map<String, Course> cMap;
	
	List<JTextArea> semContainers;
	
	/**
	 * Display the frame.
	 */
	public static void initDisplay() {
		EventQueue.invokeLater(() -> {
			try {
				FinalDisplay frame = new FinalDisplay();
				frame.setVisible(true);
				frame.connect();
				one_display = frame;
			} catch (Exception e) {e.printStackTrace();}
		});

	}
	
	/**
	 * Connection of ActionListeners to the buttons.
	 */
	private void connect() {
		//Allows submit to write instances created by Optimizer.
		submitButton.addActionListener(
				e -> displaySchedule(Optimizer.getInstance().write(), null));
		btnAdvancedOptions.addActionListener(
				e -> Optimizer.getInstance().generate());
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
	
	public void addCourse(Semester[] s, Course c) {
		if(c!=null) {
			int minIndex = 0;
			int minCredits = 150;
			for(int i = 0; i < s.length; i++) {
				List<Course> courses = new ArrayList<Course>(s[i].getCourses().values());
				int credits = 0;
				for(int j = 0; j < courses.size(); j++) {
					credits += courses.get(j).getCredits();
				}
				if(credits < minCredits) {
					minIndex = i;
					minCredits = credits;
				}
			}
			s[minIndex].addCourse(c);
			JTextArea a = semContainers.get(minIndex);
			a.setText(parseSemester(s[minIndex]));
		}
	}
	
	/**
	 * Display the schedules, each semester has many courses- display each semester's courses in each
	 * 	semester text area
	 * @param s, an array of Semesters. Course c a course to be added.
	 */
	public void displaySchedule(Semester[] s, Course c) {
		
		for(int i = 0; i < semContainers.size(); i++) {
			JTextArea a = semContainers.get(i);
			
			// Delete old action listeners
			for(MouseListener ml : a.getMouseListeners()) {
				a.removeMouseListener(ml);
			}
			
			a.setText(parseSemester(s[i]));
			final int j = i;
			a.addMouseListener(new MouseListener() {
				@Override
				public void mouseReleased(MouseEvent e) {
					if(s[j] == null) {
						return;
					}
					
			        @SuppressWarnings("unused")
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
	
	/**
	 * initiates a new window when the enter button is pressed, and 
	 * displays the information about the course
	 * that was searched for
	 * @throws IOException 
	 */
	public void displaySearch() throws IOException {
		
		String key = searchBar.getText();
		key = key.toUpperCase();
		key = key.replaceAll(" ", "");
		
		if(!cMap.containsKey(key)) {
			JTextArea textArea = new JTextArea(50, 10);
			PrintStream printStream = new PrintStream(new CustomOuputStream(textArea));
			System.setErr(printStream);
			errMessage.setText("Enter valid course");
		}
		else {
			JFrame frame = new JFrame(key);
			JPanel panel = new JPanel();
			frame.setBounds(300, 300, 500, 400);
			frame.setVisible(true);
			Course c = cMap.get(key);
			List<ClassType> sections = c.getSections();
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
			table.setFillsViewportHeight(true);
			JScrollPane ScrollPane = new JScrollPane(table);
			ScrollPane.setPreferredSize(new Dimension(400, 200));   
			panel.add(ScrollPane);
			panel.add(new JLabel("<html><b>Credits</b>: " + c.getCredits() +"<br/>" +
					prereqToString(c) + "</html>"));
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setPreferredSize(new Dimension(450,100));
			JTextArea desc = new JTextArea(c.getDescription());
			desc.setLineWrap(true);
			desc.setEditable(false);
			desc.setWrapStyleWord(true);
			scrollPane.setViewportView(desc);
			panel.add(scrollPane);
			frame.getContentPane().add(panel, BorderLayout.CENTER);
			JButton addButton = new JButton("Add Class to Semester");
			addButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					addCourse(Optimizer.getInstance().getSemesters(), c);
					frame.dispose();
				}
				
			});
			
			frame.getContentPane().add(addButton, BorderLayout.SOUTH);
		}
	}
	
	private String prereqToString(Course c) {
		if(c.getPrerequisites().size() == 0)
			return "";
		String ret = "<b>Prerequisites</b>: [";
		int i = 0;
		for(Course p : c.getPrerequisites()) {
			if(i+1 == c.getPrerequisites().size()) {
				ret += p.getName();
			}
			else
				ret += p.getName() + ",";
			i++;
		}
		ret += "]";
		return ret;
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
			
			// Dummy input to keep from generating all the time without init.
			toReturn.addProgram("gen-ed");
			toReturn.addProgram("csci-major");
			toReturn.addProgram("econ-major");
			
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
//			System.err.println("gen-ed" + " requested");
			toReturn.addProgram("gen-ed");
		}
		
		//TODO a slider, higher number referring to higher preference to fill gen-eds early.
		toReturn.setPreferGenEd(1);
		
//		System.out.println(majorOne.getText() + " requested");
		toReturn.addProgram(majorOne.getText());
		
//		System.out.println(majorTwo.getText() + " requested");
		toReturn.addProgram(majorTwo.getText());
		
//		System.out.println(minor.getText() + " requested");
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
		
		errMessage = new JLabel();
		errMessage.setForeground(Color.RED);
		
		lblEnterMajorsAnd = new JLabel("Enter Major(s) and Minor");
		
		majorOne = new JTextField();
		majorOne.setText("math-major");
		majorOne.setColumns(10);
		
		majorTwo = new JTextField();
		majorTwo.setText("csci-major");
		majorTwo.setColumns(10);
		
		minor = new JTextField();
		minor.setText("econ-major");
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
		
		cMap = Optimizer.generateMap();
		
		submitButton = new JButton("Write");
		
		btnAdvancedOptions = new JButton("Generate CSV");
		
		JTextArea semOneText = new JTextArea();
		semOneText.setEditable(false);
		JTextArea semTwoText = new JTextArea();
		semTwoText.setEditable(false);
		JTextArea semThreeText = new JTextArea();
		semThreeText.setEditable(false);
		JTextArea semFourText = new JTextArea();
		semFourText.setEditable(false);
		JTextArea semFiveText = new JTextArea();
		semFiveText.setEditable(false);
		JTextArea semSixText = new JTextArea();
		semSixText.setEditable(false);
		JTextArea semSevenText = new JTextArea();
		semSevenText.setEditable(false);
		JTextArea semEightText = new JTextArea();
		semEightText.setEditable(false);
		
		semContainers = new ArrayList<>();
		semContainers.add(semOneText);
		semContainers.add(semTwoText);
		semContainers.add(semThreeText);
		semContainers.add(semFourText);
		semContainers.add(semFiveText);
		semContainers.add(semSixText);
		semContainers.add(semSevenText);
		semContainers.add(semEightText);
		
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
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
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
							.addComponent(submitButton, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
							.addGap(255))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(semOneText, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(21)
									.addComponent(lblSearchForClasses, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addComponent(semFiveText, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE))
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
											.addComponent(lblSemester_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addGap(75)
											.addComponent(lblSemester_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
							.addGap(472))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(184)
							.addComponent(btnAdvancedOptions, GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
							.addGap(192)))
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
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(196)
					.addComponent(errMessage, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(201, Short.MAX_VALUE))
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
					.addGap(47)
					.addComponent(errMessage)
					.addGap(56)
					.addComponent(btnAdvancedOptions))
		);
		contentPane.setLayout(gl_contentPane);
		
		
		//this.getContentPane().setLayout(new FlowLayout());
		
		JLabel label = new JLabel();
		
		label.setOpaque(true);
		
		label.setBackground(Color.GREEN);
		
		getContentPane().add(label);
		
	}
	
	public static FinalDisplay getInstance() {
		if (one_display == null)
			one_display = new FinalDisplay();
		return one_display;
	}
}
