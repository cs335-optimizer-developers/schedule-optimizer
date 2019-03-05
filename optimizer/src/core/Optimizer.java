package core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import algorithm.*;
import info.Course;
import info.Semester;

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
		writeSchedule(newSchedule);
	}
	
	public static Optimizer getInstance() {
		if (one_optimizer == null)
			one_optimizer = new Optimizer();
		return one_optimizer;
	}
	
	private void writeSchedule(Semester[] schedule) {
		String source = Sources.generated_schedule;
		Path path = Paths.get(source);
		
		String toWrite = "";
		for (Semester c : schedule) {
			if (c != null) {
				System.out.println(c.sem);
				System.out.println(c.year);

				toWrite += c.sem + " " + c.year;
			}
		}
		toWrite += "\n";
			
		for (Semester s : schedule) {
			if (s != null)
				for (Course c : s.getCourses()) {
					System.out.println(c.toTitle());
					if (c != null) {
						System.out.println(c.toTitle());
						toWrite += c.toTitle() + ",";
					}
				}
			toWrite += "\n";
		}

		try {
			//Make and write to the file.
			Files.deleteIfExists(path);
			Files.createFile(path);
			Files.write(path, toWrite.getBytes());
		}
		catch (IOException e) {e.printStackTrace();}
	}
}