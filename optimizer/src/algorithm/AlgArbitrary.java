package algorithm;

import info.Course;
import info.Semester;

/**
 * Class that fills semesters arbitrarily, with no regard for prereqs or
 * the author's inputted parameters for generation.
 * 
 * @author sirjwhite
 *
 */
public class AlgArbitrary extends AlgZ {

	public void distribute(Semester[] toFill) {
		int j = 0;
		int i = 0;
		
		//One iteration for each course.
		for (Course c : toTake) {
			System.out.println(i);
			Semester current = new Semester(year,sem);
			
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