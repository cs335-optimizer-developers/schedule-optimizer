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
	
	private String daysToString() {
		String s = "";
		for(int i = 0; i < days.size(); i++) {
			s += days.get(i);
		}
		return s;
	}
	
	public String getTime() {
		return time + " [" + daysToString() + "]";
	}

	public String getQuad() {
		return quad + "";
	}
	
}