package reader;

import java.util.Map;

public class ReadDet extends Reader {

	private String source = "details/";
	
	public Map<String,String> read() {
		
		input = makeStream(source + "csci.det");
		Map<String,String> toReturn = new java.util.HashMap<>();
		
		String classNum = "XXX";
		while (moveLine()) {
			if (hasChar('+'))
				classNum = line.substring(1);
			else
				toReturn.put("CSCI"+classNum,line);
		}
		return toReturn;
	}
}