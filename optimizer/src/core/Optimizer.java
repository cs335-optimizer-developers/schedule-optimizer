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
				"csci-major"
		};
		
		schedule = alg.build(programs);
		
		//Writing schedule to local file.
		writeSchedule();
	}
	
	@SuppressWarnings("unused")
	private void testSchedule() {
		schedule = new String[4][4];
		for (int i = 0;i<4;i++)
			for (int j=0;j<4;j++)
				schedule[i][j] = i+j+" class";
		schedule[3][0] = "Data Structures";
		schedule[0][1] = "DMFP";
		schedule[0][3] = "Programming I";
		schedule[1][2] = "Programming II";
	}
	
	private void writeSchedule() {
		String source = "./"+output+"/"+"generated-schedule.csv";
		Path path = Paths.get(source);

		String toWrite = "";
		for (int i=0;i<schedule.length;i++) {
			toWrite += "Semester "+i+",";
			for (int j=0;j<schedule[i].length;j++)
				if (schedule[i][j] != null)
					toWrite += schedule[i][j] + ",";
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