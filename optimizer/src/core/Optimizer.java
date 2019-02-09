package core;

import info.Semester;

public class Optimizer {

	String input;
	String output;
	
	public Optimizer(String input, String output) {
		this.input = input;
		this.output = output;
	}

	public void generate() {
		Reader reader = new Reader(input);
		
		Algorithm alg = new AlgorithmRandom();
		Semester[] schedule;
		schedule = alg.build(reader.classes,reader.curriculum);
		
		//Trivial usage of schedule.
		System.out.println(schedule);
	}
}