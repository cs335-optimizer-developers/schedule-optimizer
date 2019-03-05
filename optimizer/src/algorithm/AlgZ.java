package algorithm;

import java.util.HashSet;
import java.util.Set;

import info.Course;
import info.Semester;
import io.ReadPrg;

public abstract class AlgZ implements Algorithm {
	
	int year = 2019;
	String sem = "fall";
	Set<Course> toTake = new HashSet<Course>();
	
	public abstract void distribute(Semester[] toFill);
	
	public Semester[] build(Semester[] classes, String[] programs) {
		ReadPrg rp = new ReadPrg();
		
		// TODO Less than ideal generation of basic courses.
		// May be better to keep them strings at parsing.
		for (String prog : programs)
			for (String s : rp.read(prog))
				toTake.add(new Course(s));
		
		Semester[] toReturn = new Semester[toTake.size()/4];
		
		distribute(toReturn);
		
		return toReturn;
	}
	
	protected void incSem() {
		if (sem == "spring")
			sem = "fall";
		else {
			year++;
			sem = "spring";
		}
	}
}
