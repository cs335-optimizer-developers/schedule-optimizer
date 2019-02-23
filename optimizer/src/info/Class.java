package info;

import java.util.ArrayList;

/**
 *	Class defines a specific class, each class has 0-many labs, and 1-many sections.
 */

// TODO add all the tags
enum Tag {SHAR,SI,HP};
enum Quad {A,B,BOTH};
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
	
	// Instantiates a class object, verifying tags exist.
	public Class(Subject subj, int number, ArrayList<Tag> tags) {
		this.subj = subj;
		this.number = number;
		if(tags==null)
			this.tags = null;
		else
			this.tags = new ArrayList<Tag>(tags);
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










