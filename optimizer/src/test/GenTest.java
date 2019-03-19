package test;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import algorithm.AlgMatch;
import core.Optimizer;
import core.ReadPopulateCSV;
import display.FinalDisplay;
import info.Course;
import info.Semester;

public class GenTest {
	
	protected Optimizer opt;
	
	@BeforeEach
	protected void reset() {
		opt = Optimizer.newInstance();
		opt.setAlgorithm(new AlgMatch());
	}
	
	@Test
	public void displayBoot() {
		FinalDisplay.getInstance();
	}
	
	@Test
	public void generate() {
		opt.generate();
	}
	
	@Test
	public void write() {
		opt.write();
	}
	
	@Test
	public void populateCSVTest() {
		Semester[] semesters = ReadPopulateCSV.buildSemesters();
		
		for (Semester s : semesters)
			assertNotNull(s);

		for (Semester s : semesters)
			if (s != null)
				for (Course c : s.getCourses())
					assertNotNull(c);
	}
}