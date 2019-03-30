package io;

import java.util.Map;

import core.Source;

/**
 * 
 * Reads in lists of prerequisites for courses from .cur files. To
 * be used by algorithms in generation of semesters.
 *
 */
public class ReadCur extends Reader {

private String source = Source.details_folder;
	
	public Map<String,String> read(String program) {
		
		input = makeStream(source + program + ".cur");
		Map<String,String> toReturn = new java.util.HashMap<>();
		
		String classNum = "XXX";
		while (moveLine()) {
			if (hasChar('+'))
				classNum = line.substring(1);
			else
				toReturn.put(program.toUpperCase()+classNum,line);
		}
		return toReturn;
	}
}
