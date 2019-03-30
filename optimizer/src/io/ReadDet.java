package io;

import java.util.Map;

import core.Source;

/**
 * 
 * Reads in course descriptions from .det files, probably to be used
 * in FinalDisplay or in creation of files in ReadPopulateCSV.
 *
 */
public class ReadDet extends Reader {

	private String source = Source.details_folder;
	
	public Map<String,String> read() {
		
		input = makeStream(source + "csci.det");
		Map<String,String> toReturn = new java.util.HashMap<>();
		
		String classNum = "XXX";
		while (moveLine()) {
			if (hasChar('+'))
				classNum = line.substring(1);
			else
				toReturn.put("CSCI"+classNum,line);
		}
		return toReturn;
	}
}