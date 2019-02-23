package info;

import java.util.ArrayList;

public class Semester {
	
	public final int year;
	public final String sem;
	private ArrayList<Class> courses;
	
	public Semester(int year, String sem) {
		this.year = year;
		this.sem = sem;
	}
	
	public void addCourse(Class c) {courses.add(c);}

	public void removeCourse(Class c) {courses.remove(c);}
}