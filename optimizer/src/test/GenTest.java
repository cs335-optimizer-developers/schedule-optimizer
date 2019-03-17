package test;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

import algorithm.*;
import core.Optimizer;
import core.ReadPopulateCSV;
import info.Course;
import info.Semester;

class GenTest {
	
	private Optimizer opt;
	
	private void reset() {
		opt = Optimizer.newInstance();
		opt.setAlgorithm(new AlgMatch());
	}
	
	@Test
	void write() {
		reset();
		opt.write();
	}
	
	@Test
	void generate() {
		reset();
		opt.generate();
	}
	
	@Test
	void writeGenerate() {
		reset();
		opt.write();
		opt.generate();
	}
	
	@Test
	void generateWrite() {
		reset();
		opt.generate();
		opt.write();
	}

	@Test
	void populateCSVTest() {
		Semester[] semesters = ReadPopulateCSV.buildSemesters();
		
		for (Semester s : semesters)
			assertNotNull(s);

		for (Semester s : semesters)
			if (s != null)
				for (Course c : s.getCourses())
					assertNotNull(c);
	}
}
