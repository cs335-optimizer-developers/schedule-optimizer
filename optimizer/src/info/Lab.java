package info;

public class Lab {
	private Details internal;
	
	public Lab(Details internal) {
		if(internal==null) {
			System.out.println("Error when creating a new lab, Details cannot be null... terminating");
			System.exit(1);
		}	
	}

}
