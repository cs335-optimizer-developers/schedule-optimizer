package info;

import java.util.ArrayList;
import java.util.List;

public class Semester {
	
	private int year;
	private String sem;
	private List<Course> courses;
	
	public Semester(int year, String sem) {
		this.year = year;
		this.sem = sem;
	}
	
	public Semester(List<Course> courses) {
		courses = new ArrayList<Course>(courses);
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public void setSemester(String sem) {
		this.sem = sem;
	}
	
	public void addCourse(Course c) {courses.add(c);}

	public void removeCourse(Course c) {courses.remove(c);}
}