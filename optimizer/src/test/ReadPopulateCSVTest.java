package test;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

import core.ReadPopulateCSV;
import info.Course;
import info.Semester;

/**
 * 
 * @author James White
 *
 */
class ReadPopulateCSVTest {

	private static Semester[] semesters = ReadPopulateCSV.buildSemesters();
	
	@Test
	public void nullSemester() {
		for (Semester s : semesters)
			assertTrue(s != null);
	}
	
	@Test
	public void nullCourse() {
		for (Semester s : semesters)
			if (s != null)
				for (Course c : s.getCourses())
					assertTrue(c != null);
	}
}
