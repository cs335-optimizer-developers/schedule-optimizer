package core;

/**
 * Reads the two CSV course files, then populates a new data file. Will be separated/removed in final ver.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import info.ClassType;
import info.Course;
import info.ClassTime;
import info.Day;
import info.ClassDetails;
import info.Lab;
import info.Quad;
import info.Section;
import info.Subject;
import info.Tag;

public class ReadPopulateCSV {

	public static void main(String[] args) {

		String csvFile = "input/schedules/fall-2018.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {
			br = new BufferedReader(new FileReader(csvFile));
			
			ClassTime meetingTimes;
			ClassDetails details;
			Course c;
			
			
			/* Algorithm
			 * 		1. Create CourseTime and Details object for each section
			 * 		2. Create the Section object or Lab object accordingly
			 * 		3. Check if the Section object is from a new Class, or an already instantiated Class
			 * 			3.a If new Class, then create new Class object, then add the Section to it.
			 * 			3.a Else add the section or lab to the correct class.
			 * 		(Assumed class is created before labs are created)
			*/
			while ((line = br.readLine()) != null) {
				// Use comma as separator
				String[] data = line.split(cvsSplitBy);
				
				// The CourseTime of this particular course (lab/section)
				meetingTimes = new ClassTime(data[6], parseDays(data[7]), parseQuad(data[3]));
				// The Details object of this particular course (lab/section)
				details = new ClassDetails(data[4], meetingTimes, data[8], data[5], data[9].equals("") ? 0.0 : Double.parseDouble(data[9]));
				
				ClassType type;
				int classNum;
				
				// Check if analyzing lab
				if(data[1].length() == 4) {
					type = new Lab(details);
					classNum = Integer.parseInt(data[1].substring(0, 3));
				}
				
				// Else analyzing a section
				else {
					type = new Section(details, Integer.parseInt(data[2]));
					classNum = Integer.parseInt(data[1]);
				}
				
				// TODO: Check if the Section object is from a new Class, or an already instantiated Class
				
				// Correct subject format (remove spaces)
				String subj = data[0].replaceAll("\\s","");
				
				// New Class
				c = new Course(Subject.valueOf(subj),classNum,(Section) type,parseTags(data[9]));
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
	
	/**
	 * Parse the string that has been extracted from the data set to a Quad enum
	 * 	If not A or B quad then assume it is a full semester class.
	 * @param s, the extracted String
	 * @return the corresponding Quad enum value (A or B) or NONE if a full semester class.
	 */
	private static Quad parseQuad(String s) {
		Quad q;
		try {
			q = Quad.valueOf(s);
		} catch(IllegalArgumentException iae) {
			q = Quad.NONE;
		}
		
		return q;
	}

	/**
	 * Parse the string that has been extracted from the data set to a Day array of Enums
	 * @param s, the extracted String
	 * @return the corresponding Quad enum value (A or B) or NONE if a full semester class.
	 */
	private static ArrayList<Day> parseDays(String s) {
		ArrayList<Day> days = new ArrayList<Day>();
		// If no day was provided in the data set for this particular class, then add blank day
		if(s.length()==0) {
			days.add(Day.BLANK);
			return days;
		}
		
		for(int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if(c == ' ')
				continue;
			
			try {
				Day d = Day.valueOf(c+"");
				days.add(d);
				
			} catch(IllegalArgumentException iae) {
				iae.printStackTrace();
			}
		}
		
		return days;
	}
	
	private static ArrayList<Tag> parseTags(String s) {
		return null;
	}

}