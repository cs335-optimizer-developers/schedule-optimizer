package core;

import display.FinalDisplay;

/**
 * 
 * @author James White
 *
 */
public class RunOptimizer {
	
	public static void main(String[] args) {
		
		//System.out.println("Initialized");
		
		Optimizer opt = Optimizer.getInstance();
		opt.generate();
		
		FinalDisplay.initDisplay();
		
		//System.out.println("Completed");
	}
}