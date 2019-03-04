package algorithm;

import java.util.ArrayList;
import java.util.List;

import info.Semester;
import reader.ReadPrg;

public class AlgArbitrary implements Algorithm {

	int year = 2019;
	String sem = "fall";
	
	public Semester[] build(Semester[] classes, String[] programs) {
		
		ReadPrg rp = new ReadPrg();
		
		List<String> toTake = new ArrayList<String>();
		
		for (String prog : programs)
			toTake.addAll(rp.read(prog));
		
		Semester[] toReturn = new Semester[toTake.size()/4+1];
		
		while (!toTake.isEmpty()) {
			Semester current = new Semester(year,sem);
			
			for (int i=toTake.size()-1;i>=0;i--)
				current.addCourse(toTake.get(i));
		
			incSem();
		}
		
		return toReturn;
	}
	
	private void incSem(String sem) {
		if (sem == "spring")
			sem = "fall";
		else {
			year++;
			sem = "fall";
		}
	}
}