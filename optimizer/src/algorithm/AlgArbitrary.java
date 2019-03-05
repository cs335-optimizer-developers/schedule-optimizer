package algorithm;

import java.util.HashSet;
import java.util.Set;

import info.Course;
import info.Semester;
import reader.ReadPrg;

public class AlgArbitrary implements Algorithm {

	int year = 2019;
	String sem = "fall";
	
	public Semester[] build(Semester[] classes, String[] programs) {
		
		ReadPrg rp = new ReadPrg();
		
		Set<String> toTake = new HashSet<String>();
		
		for (String prog : programs)
			toTake.addAll(rp.read(prog));
		
		Semester[] toReturn = new Semester[toTake.size()/4+1];
		
		int j = 0;
		int i = 0;
		for (String c : toTake) {
			Semester current = new Semester(year,sem);

			Course toAdd = new Course(c);
			current.addCourse(toAdd);
			i++;
			
			//New semester
			if (i > 3) {
				i = 0;
				toReturn[j] = current;
				incSem();
				current = new Semester(year,sem);
				j++;
			}
		}
		
		return toReturn;
	}
	
	private void incSem() {
		if (sem == "spring")
			sem = "fall";
		else {
			year++;
			sem = "spring";
		}
	}
}