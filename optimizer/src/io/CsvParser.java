package io;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import core.Source;
import info.Course;


public class CsvParser {

	public Map<String, Course> parseCsv() throws IOException {
		Map<String, Course> cMap = new HashMap<String, Course>();

		File file = new File(Source.fall_2018);
		Scanner input = new Scanner(file);
		input.nextLine();
		int i = 1;
		String current= "";
		String rest = "";
		while(input.hasNext()) {
			String data = input.nextLine();
			String [] values = data.split(",");
			
			 current = values[0] + " " + values [1];
			 rest = values[2] + "," + values[3] + "," +  values[4] + "," + values[5] 
					+ "," + values[6] + "," + values[7] +"," +  values[8] + "," + values[9] +"," + values[10]; 
			
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
	
	


