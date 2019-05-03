package info;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	//PrerequisiteCourses.
	private List<Course> prerequisites = new ArrayList<>();
	// Postrequisites, used only in AlgFinal.
	private Set<Course> postrequisites = new HashSet<>();
	//DescriptionsCourses
	private List<Course> descriptions = new ArrayList<>();
	//Credits
	private int credits;
	// Unsatisfied prereq count.
	private int pqCount = 0;

	// Instantiates a class object, verifying if tags do exist, and if the section is valid.
	public Course(Subject subj, int number, ClassType type, List<Tag> tags, int credits) {
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
		if(tags == null)
			this.tags = null;
		else
			this.tags = new ArrayList<Tag>(tags);
		this.credits = credits;
	}
	
	/**
	 * For making stub classes within algorithms.
	 * @param s, Data about a class.
	 */
	public Course(String s) {
		String[] info = s.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
		subj = Subject.valueOf(info[0]);
		number = Integer.valueOf(info[1]);
	}
	
	public int getNumber() {return number;}
	
	public void addPrereq(Course c) {
		if(c == null) {
			System.out.println("Error in class when adding a new prereq...");
			return;
		}
		this.prerequisites.add(c);
		pqCount++;
	}
	public void addPostreq(Course c) {
		if(c == null) {
			System.out.println("Error in class when adding a new postreq...");
			return;
		}
		this.postrequisites.add(c);
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
	public String parseKey() {
		return subj.toString() + ":" + number;
	}
	
	public String getCourseKey() {
		return subj + "" + number;
	}
	
	public String getName() {
		return subj + " " + number;
	}
	
	public List<ClassType> getSections() {
		return sections;
	}
	
	public List<ClassType> getLabs() {
		return labs;
	}
	public List<Course> getPrerequisites() {
		return prerequisites;
	}
	public void setPrerequisites(List<Course> p) {
		if (p != null)
			for (Course c : p)
				if (c != null)
					prerequisites.add(c);
		pqCount = prerequisites.size();
	}
	public Set<Course> getPostrequisites() {
		return postrequisites;
	}
	public void setPostrequisites(List<Course> p) {
		if (p != null)
			for (Course c : p)
				if (c != null)
					postrequisites.add(c);
	}
	
	public List<Course> getDescription(){
		return descriptions;
	}
	
	public void setDescription(List<Course> p) {
		if (p != null)
			for (Course c : p)
				if (c != null)
					descriptions.add(c);
	}
	
	public int getCredits() {
		return credits;
	}
	
	public void rmPrq(Course c) {
		prerequisites.remove(c);
	}
	
	public void lessPrq() {
		pqCount--;
	}
	
	public int getPqCount() {
		return pqCount;
	}
	
	public String toString() {
		String s = getName();
		s += "\nTags: ";
		
		if(tags != null && tags.size() != 0)
			for(Tag t : tags)
				s += t + ", ";
			
		s += "\nSections:\n";
		for(ClassType t : sections) {
			s += t.toString() + "\n";
		}
			
		
		if(labs == null || labs.size() == 0)
			return s;
		
		s += "Labs:\n";
		for(ClassType t : labs)
			s += t.toString() + "\n";
		
		return s;
	}
	
	
}










