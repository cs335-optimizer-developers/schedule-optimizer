package info;

import java.util.ArrayList;
import java.util.List;

public class ClassTime {
	private String time;
	private List<Day> days;
	private Quad quad;
	
	public ClassTime(String time, List<Day> days, Quad quad) {
		this.time = time;
		if(days==null)
			this.days = null;
		else
			this.days = new ArrayList<Day>(days);
		this.quad = quad;
	}
	
	
}