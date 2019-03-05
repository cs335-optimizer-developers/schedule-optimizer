package algorithm;

import java.util.HashSet;
import java.util.Set;

import info.Course;
import info.Semester;

/**
 * Algorithm based on AlgArbitrary. It honors when classes occur, but not
 * their prerequisites.
 * 
 * @author jw-develop
 *
 */
public class AlgMatch extends AlgZ {

	public void distribute(Semester[] toFill) {
		int fCount = 0;
		int sCount = 0;
		int fCurrent = 0;
		int sCurrent = 1;
		int fYear = 2019;
		int sYear = 2019;
		
		Set<String> sem1 = new HashSet<String>();
		Set<String> sem2 = new HashSet<String>();
		
		for (Course c : sm1)
			sem1.add(c.toTitle());
		for (Course c : sm2)
			sem2.add(c.toTitle());
		
		//One iteration for each course.
		Semester fall = new Semester(fYear,"fall");
		Semester spring = new Semester(sYear,"spring");
		for (Course c : toTake) {
			
			if (sem1.contains(c.toTitle())) {
				fall.addCourse(c);
				fCount++;
				System.out.println(c.toTitle() + " added from fall");
			}
			else if (sem2.contains(c.toTitle())) {
				spring.addCourse(c);
				sCount++;
				System.out.println(c.toTitle() + " added from spring");
			}
			else {
				System.out.println("***" + c.toTitle() + " not found ***");
			}
			
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
		toFill[fCurrent] = fall;
		toFill[sCurrent] = spring;
	}
}