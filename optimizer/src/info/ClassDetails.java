package info;

public class ClassDetails {
	
	private String title;
	private ClassTime time;
	private String prof;
	private double fee;
	
	// If no fee is given, then assume section/lab costs no money
	public ClassDetails(String title, ClassTime time, String prof) {
		this(title,time,prof,0);
	}
	
	public ClassDetails(String title, ClassTime time, String prof, double fee) {
		this.title = title;
		this.time = time;
		this.prof = prof;
		this.fee = fee;
		validateDetails();
	}

	/**
	 * Validate the creation parameters, if invalid then terminate.
	 */
	private void validateDetails() {
		if(title == null)
			System.out.println("Error creating new Details, title cannot be null, terminating...");
		else if(time == null)
			System.out.println("Error creating new Details, time cannot be null, terminating...");
		else if(prof == null)
			System.out.println("Error creating new Details, prof cannot be null, terminating...");
		else
			return;
		
		System.exit(1);
	}
	
	public String toString() {
		return "Title: " + title + "\nTime: " + time + "\nProf: " + prof;
	}
	
	public String getTime() {
		return time.getTime();
	}
	
	public String getProf() {
		return prof;
	}

	public String getQuad() {
		return time.getQuad();
	}
	
}