package core;

import algorithm.*;
import info.Semester;
import io.Writer;

/**
 * Class called by main - handles big picture functionality.
 * Currently contains pseudo-singleton pattern. 
 * Less than ideal, but it works.
 * 
 * @author jw-develop
 *
 */
public class Optimizer {

	private static Optimizer one_optimizer;
	private Semester[] availableClasses;
	private Algorithm alg;
	
	private Semester[] newSchedule;
	
	private Optimizer() {
		one_optimizer = this;
		availableClasses = ReadPopulateCSV.buildSemesters();
		
		alg = new AlgMatch();
	}

	/*
	 *  Objects instantiated by this class take input and output folders,
	 *  creating output from the input that was provided.
	 *  TODO Allow inputting of programs // specific classes.
	 */
	public void generate() {
		long first = System.currentTimeMillis();
		
		String[] programs = {
				"csci-major",
				"test-gen-ed"
		};

		newSchedule = alg.build(programs);
		
		long last = System.currentTimeMillis();
		System.out.printf("CSV generated in %s ms\n",last-first);
	}
	
	public void write() {
		Writer.writeSchedule(newSchedule);
		System.out.println("Schedule written");
	}
	
	public Semester[] getAvailableClasses() {return availableClasses;}
	
	public void setAlgorithm(Algorithm a) {alg = a;}
	
	public static Optimizer getInstance() {
		if (one_optimizer == null)
			one_optimizer = new Optimizer();
		return one_optimizer;
	}
}