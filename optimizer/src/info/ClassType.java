package info;

public abstract class ClassType {
	private Details internal;
	
	public ClassType(Details internal) {
		if(internal==null) {
			System.out.println("Error when creating a new lab, Details cannot be null... terminating");
			System.exit(1);
		}
		this.internal = internal;
	}
}
