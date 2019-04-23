package display;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import info.ClassType;
import info.Course;

public class SectionPanel extends JPanel {

	private JPanel cards = new JPanel(new CardLayout());
	List<Course> courses;

	public SectionPanel(List<Course> courses) {
		if(courses == null)
			return;
		this.courses = courses;
		create();
	}
	
	class SingularPanel extends JPanel {
		Course c;
		SingularPanel(Course c) {
			this.c = c;
			this.setPreferredSize(new Dimension(320, 240));
			this.add(new JLabel(c.getName()));
		}
	}

	private void create() {
		JFrame f = new JFrame();
		JScrollPane scrollPanel = new JScrollPane();
		f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		for (Course c : courses) {
			System.out.println(c.getName());
			SingularPanel panel = new SingularPanel(c);
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
			table.add(new JLabel("Test"));
			JScrollPane scrollPane = new JScrollPane(table);
			table.setFillsViewportHeight(true);
			scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane.setPreferredSize(new Dimension(400, 200));
			scrollPane.setMinimumSize(new Dimension(30, 30));
			panel.add(scrollPane);
			scrollPanel.setViewportView(panel);
			scrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			cards.add(panel, c.getName());
		}
		
		JPanel control = new JPanel();
		control.add(new JButton(new AbstractAction("\u22b2Prev") {

			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) cards.getLayout();
				cl.previous(cards);
			}
		}));
		control.add(new JButton(new AbstractAction("Next\u22b3") {

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
        f.setSize(600,300);
	}
}
