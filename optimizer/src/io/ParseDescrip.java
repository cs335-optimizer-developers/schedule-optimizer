package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ParseDescrip {

	public static HashMap<String, String> parseDescrip() {
		HashMap<String, String> courses = new HashMap<String, String>();
	
		BufferedReader reader;
		
		try {
			reader = new BufferedReader(new FileReader("src/io/descriptions.txt"));
			String line = reader.readLine();
			while (line != null) {
				line = reader.readLine();
				if(line == null)
					break;
				
				// Parse the subject and number for each line.
				String key = line.substring(0, line.indexOf(','));
				
				String descrips = line.substring(line.indexOf(','));
				if(key != null) {
					String preLine = line.substring(line.indexOf(' ')+1);
					// No descriptions
					if(preLine.length() < 4)
						continue;
					
					String course = " ";
					for(int j = 0; j < preLine.length(); j++) {
						char c = preLine.charAt(j);
						if(c != ' ')
							course+=c;
						else {
							//descrips.add(course);
							course = "";
						}
					}
				}
				courses.put(key, descrips);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return courses;
	
	}
}
