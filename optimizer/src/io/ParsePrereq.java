package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import info.Course;
import info.CourseKey;
import info.Subject;

public class ParsePrereq {

	public static HashMap<CourseKey, List<CourseKey>> parsePrereq() {
		HashMap<CourseKey, List<CourseKey>> courses = new HashMap<CourseKey, List<CourseKey>>();
		
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("src/io/prereqs.txt"));
			String line = reader.readLine();
			while (line != null) {
				line = reader.readLine();
				if(line == null)
					break;
				
				
				CourseKey key = null;
				
				// Parse the subject and number for each line.
				String subjNum = line.substring(0, line.indexOf(','));
				int i = 0;
				while (i < subjNum.length() && !Character.isDigit(subjNum.charAt(i)))
					i++;
				try {
					key = new CourseKey(Subject.valueOf(subjNum.substring(0, i)), Integer.parseInt(subjNum.substring(i)));
				} 
				// Ignore lab prereqs and unknown subjects
				catch(Exception nfe) {continue;}
				
				List<CourseKey> prereqs = new ArrayList<CourseKey>();
				if(key != null) {
					String preLine = line.substring(line.indexOf(' ')+1);
					// No prereqs
					if(preLine.length() < 4)
						continue;
					
					String subSubj = "";
					String subNum = "";
					for(int j = 0; j < preLine.length(); j++) {
						char c = preLine.charAt(j);
						if(c != ' ') {
							if(Character.isDigit(c))
								subNum += c;
							else
								subSubj += c;
						}
						else {
							CourseKey ck = null;
							try {
								 ck = new CourseKey(Subject.valueOf(subSubj), Integer.parseInt(subNum));
							} catch(IllegalArgumentException iae) {continue;}

							prereqs.add(ck);
							subSubj = "";
							subNum = "";
						}
					}
				}
				
				courses.put(key, prereqs);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return courses;
	}
	
}
