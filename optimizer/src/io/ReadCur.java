package io;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import core.ReadPopulateCSV;
import core.Source;
import info.Course;
import info.Semester;

/**
 * 
 * Reads in lists of prerequisites for courses from .cur files. To
 * be used by algorithms in generation of semesters.
 *
 * @author James White
 * 
 */
public class ReadCur extends Reader {

	private static String source = Source.curriculae_folder;
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

				if (dept.contains("CSCI"))
					System.out.println(c.toTitle());
				
				// Populate the var if it hasn't already been.
				if (!prereqs.containsKey(t))
					rC.populateProgram(dept);
				
				List<String> toAdd = new ArrayList<String>();
				PriorityQueue<String> wL = new PriorityQueue<String>();
				
				// If contained
				if (prereqs.get(t) != null)
					for (String s : prereqs.get(t)) {
						System.out.println(s);
						System.out.println("Another one");
					}
				c.setPrerequisites(prereqs.get(t));
			}
		}
		return sems;
	}
	
	public void populateProgram(String program) {
		
		input = makeStream(source + program.toLowerCase() + ".cur");
		
		String classNum = "XXX";
		while (moveLine()) {
			String[] words = line.split(",");
			classNum=words[0];
			ArrayList<String> list = new ArrayList<>();
			if (words.length>1)
				prereqs.put(program+classNum,list);
			for (int i=1;i<words.length;i++) {
				list.add(program+words[i]);
			}
		}
	}

	public Map<? extends String, ? extends String> read(String prog) {
		// TODO Auto-generated method stub
		return null;
	}
}