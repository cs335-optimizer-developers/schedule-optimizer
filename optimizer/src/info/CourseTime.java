package info;

import java.util.ArrayList;

public class CourseTime {
	private String time;
	private ArrayList<Day> days;
	private Quad quad;
	
	public CourseTime(String time, ArrayList<Day> days, Quad quad) {
		this.time = time;
		if(days==null)
			this.days = null;
		else
			this.days = new ArrayList<Day>(days);
		this.quad = quad;
	}
	
	
}