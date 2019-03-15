package algorithm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import core.Optimizer;
import display.DParam;
import info.Course;
import info.Semester;
import io.ReadPrg;

/**
 * Abstract class for building algorithms that use a HashSet of
 * classes to be taken.
 * 
 * @author James White
 *
 */
public abstract class AlgZ implements Algorithm {
	
	protected int year = 2019;
	protected String sem = "fall";
	protected Set<Course> toTake = new HashSet<Course>();
	
	protected List<String> programs;
	
	protected Set<Course> sm1;
	protected Set<Course> sm2;
	
	public AlgZ() {
		sm1 = new HashSet<Course>(Optimizer.getInstance()
				.getAvailableClasses()[0].getCourses());
		sm2 = new HashSet<Course>(Optimizer.getInstance()
				.getAvailableClasses()[1].getCourses());
	}
	
	public abstract void distribute(Semester[] toFill);
	
	public Semester[] build(DParam dpar) {
		this.programs = dpar.getPrograms();
		ReadPrg rp = new ReadPrg();
		
		// TODO Less than ideal generation of basic courses.
		// May be better to keep them strings at parsing.
		for (String prog : programs)
			for (String s : rp.read(prog))
				toTake.add(new Course(s));
		
		Semester[] toReturn = new Semester[toTake.size()/4+2];
		
		distribute(toReturn);
		
		return toReturn;
	}
}
