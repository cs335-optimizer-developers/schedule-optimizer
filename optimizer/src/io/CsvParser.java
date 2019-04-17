package io;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import core.Source;
import info.ClassDetails;
import info.ClassTime;
import info.ClassType;
import info.Course;
import info.Day;
import info.Lab;
import info.Quad;
import info.Section;
import info.Subject;
import info.Tag;



public class CsvParser {

	public Map<String, Course> parseCsv() throws IOException {
		Map<String, Course> cMap = new HashMap<String, Course>();
		List<Course> courses = new ArrayList<Course>();


		File file = new File(Source.fall_2018);
		Scanner input = new Scanner(file);
		input.nextLine();
		int i = 1;
		//String current= "";
		String rest = "";
		while(input.hasNext()) {
			String values = input.nextLine();
			String [] data = values.split(",");
			
			 String current = data[0] + " " + data [1];
			 
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
				
			if(cMap.containsKey(current)) {
				if(data[1].length() == 4)
					cMap.get(current).addLab(type);
				else
					cMap.get(current).addSection(type);
			}else {
				c = new Course(Subject.valueOf(subj),number,type,parseTags(data[10]));
				cMap.put(current, c);
			}	
		}
		
		input.close();
		return cMap;
		}
	
	private static Quad parseQuad(String s) {
		Quad q;
		try {
			q = Quad.valueOf(s);
		} catch(IllegalArgumentException iae) {
			q = Quad.FULL;
		}
		
		return q;
	}
	
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


