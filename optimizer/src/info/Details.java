package info;

public class Details {
	
	private String title;
	private CourseTime time;
	private String prof;
	private double fee;
	private String credits;
	
	// If no fee is given, then assume section/lab costs no money
	public Details(String title, CourseTime time, String prof, String credits) {
		this(title,time,prof,credits,0);
	}
	
	public Details(String title, CourseTime time, String prof, String credits, double fee) {
		this.title = title;
		this.time = time;
		this.prof = prof;
		this.credits = credits;
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
		else if(credits == null || fee < 0)
			System.out.println("Error creating new Details, credits or fee is invalid, terminating...");
		else
			return;
		
		System.exit(1);
	}
	
}