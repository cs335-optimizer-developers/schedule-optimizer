package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import algorithm.AlgFinal;
import algorithm.Algorithm;
import display.DParam;
import display.FinalDisplay;
import info.Course;
import info.Semester;
import io.ParseDescrip;
import io.ParsePrereq;
import io.Writer;

/**
 * Class called by main - handles big picture functionality.
 * Currently contains pseudo-singleton pattern. 
 * 
 * It is build in the main class of RunOptimizer.
 *
 */
public class Optimizer {

	private static Optimizer one_optimizer;
	private Semester[] availableClasses;
	private Algorithm alg;
	private Semester[] newSchedule;
	
	private Optimizer() {
		availableClasses = ReadPopulateCSV.buildSemesters();
		
		HashMap<String, List<String>> coursePre = ParsePrereq.parsePrereq();
		
		// Look up every course key in the courses hashmap, then add to prereq list.
		for(int i = 0; i < availableClasses.length; i++) {
			Semester s = availableClasses[i];
			Map<String, Course> courses = s.getCourses();
			List<Course> values = new ArrayList<Course>(courses.values());
			for(Course advCourse : values) {
				// Look up every prereq and map to correct course, then add to advanced course prereqs.
				List<Course> prereqs = new ArrayList<Course>();
				List<String> unmapPre = coursePre.get(advCourse.getCourseKey());
				
				// Current course has no prereqs
				if(unmapPre == null)
					continue;
				
				for(String ck : unmapPre)
					prereqs.add(courses.get(ck));
				
				advCourse.setPrerequisites(prereqs);
			}
			
		}
		
		// Add the descriptions to every course object if it exists
		HashMap<String, String> courseDescrip = ParseDescrip.parseDescrip();
		for(int i = 0; i < availableClasses.length; i++) {
			Semester s = availableClasses[i];
			Map<String, Course> courses = s.getCourses();
			List<String> keys = new ArrayList<String>(courseDescrip.keySet());
			for(String key : keys) {
				Course c = courses.get(key);
				if(c == null)
					continue;
				c.setDescription(courseDescrip.get(key));
			}
		}
		
		one_optimizer = this;
	}

	/*
	 *  Objects instantiated by this class take input and output folders,
	 *  creating output from the input that was provided.
	 *  TODO Allow inputting of programs // specific classes.
	 */
	public Semester[] generate() {
		//long first = System.currentTimeMillis();
		
		DParam dPar = FinalDisplay.requestParameters();
		
		alg = new AlgFinal();
		newSchedule = alg.build(dPar);

		return newSchedule;
		//long last = System.currentTimeMillis();
		//System.out.printf("CSV generated in %s ms\n",last-first);
	}
	
	/**
	 * Writes an array of semesters through the Writer class.
	 * @return
	 */
	public Semester[] write() {
		generate();
		if (newSchedule != null) {
			Writer.writeSchedule(newSchedule);
		} else {
			DParam dPar = FinalDisplay.requestParameters();
			newSchedule = alg.build(dPar);
		}
			
		System.out.println("Schedule written");
		return newSchedule;
	}
	
	public Semester[] getSemesters() {
		if(newSchedule == null)
			return write();
		return newSchedule;
	}
	
	public Semester[] getAvailableClasses() {return availableClasses;}
	
	public void setAlgorithm(Algorithm a) {alg = a;}
	
	public static Optimizer getInstance() {
		if (one_optimizer == null)
			one_optimizer = new Optimizer();
		return one_optimizer;
	}
	
	/**
	 * Breaks singleton, used for testing purposes.
	 * @return new instance of optimizer.
	 */
	public static Optimizer newInstance() {
		one_optimizer = new Optimizer();
		return one_optimizer;
	}
	
	/**
	 * Convert available classes into a map containing both semesters
	 * @return
	 */
	public static Map<String, Course> generateMap() {
		Map<String, Course> cMap = new HashMap<String, Course>();
		for(int i = 0; i < one_optimizer.availableClasses.length; i++) {
			Semester s = one_optimizer.availableClasses[i];
			cMap.putAll(s.getCourses());
		}
		return cMap;
	}
}