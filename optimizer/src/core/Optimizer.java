package core;

import algorithm.*;
import display.DParam;
import display.FinalDisplay;
import info.Semester;
import io.Writer;

/**
 * Class called by main - handles big picture functionality.
 * Currently contains pseudo-singleton pattern. 
 * Less than ideal, but it works.
 * 
 * @author James White
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
	public Semester[] generate() {
		//long first = System.currentTimeMillis();
		
		DParam dPar = FinalDisplay.requestParameters();

		newSchedule = alg.build(dPar);

		return newSchedule;
		//long last = System.currentTimeMillis();
		//System.out.printf("CSV generated in %s ms\n",last-first);
	}
	
	public Semester[] write() {
		if (newSchedule != null)
			Writer.writeSchedule(newSchedule);
		
		else {
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