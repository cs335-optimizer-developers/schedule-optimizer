package optimizer;

public class RunOptimizer {
	
	public static void main(String[] args) {
		
		System.out.println("Initialized");
		
		Optimizer opt = new Optimizer("input","output");
		opt.generate();
		
		System.out.println("Completed");

	}
}