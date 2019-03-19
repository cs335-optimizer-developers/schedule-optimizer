package io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import core.Source;
import info.Course;
import info.Semester;

/**
 * 
 * @author James White
 *
 */
public class Writer {
	
	/**
	 * Converts a schedule to a .csv file.
	 * @param schedule
	 */
	public static void writeSchedule(Semester[] schedule) {
		String source = Source.generated_schedule;
		Path path = Paths.get(source);
		
		String toWrite = "";
		for (Semester s : schedule) {
			//System.out.println("Schedule found");
			if (s != null)
				if (!s.getCourses().isEmpty())
					toWrite += s.sem + " " + s.year + ",";
			else;
				//System.out.println("Null or empty semester detected - no bueno");
		}
		toWrite += "\n";
		
		//The list of courses in each semester.
		List<List<Course>> circuit = new ArrayList<>();
		
		for (Semester s : schedule)
			if (s != null)
				if (!s.getCourses().isEmpty())
					circuit.add(s.getCourses());
		
		//Adding each course to the line it should be on.
		//Done this way because lines must be written together.
		boolean added = true;
		for (int i=0;added;i++) {
			for (List<Course> al : circuit) {
				added = false;
				if (i < al.size()) {
					//System.out.println(al.get(i).toTitle() + " added");
					toWrite += al.get(i).toTitle() + ",";
					added = true;
				}
			}
			if (added)
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
