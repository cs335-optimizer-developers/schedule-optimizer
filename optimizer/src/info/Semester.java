package info;

import java.util.ArrayList;

public class Semester {
	
	public final int year;
	public final String sem;
	private ArrayList<Course> courses;
	
	public Semester(int year, String sem) {
		this.year = year;
		this.sem = sem;
	}
	
	public void addCourse(Course c) {courses.add(c);}

	public void removeCourse(Course c) {courses.remove(c);}
}