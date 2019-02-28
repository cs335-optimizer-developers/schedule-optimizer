package core;

/**
 * Reads the two CSV course files, then populates a new data file. Will be separated/removed in final ver.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import info.ClassType;
import info.Course;
import info.ClassTime;
import info.Day;
import info.ClassDetails;
import info.Lab;
import info.Quad;
import info.Section;
import info.Semester;
import info.Subject;
import info.Tag;

public class ReadPopulateCSV {

	public static void main(String[] args) {
		
		Semester fall18 = createSemester("input/schedules/fall-2018.csv");
		fall18.setSemester("Fall");
		fall18.setYear(2018);
		
		Semester spring19 = createSemester("input/schedules/spring-2019.csv");
		spring19.setSemester("Spring");
		spring19.setYear(2019);
	}
	
	/**
	 * Create a semester object from the given csv file
	 * @param csvFile, a course schedule which will be parsed into a semester
	 * @return a new semester object, filled with courses, sections, and labs
	 */
	private static Semester createSemester(String csvFile) {
		BufferedReader br = null;
		String line = "";
		List<Course> courses = new ArrayList<Course>();
		
		System.out.println("Starting parse of: " + csvFile);
		try {
			br = new BufferedReader(new FileReader(csvFile));
			
			/* Algorithm
			 * 		1. Create CourseTime and Details object for each section
			 * 		2. Create the Section object or Lab object accordingly
			 * 		3. Check if the Section object is from a new Class, or an already instantiated Class
			 * 			3.a If new Class, then create new Class object, then add the Section to it.
			 * 			3.a Else add the section or lab to the correct class.
			 * 		(Assumed class is created before labs are created)
			*/
			// Skip first line containing the heading
			line = br.readLine();
			while ((line = br.readLine()) != null) {
				// System.out.println(line);
				// Use comma as separator
				String[] data = line.split(",");
				
				// Check for irregular comma separation
				if (data.length > 11 || data.length < 10) {
					System.out.printf("Irregular data length\n\n");
					System.exit(1);
					continue;
				}
				Course c;
				
				ClassTime meetingTimes = new ClassTime(data[6], parseDays(data[7]), parseQuad(data[3]));
				// Parse the fee
				double fee = 0;
				if (!data[9].isEmpty())
					fee = Double.parseDouble(data[9]);
				
				// The Details object of this particular course (lab/section)
				ClassDetails details = new ClassDetails(data[4], meetingTimes, data[8]+data[9], data[5], fee);
				
				ClassType type;
				int number;
				
				// Check if analyzing lab
				if(data[1].length() == 4) {
					type = new Lab(details);
					number = Integer.parseInt(data[1].substring(0, 3));
				}
				
				// Else analyzing a section
				else {
					type = new Section(details, Integer.parseInt(data[2]));
					number = Integer.parseInt(data[1]);
				}
				
				// Correct subject format (remove spaces, C E = CE)
				String subj = data[0].replaceAll("\\s","");
				String key = Subject.valueOf(subj).toString()+":"+number;
				
				// TODO: Check if the Section object is from a new Class, or an already instantiated Class
				boolean flag = false;
				Iterator<Course> it = courses.iterator();
				while(it.hasNext()) {
					Course cr = it.next();
					// Already Created... add lab/section
					if(cr.getKey().equals(key)) {
						// Lab
						if(data[1].length() == 4)
							cr.addLab(type);
						// Section
						else
							cr.addSection(type);
						flag = true;
						break;
					}
				}
				
				if(flag)
					continue;
				// New Course is created for the section
				c = new Course(Subject.valueOf(subj),number,type,parseTags(data[10]));
				courses.add(c);
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
		
		System.out.println("Successful!\n");
		System.out.println(courses.get(123).toString());
		return new Semester(courses);
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
		if((s.length()==0) || s.equals("TBA") || s.equals("BLANK")) {
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
	
	/**
	 * Parse the tags String from the schedule csv
	 * @param s, a String, one tags list from the csv
	 * @return ArrayList of tags from the String
	 */
	private static ArrayList<Tag> parseTags(String s) {
		ArrayList<Tag> tags = new ArrayList<>();
		String curr = "";
			try {
				for(int i = 0; i < s.length(); i++) {
					char c = s.charAt(i);
					if(c == '\'') {
						tags.add(Tag.valueOf(curr));
						curr = "";
					}
					else if(c == ' ') {
						curr = "";
						continue;
					}
					else
						curr += c;
				}
				
				tags.add(Tag.valueOf(curr));
				
			} catch(IllegalArgumentException iae) {
				if(curr.equals("NONE") || tags.isEmpty())
					tags.add(Tag.NONE);
				else {
					iae.printStackTrace();
					System.exit(1);
				}
			}
		return tags;
	}

}