package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import algorithm.AlgComplex;
import algorithm.Algorithm;
import display.DParam;
import display.FinalDisplay;
import info.Course;
import info.CourseKey;
import info.Semester;
import io.ParsePrereq;
import io.ReadCur;
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
		one_optimizer = this;
		availableClasses = 
				ReadCur.addPrerequisites(ReadPopulateCSV.buildSemesters());
		
		HashMap<CourseKey, List<CourseKey>> coursePre = ParsePrereq.parsePrereq();
		
		// Look up every course key in the courses hashmap, then add to prereq list.
		
		for(int i = 0; i < availableClasses.length; i++) {
			Semester s = availableClasses[i];
			Map<CourseKey, Course> courses = s.getCourses();
			List<Course> values = new ArrayList<Course>(courses.values());
			for(Course advCourse : values) {
				// Look up every prereq and map to correct course, then add to advanced course prereqs.
				List<Course> prereqs = new ArrayList<Course>();
				List<CourseKey> unmapPre = coursePre.get(advCourse.getCourseKey());
				
				// Current course has no prereqs
				if(unmapPre == null)
					continue;
				
				for(CourseKey ck : unmapPre) {
					prereqs.add(courses.get(ck));
				}
				
				advCourse.setPrerequisites(prereqs);
			}
			
		}
		
		alg = new AlgComplex();

	}

	/*
	 *  Objects instantiated by this class take input and output folders,
	 *  creating output from the input that was provided.
	 *  TODO Allow inputting of programs // specific classes.
	 */
	public Semester[] generate() {
		//long first = System.currentTimeMillis();
		
		DParam dPar = FinalDisplay.requestParameters();

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
		if (newSchedule != null) {
			Writer.writeSchedule(newSchedule);
		} else {
			DParam dPar = FinalDisplay.requestParameters();
			newSchedule = alg.build(dPar);
		}
			
		return newSchedule;
		//System.out.println("Schedule written");
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
}