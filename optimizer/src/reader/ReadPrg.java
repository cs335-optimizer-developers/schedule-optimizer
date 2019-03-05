package reader;

import java.util.Set;

public class ReadPrg extends Reader {

	private String source = "./optimizer/input/programs/";
	
	public Set<String> read(String prog) {
				
		input = makeStream(source + prog + ".prg");
		Set<String> toReturn = new java.util.HashSet<>();
		
		String prgName = "";
		while (moveLine()) {
			if (hasChar('+'))
				prgName = line.substring(1);
			else
				toReturn.add(prgName+line);
		}
		return toReturn;
	}
}