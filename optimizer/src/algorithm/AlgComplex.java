package algorithm;

import java.util.HashSet;
import java.util.Set;

import info.Course;
import info.Semester;

/**
 * 
 * Class that takes all relevant information into account, such
 * that output is correct, without any preference parameters.
 * 
 * Namely, this includes whether a class is offered in the fall or
 * Spring, as well as honoring prerequisite requirements.
 * 
 * @author James White
 *
 */
public class AlgComplex extends AlgZ {
	
	public void distribute(Semester[] toFill) {	
		int fCount = 0;
		int sCount = 0;
		int fCurrent = 0;
		int sCurrent = 1;
		int fYear = 2019;
		int sYear = 2020;
		
		Set<String> sem1 = new HashSet<String>();
		Set<String> sem2 = new HashSet<String>();
				
		for (Course c : sm1)
			sem1.add(c.toTitle());
		for (Course c : sm2)
			sem2.add(c.toTitle());
		
		//To favor fall or spring, whichever is ahead.
		int balance = 0;
		
		//One iteration for each course.
		Semester fall = new Semester(fYear,"fall");
		Semester spring = new Semester(sYear,"spring");
		for (Course c : toTake) {
			if (c == null)
				continue;
			if (sem1.contains(c.toTitle()) && balance <= 0) {
				fall.addCourse(c);
				fCount++;
				balance++;
				//System.out.println(c.toTitle() + " added from fall");
			}
			else if (sem2.contains(c.toTitle())) {
				spring.addCourse(c);
				sCount++;
				balance--;
				//System.out.println(c.toTitle() + " added from spring");
			}
			
			//In case course is not offered in Spring.
			else if (sem1.contains(c.toTitle())) {
				fall.addCourse(c);
				fCount++;
				balance++;
				//System.out.println(c.toTitle() + " added from fall");
			}
			else
				System.err.println("***" + c.toTitle() + " not found ***");
			//New semester
			if (fCount > 3) {
				fCount = 0;
				toFill[fCurrent] = fall;
				fYear++;
				fall = new Semester(fYear,"fall");
				fCurrent += 2;
			}
			if (sCount > 3) {
				sCount = 0;
				toFill[sCurrent] = spring;
				sYear++;
				spring = new Semester(sYear,"spring");
				sCurrent += 2;
			}
		}
		
		// Clean up, adding final semester if there are straggling classes.
		if (fall != null)
			toFill[fCurrent] = fall;
		if (spring != null)
			toFill[sCurrent] = spring;
	}
}
