package reader;

import java.util.ArrayList;

public abstract class Reader {
	
	private String source;
	
	public Reader(String source) {
		this.source = source;
	}

	public ArrayList<String> readCurriculum(String program) {
		return null;
	}
	
}