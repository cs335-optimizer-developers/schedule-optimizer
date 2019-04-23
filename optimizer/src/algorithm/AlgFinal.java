package algorithm;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
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
public class AlgFinal extends AlgZ {
	
	private Semester[] toFill;
	private int semIndex = 0;
	private Set<Course> sem2;
	private Set<Course> sem1;
	
	public void distribute(Semester[] toFill) {
		this.toFill = toFill;
		checkSem();
		
		sem1 = new HashSet<>();
		sem2 = new HashSet<>();
		
		// Populating sets for easy checking.
		// Also adding postreqs.
		for (Course c : sm1) {
			sem1.add(c);
		}
		for (Course c : sm2) {
			sem2.add(c);
		}
		
		// Comparator which favors courses with any postrequisites, then
		// with few prerequisites.
		Comparator<Course> comp = (o1,o2) -> {
			if (o1.getPrerequisites().size() > 0)
				return Integer.MIN_VALUE;
			return o1.getPostrequisites().size() - o2.getPostrequisites().size();
		};
		
		PriorityQueue<Course> pq = new PriorityQueue<>(comp);
		for (Course c : toTake)
			if (c != null) {
				pq.add(c);
				for (Course p : c.getPrerequisites())
					p.addPostreq(c);
			}

		//One iteration for each course.
		while (!pq.isEmpty()) {
			Course c = pq.poll();
			
			System.out.println("\n"+c.getCourseKey() + " is of length " + c.getPostrequisites().size());
			
			addClass(c);
		}
	}
	
	// Adds a class - assumes that it exists in one of the maps.
	public void addClass(Course c) {
		Semester s;
		checkSem();
		
		if (semIndex % 2 == 0 && sem1.contains(c) ||
			semIndex % 2 == 1 && sem2.contains(c)){
			s = toFill[semIndex];
		}
		
		// Next semester, because it can't be found in the current one.
		else {
			s = toFill[semIndex+1];
		}

		s.addCourse(c);
	}

	private void checkSem() {
		if (toFill[semIndex].getCourses().size() > 4) {
			toFill[semIndex] = new Semester(2019+((semIndex+1)/2),
					(semIndex % 2 == 0) ? "fall" : "spring");
			semIndex++;
		}
		if (toFill[semIndex].getCourses().size() > 4) {
			toFill[semIndex] = new Semester(2019+((semIndex+1)/2),
					(semIndex % 2 == 0) ? "fall" : "spring");
		}
	}
}