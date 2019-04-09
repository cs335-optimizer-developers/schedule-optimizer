package io;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import core.ReadPopulateCSV;
import core.Source;
import info.Course;
import info.Semester;

/**
 * 
 * Reads in lists of prerequisites for courses from .cur files. To
 * be used by algorithms in generation of semesters.
 *
 */
public class ReadCur extends Reader {

	private static String source = Source.details_folder;
	private static Map<String,ArrayList<String>> prereqs = new HashMap<>();

	public static void main(String[] args) {
		ReadCur.addPrerequisites(ReadPopulateCSV.buildSemesters());
	}
	
	public static Semester[] addPrerequisites(Semester[] sems) {
		for (Semester sem : sems) {
			for (Course c : sem.getCourses()) {
				
				String t = c.toTitle();
				String st[] = t.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
				String dept = st[0];
				
				ReadCur rC = new ReadCur();
				
				if (!prereqs.containsKey(t))
					prereqs.put(t,rC.populateProgram(dept).get(t));
				
				c.setPrerequisites(prereqs.get(t));
			}
		}
		
		return sems;
	}
	
	public Map<String,ArrayList<String>> populateProgram(String program) {
		
		input = makeStream(source + program + ".cur");
		Map<String,ArrayList<String>> toReturn = new HashMap<>();
		
		String classNum = "XXX";
		while (moveLine()) {
			if (hasChar('+'))
				classNum = line.substring(1);
			//else
				//toReturn.put(program.toUpperCase()+classNum,line);
		}
		return toReturn;
	}
}
