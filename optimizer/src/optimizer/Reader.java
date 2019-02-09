package optimizer;

import info.*;

public class Reader {
	
	private String source;
	
	public final CourseDetails[] curriculum;
	public final Course[] classes;

	public Reader(String source) {
		this.source = source;
		
		curriculum = null;
		classes = null;
		
	}
}