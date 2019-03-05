package algorithm;

import java.util.HashSet;
import java.util.Set;

import info.Course;
import info.Semester;
import io.ReadPrg;

/**
 * Abstract class for 
 * 
 * @author sirjwhite
 *
 */
public abstract class AlgZ implements Algorithm {
	
	protected int year = 2019;
	protected String sem = "fall";
	protected Set<Course> toTake = new HashSet<Course>();
	protected String[] programs;
	
	public abstract void distribute(Semester[] toFill);
	
	public Semester[] build(Semester[] classes, String[] programs) {
		this.programs = programs;
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
