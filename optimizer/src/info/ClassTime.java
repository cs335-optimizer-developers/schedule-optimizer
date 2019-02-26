package info;

import java.util.ArrayList;

public class ClassTime {
	private String time;
	private ArrayList<Day> days;
	private Quad quad;
	
	public ClassTime(String time, ArrayList<Day> days, Quad quad) {
		this.time = time;
		if(days==null)
			this.days = null;
		else
			this.days = new ArrayList<Day>(days);
		this.quad = quad;
	}
	
	
}