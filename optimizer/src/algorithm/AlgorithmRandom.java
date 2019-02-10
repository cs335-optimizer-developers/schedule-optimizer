package algorithm;

import java.util.ArrayList;
import java.util.List;

import reader.ReadPrg;

public class AlgorithmRandom implements Algorithm {

	public String[][] build(String[] programs) {
		
		ReadPrg rp = new ReadPrg();
		
		List<String> toTake = new ArrayList<String>();
		
		for (String prog : programs)
			toTake.addAll(rp.read(prog));
		
		String[][] toReturn = new String[toTake.size()/4+1][4];
		
		for (int i=toTake.size()-1;i>=0;i--) {
			System.out.println(i);
			toReturn[i/4][i%4] = toTake.get(i);
		}
		
		return toReturn;
	}
}