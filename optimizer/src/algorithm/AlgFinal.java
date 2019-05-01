package algorithm;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
	private Map<Course,Integer> loc;
	private PriorityQueue<Course> pq;

	public void distribute(Semester[] toFill) {
		
		this.toFill = toFill;
		
		// Initializes fields in class.
		init();
		
		// Follows train of prereqs and fills toTake set fully.
		populatePrereqs();
		
		// Uses toTake to give each class a postrequisite list.
		populatePostreqs();
		
		// Optimally allocates classes across semesters.
		fillClasses();
	}
	
	private void init() {
		
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
		
	}
	
	private void populatePrereqs() {
		
		// Iteration count.
		int check = 0;
		Set<Course> taking = new HashSet<>();
		
		// Tacking on all prerequisites.
		while (!toTake.isEmpty() && check < 10) {
			Set<Course> toRm = new HashSet<>();
			Set<Course> toAd = new HashSet<>();
			for (Course c : toTake) {
				if (c == null)
					toRm.add(c);
				else {
					taking.add(c);
					boolean safe = true;
					for (Course p : c.getPrerequisites()) {
						safe = taking.contains(p) || toTake.contains(p);
						if (!safe) {
							toAd.add(p);
//							System.out.println(p.getName()+" tacked on by "+c.getName());
						}
					}
					if (safe) {
//						System.out.print(c.getName()+" being taken");
//						if (c.getPrerequisites().size() > 0)
//							System.out.print(" with:");
//						for (Course p : c.getPrerequisites())
//							System.out.print(" "+p.getName());
//						System.out.println();
						toRm.add(c);
					}
				}
			}
			for (Course c : toRm)
				toTake.remove(c);
			for (Course c : toAd)
				toTake.add(c);
			check++;
			
			if (check > 8) {
				System.err.println("Not including, bad prereqs:");
				for (Course c : toTake) {
					System.err.println(c.getName()+ " - " + c.getPrerequisites().size() +
							" total - includes "+c.getPrerequisites().get(0).getName());
				}
			}
		}
		toTake = taking;
	}
	
	private void populatePostreqs() {
		
		// If there are any prerequisites, give minimum priority.
		// Otherwise, weight postrequisites.
		Comparator<Course> comp = (o1,o2) -> {
			int pt1 = o1.getPostrequisites().size();
			int pt2 = o2.getPostrequisites().size();
			return pt2 - pt1;
		};
		
		for (Course c : toTake)
			for (Course p : c.getPrerequisites())
				p.addPostreq(c);
		
		pq = new PriorityQueue<>(comp);
		
		for (Course c : toTake)
			if (c != null)
				pq.add(c);
	}
	
	private void fillClasses() {
		
		Set<Course> held = new HashSet<>();
		loc = new HashMap<>();

		// One iteration for each course.
		while (!pq.isEmpty()) {
			Course c = pq.poll();
			
			System.out.println();
			System.out.println("Examining "+c.getName()+" with pstrq:"+c.getPostrequisites().size()
					+" prerq: "+c.getPqCount());
			
			// Ensures class can be added.
			if (c.getPqCount() == 0) {
//				System.out.println(c.getName()+" being added");
				addCourse(c);
				for (Course p : c.getPostrequisites()) {
					p.lessPrq();
				}
			}
			
			else {
				System.out.println(c.getName()+" held");
				held.add(c);
			}
			
			Set<Course> tRm = new HashSet<>();
			for (Course h : held) {
				if (h.getPqCount() == 0) {
//					System.out.println(h.getName()+" no longer held");
					addCourse(h);
					for (Course p : h.getPostrequisites())
						p.lessPrq();
					tRm.add(h);
				}
			}
			
			for (Course r : tRm)
				held.remove(r);
		}
	}

	// Adds a class - assumes that it exists in one of the maps.
	// @return index of course that it is added.
	public int addCourse(Course c) {
		
		Semester s = toFill[semIndex];
		
		System.out.println("Attempted add");

		// Current semester is full.
		if (toFill[semIndex].totalCredits() >= 12) {
			semIndex++;
			System.out.println("Full semester - "+semIndex);
		}

		// Fetching where latest prereq is stored.
		int fPreq = semIndex;
		for (Course p : c.getPrerequisites()) {
			int ind = loc.get(p)+1;
			System.out.println(ind+" from "+p.getName());
			if (ind > fPreq)
				fPreq = ind;
		}
		
		System.out.println(c.getName()+" pushed to "+fPreq+" over "+semIndex);
		
		// Find optimal semester to add
		int i;
		for (i=fPreq;i<toFill.length;i++) {
			s = toFill[i];
			System.out.println("i: "+i);
			System.out.println(s.totalCredits()+c.getCredits());
			if (s.totalCredits()+c.getCredits() < 19) {
					//((i % 2 == 0 && sem1.contains(c)) ||
//					(i % 2 == 1 && sem2.contains(c)))) {
				
				loc.put(c, i);
				for (Course p : c.getPrerequisites())
					updateLatest(p,i);
				break;
			}
		}
		
		if (!(i < toFill.length))
			System.out.println("Error adding "+c.getName());
		else
			s.addCourse(c);
		
		return i;
	}
	
	private void updateLatest(Course c,int newNum) {
		if (loc.get(c) < newNum)
			loc.put(c, newNum);
	}

	// Populates semester array with appropriate semester objects
	private void makeSem() {
		for (int i = 0; i < toFill.length; i++)
			toFill[i] = new Semester(2019+((i+1)/2),
					(i % 2 == 0) ? "fall" : "spring");
	}
}