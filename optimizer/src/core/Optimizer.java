package core;

import algorithm.*;
import info.Semester;
import io.Writer;

/**
 * Class called by main - handles big picture functionality.
 * 
 * Currently contains pseudo-singleton pattern. Less than ideal, but it works.
 * 
 * @author sirjwhite
 *
 */
public class Optimizer {

	private static Optimizer one_optimizer;
	Semester[] newSchedule;
	
	private Optimizer() {
		one_optimizer = this;
	}

	/*
	 *  Objects instantiated by this class take input and output folders,
	 *  creating output from the input that was provided.
	 */
	public void generate() {
		
		Algorithm alg = new AlgArbitrary();
		String[] programs = {
				"csci-major",
				"test-gen-ed"
		};
				
		Semester[] availableClasses = ReadPopulateCSV.buildSemesters();
				
		newSchedule = alg.build(availableClasses,programs);
	}
	
	public void write() {
		Writer.writeSchedule(newSchedule);
		System.out.println("CSV generated!");
	}
	
	public static Optimizer getInstance() {
		if (one_optimizer == null)
			one_optimizer = new Optimizer();
		return one_optimizer;
	}
}