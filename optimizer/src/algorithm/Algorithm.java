package algorithm;

import display.DParam;
import info.Semester;

/**
 * 
 * Interface that handles the construction of semester objects based
 * on parameters that are passed from FinalDisplay through the Optimizer
 * class.
 * 
 * @author James White
 *
 */
public interface Algorithm {
	Semester[] build(DParam dpar);
}