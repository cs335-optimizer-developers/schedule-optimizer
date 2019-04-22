package info;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * Class to handle a list of courses associated with a certain year and
 * semester.
 *
 */
public class Semester {
	
	public int year;
	public String sem;
	private Map<String, Course> courses;
	
	public Semester(int year, String sem) {
		this.year = year;
		this.sem = sem;
		courses = new HashMap<String, Course>();
	}
	
	public Semester(Map<String, Course> courses) {
		this.courses = courses;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public void setSemester(String sem) {
		this.sem = sem;
	}
	
	public void addCourse(Course c) {
		courses.put(c.getCourseKey(), c);
	}
//
//	public void removeCourse(Course c) {
//		courses.remove();
//	}
	
	public Map<String, Course> getCourses() {return courses;}
	
	public boolean isEmpty() {return courses.isEmpty();}
}