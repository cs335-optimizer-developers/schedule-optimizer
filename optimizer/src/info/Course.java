package info;

import java.util.ArrayList;
import java.util.List;

/**
 *	Class defines a specific class, each class has 0-many labs, and 1-many sections.
 */

public class Course {
	// The subject of the class
	private Subject subj;
	// The class number
	private int number;
	// All possible tags a course can have
	private List<Tag> tags;
	// Each class has 0-many labs
	private List<ClassType> labs = new ArrayList<>();
	// Each class has 1-many sections
	private List<ClassType> sections = new ArrayList<>();
	
	// Instantiates a class object, verifying if tags do exist, and if the section is valid.
	public Course(Subject subj, int number, ClassType type, List<Tag> tags) {
		if(type == null) {
			System.out.println("Error when creating a new class, section cannot be null... *terminating*");
			System.exit(1);
		}
		
		if (type.getClass().equals(Lab.class))
			labs.add((Lab) type);
		else
			sections.add((Section) type);
		this.subj = subj;
		this.number = number;
		if(tags==null)
			this.tags = null;
		else
			this.tags = new ArrayList<Tag>(tags);
	}
	
	
	/**
	 * Add a new section to the sections list
	 * @param s, a new section, not null
	 */
	public void addSection(ClassType s) {
		if(s == null) {
			System.out.println("Error in class when adding a new section...");
			return;
		}
		
		sections.add(s);
	}
	
	/**
	 * Add a new lab to the labs list
	 * @param newLab, a new lab, not null
	 */
	public void addLab(ClassType newLab) {
		if(newLab==null) {
			System.out.println("Error in class when adding a new lab...");
			return;
		}
		
		labs.add(newLab);
	}
	
	/**
	 * TODO: Probably not going to return a double[], this will be implemented later.
	 * @return probability of class times
	 */
	public double[] computeTimeProb() {
		return null;
	}
	
	/**
	 * TODO: Probably not going to return a double[], this will be implemented later.
	 * @return probability of professors
	 */
	public double[] computeProfProb() {
		return null;
	}
	
	// Key to distinguish between other courses
	public String getKey() {
		return subj.toString() + ":" + number;
	}
	
	public String toString() {
		String s = getKey();
		s += "\nTags:";
		for(Tag t : tags)
			s += t + ", ";
			
		s += "\nSections:\n";
		for(ClassType t : sections)
			s += t.toString() + "\t\n";
		
		s += "Labs:\n";
		for(ClassType t : labs)
			s += t.toString() + "\n";
		
		return s+"\n";
	}
	
}










