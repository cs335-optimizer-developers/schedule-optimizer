package reader;

import java.util.HashSet;
import java.util.Set;

public class ReadPrg extends Reader {

	public Set<String> read(String prog) {
		
		input = makeStream("programs/" + prog + ".prg");
		Set<String> toReturn = new HashSet<String>();
		
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