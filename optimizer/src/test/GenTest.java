package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;

import org.junit.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import algorithm.*;
import core.Optimizer;
import core.ReadPopulateCSV;
import core.Source;
import display.FinalDisplay;
import info.Course;
import info.Semester;

public class GenTest {
	
	protected Optimizer opt;
	
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
		reset();
		opt.generate();
	}
	
	@Test
	public void write() {
		reset();
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
	
	@Test
	void allFilesFound() throws IllegalArgumentException, IllegalAccessException, FileNotFoundException {
		for (Field f : Source.class.getFields()) {
			String s = (String) f.get(null);
			File l = new File(s);
			assertTrue(l.isDirectory() || l.isFile());
		}
	}
}