package core;

import display.FinalDisplay;
import test.GenTest;

/**
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
		
		FinalDisplay.initDisplay();
	}
}