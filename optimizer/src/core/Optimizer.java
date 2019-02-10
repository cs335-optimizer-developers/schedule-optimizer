package core;

import java.util.ArrayList;

import algorithm.Algorithm;
import algorithm.AlgorithmRandom;
import reader.Reader;

public class Optimizer {

	String input;
	String output;
	String[][] schedule;
	Reader read;
	
	public Optimizer(String input, String output) {
		this.input = input;
		this.output = output;
		read = new Reader(input);
	}

	public void generate() {
		
		Algorithm alg = new AlgorithmRandom();
		String[] programs = {
				"csci"
		};
		ArrayList<String> requiredClasses = new ArrayList<>();
		
		for (String prog : programs)
			requiredClasses.addAll(read.readCurriculum(prog));
		
		schedule = alg.build(programs);
		
		//Trivial usage of schedule.
		System.out.println(schedule);
	}
}