package info;

public abstract class ClassType {
	private ClassDetails internal;
	
	public ClassType(ClassDetails internal) {
		if(internal==null) {
			System.out.println("Error when creating a new lab, Details cannot be null... terminating");
			System.exit(1);
		}
		this.internal = internal;
	}
	
	public String toString() {
		return internal.toString();
	}
	
	public String getTime() {
		return internal.getTime();
	}
	
	public String getProf() {
		return internal.getProf();
	}
	
	public String getQuad() {
		return internal.getQuad();
	}
	
}
