package io;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * 
 * @author jw-develop
 *
 */
public abstract class Reader {
	
	protected Scanner input;
	protected String line;
	
	protected Scanner makeStream(String filename) {
		Scanner scanner = null;
		
		try {
			scanner = new Scanner(Paths.get(filename));
		} 
		catch (IOException e) {e.printStackTrace();}
		
		return scanner;
    }
	
	protected boolean moveLine() {
        if (!input.hasNextLine())
			return false;
		line = input.nextLine();
		if (line.equals(""))
        	moveLine();
		return true;
	}
	
	protected boolean hasChar(char c) {
		return line.charAt(0) == c;
	}
}