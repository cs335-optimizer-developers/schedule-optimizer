package display;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import core.Source;
import info.Semester;

/**
 * 
 * Displays a csv as a pop-up. Will probably be outmoded as the display
 * becomes interactive.
 *
 */
public class CSVPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public void update(Semester[] schedule) {
		// Create and set up the window.
        JFrame frame = new JFrame("Generated_Schedule");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create and set up the content pane.
        CSVPanel newContentPane = new CSVPanel();
        frame.setContentPane(newContentPane);
        
        // Display the window.
        frame.pack();
        frame.setVisible(true);
	}

	private JTable table;
	
    public CSVPanel() {
        super(new BorderLayout(3, 3));
        
        table = new JTable(new Model());
        table.setPreferredScrollableViewportSize(new Dimension(600, 60));
        table.setFillsViewportHeight(true);
        
        JPanel ButtonOpen = new JPanel(new FlowLayout(FlowLayout.CENTER));
        add(ButtonOpen, BorderLayout.SOUTH);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        setBorder(new EmptyBorder(3,3,3,3));
        CSVFile Rd = new CSVFile();
        Model NewModel = new Model();
        
        table.setModel(NewModel);
        File DataFile = new File(Source.generated_schedule);
        ArrayList<String[]> Rs2 = Rd.ReadCSVfile(DataFile);
        NewModel.AddCSVData(Rs2);
    }

    public class CSVFile {
        private final ArrayList<String[]> Rs = new ArrayList<String[]>();
        private String[] OneRow;

        public ArrayList<String[]> ReadCSVfile(File DataFile) {
            try {
                BufferedReader brd = new BufferedReader(new FileReader(DataFile));
                
                String st = brd.readLine();
                while (st != null) {
                    OneRow = st.split(",");
                    Rs.add(OneRow);
                    st = brd.readLine();
                }
                brd.close();
            }
            catch (Exception e) {
                String errmsg = e.getMessage();
                System.out.println(errmsg);
            }
            return Rs;
        }
    }

    private class Model extends AbstractTableModel {
		private static final long serialVersionUID = 1L;
		private final String[] columnNames = 
			{"1","2","3",};
        private ArrayList<String[]> Data = new ArrayList<String[]>();

        public void AddCSVData(ArrayList<String[]> DataIn) {
            this.Data = DataIn;
            this.fireTableDataChanged();
        }

        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return Data.size();
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            return Data.get(row)[col];
        }
    }
}