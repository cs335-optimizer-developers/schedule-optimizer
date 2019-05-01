package core;

import display.FinalDisplay;
import test.GenTest;

/**
 * 
 * Main class - initiates optimizer and display to start.
 * 
 * Contains a boolean option for debugging. If set to false,
 * System.out will be redirected to an error log, preventing 
 * the program from printing to the console that it is ran from.
 * 
 * @author James White
 *
 */
public class RunOptimizer {
	
	public static void main(String[] args) {
		
		//If false, System will be redirected to error-log.txt.
		boolean debug = true;
		
		if (debug != true)
			GenTest.errorLogRedirect();
		
		Optimizer opt = Optimizer.getInstance();
		opt.generate();
		
//		FinalDisplay.initDisplay();
	}
}