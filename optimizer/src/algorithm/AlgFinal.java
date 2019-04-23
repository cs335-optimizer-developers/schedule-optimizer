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
public class AlgFinal extends AlgZ {
	
	private Semester[] toFill;
	private int semIndex = 0;
	private Set<Course> sem2;
	private Set<Course> sem1;

	public void distribute(Semester[] toFill) {
		this.toFill = toFill;
		makeSem();

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
		
		// Basic attempt to debug.
		int check = 0;
		Set<Course> taken = new HashSet<>();
		
		while (!toTake.isEmpty() && check < 30) {
			Set<Course> toRm = new HashSet<>();
			Set<Course> toAd = new HashSet<>();
			for (Course c : toTake) {
				if (c == null)
					toRm.add(c);
				else {
					boolean safe = true;
					for (Course p : c.getPrerequisites()) {
						safe = taken.contains(p);
						if (!safe)
//							System.out.println("No "+p.getName()+" for "+c.getName());
						if (!safe && !toTake.contains(p)) {
							toAd.add(p);
//							System.out.println(p.getName()+" tacked on by "+c.getName());
						}
					}
					if (safe) {
						taken.add(c);
						addCourse(c);
						toRm.add(c);
					}
				}
			}
			for (Course c : toRm)
				toTake.remove(c);
			for (Course c : toAd)
				toTake.add(c);
			check++;
		}
		
		if (check > 15) {
			System.err.println("Adding despite bad prereqs:");
			for (Course c : toTake) {
				System.err.println(c.getName()+ " - " + c.getPrerequisites().size() +
						" total - includes "+c.getPrerequisites().get(0).getName());
			}
			System.out.println();
			for (Course c : toTake) {
				addCourse(c);
			}
		}

//		// If there are any prerequisites, give minimum priority.
//		// Otherwise, weight postrequisites.
//		Comparator<Course> comp = (o1,o2) -> {
//			@SuppressWarnings("unused")
//			int pr1 = o1.getPrerequisites().size(),pt1 = o1.getPostrequisites().size();
//			@SuppressWarnings("unused")
//			int pr2 = o2.getPrerequisites().size(),pt2 = o2.getPostrequisites().size();
//			//			if (pr1 == 0 && pr2 == 0)
//			//				return pt1 - pt2;
//			//			else if (pr1 != 0 && pr2 != 0)
//			//				return pr2 - pr1;
//			//			return (pr1 == 0) ? 1000 : -1000;
//			return pr1 - pr2;
//		};
//
//		// Adding postrequisites.
//		PriorityQueue<Course> pq = new PriorityQueue<>(comp);
//
//		// Un-added prereqs being added.
//		Queue<Course> tackOn = new LinkedList<>();
//		for (Course c : toTake) {
//			if (c != null) {
//				System.out.println(c.getName());
//				if (c.getPrerequisites().size() > 0) {
//					for (Course preq : c.getPrerequisites()) {
//						if (!toTake.contains(preq)) {
//							tackOn.add(preq);
//							System.out.println("Tacking on "+preq.getName());
//							preq.addPostreq(c);
//						}
//					}
//				}
//				System.out.println(c.getPrerequisites().size());
//			}
//		}
//
//		while (!tackOn.isEmpty()) {
//			Course c = tackOn.poll();
//			for (Course p : c.getPrerequisites()) {
//				if (!toTake.contains(p)) {
//					toTake.add(p);
//					System.out.println("Further tacking "+c.getName());
//					p.addPostreq(c);
//				}
//			}
//		}
//
//		for (Course c : toTake)
//			if (c != null)
//				pq.add(c);
//		// One iteration for each course.
//		while (!pq.isEmpty()) {
//			Course c = pq.poll();
//
//			System.out.println("Examining "+c.getName()+" with pstrq:"+c.getPostrequisites().size()
//					+" prerq:"+c.getPrerequisites().size());
//
//			addClass(c);
//			for (Course p : c.getPostrequisites())
//				p.rmPrq(c);
//		}
		
//		for (int i = 0; i < 5; i++) {
//			Semester s = toFill[i];
//			System.out.println(i);
//			for (String str : s.getCourses().keySet())
//				System.out.println(str);
//		}
	}

	// Adds a class - assumes that it exists in one of the maps.
	public void addCourse(Course c) {
		Semester s = toFill[semIndex];

		// Current semester is full.
		if (toFill[semIndex].size() > 2) {
			semIndex++;
//			System.out.println("Full semester");
		}
		
//		// If current semester contains desired class.
//		if ((semIndex % 2 == 0 && sem1.contains(c)) ||
//				(semIndex % 2 == 1 && sem2.contains(c))) {
//			System.out.println("Option a");
//			s = toFill[semIndex];
//		}
//
//		// If next semester is not full.
//		else if (toFill[semIndex+1].size()<4){
//			System.out.println("Option b");
//			s = toFill[semIndex+1];
//		}
//		
//		// Special case if next semester is full.
//		else {
//			System.out.println("Had to jump 2 semesters for "+c.getName());
//			s = toFill[semIndex+3];
//		}
		
//		System.out.println(c.getName()+" added to "+semIndex);
		s.addCourse(c);
	}

	// Populates semester array with appropriate semester objects
	private void makeSem() {
		for (int i = 0; i < toFill.length; i++)
			toFill[i] = new Semester(2019+((i+1)/2),
					(i % 2 == 0) ? "fall" : "spring");
	}
}