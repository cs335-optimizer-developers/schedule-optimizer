package io;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

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
			if (sem != null)
				for (Course c : sem.getCourses()) {

					String t = c.toTitle();
					String st[] = t.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
					String dept = st[0];

					ReadCur rC = new ReadCur();

					//				if (dept.contains("CSCI"))
					//					System.err.println(c.toTitle());

					// Populate the var if it hasn't already been.
					if (!prereqs.containsKey(t))
						rC.populateProgram(dept);

					List<String> toAdd = prereqs.get(t);
					Queue<String> wL = new ArrayDeque<String>();
					if (toAdd != null)
						wL.addAll(toAdd);

					// If contained
					while (!wL.isEmpty()) {
						String cur = wL.remove();
						if (prereqs.get(cur) != null) {
							//						System.out.println(cur);
							for (String pR : prereqs.get(cur))
								if (!toAdd.contains(pR)) {
									toAdd.add(pR);
									//								System.out.println(pR);
								}
						}
					}
					c.setPrerequisites(toAdd);
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
}