package algorithm;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import info.Course;
import info.Semester;
import io.ReadPrg;

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
	private Set<String> sem2;
	private Set<String> sem1;
	private Map<String,Integer> loc = new HashMap<>();
	private PriorityQueue<Course> pq;
	private int threshold = 12;
	private int requestedCreds = 0;
	private int totalAddedCredits = 0;
	
	private int irritA = 0;
	private int irritB = 0;
	private HashSet<String> complete = new HashSet<>();
	private int bogus = 0;
	

	public void distribute(Semester[] toFill) {
	
		// Temporary bug fix.
//		smT.get("CSCI235").addPrereq(smT.get("CSCI245"));
		
		this.toFill = toFill;
		
		// Initializes fields in class.
		init();
		
		// Follows train of prereqs and fills toTake set fully.
		populatePrereqs();
		
		// Uses toTake to give each class a postrequisite list.
		populatePostreqs();
		
		// Optimally allocates classes across semesters.
		fillClasses();
		
		// Ensures that student reaches 124 credit mark.
		enoughClasses();
		
		// Print statements
		diagnostics();
	}
	
	protected void diagnostics() {
//		System.out.println("IrritA: "+irritA);
//		System.out.println("IrritB: "+irritB);
//		System.out.println("Bogus: "+bogus);
		System.out.println("Requested Creds: "+requestedCreds);
		System.out.println("Final creds: "+totalAddedCredits);
	}
	
	private void init() {
		
		makeSem();
		
		sem1 = new HashSet<>();
		sem2 = new HashSet<>();
		
		// Populating sets for easy checking.
		// Also adding postreqs.
		for (Course c : sm1) {
			sem1.add(c.getName());
		}
		for (Course c : sm2) {
			sem2.add(c.getName());
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
			
//			if (check > 8) {
//				System.err.println("Not including, bad prereqs:");
//				for (Course c : toTake) {
//					System.err.println(c.getName()+ " - " + c.getPrerequisites().size() +
//							" total - includes "+c.getPrerequisites().get(0).getName());
//				}
//			}
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
		
		Queue<Course> toPop;
		Set<Course> added;
		
		for (Course c : toTake) {
			toPop = new LinkedList<>();
			added = new HashSet<>();
			
			for (Course p : c.getPrerequisites())
				toPop.add(p);
			while (!toPop.isEmpty()) {
				Course pre = toPop.poll();
				pre.addPostreq(c);
				for (Course p : pre.getPrerequisites()) {
					if (!added.contains(p))
						toPop.add(p);
					added.add(p);
					p.addPostreq(c);
				}
			}
		}
		
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
			
//			System.out.println();
//			System.err.println("Examining "+c.getName()+" with pstrq:"+c.getPostrequisites().size()
//					+" prerq: "+c.getPqCount()+"/"+c.getPrerequisites().size());
			
			// Ensures class can be added.
			if (c.getPqCount() <= 0) {
//				System.out.println(c.getName()+" being added");
				addCourse(c);
				for (Course p : c.getPostrequisites()) {
					p.lessPrq();
				}
			}
			
			else {
//				System.out.println(c.getName()+" held");
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
		requestedCreds = totalAddedCredits;
	}

	private void enoughClasses() {
		ReadPrg rp = new ReadPrg();
		
		Set<Course> more = new HashSet<>();
		
		// Init if necessary.
		if (totalAddedCredits < 124)
			for (String s : rp.read("electives"))
				more.add(smT.get(s));
		
		// Loop until done.
		Iterator<Course> it = more.iterator();
		
		while (totalAddedCredits < 124) {
			
			// If it runs out of requested electives, it can default
			// to semester electives.
			if (!it.hasNext())
				it = sm1.iterator();
			
			Course c = it.next();
			if (c != null && 
					!complete.contains(c.getName()) &&
					c.getPqCount() == 0) {
				int num = c.getNumber()/100;
				if (num == 1)
					addCourse(c);
			}
		}

//		System.out.println("After Electives: "+totalAddedCredits);
	}
	
	// Adds a course - assumes that it exists in one of the maps.
	// @return index of course that it is added.
	private int addCourse(Course c) {
		
		Semester s = toFill[semIndex];
		
		// Fetching where latest prereq is stored.
		int fPreq = semIndex;
		for (Course p : c.getPrerequisites()) {
			if (p != null) {
				int ind = (loc.containsKey(p.getName())) ? loc.get(p.getName())+1 : 0;
//				System.out.println(ind+" from "+p.getName());
				if (ind > fPreq)
					fPreq = ind;
			}
		}
		
//		System.out.println(c.getName()+" pushed to "+fPreq+" over "+semIndex);
		
		// Find optimal semester to add
		int i;
		for (i=fPreq;i<toFill.length;i++) {
			s = toFill[i];
//			System.out.println("i: "+i);
//			System.out.println(s.totalCredits()+c.getCredits());
//			System.out.println(s.totalCredits()+c.getCredits() < 19);
			if (i % 2 == 0 && sem1.contains(c.getName()))
				irritA++;
			if (i % 2 == 1 && sem2.contains(c.getName()))
				irritB++;
			
			if (s.totalCredits()+c.getCredits() <= threshold &&
					((i % 2 == 1 && sem1.contains(c.getName())) ||
					(i % 2 == 0 && sem2.contains(c.getName()))) &&
					s.getCourses().size() < 6) {
				
				loc.put(c.getName(), i);
				for (Course p : c.getPrerequisites())
					updateLatest(p,i);
				break;
			}
		}
		
		if (!(i < 8)) {
			if (threshold < 18)
				threshold += 2;
//			System.out.println("Error adding "+c.getName()+", up to "+threshold);
		} else {
			if (!complete.contains(c.getName())) {
				complete.add(c.getName());
//				System.out.println(c.getName()+" placed in "+i);
				s.addCourse(c);
				totalAddedCredits += c.getCredits();
			} else {
				bogus++;
				return 0;
			}
			
		}
		
		return i;
	}
	
	private void updateLatest(Course c,int newNum) {
		if (loc.containsKey(c.getName()))
			if (loc.get(c.getName()) < newNum)
				loc.put(c.getName(), newNum);
			else
				return;
		loc.put(c.getName(), newNum);
	}

	// Populates semester array with appropriate semester objects
	private void makeSem() {
		for (int i = 0; i < toFill.length; i++)
			toFill[i] = new Semester(2019+((i+1)/2),
					(i % 2 == 0) ? "fall" : "spring");
	}
}