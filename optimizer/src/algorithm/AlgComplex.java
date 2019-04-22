package algorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
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
public class AlgComplex extends AlgZ {
	
	private Semester[] toFill;
	private int fCurrent;
	private int sCurrent;

	public void distribute(Semester[] toFill) {
		this.toFill = toFill;
		int fCount = 0;
		int sCount = 0;
		fCurrent = 0;
		sCurrent = 1;
		int fYear = 2019;
		int sYear = 2020;
		
		Set<String> sem1 = new HashSet<String>();
		Set<String> sem2 = new HashSet<String>();
				
		for (Course c : sm1)
			sem1.add(c.toTitle());
		for (Course c : sm2)
			sem2.add(c.toTitle());
		
		// Comparator which favors courses with few prerequisites.
		Comparator<Course> comp = (o1,o2) -> {
			return o1.getPrerequisites().size() - o2.getPrerequisites().size();
		};
		
		PriorityQueue<Course> pq = new PriorityQueue<>(comp);
		for (Course c : toTake)
			if (c != null)
				pq.add(c);
		
		//To favor fall or spring, whichever is ahead.
		int balance = 0;
		Semester fall = new Semester(fYear,"fall");
		Semester spring = new Semester(sYear,"spring");

		//One iteration for each course.
		while (!pq.isEmpty()) {
			Course c = pq.poll();
			
			System.out.println("\n"+c.toTitle() + " is of length " + c.getPrerequisites().size());
			
			boolean gtg = true;
			for (Course temp : c.getPrerequisites()) {
				String pre = temp.toTitle();
				System.out.println(pre + " check being made");
				if (!verPrereq(c,pre)) {
					System.err.printf(""+c.toTitle() + " can't be added: " +pre+"\n");
					gtg = false;
				}
			}

			if (gtg) {
				// Three options to add courses to fall or spring.
				if (sem1.contains(c.toTitle()) && balance <= 0) {
					fall.addCourse(c);
					fCount++;
					balance++;
					System.out.println(c.toTitle() + " added from fall");
				}
				else if (sem2.contains(c.toTitle())) {
					spring.addCourse(c);
					sCount++;
					balance--;
					System.out.println(c.toTitle() + " added from spring");
				}

				//In case course is not offered in Spring.
				else if (sem1.contains(c.toTitle())) {
					fall.addCourse(c);
					fCount++;
					balance++;
					System.out.println(c.toTitle() + " added from fall");
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
		}
		
		
		// Clean up, adding final semester if there are straggling classes.
		if (fall != null)
			toFill[fCurrent] = fall;
		if (spring != null)
			toFill[sCurrent] = spring;
	}

	public boolean verPrereq(Course c,String pre) {
		for (int i = Math.max(fCurrent, sCurrent);i>=0;i--) {
			if (toFill[i] != null) {
				List<Course> courses = new ArrayList<Course>(toFill[i].getCourses().values());
				for (Course check : courses) {
					if (check.toTitle().equals(pre)) {
						System.out.println(c.toTitle() +" verified from " + pre + " and added");
						return true;
					}
				}
			}
		}
		System.err.println("Returning false");
		return false;
	}
}