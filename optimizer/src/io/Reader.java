package io;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * 
 * Abstract class to read in files to be used at runtime.
 *
 */
public abstract class Reader {
	
	protected Scanner input;
	protected String line;
	
	protected Scanner makeStream(String filename) {
		Scanner scanner = new Scanner("");
		
		try {
			scanner = new Scanner(Paths.get(filename));
		} 
		catch (IOException e) {//e.printStackTrace();
//			System.out.println("Requested: " + filename + " not found.");
		}
		
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