package core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import algorithm.*;
import info.Semester;
import reader.ReadDet;

public class Optimizer {

	String input;
	String output;
	Semester[] availableClasses;
	
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
				"csci-major"
		};
		
		Semester[] newSchedule = alg.build(availableClasses,programs);
		
		//Writing schedule to local file.
		writeSchedule(newSchedule);
	}
	
	private void writeSchedule(Semester[] schedule) {
		String source = "./"+output+"/"+"generated-schedule.csv";
		Path path = Paths.get(source);
		
		Map<String,String> desc = new ReadDet().read();

		String toWrite = "";
		for (int i=0;i<schedule.length;i++)
			toWrite += "Semester "+(i+1)+",";
		toWrite += "\n";
			
		/*
		for (int i=0;i<schedule[0].length;i++) {
			for (int j=0;j<schedule.length;j++) {
			String add = schedule[j][i];
			if (add != null)
				toWrite += add+" - "+desc.get(add);
			toWrite += ",";
			}
			toWrite += "\n";
		}
		*/
		try {
			//Make and write to the file.
			Files.deleteIfExists(path);
			Files.createFile(path);
			Files.write(path, toWrite.getBytes());
		}
		catch (IOException e) {e.printStackTrace();}
	}
}