package core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import algorithm.*;
import info.Course;
import info.Semester;
import reader.ReadDet;

public class Optimizer {

	String input;
	String output;
	
	public Optimizer(String input, String output) {
		this.input = input;
		this.output = output;
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
				
		Semester[] availableClasses = ReadPopulateCSV.buildSemesters(input);
				
		Semester[] newSchedule = alg.build(availableClasses,programs);
		
		//Writing schedule to local file.
		writeSchedule(newSchedule);
	}
	
	private void writeSchedule(Semester[] schedule) {
		String source = "./optimizer/"+output+"/"+"generated-schedule.csv";
		Path path = Paths.get(source);
		
		//Map<String,String> desc = new ReadDet().read();

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