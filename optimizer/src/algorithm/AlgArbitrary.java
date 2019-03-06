package algorithm;

import info.Course;
import info.Semester;

/**
 * Class that fills semesters arbitrarily, with no regard for prereqs,
 * the author's inputted parameters for generation, or the times that
 * courses are actually offered.
 * 
 * @author James White
 *
 */
public class AlgArbitrary extends AlgZ {
	public void distribute(Semester[] toFill) {
		System.out.println("AlgArbitrary");
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
	
	private void incSem() {
		if (sem == "spring")
			sem = "fall";
		else {
			year++;
			sem = "spring";
		}
	}
}