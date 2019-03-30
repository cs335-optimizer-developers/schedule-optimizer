package io;

import java.util.Set;

import core.Source;

/**
 * 
 * Used by algorithm to produce list of classes that need to be taken
 * in each possible program.
 *
 */
public class ReadPrg extends Reader {

	private String source = Source.programs_folder;
	
	public Set<String> read(String prog) {
				
		input = makeStream(source + prog + ".prg");
		Set<String> toReturn = new java.util.HashSet<>();
		
		String prgName = "";
		while (moveLine()) {
			if (hasChar('+'))
				prgName = line.substring(1);
			else
				toReturn.add(prgName+line);
		}
		return toReturn;
	}
}