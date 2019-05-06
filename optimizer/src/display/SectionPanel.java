package display;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import info.ClassType;
import info.Course;

public class SectionPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel cards;
	List<Course> courses;

	public SectionPanel(List<Course> courses) {
		if(courses == null)
			return;
		this.courses = courses;
		create();
	}
	
	// Each page of the semester view
	class Page extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		Course c;
		Page(Course c, int num) {
			this.c = c;
			JLabel name = new JLabel(c.getName());
			this.add(name);
			Font f = name.getFont();
			name.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
			add(new JLabel("<html>[" + num + "/" + courses.size() + "]<br/>" +
					"<b>Credits</b>: " + c.getCredits() +"<br/>" +
					prereqToString(c) + "</html>"));
			
		}
	}

	// Create a list of pages
	private void create() {
		// No courses are in the semester
		if(courses == null || courses.size() == 0)
			return;
		cards = new JPanel(new CardLayout());
		JFrame f = new JFrame();
		JScrollPane scrollPanel = new JScrollPane();
		f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		int pageNum = 1;
		for (Course c : courses) {
			Page panel = new Page(c, pageNum++);
			String[] columnNames = { "TIME", "PROFESSOR", "QUAD", "SECTION" };

			List<ClassType> sections = c.getSections();
			Object[][] sectionTable = new Object[sections.size()][4];
			for (int j = 0; j < sections.size(); j++) {
				ClassType s = sections.get(j);
				sectionTable[j][0] = s.getTime().replaceAll("\\\\", ",");
				sectionTable[j][1] = s.getProf().replaceAll("\\\\", ",");
				sectionTable[j][2] = s.getQuad();
				sectionTable[j][3] = (Integer) (((info.Section) s).getSection());
			}

			JTable table = new JTable(sectionTable, columnNames);
			table.setAutoCreateRowSorter(true);
			
			JScrollPane scrollPane = new JScrollPane(table);
			table.setFillsViewportHeight(true);
			scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane.setPreferredSize(new Dimension(400, 200));
			scrollPane.setMinimumSize(new Dimension(30, 30));
			panel.add(scrollPane);
			scrollPanel.setViewportView(panel);
			scrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			JScrollPane scrollPane2 = new JScrollPane();
			scrollPane2.setPreferredSize(new Dimension(450,100));
			JTextArea desc = new JTextArea(c.getDescription());
			desc.setLineWrap(true);
			desc.setEditable(false);
			desc.setWrapStyleWord(true);
			scrollPane2.setViewportView(desc);
			panel.add(scrollPane2);
			cards.add(panel, c.getName());
		}
		
		JPanel control = new JPanel();
		control.add(new JButton(new AbstractAction("Prev") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) cards.getLayout();
				cl.previous(cards);
			}
		}));

		control.add(new JButton(new AbstractAction("Next") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) cards.getLayout();
				cl.next(cards);
			}
		}));
		
		f.add(cards, BorderLayout.CENTER);
		f.add(control, BorderLayout.SOUTH);
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
        f.setSize(500,425);
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
}
