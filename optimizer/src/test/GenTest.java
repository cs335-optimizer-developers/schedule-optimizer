package test;

import static org.junit.Assert.assertNotNull;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import algorithm.AlgMatch;
import core.Optimizer;
import core.ReadPopulateCSV;
import core.Source;
import display.FinalDisplay;
import info.Course;
import info.Semester;

/**
 * 
 * Primary test, ensuring that primary functionality is unbroken,
 * as well as housing errorLogRedirect() to be used in RunOptimizer
 * when debug is not active.
 * 
 * Tests focus especially on runtimes, showing how fast each core 
 * functionality is.
 *
 */
public class GenTest {
	
	protected Optimizer opt = Optimizer.newInstance();
	
	@BeforeAll
	public static void errorLogRedirect() {
		FileOutputStream f;
		try {
			f = new FileOutputStream(Source.error_log);
			System.setErr(new PrintStream(f));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		System.err.println("**Start of Error Log**");
	}
	
	@AfterAll
	public static void finishLog() {
		System.err.print("**End of Error Log**");
	}
	
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