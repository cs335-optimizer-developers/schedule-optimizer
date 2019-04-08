package io;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import info.Course;


public class CsvParser {

	public Map<String, Course> parseCsv() throws IOException {
		Map<String, Course> cMap = new HashMap<String, Course>();

		File file = new File("fall-2018.csv");
		Scanner input = new Scanner(file);
		input.nextLine();
		int i = 1;
		while(input.hasNext()) {
			String data = input.nextLine();
			String [] values = data.split(",");
			
			String current = values[0] + " " + values [1];
			String rest = values[2] + " " + values[3] + " " +  values[4] + " " + values[5] 
					+ " " + values[6] + " " + values[7] +" " +  values[8] + " " + values[9] +" " +  values[10]; 
			
			
			if(cMap.containsKey(current)) {
				cMap.put(current + "(" + i + ")", new Course(current, rest));
				i ++;

			}else {
				cMap.put(current, new Course(current, rest));
			}
							
		}
		
		input.close();	
		return cMap;
		
		}
	}


