package core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import algorithm.*;

public class Optimizer {

	String input;
	String output;
	String[][] schedule;
	
	public Optimizer(String input, String output) {
		this.input = input;
		this.output = output;
	}

	public void generate() {
		
		Algorithm alg = new AlgorithmRandom();
		String[] programs = {
				"csci"
		};
		
		schedule = alg.build(programs);
		
		//Test writing of schedule.
		testSchedule();
		
		//Writing schedule to local file.
		writeSchedule();
	}
	
	private void testSchedule() {
		schedule = new String[4][4];
		for (int i = 0;i<4;i++)
			for (int j=0;j<4;j++)
				schedule[i][j] = i+j+" class";
		schedule[0][2] = "Data Structures";
		schedule[1][3] = "Software Dev";		
	}
	
	private void writeSchedule() {
		String source = "./"+output+"/"+"generated-schedule.csv";
		Path path = Paths.get(source);

		String toWrite = "";
		for (int i=0;i<schedule.length;i++) {
			schedule[i][0] = "Semester "+i;
			for (int j=1;j<schedule[i].length+1;j++)
				toWrite += schedule[i][j-1] + ",";
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