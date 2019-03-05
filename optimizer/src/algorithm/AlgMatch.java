package algorithm;

import info.Course;
import info.Semester;

/**
 * Algorithm based on AlgArbitrary. It honors when classes occur, but not
 * their prerequisites.
 * 
 * @author jameswhite
 *
 */
public class AlgMatch extends AlgZ {

	public void distribute(Semester[] toFill) {
		int j = 0;
		int i = 0;
		
		//One iteration for each course.
		Semester current = new Semester(year,sem);
		for (Course c : toTake) {
			
			current.addCourse(c);
			i++;
			
			//New semester
			if (i > 3) {
				i = 0;
				toFill[j] = current;
				incSem();
				current = new Semester(year,sem);
				j++;
			}
		}
	}
}