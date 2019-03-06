package info;

import java.util.ArrayList;
import java.util.List;

public class ClassTime {
	@SuppressWarnings("unused")
	private String time;
	@SuppressWarnings("unused")
	private List<Day> days;
	@SuppressWarnings("unused")
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