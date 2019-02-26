package info;

import java.util.ArrayList;

/**
 *	Class defines a specific class, each class has 0-many labs, and 1-many sections.
 */

// TODO add all the tags, no tag = null
enum Tag {SHAR,SI,HP};
// All the possible subjects a class can be in (taken from 2019 data)
enum Subject { AHS,ANTH,ARCH,ART,ASTR,BEC,BIOL,BITH,CE,CHEM,CHIN,COMM,CORE,CSCI,
	ECON,EDUC,ENG,ENGL,ENGR,ENGW,ENVR,FREN,GEL,GEND,GEOL,GERM,GREK,HEBR,HIST,HNGR,
	IDS,IR,LATN,LING,MATH,MSCI,MUCS,MUEP,MUIP,MUMS,MUTC,NEUR,PACS,PHIL,PHYS,PSCI,PSYC,
	RELI,SCI,SOC,SPAN,SSCI,SWEL,URBN };

public class Class {
	// The subject of the class
	private Subject subj;
	// The class number
	private int number;
	// All possible tags a course can have
	private ArrayList<Tag> tags;
	
	// Each class has 0-many labs
	private ArrayList<Lab> labs;
	// Each class has 1-many sections
	private ArrayList<Section> sections;
	
	// Instantiates a class object, verifying if tags do exist, and if the section is valid.
	public Class(Subject subj, int number, Section section, ArrayList<Tag> tags) {
		if(section == null) {
			System.out.println("Error when creating a new class, section cannot be null... *terminating*");
			System.exit(1);
		}
		
		sections.add(section);
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
	public void addSection(Section s) {
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
	public void addLab(Lab newLab) {
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

}










